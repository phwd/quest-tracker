package X;

import com.facebook.common.objectpool.ObjectPool;
import com.oculus.deviceconfigservice.MobileConfigAppAwareAccessorDecorator;

/* renamed from: X.0Kp  reason: invalid class name and case insensitive filesystem */
public final class C01150Kp<T> {
    public int A00 = 16;
    public int A01 = 1024;
    public int A02 = 16;
    public long A03 = MobileConfigAppAwareAccessorDecorator.MS_IN_ONE_MINUTE;
    public ObjectPool.Allocator<T> A04;
    public AnonymousClass0LE A05;
    public Class<T> A06;

    public C01150Kp(Class<T> cls, AnonymousClass0LE r4) {
        this.A06 = cls;
        this.A05 = r4;
    }
}
