package defpackage;

import android.app.ActivityManager;
import android.os.SystemClock;

/* renamed from: ii0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3078ii0 implements Q31 {
    @Override // defpackage.Q31
    public Object get() {
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        try {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            AbstractC3364kK0.e("Android.MemoryPressureMonitor.GetMyMemoryState.Succeeded.Time", C3762mi0.a(elapsedRealtimeNanos), 1, 1000000, 50);
            return C3762mi0.b(runningAppProcessInfo.lastTrimLevel);
        } catch (Exception unused) {
            AbstractC3364kK0.e("Android.MemoryPressureMonitor.GetMyMemoryState.Failed.Time", C3762mi0.a(elapsedRealtimeNanos), 1, 1000000, 50);
            return null;
        }
    }
}
