package com.gcj.dzh.main;

import com.gcj.dzh.bean.ProcessInfoBean;
import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.FileUtils;
import com.gcj.dzh.utils.JDBCUtils;
import com.gcj.dzh.utils.TimeUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA. User: zyj Date: 13-5-16 Time: 下午2:38 To change
 * this template use File | Settings | File Templates.
 */
public class ProcessInfo implements Runnable {

    private String filePath;

    public ProcessInfo(String filePath) {
        super();
        this.filePath = filePath;
    }

    public void writeDataToDB(String pid, String processName, String mysql_IPAddress, String mysql_user, String mysql_password, String mysql_databaseName, ProcessInfoBean processInfoBean, String localHostIp) {

        String url = "jdbc:mysql://" + mysql_IPAddress + ":3306/" + mysql_databaseName;
        Connection conn = JDBCUtils.connectToMySQL(url, mysql_user, mysql_password);
        String sql = "insert into processinfo(localIP, pid, processname, updatetime, cpuusage, memusage) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            // List<ProcessInfoBean> processInfoBeanList =
            // serverData.getTopCommondContents(pid, processName, localHostIp);
            pstmt = conn.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
            // for (ProcessInfoBean processInfoBean : processInfoBeanList) {
            /*
			 * System.out.println("pid: " + processInfoBean.getPid() +
			 * "     processName: " + processName + "     " + "UpdateTime: " +
			 * processInfoBean.getUpdateTime() + "     CPUUsage: " +
			 * processInfoBean.getCpuUsage() + "     MemUsage: " +
			 * processInfoBean.getMemUsage());
			 */
            pstmt.setString(1, processInfoBean.getLocalHostIp());
            pstmt.setInt(2, processInfoBean.getPid());
            pstmt.setString(3, processInfoBean.getProceeName());
            pstmt.setString(4, processInfoBean.getUpdateTime());
            pstmt.setFloat(5, processInfoBean.getCpuUsage());
            pstmt.setInt(6, processInfoBean.getMemUsage());
            pstmt.execute();
            // System.out.println("OK");
            // }

        } catch (SQLException e) {
            e.printStackTrace(); // To change body of catch statement use File |
            // Settings | File Templates.
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
            }
        }
    }

    public void writeDataToOracle(String oracle_IPAddress, String oracle_user, String oracle_password, String oracle_databaseName, ProcessInfoBean processInfoBean) {

        String url = "jdbc:oracle:thin:@" + oracle_IPAddress + ":1521:" + oracle_databaseName;
        Connection conn = JDBCUtils.connectToOracle(url, oracle_user, oracle_password);
        String sql = "insert into pushproxyinfo(updatetime, cpuusage, memusage) values(?,?,?)";
        PreparedStatement pstmt = null;

        try {
            // List<ProcessInfoBean> processInfoBeanList =
            // serverData.getTopCommondContents(pid, processName, localHostIp);
            pstmt = conn.prepareStatement(sql);
            // for (ProcessInfoBean processInfoBean : processInfoBeanList) {
			/*
			 * System.out.println("pid: " + processInfoBean.getPid() +
			 * "     processName: " + processName + "     " + "UpdateTime: " +
			 * processInfoBean.getUpdateTime() + "     CPUUsage: " +
			 * processInfoBean.getCpuUsage() + "     MemUsage: " +
			 * processInfoBean.getMemUsage());
			 */
            pstmt.setString(1, processInfoBean.getUpdateTime());
            pstmt.setFloat(2, processInfoBean.getCpuUsage());
            pstmt.setInt(3, processInfoBean.getMemUsage());
            pstmt.execute();
            // System.out.println("OK");
            // }

        } catch (SQLException e) {
            e.printStackTrace(); // To change body of catch statement use File |
            // Settings | File Templates.
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
            }
        }
    }

    public void deleteTable(String tableName, String oracle_IPAddress, String oracle_user, String oracle_password, String oracle_databaseName) {
        String url = "jdbc:oracle:thin:@" + oracle_IPAddress + ":1521:" + oracle_databaseName;
        Connection conn = JDBCUtils.connectToOracle(url, oracle_user, oracle_password);
        String sql = "delete * from " + tableName;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
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
    }

    public String getPid(String pidPath) {
        String pidS = FileUtils.getPid(pidPath, 5000);
        if (pidS == null || pidS.endsWith(" ") || pidS == "") {
            System.out.println("Can't get the pid!");
            // System.exit(0);
        }
        return pidS;
    }

    public static void main(String[] args) {
        new Thread(new ProcessInfo(args[0])).start();
    }

    @Override
    public void run() {
        // To change body of implemented methods use File | Settings | File
        // Templates.
        ConfigUtils config = new ConfigUtils("/opt/proc/infoconfig.properties");

        String mysql_IPAddress = config.getString("mysql_IPAddress");

        String mysql_user = config.getString("mysql_user");

        String mysql_password = config.getString("mysql_password");

        String mysql_databaseName = config.getString("mysql_databaseName");

        String oracle_IPAddress = config.getString("oracle_IPAddress");

        String oracle_user = config.getString("oracle_user");

        String oracle_password = config.getString("oracle_password");

        String oracle_databaseName = config.getString("mysql_databaseName");

        String process_name = config.getString("process_name");

        String interval_time = config.getString("interval_time");

        int intervalTime = Integer.parseInt(interval_time) - 1000;

        String localhost_ip = config.getString("localhost_ip");

        String pidPath = config.getString("pidPath");

        String pid = getPid(pidPath);

        String processPath = "/proc/" + pid + "/stat";

        // String path = "/proc/stat";

        String memPath = "/proc/" + pid + "/status";

        CPUUsage cpuUsage = new CPUUsage();

        MemUsage memUsage = new MemUsage();

        // short cpuS = cpuUsage.getCPUs();

        boolean signal = true;

        boolean debug = false;

        deleteTable("pushproxyinfo", oracle_IPAddress, oracle_user, oracle_password, oracle_databaseName);

        try {
            while (true) {
                while (signal) {
                    File file1 = new File(processPath);
                    File file2 = new File(memPath);
                    if (file1.exists() && file2.exists()) {
                        signal = false;
                    } else {
                        pid = getPid(pidPath);
                        processPath = "/proc/" + pid + "/stat";
                        memPath = "/proc/" + pid + "/status";
                        debug = true;
                    }
                }

                if (debug) {
                    System.out.println(process_name + " died!Retry get the pid :" + pid);
                    debug = false;
                }

                // float pcpu = cpuUsage.calUsage(processPath, path, cpuS,1000);
                float pcpu = cpuUsage.getCPUUsageByTopCommond(pid, process_name, filePath);

                int pmem = memUsage.getMemUsage(memPath);

                ProcessInfoBean processInfoBean = new ProcessInfoBean();
                processInfoBean.setPid(Integer.parseInt(pid));
                processInfoBean.setProceeName(process_name);
                processInfoBean.setLocalHostIp(localhost_ip);
                String updateTime = TimeUtils.getCurrentTime();
                processInfoBean.setUpdateTime(updateTime);
                processInfoBean.setCpuUsage(pcpu);
                processInfoBean.setMemUsage(pmem);

                // writeDataToDB(pid, process_name, mysql_IPAddress, mysql_user,
                // mysql_password, mysql_databaseName, processInfoBean,
                // localhost_ip);
                writeDataToOracle(oracle_IPAddress, oracle_user, oracle_password, oracle_databaseName, processInfoBean);

                Thread.sleep(intervalTime);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
