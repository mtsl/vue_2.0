package com.lateralthoughts.vue.domain.client;

import java.util.List;

import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lateralthoughts.vue.domain.UserBase;

@NoArgsConstructor
public class ClientUser extends UserBase {

    List<ClientProductPurchaseRecord> purchaseHistory;

    @JsonIgnore
    public List<ClientProductPurchaseRecord> getPurchaseHistory()
    {
        return purchaseHistory;
    }

    
    /**
     * Mark as property for de-serialization ONLY
     * @return
     */
    @JsonProperty
    public void setPurchaseHistory(List<ClientProductPurchaseRecord> purchaseHistory)
    {
        this.purchaseHistory = purchaseHistory;
    }
}
