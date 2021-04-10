package com.facebook.quicklog.identifiers;

public class FbliteClientInstrumentation {
    public static final int FBLITE_FIZZ_SOCKET = 38076418;
    public static final int FBLITE_SOCKET_CREATE = 38076417;
    public static final short MODULE_ID = 581;

    public static String getMarkerName(int i) {
        return i != 1 ? i != 2 ? "UNDEFINED_QPL_EVENT" : "FBLITE_CLIENT_INSTRUMENTATION_FBLITE_FIZZ_SOCKET" : "FBLITE_CLIENT_INSTRUMENTATION_FBLITE_SOCKET_CREATE";
    }
}
