package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0td  reason: invalid class name and case insensitive filesystem */
public interface AbstractC05100td<K, V> extends Map<K, V> {
    AbstractC05100td<V, K> A5n();

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    V put(@NullableDecl K k, @NullableDecl V v);
}
