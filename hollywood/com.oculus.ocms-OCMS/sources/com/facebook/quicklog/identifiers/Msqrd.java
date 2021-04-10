package com.facebook.quicklog.identifiers;

public class Msqrd {
    public static final int AML_FACETRACKER_HIGH_POLY = 11599878;
    public static final int AML_FACETRACKER_HIGH_POLY_100 = 11599880;
    public static final int AML_FACETRACKER_SUPER_HIGH_POLY = 11599879;
    public static final int AML_FACETRACKER_SUPER_HIGH_POLY_100 = 11599881;
    public static final int BENCHMARK_RUN = 11599874;
    public static final int FACETRACKER_SINGLE_FRAME = 11599882;
    public static final short MODULE_ID = 177;

    public static String getMarkerName(int i) {
        if (i == 2) {
            return "MSQRD_BENCHMARK_RUN";
        }
        switch (i) {
            case 6:
                return "MSQRD_AML_FACETRACKER_HIGH_POLY";
            case 7:
                return "MSQRD_AML_FACETRACKER_SUPER_HIGH_POLY";
            case 8:
                return "MSQRD_AML_FACETRACKER_HIGH_POLY_100";
            case 9:
                return "MSQRD_AML_FACETRACKER_SUPER_HIGH_POLY_100";
            case 10:
                return "MSQRD_FACETRACKER_SINGLE_FRAME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
