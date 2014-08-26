package com.lateralthoughts.vue.personal.user;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lateralthoughts.vue.domain.client.ClientUser;
import com.lateralthoughts.vue.personal.GenericNetWorker;
import com.lateralthoughts.vue.utils.UrlConstants;


import junit.framework.Assert;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URL;

public class UserCreateAndUpdateTask extends AsyncTask<Void, Void, ClientUser> {
    String userUrl;

    // ClientUser clientUser;

    public static final int CREATING_NEW_USER = 0;
    public static final int UPDATING_USER_WITH_IMAGE_URL = 1;
    public static final int CREATE_USER_WITH_FACEBOOK_ID = 2;
    public static final int UPDATE_USER_WITH_FACEBOOK_ID = 3;
    public static final int UPDATE_GOOGLE_PLUS_USER_WITH_FACEBOOK_ID = 4;
    public static final int GET_USER_WITH_FACEBOOK_ID = 5;
    public static final int CREATE_ANONYMOUS_USER = 6;
    public static final int GET_USER_WITH_ID = 7;
    public static final int DELETE_USER_WITH_ID = 8;
    public static final String TAG = "USER_CREATION";
    GenericNetWorker<ClientUser> clientUserHelper;
    // String id;

    UserHelper helper = null;

    UserCreateAndUpdateTask(UserHelper helper) {
        clientUserHelper = new GenericNetWorker<ClientUser>(ClientUser.class);
        this.helper = helper;
    }

    @Override
    protected ClientUser doInBackground(Void... params) {
        try {
            String serverImageUrl;
            ClientUser retrievedClientUser;
            switch (helper.requestType) {
                case CREATING_NEW_USER:
                    userUrl = UrlConstants.CREATE_USER_RESTURL;
                    Assert.assertNotNull(helper.file);
                    Assert.assertNotNull(UserManager.userImageUrl);
                    Assert.assertNotNull(helper.clientUser);
                    serverImageUrl = getServerImageUrl(helper.file, UserManager.userImageUrl);
                    Assert.assertNotNull(serverImageUrl);
                    helper.clientUser.setUserImageURL(serverImageUrl);
                    retrievedClientUser = clientUserHelper.createObject(helper.clientUser, UrlConstants.CREATE_USER_RESTURL);
                    return retrievedClientUser;

                case UPDATING_USER_WITH_IMAGE_URL:
                    //get the created client and call update with new image key
                    userUrl = UrlConstants.UPDATE_USER_RESTURL;
                    Assert.assertNotNull(helper.file);
                    Assert.assertNotNull(UserManager.userImageUrl);
                    Assert.assertNotNull(helper.clientUser.getId());
                    serverImageUrl = getServerImageUrl(helper.file, UserManager.userImageUrl);
                    helper.clientUser.setUserImageURL(serverImageUrl);
                    userUrl = userUrl + "/" + helper.clientUser.getId().toString();
                    retrievedClientUser = clientUserHelper.updateObject(helper.clientUser, helper.clientUser.getId(), UrlConstants.UPDATE_USER_RESTURL);
                    return retrievedClientUser;

                case CREATE_USER_WITH_FACEBOOK_ID:
                    //create a new user with facebook details.
                    userUrl = UrlConstants.CREATE_USER_RESTURL;
                    Assert.assertNotNull(helper.clientUser);
                    retrievedClientUser = clientUserHelper.createObject(helper.clientUser, UrlConstants.CREATE_USER_RESTURL);
                    return retrievedClientUser;

                case UPDATE_USER_WITH_FACEBOOK_ID:
                    //get the created client and call update with facebook details
                    userUrl = UrlConstants.UPDATE_USER_RESTURL;
                    Assert.assertNotNull(helper.clientUser);
                    Assert.assertNotNull(helper.clientUser.getId());
                    userUrl = userUrl + "/" + helper.clientUser.getId().toString();
                    retrievedClientUser = clientUserHelper.updateObject(helper.clientUser, helper.clientUser.getId(), UrlConstants.UPDATE_USER_RESTURL);
                    return retrievedClientUser;

                case UPDATE_GOOGLE_PLUS_USER_WITH_FACEBOOK_ID:
                    userUrl = UrlConstants.UPDATE_USER_RESTURL;
                    retrievedClientUser = clientUserHelper.updateObject(helper.clientUser, helper.clientUser.getId(), UrlConstants.UPDATE_USER_RESTURL);
                    return retrievedClientUser;
                case GET_USER_WITH_FACEBOOK_ID:
                    //get the existing user with fb id.
                    userUrl = UrlConstants.UPDATE_USER_RESTURL;
                    userUrl = userUrl + "/" + helper.id.toString();
                    retrievedClientUser = getFacebookClientUser(String.valueOf(helper.id));
                    return retrievedClientUser;
                case CREATE_ANONYMOUS_USER:
                    //user creation with device id without having fb and g+ details.
                    userUrl = UrlConstants.CREATE_USER_RESTURL;
                    Assert.assertNotNull(helper.clientUser);
                    retrievedClientUser = clientUserHelper.createObject(helper.clientUser, UrlConstants.CREATE_USER_RESTURL);
                    return retrievedClientUser;
                case GET_USER_WITH_ID:
                    ClientUser clientUser = SaveUser.getUserFromFile();
                    Assert.assertNotNull(clientUser);
                    Assert.assertNotNull(clientUser.getId());
                    retrievedClientUser = clientUserHelper.getObject(UrlConstants.GET_USER_RESTURL, clientUser.getId());
                    retrievedClientUser.setFirstName("Raju kunche");
                    userUrl = UrlConstants.UPDATE_USER_RESTURL;
                    userUrl = userUrl + "/" + retrievedClientUser.getId().toString();
                    retrievedClientUser = clientUserHelper.updateObject(retrievedClientUser, retrievedClientUser.getId(), UrlConstants.UPDATE_USER_RESTURL);
                    return retrievedClientUser;
                case DELETE_USER_WITH_ID:
                    userUrl = UrlConstants.DELETE_USER_RESTURL;
                    clientUser = SaveUser.getUserFromFile();
                    Assert.assertNotNull(clientUser);
                    Assert.assertNotNull(clientUser.getId());
                    clientUserHelper.deleteObject(clientUser.getId(), userUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(ClientUser clientUser) {
        //TODO: Volley request needs to be changed.
        boolean retrievedClient = false;
        if (clientUser != null) {
            SaveUser.saveUserToFile(clientUser);
            retrievedClient = true;
        }
        if (helper.callback != null) {
            helper.callback.onResultComplete(retrievedClient, clientUser);
        }


    }

    private String getServerImageUrl(File file, String imageUrl) throws Exception {
        ImageManager imgManager = new ImageManager();
        file = imgManager.saveFile(file, UserManager.userImageUrl);
        String imageKey = imgManager.uploadImage(file);
        Assert.assertNotNull(imageKey);
        String imageServingURL = imgManager.getServingURLUsingImageKey(imageKey);
        return imageServingURL;
    }


    public static ClientUser getFacebookClientUser(String facebookId) throws Exception {
        ClientUser retrievedClientUser = null;

        URL url = new URL(UrlConstants.GET_USER_FACEBOOK_ID_RESTURL + "/" + facebookId);
        HttpGet httpGet = new HttpGet(url.toString());
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpResponse response = httpClient.execute(httpGet);
        if (response.getEntity() != null &&
                response.getStatusLine().getStatusCode() == 200) {
            String responseMessage = EntityUtils.toString(response.getEntity());
            if (responseMessage.length() > 0) {
                retrievedClientUser = (new ObjectMapper()).readValue(responseMessage, ClientUser.class);
            }
        } else {
        }
        httpGet.abort();
        return retrievedClientUser;
    }
}
