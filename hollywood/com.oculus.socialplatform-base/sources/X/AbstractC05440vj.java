package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0vj  reason: invalid class name and case insensitive filesystem */
public interface AbstractC05440vj<K, V> {
    Map<K, Collection<V>> A1T();

    Collection<V> A3M(@NullableDecl K k);

    @CanIgnoreReturnValue
    Collection<V> A96(@NullableDecl @CompatibleWith("K") Object obj);

    void clear();

    boolean containsKey(@NullableDecl @CompatibleWith("K") Object obj);

    boolean isEmpty();

    Set<K> keySet();

    int size();
}
