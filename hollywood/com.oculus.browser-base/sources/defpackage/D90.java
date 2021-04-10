package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* renamed from: D90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D90 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ E90 f7872a;

    public D90(E90 e90) {
        this.f7872a = e90;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void D(Tab tab, float f) {
        TabImpl tabImpl = (TabImpl) tab;
        if (!AbstractC5154ur1.g(tabImpl.s()) && !AbstractC5818ym0.a(tabImpl.s(), tabImpl.H)) {
            this.f7872a.c(f);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void F(Tab tab, boolean z) {
        if (z) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.H() > 0.05f && tabImpl.H() < 1.0f) {
                this.f7872a.c(1.0f);
            }
            this.f7872a.a(true);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void T(Tab tab, boolean z, boolean z2) {
        if (z && z2) {
            E90 e90 = this.f7872a;
            if (!e90.d) {
                e90.c.a();
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void q(Tab tab) {
        this.f7872a.a(false);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        if (!navigationHandle.c && navigationHandle.f10940a) {
            if (AbstractC5818ym0.a(navigationHandle.e.f11029a, ((TabImpl) tab).H)) {
                this.f7872a.a(false);
                return;
            }
            H90 h90 = this.f7872a.c;
            UH0 uh0 = h90.f8140a;
            SH0 sh0 = F90.f7995a;
            uh0.l(sh0, 2);
            h90.b.removeMessages(1);
            E90 e90 = this.f7872a;
            if (!e90.d) {
                e90.f7939a.l(sh0, 0);
            }
            this.f7872a.c(((TabImpl) tab).H());
        }
    }
}
