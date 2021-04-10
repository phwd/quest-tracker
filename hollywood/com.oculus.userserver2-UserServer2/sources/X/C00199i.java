package X;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import java.util.Iterator;

/* renamed from: X.9i  reason: invalid class name and case insensitive filesystem */
public class C00199i extends AbstractC0104Mf<E> {
    public final /* synthetic */ AnonymousClass6q A00;

    public C00199i(AnonymousClass6q r1) {
        this.A00 = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return new SX((TreeMultiset) ((C00050s) this.A00).A00);
    }

    public final int size() {
        return ((C00050s) this.A00).A00.entrySet().size();
    }
}
