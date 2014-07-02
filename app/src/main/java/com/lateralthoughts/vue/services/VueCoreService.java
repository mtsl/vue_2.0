package com.lateralthoughts.vue.services;

public class VueCoreService implements CoreInternals {
    private static VueCoreService sVueCoreService;
    private VueCoreService () {

    }

    public static VueCoreService getCoreService() {
        if (null == sVueCoreService) {
            sVueCoreService = new VueCoreService();
        }
        return sVueCoreService;
    }

    public void setCardsDataLoadedListener() {

    }

    //the following methods are from CoreInternals
    @Override
    public void notifyCardsMetaDataLoaded() {
        //TODO: Core service can in turn notify the UI adapter indicating new data is available.

    }

}
