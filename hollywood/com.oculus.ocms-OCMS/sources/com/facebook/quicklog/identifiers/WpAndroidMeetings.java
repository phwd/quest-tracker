package com.facebook.quicklog.identifiers;

import androidx.core.view.PointerIconCompat;

public class WpAndroidMeetings {
    public static final int CANCEL_MEETING = 582422609;
    public static final int CHECK_UNCHECK_ACTION_ITEM = 582434589;
    public static final int CREATE_ACTION_ITEM = 582430343;
    public static final int CREATE_GARDEN = 582434417;
    public static final int CREATE_MEETING = 582430663;
    public static final int CREATE_MEET_NOW_ROOM = 582419441;
    public static final int DELETE_ACTION_ITEM = 582422329;
    public static final int EDIT_ACTION_ITEM = 582427635;
    public static final int EDIT_MEETING = 582430390;
    public static final int JOIN_MEETING = 582422610;
    public static final int LOAD_ACTION_ITEMS = 582424541;
    public static final int LOAD_GARDENS_TAB = 582421627;
    public static final int LOAD_GARDEN_DETAILS = 582419659;
    public static final int LOAD_MEETINGS_TAB = 582429403;
    public static final int LOAD_MEETING_DETAILS = 582430306;
    public static final int MEETING_RSVP = 582430754;
    public static final short MODULE_ID = 8887;

    public static String getMarkerName(int i) {
        switch (i) {
            case PointerIconCompat.TYPE_VERTICAL_TEXT:
                return "WP_ANDROID_MEETINGS_CREATE_MEET_NOW_ROOM";
            case 1227:
                return "WP_ANDROID_MEETINGS_LOAD_GARDEN_DETAILS";
            case 3195:
                return "WP_ANDROID_MEETINGS_LOAD_GARDENS_TAB";
            case 3897:
                return "WP_ANDROID_MEETINGS_DELETE_ACTION_ITEM";
            case 4177:
                return "WP_ANDROID_MEETINGS_CANCEL_MEETING";
            case 4178:
                return "WP_ANDROID_MEETINGS_JOIN_MEETING";
            case 6109:
                return "WP_ANDROID_MEETINGS_LOAD_ACTION_ITEMS";
            case 9203:
                return "WP_ANDROID_MEETINGS_EDIT_ACTION_ITEM";
            case 10971:
                return "WP_ANDROID_MEETINGS_LOAD_MEETINGS_TAB";
            case 11874:
                return "WP_ANDROID_MEETINGS_LOAD_MEETING_DETAILS";
            case 11911:
                return "WP_ANDROID_MEETINGS_CREATE_ACTION_ITEM";
            case 11958:
                return "WP_ANDROID_MEETINGS_EDIT_MEETING";
            case 12231:
                return "WP_ANDROID_MEETINGS_CREATE_MEETING";
            case 12322:
                return "WP_ANDROID_MEETINGS_MEETING_RSVP";
            case 15985:
                return "WP_ANDROID_MEETINGS_CREATE_GARDEN";
            case 16157:
                return "WP_ANDROID_MEETINGS_CHECK_UNCHECK_ACTION_ITEM";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
