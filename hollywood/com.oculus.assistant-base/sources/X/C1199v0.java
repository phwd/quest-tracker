package X;

/* renamed from: X.v0  reason: case insensitive filesystem */
public final class C1199v0 extends bF {
    public static final C0518bG A00 = new C0518bG();
    public static final C1199v0 A01 = new C1199v0(1, 0);

    @Override // X.bF
    public final boolean equals(Object obj) {
        if (!(obj instanceof C1199v0)) {
            return false;
        }
        if (A00() && ((bF) obj).A00()) {
            return true;
        }
        bF bFVar = (bF) obj;
        if (this.A00 == bFVar.A00 && this.A01 == bFVar.A01) {
            return true;
        }
        return false;
    }

    @Override // X.bF
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A00);
        sb.append("..");
        sb.append(this.A01);
        return sb.toString();
    }

    @Override // X.bF
    public final int hashCode() {
        if (A00()) {
            return -1;
        }
        return (this.A00 * 31) + this.A01;
    }

    public C1199v0(int i, int i2) {
        super(i, i2);
    }
}
