package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.locks.ReentrantLock;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1u6  reason: invalid class name */
public final class AnonymousClass1u6 extends ReentrantLock {
    public final AnonymousClass0LF mNanoClock;

    public AnonymousClass1u6(AnonymousClass0LF r1) {
        this.mNanoClock = r1;
    }

    public final void A00() {
        super.lock();
    }
}
