package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.AbstractMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class TreeMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, Serializable {
    private static final boolean BLACK = true;
    private static final boolean RED = false;
    private static final Object UNBOUNDED = new Object();
    private static final long serialVersionUID = 919286545866124006L;
    private final Comparator<? super K> comparator;
    private transient NavigableMap<K, V> descendingMap;
    private transient TreeMap<K, V>.EntrySet entrySet;
    private transient int modCount;
    private transient KeySet<K> navigableKeySet;
    private transient TreeMapEntry<K, V> root;
    private transient int size;

    public TreeMap() {
        this.size = 0;
        this.modCount = 0;
        this.comparator = null;
    }

    public TreeMap(Comparator<? super K> comparator2) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator2;
    }

    public TreeMap(Map<? extends K, ? extends V> m) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = null;
        putAll(m);
    }

    public TreeMap(SortedMap<K, ? extends V> m) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = m.comparator();
        try {
            buildFromSorted(m.size(), m.entrySet().iterator(), null, null);
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        for (TreeMapEntry<K, V> e = getFirstEntry(); e != null; e = successor(e)) {
            if (valEquals(value, e.value)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        TreeMapEntry<K, V> p = getEntry(key);
        if (p == null) {
            return null;
        }
        return p.value;
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return this.comparator;
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        return (K) key(getFirstEntry());
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        return (K) key(getLastEntry());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        Comparator<?> c;
        Comparator<?> comparator2;
        int mapSize = map.size();
        if (this.size != 0 || mapSize == 0 || !(map instanceof SortedMap) || ((c = ((SortedMap) map).comparator()) != (comparator2 = this.comparator) && (c == null || !c.equals(comparator2)))) {
            super.putAll(map);
            return;
        }
        this.modCount++;
        try {
            buildFromSorted(mapSize, map.entrySet().iterator(), null, null);
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getEntry(Object key) {
        if (this.comparator != null) {
            return getEntryUsingComparator(key);
        }
        if (key != null) {
            Comparable<? super K> k = (Comparable) key;
            TreeMapEntry<K, V> p = this.root;
            while (p != null) {
                int cmp = k.compareTo(p.key);
                if (cmp < 0) {
                    p = p.left;
                } else if (cmp <= 0) {
                    return p;
                } else {
                    p = p.right;
                }
            }
            return null;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getEntryUsingComparator(Object key) {
        Comparator<? super K> cpr = this.comparator;
        if (cpr == null) {
            return null;
        }
        TreeMapEntry<K, V> p = this.root;
        while (p != null) {
            int cmp = cpr.compare(key, p.key);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp <= 0) {
                return p;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getCeilingEntry(K key) {
        TreeMapEntry<K, V> p = this.root;
        while (p != null) {
            int cmp = compare(key, p.key);
            if (cmp < 0) {
                if (p.left == null) {
                    return p;
                }
                p = p.left;
            } else if (cmp <= 0) {
                return p;
            } else {
                if (p.right != null) {
                    p = p.right;
                } else {
                    TreeMapEntry<K, V> parent = p.parent;
                    TreeMapEntry<K, V> ch = p;
                    while (parent != null && ch == parent.right) {
                        ch = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getFloorEntry(K key) {
        TreeMapEntry<K, V> p = this.root;
        while (p != null) {
            int cmp = compare(key, p.key);
            if (cmp > 0) {
                if (p.right == null) {
                    return p;
                }
                p = p.right;
            } else if (cmp >= 0) {
                return p;
            } else {
                if (p.left != null) {
                    p = p.left;
                } else {
                    TreeMapEntry<K, V> parent = p.parent;
                    TreeMapEntry<K, V> ch = p;
                    while (parent != null && ch == parent.left) {
                        ch = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getHigherEntry(K key) {
        TreeMapEntry<K, V> p = this.root;
        while (p != null) {
            if (compare(key, p.key) < 0) {
                if (p.left == null) {
                    return p;
                }
                p = p.left;
            } else if (p.right != null) {
                p = p.right;
            } else {
                TreeMapEntry<K, V> parent = p.parent;
                TreeMapEntry<K, V> ch = p;
                while (parent != null && ch == parent.right) {
                    ch = parent;
                    parent = parent.parent;
                }
                return parent;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getLowerEntry(K key) {
        TreeMapEntry<K, V> p = this.root;
        while (p != null) {
            if (compare(key, p.key) > 0) {
                if (p.right == null) {
                    return p;
                }
                p = p.right;
            } else if (p.left != null) {
                p = p.left;
            } else {
                TreeMapEntry<K, V> parent = p.parent;
                TreeMapEntry<K, V> ch = p;
                while (parent != null && ch == parent.left) {
                    ch = parent;
                    parent = parent.parent;
                }
                return parent;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        int cmp;
        TreeMapEntry<K, V> parent;
        int cmp2;
        TreeMapEntry<K, V> t = this.root;
        if (t == null) {
            compare(key, key);
            this.root = new TreeMapEntry<>(key, value, null);
            this.size = 1;
            this.modCount++;
            return null;
        }
        Comparator<? super K> cpr = this.comparator;
        if (cpr != null) {
            do {
                parent = t;
                cmp = cpr.compare(key, t.key);
                if (cmp < 0) {
                    t = t.left;
                    continue;
                } else if (cmp <= 0) {
                    return t.setValue(value);
                } else {
                    t = t.right;
                    continue;
                }
            } while (t != null);
        } else if (key != null) {
            K k = key;
            do {
                cmp2 = k.compareTo(t.key);
                if (cmp2 < 0) {
                    t = t.left;
                    continue;
                } else if (cmp2 <= 0) {
                    return t.setValue(value);
                } else {
                    t = t.right;
                    continue;
                }
            } while (t != null);
            parent = t;
            cmp = cmp2;
        } else {
            throw new NullPointerException();
        }
        TreeMapEntry<K, V> e = new TreeMapEntry<>(key, value, parent);
        if (cmp < 0) {
            parent.left = e;
        } else {
            parent.right = e;
        }
        fixAfterInsertion(e);
        this.size++;
        this.modCount++;
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        TreeMapEntry<K, V> p = getEntry(key);
        if (p == null) {
            return null;
        }
        V oldValue = p.value;
        deleteEntry(p);
        return oldValue;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.modCount++;
        this.size = 0;
        this.root = null;
    }

    @Override // java.util.AbstractMap
    public Object clone() {
        try {
            TreeMap<?, ?> clone = (TreeMap) super.clone();
            clone.root = null;
            clone.size = 0;
            clone.modCount = 0;
            clone.entrySet = null;
            clone.navigableKeySet = null;
            clone.descendingMap = null;
            try {
                clone.buildFromSorted(this.size, entrySet().iterator(), null, null);
            } catch (IOException | ClassNotFoundException e) {
            }
            return clone;
        } catch (CloneNotSupportedException e2) {
            throw new InternalError(e2);
        }
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        return exportEntry(getFirstEntry());
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        return exportEntry(getLastEntry());
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollFirstEntry() {
        TreeMapEntry<K, V> p = getFirstEntry();
        Map.Entry<K, V> result = exportEntry(p);
        if (p != null) {
            deleteEntry(p);
        }
        return result;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollLastEntry() {
        TreeMapEntry<K, V> p = getLastEntry();
        Map.Entry<K, V> result = exportEntry(p);
        if (p != null) {
            deleteEntry(p);
        }
        return result;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K key) {
        return exportEntry(getLowerEntry(key));
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K key) {
        return (K) keyOrNull(getLowerEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K key) {
        return exportEntry(getFloorEntry(key));
    }

    @Override // java.util.NavigableMap
    public K floorKey(K key) {
        return (K) keyOrNull(getFloorEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K key) {
        return exportEntry(getCeilingEntry(key));
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K key) {
        return (K) keyOrNull(getCeilingEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K key) {
        return exportEntry(getHigherEntry(key));
    }

    @Override // java.util.NavigableMap
    public K higherKey(K key) {
        return (K) keyOrNull(getHigherEntry(key));
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set<K> keySet() {
        return navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> navigableKeySet() {
        KeySet<K> nks = this.navigableKeySet;
        if (nks != null) {
            return nks;
        }
        KeySet<K> keySet = new KeySet<>(this);
        this.navigableKeySet = keySet;
        return keySet;
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Collection<V> vs2 = new Values();
        this.values = vs2;
        return vs2;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set<Map.Entry<K, V>> entrySet() {
        TreeMap<K, V>.EntrySet es = this.entrySet;
        if (es != null) {
            return es;
        }
        TreeMap<K, V>.EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> descendingMap() {
        NavigableMap<K, V> km = this.descendingMap;
        if (km != null) {
            return km;
        }
        DescendingSubMap descendingSubMap = new DescendingSubMap(this, true, null, true, true, null, true);
        this.descendingMap = descendingSubMap;
        return descendingSubMap;
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return new AscendingSubMap(this, false, fromKey, fromInclusive, false, toKey, toInclusive);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return new AscendingSubMap(this, true, null, true, false, toKey, inclusive);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return new AscendingSubMap(this, false, fromKey, inclusive, true, null, true);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return subMap(fromKey, true, toKey, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> headMap(K toKey) {
        return headMap(toKey, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public SortedMap<K, V> tailMap(K fromKey) {
        return tailMap(fromKey, true);
    }

    @Override // java.util.Map
    public boolean replace(K key, V oldValue, V newValue) {
        TreeMapEntry<K, V> p = getEntry(key);
        if (p == null || !Objects.equals(oldValue, p.value)) {
            return false;
        }
        p.value = newValue;
        return true;
    }

    @Override // java.util.Map
    public V replace(K key, V value) {
        TreeMapEntry<K, V> p = getEntry(key);
        if (p == null) {
            return null;
        }
        V oldValue = p.value;
        p.value = value;
        return oldValue;
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        int expectedModCount = this.modCount;
        for (TreeMapEntry<K, V> e = getFirstEntry(); e != null; e = successor(e)) {
            action.accept(e.key, e.value);
            if (expectedModCount != this.modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        int expectedModCount = this.modCount;
        for (TreeMapEntry<K, V> e = getFirstEntry(); e != null; e = successor(e)) {
            e.value = (V) function.apply(e.key, e.value);
            if (expectedModCount != this.modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            TreeMap treeMap = TreeMap.this;
            return new ValueIterator(treeMap.getFirstEntry());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return TreeMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return TreeMap.this.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object o) {
            for (TreeMapEntry<K, V> e = TreeMap.this.getFirstEntry(); e != null; e = TreeMap.successor(e)) {
                if (TreeMap.valEquals(e.getValue(), o)) {
                    TreeMap.this.deleteEntry(e);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            TreeMap.this.clear();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<V> spliterator() {
            return new ValueSpliterator(TreeMap.this, null, null, 0, -1, 0);
        }
    }

    /* access modifiers changed from: package-private */
    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            TreeMap treeMap = TreeMap.this;
            return new EntryIterator(treeMap.getFirstEntry());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            Object value = entry.getValue();
            TreeMapEntry<K, V> p = TreeMap.this.getEntry(entry.getKey());
            if (p == null || !TreeMap.valEquals(p.getValue(), value)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            Object value = entry.getValue();
            TreeMapEntry<K, V> p = TreeMap.this.getEntry(entry.getKey());
            if (p == null || !TreeMap.valEquals(p.getValue(), value)) {
                return false;
            }
            TreeMap.this.deleteEntry(p);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return TreeMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            TreeMap.this.clear();
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return new EntrySpliterator(TreeMap.this, null, null, 0, -1, 0);
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> keyIterator() {
        return new KeyIterator(getFirstEntry());
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> descendingKeyIterator() {
        return new DescendingKeyIterator(getLastEntry());
    }

    /* access modifiers changed from: package-private */
    public static final class KeySet<E> extends AbstractSet<E> implements NavigableSet<E> {
        private final NavigableMap<E, ?> m;

        KeySet(NavigableMap<E, ?> map) {
            this.m = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.util.NavigableSet, java.lang.Iterable
        public Iterator<E> iterator() {
            NavigableMap<E, ?> navigableMap = this.m;
            if (navigableMap instanceof TreeMap) {
                return ((TreeMap) navigableMap).keyIterator();
            }
            return ((NavigableSubMap) navigableMap).keyIterator();
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            NavigableMap<E, ?> navigableMap = this.m;
            if (navigableMap instanceof TreeMap) {
                return ((TreeMap) navigableMap).descendingKeyIterator();
            }
            return ((NavigableSubMap) navigableMap).descendingKeyIterator();
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
        public void clear() {
            this.m.clear();
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

        @Override // java.util.SortedSet
        public E first() {
            return this.m.firstKey();
        }

        @Override // java.util.SortedSet
        public E last() {
            return this.m.lastKey();
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return this.m.comparator();
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

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            int oldSize = size();
            this.m.remove(o);
            return size() != oldSize;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            return new KeySet(this.m.subMap(fromElement, fromInclusive, toElement, toInclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            return new KeySet(this.m.headMap(toElement, inclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            return new KeySet(this.m.tailMap(fromElement, inclusive));
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

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new KeySet(this.m.descendingMap());
        }

        @Override // java.util.SortedSet, java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return TreeMap.keySpliteratorFor(this.m);
        }
    }

    abstract class PrivateEntryIterator<T> implements Iterator<T> {
        int expectedModCount;
        TreeMapEntry<K, V> lastReturned = null;
        TreeMapEntry<K, V> next;

        PrivateEntryIterator(TreeMapEntry<K, V> first) {
            this.expectedModCount = TreeMap.this.modCount;
            this.next = first;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> nextEntry() {
            TreeMapEntry<K, V> e = this.next;
            if (e == null) {
                throw new NoSuchElementException();
            } else if (TreeMap.this.modCount == this.expectedModCount) {
                this.next = TreeMap.successor(e);
                this.lastReturned = e;
                return e;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> prevEntry() {
            TreeMapEntry<K, V> e = this.next;
            if (e == null) {
                throw new NoSuchElementException();
            } else if (TreeMap.this.modCount == this.expectedModCount) {
                this.next = TreeMap.predecessor(e);
                this.lastReturned = e;
                return e;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            } else if (TreeMap.this.modCount == this.expectedModCount) {
                if (!(this.lastReturned.left == null || this.lastReturned.right == null)) {
                    this.next = this.lastReturned;
                }
                TreeMap.this.deleteEntry(this.lastReturned);
                this.expectedModCount = TreeMap.this.modCount;
                this.lastReturned = null;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    final class EntryIterator extends TreeMap<K, V>.PrivateEntryIterator {
        EntryIterator(TreeMapEntry<K, V> first) {
            super(first);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    final class ValueIterator extends TreeMap<K, V>.PrivateEntryIterator {
        ValueIterator(TreeMapEntry<K, V> first) {
            super(first);
        }

        @Override // java.util.Iterator
        public V next() {
            return nextEntry().value;
        }
    }

    /* access modifiers changed from: package-private */
    public final class KeyIterator extends TreeMap<K, V>.PrivateEntryIterator {
        KeyIterator(TreeMapEntry<K, V> first) {
            super(first);
        }

        @Override // java.util.Iterator
        public K next() {
            return nextEntry().key;
        }
    }

    /* access modifiers changed from: package-private */
    public final class DescendingKeyIterator extends TreeMap<K, V>.PrivateEntryIterator {
        DescendingKeyIterator(TreeMapEntry<K, V> first) {
            super(first);
        }

        @Override // java.util.Iterator
        public K next() {
            return prevEntry().key;
        }

        @Override // java.util.Iterator, java.util.TreeMap.PrivateEntryIterator
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            } else if (TreeMap.this.modCount == this.expectedModCount) {
                TreeMap.this.deleteEntry(this.lastReturned);
                this.lastReturned = null;
                this.expectedModCount = TreeMap.this.modCount;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final int compare(Object k1, Object k2) {
        Comparator<? super K> comparator2 = this.comparator;
        if (comparator2 == null) {
            return ((Comparable) k1).compareTo(k2);
        }
        return comparator2.compare(k1, k2);
    }

    static final boolean valEquals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }

    static <K, V> Map.Entry<K, V> exportEntry(TreeMapEntry<K, V> e) {
        if (e == null) {
            return null;
        }
        return new AbstractMap.SimpleImmutableEntry(e);
    }

    static <K, V> K keyOrNull(TreeMapEntry<K, V> e) {
        if (e == null) {
            return null;
        }
        return e.key;
    }

    static <K> K key(TreeMapEntry<K, ?> e) {
        if (e != null) {
            return e.key;
        }
        throw new NoSuchElementException();
    }

    /* access modifiers changed from: package-private */
    public static abstract class NavigableSubMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Serializable {
        private static final long serialVersionUID = 2765629423043303731L;
        transient NavigableMap<K, V> descendingMapView;
        transient NavigableSubMap<K, V>.EntrySetView entrySetView;
        final boolean fromStart;
        final K hi;
        final boolean hiInclusive;
        final K lo;
        final boolean loInclusive;
        final TreeMap<K, V> m;
        transient KeySet<K> navigableKeySetView;
        final boolean toEnd;

        /* access modifiers changed from: package-private */
        public abstract Iterator<K> descendingKeyIterator();

        /* access modifiers changed from: package-private */
        public abstract Iterator<K> keyIterator();

        /* access modifiers changed from: package-private */
        public abstract Spliterator<K> keySpliterator();

        /* access modifiers changed from: package-private */
        public abstract TreeMapEntry<K, V> subCeiling(K k);

        /* access modifiers changed from: package-private */
        public abstract TreeMapEntry<K, V> subFloor(K k);

        /* access modifiers changed from: package-private */
        public abstract TreeMapEntry<K, V> subHigher(K k);

        /* access modifiers changed from: package-private */
        public abstract TreeMapEntry<K, V> subHighest();

        /* access modifiers changed from: package-private */
        public abstract TreeMapEntry<K, V> subLower(K k);

        /* access modifiers changed from: package-private */
        public abstract TreeMapEntry<K, V> subLowest();

        NavigableSubMap(TreeMap<K, V> m2, boolean fromStart2, K lo2, boolean loInclusive2, boolean toEnd2, K hi2, boolean hiInclusive2) {
            if (fromStart2 || toEnd2) {
                if (!fromStart2) {
                    m2.compare(lo2, lo2);
                }
                if (!toEnd2) {
                    m2.compare(hi2, hi2);
                }
            } else if (m2.compare(lo2, hi2) > 0) {
                throw new IllegalArgumentException("fromKey > toKey");
            }
            this.m = m2;
            this.fromStart = fromStart2;
            this.lo = lo2;
            this.loInclusive = loInclusive2;
            this.toEnd = toEnd2;
            this.hi = hi2;
            this.hiInclusive = hiInclusive2;
        }

        /* access modifiers changed from: package-private */
        public final boolean tooLow(Object key) {
            if (this.fromStart) {
                return false;
            }
            int c = this.m.compare(key, this.lo);
            if (c < 0) {
                return true;
            }
            if (c != 0 || this.loInclusive) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public final boolean tooHigh(Object key) {
            if (this.toEnd) {
                return false;
            }
            int c = this.m.compare(key, this.hi);
            if (c > 0) {
                return true;
            }
            if (c != 0 || this.hiInclusive) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public final boolean inRange(Object key) {
            return !tooLow(key) && !tooHigh(key);
        }

        /* access modifiers changed from: package-private */
        public final boolean inClosedRange(Object key) {
            return (this.fromStart || this.m.compare(key, this.lo) >= 0) && (this.toEnd || this.m.compare(this.hi, key) >= 0);
        }

        /* access modifiers changed from: package-private */
        public final boolean inRange(Object key, boolean inclusive) {
            return inclusive ? inRange(key) : inClosedRange(key);
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absLowest() {
            TreeMapEntry<K, V> e;
            if (this.fromStart) {
                e = this.m.getFirstEntry();
            } else if (this.loInclusive) {
                e = this.m.getCeilingEntry(this.lo);
            } else {
                e = this.m.getHigherEntry(this.lo);
            }
            if (e == null || tooHigh(e.key)) {
                return null;
            }
            return e;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absHighest() {
            TreeMapEntry<K, V> e;
            if (this.toEnd) {
                e = this.m.getLastEntry();
            } else if (this.hiInclusive) {
                e = this.m.getFloorEntry(this.hi);
            } else {
                e = this.m.getLowerEntry(this.hi);
            }
            if (e == null || tooLow(e.key)) {
                return null;
            }
            return e;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absCeiling(K key) {
            if (tooLow(key)) {
                return absLowest();
            }
            TreeMapEntry<K, V> e = this.m.getCeilingEntry(key);
            if (e == null || tooHigh(e.key)) {
                return null;
            }
            return e;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absHigher(K key) {
            if (tooLow(key)) {
                return absLowest();
            }
            TreeMapEntry<K, V> e = this.m.getHigherEntry(key);
            if (e == null || tooHigh(e.key)) {
                return null;
            }
            return e;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absFloor(K key) {
            if (tooHigh(key)) {
                return absHighest();
            }
            TreeMapEntry<K, V> e = this.m.getFloorEntry(key);
            if (e == null || tooLow(e.key)) {
                return null;
            }
            return e;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absLower(K key) {
            if (tooHigh(key)) {
                return absHighest();
            }
            TreeMapEntry<K, V> e = this.m.getLowerEntry(key);
            if (e == null || tooLow(e.key)) {
                return null;
            }
            return e;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absHighFence() {
            if (this.toEnd) {
                return null;
            }
            if (this.hiInclusive) {
                return this.m.getHigherEntry(this.hi);
            }
            return this.m.getCeilingEntry(this.hi);
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry<K, V> absLowFence() {
            if (this.fromStart) {
                return null;
            }
            if (this.loInclusive) {
                return this.m.getLowerEntry(this.lo);
            }
            return this.m.getFloorEntry(this.lo);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return (!this.fromStart || !this.toEnd) ? entrySet().isEmpty() : this.m.isEmpty();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return (!this.fromStart || !this.toEnd) ? entrySet().size() : this.m.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final boolean containsKey(Object key) {
            return inRange(key) && this.m.containsKey(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final V put(K key, V value) {
            if (inRange(key)) {
                return this.m.put(key, value);
            }
            throw new IllegalArgumentException("key out of range");
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final V get(Object key) {
            if (!inRange(key)) {
                return null;
            }
            return this.m.get(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final V remove(Object key) {
            if (!inRange(key)) {
                return null;
            }
            return this.m.remove(key);
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> ceilingEntry(K key) {
            return TreeMap.exportEntry(subCeiling(key));
        }

        @Override // java.util.NavigableMap
        public final K ceilingKey(K key) {
            return (K) TreeMap.keyOrNull(subCeiling(key));
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> higherEntry(K key) {
            return TreeMap.exportEntry(subHigher(key));
        }

        @Override // java.util.NavigableMap
        public final K higherKey(K key) {
            return (K) TreeMap.keyOrNull(subHigher(key));
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> floorEntry(K key) {
            return TreeMap.exportEntry(subFloor(key));
        }

        @Override // java.util.NavigableMap
        public final K floorKey(K key) {
            return (K) TreeMap.keyOrNull(subFloor(key));
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> lowerEntry(K key) {
            return TreeMap.exportEntry(subLower(key));
        }

        @Override // java.util.NavigableMap
        public final K lowerKey(K key) {
            return (K) TreeMap.keyOrNull(subLower(key));
        }

        @Override // java.util.SortedMap
        public final K firstKey() {
            return (K) TreeMap.key(subLowest());
        }

        @Override // java.util.SortedMap
        public final K lastKey() {
            return (K) TreeMap.key(subHighest());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> firstEntry() {
            return TreeMap.exportEntry(subLowest());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> lastEntry() {
            return TreeMap.exportEntry(subHighest());
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollFirstEntry() {
            TreeMapEntry<K, V> e = subLowest();
            Map.Entry<K, V> result = TreeMap.exportEntry(e);
            if (e != null) {
                this.m.deleteEntry(e);
            }
            return result;
        }

        @Override // java.util.NavigableMap
        public final Map.Entry<K, V> pollLastEntry() {
            TreeMapEntry<K, V> e = subHighest();
            Map.Entry<K, V> result = TreeMap.exportEntry(e);
            if (e != null) {
                this.m.deleteEntry(e);
            }
            return result;
        }

        @Override // java.util.NavigableMap
        public final NavigableSet<K> navigableKeySet() {
            KeySet<K> nksv = this.navigableKeySetView;
            if (nksv != null) {
                return nksv;
            }
            KeySet<K> keySet = new KeySet<>(this);
            this.navigableKeySetView = keySet;
            return keySet;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public final Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public final SortedMap<K, V> subMap(K fromKey, K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public final SortedMap<K, V> headMap(K toKey) {
            return headMap(toKey, false);
        }

        @Override // java.util.NavigableMap, java.util.SortedMap
        public final SortedMap<K, V> tailMap(K fromKey) {
            return tailMap(fromKey, true);
        }

        abstract class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
            private transient int size = -1;
            private transient int sizeModCount;

            EntrySetView() {
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                if (NavigableSubMap.this.fromStart && NavigableSubMap.this.toEnd) {
                    return NavigableSubMap.this.m.size();
                }
                if (this.size == -1 || this.sizeModCount != ((TreeMap) NavigableSubMap.this.m).modCount) {
                    this.sizeModCount = ((TreeMap) NavigableSubMap.this.m).modCount;
                    this.size = 0;
                    Iterator<?> i = iterator();
                    while (i.hasNext()) {
                        this.size++;
                        i.next();
                    }
                }
                return this.size;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                TreeMapEntry<K, V> n = NavigableSubMap.this.absLowest();
                return n == null || NavigableSubMap.this.tooHigh(n.key);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object o) {
                TreeMapEntry<K, V> entry;
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> entry2 = (Map.Entry) o;
                Object key = entry2.getKey();
                if (NavigableSubMap.this.inRange(key) && (entry = NavigableSubMap.this.m.getEntry(key)) != null && TreeMap.valEquals(entry.getValue(), entry2.getValue())) {
                    return true;
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object o) {
                TreeMapEntry<K, V> node;
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> entry = (Map.Entry) o;
                Object key = entry.getKey();
                if (!NavigableSubMap.this.inRange(key) || (node = NavigableSubMap.this.m.getEntry(key)) == null || !TreeMap.valEquals(node.getValue(), entry.getValue())) {
                    return false;
                }
                NavigableSubMap.this.m.deleteEntry(node);
                return true;
            }
        }

        abstract class SubMapIterator<T> implements Iterator<T> {
            int expectedModCount;
            final Object fenceKey;
            TreeMapEntry<K, V> lastReturned = null;
            TreeMapEntry<K, V> next;

            SubMapIterator(TreeMapEntry<K, V> first, TreeMapEntry<K, V> fence) {
                this.expectedModCount = ((TreeMap) NavigableSubMap.this.m).modCount;
                this.next = first;
                this.fenceKey = fence == null ? TreeMap.UNBOUNDED : fence.key;
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                TreeMapEntry<K, V> treeMapEntry = this.next;
                return (treeMapEntry == null || treeMapEntry.key == this.fenceKey) ? false : true;
            }

            /* access modifiers changed from: package-private */
            public final TreeMapEntry<K, V> nextEntry() {
                TreeMapEntry<K, V> e = this.next;
                if (e == null || e.key == this.fenceKey) {
                    throw new NoSuchElementException();
                } else if (((TreeMap) NavigableSubMap.this.m).modCount == this.expectedModCount) {
                    this.next = TreeMap.successor(e);
                    this.lastReturned = e;
                    return e;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            /* access modifiers changed from: package-private */
            public final TreeMapEntry<K, V> prevEntry() {
                TreeMapEntry<K, V> e = this.next;
                if (e == null || e.key == this.fenceKey) {
                    throw new NoSuchElementException();
                } else if (((TreeMap) NavigableSubMap.this.m).modCount == this.expectedModCount) {
                    this.next = TreeMap.predecessor(e);
                    this.lastReturned = e;
                    return e;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            /* access modifiers changed from: package-private */
            public final void removeAscending() {
                if (this.lastReturned == null) {
                    throw new IllegalStateException();
                } else if (((TreeMap) NavigableSubMap.this.m).modCount == this.expectedModCount) {
                    if (!(this.lastReturned.left == null || this.lastReturned.right == null)) {
                        this.next = this.lastReturned;
                    }
                    NavigableSubMap.this.m.deleteEntry(this.lastReturned);
                    this.lastReturned = null;
                    this.expectedModCount = ((TreeMap) NavigableSubMap.this.m).modCount;
                } else {
                    throw new ConcurrentModificationException();
                }
            }

            /* access modifiers changed from: package-private */
            public final void removeDescending() {
                if (this.lastReturned == null) {
                    throw new IllegalStateException();
                } else if (((TreeMap) NavigableSubMap.this.m).modCount == this.expectedModCount) {
                    NavigableSubMap.this.m.deleteEntry(this.lastReturned);
                    this.lastReturned = null;
                    this.expectedModCount = ((TreeMap) NavigableSubMap.this.m).modCount;
                } else {
                    throw new ConcurrentModificationException();
                }
            }
        }

        final class SubMapEntryIterator extends NavigableSubMap<K, V>.SubMapIterator {
            SubMapEntryIterator(TreeMapEntry<K, V> first, TreeMapEntry<K, V> fence) {
                super(first, fence);
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                return nextEntry();
            }

            @Override // java.util.Iterator
            public void remove() {
                removeAscending();
            }
        }

        final class DescendingSubMapEntryIterator extends NavigableSubMap<K, V>.SubMapIterator {
            DescendingSubMapEntryIterator(TreeMapEntry<K, V> last, TreeMapEntry<K, V> fence) {
                super(last, fence);
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                return prevEntry();
            }

            @Override // java.util.Iterator
            public void remove() {
                removeDescending();
            }
        }

        final class SubMapKeyIterator extends NavigableSubMap<K, V>.SubMapIterator implements Spliterator<K> {
            SubMapKeyIterator(TreeMapEntry<K, V> first, TreeMapEntry<K, V> fence) {
                super(first, fence);
            }

            @Override // java.util.Iterator
            public K next() {
                return nextEntry().key;
            }

            @Override // java.util.Iterator
            public void remove() {
                removeAscending();
            }

            @Override // java.util.Spliterator
            public Spliterator<K> trySplit() {
                return null;
            }

            @Override // java.util.Iterator, java.util.Spliterator
            public void forEachRemaining(Consumer<? super K> action) {
                while (hasNext()) {
                    action.accept((Object) next());
                }
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super K> action) {
                if (!hasNext()) {
                    return false;
                }
                action.accept((Object) next());
                return true;
            }

            @Override // java.util.Spliterator
            public long estimateSize() {
                return Long.MAX_VALUE;
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return 21;
            }

            @Override // java.util.Spliterator
            public final Comparator<? super K> getComparator() {
                return NavigableSubMap.this.comparator();
            }
        }

        final class DescendingSubMapKeyIterator extends NavigableSubMap<K, V>.SubMapIterator implements Spliterator<K> {
            DescendingSubMapKeyIterator(TreeMapEntry<K, V> last, TreeMapEntry<K, V> fence) {
                super(last, fence);
            }

            @Override // java.util.Iterator
            public K next() {
                return prevEntry().key;
            }

            @Override // java.util.Iterator
            public void remove() {
                removeDescending();
            }

            @Override // java.util.Spliterator
            public Spliterator<K> trySplit() {
                return null;
            }

            @Override // java.util.Iterator, java.util.Spliterator
            public void forEachRemaining(Consumer<? super K> action) {
                while (hasNext()) {
                    action.accept((Object) next());
                }
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super K> action) {
                if (!hasNext()) {
                    return false;
                }
                action.accept((Object) next());
                return true;
            }

            @Override // java.util.Spliterator
            public long estimateSize() {
                return Long.MAX_VALUE;
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return 17;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class AscendingSubMap<K, V> extends NavigableSubMap<K, V> {
        private static final long serialVersionUID = 912986545866124060L;

        AscendingSubMap(TreeMap<K, V> m, boolean fromStart, K lo, boolean loInclusive, boolean toEnd, K hi, boolean hiInclusive) {
            super(m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.m.comparator();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            if (!inRange(fromKey, fromInclusive)) {
                throw new IllegalArgumentException("fromKey out of range");
            } else if (inRange(toKey, toInclusive)) {
                return new AscendingSubMap(this.m, false, fromKey, fromInclusive, false, toKey, toInclusive);
            } else {
                throw new IllegalArgumentException("toKey out of range");
            }
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            if (inRange(toKey) || (!this.toEnd && this.m.compare(toKey, this.hi) == 0 && !this.hiInclusive && !inclusive)) {
                return new AscendingSubMap(this.m, this.fromStart, this.lo, this.loInclusive, false, toKey, inclusive);
            }
            throw new IllegalArgumentException("toKey out of range");
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            if (inRange(fromKey) || (!this.fromStart && this.m.compare(fromKey, this.lo) == 0 && !this.loInclusive && !inclusive)) {
                return new AscendingSubMap(this.m, false, fromKey, inclusive, this.toEnd, this.hi, this.hiInclusive);
            }
            throw new IllegalArgumentException("fromKey out of range");
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            NavigableMap<K, V> mv = this.descendingMapView;
            if (mv != null) {
                return mv;
            }
            DescendingSubMap descendingSubMap = new DescendingSubMap(this.m, this.fromStart, this.lo, this.loInclusive, this.toEnd, this.hi, this.hiInclusive);
            this.descendingMapView = descendingSubMap;
            return descendingSubMap;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public Iterator<K> keyIterator() {
            return new NavigableSubMap.SubMapKeyIterator(absLowest(), absHighFence());
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public Spliterator<K> keySpliterator() {
            return new NavigableSubMap.SubMapKeyIterator(absLowest(), absHighFence());
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public Iterator<K> descendingKeyIterator() {
            return new NavigableSubMap.DescendingSubMapKeyIterator(absHighest(), absLowFence());
        }

        final class AscendingEntrySetView extends NavigableSubMap<K, V>.EntrySetView {
            AscendingEntrySetView() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                AscendingSubMap ascendingSubMap = AscendingSubMap.this;
                return new NavigableSubMap.SubMapEntryIterator(ascendingSubMap.absLowest(), AscendingSubMap.this.absHighFence());
            }
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            NavigableSubMap<K, V>.EntrySetView es = this.entrySetView;
            if (es != null) {
                return es;
            }
            AscendingEntrySetView ascendingEntrySetView = new AscendingEntrySetView();
            this.entrySetView = ascendingEntrySetView;
            return ascendingEntrySetView;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subLowest() {
            return absLowest();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subHighest() {
            return absHighest();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subCeiling(K key) {
            return absCeiling(key);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subHigher(K key) {
            return absHigher(key);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subFloor(K key) {
            return absFloor(key);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subLower(K key) {
            return absLower(key);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class DescendingSubMap<K, V> extends NavigableSubMap<K, V> {
        private static final long serialVersionUID = 912986545866120460L;
        private final Comparator<? super K> reverseComparator = Collections.reverseOrder(this.m.comparator);

        DescendingSubMap(TreeMap<K, V> m, boolean fromStart, K lo, boolean loInclusive, boolean toEnd, K hi, boolean hiInclusive) {
            super(m, fromStart, lo, loInclusive, toEnd, hi, hiInclusive);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return this.reverseComparator;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            if (!inRange(fromKey, fromInclusive)) {
                throw new IllegalArgumentException("fromKey out of range");
            } else if (inRange(toKey, toInclusive)) {
                return new DescendingSubMap(this.m, false, toKey, toInclusive, false, fromKey, fromInclusive);
            } else {
                throw new IllegalArgumentException("toKey out of range");
            }
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            if (inRange(toKey) || (!this.fromStart && this.m.compare(toKey, this.lo) == 0 && !this.loInclusive && !inclusive)) {
                return new DescendingSubMap(this.m, false, toKey, inclusive, this.toEnd, this.hi, this.hiInclusive);
            }
            throw new IllegalArgumentException("toKey out of range");
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            if (inRange(fromKey) || (!this.toEnd && this.m.compare(fromKey, this.hi) == 0 && !this.hiInclusive && !inclusive)) {
                return new DescendingSubMap(this.m, this.fromStart, this.lo, this.loInclusive, false, fromKey, inclusive);
            }
            throw new IllegalArgumentException("fromKey out of range");
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            NavigableMap<K, V> mv = this.descendingMapView;
            if (mv != null) {
                return mv;
            }
            AscendingSubMap ascendingSubMap = new AscendingSubMap(this.m, this.fromStart, this.lo, this.loInclusive, this.toEnd, this.hi, this.hiInclusive);
            this.descendingMapView = ascendingSubMap;
            return ascendingSubMap;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public Iterator<K> keyIterator() {
            return new NavigableSubMap.DescendingSubMapKeyIterator(absHighest(), absLowFence());
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public Spliterator<K> keySpliterator() {
            return new NavigableSubMap.DescendingSubMapKeyIterator(absHighest(), absLowFence());
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public Iterator<K> descendingKeyIterator() {
            return new NavigableSubMap.SubMapKeyIterator(absLowest(), absHighFence());
        }

        final class DescendingEntrySetView extends NavigableSubMap<K, V>.EntrySetView {
            DescendingEntrySetView() {
                super();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, V>> iterator() {
                DescendingSubMap descendingSubMap = DescendingSubMap.this;
                return new NavigableSubMap.DescendingSubMapEntryIterator(descendingSubMap.absHighest(), DescendingSubMap.this.absLowFence());
            }
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            NavigableSubMap<K, V>.EntrySetView es = this.entrySetView;
            if (es != null) {
                return es;
            }
            DescendingEntrySetView descendingEntrySetView = new DescendingEntrySetView();
            this.entrySetView = descendingEntrySetView;
            return descendingEntrySetView;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subLowest() {
            return absHighest();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subHighest() {
            return absLowest();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subCeiling(K key) {
            return absFloor(key);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subHigher(K key) {
            return absLower(key);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subFloor(K key) {
            return absCeiling(key);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.TreeMap.NavigableSubMap
        public TreeMapEntry<K, V> subLower(K key) {
            return absHigher(key);
        }
    }

    private class SubMap extends AbstractMap<K, V> implements SortedMap<K, V>, Serializable {
        private static final long serialVersionUID = -6520786458950516097L;
        private K fromKey;
        private boolean fromStart = false;
        private boolean toEnd = false;
        private K toKey;

        private SubMap() {
        }

        private Object readResolve() {
            return new AscendingSubMap(TreeMap.this, this.fromStart, this.fromKey, true, this.toEnd, this.toKey, false);
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            throw new InternalError();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            throw new InternalError();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            throw new InternalError();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            throw new InternalError();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            throw new InternalError();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            throw new InternalError();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            throw new InternalError();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TreeMapEntry<K, V> implements Map.Entry<K, V> {
        boolean color = true;
        K key;
        TreeMapEntry<K, V> left;
        TreeMapEntry<K, V> parent;
        TreeMapEntry<K, V> right;
        V value;

        TreeMapEntry(K key2, V value2, TreeMapEntry<K, V> parent2) {
            this.key = key2;
            this.value = value2;
            this.parent = parent2;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V value2) {
            V oldValue = this.value;
            this.value = value2;
            return oldValue;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            if (!TreeMap.valEquals(this.key, e.getKey()) || !TreeMap.valEquals(this.value, e.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.key;
            int valueHash = 0;
            int keyHash = k == null ? 0 : k.hashCode();
            V v = this.value;
            if (v != null) {
                valueHash = v.hashCode();
            }
            return keyHash ^ valueHash;
        }

        public String toString() {
            return ((Object) this.key) + "=" + ((Object) this.value);
        }
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getFirstEntry() {
        TreeMapEntry<K, V> p = this.root;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
        }
        return p;
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry<K, V> getLastEntry() {
        TreeMapEntry<K, V> p = this.root;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
        }
        return p;
    }

    static <K, V> TreeMapEntry<K, V> successor(TreeMapEntry<K, V> t) {
        if (t == null) {
            return null;
        }
        if (t.right != null) {
            TreeMapEntry<K, V> p = t.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        TreeMapEntry<K, V> p2 = t.parent;
        TreeMapEntry<K, V> ch = t;
        while (p2 != null && ch == p2.right) {
            ch = p2;
            p2 = p2.parent;
        }
        return p2;
    }

    static <K, V> TreeMapEntry<K, V> predecessor(TreeMapEntry<K, V> t) {
        if (t == null) {
            return null;
        }
        if (t.left != null) {
            TreeMapEntry<K, V> p = t.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        TreeMapEntry<K, V> p2 = t.parent;
        TreeMapEntry<K, V> ch = t;
        while (p2 != null && ch == p2.left) {
            ch = p2;
            p2 = p2.parent;
        }
        return p2;
    }

    private static <K, V> boolean colorOf(TreeMapEntry<K, V> p) {
        if (p == null) {
            return true;
        }
        return p.color;
    }

    private static <K, V> TreeMapEntry<K, V> parentOf(TreeMapEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        return p.parent;
    }

    private static <K, V> void setColor(TreeMapEntry<K, V> p, boolean c) {
        if (p != null) {
            p.color = c;
        }
    }

    private static <K, V> TreeMapEntry<K, V> leftOf(TreeMapEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        return p.left;
    }

    private static <K, V> TreeMapEntry<K, V> rightOf(TreeMapEntry<K, V> p) {
        if (p == null) {
            return null;
        }
        return p.right;
    }

    private void rotateLeft(TreeMapEntry<K, V> p) {
        if (p != null) {
            TreeMapEntry<K, V> r = p.right;
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            r.parent = p.parent;
            if (p.parent == null) {
                this.root = r;
            } else if (p.parent.left == p) {
                p.parent.left = r;
            } else {
                p.parent.right = r;
            }
            r.left = p;
            p.parent = r;
        }
    }

    private void rotateRight(TreeMapEntry<K, V> p) {
        if (p != null) {
            TreeMapEntry<K, V> l = p.left;
            p.left = l.right;
            if (l.right != null) {
                l.right.parent = p;
            }
            l.parent = p.parent;
            if (p.parent == null) {
                this.root = l;
            } else if (p.parent.right == p) {
                p.parent.right = l;
            } else {
                p.parent.left = l;
            }
            l.right = p;
            p.parent = l;
        }
    }

    private void fixAfterInsertion(TreeMapEntry<K, V> x) {
        x.color = false;
        while (x != null && x != this.root && !x.parent.color) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                TreeMapEntry<K, V> y = rightOf(parentOf(parentOf(x)));
                if (!colorOf(y)) {
                    setColor(parentOf(x), true);
                    setColor(y, true);
                    setColor(parentOf(parentOf(x)), false);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), true);
                    setColor(parentOf(parentOf(x)), false);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                TreeMapEntry<K, V> y2 = leftOf(parentOf(parentOf(x)));
                if (!colorOf(y2)) {
                    setColor(parentOf(x), true);
                    setColor(y2, true);
                    setColor(parentOf(parentOf(x)), false);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), true);
                    setColor(parentOf(parentOf(x)), false);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        this.root.color = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deleteEntry(TreeMapEntry<K, V> p) {
        this.modCount++;
        this.size--;
        if (!(p.left == null || p.right == null)) {
            TreeMapEntry<K, V> s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        }
        TreeMapEntry<K, V> replacement = p.left != null ? p.left : p.right;
        if (replacement != null) {
            replacement.parent = p.parent;
            if (p.parent == null) {
                this.root = replacement;
            } else if (p == p.parent.left) {
                p.parent.left = replacement;
            } else {
                p.parent.right = replacement;
            }
            p.parent = null;
            p.right = null;
            p.left = null;
            if (p.color) {
                fixAfterDeletion(replacement);
            }
        } else if (p.parent == null) {
            this.root = null;
        } else {
            if (p.color) {
                fixAfterDeletion(p);
            }
            if (p.parent != null) {
                if (p == p.parent.left) {
                    p.parent.left = null;
                } else if (p == p.parent.right) {
                    p.parent.right = null;
                }
                p.parent = null;
            }
        }
    }

    private void fixAfterDeletion(TreeMapEntry<K, V> x) {
        while (x != this.root && colorOf(x)) {
            if (x == leftOf(parentOf(x))) {
                TreeMapEntry<K, V> sib = rightOf(parentOf(x));
                if (!colorOf(sib)) {
                    setColor(sib, true);
                    setColor(parentOf(x), false);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }
                if (colorOf(leftOf(sib)) && colorOf(rightOf(sib))) {
                    setColor(sib, false);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib))) {
                        setColor(leftOf(sib), true);
                        setColor(sib, false);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), true);
                    setColor(rightOf(sib), true);
                    rotateLeft(parentOf(x));
                    x = this.root;
                }
            } else {
                TreeMapEntry<K, V> sib2 = leftOf(parentOf(x));
                if (!colorOf(sib2)) {
                    setColor(sib2, true);
                    setColor(parentOf(x), false);
                    rotateRight(parentOf(x));
                    sib2 = leftOf(parentOf(x));
                }
                if (colorOf(rightOf(sib2)) && colorOf(leftOf(sib2))) {
                    setColor(sib2, false);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib2))) {
                        setColor(rightOf(sib2), true);
                        setColor(sib2, false);
                        rotateLeft(sib2);
                        sib2 = leftOf(parentOf(x));
                    }
                    setColor(sib2, colorOf(parentOf(x)));
                    setColor(parentOf(x), true);
                    setColor(leftOf(sib2), true);
                    rotateRight(parentOf(x));
                    x = this.root;
                }
            }
        }
        setColor(x, true);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(this.size);
        for (Map.Entry<K, V> e : entrySet()) {
            s.writeObject(e.getKey());
            s.writeObject(e.getValue());
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        buildFromSorted(s.readInt(), null, s, null);
    }

    /* access modifiers changed from: package-private */
    public void readTreeSet(int size2, ObjectInputStream s, V defaultVal) throws IOException, ClassNotFoundException {
        buildFromSorted(size2, null, s, defaultVal);
    }

    /* access modifiers changed from: package-private */
    public void addAllForTreeSet(SortedSet<? extends K> set, V defaultVal) {
        try {
            buildFromSorted(set.size(), set.iterator(), null, defaultVal);
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void buildFromSorted(int size2, Iterator<?> it, ObjectInputStream str, V defaultVal) throws IOException, ClassNotFoundException {
        this.size = size2;
        this.root = buildFromSorted(0, 0, size2 - 1, computeRedLevel(size2), it, str, defaultVal);
    }

    /* JADX INFO: Multiple debug info for r1v11 java.lang.Object: [D('entry' java.util.Map$Entry<?, ?>), D('value' V)] */
    private final TreeMapEntry<K, V> buildFromSorted(int level, int lo, int hi, int redLevel, Iterator<?> it, ObjectInputStream str, V defaultVal) throws IOException, ClassNotFoundException {
        V value;
        Object obj;
        if (hi < lo) {
            return null;
        }
        int mid = (lo + hi) >>> 1;
        TreeMapEntry<K, V> left = null;
        if (lo < mid) {
            left = buildFromSorted(level + 1, lo, mid - 1, redLevel, it, str, defaultVal);
        }
        if (it == null) {
            Object readObject = str.readObject();
            value = defaultVal != null ? defaultVal : str.readObject();
            obj = readObject;
        } else if (defaultVal == null) {
            Map.Entry<?, ?> entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            value = entry.getValue();
            obj = key;
        } else {
            value = defaultVal;
            obj = it.next();
        }
        TreeMapEntry<K, V> middle = new TreeMapEntry<>(obj, value, null);
        if (level == redLevel) {
            middle.color = false;
        }
        if (left != null) {
            middle.left = left;
            left.parent = middle;
        }
        if (mid < hi) {
            TreeMapEntry<K, V> right = buildFromSorted(level + 1, mid + 1, hi, redLevel, it, str, defaultVal);
            middle.right = right;
            right.parent = middle;
        }
        return middle;
    }

    private static int computeRedLevel(int sz) {
        int level = 0;
        for (int m = sz - 1; m >= 0; m = (m / 2) - 1) {
            level++;
        }
        return level;
    }

    static <K> Spliterator<K> keySpliteratorFor(NavigableMap<K, ?> m) {
        if (m instanceof TreeMap) {
            return ((TreeMap) m).keySpliterator();
        }
        if (m instanceof DescendingSubMap) {
            DescendingSubMap<K, ?> dm = (DescendingSubMap) m;
            TreeMap<K, ?> tm = dm.m;
            if (dm == ((TreeMap) tm).descendingMap) {
                return tm.descendingKeySpliterator();
            }
        }
        return ((NavigableSubMap) m).keySpliterator();
    }

    /* access modifiers changed from: package-private */
    public final Spliterator<K> keySpliterator() {
        return new KeySpliterator(this, null, null, 0, -1, 0);
    }

    /* access modifiers changed from: package-private */
    public final Spliterator<K> descendingKeySpliterator() {
        return new DescendingKeySpliterator(this, null, null, 0, -2, 0);
    }

    static class TreeMapSpliterator<K, V> {
        TreeMapEntry<K, V> current;
        int est;
        int expectedModCount;
        TreeMapEntry<K, V> fence;
        int side;
        final TreeMap<K, V> tree;

        TreeMapSpliterator(TreeMap<K, V> tree2, TreeMapEntry<K, V> origin, TreeMapEntry<K, V> fence2, int side2, int est2, int expectedModCount2) {
            this.tree = tree2;
            this.current = origin;
            this.fence = fence2;
            this.side = side2;
            this.est = est2;
            this.expectedModCount = expectedModCount2;
        }

        /* access modifiers changed from: package-private */
        public final int getEstimate() {
            int s = this.est;
            if (s >= 0) {
                return s;
            }
            TreeMap<K, V> t = this.tree;
            if (t != null) {
                this.current = s == -1 ? t.getFirstEntry() : t.getLastEntry();
                int s2 = ((TreeMap) t).size;
                this.est = s2;
                this.expectedModCount = ((TreeMap) t).modCount;
                return s2;
            }
            this.est = 0;
            return 0;
        }

        public final long estimateSize() {
            return (long) getEstimate();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class KeySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<K> {
        KeySpliterator(TreeMap<K, V> tree, TreeMapEntry<K, V> origin, TreeMapEntry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public KeySpliterator<K, V> trySplit() {
            TreeMapEntry<K, V> s;
            if (this.est < 0) {
                getEstimate();
            }
            int d = this.side;
            TreeMapEntry<K, V> e = this.current;
            TreeMapEntry<K, V> f = this.fence;
            if (e == null || e == f) {
                s = null;
            } else if (d == 0) {
                s = this.tree.root;
            } else if (d > 0) {
                s = e.right;
            } else if (d >= 0 || f == null) {
                s = null;
            } else {
                s = f.left;
            }
            if (s == null || s == e || s == f || this.tree.compare(e.key, s.key) >= 0) {
                return null;
            }
            this.side = 1;
            TreeMap treeMap = this.tree;
            this.current = s;
            int i = this.est >>> 1;
            this.est = i;
            return new KeySpliterator<>(treeMap, e, s, -1, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> f = this.fence;
                TreeMapEntry<K, V> treeMapEntry = this.current;
                TreeMapEntry<K, V> e = treeMapEntry;
                if (treeMapEntry != null && e != f) {
                    this.current = f;
                    do {
                        action.accept(e.key);
                        TreeMapEntry<K, V> treeMapEntry2 = e.right;
                        TreeMapEntry<K, V> p = treeMapEntry2;
                        if (treeMapEntry2 == null) {
                            while (true) {
                                TreeMapEntry<K, V> treeMapEntry3 = e.parent;
                                p = treeMapEntry3;
                                if (treeMapEntry3 == null || e != p.right) {
                                    break;
                                }
                                e = p;
                            }
                        } else {
                            while (true) {
                                TreeMapEntry<K, V> pl = p.left;
                                if (pl == null) {
                                    break;
                                }
                                p = pl;
                            }
                        }
                        e = p;
                        if (p == null) {
                            break;
                        }
                    } while (e != f);
                    if (this.tree.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> e = this.current;
                if (e == null || e == this.fence) {
                    return false;
                }
                this.current = TreeMap.successor(e);
                action.accept(e.key);
                if (this.tree.modCount == this.expectedModCount) {
                    return true;
                }
                throw new ConcurrentModificationException();
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return (this.side == 0 ? 64 : 0) | 1 | 4 | 16;
        }

        @Override // java.util.Spliterator
        public final Comparator<? super K> getComparator() {
            return this.tree.comparator;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class DescendingKeySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<K> {
        DescendingKeySpliterator(TreeMap<K, V> tree, TreeMapEntry<K, V> origin, TreeMapEntry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public DescendingKeySpliterator<K, V> trySplit() {
            TreeMapEntry<K, V> s;
            if (this.est < 0) {
                getEstimate();
            }
            int d = this.side;
            TreeMapEntry<K, V> e = this.current;
            TreeMapEntry<K, V> f = this.fence;
            if (e == null || e == f) {
                s = null;
            } else if (d == 0) {
                s = this.tree.root;
            } else if (d < 0) {
                s = e.left;
            } else if (d <= 0 || f == null) {
                s = null;
            } else {
                s = f.right;
            }
            if (s == null || s == e || s == f || this.tree.compare(e.key, s.key) <= 0) {
                return null;
            }
            this.side = 1;
            TreeMap treeMap = this.tree;
            this.current = s;
            int i = this.est >>> 1;
            this.est = i;
            return new DescendingKeySpliterator<>(treeMap, e, s, -1, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> f = this.fence;
                TreeMapEntry<K, V> treeMapEntry = this.current;
                TreeMapEntry<K, V> e = treeMapEntry;
                if (treeMapEntry != null && e != f) {
                    this.current = f;
                    do {
                        action.accept(e.key);
                        TreeMapEntry<K, V> treeMapEntry2 = e.left;
                        TreeMapEntry<K, V> p = treeMapEntry2;
                        if (treeMapEntry2 == null) {
                            while (true) {
                                TreeMapEntry<K, V> treeMapEntry3 = e.parent;
                                p = treeMapEntry3;
                                if (treeMapEntry3 == null || e != p.left) {
                                    break;
                                }
                                e = p;
                            }
                        } else {
                            while (true) {
                                TreeMapEntry<K, V> pr = p.right;
                                if (pr == null) {
                                    break;
                                }
                                p = pr;
                            }
                        }
                        e = p;
                        if (p == null) {
                            break;
                        }
                    } while (e != f);
                    if (this.tree.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> e = this.current;
                if (e == null || e == this.fence) {
                    return false;
                }
                this.current = TreeMap.predecessor(e);
                action.accept(e.key);
                if (this.tree.modCount == this.expectedModCount) {
                    return true;
                }
                throw new ConcurrentModificationException();
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return (this.side == 0 ? 64 : 0) | 1 | 16;
        }
    }

    static final class ValueSpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<V> {
        ValueSpliterator(TreeMap<K, V> tree, TreeMapEntry<K, V> origin, TreeMapEntry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public ValueSpliterator<K, V> trySplit() {
            TreeMapEntry<K, V> s;
            if (this.est < 0) {
                getEstimate();
            }
            int d = this.side;
            TreeMapEntry<K, V> e = this.current;
            TreeMapEntry<K, V> f = this.fence;
            if (e == null || e == f) {
                s = null;
            } else if (d == 0) {
                s = this.tree.root;
            } else if (d > 0) {
                s = e.right;
            } else if (d >= 0 || f == null) {
                s = null;
            } else {
                s = f.left;
            }
            if (s == null || s == e || s == f || this.tree.compare(e.key, s.key) >= 0) {
                return null;
            }
            this.side = 1;
            TreeMap treeMap = this.tree;
            this.current = s;
            int i = this.est >>> 1;
            this.est = i;
            return new ValueSpliterator<>(treeMap, e, s, -1, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super V> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> f = this.fence;
                TreeMapEntry<K, V> treeMapEntry = this.current;
                TreeMapEntry<K, V> e = treeMapEntry;
                if (treeMapEntry != null && e != f) {
                    this.current = f;
                    do {
                        action.accept(e.value);
                        TreeMapEntry<K, V> treeMapEntry2 = e.right;
                        TreeMapEntry<K, V> p = treeMapEntry2;
                        if (treeMapEntry2 == null) {
                            while (true) {
                                TreeMapEntry<K, V> treeMapEntry3 = e.parent;
                                p = treeMapEntry3;
                                if (treeMapEntry3 == null || e != p.right) {
                                    break;
                                }
                                e = p;
                            }
                        } else {
                            while (true) {
                                TreeMapEntry<K, V> pl = p.left;
                                if (pl == null) {
                                    break;
                                }
                                p = pl;
                            }
                        }
                        e = p;
                        if (p == null) {
                            break;
                        }
                    } while (e != f);
                    if (this.tree.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super V> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> e = this.current;
                if (e == null || e == this.fence) {
                    return false;
                }
                this.current = TreeMap.successor(e);
                action.accept(e.value);
                if (this.tree.modCount == this.expectedModCount) {
                    return true;
                }
                throw new ConcurrentModificationException();
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return (this.side == 0 ? 64 : 0) | 16;
        }
    }

    static final class EntrySpliterator<K, V> extends TreeMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {
        private static /* synthetic */ Object $deserializeLambda$(SerializedLambda lambda) {
            String implMethodName = lambda.getImplMethodName();
            if (((implMethodName.hashCode() == 1620203517 && implMethodName.equals("lambda$getComparator$d5a01062$1")) ? (char) 0 : 65535) == 0 && lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/TreeMap$EntrySpliterator") && lambda.getImplMethodSignature().equals("(Ljava/util/Map$Entry;Ljava/util/Map$Entry;)I")) {
                return $$Lambda$TreeMap$EntrySpliterator$YqCulUmBGNzQr1PJ_ERFnbxUmTQ.INSTANCE;
            }
            throw new IllegalArgumentException("Invalid lambda deserialization");
        }

        EntrySpliterator(TreeMap<K, V> tree, TreeMapEntry<K, V> origin, TreeMapEntry<K, V> fence, int side, int est, int expectedModCount) {
            super(tree, origin, fence, side, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public EntrySpliterator<K, V> trySplit() {
            TreeMapEntry<K, V> s;
            if (this.est < 0) {
                getEstimate();
            }
            int d = this.side;
            TreeMapEntry<K, V> e = this.current;
            TreeMapEntry<K, V> f = this.fence;
            if (e == null || e == f) {
                s = null;
            } else if (d == 0) {
                s = this.tree.root;
            } else if (d > 0) {
                s = e.right;
            } else if (d >= 0 || f == null) {
                s = null;
            } else {
                s = f.left;
            }
            if (s == null || s == e || s == f || this.tree.compare(e.key, s.key) >= 0) {
                return null;
            }
            this.side = 1;
            TreeMap treeMap = this.tree;
            this.current = s;
            int i = this.est >>> 1;
            this.est = i;
            return new EntrySpliterator<>(treeMap, e, s, -1, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> f = this.fence;
                TreeMapEntry<K, V> treeMapEntry = this.current;
                TreeMapEntry<K, V> e = treeMapEntry;
                if (treeMapEntry != null && e != f) {
                    this.current = f;
                    do {
                        action.accept(e);
                        TreeMapEntry<K, V> treeMapEntry2 = e.right;
                        TreeMapEntry<K, V> p = treeMapEntry2;
                        if (treeMapEntry2 == null) {
                            while (true) {
                                TreeMapEntry<K, V> treeMapEntry3 = e.parent;
                                p = treeMapEntry3;
                                if (treeMapEntry3 == null || e != p.right) {
                                    break;
                                }
                                e = p;
                            }
                        } else {
                            while (true) {
                                TreeMapEntry<K, V> pl = p.left;
                                if (pl == null) {
                                    break;
                                }
                                p = pl;
                            }
                        }
                        e = p;
                        if (p == null) {
                            break;
                        }
                    } while (e != f);
                    if (this.tree.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                if (this.est < 0) {
                    getEstimate();
                }
                TreeMapEntry<K, V> e = this.current;
                if (e == null || e == this.fence) {
                    return false;
                }
                this.current = TreeMap.successor(e);
                action.accept(e);
                if (this.tree.modCount == this.expectedModCount) {
                    return true;
                }
                throw new ConcurrentModificationException();
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return (this.side == 0 ? 64 : 0) | 1 | 4 | 16;
        }

        @Override // java.util.Spliterator
        public Comparator<Map.Entry<K, V>> getComparator() {
            if (this.tree.comparator != null) {
                return Map.Entry.comparingByKey(this.tree.comparator);
            }
            return $$Lambda$TreeMap$EntrySpliterator$YqCulUmBGNzQr1PJ_ERFnbxUmTQ.INSTANCE;
        }
    }
}
