package com.lateralthoughts.vue.personal.product.productApi;


import com.lateralthoughts.vue.utils.UrlConstants;

public class ProductImageApi implements ProductApiInterface {
    @Override
    public String getUrl() {
        return UrlConstants.GET_PRODUCTIMAGE_RESTURL;
    }

    @Override
    public String createUrl() {
        return UrlConstants.CREATE_PRODUCTIMAGE_RESTURL;
    }

    @Override
    public String updateUrl() {
        return UrlConstants.UPDATE_PRODUCTIMAGE_RESTURL;
    }

    @Override
    public String deleteUrl() {
        return UrlConstants.DELETE_PRODUCTIMAGE_RESTURL;

    }
}
