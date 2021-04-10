package X;

/* renamed from: X.065  reason: invalid class name */
public final class AnonymousClass065<E> implements Cloneable {
    public static final Object A04 = new Object();
    public int A00;
    public boolean A01 = false;
    public long[] A02;
    public Object[] A03;

    private void A00() {
        int i = this.A00;
        long[] jArr = this.A02;
        Object[] objArr = this.A03;
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
        this.A01 = false;
        this.A00 = i2;
    }

    public final E A01(long j, E e) {
        E e2;
        int A012 = AnonymousClass064.A01(this.A02, this.A00, j);
        if (A012 < 0 || (e2 = (E) this.A03[A012]) == A04) {
            return e;
        }
        return e2;
    }

    public final void A02(long j, E e) {
        long[] jArr = this.A02;
        long[] jArr2 = jArr;
        int i = this.A00;
        int i2 = i;
        int A012 = AnonymousClass064.A01(jArr, i, j);
        if (A012 >= 0) {
            this.A03[A012] = e;
            return;
        }
        int i3 = A012 ^ -1;
        if (i3 < i) {
            Object[] objArr = this.A03;
            if (objArr[i3] == A04) {
                jArr[i3] = j;
                objArr[i3] = e;
                return;
            }
        }
        if (this.A01 && i >= jArr.length) {
            A00();
            i2 = this.A00;
            i3 = AnonymousClass064.A01(jArr, i2, j) ^ -1;
        }
        int length = jArr.length;
        if (i2 >= length) {
            int i4 = (i2 + 1) << 3;
            int i5 = 4;
            while (true) {
                int i6 = (1 << i5) - 12;
                if (i4 > i6) {
                    i5++;
                    if (i5 >= 32) {
                        break;
                    }
                } else {
                    i4 = i6;
                    break;
                }
            }
            int i7 = i4 >> 3;
            jArr2 = new long[i7];
            Object[] objArr2 = new Object[i7];
            System.arraycopy(jArr, 0, jArr2, 0, length);
            Object[] objArr3 = this.A03;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.A02 = jArr2;
            this.A03 = objArr2;
        }
        int i8 = this.A00 - i3;
        if (i8 != 0) {
            int i9 = i3 + 1;
            System.arraycopy(jArr2, i3, jArr2, i9, i8);
            Object[] objArr4 = this.A03;
            System.arraycopy(objArr4, i3, objArr4, i9, this.A00 - i3);
        }
        this.A02[i3] = j;
        this.A03[i3] = e;
        this.A00++;
    }

    public final String toString() {
        if (this.A01) {
            A00();
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
                A00();
            }
            sb.append(this.A02[i2]);
            sb.append('=');
            if (this.A01) {
                A00();
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

    public AnonymousClass065() {
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
            AnonymousClass065 r1 = (AnonymousClass065) super.clone();
            r1.A02 = (long[]) this.A02.clone();
            r1.A03 = (Object[]) this.A03.clone();
            return r1;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
