package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;

public enum TooltipColor {
    Black("black"),
    Gray("gray");
    
    private static final String TAG = TooltipColor.class.getSimpleName();
    private final String mIPCString;

    private TooltipColor(String IPCString) {
        this.mIPCString = IPCString;
    }

    public String getIPCString() {
        return this.mIPCString;
    }

    public static TooltipColor getTooltipColor(String tooltipColorString) {
        TooltipColor[] values = values();
        for (TooltipColor tooltipColor : values) {
            if (tooltipColor.getIPCString().equals(tooltipColorString)) {
                return tooltipColor;
            }
        }
        Log.w(TAG, String.format("Unexpected tooltip color string \"%s\", defaulting to TooltipColor.Black", tooltipColorString));
        return Black;
    }
}
