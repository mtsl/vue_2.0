package com.lateralthoughts.vue.personal.product;


import android.util.Log;

import com.lateralthoughts.vue.domain.client.ClientProductProvider;
import com.lateralthoughts.vue.personal.OnResult;


public class ProviderManager {
    private String logMessage;
    private final String TAG = "ProviderManager";
    ProductHelper productHelper;

    public ProviderManager() {
        productHelper = new ProductHelper();
        productHelper.callback = new ResultCallBack();
        productHelper.type = ClientProductProvider.class;
    }

    public void createProductProvider(ClientProductProvider productProvider) {
        logMessage = "createProductProvider";
        productHelper.requestType = ProductManagerTask.CREATE_PRODUCT;
        productHelper.object = productProvider;
        callProductTask(productHelper);
    }

    public void updateProductProvider(ClientProductProvider productProvider) {
        logMessage = "updateProductProvider";
    }

    public void getProductProvider(Long id) {
        logMessage = "getProductProvider";
    }

    public void deleteProductProvider(Long id) {
        logMessage = "deleteProductProvider";
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
                ClientProductProvider provider = (ClientProductProvider) object;
                Log.i(TAG, TAG + " " + logMessage + " Success");
            } else {
                Log.i(TAG, TAG + " " + logMessage + " Failed");

            }
        }
    }

    /**
     * test code to be deleted
     */
    private ClientProductProvider getSampleProvider() {
        ClientProductProvider productProvider = new ClientProductProvider();
        //productProvider.setOwnerProductId(5308304431513600L);
        //productProvider.setExternalURL("http://ecx.images-amazon.com/images/I/91kHfDTB1-L._UL1500_.jpg");
        //productProvider.setOnSale(true);
        // productProvider.setPrice(23.65);
        return productProvider;
    }
}
