package X;

import java.io.IOException;

/* renamed from: X.0E5  reason: invalid class name */
public final class AnonymousClass0E5 extends AnonymousClass0K5 {
    public final String A00;

    @Override // X.AnonymousClass0K5, X.AnonymousClass0o6
    public final /* bridge */ /* synthetic */ AnonymousClass0o6 A00(AbstractC02580aL r4) {
        if (super.A00 == r4) {
            return this;
        }
        return new AnonymousClass0E5(this.A01, r4, this.A00);
    }

    @Override // X.AnonymousClass0K5
    public final /* bridge */ /* synthetic */ AnonymousClass0K5 A0A(AbstractC02580aL r4) {
        if (super.A00 == r4) {
            return this;
        }
        return new AnonymousClass0E5(this.A01, r4, this.A00);
    }

    public AnonymousClass0E5(AnonymousClass0o4 r1, AbstractC02580aL r2, String str) {
        super(r1, r2);
        this.A00 = str;
    }

    @Override // X.AnonymousClass0K5, X.AnonymousClass0o6
    public final void A02(Object obj, AbstractC02640aV r4) throws IOException, C05910ld {
        r4.A0F();
        String str = this.A00;
        String A56 = this.A01.A56(obj);
        r4.A0P(str);
        r4.A0S(A56);
    }

    @Override // X.AnonymousClass0K5, X.AnonymousClass0o6
    public final void A08(Object obj, AbstractC02640aV r3, String str) throws IOException, C05910ld {
        r3.A0F();
        r3.A0P(this.A00);
        r3.A0S(str);
    }

    @Override // X.AnonymousClass0K5, X.AnonymousClass0o6
    public final void A05(Object obj, AbstractC02640aV r2) throws IOException, C05910ld {
        r2.A0C();
    }

    @Override // X.AnonymousClass0K5, X.AnonymousClass0o6
    public final void A09(Object obj, AbstractC02640aV r2, String str) throws IOException, C05910ld {
        r2.A0C();
    }
}
