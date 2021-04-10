package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;

public enum TooltipPosition {
    Top("top"),
    Bottom("bottom"),
    Left("left"),
    Right("right");
    
    private static final String TAG = TooltipPosition.class.getSimpleName();
    private final String IPCString;

    private TooltipPosition(String IPCString2) {
        this.IPCString = IPCString2;
    }

    public String getIPCString() {
        return this.IPCString;
    }

    public static TooltipPosition getTooltipPosition(String tooltipPositionString) {
        TooltipPosition[] values = values();
        for (TooltipPosition tooltipPosition : values) {
            if (tooltipPosition.getIPCString().equals(tooltipPositionString)) {
                return tooltipPosition;
            }
        }
        Log.w(TAG, String.format("Unexpected tooltip position string \"%s\", defaulting to TooltipPosition.Top", tooltipPositionString));
        return Top;
    }
}
