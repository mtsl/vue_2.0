package com.lateralthoughts.vue.personal;


import android.os.Message;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lateralthoughts.vue.domain.client.ClientAisle;
import com.lateralthoughts.vue.domain.client.ClientProduct;
import com.lateralthoughts.vue.domain.client.ClientUser;
import com.lateralthoughts.vue.models.VueContentModelImpl;
import com.lateralthoughts.vue.personal.user.ResponseHandler;


import org.apache.http.entity.StringEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GenericJson<T> extends ResponseHandler {
    T type;
    String url;
    String requestMessage;

    public GenericJson(T type, String url, String requestMessage) {
        this.type = type;
        this.url = url;
        this.requestMessage = requestMessage;
    }

    public void creteAndUpdateTask() {
        Response.Listener listener = new Response.Listener<String>() {

            @Override
            public void onResponse(String jsonArray) {
                if (null != jsonArray) {
                    if (type instanceof ClientUser) {
                        Message msg = new Message();
                        msg.obj = jsonArray;
                        sendMessage(msg);

                    } else if (type instanceof ClientAisle) {

                    } else if (type instanceof ClientProduct) {

                    }
                }
                logMessage(requestMessage + " Successfully");
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error.networkResponse
                        && null != error.networkResponse.data) {
                    String errorData = error.networkResponse.data.toString();
                    logMessage(requestMessage + " Failed");
                    if (type instanceof ClientUser) {
                        Message msg = new Message();
                        msg.obj = "From Client User";
                        msg.what = 0;
                        sendMessage(msg);

                    } else if (type instanceof ClientAisle) {
                        Message msg = new Message();
                        msg.obj = "From Aisle User";
                        msg.what = 1;
                        sendMessage(msg);
                    } else if (type instanceof ClientProduct) {
                        Message msg = new Message();
                        msg.obj = "From Product User";
                        msg.what = 2;
                        sendMessage(msg);
                    }
                }
            }
        };
        try {

            ObjectMapper mapper = new ObjectMapper();
            String userAsString = mapper.writeValueAsString(type);
            CreateOrUpdateRequest request = new CreateOrUpdateRequest(
                    userAsString, url,
                    listener, errorListener);
            VueContentModelImpl.getContentModel().getRequestQueue().add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Custom json request.
     */
    private class CreateOrUpdateRequest extends Request<String> {
        // ... other methods go here
        private Response.Listener<String> mListener;
        private Response.ErrorListener mErrorListener;
        private String mUserAsString;
        private StringEntity mEntity;

        public CreateOrUpdateRequest(String userAsString, String url,
                                     Response.Listener<String> listener,
                                     Response.ErrorListener errorListener) {
            super(Method.PUT, url, errorListener);
            mListener = listener;
            mErrorListener = errorListener;
            mUserAsString = userAsString;
            try {
                mEntity = new StringEntity(mUserAsString);
            } catch (UnsupportedEncodingException ex) {
            }
        }

        @Override
        public String getBodyContentType() {
            return mEntity.getContentType().getValue();
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                mEntity.writeTo(bos);
            } catch (IOException e) {
                VolleyLog.e("IOException writing to ByteArrayOutputStream");
            }
            return bos.toByteArray();
        }

        @Override
        public Map<String, String> getHeaders() {
            Map<String, String> header = new HashMap<String, String>();
            header.put("Content-Type", "application/json;charset=UTF-8");


            return header;
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed,
                    HttpHeaderParser.parseCacheHeaders(response));
        }

        @Override
        protected void deliverResponse(String s) {
            mListener.onResponse(s);
        }

        @Override
        public void deliverError(VolleyError error) {
            mErrorListener.onErrorResponse(error);
        }

    }
}
