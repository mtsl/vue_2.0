package com.lateralthoughts.vue.presenters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.lateralthoughts.vue.models.VueContentModelImpl;

import java.util.ArrayList;

public class DataAdapter extends BaseAdapter implements DataContainer {
    //ArrayList<AisleWindowContent> mAisleWindowList = new ArrayList<AisleWindowContent>();
 VueContentModelImpl mVueContentModel;

    DataAdapter(Context context) {
        mVueContentModel = (VueContentModelImpl) VueContentModelImpl.getContentModel();
        mVueContentModel.setDataRegisterListener(this);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        // return mAisleWindowList.size();
        return 10;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void notifyAdapters() {
        notifyDataSetChanged();

    }
    
   /* @Override
    public void addMoreData(ArrayList<AisleWindowContent> aisleList) {
        mAisleWindowList.addAll(aisleList);
        notifyDataSetChanged();
    }*/

    @Override
    public void clearAllData() {
        //mAisleWindowList.clear();
        notifyDataSetChanged();

    }

}
