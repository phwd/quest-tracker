package com.facebook.quicklog.identifiers;

public class MobileBoost {
    public static final int BOOSTER_SUPPORT = 27328521;
    public static final int CPU_BOOST_COMPATIBILITY = 27328527;
    public static final int ENABLED_BOOSTERS = 27328522;
    public static final int INITIALIZATION = 27328513;
    public static final int INIT_ALL_BOOSTERS = 27328517;
    public static final int INIT_BOOSTER = 27328518;
    public static final int INIT_MARKERS = 27328524;
    public static final int INIT_MOBILE_BOOST = 27328516;
    public static final int MB_GENERIC_EVENT = 27328523;
    public static final int MB_GENERIC_INIT_EVENT = 27328525;
    public static final int MB_OVERLAPPING_BOOST = 27328526;
    public static final int MOBILE_BOOST_IG_OPTIMIZATION_TEST = 27328514;
    public static final short MODULE_ID = 417;
    public static final int NO_OP_BOOSTER = 27328520;
    public static final int RAW_OPTIMIZATION_JSON = 27328528;
    public static final int TRIGGER_BOOSTING = 27328519;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "MOBILE_BOOST_INITIALIZATION";
            case 2:
                return "MOBILE_BOOST_MOBILE_BOOST_IG_OPTIMIZATION_TEST";
            case 3:
            default:
                return "UNDEFINED_QPL_EVENT";
            case 4:
                return "MOBILE_BOOST_INIT_MOBILE_BOOST";
            case 5:
                return "MOBILE_BOOST_INIT_ALL_BOOSTERS";
            case 6:
                return "MOBILE_BOOST_INIT_BOOSTER";
            case 7:
                return "MOBILE_BOOST_TRIGGER_BOOSTING";
            case 8:
                return "MOBILE_BOOST_NO_OP_BOOSTER";
            case 9:
                return "MOBILE_BOOST_BOOSTER_SUPPORT";
            case 10:
                return "MOBILE_BOOST_ENABLED_BOOSTERS";
            case 11:
                return "MOBILE_BOOST_MB_GENERIC_EVENT";
            case 12:
                return "MOBILE_BOOST_INIT_MARKERS";
            case 13:
                return "MOBILE_BOOST_MB_GENERIC_INIT_EVENT";
            case 14:
                return "MOBILE_BOOST_MB_OVERLAPPING_BOOST";
            case 15:
                return "MOBILE_BOOST_CPU_BOOST_COMPATIBILITY";
            case 16:
                return "MOBILE_BOOST_RAW_OPTIMIZATION_JSON";
        }
    }
}
