package org.chromium.content.browser.accessibility;

import android.os.Handler;
import android.provider.Settings;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowserAccessibilityState {
    public static void registerAnimatorDurationScaleObserver() {
        Handler handler = new Handler(ThreadUtils.c());
        ContextUtils.getApplicationContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor("animator_duration_scale"), false, new C0880Oj(handler));
    }
}
