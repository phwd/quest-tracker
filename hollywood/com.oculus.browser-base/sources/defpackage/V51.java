package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: V51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V51 extends AbstractC5783ya1 {
    public V51(X51 x51) {
    }

    @Override // defpackage.AbstractC5783ya1
    public void F(Tab tab) {
        int id = tab.getId();
        X51.d().edit().remove(X51.f(id)).remove(X51.e(id)).remove(X51.c(id)).remove(X51.b(id)).apply();
    }
}
