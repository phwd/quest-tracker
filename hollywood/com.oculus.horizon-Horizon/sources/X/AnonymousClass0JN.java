package X;

import com.facebook.common.jniexecutors.PooledNativeRunnable;

/* renamed from: X.0JN  reason: invalid class name */
public class AnonymousClass0JN extends C06740pk<PooledNativeRunnable> {
    public AnonymousClass0JN() {
        super(PooledNativeRunnable.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.C06740pk
    public final PooledNativeRunnable A00() {
        return new PooledNativeRunnable();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.C06740pk
    public final void A01(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mNativeExecutor = null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.C06740pk
    public final void A02(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mHybridData = null;
    }
}
