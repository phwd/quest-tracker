package X;

/* renamed from: X.02n  reason: invalid class name */
public final class AnonymousClass02n<E> implements Cloneable {
    public static final Object A04 = new Object();
    public int A00;
    public boolean A01 = false;
    public long[] A02;
    public Object[] A03;

    public static void A00(AnonymousClass02n r9) {
        int i = r9.A00;
        long[] jArr = r9.A02;
        Object[] objArr = r9.A03;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != A04) {
                if (i3 != i2) {
                    jArr[i2] = jArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        r9.A01 = false;
        r9.A00 = i2;
    }

    public final E A01(long j, E e) {
        E e2;
        int A012 = AnonymousClass02m.A01(this.A02, this.A00, j);
        if (A012 < 0 || (e2 = (E) this.A03[A012]) == A04) {
            return e;
        }
        return e2;
    }

    public final void A02(long j, E e) {
        long[] jArr = this.A02;
        int i = this.A00;
        int A012 = AnonymousClass02m.A01(jArr, i, j);
        if (A012 >= 0) {
            this.A03[A012] = e;
            return;
        }
        int i2 = A012 ^ -1;
        if (i2 < i) {
            Object[] objArr = this.A03;
            if (objArr[i2] == A04) {
                jArr[i2] = j;
                objArr[i2] = e;
                return;
            }
        }
        if (this.A01 && i >= jArr.length) {
            A00(this);
            i = this.A00;
            i2 = AnonymousClass02m.A01(jArr, i, j) ^ -1;
        }
        int length = jArr.length;
        if (i >= length) {
            int i3 = (i + 1) << 3;
            int i4 = 4;
            while (true) {
                int i5 = (1 << i4) - 12;
                if (i3 > i5) {
                    i4++;
                    if (i4 >= 32) {
                        break;
                    }
                } else {
                    i3 = i5;
                    break;
                }
            }
            int i6 = i3 >> 3;
            long[] jArr2 = new long[i6];
            Object[] objArr2 = new Object[i6];
            System.arraycopy(jArr, 0, jArr2, 0, length);
            Object[] objArr3 = this.A03;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.A02 = jArr2;
            jArr = jArr2;
            this.A03 = objArr2;
        }
        int i7 = this.A00 - i2;
        if (i7 != 0) {
            int i8 = i2 + 1;
            System.arraycopy(jArr, i2, jArr, i8, i7);
            Object[] objArr4 = this.A03;
            System.arraycopy(objArr4, i2, objArr4, i8, this.A00 - i2);
        }
        this.A02[i2] = j;
        this.A03[i2] = e;
        this.A00++;
    }

    public final String toString() {
        if (this.A01) {
            A00(this);
        }
        int i = this.A00;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.A00; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            if (this.A01) {
                A00(this);
            }
            sb.append(this.A02[i2]);
            sb.append('=');
            if (this.A01) {
                A00(this);
            }
            Object obj = this.A03[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass02n() {
        int i = 80;
        int i2 = 4;
        while (true) {
            int i3 = (1 << i2) - 12;
            if (80 > i3) {
                i2++;
                if (i2 >= 32) {
                    break;
                }
            } else {
                i = i3;
                break;
            }
        }
        int i4 = i >> 3;
        this.A02 = new long[i4];
        this.A03 = new Object[i4];
    }

    @Override // java.lang.Object
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        try {
            AnonymousClass02n r1 = (AnonymousClass02n) super.clone();
            r1.A02 = (long[]) this.A02.clone();
            r1.A03 = (Object[]) this.A03.clone();
            return r1;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
