package com.facebook.quicklog.identifiers;

public class PmaPerf {
    public static final int APP_ONCREATE_TO_LOGIN_ONCREATE = 9961489;
    public static final int COLD_START = 9961486;
    public static final int COLD_START_CREATE_DELEGATE = 9961478;
    public static final int COLD_START_FBAPPIMPL_ON_CREATE = 9961485;
    public static final int COLD_START_FIRST_RUN = 9961487;
    public static final int COMMSHUB_CONVERSATION_LOAD = 9961479;
    public static final int COMMSHUB_CONVERSATION_LOAD_SYNC = 9961511;
    public static final int COMMSHUB_MESSAGE_LIST_LOAD_FIRST_PAGE = 9961480;
    public static final int CONTAINER_FRAGMENT_ONCREATE_TO_CONTEXT_ITEMS_RENDERED = 9961497;
    public static final int CONTAINER_FRAGMENT_ONCREATE_TO_PAGE_ONRESUME = 9961496;
    public static final int CONTAINER_FRAGMENT_ONCREATE_TO_PAGE_VIEW_CREATED = 9961495;
    public static final int COVER_PHOTO_DOWNLOADED = 9961500;
    public static final int FACEWEB_CREATE = 9961517;
    public static final int FETCH_ALL_PAGES_GRAPH_API_METHOD = 9961492;
    public static final int FETCH_PAGE_HEADER_DATA_FROM_CACHE = 9961499;
    public static final int FETCH_PAGE_HEADER_DATA_FROM_SERVER = 9961498;
    public static final int FETCH_URI_CONFIG_FQL_API_METHOD = 9961494;
    public static final int LOAD_ALL_PAGES_AND_EXTRAS = 9961493;
    public static final int LOGIN_ONCREATE_TO_CHROME_ACTIVITY_ONCREATE = 9961490;
    public static final int LOGIN_ONCREATE_TO_REFRESH_FRAGMENT_ONCREATE = 9961491;
    public static final short MODULE_ID = 152;
    public static final int OLDINBOX_CONVERSATION_LOAD = 9961510;
    public static final int OLDINBOX_MESSAGE_LIST_LOAD_NEXT_PAGE = 9961504;
    public static final int OLDINBOX_MESSAGE_LIST_UPDATE_INBOX = 9961507;
    public static final int OLDINBOX_MESSAGE_LIST_UPDATE_TAG = 9961506;
    public static final int PMA_POST_TAB_TTRC = 9961526;
    public static final int PROFILE_PHOTO_DOWNLOADED = 9961501;
    public static final int THREADLIST_TO_THREADVIEW = 9961513;
    public static final int UPLOAD_COVER_PHOTO = 9961508;
    public static final int UPLOAD_PROFILE_PHOTO = 9961509;
    public static final int WARM_START = 9961488;

    public static String getMarkerName(int i) {
        if (i == 6) {
            return "PMA_PERF_COLD_START_CREATE_DELEGATE";
        }
        if (i == 7) {
            return "PMA_PERF_COMMSHUB_CONVERSATION_LOAD";
        }
        if (i == 8) {
            return "PMA_PERF_COMMSHUB_MESSAGE_LIST_LOAD_FIRST_PAGE";
        }
        if (i == 32) {
            return "PMA_PERF_OLDINBOX_MESSAGE_LIST_LOAD_NEXT_PAGE";
        }
        if (i == 41) {
            return "PMA_PERF_THREADLIST_TO_THREADVIEW";
        }
        if (i == 45) {
            return "PMA_PERF_FACEWEB_CREATE";
        }
        if (i == 54) {
            return "PMA_PERF_PMA_POST_TAB_TTRC";
        }
        switch (i) {
            case 13:
                return "PMA_PERF_COLD_START_FBAPPIMPL_ON_CREATE";
            case 14:
                return "PMA_PERF_COLD_START";
            case 15:
                return "PMA_PERF_COLD_START_FIRST_RUN";
            case 16:
                return "PMA_PERF_WARM_START";
            case 17:
                return "PMA_PERF_APP_ONCREATE_TO_LOGIN_ONCREATE";
            case 18:
                return "PMA_PERF_LOGIN_ONCREATE_TO_CHROME_ACTIVITY_ONCREATE";
            case 19:
                return "PMA_PERF_LOGIN_ONCREATE_TO_REFRESH_FRAGMENT_ONCREATE";
            case 20:
                return "PMA_PERF_FETCH_ALL_PAGES_GRAPH_API_METHOD";
            case 21:
                return "PMA_PERF_LOAD_ALL_PAGES_AND_EXTRAS";
            case 22:
                return "PMA_PERF_FETCH_URI_CONFIG_FQL_API_METHOD";
            case 23:
                return "PMA_PERF_CONTAINER_FRAGMENT_ONCREATE_TO_PAGE_VIEW_CREATED";
            case 24:
                return "PMA_PERF_CONTAINER_FRAGMENT_ONCREATE_TO_PAGE_ONRESUME";
            case 25:
                return "PMA_PERF_CONTAINER_FRAGMENT_ONCREATE_TO_CONTEXT_ITEMS_RENDERED";
            case 26:
                return "PMA_PERF_FETCH_PAGE_HEADER_DATA_FROM_SERVER";
            case 27:
                return "PMA_PERF_FETCH_PAGE_HEADER_DATA_FROM_CACHE";
            case 28:
                return "PMA_PERF_COVER_PHOTO_DOWNLOADED";
            case 29:
                return "PMA_PERF_PROFILE_PHOTO_DOWNLOADED";
            default:
                switch (i) {
                    case 34:
                        return "PMA_PERF_OLDINBOX_MESSAGE_LIST_UPDATE_TAG";
                    case 35:
                        return "PMA_PERF_OLDINBOX_MESSAGE_LIST_UPDATE_INBOX";
                    case 36:
                        return "PMA_PERF_UPLOAD_COVER_PHOTO";
                    case 37:
                        return "PMA_PERF_UPLOAD_PROFILE_PHOTO";
                    case 38:
                        return "PMA_PERF_OLDINBOX_CONVERSATION_LOAD";
                    case 39:
                        return "PMA_PERF_COMMSHUB_CONVERSATION_LOAD_SYNC";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
