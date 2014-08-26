package com.lateralthoughts.vue.utils;

public class UrlConstants {
    private static final String DEVELOPMENT_SERVER_PROJECT_ID =
            "341676083313";
    private static final String DEVELOPMENT_SERVER_APPLICATION_ID =
            "vue-server-dev.appspot.com/";

    private static final String TEST_SERVER_PROJECT_ID =
            "477960328185";
    private static final String TEST_SERVER_APPLICATION_ID =
            "vue-server-test.appspot.com/";

    private static final String PROD_SERVER_PROJECT_ID =
            "876955216873";
    private static final String PROD_SERVER_APPLICATION_ID =
            "vue-server-prod.appspot.com/";

    private static final String LOCALHOST_SERVER_PROJECT_ID =
            "0";
    private static final String LOCALHOST_SERVER_APPLICATION_ID =
            "localhost:8888/";


    public static final String CURRENT_SERVER_PROJECT_ID =
            DEVELOPMENT_SERVER_PROJECT_ID;
    public static final String CURRENT_SERVER_APPLICATION_ID =
            DEVELOPMENT_SERVER_APPLICATION_ID;

    private static final String CURRENT_VERSION =
            "3dot1.";
    //private static final String CURRENT_VERSION =
    //		"";
    public static final String SERVER_PREFIX =
            "https://" + CURRENT_VERSION + CURRENT_SERVER_APPLICATION_ID;


    /**
     * (C)reate routine URL's
     */
    public static final String CREATE_USER_RESTURL =
            SERVER_PREFIX + "api/user";
    public static final String CREATE_AISLE_RESTURL =
            SERVER_PREFIX + "api/aisle";
    public static final String CREATE_PRODUCT_RESTURL =
            SERVER_PREFIX + "api/product";
    public static final String CREATE_PRODUCTCOMMENT_RESTURL =
            SERVER_PREFIX + "api/productcomment";
    public static final String CREATE_PRODUCTIMAGE_RESTURL =
            SERVER_PREFIX + "api/productimage";
    public static final String CREATE_PRODUCTRATING_RESTURL =
            SERVER_PREFIX + "api/productrating";
    public static final String CREATE_PRODUCTTAG_RESTURL =
            SERVER_PREFIX + "api/producttag";
    public static final String CREATE_BOOKMARK_RESTURL =
            SERVER_PREFIX + "api/aislebookmarkcreate";
    public static final String CREATE_WEBSIGNUPUSER_RESTURL =
            SERVER_PREFIX + "api/websignupusersave";
    public static final String CREATE_PRODUCTPROVIDER_RESTURL =
            SERVER_PREFIX+"api/productprovider";

    /**
     * (R)ead routine URL's
     */
    public static final String GET_USER_RESTURL =
            SERVER_PREFIX + "api/user";
    public static final String GET_USER_FACEBOOK_ID_RESTURL =
            SERVER_PREFIX + "api/facebookuser";
    public static final String GET_AISLE_RESTURL =
            SERVER_PREFIX + "api/aisle";
    public static final String GET_AISLES_BY_USER =
            SERVER_PREFIX + "api/aisles/user";
    public static final String GET_PRODUCT_RESTURL =
            SERVER_PREFIX + "api/product";
    public static final String GET_PRODUCTCOMMENT_RESTURL =
            SERVER_PREFIX + "api/productcomment";
    public static final String GET_PRODUCTIMAGE_RESTURL =
            SERVER_PREFIX + "api/productimage";
    public static final String GET_PRODUCTRATING_RESTURL =
            SERVER_PREFIX + "api/productrating";
    public static final String GET_PRODUCTTAG_RESTURL =
            SERVER_PREFIX + "api/producttag";
    public static final String GET_IMAGELIST_RESTURL =
            SERVER_PREFIX + "api/imagesget/aisle";
    public static final String GET_IMAGE_RESTURL =
            SERVER_PREFIX + "api/imageget/id";
    public static final String GET_BOOKMARKS_RESTURL =
            SERVER_PREFIX + "api/aislebookmarksgetall";
    public static final String GET_RATINGS_RESTURL =
            SERVER_PREFIX + "api/imageratingsgetall";
    public static final String GET_WEBSIGNUPUSER_RESTURL =
            SERVER_PREFIX + "api/websignupuserget";
    public static final String GET_TRENDINGAISLES_RESTURL =
            SERVER_PREFIX + "api/trendingaislesgetorderedbytime";
    public static final String GET_TRENDINGAISLESWITHIMAGES_RESTURL =
            SERVER_PREFIX + "api/trendingaislesgetorderedbytime/withimages";
    public static final String GET_UNIQUE_ONETIME_IMAGE_UPLOAD_RESTURL =
            SERVER_PREFIX + "api/getUrlToUploadImage";
    public static final String GET_IMAGE_FILE_RESTURL =
            SERVER_PREFIX + "upload";

    /**
     * (U)pdate routine URL's
     */
    public static final String UPDATE_AISLE_RESTURL =
            SERVER_PREFIX + "api/aisle";
    public static final String UPDATE_BOOKMARK_RESTURL =
            SERVER_PREFIX + "api/aislebookmarkupdate";
    public static final String UPDATE_USER_RESTURL =
            SERVER_PREFIX + "api/user";
    public static final String UPDATE_PRODUCT_RESTURL =
            SERVER_PREFIX + "api/product";
    public static final String UPDATE_PRODUCTCOMMENT_RESTURL =
            SERVER_PREFIX + "api/productcomment";
    public static final String UPDATE_PRODUCTIMAGE_RESTURL =
            SERVER_PREFIX + "api/productimage";
    public static final String UPDATE_PRODUCTRATING_RESTURL =
            SERVER_PREFIX + "api/productrating";
    public static final String UPDATE_PRODUCTTAG_RESTURL =
            SERVER_PREFIX + "api/producttag";



    /**
     * (D)elete routine URL's
     */
    public static final String DELETE_IMAGECOMMENT_RESTURL =
            SERVER_PREFIX + "api/imagecommentdelete";
    public static final String DELETE_USER_RESTURL =
            SERVER_PREFIX + "api/testdeleteusertree";
    public static final String DELETE_WEBSIGNUPUSER_RESTURL =
            SERVER_PREFIX + "api/testwebsignupuserdelete";
    public static final String DELETE_AISLE_RESTURL =
            SERVER_PREFIX + "api/aisle";
    public static final String DELETE_PRODUCT_RESTURL =
            SERVER_PREFIX + "api/product";
    public static final String DELETE_PRODUCTCOMMENT_RESTURL =
            SERVER_PREFIX + "api/productcomment";
    public static final String DELETE_PRODUCTIMAGE_RESTURL =
            SERVER_PREFIX + "api/productimage";
    public static final String DELETE_PRODUCTRATING_RESTURL =
            SERVER_PREFIX + "api/productrating";
    public static final String DELETE_PRODUCTTAG_RESTURL =
            SERVER_PREFIX + "api/producttag";


    /**
     * Search URL's
     */
    public static final String SEARCH_BY_KEYWORD_BASE_URL =
            SERVER_PREFIX + "api/getaisleswithmatchingoccassion/";
    public static final String SEARCH_BY_USER =
            SERVER_PREFIX + "api/getaisleswithmatchingfacebookORGPlus/";
    public static final String SEARCH_BY_FIRSTNAME_OR_LASTNAME_BASE_URL =
            SERVER_PREFIX + "api/getaisleswithmatchinguserName/";

    /**
     * Upload URL's
     */
    public static final String IMAGE_UPLOAD_RESTURL =
            SERVER_PREFIX + "upload";
    ;

}

