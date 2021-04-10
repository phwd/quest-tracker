package X;

import java.io.IOException;

/* renamed from: X.05U  reason: invalid class name */
public final class AnonymousClass05U extends AnonymousClass07j {
    public static final AnonymousClass05U[] A01;
    public final int A00;

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass05U) obj).A00 == this.A00;
        }
        return true;
    }

    static {
        AnonymousClass05U[] r3 = new AnonymousClass05U[12];
        A01 = r3;
        int i = 0;
        do {
            r3[i] = new AnonymousClass05U(i - 1);
            i++;
        } while (i < 12);
    }

    @Override // X.AnonymousClass07j, X.AnonymousClass0aF
    public final String A02() {
        int i = this.A00;
        String[] strArr = C06130m0.A02;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C06130m0.A03;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C05910ld {
        r2.A0J(this.A00);
    }

    public final int hashCode() {
        return this.A00;
    }

    public AnonymousClass05U(int i) {
        this.A00 = i;
    }
}
