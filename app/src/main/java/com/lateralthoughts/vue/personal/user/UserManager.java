package com.lateralthoughts.vue.personal.user;

import android.content.Context;
import android.util.Log;


import com.lateralthoughts.vue.domain.client.ClientUser;
import com.lateralthoughts.vue.personal.OnResult;
import com.lateralthoughts.vue.utils.FileCache;


import java.io.File;

public class UserManager {
    public static String userImageUrl = "https://lh5.googleusercontent.com/-u5KwAmhVoUI/AAAAAAAAAAI/AAAAAAAAADg/5zfJJy26SNE/photo.jpg?sz=50";
    private String logMessage;
    private String TAG = "UserManager";
    UserHelper helper;

    //public static String userImageUrl = "https://graph.facebook.com/477936655643127/picture?type=large";
    public UserManager() {
        UserHelper helper = new UserHelper();
        helper.callback = new ResultCallBack();
    }

    /**
     * create new user with user entered values.
     */
    public void createNewClientUser(ClientUser newClientUser, Context context) {
        logMessage = "createNewClientUser";
        FileCache fileCache = new FileCache(context);
        File file = fileCache.getFile(userImageUrl);
        UserHelper helper = new UserHelper();
        helper.requestType = UserCreateAndUpdateTask.CREATING_NEW_USER;
        helper.callback = new ResultCallBack();
        helper.clientUser = newClientUser;
        helper.file = file;
        callUserTask(helper);

    }

    /**
     * update the client with the profile image url.
     */
    public void updateClientUserWithImageUrl(String userImageUrl, Context context) {
        logMessage = "updateClientUserWithImageUrl";
        //get the existing Client
        ClientUser clientUser = SaveUser.getUserFromFile();
        //get the file name and path.
        FileCache fileCache = new FileCache(context);
        File file = fileCache.getFile(UserManager.userImageUrl);
        helper.requestType = UserCreateAndUpdateTask.UPDATING_USER_WITH_IMAGE_URL;
        helper.clientUser = clientUser;
        helper.file = file;
        callUserTask(helper);
    }

    /**
     * if there is no user existed then create a new user with fb details.
     */
    public void createNewClientUserWithFacebookId(ClientUser createdUser, OnResult callback) {
        logMessage = "createNewClientUserWithFacebookId";
        helper.requestType = UserCreateAndUpdateTask.CREATE_USER_WITH_FACEBOOK_ID;
        helper.callback = callback;
        helper.clientUser = createdUser;
        callUserTask(helper);
    }

    /**
     * if there is already existing user update that user with facebook details.
     */
    public void updateClientUserWithFacebookId(ClientUser updateUserWithFacebookId, OnResult callback) {
        logMessage = "updateClientUserWithFacebookId";
        helper.requestType = UserCreateAndUpdateTask.UPDATE_USER_WITH_FACEBOOK_ID;
        helper.callback = callback;
        helper.clientUser = updateUserWithFacebookId;
        callUserTask(helper);

    }

    /**
     * update the g+ user with fb details.
     */
    private void updateGooglePlusClientUserWithFacebookId(ClientUser createdUser, String facebookId) {
        logMessage = "updateGooglePlusClientUserWithFacebookId";
        //get the existing Client
        ClientUser clientUser = SaveUser.getUserFromFile();
        //set the facebook ID to the existing client.
        helper.requestType = UserCreateAndUpdateTask.UPDATE_GOOGLE_PLUS_USER_WITH_FACEBOOK_ID;
        helper.clientUser = clientUser;
        clientUser.setFacebookId(facebookId);
        callUserTask(helper);
    }

    /**
     * retrieves created user from server with facebook ID.
     */
    public void getUserWithFacebookId(String fbId, OnResult callback) {
        helper.requestType = UserCreateAndUpdateTask.GET_USER_WITH_FACEBOOK_ID;
        helper.callback = callback;
        helper.id = fbId;
        callUserTask(helper);
        ;
    }

    /**
     * Creation of anonymous user with device id,
     */
    public void createAnonymousUser() {
        logMessage = "createAnonymousUser";
        UserHelper helper = new UserHelper();
        helper.requestType = UserCreateAndUpdateTask.CREATE_ANONYMOUS_USER;
        helper.clientUser = createUser();
        callUserTask(helper);
    }

    public void getCreatedUser() {
        helper.requestType = UserCreateAndUpdateTask.GET_USER_WITH_ID;
        callUserTask(helper);
    }

    public void deleteUser() {
        //send user to be deleted.
        logMessage = "deleteUser";
        helper.requestType = UserCreateAndUpdateTask.DELETE_USER_WITH_ID;
        callUserTask(helper);

    }

    /**
     * returns the anonymous user with device id.
     */
    private ClientUser createUser() {
        String firstName = "Anonymous";
        String lastName = "";
        //String deviceId = VueConstants.deviceId;
        String deviceId = "" + System.currentTimeMillis();
        ClientUser newUser = new ClientUser();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setDeviceId(deviceId);
        return newUser;
    }

    private class ResultCallBack implements OnResult {
        @Override
        public void onResultComplete(boolean status, Object object) {
            if (status) {
                ClientUser clientUser = (ClientUser) object;
                Log.i(TAG, TAG + " " + logMessage + " Success");
            } else {
                Log.i(TAG, TAG + " " + logMessage + " Failed");
            }
        }
    }

    /**
     * background task for executing the user request.
     */
    private void callUserTask(UserHelper helper) {
        UserCreateAndUpdateTask userCreateAndUpdateTask = new UserCreateAndUpdateTask(helper);
        userCreateAndUpdateTask.execute();
    }
}