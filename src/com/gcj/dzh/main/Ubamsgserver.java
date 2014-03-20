package com.gcj.dzh.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.FileUtils;
import com.gcj.dzh.utils.TimeUtils;

public class Ubamsgserver {

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

        Runtime run = Runtime.getRuntime();

        CPUUsage cpuUsage = new CPUUsage();

        MemUsage memUsage = new MemUsage();

        Connection conn = null;

        PreparedStatement pstmt = null;

        Statement stmt = null;

        String driver = "com.mysql.jdbc.Driver";

        String insert_ubamsgserver = "insert into ubamsgserver(updatetime, cpuusage, memusage) values(?,?,?)";

        String delete_ubamsgserver = "delete from ubamsgserver";

        try {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, mysql_user, mysql_password);
                if (conn != null) {
                    // System.out.println("Has been successfully connect to MySQL: "
                    // + mysql_IPAddress);
                } else {
                    System.out.println("Can't successfully connect to MySQL: " + mysql_IPAddress);
                    System.exit(0);
                }
                stmt = conn.createStatement();
                stmt.executeUpdate(delete_ubamsgserver);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        while (true) {
            if (statusFile.exists()) {
                String cmd = "top -p " + pid + " -b -n 1";

                while (true) {
                    int pmem = memUsage.getMemUsage(statusFile);
                    if (pmem == -1) {
                        break;
                    } else {
                        String updateTime = TimeUtils.getCurrentTime();
                        // long updateTime = System.currentTimeMillis();
                        float pcpu = cpuUsage.getCPUUsageByTopCommond(run, cmd, process_name);
                        try {
                            try {
                                Class.forName(driver);
                                conn = DriverManager.getConnection(url, mysql_user, mysql_password);
                                if (conn != null) {
                                    // System.out.println("Has been successfully connect to MySQL: "
                                    // + mysql_IPAddress);
                                } else {
                                    System.out.println("Can't successfully connect to MySQL: " + mysql_IPAddress);
                                    System.exit(0);
                                }
                                pstmt = conn.prepareStatement(insert_ubamsgserver);
                                pstmt.setString(1, updateTime);
                                pstmt.setFloat(2, pcpu);
                                pstmt.setInt(3, pmem);
                                pstmt.execute();
                            } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                            try {
                                if (pstmt != null)
                                    pstmt.close();
                                if (conn != null)
                                    conn.close();
                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        Thread.sleep(intervalTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println(memPath + " 不存在！");
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
    }
}
