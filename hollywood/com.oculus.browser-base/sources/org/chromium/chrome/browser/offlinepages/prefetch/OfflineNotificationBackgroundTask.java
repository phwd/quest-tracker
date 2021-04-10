package org.chromium.chrome.browser.offlinepages.prefetch;

import J.N;
import android.content.Context;
import android.os.Build;
import java.util.Calendar;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OfflineNotificationBackgroundTask extends AbstractC4798sm0 {
    public AbstractC0804Ne f;

    public static void l(int i) {
        if (!m()) {
            long j = i != 0 ? i != 1 ? -1 : 900000 : 3600000;
            PU0 pu0 = NU0.f8549a;
            long j2 = 0;
            long h = pu0.h("prefetch_notification_shown_time", 0);
            if (h > 0) {
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                if (h > timeInMillis) {
                    pu0.o("prefetch_notification_shown_time", timeInMillis);
                    h = timeInMillis;
                }
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(h);
                instance.add(5, 1);
                instance.set(11, 7);
                instance.set(12, 0);
                instance.set(13, 0);
                instance.set(14, 0);
                long timeInMillis2 = instance.getTimeInMillis();
                if (timeInMillis2 > timeInMillis) {
                    j2 = timeInMillis2 - timeInMillis;
                }
            }
            if (j < j2) {
                j = j2;
            }
            C1111Se1 a2 = TaskInfo.a(79, j, 2 * j);
            a2.e = true;
            a2.f = true;
            AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), a2.a());
        }
    }

    public static boolean m() {
        PU0 pu0 = NU0.f8549a;
        boolean z = !pu0.d("prefetch_notification_has_new_pages", false);
        if (CachedFeatureFlags.isEnabled("PrefetchNotificationSchedulingIntegration")) {
            return z;
        }
        boolean z2 = pu0.f("prefetch_notification_ignored_counter", 0) >= 3;
        boolean z3 = Build.VERSION.SDK_INT < 26 && !pu0.d("prefetch_notification_enabled", true);
        if (z || z2 || z3) {
            return true;
        }
        return false;
    }

    public static void onFreshOfflineContentAvailable() {
        PU0 pu0 = NU0.f8549a;
        pu0.m("prefetch_notification_has_new_pages", true);
        pu0.n("prefetch_notification_offline_counter", 0);
        l(0);
    }

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        if (m()) {
            k();
        } else {
            l(0);
        }
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        if (m()) {
            k();
            return 2;
        } else if (C5222vE.d(context) != 6) {
            NU0.f8549a.n("prefetch_notification_offline_counter", 0);
            l(0);
            return 2;
        } else if (NU0.f8549a.c("prefetch_notification_offline_counter") >= 4) {
            return 0;
        } else {
            l(1);
            return 2;
        }
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        this.f = ne;
        OfflinePageBridge a2 = OfflinePageBridge.a(Profile.b());
        PrefetchedPagesNotifier.b().c(0);
        N.MFVw2bHR(a2.f10718a, a2, NU0.f8549a.h("prefetch_notification_shown_time", 0), new C0410Gr0(this));
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return true;
    }

    public final void k() {
        PU0 pu0 = NU0.f8549a;
        pu0.n("prefetch_notification_offline_counter", 0);
        pu0.m("prefetch_notification_has_new_pages", false);
    }
}
