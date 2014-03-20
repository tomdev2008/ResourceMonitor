package com.gcj.dzh.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils {
	public static boolean isNull(String str) {
		if (str.equals("") || str == null || str.length() == 0) {
			return true;
		}
		return false;
	}

	public static byte[] stringToByte(String str) {
		try {
		return str.getBytes("gb2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
