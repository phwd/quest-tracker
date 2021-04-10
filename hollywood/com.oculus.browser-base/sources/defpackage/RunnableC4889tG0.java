package defpackage;

import android.content.SharedPreferences;
import android.os.Build;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

/* renamed from: tG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4889tG0 implements Runnable {
    public void run() {
        C3581lf b = AbstractC2898hf.b();
        ContextUtils.getApplicationContext();
        Objects.requireNonNull(b);
        TraceEvent j0 = TraceEvent.j0("BackgroundTaskScheduler.checkForOSUpgrade");
        try {
            Object obj = ThreadUtils.f10596a;
            TraceEvent j02 = TraceEvent.j0("BackgroundTaskSchedulerPrefs.getLastSdkVersion");
            try {
                SharedPreferences sharedPreferences = AbstractC3983nz.f10523a;
                int i = Build.VERSION.SDK_INT;
                int i2 = sharedPreferences.getInt("bts_last_sdk_version", i);
                if (j02 != null) {
                    j02.close();
                }
                AbstractC4776sf.d();
                if (i2 != i) {
                    TraceEvent k0 = TraceEvent.k0("BackgroundTaskSchedulerPrefs.setLastSdkVersion", Integer.toString(i));
                    try {
                        AbstractC3983nz.f10523a.edit().putInt("bts_last_sdk_version", i).apply();
                        if (k0 != null) {
                            k0.close();
                        }
                    } catch (Throwable th) {
                        AbstractC0754Mh1.f8495a.a(th, th);
                    }
                }
                C5116uf.f().d();
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th2) {
                AbstractC0754Mh1.f8495a.a(th, th2);
            }
            throw th;
            throw th;
            throw th;
        } catch (Throwable th3) {
            AbstractC0754Mh1.f8495a.a(th, th3);
        }
    }
}
