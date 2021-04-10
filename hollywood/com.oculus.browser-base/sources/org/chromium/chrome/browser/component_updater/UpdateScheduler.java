package org.chromium.chrome.browser.component_updater;

import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UpdateScheduler {

    /* renamed from: a  reason: collision with root package name */
    public static UpdateScheduler f10631a;
    public AbstractC0804Ne b;
    public long c;
    public long d;

    public static UpdateScheduler getInstance() {
        if (f10631a == null) {
            f10631a = new UpdateScheduler();
        }
        return f10631a;
    }

    public static boolean isAvailable() {
        return true;
    }

    public final void a(long j) {
        if (this.b == null) {
            C1111Se1 a2 = TaskInfo.a(2, j, 2147483647L);
            a2.f = true;
            a2.c = 2;
            a2.e = true;
            AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a2.a());
        }
    }

    public final void cancelTask() {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 2);
    }

    public final void finishTask(boolean z) {
        this.b.a(false);
        this.b = null;
        if (z) {
            a(this.d);
        }
    }

    public final void schedule(long j, long j2) {
        this.d = j2;
        a(j);
    }

    public final void setNativeScheduler(long j) {
        this.c = j;
    }
}
