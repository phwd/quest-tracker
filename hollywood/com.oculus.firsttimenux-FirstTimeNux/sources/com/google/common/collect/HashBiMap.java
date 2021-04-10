package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final int ABSENT = -1;
    private static final int ENDPOINT = -2;
    private transient Set<Map.Entry<K, V>> entrySet;
    @NullableDecl
    private transient int firstInInsertionOrder;
    private transient int[] hashTableKToV;
    private transient int[] hashTableVToK;
    @RetainedWith
    @MonotonicNonNullDecl
    private transient BiMap<V, K> inverse;
    private transient Set<K> keySet;
    transient K[] keys;
    @NullableDecl
    private transient int lastInInsertionOrder;
    transient int modCount;
    private transient int[] nextInBucketKToV;
    private transient int[] nextInBucketVToK;
    private transient int[] nextInInsertionOrder;
    private transient int[] prevInInsertionOrder;
    transient int size;
    private transient Set<V> valueSet;
    transient V[] values;

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int expectedSize) {
        return new HashBiMap<>(expectedSize);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> bimap = create(map.size());
        bimap.putAll(map);
        return bimap;
    }

    private HashBiMap(int expectedSize) {
        init(expectedSize);
    }

    /* access modifiers changed from: package-private */
    public void init(int expectedSize) {
        CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
        int tableSize = Hashing.closedTableSize(expectedSize, 1.0d);
        this.size = 0;
        this.keys = (K[]) new Object[expectedSize];
        this.values = (V[]) new Object[expectedSize];
        this.hashTableKToV = createFilledWithAbsent(tableSize);
        this.hashTableVToK = createFilledWithAbsent(tableSize);
        this.nextInBucketKToV = createFilledWithAbsent(expectedSize);
        this.nextInBucketVToK = createFilledWithAbsent(expectedSize);
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.prevInInsertionOrder = createFilledWithAbsent(expectedSize);
        this.nextInInsertionOrder = createFilledWithAbsent(expectedSize);
    }

    private static int[] createFilledWithAbsent(int size2) {
        int[] array = new int[size2];
        Arrays.fill(array, -1);
        return array;
    }

    private static int[] expandAndFillWithAbsent(int[] array, int newSize) {
        int oldSize = array.length;
        int[] result = Arrays.copyOf(array, newSize);
        Arrays.fill(result, oldSize, newSize, -1);
        return result;
    }

    public int size() {
        return this.size;
    }

    private void ensureCapacity(int minCapacity) {
        if (this.nextInBucketKToV.length < minCapacity) {
            int newCapacity = ImmutableCollection.Builder.expandedCapacity(this.nextInBucketKToV.length, minCapacity);
            this.keys = (K[]) Arrays.copyOf(this.keys, newCapacity);
            this.values = (V[]) Arrays.copyOf(this.values, newCapacity);
            this.nextInBucketKToV = expandAndFillWithAbsent(this.nextInBucketKToV, newCapacity);
            this.nextInBucketVToK = expandAndFillWithAbsent(this.nextInBucketVToK, newCapacity);
            this.prevInInsertionOrder = expandAndFillWithAbsent(this.prevInInsertionOrder, newCapacity);
            this.nextInInsertionOrder = expandAndFillWithAbsent(this.nextInInsertionOrder, newCapacity);
        }
        if (this.hashTableKToV.length < minCapacity) {
            int newTableSize = Hashing.closedTableSize(minCapacity, 1.0d);
            this.hashTableKToV = createFilledWithAbsent(newTableSize);
            this.hashTableVToK = createFilledWithAbsent(newTableSize);
            for (int entryToRehash = 0; entryToRehash < this.size; entryToRehash++) {
                int keyBucket = bucket(Hashing.smearedHash(this.keys[entryToRehash]));
                this.nextInBucketKToV[entryToRehash] = this.hashTableKToV[keyBucket];
                this.hashTableKToV[keyBucket] = entryToRehash;
                int valueBucket = bucket(Hashing.smearedHash(this.values[entryToRehash]));
                this.nextInBucketVToK[entryToRehash] = this.hashTableVToK[valueBucket];
                this.hashTableVToK[valueBucket] = entryToRehash;
            }
        }
    }

    private int bucket(int hash) {
        return (this.hashTableKToV.length - 1) & hash;
    }

    /* access modifiers changed from: package-private */
    public int findEntryByKey(@NullableDecl Object key) {
        return findEntryByKey(key, Hashing.smearedHash(key));
    }

    /* access modifiers changed from: package-private */
    public int findEntryByKey(@NullableDecl Object key, int keyHash) {
        return findEntry(key, keyHash, this.hashTableKToV, this.nextInBucketKToV, this.keys);
    }

    /* access modifiers changed from: package-private */
    public int findEntryByValue(@NullableDecl Object value) {
        return findEntryByValue(value, Hashing.smearedHash(value));
    }

    /* access modifiers changed from: package-private */
    public int findEntryByValue(@NullableDecl Object value, int valueHash) {
        return findEntry(value, valueHash, this.hashTableVToK, this.nextInBucketVToK, this.values);
    }

    /* access modifiers changed from: package-private */
    public int findEntry(@NullableDecl Object o, int oHash, int[] hashTable, int[] nextInBucket, Object[] array) {
        int entry = hashTable[bucket(oHash)];
        while (entry != -1) {
            if (Objects.equal(array[entry], o)) {
                return entry;
            }
            entry = nextInBucket[entry];
        }
        return -1;
    }

    public boolean containsKey(@NullableDecl Object key) {
        return findEntryByKey(key) != -1;
    }

    public boolean containsValue(@NullableDecl Object value) {
        return findEntryByValue(value) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public V get(@NullableDecl Object key) {
        int entry = findEntryByKey(key);
        if (entry == -1) {
            return null;
        }
        return this.values[entry];
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public K getInverse(@NullableDecl Object value) {
        int entry = findEntryByValue(value);
        if (entry == -1) {
            return null;
        }
        return this.keys[entry];
    }

    @Override // com.google.common.collect.BiMap, java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V put(@NullableDecl K key, @NullableDecl V value) {
        return put(key, value, false);
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public V put(@NullableDecl K key, @NullableDecl V value, boolean force) {
        int keyHash = Hashing.smearedHash(key);
        int entryForKey = findEntryByKey(key, keyHash);
        if (entryForKey != -1) {
            V oldValue = this.values[entryForKey];
            if (Objects.equal(oldValue, value)) {
                return value;
            }
            replaceValueInEntry(entryForKey, value, force);
            return oldValue;
        }
        int valueHash = Hashing.smearedHash(value);
        int valueEntry = findEntryByValue(value, valueHash);
        if (!force) {
            Preconditions.checkArgument(valueEntry == -1, "Value already present: %s", value);
        } else if (valueEntry != -1) {
            removeEntryValueHashKnown(valueEntry, valueHash);
        }
        ensureCapacity(this.size + 1);
        this.keys[this.size] = key;
        this.values[this.size] = value;
        insertIntoTableKToV(this.size, keyHash);
        insertIntoTableVToK(this.size, valueHash);
        setSucceeds(this.lastInInsertionOrder, this.size);
        setSucceeds(this.size, -2);
        this.size++;
        this.modCount++;
        return null;
    }

    @Override // com.google.common.collect.BiMap
    @CanIgnoreReturnValue
    @NullableDecl
    public V forcePut(@NullableDecl K key, @NullableDecl V value) {
        return put(key, value, true);
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public K putInverse(@NullableDecl V value, @NullableDecl K key, boolean force) {
        int valueHash = Hashing.smearedHash(value);
        int entryForValue = findEntryByValue(value, valueHash);
        if (entryForValue != -1) {
            K oldKey = this.keys[entryForValue];
            if (Objects.equal(oldKey, key)) {
                return key;
            }
            replaceKeyInEntry(entryForValue, key, force);
            return oldKey;
        }
        int predecessor = this.lastInInsertionOrder;
        int keyHash = Hashing.smearedHash(key);
        int keyEntry = findEntryByKey(key, keyHash);
        if (!force) {
            Preconditions.checkArgument(keyEntry == -1, "Key already present: %s", key);
        } else if (keyEntry != -1) {
            predecessor = this.prevInInsertionOrder[keyEntry];
            removeEntryKeyHashKnown(keyEntry, keyHash);
        }
        ensureCapacity(this.size + 1);
        this.keys[this.size] = key;
        this.values[this.size] = value;
        insertIntoTableKToV(this.size, keyHash);
        insertIntoTableVToK(this.size, valueHash);
        int successor = predecessor == -2 ? this.firstInInsertionOrder : this.nextInInsertionOrder[predecessor];
        setSucceeds(predecessor, this.size);
        setSucceeds(this.size, successor);
        this.size++;
        this.modCount++;
        return null;
    }

    private void setSucceeds(int prev, int next) {
        if (prev == -2) {
            this.firstInInsertionOrder = next;
        } else {
            this.nextInInsertionOrder[prev] = next;
        }
        if (next == -2) {
            this.lastInInsertionOrder = prev;
        } else {
            this.prevInInsertionOrder[next] = prev;
        }
    }

    private void insertIntoTableKToV(int entry, int keyHash) {
        Preconditions.checkArgument(entry != -1);
        int keyBucket = bucket(keyHash);
        this.nextInBucketKToV[entry] = this.hashTableKToV[keyBucket];
        this.hashTableKToV[keyBucket] = entry;
    }

    private void insertIntoTableVToK(int entry, int valueHash) {
        Preconditions.checkArgument(entry != -1);
        int valueBucket = bucket(valueHash);
        this.nextInBucketVToK[entry] = this.hashTableVToK[valueBucket];
        this.hashTableVToK[valueBucket] = entry;
    }

    private void deleteFromTableKToV(int entry, int keyHash) {
        Preconditions.checkArgument(entry != -1);
        int keyBucket = bucket(keyHash);
        if (this.hashTableKToV[keyBucket] == entry) {
            this.hashTableKToV[keyBucket] = this.nextInBucketKToV[entry];
            this.nextInBucketKToV[entry] = -1;
            return;
        }
        int prevInBucket = this.hashTableKToV[keyBucket];
        int entryInBucket = this.nextInBucketKToV[prevInBucket];
        while (entryInBucket != -1) {
            if (entryInBucket == entry) {
                this.nextInBucketKToV[prevInBucket] = this.nextInBucketKToV[entry];
                this.nextInBucketKToV[entry] = -1;
                return;
            }
            prevInBucket = entryInBucket;
            entryInBucket = this.nextInBucketKToV[entryInBucket];
        }
        throw new AssertionError("Expected to find entry with key " + ((Object) this.keys[entry]));
    }

    private void deleteFromTableVToK(int entry, int valueHash) {
        Preconditions.checkArgument(entry != -1);
        int valueBucket = bucket(valueHash);
        if (this.hashTableVToK[valueBucket] == entry) {
            this.hashTableVToK[valueBucket] = this.nextInBucketVToK[entry];
            this.nextInBucketVToK[entry] = -1;
            return;
        }
        int prevInBucket = this.hashTableVToK[valueBucket];
        int entryInBucket = this.nextInBucketVToK[prevInBucket];
        while (entryInBucket != -1) {
            if (entryInBucket == entry) {
                this.nextInBucketVToK[prevInBucket] = this.nextInBucketVToK[entry];
                this.nextInBucketVToK[entry] = -1;
                return;
            }
            prevInBucket = entryInBucket;
            entryInBucket = this.nextInBucketVToK[entryInBucket];
        }
        throw new AssertionError("Expected to find entry with value " + ((Object) this.values[entry]));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void replaceValueInEntry(int entry, @NullableDecl V newValue, boolean force) {
        Preconditions.checkArgument(entry != -1);
        int newValueHash = Hashing.smearedHash(newValue);
        int newValueIndex = findEntryByValue(newValue, newValueHash);
        if (newValueIndex != -1) {
            if (force) {
                removeEntryValueHashKnown(newValueIndex, newValueHash);
                if (entry == this.size) {
                    entry = newValueIndex;
                }
            } else {
                throw new IllegalArgumentException("Value already present in map: " + ((Object) newValue));
            }
        }
        deleteFromTableVToK(entry, Hashing.smearedHash(this.values[entry]));
        this.values[entry] = newValue;
        insertIntoTableVToK(entry, newValueHash);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void replaceKeyInEntry(int entry, @NullableDecl K newKey, boolean force) {
        Preconditions.checkArgument(entry != -1);
        int newKeyHash = Hashing.smearedHash(newKey);
        int newKeyIndex = findEntryByKey(newKey, newKeyHash);
        int newPredecessor = this.lastInInsertionOrder;
        int newSuccessor = -2;
        if (newKeyIndex != -1) {
            if (force) {
                newPredecessor = this.prevInInsertionOrder[newKeyIndex];
                newSuccessor = this.nextInInsertionOrder[newKeyIndex];
                removeEntryKeyHashKnown(newKeyIndex, newKeyHash);
                if (entry == this.size) {
                    entry = newKeyIndex;
                }
            } else {
                throw new IllegalArgumentException("Key already present in map: " + ((Object) newKey));
            }
        }
        if (newPredecessor == entry) {
            newPredecessor = this.prevInInsertionOrder[entry];
        } else if (newPredecessor == this.size) {
            newPredecessor = newKeyIndex;
        }
        if (newSuccessor == entry) {
            newSuccessor = this.nextInInsertionOrder[entry];
        } else if (newSuccessor == this.size) {
            newSuccessor = newKeyIndex;
        }
        setSucceeds(this.prevInInsertionOrder[entry], this.nextInInsertionOrder[entry]);
        deleteFromTableKToV(entry, Hashing.smearedHash(this.keys[entry]));
        this.keys[entry] = newKey;
        insertIntoTableKToV(entry, Hashing.smearedHash(newKey));
        setSucceeds(newPredecessor, entry);
        setSucceeds(entry, newSuccessor);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V remove(@NullableDecl Object key) {
        int keyHash = Hashing.smearedHash(key);
        int entry = findEntryByKey(key, keyHash);
        if (entry == -1) {
            return null;
        }
        V v = this.values[entry];
        removeEntryKeyHashKnown(entry, keyHash);
        return v;
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public K removeInverse(@NullableDecl Object value) {
        int valueHash = Hashing.smearedHash(value);
        int entry = findEntryByValue(value, valueHash);
        if (entry == -1) {
            return null;
        }
        K k = this.keys[entry];
        removeEntryValueHashKnown(entry, valueHash);
        return k;
    }

    /* access modifiers changed from: package-private */
    public void removeEntry(int entry) {
        removeEntryKeyHashKnown(entry, Hashing.smearedHash(this.keys[entry]));
    }

    private void removeEntry(int entry, int keyHash, int valueHash) {
        Preconditions.checkArgument(entry != -1);
        deleteFromTableKToV(entry, keyHash);
        deleteFromTableVToK(entry, valueHash);
        setSucceeds(this.prevInInsertionOrder[entry], this.nextInInsertionOrder[entry]);
        moveEntryToIndex(this.size - 1, entry);
        this.keys[this.size - 1] = null;
        this.values[this.size - 1] = null;
        this.size--;
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    public void removeEntryKeyHashKnown(int entry, int keyHash) {
        removeEntry(entry, keyHash, Hashing.smearedHash(this.values[entry]));
    }

    /* access modifiers changed from: package-private */
    public void removeEntryValueHashKnown(int entry, int valueHash) {
        removeEntry(entry, Hashing.smearedHash(this.keys[entry]), valueHash);
    }

    private void moveEntryToIndex(int src, int dest) {
        if (src != dest) {
            int predecessor = this.prevInInsertionOrder[src];
            int successor = this.nextInInsertionOrder[src];
            setSucceeds(predecessor, dest);
            setSucceeds(dest, successor);
            K key = this.keys[src];
            V value = this.values[src];
            this.keys[dest] = key;
            this.values[dest] = value;
            int keyBucket = bucket(Hashing.smearedHash(key));
            if (this.hashTableKToV[keyBucket] == src) {
                this.hashTableKToV[keyBucket] = dest;
            } else {
                int prevInBucket = this.hashTableKToV[keyBucket];
                int entryInBucket = this.nextInBucketKToV[prevInBucket];
                while (entryInBucket != src) {
                    prevInBucket = entryInBucket;
                    entryInBucket = this.nextInBucketKToV[entryInBucket];
                }
                this.nextInBucketKToV[prevInBucket] = dest;
            }
            this.nextInBucketKToV[dest] = this.nextInBucketKToV[src];
            this.nextInBucketKToV[src] = -1;
            int valueBucket = bucket(Hashing.smearedHash(value));
            if (this.hashTableVToK[valueBucket] == src) {
                this.hashTableVToK[valueBucket] = dest;
            } else {
                int prevInBucket2 = this.hashTableVToK[valueBucket];
                int entryInBucket2 = this.nextInBucketVToK[prevInBucket2];
                while (entryInBucket2 != src) {
                    prevInBucket2 = entryInBucket2;
                    entryInBucket2 = this.nextInBucketVToK[entryInBucket2];
                }
                this.nextInBucketVToK[prevInBucket2] = dest;
            }
            this.nextInBucketVToK[dest] = this.nextInBucketVToK[src];
            this.nextInBucketVToK[src] = -1;
        }
    }

    public void clear() {
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        Arrays.fill(this.hashTableKToV, -1);
        Arrays.fill(this.hashTableVToK, -1);
        Arrays.fill(this.nextInBucketKToV, 0, this.size, -1);
        Arrays.fill(this.nextInBucketVToK, 0, this.size, -1);
        Arrays.fill(this.prevInInsertionOrder, 0, this.size, -1);
        Arrays.fill(this.nextInInsertionOrder, 0, this.size, -1);
        this.size = 0;
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    public static abstract class View<K, V, T> extends AbstractSet<T> {
        final HashBiMap<K, V> biMap;

        /* access modifiers changed from: package-private */
        public abstract T forEntry(int i);

        View(HashBiMap<K, V> biMap2) {
            this.biMap = biMap2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                /* class com.google.common.collect.HashBiMap.View.AnonymousClass1 */
                private int expectedModCount = View.this.biMap.modCount;
                private int index = ((HashBiMap) View.this.biMap).firstInInsertionOrder;
                private int indexToRemove = -1;
                private int remaining = View.this.biMap.size;

                private void checkForComodification() {
                    if (View.this.biMap.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                public boolean hasNext() {
                    checkForComodification();
                    return this.index != -2 && this.remaining > 0;
                }

                @Override // java.util.Iterator
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    T result = (T) View.this.forEntry(this.index);
                    this.indexToRemove = this.index;
                    this.index = ((HashBiMap) View.this.biMap).nextInInsertionOrder[this.index];
                    this.remaining--;
                    return result;
                }

                public void remove() {
                    checkForComodification();
                    CollectPreconditions.checkRemove(this.indexToRemove != -1);
                    View.this.biMap.removeEntry(this.indexToRemove);
                    if (this.index == View.this.biMap.size) {
                        this.index = this.indexToRemove;
                    }
                    this.indexToRemove = -1;
                    this.expectedModCount = View.this.biMap.modCount;
                }
            };
        }

        public int size() {
            return this.biMap.size;
        }

        public void clear() {
            this.biMap.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> result = this.keySet;
        if (result != null) {
            return result;
        }
        Set<K> result2 = new KeySet();
        this.keySet = result2;
        return result2;
    }

    /* access modifiers changed from: package-private */
    public final class KeySet extends View<K, V, K> {
        KeySet() {
            super(HashBiMap.this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public K forEntry(int entry) {
            return HashBiMap.this.keys[entry];
        }

        public boolean contains(@NullableDecl Object o) {
            return HashBiMap.this.containsKey(o);
        }

        public boolean remove(@NullableDecl Object o) {
            int oHash = Hashing.smearedHash(o);
            int entry = HashBiMap.this.findEntryByKey(o, oHash);
            if (entry == -1) {
                return false;
            }
            HashBiMap.this.removeEntryKeyHashKnown(entry, oHash);
            return true;
        }
    }

    @Override // com.google.common.collect.BiMap, java.util.AbstractMap, java.util.Map
    public Set<V> values() {
        Set<V> result = this.valueSet;
        if (result != null) {
            return result;
        }
        Set<V> result2 = new ValueSet();
        this.valueSet = result2;
        return result2;
    }

    /* access modifiers changed from: package-private */
    public final class ValueSet extends View<K, V, V> {
        ValueSet() {
            super(HashBiMap.this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public V forEntry(int entry) {
            return HashBiMap.this.values[entry];
        }

        public boolean contains(@NullableDecl Object o) {
            return HashBiMap.this.containsValue(o);
        }

        public boolean remove(@NullableDecl Object o) {
            int oHash = Hashing.smearedHash(o);
            int entry = HashBiMap.this.findEntryByValue(o, oHash);
            if (entry == -1) {
                return false;
            }
            HashBiMap.this.removeEntryValueHashKnown(entry, oHash);
            return true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> result = this.entrySet;
        if (result != null) {
            return result;
        }
        Set<Map.Entry<K, V>> result2 = new EntrySet();
        this.entrySet = result2;
        return result2;
    }

    final class EntrySet extends View<K, V, Map.Entry<K, V>> {
        EntrySet() {
            super(HashBiMap.this);
        }

        public boolean contains(@NullableDecl Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            Object k = e.getKey();
            Object v = e.getValue();
            int eIndex = HashBiMap.this.findEntryByKey(k);
            if (eIndex == -1 || !Objects.equal(v, HashBiMap.this.values[eIndex])) {
                return false;
            }
            return true;
        }

        @CanIgnoreReturnValue
        public boolean remove(@NullableDecl Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry) o;
                Object k = e.getKey();
                Object v = e.getValue();
                int kHash = Hashing.smearedHash(k);
                int eIndex = HashBiMap.this.findEntryByKey(k, kHash);
                if (eIndex != -1 && Objects.equal(v, HashBiMap.this.values[eIndex])) {
                    HashBiMap.this.removeEntryKeyHashKnown(eIndex, kHash);
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<K, V> forEntry(int entry) {
            return new EntryForKey(entry);
        }
    }

    /* access modifiers changed from: package-private */
    public final class EntryForKey extends AbstractMapEntry<K, V> {
        int index;
        @NullableDecl
        final K key;

        EntryForKey(int index2) {
            this.key = HashBiMap.this.keys[index2];
            this.index = index2;
        }

        /* access modifiers changed from: package-private */
        public void updateIndex() {
            if (this.index == -1 || this.index > HashBiMap.this.size || !Objects.equal(HashBiMap.this.keys[this.index], this.key)) {
                this.index = HashBiMap.this.findEntryByKey(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @NullableDecl
        public V getValue() {
            updateIndex();
            if (this.index == -1) {
                return null;
            }
            return HashBiMap.this.values[this.index];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V value) {
            updateIndex();
            if (this.index == -1) {
                return (V) HashBiMap.this.put(this.key, value);
            }
            V oldValue = HashBiMap.this.values[this.index];
            if (Objects.equal(oldValue, value)) {
                return value;
            }
            HashBiMap.this.replaceValueInEntry(this.index, value, false);
            return oldValue;
        }
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        BiMap<V, K> result = this.inverse;
        if (result != null) {
            return result;
        }
        BiMap<V, K> result2 = new Inverse<>(this);
        this.inverse = result2;
        return result2;
    }

    static class Inverse<K, V> extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {
        private final HashBiMap<K, V> forward;
        private transient Set<Map.Entry<V, K>> inverseEntrySet;

        Inverse(HashBiMap<K, V> forward2) {
            this.forward = forward2;
        }

        public int size() {
            return this.forward.size;
        }

        public boolean containsKey(@NullableDecl Object key) {
            return this.forward.containsValue(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        public K get(@NullableDecl Object key) {
            return this.forward.getInverse(key);
        }

        public boolean containsValue(@NullableDecl Object value) {
            return this.forward.containsKey(value);
        }

        @Override // com.google.common.collect.BiMap, java.util.AbstractMap, java.util.Map
        @CanIgnoreReturnValue
        @NullableDecl
        public K put(@NullableDecl V value, @NullableDecl K key) {
            return this.forward.putInverse(value, key, false);
        }

        @Override // com.google.common.collect.BiMap
        @CanIgnoreReturnValue
        @NullableDecl
        public K forcePut(@NullableDecl V value, @NullableDecl K key) {
            return this.forward.putInverse(value, key, true);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<K, V> inverse() {
            return this.forward;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CanIgnoreReturnValue
        @NullableDecl
        public K remove(@NullableDecl Object value) {
            return this.forward.removeInverse(value);
        }

        public void clear() {
            this.forward.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<V> keySet() {
            return this.forward.values();
        }

        @Override // com.google.common.collect.BiMap, java.util.AbstractMap, java.util.Map
        public Set<K> values() {
            return this.forward.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<V, K>> entrySet() {
            Set<Map.Entry<V, K>> result = this.inverseEntrySet;
            if (result != null) {
                return result;
            }
            InverseEntrySet inverseEntrySet2 = new InverseEntrySet(this.forward);
            this.inverseEntrySet = inverseEntrySet2;
            return inverseEntrySet2;
        }

        @GwtIncompatible("serialization")
        private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
            in.defaultReadObject();
            ((HashBiMap) this.forward).inverse = this;
        }
    }

    static class InverseEntrySet<K, V> extends View<K, V, Map.Entry<V, K>> {
        InverseEntrySet(HashBiMap<K, V> biMap) {
            super(biMap);
        }

        public boolean contains(@NullableDecl Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            Object v = e.getKey();
            Object k = e.getValue();
            int eIndex = this.biMap.findEntryByValue(v);
            if (eIndex == -1 || !Objects.equal(this.biMap.keys[eIndex], k)) {
                return false;
            }
            return true;
        }

        public boolean remove(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry) o;
                Object v = e.getKey();
                Object k = e.getValue();
                int vHash = Hashing.smearedHash(v);
                int eIndex = this.biMap.findEntryByValue(v, vHash);
                if (eIndex != -1 && Objects.equal(this.biMap.keys[eIndex], k)) {
                    this.biMap.removeEntryValueHashKnown(eIndex, vHash);
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<V, K> forEntry(int entry) {
            return new EntryForValue(this.biMap, entry);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class EntryForValue<K, V> extends AbstractMapEntry<V, K> {
        final HashBiMap<K, V> biMap;
        int index;
        final V value;

        EntryForValue(HashBiMap<K, V> biMap2, int index2) {
            this.biMap = biMap2;
            this.value = biMap2.values[index2];
            this.index = index2;
        }

        private void updateIndex() {
            if (this.index == -1 || this.index > this.biMap.size || !Objects.equal(this.value, this.biMap.values[this.index])) {
                this.index = this.biMap.findEntryByValue(this.value);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getKey() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getValue() {
            updateIndex();
            if (this.index == -1) {
                return null;
            }
            return this.biMap.keys[this.index];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K setValue(K key) {
            updateIndex();
            if (this.index == -1) {
                return this.biMap.putInverse(this.value, key, false);
            }
            K oldKey = this.biMap.keys[this.index];
            if (Objects.equal(oldKey, key)) {
                return key;
            }
            this.biMap.replaceKeyInEntry(this.index, key, false);
            return oldKey;
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        Serialization.writeMap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int size2 = Serialization.readCount(stream);
        init(16);
        Serialization.populateMap(this, stream, size2);
    }
}
