package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* renamed from: p91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4187p91 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I91 f11053a;

    public C4187p91(I91 i91) {
        this.f11053a = i91;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void Q(Tab tab) {
        int x = this.f11053a.g.x(tab.getId());
        if (x != -1) {
            if (((AbstractC0246Ea1) this.f11053a.i).o(tab.getId()) != null) {
                ((C4765sb0) this.f11053a.g.get(x)).b.m(AbstractC5106ub1.g, this.f11053a.f(C4384qI0.a(tab)));
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        if (AbstractC4772sd1.f()) {
            int x = this.f11053a.g.x(tab.getId());
            if (x == -1) {
                I91 i91 = this.f11053a;
                if (i91.s) {
                    Tab a2 = AbstractC3842n81.a(i91.i, tab);
                    if (a2 != null) {
                        x = this.f11053a.g.x(a2.getId());
                    } else {
                        return;
                    }
                }
            }
            if (x != -1) {
                ((C4765sb0) this.f11053a.g.get(x)).b.m(AbstractC5106ub1.s, this.f11053a.d(tab));
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        TabImpl tabImpl = (TabImpl) tab;
        if (!AbstractC5154ur1.g(tabImpl.s()) && !navigationHandle.c && navigationHandle.f10940a && this.f11053a.g.x(tabImpl.getId()) != -1) {
            K91 k91 = this.f11053a.g;
            ((C4765sb0) k91.get(k91.x(tabImpl.getId()))).b.m(AbstractC5106ub1.d, this.f11053a.p.a(tabImpl.H));
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void x(Tab tab, Bitmap bitmap) {
        this.f11053a.l(C4384qI0.a(tab), bitmap);
    }
}
