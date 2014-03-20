package com.gcj.dzh.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.FileUtils;

public class MonitorInfo implements Runnable {

    private static final Log LOG = LogFactory.getLog(MonitorInfo.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // ConfigUtils config = new
        // ConfigUtils("/opt/proc/infoconfig.properties");

        // String configFilePath = null;
        // if (args[0] == null) {
        // configFilePath = "/opt/proc/infoconfig.properties";
        // } else {
        // configFilePath = args[0];
        // }
        MonitorInfo monitorInfo = new MonitorInfo();
        Thread thread = new Thread(monitorInfo);
        thread.start();

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        String configFilePath = "/opt/proc/infoconfig.properties";
        ConfigUtils config = new ConfigUtils(configFilePath);

        String mysql_IPAddress = config.getString("mysql_IPAddress");

        String mysql_user = config.getString("mysql_user");

        String mysql_password = config.getString("mysql_password");

        String mysql_databaseName = config.getString("mysql_databaseName");

        String url = "jdbc:mysql://" + mysql_IPAddress + ":3306/" + mysql_databaseName;

        String process_name = config.getString("process_name");

        String interval_time = config.getString("interval_time");

        int intervalTime = Integer.parseInt(interval_time) - 1000;

        String pidPath = config.getString("pidPath");

        String pid = FileUtils.getPid(pidPath, intervalTime);

        String memPath = "/proc/" + pid + "/status";

        File statusFile = new File(memPath);

        CPUUsage cpuUsage = new CPUUsage();

        MemUsage memUsage = new MemUsage();

        Connection conn = null;

        PreparedStatement pstmt = null;

        Statement stmt = null;

        String driver = "com.mysql.jdbc.Driver";

        String insert_pushproxyinfo = "insert into pushproxyinfo(updatetime, cpuusage, memusage) values(?,?,?)";

        String delete_pushproxyinfo = "delete from pushproxyinfo";

        java.util.Date today = new java.util.Date();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, mysql_user, mysql_password);
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                stmt.executeUpdate(delete_pushproxyinfo);
                conn.commit();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            pstmt = conn.prepareStatement(insert_pushproxyinfo);
            int pmem = -1;
            String updateTime;
            float pcpu;
            // String cmd;
            boolean fileLock = true;
            boolean isRun = true;
            short cpuS = cpuUsage.getCPUs();
            String processPath = "/proc/" + pid + "/stat";
            String path = "/proc/stat";
            while (fileLock) {
                if (statusFile.exists()) {
                    // cmd = "top -p " + pid + " -b -n 1";
                    isRun = true;
                    while (isRun) {
                        pmem = memUsage.getMemUsage(statusFile);
                        if (pmem == -1) {
                            isRun = false;
                        } else {
                            today = new java.util.Date();
                            updateTime = f.format(today);

                            // long updateTime = System.currentTimeMillis();
                            // pcpu = cpuUsage.getCPUUsageByTopCommond(run, cmd,
                            // process_name);
                            double cpuD = cpuUsage.calUsage(processPath, path, cpuS, 1000);
                            pcpu = (float) (Math.round(cpuD * 100)) / 100;
                            pstmt.setString(1, updateTime);
                            pstmt.setFloat(2, pcpu);
                            pstmt.setInt(3, pmem);
                            pstmt.executeUpdate();
                            conn.commit();
                        }
                        try {
                            Thread.sleep(intervalTime);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    LOG.info(memPath + " does not exist!");
                    try {
                        Thread.sleep(intervalTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    pid = FileUtils.getPid(pidPath, intervalTime);
                    memPath = "/proc/" + pid + "/status";
                    statusFile = new File(memPath);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
