package com.facebook.quicklog.identifiers;

public class ProfileEdit {
    public static final short MODULE_ID = 565;
    public static final int PROFILE_EDIT_INITIAL_LOAD_TTRC = 37027843;
    public static final int PROFILE_EDIT_INITIAL_LOAD_TTRC_ANDROID = 37027841;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "PROFILE_EDIT_PROFILE_EDIT_INITIAL_LOAD_TTRC" : "PROFILE_EDIT_PROFILE_EDIT_INITIAL_LOAD_TTRC_ANDROID";
    }
}
