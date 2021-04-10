package org.chromium.device.vibration;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VibrationManagerImpl implements Ps1 {
    public static long F = -1;
    public static boolean G;
    public final AudioManager H;
    public final Vibrator I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f10967J;

    public VibrationManagerImpl() {
        Context applicationContext = ContextUtils.getApplicationContext();
        this.H = (AudioManager) applicationContext.getSystemService("audio");
        this.I = (Vibrator) applicationContext.getSystemService("vibrator");
        boolean z = applicationContext.checkCallingOrSelfPermission("android.permission.VIBRATE") == 0;
        this.f10967J = z;
        if (!z) {
            AbstractC1220Ua0.f("VibrationManagerImpl", "Failed to use vibrate API, requires VIBRATE permission.", new Object[0]);
        }
    }

    public static boolean getVibrateCancelledForTesting() {
        return G;
    }

    public static long getVibrateMilliSecondsForTesting() {
        return F;
    }

    @Override // defpackage.Ps1
    public void L(long j, C1917bt1 bt1) {
        long max = Math.max(1L, Math.min(j, 10000L));
        if (this.H.getRingerMode() != 0 && this.f10967J) {
            this.I.vibrate(max);
        }
        F = max;
        bt1.a();
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
    }

    @Override // defpackage.Ps1
    public void m(Xs1 xs1) {
        if (this.f10967J) {
            this.I.cancel();
        }
        G = true;
        xs1.a();
    }
}
