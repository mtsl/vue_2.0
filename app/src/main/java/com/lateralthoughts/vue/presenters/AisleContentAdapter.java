package com.lateralthoughts.vue.presenters;

import android.widget.ImageView;


public class AisleContentAdapter implements IAisleContentAdapter {
    static AisleContentAdapter sAisleContentAdapter;

    public AisleContentAdapter() {

    }

    public static AisleContentAdapter getInstance() {
        if (sAisleContentAdapter == null) {
            sAisleContentAdapter = new AisleContentAdapter();
        }
        return sAisleContentAdapter;
    }

    @Override
    public void setContentSource(String uniqueAisleId) {

    }

    @Override
    public void releaseContentSource() {

    }

    @Override
    public void setPivot(int index) {

    }

    @Override
    public int getAisleItemsCount() {

        return 0;
    }

    /**
     * Adds image to the horizontal flipper
     */
    @Override
    public boolean setAisleContent(AisleContentBrowser contentBrowser,
                                   int currentIndex, int wantedIndex, boolean shiftPivot,
                                   int imagesCount) {
        return true;
    }

    public void loadImage(ImageView imageView, String imgUrl,
                          AisleContentBrowser contentBrowser) {

    }

    @Override
    public String getAisleId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getImageId(int mCurrentIndex) {
        // TODO Auto-generated method stub
        return null;
    }


}
