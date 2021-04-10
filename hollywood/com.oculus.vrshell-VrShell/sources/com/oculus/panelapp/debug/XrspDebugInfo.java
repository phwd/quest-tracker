package com.oculus.panelapp.debug;

public class XrspDebugInfo {
    public static native int getState();

    private static native byte[] getStateAsByteArray();

    public static String getStateString() {
        return new String(getStateAsByteArray());
    }
}
