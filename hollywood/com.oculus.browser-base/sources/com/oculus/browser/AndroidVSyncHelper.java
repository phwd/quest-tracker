package com.oculus.browser;

import android.view.WindowManager;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AndroidVSyncHelper {
    public AndroidVSyncHelper(long j) {
    }

    public static AndroidVSyncHelper create(long j) {
        return new AndroidVSyncHelper(j);
    }

    public final float getRefreshRate() {
        return ((WindowManager) ContextUtils.getApplicationContext().getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }
}
