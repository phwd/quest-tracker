package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* renamed from: X.tr  reason: case insensitive filesystem */
public class C1151tr extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {
    public final /* synthetic */ AbstractMapBasedMultimap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1151tr(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, List list, C0358Ti ti) {
        super(abstractMapBasedMultimap, obj, list, ti);
        this.A00 = abstractMapBasedMultimap;
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        A01();
        boolean isEmpty = this.A00.isEmpty();
        ((List) this.A00).add(i, obj);
        this.A00.A00++;
        if (isEmpty) {
            A00();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.A00).addAll(i, collection);
        if (!addAll) {
            return addAll;
        }
        int size2 = this.A00.size();
        this.A00.A00 += size2 - size;
        if (size != 0) {
            return addAll;
        }
        A00();
        return addAll;
    }

    @Override // java.util.List
    public final Object get(int i) {
        A01();
        return ((List) this.A00).get(i);
    }

    public final int indexOf(Object obj) {
        A01();
        return ((List) this.A00).indexOf(obj);
    }

    public final int lastIndexOf(Object obj) {
        A01();
        return ((List) this.A00).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        A01();
        Object remove = ((List) this.A00).remove(i);
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A00;
        abstractMapBasedMultimap.A00--;
        A02();
        return remove;
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        A01();
        return ((List) this.A00).set(i, obj);
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        A01();
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A00;
        Object obj = this.A03;
        List subList = ((List) this.A00).subList(i, i2);
        C0358Ti ti = this.A02;
        if (ti == null) {
            ti = this;
        }
        if (subList instanceof RandomAccess) {
            return new C0151Dt(abstractMapBasedMultimap, obj, subList, ti);
        }
        return new C1151tr(abstractMapBasedMultimap, obj, subList, ti);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        A01();
        return new C1150tq(this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        A01();
        return new C1150tq(this, i);
    }
}
