package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class TreeSet<E> extends AbstractSet<E> implements NavigableSet<E>, Cloneable, Serializable {
    private static final Object PRESENT = new Object();
    private static final long serialVersionUID = -2479143000061671589L;
    private transient NavigableMap<E, Object> m;

    TreeSet(NavigableMap<E, Object> m2) {
        this.m = m2;
    }

    public TreeSet() {
        this(new TreeMap());
    }

    public TreeSet(Comparator<? super E> comparator) {
        this(new TreeMap(comparator));
    }

    public TreeSet(Collection<? extends E> c) {
        this();
        addAll(c);
    }

    public TreeSet(SortedSet<E> s) {
        this(s.comparator());
        addAll(s);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.util.NavigableSet, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.m.navigableKeySet().iterator();
    }

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return this.m.descendingKeySet().iterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return new TreeSet(this.m.descendingMap());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.m.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.m.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object o) {
        return this.m.containsKey(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e) {
        return this.m.put(e, PRESENT) == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object o) {
        return this.m.remove(o) == PRESENT;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.m.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> c) {
        SortedSet<? extends E> set;
        TreeMap<E, Object> map;
        Comparator<?> cc;
        Comparator<? super E> mc;
        if (this.m.size() == 0 && c.size() > 0 && (c instanceof SortedSet)) {
            NavigableMap<E, Object> navigableMap = this.m;
            if ((navigableMap instanceof TreeMap) && ((cc = (set = (SortedSet) c).comparator()) == (mc = (map = (TreeMap) navigableMap).comparator()) || (cc != null && cc.equals(mc)))) {
                map.addAllForTreeSet(set, PRESENT);
                return true;
            }
        }
        return super.addAll(c);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return new TreeSet(this.m.subMap(fromElement, fromInclusive, toElement, toInclusive));
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return new TreeSet(this.m.headMap(toElement, inclusive));
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return new TreeSet(this.m.tailMap(fromElement, inclusive));
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public SortedSet<E> headSet(E toElement) {
        return headSet(toElement, false);
    }

    @Override // java.util.SortedSet, java.util.NavigableSet
    public SortedSet<E> tailSet(E fromElement) {
        return tailSet(fromElement, true);
    }

    @Override // java.util.SortedSet
    public Comparator<? super E> comparator() {
        return this.m.comparator();
    }

    @Override // java.util.SortedSet
    public E first() {
        return this.m.firstKey();
    }

    @Override // java.util.SortedSet
    public E last() {
        return this.m.lastKey();
    }

    @Override // java.util.NavigableSet
    public E lower(E e) {
        return this.m.lowerKey(e);
    }

    @Override // java.util.NavigableSet
    public E floor(E e) {
        return this.m.floorKey(e);
    }

    @Override // java.util.NavigableSet
    public E ceiling(E e) {
        return this.m.ceilingKey(e);
    }

    @Override // java.util.NavigableSet
    public E higher(E e) {
        return this.m.higherKey(e);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        Map.Entry<E, ?> e = this.m.pollFirstEntry();
        if (e == null) {
            return null;
        }
        return e.getKey();
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        Map.Entry<E, ?> e = this.m.pollLastEntry();
        if (e == null) {
            return null;
        }
        return e.getKey();
    }

    public Object clone() {
        try {
            TreeSet<E> clone = (TreeSet) super.clone();
            clone.m = new TreeMap((SortedMap) this.m);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(this.m.comparator());
        s.writeInt(this.m.size());
        for (E e : this.m.keySet()) {
            s.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        TreeMap<E, Object> tm = new TreeMap<>((Comparator) s.readObject());
        this.m = tm;
        tm.readTreeSet(s.readInt(), s, PRESENT);
    }

    @Override // java.util.SortedSet, java.util.Collection, java.util.Set, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return TreeMap.keySpliteratorFor(this.m);
    }
}
