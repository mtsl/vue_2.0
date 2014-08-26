package com.lateralthoughts.vue.personal.product;


import android.util.Log;

import com.lateralthoughts.vue.domain.client.ClientProductImage;
import com.lateralthoughts.vue.personal.OnResult;


public class ImageManager {
    private String logMessage;
    private final String TAG = "ImageManager";
    ProductHelper productHelper;

    public ImageManager() {
        productHelper = new ProductHelper();
        productHelper.callback = new ResultCallBack();
        productHelper.type = ClientProductImage.class;
    }

    public void createProductImage(ClientProductImage image) {
        logMessage = "createProductImage";
        productHelper.requestType = ProductManagerTask.CREATE_PRODUCT;
        productHelper.object = image;
        callProductTask(productHelper);
    }

    public void updateProductImage(ClientProductImage image) {
        logMessage = "updateProductImage";

    }

    public void getProductImage(Long id) {
        logMessage = "getProductImage";
    }

    public void deleteProductImage(Long id) {
        logMessage = "deleteProductImage";
    }

    /**
     * background task for executing the user request.
     */
    private void callProductTask(ProductHelper helper) {
        ProductManagerTask productManagerTask = new ProductManagerTask(helper);
        productManagerTask.execute();
    }

    private class ResultCallBack implements OnResult {
        @Override
        public void onResultComplete(boolean status, Object object) {
            if (status) {
                ImageManager imageManager = (ImageManager) object;
                Log.i(TAG, TAG + " " + logMessage + " Success");
            } else {
                Log.i(TAG, TAG + " " + logMessage + " Failed");

            }
        }
    }

    /**
     * test code to be deleted later.
     */
    private ClientProductImage getSampleImage() {
        ClientProductImage image = new ClientProductImage();
        //image.setDescription("imageDescripiton");
        //image.setExternalURL("http://ecx.images-amazon.com/images/I/91kHfDTB1-L._UL1500_.jpg");
        //image.setOrignalHeight(400f);
        //image.setOrignalWidth(400f);
        //image.setModifiedHeight(583f);
        // image.setModifiedWidth(380f);
        //image.setOwnerProductId(5308304431513600L);
        return image;
    }
}
