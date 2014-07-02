package com.lateralthoughts.vue.domain;

import java.util.List;

import com.googlecode.objectify.annotation.Index;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
public class ProductBase {

    /** Primary key */
    @Getter @Setter Long id;
    
    /** Parent information */
    @Getter @Setter Long ownerAisleId;
    @Getter @Setter Long ownerProductListId;

    @Getter @Setter @Index String title;
    @Getter @Setter @Index String description;
    
    public enum ProductStateEnum {
        USER_CREATED,
        CLONED,
        CURATED}
    @Getter @Setter ProductStateEnum currentProductState;

    @Getter @Setter List<Long> relatedProductIds;
    
}