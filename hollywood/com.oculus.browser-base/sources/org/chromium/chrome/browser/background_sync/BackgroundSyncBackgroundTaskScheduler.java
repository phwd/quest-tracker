package org.chromium.chrome.browser.background_sync;

import android.os.Bundle;
import java.util.Iterator;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BackgroundSyncBackgroundTaskScheduler {

    /* renamed from: a  reason: collision with root package name */
    public static BackgroundSyncBackgroundTaskScheduler f10616a;
    public final C1322Vq0 b = new C1322Vq0();

    public static BackgroundSyncBackgroundTaskScheduler getInstance() {
        if (f10616a == null) {
            f10616a = new BackgroundSyncBackgroundTaskScheduler();
        }
        return f10616a;
    }

    public void cancelOneOffTask(int i) {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), i != 0 ? i != 1 ? -1 : 105 : 102);
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0743Me) uq0.next()).b(i);
            } else {
                return;
            }
        }
    }

    public boolean scheduleOneOffTask(int i, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("SoonestWakeupTime", System.currentTimeMillis() + j);
        C1294Ve1 ve1 = new C1294Ve1();
        ve1.f9097a = j;
        ve1.c = true;
        ve1.b = Long.MAX_VALUE;
        ve1.d = true;
        C1111Se1 b2 = TaskInfo.b(i != 0 ? i != 1 ? -1 : 105 : 102, ve1.a());
        b2.c = 1;
        b2.f = true;
        b2.e = true;
        b2.b = bundle;
        boolean b3 = AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), b2.a());
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (!uq0.hasNext()) {
                return b3;
            }
            ((AbstractC0743Me) uq0.next()).a(i, j);
        }
    }
}
