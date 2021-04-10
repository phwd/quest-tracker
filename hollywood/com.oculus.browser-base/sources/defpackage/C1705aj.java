package defpackage;

import org.chromium.chrome.features.start_surface.BottomBarView;

/* renamed from: aj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1705aj implements AbstractC5716y81 {
    public final /* synthetic */ BottomBarView F;

    public C1705aj(BottomBarView bottomBarView) {
        this.F = bottomBarView;
    }

    @Override // defpackage.AbstractC5546x81
    public void a(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void c(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void f(D81 d81) {
        BottomBarView bottomBarView = this.F;
        H01 h01 = bottomBarView.I;
        if (h01 != null) {
            if (d81 == bottomBarView.G) {
                h01.f8129a.i(false);
                h01.f8129a.g();
                AbstractC3535lK0.a("StartSurface.TwoPanes.BottomBar.TapHome");
            } else if (d81 == bottomBarView.H) {
                h01.f8129a.i(true);
                h01.f8129a.g();
                AbstractC3535lK0.a("StartSurface.TwoPanes.BottomBar.TapExploreSurface");
            }
        }
    }
}
