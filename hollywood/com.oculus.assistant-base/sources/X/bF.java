package X;

import java.util.Iterator;

public class bF implements Iterable {
    public static final C0517bE A03 = new C0517bE();
    public final int A00;
    public final int A01;
    public final int A02;

    public bF(int i, int i2) {
        this.A00 = i;
        if (i < i2) {
            int i3 = i2 % 1;
            int i4 = i % 1;
            int i5 = ((i3 < 0 ? i3 + 1 : i3) - (i4 < 0 ? i4 + 1 : i4)) % 1;
            i2 -= i5 < 0 ? i5 + 1 : i5;
        }
        this.A01 = i2;
        this.A02 = 1;
    }

    public final boolean A00() {
        if (!(this instanceof C1199v0)) {
            if (this.A02 > 0) {
                if (this.A00 > this.A01) {
                    return true;
                }
                return false;
            } else if (this.A00 < this.A01) {
                return true;
            } else {
                return false;
            }
        } else if (this.A00 > this.A01) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof bF)) {
            return false;
        }
        if (A00() && ((bF) obj).A00()) {
            return true;
        }
        bF bFVar = (bF) obj;
        if (this.A00 == bFVar.A00 && this.A01 == bFVar.A01 && this.A02 == bFVar.A02) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new C1200v1(this.A00, this.A01, this.A02);
    }

    public String toString() {
        StringBuilder sb;
        int i = this.A02;
        if (i > 0) {
            sb = new StringBuilder();
            sb.append(this.A00);
            sb.append("..");
            sb.append(this.A01);
            sb.append(" step ");
        } else {
            sb = new StringBuilder();
            sb.append(this.A00);
            sb.append(" downTo ");
            sb.append(this.A01);
            sb.append(" step ");
            i = -i;
        }
        sb.append(i);
        return sb.toString();
    }

    public int hashCode() {
        if (A00()) {
            return -1;
        }
        return (((this.A00 * 31) + this.A01) * 31) + this.A02;
    }
}
