package X;

import java.io.IOException;

/* renamed from: X.0Cm  reason: invalid class name */
public final class AnonymousClass0Cm extends AnonymousClass0Or {
    public final String A00;

    @Override // X.AnonymousClass0Or, X.AbstractC04550qd
    public final /* bridge */ /* synthetic */ AbstractC04550qd A00(AbstractC02220iI r4) {
        if (super.A00 == r4) {
            return this;
        }
        return new AnonymousClass0Cm(this.A01, r4, this.A00);
    }

    @Override // X.AnonymousClass0Or
    public final /* bridge */ /* synthetic */ AnonymousClass0Or A0A(AbstractC02220iI r4) {
        if (super.A00 == r4) {
            return this;
        }
        return new AnonymousClass0Cm(this.A01, r4, this.A00);
    }

    public AnonymousClass0Cm(AbstractC04530qb r1, AbstractC02220iI r2, String str) {
        super(r1, r2);
        this.A00 = str;
    }

    @Override // X.AnonymousClass0Or, X.AbstractC04550qd
    public final void A02(Object obj, AbstractC02300iS r4) throws IOException, C03620oC {
        r4.A0I();
        String str = this.A00;
        String A5X = this.A01.A5X(obj);
        r4.A0R(str);
        r4.A0U(A5X);
    }

    @Override // X.AnonymousClass0Or, X.AbstractC04550qd
    public final void A08(Object obj, AbstractC02300iS r3, String str) throws IOException, C03620oC {
        r3.A0I();
        r3.A0R(this.A00);
        r3.A0U(str);
    }

    @Override // X.AnonymousClass0Or, X.AbstractC04550qd
    public final void A05(Object obj, AbstractC02300iS r2) throws IOException, C03620oC {
        r2.A0F();
    }

    @Override // X.AnonymousClass0Or, X.AbstractC04550qd
    public final void A09(Object obj, AbstractC02300iS r2, String str) throws IOException, C03620oC {
        r2.A0F();
    }
}
