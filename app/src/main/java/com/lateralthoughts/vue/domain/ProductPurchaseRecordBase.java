package com.lateralthoughts.vue.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
public class ProductPurchaseRecordBase
{
    /** Primary key */
    @Getter @Setter Long id;
    
    /** Parent information */
    @Getter @Setter Long ownerUserId;

    @Getter @Setter Double purchasePrice;
    @Getter @Setter Long purchaseDataInEpochTimeSeconds;
    @Getter @Setter String freeFormPurchaseLocation;
    @Getter @Setter Float latitudeOfPurchase;
    @Getter @Setter Float longitudeOfPurchase;
}
