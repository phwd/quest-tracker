package com.facebook.quicklog.identifiers;

public class SparkBrowser {
    public static final int AFFORDANCE_TTD = 42205191;
    public static final int COORDINATOR_DET_TO_ACT_TTP = 42205192;
    public static final int GLOBAL_DETERMINATOR_STARTUP = 42205190;
    public static final int GLOBAL_DETERMINATOR_TTI = 42205189;
    public static final short MODULE_ID = 644;
    public static final int NAMETAG_TTI = 42205186;
    public static final int SPARK_BROWSER_SESSION = 42205185;
    public static final int SPARK_BROWSER_TIME_TO_FIRST_FRAME = 42205187;
    public static final int TARGET_AR_TTI = 42205188;
    public static final int TRACKING_ACTIVATOR_RENDER_TIME = 42205193;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "SPARK_BROWSER_SPARK_BROWSER_SESSION";
            case 2:
                return "SPARK_BROWSER_NAMETAG_TTI";
            case 3:
                return "SPARK_BROWSER_SPARK_BROWSER_TIME_TO_FIRST_FRAME";
            case 4:
                return "SPARK_BROWSER_TARGET_AR_TTI";
            case 5:
                return "SPARK_BROWSER_GLOBAL_DETERMINATOR_TTI";
            case 6:
                return "SPARK_BROWSER_GLOBAL_DETERMINATOR_STARTUP";
            case 7:
                return "SPARK_BROWSER_AFFORDANCE_TTD";
            case 8:
                return "SPARK_BROWSER_COORDINATOR_DET_TO_ACT_TTP";
            case 9:
                return "SPARK_BROWSER_TRACKING_ACTIVATOR_RENDER_TIME";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
