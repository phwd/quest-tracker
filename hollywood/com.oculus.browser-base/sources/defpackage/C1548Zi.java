package defpackage;

import org.chromium.chrome.features.start_surface.BottomBarView;

/* renamed from: Zi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1548Zi implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        BottomBarView bottomBarView = (BottomBarView) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = N01.f8519a;
        if (th0 == kh0) {
            bottomBarView.I = (H01) uh0.g(th0);
            return;
        }
        SH0 sh0 = N01.c;
        if (sh0 == kh0) {
            int f = uh0.f(sh0);
            if (f != bottomBarView.F.h()) {
                bottomBarView.F.i(f).b();
                return;
            }
            return;
        }
        QH0 qh0 = N01.d;
        boolean z = true;
        int i = 4;
        if (qh0 == kh0) {
            if (!uh0.h(N01.g) || !uh0.h(qh0)) {
                z = false;
            }
            if (z) {
                i = 0;
            }
            bottomBarView.setVisibility(i);
            return;
        }
        QH0 qh02 = N01.g;
        if (qh02 == kh0) {
            if (!uh0.h(qh02) || !uh0.h(qh0)) {
                z = false;
            }
            if (z) {
                i = 0;
            }
            bottomBarView.setVisibility(i);
        }
    }
}
