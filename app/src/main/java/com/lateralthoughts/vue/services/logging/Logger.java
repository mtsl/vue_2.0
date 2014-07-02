package com.lateralthoughts.vue.services.logging;

import android.util.Log;

public class Logger {

    public static void e() {

    }

    public static void i() {

    }

    public static void analytics(String tag, String message) {
        Log.e(tag, message);
    }

    public static void v() {

    }

    public static void console(String tag, String message) {
        //these logs should be sent to some source such as MixPanel
        Log.v(tag, message);

    }
}
