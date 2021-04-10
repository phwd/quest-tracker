package com.oculus.vrshell.panels.systemtooltip;

import android.util.Log;
import com.oculus.android.exoplayer2.text.ttml.TtmlNode;

public enum TooltipPosition {
    Top("top"),
    Bottom("bottom"),
    Left(TtmlNode.LEFT),
    Right(TtmlNode.RIGHT);
    
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
