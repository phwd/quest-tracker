package com.facebook.quicklog.identifiers;

public class MessengerLite {
    public static final int ANY_THREAD_LIST = 15269917;
    public static final int ANY_THREAD_VIEW = 15269921;
    public static final int API_EXECUTE = 15269906;
    public static final int CAMERA_TIME_BACK_FROM_PREVIEW = 15269926;
    public static final int CAMERA_TIME_TO_CAPTURE_PHOTO = 15269927;
    public static final int CAMERA_TIME_TO_INTERACT = 15269925;
    public static final int CAMERA_TIME_TO_START_VIDEO_CAPTURE = 15269928;
    public static final int CAMERA_TIME_TO_STOP_VIDEO_CAPTURE = 15269929;
    public static final int COLD_START_TO_THREAD_VIEW = 15269916;
    public static final int COLD_START_TO_UI = 15269915;
    public static final int COMPONENT_TTI = 15269890;
    public static final int DB_OPERATION = 15269910;
    public static final int FAST_LUKEWARM_START_TO_THREAD_LIST = 15269918;
    public static final int LOGIN = 15269909;
    public static final int MEDIA_DOWNLOAD = 15269895;
    public static final int MEDIA_PICKER_LOAD_TIME = 15269930;
    public static final int MEDIA_PICKER_SCROLL_PERF = 15269932;
    public static final int MEDIA_UPLOAD_ANDROID = 15269897;
    public static final int MESSENGER_LITE_VIDEO_TRANSCODE_TIME = 15269903;
    public static final int MLITE_RESOURCES_WAIT_TIME = 15269902;
    public static final int MLITE_STORY_VIEWER_INITIAL_LOAD = 15269934;
    public static final int MLITE_TIME_TO_LOAD_THREAD_LIST = 15269900;
    public static final short MODULE_ID = 233;
    public static final int NETWORK_REQUEST = 15269894;
    public static final int RTC_INCOMING_CALL_ANSWERED = 15269908;
    public static final int SEND_MESSAGE = 15269923;
    public static final int SLOW_LUKEWARM_START_TO_THREAD_LIST = 15269919;
    public static final int SQLITE_TIME_PROFILE_IN_JOURNAL_MODE = 15269914;
    public static final int SQLITE_TIME_PROFILE_IN_WAL_MODE = 15269913;
    public static final int SQLITE_TIME_TO_CREATE_DB = 15269907;
    public static final int SQLITE_TIME_TO_MIGRATE_DATA = 15269901;
    public static final int SQLITE_TIME_TO_UPGRADE_DB = 15269893;
    public static final int SQLITE_TIME_WAL_CHECKPOINT = 15269912;
    public static final int STORY_VIEWER_TRANSITION = 15269922;
    public static final int THREAD_LIST_TO_THREAD_VIEW = 15269920;
    public static final int UI_COLD_START_TTI = 15269911;

    public static String getMarkerName(int i) {
        switch (i) {
            case 2:
                return "MESSENGER_LITE_COMPONENT_TTI";
            case 3:
            case 4:
            case 8:
            case 10:
            case 11:
            case 16:
            case 17:
            case 36:
            case 43:
            case 45:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 5:
                return "MESSENGER_LITE_SQLITE_TIME_TO_UPGRADE_DB";
            case 6:
                return "MESSENGER_LITE_NETWORK_REQUEST";
            case 7:
                return "MESSENGER_LITE_MEDIA_DOWNLOAD";
            case 9:
                return "MESSENGER_LITE_MEDIA_UPLOAD_ANDROID";
            case 12:
                return "MESSENGER_LITE_MLITE_TIME_TO_LOAD_THREAD_LIST";
            case 13:
                return "MESSENGER_LITE_SQLITE_TIME_TO_MIGRATE_DATA";
            case 14:
                return "MESSENGER_LITE_MLITE_RESOURCES_WAIT_TIME";
            case 15:
                return "MESSENGER_LITE_MESSENGER_LITE_VIDEO_TRANSCODE_TIME";
            case 18:
                return "MESSENGER_LITE_API_EXECUTE";
            case 19:
                return "MESSENGER_LITE_SQLITE_TIME_TO_CREATE_DB";
            case 20:
                return "MESSENGER_LITE_RTC_INCOMING_CALL_ANSWERED";
            case 21:
                return "MESSENGER_LITE_LOGIN";
            case 22:
                return "MESSENGER_LITE_DB_OPERATION";
            case 23:
                return "MESSENGER_LITE_UI_COLD_START_TTI";
            case 24:
                return "MESSENGER_LITE_SQLITE_TIME_WAL_CHECKPOINT";
            case 25:
                return "MESSENGER_LITE_SQLITE_TIME_PROFILE_IN_WAL_MODE";
            case 26:
                return "MESSENGER_LITE_SQLITE_TIME_PROFILE_IN_JOURNAL_MODE";
            case 27:
                return "MESSENGER_LITE_COLD_START_TO_UI";
            case 28:
                return "MESSENGER_LITE_COLD_START_TO_THREAD_VIEW";
            case 29:
                return "MESSENGER_LITE_ANY_THREAD_LIST";
            case 30:
                return "MESSENGER_LITE_FAST_LUKEWARM_START_TO_THREAD_LIST";
            case 31:
                return "MESSENGER_LITE_SLOW_LUKEWARM_START_TO_THREAD_LIST";
            case 32:
                return "MESSENGER_LITE_THREAD_LIST_TO_THREAD_VIEW";
            case 33:
                return "MESSENGER_LITE_ANY_THREAD_VIEW";
            case 34:
                return "MESSENGER_LITE_STORY_VIEWER_TRANSITION";
            case 35:
                return "MESSENGER_LITE_SEND_MESSAGE";
            case 37:
                return "MESSENGER_LITE_CAMERA_TIME_TO_INTERACT";
            case 38:
                return "MESSENGER_LITE_CAMERA_TIME_BACK_FROM_PREVIEW";
            case 39:
                return "MESSENGER_LITE_CAMERA_TIME_TO_CAPTURE_PHOTO";
            case 40:
                return "MESSENGER_LITE_CAMERA_TIME_TO_START_VIDEO_CAPTURE";
            case 41:
                return "MESSENGER_LITE_CAMERA_TIME_TO_STOP_VIDEO_CAPTURE";
            case 42:
                return "MESSENGER_LITE_MEDIA_PICKER_LOAD_TIME";
            case 44:
                return "MESSENGER_LITE_MEDIA_PICKER_SCROLL_PERF";
            case 46:
                return "MESSENGER_LITE_MLITE_STORY_VIEWER_INITIAL_LOAD";
        }
    }
}
