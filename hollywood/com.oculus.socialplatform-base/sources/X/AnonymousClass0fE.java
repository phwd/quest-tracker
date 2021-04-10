package X;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0fE  reason: invalid class name */
public final class AnonymousClass0fE<K, V> extends AnonymousClass0tY<K, V> {
    @NullableDecl
    public AnonymousClass0fE<K, V> A00;
    @NullableDecl
    public AnonymousClass0fE<K, V> A01;
    @NullableDecl
    public V A02;
    @NullableDecl
    public AnonymousClass0fE<K, V> A03;
    @NullableDecl
    public AnonymousClass0fE<K, V> A04;
    @NullableDecl
    public final K A05;

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final V setValue(@NullableDecl V v) {
        V v2 = this.A02;
        this.A02 = v;
        return v2;
    }

    public AnonymousClass0fE(@NullableDecl K k, @NullableDecl V v) {
        this.A05 = k;
        this.A02 = v;
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final K getKey() {
        return this.A05;
    }

    @Override // X.AnonymousClass0tY, java.util.Map.Entry
    public final V getValue() {
        return this.A02;
    }
}
