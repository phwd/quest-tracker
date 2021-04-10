package com.facebook.quicklog.identifiers;

public class ThreedPhotos {
    public static final int CNN_DEPTH_GENERATION = 37617674;
    public static final int CNN_MODEL_DOWNLOAD = 37617673;
    public static final int CREATION_E2E = 37617693;
    public static final int GLB_CREATION = 37617669;
    public static final int GLB_CREATION_ANDROID = 37617665;
    public static final int GLTF_DOWNLOAD = 37617672;
    public static final int MODEL_DOWNLOAD = 37628909;
    public static final short MODULE_ID = 574;
    public static final int PARSE_DEPTH_DATA = 37617676;
    public static final int PROCESSOR_ADD_FRAME = 37617692;
    public static final int PROCESSOR_BOUNDARY_INIT = 37617683;
    public static final int PROCESSOR_CLOSE_BACKGROUND_GAPS = 37617686;
    public static final int PROCESSOR_CREATE_ATLAS = 37617688;
    public static final int PROCESSOR_CREATE_MESH = 37617691;
    public static final int PROCESSOR_DEPTH_MAP_FILTER = 37617680;
    public static final int PROCESSOR_DEPTH_REFINEMENT_WITH_MATTE = 37617694;
    public static final int PROCESSOR_EXPAND_BACKGROUND = 37617685;
    public static final int PROCESSOR_EXPAND_OUTSIDE_IN_PLACE = 37617687;
    public static final int PROCESSOR_FIX_DEPTH_CC = 37617682;
    public static final int PROCESSOR_INITIALIZE = 37617679;
    public static final int PROCESSOR_INIT_DEEP_IMAGE = 37617681;
    public static final int PROCESSOR_INPAINT_ATLAS = 37617689;
    public static final int PROCESSOR_PAD_ATLAS = 37617690;
    public static final int PROCESSOR_PROCESS = 37617678;
    public static final int PROCESSOR_SHRINK_FOREGROUND = 37617684;
    public static final int RENDERER_CREATED = 37617697;
    public static final int RESIZE_FALLBACK_PHOTO = 37617695;
    public static final int VOLTRON_DOWNLOAD_ANDROID = 37617675;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "THREED_PHOTOS_GLB_CREATION_ANDROID";
        }
        if (i == 5) {
            return "THREED_PHOTOS_GLB_CREATION";
        }
        if (i == 33) {
            return "THREED_PHOTOS_RENDERER_CREATED";
        }
        if (i == 11245) {
            return "THREED_PHOTOS_MODEL_DOWNLOAD";
        }
        switch (i) {
            case 8:
                return "THREED_PHOTOS_GLTF_DOWNLOAD";
            case 9:
                return "THREED_PHOTOS_CNN_MODEL_DOWNLOAD";
            case 10:
                return "THREED_PHOTOS_CNN_DEPTH_GENERATION";
            case 11:
                return "THREED_PHOTOS_VOLTRON_DOWNLOAD_ANDROID";
            case 12:
                return "THREED_PHOTOS_PARSE_DEPTH_DATA";
            default:
                switch (i) {
                    case 14:
                        return "THREED_PHOTOS_PROCESSOR_PROCESS";
                    case 15:
                        return "THREED_PHOTOS_PROCESSOR_INITIALIZE";
                    case 16:
                        return "THREED_PHOTOS_PROCESSOR_DEPTH_MAP_FILTER";
                    case 17:
                        return "THREED_PHOTOS_PROCESSOR_INIT_DEEP_IMAGE";
                    case 18:
                        return "THREED_PHOTOS_PROCESSOR_FIX_DEPTH_CC";
                    case 19:
                        return "THREED_PHOTOS_PROCESSOR_BOUNDARY_INIT";
                    case 20:
                        return "THREED_PHOTOS_PROCESSOR_SHRINK_FOREGROUND";
                    case 21:
                        return "THREED_PHOTOS_PROCESSOR_EXPAND_BACKGROUND";
                    case 22:
                        return "THREED_PHOTOS_PROCESSOR_CLOSE_BACKGROUND_GAPS";
                    case 23:
                        return "THREED_PHOTOS_PROCESSOR_EXPAND_OUTSIDE_IN_PLACE";
                    case 24:
                        return "THREED_PHOTOS_PROCESSOR_CREATE_ATLAS";
                    case 25:
                        return "THREED_PHOTOS_PROCESSOR_INPAINT_ATLAS";
                    case 26:
                        return "THREED_PHOTOS_PROCESSOR_PAD_ATLAS";
                    case 27:
                        return "THREED_PHOTOS_PROCESSOR_CREATE_MESH";
                    case 28:
                        return "THREED_PHOTOS_PROCESSOR_ADD_FRAME";
                    case 29:
                        return "THREED_PHOTOS_CREATION_E2E";
                    case 30:
                        return "THREED_PHOTOS_PROCESSOR_DEPTH_REFINEMENT_WITH_MATTE";
                    case 31:
                        return "THREED_PHOTOS_RESIZE_FALLBACK_PHOTO";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
