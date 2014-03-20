package com.gcj.dzh.main;

import java.io.File;

import com.gcj.dzh.bean.Info;
import com.gcj.dzh.dao.InfoDao;
import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.Constants;
import com.gcj.dzh.utils.DoubleUtils;
import com.gcj.dzh.utils.FileUtils;
import com.gcj.dzh.utils.MybatisUtils;
import com.gcj.dzh.utils.TimeUtils;

public class ElasticSearch implements Runnable {

	private InfoDao infoDao;

	private int intervalTime;

	private String pidPath;

	private String processName;

	public ElasticSearch(int intervalTime, String pidPath, String processName) {
		super();
		// TODO Auto-generated constructor stub
		infoDao = MybatisUtils.session.getMapper(InfoDao.class);
		this.intervalTime = intervalTime;
		this.pidPath = pidPath;
		this.processName = processName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String pid = FileUtils.getPidOnce(pidPath);
		String memPath = "/proc/" + pid + "/status";
		String cmd = "top -p " + pid + " -b -n 1";
		// String processPath = "/proc/" + pid + "/stat";
		// String path = "/proc/stat";
		File statusFile = new File(memPath);
		CPUUsage cpuUsage = new CPUUsage();
		MemUsage memUsage = new MemUsage();
		// short cpuS = cpuUsage.getCPUs();
		String currentTime;
		int pmem = -1;
		float pcpu;
		Runtime run = Runtime.getRuntime();
		while (true) {
			pmem = memUsage.getMemUsage(statusFile);
			currentTime = TimeUtils.getCurrentTime();
			// System.out.println(currentTime);
			// double cpuD = cpuUsage.calUsage(processPath, path, cpuS, 1000);
			// System.out.println(cpuD);

			pcpu = DoubleUtils.round(cpuUsage.getCPUUsageByTopCommond(run, cmd, processName));
			Info info = new Info();
			info.setUpdateTime(currentTime);
			info.setCpuUsage(pcpu);
			info.setMemUsage(pmem);
			infoDao.insert_elasticsearch(info);
			MybatisUtils.session.commit();
			try {
				Thread.sleep(this.intervalTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// MybatisUtils.closeSession();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigUtils conf = new ConfigUtils(Constants.configFilePath);
		int intervalTime = conf.getInt("interval_time");
		String pidPath = conf.getString("pidPath");
		String processName = conf.getString("process_name");
		ElasticSearch querywebMain = new ElasticSearch(intervalTime, pidPath, processName);
		Thread thread = new Thread(querywebMain);
		thread.start();
	}
}
