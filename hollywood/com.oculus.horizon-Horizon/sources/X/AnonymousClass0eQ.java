package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0eQ  reason: invalid class name */
public class AnonymousClass0eQ extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
    public final /* synthetic */ AbstractMapBasedMultimap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0eQ(@NullableDecl AbstractMapBasedMultimap abstractMapBasedMultimap, K k, Set<V> set) {
        super(abstractMapBasedMultimap, k, set);
        this.A00 = abstractMapBasedMultimap;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean A03 = C07190ra.A03((Set) this.A00, collection);
        if (!A03) {
            return A03;
        }
        int size2 = this.A00.size();
        this.A00.A00 += size2 - size;
        A00();
        return A03;
    }
}
