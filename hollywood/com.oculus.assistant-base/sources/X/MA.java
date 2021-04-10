package X;

public final class MA {
    public final String A00;
    public final boolean A01;
    public final boolean A02;
    public final boolean A03;

    public final boolean equals(Object obj) {
        if (!(obj instanceof MA)) {
            return false;
        }
        MA ma = (MA) obj;
        if (this.A00.equals(ma.A00) && this.A01 == ma.A01 && this.A02 == ma.A02 && this.A03 == ma.A03) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.A00.hashCode() + (this.A01 ? 1 : 0) + (this.A02 ? 1 : 0) + (this.A03 ? 1 : 0);
    }

    public MA(String str, boolean z, boolean z2, boolean z3) {
        this.A00 = str;
        this.A01 = z;
        this.A02 = z2;
        this.A03 = z3;
    }
}
