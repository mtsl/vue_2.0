package com.lateralthoughts.vue.services.networking;

//Vue imports
import com.lateralthoughts.vue.models.VueContentModelImpl;

//android and java imports
import com.android.volley.Response;
import com.android.volley.VolleyError;
import android.os.Message;
import org.json.JSONArray;
import java.lang.ref.WeakReference;

public class GetAislesResponse implements Response.Listener<JSONArray>,
                                          Response.ErrorListener {

    WeakReference<GetAislesRequest> mWeakRequest;

    public GetAislesResponse () {

    }

    public void setRequestObject (GetAislesRequest request) {
        mWeakRequest = new WeakReference<GetAislesRequest>(request);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        volleyError.getMessage();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onResponse(JSONArray jsonArray) {
        if(mWeakRequest.get() != null) {
            Message msg = new Message();
            msg.what = VueContentModelImpl.AISLE_DATA_NOTIFY;
            msg.arg1 = mWeakRequest.get().getOffset();
            msg.arg2 = mWeakRequest.get().getLimit();
            msg.obj = jsonArray;
            VueContentModelImpl.sendMessage(msg);
        }
    }
}
