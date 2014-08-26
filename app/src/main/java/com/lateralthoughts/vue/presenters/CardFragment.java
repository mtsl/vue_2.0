package com.lateralthoughts.vue.presenters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lateralthoughts.vue.R;
import com.lateralthoughts.vue.domain.client.ClientUser;
import com.lateralthoughts.vue.login.GooglePlayServicesActivity;
import com.lateralthoughts.vue.user.UserManager;
import com.lateralthoughts.vue.utils.VueConstants;


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
        RelativeLayout facebook_id = (RelativeLayout) cardHeaderView.findViewById(R.id.facebook_id);
        RelativeLayout gPlus_LayoutId = (RelativeLayout) cardHeaderView.findViewById(R.id.g_plus__layout_id);
        facebook_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientUser user = createUser();
                UserManager userManager = new UserManager();
                userManager.createNewClientUser(user, getActivity());
            }
        });
        gPlus_LayoutId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),GooglePlayServicesActivity.class);
                startActivity(intent);
            }
        });
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

    public ClientUser createUser() {
        String firstName =
                "Raju";
        String lastName =
                "Kunche";
        String deviceId = VueConstants.deviceId;
        ClientUser newUser = new ClientUser();
        newUser.setFacebookShortTermAcessToken("CAACEdEose0cBAKoh8ENuOZClgLZCsHg9X8lgpePpVikEoBCOsCrcE0cDyA6cQAWzFZBUMYCQiRlSYhzQ9fDZC9bfRm9SfljwSgiAqcMaXMi8v2sb68dryfZC7hkL2yC3NWqG5cXyywe11Bq3RNpzQmJ3YRTDXGaB8N9wBaq1zCA8Jp2bhIDzjrA3nZATyEwoHwm9BzDPRRLVExV4o0PZB2cy9ZA8uWCa9TYZD");
        newUser.setFacebookId("FACEBOOK_ID_" + System.currentTimeMillis());
        newUser.setGooglePlusId("GOOGLEPLUS_ID_" + System.currentTimeMillis());
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setDeviceId(deviceId);
        return newUser;
    }

}
