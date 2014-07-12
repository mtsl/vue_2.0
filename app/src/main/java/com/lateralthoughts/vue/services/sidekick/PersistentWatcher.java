package com.lateralthoughts.vue.services.sidekick;

//app level imports
import android.app.Service;
import android.content.Intent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.lateralthoughts.vue.services.logging.Logger;
import com.lateralthoughts.vue.domain.UserContext;

import android.location.Location;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.List;
import android.os.Environment;
import android.content.SharedPreferences;

//OS imports
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.os.HandlerThread;
import android.os.Process;
import android.os.IBinder;

import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;

public class PersistentWatcher extends Service implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        LocationListener {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private UserContext mUserContext;

    private static final String LOG_TAG = "VueLocationInfo";

    public static final String REASON = "REASON";
    public static final int START_SIDEKICK = 13;
    public static final int STOP_SIDEKICK = 26;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    // Update frequency in seconds
    public static final int UPDATE_INTERVAL_IN_SECONDS = 60; //10 min interval for location updates
    // Update frequency in milliseconds
    private static final long UPDATE_INTERVAL =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
    // The fastest update frequency, in seconds
    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    // A fast frequency ceiling in milliseconds
    private static final long FASTEST_INTERVAL =
            MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
    // Define an object that holds accuracy and frequency parameters
    LocationRequest mLocationRequest;
    LocationClient mLocationClient;

    private boolean mUserContextInfoNeedsSave;
    private long mLastTimeOfContextInfoUpload;
    private long mLastTimeOfContextInfoSave;
    private SharedPreferences mWatcherPreferences;
    private static final String WATCHER_PREFS_FILE = "PersistentWatcherPrefs";
    private static final String WATCHER_LAST_UPLOADED_TIME = "LastUploadedTime";
    private static final String WATCHER_LAST_SAVE_TIME = "LastSavedTime";

    //during development use this:
    //private static final int UPLOAD_TIME_EXPIRY = 10 * 60; //we upload every 10 mins

    //during release use this
    private static final int UPLOAD_TIME_EXPIRY = 24 * 60 * 60; //we upload every 24 hours
    private static final int FILE_WRITE_TIMER_EXPIRY = 1 * 60 * 60; //we upload every 10 mins

    @Override
    public void onConnected(Bundle bundle) {
        mLocationClient.requestLocationUpdates(mLocationRequest, this);
    }

    @Override
    public void onDisconnected() {
    }

    @Override
    public void onLocationChanged(Location location) {
        Logger.console("VueDebug", location.toString());
        UserContext.LocationInfo info = new UserContext.LocationInfo();
        info.setLatitude(String.valueOf(location.getLatitude()));
        info.setLongitude(String.valueOf(location.getLongitude()));
        info.setTimeStamp(String.valueOf(location.getTime()));
        mUserContext.addLocationInfo(info);

        mUserContextInfoNeedsSave = true;
        if(System.currentTimeMillis() - mLastTimeOfContextInfoUpload > (UPLOAD_TIME_EXPIRY * 1000)) {
            //looks like its been a while since we last uploaded the data so we should do it now
            //TODO: send a message to the background thread so it can upload it there

            //once upload is done we should reset the last uploaded time
            mLastTimeOfContextInfoUpload = System.currentTimeMillis();

            //this is also a good time to clear the contents of the file
            clearContextInfoFile();

            SharedPreferences.Editor editor = mWatcherPreferences.edit();
            editor.putLong(WATCHER_LAST_UPLOADED_TIME, mLastTimeOfContextInfoUpload);
            editor.commit();
        }

        if(mUserContextInfoNeedsSave) {
            if(System.currentTimeMillis() - mLastTimeOfContextInfoSave > FILE_WRITE_TIMER_EXPIRY * 1000) {
                Logger.console("VueDebug","We haven't saved the context to file in a while so lets do it now!");
                saveContextInfoToFile();

                //overwrite the local context info
                mUserContext = new UserContext();
                mLastTimeOfContextInfoSave = System.currentTimeMillis();
                SharedPreferences.Editor editor = mWatcherPreferences.edit();
                editor.putLong(WATCHER_LAST_SAVE_TIME, mLastTimeOfContextInfoSave);
                editor.commit();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    private void writeToSdcard() {
        String path = Environment.getExternalStorageDirectory().toString();
        File dir = new File(path + "/VueContextInfo/");
        if(!dir.isDirectory()) {
            dir.mkdir();
        }
    }

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch(msg.arg1) {
                case START_SIDEKICK:
                    mLocationRequest = LocationRequest.create();
                    mLocationRequest.setPriority(
                            LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    // Set the update interval to 5 seconds
                    mLocationRequest.setInterval(UPDATE_INTERVAL);
                    // Set the fastest update interval to 1 second
                    mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

                    mLocationClient = new LocationClient(PersistentWatcher.this, PersistentWatcher.this, PersistentWatcher.this);
                    mLocationClient.connect();

                    mUserContext = new UserContext();
                    //lets also work on getting a list of applications installed
                    PackageManager packageManager = PersistentWatcher.this.getPackageManager();
                    List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA | PackageManager.GET_UNINSTALLED_PACKAGES);
                    for(int i=0;i<apps.size();i++) {
                        mUserContext.addInstalledApp(apps.get(i).packageName);
                    }
                    break;

                case STOP_SIDEKICK:
                    mLocationClient.disconnect();
                    stopSelf(msg.arg1);
                    break;
            }
        }
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread("TrackerService",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);

        mWatcherPreferences = getSharedPreferences(WATCHER_PREFS_FILE,Context.MODE_PRIVATE);
        mLastTimeOfContextInfoUpload = mWatcherPreferences.getLong(WATCHER_LAST_UPLOADED_TIME, 0);
        mLastTimeOfContextInfoSave = mWatcherPreferences.getLong(WATCHER_LAST_SAVE_TIME, 0);

        if(0 == mLastTimeOfContextInfoUpload) {
            mLastTimeOfContextInfoUpload = System.currentTimeMillis();
        }

        if(0 == mLastTimeOfContextInfoSave) {
            mLastTimeOfContextInfoSave = System.currentTimeMillis();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        if(intent != null && intent.getExtras() != null) {
            msg.arg1 = intent.getExtras().getInt(REASON);
        }
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Logger.console("VueDebug","Watcher is being destroyed - about to save context info to file");
        mLocationClient.disconnect();

        if(mUserContextInfoNeedsSave) {
            //we have some local information in mUserContext that we should save locally
            saveContextInfoToFile();
            mLastTimeOfContextInfoSave = System.currentTimeMillis();
            SharedPreferences.Editor editor = mWatcherPreferences.edit();
            editor.putLong(WATCHER_LAST_SAVE_TIME, mLastTimeOfContextInfoSave);
        }
    }

    private void saveContextInfoToFile() {
        ObjectMapper mapper = new ObjectMapper();
        FileOutputStream outputStream;

        try {
            writeToSdcard();
            String path = Environment.getExternalStorageDirectory().toString();
            File dir = new File(path + "/VueContextInfo/");
            File file = new File(dir, "/" + "ContextInfo" + ".json");

            try {
                outputStream = new FileOutputStream(file);
                mapper.writeValue(outputStream, mUserContext);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void clearContextInfoFile() {
        ObjectMapper mapper = new ObjectMapper();
        FileOutputStream outputStream;

        try {
            writeToSdcard();
            String path = Environment.getExternalStorageDirectory().toString();
            File dir = new File(path + "/VueContextInfo/");
            File file = new File(dir, "/" + "ContextInfo" + ".json");

            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(new String().getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
