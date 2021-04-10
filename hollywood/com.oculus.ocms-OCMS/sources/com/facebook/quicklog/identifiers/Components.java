package com.facebook.quicklog.identifiers;

public class Components {
    public static final int BENCHMARK_RUN = 9044005;
    public static final int CALCULATE_LAYOUT = 9043969;
    public static final int CALCULATE_LAYOUT_STATE = 9044003;
    public static final int COLLECT_RESULTS = 9043972;
    public static final int CREATE_LAYOUT = 9043970;
    public static final int CSS_LAYOUT = 9043971;
    public static final int DRAW = 9044004;
    public static final int DUPLICATE_KEY = 9043997;
    public static final int FETCH_GRAPHQL = 9043996;
    public static final int INIT_RANGE = 9044010;
    public static final int LAYOUT_STATE_FUTURE_GET_WAIT = 9044009;
    public static final short MODULE_ID = 138;
    public static final int MOUNT = 9043975;
    public static final int PREPARE_MOUNT = 9043974;
    public static final int PRE_ALLOCATE_MOUNT_CONTENT = 9043979;
    public static final int PROCESS_VISIBILITY_OUTPUTS = 9044011;
    public static final int RESUME_CALCULATE_LAYOUT_STATE = 9044007;
    public static final int SECTIONS_CREATE_NEW_TREE = 9044001;
    public static final int SECTIONS_DATA_DIFF_CALCULATE_DIFF = 9043998;
    public static final int SECTIONS_GENERATE_CHANGESET = 9044002;
    public static final int SECTIONS_INITIAL_TTI = 9043993;
    public static final int SECTIONS_ON_CREATE_CHILDREN = 9044000;
    public static final int SECTIONS_SET_ROOT = 9043999;
    public static final int SHOULD_UPDATE_REFERENCE_LAYOUT_MISMATCH = 9043978;
    public static final int STETHO_INSPECT_COMPONENT = 9043995;
    public static final int STETHO_UPDATE_COMPONENT = 9043994;

    public static String getMarkerName(int i) {
        if (i == 1) {
            return "COMPONENTS_CALCULATE_LAYOUT";
        }
        if (i == 2) {
            return "COMPONENTS_CREATE_LAYOUT";
        }
        if (i == 3) {
            return "COMPONENTS_CSS_LAYOUT";
        }
        if (i == 4) {
            return "COMPONENTS_COLLECT_RESULTS";
        }
        if (i == 6) {
            return "COMPONENTS_PREPARE_MOUNT";
        }
        if (i == 7) {
            return "COMPONENTS_MOUNT";
        }
        if (i == 10) {
            return "COMPONENTS_SHOULD_UPDATE_REFERENCE_LAYOUT_MISMATCH";
        }
        if (i == 11) {
            return "COMPONENTS_PRE_ALLOCATE_MOUNT_CONTENT";
        }
        if (i == 39) {
            return "COMPONENTS_RESUME_CALCULATE_LAYOUT_STATE";
        }
        switch (i) {
            case 25:
                return "COMPONENTS_SECTIONS_INITIAL_TTI";
            case 26:
                return "COMPONENTS_STETHO_UPDATE_COMPONENT";
            case 27:
                return "COMPONENTS_STETHO_INSPECT_COMPONENT";
            case 28:
                return "COMPONENTS_FETCH_GRAPHQL";
            case 29:
                return "COMPONENTS_DUPLICATE_KEY";
            case 30:
                return "COMPONENTS_SECTIONS_DATA_DIFF_CALCULATE_DIFF";
            case 31:
                return "COMPONENTS_SECTIONS_SET_ROOT";
            case 32:
                return "COMPONENTS_SECTIONS_ON_CREATE_CHILDREN";
            case 33:
                return "COMPONENTS_SECTIONS_CREATE_NEW_TREE";
            case 34:
                return "COMPONENTS_SECTIONS_GENERATE_CHANGESET";
            case 35:
                return "COMPONENTS_CALCULATE_LAYOUT_STATE";
            case 36:
                return "COMPONENTS_DRAW";
            case 37:
                return "COMPONENTS_BENCHMARK_RUN";
            default:
                switch (i) {
                    case 41:
                        return "COMPONENTS_LAYOUT_STATE_FUTURE_GET_WAIT";
                    case 42:
                        return "COMPONENTS_INIT_RANGE";
                    case 43:
                        return "COMPONENTS_PROCESS_VISIBILITY_OUTPUTS";
                    default:
                        return "UNDEFINED_QPL_EVENT";
                }
        }
    }
}
