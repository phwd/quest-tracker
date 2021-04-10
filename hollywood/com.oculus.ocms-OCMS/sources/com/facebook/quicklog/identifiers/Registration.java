package com.facebook.quicklog.identifiers;

public class Registration {
    public static final short MODULE_ID = 64;
    public static final int PERF_LOGGING_ACCOUNT_CREATION = 4194305;
    public static final int PERF_LOGGING_REGISTRATION_LOGIN = 4194307;
    public static final int PERF_LOGGING_STEP_VALIDATION = 4194306;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNDEFINED_QPL_EVENT" : "REGISTRATION_PERF_LOGGING_REGISTRATION_LOGIN" : "REGISTRATION_PERF_LOGGING_STEP_VALIDATION" : "REGISTRATION_PERF_LOGGING_ACCOUNT_CREATION";
    }
}
