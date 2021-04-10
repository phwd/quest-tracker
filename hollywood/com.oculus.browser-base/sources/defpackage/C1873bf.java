package defpackage;

import android.app.job.JobParameters;
import org.chromium.base.ThreadUtils;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService;

/* renamed from: bf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1873bf implements AbstractC0804Ne {

    /* renamed from: a  reason: collision with root package name */
    public final BackgroundTaskJobService f9551a;
    public final AbstractC0865Oe b;
    public final JobParameters c;

    public C1873bf(BackgroundTaskJobService backgroundTaskJobService, AbstractC0865Oe oe, JobParameters jobParameters) {
        this.f9551a = backgroundTaskJobService;
        this.b = oe;
        this.c = jobParameters;
    }

    @Override // defpackage.AbstractC0804Ne
    public void a(boolean z) {
        ThreadUtils.h(new RunnableC1693af(this, z));
    }
}
