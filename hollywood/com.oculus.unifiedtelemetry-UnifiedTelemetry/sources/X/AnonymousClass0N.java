package X;

/* renamed from: X.0N  reason: invalid class name */
public final class AnonymousClass0N extends AnonymousClass30 {
    public final double A00;

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Double.compare(this.A00, ((AnonymousClass0N) obj).A00) == 0;
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final Number A01() {
        return Double.valueOf(this.A00);
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final String A06() {
        return Double.toString(this.A00);
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.A00);
        return ((int) doubleToLongBits) ^ ((int) (doubleToLongBits >> 32));
    }

    public AnonymousClass0N(double d) {
        this.A00 = d;
    }
}
