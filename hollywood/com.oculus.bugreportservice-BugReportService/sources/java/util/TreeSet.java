package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TreeSet extends AbstractSet implements NavigableSet, Cloneable, Serializable {
    private static final Object PRESENT = new Object();
    private static final long serialVersionUID = -2479143000061671589L;
    private transient NavigableMap m;

    TreeSet(NavigableMap navigableMap) {
        this.m = navigableMap;
    }

    public TreeSet() {
        this(new TreeMap());
    }

    public TreeSet(Comparator comparator) {
        this(new TreeMap(comparator));
    }

    public TreeSet(Collection collection) {
        this();
        addAll(collection);
    }

    public TreeSet(SortedSet sortedSet) {
        this(sortedSet.comparator());
        addAll(sortedSet);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.util.NavigableSet, java.lang.Iterable
    public Iterator iterator() {
        return this.m.navigableKeySet().iterator();
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
    public boolean contains(Object obj) {
        return this.m.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        return this.m.put(obj, PRESENT) == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        return this.m.remove(obj) == PRESENT;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.m.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        SortedSet sortedSet;
        TreeMap treeMap;
        Comparator comparator;
        Comparator comparator2;
        if (this.m.size() == 0 && collection.size() > 0 && (collection instanceof SortedSet)) {
            NavigableMap navigableMap = this.m;
            if ((navigableMap instanceof TreeMap) && ((comparator = (sortedSet = (SortedSet) collection).comparator()) == (comparator2 = (treeMap = (TreeMap) navigableMap).comparator()) || (comparator != null && comparator.equals(comparator2)))) {
                treeMap.addAllForTreeSet(sortedSet, PRESENT);
                return true;
            }
        }
        return super.addAll(collection);
    }

    @Override // java.util.SortedSet
    public Comparator comparator() {
        return this.m.comparator();
    }

    @Override // java.util.SortedSet
    public Object first() {
        return this.m.firstKey();
    }

    public Object clone() {
        try {
            TreeSet treeSet = (TreeSet) super.clone();
            treeSet.m = new TreeMap(this.m);
            return treeSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
