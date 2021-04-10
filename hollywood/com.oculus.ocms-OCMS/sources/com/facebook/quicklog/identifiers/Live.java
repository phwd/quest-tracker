package com.facebook.quicklog.identifiers;

public class Live {
    public static final int ANDROID_RTMP_DISK_RECORDING_HANDLE_FRAME = 14548997;
    public static final int ANDROID_RTMP_HANDLE_FRAME = 14548993;
    public static final int ANDROID_RTMP_LIVE_STREAMING_HANDLE_FRAME = 14548996;
    public static final int BROADCAST_COMPOSER_NETWORK_CONNECT_TTL = 14549003;
    public static final int BROADCAST_RECORD_VIDEO_ENTITY_TTL = 14549002;
    public static final int COMMENTS_TTI = 14548998;
    public static final int FACECASTFORM_RESPONSIVENESS = 14553689;
    public static final int FACECAST_BROADCASTER_FUNNEL_TEST = 14554143;
    public static final int FULLSCREEN_L0_PLAYBACK_TTRC = 14570820;
    public static final int FULLSCREEN_L1_CORE_FUNCTIONALITY_TTRC = 14563746;
    public static final int FULLSCREEN_L2_KEY_EXPERIENCE_TTRC = 14570292;
    public static final int FULLSCREEN_RESPONSIVENESS = 14558945;
    public static final int FULLSCREEN_STEADY_STATE = 14559974;
    public static final int LIVE_ANDROID_CONTROLLER_LIFECYCLE = 14549004;
    public static final int LIVE_DONATION_TTRC = 14555175;
    public static final int LIVE_EVENT_DECAY_ANIMATION = 14549008;
    public static final int LIVE_STATUS_TRANSITION = 14553642;
    public static final int LIVING_ROOM_ADD_VIDEO_TAB_TTL = 14549007;
    public static final int LIVING_ROOM_ASYNC_CONTROLLER_REQUEST_RESULT = 14549018;
    public static final int LIVING_ROOM_ASYNC_CONTROLLER_REQUEST_TYPEAHEAD = 14549017;
    public static final int LIVING_ROOM_AVD_CONTENT_TTL = 14549019;
    public static final int LIVING_ROOM_BUMPER_ANIMATION = 14549006;
    public static final int LIVING_ROOM_CONVERSATION_TOGGLE_ANDROID = 14549011;
    public static final int LIVING_ROOM_CREATION_TTL = 14549013;
    public static final int LIVING_ROOM_JOIN_TTL = 14549000;
    public static final int LIVING_ROOM_VIDEO_STATE_TTI = 14548999;
    public static final short MODULE_ID = 222;
    public static final int PLAYER_LIVING_ROOM_NT_AVD_TTL = 14549014;
    public static final int PREPOP_LIVING_ROOM_NT_AVD_TTL = 14549012;
    public static final int STARS_COMMENT_FETCHED_TO_VPV_FB4A = 14549016;
    public static final int UNUSED_I = 14548995;
    public static final int UNUSED_II = 14548994;
    public static final int VIEWER_FULLSCREEN_VIDEO_ENTITY_TTL = 14549001;

    public static String getMarkerName(int i) {
        if (i == 4650) {
            return "LIVE_LIVE_STATUS_TRANSITION";
        }
        if (i == 4697) {
            return "LIVE_FACECASTFORM_RESPONSIVENESS";
        }
        if (i == 5151) {
            return "LIVE_FACECAST_BROADCASTER_FUNNEL_TEST";
        }
        if (i == 6183) {
            return "LIVE_LIVE_DONATION_TTRC";
        }
        if (i == 9953) {
            return "LIVE_FULLSCREEN_RESPONSIVENESS";
        }
        if (i == 10982) {
            return "LIVE_FULLSCREEN_STEADY_STATE";
        }
        if (i == 14754) {
            return "LIVE_FULLSCREEN_L1_CORE_FUNCTIONALITY_TTRC";
        }
        if (i == 21300) {
            return "LIVE_FULLSCREEN_L2_KEY_EXPERIENCE_TTRC";
        }
        if (i == 21828) {
            return "LIVE_FULLSCREEN_L0_PLAYBACK_TTRC";
        }
        switch (i) {
            case 1:
                return "LIVE_ANDROID_RTMP_HANDLE_FRAME";
            case 2:
                return "LIVE_UNUSED_II";
            case 3:
                return "LIVE_UNUSED_I";
            case 4:
                return "LIVE_ANDROID_RTMP_LIVE_STREAMING_HANDLE_FRAME";
            case 5:
                return "LIVE_ANDROID_RTMP_DISK_RECORDING_HANDLE_FRAME";
            case 6:
                return "LIVE_COMMENTS_TTI";
            case 7:
                return "LIVE_LIVING_ROOM_VIDEO_STATE_TTI";
            case 8:
                return "LIVE_LIVING_ROOM_JOIN_TTL";
            case 9:
                return "LIVE_VIEWER_FULLSCREEN_VIDEO_ENTITY_TTL";
            case 10:
                return "LIVE_BROADCAST_RECORD_VIDEO_ENTITY_TTL";
            case 11:
                return "LIVE_BROADCAST_COMPOSER_NETWORK_CONNECT_TTL";
            case 12:
                return "LIVE_LIVE_ANDROID_CONTROLLER_LIFECYCLE";
            default:
                switch (i) {
                    case 14:
                        return "LIVE_LIVING_ROOM_BUMPER_ANIMATION";
                    case 15:
                        return "LIVE_LIVING_ROOM_ADD_VIDEO_TAB_TTL";
                    case 16:
                        return "LIVE_LIVE_EVENT_DECAY_ANIMATION";
                    default:
                        switch (i) {
                            case 19:
                                return "LIVE_LIVING_ROOM_CONVERSATION_TOGGLE_ANDROID";
                            case 20:
                                return "LIVE_PREPOP_LIVING_ROOM_NT_AVD_TTL";
                            case 21:
                                return "LIVE_LIVING_ROOM_CREATION_TTL";
                            case 22:
                                return "LIVE_PLAYER_LIVING_ROOM_NT_AVD_TTL";
                            default:
                                switch (i) {
                                    case 24:
                                        return "LIVE_STARS_COMMENT_FETCHED_TO_VPV_FB4A";
                                    case 25:
                                        return "LIVE_LIVING_ROOM_ASYNC_CONTROLLER_REQUEST_TYPEAHEAD";
                                    case 26:
                                        return "LIVE_LIVING_ROOM_ASYNC_CONTROLLER_REQUEST_RESULT";
                                    case 27:
                                        return "LIVE_LIVING_ROOM_AVD_CONTENT_TTL";
                                    default:
                                        return "UNDEFINED_QPL_EVENT";
                                }
                        }
                }
        }
    }
}
