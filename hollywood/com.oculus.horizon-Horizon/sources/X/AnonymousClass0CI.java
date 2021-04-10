package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ConcurrentMap;

@GwtCompatible
/* renamed from: X.0CI  reason: invalid class name */
public abstract class AnonymousClass0CI<K, V> extends AnonymousClass0eC<K, V> implements ConcurrentMap<K, V> {
    /* renamed from: A02 */
    public abstract ConcurrentMap<K, V> A01();

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public final V putIfAbsent(K k, V v) {
        return A01().putIfAbsent(k, v);
    }

    @CanIgnoreReturnValue
    public final boolean remove(Object obj, Object obj2) {
        return A01().remove(obj, obj2);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public final V replace(K k, V v) {
        return A01().replace(k, v);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public final boolean replace(K k, V v, V v2) {
        return A01().replace(k, v, v2);
    }
}
