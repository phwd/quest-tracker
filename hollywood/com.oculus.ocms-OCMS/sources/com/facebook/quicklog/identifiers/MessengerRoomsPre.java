package com.facebook.quicklog.identifiers;

public class MessengerRoomsPre {
    public static final int LOAD_ROOM_CHAT_THREAD = 59712361;
    public static final int MESENGER_ROOMS_CALL_EVENT_SUMMARY = 59703302;
    public static final int MESSENGER_ROOMS_ACTIVE_DRAWER_STATE_CHANGE = 59703303;
    public static final int MESSENGER_ROOMS_CALL_AUDIO_OUTPUT_CHANGED = 59703305;
    public static final int MESSENGER_ROOMS_CALL_MEDIA_STATE_CHANGED = 59703306;
    public static final int MESSENGER_ROOMS_CAMERA_SHARED_STATE_CHANGED = 59703304;
    public static final int MESSENGER_ROOMS_DOMINANT_SPEAKER_CHANGE = 59703298;
    public static final int MESSENGER_ROOMS_FRAME_DROPS = 59703297;
    public static final int MESSENGER_ROOMS_MEDIA_STATUS_UPDATE = 59703300;
    public static final int MESSENGER_ROOMS_PARTICIPANT_DATA_UPDATED = 59703301;
    public static final int MESSENGER_ROOMS_SUBSCRIBE_TO_VIDEO_STREAM = 59703299;
    public static final int MESSENGER_ROOMS_TO_LIVE_GUEST_ACCEPT_OPT_IN_EVENT = 59714611;
    public static final int MESSENGER_ROOMS_TO_LIVE_HOST_END_BROADCAST_EVENT = 59708352;
    public static final int MESSENGER_ROOMS_TO_LIVE_HOST_EVENT = 59707154;
    public static final short MODULE_ID = 911;
    public static final int ROOMS_IN_CALL = 59710360;

    public static String getMarkerName(int i) {
        if (i == 3858) {
            return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_TO_LIVE_HOST_EVENT";
        }
        if (i == 5056) {
            return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_TO_LIVE_HOST_END_BROADCAST_EVENT";
        }
        if (i == 7064) {
            return "MESSENGER_ROOMS_PRE_ROOMS_IN_CALL";
        }
        if (i == 9065) {
            return "MESSENGER_ROOMS_PRE_LOAD_ROOM_CHAT_THREAD";
        }
        if (i == 11315) {
            return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_TO_LIVE_GUEST_ACCEPT_OPT_IN_EVENT";
        }
        switch (i) {
            case 1:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_FRAME_DROPS";
            case 2:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_DOMINANT_SPEAKER_CHANGE";
            case 3:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_SUBSCRIBE_TO_VIDEO_STREAM";
            case 4:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_MEDIA_STATUS_UPDATE";
            case 5:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_PARTICIPANT_DATA_UPDATED";
            case 6:
                return "MESSENGER_ROOMS_PRE_MESENGER_ROOMS_CALL_EVENT_SUMMARY";
            case 7:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_ACTIVE_DRAWER_STATE_CHANGE";
            case 8:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_CAMERA_SHARED_STATE_CHANGED";
            case 9:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_CALL_AUDIO_OUTPUT_CHANGED";
            case 10:
                return "MESSENGER_ROOMS_PRE_MESSENGER_ROOMS_CALL_MEDIA_STATE_CHANGED";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
