package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: Ha1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0429Ha1 extends AbstractC1099Sa1 {
    public final /* synthetic */ C0551Ja1 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0429Ha1(C0551Ja1 ja1, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = ja1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void F(Tab tab, boolean z) {
        C0551Ja1 ja1 = this.I;
        Objects.requireNonNull(ja1);
        C4766sb1 sb1 = ja1.m;
        sb1.d(tab);
        sb1.t();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void H(Tab tab) {
        C4766sb1 sb1 = this.I.m;
        sb1.d(tab);
        sb1.t();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        tab.getId();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        tab.getId();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        String s = tab.s();
        TabContentManager tabContentManager = this.I.s;
        int id = tab.getId();
        long j = tabContentManager.g;
        if (j != 0) {
            N.MO5IR90z(j, tabContentManager, id, s);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        if (this.I.m(tab.getId()) == this.I.i()) {
            this.I.s.g(tab.getId(), ((TabImpl) tab).s());
        }
    }

    @Override // defpackage.AbstractC1099Sa1, defpackage.AbstractC5553xB
    public void f(Tab tab, int i) {
        C4766sb1 sb1 = this.I.m;
        sb1.d(tab);
        sb1.t();
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            C0551Ja1 ja1 = this.I;
            if (!ja1.j) {
                ja1.l(tab.a()).u(tab);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void l(Tab tab) {
        this.I.h(tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        if (C3372kO0.W(tab)) {
            this.I.s.j(tab.getId());
        }
        tab.getId();
    }
}
