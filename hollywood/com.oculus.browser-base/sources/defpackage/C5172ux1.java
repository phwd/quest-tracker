package defpackage;

import J.N;
import android.content.SharedPreferences;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: ux1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5172ux1 implements AbstractC1678aa {

    /* renamed from: a  reason: collision with root package name */
    public static C5172ux1 f11449a;
    public AbstractC3441kp0 b;
    public OU0 c;

    public C5172ux1() {
        b(c());
        int stateForApplication = ApplicationStatus.getStateForApplication();
        if (stateForApplication == 1 || stateForApplication == 2) {
            d();
        }
        ApplicationStatus.h.b(this);
    }

    public static void b(boolean z) {
        N.Mf2ABpoH(Wr1.a(Profile.b()).f10883a, "webkit.webprefs.force_dark_mode_enabled", z);
    }

    public static boolean c() {
        if (!IV.a().c() || !NU0.f8549a.d("darken_websites_enabled", false)) {
            return false;
        }
        return true;
    }

    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        if (i == 1) {
            d();
        } else if (i == 3 && this.b != null) {
            IV.a().d(this.b);
            PU0 pu0 = NU0.f8549a;
            SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = (SharedPreferences.OnSharedPreferenceChangeListener) pu0.b.get(this.c);
            if (onSharedPreferenceChangeListener != null) {
                AbstractC3983nz.f10523a.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
            }
            this.b = null;
            this.c = null;
        }
    }

    public final void d() {
        if (this.b == null) {
            this.b = new C4832sx1();
            this.c = new C5002tx1();
            b(c());
            IV.a().b(this.b);
            NU0.f8549a.a(this.c);
        }
    }
}
