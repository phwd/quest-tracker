package X;

import android.app.job.JobParameters;

/* renamed from: X.0q2  reason: invalid class name and case insensitive filesystem */
public class C06850q2 implements AnonymousClass0HZ {
    public final C06870qB A00;

    @Override // X.AnonymousClass0HZ
    public final void A7D(boolean z) {
        C06870qB r0 = this.A00;
        JobParameters jobParameters = r0.A00;
        jobParameters.getJobId();
        r0.A01.jobFinished(jobParameters, z);
    }

    public C06850q2(C06870qB r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0HZ
    public final void A65() {
    }
}
