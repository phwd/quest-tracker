package com.facebook.quicklog.identifiers;

public class Places {
    public static final int ANDROID_PLACE_PICKER_CHECKIN_START_TO_PLACE_FETCH_START = 1376279;
    public static final int ANDROID_PLACE_PICKER_PLACE_FETCH_END_TO_PLACES_RENDERED = 1376281;
    public static final int ANDROID_PLACE_PICKER_PLACE_FETCH_START_TO_PLACE_FETCH_END = 1376280;
    public static final int ANDROID_PLACE_PICKER_TAP_ACTION_TO_CHECKIN_START = 1376278;
    public static final short MODULE_ID = 21;
    public static final int PLACES_PICKER_CHECKIN_BUTTON_TTI = 1376262;
    public static final int PLACES_PICKER_LOCATION_PIN_TTI = 1376261;
    public static final int PLACE_PICKER_INTERACTIONS = 1376286;
    public static final int PLACE_PICKER_LOCATION_PERF = 1376287;
    public static final int PLACE_PICKER_PERF = 1376285;

    public static String getMarkerName(int i) {
        if (i == 5) {
            return "PLACES_PLACES_PICKER_LOCATION_PIN_TTI";
        }
        if (i == 6) {
            return "PLACES_PLACES_PICKER_CHECKIN_BUTTON_TTI";
        }
        switch (i) {
            case 22:
                return "PLACES_ANDROID_PLACE_PICKER_TAP_ACTION_TO_CHECKIN_START";
            case 23:
                return "PLACES_ANDROID_PLACE_PICKER_CHECKIN_START_TO_PLACE_FETCH_START";
            case 24:
                return "PLACES_ANDROID_PLACE_PICKER_PLACE_FETCH_START_TO_PLACE_FETCH_END";
            case 25:
                return "PLACES_ANDROID_PLACE_PICKER_PLACE_FETCH_END_TO_PLACES_RENDERED";
            default:
                switch (i) {
                    case 29:
                        return "PLACES_PLACE_PICKER_PERF";
                    case 30:
                        return "PLACES_PLACE_PICKER_INTERACTIONS";
                    case 31:
                        return "PLACES_PLACE_PICKER_LOCATION_PERF";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
