package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* renamed from: U51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U51 extends AbstractC1099Sa1 {
    public U51(X51 x51, AbstractC0124Ca1 ca1) {
        super(ca1);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void Q(Tab tab) {
        if (!((TabImpl) tab).H) {
            X51.d().edit().putString(X51.e(tab.getId()), tab.getTitle()).apply();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        if (!((TabImpl) tab).H) {
            X51.d().edit().putString(X51.f(tab.getId()), ((TabImpl) tab).s()).apply();
        }
    }

    @Override // defpackage.AbstractC1099Sa1, defpackage.AbstractC5553xB
    public void f(Tab tab, int i) {
        if (!tab.a()) {
            X51.d().edit().putInt(X51.c(tab.getId()), i).apply();
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        if (!tab.a() && navigationHandle.f10940a && tab.l() != null) {
            X51.a(tab);
        }
    }
}
