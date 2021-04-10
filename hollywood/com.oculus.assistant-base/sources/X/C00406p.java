package X;

/* renamed from: X.6p  reason: invalid class name and case insensitive filesystem */
public final class C00406p extends AnonymousClass0V {
    public static final C00406p[] A01;
    public final int A00;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C00406p) obj).A00 == this.A00;
        }
        return true;
    }

    static {
        C00406p[] r3 = new C00406p[12];
        A01 = r3;
        int i = 0;
        do {
            r3[i] = new C00406p(i - 1);
            i++;
        } while (i < 12);
    }

    public C00406p(int i) {
        this.A00 = i;
    }

    public final int hashCode() {
        return this.A00;
    }
}
