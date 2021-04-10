package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.Th  reason: case insensitive filesystem */
public class C0357Th implements Iterator {
    public final Collection A00;
    public final Iterator A01;
    public final /* synthetic */ C0358Ti A02;

    public static final void A00(C0357Th th) {
        C0358Ti ti = th.A02;
        ti.A01();
        if (ti.A00 != th.A00) {
            throw new ConcurrentModificationException();
        }
    }

    public final void remove() {
        this.A01.remove();
        C0358Ti ti = this.A02;
        AbstractMapBasedMultimap abstractMapBasedMultimap = ti.A04;
        abstractMapBasedMultimap.A00--;
        ti.A02();
    }

    public final boolean hasNext() {
        A00(this);
        return this.A01.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        A00(this);
        return this.A01.next();
    }

    public C0357Th(C0358Ti ti) {
        Iterator it;
        this.A02 = ti;
        Collection collection = ti.A00;
        this.A00 = collection;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.A01 = it;
    }

    public C0357Th(C0358Ti ti, Iterator it) {
        this.A02 = ti;
        this.A00 = ti.A00;
        this.A01 = it;
    }
}
