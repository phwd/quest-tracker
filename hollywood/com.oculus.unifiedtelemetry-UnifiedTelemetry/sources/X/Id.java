package X;

public abstract class Id<T> {
    public int A00;
    public Iv<T> A01;
    public Iv<T> A02;
    public T A03;

    public abstract T A01(int i);

    public final T A00() {
        Iv<T> iv = this.A02;
        if (iv != null) {
            this.A03 = iv.A02;
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
        Iv<T> iv = new Iv<>(t, i);
        if (this.A01 == null) {
            this.A02 = iv;
            this.A01 = iv;
        } else {
            Iv<T> iv2 = this.A02;
            if (iv2.A00 == null) {
                iv2.A00 = iv;
                this.A02 = iv;
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
        for (Iv<T> iv = this.A01; iv != null; iv = iv.A00) {
            T t2 = iv.A02;
            int i4 = iv.A01;
            System.arraycopy(t2, 0, A012, i3, i4);
            i3 += i4;
        }
        System.arraycopy(t, 0, A012, i3, i);
        int i5 = i3 + i;
        if (i5 == i2) {
            return A012;
        }
        StringBuilder sb = new StringBuilder("Should have gotten ");
        sb.append(i2);
        sb.append(" entries, got ");
        sb.append(i5);
        throw new IllegalStateException(sb.toString());
    }
}
