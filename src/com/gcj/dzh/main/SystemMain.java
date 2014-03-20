package com.gcj.dzh.main;

import com.gcj.dzh.utils.ConfigUtils;
import com.gcj.dzh.utils.HostUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-17
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
public class SystemMain {
    public void start() {
        String configFilePath = "/opt/resource_monitor/conf/infoconfig.properties";
        ConfigUtils conf = new ConfigUtils(configFilePath);
        int sampleTime = conf.getInt("interval_time");
        String localhostIP = HostUtil.getLocalHostIP();
        new Thread(new IOStat(sampleTime, localhostIP)).start();
    }

    public static void main(String[] args) {
        SystemMain systemMain = new SystemMain();
        systemMain.start();
    }
}
