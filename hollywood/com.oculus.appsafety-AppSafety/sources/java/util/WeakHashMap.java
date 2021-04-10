package java.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class WeakHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final Object NULL_KEY = new Object();
    private transient Set<Map.Entry<K, V>> entrySet;
    private final float loadFactor;
    int modCount;
    private final ReferenceQueue<Object> queue;
    private int size;
    Entry<K, V>[] table;
    private int threshold;

    private Entry<K, V>[] newTable(int n) {
        return new Entry[n];
    }

    public WeakHashMap(int initialCapacity, float loadFactor2) {
        this.queue = new ReferenceQueue<>();
        if (initialCapacity >= 0) {
            initialCapacity = initialCapacity > 1073741824 ? 1073741824 : initialCapacity;
            if (loadFactor2 <= 0.0f || Float.isNaN(loadFactor2)) {
                throw new IllegalArgumentException("Illegal Load factor: " + loadFactor2);
            }
            int capacity = 1;
            while (capacity < initialCapacity) {
                capacity <<= 1;
            }
            this.table = newTable(capacity);
            this.loadFactor = loadFactor2;
            this.threshold = (int) (((float) capacity) * loadFactor2);
            return;
        }
        throw new IllegalArgumentException("Illegal Initial Capacity: " + initialCapacity);
    }

    public WeakHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public WeakHashMap() {
        this(16, DEFAULT_LOAD_FACTOR);
    }

    public WeakHashMap(Map<? extends K, ? extends V> m) {
        this(Math.max(((int) (((float) m.size()) / DEFAULT_LOAD_FACTOR)) + 1, 16), DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    private static Object maskNull(Object key) {
        return key == null ? NULL_KEY : key;
    }

    static Object unmaskNull(Object key) {
        if (key == NULL_KEY) {
            return null;
        }
        return key;
    }

    private static boolean eq(Object x, Object y) {
        return x == y || x.equals(y);
    }

    /* access modifiers changed from: package-private */
    public final int hash(Object k) {
        int h = k.hashCode();
        int h2 = h ^ ((h >>> 20) ^ (h >>> 12));
        return ((h2 >>> 7) ^ h2) ^ (h2 >>> 4);
    }

    private static int indexFor(int h, int length) {
        return (length - 1) & h;
    }

    private void expungeStaleEntries() {
        while (true) {
            Object x = this.queue.poll();
            if (x != null) {
                synchronized (this.queue) {
                    Entry<K, V> e = (Entry) x;
                    int i = indexFor(e.hash, this.table.length);
                    Entry<K, V> prev = this.table[i];
                    Entry<K, V> p = prev;
                    while (true) {
                        if (p == null) {
                            break;
                        }
                        Entry<K, V> next = p.next;
                        if (p == e) {
                            if (prev == e) {
                                this.table[i] = next;
                            } else {
                                prev.next = next;
                            }
                            e.value = null;
                            this.size--;
                        } else {
                            prev = p;
                            p = next;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    private Entry<K, V>[] getTable() {
        expungeStaleEntries();
        return this.table;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        if (this.size == 0) {
            return 0;
        }
        expungeStaleEntries();
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K, V>[] tab = getTable();
        for (Entry<K, V> e = tab[indexFor(h, tab.length)]; e != null; e = e.next) {
            if (e.hash == h && eq(k, e.get())) {
                return e.value;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    /* access modifiers changed from: package-private */
    public Entry<K, V> getEntry(Object key) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K, V>[] tab = getTable();
        Entry<K, V> e = tab[indexFor(h, tab.length)];
        while (e != null && (e.hash != h || !eq(k, e.get()))) {
            e = e.next;
        }
        return e;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K, V>[] tab = getTable();
        int i = indexFor(h, tab.length);
        for (Entry<K, V> e = tab[i]; e != null; e = e.next) {
            if (h == e.hash && eq(k, e.get())) {
                V oldValue = e.value;
                if (value != oldValue) {
                    e.value = value;
                }
                return oldValue;
            }
        }
        this.modCount++;
        tab[i] = new Entry<>(k, value, this.queue, h, tab[i]);
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 < this.threshold) {
            return null;
        }
        resize(tab.length * 2);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void resize(int newCapacity) {
        Entry<K, V>[] oldTable = getTable();
        if (oldTable.length == 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        Entry<K, V>[] newTable = newTable(newCapacity);
        transfer(oldTable, newTable);
        this.table = newTable;
        if (this.size >= this.threshold / 2) {
            this.threshold = (int) (((float) newCapacity) * this.loadFactor);
            return;
        }
        expungeStaleEntries();
        transfer(newTable, oldTable);
        this.table = oldTable;
    }

    private void transfer(Entry<K, V>[] src, Entry<K, V>[] dest) {
        for (int j = 0; j < src.length; j++) {
            Entry<K, V> e = src[j];
            src[j] = null;
            while (e != null) {
                Entry<K, V> next = e.next;
                if (e.get() == null) {
                    e.next = null;
                    e.value = null;
                    this.size--;
                } else {
                    int i = indexFor(e.hash, dest.length);
                    e.next = dest[i];
                    dest[i] = e;
                }
                e = next;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.WeakHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        int numKeysToBeAdded = m.size();
        if (numKeysToBeAdded != 0) {
            if (numKeysToBeAdded > this.threshold) {
                int targetCapacity = (int) ((((float) numKeysToBeAdded) / this.loadFactor) + 1.0f);
                if (targetCapacity > 1073741824) {
                    targetCapacity = 1073741824;
                }
                int newCapacity = this.table.length;
                while (newCapacity < targetCapacity) {
                    newCapacity <<= 1;
                }
                if (newCapacity > this.table.length) {
                    resize(newCapacity);
                }
            }
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                put(e.getKey(), e.getValue());
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        Object k = maskNull(key);
        int h = hash(k);
        Entry<K, V>[] tab = getTable();
        int i = indexFor(h, tab.length);
        Entry<K, V> prev = tab[i];
        Entry<K, V> e = prev;
        while (e != null) {
            Entry<K, V> next = e.next;
            if (h != e.hash || !eq(k, e.get())) {
                prev = e;
                e = next;
            } else {
                this.modCount++;
                this.size--;
                if (prev == e) {
                    tab[i] = next;
                } else {
                    prev.next = next;
                }
                return e.value;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean removeMapping(Object o) {
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        Entry<K, V>[] tab = getTable();
        Map.Entry<?, ?> entry = (Map.Entry) o;
        int h = hash(maskNull(entry.getKey()));
        int i = indexFor(h, tab.length);
        Entry<K, V> prev = tab[i];
        Entry<K, V> e = prev;
        while (e != null) {
            Entry<K, V> next = e.next;
            if (h != e.hash || !e.equals(entry)) {
                prev = e;
                e = next;
            } else {
                this.modCount++;
                this.size--;
                if (prev == e) {
                    tab[i] = next;
                } else {
                    prev.next = next;
                }
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        do {
        } while (this.queue.poll() != null);
        this.modCount++;
        Arrays.fill(this.table, (Object) null);
        this.size = 0;
        do {
        } while (this.queue.poll() != null);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        if (value == null) {
            return containsNullValue();
        }
        Entry<K, V>[] tab = getTable();
        int i = tab.length;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return false;
            }
            for (Entry<K, V> e = tab[i2]; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
            i = i2;
        }
    }

    private boolean containsNullValue() {
        Entry<K, V>[] tab = getTable();
        int i = tab.length;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return false;
            }
            for (Entry<K, V> e = tab[i2]; e != null; e = e.next) {
                if (e.value == null) {
                    return true;
                }
            }
            i = i2;
        }
    }

    /* access modifiers changed from: private */
    public static class Entry<K, V> extends WeakReference<Object> implements Map.Entry<K, V> {
        final int hash;
        Entry<K, V> next;
        V value;

        Entry(Object key, V value2, ReferenceQueue<Object> queue, int hash2, Entry<K, V> next2) {
            super(key, queue);
            this.value = value2;
            this.hash = hash2;
            this.next = next2;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) WeakHashMap.unmaskNull(get());
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            K k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                V v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2) {
                    return true;
                }
                if (v1 == null || !v1.equals(v2)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public String toString() {
            return ((Object) getKey()) + "=" + ((Object) getValue());
        }
    }

    private abstract class HashIterator<T> implements Iterator<T> {
        private Object currentKey;
        private Entry<K, V> entry;
        private int expectedModCount = WeakHashMap.this.modCount;
        private int index;
        private Entry<K, V> lastReturned;
        private Object nextKey;

        HashIterator() {
            this.index = WeakHashMap.this.isEmpty() ? 0 : WeakHashMap.this.table.length;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Entry<K, V>[] t = WeakHashMap.this.table;
            while (this.nextKey == null) {
                Entry<K, V> e = this.entry;
                int i = this.index;
                while (e == null && i > 0) {
                    i--;
                    e = t[i];
                }
                this.entry = e;
                this.index = i;
                if (e == null) {
                    this.currentKey = null;
                    return false;
                }
                this.nextKey = e.get();
                if (this.nextKey == null) {
                    this.entry = this.entry.next;
                }
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public Entry<K, V> nextEntry() {
            if (WeakHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (this.nextKey != null || hasNext()) {
                Entry<K, V> entry2 = this.entry;
                this.lastReturned = entry2;
                this.entry = entry2.next;
                this.currentKey = this.nextKey;
                this.nextKey = null;
                return this.lastReturned;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            } else if (WeakHashMap.this.modCount == this.expectedModCount) {
                WeakHashMap.this.remove(this.currentKey);
                this.expectedModCount = WeakHashMap.this.modCount;
                this.lastReturned = null;
                this.currentKey = null;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ValueIterator extends WeakHashMap<K, V>.HashIterator {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            return nextEntry().value;
        }
    }

    private class KeyIterator extends WeakHashMap<K, V>.HashIterator {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    /* access modifiers changed from: private */
    public class EntryIterator extends WeakHashMap<K, V>.HashIterator {
        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        Set<K> ks2 = new KeySet();
        this.keySet = ks2;
        return ks2;
    }

    /* access modifiers changed from: private */
    public class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return WeakHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return WeakHashMap.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!WeakHashMap.this.containsKey(o)) {
                return false;
            }
            WeakHashMap.this.remove(o);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            WeakHashMap.this.clear();
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<K> spliterator() {
            return new KeySpliterator(WeakHashMap.this, 0, -1, 0, 0);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Collection<V> vs2 = new Values();
        this.values = vs2;
        return vs2;
    }

    private class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return WeakHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return WeakHashMap.this.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            WeakHashMap.this.clear();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<V> spliterator() {
            return new ValueSpliterator(WeakHashMap.this, 0, -1, 0, 0);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            Entry<K, V> candidate = WeakHashMap.this.getEntry(e.getKey());
            if (candidate == null || !candidate.equals(e)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return WeakHashMap.this.removeMapping(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return WeakHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            WeakHashMap.this.clear();
        }

        private List<Map.Entry<K, V>> deepCopy() {
            List<Map.Entry<K, V>> list = new ArrayList<>(size());
            Iterator<Map.Entry<K, V>> it = iterator();
            while (it.hasNext()) {
                list.add(new AbstractMap.SimpleEntry<>(it.next()));
            }
            return list;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return deepCopy().toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] a) {
            return (T[]) deepCopy().toArray(a);
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return new EntrySpliterator(WeakHashMap.this, 0, -1, 0, 0);
        }
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        int expectedModCount = this.modCount;
        Entry<K, V>[] tab = getTable();
        for (Entry<K, V> entry : tab) {
            while (entry != null) {
                Object key = entry.get();
                if (key != null) {
                    action.accept((Object) unmaskNull(key), entry.value);
                }
                entry = entry.next;
                if (expectedModCount != this.modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        int expectedModCount = this.modCount;
        Entry<K, V>[] tab = getTable();
        for (Entry<K, V> entry : tab) {
            while (entry != null) {
                Object key = entry.get();
                if (key != null) {
                    entry.value = (V) function.apply((Object) unmaskNull(key), entry.value);
                }
                entry = entry.next;
                if (expectedModCount != this.modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    static class WeakHashMapSpliterator<K, V> {
        Entry<K, V> current;
        int est;
        int expectedModCount;
        int fence;
        int index;
        final WeakHashMap<K, V> map;

        WeakHashMapSpliterator(WeakHashMap<K, V> m, int origin, int fence2, int est2, int expectedModCount2) {
            this.map = m;
            this.index = origin;
            this.fence = fence2;
            this.est = est2;
            this.expectedModCount = expectedModCount2;
        }

        /* access modifiers changed from: package-private */
        public final int getFence() {
            int hi = this.fence;
            if (hi >= 0) {
                return hi;
            }
            WeakHashMap<K, V> m = this.map;
            this.est = m.size();
            this.expectedModCount = m.modCount;
            int hi2 = m.table.length;
            this.fence = hi2;
            return hi2;
        }

        public final long estimateSize() {
            getFence();
            return (long) this.est;
        }
    }

    static final class KeySpliterator<K, V> extends WeakHashMapSpliterator<K, V> implements Spliterator<K> {
        KeySpliterator(WeakHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public KeySpliterator<K, V> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                return null;
            }
            WeakHashMap weakHashMap = this.map;
            this.index = mid;
            int i = this.est >>> 1;
            this.est = i;
            return new KeySpliterator<>(weakHashMap, lo, mid, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> action) {
            int mc;
            if (action != null) {
                WeakHashMap<K, V> m = this.map;
                Entry<K, V>[] tab = m.table;
                int i = this.fence;
                int hi = i;
                if (i < 0) {
                    mc = m.modCount;
                    this.expectedModCount = mc;
                    int length = tab.length;
                    this.fence = length;
                    hi = length;
                } else {
                    mc = this.expectedModCount;
                }
                if (tab.length >= hi) {
                    int i2 = this.index;
                    int i3 = i2;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (i3 < hi || this.current != null) {
                            Entry<K, V> p = this.current;
                            this.current = null;
                            while (true) {
                                if (p == null) {
                                    p = tab[i3];
                                    i3++;
                                } else {
                                    Object x = p.get();
                                    p = p.next;
                                    if (x != null) {
                                        action.accept((Object) WeakHashMap.unmaskNull(x));
                                    }
                                }
                                if (p == null && i3 >= hi) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> action) {
            if (action != null) {
                Entry<K, V>[] tab = this.map.table;
                int length = tab.length;
                int hi = getFence();
                if (length < hi || this.index < 0) {
                    return false;
                }
                while (true) {
                    if (this.current == null && this.index >= hi) {
                        return false;
                    }
                    if (this.current == null) {
                        int i = this.index;
                        this.index = i + 1;
                        this.current = tab[i];
                    } else {
                        Object x = this.current.get();
                        this.current = this.current.next;
                        if (x != null) {
                            action.accept((Object) WeakHashMap.unmaskNull(x));
                            if (this.map.modCount == this.expectedModCount) {
                                return true;
                            }
                            throw new ConcurrentModificationException();
                        }
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 1;
        }
    }

    static final class ValueSpliterator<K, V> extends WeakHashMapSpliterator<K, V> implements Spliterator<V> {
        ValueSpliterator(WeakHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public ValueSpliterator<K, V> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                return null;
            }
            WeakHashMap weakHashMap = this.map;
            this.index = mid;
            int i = this.est >>> 1;
            this.est = i;
            return new ValueSpliterator<>(weakHashMap, lo, mid, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super V> action) {
            int mc;
            if (action != null) {
                WeakHashMap<K, V> m = this.map;
                Entry<K, V>[] tab = m.table;
                int i = this.fence;
                int hi = i;
                if (i < 0) {
                    mc = m.modCount;
                    this.expectedModCount = mc;
                    int length = tab.length;
                    this.fence = length;
                    hi = length;
                } else {
                    mc = this.expectedModCount;
                }
                if (tab.length >= hi) {
                    int i2 = this.index;
                    int i3 = i2;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (i3 < hi || this.current != null) {
                            Entry<K, V> p = this.current;
                            this.current = null;
                            while (true) {
                                if (p == null) {
                                    p = tab[i3];
                                    i3++;
                                } else {
                                    Object x = p.get();
                                    V v = p.value;
                                    p = p.next;
                                    if (x != null) {
                                        action.accept(v);
                                    }
                                }
                                if (p == null && i3 >= hi) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super V> action) {
            if (action != null) {
                Entry<K, V>[] tab = this.map.table;
                int length = tab.length;
                int hi = getFence();
                if (length < hi || this.index < 0) {
                    return false;
                }
                while (true) {
                    if (this.current == null && this.index >= hi) {
                        return false;
                    }
                    if (this.current == null) {
                        int i = this.index;
                        this.index = i + 1;
                        this.current = tab[i];
                    } else {
                        Object x = this.current.get();
                        V v = this.current.value;
                        this.current = this.current.next;
                        if (x != null) {
                            action.accept(v);
                            if (this.map.modCount == this.expectedModCount) {
                                return true;
                            }
                            throw new ConcurrentModificationException();
                        }
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 0;
        }
    }

    static final class EntrySpliterator<K, V> extends WeakHashMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {
        EntrySpliterator(WeakHashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public EntrySpliterator<K, V> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid) {
                return null;
            }
            WeakHashMap weakHashMap = this.map;
            this.index = mid;
            int i = this.est >>> 1;
            this.est = i;
            return new EntrySpliterator<>(weakHashMap, lo, mid, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            int mc;
            if (action != null) {
                WeakHashMap<K, V> m = this.map;
                Entry<K, V>[] tab = m.table;
                int i = this.fence;
                int hi = i;
                if (i < 0) {
                    mc = m.modCount;
                    this.expectedModCount = mc;
                    int length = tab.length;
                    this.fence = length;
                    hi = length;
                } else {
                    mc = this.expectedModCount;
                }
                if (tab.length >= hi) {
                    int i2 = this.index;
                    int i3 = i2;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (i3 < hi || this.current != null) {
                            Entry<K, V> p = this.current;
                            this.current = null;
                            while (true) {
                                if (p == null) {
                                    p = tab[i3];
                                    i3++;
                                } else {
                                    Object x = p.get();
                                    V v = p.value;
                                    p = p.next;
                                    if (x != null) {
                                        action.accept(new AbstractMap.SimpleImmutableEntry(WeakHashMap.unmaskNull(x), v));
                                    }
                                }
                                if (p == null && i3 >= hi) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (m.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                Entry<K, V>[] tab = this.map.table;
                int length = tab.length;
                int hi = getFence();
                if (length < hi || this.index < 0) {
                    return false;
                }
                while (true) {
                    if (this.current == null && this.index >= hi) {
                        return false;
                    }
                    if (this.current == null) {
                        int i = this.index;
                        this.index = i + 1;
                        this.current = tab[i];
                    } else {
                        Object x = this.current.get();
                        V v = this.current.value;
                        this.current = this.current.next;
                        if (x != null) {
                            action.accept(new AbstractMap.SimpleImmutableEntry(WeakHashMap.unmaskNull(x), v));
                            if (this.map.modCount == this.expectedModCount) {
                                return true;
                            }
                            throw new ConcurrentModificationException();
                        }
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 1;
        }
    }
}
