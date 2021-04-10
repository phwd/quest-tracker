package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: gm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2750gm1 extends WK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2921hm1 f10019a;

    public C2750gm1(C2921hm1 hm1) {
        this.f10019a = hm1;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void h(Tab tab) {
        int i;
        C2921hm1 hm1 = this.f10019a;
        Objects.requireNonNull(hm1);
        if (!tab.isNativePage()) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.L != null && !AbstractC5154ur1.f(tab.getUrl())) {
                int m = tabImpl.L.m();
                if (m == 0) {
                    i = 0;
                } else {
                    i = !hm1.i(tab, m) ? 1 : 2;
                }
                AbstractC3364kK0.g("Android.ThemeColor", i, 3);
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void s(Tab tab, int i) {
        C2921hm1 hm1 = this.f10019a;
        hm1.b(hm1.e(tab, i), true);
        hm1.P = hm1.i(tab, i);
    }
}
