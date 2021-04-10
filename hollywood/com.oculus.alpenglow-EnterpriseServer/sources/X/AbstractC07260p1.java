package X;

/* renamed from: X.0p1  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC07260p1<T> {
    public int A00;
    public C07250p0<T> A01;
    public C07250p0<T> A02;
    public T A03;

    public abstract T A01(int i);

    public final T A00() {
        C07250p0<T> r0 = this.A02;
        if (r0 != null) {
            this.A03 = r0.A02;
        }
        this.A02 = null;
        this.A01 = null;
        this.A00 = 0;
        T t = this.A03;
        if (t == null) {
            return A01(12);
        }
        return t;
    }

    public final T A02(T t, int i) {
        int i2;
        C07250p0<T> r2 = new C07250p0<>(t, i);
        if (this.A01 == null) {
            this.A02 = r2;
            this.A01 = r2;
        } else {
            C07250p0<T> r1 = this.A02;
            if (r1.A00 == null) {
                r1.A00 = r2;
                this.A02 = r2;
            } else {
                throw new IllegalStateException();
            }
        }
        this.A00 += i;
        if (i < 16384) {
            i2 = i + i;
        } else {
            i2 = i + (i >> 2);
        }
        return A01(i2);
    }

    public final T A03(T t, int i) {
        int i2 = this.A00 + i;
        T A012 = A01(i2);
        int i3 = 0;
        for (C07250p0<T> r0 = this.A01; r0 != null; r0 = r0.A00) {
            T t2 = r0.A02;
            int i4 = r0.A01;
            System.arraycopy(t2, 0, A012, i3, i4);
            i3 += i4;
        }
        System.arraycopy(t, 0, A012, i3, i);
        int i5 = i3 + i;
        if (i5 == i2) {
            return A012;
        }
        throw new IllegalStateException(AnonymousClass006.A03("Should have gotten ", i2, " entries, got ", i5));
    }
}
