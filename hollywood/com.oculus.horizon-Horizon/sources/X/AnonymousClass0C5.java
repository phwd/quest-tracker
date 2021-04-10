package X;

import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0C5  reason: invalid class name */
public final class AnonymousClass0C5<K, V> extends AbstractC03630dp<K, V, AnonymousClass0C5<K, V>> implements AbstractC03570dc<K, V, AnonymousClass0C5<K, V>> {
    @NullableDecl
    public volatile V A00 = null;

    public AnonymousClass0C5(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl AnonymousClass0C5<K, V> r5) {
        super(referenceQueue, k, i, r5);
    }

    @Override // X.AbstractC07000ql
    @NullableDecl
    public final V getValue() {
        return this.A00;
    }
}
