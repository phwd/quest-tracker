package X;

import java.io.IOException;

/* renamed from: X.0K4  reason: invalid class name */
public final class AnonymousClass0K4 extends AnonymousClass0Zd {
    public final String A00;

    @Override // X.AnonymousClass0o6
    public final AnonymousClass0o6 A00(AbstractC02580aL r4) {
        if (super.A00 == r4) {
            return this;
        }
        return new AnonymousClass0K4(this.A01, r4, this.A00);
    }

    @Override // X.AnonymousClass0o6
    public final void A04(Object obj, AbstractC02640aV r4) throws IOException, C05910ld {
        String A56 = this.A01.A56(obj);
        r4.A0B();
        r4.A0P(this.A00);
        r4.A0S(A56);
    }

    @Override // X.AnonymousClass0o6
    public final void A05(Object obj, AbstractC02640aV r4) throws IOException, C05910ld {
        String A56 = this.A01.A56(obj);
        r4.A0C();
        r4.A0P(this.A00);
        r4.A0S(A56);
    }

    @Override // X.AnonymousClass0o6
    public final void A06(Object obj, AbstractC02640aV r4) throws IOException, C05910ld {
        String A56 = this.A01.A56(obj);
        r4.A0P(this.A00);
        r4.A0S(A56);
    }

    public AnonymousClass0K4(AnonymousClass0o4 r1, AbstractC02580aL r2, String str) {
        super(r1, r2);
        this.A00 = str;
    }

    @Override // X.AnonymousClass0o6
    public final void A09(Object obj, AbstractC02640aV r3, String str) throws IOException, C05910ld {
        r3.A0C();
        r3.A0P(this.A00);
        r3.A0S(str);
    }

    @Override // X.AnonymousClass0o6
    public final void A01(Object obj, AbstractC02640aV r2) throws IOException, C05910ld {
        r2.A0E();
    }

    @Override // X.AnonymousClass0o6
    public final void A02(Object obj, AbstractC02640aV r2) throws IOException, C05910ld {
        r2.A0F();
    }

    @Override // X.AnonymousClass0o6
    public final void A03(Object obj, AbstractC02640aV r2) throws IOException, C05910ld {
    }

    @Override // X.AnonymousClass0o6
    public final void A07(Object obj, AbstractC02640aV r2, Class<?> cls) throws IOException, C05910ld {
    }

    @Override // X.AnonymousClass0o6
    public final void A08(Object obj, AbstractC02640aV r2, String str) throws IOException, C05910ld {
        r2.A0F();
    }
}
