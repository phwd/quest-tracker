package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* renamed from: X.Ti  reason: case insensitive filesystem */
public class C0358Ti extends AbstractCollection<V> {
    public Collection A00;
    public final Collection A01;
    public final C0358Ti A02;
    public final Object A03;
    public final /* synthetic */ AbstractMapBasedMultimap A04;

    public C0358Ti(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, Collection collection, C0358Ti ti) {
        Collection collection2;
        this.A04 = abstractMapBasedMultimap;
        this.A03 = obj;
        this.A00 = collection;
        this.A02 = ti;
        if (ti == null) {
            collection2 = null;
        } else {
            collection2 = ti.A00;
        }
        this.A01 = collection2;
    }

    public final void A00() {
        C0358Ti ti = this.A02;
        if (ti != null) {
            ti.A00();
        } else {
            this.A04.A01.put(this.A03, this.A00);
        }
    }

    public final void A01() {
        Collection collection;
        C0358Ti ti = this.A02;
        if (ti != null) {
            ti.A01();
            if (ti.A00 != this.A01) {
                throw new ConcurrentModificationException();
            }
        } else if (this.A00.isEmpty() && (collection = (Collection) this.A04.A01.get(this.A03)) != null) {
            this.A00 = collection;
        }
    }

    public final void A02() {
        C0358Ti ti = this.A02;
        if (ti != null) {
            ti.A02();
        } else if (this.A00.isEmpty()) {
            this.A04.A01.remove(this.A03);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        A01();
        return this.A00.equals(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        if (collection != null) {
            int size = size();
            boolean retainAll = this.A00.retainAll(collection);
            if (retainAll) {
                int size2 = this.A00.size();
                this.A04.A00 += size2 - size;
                A02();
            }
            return retainAll;
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        A01();
        boolean isEmpty = this.A00.isEmpty();
        boolean add = this.A00.add(obj);
        if (add) {
            this.A04.A00++;
            if (isEmpty) {
                A00();
            }
        }
        return add;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.A00.addAll(collection);
        if (!addAll) {
            return addAll;
        }
        int size2 = this.A00.size();
        this.A04.A00 += size2 - size;
        if (size != 0) {
            return addAll;
        }
        A00();
        return addAll;
    }

    public final void clear() {
        int size = size();
        if (size != 0) {
            this.A00.clear();
            this.A04.A00 -= size;
            A02();
        }
    }

    public final boolean contains(Object obj) {
        A01();
        return this.A00.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection collection) {
        A01();
        return this.A00.containsAll(collection);
    }

    public final int hashCode() {
        A01();
        return this.A00.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        A01();
        return new C0357Th(this);
    }

    public final boolean remove(Object obj) {
        A01();
        boolean remove = this.A00.remove(obj);
        if (remove) {
            AbstractMapBasedMultimap abstractMapBasedMultimap = this.A04;
            abstractMapBasedMultimap.A00--;
            A02();
        }
        return remove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.A00.removeAll(collection);
        if (!removeAll) {
            return removeAll;
        }
        int size2 = this.A00.size();
        this.A04.A00 += size2 - size;
        A02();
        return removeAll;
    }

    public final int size() {
        A01();
        return this.A00.size();
    }

    public final String toString() {
        A01();
        return this.A00.toString();
    }
}
