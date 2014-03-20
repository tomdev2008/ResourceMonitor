package com.gcj.dzh.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gcj.dzh.bean.ProcessInfoBean;
import com.gcj.dzh.dao.ProcessInfoDao;
import com.gcj.dzh.utils.MybatisUtils;
import com.gcj.dzh.utils.TimeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CPUAndMemMonitor implements Runnable {

    private static final Log LOG = LogFactory.getLog(CPUAndMemMonitor.class);

    private ProcessInfoDao processInfoDao;

    private String cmd;

    private String processName;

    private String processId;

    private int intervalTime;

    private volatile boolean running = true;

    private String ip;

    public CPUAndMemMonitor(String cmd, String processName, String processId, int intervalTime, String ip) {
        super();
        this.cmd = cmd;
        this.processName = processName;
        this.processId = processId;
        this.intervalTime = intervalTime;
        this.ip = ip;
        processInfoDao = MybatisUtils.session.getMapper(ProcessInfoDao.class);
    }

    /**
     * 根据top命令获取进程的CPU使用率
     *
     * @param run
     * @param cmd
     * @return
     */
    private float getCPUUsageByTopCommond(Runtime run, String cmd) {
        float pcpu = -1;
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        Process p = null;
        try {
            p = run.exec(cmd);
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in), 1024);
            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                if (lineStr.contains(processName)) {
                    lineStr = lineStr.replaceAll(" {2,}", " ").trim();
                    String[] array = lineStr.split(" ");
                    String cpu = array[8];
                    pcpu = Float.parseFloat(cpu);
                }
            }
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1) {
                    LOG.error("Failed to perform the command: " + cmd);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (inBr != null)
                    inBr.close();
                if (in != null)
                    in.close();
                if (p.getErrorStream() != null) {
                    p.getErrorStream().close();
                }
                if (p.getInputStream() != null) {
                    p.getInputStream().close();
                }
                if (p.getOutputStream() != null) {
                    p.getOutputStream().close();
                }
                if (p != null) {
                    p.waitFor();
                    p.destroy();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return pcpu;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        MemUsage memUsage = new MemUsage();
        Runtime run = Runtime.getRuntime();
        String memPath = "/proc/" + processId + "/status";
        LOG.info("Reading the file for memory: " + memPath);
        String currentTime;
        File statusFile;
        float pcpu;
        int pmem;
        ProcessInfoBean processInfoBean = new ProcessInfoBean();
        processInfoBean.setLocalHostIp(ip);
        processInfoBean.setProceeName(processName);
        while (running) {
            pcpu = getCPUUsageByTopCommond(run, cmd);
            if (pcpu != -1) {
                statusFile = new File(memPath);
                if (statusFile.exists()) {
                    pmem = memUsage.getMemUsage(statusFile);
                    if (pmem != -1) {
                        currentTime = TimeUtils.getCurrentTime();//获取系统当前时间
                        processInfoBean.setUpdateTime(currentTime);
                        processInfoBean.setCpuUsage(pcpu);
                        processInfoBean.setMemUsage(pmem);
                        processInfoBean.setPid(Integer.parseInt(processId));
                        processInfoDao.insert_process_monitor(processInfoBean);
                        MybatisUtils.session.commit();
                        try {
                            Thread.sleep(intervalTime);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
}
