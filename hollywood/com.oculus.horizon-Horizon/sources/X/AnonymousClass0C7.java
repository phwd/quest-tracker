package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0C7  reason: invalid class name */
public final class AnonymousClass0C7<K, V> extends AbstractC03640dq<K, V, AnonymousClass0C7<K, V>> implements AbstractC03570dc<K, V, AnonymousClass0C7<K, V>> {
    @NullableDecl
    public volatile V A00 = null;

    public AnonymousClass0C7(K k, int i, @NullableDecl AnonymousClass0C7<K, V> r4) {
        super(k, i, r4);
    }

    @Override // X.AbstractC07000ql
    @NullableDecl
    public final V getValue() {
        return this.A00;
    }
}
