package X;

import java.io.IOException;

/* renamed from: X.0nO  reason: invalid class name */
public final class AnonymousClass0nO {
    public AnonymousClass0nN A00;
    public Object A01;
    public int A02;
    public final AbstractC02570aK A03;
    public final AnonymousClass0nL A04;
    public final Object[] A05;
    public final AnonymousClass0aT A06;

    public final void A00(C06560nA r3, String str, Object obj) {
        this.A00 = new AnonymousClass0a2(this.A00, obj, r3, str);
    }

    public final void A01(AbstractC01680Ku r3, Object obj) {
        this.A00 = new AnonymousClass0a0(this.A00, obj, r3);
    }

    public final boolean A02(int i, Object obj) {
        this.A05[i] = obj;
        int i2 = this.A02 - 1;
        this.A02 = i2;
        if (i2 > 0) {
            return false;
        }
        return true;
    }

    public final boolean A03(String str) throws IOException {
        AnonymousClass0nL r1 = this.A04;
        if (r1 == null || !str.equals(r1.propertyName)) {
            return false;
        }
        this.A01 = r1.deserializer.A09(this.A06, this.A03);
        return true;
    }

    public AnonymousClass0nO(AnonymousClass0aT r2, AbstractC02570aK r3, int i, AnonymousClass0nL r5) {
        this.A06 = r2;
        this.A03 = r3;
        this.A02 = i;
        this.A04 = r5;
        this.A05 = new Object[i];
    }
}
