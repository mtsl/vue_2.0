package com.lateralthoughts.vue.personal.product;

import android.util.Log;

import com.lateralthoughts.vue.domain.client.ClientProduct;
import com.lateralthoughts.vue.personal.OnResult;


public class ProductManager {
    ProductHelper productHelper;
      public  ProductManager(){
           productHelper = new ProductHelper();
           productHelper.type = ClientProduct.class;
           productHelper.callback = new ResultCallBack();
       }
       private String logMessage;
       private final String TAG = "ProductManager";
    public void createClientProduct(ClientProduct clientProduct){
        logMessage = "CreateClientProduct";
        productHelper.requestType = ProductManagerTask.CREATE_PRODUCT;
        productHelper.object = clientProduct;

        callProductTask(productHelper);
    }
    public void updateClientProduct(ClientProduct updateClientProduct){
        logMessage = "UpdateClientProduct";
        productHelper.requestType = ProductManagerTask.UPDATE_PRODUCT;
        productHelper.object = updateClientProduct;
        callProductTask(productHelper);
    }
    public void getClientProduct(Long id){
        logMessage = "GetClientProduct";
        productHelper.requestType = ProductManagerTask.GET_PRODUCT;
        productHelper.id = id;
        callProductTask(productHelper);
    }
    public void deleteClientProduct(Long id){
        logMessage = "DeleteClientProduct";
        productHelper.requestType = ProductManagerTask.DELETE_PRODUCT;
        productHelper.id = id;
        callProductTask(productHelper);


    }

    /**
     * will be executed when result is ready.
     */
    private class  ResultCallBack implements OnResult {
        @Override
        public void onResultComplete(boolean status,Object object) {
            if(status) {
                ClientProduct clientProduct = (ClientProduct) object;
                Log.i(TAG, TAG + " " + logMessage + " Success");
            } else {
                Log.i(TAG, TAG + " " + logMessage + " Failed");
            }
        }
    }

    /**
     *
     * background task for executing the user request.
     */
    private void callProductTask( ProductHelper helper){
        ProductManagerTask productManagerTask = new ProductManagerTask(helper);
        productManagerTask.execute();
    }

    /**
     *
     * test code
     */
    private ClientProduct createProduct()
    {
        ClientProduct product = new ClientProduct();
        //product.setDescription("Dummy description");
        //product.setOwnerAisleId(Long.valueOf("4887425285357568"));
        //product.setCurrentProductState(ProductBase.ProductStateEnum.USER_CREATED);
        //product.setTitle("Test product");
        return product;
    }
    /**
     * test code.
     */
    private ClientProduct updateClientProductObject(){
        ClientProduct product = new ClientProduct();
        //product.setId(5423325266313216L);
       // product.setOwnerProductListId(null);
        //product.setOrignalCreatorId(null);
        //product.setDescription("Dummy description");
        //product.setOwnerAisleId(Long.valueOf("4887425285357568"));
        //product.setCurrentProductState(ProductBase.ProductStateEnum.USER_CREATED);
        //product.setTitle("Test product");
        return product;
    }
}
