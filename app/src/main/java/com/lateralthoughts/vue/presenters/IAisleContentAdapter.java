package com.lateralthoughts.vue.presenters;


public interface IAisleContentAdapter {
    public void setContentSource(String uniqueAisleId);

    public void releaseContentSource();

    public void setPivot(int index);

    public int getAisleItemsCount();

    public boolean setAisleContent(AisleContentBrowser contentBrowser,
                                   int currentIndex, int wantedIndex, boolean shiftPivot,
                                   int imagesCount);

    public String getAisleId();

    public String getImageId(int mCurrentIndex);


}
