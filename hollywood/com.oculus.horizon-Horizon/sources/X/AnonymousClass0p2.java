package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0p2  reason: invalid class name */
public interface AnonymousClass0p2<K, V> extends Map<K, V> {
    @CanIgnoreReturnValue
    @NullableDecl
    V A2r(@NullableDecl K k, @NullableDecl V v);

    AnonymousClass0p2<V, K> A4s();
}
