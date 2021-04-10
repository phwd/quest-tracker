package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* renamed from: e11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2283e11 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2454f11 f9825a;

    public C2283e11(C2454f11 f11, C2113d11 d11) {
        this.f9825a = f11;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.f9825a.h();
        this.f9825a.i(7);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        Q31 q31 = this.f9825a.o;
        if (q31 != null && ((Boolean) q31.get()).booleanValue()) {
            this.f9825a.i(8);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void N(Tab tab) {
        this.f9825a.j = true;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        this.f9825a.i(6);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void w(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.f10940a) {
            C2454f11 f11 = this.f9825a;
            if (f11.j) {
                f11.i(5);
            }
        }
    }
}
