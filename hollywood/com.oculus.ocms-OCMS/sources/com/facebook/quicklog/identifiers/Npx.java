package com.facebook.quicklog.identifiers;

public class Npx {
    public static final short MODULE_ID = 456;
    public static final int PROFILE_PICTURE_NUX_TTI_FB4A = 29884419;
    public static final int PROFILE_PICTURE_NUX_TTRC_FB4A = 29884420;
    public static final int QUICK_FRIENDING_TTI_FB4A = 29884417;
    public static final int QUICK_FRIENDING_TTRC_FB4A = 29884418;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED_QPL_EVENT" : "NPX_PROFILE_PICTURE_NUX_TTRC_FB4A" : "NPX_PROFILE_PICTURE_NUX_TTI_FB4A" : "NPX_QUICK_FRIENDING_TTRC_FB4A" : "NPX_QUICK_FRIENDING_TTI_FB4A";
    }
}
