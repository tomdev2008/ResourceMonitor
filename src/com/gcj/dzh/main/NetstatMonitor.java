package com.gcj.dzh.main;

import com.gcj.dzh.bean.NetstatBean;
import com.gcj.dzh.dao.ProcessInfoDao;
import com.gcj.dzh.utils.FileUtils;
import com.gcj.dzh.utils.MybatisUtils;
import com.gcj.dzh.utils.TimeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-8 Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
public class NetstatMonitor implements Runnable {

    private static final Log LOG = LogFactory.getLog(NetstatMonitor.class);

    private String localHostIp;

    private ProcessInfoDao processInfoDao;

    private int intervalTime;

    private volatile boolean running = true;

    private int listeningPort;

    public NetstatMonitor(String localHostIp, int intervalTime, int listeningPort) {
        super();
        this.localHostIp = localHostIp;
        this.intervalTime = intervalTime;
        this.listeningPort = listeningPort;
        processInfoDao = MybatisUtils.session.getMapper(ProcessInfoDao.class);
    }

    public int getResult(String cmd) {
        int result = 0;
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        Runtime run = Runtime.getRuntime();
        try {
            Process p = run.exec(cmd);
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in), 1024);
            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                //System.out.println(cmd + "-->" + lineStr);
                result = Integer.parseInt(lineStr);
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
            } catch (IOException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
            }
        }
        return result;
    }

    public void addPermissions(String file) {
        String cmd = "chmod +x " + file;
        Runtime run = Runtime.getRuntime();
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        try {
            Process p = run.exec(cmd);
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in), 1024);
            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                System.out.println(lineStr);
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
            } catch (IOException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
            }
        }
    }

    @Override
    public void run() {
        // To change body of implemented methods use File | Settings | File
        // Templates.
        NetstatBean netstatBean = new NetstatBean();
        netstatBean.setLocalHostIp(localHostIp);
        netstatBean.setPort(listeningPort);
        String totalCmd = "netstat -ant |grep ':" + listeningPort + "' |wc -l";
        String establishedCmd = "netstat -ant |grep ':" + listeningPort + "' |grep 'ESTABLISHED' |wc -l";
        String timeWaitCmd = "netstat -ant |grep ':" + listeningPort + "' |grep 'TIME_WAIT' |wc -l";
        String finWait2Cmd = "netstat -ant |grep ':" + listeningPort + "' | grep 'FIN_WAIT2' |wc -l";
        LOG.info("netstat For All STATE: " + totalCmd);
        LOG.info("netstat For ESTABLISHED STATE：" + establishedCmd);
        LOG.info("netstat For TIME_WAIT STATE：" + timeWaitCmd);
        LOG.info("netstat FOR FIN_WAIT2 STATE：" + finWait2Cmd);
        FileUtils.writeFile("established.sh", establishedCmd);
        FileUtils.writeFile("fin_wait2.sh", finWait2Cmd);
        FileUtils.writeFile("time_wait.sh", timeWaitCmd);
        FileUtils.writeFile("total.sh", totalCmd);
        addPermissions("established.sh");
        addPermissions("fin_wait2.sh");
        addPermissions("time_wait.sh");
        addPermissions("total.sh");
        while (running) {
            try {
                Thread.sleep(intervalTime);
            } catch (InterruptedException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
            }
            int total = getResult("sh total.sh");
            int established = getResult("sh established.sh");
            int timeWait = getResult("sh time_wait.sh");
            int finWait2 = getResult("sh fin_wait2.sh");
            netstatBean.setUpdateTime(TimeUtils.getCurrentTime());
            netstatBean.setTotal(total);
            netstatBean.setEstablished(established);
            netstatBean.setTimeWait(timeWait);
            netstatBean.setFinWait2(finWait2);
            processInfoDao.insert_netstat_monitor(netstatBean);
            MybatisUtils.session.commit();
        }
    }
}
