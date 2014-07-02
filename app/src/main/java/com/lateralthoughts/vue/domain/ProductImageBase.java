package com.lateralthoughts.vue.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
public class ProductImageBase {

    /** Primary key */
    @Getter @Setter Long id;
    
    /** Parent information */
    @Getter @Setter Long ownerProductId;

    @Getter @Setter String externalURL;
    @Getter @Setter String internalURL;
    @Getter @Setter Float orignalHeight;
    @Getter @Setter Float orignalWidth;
    @Getter @Setter Float modifiedHeight;
    @Getter @Setter Float modifiedWidth;
    @Getter @Setter String description;

}