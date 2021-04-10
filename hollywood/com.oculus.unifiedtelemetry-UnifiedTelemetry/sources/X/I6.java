package X;

import com.facebook.common.jniexecutors.PooledNativeRunnable;

public class I6 extends YN<PooledNativeRunnable> {
    public I6() {
        super(PooledNativeRunnable.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.YN
    public final PooledNativeRunnable A00() {
        return new PooledNativeRunnable();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.YN
    public final void A01(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mNativeExecutor = null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.YN
    public final void A02(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mHybridData = null;
    }
}
