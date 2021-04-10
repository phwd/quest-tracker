package com.facebook.quicklog.identifiers;

public class ImagesFetch {
    public static final int LOG_PARAM_BACKGROUND_RESOURCE_ID_EXISTS = 5439510;
    public static final int LOG_PARAM_DRAWABLE_FROM_FETCH_IMAGE_PARAMS = 5439512;
    public static final int LOG_PARAM_IMAGESPEC_DRAWABLE_EXISTS = 5439511;
    public static final int LOG_PARAM_IMAGESPEC_RESOURCE_ID_EXISTS = 5439513;
    public static final int LOG_PARAM_IMAGE_FETCH_EXCEPTION = 5439505;
    public static final int LOG_PARAM_IMAGE_SOURCE = 5439504;
    public static final int LOG_PARAM_IS_IMAGEVIEW_VISIBLE = 5439509;
    public static final int LOG_PARAM_IS_SHOWN_IN_GALLERY = 5439508;
    public static final int LOG_PARAM_NETWORK_FETCH_REQUEST_START_DELAY = 5439501;
    public static final int LOG_PARAM_OPERATION_RESULT = 5439506;
    public static final int LOG_PARAM_RETRY_NUMBER = 5439502;
    public static final int LOG_PARAM_STACKTRACE = 5439507;
    public static final int LOG_PARAM_URI_KEY = 5439514;
    public static final int LOG_PARAM_URL_BEING_FETCHED = 5439503;
    public static final short MODULE_ID = 83;
    public static final int URLIMAGE_BINDMODEL_TO_RENDER = 5439489;
    public static final int URLIMAGE_BITMAP_FROM_FILE = 5439497;
    public static final int URLIMAGE_DOWNLOAD_AND_INSERT_INTO_CACHE = 5439493;
    public static final int URLIMAGE_IMAGE_PARSING = 5439495;
    public static final int URLIMAGE_IMAGE_PROCESSING = 5439494;
    public static final int URLIMAGE_LOG_MODE = 5439499;
    public static final int URLIMAGE_NETWORK_FETCH = 5439492;
    public static final int URLIMAGE_NULL_OR_EMPTY_URI = 5439498;
    public static final int URLIMAGE_PIPELINE_EXPERIMENT = 5439490;
    public static final int URLIMAGE_PREFETCH = 5439491;
    public static final int URLIMAGE_UNDERLYING_IMAGE_PARSING = 5439496;
    public static final int URLIMAGE_UPDATE_IMAGE_VIEW = 5439500;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "IMAGES_FETCH_URLIMAGE_BINDMODEL_TO_RENDER";
            case 2:
                return "IMAGES_FETCH_URLIMAGE_PIPELINE_EXPERIMENT";
            case 3:
                return "IMAGES_FETCH_URLIMAGE_PREFETCH";
            case 4:
                return "IMAGES_FETCH_URLIMAGE_NETWORK_FETCH";
            case 5:
                return "IMAGES_FETCH_URLIMAGE_DOWNLOAD_AND_INSERT_INTO_CACHE";
            case 6:
                return "IMAGES_FETCH_URLIMAGE_IMAGE_PROCESSING";
            case 7:
                return "IMAGES_FETCH_URLIMAGE_IMAGE_PARSING";
            case 8:
                return "IMAGES_FETCH_URLIMAGE_UNDERLYING_IMAGE_PARSING";
            case 9:
                return "IMAGES_FETCH_URLIMAGE_BITMAP_FROM_FILE";
            case 10:
                return "IMAGES_FETCH_URLIMAGE_NULL_OR_EMPTY_URI";
            case 11:
                return "IMAGES_FETCH_URLIMAGE_LOG_MODE";
            case 12:
                return "IMAGES_FETCH_URLIMAGE_UPDATE_IMAGE_VIEW";
            case 13:
                return "IMAGES_FETCH_LOG_PARAM_NETWORK_FETCH_REQUEST_START_DELAY";
            case 14:
                return "IMAGES_FETCH_LOG_PARAM_RETRY_NUMBER";
            case 15:
                return "IMAGES_FETCH_LOG_PARAM_URL_BEING_FETCHED";
            case 16:
                return "IMAGES_FETCH_LOG_PARAM_IMAGE_SOURCE";
            case 17:
                return "IMAGES_FETCH_LOG_PARAM_IMAGE_FETCH_EXCEPTION";
            case 18:
                return "IMAGES_FETCH_LOG_PARAM_OPERATION_RESULT";
            case 19:
                return "IMAGES_FETCH_LOG_PARAM_STACKTRACE";
            case 20:
                return "IMAGES_FETCH_LOG_PARAM_IS_SHOWN_IN_GALLERY";
            case 21:
                return "IMAGES_FETCH_LOG_PARAM_IS_IMAGEVIEW_VISIBLE";
            case 22:
                return "IMAGES_FETCH_LOG_PARAM_BACKGROUND_RESOURCE_ID_EXISTS";
            case 23:
                return "IMAGES_FETCH_LOG_PARAM_IMAGESPEC_DRAWABLE_EXISTS";
            case 24:
                return "IMAGES_FETCH_LOG_PARAM_DRAWABLE_FROM_FETCH_IMAGE_PARAMS";
            case 25:
                return "IMAGES_FETCH_LOG_PARAM_IMAGESPEC_RESOURCE_ID_EXISTS";
            case 26:
                return "IMAGES_FETCH_LOG_PARAM_URI_KEY";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
