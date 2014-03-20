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

public class CjiMain implements Runnable {

    private InfoDao infoDao;

    private int intervalTime;

    private String pidPath;

    public CjiMain(int intervalTime, String pidPath) {
        super();
        // TODO Auto-generated constructor stub
        infoDao = MybatisUtils.session.getMapper(InfoDao.class);
        this.intervalTime = intervalTime;
        this.pidPath = pidPath;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        String pid = FileUtils.getPidOnce(pidPath);
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

        while (true) {
            pmem = memUsage.getMemUsage(statusFile);
            currentTime = TimeUtils.getCurrentTime();
            // System.out.println(currentTime);
            double cpuD = cpuUsage.calUsage(processPath, path, cpuS, 1000);
            // System.out.println(cpuD);
            pcpu = DoubleUtils.round(cpuD);
            Info info = new Info();
            info.setUpdateTime(currentTime);
            info.setCpuUsage(pcpu);
            info.setMemUsage(pmem);
            infoDao.insert_cjiinfo(info);
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
        ConfigUtils config = new ConfigUtils(Constants.configFilePath);
        String intervalTimeStr = config.getString("interval_time");
        int intervalTime = Integer.parseInt(intervalTimeStr);
        String pidPath = config.getString("pidPath");
        CjiMain cjiMain = new CjiMain(intervalTime, pidPath);
        Thread thread = new Thread(cjiMain);
        thread.start();
    }
}
