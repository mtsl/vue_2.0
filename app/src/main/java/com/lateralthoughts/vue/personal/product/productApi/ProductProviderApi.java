package com.lateralthoughts.vue.personal.product.productApi;


import com.lateralthoughts.vue.utils.UrlConstants;

public class ProductProviderApi implements ProductApiInterface {
    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String createUrl() {
        return UrlConstants.CREATE_PRODUCTPROVIDER_RESTURL;
    }

    @Override
    public String updateUrl() {
        return null;
    }

    @Override
    public String deleteUrl() {
        return null;

    }
}
