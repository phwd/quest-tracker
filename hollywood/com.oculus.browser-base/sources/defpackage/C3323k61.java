package defpackage;

import J.N;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: k61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3323k61 extends AbstractC1099Sa1 {
    public final TabContentManager I;

    /* renamed from: J  reason: collision with root package name */
    public final UT f10262J;
    public final TT K;
    public boolean L;
    public Tab M;

    public C3323k61(TabContentManager tabContentManager, UT ut, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = tabContentManager;
        this.f10262J = ut;
        C3152j61 j61 = new C3152j61(this);
        this.K = j61;
        ut.b(j61);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void C(Tab tab, boolean z) {
        Tab tab2;
        if (z && this.L && (tab2 = this.M) != null) {
            this.I.j(tab2.getId());
            this.L = false;
            this.M = null;
        }
    }

    @Override // defpackage.AbstractC1099Sa1
    public void V(Tab tab) {
        TabContentManager tabContentManager = this.I;
        long j = tabContentManager.g;
        if (j != 0) {
            N.MRvrWXuI(j, tabContentManager, tab, tab.getId());
        }
    }

    @Override // defpackage.AbstractC1099Sa1
    public void W(Tab tab) {
        TabContentManager tabContentManager = this.I;
        long j = tabContentManager.g;
        if (j != 0) {
            N.M6M_espa(j, tabContentManager, tab, tab.getId());
        }
    }

    @Override // defpackage.AbstractC1099Sa1
    public void destroy() {
        super.destroy();
        this.f10262J.f(this.K);
    }
}
