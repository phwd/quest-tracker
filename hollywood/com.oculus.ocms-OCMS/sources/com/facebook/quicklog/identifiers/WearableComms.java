package com.facebook.quicklog.identifiers;

import com.facebook.ultralight.UL;

public class WearableComms {
    public static final int ATTACHMENT_VIEW_TTRC = 1051527711;
    public static final int CAMERA_TTI = 1051535722;
    public static final int COMMSHUB_CALLOG_LOAD_MORE = 1051539021;
    public static final int COMMSHUB_CALLOG_SCROLL_PERF = 1051528107;
    public static final int COMMSHUB_CALLOG_TTRC = 1051529902;
    public static final int COMMSHUB_CALLOG_UPDATE = 1051534602;
    public static final int COMMSHUB_INBOX_LOAD_MORE = 1051541028;
    public static final int COMMSHUB_INBOX_SCROLL_PERF = 1051536812;
    public static final int COMMSHUB_INBOX_TTRC = 1051536219;
    public static final int COMMSHUB_INBOX_UPDATE = 1051532591;
    public static final int CONTACTS_SEARCH_LATENCY = 1051540787;
    public static final int CONTACTS_VIEW_SCROLL_PERF = 1051534920;
    public static final int FULL_CONTACTS_VIEW_LOAD_MORE_LATENCY = 1051528703;
    public static final int FULL_CONTACTS_VIEW_TTRC = 1051535566;
    public static final short MODULE_ID = 16045;
    public static final int NOTIF_TO_THREAD_VIEW_TTRC = 1051532903;
    public static final int PERF_AUDIO_REPLY = 1051538293;
    public static final int PHONE_CALL_TTRC = 1051527187;
    public static final int SUGGESTED_CONTACTS_VIEW_TTRC = 1051537001;
    public static final int THREAD_VIEW_LOAD_MORE = 1051537420;
    public static final int THREAD_VIEW_SCROLL_PERF = 1051528198;
    public static final int THREAD_VIEW_TTRC = 1051526156;
    public static final int THREAD_VIEW_UPDATE = 1051538591;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1036:
                return "WEARABLE_COMMS_THREAD_VIEW_TTRC";
            case UL.id._UL__ULSEP_android_renderscript_RenderScript_ULSEP_BINDING_ID /*{ENCODED_INT: 2067}*/:
                return "WEARABLE_COMMS_PHONE_CALL_TTRC";
            case 2591:
                return "WEARABLE_COMMS_ATTACHMENT_VIEW_TTRC";
            case 2987:
                return "WEARABLE_COMMS_COMMSHUB_CALLOG_SCROLL_PERF";
            case 3078:
                return "WEARABLE_COMMS_THREAD_VIEW_SCROLL_PERF";
            case 3583:
                return "WEARABLE_COMMS_FULL_CONTACTS_VIEW_LOAD_MORE_LATENCY";
            case 4782:
                return "WEARABLE_COMMS_COMMSHUB_CALLOG_TTRC";
            case 7471:
                return "WEARABLE_COMMS_COMMSHUB_INBOX_UPDATE";
            case 7783:
                return "WEARABLE_COMMS_NOTIF_TO_THREAD_VIEW_TTRC";
            case 9482:
                return "WEARABLE_COMMS_COMMSHUB_CALLOG_UPDATE";
            case 9800:
                return "WEARABLE_COMMS_CONTACTS_VIEW_SCROLL_PERF";
            case 10446:
                return "WEARABLE_COMMS_FULL_CONTACTS_VIEW_TTRC";
            case 10602:
                return "WEARABLE_COMMS_CAMERA_TTI";
            case 11099:
                return "WEARABLE_COMMS_COMMSHUB_INBOX_TTRC";
            case 11692:
                return "WEARABLE_COMMS_COMMSHUB_INBOX_SCROLL_PERF";
            case 11881:
                return "WEARABLE_COMMS_SUGGESTED_CONTACTS_VIEW_TTRC";
            case 12300:
                return "WEARABLE_COMMS_THREAD_VIEW_LOAD_MORE";
            case 13173:
                return "WEARABLE_COMMS_PERF_AUDIO_REPLY";
            case 13471:
                return "WEARABLE_COMMS_THREAD_VIEW_UPDATE";
            case 13901:
                return "WEARABLE_COMMS_COMMSHUB_CALLOG_LOAD_MORE";
            case 15667:
                return "WEARABLE_COMMS_CONTACTS_SEARCH_LATENCY";
            case 15908:
                return "WEARABLE_COMMS_COMMSHUB_INBOX_LOAD_MORE";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
