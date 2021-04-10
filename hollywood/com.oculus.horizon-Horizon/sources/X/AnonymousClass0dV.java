package X;

import X.AbstractC07000ql;
import com.google.j2objc.annotations.Weak;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: X.0dV  reason: invalid class name */
public final class AnonymousClass0dV<K, V, E extends AbstractC07000ql<K, V, E>> extends WeakReference<V> implements AbstractC07040qr<K, V, E> {
    @Weak
    public final E A00;

    public AnonymousClass0dV(ReferenceQueue<V> referenceQueue, V v, E e) {
        super(v, referenceQueue);
        this.A00 = e;
    }

    @Override // X.AbstractC07040qr
    public final AbstractC07040qr<K, V, E> A1u(ReferenceQueue<V> referenceQueue, E e) {
        return new AnonymousClass0dV(referenceQueue, get(), e);
    }

    @Override // X.AbstractC07040qr
    public final E A3O() {
        return this.A00;
    }
}
