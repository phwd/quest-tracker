package defpackage;

import java.util.concurrent.locks.Lock;

/* renamed from: Ql  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1006Ql implements AutoCloseable {
    public final Lock F;
    public boolean G;

    public C1006Ql(Lock lock, boolean z) {
        this.F = lock;
        this.G = z;
    }

    public static C1006Ql Y(Lock lock) {
        lock.lock();
        return new C1006Ql(lock, true);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.G) {
            this.G = false;
            this.F.unlock();
            return;
        }
        throw new IllegalStateException("mLock isn't locked.");
    }
}
