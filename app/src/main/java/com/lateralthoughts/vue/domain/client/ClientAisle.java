package com.lateralthoughts.vue.domain.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lateralthoughts.vue.domain.AisleBase;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClientAisle extends AisleBase{
    
    List<ClientProduct> productList;

    /**
     * Ignore serialization of this field
     * @return
     */
    @JsonIgnore
    public List<ClientProduct> getProductList()
    {
        return productList;
    }

    /**
     * Mark as property for de-serialization
     * @return
     */
    @JsonProperty
    public void setProductList(List<ClientProduct> productList)
    {
        this.productList = productList;
    }
}
