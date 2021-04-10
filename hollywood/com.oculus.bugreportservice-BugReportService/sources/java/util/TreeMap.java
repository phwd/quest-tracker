package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class TreeMap extends AbstractMap implements NavigableMap, Cloneable, Serializable {
    private static final Object UNBOUNDED = new Object();
    private static final long serialVersionUID = 919286545866124006L;
    private final Comparator comparator;
    private transient NavigableMap descendingMap;
    private transient EntrySet entrySet;
    private transient int modCount;
    private transient KeySet navigableKeySet;
    private transient TreeMapEntry root;
    private transient int size;

    public TreeMap() {
        this.size = 0;
        this.modCount = 0;
        this.comparator = null;
    }

    public TreeMap(Comparator comparator2) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator2;
    }

    public TreeMap(SortedMap sortedMap) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = sortedMap.comparator();
        try {
            buildFromSorted(sortedMap.size(), sortedMap.entrySet().iterator(), null, null);
        } catch (IOException | ClassNotFoundException unused) {
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return getEntry(obj) != null;
    }

    @Override // java.util.AbstractMap
    public boolean containsValue(Object obj) {
        for (TreeMapEntry firstEntry = getFirstEntry(); firstEntry != null; firstEntry = successor(firstEntry)) {
            if (valEquals(obj, firstEntry.value)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        TreeMapEntry entry = getEntry(obj);
        if (entry == null) {
            return null;
        }
        return entry.value;
    }

    @Override // java.util.SortedMap
    public Comparator comparator() {
        return this.comparator;
    }

    @Override // java.util.SortedMap
    public Object firstKey() {
        return key(getFirstEntry());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        Comparator comparator2;
        Comparator comparator3;
        int size2 = map.size();
        if (this.size != 0 || size2 == 0 || !(map instanceof SortedMap) || ((comparator2 = ((SortedMap) map).comparator()) != (comparator3 = this.comparator) && (comparator2 == null || !comparator2.equals(comparator3)))) {
            super.putAll(map);
            return;
        }
        this.modCount++;
        try {
            buildFromSorted(size2, map.entrySet().iterator(), null, null);
        } catch (IOException | ClassNotFoundException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry getEntry(Object obj) {
        if (this.comparator != null) {
            return getEntryUsingComparator(obj);
        }
        if (obj != null) {
            Comparable comparable = (Comparable) obj;
            TreeMapEntry treeMapEntry = this.root;
            while (treeMapEntry != null) {
                int compareTo = comparable.compareTo(treeMapEntry.key);
                if (compareTo < 0) {
                    treeMapEntry = treeMapEntry.left;
                } else if (compareTo <= 0) {
                    return treeMapEntry;
                } else {
                    treeMapEntry = treeMapEntry.right;
                }
            }
            return null;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry getEntryUsingComparator(Object obj) {
        Comparator comparator2 = this.comparator;
        if (comparator2 == null) {
            return null;
        }
        TreeMapEntry treeMapEntry = this.root;
        while (treeMapEntry != null) {
            int compare = comparator2.compare(obj, treeMapEntry.key);
            if (compare < 0) {
                treeMapEntry = treeMapEntry.left;
            } else if (compare <= 0) {
                return treeMapEntry;
            } else {
                treeMapEntry = treeMapEntry.right;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        int i;
        TreeMapEntry treeMapEntry;
        TreeMapEntry treeMapEntry2;
        TreeMapEntry treeMapEntry3 = this.root;
        if (treeMapEntry3 == null) {
            compare(obj, obj);
            this.root = new TreeMapEntry(obj, obj2, null);
            this.size = 1;
            this.modCount++;
            return null;
        }
        Comparator comparator2 = this.comparator;
        if (comparator2 != null) {
            while (true) {
                i = comparator2.compare(obj, treeMapEntry3.key);
                if (i < 0) {
                    treeMapEntry2 = treeMapEntry3.left;
                } else if (i <= 0) {
                    return treeMapEntry3.setValue(obj2);
                } else {
                    treeMapEntry2 = treeMapEntry3.right;
                }
                if (treeMapEntry2 == null) {
                    break;
                }
                treeMapEntry3 = treeMapEntry2;
            }
        } else if (obj != null) {
            Comparable comparable = (Comparable) obj;
            while (true) {
                i = comparable.compareTo(treeMapEntry3.key);
                if (i < 0) {
                    treeMapEntry = treeMapEntry3.left;
                } else if (i <= 0) {
                    return treeMapEntry3.setValue(obj2);
                } else {
                    treeMapEntry = treeMapEntry3.right;
                }
                if (treeMapEntry == null) {
                    break;
                }
                treeMapEntry3 = treeMapEntry;
            }
        } else {
            throw new NullPointerException();
        }
        TreeMapEntry treeMapEntry4 = new TreeMapEntry(obj, obj2, treeMapEntry3);
        if (i < 0) {
            treeMapEntry3.left = treeMapEntry4;
        } else {
            treeMapEntry3.right = treeMapEntry4;
        }
        fixAfterInsertion(treeMapEntry4);
        this.size++;
        this.modCount++;
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        TreeMapEntry entry = getEntry(obj);
        if (entry == null) {
            return null;
        }
        Object obj2 = entry.value;
        deleteEntry(entry);
        return obj2;
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
            TreeMap treeMap = (TreeMap) super.clone();
            treeMap.root = null;
            treeMap.size = 0;
            treeMap.modCount = 0;
            treeMap.entrySet = null;
            treeMap.navigableKeySet = null;
            treeMap.descendingMap = null;
            try {
                treeMap.buildFromSorted(this.size, entrySet().iterator(), null, null);
            } catch (IOException | ClassNotFoundException unused) {
            }
            return treeMap;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set keySet() {
        return navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public NavigableSet navigableKeySet() {
        KeySet keySet = this.navigableKeySet;
        if (keySet != null) {
            return keySet;
        }
        KeySet keySet2 = new KeySet(this);
        this.navigableKeySet = keySet2;
        return keySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.values = values;
        return values;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set entrySet() {
        EntrySet entrySet2 = this.entrySet;
        if (entrySet2 != null) {
            return entrySet2;
        }
        EntrySet entrySet3 = new EntrySet();
        this.entrySet = entrySet3;
        return entrySet3;
    }

    class Values extends AbstractCollection {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            TreeMap treeMap = TreeMap.this;
            return new ValueIterator(treeMap.getFirstEntry());
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return TreeMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return TreeMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            for (TreeMapEntry firstEntry = TreeMap.this.getFirstEntry(); firstEntry != null; firstEntry = TreeMap.successor(firstEntry)) {
                if (TreeMap.valEquals(firstEntry.getValue(), obj)) {
                    TreeMap.this.deleteEntry(firstEntry);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            TreeMap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public class EntrySet extends AbstractSet {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            TreeMap treeMap = TreeMap.this;
            return new EntryIterator(treeMap.getFirstEntry());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            TreeMapEntry entry2 = TreeMap.this.getEntry(entry.getKey());
            if (entry2 == null || !TreeMap.valEquals(entry2.getValue(), value)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object value = entry.getValue();
            TreeMapEntry entry2 = TreeMap.this.getEntry(entry.getKey());
            if (entry2 == null || !TreeMap.valEquals(entry2.getValue(), value)) {
                return false;
            }
            TreeMap.this.deleteEntry(entry2);
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
    }

    /* access modifiers changed from: package-private */
    public Iterator keyIterator() {
        return new KeyIterator(getFirstEntry());
    }

    /* access modifiers changed from: package-private */
    public static final class KeySet extends AbstractSet implements NavigableSet {
        private final NavigableMap m;

        KeySet(NavigableMap navigableMap) {
            this.m = navigableMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.util.NavigableSet, java.lang.Iterable
        public Iterator iterator() {
            NavigableMap navigableMap = this.m;
            if (navigableMap instanceof TreeMap) {
                return ((TreeMap) navigableMap).keyIterator();
            }
            return ((NavigableSubMap) navigableMap).keyIterator();
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
        public void clear() {
            this.m.clear();
        }

        @Override // java.util.SortedSet
        public Object first() {
            return this.m.firstKey();
        }

        @Override // java.util.SortedSet
        public Comparator comparator() {
            return this.m.comparator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int size = size();
            this.m.remove(obj);
            return size() != size;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract class PrivateEntryIterator implements Iterator {
        int expectedModCount;
        TreeMapEntry lastReturned = null;
        TreeMapEntry next;

        PrivateEntryIterator(TreeMapEntry treeMapEntry) {
            this.expectedModCount = TreeMap.this.modCount;
            this.next = treeMapEntry;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: package-private */
        public final TreeMapEntry nextEntry() {
            TreeMapEntry treeMapEntry = this.next;
            if (treeMapEntry == null) {
                throw new NoSuchElementException();
            } else if (TreeMap.this.modCount == this.expectedModCount) {
                this.next = TreeMap.successor(treeMapEntry);
                this.lastReturned = treeMapEntry;
                return treeMapEntry;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            } else if (TreeMap.this.modCount == this.expectedModCount) {
                TreeMapEntry treeMapEntry = this.lastReturned;
                if (!(treeMapEntry.left == null || treeMapEntry.right == null)) {
                    this.next = treeMapEntry;
                }
                TreeMap.this.deleteEntry(this.lastReturned);
                this.expectedModCount = TreeMap.this.modCount;
                this.lastReturned = null;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    final class EntryIterator extends PrivateEntryIterator {
        EntryIterator(TreeMapEntry treeMapEntry) {
            super(treeMapEntry);
        }

        @Override // java.util.Iterator
        public Map.Entry next() {
            return nextEntry();
        }
    }

    final class ValueIterator extends PrivateEntryIterator {
        ValueIterator(TreeMapEntry treeMapEntry) {
            super(treeMapEntry);
        }

        @Override // java.util.Iterator
        public Object next() {
            return nextEntry().value;
        }
    }

    /* access modifiers changed from: package-private */
    public final class KeyIterator extends PrivateEntryIterator {
        KeyIterator(TreeMapEntry treeMapEntry) {
            super(treeMapEntry);
        }

        @Override // java.util.Iterator
        public Object next() {
            return nextEntry().key;
        }
    }

    /* access modifiers changed from: package-private */
    public final int compare(Object obj, Object obj2) {
        Comparator comparator2 = this.comparator;
        if (comparator2 == null) {
            return ((Comparable) obj).compareTo(obj2);
        }
        return comparator2.compare(obj, obj2);
    }

    static final boolean valEquals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    static Object key(TreeMapEntry treeMapEntry) {
        if (treeMapEntry != null) {
            return treeMapEntry.key;
        }
        throw new NoSuchElementException();
    }

    static abstract class NavigableSubMap extends AbstractMap implements NavigableMap, Serializable {
        private static final long serialVersionUID = 2765629423043303731L;
        final boolean fromStart;
        final Object hi;
        final boolean hiInclusive;
        final Object lo;
        final boolean loInclusive;
        final TreeMap m;
        transient KeySet navigableKeySetView;
        final boolean toEnd;

        /* access modifiers changed from: package-private */
        public abstract Iterator keyIterator();

        /* access modifiers changed from: package-private */
        public abstract TreeMapEntry subLowest();

        /* access modifiers changed from: package-private */
        public final boolean tooLow(Object obj) {
            if (this.fromStart) {
                return false;
            }
            int compare = this.m.compare(obj, this.lo);
            if (compare >= 0) {
                return compare == 0 && !this.loInclusive;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public final boolean tooHigh(Object obj) {
            if (this.toEnd) {
                return false;
            }
            int compare = this.m.compare(obj, this.hi);
            if (compare <= 0) {
                return compare == 0 && !this.hiInclusive;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public final boolean inRange(Object obj) {
            return !tooLow(obj) && !tooHigh(obj);
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
        public final boolean containsKey(Object obj) {
            return inRange(obj) && this.m.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Object put(Object obj, Object obj2) {
            if (inRange(obj)) {
                return this.m.put(obj, obj2);
            }
            throw new IllegalArgumentException("key out of range");
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Object get(Object obj) {
            if (!inRange(obj)) {
                return null;
            }
            return this.m.get(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Object remove(Object obj) {
            if (!inRange(obj)) {
                return null;
            }
            return this.m.remove(obj);
        }

        @Override // java.util.SortedMap
        public final Object firstKey() {
            return TreeMap.key(subLowest());
        }

        @Override // java.util.NavigableMap
        public final NavigableSet navigableKeySet() {
            KeySet keySet = this.navigableKeySetView;
            if (keySet != null) {
                return keySet;
            }
            KeySet keySet2 = new KeySet(this);
            this.navigableKeySetView = keySet2;
            return keySet2;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public final Set keySet() {
            return navigableKeySet();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TreeMapEntry implements Map.Entry {
        boolean color = true;
        Object key;
        TreeMapEntry left;
        TreeMapEntry parent;
        TreeMapEntry right;
        Object value;

        TreeMapEntry(Object obj, Object obj2, TreeMapEntry treeMapEntry) {
            this.key = obj;
            this.value = obj2;
            this.parent = treeMapEntry;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object obj) {
            Object obj2 = this.value;
            this.value = obj;
            return obj2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!TreeMap.valEquals(this.key, entry.getKey()) || !TreeMap.valEquals(this.value, entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.key;
            int i = 0;
            int hashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.value;
            if (obj2 != null) {
                i = obj2.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    /* access modifiers changed from: package-private */
    public final TreeMapEntry getFirstEntry() {
        TreeMapEntry treeMapEntry = this.root;
        if (treeMapEntry != null) {
            while (true) {
                TreeMapEntry treeMapEntry2 = treeMapEntry.left;
                if (treeMapEntry2 == null) {
                    break;
                }
                treeMapEntry = treeMapEntry2;
            }
        }
        return treeMapEntry;
    }

    static TreeMapEntry successor(TreeMapEntry treeMapEntry) {
        if (treeMapEntry == null) {
            return null;
        }
        TreeMapEntry treeMapEntry2 = treeMapEntry.right;
        if (treeMapEntry2 != null) {
            while (true) {
                TreeMapEntry treeMapEntry3 = treeMapEntry2.left;
                if (treeMapEntry3 == null) {
                    return treeMapEntry2;
                }
                treeMapEntry2 = treeMapEntry3;
            }
        } else {
            TreeMapEntry treeMapEntry4 = treeMapEntry.parent;
            while (true) {
                treeMapEntry = treeMapEntry4;
                if (treeMapEntry == null || treeMapEntry != treeMapEntry.right) {
                    return treeMapEntry;
                }
                treeMapEntry4 = treeMapEntry.parent;
            }
            return treeMapEntry;
        }
    }

    private static boolean colorOf(TreeMapEntry treeMapEntry) {
        if (treeMapEntry == null) {
            return true;
        }
        return treeMapEntry.color;
    }

    private static TreeMapEntry parentOf(TreeMapEntry treeMapEntry) {
        if (treeMapEntry == null) {
            return null;
        }
        return treeMapEntry.parent;
    }

    private static void setColor(TreeMapEntry treeMapEntry, boolean z) {
        if (treeMapEntry != null) {
            treeMapEntry.color = z;
        }
    }

    private static TreeMapEntry leftOf(TreeMapEntry treeMapEntry) {
        if (treeMapEntry == null) {
            return null;
        }
        return treeMapEntry.left;
    }

    private static TreeMapEntry rightOf(TreeMapEntry treeMapEntry) {
        if (treeMapEntry == null) {
            return null;
        }
        return treeMapEntry.right;
    }

    private void rotateLeft(TreeMapEntry treeMapEntry) {
        if (treeMapEntry != null) {
            TreeMapEntry treeMapEntry2 = treeMapEntry.right;
            TreeMapEntry treeMapEntry3 = treeMapEntry2.left;
            treeMapEntry.right = treeMapEntry3;
            if (treeMapEntry3 != null) {
                treeMapEntry3.parent = treeMapEntry;
            }
            treeMapEntry2.parent = treeMapEntry.parent;
            TreeMapEntry treeMapEntry4 = treeMapEntry.parent;
            if (treeMapEntry4 == null) {
                this.root = treeMapEntry2;
            } else if (treeMapEntry4.left == treeMapEntry) {
                treeMapEntry4.left = treeMapEntry2;
            } else {
                treeMapEntry4.right = treeMapEntry2;
            }
            treeMapEntry2.left = treeMapEntry;
            treeMapEntry.parent = treeMapEntry2;
        }
    }

    private void rotateRight(TreeMapEntry treeMapEntry) {
        if (treeMapEntry != null) {
            TreeMapEntry treeMapEntry2 = treeMapEntry.left;
            TreeMapEntry treeMapEntry3 = treeMapEntry2.right;
            treeMapEntry.left = treeMapEntry3;
            if (treeMapEntry3 != null) {
                treeMapEntry3.parent = treeMapEntry;
            }
            treeMapEntry2.parent = treeMapEntry.parent;
            TreeMapEntry treeMapEntry4 = treeMapEntry.parent;
            if (treeMapEntry4 == null) {
                this.root = treeMapEntry2;
            } else if (treeMapEntry4.right == treeMapEntry) {
                treeMapEntry4.right = treeMapEntry2;
            } else {
                treeMapEntry4.left = treeMapEntry2;
            }
            treeMapEntry2.right = treeMapEntry;
            treeMapEntry.parent = treeMapEntry2;
        }
    }

    private void fixAfterInsertion(TreeMapEntry treeMapEntry) {
        treeMapEntry.color = false;
        while (treeMapEntry != null && treeMapEntry != this.root && !treeMapEntry.parent.color) {
            if (parentOf(treeMapEntry) == leftOf(parentOf(parentOf(treeMapEntry)))) {
                TreeMapEntry rightOf = rightOf(parentOf(parentOf(treeMapEntry)));
                if (!colorOf(rightOf)) {
                    setColor(parentOf(treeMapEntry), true);
                    setColor(rightOf, true);
                    setColor(parentOf(parentOf(treeMapEntry)), false);
                    treeMapEntry = parentOf(parentOf(treeMapEntry));
                } else {
                    if (treeMapEntry == rightOf(parentOf(treeMapEntry))) {
                        treeMapEntry = parentOf(treeMapEntry);
                        rotateLeft(treeMapEntry);
                    }
                    setColor(parentOf(treeMapEntry), true);
                    setColor(parentOf(parentOf(treeMapEntry)), false);
                    rotateRight(parentOf(parentOf(treeMapEntry)));
                }
            } else {
                TreeMapEntry leftOf = leftOf(parentOf(parentOf(treeMapEntry)));
                if (!colorOf(leftOf)) {
                    setColor(parentOf(treeMapEntry), true);
                    setColor(leftOf, true);
                    setColor(parentOf(parentOf(treeMapEntry)), false);
                    treeMapEntry = parentOf(parentOf(treeMapEntry));
                } else {
                    if (treeMapEntry == leftOf(parentOf(treeMapEntry))) {
                        treeMapEntry = parentOf(treeMapEntry);
                        rotateRight(treeMapEntry);
                    }
                    setColor(parentOf(treeMapEntry), true);
                    setColor(parentOf(parentOf(treeMapEntry)), false);
                    rotateLeft(parentOf(parentOf(treeMapEntry)));
                }
            }
        }
        this.root.color = true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deleteEntry(TreeMapEntry treeMapEntry) {
        this.modCount++;
        this.size--;
        if (!(treeMapEntry.left == null || treeMapEntry.right == null)) {
            TreeMapEntry successor = successor(treeMapEntry);
            treeMapEntry.key = successor.key;
            treeMapEntry.value = successor.value;
            treeMapEntry = successor;
        }
        TreeMapEntry treeMapEntry2 = treeMapEntry.left;
        if (treeMapEntry2 == null) {
            treeMapEntry2 = treeMapEntry.right;
        }
        if (treeMapEntry2 != null) {
            treeMapEntry2.parent = treeMapEntry.parent;
            TreeMapEntry treeMapEntry3 = treeMapEntry.parent;
            if (treeMapEntry3 == null) {
                this.root = treeMapEntry2;
            } else if (treeMapEntry == treeMapEntry3.left) {
                treeMapEntry3.left = treeMapEntry2;
            } else {
                treeMapEntry3.right = treeMapEntry2;
            }
            treeMapEntry.parent = null;
            treeMapEntry.right = null;
            treeMapEntry.left = null;
            if (treeMapEntry.color) {
                fixAfterDeletion(treeMapEntry2);
            }
        } else if (treeMapEntry.parent == null) {
            this.root = null;
        } else {
            if (treeMapEntry.color) {
                fixAfterDeletion(treeMapEntry);
            }
            TreeMapEntry treeMapEntry4 = treeMapEntry.parent;
            if (treeMapEntry4 != null) {
                if (treeMapEntry == treeMapEntry4.left) {
                    treeMapEntry4.left = null;
                } else if (treeMapEntry == treeMapEntry4.right) {
                    treeMapEntry4.right = null;
                }
                treeMapEntry.parent = null;
            }
        }
    }

    private void fixAfterDeletion(TreeMapEntry treeMapEntry) {
        while (treeMapEntry != this.root && colorOf(treeMapEntry)) {
            if (treeMapEntry == leftOf(parentOf(treeMapEntry))) {
                TreeMapEntry rightOf = rightOf(parentOf(treeMapEntry));
                if (!colorOf(rightOf)) {
                    setColor(rightOf, true);
                    setColor(parentOf(treeMapEntry), false);
                    rotateLeft(parentOf(treeMapEntry));
                    rightOf = rightOf(parentOf(treeMapEntry));
                }
                if (colorOf(leftOf(rightOf)) && colorOf(rightOf(rightOf))) {
                    setColor(rightOf, false);
                    treeMapEntry = parentOf(treeMapEntry);
                } else {
                    if (colorOf(rightOf(rightOf))) {
                        setColor(leftOf(rightOf), true);
                        setColor(rightOf, false);
                        rotateRight(rightOf);
                        rightOf = rightOf(parentOf(treeMapEntry));
                    }
                    setColor(rightOf, colorOf(parentOf(treeMapEntry)));
                    setColor(parentOf(treeMapEntry), true);
                    setColor(rightOf(rightOf), true);
                    rotateLeft(parentOf(treeMapEntry));
                    treeMapEntry = this.root;
                }
            } else {
                TreeMapEntry leftOf = leftOf(parentOf(treeMapEntry));
                if (!colorOf(leftOf)) {
                    setColor(leftOf, true);
                    setColor(parentOf(treeMapEntry), false);
                    rotateRight(parentOf(treeMapEntry));
                    leftOf = leftOf(parentOf(treeMapEntry));
                }
                if (colorOf(rightOf(leftOf)) && colorOf(leftOf(leftOf))) {
                    setColor(leftOf, false);
                    treeMapEntry = parentOf(treeMapEntry);
                } else {
                    if (colorOf(leftOf(leftOf))) {
                        setColor(rightOf(leftOf), true);
                        setColor(leftOf, false);
                        rotateLeft(leftOf);
                        leftOf = leftOf(parentOf(treeMapEntry));
                    }
                    setColor(leftOf, colorOf(parentOf(treeMapEntry)));
                    setColor(parentOf(treeMapEntry), true);
                    setColor(leftOf(leftOf), true);
                    rotateRight(parentOf(treeMapEntry));
                    treeMapEntry = this.root;
                }
            }
        }
        setColor(treeMapEntry, true);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    /* access modifiers changed from: package-private */
    public void addAllForTreeSet(SortedSet sortedSet, Object obj) {
        try {
            buildFromSorted(sortedSet.size(), sortedSet.iterator(), null, obj);
        } catch (IOException | ClassNotFoundException unused) {
        }
    }

    private void buildFromSorted(int i, Iterator it, ObjectInputStream objectInputStream, Object obj) {
        this.size = i;
        this.root = buildFromSorted(0, 0, i - 1, computeRedLevel(i), it, objectInputStream, obj);
    }

    private final TreeMapEntry buildFromSorted(int i, int i2, int i3, int i4, Iterator it, ObjectInputStream objectInputStream, Object obj) {
        Object obj2;
        Object obj3;
        if (i3 < i2) {
            return null;
        }
        int i5 = (i2 + i3) >>> 1;
        TreeMapEntry buildFromSorted = i2 < i5 ? buildFromSorted(i + 1, i2, i5 - 1, i4, it, objectInputStream, obj) : null;
        if (it != null) {
            if (obj == null) {
                Map.Entry entry = (Map.Entry) it.next();
                obj2 = entry.getKey();
                obj3 = entry.getValue();
            } else {
                obj2 = it.next();
                obj3 = obj;
            }
            TreeMapEntry treeMapEntry = new TreeMapEntry(obj2, obj3, null);
            if (i == i4) {
                treeMapEntry.color = false;
            }
            if (buildFromSorted != null) {
                treeMapEntry.left = buildFromSorted;
                buildFromSorted.parent = treeMapEntry;
            }
            if (i5 < i3) {
                TreeMapEntry buildFromSorted2 = buildFromSorted(i + 1, i5 + 1, i3, i4, it, objectInputStream, obj);
                treeMapEntry.right = buildFromSorted2;
                buildFromSorted2.parent = treeMapEntry;
            }
            return treeMapEntry;
        }
        objectInputStream.readObject();
        throw null;
    }

    private static int computeRedLevel(int i) {
        int i2 = 0;
        for (int i3 = i - 1; i3 >= 0; i3 = (i3 / 2) - 1) {
            i2++;
        }
        return i2;
    }
}
