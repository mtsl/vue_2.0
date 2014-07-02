package com.lateralthoughts.vue.domain;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.googlecode.objectify.annotation.Index;

@EqualsAndHashCode
@NoArgsConstructor
public class ProductProviderBase
{
    /** Primary key */
    @Getter @Setter Long id;
    
    /** Parent information */
    @Getter @Setter Long ownerProductId;

    public enum ProductAvailabilityEnum {
        EXCESS_INVENTORY,
        IN_STOCK,
        RUNNING_LOW,
        TEMPORARY_UNAVAILABLE,
        PERMANENTLY_UNAVAILABLE,
        OUT_OF_STOCK
    };

    @Getter @Setter String externalURL; 
    @Getter @Setter int quantity;

    /**
     * Indexed search-able fields
     */
    @Getter @Setter @Index String currencyCode;
    @Getter @Setter @Index String store;
    @Getter @Setter @Index ProductAvailabilityEnum availability;
    @Getter @Setter @Index double price;
    @Getter @Setter @Index boolean onSale;
    @Getter @Setter @Index float salePrice;
    @Getter @Setter @Index long saleExpirtyDate;

    /** TODO: Still thinking if these should be modelled as sizes or searchable constant strings */
    @Getter @Setter List<String> availableColors;
    @Getter @Setter List<String> availableSizes;
    @Getter @Setter List<Long> availableAtZipCodes;
}
