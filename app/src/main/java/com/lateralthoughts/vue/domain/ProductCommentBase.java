package com.lateralthoughts.vue.domain;

import com.googlecode.objectify.annotation.Index;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
public class ProductCommentBase {

    /** Primary key */
    @Getter @Setter Long id;
    
    /** Parent information */
    @Getter @Setter @Index Long ownerProductId;
    @Getter @Setter @Index Long ownerUserId;
    
    @Getter @Setter String comment;
}



