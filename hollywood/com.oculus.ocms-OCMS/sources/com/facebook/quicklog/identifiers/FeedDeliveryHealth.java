package com.facebook.quicklog.identifiers;

public class FeedDeliveryHealth {
    public static final int DB_CLEAR_ANDROID = 31653894;
    public static final int DB_EMPTY_ANDROID = 31653893;
    public static final int FBLITE_FEED_HELATH = 31653908;
    public static final int FEED_FORMATTED_FBLITE = 31653918;
    public static final int FEED_RENDERED_ANDROID = 31653906;
    public static final int FEED_RENDERED_FB4A = 31653914;
    public static final int FEED_RENDERED_FBLITE = 31653917;
    public static final int FEED_REQUEST_FB4A = 31653909;
    public static final int FEED_RESPONSE_FB4A = 31653910;
    public static final int FETCH_ATTEMPT_ANDROID = 31653889;
    public static final int FETCH_ATTEMPT_FBLITE = 31653895;
    public static final short MODULE_ID = 483;
    public static final int NETWORK_CALLBACK_TIMEOUT_FB4A = 31653913;
    public static final int NETWORK_CALLBACK_TIMEOUT_FBLITE = 31653916;
    public static final int RECEIVED_EDGES_ANDROID = 31653892;
    public static final int RECEIVED_EDGES_FBLITE = 31653898;
    public static final int RESPONSE_EMPTY_ANDROID = 31653891;
    public static final int RESPONSE_FAILURE_ANDROID = 31653890;
    public static final int RESPONSE_FAILURE_FBLITE = 31653896;
    public static final int RETRIEVE_NEXT_STORY = 31653919;
    public static final int TIGON_REQUEST_FB4A = 31653912;
    public static final int TIGON_RESPONSE_FB4A = 31653915;

    public static String getMarkerName(int i) {
        if (i == 10) {
            return "FEED_DELIVERY_HEALTH_RECEIVED_EDGES_FBLITE";
        }
        if (i == 18) {
            return "FEED_DELIVERY_HEALTH_FEED_RENDERED_ANDROID";
        }
        switch (i) {
            case 1:
                return "FEED_DELIVERY_HEALTH_FETCH_ATTEMPT_ANDROID";
            case 2:
                return "FEED_DELIVERY_HEALTH_RESPONSE_FAILURE_ANDROID";
            case 3:
                return "FEED_DELIVERY_HEALTH_RESPONSE_EMPTY_ANDROID";
            case 4:
                return "FEED_DELIVERY_HEALTH_RECEIVED_EDGES_ANDROID";
            case 5:
                return "FEED_DELIVERY_HEALTH_DB_EMPTY_ANDROID";
            case 6:
                return "FEED_DELIVERY_HEALTH_DB_CLEAR_ANDROID";
            case 7:
                return "FEED_DELIVERY_HEALTH_FETCH_ATTEMPT_FBLITE";
            case 8:
                return "FEED_DELIVERY_HEALTH_RESPONSE_FAILURE_FBLITE";
            default:
                switch (i) {
                    case 20:
                        return "FEED_DELIVERY_HEALTH_FBLITE_FEED_HELATH";
                    case 21:
                        return "FEED_DELIVERY_HEALTH_FEED_REQUEST_FB4A";
                    case 22:
                        return "FEED_DELIVERY_HEALTH_FEED_RESPONSE_FB4A";
                    default:
                        switch (i) {
                            case 24:
                                return "FEED_DELIVERY_HEALTH_TIGON_REQUEST_FB4A";
                            case 25:
                                return "FEED_DELIVERY_HEALTH_NETWORK_CALLBACK_TIMEOUT_FB4A";
                            case 26:
                                return "FEED_DELIVERY_HEALTH_FEED_RENDERED_FB4A";
                            case 27:
                                return "FEED_DELIVERY_HEALTH_TIGON_RESPONSE_FB4A";
                            case 28:
                                return "FEED_DELIVERY_HEALTH_NETWORK_CALLBACK_TIMEOUT_FBLITE";
                            case 29:
                                return "FEED_DELIVERY_HEALTH_FEED_RENDERED_FBLITE";
                            case 30:
                                return "FEED_DELIVERY_HEALTH_FEED_FORMATTED_FBLITE";
                            case 31:
                                return "FEED_DELIVERY_HEALTH_RETRIEVE_NEXT_STORY";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
