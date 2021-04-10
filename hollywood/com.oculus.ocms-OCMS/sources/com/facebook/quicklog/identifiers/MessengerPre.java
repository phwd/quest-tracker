package com.facebook.quicklog.identifiers;

public class MessengerPre {
    public static final int COMPOSE_MESSAGE = 60497832;
    public static final int EXTERNAL_INTENT = 60499497;
    public static final int MESSAGE_SEND_ENQUEUE = 60502903;
    public static final int MESSENGER_APP_USERFLOW = 60496927;
    public static final short MODULE_ID = 923;
    public static final int RTC_INCOMING_CALL_USER_FLOW = 60505791;
    public static final int RTC_OUTGOING_CALL_USER_FLOW = 60496938;
    public static final int SEARCHBAR_QUERY = 60502402;
    public static final int SEARCH_CONTACT = 60500083;
    public static final int SEARCH_MESSAGES = 60504098;
    public static final int SEARCH_TYPEAHEAD = 60491234;
    public static final int SEND_MESSAGE = 60490760;
    public static final int THREADLIST_TO_ADVANCED_CRYPTO_THREADVIEW = 60501237;
    public static final int THREADLIST_TO_CARRIER_MESSAGING_THREADVIEW = 60501749;
    public static final int THREADLIST_TO_THREADVIEW = 60491673;
    public static final int THREADLIST_TO_TINCAN_THREADVIEW = 60503818;
    public static final int TINCAN_COMPOSE_MESSAGE = 60496000;
    public static final int TINCAN_MESSAGE_SEND_ENQUEUE = 60493677;
    public static final int VIEW_STORIES = 60500227;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1032:
                return "MESSENGER_SEND_MESSAGE";
            case 1506:
                return "MESSENGER_PRE_SEARCH_TYPEAHEAD";
            case 1945:
                return "MESSENGER_PRE_THREADLIST_TO_THREADVIEW";
            case 3949:
                return "MESSENGER_PRE_TINCAN_MESSAGE_SEND_ENQUEUE";
            case 6272:
                return "MESSENGER_PRE_TINCAN_COMPOSE_MESSAGE";
            case 7199:
                return "MESSENGER_PRE_MESSENGER_APP_USERFLOW";
            case 7210:
                return "MESSENGER_PRE_RTC_OUTGOING_CALL_USER_FLOW";
            case 8104:
                return "MESSENGER_PRE_COMPOSE_MESSAGE";
            case 9769:
                return "MESSENGER_PRE_EXTERNAL_INTENT";
            case 10355:
                return "MESSENGER_PRE_SEARCH_CONTACT";
            case 10499:
                return "MESSENGER_PRE_VIEW_STORIES";
            case 11509:
                return "MESSENGER_PRE_THREADLIST_TO_ADVANCED_CRYPTO_THREADVIEW";
            case 12021:
                return "MESSENGER_PRE_THREADLIST_TO_CARRIER_MESSAGING_THREADVIEW";
            case 12674:
                return "MESSENGER_PRE_SEARCHBAR_QUERY";
            case 13175:
                return "MESSENGER_PRE_MESSAGE_SEND_ENQUEUE";
            case 14090:
                return "MESSENGER_PRE_THREADLIST_TO_TINCAN_THREADVIEW";
            case 14370:
                return "MESSENGER_PRE_SEARCH_MESSAGES";
            case 16063:
                return "MESSENGER_PRE_RTC_INCOMING_CALL_USER_FLOW";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
