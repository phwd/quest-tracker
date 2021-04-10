package X;

/* renamed from: X.6q  reason: invalid class name and case insensitive filesystem */
public final class C00416q extends AnonymousClass0V {
    public final double A00;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Double.compare(this.A00, ((C00416q) obj).A00) == 0;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.A00);
        return ((int) doubleToLongBits) ^ ((int) (doubleToLongBits >> 32));
    }

    public C00416q(double d) {
        this.A00 = d;
    }
}
