package X;

import X.AbstractC07000ql;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0dp  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03630dp<K, V, E extends AbstractC07000ql<K, V, E>> extends WeakReference<K> implements AbstractC07000ql<K, V, E> {
    @NullableDecl
    public final E A00;
    public final int A01;

    public AbstractC03630dp(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl E e) {
        super(k, referenceQueue);
        this.A01 = i;
        this.A00 = e;
    }

    @Override // X.AbstractC07000ql
    public final int A3V() {
        return this.A01;
    }

    @Override // X.AbstractC07000ql
    public final E A40() {
        return this.A00;
    }

    @Override // X.AbstractC07000ql
    public final K getKey() {
        return (K) get();
    }
}
