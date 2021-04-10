package org.chromium.components.background_task_scheduler.internal;

import android.app.job.JobParameters;
import android.app.job.JobService;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BackgroundTaskJobService extends JobService {
    public AbstractC3923nf F = new C1536Ze();
    public final Map G = new HashMap();

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onStartJob(android.app.job.JobParameters r13) {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService.onStartJob(android.app.job.JobParameters):boolean");
    }

    public boolean onStopJob(JobParameters jobParameters) {
        Object obj = ThreadUtils.f10596a;
        if (!this.G.containsKey(Integer.valueOf(jobParameters.getJobId()))) {
            StringBuilder i = AbstractC2531fV.i("Failed to stop job, because job with job id ");
            i.append(jobParameters.getJobId());
            i.append(" does not exist.");
            AbstractC1220Ua0.f("BkgrdTaskJS", i.toString(), new Object[0]);
            return false;
        }
        C2046cf1 c = C4265pf.c(jobParameters);
        C5116uf.f().j(c.f9623a);
        boolean a2 = ((AbstractC0865Oe) this.G.get(Integer.valueOf(jobParameters.getJobId()))).a(ContextUtils.getApplicationContext(), c);
        this.G.remove(Integer.valueOf(jobParameters.getJobId()));
        return a2;
    }
}
