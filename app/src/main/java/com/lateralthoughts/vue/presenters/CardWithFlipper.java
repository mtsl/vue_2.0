package com.lateralthoughts.vue.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lateralthoughts.vue.R;
import com.lateralthoughts.vue.utils.Utils;


public class CardWithFlipper extends DataAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private int mPagerCardBottomMargin = 22 + 48;
    private int mTempHeight = 1000;

    
    CardWithFlipper(Context context) {
        super(context);
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resources resources = mContext.getResources();
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CardViewHolder  viewHolder;
        if (convertView == null) {
            viewHolder = new CardViewHolder();
            convertView = mInflater.inflate(R.layout.card_flipper, null);
            viewHolder.aisleContentBrowser = (AisleContentBrowser) convertView
                    .findViewById(R.id.pager);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) convertView.getTag();
        }
        // set the params based on the best image height in the aisle.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,
                Utils.getCurrentCardHeight(mTempHeight,mContext)
                        + Utils.getPixel(mContext, mPagerCardBottomMargin));
        viewHolder.aisleContentBrowser.setLayoutParams(params);
        return convertView;
    }

    


}
