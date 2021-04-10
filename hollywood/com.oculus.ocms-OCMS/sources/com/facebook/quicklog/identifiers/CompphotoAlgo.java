package com.facebook.quicklog.identifiers;

public class CompphotoAlgo {
    public static final int COMPPHOTO_AUTOENHANCE_TIME_TO_FIRST_FRAME = 60686337;
    public static final int COMPPHOTO_TIME_TO_FIRST_FRAME = 60691185;
    public static final short MODULE_ID = 926;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 4849 ? "UNDEFINED_QPL_EVENT" : "COMPPHOTO_ALGO_COMPPHOTO_TIME_TO_FIRST_FRAME" : "COMPPHOTO_ALGO_COMPPHOTO_AUTOENHANCE_TIME_TO_FIRST_FRAME";
    }
}
