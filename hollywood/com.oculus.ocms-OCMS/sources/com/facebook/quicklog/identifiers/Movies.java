package com.facebook.quicklog.identifiers;

public class Movies {
    public static final short MODULE_ID = 294;
    public static final int MOVIES_HOME_MOVIES_TAB_TTI_ANDROID = 19267592;
    public static final int MOVIES_HOME_MOVIES_TAB_TTI_FETCH_ANDROID = 19267593;
    public static final int MOVIES_HOME_THEATERS_TAB_TTI_ANDROID = 19267596;
    public static final int MOVIES_HOME_THEATERS_TAB_TTI_FETCH_ANDROID = 19267597;
    public static final int MOVIES_NATIVE_CHECKOUT_CONFIRMATION_TTRC_ANDROID = 19267605;
    public static final int MOVIES_NATIVE_CHECKOUT_ORDER_DETAILS_TTRC_ANDROID = 19267606;
    public static final int MOVIES_NATIVE_CHECKOUT_PAYMENTS_TTRC_ANDROID = 19267604;
    public static final int MOVIES_NATIVE_CHECKOUT_SEATMAP_TTRC_ANDROID = 19267603;
    public static final int MOVIES_NATIVE_CHECKOUT_TICKETS_TTRC_ANDROID = 19267599;
    public static final int MOVIES_PERMALINK_HEADER_FETCH_TTI = 19267585;
    public static final int MOVIES_PERMALINK_TTI = 19267586;
    public static final int SHOWTIME_PICKER_FETCH_TTI = 19267588;
    public static final int SHOWTIME_PICKER_TTI = 19267587;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "MOVIES_MOVIES_PERMALINK_HEADER_FETCH_TTI";
        }
        if (i == 2) {
            return "Movies Permalink TTI";
        }
        if (i == 3) {
            return "MOVIES_SHOWTIME_PICKER_TTI";
        }
        if (i == 4) {
            return "MOVIES_SHOWTIME_PICKER_FETCH_TTI";
        }
        if (i == 8) {
            return "MOVIES_MOVIES_HOME_MOVIES_TAB_TTI_ANDROID";
        }
        if (i == 9) {
            return "MOVIES_MOVIES_HOME_MOVIES_TAB_TTI_FETCH_ANDROID";
        }
        if (i == 12) {
            return "MOVIES_MOVIES_HOME_THEATERS_TAB_TTI_ANDROID";
        }
        if (i == 13) {
            return "MOVIES_MOVIES_HOME_THEATERS_TAB_TTI_FETCH_ANDROID";
        }
        if (i == 15) {
            return "MOVIES_MOVIES_NATIVE_CHECKOUT_TICKETS_TTRC_ANDROID";
        }
        switch (i) {
            case 19:
                return "MOVIES_MOVIES_NATIVE_CHECKOUT_SEATMAP_TTRC_ANDROID";
            case 20:
                return "MOVIES_MOVIES_NATIVE_CHECKOUT_PAYMENTS_TTRC_ANDROID";
            case 21:
                return "MOVIES_MOVIES_NATIVE_CHECKOUT_CONFIRMATION_TTRC_ANDROID";
            case 22:
                return "MOVIES_MOVIES_NATIVE_CHECKOUT_ORDER_DETAILS_TTRC_ANDROID";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
