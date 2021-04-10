package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedListMultimap;
import java.util.Iterator;

/* renamed from: X.tv  reason: case insensitive filesystem */
public class C1155tv extends UL<K, V> {
    public final /* synthetic */ AbstractC1156tw A00;

    public C1155tv(AbstractC1156tw twVar) {
        this.A00 = twVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        AbstractC1156tw twVar = this.A00;
        if (twVar instanceof LinkedListMultimap) {
            throw new AssertionError("should never be called");
        } else if (!(twVar instanceof ImmutableMultimap)) {
            return new C1148to((AbstractMapBasedMultimap) twVar);
        } else {
            return new C1161uD((ImmutableMultimap) twVar);
        }
    }
}
