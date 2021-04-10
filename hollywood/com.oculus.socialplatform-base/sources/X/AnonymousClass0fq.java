package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0fq  reason: invalid class name */
public abstract class AnonymousClass0fq<K, V> implements AbstractC05440vj<K, V> {
    @MonotonicNonNullDecl
    public transient Collection<Map.Entry<K, V>> A00;
    @MonotonicNonNullDecl
    public transient Map<K, Collection<V>> A01;
    @MonotonicNonNullDecl
    public transient Set<K> A02;

    public abstract Collection<Map.Entry<K, V>> A03();

    public abstract Map<K, Collection<V>> A04();

    public abstract Set<K> A05();

    public Collection<Map.Entry<K, V>> A02() {
        Collection<Map.Entry<K, V>> collection = this.A00;
        if (collection != null) {
            return collection;
        }
        Collection<Map.Entry<K, V>> A03 = A03();
        this.A00 = A03;
        return A03;
    }

    @Override // X.AbstractC05440vj
    public final Map<K, Collection<V>> A1T() {
        Map<K, Collection<V>> map = this.A01;
        if (map != null) {
            return map;
        }
        Map<K, Collection<V>> A04 = A04();
        this.A01 = A04;
        return A04;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractC05440vj) {
            return A1T().equals(((AbstractC05440vj) obj).A1T());
        }
        return false;
    }

    @Override // X.AbstractC05440vj
    public final Set<K> keySet() {
        Set<K> set = this.A02;
        if (set != null) {
            return set;
        }
        Set<K> A05 = A05();
        this.A02 = A05;
        return A05;
    }

    public final int hashCode() {
        return A1T().hashCode();
    }

    @Override // X.AbstractC05440vj
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return A1T().toString();
    }
}
