package com.gcj.dzh.main;

import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.HostUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by gaochuanjun on 14-3-3.
 */
public class ServerMain {

    private static final Log LOG = LogFactory.getLog(ServerMain.class);

    /**
     * 程序的开始
     */
    public void start() {
        String configFilePath = "/opt/resource_monitor/conf/infoconfig.properties";
        ConfigUtils conf = new ConfigUtils(configFilePath);
        //String pidPath = conf.getString("pidPath");
        String processName = conf.getString("process_name");
        int sampleTime = conf.getInt("sampling_time");
        String localhostIP = HostUtil.getLocalHostIP();
        //String pid = FileUtils.getPidOnce(pidPath);
        //String cmd = "top -p " + pid + " -b -n 1";
        //int listeningPort = conf.getInt("listening_port");
        //String networkCardName = conf.getString("network_card_name");
        //LOG.info("The Path of Process Id: " + pidPath);
        LOG.info("Process Name: " + processName);
        LOG.info("Sample Time: " + sampleTime);
        LOG.info("The IP Address of Localhost: " + localhostIP);
        //LOG.info("Process Id: " + pid);
        //LOG.info("Top Command For Collecting CPU & Memory: " + cmd);
        //LOG.info("The Port for netstat: " + listeningPort);
        //LOG.info("The Name of Network Card: " + networkCardName);
        LOG.info("Starting The Thread for CPU & Memory......");
        //new Thread(new TotalCPUMonitor(pid, localhostIP, processName, cmd, sampleTime)).start();
        new Thread(new TotalCPUMonitor(localhostIP, processName, sampleTime)).start();
    }


    public static void main(String[] args) {
        ServerMain serverMain = new ServerMain();
        serverMain.start();
    }
}