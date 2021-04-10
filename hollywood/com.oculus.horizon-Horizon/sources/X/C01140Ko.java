package X;

import com.facebook.common.objectpool.ObjectPool;
import java.lang.reflect.Array;

/* renamed from: X.0Ko  reason: invalid class name and case insensitive filesystem */
public final class C01140Ko<T> {
    public int A00;
    public long A01;
    public T[] A02;
    public final int A03;
    public final int A04;
    public final int A05;
    public final long A06;
    public final ObjectPool.Allocator<T> A07;
    public final AnonymousClass0LE A08;
    public final Class<T> A09;

    public static void A00(C01140Ko r4, int i) {
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) r4.A09, i));
        T[] tArr2 = r4.A02;
        System.arraycopy(tArr2, 0, tArr, 0, Math.min(tArr2.length, i));
        r4.A02 = tArr;
        r4.A00 = Math.min(r4.A00, i);
    }

    public C01140Ko(Class<T> cls, int i, int i2, int i3, long j, ObjectPool.Allocator<T> allocator, AnonymousClass0LE r10) {
        this.A09 = cls;
        int max = Math.max(i, 0);
        this.A05 = max;
        this.A04 = Math.max(max, i2);
        this.A03 = Math.max(i3, 1);
        this.A06 = j;
        this.A07 = allocator;
        this.A08 = r10;
        this.A02 = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, max));
    }
}
