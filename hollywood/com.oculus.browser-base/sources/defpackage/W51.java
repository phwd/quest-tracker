package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: W51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W51 extends VK {
    public final /* synthetic */ X51 F;

    public W51(X51 x51) {
        this.F = x51;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void b() {
        AbstractC3568la1 g = ((AbstractC0246Ea1) this.F.b).c.g(false);
        for (int i = 0; i < g.getCount(); i++) {
            Tab tabAt = g.getTabAt(i);
            int id = tabAt.getId();
            X51.d().edit().putString(X51.f(id), tabAt.s()).apply();
            int id2 = tabAt.getId();
            X51.d().edit().putString(X51.e(id2), tabAt.getTitle()).apply();
            int id3 = tabAt.getId();
            X51.d().edit().putInt(X51.c(id3), C5383wB.q(tabAt).R).apply();
        }
        Tab j = ((AbstractC0246Ea1) this.F.b).j();
        if (j != null) {
            X51.a(j);
        }
        g.c.b(this.F.c);
    }
}
