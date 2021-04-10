package X;

/* renamed from: X.00O  reason: invalid class name */
public final class AnonymousClass00O extends AnonymousClass038 {
    public static final AnonymousClass00O[] A01;
    public final int A00;

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass00O) obj).A00 == this.A00;
        }
        return true;
    }

    static {
        AnonymousClass00O[] r3 = new AnonymousClass00O[12];
        A01 = r3;
        int i = 0;
        do {
            r3[i] = new AnonymousClass00O(i - 1);
            i++;
        } while (i < 12);
    }

    @Override // X.AbstractC03980gY, X.AnonymousClass038
    public final String A02() {
        int i = this.A00;
        String[] strArr = C05010k3.A00;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C05010k3.A01;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    public AnonymousClass00O(int i) {
        this.A00 = i;
    }

    public final int hashCode() {
        return this.A00;
    }
}
