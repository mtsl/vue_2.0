package com.lateralthoughts.vue.personal.aisle;

import android.util.Log;

import com.lateralthoughts.vue.domain.client.ClientAisle;
import com.lateralthoughts.vue.personal.OnResult;
import com.lateralthoughts.vue.personal.user.SaveUser;


public class AisleManager {
    private String mLogMessage;
    private String TAG = "AisleManager";
    AisleHelper aisleHelper;

    AisleManager() {
        aisleHelper = new AisleHelper();
        aisleHelper.callback = new ResultCallBack();
    }


    public void createAisle(ClientAisle clientAisle) {
        mLogMessage = "createAisle";
        aisleHelper.requestType = AisleManagerTask.CREATE_AISLE;
        aisleHelper.clientAisle = clientAisle;
        callAisleTask(aisleHelper);
    }


    public void retrieveAisleByUser(Long id) {
        mLogMessage = "retrieveAisle";
        aisleHelper.requestType = AisleManagerTask.GET_AISLES_BY_USER;
        callAisleTask(aisleHelper);

    }


    public void updateAisle(ClientAisle updateAisle) {
        mLogMessage = "updateAisle";
        aisleHelper.requestType = AisleManagerTask.UPDATE_AISLE;
        aisleHelper.clientAisle = updateAisle;
        callAisleTask(aisleHelper);
    }

    public void deleteAisle(Long aisleId) {
        mLogMessage = "deleteAisle";
        aisleHelper.requestType = AisleManagerTask.DELETE_AISLE;
        aisleHelper.aisleId = aisleId;
        callAisleTask(aisleHelper);
    }

    /**
     * test code to be deleted.
     */
    private ClientAisle getSampleAisle() {
        long userId = SaveUser.getUserFromFile().getId();
        ClientAisle clientAisle = new ClientAisle();
        //clientAisle.setOwnerUserId(userId);
        //clientAisle.setLookingFor("marriage suit");
        //clientAisle.setCategory("Party");
        //clientAisle.setName("raju's party suit aisle");
        //clientAisle.setOwnerUserId(6419807607980032L);
        // clientAisle.setDescription("Creating a test aisle for unit test");
        return clientAisle;
    }

    /**
     * background task for executing the user request.
     */
    private void callAisleTask(AisleHelper helper) {
        AisleManagerTask aisleManagerTask = new AisleManagerTask(helper);
        aisleManagerTask.execute();
    }

    private class ResultCallBack implements OnResult {
        @Override
        public void onResultComplete(boolean status, Object object) {
            if (status) {
                ClientAisle clientAisle = (ClientAisle) object;
                Log.i(TAG, TAG + " " + mLogMessage + " Success");
            } else {
                Log.i(TAG, TAG + " " + mLogMessage + " Failed");
            }
        }
    }

}
