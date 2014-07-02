package com.lateralthoughts.vue.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.googlecode.objectify.annotation.Index;

@EqualsAndHashCode
@NoArgsConstructor
public class ProductRatingBase {

    /** Parent information */
    @Getter @Setter @Index Long ownerProductId;
    @Getter @Setter @Index Long ownerUserId;

    /** Primary key */
    @Getter @Setter Long id;
    @Getter @Setter @Index Boolean liked;
}
