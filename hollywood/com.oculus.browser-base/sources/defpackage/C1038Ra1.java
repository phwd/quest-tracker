package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Ra1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1038Ra1 extends AbstractC0855Oa1 {
    public final /* synthetic */ AbstractC1099Sa1 c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1038Ra1(AbstractC1099Sa1 sa1, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.c = sa1;
    }

    @Override // defpackage.AbstractC5783ya1
    public void A(int i, boolean z) {
        Tab tab = (Tab) this.c.H.get(i);
        if (tab != null) {
            this.c.H.remove(i);
            this.c.W(tab);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void G(Tab tab) {
        this.c.H.remove(tab.getId());
    }

    @Override // defpackage.AbstractC5783ya1
    public void I(Tab tab) {
        if (tab != null) {
            PostTask.b(Zo1.f9374a, new RunnableC0916Pa1(this, tab), 0);
            this.c.W(tab);
        }
    }

    @Override // defpackage.AbstractC5783ya1
    public void K(Tab tab, boolean z) {
        this.c.H.put(tab.getId(), tab);
    }

    @Override // defpackage.AbstractC0855Oa1
    public void L() {
        ArrayList arrayList = new ArrayList();
        List list = ((AbstractC0246Ea1) this.c.F).f7969a;
        for (int i = 0; i < list.size(); i++) {
            N81 j = ((TabModel) list.get(i)).j();
            for (int i2 = 0; i2 < j.getCount(); i2++) {
                Tab tabAt = j.getTabAt(i2);
                tabAt.A(this.c);
                C5383wB.q(tabAt).Y.b(this.c);
                arrayList.add(tabAt);
            }
        }
        ThreadUtils.b().postAtFrontOfQueue(new RunnableC0977Qa1(this, arrayList));
    }

    @Override // defpackage.AbstractC5783ya1
    public void z(Tab tab, int i, int i2) {
        tab.A(this.c);
        C5383wB q = C5383wB.q(tab);
        q.Y.b(this.c);
        this.c.V(tab);
    }
}
