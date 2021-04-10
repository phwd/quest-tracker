package X;

import X.AbstractC07000ql;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0dq  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03640dq<K, V, E extends AbstractC07000ql<K, V, E>> implements AbstractC07000ql<K, V, E> {
    @NullableDecl
    public final E A00;
    public final int A01;
    public final K A02;

    public AbstractC03640dq(K k, int i, @NullableDecl E e) {
        this.A02 = k;
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
        return this.A02;
    }
}
