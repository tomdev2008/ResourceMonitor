package com.gcj.dzh.common;

import java.util.List;

public class StringUtil {

	public static boolean isNull(String str) {
		if ((str == null) || (str.equals("null")) || (str.trim().length() == 0)) {
			return true;
		}
		return false;
	}

	public static boolean isNull(List<?> list) {
		if ((list == null) || (list.size() == 0)) {
			return true;
		}
		return false;
	}

	public static String asciiToStr(String ascii) {
		String[] chars = ascii.split(" ");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			sb.append((char) Integer.parseInt(chars[i]));
		}
		return sb.toString();
	}

	public static String decode(String stringTmp) {
		if (stringTmp == null)
			return "";
		String stringname = "";
		for (int i = 0; i < stringTmp.length(); i++) {
			char charint = stringTmp.charAt(i);
			switch (charint) {
			case '~':
				String stringtmp = stringTmp.substring(i + 1, i + 3);
				stringname = stringname + (char) Integer.parseInt(stringtmp, 16);
				i += 2;
				break;
			case '^':
				String stringtmp2 = stringTmp.substring(i + 1, i + 5);
				stringname = stringname + (char) Integer.parseInt(stringtmp2, 16);
				i += 4;
				break;
			default:
				stringname = stringname + charint;
			}

		}

		return stringname;
	}

	public static String encode(String stringname) {
		if (stringname == null)
			return "";
		String stringTmp = "";
		for (int i = 0; i < stringname.length(); i++) {
			int charat = stringname.charAt(i);
			if (charat > 255) {
				String tmp = Integer.toString(charat, 16);
				for (int j = tmp.length(); j < 4; j++) {
					tmp = "0" + tmp;
				}
				stringTmp = stringTmp + "^" + tmp;
			} else if ((charat < 48) || ((charat > 57) && (charat < 65)) || ((charat > 90) && (charat < 97)) || (charat > 122)) {
				String stringat = Integer.toString(charat, 16);
				for (int j = stringat.length(); j < 2; j++) {
					stringat = "0" + stringat;
				}
				stringTmp = stringTmp + "~" + stringat;
			} else {
				stringTmp = stringTmp + stringname.charAt(i);
			}

		}

		return stringTmp;
	}

	public static byte[] decode2(String stringTmp) {
		if (stringTmp == null)
			return null;
		int pos = 0;
		byte[] dectmp = new byte[stringTmp.length()];
		for (int i = 0; i < stringTmp.length(); i++) {
			char charint = stringTmp.charAt(i);
			switch (charint) {
			case '~':
				String stringtmp = stringTmp.substring(i + 1, i + 3);
				dectmp[pos] = ((byte) (char) Integer.parseInt(stringtmp, 16));
				i += 2;
				break;
			case '^':
				String stringtmp2 = stringTmp.substring(i + 1, i + 5);
				dectmp[pos] = ((byte) (char) Integer.parseInt(stringtmp2, 16));
				i += 4;
				break;
			default:
				dectmp[pos] = ((byte) charint);
			}

			pos++;
		}
		byte[] decArr = new byte[pos];
		for (int i = 0; i < pos; i++) {
			decArr[i] = dectmp[i];
		}
		return decArr;
	}

	public static String encode2(byte[] data) {
		if (data == null)
			return "";
		String stringTmp = "";
		for (int i = 0; i < data.length; i++) {
			int charat = (char) data[i];
			if (charat > 255) {
				String tmp = Integer.toString(charat, 16);
				for (int j = tmp.length(); j < 4; j++) {
					tmp = "0" + tmp;
				}
				stringTmp = stringTmp + "^" + tmp;
			} else if ((charat < 48) || ((charat > 57) && (charat < 65)) || ((charat > 90) && (charat < 97)) || (charat > 122)) {
				String stringat = Integer.toString(charat, 16);
				for (int j = stringat.length(); j < 2; j++) {
					stringat = "0" + stringat;
				}
				stringTmp = stringTmp + "~" + stringat;
			} else {
				stringTmp = stringTmp + (char) data[i];
			}

		}

		return stringTmp;
	}
}