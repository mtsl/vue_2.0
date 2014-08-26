package com.lateralthoughts.vue.personal.product.productApi;


import com.lateralthoughts.vue.domain.client.ClientProduct;
import com.lateralthoughts.vue.domain.client.ClientProductComment;
import com.lateralthoughts.vue.domain.client.ClientProductImage;
import com.lateralthoughts.vue.domain.client.ClientProductProvider;
import com.lateralthoughts.vue.domain.client.ClientProductRating;

public class ProductApiConnector {
    public static ProductApiInterface getUrlInterface(Class type) {
        if (type.equals((ClientProduct.class))) {
            return new ProductClientApi();
        } else if (type.equals(ClientProductImage.class)) {
            return new ProductImageApi();
        } else if (type.equals(ClientProductComment.class)) {
            return new ProductCommentApi();
        } else if (type.equals(ClientProductProvider.class)) {
            return new ProductProviderApi();
        } else if (type.equals(ClientProductRating.class)) {
            return new ProductRatingApi();
        }

        return null;
    }
}
