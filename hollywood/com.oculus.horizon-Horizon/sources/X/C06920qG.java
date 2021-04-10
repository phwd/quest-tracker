package X;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0qG  reason: invalid class name and case insensitive filesystem */
public class C06920qG extends AnonymousClass0GE<Object>.BatchLock {
    @GuardedBy("this")
    public boolean A00 = false;
    public final /* synthetic */ C06910qF A01;

    public final synchronized void A02() {
        this.A00 = true;
    }

    public final void A03() {
    }

    public final boolean A06() {
        return true;
    }

    public final synchronized boolean A07() {
        return this.A00;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C06920qG(C06910qF r2, Object obj) {
        super(r2, obj);
        this.A01 = r2;
    }
}
