package X;

import android.app.job.JobParameters;

/* renamed from: X.oZ  reason: case insensitive filesystem */
public final class C0907oZ implements AbstractC0383Uv {
    public final /* synthetic */ JobParameters A00;
    public final /* synthetic */ Gz A01;

    public C0907oZ(Gz gz, JobParameters jobParameters) {
        this.A01 = gz;
        this.A00 = jobParameters;
    }

    @Override // X.AbstractC0383Uv
    public final void A49(Throwable th) {
        Gz.A00(this.A01, this.A00, th);
    }

    @Override // X.AbstractC0383Uv
    public final void A4N(Object obj) {
        C0904oW oWVar = (C0904oW) obj;
        if (oWVar == null) {
            Gz.A00(this.A01, this.A00, new IllegalStateException("papaya is null"));
            return;
        }
        Gz gz = this.A01;
        JobParameters jobParameters = this.A00;
        synchronized (gz) {
            gz.A01 = oWVar;
            gz.A03 = true;
            C1192ut.A00(AnonymousClass1O.A00(oWVar.A01.A00(), new C0902oU(oWVar), oWVar.A02), new C0908oa(gz, jobParameters), V3.INSTANCE);
        }
    }
}
