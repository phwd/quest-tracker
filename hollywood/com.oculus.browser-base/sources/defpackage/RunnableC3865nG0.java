package defpackage;

import J.N;
import java.util.concurrent.TimeUnit;

/* renamed from: nG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3865nG0 implements Runnable {
    public void run() {
        long[] jArr;
        String i = NU0.f8549a.i("Chrome.OfflineMeasurements.TimeBetweenChecksMillisList", "");
        if (i.equals("")) {
            jArr = new long[0];
        } else {
            String[] split = i.split(",");
            long[] jArr2 = new long[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                try {
                    jArr2[i2] = Long.parseLong(split[i2]);
                } catch (NumberFormatException unused) {
                    jArr2[i2] = -1;
                }
            }
            jArr = jArr2;
        }
        for (long j : jArr) {
            if (j >= 0) {
                AbstractC3364kK0.f("Offline.Measurements.TimeBetweenChecks", j, TimeUnit.MINUTES.toMillis(1), TimeUnit.DAYS.toMillis(1), 50);
            }
        }
        NU0.f8549a.l("Chrome.OfflineMeasurements.TimeBetweenChecksMillisList");
        if (N.M09VlOh_("OfflineMeasurementsBackgroundTask")) {
            C0349Fr0.e();
        } else {
            C0349Fr0.d();
        }
    }
}
