package X;

import java.lang.reflect.Array;

public final class CN {
    public int A00;
    public long A01;
    public Object[] A02;
    public final int A03;
    public final int A04;
    public final int A05;
    public final long A06;
    public final C0829jR A07;
    public final Ca A08;
    public final Class A09;

    public static void A00(CN cn, int i) {
        Object[] objArr = (Object[]) Array.newInstance(cn.A09, i);
        Object[] objArr2 = cn.A02;
        System.arraycopy(objArr2, 0, objArr, 0, Math.min(objArr2.length, i));
        cn.A02 = objArr;
        cn.A00 = Math.min(cn.A00, i);
    }

    public CN(Class cls, int i, int i2, int i3, long j, C0829jR jRVar, Ca ca) {
        this.A09 = cls;
        int max = Math.max(i, 0);
        this.A05 = max;
        this.A04 = Math.max(max, i2);
        this.A03 = Math.max(i3, 1);
        this.A06 = j;
        this.A07 = jRVar;
        this.A08 = ca;
        this.A02 = (Object[]) Array.newInstance(cls, max);
    }
}
