package com.lateralthoughts.vue;

import android.app.Application;

public class AnchoredContext extends Application {

    private static AnchoredContext sAnchoredContext;

    public static AnchoredContext getInstance() {
        return sAnchoredContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAnchoredContext = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
