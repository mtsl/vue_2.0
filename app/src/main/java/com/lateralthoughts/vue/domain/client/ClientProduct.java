package com.lateralthoughts.vue.domain.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lateralthoughts.vue.domain.ProductBase;

public class ClientProduct extends ProductBase {

    /** Read-only user fields, that are populated by the server.*/
    List<ClientProductProvider> productProviders;
    List<ClientProductImage> images;
    List<ClientProductTag> tags;
    List<ClientProductComment> comments;
    List<ClientProductRating> ratings;
    
    /** Read-only provenance fields */
    Long orignalCreatorId;
    Long orignalProductId;
    
    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public Long getOrignalCreatorId()
    {
        return orignalCreatorId;
    }

    /**
     * Mark as property for de-serialization
     * @return
     */
    @JsonProperty
    public void setOrignalCreatorId(Long orignalCreatorId)
    {
        this.orignalCreatorId = orignalCreatorId;
    }

    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public Long getOrignalProductId()
    {
        return orignalProductId;
    }

    /**
     * Mark as property for de-serialization
     * @return
     */
    @JsonProperty
    public void setOrignalProductId(Long orignalProductId)
    {
        this.orignalProductId = orignalProductId;
    }

    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public List<ClientProductProvider> getProductProviders() {
        return productProviders;
    }

    /**
     * Mark as property for de-serialization
     * @return
     */
    @JsonProperty
    public void setProductProviders(List<ClientProductProvider> productProviders) {
        this.productProviders = productProviders;
    }

    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public List<ClientProductTag> getTags() {
        return this.tags;
    }

    /**
     * Mark as property for de-serialization
     * @return
     */
    @JsonProperty
    public void setTags(List<ClientProductTag> tags) {
        this.tags = tags;
    }

    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public List<ClientProductImage> getImages() {
        return images;
    }

    /**
     * Mark as property for de-serialization
     * @return
     */
    @JsonProperty
    public void setImages(List<ClientProductImage> images) {
        this.images = images;
    }

    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public List<ClientProductComment> getComments() {
        return comments;
    }

    @JsonProperty
    public void setComments(List<ClientProductComment> comments) {
        this.comments = comments;
    }

    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public List<ClientProductRating> getRatings() {
        return ratings;
    }

    @JsonProperty
    public void setRatings(List<ClientProductRating> ratings) {
        this.ratings = ratings;
    }
}
