package defpackage;

import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskGcmTaskService;

/* renamed from: Ve  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1292Ve implements Runnable {
    public final /* synthetic */ C2046cf1 F;
    public final /* synthetic */ AtomicBoolean G;
    public final /* synthetic */ AbstractC0865Oe H;

    public RunnableC1292Ve(BackgroundTaskGcmTaskService backgroundTaskGcmTaskService, C2046cf1 cf1, AtomicBoolean atomicBoolean, AbstractC0865Oe oe) {
        this.F = cf1;
        this.G = atomicBoolean;
        this.H = oe;
    }

    public void run() {
        C5116uf.f().j(this.F.f9623a);
        this.G.set(this.H.a(ContextUtils.getApplicationContext(), this.F));
    }
}
