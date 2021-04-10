package X;

import java.util.concurrent.CountDownLatch;

/* renamed from: X.f4  reason: case insensitive filesystem */
public final class C0676f4 implements AbstractC00517z {
    public final CountDownLatch A00 = new CountDownLatch(1);
    public volatile boolean A01;

    @Override // X.AbstractC00517z
    public final void A4R(boolean z) {
        this.A01 = z;
        this.A00.countDown();
    }
}
