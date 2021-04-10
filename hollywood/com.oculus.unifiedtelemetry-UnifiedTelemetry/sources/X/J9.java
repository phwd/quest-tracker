package X;

import java.lang.reflect.Array;

public final class J9 {
    public int A00;
    public JJ A01;
    public JJ A02;
    public Object[] A03;

    public static final void A00(J9 j9, Object obj, int i, Object[] objArr, int i2) {
        int i3 = 0;
        for (JJ jj = j9.A01; jj != null; jj = jj.A00) {
            Object[] objArr2 = jj.A01;
            int length = objArr2.length;
            System.arraycopy(objArr2, 0, obj, i3, length);
            i3 += length;
        }
        System.arraycopy(objArr, 0, obj, i3, i2);
        int i4 = i3 + i2;
        if (i4 != i) {
            StringBuilder sb = new StringBuilder("Should have gotten ");
            sb.append(i);
            sb.append(" entries, got ");
            sb.append(i4);
            throw new IllegalStateException(sb.toString());
        }
    }

    public final Object[] A01() {
        JJ jj = this.A02;
        if (jj != null) {
            this.A03 = jj.A01;
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
        JJ jj = new JJ(objArr);
        if (this.A01 == null) {
            this.A02 = jj;
            this.A01 = jj;
        } else {
            JJ jj2 = this.A02;
            if (jj2.A00 == null) {
                jj2.A00 = jj;
                this.A02 = jj;
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
        JJ jj = this.A02;
        if (jj != null) {
            this.A03 = jj.A01;
        }
        this.A02 = null;
        this.A01 = null;
        this.A00 = 0;
        return tArr;
    }
}
