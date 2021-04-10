package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0t7  reason: invalid class name and case insensitive filesystem */
public interface AbstractC07440t7<K, V> {
    Map<K, Collection<V>> A18();

    boolean A1q(@NullableDecl @CompatibleWith("K") Object obj, @NullableDecl @CompatibleWith("V") Object obj2);

    Collection<V> A2s(@NullableDecl K k);

    Set<K> keySet();
}
