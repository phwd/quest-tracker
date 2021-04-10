package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;

public enum TooltipColor {
    Black("black"),
    Gray("gray");
    
    private static final String TAG = TooltipColor.class.getSimpleName();
    private final String mIPCString;

    private TooltipColor(String str) {
        this.mIPCString = str;
    }

    public String getIPCString() {
        return this.mIPCString;
    }

    public static TooltipColor getTooltipColor(String str) {
        TooltipColor[] values = values();
        for (TooltipColor tooltipColor : values) {
            if (tooltipColor.getIPCString().equals(str)) {
                return tooltipColor;
            }
        }
        Log.w(TAG, String.format("Unexpected tooltip color string \"%s\", defaulting to TooltipColor.Black", str));
        return Black;
    }
}
