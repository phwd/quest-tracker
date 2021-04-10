package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class Events {
    public static final int DELETE_EVENT = 393283;
    public static final int EVENTS_ANDROID_COMPOSER_CREATION_TEST = 406001;
    public static final int EVENTS_BOOKMARK_TAIL_LOAD_ANDROID = 404703;
    public static final int EVENTS_BOOKMARK_TTRC = 416215;
    public static final int EVENTS_CAMPAIGN_LANDING_TTI_ANDROID = 393255;
    public static final int EVENTS_COMPOSER_LAUNCH_TTI_MARKER = 393233;
    public static final int EVENTS_DASHBOARD_FEED_TTRC_ANDROID = 393267;
    public static final int EVENTS_DASHBOARD_LOAD_FIRST_PAGE_OF_FEED_UNITS = 393253;
    public static final int EVENTS_DASHBOARD_LOAD_TTRC = 393262;
    public static final int EVENTS_DASHBOARD_TTI_MARKER = 393234;
    public static final int EVENT_CREATE_TTI = 393232;
    public static final int EVENT_CREATION_FLOW_ANDROID = 396856;
    public static final int EVENT_CREATION_MULTISTEP_FLOW_FB4A = 402504;
    public static final int EVENT_CREATION_NT_FBLITE = 396152;
    public static final int EVENT_GUESTLIST_TTRC = 393282;
    public static final int EVENT_PERMALINK_TTI_MARKER = 393237;
    public static final int EVENT_PERMALINK_TTRC = 393261;
    public static final int EVENT_TICKET_CHECKOUT_TTRC = 393274;
    public static final int EVENT_TICKET_PURCHASE_TTRC = 393275;
    public static final int EVENT_TICKET_RESERVATION_TTRC = 393273;
    public static final int EVENT_TICKET_SEATMAP_TTRC = 393277;
    public static final int EVENT_TICKET_SELECTION_TTRC = 393272;
    public static final int LOCAL_APPMARK_EARLY_MAP_TTRC = 393280;
    public static final int LOCAL_APPMARK_ENTITY_PREVIEW_TTRC = 393278;
    public static final int LOCAL_APPMARK_FEED_TTRC = 393268;
    public static final int LOCAL_APPMARK_GUIDE_TTRC = 393270;
    public static final int LOCAL_APPMARK_MAP_TTRC = 393269;
    public static final int LOCAL_APPMARK_SERACH_TTRC = 393271;
    public static final int LOCAL_APPMARK_TYPEAHEAD_TTRC = 393279;
    public static final short MODULE_ID = 6;
    public static final int NOTIFICATION_SETTINGS_ANDROID_TTRC = 393266;

    public static String getMarkerName(int i) {
        if (i == 21) {
            return "EVENTS_EVENT_PERMALINK_TTI_MARKER";
        }
        if (i == 37) {
            return "EVENTS_EVENTS_DASHBOARD_LOAD_FIRST_PAGE_OF_FEED_UNITS";
        }
        if (i == 39) {
            return "EVENTS_CAMPAIGN_LANDING_TTI_ANDROID_MARKER";
        }
        if (i == 2936) {
            return "EVENTS_EVENT_CREATION_NT_FBLITE";
        }
        if (i == 3640) {
            return "EVENTS_EVENT_CREATION_FLOW_ANDROID";
        }
        if (i == 9288) {
            return "EVENTS_EVENT_CREATION_MULTISTEP_FLOW_FB4A";
        }
        if (i == 11487) {
            return "EVENTS_EVENTS_BOOKMARK_TAIL_LOAD_ANDROID";
        }
        if (i == 12785) {
            return "EVENTS_EVENTS_ANDROID_COMPOSER_CREATION_TEST";
        }
        if (i == 22999) {
            return "EVENTS_EVENTS_BOOKMARK_TTRC";
        }
        if (i == 45) {
            return "EVENTS_EVENT_PERMALINK_TTRC";
        }
        if (i == 46) {
            return "EVENTS_EVENTS_DASHBOARD_LOAD_TTRC";
        }
        if (i == 66) {
            return "EVENTS_EVENT_GUESTLIST_TTRC";
        }
        if (i == 67) {
            return "EVENTS_DELETE_EVENT";
        }
        switch (i) {
            case 16:
                return "EVENTS_EVENT_CREATE_TTI";
            case 17:
                return "EVENTS_EVENTS_COMPOSER_LAUNCH_TTI_MARKER";
            case 18:
                return "EVENTS_EVENTS_DASHBOARD_TTI_MARKER";
            default:
                switch (i) {
                    case 50:
                        return "Android Events Notification Settings TTRC";
                    case 51:
                        return "EVENTS_EVENTS_DASHBOARD_FEED_TTRC_ANDROID";
                    case 52:
                        return "EVENTS_LOCAL_APPMARK_FEED_TTRC";
                    case 53:
                        return "EVENTS_LOCAL_APPMARK_MAP_TTRC";
                    case 54:
                        return "EVENTS_LOCAL_APPMARK_GUIDE_TTRC";
                    case 55:
                        return "EVENTS_LOCAL_APPMARK_SERACH_TTRC";
                    case 56:
                        return "EVENTS_EVENT_TICKET_SELECTION_TTRC";
                    case 57:
                        return "EVENTS_EVENT_TICKET_RESERVATION_TTRC";
                    case 58:
                        return "EVENTS_EVENT_TICKET_CHECKOUT_TTRC";
                    case 59:
                        return "EVENTS_EVENT_TICKET_PURCHASE_TTRC";
                    default:
                        switch (i) {
                            case UL.id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                                return "EVENTS_EVENT_TICKET_SEATMAP_TTRC";
                            case 62:
                                return "EVENTS_LOCAL_APPMARK_ENTITY_PREVIEW_TTRC";
                            case 63:
                                return "EVENTS_LOCAL_APPMARK_TYPEAHEAD_TTRC";
                            case 64:
                                return "EVENTS_LOCAL_APPMARK_EARLY_MAP_TTRC";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
