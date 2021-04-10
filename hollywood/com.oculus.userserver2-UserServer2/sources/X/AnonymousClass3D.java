package X;

/* renamed from: X.3D  reason: invalid class name */
public final class AnonymousClass3D<E> implements Cloneable {
    public static final Object A04 = new Object();
    public int A00;
    public boolean A01;
    public int[] A02;
    public Object[] A03;

    public static void A00(AnonymousClass3D r8) {
        int i = r8.A00;
        int[] iArr = r8.A02;
        Object[] objArr = r8.A03;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != A04) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        r8.A01 = false;
        r8.A00 = i2;
    }

    public final int A01() {
        if (this.A01) {
            A00(this);
        }
        return this.A00;
    }

    public final void A02(int i, E e) {
        int[] iArr = this.A02;
        int[] iArr2 = iArr;
        int i2 = this.A00;
        int i3 = i2;
        int A002 = AnonymousClass33.A00(iArr, i2, i);
        if (A002 >= 0) {
            this.A03[A002] = e;
            return;
        }
        int i4 = A002 ^ -1;
        if (i4 < i2) {
            Object[] objArr = this.A03;
            if (objArr[i4] == A04) {
                iArr[i4] = i;
                objArr[i4] = e;
                return;
            }
        }
        if (this.A01 && i2 >= iArr.length) {
            A00(this);
            i3 = this.A00;
            i4 = AnonymousClass33.A00(iArr, i3, i) ^ -1;
        }
        int length = iArr.length;
        if (i3 >= length) {
            int i5 = (i3 + 1) << 2;
            int i6 = 4;
            while (true) {
                int i7 = (1 << i6) - 12;
                if (i5 > i7) {
                    i6++;
                    if (i6 >= 32) {
                        break;
                    }
                } else {
                    i5 = i7;
                    break;
                }
            }
            int i8 = i5 >> 2;
            iArr2 = new int[i8];
            Object[] objArr2 = new Object[i8];
            System.arraycopy(iArr, 0, iArr2, 0, length);
            Object[] objArr3 = this.A03;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.A02 = iArr2;
            this.A03 = objArr2;
        }
        int i9 = this.A00 - i4;
        if (i9 != 0) {
            int i10 = i4 + 1;
            System.arraycopy(iArr2, i4, iArr2, i10, i9);
            Object[] objArr4 = this.A03;
            System.arraycopy(objArr4, i4, objArr4, i10, this.A00 - i4);
        }
        this.A02[i4] = i;
        this.A03[i4] = e;
        this.A00++;
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        try {
            AnonymousClass3D r1 = (AnonymousClass3D) super.clone();
            r1.A02 = (int[]) this.A02.clone();
            r1.A03 = (Object[]) this.A03.clone();
            return r1;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final String toString() {
        if (A01() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.A00 * 28);
        sb.append('{');
        for (int i = 0; i < this.A00; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (this.A01) {
                A00(this);
            }
            sb.append(this.A02[i]);
            sb.append('=');
            if (this.A01) {
                A00(this);
            }
            Object obj = this.A03[i];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass3D() {
        this(10);
    }

    public AnonymousClass3D(int i) {
        Object[] objArr;
        this.A01 = false;
        if (i == 0) {
            this.A02 = AnonymousClass33.A00;
            objArr = AnonymousClass33.A01;
        } else {
            int i2 = i << 2;
            int i3 = 4;
            while (true) {
                int i4 = (1 << i3) - 12;
                if (i2 > i4) {
                    i3++;
                    if (i3 >= 32) {
                        break;
                    }
                } else {
                    i2 = i4;
                    break;
                }
            }
            int i5 = i2 >> 2;
            this.A02 = new int[i5];
            objArr = new Object[i5];
        }
        this.A03 = objArr;
    }
}
