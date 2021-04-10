package X;

import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.facebook.analytics2.logger.LollipopUploadService;

public final class fD extends AnonymousClass7u {
    public final JobScheduler A00;
    public final ComponentName A01;
    public final Context A02;

    public fD(Context context) {
        this.A02 = context;
        this.A00 = (JobScheduler) context.getSystemService("jobscheduler");
        this.A01 = new ComponentName(context, LollipopUploadService.class);
    }
}
