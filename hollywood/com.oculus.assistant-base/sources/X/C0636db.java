package X;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/* renamed from: X.db  reason: case insensitive filesystem */
public final class C0636db extends AnonymousClass17 {
    public final JobInfo A00 = new JobInfo.Builder(1000, this.A02).setOverrideDeadline(0).build();
    public final JobScheduler A01;

    public C0636db(Context context, ComponentName componentName) {
        super(componentName);
        A01();
        this.A01 = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
    }

    @Override // X.AnonymousClass17
    public final void A02(Intent intent) {
        this.A01.enqueue(this.A00, new JobWorkItem(intent));
    }
}
