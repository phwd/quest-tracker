package defpackage;

import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Nt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0839Nt implements Runnable {
    public final AbstractActivityC2601fu F;

    public RunnableC0839Nt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    public void run() {
        C5285ve1 ve1 = (C5285ve1) this.F.b1;
        Tab tab = ve1.F.W0.H;
        if (tab != null && tab.l() != null && tab.isUserInteractable()) {
            C2240dn0 dn0 = new C2240dn0(ve1.F.getWindow().getDecorView().findViewById(16908290), ve1.F, new C2897he1(ve1), Profile.a(tab.l()));
            ve1.M0 = dn0;
            dn0.S = new C5455we1(tab, new C3068ie1(ve1), ve1.F.getResources().getString(R.string.f61810_resource_name_obfuscated_RES_2131953498));
            if (!ve1.M0.d(false, true)) {
                ve1.M0 = null;
            } else {
                ve1.a0.j(new C4435qe1(ve1));
            }
        }
    }
}
