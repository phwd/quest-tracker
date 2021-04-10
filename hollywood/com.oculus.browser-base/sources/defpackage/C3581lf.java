package defpackage;

import android.content.Context;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* renamed from: lf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3581lf {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2385ef f10361a;
    public final AbstractC2385ef b;

    public C3581lf(AbstractC2385ef efVar, AbstractC2385ef efVar2) {
        this.f10361a = efVar;
        this.b = efVar2;
    }

    public void a(Context context, int i) {
        TraceEvent k0 = TraceEvent.k0("BackgroundTaskScheduler.cancel", Integer.toString(i));
        try {
            Object obj = ThreadUtils.f10596a;
            C5116uf.f().c("Android.BackgroundTaskScheduler.TaskCanceled", AbstractC2556ff.b(i));
            TO0 b2 = AbstractC4776sf.b(i);
            AbstractC4776sf.e(i);
            if (b2 == null) {
                AbstractC1220Ua0.a("BkgrdTaskScheduler", "Task cannot be canceled because no data was found instorage or data was invalid", new Object[0]);
                if (k0 != null) {
                    k0.close();
                    return;
                }
                return;
            }
            if (b2.m() == 3) {
                this.b.a(context, i);
            } else {
                this.f10361a.a(context, i);
            }
            if (k0 != null) {
                k0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public boolean b(Context context, TaskInfo taskInfo) {
        if (AbstractC1575Zv.e().g("ignore-background-tasks")) {
            return true;
        }
        TraceEvent k0 = TraceEvent.k0("BackgroundTaskScheduler.schedule", Integer.toString(taskInfo.f10811a));
        try {
            Object obj = ThreadUtils.f10596a;
            C3410kf kfVar = new C3410kf(this, context, taskInfo);
            taskInfo.g.a(kfVar);
            boolean z = kfVar.c;
            C5116uf f = C5116uf.f();
            int i = taskInfo.f10811a;
            if (z) {
                f.c("Android.BackgroundTaskScheduler.TaskScheduled.Success", AbstractC2556ff.b(i));
            } else {
                f.c("Android.BackgroundTaskScheduler.TaskScheduled.Failure", AbstractC2556ff.b(i));
            }
            taskInfo.g.a(new C3239jf(this, taskInfo.f10811a));
            if (z) {
                AbstractC4776sf.a(taskInfo);
            }
            if (k0 != null) {
                k0.close();
            }
            return z;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
