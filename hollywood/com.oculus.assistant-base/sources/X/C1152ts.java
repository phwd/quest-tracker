package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.Set;

/* renamed from: X.ts  reason: case insensitive filesystem */
public final class C1152ts extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
    public final /* synthetic */ AbstractMapBasedMultimap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1152ts(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, Set set) {
        super(abstractMapBasedMultimap, obj, set, null);
        this.A00 = abstractMapBasedMultimap;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean A02 = UX.A02((Set) this.A00, collection);
        if (!A02) {
            return A02;
        }
        int size2 = this.A00.size();
        this.A00.A00 += size2 - size;
        A02();
        return A02;
    }
}
