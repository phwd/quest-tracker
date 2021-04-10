package com.google.common.collect;

import com.facebook.imagepipeline.common.BytesRange;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashMap;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntFunction;
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

    private static int getHash(long j) {
        return (int) (j >>> 32);
    }

    private static int getNext(long j) {
        return (int) j;
    }

    private static long swapNext(long j, int i) {
        return (j & HASH_MASK) | (((long) i) & NEXT_MASK);
    }

    /* access modifiers changed from: package-private */
    public void accessEntry(int i) {
    }

    /* access modifiers changed from: package-private */
    public int adjustAfterRemove(int i, int i2) {
        return i - 1;
    }

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int i) {
        return new CompactHashMap<>(i);
    }

    CompactHashMap() {
        init(3, 1.0f);
    }

    CompactHashMap(int i) {
        this(i, 1.0f);
    }

    CompactHashMap(int i, float f) {
        init(i, f);
    }

    /* access modifiers changed from: package-private */
    public void init(int i, float f) {
        boolean z = false;
        Preconditions.checkArgument(i >= 0, "Initial capacity must be non-negative");
        if (f > 0.0f) {
            z = true;
        }
        Preconditions.checkArgument(z, "Illegal load factor");
        int closedTableSize = Hashing.closedTableSize(i, (double) f);
        this.table = newTable(closedTableSize);
        this.loadFactor = f;
        this.keys = new Object[i];
        this.values = new Object[i];
        this.entries = newEntries(i);
        this.threshold = Math.max(1, (int) (((float) closedTableSize) * f));
    }

    private static int[] newTable(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static long[] newEntries(int i) {
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V put(@NullableDecl K k, @NullableDecl V v) {
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
        int smearedHash = Hashing.smearedHash(k);
        int hashTableMask = hashTableMask() & smearedHash;
        int i = this.size;
        int[] iArr = this.table;
        int i2 = iArr[hashTableMask];
        if (i2 == -1) {
            iArr[hashTableMask] = i;
        } else {
            while (true) {
                long j = jArr[i2];
                if (getHash(j) != smearedHash || !Objects.equal(k, objArr[i2])) {
                    int next = getNext(j);
                    if (next == -1) {
                        jArr[i2] = swapNext(j, i);
                        break;
                    }
                    i2 = next;
                } else {
                    V v2 = (V) objArr2[i2];
                    objArr2[i2] = v;
                    accessEntry(i2);
                    return v2;
                }
            }
        }
        if (i != Integer.MAX_VALUE) {
            int i3 = i + 1;
            resizeMeMaybe(i3);
            insertEntry(i, k, v, smearedHash);
            this.size = i3;
            if (i >= this.threshold) {
                resizeTable(this.table.length * 2);
            }
            this.modCount++;
            return null;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    /* access modifiers changed from: package-private */
    public void insertEntry(int i, @NullableDecl K k, @NullableDecl V v, int i2) {
        this.entries[i] = (((long) i2) << 32) | NEXT_MASK;
        this.keys[i] = k;
        this.values[i] = v;
    }

    private void resizeMeMaybe(int i) {
        int length = this.entries.length;
        if (i > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = BytesRange.TO_END_OF_CONTENT;
            }
            if (max != length) {
                resizeEntries(max);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resizeEntries(int i) {
        this.keys = Arrays.copyOf(this.keys, i);
        this.values = Arrays.copyOf(this.values, i);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.entries = copyOf;
    }

    private void resizeTable(int i) {
        if (this.table.length >= 1073741824) {
            this.threshold = BytesRange.TO_END_OF_CONTENT;
            return;
        }
        int i2 = ((int) (((float) i) * this.loadFactor)) + 1;
        int[] newTable = newTable(i);
        long[] jArr = this.entries;
        int length = newTable.length - 1;
        for (int i3 = 0; i3 < this.size; i3++) {
            int hash = getHash(jArr[i3]);
            int i4 = hash & length;
            int i5 = newTable[i4];
            newTable[i4] = i3;
            jArr[i3] = (((long) hash) << 32) | (((long) i5) & NEXT_MASK);
        }
        this.threshold = i2;
        this.table = newTable;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int indexOf(@NullableDecl Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int i = this.table[hashTableMask() & smearedHash];
        while (i != -1) {
            long j = this.entries[i];
            if (getHash(j) == smearedHash && Objects.equal(obj, this.keys[i])) {
                return i;
            }
            i = getNext(j);
        }
        return -1;
    }

    public boolean containsKey(@NullableDecl Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        int indexOf = indexOf(obj);
        accessEntry(indexOf);
        if (indexOf == -1) {
            return null;
        }
        return (V) this.values[indexOf];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public V remove(@NullableDecl Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    @NullableDecl
    private V remove(@NullableDecl Object obj, int i) {
        int hashTableMask = hashTableMask() & i;
        int i2 = this.table[hashTableMask];
        if (i2 == -1) {
            return null;
        }
        int i3 = -1;
        while (true) {
            if (getHash(this.entries[i2]) != i || !Objects.equal(obj, this.keys[i2])) {
                int next = getNext(this.entries[i2]);
                if (next == -1) {
                    return null;
                }
                i3 = i2;
                i2 = next;
            } else {
                V v = (V) this.values[i2];
                if (i3 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[i2]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i3] = swapNext(jArr[i3], getNext(jArr[i2]));
                }
                moveLastEntry(i2);
                this.size--;
                this.modCount++;
                return v;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @CanIgnoreReturnValue
    private V removeEntry(int i) {
        return remove(this.keys[i], getHash(this.entries[i]));
    }

    /* access modifiers changed from: package-private */
    public void moveLastEntry(int i) {
        int size2 = size() - 1;
        if (i < size2) {
            Object[] objArr = this.keys;
            objArr[i] = objArr[size2];
            Object[] objArr2 = this.values;
            objArr2[i] = objArr2[size2];
            objArr[size2] = null;
            objArr2[size2] = null;
            long[] jArr = this.entries;
            long j = jArr[size2];
            jArr[i] = j;
            jArr[size2] = -1;
            int hash = getHash(j) & hashTableMask();
            int[] iArr = this.table;
            int i2 = iArr[hash];
            if (i2 == size2) {
                iArr[hash] = i;
                return;
            }
            while (true) {
                long j2 = this.entries[i2];
                int next = getNext(j2);
                if (next == size2) {
                    this.entries[i2] = swapNext(j2, i);
                    return;
                }
                i2 = next;
            }
        } else {
            this.keys[i] = null;
            this.values[i] = null;
            this.entries[i] = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    /* access modifiers changed from: package-private */
    public int getSuccessor(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
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
            if (hasNext()) {
                int i = this.currentIndex;
                this.indexToRemove = i;
                T output = getOutput(i);
                this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
                return output;
            }
            throw new NoSuchElementException();
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

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction) {
        Preconditions.checkNotNull(biFunction);
        for (int i = 0; i < this.size; i++) {
            Object[] objArr = this.values;
            objArr[i] = biFunction.apply(this.keys[i], objArr[i]);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set != null) {
            return set;
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
    public class KeySetView extends Maps.KeySet<K, V> {
        KeySetView() {
            super(CompactHashMap.this);
        }

        public Object[] toArray() {
            return ObjectArrays.copyAsObjectArray(CompactHashMap.this.keys, 0, CompactHashMap.this.size);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) ObjectArrays.toArrayImpl(CompactHashMap.this.keys, 0, CompactHashMap.this.size, tArr);
        }

        @Override // com.google.common.collect.Maps.KeySet
        public boolean remove(@NullableDecl Object obj) {
            int indexOf = CompactHashMap.this.indexOf(obj);
            if (indexOf == -1) {
                return false;
            }
            CompactHashMap.this.removeEntry(indexOf);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, com.google.common.collect.Maps.KeySet, java.lang.Iterable
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<K> spliterator() {
            return Spliterators.spliterator(CompactHashMap.this.keys, 0, CompactHashMap.this.size, 17);
        }

        @Override // com.google.common.collect.Maps.KeySet, java.lang.Iterable
        public void forEach(Consumer<? super K> consumer) {
            Preconditions.checkNotNull(consumer);
            for (int i = 0; i < CompactHashMap.this.size; i++) {
                consumer.accept(CompactHashMap.this.keys[i]);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<K> keySetIterator() {
        return new CompactHashMap<K, V>.Itr() {
            /* class com.google.common.collect.CompactHashMap.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public K getOutput(int i) {
                return (K) CompactHashMap.this.keys[i];
            }
        };
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> biConsumer) {
        Preconditions.checkNotNull(biConsumer);
        for (int i = 0; i < this.size; i++) {
            biConsumer.accept(this.keys[i], this.values[i]);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySetView;
        if (set != null) {
            return set;
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
    public class EntrySetView extends Maps.EntrySet<K, V> {
        EntrySetView() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.EntrySet
        public Map<K, V> map() {
            return CompactHashMap.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return CollectSpliterators.indexed(CompactHashMap.this.size, 17, new IntFunction() {
                /* class com.google.common.collect.$$Lambda$CompactHashMap$EntrySetView$zx9uq9I50IJ3NBsUVususdSDP4g */

                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return CompactHashMap.EntrySetView.this.lambda$spliterator$0$CompactHashMap$EntrySetView(i);
                }
            });
        }

        public /* synthetic */ Map.Entry lambda$spliterator$0$CompactHashMap$EntrySetView(int i) {
            return new MapEntry(i);
        }

        @Override // com.google.common.collect.Maps.EntrySet
        public boolean contains(@NullableDecl Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int indexOf = CompactHashMap.this.indexOf(entry.getKey());
            if (indexOf == -1 || !Objects.equal(CompactHashMap.this.values[indexOf], entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.Maps.EntrySet
        public boolean remove(@NullableDecl Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int indexOf = CompactHashMap.this.indexOf(entry.getKey());
            if (indexOf == -1 || !Objects.equal(CompactHashMap.this.values[indexOf], entry.getValue())) {
                return false;
            }
            CompactHashMap.this.removeEntry(indexOf);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entrySetIterator() {
        return new CompactHashMap<K, V>.Itr() {
            /* class com.google.common.collect.CompactHashMap.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public Map.Entry<K, V> getOutput(int i) {
                return new MapEntry(i);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public final class MapEntry extends AbstractMapEntry<K, V> {
        @NullableDecl
        private final K key;
        private int lastKnownIndex;

        MapEntry(int i) {
            this.key = (K) CompactHashMap.this.keys[i];
            this.lastKnownIndex = i;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            int i = this.lastKnownIndex;
            if (i == -1 || i >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.keys[this.lastKnownIndex])) {
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
        public V setValue(V v) {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                CompactHashMap.this.put(this.key, v);
                return null;
            }
            V v2 = (V) CompactHashMap.this.values[this.lastKnownIndex];
            CompactHashMap.this.values[this.lastKnownIndex] = v;
            return v2;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean containsValue(@NullableDecl Object obj) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equal(obj, this.values[i])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
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
    public class ValuesView extends Maps.Values<K, V> {
        ValuesView() {
            super(CompactHashMap.this);
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }

        @Override // com.google.common.collect.Maps.Values, java.lang.Iterable
        public void forEach(Consumer<? super V> consumer) {
            Preconditions.checkNotNull(consumer);
            for (int i = 0; i < CompactHashMap.this.size; i++) {
                consumer.accept(CompactHashMap.this.values[i]);
            }
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<V> spliterator() {
            return Spliterators.spliterator(CompactHashMap.this.values, 0, CompactHashMap.this.size, 16);
        }

        public Object[] toArray() {
            return ObjectArrays.copyAsObjectArray(CompactHashMap.this.values, 0, CompactHashMap.this.size);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) ObjectArrays.toArrayImpl(CompactHashMap.this.values, 0, CompactHashMap.this.size, tArr);
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valuesIterator() {
        return new CompactHashMap<K, V>.Itr() {
            /* class com.google.common.collect.CompactHashMap.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public V getOutput(int i) {
                return (V) CompactHashMap.this.values[i];
            }
        };
    }

    public void trimToSize() {
        int i = this.size;
        if (i < this.entries.length) {
            resizeEntries(i);
        }
        int max = Math.max(1, Integer.highestOneBit((int) (((float) i) / this.loadFactor)));
        if (max < 1073741824 && ((double) i) / ((double) max) > ((double) this.loadFactor)) {
            max <<= 1;
        }
        if (max < this.table.length) {
            resizeTable(max);
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

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.size);
        for (int i = 0; i < this.size; i++) {
            objectOutputStream.writeObject(this.keys[i]);
            objectOutputStream.writeObject(this.values[i]);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.CompactHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init(3, 1.0f);
        int readInt = objectInputStream.readInt();
        while (true) {
            readInt--;
            if (readInt >= 0) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            } else {
                return;
            }
        }
    }
}
