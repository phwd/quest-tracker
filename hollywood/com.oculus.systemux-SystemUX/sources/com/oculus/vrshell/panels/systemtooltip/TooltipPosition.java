package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;

public enum TooltipPosition {
    Top("top"),
    Bottom("bottom"),
    Left("left"),
    Right("right");
    
    private static final String TAG = TooltipPosition.class.getSimpleName();
    private final String IPCString;

    private TooltipPosition(String str) {
        this.IPCString = str;
    }

    public String getIPCString() {
        return this.IPCString;
    }

    public static TooltipPosition getTooltipPosition(String str) {
        TooltipPosition[] values = values();
        for (TooltipPosition tooltipPosition : values) {
            if (tooltipPosition.getIPCString().equals(str)) {
                return tooltipPosition;
            }
        }
        Log.w(TAG, String.format("Unexpected tooltip position string \"%s\", defaulting to TooltipPosition.Top", str));
        return Top;
    }
}
