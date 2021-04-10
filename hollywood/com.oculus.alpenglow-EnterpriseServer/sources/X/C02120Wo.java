package X;

import java.io.IOException;
import java.util.Map;

/* renamed from: X.0Wo  reason: invalid class name and case insensitive filesystem */
public final class C02120Wo<T> extends AnonymousClass0Bd<T> {
    public final AbstractC01170Du<T> A00;
    public final Map<String, AnonymousClass0FZ> A01;

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r4, T t) throws IOException {
        if (t == null) {
            r4.A0A();
            return;
        }
        r4.A07();
        try {
            for (AnonymousClass0FZ r1 : this.A01.values()) {
                if (r1.A02(t)) {
                    r4.A0D(r1.A02);
                    r1.A01(r4, t);
                }
            }
            r4.A09();
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public C02120Wo(AbstractC01170Du<T> r1, Map<String, AnonymousClass0FZ> map) {
        this.A00 = r1;
        this.A01 = map;
    }

    @Override // X.AnonymousClass0Bd
    public final T A02(AnonymousClass0Fo r4) throws IOException {
        if (r4.A0D() == AnonymousClass007.A0I) {
            r4.A0L();
            return null;
        }
        T A1o = this.A00.A1o();
        try {
            r4.A0I();
            while (r4.A0N()) {
                AnonymousClass0FZ r1 = this.A01.get(r4.A0E());
                if (r1 == null || !r1.A00) {
                    r4.A0M();
                } else {
                    r1.A00(r4, A1o);
                }
            }
            r4.A0K();
            return A1o;
        } catch (IllegalStateException e) {
            throw new AnonymousClass0XQ(e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }
}
