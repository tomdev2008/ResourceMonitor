package com.gcj.dzh.utils;

public class DoubleUtils {
    public static float round(double value) {
        return (float) ((Math.round(value * 100)) / 100.0);
    }
}
