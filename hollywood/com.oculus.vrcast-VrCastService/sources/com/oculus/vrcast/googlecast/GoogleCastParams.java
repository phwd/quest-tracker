package com.oculus.vrcast.googlecast;

import android.os.SystemProperties;
import android.util.Log;

/* access modifiers changed from: package-private */
public class GoogleCastParams {
    private static final String PREFIX = "debug.oculus.gcast.";
    private static final String TAG = "GoogleCastParams";
    public int bitrate;
    public boolean disableTdls;
    public int fps;
    public int height;
    public int targetPlayoutDelay;
    public int width;

    GoogleCastParams() {
    }

    private static int getRangedInt(String str, int i, int i2, int i3) {
        int i4 = SystemProperties.getInt(str, i3);
        if (i4 < i) {
            Log.w(TAG, "Property " + str + " is below minimum value " + i + "; defaulting to " + i3);
            return i3;
        } else if (i4 <= i2) {
            return i4;
        } else {
            Log.w(TAG, "Property " + str + " is above maximum value " + i2 + "; defaulting to " + i3);
            return i3;
        }
    }

    public static GoogleCastParams loadFromSysProps() {
        GoogleCastParams googleCastParams = new GoogleCastParams();
        googleCastParams.width = getRangedInt("debug.oculus.gcast.width", 1, 4096, 1280);
        googleCastParams.height = getRangedInt("debug.oculus.gcast.height", 1, 4096, 720);
        googleCastParams.fps = getRangedInt("debug.oculus.gcast.fps", 1, 144, 36);
        googleCastParams.disableTdls = SystemProperties.getBoolean("debug.oculus.gcast.disable_tdls", false);
        googleCastParams.bitrate = getRangedInt("debug.oculus.gcast.bitrate", 1, 100000, 3000);
        googleCastParams.targetPlayoutDelay = getRangedInt("debug.oculus.gcast.latency", 1, 1000, 200);
        return googleCastParams;
    }
}
