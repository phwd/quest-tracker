package X;

import com.google.common.collect.AbstractMapBasedMultiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;

/* renamed from: X.9l  reason: invalid class name */
public class AnonymousClass9l extends AbstractC0105Mg<E> {
    public final /* synthetic */ NO A00;

    public AnonymousClass9l(NO no) {
        this.A00 = no;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, X.AbstractC0105Mg, java.util.Set, java.lang.Iterable
    public final Iterator<E> iterator() {
        NO no = this.A00;
        if (!(no instanceof TreeMultiset)) {
            return new C0109Ne((AbstractMapBasedMultiset) no);
        }
        return new C0106Mk(no.A03());
    }
}
