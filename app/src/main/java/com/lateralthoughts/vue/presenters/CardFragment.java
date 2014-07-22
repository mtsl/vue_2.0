package com.lateralthoughts.vue.presenters;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.lateralthoughts.vue.R;


public class CardFragment extends Fragment {

    private ListView mCardList;
    private CardWithFlipper mCard;
    public static boolean sIsListScrolling;
    public static boolean sIsTouchScrollingCall;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_fragment_listview_holder, null);
        mCardList = (ListView) view.findViewById(R.id.card_list);
        //create adapter for card fragment.
        mCard = new CardWithFlipper(getActivity());
        //add header view to the list.
        View cardHeaderView = inflater.inflate(
                R.layout.card_fragment_listview_header, null);
        //add header view to the list.
        mCardList.addHeaderView(cardHeaderView);
        mCardList.setAdapter(mCard);
        mCardList.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        sIsListScrolling = true;
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        sIsTouchScrollingCall = true;
                        break;
                    case SCROLL_STATE_IDLE:
                        sIsListScrolling = false;
                        sIsTouchScrollingCall = false;
                        break;
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
        return view;
    }

}
