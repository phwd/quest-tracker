package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.media.MediaLauncherActivity;

/* renamed from: di0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2225di0 implements Runnable {
    public void run() {
        Context applicationContext = ContextUtils.getApplicationContext();
        PackageManager packageManager = applicationContext.getPackageManager();
        ComponentName componentName = new ComponentName(applicationContext, MediaLauncherActivity.class);
        ComponentName componentName2 = new ComponentName(applicationContext, "org.chromium.chrome.browser.media.AudioLauncherActivity");
        int i = 2;
        int i2 = AbstractC2395ei0.c() ? 1 : 2;
        boolean z = false;
        if (AbstractC2395ei0.c()) {
            if (!(SysUtils.isLowEndDevice() && Build.VERSION.SDK_INT >= 26)) {
                z = true;
            }
        }
        if (z) {
            i = 1;
        }
        if (packageManager.getComponentEnabledSetting(componentName) != i2) {
            packageManager.setComponentEnabledSetting(componentName, i2, 1);
        }
        if (packageManager.getComponentEnabledSetting(componentName2) != i) {
            packageManager.setComponentEnabledSetting(componentName2, i, 1);
        }
    }
}
