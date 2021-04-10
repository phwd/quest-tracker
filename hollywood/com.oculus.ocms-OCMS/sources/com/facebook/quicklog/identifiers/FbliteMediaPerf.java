package com.facebook.quicklog.identifiers;

public class FbliteMediaPerf {
    public static final int FBLITE_VIDEO_FS_META_FOOTER_CLICK = 40173573;
    public static final int FBLITE_WATCH_FS_NEXT_VIDEO_CLICK = 40173572;
    public static final int IMAGE_FETCH = 40173569;
    public static final int IMAGE_LOAD = 40173570;
    public static final int IMAGE_LOAD_EB = 40173574;
    public static final int IMAGE_LOAD_WEB = 40173575;
    public static final int MEDIA_UPLOAD = 40173571;
    public static final short MODULE_ID = 613;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "FBLITE_MEDIA_PERF_IMAGE_FETCH";
            case 2:
                return "Image Load";
            case 3:
                return "FBLITE_MEDIA_PERF_MEDIA_UPLOAD";
            case 4:
                return "FBLITE_MEDIA_PERF_FBLITE_WATCH_FS_NEXT_VIDEO_CLICK";
            case 5:
                return "FBLITE_MEDIA_PERF_FBLITE_VIDEO_FS_META_FOOTER_CLICK";
            case 6:
                return "FBLITE_MEDIA_PERF_IMAGE_LOAD_EB";
            case 7:
                return "FBLITE_MEDIA_PERF_IMAGE_LOAD_WEB";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
