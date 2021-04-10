package org.chromium.ui.gfx;

import android.provider.Settings;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Animation {
    public static boolean prefersReducedMotion() {
        return ((double) Settings.Global.getFloat(ContextUtils.getApplicationContext().getContentResolver(), "animator_duration_scale", 1.0f)) == 0.0d;
    }
}
