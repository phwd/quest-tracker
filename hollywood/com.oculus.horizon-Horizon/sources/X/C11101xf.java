package X;

import java.util.concurrent.CountDownLatch;

/* renamed from: X.1xf  reason: invalid class name and case insensitive filesystem */
public class C11101xf implements AbstractC11151xn {
    public final /* synthetic */ C11071xZ A00;
    public final /* synthetic */ CountDownLatch A01;

    public C11101xf(C11071xZ r1, CountDownLatch countDownLatch) {
        this.A00 = r1;
        this.A01 = countDownLatch;
    }

    @Override // X.AbstractC11151xn
    public final void A6B() {
        this.A01.countDown();
    }
}
