package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.QC;
import android.app.job.JobScheduler;
import android.content.Context;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class LowBatteryCheckScheduler {
    public static final long BACK_OFF_INTERVAL;
    public static final long INITIAL_MIN_LATENCY;
    public static final String TAG = "LowBatteryCheckScheduler";
    public static volatile LowBatteryCheckScheduler _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_LowBatteryCheckScheduler_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;

    @Nullable
    public static JobScheduler A00(LowBatteryCheckScheduler lowBatteryCheckScheduler) {
        return (JobScheduler) ((Context) AbstractC0096Hu.A03(0, 3, lowBatteryCheckScheduler._UL_mInjectionContext)).getSystemService("jobscheduler");
    }

    static {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        BACK_OFF_INTERVAL = timeUnit.toMillis(10);
        INITIAL_MIN_LATENCY = timeUnit.toMillis(30);
    }

    @Inject
    public LowBatteryCheckScheduler(AbstractC0247Xu xu) {
        this._UL_mInjectionContext = new QC(1, xu);
    }
}
