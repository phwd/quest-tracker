package org.chromium.chrome.browser.notifications;

import android.os.Bundle;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationTriggerScheduler {

    /* renamed from: a  reason: collision with root package name */
    public C2590fq0 f10704a;

    public NotificationTriggerScheduler(C2590fq0 fq0) {
        this.f10704a = fq0;
    }

    public static NotificationTriggerScheduler getInstance() {
        return AbstractC2761gq0.f10025a;
    }

    public void schedule(long j) {
        Objects.requireNonNull(this.f10704a);
        long currentTimeMillis = System.currentTimeMillis();
        PU0 pu0 = NU0.f8549a;
        long h = pu0.h("notification_trigger_scheduler.next_trigger", Long.MAX_VALUE);
        if (j < h) {
            pu0.o("notification_trigger_scheduler.next_trigger", j);
        } else if (h < currentTimeMillis) {
            j = h;
        } else {
            return;
        }
        Math.max(j - currentTimeMillis, 0L);
        Bundle bundle = new Bundle();
        bundle.putLong("Timestamp", j);
        C1172Te1 te1 = new C1172Te1();
        te1.f8972a = j;
        C1111Se1 b = TaskInfo.b(104, new C1233Ue1(te1, null));
        b.f = true;
        b.e = true;
        b.b = bundle;
        AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), b.a());
    }
}
