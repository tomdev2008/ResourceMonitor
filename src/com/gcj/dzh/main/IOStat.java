package com.gcj.dzh.main;

import com.gcj.dzh.bean.IOStatBean;
import com.gcj.dzh.dao.ProcessInfoDao;
import com.gcj.dzh.utils.MybatisUtils;
import com.gcj.dzh.utils.TimeUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-17
 * Time: 下午5:31
 * To change this template use File | Settings | File Templates.
 */
public class IOStat implements Runnable {

    private ProcessInfoDao processInfoDao;

    private int intervalTime;

    private volatile boolean running = true;

    private String ip;

    public IOStat(int intervalTime, String ip) {
        this.intervalTime = intervalTime;
        this.ip = ip;
        processInfoDao = MybatisUtils.session.getMapper(ProcessInfoDao.class);
    }

    public IOStatBean getInfoByCmd(Runtime run) {
        IOStatBean ioStatBean = new IOStatBean();
        ioStatBean.setIp(ip);
        String cmd = "iostat";
        BufferedInputStream in = null;
        BufferedReader inBr = null;
        Process p = null;
        try {
            ioStatBean.setUpdateTime(TimeUtils.getCurrentTime());
            p = run.exec(cmd);
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in), 1024);
            String lineStr;
            int lineNumber = 0;
            while ((lineStr = inBr.readLine()) != null) {

                lineNumber++;
                if (lineNumber == 4) {
                    System.out.println(lineStr);
                    lineStr = lineStr.replaceAll(" {2,}", " ").trim();
                    String[] array = lineStr.split(" ");
                    ioStatBean.setUser(Float.parseFloat(array[0]));
                    ioStatBean.setNice(Float.parseFloat(array[1]));
                    ioStatBean.setSystem(Float.parseFloat(array[2]));
                    ioStatBean.setIowait(Float.parseFloat(array[3]));
                    ioStatBean.setSteal(Float.parseFloat(array[4]));
                    ioStatBean.setIdle(Float.parseFloat(array[5]));
                    for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " ");
                    }
                    System.out.print("\n");
                }
                if (lineNumber == 7) {
                    System.out.println(lineStr);
                    lineStr = lineStr.replaceAll(" {2,}", " ").trim();
                    String[] array = lineStr.split(" ");
                    ioStatBean.setTps(Double.parseDouble(array[1]));
                    ioStatBean.setBlkReadSpeed(Double.parseDouble(array[2]));
                    ioStatBean.setBlkWrtnSpeed(Double.parseDouble(array[3]));
                    ioStatBean.setBlkRead(Double.parseDouble(array[4]));
                    ioStatBean.setBlkWrtn(Double.parseDouble(array[5]));
                    for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " ");
                    }
                    System.out.print("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
        return ioStatBean;
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        Runtime run = Runtime.getRuntime();
        while (running) {
            IOStatBean ioStatBean = getInfoByCmd(run);
            processInfoDao.insert_iostat_monitor(ioStatBean);
            MybatisUtils.session.commit();
            try {
                Thread.sleep(intervalTime);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
