package com.facebook.quicklog.identifiers;

public class Interaction {
    public static final int ACTIVITY_ON_CREATE = 4980742;
    public static final int ACTIVITY_ON_PAUSE = 4980745;
    public static final int ACTIVITY_ON_RESUME = 4980744;
    public static final int ACTIVITY_ON_START = 4980743;
    public static final int ACTIVITY_ON_STOP = 4980749;
    public static final int ADD_FRAGMENT = 4980748;
    public static final int ANIMATION = 4980746;
    public static final int INTENT_MAPPING = 4980739;
    public static final int LOAD_EVENTS_DASHBOARD = 4980762;
    public static final int LOAD_EVENT_PERMALINK = 4980752;
    public static final int LOAD_GROUPS_FEED = 4980753;
    public static final int LOAD_PAGE_HEADER = 4980751;
    public static final int LOAD_PAGE_HEADER_ADMIN = 4980754;
    public static final int LOAD_PERMALINK = 4980755;
    public static final int LOAD_TIMELINE_HEADER = 4980750;
    public static final int LOAD_WEB_VIEW = 4980760;
    public static final short MODULE_ID = 76;
    public static final int NEW_FRAGMENT = 4980747;
    public static final int OPEN_CHECK_IN = 4980759;
    public static final int OPEN_COMPOSER = 4980756;
    public static final int OPEN_MEDIA_PICKER = 4980757;
    public static final int OPEN_PHOTOS_FEED = 4980763;
    public static final int OPEN_PHOTO_GALLERY = 4980758;
    public static final int SEARCH_TYPEAHEAD = 4980761;
    public static final int START_ACTIVITY = 4980740;
    public static final int START_ACTIVITY_FOR_RESULT = 4980741;
    public static final int TIME_TO_ACTIVITY_ON_CREATE = 4980765;
    public static final int TIME_TO_ACTIVITY_ON_PAUSE = 4980764;
    public static final int TIME_TO_FRAGMENT_ON_CREATE = 4980766;
    public static final int TOUCH = 4980738;
    public static final int TTI = 4980737;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "INTERACTION_TTI";
            case 2:
                return "INTERACTION_TOUCH";
            case 3:
                return "INTERACTION_INTENT_MAPPING";
            case 4:
                return "INTERACTION_START_ACTIVITY";
            case 5:
                return "INTERACTION_START_ACTIVITY_FOR_RESULT";
            case 6:
                return "INTERACTION_ACTIVITY_ON_CREATE";
            case 7:
                return "INTERACTION_ACTIVITY_ON_START";
            case 8:
                return "INTERACTION_ACTIVITY_ON_RESUME";
            case 9:
                return "INTERACTION_ACTIVITY_ON_PAUSE";
            case 10:
                return "INTERACTION_ANIMATION";
            case 11:
                return "INTERACTION_NEW_FRAGMENT";
            case 12:
                return "INTERACTION_ADD_FRAGMENT";
            case 13:
                return "INTERACTION_ACTIVITY_ON_STOP";
            case 14:
                return "INTERACTION_LOAD_TIMELINE_HEADER";
            case 15:
                return "INTERACTION_LOAD_PAGE_HEADER";
            case 16:
                return "INTERACTION_LOAD_EVENT_PERMALINK";
            case 17:
                return "INTERACTION_LOAD_GROUPS_FEED";
            case 18:
                return "INTERACTION_LOAD_PAGE_HEADER_ADMIN";
            case 19:
                return "INTERACTION_LOAD_PERMALINK";
            case 20:
                return "INTERACTION_OPEN_COMPOSER";
            case 21:
                return "INTERACTION_OPEN_MEDIA_PICKER";
            case 22:
                return "INTERACTION_OPEN_PHOTO_GALLERY";
            case 23:
                return "INTERACTION_OPEN_CHECK_IN";
            case 24:
                return "INTERACTION_LOAD_WEB_VIEW";
            case 25:
                return "INTERACTION_SEARCH_TYPEAHEAD";
            case 26:
                return "INTERACTION_LOAD_EVENTS_DASHBOARD";
            case 27:
                return "INTERACTION_OPEN_PHOTOS_FEED";
            case 28:
                return "INTERACTION_TIME_TO_ACTIVITY_ON_PAUSE";
            case 29:
                return "INTERACTION_TIME_TO_ACTIVITY_ON_CREATE";
            case 30:
                return "INTERACTION_TIME_TO_FRAGMENT_ON_CREATE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
