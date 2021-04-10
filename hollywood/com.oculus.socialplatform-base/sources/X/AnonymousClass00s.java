package X;

import java.io.IOException;

/* renamed from: X.00s  reason: invalid class name */
public final class AnonymousClass00s extends AnonymousClass04R {
    public final double A00;

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Double.compare(this.A00, ((AnonymousClass00s) obj).A00) == 0;
    }

    @Override // X.AnonymousClass04R, X.AbstractC02170iC
    public final String A02() {
        return Double.toString(this.A00);
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C03620oC {
        r3.A0K(this.A00);
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.A00);
        return ((int) doubleToLongBits) ^ ((int) (doubleToLongBits >> 32));
    }

    public AnonymousClass00s(double d) {
        this.A00 = d;
    }
}
