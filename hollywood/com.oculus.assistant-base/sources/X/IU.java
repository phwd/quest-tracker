package X;

import java.util.concurrent.locks.ReentrantLock;

public final class IU extends ReentrantLock {
    public final Cb mNanoClock;

    public IU(Cb cb) {
        this.mNanoClock = cb;
    }

    public final void A00() {
        super.lock();
    }
}
