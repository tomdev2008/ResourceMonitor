package com.gcj.dzh.main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gcj.dzh.bean.SystemResourceBean;
import com.gcj.dzh.dao.ProcessInfoDao;
import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.DoubleUtils;
import com.gcj.dzh.utils.MybatisUtils;
import com.gcj.dzh.utils.TimeUtils;

public class SystemResourceMain {

	private ProcessInfoDao processInfoDao;

	public SystemResourceMain() {
		super();
		// TODO Auto-generated constructor stub
		processInfoDao = MybatisUtils.session.getMapper(ProcessInfoDao.class);
	}

	public void start() {
		String configFilePath = "/opt/resource_monitor/conf/infoconfig.properties";
		// String configFilePath = "D:\\test\\infoconfig.properties";
		ConfigUtils conf = new ConfigUtils(configFilePath);
		int sampleTime = conf.getInt("interval_time");
		String localhostIP = conf.getString("localhost_ip");
		// File file = new File("D:\\test\\top.txt");
		Runtime run = Runtime.getRuntime();
		SystemResourceBean systemResourceBean = new SystemResourceBean();
		systemResourceBean.setLocalHostIp(localhostIP);
		float cpuUsage = 0;
		float memUsed = 0;
		String currentTime;
		while (true) {
			currentTime = TimeUtils.getCurrentTime();
			getResourceDataByTop(cpuUsage, memUsed, run, "top -b -n 1");
			systemResourceBean.setUpdateTime(currentTime);
			systemResourceBean.setCpuUsage(cpuUsage);
			systemResourceBean.setMemUsage(memUsed);
			processInfoDao.insert_resource_monitor(systemResourceBean);
			MybatisUtils.session.commit();
			try {
				Thread.sleep(sampleTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void getResourceDataFromFile(float cpuUsage, float memUsed) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("D:\\test\\top.txt"));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains("Cpu(s)")) {
					tempString = tempString.replaceAll(" ", "");
					String[] array = tempString.split(":");
					String[] array1 = array[1].split(",");
					String[] array2 = array1[3].split("%");
					double value = Double.parseDouble(array2[0]);
					cpuUsage = DoubleUtils.round(100.0 - value);

				}
				if (tempString.contains("Mem:")) {
					tempString = tempString.replaceAll(" ", "");
					String[] array = tempString.split(":");
					String[] array1 = array[1].split(",");
					String[] array2 = array1[1].split("k");
					double value = Double.parseDouble(array2[0]);
					value = value / 1024;
					memUsed = DoubleUtils.round(value / 1024);
				}
			}
			System.out.println(cpuUsage + "%");
			System.out.println(memUsed + "GB");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void getResourceDataByTop(float cpuUsage, float memUsed, Runtime run, String cmd) {
		BufferedInputStream in = null;
		BufferedReader inBr = null;
		Process p = null;
		try {
			p = run.exec(cmd);
			in = new BufferedInputStream(p.getInputStream());
			inBr = new BufferedReader(new InputStreamReader(in), 1024);
			String tempString = null;
			while ((tempString = inBr.readLine()) != null) {
				if (tempString.contains("Cpu(s)")) {
					tempString = tempString.replaceAll(" ", "");
					String[] array = tempString.split(":");
					String[] array1 = array[1].split(",");
					String[] array2 = array1[3].split("%");
					double value = Double.parseDouble(array2[0]);
					cpuUsage = DoubleUtils.round(100.0 - value);

				}
				if (tempString.contains("Mem:")) {
					tempString = tempString.replaceAll(" ", "");
					String[] array = tempString.split(":");
					String[] array1 = array[1].split(",");
					String[] array2 = array1[1].split("k");
					double value = Double.parseDouble(array2[0]);
					value = value / 1024;
					memUsed = DoubleUtils.round(value / 1024);
				}
			}
			 System.out.println(cpuUsage + "%");
			 System.out.println(memUsed + "GB");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SystemResourceMain systemResourceMain = new SystemResourceMain();
		systemResourceMain.start();
	}
}
