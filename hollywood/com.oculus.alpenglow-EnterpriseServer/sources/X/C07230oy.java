package X;

import java.lang.reflect.Array;

/* renamed from: X.0oy  reason: invalid class name and case insensitive filesystem */
public final class C07230oy {
    public int A00;
    public C07220ox A01;
    public C07220ox A02;
    public Object[] A03;

    public static final void A00(C07230oy r4, Object obj, int i, Object[] objArr, int i2) {
        int i3 = 0;
        for (C07220ox r0 = r4.A01; r0 != null; r0 = r0.A00) {
            Object[] objArr2 = r0.A01;
            int length = objArr2.length;
            System.arraycopy(objArr2, 0, obj, i3, length);
            i3 += length;
        }
        System.arraycopy(objArr, 0, obj, i3, i2);
        int i4 = i3 + i2;
        if (i4 != i) {
            throw new IllegalStateException(AnonymousClass006.A03("Should have gotten ", i, " entries, got ", i4));
        }
    }

    public final Object[] A01() {
        C07220ox r0 = this.A02;
        if (r0 != null) {
            this.A03 = r0.A01;
        }
        this.A02 = null;
        this.A01 = null;
        this.A00 = 0;
        Object[] objArr = this.A03;
        if (objArr == null) {
            return new Object[12];
        }
        return objArr;
    }

    public final Object[] A02(Object[] objArr) {
        int i;
        C07220ox r2 = new C07220ox(objArr);
        if (this.A01 == null) {
            this.A02 = r2;
            this.A01 = r2;
        } else {
            C07220ox r1 = this.A02;
            if (r1.A00 == null) {
                r1.A00 = r2;
                this.A02 = r2;
            } else {
                throw new IllegalStateException();
            }
        }
        int length = objArr.length;
        this.A00 += length;
        if (length < 16384) {
            i = length + length;
        } else {
            i = length + (length >> 2);
        }
        return new Object[i];
    }

    public final <T> T[] A03(Object[] objArr, int i, Class<T> cls) {
        int i2 = this.A00 + i;
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        A00(this, tArr, i2, objArr, i);
        C07220ox r0 = this.A02;
        if (r0 != null) {
            this.A03 = r0.A01;
        }
        this.A02 = null;
        this.A01 = null;
        this.A00 = 0;
        return tArr;
    }
}
