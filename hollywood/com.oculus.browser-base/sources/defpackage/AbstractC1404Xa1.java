package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.find_in_page.FindMatchRectsDetails;
import org.chromium.components.find_in_page.FindNotificationDetails;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: Xa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class AbstractC1404Xa1 {
    public abstract void A(Tab tab, int i);

    public abstract void B(Tab tab, String str);

    public abstract void C(Tab tab, boolean z);

    public abstract void D(Tab tab, float f);

    public abstract void E(Tab tab, boolean z);

    public abstract void F(Tab tab, boolean z);

    public abstract void G(Tab tab, LoadUrlParams loadUrlParams, int i);

    public abstract void H(Tab tab);

    public abstract void I(Tab tab, int i);

    public abstract void J(Tab tab, GURL gurl);

    public abstract void K(Tab tab, GURL gurl);

    public abstract void L(Tab tab, boolean z);

    public abstract void M(Tab tab);

    public abstract void N(Tab tab);

    public abstract void O(Tab tab);

    public abstract void P(Tab tab, int i);

    public abstract void Q(Tab tab);

    public abstract void R(Tab tab, GURL gurl);

    public abstract void S(Tab tab);

    public abstract void T(Tab tab, boolean z, boolean z2);

    public abstract void U(Tab tab);

    public abstract void h(Tab tab);

    public void i(Tab tab, WindowAndroid windowAndroid) {
        if (tab != null && windowAndroid == null) {
            tab.I(this);
        }
    }

    public abstract void j(Tab tab, int i);

    public abstract void k(Tab tab, int i, int i2, int i3, int i4, int i5);

    public abstract void l(Tab tab);

    public abstract void m(Tab tab, boolean z);

    public abstract void n(Tab tab);

    public abstract void o(boolean z);

    public abstract void p(Tab tab);

    public abstract void q(Tab tab);

    public abstract void r(Tab tab);

    public abstract void s(Tab tab, int i);

    public abstract void t(Tab tab, boolean z, int i, GURL gurl);

    public abstract void u(Tab tab, NavigationHandle navigationHandle);

    public abstract void v(Tab tab, NavigationHandle navigationHandle);

    public abstract void w(Tab tab, NavigationHandle navigationHandle);

    public abstract void x(Tab tab, Bitmap bitmap);

    public abstract void y(FindMatchRectsDetails findMatchRectsDetails);

    public abstract void z(FindNotificationDetails findNotificationDetails);
}
