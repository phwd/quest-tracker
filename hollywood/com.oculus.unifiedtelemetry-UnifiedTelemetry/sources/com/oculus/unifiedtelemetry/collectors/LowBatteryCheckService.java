package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.QC;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import java.util.Calendar;

public class LowBatteryCheckService extends JobService {
    public static final String EVENT_NAME_LOW_CHARGE = "oculus_mobile_power_low_charge";
    public static final String TAG = "LowBatteryCheckService";
    public QC _UL_mInjectionContext;
    public final Calendar mCalendar = Calendar.getInstance();

    public final boolean onStartJob(final JobParameters jobParameters) {
        ((Handler) AbstractC0096Hu.A03(1, 90, this._UL_mInjectionContext)).post(new Runnable() {
            /* class com.oculus.unifiedtelemetry.collectors.LowBatteryCheckService.AnonymousClass1 */

            /* JADX WARNING: Code restructure failed: missing block: B:22:0x009b, code lost:
                if (r1 > 22) goto L_0x009d;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e1, code lost:
                if (r11 != false) goto L_0x00d7;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:7:0x002f, code lost:
                if (r1 == 2) goto L_0x0031;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void run() {
                /*
                // Method dump skipped, instructions count: 244
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.LowBatteryCheckService.AnonymousClass1.run():void");
            }
        });
        return true;
    }

    public final void onCreate() {
        super.onCreate();
        this._UL_mInjectionContext = new QC(4, AbstractC0096Hu.get(this));
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
