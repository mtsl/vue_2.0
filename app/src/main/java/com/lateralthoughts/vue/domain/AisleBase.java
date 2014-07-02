package com.lateralthoughts.vue.domain;

import com.googlecode.objectify.annotation.Index;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
public class AisleBase {
    
    /** Primary key */
    @Getter @Setter Long id;
    
    /** Parent information */
    @Getter @Setter Long ownerUserId;

    /** Aisle fields */
    @Getter @Setter @Index String category;
    @Getter @Setter @Index String lookingFor;
    @Getter @Setter @Index String name;
    @Getter @Setter @Index String occassion;
    @Getter @Setter @Index int bookmarkCount;
    @Getter @Setter @Index String description;
}
