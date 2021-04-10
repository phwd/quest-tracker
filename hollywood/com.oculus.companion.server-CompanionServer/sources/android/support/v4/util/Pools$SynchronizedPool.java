package android.support.v4.util;

public class Pools$SynchronizedPool<T> extends Pools$SimplePool<T> {
    private final Object mLock = new Object();

    public Pools$SynchronizedPool(int i) {
        super(i);
    }

    @Override // android.support.v4.util.Pools$SimplePool, android.support.v4.util.Pools$Pool
    public T acquire() {
        T t;
        synchronized (this.mLock) {
            t = (T) super.acquire();
        }
        return t;
    }

    @Override // android.support.v4.util.Pools$SimplePool, android.support.v4.util.Pools$Pool
    public boolean release(T t) {
        boolean release;
        synchronized (this.mLock) {
            release = super.release(t);
        }
        return release;
    }
}
