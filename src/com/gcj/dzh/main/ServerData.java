package com.gcj.dzh.main;

import com.gcj.dzh.bean.ProcessInfoBean;
import com.gcj.dzh.utils.TimeUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: zyj Date: 13-5-16 Time: 上午11:05 To change
 * this template use File | Settings | File Templates.
 */
public class ServerData {

	public List<String> getPidsByProcessName(String processName) {
		List<String> pidList = new ArrayList<String>();
		String cmd = "pgrep " + processName;
		Runtime run = Runtime.getRuntime();
		BufferedInputStream in = null;
		BufferedReader inBr = null;
		try {
			Process p = run.exec(cmd);
			in = new BufferedInputStream(p.getInputStream());
			inBr = new BufferedReader(new InputStreamReader(in), 1024);
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				pidList.add(lineStr);
			}
			try {
				if (p.waitFor() != 0) {
					if (p.exitValue() == 1) {
						System.err.println("命令执行失败!");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
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
		return pidList;
	}

	public List<ProcessInfoBean> getTopCommondContents(String pid, String processName, String localHostIP) {
		List<ProcessInfoBean> processInfoBeanList = new ArrayList<ProcessInfoBean>();

		//
		Runtime run = Runtime.getRuntime();
		BufferedInputStream in = null;
		BufferedReader inBr = null;

		try {
			// for (String pid : pidList) {
			ProcessInfoBean processInfoBean = new ProcessInfoBean();
			processInfoBean.setPid(Integer.parseInt(pid));
			processInfoBean.setProceeName(processName);
			processInfoBean.setLocalHostIp(localHostIP);
			String cmd = "top -p " + pid + " -b -n 1";
			String updateTime = TimeUtils.getCurrentTime();
			processInfoBean.setUpdateTime(updateTime);
			Process p = run.exec(cmd);
			in = new BufferedInputStream(p.getInputStream());
			inBr = new BufferedReader(new InputStreamReader(in), 1024);
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				// System.out.println(lineStr);
				if (lineStr.contains(processName)) {
					// System.out.println(tempString);
					lineStr = lineStr.replaceAll(" {2,}", " ").trim();
					String[] array = lineStr.split(" ");
					// String cpu = lineStr.substring(42, 45);
					String cpu = array[8];
					// System.out.println(cpu);
					processInfoBean.setCpuUsage(Float.parseFloat(cpu));
					// String memory = lineStr.substring(47, 50);
					// String memory = array[9];
					// System.out.println(memory);
					// processInfoBean.setMemUsage(Float.parseFloat(memory));
					processInfoBeanList.add(processInfoBean);
				} /*
				 * else if (lineStr.contains("load average")) {
				 * //System.out.println(tempString); processInfoBean = new
				 * ProcessInfoBean();
				 * processInfoBean.setProceeName(processName); String updateTime
				 * = lineStr.substring(6, 14);
				 * processInfoBean.setUpdateTime(updateTime);
				 * System.out.println(updateTime); }
				 */
			}
			try {
				if (p.waitFor() != 0) {
					if (p.exitValue() == 1) {
						System.err.println("命令执行失败!");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace(); // To change body of catch statement
										// use File | Settings | File
										// Templates.
			}
			// }

		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
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
		return processInfoBeanList;
	}
}
