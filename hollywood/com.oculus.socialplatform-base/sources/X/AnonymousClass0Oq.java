package X;

import java.io.IOException;

/* renamed from: X.0Oq  reason: invalid class name */
public final class AnonymousClass0Oq extends AbstractC01860hD {
    public final String A00;

    @Override // X.AbstractC04550qd
    public final AbstractC04550qd A00(AbstractC02220iI r4) {
        if (super.A00 == r4) {
            return this;
        }
        return new AnonymousClass0Oq(this.A01, r4, this.A00);
    }

    @Override // X.AbstractC04550qd
    public final void A04(Object obj, AbstractC02300iS r4) throws IOException, C03620oC {
        String A5X = this.A01.A5X(obj);
        r4.A0E();
        r4.A0R(this.A00);
        r4.A0U(A5X);
    }

    @Override // X.AbstractC04550qd
    public final void A05(Object obj, AbstractC02300iS r4) throws IOException, C03620oC {
        String A5X = this.A01.A5X(obj);
        r4.A0F();
        r4.A0R(this.A00);
        r4.A0U(A5X);
    }

    @Override // X.AbstractC04550qd
    public final void A06(Object obj, AbstractC02300iS r4) throws IOException, C03620oC {
        String A5X = this.A01.A5X(obj);
        r4.A0R(this.A00);
        r4.A0U(A5X);
    }

    public AnonymousClass0Oq(AbstractC04530qb r1, AbstractC02220iI r2, String str) {
        super(r1, r2);
        this.A00 = str;
    }

    @Override // X.AbstractC04550qd
    public final void A09(Object obj, AbstractC02300iS r3, String str) throws IOException, C03620oC {
        r3.A0F();
        r3.A0R(this.A00);
        r3.A0U(str);
    }

    @Override // X.AbstractC04550qd
    public final void A01(Object obj, AbstractC02300iS r2) throws IOException, C03620oC {
        r2.A0H();
    }

    @Override // X.AbstractC04550qd
    public final void A02(Object obj, AbstractC02300iS r2) throws IOException, C03620oC {
        r2.A0I();
    }

    @Override // X.AbstractC04550qd
    public final void A03(Object obj, AbstractC02300iS r2) throws IOException, C03620oC {
    }

    @Override // X.AbstractC04550qd
    public final void A07(Object obj, AbstractC02300iS r2, Class<?> cls) throws IOException, C03620oC {
    }

    @Override // X.AbstractC04550qd
    public final void A08(Object obj, AbstractC02300iS r2, String str) throws IOException, C03620oC {
        r2.A0I();
    }
}
