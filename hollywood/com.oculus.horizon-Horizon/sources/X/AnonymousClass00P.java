package X;

/* renamed from: X.00P  reason: invalid class name */
public final class AnonymousClass00P extends AnonymousClass038 {
    public final double A00;

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Double.compare(this.A00, ((AnonymousClass00P) obj).A00) == 0;
    }

    @Override // X.AbstractC03980gY, X.AnonymousClass038
    public final String A02() {
        return Double.toString(this.A00);
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.A00);
        return ((int) doubleToLongBits) ^ ((int) (doubleToLongBits >> 32));
    }

    public AnonymousClass00P(double d) {
        this.A00 = d;
    }
}
