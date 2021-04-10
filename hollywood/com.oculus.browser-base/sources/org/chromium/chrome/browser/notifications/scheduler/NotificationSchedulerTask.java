package org.chromium.chrome.browser.notifications.scheduler;

import J.N;
import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationSchedulerTask extends AbstractC4798sm0 {
    public static void cancel() {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 103);
    }

    public static void schedule(long j, long j2) {
        C3581lf b = AbstractC2898hf.b();
        C1111Se1 a2 = TaskInfo.a(103, j, j2);
        a2.f = true;
        a2.e = true;
        b.b(ContextUtils.getApplicationContext(), a2.a());
    }

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        return 0;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        N.Mgeg_Rc9(this, new C1198Tp0(this, ne));
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return N.M91xgL_Z(this);
    }
}
