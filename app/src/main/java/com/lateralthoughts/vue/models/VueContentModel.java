package com.lateralthoughts.vue.models;

//android and java imports
import com.android.volley.RequestQueue;

/**
 * This interface and the methods defined here is the glue that holds together the UI layer of the application
 * with the underlying nuts and bolts. The UI parts of Vue (basically code that falls under the presenters package)
 * should ALWAYS use this layer of APIs to access content that resides in Vue
 * The UI SHOULD NOT care about whether the content comes from the network or from a local cache - that will be determined
 * by the underlying implementation based on a network rules engine.
 *
 */
public interface VueContentModel {
    public enum AppInstallState {
        /* This indicates that the app is installed for the first time on this device. This can also happen when the app was deleted
           and then re-installed */
        NEW_INSTALL,

        /* This state indicates that the app has been updated from a previous version */
        UPDATED_INSTALL,

        /* This state indicates that we don't know anything about install states. This happens when we just continue to use the app
           as usual */
        NO_INSTALL_STATE,
    }

    public static final String MAJOR_VERSION = "1";
    public static final String MINOR_VERSION = "0";
    public static final String FIX_NUMBER = "0";

    public void onPause();

    public void onResume();

    public int getTotalNumberOfCards();

    public void getCardsMetaData(final int offset, final int limit);

    public RequestQueue getRequestQueue();
}
