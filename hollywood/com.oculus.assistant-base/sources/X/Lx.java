package X;

public final class Lx {
    public byte A00;
    public boolean A01;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || !(obj instanceof Lx)) {
                return false;
            }
            Lx lx = (Lx) obj;
            if (!(this.A00 == lx.A00 && this.A01 == lx.A01)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return new Byte(this.A00).hashCode() + new Boolean(this.A01).hashCode();
    }

    public Lx() {
        this.A00 = 3;
        this.A01 = true;
        this.A00 = 3;
        this.A01 = true;
    }

    public Lx(byte b, boolean z) {
        this.A00 = 3;
        this.A01 = true;
        this.A00 = b;
        this.A01 = z;
    }
}
