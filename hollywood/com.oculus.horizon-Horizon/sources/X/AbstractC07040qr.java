package X;

import X.AbstractC07000ql;
import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0qr  reason: invalid class name and case insensitive filesystem */
public interface AbstractC07040qr<K, V, E extends AbstractC07000ql<K, V, E>> {
    AbstractC07040qr<K, V, E> A1u(ReferenceQueue<V> referenceQueue, E e);

    E A3O();

    void clear();

    @NullableDecl
    V get();
}
