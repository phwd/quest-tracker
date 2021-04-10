package X;

import android.app.job.JobParameters;
import android.app.job.JobWorkItem;
import android.content.Intent;

/* renamed from: X.dZ  reason: case insensitive filesystem */
public final class C0634dZ implements AnonymousClass16 {
    public final JobWorkItem A00;
    public final /* synthetic */ job.JobServiceEngineC0635da A01;

    public C0634dZ(job.JobServiceEngineC0635da daVar, JobWorkItem jobWorkItem) {
        this.A01 = daVar;
        this.A00 = jobWorkItem;
    }

    @Override // X.AnonymousClass16
    public final void A1S() {
        job.JobServiceEngineC0635da daVar = this.A01;
        synchronized (daVar.A02) {
            JobParameters jobParameters = daVar.A00;
            if (jobParameters != null) {
                jobParameters.completeWork(this.A00);
            }
        }
    }

    @Override // X.AnonymousClass16
    public final Intent A2V() {
        return this.A00.getIntent();
    }
}
