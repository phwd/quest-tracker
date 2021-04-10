package X;

import java.io.IOException;

/* renamed from: X.0Or  reason: invalid class name */
public class AnonymousClass0Or extends AbstractC01860hD {
    /* renamed from: A0A */
    public AnonymousClass0Or A00(AbstractC02220iI r3) {
        if (this.A00 == r3) {
            return this;
        }
        return new AnonymousClass0Or(this.A01, r3);
    }

    @Override // X.AbstractC04550qd
    public final void A01(Object obj, AbstractC02300iS r3) throws IOException, C03620oC {
        r3.A0H();
        r3.A0U(this.A01.A5X(obj));
        r3.A0H();
    }

    @Override // X.AbstractC04550qd
    public void A02(Object obj, AbstractC02300iS r3) throws IOException, C03620oC {
        r3.A0H();
        r3.A0U(this.A01.A5X(obj));
        r3.A0I();
    }

    @Override // X.AbstractC04550qd
    public final void A03(Object obj, AbstractC02300iS r3) throws IOException, C03620oC {
        r3.A0H();
        r3.A0U(this.A01.A5X(obj));
    }

    @Override // X.AbstractC04550qd
    public final void A04(Object obj, AbstractC02300iS r2) throws IOException, C03620oC {
        r2.A0E();
        r2.A0E();
    }

    @Override // X.AbstractC04550qd
    public void A05(Object obj, AbstractC02300iS r2) throws IOException, C03620oC {
        r2.A0F();
        r2.A0E();
    }

    @Override // X.AbstractC04550qd
    public final void A07(Object obj, AbstractC02300iS r3, Class<?> cls) throws IOException, C03620oC {
        r3.A0H();
        r3.A0U(this.A01.A5Y(obj, cls));
    }

    @Override // X.AbstractC04550qd
    public void A08(Object obj, AbstractC02300iS r2, String str) throws IOException, C03620oC {
        r2.A0H();
        r2.A0U(str);
        r2.A0I();
    }

    public AnonymousClass0Or(AbstractC04530qb r1, AbstractC02220iI r2) {
        super(r1, r2);
    }

    @Override // X.AbstractC04550qd
    public final void A06(Object obj, AbstractC02300iS r2) throws IOException, C03620oC {
        r2.A0E();
    }

    @Override // X.AbstractC04550qd
    public void A09(Object obj, AbstractC02300iS r2, String str) throws IOException, C03620oC {
        A05(obj, r2);
    }
}
