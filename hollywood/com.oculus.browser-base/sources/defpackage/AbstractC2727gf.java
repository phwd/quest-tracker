package defpackage;

import org.chromium.base.TraceEvent;

/* renamed from: gf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2727gf {
    public static void a() {
        TraceEvent j0 = TraceEvent.j0("BackgroundTaskSchedulerPrefs.warmUpSharedPrefs");
        try {
            AbstractC4776sf.c();
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
