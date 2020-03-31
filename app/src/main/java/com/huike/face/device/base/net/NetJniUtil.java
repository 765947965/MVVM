package com.huike.face.device.base.net;



public class NetJniUtil {
    static {
        System.loadLibrary("NetJniUtil");
    }

    public static native String sing(String[] args);
}
