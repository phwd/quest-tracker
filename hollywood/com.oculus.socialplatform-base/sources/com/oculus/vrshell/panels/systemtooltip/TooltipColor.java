package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;

public enum TooltipColor {
    Black("black"),
    Gray("gray");
    
    public static final String TAG = "TooltipColor";
    public final String mIPCString;

    public String getIPCString() {
        return this.mIPCString;
    }

    /* access modifiers changed from: public */
    TooltipColor(String str) {
        this.mIPCString = str;
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
