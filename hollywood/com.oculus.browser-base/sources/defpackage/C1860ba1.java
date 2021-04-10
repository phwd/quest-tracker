package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.url.GURL;

/* renamed from: ba1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1860ba1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2202da1 f9548a;

    public C1860ba1(C2202da1 da1) {
        this.f9548a = da1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void C(Tab tab, boolean z) {
        this.f9548a.g();
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        C2202da1 da1 = this.f9548a;
        if (da1.M == tab) {
            da1.H.c(1, 10);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        C2202da1 da1 = this.f9548a;
        if (da1.M == tab) {
            da1.H.c(1, 7);
            this.f9548a.M = null;
        }
    }
}
