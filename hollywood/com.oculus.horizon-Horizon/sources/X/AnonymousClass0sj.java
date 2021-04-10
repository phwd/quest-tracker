package X;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.RequiresApi;

@RequiresApi(26)
/* renamed from: X.0sj  reason: invalid class name */
public final class AnonymousClass0sj extends AnonymousClass03V {
    public final JobInfo A00;
    public final JobScheduler A01;

    @Override // X.AnonymousClass03V
    public final void A04(Intent intent) {
        this.A01.enqueue(this.A00, new JobWorkItem(intent));
    }

    public AnonymousClass0sj(Context context, ComponentName componentName, int i) {
        super(componentName);
        A03(i);
        this.A00 = new JobInfo.Builder(i, this.A02).setOverrideDeadline(0).build();
        this.A01 = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
    }
}
