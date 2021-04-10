package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.components.payments.PaymentApp;

/* renamed from: bz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1934bz0 extends AbstractC1399Wy0 {

    /* renamed from: a  reason: collision with root package name */
    public final Set f9576a;
    public final List b = new ArrayList();
    public final AbstractC1460Xy0 c;
    public boolean d;
    public final /* synthetic */ C2105cz0 e;

    public C1934bz0(C2105cz0 cz0, Set set, AbstractC1460Xy0 xy0, AbstractC1763az0 az0) {
        this.e = cz0;
        this.f9576a = set;
        this.c = xy0;
    }

    @Override // defpackage.AbstractC1460Xy0, defpackage.AbstractC1399Wy0
    public void a(AbstractC1521Yy0 yy0) {
        this.f9576a.remove(yy0);
        if (this.f9576a.isEmpty()) {
            if (!this.d) {
                this.c.i(false);
            }
            List list = this.b;
            HashMap hashMap = new HashMap();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                hashMap.put(((PaymentApp) list.get(i)).g, (PaymentApp) list.get(i));
            }
            for (int i2 = 0; i2 < size; i2++) {
                hashMap.remove(((PaymentApp) list.get(i2)).m());
            }
            HashSet hashSet = new HashSet(hashMap.values());
            Iterator it = hashMap.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PaymentApp paymentApp = (PaymentApp) it.next();
                if (paymentApp.x()) {
                    hashSet.clear();
                    hashSet.add(paymentApp);
                    break;
                }
                Set<String> n = paymentApp.n();
                if (n != null) {
                    for (String str : n) {
                        if (hashMap.containsKey(str)) {
                            hashSet.remove(paymentApp);
                        }
                    }
                }
            }
            this.b.clear();
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                this.c.j((PaymentApp) it2.next());
            }
            this.c.a(this.e);
        }
    }

    @Override // defpackage.AbstractC1460Xy0
    public AbstractC1582Zy0 d() {
        return this.c.d();
    }

    @Override // defpackage.AbstractC1460Xy0, defpackage.AbstractC1399Wy0
    public void i(boolean z) {
        if (z && !this.d) {
            this.d = true;
            this.c.i(true);
        }
    }

    @Override // defpackage.AbstractC1460Xy0
    public void j(PaymentApp paymentApp) {
        this.b.add(paymentApp);
    }

    @Override // defpackage.AbstractC1460Xy0, defpackage.AbstractC1399Wy0
    public void k(String str) {
        this.c.k(str);
    }
}
