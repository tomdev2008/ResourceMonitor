package com.gcj.dzh.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: zyj Date: 13-5-15 Time: 下午4:24 To change
 * this template use File | Settings | File Templates.
 */
public class GenerateShell {
	public static void getShell(String processName) {
		List<String> pidList = FileUtils.readFile("D:\\工作\\pid.txt");

		FileWriter fileWriter = null;
		int j = 1;
		StringBuilder sb = null;

		try {
			String result = null;
			for (int i = 0; i < pidList.size(); i++) {

				if (i % 20 == 0) {

					String filePath = "D:\\工作\\" + processName + "_top_" + j + ".sh";
					fileWriter = new FileWriter(filePath);
					sb = new StringBuilder();
					fileWriter.write("#!/bin/sh\r\n");
					j++;
				}

				sb.append("top -p ");

				String pid = pidList.get(i);
				sb.append(pid).append(",");

				if ((i + 1) % 20 == 0) {
					String temp = sb.toString();
					int len = temp.length();
					String str = temp.substring(0, len - 1);
					result = str + " -d 10 -b>" + processName + "_top_" + j + ".log &\r";
					fileWriter.write(result);
				}
			}
			String temp = sb.toString();
			int len = temp.length();
			String str = temp.substring(0, len - 1);
			result = str + " -d 10 -b>" + processName + "_top_" + j + ".log &\r";
			fileWriter.write(result);

		} catch (IOException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		} finally {
			try {
				if (fileWriter != null)
					fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		}
		// }
	}

	public static void main(String[] args) {
		GenerateShell.getShell("CalculationUnit");
	}
}
