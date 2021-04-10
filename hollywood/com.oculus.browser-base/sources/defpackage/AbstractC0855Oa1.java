package defpackage;

import java.util.List;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Oa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0855Oa1 extends AbstractC5783ya1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0124Ca1 f8633a;
    public AbstractC0612Ka1 b;

    public AbstractC0855Oa1(AbstractC0124Ca1 ca1) {
        this.f8633a = ca1;
        if (((AbstractC0246Ea1) ca1).f7969a.isEmpty()) {
            C0794Na1 na1 = new C0794Na1(this);
            this.b = na1;
            ((AbstractC0246Ea1) ca1).c(na1);
            return;
        }
        M();
    }

    public void L() {
    }

    public final void M() {
        List list = ((AbstractC0246Ea1) this.f8633a).f7969a;
        for (int i = 0; i < list.size(); i++) {
            ((TabModel) list.get(i)).n(this);
        }
        L();
    }

    public void destroy() {
        AbstractC0612Ka1 ka1 = this.b;
        if (ka1 != null) {
            ((AbstractC0246Ea1) this.f8633a).f.c(ka1);
            this.b = null;
        }
        List list = ((AbstractC0246Ea1) this.f8633a).f7969a;
        for (int i = 0; i < list.size(); i++) {
            ((TabModel) list.get(i)).w(this);
        }
    }
}
