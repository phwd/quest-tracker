package X;

import java.io.IOException;

/* renamed from: X.1Y  reason: invalid class name */
public final class AnonymousClass1Y {
    public int A00;
    public AnonymousClass1U A01;
    public Object A02;
    public final Object[] A03;
    public final Rn A04;
    public final AbstractC0122Rd A05;
    public final C00020k A06;

    public final boolean A02(Object obj) {
        this.A03[-1] = obj;
        int i = this.A00 - 1;
        this.A00 = i;
        if (i > 0) {
            return false;
        }
        return true;
    }

    public final void A00(C00000b r3, String str, Object obj) {
        this.A01 = new RC(this.A01, obj, r3, str);
    }

    public final void A01(AQ aq, Object obj) {
        this.A01 = new R9(this.A01, obj, aq);
    }

    public final boolean A03(String str) throws IOException {
        C00020k r1 = this.A06;
        if (r1 == null || !str.equals(r1.propertyName)) {
            return false;
        }
        this.A02 = r1.deserializer.A03(this.A04, null);
        return true;
    }

    public AnonymousClass1Y(Rn rn, AbstractC0122Rd rd, int i, C00020k r5) {
        this.A04 = rn;
        this.A05 = rd;
        this.A00 = i;
        this.A06 = r5;
        this.A03 = new Object[i];
    }
}
