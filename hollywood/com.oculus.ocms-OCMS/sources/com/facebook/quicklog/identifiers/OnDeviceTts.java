package com.facebook.quicklog.identifiers;

public class OnDeviceTts {
    public static final int CREATE = 60751875;
    public static final int LOAD_MODEL = 60751874;
    public static final int LOAD_MODEL_CLIENT = 60751877;
    public static final short MODULE_ID = 927;
    public static final int RELEASE = 60751876;
    public static final int SPEAK = 60751873;
    public static final int SPEAK_CLIENT = 60751878;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "ON_DEVICE_TTS_SPEAK";
            case 2:
                return "ON_DEVICE_TTS_LOAD_MODEL";
            case 3:
                return "ON_DEVICE_TTS_CREATE";
            case 4:
                return "ON_DEVICE_TTS_RELEASE";
            case 5:
                return "ON_DEVICE_TTS_LOAD_MODEL_CLIENT";
            case 6:
                return "ON_DEVICE_TTS_SPEAK_CLIENT";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
