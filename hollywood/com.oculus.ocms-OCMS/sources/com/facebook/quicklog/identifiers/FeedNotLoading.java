package com.facebook.quicklog.identifiers;

public class FeedNotLoading {
    public static final int FETCH_ERROR_API = 16056324;
    public static final int FETCH_ERROR_API_SERVER_OOM = 16056327;
    public static final int FETCH_ERROR_CONNECTION_FAILURE = 16056329;
    public static final int FETCH_ERROR_CONNECTION_FAILURE_NETWORK_UNREACHABLE = 16056330;
    public static final int FETCH_ERROR_HTTP_40X = 16056326;
    public static final int FETCH_ERROR_HTTP_500 = 16056325;
    public static final int FETCH_ERROR_OTHER = 16056328;
    public static final int FETCH_INCOMPLETE_10S = 16056339;
    public static final int FETCH_INCOMPLETE_5S = 16056338;
    public static final int FETCH_REQUEST = 16056321;
    public static final int FETCH_SUCCESS_NON_ZERO = 16056322;
    public static final int FETCH_SUCCESS_ZERO = 16056323;
    public static final int INDICATOR_SHOWN = 16056333;
    public static final short MODULE_ID = 245;
    public static final int PULL_TO_REFRESH = 16056337;
    public static final int RETRY_BUTTON_CLICKED = 16056332;
    public static final int SAD_CLOUD = 16056331;
    public static final int STORIES_ADDED_TO_UI = 16056335;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "FEED_NOT_LOADING_FETCH_REQUEST";
            case 2:
                return "FEED_NOT_LOADING_FETCH_SUCCESS_NON_ZERO";
            case 3:
                return "FEED_NOT_LOADING_FETCH_SUCCESS_ZERO";
            case 4:
                return "FEED_NOT_LOADING_FETCH_ERROR_API";
            case 5:
                return "FEED_NOT_LOADING_FETCH_ERROR_HTTP_500";
            case 6:
                return "FEED_NOT_LOADING_FETCH_ERROR_HTTP_40X";
            case 7:
                return "FEED_NOT_LOADING_FETCH_ERROR_API_SERVER_OOM";
            case 8:
                return "FEED_NOT_LOADING_FETCH_ERROR_OTHER";
            case 9:
                return "FEED_NOT_LOADING_FETCH_ERROR_CONNECTION_FAILURE";
            case 10:
                return "FEED_NOT_LOADING_FETCH_ERROR_CONNECTION_FAILURE_NETWORK_UNREACHABLE";
            case 11:
                return "FEED_NOT_LOADING_SAD_CLOUD";
            case 12:
                return "FEED_NOT_LOADING_RETRY_BUTTON_CLICKED";
            case 13:
                return "FEED_NOT_LOADING_INDICATOR_SHOWN";
            case 14:
            case 16:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 15:
                return "FEED_NOT_LOADING_STORIES_ADDED_TO_UI";
            case 17:
                return "FEED_NOT_LOADING_PULL_TO_REFRESH";
            case 18:
                return "FEED_NOT_LOADING_FETCH_INCOMPLETE_5S";
            case 19:
                return "FEED_NOT_LOADING_FETCH_INCOMPLETE_10S";
        }
    }
}
