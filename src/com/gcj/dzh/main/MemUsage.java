package com.gcj.dzh.main;

import com.gcj.dzh.bean.ServerMsgBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemUsage {

    public int getMemUsage(String path) {
        int memUsage = 0;
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("VmRSS")) {
                    Matcher m = p.matcher(tempString);
                    memUsage = (Integer.parseInt(m.replaceAll("").trim())) / 1024;
                }
            }
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
        return memUsage;
    }

    /**
     * 获取进程的物理内存占用
     *
     * @param statusFile
     * @return
     */
    public int getMemUsage(File statusFile) {
        int memUsage = -1;
        BufferedReader reader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(statusFile);
            reader = new BufferedReader(fileReader);
            String tempString;
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m;
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("VmRSS")) {
                    m = p.matcher(tempString);
                    memUsage = (Integer.parseInt(m.replaceAll("").trim())) / 1024;
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
        return memUsage;
    }

    /**
     * 获取进程的物理内存占用
     *
     * @param statusFile
     * @return
     */
    public boolean getMemUsage(File statusFile, ServerMsgBean serverMsgBean) {
        int memUsage = -1;
        BufferedReader reader = null;
        FileReader fileReader = null;
        boolean result = false;
        try {
            fileReader = new FileReader(statusFile);
            reader = new BufferedReader(fileReader);
            String tempString;
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m;
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("VmRSS")) {
                    m = p.matcher(tempString);
                    memUsage = (Integer.parseInt(m.replaceAll("").trim())) / 1024;
                }
            }
            result = true;
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
        serverMsgBean.setMemUsage(memUsage);
        return result;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MemUsage memUsage = new MemUsage();
        int mem = memUsage.getMemUsage("D:\\status");
        System.out.println(mem);
    }
}
