package defpackage;

import android.text.TextUtils;
import org.chromium.chrome.browser.previews.PreviewsAndroidBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Kk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Kk1 extends Y2 {
    public final /* synthetic */ Uk1 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Kk1(Uk1 uk1, C1595a3 a3Var) {
        super(a3Var);
        this.d = uk1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void E(Tab tab, boolean z) {
        if (z) {
            this.d.r();
            Uk1.i(this.d, true);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void F(Tab tab, boolean z) {
        if (z) {
            Uk1.i(this.d, true);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void G(Tab tab, LoadUrlParams loadUrlParams, int i) {
        Uk1.f(this.d);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void H(Tab tab) {
        if (tab == this.d.Z.d()) {
            this.d.r();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void O(Tab tab) {
        if (this.d.Z.d() != null) {
            this.d.Z.z();
            this.d.Z.B();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        if (!TextUtils.isEmpty(tab.s())) {
            this.d.L.f10790J.I = true;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void Q(Tab tab) {
        this.d.Z.A();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        Uk1.i(this.d, true);
        this.d.L.f10790J.I = true;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        if (z) {
            this.d.Z.B();
            this.d.Z.z();
        }
    }

    @Override // defpackage.Y2
    public void V(Tab tab, boolean z) {
        this.d.T.m(tab);
        if (tab == null) {
            this.d.Z.B();
            return;
        }
        this.d.o(tab);
        this.d.n();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        Uk1 uk1 = this.d;
        if (uk1.Z.r()) {
            uk1.Z.d().Q();
        }
        this.d.K.f9104a.A();
        Tab d2 = this.d.Z.d();
        if (d2 != null) {
            d2.Q();
        }
        this.d.Z.z();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        Uk1.i(this.d, false);
        this.d.r();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        NavigationController f;
        NavigationEntry s;
        if (navigationHandle.f && navigationHandle.f10940a && !navigationHandle.c) {
            this.d.K.f9104a.x();
        }
        if (navigationHandle.f && AbstractC5566xF0.a(tab)) {
            this.d.Z.z();
            String b = PreviewsAndroidBridge.a().b(tab.l());
            if (navigationHandle.f10940a) {
                AbstractC5736yF0.a(b, 2);
            }
        }
        if (navigationHandle.j != 0 && navigationHandle.f10940a) {
            WebContents l = tab.l();
            boolean z = false;
            if (!(l == null || (f = l.f()) == null || (s = f.s()) == null)) {
                z = !AbstractC5154ur1.h(s.b);
            }
            if (!z) {
                Uk1.f(this.d);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a && ((TabImpl) tab).L != null) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.L.f() != null && tabImpl.L.f().x()) {
                this.d.Z.B();
            }
        }
    }
}
