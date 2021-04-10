package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0fd  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01620fd<K, V> extends AbstractC05120uD implements Map.Entry<K, V> {
    /* renamed from: A01 */
    public abstract Map.Entry<K, V> A00();

    public final boolean equals(@NullableDecl Object obj) {
        return A00().equals(obj);
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return A00().getKey();
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return A00().getValue();
    }

    public final int hashCode() {
        return A00().hashCode();
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        return A00().setValue(v);
    }
}
