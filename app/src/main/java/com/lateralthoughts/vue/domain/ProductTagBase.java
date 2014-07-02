package com.lateralthoughts.vue.domain;

import com.googlecode.objectify.annotation.Index;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
public class ProductTagBase {

    /** Primary key */
    @Getter @Setter String tagString;
    
    @Getter @Setter @Index String tagCategory;
    @Getter @Setter @Index String tagSubCategory;
}
