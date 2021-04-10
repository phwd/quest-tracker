package org.chromium.device.screen_orientation;

import android.provider.Settings;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ScreenOrientationListener {
    public static boolean isAutoRotateEnabledByUser() {
        return Settings.System.getInt(ContextUtils.getApplicationContext().getContentResolver(), "accelerometer_rotation", 0) == 1;
    }
}
