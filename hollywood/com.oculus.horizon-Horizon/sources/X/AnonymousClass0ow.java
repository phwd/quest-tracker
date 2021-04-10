package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0ow  reason: invalid class name */
public class AnonymousClass0ow extends AbstractCollection<V> {
    public Collection<V> A00;
    @NullableDecl
    public final K A01;
    public final /* synthetic */ AbstractMapBasedMultimap A02;

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Collection<V> */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass0ow(@NullableDecl K k, Collection<V> collection, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
        this.A02 = k;
        this.A01 = collection;
        this.A00 = wrappedCollection;
    }

    public final void A00() {
        if (this.A00.isEmpty()) {
            this.A02.A01.remove(this.A01);
        }
    }

    public final void A01() {
        Collection<V> collection;
        if (this.A00.isEmpty() && (collection = this.A02.A01.get(this.A01)) != null) {
            this.A00 = collection;
        }
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        A01();
        return this.A00.equals(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            int size = size();
            boolean retainAll = this.A00.retainAll(collection);
            if (retainAll) {
                int size2 = this.A00.size();
                this.A02.A00 += size2 - size;
                A00();
            }
            return retainAll;
        }
        throw null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(V v) {
        A01();
        boolean isEmpty = this.A00.isEmpty();
        boolean add = this.A00.add(v);
        if (add) {
            AbstractMapBasedMultimap abstractMapBasedMultimap = this.A02;
            abstractMapBasedMultimap.A00++;
            if (isEmpty) {
                abstractMapBasedMultimap.A01.put(this.A01, this.A00);
            }
        }
        return add;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection<? extends V> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.A00.addAll(collection);
        if (!addAll) {
            return addAll;
        }
        int size2 = this.A00.size();
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A02;
        abstractMapBasedMultimap.A00 += size2 - size;
        if (size != 0) {
            return addAll;
        }
        abstractMapBasedMultimap.A01.put(this.A01, this.A00);
        return addAll;
    }

    public final void clear() {
        int size = size();
        if (size != 0) {
            this.A00.clear();
            this.A02.A00 -= size;
            A00();
        }
    }

    public final boolean contains(Object obj) {
        A01();
        return this.A00.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection<?> collection) {
        A01();
        return this.A00.containsAll(collection);
    }

    public final int hashCode() {
        A01();
        return this.A00.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<V> iterator() {
        A01();
        return new AnonymousClass0ov(this);
    }

    public final boolean remove(Object obj) {
        A01();
        boolean remove = this.A00.remove(obj);
        if (remove) {
            AbstractMapBasedMultimap abstractMapBasedMultimap = this.A02;
            abstractMapBasedMultimap.A00--;
            A00();
        }
        return remove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.A00.removeAll(collection);
        if (!removeAll) {
            return removeAll;
        }
        int size2 = this.A00.size();
        this.A02.A00 += size2 - size;
        A00();
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
