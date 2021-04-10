package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtIncompatible
public class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    private static final float DEFAULT_LOAD_FACTOR = 1.0f;
    private static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    @MonotonicNonNullDecl
    transient Object[] elements;
    @MonotonicNonNullDecl
    private transient long[] entries;
    transient float loadFactor;
    transient int modCount;
    private transient int size;
    @MonotonicNonNullDecl
    private transient int[] table;
    private transient int threshold;

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> set = createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    public static <E> CompactHashSet<E> create(E... elements2) {
        CompactHashSet<E> set = createWithExpectedSize(elements2.length);
        Collections.addAll(set, elements2);
        return set;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactHashSet<>(expectedSize);
    }

    CompactHashSet() {
        init(3, DEFAULT_LOAD_FACTOR);
    }

    CompactHashSet(int expectedSize) {
        init(expectedSize, DEFAULT_LOAD_FACTOR);
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
        this.elements = new Object[expectedSize];
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

    /* access modifiers changed from: private */
    public static int getHash(long entry) {
        return (int) (entry >>> 32);
    }

    private static int getNext(long entry) {
        return (int) entry;
    }

    private static long swapNext(long entry, int newNext) {
        return (HASH_MASK & entry) | (NEXT_MASK & ((long) newNext));
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean add(@NullableDecl E object) {
        long entry;
        long[] entries2 = this.entries;
        Object[] elements2 = this.elements;
        int hash = Hashing.smearedHash(object);
        int tableIndex = hash & hashTableMask();
        int newEntryIndex = this.size;
        int next = this.table[tableIndex];
        if (next == -1) {
            this.table[tableIndex] = newEntryIndex;
        } else {
            do {
                entry = entries2[next];
                if (getHash(entry) == hash && Objects.equal(object, elements2[next])) {
                    return false;
                }
                next = getNext(entry);
            } while (next != -1);
            entries2[next] = swapNext(entry, newEntryIndex);
        }
        if (newEntryIndex == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int newSize = newEntryIndex + 1;
        resizeMeMaybe(newSize);
        insertEntry(newEntryIndex, object, hash);
        this.size = newSize;
        if (newEntryIndex >= this.threshold) {
            resizeTable(this.table.length * 2);
        }
        this.modCount++;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int entryIndex, E object, int hash) {
        this.entries[entryIndex] = (((long) hash) << 32) | NEXT_MASK;
        this.elements[entryIndex] = object;
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
        this.elements = Arrays.copyOf(this.elements, newCapacity);
        long[] entries2 = this.entries;
        int oldSize = entries2.length;
        long[] entries3 = Arrays.copyOf(entries2, newCapacity);
        if (newCapacity > oldSize) {
            Arrays.fill(entries3, oldSize, newCapacity, -1L);
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

    public boolean contains(@NullableDecl Object object) {
        int hash = Hashing.smearedHash(object);
        int next = this.table[hashTableMask() & hash];
        while (next != -1) {
            long entry = this.entries[next];
            if (getHash(entry) == hash && Objects.equal(object, this.elements[next])) {
                return true;
            }
            next = getNext(entry);
        }
        return false;
    }

    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object object) {
        return remove(object, Hashing.smearedHash(object));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @CanIgnoreReturnValue
    private boolean remove(Object object, int hash) {
        int tableIndex = hash & hashTableMask();
        int next = this.table[tableIndex];
        if (next == -1) {
            return false;
        }
        int last = -1;
        do {
            if (getHash(this.entries[next]) != hash || !Objects.equal(object, this.elements[next])) {
                last = next;
                next = getNext(this.entries[next]);
            } else {
                if (last == -1) {
                    this.table[tableIndex] = getNext(this.entries[next]);
                } else {
                    this.entries[last] = swapNext(this.entries[last], getNext(this.entries[next]));
                }
                moveEntry(next);
                this.size--;
                this.modCount++;
                return true;
            }
        } while (next != -1);
        return false;
    }

    /* access modifiers changed from: package-private */
    public void moveEntry(int dstIndex) {
        long entry;
        int srcIndex = size() - 1;
        if (dstIndex < srcIndex) {
            this.elements[dstIndex] = this.elements[srcIndex];
            this.elements[srcIndex] = null;
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
        this.elements[dstIndex] = null;
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /* class com.google.common.collect.CompactHashSet.AnonymousClass1 */
            int expectedModCount = CompactHashSet.this.modCount;
            int index = CompactHashSet.this.firstEntryIndex();
            int indexToRemove = -1;

            public boolean hasNext() {
                return this.index >= 0;
            }

            @Override // java.util.Iterator
            public E next() {
                checkForConcurrentModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.indexToRemove = this.index;
                E result = (E) CompactHashSet.this.elements[this.index];
                this.index = CompactHashSet.this.getSuccessor(this.index);
                return result;
            }

            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                this.expectedModCount++;
                CompactHashSet.this.remove(CompactHashSet.this.elements[this.indexToRemove], CompactHashSet.getHash(CompactHashSet.this.entries[this.indexToRemove]));
                this.index = CompactHashSet.this.adjustAfterRemove(this.index, this.indexToRemove);
                this.indexToRemove = -1;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] a) {
        return (T[]) ObjectArrays.toArrayImpl(this.elements, 0, this.size, a);
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
        Arrays.fill(this.elements, 0, this.size, (Object) null);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size);
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            stream.writeObject(it.next());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.collect.CompactHashSet<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        init(3, DEFAULT_LOAD_FACTOR);
        int i = stream.readInt();
        while (true) {
            i--;
            if (i >= 0) {
                add(stream.readObject());
            } else {
                return;
            }
        }
    }
}
