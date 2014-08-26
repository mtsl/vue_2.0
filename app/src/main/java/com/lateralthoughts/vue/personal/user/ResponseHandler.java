package com.lateralthoughts.vue.personal.user;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ResponseHandler extends Handler {
    private final String TAG = "RespnseHandler ";
    private static final int USER_REQUEST = 0;
    private static final int AISLE_REQUEST = 1;
    private static final int PRODUCT_REQUEST = 2;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case USER_REQUEST:
                Log.i("TAG", TAG + " " + msg.obj);
                break;
            case AISLE_REQUEST:
                Log.i("TAG", TAG + " " + msg.obj);
                break;
            case PRODUCT_REQUEST:
                Log.i("TAG", TAG + " " + msg.obj);
                break;

        }
    }

    protected void logMessage(String message) {
        Log.i(TAG, TAG + message);
        //TODO: logs the message to the file for analytics.
    }
}

