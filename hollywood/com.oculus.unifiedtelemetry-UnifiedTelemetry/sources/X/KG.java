package X;

import com.facebook.common.objectpool.ObjectPool;
import java.lang.reflect.Array;

public final class KG<T> {
    public int A00;
    public long A01;
    public T[] A02;
    public final int A03;
    public final int A04;
    public final int A05;
    public final long A06;
    public final ObjectPool.Allocator<T> A07;
    public final AbstractC0106Kc A08;
    public final Class<T> A09;

    public static void A00(KG kg, int i) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) kg.A09, i));
        T[] tArr2 = kg.A02;
        System.arraycopy(tArr2, 0, tArr, 0, Math.min(tArr2.length, i));
        kg.A02 = tArr;
        kg.A00 = Math.min(kg.A00, i);
    }

    public KG(Class<T> cls, int i, int i2, int i3, long j, ObjectPool.Allocator<T> allocator, AbstractC0106Kc kc) {
        this.A09 = cls;
        int max = Math.max(i, 0);
        this.A05 = max;
        this.A04 = Math.max(max, i2);
        this.A03 = Math.max(i3, 1);
        this.A06 = j;
        this.A07 = allocator;
        this.A08 = kc;
        this.A02 = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, max));
    }
}
