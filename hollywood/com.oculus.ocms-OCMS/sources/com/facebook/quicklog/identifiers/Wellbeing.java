package com.facebook.quicklog.identifiers;

public class Wellbeing {
    public static final short MODULE_ID = 719;
    public static final int YTOF1_TTRC = 47120385;
    public static final int YTOF2_MANAGE_TTRC = 47120388;
    public static final int YTOF2_MORE_TTRC = 47120389;
    public static final int YTOF2_NOTIFICATIONS_TTRC = 47120390;
    public static final int YTOF2_TTRC = 47120386;
    public static final int YTOF2_USAGE_TTRC = 47120387;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "WELLBEING_YTOF1_TTRC";
            case 2:
                return "WELLBEING_YTOF2_TTRC";
            case 3:
                return "WELLBEING_YTOF2_USAGE_TTRC";
            case 4:
                return "WELLBEING_YTOF2_MANAGE_TTRC";
            case 5:
                return "WELLBEING_YTOF2_MORE_TTRC";
            case 6:
                return "WELLBEING_YTOF2_NOTIFICATIONS_TTRC";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
