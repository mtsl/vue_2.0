package com.lateralthoughts.vue.personal.product;

import android.os.AsyncTask;

import com.lateralthoughts.vue.domain.client.ClientProduct;
import com.lateralthoughts.vue.domain.client.ClientProductImage;
import com.lateralthoughts.vue.domain.client.ClientProductProvider;
import com.lateralthoughts.vue.personal.GenericNetWorker;
import com.lateralthoughts.vue.personal.product.productApi.ProductApiConnector;
import com.lateralthoughts.vue.personal.product.productApi.ProductApiInterface;


/**
 * Generic Async task for all product types(product,image,providers,comment,ratings.
 */
public class ProductManagerTask extends AsyncTask<Void, Void, Object> {
    private static final String TAG = "productMangerTask";
    public static final int CREATE_PRODUCT = 0;
    public static final int UPDATE_PRODUCT = 1;
    public static final int GET_PRODUCT = 2;
    public static final int DELETE_PRODUCT = 3;
    private ProductHelper productHelper;
    private ProductApiInterface api;

    public ProductManagerTask(ProductHelper productHelper) {
        this.productHelper = productHelper;
        api = ProductApiConnector.getUrlInterface(productHelper.type);
    }

    @Override
    protected Object doInBackground(Void... voids) {
        try {
            String url;
            switch (productHelper.requestType) {

                case CREATE_PRODUCT:
                    url = api.createUrl();
                    if (productHelper.type.equals(ClientProduct.class)) {
                        GenericNetWorker<ClientProduct> clientProductHelper = new GenericNetWorker<ClientProduct>(ClientProduct.class);
                        ClientProduct clientProduct = (ClientProduct) productHelper.object;
                        productHelper.object = clientProductHelper.createObject(clientProduct, url);
                    } else if (productHelper.type.equals(ClientProductImage.class)) {
                        GenericNetWorker<ClientProductImage> clientProductHelper = new GenericNetWorker<ClientProductImage>(ClientProductImage.class);
                        ClientProductImage clientProductImage = (ClientProductImage) productHelper.object;
                        productHelper.object = clientProductHelper.createObject(clientProductImage, url);
                    } else if (productHelper.type.equals(ClientProductProvider.class)) {
                        GenericNetWorker<ClientProductProvider> clientProductHelper = new GenericNetWorker<ClientProductProvider>(ClientProductProvider.class);
                        ClientProductProvider clientProductProvider = (ClientProductProvider) productHelper.object;
                        productHelper.object = clientProductHelper.createObject(clientProductProvider, url);
                    }
                    return productHelper.object;

                case UPDATE_PRODUCT:
                    url = api.updateUrl();
                    if (productHelper.type.equals(ClientProduct.class)) {
                        GenericNetWorker<ClientProduct> clientProductHelper = new GenericNetWorker<ClientProduct>(ClientProduct.class);
                        ClientProduct clientProduct = (ClientProduct) productHelper.object;
                        //productHelper.object = clientProductHelper.updateObject(clientProduct, clientProduct.getId(), url);
                    } else if (productHelper.type.equals(ClientProductImage.class)) {
                        GenericNetWorker<ClientProductImage> clientProductHelper = new GenericNetWorker<ClientProductImage>(ClientProductImage.class);
                        ClientProductImage clientProductImage = (ClientProductImage) productHelper.object;
                        //productHelper.object = clientProductHelper.updateObject(clientProductImage, clientProductImage.getId(), url);
                    } else if (productHelper.type.equals(ClientProductProvider.class)) {
                        GenericNetWorker<ClientProductProvider> clientProductHelper = new GenericNetWorker<ClientProductProvider>(ClientProductProvider.class);
                        ClientProductProvider clientProductProvider = (ClientProductProvider) productHelper.object;
                        // productHelper.object = clientProductHelper.updateObject(clientProductProvider, clientProductProvider.getId(), url);
                    }
                    return productHelper.object;
                case GET_PRODUCT:

                    url = api.getUrl();
                    if (productHelper.type.equals(ClientProduct.class)) {
                        GenericNetWorker<ClientProduct> clientProductHelper = new GenericNetWorker<ClientProduct>(ClientProduct.class);
                        productHelper.object = clientProductHelper.getObject(url, productHelper.id);
                    } else if (productHelper.type.equals(ClientProductImage.class)) {
                        GenericNetWorker<ClientProductImage> clientProductHelper = new GenericNetWorker<ClientProductImage>(ClientProductImage.class);
                        productHelper.object = clientProductHelper.getObject(url, productHelper.id);
                    } else if (productHelper.type.equals(ClientProductProvider.class)) {
                        GenericNetWorker<ClientProductProvider> clientProductHelper = new GenericNetWorker<ClientProductProvider>(ClientProductProvider.class);
                        productHelper.object = clientProductHelper.getObject(url, productHelper.id);
                    }
                    return productHelper.object;
                case DELETE_PRODUCT:
                    url = api.deleteUrl();
                    if (productHelper.type.equals(ClientProduct.class)) {
                        GenericNetWorker<ClientProduct> clientProductHelper = new GenericNetWorker<ClientProduct>(ClientProduct.class);
                        clientProductHelper.deleteObject(productHelper.id, url);
                    } else if (productHelper.type.equals(ClientProductImage.class)) {
                        GenericNetWorker<ClientProductImage> clientProductHelper = new GenericNetWorker<ClientProductImage>(ClientProductImage.class);
                        clientProductHelper.deleteObject(productHelper.id, url);
                    } else if (productHelper.type.equals(ClientProductProvider.class)) {
                        GenericNetWorker<ClientProductProvider> clientProductHelper = new GenericNetWorker<ClientProductProvider>(ClientProductProvider.class);
                        clientProductHelper.deleteObject(productHelper.id, url);
                    }
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object clientProduct) {
        super.onPostExecute(clientProduct);
        if (clientProduct != null) {
            if (productHelper.callback != null)
                productHelper.callback.onResultComplete(true, clientProduct);
        } else {
            if (productHelper.callback != null)
                productHelper.callback.onResultComplete(false, null);
        }
    }

}
