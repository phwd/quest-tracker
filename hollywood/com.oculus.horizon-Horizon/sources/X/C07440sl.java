package X;

import android.app.job.JobParameters;
import android.app.job.JobWorkItem;
import android.content.Intent;

/* renamed from: X.0sl  reason: invalid class name and case insensitive filesystem */
public final class C07440sl implements AnonymousClass03U {
    public final JobWorkItem A00;
    public final /* synthetic */ AnonymousClass0sk A01;

    public C07440sl(AnonymousClass0sk r1, JobWorkItem jobWorkItem) {
        this.A01 = r1;
        this.A00 = jobWorkItem;
    }

    @Override // X.AnonymousClass03U
    public final void A1m() {
        AnonymousClass0sk r0 = this.A01;
        synchronized (r0.A02) {
            JobParameters jobParameters = r0.A00;
            if (jobParameters != null) {
                jobParameters.completeWork(this.A00);
            }
        }
    }

    @Override // X.AnonymousClass03U
    public final Intent A3f() {
        return this.A00.getIntent();
    }
}
