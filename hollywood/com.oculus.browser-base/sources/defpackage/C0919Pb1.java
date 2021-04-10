package defpackage;

import android.os.Handler;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: Pb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0919Pb1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public Handler f8700a = new HandlerC0858Ob1(this);
    public final /* synthetic */ C0980Qb1 b;

    public C0919Pb1(C0980Qb1 qb1) {
        this.b = qb1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.f8700a.removeMessages(1);
        C0980Qb1.o(this.b, false);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        this.f8700a.removeMessages(1);
        C0980Qb1.o(this.b, false);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        V();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        this.f8700a.removeMessages(1);
        C0980Qb1.o(this.b, !HG.c(gurl.h()));
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void L(Tab tab, boolean z) {
        this.b.r();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void O(Tab tab) {
        this.b.r();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        this.b.r();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        if (z) {
            V();
        }
    }

    public final void V() {
        if (this.b.L && !this.f8700a.hasMessages(1)) {
            this.f8700a.sendEmptyMessageDelayed(1, 3000);
        }
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            this.b.r();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void n(Tab tab) {
        this.b.q(tab.l());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        this.f8700a.removeCallbacksAndMessages(null);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f && navigationHandle.f10940a) {
            this.f8700a.removeMessages(1);
            this.f8700a.sendEmptyMessageDelayed(1, 3000);
        }
    }
}
