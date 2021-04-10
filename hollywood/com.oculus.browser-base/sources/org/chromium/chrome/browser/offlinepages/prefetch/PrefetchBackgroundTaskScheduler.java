package org.chromium.chrome.browser.offlinepages.prefetch;

import android.os.Bundle;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrefetchBackgroundTaskScheduler {
    public static void a(int i, boolean z) {
        C1111Se1 a2 = TaskInfo.a(78, ((z ? 20 : 900) + ((long) i)) * 1000, 604800000);
        a2.c = 2;
        a2.e = true;
        a2.f = true;
        if (z) {
            a2.c = 1;
            Bundle bundle = new Bundle(1);
            bundle.putBoolean("limitlessPrefetching", true);
            a2.b = bundle;
        }
        AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a2.a());
    }

    public static void cancelTask() {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), 78);
    }

    public static void scheduleTask(int i) {
        a(i, false);
    }

    public static void scheduleTaskLimitless(int i) {
        a(i, true);
    }
}
