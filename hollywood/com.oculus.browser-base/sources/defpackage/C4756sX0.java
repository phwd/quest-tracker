package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: sX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4756sX0 extends VK {
    public final /* synthetic */ C4926tX0 F;

    public C4756sX0(C4926tX0 tx0) {
        this.F = tx0;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void b() {
        TabModel l = ((AbstractC0246Ea1) this.F.b).l(false);
        C4926tX0 tx0 = this.F;
        if (tx0.j) {
            tx0.j = false;
            l.n(tx0.e);
        }
        int index = l.index();
        if (index != -1) {
            Tab tabAt = l.getTabAt(index);
            this.F.c.m(AbstractC5096uX0.d, tabAt.getTitle());
            C4926tX0 tx02 = this.F;
            if (tx02.k == null) {
                tx02.k = Long.valueOf(SystemClock.elapsedRealtime());
            }
            C4926tX0 tx03 = this.F;
            C2307e91 e91 = tx03.d;
            if (e91.n) {
                tx03.l = true;
                e91.b(tabAt.s(), false, new C4245pX0(tx03));
            }
        }
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void c(TabModel tabModel, TabModel tabModel2) {
        if (!tabModel.a()) {
            this.F.h = true;
        }
    }
}
