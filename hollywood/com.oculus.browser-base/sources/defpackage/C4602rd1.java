package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: rd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4602rd1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f11209a;
    public final Callback b;

    public C4602rd1(Tab tab, Callback callback) {
        this.f11209a = tab;
        this.b = callback;
        ((TabImpl) tab).P.b(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void O(Tab tab) {
        V(tab, false);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        V(tab, false);
    }

    public final void V(Tab tab, boolean z) {
        int m = tab.m();
        if (z) {
            m = tab.l().m();
        }
        this.b.onResult(Integer.valueOf(m));
    }

    @Override // defpackage.AbstractC1404Xa1
    public void i(Tab tab, WindowAndroid windowAndroid) {
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        tab.I(this);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void t(Tab tab, boolean z, int i, GURL gurl) {
        V(tab, true);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        if (navigationHandle.j != 0) {
            V(tab, true);
        }
    }
}
