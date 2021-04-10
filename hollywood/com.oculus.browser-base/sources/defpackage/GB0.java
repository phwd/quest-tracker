package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.background_sync.BackgroundSyncBackgroundTaskScheduler;

/* renamed from: GB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GB0 extends AbstractC4798sm0 {
    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        BackgroundSyncBackgroundTaskScheduler.getInstance().scheduleOneOffTask(1, 360000);
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        return C5222vE.d(context) == 6 ? 1 : 0;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        AbstractC3364kK0.i("BackgroundSync.Periodic.Wakeup.DelayTime", System.currentTimeMillis() - cf1.b.getLong("SoonestWakeupTime"));
        N.M3y91C0s(new FB0(ne));
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
