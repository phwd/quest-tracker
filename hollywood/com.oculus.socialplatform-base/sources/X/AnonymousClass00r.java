package X;

import java.io.IOException;

/* renamed from: X.00r  reason: invalid class name */
public final class AnonymousClass00r extends AnonymousClass04R {
    public static final AnonymousClass00r[] A01;
    public final int A00;

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass00r) obj).A00 == this.A00;
        }
        return true;
    }

    static {
        AnonymousClass00r[] r3 = new AnonymousClass00r[12];
        A01 = r3;
        int i = 0;
        do {
            r3[i] = new AnonymousClass00r(i - 1);
            i++;
        } while (i < 12);
    }

    @Override // X.AnonymousClass04R, X.AbstractC02170iC
    public final String A02() {
        int i = this.A00;
        String[] strArr = C03790oY.A02;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C03790oY.A03;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C03620oC {
        r2.A0M(this.A00);
    }

    public AnonymousClass00r(int i) {
        this.A00 = i;
    }

    public final int hashCode() {
        return this.A00;
    }
}
