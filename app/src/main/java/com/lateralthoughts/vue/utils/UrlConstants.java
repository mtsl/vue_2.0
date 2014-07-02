package com.lateralthoughts.vue.utils;

public class UrlConstants {
    // Uncomment this to point to production content server
    // public static final String SERVER_BASE_URL =
    // "http://2dot12-restlet.vueapi-canary-development.appspot.com/";
    public static final String SERVER_BASE_URL = "http://2dot12-restlet.vueapi-canary.appspot.com/";

    private static final String CANARY_SERVER_PROJECT_ID = "876955216873";
    private static final String DEVELOPMENT_SERVER_PROJECT_ID = "477960328185";
    private static final String DEVELOPMENT1_SERVER_PROJECT_ID = "341676083313";
    public static final String CURRENT_SERVER_PROJECT_ID = CANARY_SERVER_PROJECT_ID;

    /**
     * (C)reate routine URL's
     */
    public static final String CREATE_USER_RESTURL = SERVER_BASE_URL
            + "api/usercreate";
    public static final String CREATE_AISLE_RESTURL = SERVER_BASE_URL
            + "api/aislecreate";
    public static final String CREATE_IMAGE_RESTURL = SERVER_BASE_URL
            + "api/imagecreate";
    public static final String CREATE_BOOKMARK_RESTURL = SERVER_BASE_URL
            + "api/aislebookmarkcreate";
    public static final String CREATE_RATING_RESTURL = SERVER_BASE_URL
            + "api/imageratingcreate";
    public static final String CREATE_IMAGECOMMENT_RESTURL = SERVER_BASE_URL
            + "api/imagecommentcreate";

    public static final String GET_USER_RESTURL = SERVER_BASE_URL
            + "api/userget/id/";
    public static final String GET_USER_FACEBOOK_ID_RESTURL = SERVER_BASE_URL
            + "api/userget/facebook/";
    public static final String GET_USER_GOOGLEPLUS_ID_RESTURL = SERVER_BASE_URL
            + "api/userget/googleplus/";
    public static final String GET_AISLE_RESTURL = SERVER_BASE_URL
            + "api/aisleget/id/";
    public static final String GET_AISLELIST_BYUSER_RESTURL = SERVER_BASE_URL
            + "api/aislesget/user";
    public static final String GET_IMAGELIST_RESTURL = SERVER_BASE_URL
            + "api/imagesget/aisle/";
    public static final String GET_TRENDINGAISLES_RESTURL = SERVER_BASE_URL
            + "api/trendingaislesgetorderedbytime";
    public static final String GET_BOOKMARK_Aisles = SERVER_BASE_URL
            + "api/aislebookmarksgetall";
    public static final String GET_RATINGS_RESTURL = SERVER_BASE_URL
            + "api/imageratingsgetall";
    public static final String GET_UNIQUE_ONETIME_IMAGE_UPLOAD_RESTURL = SERVER_BASE_URL
            + "api/getUrlToUploadImage";
    public static final String GET_IMAGE_FILE_RESTURL = SERVER_BASE_URL
            + "upload";

    public static final String SEARCH_BY_KEYWORD_BASE_URL = SERVER_BASE_URL
            + "api/getaisleswithmatchingoccassion/";
    public static final String SEARCH_BY_USER = SERVER_BASE_URL
            + "api/getaisleswithmatchingfacebookORGPlus/";

    public static final String DELETE_IMAGE_RESTURL = SERVER_BASE_URL
            + "api/imagedelete";
    public static final String GET_IMAGE_RESTURL = SERVER_BASE_URL
            + "api/imageget/id/";

    public static final String UPDATE_USER_RESTURL = SERVER_BASE_URL
            + "api/userupdate";
    public static final String UPDATE_AISLE_RESTURL = SERVER_BASE_URL
            + "api/aisleupdate";
    public static final String UPDATE_BOOKMARK_RESTURL = SERVER_BASE_URL
            + "api/aislebookmarkupdate";
    public static final String UPDATE_RATING_RESTURL = SERVER_BASE_URL
            + "api/imageratingupdate";
}

