package com.gcj.dzh.common;

import org.apache.commons.logging.LogFactory;

public class Log {
	private static org.apache.commons.logging.Log log = LogFactory.getLog("");

	public static void debug(Object param) {
		log.debug(param);
	}

	public static void info(Object param) {
		log.info(param);
	}

	public static void warn(Object param) {
		log.warn(param);
	}

	public static void error(Object param) {
		log.error(param);
	}

	public static void fatal(Object param) {
		log.fatal(param);
	}

	public static void out(Object param) {
		log.info(param);
	}
}