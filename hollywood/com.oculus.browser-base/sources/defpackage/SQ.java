package defpackage;

import android.content.Intent;
import android.os.Build;

/* renamed from: SQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class SQ {
    public static boolean a(boolean z, Intent intent) {
        boolean z2 = false;
        if (intent.getBooleanExtra("Extra.FreChromeLaunchIntentIsCct", false) || Z60.e(intent)) {
            z2 = true;
        }
        return b(z, z2);
    }

    public static boolean b(boolean z, boolean z2) {
        if (!AbstractC1575Zv.e().g("disable-fre") && !AbstractC3153j7.f()) {
            if ((Build.VERSION.SDK_INT >= 29 ? C2983i7.b() : false) || YQ.a()) {
                return false;
            }
            PU0 pu0 = NU0.f8549a;
            if (pu0.d("Chrome.FirstRun.SkippedByPolicy", false) && (z2 || z)) {
                return false;
            }
            if (!z) {
                return true;
            }
            if (pu0.d("skip_welcome_page", false) || pu0.d("lightweight_first_run_flow", false)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
