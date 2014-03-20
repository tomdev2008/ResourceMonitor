package com.gcj.dzh.main;

import com.gcj.dzh.http.Traffic;
import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.FileUtils;
import com.gcj.dzh.utils.HostUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA. User: zyj Date: 13-5-15 Time: 下午2:49 To change
 * this template use File | Settings | File Templates.
 */
public class ProcessMain {

    private static final Log LOG = LogFactory.getLog(ProcessMain.class);

    public void start() {
        String configFilePath = "/opt/resource_monitor/conf/infoconfig.properties";
        ConfigUtils conf = new ConfigUtils(configFilePath);
        String pidPath = conf.getString("pidPath");
        String processName = conf.getString("process_name");
        int sampleTime = conf.getInt("sampling_time");
        String localhostIP = HostUtil.getLocalHostIP();
        String pid = FileUtils.getPidOnce(pidPath);
        String cmd = "top -p " + pid + " -b -n 1";
        int listeningPort = conf.getInt("listening_port");
        String networkCardName = conf.getString("network_card_name");
        boolean isCollectCPUAndMemory = conf.getBoolean("collect_cpu_memory");
        boolean isCollectTraffic = conf.getBoolean("collect_traffic");
        boolean isCollectNetstat = conf.getBoolean("collect_netstat");
        boolean isSimpleProcess = conf.getBoolean("simple_process");
        LOG.info("The Path of Process Id: " + pidPath);
        LOG.info("Process Name: " + processName);
        LOG.info("Sample Time: " + sampleTime);
        LOG.info("The IP Address of Localhost: " + localhostIP);
        LOG.info("Process Id: " + pid);
        LOG.info("Top Command For Collecting CPU & Memory: " + cmd);
        LOG.info("The Port for netstat: " + listeningPort);
        LOG.info("The Name of Network Card: " + networkCardName);
        LOG.info("Is Collect CPU & Memory: " + isCollectCPUAndMemory);
        LOG.info("Is Collect Traffic: " + isCollectTraffic);
        LOG.info("Is Collect Netstat: " + isCollectNetstat);
        LOG.info("Is Simple Process: " + isSimpleProcess);
        if (isCollectCPUAndMemory && isSimpleProcess) {
            LOG.info("Starting The Thread for CPU & Memory......");
            new Thread(new CPUAndMemMonitor(cmd, processName, pid, sampleTime, localhostIP)).start();
        } else if (!isSimpleProcess && isCollectCPUAndMemory) {
            String[] array = pid.split(",");
            for (int i = 0; i < array.length; i++) {
                LOG.info("Starting The Thread[" + i + "] for CPU & Memory......");
                new Thread(new CPUAndMemMonitor(cmd, processName, array[i], sampleTime, localhostIP)).start();
            }
        }
        if (isCollectTraffic) {
            LOG.info("Starting The Thread for traffic......");
            new Thread(new Traffic(sampleTime, localhostIP, networkCardName)).start();
        }
        if (isCollectNetstat) {
            LOG.info("Starting The Thread for netstat......");
            new Thread(new NetstatMonitor(localhostIP, sampleTime, listeningPort)).start();
        }
    }

    public static void main(String[] args) {
        ProcessMain processMain = new ProcessMain();
        processMain.start();
    }
}
