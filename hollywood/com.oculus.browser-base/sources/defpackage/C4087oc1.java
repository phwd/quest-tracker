package defpackage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/* renamed from: oc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4087oc1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4599rc1 f10563a;

    public C4087oc1(C4599rc1 rc1) {
        this.f10563a = rc1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4599rc1 rc1 = this.f10563a;
        C3574lc1 lc1 = (C3574lc1) obj;
        synchronized (rc1.K) {
            if (lc1.b.equals(rc1.L)) {
                rc1.O--;
                rc1.K.addAll(lc1.f10356a);
                if (rc1.O == 0) {
                    Iterator it = rc1.N.iterator();
                    while (true) {
                        C1261Uq0 uq0 = (C1261Uq0) it;
                        if (!uq0.hasNext()) {
                            break;
                        }
                        List<C1529Zb1> f = C4599rc1.f(rc1.K);
                        C4258pc1 pc1 = new C4258pc1(rc1);
                        C2891hc1 hc1 = (C2891hc1) ((AbstractC3745mc1) uq0.next());
                        Objects.requireNonNull(hc1);
                        if (((LinkedList) f).size() != 0) {
                            for (C1529Zb1 zb1 : f) {
                                hc1.b(new C2720gc1(hc1, zb1, pc1));
                            }
                        }
                    }
                }
            }
        }
    }
}
