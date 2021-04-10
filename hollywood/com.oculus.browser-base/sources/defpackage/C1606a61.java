package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: a61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1606a61 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1786b61 f9409a;

    public C1606a61(C1786b61 b61) {
        this.f9409a = b61;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void B(Tab tab, String str) {
        this.f9409a.m();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        V(tab, true);
    }

    public final void V(Tab tab, boolean z) {
        if (this.f9409a.c() == 1 && z && C1957c61.V(tab).G == 0) {
            this.f9409a.h(1, false);
        } else {
            this.f9409a.k();
        }
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            this.f9409a.m();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        tab.I(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a) {
            V(tab, navigationHandle.f);
        }
    }
}
