package X;

import java.io.IOException;

/* renamed from: X.05W  reason: invalid class name */
public final class AnonymousClass05W extends AnonymousClass07j {
    public final double A00;

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Double.compare(this.A00, ((AnonymousClass05W) obj).A00) == 0;
    }

    @Override // X.AnonymousClass07j, X.AnonymousClass0aF
    public final String A02() {
        return Double.toString(this.A00);
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C05910ld {
        r3.A0H(this.A00);
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.A00);
        return ((int) doubleToLongBits) ^ ((int) (doubleToLongBits >> 32));
    }

    public AnonymousClass05W(double d) {
        this.A00 = d;
    }
}
