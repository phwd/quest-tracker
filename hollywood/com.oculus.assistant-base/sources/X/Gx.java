package X;

import android.app.job.JobParameters;

public final class Gx implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.papaya.client.PapayaJobService$4";
    public final /* synthetic */ JobParameters A00;
    public final /* synthetic */ Gz A01;

    public Gx(Gz gz, JobParameters jobParameters) {
        this.A01 = gz;
        this.A00 = jobParameters;
    }

    public final void run() {
        this.A01.jobFinished(this.A00, true);
    }
}
