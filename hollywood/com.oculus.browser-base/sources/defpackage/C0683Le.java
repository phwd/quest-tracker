package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.background_sync.BackgroundSyncBackgroundTaskScheduler;

/* renamed from: Le  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0683Le extends AbstractC4798sm0 {
    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        BackgroundSyncBackgroundTaskScheduler.getInstance().scheduleOneOffTask(0, 360000);
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        return C5222vE.d(context) == 6 ? 1 : 0;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        AbstractC3364kK0.i("BackgroundSync.Wakeup.DelayTime", System.currentTimeMillis() - cf1.b.getLong("SoonestWakeupTime"));
        N.MSbjZsF$(new RunnableC0622Ke(ne));
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return true;
    }
}
