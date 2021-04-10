package defpackage;

import J.N;
import android.content.Context;
import java.util.Objects;
import org.chromium.chrome.browser.notifications.NotificationTriggerScheduler;

/* renamed from: eq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2419eq0 extends AbstractC4798sm0 {
    public boolean f = true;

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        NotificationTriggerScheduler instance = NotificationTriggerScheduler.getInstance();
        Objects.requireNonNull(instance.f10704a);
        instance.schedule(System.currentTimeMillis() + 540000);
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        boolean z;
        NotificationTriggerScheduler instance = NotificationTriggerScheduler.getInstance();
        long j = cf1.b.getLong("Timestamp");
        Objects.requireNonNull(instance);
        PU0 pu0 = NU0.f8549a;
        if (pu0.h("notification_trigger_scheduler.next_trigger", Long.MAX_VALUE) != j) {
            z = false;
        } else {
            pu0.l("notification_trigger_scheduler.next_trigger");
            z = true;
        }
        this.f = z;
        if (z) {
            return 0;
        }
        return 2;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        Objects.requireNonNull(NotificationTriggerScheduler.getInstance());
        N.M2E1scwJ();
        this.f = false;
        ne.a(false);
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return this.f;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return this.f;
    }
}
