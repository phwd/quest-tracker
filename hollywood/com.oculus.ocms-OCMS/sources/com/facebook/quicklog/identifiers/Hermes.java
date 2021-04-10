package com.facebook.quicklog.identifiers;

public class Hermes {
    public static final int EXECUTE_BYTECODE = 17039364;
    public static final int GC_BENCH = 17039361;
    public static final int INITIALIZE_VM = 17039363;
    public static final int INITIALIZE_VM2 = 17039365;
    public static final int LINK_HERMES = 17039362;
    public static final int LINK_JAVA2JS = 17039366;
    public static final short MODULE_ID = 260;

    public static String getMarkerName(int i) {
        switch (i) {
            case 1:
                return "GC Benchmarks";
            case 2:
                return "HERMES_LINK_HERMES";
            case 3:
                return "HERMES_INITIALIZE_VM";
            case 4:
                return "HERMES_EXECUTE_BYTECODE";
            case 5:
                return "HERMES_INITIALIZE_VM2";
            case 6:
                return "HERMES_LINK_JAVA2JS";
            default:
                return "UNDEFINED_QPL_EVENT";
        }
    }
}
