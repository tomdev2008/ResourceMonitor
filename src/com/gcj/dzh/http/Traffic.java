package com.gcj.dzh.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gcj.dzh.bean.TrafficInfoBean;
import com.gcj.dzh.common.Parameter;
import com.gcj.dzh.dao.TrafficDao;
import com.gcj.dzh.utils.DoubleUtils;
import com.gcj.dzh.utils.MybatisUtils;
import com.gcj.dzh.utils.TimeUtils;

public class Traffic implements Runnable {

    private volatile boolean running = true;
    private int intervalTime;
    private String ip;
    private TrafficDao trafficDao;
    private String networkCardName;

    public Traffic(int intervalTime, String ip, String networkCardName) {
        super();
        this.intervalTime = intervalTime;
        this.ip = ip;
        this.networkCardName = networkCardName;
        this.trafficDao = MybatisUtils.session.getMapper(TrafficDao.class);
    }

    public double[] getDev(String filePath) {
        double[] result = new double[6];
        File file = new File(filePath);
        BufferedReader reader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String tempString;
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains(networkCardName)) {
                    Matcher m = p.matcher(tempString);
                    String str = m.replaceAll(" ").trim().replaceAll(" {2,}", " ");
                    String[] array = str.split(" ");
                    result[0] += Double.parseDouble(array[1]);
                    result[1] += Double.parseDouble(array[2]);
                    result[2] += Double.parseDouble(array[3]);
                    result[3] += Double.parseDouble(array[9]);
                    result[4] += Double.parseDouble(array[10]);
                    result[5] += Double.parseDouble(array[11]);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null)
                    fileReader.close();
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public double[] getTrafficSpeed(String filePath) {
        double[] result = new double[6];
        double[] begin = getDev(filePath);
        try {
            Thread.sleep(Parameter.TRAFFIC_SAMPLE_TIME);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        double[] end = getDev(filePath);
        for (int i = 0; i < begin.length; i++) {
            result[i] = (end[i] - begin[i]);
        }
        return result;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        double[] result;
        String currentTime;
        TrafficInfoBean trafficInfoBean = new TrafficInfoBean();
        trafficInfoBean.setIp(ip);
        while (running) {
            result = getTrafficSpeed("/proc/net/dev");
            currentTime = TimeUtils.getCurrentTime();
            trafficInfoBean.setUpdateTime(currentTime);
            trafficInfoBean.setReceiveTraffic(DoubleUtils.round(result[0]));
            trafficInfoBean.setReceivePackets(DoubleUtils.round(result[1]));
            trafficInfoBean.setReceiveErrs(DoubleUtils.round(result[2]));
            trafficInfoBean.setTransmitTraffic(DoubleUtils.round(result[3]));
            trafficInfoBean.setTransmitPackets(DoubleUtils.round(result[4]));
            trafficInfoBean.setTransmitErrs(DoubleUtils.round(result[5]));
            trafficDao.insert_traffic(trafficInfoBean);
            MybatisUtils.session.commit();
            try {
                Thread.sleep(intervalTime);
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
        Traffic traffic = new Traffic(5000, "127.0.0.1", "eth0");
        Thread thread = new Thread(traffic);
        thread.start();
    }
}
