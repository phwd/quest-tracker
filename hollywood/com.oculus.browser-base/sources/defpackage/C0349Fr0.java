package defpackage;

import J.N;
import android.content.Context;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: Fr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0349Fr0 implements AbstractC0865Oe {
    public static void d() {
        PU0 pu0 = NU0.f8549a;
        if (((long) pu0.f("Chrome.OfflineMeasurements.CurrentTaskMeasurementIntervalInMinutes", 0)) != 0) {
            AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 108);
        }
        pu0.l("Chrome.OfflineMeasurements.LastCheckMillis");
        pu0.l("Chrome.OfflineMeasurements.CurrentTaskMeasurementIntervalInMinutes");
    }

    public static void e() {
        int M37SqSAy = N.M37SqSAy("OfflineMeasurementsBackgroundTask", "measurement_interval_in_minutes", 60);
        PU0 pu0 = NU0.f8549a;
        int f = pu0.f("Chrome.OfflineMeasurements.CurrentTaskMeasurementIntervalInMinutes", 0);
        if (f != M37SqSAy) {
            if (f != 0) {
                d();
            }
            C1416Xe1 xe1 = new C1416Xe1();
            TimeUnit timeUnit = TimeUnit.MINUTES;
            long j = (long) M37SqSAy;
            xe1.f9225a = timeUnit.toMillis(j);
            AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), TaskInfo.b(108, xe1.a()).a());
            AbstractC3364kK0.f("Offline.Measurements.MeasurementInterval", timeUnit.toMillis(j), timeUnit.toMillis(1), TimeUnit.DAYS.toMillis(1), 50);
            pu0.n("Chrome.OfflineMeasurements.CurrentTaskMeasurementIntervalInMinutes", M37SqSAy);
            pu0.o("Chrome.OfflineMeasurements.LastCheckMillis", -1);
        }
    }

    @Override // defpackage.AbstractC0865Oe
    public boolean a(Context context, C2046cf1 cf1) {
        return false;
    }

    @Override // defpackage.AbstractC0865Oe
    public boolean b(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        if (!CachedFeatureFlags.isEnabled("OfflineMeasurementsBackgroundTask")) {
            d();
            return false;
        }
        PU0 pu0 = NU0.f8549a;
        long h = pu0.h("Chrome.OfflineMeasurements.LastCheckMillis", 0);
        long currentTimeMillis = System.currentTimeMillis();
        pu0.o("Chrome.OfflineMeasurements.LastCheckMillis", currentTimeMillis);
        if (h > 0) {
            long j = currentTimeMillis - h;
            StringBuilder sb = new StringBuilder(pu0.i("Chrome.OfflineMeasurements.TimeBetweenChecksMillisList", ""));
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(j);
            pu0.p("Chrome.OfflineMeasurements.TimeBetweenChecksMillisList", sb.toString());
        }
        return false;
    }

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        e();
    }
}
