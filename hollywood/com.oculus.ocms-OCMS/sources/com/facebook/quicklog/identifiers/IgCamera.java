package com.facebook.quicklog.identifiers;

public class IgCamera {
    public static final int CAMERA_DID_SHOW_EFFECTS = 17629206;
    public static final int DOWNLOAD_EFFECT_STORIES = 17629205;
    public static final int DOWNLOAD_METADATA = 17629200;
    public static final int DOWNLOAD_PREVIEWS = 17629201;
    public static final int DRAFT_EFFECT_DEMO_UPLOAD = 17638750;
    public static final int DRAFT_EFFECT_LINK_LOAD = 17629208;
    public static final int EFFECT_LINK_LOAD = 17654727;
    public static final int EFFECT_SEARCH_FETCH = 17631975;
    public static final int EFFECT_SELECTED_POST_CAPTURE = 17631547;
    public static final int EFFECT_SELECTED_PRE_CAPTURE = 17629207;
    public static final int MINI_GALLERY_CATEGORIES_FETCH = 17635885;
    public static final int MINI_GALLERY_DOWNLOAD_PREVIEWS = 17631795;
    public static final int MINI_GALLERY_LOAD = 17638221;
    public static final int MINI_GALLERY_PREFETCH = 17640585;
    public static final short MODULE_ID = 269;
    public static final int REPORT_EFFECT = 17629204;
    public static final int TIME_TO_FIRST_GALLERY_LOAD = 17629202;
    public static final int TTCP = 17629195;
    public static final int TTFF = 17629193;
    public static final int TTI = 17629194;
    public static final int WRITE_DEVICE_CAPABILITIES = 17637475;

    public static String getMarkerName(int i) {
        if (i == 2363) {
            return "IG_CAMERA_EFFECT_SELECTED_POST_CAPTURE";
        }
        if (i == 2611) {
            return "IG_CAMERA_MINI_GALLERY_DOWNLOAD_PREVIEWS";
        }
        if (i == 2791) {
            return "IG_CAMERA_EFFECT_SEARCH_FETCH";
        }
        if (i == 6701) {
            return "IG_CAMERA_MINI_GALLERY_CATEGORIES_FETCH";
        }
        if (i == 8291) {
            return "IG_CAMERA_WRITE_DEVICE_CAPABILITIES";
        }
        if (i == 9037) {
            return "IG_CAMERA_MINI_GALLERY_LOAD";
        }
        if (i == 9566) {
            return "IG_CAMERA_DRAFT_EFFECT_DEMO_UPLOAD";
        }
        if (i == 11401) {
            return "IG_CAMERA_MINI_GALLERY_PREFETCH";
        }
        if (i == 25543) {
            return "IG_CAMERA_EFFECT_LINK_LOAD";
        }
        switch (i) {
            case 9:
                return "IG_CAMERA_TTFF";
            case 10:
                return "IG_CAMERA_TTI";
            case 11:
                return "IG_CAMERA_TTCP";
            default:
                switch (i) {
                    case 16:
                        return "IG_CAMERA_DOWNLOAD_METADATA";
                    case 17:
                        return "IG_CAMERA_DOWNLOAD_PREVIEWS";
                    case 18:
                        return "IG_CAMERA_TIME_TO_FIRST_GALLERY_LOAD";
                    default:
                        switch (i) {
                            case 20:
                                return "IG_CAMERA_REPORT_EFFECT";
                            case 21:
                                return "IG_CAMERA_DOWNLOAD_EFFECT_STORIES";
                            case 22:
                                return "IG_CAMERA_CAMERA_DID_SHOW_EFFECTS";
                            case 23:
                                return "IG_CAMERA_EFFECT_SELECTED_PRE_CAPTURE";
                            case 24:
                                return "IG_CAMERA_DRAFT_EFFECT_LINK_LOAD";
                            default:
                                return "UNDEFINED_QPL_EVENT";
                        }
                }
        }
    }
}
