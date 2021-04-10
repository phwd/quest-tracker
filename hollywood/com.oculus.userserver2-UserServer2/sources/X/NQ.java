package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class NQ<K, V> implements Qy<K, V> {
    @Override // X.Qy
    public final Map<K, Collection<V>> A0q() {
        if (this instanceof ImmutableMultimap) {
            return ((ImmutableMultimap) this).A00;
        }
        throw new AssertionError("should never be called");
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Qy) {
            return A0q().equals(((Qy) obj).A0q());
        }
        return false;
    }

    @Override // X.Qy
    public final Set<K> keySet() {
        if (!(this instanceof ImmutableMultimap)) {
            throw new AssertionError("unreachable");
        }
        ImmutableMap<K, ? extends ImmutableCollection<V>> immutableMap = ((ImmutableMultimap) this).A00;
        ImmutableSet<K> immutableSet = immutableMap.A02;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) immutableMap;
        RegularImmutableMap.KeySet keySet = new RegularImmutableMap.KeySet(regularImmutableMap, new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.A02, 0, regularImmutableMap.A00));
        immutableMap.A02 = keySet;
        return keySet;
    }

    @Override // X.Qy
    public final boolean A1C(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Collection<V> collection = A0q().get(obj);
        if (collection == null || !collection.contains(obj2)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return A0q().hashCode();
    }

    public final String toString() {
        return A0q().toString();
    }
}
