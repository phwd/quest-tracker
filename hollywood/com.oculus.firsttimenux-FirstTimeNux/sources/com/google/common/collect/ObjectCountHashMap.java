package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD, serializable = BuildConfig.IS_INTERNAL_BUILD)
public class ObjectCountHashMap<K> {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    @VisibleForTesting
    transient long[] entries;
    transient Object[] keys;
    private transient float loadFactor;
    transient int modCount;
    transient int size;
    private transient int[] table;
    private transient int threshold;
    transient int[] values;

    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int expectedSize) {
        return new ObjectCountHashMap<>(expectedSize);
    }

    ObjectCountHashMap() {
        init(3, DEFAULT_LOAD_FACTOR);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.ObjectCountHashMap<K> */
    /* JADX WARN: Multi-variable type inference failed */
    ObjectCountHashMap(ObjectCountHashMap<? extends K> map) {
        init(map.size(), DEFAULT_LOAD_FACTOR);
        int i = map.firstIndex();
        while (i != -1) {
            put(map.getKey(i), map.getValue(i));
            i = map.nextIndex(i);
        }
    }

    ObjectCountHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    ObjectCountHashMap(int expectedSize, float loadFactor2) {
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
        this.values = new int[expectedSize];
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

    /* access modifiers changed from: package-private */
    public int firstIndex() {
        return this.size == 0 ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int nextIndex(int index) {
        if (index + 1 < this.size) {
            return index + 1;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int nextIndexAfterRemove(int oldNextIndex, int removedIndex) {
        return oldNextIndex - 1;
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public K getKey(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return (K) this.keys[index];
    }

    /* access modifiers changed from: package-private */
    public int getValue(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return this.values[index];
    }

    /* access modifiers changed from: package-private */
    public void setValue(int index, int newValue) {
        Preconditions.checkElementIndex(index, this.size);
        this.values[index] = newValue;
    }

    /* access modifiers changed from: package-private */
    public Multiset.Entry<K> getEntry(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return new MapEntry(index);
    }

    class MapEntry extends Multisets.AbstractEntry<K> {
        @NullableDecl
        final K key;
        int lastKnownIndex;

        MapEntry(int index) {
            this.key = (K) ObjectCountHashMap.this.keys[index];
            this.lastKnownIndex = index;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public K getElement() {
            return this.key;
        }

        /* access modifiers changed from: package-private */
        public void updateLastKnownIndex() {
            if (this.lastKnownIndex == -1 || this.lastKnownIndex >= ObjectCountHashMap.this.size() || !Objects.equal(this.key, ObjectCountHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = ObjectCountHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.values[this.lastKnownIndex];
        }

        @CanIgnoreReturnValue
        public int setCount(int count) {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                ObjectCountHashMap.this.put(this.key, count);
                return 0;
            }
            int i = ObjectCountHashMap.this.values[this.lastKnownIndex];
            ObjectCountHashMap.this.values[this.lastKnownIndex] = count;
            return i;
        }
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
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > this.entries.length) {
            resizeEntries(minCapacity);
        }
        if (minCapacity >= this.threshold) {
            resizeTable(Math.max(2, Integer.highestOneBit(minCapacity - 1) << 1));
        }
    }

    @CanIgnoreReturnValue
    public int put(@NullableDecl K key, int value) {
        long entry;
        CollectPreconditions.checkPositive(value, "count");
        long[] entries2 = this.entries;
        Object[] keys2 = this.keys;
        int[] values2 = this.values;
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
                    int oldValue = values2[next];
                    values2[next] = value;
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
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int entryIndex, @NullableDecl K key, int value, int hash) {
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

    /* access modifiers changed from: package-private */
    public int indexOf(@NullableDecl Object key) {
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

    public int get(@NullableDecl Object key) {
        int index = indexOf(key);
        if (index == -1) {
            return 0;
        }
        return this.values[index];
    }

    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object key) {
        return remove(key, Hashing.smearedHash(key));
    }

    private int remove(@NullableDecl Object key, int hash) {
        int i = 0;
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
                    i = this.values[next];
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
        return i;
    }

    /* access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public int removeEntry(int entryIndex) {
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
            this.values[srcIndex] = 0;
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
        this.values[dstIndex] = 0;
        this.entries[dstIndex] = -1;
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }
}
