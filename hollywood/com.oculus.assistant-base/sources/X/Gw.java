package X;

import android.app.job.JobParameters;

public final class Gw implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.papaya.client.PapayaJobService$1";
    public final /* synthetic */ JobParameters A00;
    public final /* synthetic */ Gz A01;

    public Gw(Gz gz, JobParameters jobParameters) {
        this.A01 = gz;
        this.A00 = jobParameters;
    }

    public final void run() {
        Gz gz = this.A01;
        JobParameters jobParameters = this.A00;
        synchronized (gz) {
            C1192ut.A00(gz.A02(), new C0907oZ(gz, jobParameters), V3.INSTANCE);
        }
    }
}
