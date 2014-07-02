package com.lateralthoughts.vue.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Index;

@EqualsAndHashCode
public class UserBase {

    public static final String DEFAULT_FACEBOOK_ID = "FACEBOOK_ID_UNKNOWN";
    public static final String DEFAULT_GOOGLEPLUS_ID = "GOOGLE_PLUS_ID_UNKNOWN";

    /** Primary key */
    @Getter @Setter Long id;
    
    @Getter @Setter String email;
    @Getter @Setter String firstName;
    @Getter @Setter String lastName;
    @Getter @Setter Long joinTime;
    @Getter @Setter String deviceId;
    @Getter @Setter String gcmRegistrationId;

    /** Indexed as objectify can only query indexed fields */
    @Getter @Setter @Index String facebookId;
    @Getter @Setter @Index String googlePlusId;

    /** User photo URL. Client is responsible for uploading to BlobStore and storing this.*/
    @Getter @Setter String userImageURL;

    public UserBase() {
        facebookId = DEFAULT_FACEBOOK_ID;
        googlePlusId = DEFAULT_GOOGLEPLUS_ID;
    }
    
    
}
