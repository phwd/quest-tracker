package X;

import java.io.IOException;

/* renamed from: X.0px  reason: invalid class name and case insensitive filesystem */
public final class C04420px {
    public AbstractC04410pw A00;
    public Object A01;
    public int A02;
    public final AbstractC02210iH A03;
    public final C04390pu A04;
    public final Object[] A05;
    public final AbstractC02280iQ A06;

    public final void A00(C04290pj r3, String str, Object obj) {
        this.A00 = new C02080hx(this.A00, obj, r3, str);
    }

    public final void A01(AbstractC01170Rz r3, Object obj) {
        this.A00 = new C02060hv(this.A00, obj, r3);
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
        C04390pu r1 = this.A04;
        if (r1 == null || !str.equals(r1.propertyName)) {
            return false;
        }
        this.A01 = r1.deserializer.A0A(this.A06, this.A03);
        return true;
    }

    public C04420px(AbstractC02280iQ r2, AbstractC02210iH r3, int i, C04390pu r5) {
        this.A06 = r2;
        this.A03 = r3;
        this.A02 = i;
        this.A04 = r5;
        this.A05 = new Object[i];
    }
}
