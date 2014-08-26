package com.lateralthoughts.vue.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Index;

@EqualsAndHashCode
public class UserBase {

/*    public static final String DEFAULT_FACEBOOK_ID = "FACEBOOK_ID_UNKNOWN";
    public static final String DEFAULT_GOOGLEPLUS_ID = "GOOGLE_PLUS_ID_UNKNOWN";

    *//** Primary key *//*
    @Getter @Setter public Long id;
    
    @Getter @Setter public String email;
    @Getter @Setter public String firstName;
    @Getter @Setter public String lastName;
    @Getter @Setter public Long joinTime;
    @Getter @Setter public String deviceId;
    @Getter @Setter public String gcmRegistrationId;

    *//** Indexed as objectify can only query indexed fields *//*
    @Getter @Setter @Index public String facebookId;
    @Getter @Setter @Index public String googlePlusId;

    *//** User photo URL. Client is responsible for uploading to BlobStore and storing this.*//*
    @Getter @Setter public String userImageURL;
    public String facebookShortTermAcessToken;


    public UserBase() {
        facebookId = DEFAULT_FACEBOOK_ID;
        googlePlusId = DEFAULT_GOOGLEPLUS_ID;
    }*/
    public static final String DEFAULT_FACEBOOK_ID = "FACEBOOK_ID_UNKNOWN";
    public static final String DEFAULT_GOOGLEPLUS_ID = "GOOGLE_PLUS_ID_UNKNOWN";

    Long id;

    String email;

    public String getFirstName() {
        return firstName;
    }

    public static String getDefaultFacebookId() {
        return DEFAULT_FACEBOOK_ID;
    }

    public static String getDefaultGoogleplusId() {
        return DEFAULT_GOOGLEPLUS_ID;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getJoinTime() {
        return joinTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getGcmRegistrationId() {
        return gcmRegistrationId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public String getGooglePlusId() {
        return googlePlusId;
    }

    public String getFacebookShortTermAcessToken() {
        return facebookShortTermAcessToken;
    }

    public String getFacebookLongTermAcessToken() {
        return facebookLongTermAcessToken;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    String firstName;
    String lastName;
    Long joinTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJoinTime(Long joinTime) {
        this.joinTime = joinTime;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setGcmRegistrationId(String gcmRegistrationId) {
        this.gcmRegistrationId = gcmRegistrationId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public void setGooglePlusId(String googlePlusId) {
        this.googlePlusId = googlePlusId;
    }

    public void setFacebookShortTermAcessToken(String facebookShortTermAcessToken) {
        this.facebookShortTermAcessToken = facebookShortTermAcessToken;
    }

    public void setFacebookLongTermAcessToken(String facebookLongTermAcessToken) {
        this.facebookLongTermAcessToken = facebookLongTermAcessToken;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    String deviceId;
    String gcmRegistrationId;

    /**
     * Indexed as objectify can only query indexed fields
     */
    String facebookId;
    String googlePlusId;

    String facebookShortTermAcessToken;
    String facebookLongTermAcessToken;

    /**
     * User photo URL. Client is responsible for uploading to BlobStore and storing this.
     */
    String userImageURL;

    public UserBase() {
        facebookId = DEFAULT_FACEBOOK_ID;
        googlePlusId = DEFAULT_GOOGLEPLUS_ID;
    }
    
}
