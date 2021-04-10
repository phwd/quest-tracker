package defpackage;

import android.app.job.JobInfo;
import android.os.PersistableBundle;

/* renamed from: of  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4094of implements AbstractC1538Ze1 {

    /* renamed from: a  reason: collision with root package name */
    public final JobInfo.Builder f10567a;
    public final PersistableBundle b;

    public C4094of(JobInfo.Builder builder, PersistableBundle persistableBundle) {
        this.f10567a = builder;
        this.b = persistableBundle;
    }

    @Override // defpackage.AbstractC1538Ze1
    public void a(C1477Ye1 ye1) {
        if (ye1.d) {
            this.b.putLong("_background_task_schedule_time", System.currentTimeMillis());
            this.b.putLong("_background_task_interval_time", ye1.f9286a);
            if (ye1.c) {
                this.b.putLong("_background_task_flex_time", ye1.b);
            }
        }
        this.f10567a.setExtras(this.b);
        if (ye1.c) {
            this.f10567a.setPeriodic(ye1.f9286a, ye1.b);
        } else {
            this.f10567a.setPeriodic(ye1.f9286a);
        }
    }

    @Override // defpackage.AbstractC1538Ze1
    public void b(C1355We1 we1) {
        if (we1.d) {
            this.b.putLong("_background_task_schedule_time", System.currentTimeMillis());
            this.b.putLong("_background_task_end_time", we1.b);
        }
        this.f10567a.setExtras(this.b);
        if (we1.c) {
            this.f10567a.setMinimumLatency(we1.f9162a);
        }
        long j = we1.b;
        if (we1.d) {
            j += 1000;
        }
        this.f10567a.setOverrideDeadline(j);
    }

    @Override // defpackage.AbstractC1538Ze1
    public void c(C1233Ue1 ue1) {
        throw new RuntimeException("Exact tasks should not be scheduled with JobScheduler.");
    }
}
