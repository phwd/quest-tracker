package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtIncompatible
public class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    @VisibleForTesting
    @MonotonicNonNullDecl
    transient long[] entries;
    @MonotonicNonNullDecl
    private transient Set<Map.Entry<K, V>> entrySetView;
    @MonotonicNonNullDecl
    private transient Set<K> keySetView;
    @VisibleForTesting
    @MonotonicNonNullDecl
    transient Object[] keys;
    transient float loadFactor;
    transient int modCount;
    private transient int size;
    @MonotonicNonNullDecl
    private transient int[] table;
    private transient int threshold;
    @VisibleForTesting
    @MonotonicNonNullDecl
    transient Object[] values;
    @MonotonicNonNullDecl
    private transient Collection<V> valuesView;

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactHashMap<>(expectedSize);
    }

    CompactHashMap() {
        init(3, DEFAULT_LOAD_FACTOR);
    }

    CompactHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    CompactHashMap(int expectedSize, float loadFactor2) {
        init(expectedSize, loadFactor2);
    }

    /* access modifiers changed from: package-private */
    public void init(int expectedSize, float loadFactor2) {
        boolean z;
        boolean z2 = false;
        if (expectedSize >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Initial capacity must be non-negative");
        if (loadFactor2 > 0.0f) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Illegal load factor");
        int buckets = Hashing.closedTableSize(expectedSize, (double) loadFactor2);
        this.table = newTable(buckets);
        this.loadFactor = loadFactor2;
        this.keys = new Object[expectedSize];
        this.values = new Object[expectedSize];
        this.entries = newEntries(expectedSize);
        this.threshold = Math.max(1, (int) (((float) buckets) * loadFactor2));
    }

    private static int[] newTable(int size2) {
        int[] array = new int[size2];
        Arrays.fill(array, -1);
        return array;
    }

    private static long[] newEntries(int size2) {
        long[] array = new long[size2];
        Arrays.fill(array, -1L);
        return array;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    private static int getHash(long entry) {
        return (int) (entry >>> 32);
    }

    private static int getNext(long entry) {
        return (int) entry;
    }

    private static long swapNext(long entry, int newNext) {
        return (HASH_MASK & entry) | (NEXT_MASK & ((long) newNext));
    }

    /* access modifiers changed from: package-private */
    public void accessEntry(int index) {
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V put(@NullableDecl K key, @NullableDecl V value) {
        long entry;
        long[] entries2 = this.entries;
        Object[] keys2 = this.keys;
        Object[] values2 = this.values;
        int hash = Hashing.smearedHash(key);
        int tableIndex = hash & hashTableMask();
        int newEntryIndex = this.size;
        int next = this.table[tableIndex];
        if (next == -1) {
            this.table[tableIndex] = newEntryIndex;
        } else {
            do {
                entry = entries2[next];
                if (getHash(entry) != hash || !Objects.equal(key, keys2[next])) {
                    next = getNext(entry);
                } else {
                    V oldValue = (V) values2[next];
                    values2[next] = value;
                    accessEntry(next);
                    return oldValue;
                }
            } while (next != -1);
            entries2[next] = swapNext(entry, newEntryIndex);
        }
        if (newEntryIndex == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int newSize = newEntryIndex + 1;
        resizeMeMaybe(newSize);
        insertEntry(newEntryIndex, key, value, hash);
        this.size = newSize;
        if (newEntryIndex >= this.threshold) {
            resizeTable(this.table.length * 2);
        }
        this.modCount++;
        return null;
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int entryIndex, @NullableDecl K key, @NullableDecl V value, int hash) {
        this.entries[entryIndex] = (((long) hash) << 32) | NEXT_MASK;
        this.keys[entryIndex] = key;
        this.values[entryIndex] = value;
    }

    private void resizeMeMaybe(int newSize) {
        int entriesSize = this.entries.length;
        if (newSize > entriesSize) {
            int newCapacity = entriesSize + Math.max(1, entriesSize >>> 1);
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            if (newCapacity != entriesSize) {
                resizeEntries(newCapacity);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resizeEntries(int newCapacity) {
        this.keys = Arrays.copyOf(this.keys, newCapacity);
        this.values = Arrays.copyOf(this.values, newCapacity);
        long[] entries2 = this.entries;
        int oldCapacity = entries2.length;
        long[] entries3 = Arrays.copyOf(entries2, newCapacity);
        if (newCapacity > oldCapacity) {
            Arrays.fill(entries3, oldCapacity, newCapacity, -1L);
        }
        this.entries = entries3;
    }

    private void resizeTable(int newCapacity) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int newThreshold = ((int) (((float) newCapacity) * this.loadFactor)) + 1;
        int[] newTable = newTable(newCapacity);
        long[] entries2 = this.entries;
        int mask = newTable.length - 1;
        for (int i = 0; i < this.size; i++) {
            int hash = getHash(entries2[i]);
            int tableIndex = hash & mask;
            int next = newTable[tableIndex];
            newTable[tableIndex] = i;
            entries2[i] = (((long) hash) << 32) | (NEXT_MASK & ((long) next));
        }
        this.threshold = newThreshold;
        this.table = newTable;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int indexOf(@NullableDecl Object key) {
        int hash = Hashing.smearedHash(key);
        int next = this.table[hashTableMask() & hash];
        while (next != -1) {
            long entry = this.entries[next];
            if (getHash(entry) == hash && Objects.equal(key, this.keys[next])) {
                return next;
            }
            next = getNext(entry);
        }
        return -1;
    }

    public boolean containsKey(@NullableDecl Object key) {
        return indexOf(key) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(@NullableDecl Object key) {
        int index = indexOf(key);
        accessEntry(index);
        if (index == -1) {
            return null;
        }
        return (V) this.values[index];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V remove(@NullableDecl Object key) {
        return remove(key, Hashing.smearedHash(key));
    }

    @NullableDecl
    private V remove(@NullableDecl Object key, int hash) {
        V v = null;
        int tableIndex = hash & hashTableMask();
        int next = this.table[tableIndex];
        if (next != -1) {
            int last = -1;
            while (true) {
                if (getHash(this.entries[next]) != hash || !Objects.equal(key, this.keys[next])) {
                    last = next;
                    next = getNext(this.entries[next]);
                    if (next == -1) {
                        break;
                    }
                } else {
                    v = (V) this.values[next];
                    if (last == -1) {
                        this.table[tableIndex] = getNext(this.entries[next]);
                    } else {
                        this.entries[last] = swapNext(this.entries[last], getNext(this.entries[next]));
                    }
                    moveLastEntry(next);
                    this.size--;
                    this.modCount++;
                }
            }
        }
        return v;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @CanIgnoreReturnValue
    private V removeEntry(int entryIndex) {
        return remove(this.keys[entryIndex], getHash(this.entries[entryIndex]));
    }

    /* access modifiers changed from: package-private */
    public void moveLastEntry(int dstIndex) {
        long entry;
        int srcIndex = size() - 1;
        if (dstIndex < srcIndex) {
            this.keys[dstIndex] = this.keys[srcIndex];
            this.values[dstIndex] = this.values[srcIndex];
            this.keys[srcIndex] = null;
            this.values[srcIndex] = null;
            long lastEntry = this.entries[srcIndex];
            this.entries[dstIndex] = lastEntry;
            this.entries[srcIndex] = -1;
            int tableIndex = getHash(lastEntry) & hashTableMask();
            int lastNext = this.table[tableIndex];
            if (lastNext == srcIndex) {
                this.table[tableIndex] = dstIndex;
                return;
            }
            do {
                entry = this.entries[lastNext];
                lastNext = getNext(entry);
            } while (lastNext != srcIndex);
            this.entries[lastNext] = swapNext(entry, dstIndex);
            return;
        }
        this.keys[dstIndex] = null;
        this.values[dstIndex] = null;
        this.entries[dstIndex] = -1;
    }

    /* access modifiers changed from: package-private */
    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int getSuccessor(int entryIndex) {
        if (entryIndex + 1 < this.size) {
            return entryIndex + 1;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove - 1;
    }

    private abstract class Itr<T> implements Iterator<T> {
        int currentIndex;
        int expectedModCount;
        int indexToRemove;

        /* access modifiers changed from: package-private */
        public abstract T getOutput(int i);

        private Itr() {
            this.expectedModCount = CompactHashMap.this.modCount;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.indexToRemove = this.currentIndex;
            T result = getOutput(this.currentIndex);
            this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
            return result;
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            this.expectedModCount++;
            CompactHashMap.this.removeEntry(this.indexToRemove);
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        if (this.keySetView != null) {
            return this.keySetView;
        }
        Set<K> createKeySet = createKeySet();
        this.keySetView = createKeySet;
        return createKeySet;
    }

    /* access modifiers changed from: package-private */
    public Set<K> createKeySet() {
        return new KeySetView();
    }

    /* access modifiers changed from: package-private */
    public class KeySetView extends AbstractSet<K> {
        KeySetView() {
        }

        public int size() {
            return CompactHashMap.this.size;
        }

        public boolean contains(Object o) {
            return CompactHashMap.this.containsKey(o);
        }

        public boolean remove(@NullableDecl Object o) {
            int index = CompactHashMap.this.indexOf(o);
            if (index == -1) {
                return false;
            }
            CompactHashMap.this.removeEntry(index);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        public void clear() {
            CompactHashMap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> keySetIterator() {
        return new CompactHashMap<K, V>.Itr() {
            /* class com.google.common.collect.CompactHashMap.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public K getOutput(int entry) {
                return (K) CompactHashMap.this.keys[entry];
            }
        };
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySetView != null) {
            return this.entrySetView;
        }
        Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
        this.entrySetView = createEntrySet;
        return createEntrySet;
    }

    /* access modifiers changed from: package-private */
    public Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    /* access modifiers changed from: package-private */
    public class EntrySetView extends AbstractSet<Map.Entry<K, V>> {
        EntrySetView() {
        }

        public int size() {
            return CompactHashMap.this.size;
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        public boolean contains(@NullableDecl Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            int index = CompactHashMap.this.indexOf(entry.getKey());
            if (index == -1 || !Objects.equal(CompactHashMap.this.values[index], entry.getValue())) {
                return false;
            }
            return true;
        }

        public boolean remove(@NullableDecl Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> entry = (Map.Entry) o;
                int index = CompactHashMap.this.indexOf(entry.getKey());
                if (index != -1 && Objects.equal(CompactHashMap.this.values[index], entry.getValue())) {
                    CompactHashMap.this.removeEntry(index);
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        return new CompactHashMap<K, V>.Itr() {
            /* class com.google.common.collect.CompactHashMap.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public Map.Entry<K, V> getOutput(int entry) {
                return new MapEntry(entry);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final class MapEntry extends AbstractMapEntry<K, V> {
        @NullableDecl
        private final K key;
        private int lastKnownIndex;

        MapEntry(int index) {
            this.key = (K) CompactHashMap.this.keys[index];
            this.lastKnownIndex = index;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            if (this.lastKnownIndex == -1 || this.lastKnownIndex >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                return null;
            }
            return (V) CompactHashMap.this.values[this.lastKnownIndex];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V value) {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                CompactHashMap.this.put(this.key, value);
                return null;
            }
            V v = (V) CompactHashMap.this.values[this.lastKnownIndex];
            CompactHashMap.this.values[this.lastKnownIndex] = value;
            return v;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsValue(@NullableDecl Object value) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equal(value, this.values[i])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        if (this.valuesView != null) {
            return this.valuesView;
        }
        Collection<V> createValues = createValues();
        this.valuesView = createValues;
        return createValues;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createValues() {
        return new ValuesView();
    }

    /* access modifiers changed from: package-private */
    public class ValuesView extends AbstractCollection<V> {
        ValuesView() {
        }

        public int size() {
            return CompactHashMap.this.size;
        }

        public void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valuesIterator() {
        return new CompactHashMap<K, V>.Itr() {
            /* class com.google.common.collect.CompactHashMap.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public V getOutput(int entry) {
                return (V) CompactHashMap.this.values[entry];
            }
        };
    }

    public void trimToSize() {
        int size2 = this.size;
        if (size2 < this.entries.length) {
            resizeEntries(size2);
        }
        int minimumTableSize = Math.max(1, Integer.highestOneBit((int) (((float) size2) / this.loadFactor)));
        if (minimumTableSize < 1073741824 && ((double) size2) / ((double) minimumTableSize) > ((double) this.loadFactor)) {
            minimumTableSize <<= 1;
        }
        if (minimumTableSize < this.table.length) {
            resizeTable(minimumTableSize);
        }
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size);
        for (int i = 0; i < this.size; i++) {
            stream.writeObject(this.keys[i]);
            stream.writeObject(this.values[i]);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.google.common.collect.CompactHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        init(3, DEFAULT_LOAD_FACTOR);
        int i = stream.readInt();
        while (true) {
            i--;
            if (i >= 0) {
                put(stream.readObject(), stream.readObject());
            } else {
                return;
            }
        }
    }
}
