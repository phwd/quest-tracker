package com.facebook.quicklog.identifiers;

public class Bugreport {
    public static final int BUGREPORT_FUNNEL = 30539800;
    public static final int BUG_REPORTER_FUNNEL = 30554356;
    public static final int BUG_REPORT_CORE_INFRA_FUNNEL = 30552700;
    public static final int CAPTURE_SCREENSHOT = 30539788;
    public static final int FINALIZE = 30539782;
    public static final int FINALIZE_SHADOW = 30539791;
    public static final int FLOW_START = 30539796;
    public static final int GENERATE_REPORT = 30545646;
    public static final int GET_EXTRA = 30539786;
    public static final int GET_EXTRA_ANDROID = 30539780;
    public static final int LOOM_VIDEO_PROFILER = 30539787;
    public static final int MENU_DISMISSED = 30539794;
    public static final short MODULE_ID = 466;
    public static final int OPEN_MENU = 30539799;
    public static final int OUTLIER_DETECTED = 30547628;
    public static final int PERSIST_TO_DISK = 30539783;
    public static final int RAGE_SHAKE_ENABLED = 30539798;
    public static final int REPORT_FLOW = 30539781;
    public static final int SOMETHING_NOT_WORKING_ON_CLICK = 30539795;
    public static final int TOGGLE_SHAKE_SETTING = 30539793;
    public static final int UPLOAD = 30539777;
    public static final int UPLOAD_ATTACHMENTS = 30539778;
    public static final int UPLOAD_ATTACHMENTS_SHADOW = 30539801;
    public static final int UPLOAD_SHADOW = 30539790;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "BUGREPORT_UPLOAD";
        }
        if (i == 2) {
            return "BUGREPORT_UPLOAD_ATTACHMENTS";
        }
        if (i == 4) {
            return "BUGREPORT_GET_EXTRA_ANDROID";
        }
        if (i == 5) {
            return "BUGREPORT_REPORT_FLOW";
        }
        if (i == 6) {
            return "BUGREPORT_FINALIZE";
        }
        if (i == 7) {
            return "BUGREPORT_PERSIST_TO_DISK";
        }
        if (i == 14) {
            return "BUGREPORT_UPLOAD_SHADOW";
        }
        if (i == 15) {
            return "BUGREPORT_FINALIZE_SHADOW";
        }
        if (i == 5870) {
            return "BUGREPORT_GENERATE_REPORT";
        }
        if (i == 7852) {
            return "BUGREPORT_OUTLIER_DETECTED";
        }
        if (i == 12924) {
            return "BUGREPORT_BUG_REPORT_CORE_INFRA_FUNNEL";
        }
        if (i == 14580) {
            return "BUGREPORT_BUG_REPORTER_FUNNEL";
        }
        switch (i) {
            case 10:
                return "BUGREPORT_GET_EXTRA";
            case 11:
                return "BUGREPORT_LOOM_VIDEO_PROFILER";
            case 12:
                return "BUGREPORT_CAPTURE_SCREENSHOT";
            default:
                switch (i) {
                    case 17:
                        return "BUGREPORT_TOGGLE_SHAKE_SETTING";
                    case 18:
                        return "BUGREPORT_MENU_DISMISSED";
                    case 19:
                        return "BUGREPORT_SOMETHING_NOT_WORKING_ON_CLICK";
                    case 20:
                        return "BUGREPORT_FLOW_START";
                    default:
                        switch (i) {
                            case 22:
                                return "BUGREPORT_RAGE_SHAKE_ENABLED";
                            case 23:
                                return "BUGREPORT_OPEN_MENU";
                            case 24:
                                return "BUGREPORT_BUGREPORT_FUNNEL";
                            case 25:
                                return "BUGREPORT_UPLOAD_ATTACHMENTS_SHADOW";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
