package X;

/* renamed from: X.Ox  reason: case insensitive filesystem */
public final class C0276Ox {
    public AbstractC0275Ow A00;
    public Object A01;
    public int A02;
    public final AbstractC1022qr A03;
    public final C0273Ou A04;
    public final Object[] A05;
    public final AbstractC1014qi A06;

    public final void A00(C0267Oj oj, String str, Object obj) {
        this.A00 = new C1035r8(this.A00, obj, oj, str);
    }

    public final void A01(AbstractC1034r7 r7Var, Object obj) {
        this.A00 = new C1037rA(this.A00, obj, r7Var);
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

    public final boolean A03(String str) {
        C0273Ou ou = this.A04;
        if (ou == null || !str.equals(ou.propertyName)) {
            return false;
        }
        this.A01 = ou.deserializer.A0C(this.A06, this.A03);
        return true;
    }

    public C0276Ox(AbstractC1014qi qiVar, AbstractC1022qr qrVar, int i, C0273Ou ou) {
        this.A06 = qiVar;
        this.A03 = qrVar;
        this.A02 = i;
        this.A04 = ou;
        this.A05 = new Object[i];
    }
}
