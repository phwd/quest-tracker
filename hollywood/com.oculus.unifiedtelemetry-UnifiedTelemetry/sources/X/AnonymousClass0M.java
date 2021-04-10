package X;

/* renamed from: X.0M  reason: invalid class name */
public final class AnonymousClass0M extends AnonymousClass30 {
    public static final AnonymousClass0M[] A01;
    public final int A00;

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass0M) obj).A00 == this.A00;
        }
        return true;
    }

    static {
        AnonymousClass0M[] r3 = new AnonymousClass0M[12];
        A01 = r3;
        int i = 0;
        do {
            r3[i] = new AnonymousClass0M(i - 1);
            i++;
        } while (i < 12);
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final Number A01() {
        return Integer.valueOf(this.A00);
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final String A06() {
        int i = this.A00;
        String[] strArr = C0461of.A00;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C0461of.A01;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    public AnonymousClass0M(int i) {
        this.A00 = i;
    }

    public final int hashCode() {
        return this.A00;
    }
}
