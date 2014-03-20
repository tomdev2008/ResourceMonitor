package com.gcj.dzh.utils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class HostUtil {

    public static String getLocalIP() {
        String ip = "";
        try {
            Enumeration<?> e1 = NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                // System.out.println("e1: " + e1);
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                // if (!ni.getName().equals(getLocalNIC())) {
                // continue;
                // } else {
                Enumeration<?> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    InetAddress ia = (InetAddress) e2.nextElement();
                    // System.out.println("ia: " + ia);
                    if (ia instanceof Inet6Address) {
                        // System.out.println("ia is IPV6: " + ia);
                        continue;
                    }
                    ip = ia.getHostAddress();
                    if (StringUtils.isNull(ip)) {
                        // System.out.println("ip is null!");
                        continue;
                    } else {
                        break;
                    }
                    // System.out.println("ip:" + ip);
                }
                break;
                // }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        // System.out.println("ip: " + ip);
        return ip;
    }

    public static String getLocalHostIP() {
        String localHostIP = null;
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();

            while (netInterfaces.hasMoreElements()) {

                NetworkInterface ni = netInterfaces.nextElement();

                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    String ip = ips.nextElement().getHostAddress();
                    if (isboolIp(ip) && !ip.equals("127.0.0.1")) {
                        localHostIP = ip;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localHostIP;
    }

    private static boolean isboolIp(String ipAddress) {
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        if (ipAddress.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
