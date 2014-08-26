package com.lateralthoughts.vue.personal.aisle;


import android.os.AsyncTask;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lateralthoughts.vue.domain.client.ClientAisle;
import com.lateralthoughts.vue.domain.client.ClientUser;
import com.lateralthoughts.vue.personal.GenericNetWorker;
import com.lateralthoughts.vue.personal.user.SaveUser;
import com.lateralthoughts.vue.utils.UrlConstants;

import junit.framework.Assert;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AisleManagerTask extends AsyncTask<Void, Void, ArrayList<ClientAisle>> {
    private static final String TAG = "aisleMangerTask";
    public static final int CREATE_AISLE = 0;
    public static final int UPDATE_AISLE = 1;
    public static final int UPDATE_AISLES = 2;
    public static final int GET_AISLES_BY_USER = 3;
    public static final int DELETE_AISLE = 4;
    public static final int DELETE_AISLES = 5;

    GenericNetWorker<ClientAisle> clientAisleHelper;
    //ClientAisle clientAisle;

    AisleHelper aisleHelper;

    AisleManagerTask(AisleHelper aisleHelper) {
        this.aisleHelper = aisleHelper;
        clientAisleHelper = new GenericNetWorker<ClientAisle>(ClientAisle.class);
    }

    @Override
    protected ArrayList<ClientAisle> doInBackground(Void... voids) {
        try {
            String url;
            ArrayList<ClientAisle> aislesList = new ArrayList<ClientAisle>();
            switch (aisleHelper.requestType) {
                case CREATE_AISLE:
                    url = UrlConstants.CREATE_AISLE_RESTURL;
                    aisleHelper.clientAisle = clientAisleHelper.createObject(aisleHelper.clientAisle, url);
                    aislesList.add(aisleHelper.clientAisle);
                    return aislesList;

                case UPDATE_AISLE:
                    url = UrlConstants.UPDATE_AISLE_RESTURL;
                    // aisleHelper.clientAisle = clientAisleHelper.updateObject(aisleHelper.clientAisle, aisleHelper.clientAisle.getId(),url);
                    aislesList.add(aisleHelper.clientAisle);
                    return aislesList;

                case UPDATE_AISLES:
                    //aislesList = (ArrayList<ClientAisle>)getAisles(aisleHelper.clientAisle.getOwnerUserId());
                    return aislesList;

                case GET_AISLES_BY_USER:
                    ClientUser user = SaveUser.getUserFromFile();
                    Assert.assertNotNull(user);
                    aislesList = (ArrayList<ClientAisle>) getAisles(user.getId());
                    if (aislesList != null && aislesList.size() > 0) {

                    } else {

                    }
                    return aislesList;

                case DELETE_AISLE:
                    url = UrlConstants.DELETE_AISLE_RESTURL;
                    clientAisleHelper.deleteObject(Long.valueOf(aisleHelper.aisleId), url);
                    return null;

                case DELETE_AISLES:
                    return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<ClientAisle> clientAisles) {
        super.onPostExecute(clientAisles);
        if (clientAisles != null) {
            //TODO: save the new aisle/s.
            aisleHelper.callback.onResultComplete(true, clientAisles);
        } else {
            aisleHelper.callback.onResultComplete(false, clientAisles);
        }
    }

    /**
     * Get all the aisles matched with user Id.
     */
    public List<ClientAisle> getAisles(Long userId) {
        List<ClientAisle> aisles = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(UrlConstants.GET_AISLES_BY_USER + "/" + userId);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                // throw some exception
            } else {
                InputStream in =
                        new BufferedInputStream(urlConnection.getInputStream());
                String responseMessage = GenericNetWorker.getStringFromInputStream(in);
                if (responseMessage.length() > 0) {
                    try {
                        aisles = (new ObjectMapper()).readValue(responseMessage, new TypeReference<List<ClientAisle>>() {
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MalformedURLException e) {
            // handle invalid URL
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            // handle timeout
            e.printStackTrace();
        } catch (IOException e) {
            // handle I/0
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return aisles;
    }
}
