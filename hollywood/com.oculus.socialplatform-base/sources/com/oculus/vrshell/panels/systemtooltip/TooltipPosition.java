package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;

public enum TooltipPosition {
    Top("top"),
    Bottom("bottom"),
    Left("left"),
    Right("right");
    
    public static final String TAG = "TooltipPosition";
    public final String IPCString;

    public String getIPCString() {
        return this.IPCString;
    }

    /* access modifiers changed from: public */
    TooltipPosition(String str) {
        this.IPCString = str;
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
