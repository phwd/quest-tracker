package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0eC  reason: invalid class name */
public abstract class AnonymousClass0eC<K, V> extends AbstractC06680pa implements Map<K, V> {
    /* renamed from: A01 */
    public abstract Map<K, V> A00();

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this || A00().equals(obj)) {
            return true;
        }
        return false;
    }

    public final void clear() {
        A00().clear();
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        return A00().containsKey(obj);
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        return A00().containsValue(obj);
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return A00().entrySet();
    }

    @Override // java.util.Map
    public final V get(@NullableDecl Object obj) {
        return A00().get(obj);
    }

    public final int hashCode() {
        return A00().hashCode();
    }

    public final boolean isEmpty() {
        return A00().isEmpty();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return A00().keySet();
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    public final V put(K k, V v) {
        return A00().put(k, v);
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        A00().putAll(map);
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    public final V remove(Object obj) {
        return A00().remove(obj);
    }

    public final int size() {
        return A00().size();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return A00().values();
    }
}
