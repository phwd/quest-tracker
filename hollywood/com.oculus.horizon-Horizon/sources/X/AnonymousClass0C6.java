package X;

import com.google.common.collect.MapMakerInternalMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0C6  reason: invalid class name */
public final class AnonymousClass0C6<K, V> extends AbstractC03640dq<K, V, AnonymousClass0C6<K, V>> implements AnonymousClass0dW<K, V, AnonymousClass0C6<K, V>> {
    public volatile AbstractC07040qr<K, V, AnonymousClass0C6<K, V>> A00 = ((AbstractC07040qr<K, V, AnonymousClass0C6<K, V>>) MapMakerInternalMap.A07);

    @Override // X.AbstractC07000ql
    public final V getValue() {
        return this.A00.get();
    }

    public AnonymousClass0C6(K k, int i, @NullableDecl AnonymousClass0C6<K, V> r4) {
        super(k, i, r4);
    }

    @Override // X.AnonymousClass0dW
    public final AbstractC07040qr<K, V, AnonymousClass0C6<K, V>> A4f() {
        return this.A00;
    }
}
