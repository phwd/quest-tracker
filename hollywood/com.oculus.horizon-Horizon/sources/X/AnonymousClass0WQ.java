package X;

import java.io.IOException;
import java.util.Map;

/* renamed from: X.0WQ  reason: invalid class name */
public final class AnonymousClass0WQ<T> extends AnonymousClass0yl<T> {
    public final AbstractC09010zC<T> A00;
    public final Map<String, AbstractC09070zL> A01;

    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r4, T t) throws IOException {
        if (t == null) {
            r4.A09();
            return;
        }
        r4.A06();
        try {
            for (AbstractC09070zL r1 : this.A01.values()) {
                if (r1.A02(t)) {
                    r4.A0C(r1.A02);
                    r1.A01(r4, t);
                }
            }
            r4.A08();
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public AnonymousClass0WQ(AbstractC09010zC<T> r1, Map<String, AbstractC09070zL> map) {
        this.A00 = r1;
        this.A01 = map;
    }

    @Override // X.AnonymousClass0yl
    public final T A02(C09120zR r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A0I) {
            r4.A0P();
            return null;
        }
        T A1q = this.A00.A1q();
        try {
            r4.A0M();
            while (r4.A0R()) {
                AbstractC09070zL r1 = this.A01.get(r4.A0I());
                if (r1 == null || !r1.A00) {
                    r4.A0Q();
                } else {
                    r1.A00(r4, A1q);
                }
            }
            r4.A0O();
            return A1q;
        } catch (IllegalStateException e) {
            throw new C03080c5(e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }
}
