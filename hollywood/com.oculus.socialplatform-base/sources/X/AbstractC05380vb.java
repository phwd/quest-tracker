package X;

import com.google.common.annotations.GwtCompatible;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

@GwtCompatible
/* renamed from: X.0vb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05380vb<K, V> extends AbstractMap<K, V> {
    @MonotonicNonNullDecl
    public transient Collection<V> A00;
    @MonotonicNonNullDecl
    public transient Set<Map.Entry<K, V>> A01;
    @MonotonicNonNullDecl
    public transient Set<K> A02;

    public abstract Set<Map.Entry<K, V>> A00();

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.A01;
        if (set != null) {
            return set;
        }
        Set<Map.Entry<K, V>> A002 = A00();
        this.A01 = A002;
        return A002;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.A02;
        if (set != null) {
            return set;
        }
        AnonymousClass0f6 r0 = new AnonymousClass0f6(this);
        this.A02 = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection<V> values() {
        Collection<V> collection = this.A00;
        if (collection != null) {
            return collection;
        }
        C05370va r0 = new C05370va(this);
        this.A00 = r0;
        return r0;
    }
}
