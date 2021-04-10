package defpackage;

import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskGcmTaskService;

/* renamed from: Ue  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1231Ue implements Runnable {
    public final /* synthetic */ C2046cf1 F;
    public final /* synthetic */ AtomicBoolean G;
    public final /* synthetic */ AbstractC0865Oe H;
    public final /* synthetic */ C1475Ye I;

    public RunnableC1231Ue(BackgroundTaskGcmTaskService backgroundTaskGcmTaskService, C2046cf1 cf1, AtomicBoolean atomicBoolean, AbstractC0865Oe oe, C1475Ye ye) {
        this.F = cf1;
        this.G = atomicBoolean;
        this.H = oe;
        this.I = ye;
    }

    public void run() {
        C5116uf.f().i(this.F.f9623a);
        this.G.set(this.H.b(ContextUtils.getApplicationContext(), this.F, new C1414Xe(this.I)));
    }
}
