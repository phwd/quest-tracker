package X;

import com.facebook.common.objectpool.ObjectPool;

public final class KH<T> {
    public int A00 = 16;
    public int A01 = 1024;
    public int A02 = 16;
    public long A03 = 60000;
    public ObjectPool.Allocator<T> A04;
    public AbstractC0106Kc A05;
    public Class<T> A06;

    public KH(Class<T> cls, AbstractC0106Kc kc) {
        this.A06 = cls;
        this.A05 = kc;
    }
}
