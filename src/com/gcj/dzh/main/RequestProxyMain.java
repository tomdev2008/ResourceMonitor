package com.gcj.dzh.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gcj.dzh.main.CPUUsage;
import com.gcj.dzh.main.MemUsage;
import com.gcj.dzh.utils.Constants;
import com.gcj.dzh.utils.FileUtils;
import com.gcj.dzh.utils.SQL;
import com.gcj.dzh.utils.SystemUtils;
import com.gcj.dzh.utils.TimeUtils;

public class RequestProxyMain implements Runnable {

	private Connection conn;

	private PreparedStatement pstmt;

	Constants constants;

	private int intervalTime;

	public RequestProxyMain() {
		super();
		try {
			Class.forName(Constants.mysql_driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SystemUtils.closeJVM();
		}
		constants = new Constants();
		constants.getAllConfigInfo();
		this.intervalTime = Integer.parseInt(constants.getInterval_time()) - 1000;
	}

	public void initDB() {
		try {
			conn = DriverManager.getConnection(constants.getUrl(), constants.getMysql_user(), constants.getMysql_password());
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL.insert_request_proxy_info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SystemUtils.closeJVM();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		initDB();
		String pid = FileUtils.getPidOnce(constants.getPidPath());
		String memPath = "/proc/" + pid + "/status";
		String processPath = "/proc/" + pid + "/stat";
		String path = "/proc/stat";
		File statusFile = new File(memPath);
		CPUUsage cpuUsage = new CPUUsage();
		MemUsage memUsage = new MemUsage();
		short cpuS = cpuUsage.getCPUs();
		String currentTime;
		int pmem = -1;
		float pcpu;
		try {
			while (true) {
				pmem = memUsage.getMemUsage(statusFile);
				currentTime = TimeUtils.getCurrentTime();
				// System.out.println(currentTime);
				double cpuD = cpuUsage.calUsage(processPath, path, cpuS, 1000);
				// System.out.println(cpuD);
				pcpu = round(cpuD);
				pstmt.setString(1, currentTime);
				pstmt.setFloat(2, pcpu);
				pstmt.setInt(3, pmem);
				pstmt.executeUpdate();
				conn.commit();
				try {
					Thread.sleep(this.intervalTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SystemUtils.closeJVM();
		} finally {
			closeDBConnection();
		}
	}

	private float round(double value) {
		return (float) (Math.round(value * 100)) / 100;
	}

	public void closeDBConnection() {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SystemUtils.closeJVM();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RequestProxyMain requestProxyMain = new RequestProxyMain();
		Thread thread = new Thread(requestProxyMain);
		thread.start();
	}
}
