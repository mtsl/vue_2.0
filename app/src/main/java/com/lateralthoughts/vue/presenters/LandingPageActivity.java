package com.lateralthoughts.vue.presenters;

//internal Vue imports

//android imports
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.Dialog;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.lateralthoughts.vue.services.logging.Logger;
import com.lateralthoughts.vue.services.sidekick.PersistentWatcher;

/**
 * This is the landing page activity for Vue. By and large we should keep this activity simple. The purpose of this activity
 * is to determine exactly where to take the user in the Vue experience and also to determine whether any prerequisites we have
 * are already taken care of. Here are some examples of valid things we can do here:
 * 1. Check to see if this is the first time that Vue is launched on this device. If so, we might want to redirect the user to a quick
 * tour of the app before redirecting them to the actual Vue Experience
 * 2. Check to see if the app was launched because the user clicked on notifications - maybe we might redirect them to a new activity in that case
 *
 */

public class LandingPageActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Logger.console("VueDebug","onCreate of LandingPage invoked");


        boolean isAvailable = arePlayServicesAccessible();
        Intent watcherServiceIntent = new Intent(this, PersistentWatcher.class);
        watcherServiceIntent.putExtra(PersistentWatcher.REASON, PersistentWatcher.START_SIDEKICK);
        startService(watcherServiceIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.console("VueDebug","onResume of LandingPage invoked");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.console("VueDebug","onPause of LandingPage invoked");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.console("VueDebug","onStop of LandingPage invoked");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.console("VueDebug","onDestroy of LandingPage invoked");
    }

    public static class LocationErrorFragment extends DialogFragment {

        private Dialog mDialog;
        public LocationErrorFragment() {
            super();
        }

        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceData){
            return mDialog;
        }

    }

    private boolean arePlayServicesAccessible() {
        int isAccessible = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        boolean returnCode = false;
        if (ConnectionResult.SUCCESS == isAccessible) {
            returnCode = true;
        } else {
            //TODO: we need a way to catch errors

        }
        return returnCode;
    }

}
