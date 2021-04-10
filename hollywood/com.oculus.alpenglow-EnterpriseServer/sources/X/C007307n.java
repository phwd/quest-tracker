package X;

import java.io.IOException;

/* renamed from: X.07n  reason: invalid class name and case insensitive filesystem */
public final class C007307n extends AnonymousClass0E2 {
    public static final C007307n A01 = new C007307n(false);
    public static final C007307n A02 = new C007307n(true);
    public final boolean A00;

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this.A00 == ((C007307n) obj).A00;
        }
        return true;
    }

    @Override // X.AnonymousClass0aF
    public final String A02() {
        if (this.A00) {
            return "true";
        }
        return "false";
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C05910ld {
        r2.A0W(this.A00);
    }

    public C007307n(boolean z) {
        this.A00 = z;
    }
}
