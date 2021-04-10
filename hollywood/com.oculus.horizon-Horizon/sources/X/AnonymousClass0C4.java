package X;

import com.google.common.collect.MapMakerInternalMap;
import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0C4  reason: invalid class name */
public final class AnonymousClass0C4<K, V> extends AbstractC03630dp<K, V, AnonymousClass0C4<K, V>> implements AnonymousClass0dW<K, V, AnonymousClass0C4<K, V>> {
    public volatile AbstractC07040qr<K, V, AnonymousClass0C4<K, V>> A00 = ((AbstractC07040qr<K, V, AnonymousClass0C4<K, V>>) MapMakerInternalMap.A07);

    @Override // X.AbstractC07000ql
    public final V getValue() {
        return this.A00.get();
    }

    public AnonymousClass0C4(ReferenceQueue<K> referenceQueue, K k, int i, @NullableDecl AnonymousClass0C4<K, V> r5) {
        super(referenceQueue, k, i, r5);
    }

    @Override // X.AnonymousClass0dW
    public final AbstractC07040qr<K, V, AnonymousClass0C4<K, V>> A4f() {
        return this.A00;
    }
}
