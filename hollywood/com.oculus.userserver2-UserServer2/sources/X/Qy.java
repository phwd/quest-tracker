package X;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public interface Qy<K, V> {
    Map<K, Collection<V>> A0q();

    boolean A1C(@NullableDecl @CompatibleWith("K") Object obj, @NullableDecl @CompatibleWith("V") Object obj2);

    Collection<V> A1b(@NullableDecl K k);

    Set<K> keySet();
}
