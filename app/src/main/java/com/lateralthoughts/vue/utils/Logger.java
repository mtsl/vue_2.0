package com.lateralthoughts.vue.utils;

import android.util.Log;

public class Logger {
    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    public static void w(String tag, String message) {

    }

    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    public static void analytics(String tag, String message) {
        //these logs should be sent to some source such as MixPanel
        Log.v(tag, message);

    }
}
