package X;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.Yw  reason: case insensitive filesystem */
public class C0272Yw extends Fz<Object>.BatchLock {
    @GuardedBy("this")
    public boolean A00 = false;
    public final /* synthetic */ C0271Yv A01;

    public final String A01() {
        return "InProcessBatchLock";
    }

    public final void A04() {
    }

    public final synchronized void A05() {
        this.A00 = true;
    }

    public final void A06() {
    }

    public final synchronized boolean A09() {
        return this.A00;
    }

    public final boolean A0A() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0272Yw(C0271Yv yv, Object obj) {
        super(yv, obj);
        this.A01 = yv;
    }
}
