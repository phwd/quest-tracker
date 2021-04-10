package com.facebook.quicklog.identifiers;

public class StellaApp {
    public static final int ALL_THUMBNAIL_LIST_QUERY_RESPONSE = 55121118;
    public static final int ASSISTANT_INTERACTION = 55115794;
    public static final int BASIC_MEDIA_EDITOR_CROP_TOOL_TTRC = 55128400;
    public static final int BASIC_MEDIA_EDITOR_LANDING_TTRC = 55129674;
    public static final int BASIC_MEDIA_EDITOR_LIGHTING_TOOL_TTRC = 55128563;
    public static final int BASIC_MEDIA_EDITOR_REVERT_EDIT_TTRC = 55128397;
    public static final int BASIC_MEDIA_EDITOR_SAVE_EDIT_TTRC = 55126405;
    public static final int BASIC_MEDIA_EDITOR_TRIM_TOOL_TTRC = 55125237;
    public static final int BLE_PAIRING = 55115789;
    public static final int BLUELINK_FETCH_ASSET = 55126141;
    public static final int BLUELINK_READY = 55115790;
    public static final int BLUELINK_SERVICE_REQUEST = 55118725;
    public static final int BLUELINK_SERVICE_REQUEST_RESPONSE = 55125837;
    public static final int BLUELINK_TOPIC_BUFFER = 55130747;
    public static final int BLUELINK_TOPIC_MESSAGE = 55125532;
    public static final int BT_CONNECTION = 55129933;
    public static final int BT_CONNECTION_SESSION = 55121766;
    public static final int CAPTURED_MEDIA_SYNC_USER_FLOW = 55117589;
    public static final int CAPTURE_NOTIF_TO_THUMBNAIL_UI = 55115793;
    public static final int COMPILATION_CLIP_VIEW_TTI = 55129429;
    public static final int COMPONENT_TTI = 55115778;
    public static final int DEVICE_HTTP_REQUEST = 55120346;
    public static final int DEVICE_TELEMETRY_LIST = 55126196;
    public static final int DEVICE_TELEMETRY_PROCESS_BATCH = 55131658;
    public static final int EDITED_MEDIA_SYNC_USER_FLOW = 55121218;
    public static final int FAVORITE_THUMBNAIL_LIST_QUERY_RESPONSE = 55124369;
    public static final int FETCH_MEDIA_CALIBRATION = 55127058;
    public static final int FETCH_RAW_MEDIA = 55115781;
    public static final int FULL_SCREEN_MEDIA_QUERY_RESPONSE = 55115787;
    public static final int FULL_SCREEN_MEDIA_TTI = 55115788;
    public static final int GRAPHQL_FETCH = 55121076;
    public static final int GRAPHQL_QUERY_RESPONSE = 55128069;
    public static final int HOME_TO_MEDIA_GALLERY_TTRC = 55115785;
    public static final int LOCATION_SERVICES_QUERY_RESPONSE = 55129694;
    public static final int MEDIA_GALLERY_SCROLL_PERF = 55128643;
    public static final int MEDIA_GALLERY_TTRC = 55117702;
    public static final int MEDIA_IMPORTING_USER_WAIT = 55131679;
    public static final int MEDIA_TRANSFER_INPUT = 55115777;
    public static final short MODULE_ID = 841;
    public static final int OOBE_INTERACTION = 55122685;
    public static final int PAIRING_CONNECTION = 55127098;
    public static final int PLACE_THUMBNAIL_LIST_QUERY_RESPONSE = 55117724;
    public static final int PLUGIN_LATENCY = 55115791;
    public static final int PROCESS_MEDIA_CAPTURE = 55115780;
    public static final int REMEDY_DIALOG_SHOWN = 55122347;
    public static final int REQUEST_FETCH_CHANNEL = 55121666;
    public static final int SCROLL_PERF = 55127333;
    public static final int SHOW_FULL_SCREEN_MEDIA_TTRC = 55130546;
    public static final int SHOW_FULL_SCREEN_PHOTO_TTRC = 55128485;
    public static final int SHOW_FULL_SCREEN_VIDEO_TTRC = 55122036;
    public static final int SHOW_MEDIA_EDIT_UI_TTI = 55115795;
    public static final int STELLA_FIRMWARE_UPDATE_FUNNEL = 55116919;
    public static final int STELLA_LOGIN_FUNNEL = 55130310;
    public static final int STELLA_OOBE_FUNNEL = 55129714;
    public static final int STELLA_PAIRING_FUNNEL = 55124276;
    public static final int THUMBNAIL_LIST_QUERY_RESPONSE = 55115786;
    public static final int USER_PAIRING = 55124358;
    public static final int WIFI_CONNECTION_SETUP = 55117748;
    public static final int WIFI_FETCH_ASSET = 55118521;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "STELLA_APP_MEDIA_TRANSFER_INPUT";
        }
        if (i == 2) {
            return "STELLA_APP_COMPONENT_TTI";
        }
        if (i == 4) {
            return "STELLA_APP_PROCESS_MEDIA_CAPTURE";
        }
        if (i == 5) {
            return "STELLA_APP_FETCH_RAW_MEDIA";
        }
        switch (i) {
            case 9:
                return "STELLA_APP_HOME_TO_MEDIA_GALLERY_TTRC";
            case 10:
                return "STELLA_APP_THUMBNAIL_LIST_QUERY_RESPONSE";
            case 11:
                return "STELLA_APP_FULL_SCREEN_MEDIA_QUERY_RESPONSE";
            case 12:
                return "STELLA_APP_FULL_SCREEN_MEDIA_TTI";
            case 13:
                return "STELLA_APP_BLE_PAIRING";
            case 14:
                return "STELLA_APP_BLUELINK_READY";
            case 15:
                return "STELLA_APP_PLUGIN_LATENCY";
            default:
                switch (i) {
                    case 17:
                        return "STELLA_APP_CAPTURE_NOTIF_TO_THUMBNAIL_UI";
                    case 18:
                        return "STELLA_APP_ASSISTANT_INTERACTION";
                    case 19:
                        return "STELLA_APP_SHOW_MEDIA_EDIT_UI_TTI";
                    default:
                        switch (i) {
                            case 1143:
                                return "STELLA_APP_STELLA_FIRMWARE_UPDATE_FUNNEL";
                            case 1813:
                                return "STELLA_APP_CAPTURED_MEDIA_SYNC_USER_FLOW";
                            case 1926:
                                return "STELLA_APP_MEDIA_GALLERY_TTRC";
                            case 1948:
                                return "STELLA_APP_PLACE_THUMBNAIL_LIST_QUERY_RESPONSE";
                            case 1972:
                                return "STELLA_APP_WIFI_CONNECTION_SETUP";
                            case 2745:
                                return "STELLA_APP_WIFI_FETCH_ASSET";
                            case 2949:
                                return "STELLA_APP_BLUELINK_SERVICE_REQUEST";
                            case 4570:
                                return "STELLA_APP_DEVICE_HTTP_REQUEST";
                            case 5300:
                                return "STELLA_APP_GRAPHQL_FETCH";
                            case 5342:
                                return "STELLA_APP_ALL_THUMBNAIL_LIST_QUERY_RESPONSE";
                            case 5442:
                                return "STELLA_APP_EDITED_MEDIA_SYNC_USER_FLOW";
                            case 5890:
                                return "STELLA_APP_REQUEST_FETCH_CHANNEL";
                            case 5990:
                                return "STELLA_APP_BT_CONNECTION_SESSION";
                            case 6260:
                                return "STELLA_APP_SHOW_FULL_SCREEN_VIDEO_TTRC";
                            case 6571:
                                return "STELLA_APP_REMEDY_DIALOG_SHOWN";
                            case 6909:
                                return "STELLA_APP_OOBE_INTERACTION";
                            case 8500:
                                return "STELLA_APP_STELLA_PAIRING_FUNNEL";
                            case 8582:
                                return "STELLA_APP_USER_PAIRING";
                            case 8593:
                                return "STELLA_APP_FAVORITE_THUMBNAIL_LIST_QUERY_RESPONSE";
                            case 9461:
                                return "STELLA_APP_BASIC_MEDIA_EDITOR_TRIM_TOOL_TTRC";
                            case 9756:
                                return "STELLA_APP_BLUELINK_TOPIC_MESSAGE";
                            case 10061:
                                return "STELLA_APP_BLUELINK_SERVICE_REQUEST_RESPONSE";
                            case 10365:
                                return "STELLA_APP_BLUELINK_FETCH_ASSET";
                            case 10420:
                                return "STELLA_APP_DEVICE_TELEMETRY_LIST";
                            case 10629:
                                return "STELLA_APP_BASIC_MEDIA_EDITOR_SAVE_EDIT_TTRC";
                            case 11282:
                                return "STELLA_APP_FETCH_MEDIA_CALIBRATION";
                            case 11322:
                                return "STELLA_APP_PAIRING_CONNECTION";
                            case 11557:
                                return "STELLA_APP_SCROLL_PERF";
                            case 12293:
                                return "STELLA_APP_GRAPHQL_QUERY_RESPONSE";
                            case 12621:
                                return "STELLA_APP_BASIC_MEDIA_EDITOR_REVERT_EDIT_TTRC";
                            case 12624:
                                return "STELLA_APP_BASIC_MEDIA_EDITOR_CROP_TOOL_TTRC";
                            case 12709:
                                return "STELLA_APP_SHOW_FULL_SCREEN_PHOTO_TTRC";
                            case 12787:
                                return "STELLA_APP_BASIC_MEDIA_EDITOR_LIGHTING_TOOL_TTRC";
                            case 12867:
                                return "STELLA_APP_MEDIA_GALLERY_SCROLL_PERF";
                            case 13653:
                                return "STELLA_APP_COMPILATION_CLIP_VIEW_TTI";
                            case 13898:
                                return "STELLA_APP_BASIC_MEDIA_EDITOR_LANDING_TTRC";
                            case 13918:
                                return "STELLA_APP_LOCATION_SERVICES_QUERY_RESPONSE";
                            case 13938:
                                return "STELLA_APP_STELLA_OOBE_FUNNEL";
                            case 14157:
                                return "STELLA_APP_BT_CONNECTION";
                            case 14534:
                                return "STELLA_APP_STELLA_LOGIN_FUNNEL";
                            case 14770:
                                return "STELLA_APP_SHOW_FULL_SCREEN_MEDIA_TTRC";
                            case 14971:
                                return "STELLA_APP_BLUELINK_TOPIC_BUFFER";
                            case 15882:
                                return "STELLA_APP_DEVICE_TELEMETRY_PROCESS_BATCH";
                            case 15903:
                                return "STELLA_APP_MEDIA_IMPORTING_USER_WAIT";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
