package java.util.concurrent;

import android.support.v4.view.InputDeviceCompat;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CopyOnWriteArraySet<E> extends AbstractSet<E> implements Serializable {
    private static final long serialVersionUID = 5457747651344034263L;
    private final CopyOnWriteArrayList<E> al;

    public CopyOnWriteArraySet() {
        this.al = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArraySet(Collection<? extends E> c) {
        if (c.getClass() == CopyOnWriteArraySet.class) {
            this.al = new CopyOnWriteArrayList<>(((CopyOnWriteArraySet) c).al);
            return;
        }
        this.al = new CopyOnWriteArrayList<>();
        this.al.addAllAbsent(c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.al.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.al.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object o) {
        return this.al.contains(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return this.al.toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] a) {
        return (T[]) this.al.toArray(a);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.al.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object o) {
        return this.al.remove(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e) {
        return this.al.addIfAbsent(e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> c) {
        if (c instanceof Set) {
            return compareSets(this.al.getArray(), (Set) c) >= 0;
        }
        return this.al.containsAll(c);
    }

    private static int compareSets(Object[] snapshot, Set<?> set) {
        int len = snapshot.length;
        boolean[] matched = new boolean[len];
        int j = 0;
        for (Object x : set) {
            for (int i = j; i < len; i++) {
                if (!matched[i] && Objects.equals(x, snapshot[i])) {
                    matched[i] = true;
                    if (i == j) {
                        do {
                            j++;
                            if (j >= len) {
                                break;
                            }
                        } while (matched[j]);
                    }
                }
            }
            return -1;
        }
        if (j == len) {
            return 0;
        }
        return 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> c) {
        return this.al.addAllAbsent(c) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean removeAll(Collection<?> c) {
        return this.al.removeAll(c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> c) {
        return this.al.retainAll(c);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.al.iterator();
    }

    @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
    public boolean equals(Object o) {
        return o == this || ((o instanceof Set) && compareSets(this.al.getArray(), (Set) o) == 0);
    }

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super E> filter) {
        return this.al.removeIf(filter);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> action) {
        this.al.forEach(action);
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this.al.getArray(), (int) InputDeviceCompat.SOURCE_GAMEPAD);
    }
}
