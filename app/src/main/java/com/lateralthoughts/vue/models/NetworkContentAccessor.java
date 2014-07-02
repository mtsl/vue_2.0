package com.lateralthoughts.vue.models;

//Vue imports
import com.lateralthoughts.vue.services.networking.GetAislesRequest;
import com.lateralthoughts.vue.services.networking.GetAislesResponse;
import com.lateralthoughts.vue.utils.UrlConstants;

//android and java imports

public class NetworkContentAccessor {
    private VueContentModelImpl mContentModel;
    private static NetworkContentAccessor sNetworkContentAccessor;

    private NetworkContentAccessor() {
        mContentModel = (VueContentModelImpl)VueContentModelImpl.getContentModel();
    }

    public static NetworkContentAccessor getInstance() {
        if(null == sNetworkContentAccessor) {
            sNetworkContentAccessor = new NetworkContentAccessor();
        }
        return sNetworkContentAccessor;
    }

    public void getAislesInRange(int offset, int limit) {
        StringBuilder builder = new StringBuilder();
        builder.append(UrlConstants.GET_TRENDINGAISLES_RESTURL).append("/").
                                                                append(String.valueOf(limit)).
                                                                append("/").
                                                                append(String.valueOf(offset));
        GetAislesResponse responseHandler = new GetAislesResponse();
        GetAislesRequest request = new GetAislesRequest(offset, limit, builder.toString(), responseHandler);
        responseHandler.setRequestObject(request);
        mContentModel.getRequestQueue().add(request);
    }
}
