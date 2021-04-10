package defpackage;

import android.content.Context;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: tp1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4978tp1 extends AbstractC4758sY0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11372a;
    public final AbstractC0124Ca1 b;
    public final AbstractC4928tY0 c;
    public final RK d;
    public final AbstractC0612Ka1 e;
    public final AbstractC0855Oa1 f;

    public C4978tp1(Context context, AbstractC0124Ca1 ca1, AbstractC4928tY0 ty0) {
        this.f11372a = context;
        this.b = ca1;
        this.c = ty0;
        C4297pp1 pp1 = new C4297pp1(this);
        this.d = pp1;
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
        ((I71) ea1.c.g(false)).g.b(pp1);
        ((I71) ea1.c.g(true)).g.b(pp1);
        C4468qp1 qp1 = new C4468qp1(this);
        this.e = qp1;
        ea1.c(qp1);
        this.f = new C4638rp1(this, ca1);
    }

    @Override // defpackage.AbstractC4758sY0
    public void c(Object obj) {
        List list = (List) obj;
        I71 i71 = (I71) ((AbstractC0246Ea1) this.b).c.d();
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                C4808sp1 sp1 = (C4808sp1) list.get(size);
                Tab tab = sp1.f11303a;
                int i = sp1.b;
                int i2 = sp1.c;
                Objects.requireNonNull(i71);
                if (tab.isInitialized()) {
                    int e2 = AbstractC1160Ta1.e(i71.b, tab.getId());
                    C5383wB.q(tab).u(i2);
                    if (e2 == i) {
                        i71.B(tab, i, e2);
                    } else {
                        if (e2 < i) {
                            i++;
                        }
                        i71.b.m(tab.getId(), i);
                    }
                }
            } else {
                return;
            }
        }
    }
}
