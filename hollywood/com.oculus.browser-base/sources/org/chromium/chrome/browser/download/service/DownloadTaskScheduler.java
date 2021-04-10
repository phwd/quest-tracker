package org.chromium.chrome.browser.download.service;

import android.os.Bundle;
import org.chromium.base.ContextUtils;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadTaskScheduler {
    public static int a(int i) {
        if (i == 0) {
            return 53;
        }
        if (i == 1) {
            return 54;
        }
        if (i != 2) {
            return i != 3 ? -1 : 57;
        }
        return 56;
    }

    public static void cancelTask(int i) {
        AbstractC2898hf.b().a(ContextUtils.getApplicationContext(), a(i));
    }

    public static void scheduleTask(int i, boolean z, boolean z2, int i2, long j, long j2) {
        int i3;
        Bundle bundle = new Bundle();
        bundle.putInt("extra_task_type", i);
        bundle.putInt("extra_optimal_battery_percentage", i2);
        bundle.putBoolean("extra_battery_requires_charging", z2);
        C3581lf b = AbstractC2898hf.b();
        C1111Se1 a2 = TaskInfo.a(a(i), j * 1000, j2 * 1000);
        int i4 = 2;
        if (i != 0) {
            if (i == 1) {
                i3 = 0;
            } else if (i != 2) {
                i3 = i != 3 ? -1 : 1;
            }
            a2.c = i3;
            a2.d = z2;
            a2.f = true;
            a2.e = true;
            a2.b = bundle;
            b.b(ContextUtils.getApplicationContext(), a2.a());
        }
        if (!z) {
            i4 = 1;
        }
        i3 = i4;
        a2.c = i3;
        a2.d = z2;
        a2.f = true;
        a2.e = true;
        a2.b = bundle;
        b.b(ContextUtils.getApplicationContext(), a2.a());
    }
}
