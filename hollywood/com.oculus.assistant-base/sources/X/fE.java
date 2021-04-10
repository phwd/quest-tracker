package X;

import android.app.job.JobParameters;
import com.facebook.analytics2.logger.LollipopUploadService;

public final class fE implements AbstractC00517z {
    public final JobParameters A00;
    public final /* synthetic */ LollipopUploadService A01;

    public fE(LollipopUploadService lollipopUploadService, JobParameters jobParameters) {
        this.A01 = lollipopUploadService;
        this.A00 = jobParameters;
    }

    @Override // X.AbstractC00517z
    public final void A4R(boolean z) {
        JobParameters jobParameters = this.A00;
        C0139Dd.A0H("PostLolliopUploadService", "onVoluntaryCompletion callback fired for jobId: %d needsReschedule: %s", Integer.valueOf(jobParameters.getJobId()), Boolean.valueOf(z));
        this.A01.jobFinished(jobParameters, z);
    }
}
