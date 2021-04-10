package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0eN  reason: invalid class name */
public abstract class AnonymousClass0eN<K, V> implements AbstractC07090r4<K, V> {
    @MonotonicNonNullDecl
    public transient Map<K, Collection<V>> A00;
    @MonotonicNonNullDecl
    public transient Set<K> A01;

    public abstract Map<K, Collection<V>> A00();

    public abstract Set<K> A01();

    @Override // X.AbstractC07090r4
    public Map<K, Collection<V>> A1H() {
        Map<K, Collection<V>> map = this.A00;
        if (map != null) {
            return map;
        }
        Map<K, Collection<V>> A002 = A00();
        this.A00 = A002;
        return A002;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractC07090r4) {
            return A1H().equals(((AbstractC07090r4) obj).A1H());
        }
        return false;
    }

    @Override // X.AbstractC07090r4
    public Set<K> keySet() {
        Set<K> set = this.A01;
        if (set != null) {
            return set;
        }
        Set<K> A012 = A01();
        this.A01 = A012;
        return A012;
    }

    @Override // X.AbstractC07090r4
    public final boolean A1s(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Collection<V> collection = A1H().get(obj);
        if (collection == null || !collection.contains(obj2)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC07090r4
    @CanIgnoreReturnValue
    public boolean A7h(@NullableDecl K k, @NullableDecl V v) {
        return A2u(k).add(v);
    }

    public final int hashCode() {
        return A1H().hashCode();
    }

    public final String toString() {
        return A1H().toString();
    }
}
