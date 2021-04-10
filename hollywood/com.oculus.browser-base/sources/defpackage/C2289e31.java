package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.url.GURL;

/* renamed from: e31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2289e31 extends AbstractC1099Sa1 {
    public final /* synthetic */ C2631g31 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2289e31(C2631g31 g31, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.I = g31;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void E(Tab tab, boolean z) {
        C3998o31 f = this.I.f(((TabImpl) tab).H).f(tab.getId());
        if (f != null) {
            Y91 y91 = f.g;
            if (!y91.d) {
                y91.d = true;
                y91.c.a();
            }
            y91.f9258a.removeCallbacks(y91.f);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void F(Tab tab, boolean z) {
        C3998o31 f = this.I.f(((TabImpl) tab).H).f(tab.getId());
        if (f != null) {
            Y91 y91 = f.g;
            if (y91.d) {
                y91.f9258a.removeCallbacks(y91.f);
                y91.f9258a.postDelayed(y91.f, 100);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void I(Tab tab, int i) {
        this.I.f(((TabImpl) tab).H).t(tab.getId());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        this.I.f(tab.a()).t(tab.getId());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        C3998o31 f = this.I.f(tab.a()).f(tab.getId());
        if (f != null) {
            Y91 y91 = f.g;
            if (!y91.e) {
                y91.e = true;
                y91.c.a();
            }
            y91.f9258a.removeCallbacks(y91.g);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void Q(Tab tab) {
        C2631g31.b(this.I, tab);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.I.f(tab.a()).t(tab.getId());
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void x(Tab tab, Bitmap bitmap) {
        C2631g31.b(this.I, tab);
    }
}
