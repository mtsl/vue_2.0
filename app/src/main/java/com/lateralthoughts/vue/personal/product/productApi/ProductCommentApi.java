package com.lateralthoughts.vue.personal.product.productApi;

import com.lateralthoughts.vue.utils.UrlConstants;

public class ProductCommentApi implements ProductApiInterface {
    @Override
    public String getUrl() {
        return UrlConstants.GET_PRODUCTCOMMENT_RESTURL;
    }

    @Override
    public String createUrl() {
        return UrlConstants.CREATE_PRODUCTCOMMENT_RESTURL;
    }

    @Override
    public String updateUrl() {
        return UrlConstants.UPDATE_PRODUCTCOMMENT_RESTURL;
    }

    @Override
    public String deleteUrl() {
        return UrlConstants.DELETE_PRODUCTCOMMENT_RESTURL;

    }
}
