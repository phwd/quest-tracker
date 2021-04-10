package X;

import com.facebook.common.objectpool.ObjectPool;

/* renamed from: X.0Kp  reason: invalid class name and case insensitive filesystem */
public final class C01670Kp<T> {
    public int A00 = 16;
    public int A01 = 1024;
    public int A02 = 16;
    public long A03 = 60000;
    public ObjectPool.Allocator<T> A04;
    public AnonymousClass0LK A05;
    public Class<T> A06;

    public C01670Kp(Class<T> cls, AnonymousClass0LK r4) {
        this.A06 = cls;
        this.A05 = r4;
    }
}
