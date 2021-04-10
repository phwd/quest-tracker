package defpackage;

import android.util.SparseArray;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Sa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1099Sa1 extends WK implements AbstractC5553xB {
    public final AbstractC0124Ca1 F;
    public final AbstractC0855Oa1 G;
    public final SparseArray H = new SparseArray();

    public AbstractC1099Sa1(AbstractC0124Ca1 ca1) {
        this.F = ca1;
        this.G = new C1038Ra1(this, ca1);
    }

    public void V(Tab tab) {
    }

    public void W(Tab tab) {
    }

    public void destroy() {
        this.G.destroy();
        List list = ((AbstractC0246Ea1) this.F).f7969a;
        for (int i = 0; i < list.size(); i++) {
            TabModel tabModel = (TabModel) list.get(i);
            tabModel.w(this.G);
            N81 j = tabModel.j();
            for (int i2 = 0; i2 < j.getCount(); i2++) {
                Tab tabAt = j.getTabAt(i2);
                tabAt.I(this);
                if (tabAt.isInitialized()) {
                    C5383wB.q(tabAt).Y.c(this);
                }
                W(tabAt);
            }
        }
    }

    @Override // defpackage.AbstractC5553xB
    public void f(Tab tab, int i) {
    }
}
