package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Yd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1474Yd1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1872be1 f9283a;

    public C1474Yd1(C1872be1 be1) {
        this.f9283a = be1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        C1872be1 be1 = this.f9283a;
        C0090Bk bk = be1.K;
        if (bk != null) {
            bk.p(be1.P);
            be1.P = -1;
        }
        C1872be1.c(this.f9283a, false);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        if (this.f9283a.h()) {
            C1872be1 be1 = this.f9283a;
            C0090Bk bk = be1.K;
            if (bk != null && be1.P == -1) {
                be1.P = bk.q();
            }
            C1872be1.c(this.f9283a, true);
        }
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }
}
