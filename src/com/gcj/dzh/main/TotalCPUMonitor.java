package com.gcj.dzh.main;

import com.gcj.dzh.bean.ServerMsgBean;
import com.gcj.dzh.dao.ServerMsgDao;
import com.gcj.dzh.utils.MybatisUtils;
import com.gcj.dzh.utils.TimeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * Created by gaochuanjun on 14-3-3.
 */
public class TotalCPUMonitor implements Runnable {

    private static final Log LOG = LogFactory.getLog(TotalCPUMonitor.class);

    private int processId;  //进程ID

    private String processIdStr; //进程ID字符串形式

    private String ip; //本机IP地址

    private String processName;  //进程名

    private volatile boolean running = true;

    private volatile boolean shutdown = true;

    private volatile boolean getPidLoop = true;

    private String cmd; //获取进程CPU使用率的top命令

    private ServerMsgDao serverMsgDao;

    private int intervalTime;  //采集信息的间隔

    /**
     * 构造函数
     *
     * @param ip
     * @param processName
     * @param intervalTime
     */
    public TotalCPUMonitor(String ip, String processName, int intervalTime) {
        this.ip = ip;
        this.processName = processName;
        this.intervalTime = intervalTime;
        serverMsgDao = MybatisUtils.session.getMapper(ServerMsgDao.class);
    }

    /**
     * 根据top命令获取进程的cpu使用率
     *
     * @param run
     * @param serverMsgBean
     * @return
     */
    private boolean getCPUMsgByTop(Runtime run, ServerMsgBean serverMsgBean) {
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        Process p = null;
        boolean result = false;
        try {
            p = run.exec(cmd);
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in), 1024);
            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                if (lineStr.contains(processName)) {
                    result = true;  //如果能够获取到进程的cpu信息，返回true
                    LOG.debug(lineStr);
                    lineStr = lineStr.replaceAll(" {2,}", " ").trim();//去掉字符串中多余的空格，只留一个空格
                    LOG.debug(lineStr);
                    String[] array = lineStr.split(" ");
                    float cpuUsage = Float.parseFloat(array[8]);
                    serverMsgBean.setCpuUsage(cpuUsage);
                }
            }
        } catch (IOException e) {
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
        return result;
    }

    /**
     * 从proc获取进程平均负载信息
     *
     * @param serverMsgBean
     * @return
     */
    public boolean getProcessInfoFromProc(ServerMsgBean serverMsgBean) {
        FileReader reader = null;
        BufferedReader br = null;
        boolean result = false;
        try {
            float oneMinsProcs;
            float fiveMInsProcs;
            float fifteenMinsProcs;
            reader = new FileReader("/proc/loadavg"); //读取来自/proc/loadavg文件中的信息
            br = new BufferedReader(reader);
            String str = br.readLine();
            while (str != null && str != "" && str != " ") {
                String a[] = str.split(" "); //根据空格对字符串进行切分
                oneMinsProcs = Float.parseFloat(a[0]); //第一位为1分钟进程数
                fiveMInsProcs = Float.parseFloat(a[1]); //第二位为5分钟进程数
                fifteenMinsProcs = Float.parseFloat(a[2]); //第三位为15分钟进程数
                serverMsgBean.setOneMinsProcs(oneMinsProcs);
                serverMsgBean.setFiveMinsProcs(fiveMInsProcs);
                serverMsgBean.setFifteenMinsProcs(fifteenMinsProcs);
                str = br.readLine();
            }
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * debug模式
     *
     * @param serverMsgBean
     */
    private void debugMsg(ServerMsgBean serverMsgBean) {
        LOG.debug("LocalHostIp: " + serverMsgBean.getLocalHostIp() + "    pid: "
                + serverMsgBean.getPid() + "  UpdateTime: "
                + serverMsgBean.getUpdateTime() + "   ProceeName: "
                + serverMsgBean.getProceeName() + "   OneMinsProcs: "
                + serverMsgBean.getOneMinsProcs() + "   FiveMinsProcs: "
                + serverMsgBean.getFiveMinsProcs() + " FifteenMinsProcs: "
                + serverMsgBean.getFifteenMinsProcs() + "   CPUUsage: "
                + serverMsgBean.getCpuUsage() + "   IOWaitTime: "
                + serverMsgBean.getIowaitTime() + "   MemUsage: "
                + serverMsgBean.getMemUsage());
    }

    /**
     * 获取进程的pid信息，通过执行脚本/opt/resource_monitor/bin/getpid.sh
     *
     * @return
     */
    public int getPid() {
        int processId = 0;
        Runtime run = Runtime.getRuntime();
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        Process p = null;
        String cmd = "sh /opt/resource_monitor/bin/getpid.sh";
        while (getPidLoop) {
            try {
                p = run.exec(cmd);
                in = new BufferedInputStream(p.getInputStream());
                inBr = new BufferedReader(new InputStreamReader(in), 1024);
                String lineStr;
                while ((lineStr = inBr.readLine()) != null) {
                    processId = Integer.parseInt(lineStr);
                }
            } catch (IOException e) {
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
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果成功获取到进程pid，则退出循环
            if (processId != 0) {
                getPidLoop = false;
            }
            try {
                Thread.sleep(intervalTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return processId;
    }


    @Override
    public void run() {

        MemUsage memUsage = new MemUsage();
        Runtime run = Runtime.getRuntime();
        String memPath;

        String currentTime;
        File statusFile;
        ServerMsgBean serverMsgBean = new ServerMsgBean();
        serverMsgBean.setLocalHostIp(ip);
        serverMsgBean.setProceeName(processName);
        //serverMsgBean.setPid(Integer.parseInt(processId));
        boolean processInfo;
        boolean cpuMsg;
        boolean memInfo;
        while (shutdown) {
            processId = getPid(); //获取进程id
            LOG.info("Process Id: " + processId);
            cmd = "top -p " + processId + " -b -n 1"; //top命令
            LOG.info("Top Command For Collecting CPU & Memory: " + cmd);
            running = true;
            processIdStr = String.valueOf(processId);
            memPath = "/proc/" + processIdStr + "/status";
            LOG.info("Reading the file for memory: " + memPath);
            statusFile = new File(memPath);
            serverMsgBean.setPid(processId);
            while (shutdown && running) {

                currentTime = TimeUtils.getCurrentTime();//获取系统当前时间
                serverMsgBean.setUpdateTime(currentTime);

                //获取进程的负载信息
                processInfo = getProcessInfoFromProc(serverMsgBean);

                //获取进程的cpu信息
                cpuMsg = getCPUMsgByTop(run, serverMsgBean);

                //获取内存信息
                memInfo = memUsage.getMemUsage(statusFile, serverMsgBean);

                debugMsg(serverMsgBean);

                //如果三个信息都获取到了，则存储至数据库
                if (processInfo && cpuMsg && memInfo) {
                    serverMsgDao.insert_server_monitor(serverMsgBean);
                    MybatisUtils.session.commit();

                } else if (!cpuMsg || !memInfo) {//否则，查看是否cpu还是内存没有获取到，若是这两个未获取到，一般是因为进程id改变了
                    running = false;
                    getPidLoop = true;
                }
                try {
                    Thread.sleep(intervalTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {

    }
}