package X;

import java.io.IOException;
import java.util.Map;

/* renamed from: X.0dp  reason: invalid class name and case insensitive filesystem */
public final class C01370dp<T> extends AnonymousClass13Y<T> {
    public final AnonymousClass143<T> A00;
    public final Map<String, AnonymousClass14C> A01;

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r4, T t) throws IOException {
        if (t == null) {
            r4.A09();
            return;
        }
        r4.A06();
        try {
            for (AnonymousClass14C r1 : this.A01.values()) {
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

    public C01370dp(AnonymousClass143<T> r1, Map<String, AnonymousClass14C> map) {
        this.A00 = r1;
        this.A01 = map;
    }

    @Override // X.AnonymousClass13Y
    public final T A02(AnonymousClass14I r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A09) {
            r4.A0P();
            return null;
        }
        T A2F = this.A00.A2F();
        try {
            r4.A0M();
            while (r4.A0R()) {
                AnonymousClass14C r1 = this.A01.get(r4.A0I());
                if (r1 == null || !r1.A00) {
                    r4.A0Q();
                } else {
                    r1.A00(r4, A2F);
                }
            }
            r4.A0O();
            return A2F;
        } catch (IllegalStateException e) {
            throw new AnonymousClass0eR(e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }
}
