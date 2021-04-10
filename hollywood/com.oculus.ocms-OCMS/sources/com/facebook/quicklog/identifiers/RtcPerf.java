package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class RtcPerf {
    public static final int ENGINE_INIT_SIGNALING_HANDLER = 16268901;
    public static final int FB_APP_TIME_TO_REDIRECT = 16252975;
    public static final int LAB_METRIC = 16252934;
    public static final short MODULE_ID = 248;
    public static final int NT_ACTION_DELAYED = 16252978;
    public static final int NT_ACTION_DURATION = 16252977;
    public static final int NT_ACTION_START = 16252979;
    public static final int PEER_VIDEO_SUBSCRIPTION_TO_FIRST_FRAME = 16260392;
    public static final int PLATFORM_CLIENT_TRANSACTION = 16252959;
    public static final int PLATFORM_FIRST_MEDIA_RECEIVED = 16263317;
    public static final int PLATFORM_MWPP_PRECONNECT_FLOW = 16252970;
    public static final int PLATFORM_MW_CONNECT_TO_PARTICIPANT_FLOW = 16252955;
    public static final int PLATFORM_MW_INVITE_PARTICIPANT_INVITEE_FLOW = 16252956;
    public static final int PLATFORM_MW_INVITE_PARTICIPANT_INVITER_FLOW = 16252957;
    public static final int PLATFORM_MW_JOIN_CALL_FLOW = 16252954;
    public static final int PLATFORM_MW_SET_REMOTE_DESCRIPTION = 16252962;
    public static final int PLATFORM_P2P_ACCEPT_CALL_CALLEE_FLOW = 16252951;
    public static final int PLATFORM_P2P_ACCEPT_CALL_CALLER_FLOW = 16252950;
    public static final int PLATFORM_P2P_AUDIO_PERF = 16252941;
    public static final int PLATFORM_P2P_SET_LOCAL_DESCRIPTION = 16252943;
    public static final int PLATFORM_P2P_SET_REMOTE_DESCRIPTION = 16252940;
    public static final int PLATFORM_P2P_START_CALL_CALLEE_FLOW = 16252948;
    public static final int PLATFORM_P2P_START_CALL_CALLER_FLOW = 16252947;
    public static final int PLATFORM_P2P_VIDEO_PERF = 16252942;
    public static final int PLATFORM_SDP_RENEGOTIATION = 16252969;
    public static final int PLATFORM_SERVER_TRANSACTION = 16252963;
    public static final int RTC_CALL_ENDED = 16252964;
    public static final int RTC_CALL_ENDED_INTERACTIVE = 16252982;
    public static final int RTC_COEX_SERVER_MSG = 16252968;
    public static final int RTC_CREATE_CALL_ENT = 16252935;
    public static final int RTC_ENTER_ROOM = 16273691;
    public static final int RTC_FULLSCREEN_CREATE_TO_VISIBLE = 16252932;
    public static final int RTC_FULLSCREEN_TO_VCH = 16252931;
    public static final int RTC_INCOMING_CALL_ANSWERED = 16252938;
    public static final int RTC_INCOMING_CALL_ANSWERED_TO_INTERACTIVE = 16252974;
    public static final int RTC_INCOMING_CALL_START = 16252930;
    public static final int RTC_INCOMING_CALL_START_TO_INTERACTIVE = 16252973;
    public static final int RTC_INCOMING_SIGNALING = 16256049;
    public static final int RTC_LOOPBACK_AUDIO = 16252967;
    public static final int RTC_MEDIA_STATE_UPDATE_TO_INTERACTIVE = 16252981;
    public static final int RTC_MQTT_TO_ENGINE = 16255329;
    public static final int RTC_OPEN_SCRIM = 16252952;
    public static final int RTC_OUTGOING_CALL_START = 16252929;
    public static final int RTC_OUTGOING_CALL_START_TO_INTERACTIVE = 16252972;
    public static final int RTC_REGENERATE_GRID_LAYOUT = 16252953;
    public static final int RTC_ROOM_JOIN = 16252976;
    public static final int RTC_SCRIM_CONTACTS_PICKER = 16252958;
    public static final int RTC_SESSION_DURATION = 16278579;
    public static final int RTC_SIGNALING_MESSAGE_DELAY = 16269188;
    public static final int RTC_SIGNALING_MESSAGE_FLOW = 16256969;
    public static final int RTC_USER_STATE_UPDATE_TO_INTERACTIVE = 16252980;
    public static final int RTC_VCH_TO_FULLSCREEN = 16252933;
    public static final int RTC_VOIP_STATUS_BAR_PRESSED = 16252939;

    public static String getMarkerName(int i) {
        if (i == 19) {
            return "RTC_PERF_PLATFORM_P2P_START_CALL_CALLER_FLOW";
        }
        if (i == 20) {
            return "RTC_PERF_PLATFORM_P2P_START_CALL_CALLEE_FLOW";
        }
        if (i == 2401) {
            return "RTC_PERF_RTC_MQTT_TO_ENGINE";
        }
        if (i == 3121) {
            return "RTC_PERF_RTC_INCOMING_SIGNALING";
        }
        if (i == 4041) {
            return "RTC_PERF_RTC_SIGNALING_MESSAGE_FLOW";
        }
        if (i == 7464) {
            return "RTC_PERF_PEER_VIDEO_SUBSCRIPTION_TO_FIRST_FRAME";
        }
        if (i == 10389) {
            return "RTC_PERF_PLATFORM_FIRST_MEDIA_RECEIVED";
        }
        if (i == 15973) {
            return "RTC_PERF_ENGINE_INIT_SIGNALING_HANDLER";
        }
        if (i == 16260) {
            return "RTC_PERF_RTC_SIGNALING_MESSAGE_DELAY";
        }
        if (i == 20763) {
            return "RTC_PERF_RTC_ENTER_ROOM";
        }
        if (i == 25651) {
            return "RTC_PERF_RTC_SESSION_DURATION";
        }
        switch (i) {
            case 1:
                return "RTC_PERF_RTC_OUTGOING_CALL_START";
            case 2:
                return "RTC_PERF_RTC_INCOMING_CALL_START";
            case 3:
                return "RTC_PERF_RTC_FULLSCREEN_TO_VCH";
            case 4:
                return "RTC_PERF_RTC_FULLSCREEN_CREATE_TO_VISIBLE";
            case 5:
                return "RTC_PERF_RTC_VCH_TO_FULLSCREEN";
            case 6:
                return "RTC_PERF_LAB_METRIC";
            case 7:
                return "RTC_PERF_RTC_CREATE_CALL_ENT";
            default:
                switch (i) {
                    case 10:
                        return "RTC_PERF_RTC_INCOMING_CALL_ANSWERED";
                    case 11:
                        return "RTC_PERF_RTC_VOIP_STATUS_BAR_PRESSED";
                    case 12:
                        return "RTC_PERF_PLATFORM_P2P_SET_REMOTE_DESCRIPTION";
                    case 13:
                        return "RTC_PERF_PLATFORM_P2P_AUDIO_PERF";
                    case 14:
                        return "RTC_PERF_PLATFORM_P2P_VIDEO_PERF";
                    case 15:
                        return "RTC_PERF_PLATFORM_P2P_SET_LOCAL_DESCRIPTION";
                    default:
                        switch (i) {
                            case 22:
                                return "RTC_PERF_PLATFORM_P2P_ACCEPT_CALL_CALLER_FLOW";
                            case 23:
                                return "RTC_PERF_PLATFORM_P2P_ACCEPT_CALL_CALLEE_FLOW";
                            case 24:
                                return "RTC_PERF_RTC_OPEN_SCRIM";
                            case 25:
                                return "RTC_PERF_RTC_REGENERATE_GRID_LAYOUT";
                            case 26:
                                return "RTC_PERF_PLATFORM_MW_JOIN_CALL_FLOW";
                            case 27:
                                return "RTC_PERF_PLATFORM_MW_CONNECT_TO_PARTICIPANT_FLOW";
                            case 28:
                                return "RTC_PERF_PLATFORM_MW_INVITE_PARTICIPANT_INVITEE_FLOW";
                            case 29:
                                return "RTC_PERF_PLATFORM_MW_INVITE_PARTICIPANT_INVITER_FLOW";
                            case 30:
                                return "RTC_PERF_RTC_SCRIM_CONTACTS_PICKER";
                            case 31:
                                return "RTC_PERF_PLATFORM_CLIENT_TRANSACTION";
                            default:
                                switch (i) {
                                    case 34:
                                        return "RTC_PERF_PLATFORM_MW_SET_REMOTE_DESCRIPTION";
                                    case 35:
                                        return "RTC_PERF_PLATFORM_SERVER_TRANSACTION";
                                    case 36:
                                        return "RTC_PERF_RTC_CALL_ENDED";
                                    default:
                                        switch (i) {
                                            case 39:
                                                return "RTC_PERF_RTC_LOOPBACK_AUDIO";
                                            case 40:
                                                return "RTC_PERF_RTC_COEX_SERVER_MSG";
                                            case 41:
                                                return "RTC_PERF_PLATFORM_SDP_RENEGOTIATION";
                                            case 42:
                                                return "RTC_PERF_PLATFORM_MWPP_PRECONNECT_FLOW";
                                            default:
                                                switch (i) {
                                                    case 44:
                                                        return "RTC_PERF_RTC_OUTGOING_CALL_START_TO_INTERACTIVE";
                                                    case 45:
                                                        return "RTC_PERF_RTC_INCOMING_CALL_START_TO_INTERACTIVE";
                                                    case 46:
                                                        return "RTC_PERF_RTC_INCOMING_CALL_ANSWERED_TO_INTERACTIVE";
                                                    case 47:
                                                        return "RTC_PERF_FB_APP_TIME_TO_REDIRECT";
                                                    case 48:
                                                        return "RTC_PERF_RTC_ROOM_JOIN";
                                                    case UL.id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                                                        return "RTC_PERF_NT_ACTION_DURATION";
                                                    case 50:
                                                        return "RTC_PERF_NT_ACTION_DELAYED";
                                                    case 51:
                                                        return "RTC_PERF_NT_ACTION_START";
                                                    case 52:
                                                        return "RTC_PERF_RTC_USER_STATE_UPDATE_TO_INTERACTIVE";
                                                    case 53:
                                                        return "RTC_PERF_RTC_MEDIA_STATE_UPDATE_TO_INTERACTIVE";
                                                    case 54:
                                                        return "RTC_PERF_RTC_CALL_ENDED_INTERACTIVE";
                                                    default:
                                                        return "UNDEFINED_QPL_EVENT";
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
