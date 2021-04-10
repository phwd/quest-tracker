package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0r4  reason: invalid class name and case insensitive filesystem */
public interface AbstractC07090r4<K, V> {
    Map<K, Collection<V>> A1H();

    boolean A1s(@NullableDecl @CompatibleWith("K") Object obj, @NullableDecl @CompatibleWith("V") Object obj2);

    Collection<V> A2u(@NullableDecl K k);

    @CanIgnoreReturnValue
    boolean A7h(@NullableDecl K k, @NullableDecl V v);

    Set<K> keySet();
}
