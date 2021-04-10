package com.facebook.common.collectlite;

import com.facebook.debug.log.LoggingUtil;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

public class LeanHashMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    static final float LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_ARRAY_SIZE = 536870912;
    private static final int MAXIMUM_CAPACITY = 268435456;
    private static final int MINIMUM_CAPACITY = 4;
    private static final Object NULL_KEY = new Object() {
        /* class com.facebook.common.collectlite.LeanHashMap.AnonymousClass1 */

        public boolean equals(Object obj) {
            return obj == this;
        }

        public int hashCode() {
            return 0;
        }
    };
    private static final long serialVersionUID = 5491530837953731070L;
    @Nullable
    private transient Set<Map.Entry<K, V>> mEntrySet;
    @Nullable
    private transient Set<K> mKeySet;
    private transient int mModCount;
    private transient int mSize;
    private transient Object[] mTable;
    private transient int mThreshold;
    @Nullable
    private transient Collection<V> mValues;

    /* access modifiers changed from: private */
    public static int keyIndex(int i) {
        return i << 1;
    }

    private static int roundUpToPowerOfTwo(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        return (i6 | (i6 >>> 16)) + 1;
    }

    private static int secondaryHash(int i) {
        int i2 = i + ((i << 15) ^ -12931);
        int i3 = i2 ^ (i2 >>> 10);
        int i4 = i3 + (i3 << 3);
        int i5 = i4 ^ (i4 >>> 6);
        int i6 = i5 + (i5 << 2) + (i5 << 14);
        return i6 ^ (i6 >>> 16);
    }

    /* access modifiers changed from: private */
    public static int valueIndex(int i) {
        return (i << 1) + 1;
    }

    public LeanHashMap() {
        this(4);
    }

    public LeanHashMap(int i) {
        if (i >= 0) {
            int i2 = MAXIMUM_CAPACITY;
            if (i < 4) {
                i2 = 4;
            } else if (i <= MAXIMUM_CAPACITY) {
                i2 = roundUpToPowerOfTwo(i);
            }
            resizeMappingTable(i2);
            return;
        }
        throw new IllegalArgumentException("Invalid capacity: " + i + " (must not be less than zero).");
    }

    public LeanHashMap(Map<? extends K, ? extends V> map) {
        this(map.size());
        putAll(map);
    }

    public boolean containsKey(@Nullable Object obj) {
        return findKeyBaseIndex(obj) >= 0;
    }

    public boolean containsValue(@Nullable Object obj) {
        if (obj == null) {
            return containsNullValue();
        }
        int currentCapacity = currentCapacity();
        for (int i = 0; i < currentCapacity; i++) {
            Object obj2 = this.mTable[valueIndex(i)];
            if (obj2 != null && (obj2 == obj || obj2.equals(obj))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsNullValue() {
        int currentCapacity = currentCapacity();
        for (int i = 0; i < currentCapacity; i++) {
            if (this.mTable[valueIndex(i)] == null && this.mTable[keyIndex(i)] != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    @Nullable
    public V get(@Nullable Object obj) {
        int findKeyBaseIndex = findKeyBaseIndex(obj);
        if (findKeyBaseIndex >= 0) {
            return (V) this.mTable[valueIndex(findKeyBaseIndex)];
        }
        return null;
    }

    public int firstIndex() {
        return nextIndex(-1);
    }

    public int nextIndex(int i) {
        if (this.mSize <= 0) {
            return -1;
        }
        int currentCapacity = currentCapacity();
        do {
            i++;
            if (i >= currentCapacity) {
                return -1;
            }
        } while (this.mTable[keyIndex(i)] == null);
        return i;
    }

    @Nullable
    public K keyAt(int i) {
        K k = (K) this.mTable[keyIndex(i)];
        if (k == null) {
            throw new NoSuchElementException();
        } else if (k == NULL_KEY) {
            return null;
        } else {
            return k;
        }
    }

    @Nullable
    public V valueAt(int i) {
        if (this.mTable[keyIndex(i)] != null) {
            return (V) this.mTable[valueIndex(i)];
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.mEntrySet == null) {
            this.mEntrySet = new EntrySet();
        }
        return this.mEntrySet;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        if (this.mKeySet == null) {
            this.mKeySet = new KeySet();
        }
        return this.mKeySet;
    }

    @Override // java.util.Map
    public Collection<V> values() {
        if (this.mValues == null) {
            this.mValues = new Values();
        }
        return this.mValues;
    }

    @Override // java.util.Map
    @Nullable
    public V put(@Nullable K k, @Nullable V v) {
        if (k == null) {
            k = (K) NULL_KEY;
        }
        int baseIndex = baseIndex(k);
        while (true) {
            Object obj = this.mTable[keyIndex(baseIndex)];
            if (obj == null) {
                addNewEntry(k, v, baseIndex);
                return null;
            } else if (obj == k || obj.equals(k)) {
                int valueIndex = valueIndex(baseIndex);
                Object[] objArr = this.mTable;
                V v2 = (V) objArr[valueIndex];
                objArr[valueIndex] = v;
            } else {
                baseIndex = nextBaseIndex(baseIndex);
            }
        }
        int valueIndex2 = valueIndex(baseIndex);
        Object[] objArr2 = this.mTable;
        V v22 = (V) objArr2[valueIndex2];
        objArr2[valueIndex2] = v;
        return v22;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.facebook.common.collectlite.LeanHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map.size() != 0) {
            if (map.size() > size() && map.size() > currentCapacity()) {
                increaseCapacityAndRehash(roundUpToPowerOfTwo(map.size()));
            }
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private void addNewEntry(Object obj, Object obj2, int i) {
        int i2 = this.mSize;
        if (i2 != MAXIMUM_CAPACITY) {
            if (i2 >= this.mThreshold) {
                doubleCapacityAndRehash();
                i = baseIndex(obj);
            }
            while (this.mTable[keyIndex(i)] != null) {
                i = nextBaseIndex(i);
            }
            this.mTable[keyIndex(i)] = obj;
            this.mTable[valueIndex(i)] = obj2;
            this.mSize++;
            this.mModCount++;
            return;
        }
        throw new IllegalStateException("Maximum capacity reached, cannot add new item.");
    }

    @Override // java.util.Map
    @Nullable
    public V remove(Object obj) {
        int findKeyBaseIndex = findKeyBaseIndex(obj);
        if (findKeyBaseIndex < 0) {
            return null;
        }
        V valueAt = valueAt(findKeyBaseIndex);
        removeEntryAt(findKeyBaseIndex);
        return valueAt;
    }

    public void clear() {
        this.mSize = 0;
        Arrays.fill(this.mTable, (Object) null);
        this.mModCount++;
    }

    public int size() {
        return this.mSize;
    }

    public boolean isEmpty() {
        return this.mSize == 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean z = true;
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            if (!z) {
                sb.append(", ");
            }
            Object keyAt = keyAt(firstIndex);
            if (keyAt == null) {
                keyAt = LoggingUtil.NO_HASHCODE;
            }
            sb.append(keyAt);
            sb.append("=");
            sb.append((Object) valueAt(firstIndex));
            z = false;
            firstIndex = nextIndex(firstIndex);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // java.lang.Object
    public Object clone() throws CloneNotSupportedException {
        try {
            LeanHashMap leanHashMap = (LeanHashMap) super.clone();
            leanHashMap.mTable = new Object[this.mTable.length];
            Object[] objArr = this.mTable;
            System.arraycopy(objArr, 0, leanHashMap.mTable, 0, objArr.length);
            return leanHashMap;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public int hashCode() {
        int i;
        int firstIndex = firstIndex();
        int i2 = 0;
        while (firstIndex >= 0) {
            K keyAt = keyAt(firstIndex);
            V valueAt = valueAt(firstIndex);
            if (keyAt == null) {
                i = 0;
            } else {
                i = keyAt.hashCode();
            }
            i2 += i ^ (valueAt == null ? 0 : valueAt.hashCode());
            firstIndex = nextIndex(firstIndex);
        }
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            K keyAt = keyAt(firstIndex);
            V valueAt = valueAt(firstIndex);
            if (!map.containsKey(keyAt) || !objectEquals(map.get(keyAt), valueAt)) {
                return false;
            }
            firstIndex = nextIndex(firstIndex);
        }
        return true;
    }

    private void doubleCapacityAndRehash() {
        increaseCapacityAndRehash(currentCapacity() * 2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.facebook.common.collectlite.LeanHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void increaseCapacityAndRehash(int i) {
        if (((i - 1) & i) == 0) {
            int currentCapacity = currentCapacity();
            if (i <= currentCapacity) {
                throw new IllegalArgumentException("New capacity must be greater than current capacity.");
            } else if (i <= MAXIMUM_CAPACITY) {
                Object[] objArr = this.mTable;
                int i2 = this.mSize;
                resizeMappingTable(i);
                if (i2 > 0) {
                    int i3 = 0;
                    for (int i4 = 0; i4 < currentCapacity && i3 < i2; i4++) {
                        Object obj = objArr[keyIndex(i4)];
                        if (obj != null) {
                            put(obj, objArr[valueIndex(i4)]);
                            i3++;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("New capacity is greater than maximum capacity.");
            }
        } else {
            throw new IllegalArgumentException("New capacity must be a power of two.");
        }
    }

    private void resizeMappingTable(int i) {
        if (((i - 1) & i) == 0) {
            this.mThreshold = (i >> 1) + (i >> 2);
            this.mSize = 0;
            this.mTable = new Object[(i * 2)];
            return;
        }
        throw new IllegalArgumentException("Capacity must be a power of two.");
    }

    private int baseIndex(Object obj) {
        return keyHash(obj) & (currentCapacity() - 1);
    }

    private int nextBaseIndex(int i) {
        return (i + 1) & (currentCapacity() - 1);
    }

    private int currentCapacity() {
        return this.mTable.length >>> 1;
    }

    /* access modifiers changed from: private */
    public static boolean objectEquals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    private static int keyHash(Object obj) {
        return secondaryHash(obj.hashCode());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean containsMapping(Object obj, Object obj2) {
        int findKeyBaseIndex = findKeyBaseIndex(obj);
        return findKeyBaseIndex >= 0 && objectEquals(obj2, this.mTable[valueIndex(findKeyBaseIndex)]);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeMapping(Object obj, Object obj2) {
        int findKeyBaseIndex = findKeyBaseIndex(obj);
        if (findKeyBaseIndex < 0 || !objectEquals(obj2, this.mTable[valueIndex(findKeyBaseIndex)])) {
            return false;
        }
        removeEntryAt(findKeyBaseIndex);
        return true;
    }

    private void removeEntryAt(int i) {
        if (this.mTable[keyIndex(i)] != null) {
            int nextBaseIndex = nextBaseIndex(i);
            while (this.mTable[keyIndex(nextBaseIndex)] != null) {
                if (needToShiftUpOnDelete(i, nextBaseIndex)) {
                    this.mTable[keyIndex(i)] = this.mTable[keyIndex(nextBaseIndex)];
                    this.mTable[valueIndex(i)] = this.mTable[valueIndex(nextBaseIndex)];
                    i = nextBaseIndex;
                }
                nextBaseIndex = nextBaseIndex(nextBaseIndex);
            }
            this.mTable[keyIndex(i)] = null;
            this.mTable[valueIndex(i)] = null;
            this.mSize--;
            this.mModCount++;
            return;
        }
        throw new NoSuchElementException();
    }

    private boolean needToShiftUpOnDelete(int i, int i2) {
        if (i == i2) {
            return false;
        }
        Object obj = this.mTable[keyIndex(i2)];
        if (obj != null) {
            int baseIndex = baseIndex(obj);
            if (i < i2) {
                if (baseIndex <= i || baseIndex > i2) {
                    return true;
                }
                return false;
            } else if (baseIndex > i || baseIndex <= i2) {
                return false;
            } else {
                return true;
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    private int findKeyBaseIndex(Object obj) {
        if (this.mSize == 0) {
            return -1;
        }
        if (obj == null) {
            obj = NULL_KEY;
        }
        int baseIndex = baseIndex(obj);
        Object obj2 = this.mTable[keyIndex(baseIndex)];
        while (obj2 != null) {
            if (obj2 == obj || obj2.equals(obj)) {
                return baseIndex;
            }
            baseIndex = nextBaseIndex(baseIndex);
            obj2 = this.mTable[keyIndex(baseIndex)];
        }
        return -1;
    }

    private class BaseMapIterator implements Map.Entry<K, V> {
        private int mCurrentBaseIndex = -1;
        private int mExpectedModCount;
        private int mNextBaseIndex;

        BaseMapIterator() {
            this.mExpectedModCount = LeanHashMap.this.mModCount;
            this.mNextBaseIndex = LeanHashMap.this.firstIndex();
        }

        public boolean hasNext() {
            return this.mNextBaseIndex >= 0;
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, V> nextEntry() {
            if (this.mExpectedModCount == LeanHashMap.this.mModCount) {
                int i = this.mNextBaseIndex;
                if (i >= 0) {
                    this.mCurrentBaseIndex = i;
                    this.mNextBaseIndex = LeanHashMap.this.nextIndex(i);
                    return this;
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Map.Entry
        @Nullable
        public K getKey() {
            validateIndexPosition();
            return (K) LeanHashMap.this.keyAt(this.mCurrentBaseIndex);
        }

        @Override // java.util.Map.Entry
        @Nullable
        public V getValue() {
            validateIndexPosition();
            return (V) LeanHashMap.this.valueAt(this.mCurrentBaseIndex);
        }

        @Override // java.util.Map.Entry
        @Nullable
        public V setValue(V v) {
            validateIndexPosition();
            int valueIndex = LeanHashMap.valueIndex(this.mCurrentBaseIndex);
            V v2 = (V) LeanHashMap.this.mTable[valueIndex];
            LeanHashMap.this.mTable[valueIndex] = v;
            return v2;
        }

        public void remove() {
            validateIndexPosition();
            if (this.mExpectedModCount == LeanHashMap.this.mModCount) {
                this.mExpectedModCount++;
                LeanHashMap leanHashMap = LeanHashMap.this;
                leanHashMap.remove(leanHashMap.keyAt(this.mCurrentBaseIndex));
                if (LeanHashMap.this.mTable[LeanHashMap.keyIndex(this.mCurrentBaseIndex)] != null) {
                    this.mNextBaseIndex = this.mCurrentBaseIndex;
                }
                this.mCurrentBaseIndex = -1;
                return;
            }
            throw new ConcurrentModificationException();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!LeanHashMap.objectEquals(entry.getKey(), getKey()) || !LeanHashMap.objectEquals(entry.getValue(), getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i;
            Object key = getKey();
            Object value = getValue();
            int i2 = 0;
            if (key == null) {
                i = 0;
            } else {
                i = key.hashCode();
            }
            if (value != null) {
                i2 = value.hashCode();
            }
            return i ^ i2;
        }

        private void validateIndexPosition() {
            if (this.mCurrentBaseIndex < 0) {
                throw new IllegalStateException("Iterator not pointing to any element.");
            }
        }
    }

    private final class EntryIterator extends LeanHashMap<K, V>.BaseMapIterator implements Iterator<Map.Entry<K, V>> {
        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    private final class KeyIterator extends LeanHashMap<K, V>.BaseMapIterator implements Iterator<K> {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        @Nullable
        public K next() {
            return nextEntry().getKey();
        }
    }

    private final class ValueIterator extends LeanHashMap<K, V>.BaseMapIterator implements Iterator<V> {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        @Nullable
        public V next() {
            return nextEntry().getValue();
        }
    }

    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return LeanHashMap.this.containsMapping(entry.getKey(), entry.getValue());
        }

        public int size() {
            return LeanHashMap.this.size();
        }

        public boolean isEmpty() {
            return LeanHashMap.this.isEmpty();
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return LeanHashMap.this.removeMapping(entry.getKey(), entry.getValue());
        }

        public void clear() {
            LeanHashMap.this.clear();
        }
    }

    private final class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public boolean contains(Object obj) {
            return LeanHashMap.this.containsKey(obj);
        }

        public int size() {
            return LeanHashMap.this.size();
        }

        public boolean isEmpty() {
            return LeanHashMap.this.isEmpty();
        }

        public boolean remove(Object obj) {
            int size = size();
            LeanHashMap.this.remove(obj);
            return size() != size;
        }

        public void clear() {
            LeanHashMap.this.clear();
        }
    }

    private final class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public boolean contains(Object obj) {
            return LeanHashMap.this.containsValue(obj);
        }

        public int size() {
            return LeanHashMap.this.size();
        }

        public boolean isEmpty() {
            return LeanHashMap.this.isEmpty();
        }

        public void clear() {
            LeanHashMap.this.clear();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeFloat(LOAD_FACTOR);
        objectOutputStream.writeInt(currentCapacity());
        objectOutputStream.writeInt(this.mSize);
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            objectOutputStream.writeObject(keyAt(firstIndex));
            objectOutputStream.writeObject(valueAt(firstIndex));
            firstIndex = nextIndex(firstIndex);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.facebook.common.collectlite.LeanHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        objectInputStream.readFloat();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            int i = MAXIMUM_CAPACITY;
            if (readInt < 4) {
                i = 4;
            } else if (readInt <= MAXIMUM_CAPACITY) {
                i = roundUpToPowerOfTwo(readInt);
            }
            resizeMappingTable(i);
            int readInt2 = objectInputStream.readInt();
            if (readInt2 >= 0) {
                for (int i2 = 0; i2 < readInt2; i2++) {
                    put(objectInputStream.readObject(), objectInputStream.readObject());
                }
                return;
            }
            throw new InvalidObjectException("Invalid size: " + readInt2);
        }
        throw new IllegalArgumentException("Invalid capacity: " + readInt);
    }
}
