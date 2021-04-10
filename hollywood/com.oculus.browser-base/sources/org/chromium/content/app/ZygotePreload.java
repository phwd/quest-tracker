package org.chromium.content.app;

import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.os.SystemClock;
import org.chromium.base.JNIUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZygotePreload implements android.app.ZygotePreload {
    public void doPreload(ApplicationInfo applicationInfo) {
        try {
            int myPid = Process.myPid();
            long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            C1732ap.b = myPid;
            C1732ap.c = currentThreadTimeMillis;
            JNIUtils.f10587a = Boolean.TRUE;
            C2474f80 f80 = C2474f80.f9900a;
            synchronized (f80.j) {
                f80.g(applicationInfo, true);
                f80.h();
                f80.l = true;
            }
        } catch (Throwable th) {
            AbstractC1220Ua0.f("ZygotePreload", "Exception in zygote", th);
        }
    }
}
