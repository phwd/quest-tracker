package X;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* renamed from: X.0YL  reason: invalid class name */
public abstract class AnonymousClass0YL<K, V> implements AbstractC07440t7<K, V> {
    public abstract Map<K, Collection<V>> A03();

    public abstract Set<K> A04();

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractC07440t7) {
            return A18().equals(((AbstractC07440t7) obj).A18());
        }
        return false;
    }

    @Override // X.AbstractC07440t7
    public Map<K, Collection<V>> A18() {
        A03();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC07440t7
    public final boolean A1q(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Collection<V> collection = A18().get(obj);
        if (collection == null || !collection.contains(obj2)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return A18().hashCode();
    }

    @Override // X.AbstractC07440t7
    public Set<K> keySet() {
        A04();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final String toString() {
        return A18().toString();
    }
}
