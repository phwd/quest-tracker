package X;

/* renamed from: X.21f  reason: invalid class name and case insensitive filesystem */
public final class C139921f<T> {
    public int A00;
    public Object[] A01;
    public final int A02 = 4;
    public final Object[] A03;

    public C139921f() {
        Object[] objArr = new Object[5];
        this.A03 = objArr;
        this.A01 = objArr;
    }

    public final void A00(T t) {
        int i = this.A02;
        int i2 = this.A00;
        if (i2 == i) {
            Object[] objArr = new Object[(i + 1)];
            this.A01[i] = objArr;
            this.A01 = objArr;
            i2 = 0;
        }
        this.A01[i2] = t;
        this.A00 = i2 + 1;
    }
}
