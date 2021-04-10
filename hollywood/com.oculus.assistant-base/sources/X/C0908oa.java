package X;

import android.app.job.JobParameters;

/* renamed from: X.oa  reason: case insensitive filesystem */
public final class C0908oa implements AbstractC0383Uv {
    public final /* synthetic */ JobParameters A00;
    public final /* synthetic */ Gz A01;

    @Override // X.AbstractC0383Uv
    public final /* bridge */ /* synthetic */ void A4N(Object obj) {
        Gz gz = this.A01;
        Gz.A00(gz, this.A00, null);
        synchronized (gz) {
            gz.A03 = false;
        }
    }

    public C0908oa(Gz gz, JobParameters jobParameters) {
        this.A01 = gz;
        this.A00 = jobParameters;
    }

    @Override // X.AbstractC0383Uv
    public final void A49(Throwable th) {
        Gz gz = this.A01;
        Gz.A00(gz, this.A00, th);
        synchronized (gz) {
            gz.A03 = false;
        }
    }
}
