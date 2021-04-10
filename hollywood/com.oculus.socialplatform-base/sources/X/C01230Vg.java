package X;

import com.facebook.common.jniexecutors.PooledNativeRunnable;

/* renamed from: X.0Vg  reason: invalid class name and case insensitive filesystem */
public class C01230Vg extends C04980re<PooledNativeRunnable> {
    public C01230Vg() {
        super(PooledNativeRunnable.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.C04980re
    public final PooledNativeRunnable A00() {
        return new PooledNativeRunnable();
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.C04980re
    public final void A01(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mNativeExecutor = null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.C04980re
    public final void A02(PooledNativeRunnable pooledNativeRunnable) {
        pooledNativeRunnable.mHybridData = null;
    }
}
