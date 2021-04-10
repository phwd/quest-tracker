package X;

import com.facebook.common.jniexecutors.PooledNativeRunnable;

/* renamed from: X.0Lw  reason: invalid class name */
public class AnonymousClass0Lw extends C03250bu<PooledNativeRunnable> {
    public AnonymousClass0Lw() {
        super(PooledNativeRunnable.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.C03250bu
    public final PooledNativeRunnable A00() {
        return new PooledNativeRunnable();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.C03250bu
    public final void A01(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mNativeExecutor = null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.C03250bu
    public final void A02(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mHybridData = null;
    }
}
