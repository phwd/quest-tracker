package com.oculus.os.logcollector;

final class Screenshooter {
    public static final int ROTATION_FREEZE_0 = 0;
    public static final int ROTATION_FREEZE_180 = 2;
    public static final int ROTATION_FREEZE_270 = 3;
    public static final int ROTATION_FREEZE_90 = 1;
    private static final String TAG = "Screenshooter";

    private static float getDegreesForRotation(int i) {
        if (i == 1) {
            return 270.0f;
        }
        if (i != 2) {
            return i != 3 ? 0.0f : 90.0f;
        }
        return 180.0f;
    }

    Screenshooter() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Bitmap takeScreenshot() {
        /*
        // Method dump skipped, instructions count: 196
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.logcollector.Screenshooter.takeScreenshot():android.graphics.Bitmap");
    }
}
