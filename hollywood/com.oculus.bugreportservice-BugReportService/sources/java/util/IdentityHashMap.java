package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.Map;

public class IdentityHashMap extends AbstractMap implements Map, Serializable, Cloneable {
    static final Object NULL_KEY = new Object();
    private static final long serialVersionUID = 8188218128353913216L;
    private transient Set entrySet;
    transient int modCount;
    int size;
    transient Object[] table;

    /* access modifiers changed from: private */
    public static int nextKeyIndex(int i, int i2) {
        int i3 = i + 2;
        if (i3 < i2) {
            return i3;
        }
        return 0;
    }

    private static Object maskNull(Object obj) {
        return obj == null ? NULL_KEY : obj;
    }

    static final Object unmaskNull(Object obj) {
        if (obj == NULL_KEY) {
            return null;
        }
        return obj;
    }

    public IdentityHashMap() {
        init(32);
    }

    private static int capacity(int i) {
        if (i > 178956970) {
            return 536870912;
        }
        if (i <= 2) {
            return 4;
        }
        return Integer.highestOneBit(i + (i << 1));
    }

    private void init(int i) {
        this.table = new Object[(i * 2)];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    /* access modifiers changed from: private */
    public static int hash(Object obj, int i) {
        int identityHashCode = System.identityHashCode(obj);
        return ((identityHashCode << 1) - (identityHashCode << 8)) & (i - 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        Object maskNull = maskNull(obj);
        Object[] objArr = this.table;
        int length = objArr.length;
        int hash = hash(maskNull, length);
        while (true) {
            Object obj2 = objArr[hash];
            if (obj2 == maskNull) {
                return objArr[hash + 1];
            }
            if (obj2 == null) {
                return null;
            }
            hash = nextKeyIndex(hash, length);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Object maskNull = maskNull(obj);
        Object[] objArr = this.table;
        int length = objArr.length;
        int hash = hash(maskNull, length);
        while (true) {
            Object obj2 = objArr[hash];
            if (obj2 == maskNull) {
                return true;
            }
            if (obj2 == null) {
                return false;
            }
            hash = nextKeyIndex(hash, length);
        }
    }

    @Override // java.util.AbstractMap
    public boolean containsValue(Object obj) {
        Object[] objArr = this.table;
        for (int i = 1; i < objArr.length; i += 2) {
            if (objArr[i] == obj && objArr[i - 1] != null) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean containsMapping(Object obj, Object obj2) {
        Object maskNull = maskNull(obj);
        Object[] objArr = this.table;
        int length = objArr.length;
        int hash = hash(maskNull, length);
        while (true) {
            Object obj3 = objArr[hash];
            if (obj3 == maskNull) {
                return objArr[hash + 1] == obj2;
            }
            if (obj3 == null) {
                return false;
            }
            hash = nextKeyIndex(hash, length);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        r3 = r5.size + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (((r3 << 1) + r3) <= r1) goto L_0x002d;
     */
    @Override // java.util.AbstractMap, java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object put(java.lang.Object r6, java.lang.Object r7) {
        /*
            r5 = this;
            java.lang.Object r6 = maskNull(r6)
        L_0x0004:
            java.lang.Object[] r0 = r5.table
            int r1 = r0.length
            int r2 = hash(r6, r1)
        L_0x000b:
            r3 = r0[r2]
            if (r3 == 0) goto L_0x001d
            if (r3 != r6) goto L_0x0018
            int r2 = r2 + 1
            r5 = r0[r2]
            r0[r2] = r7
            return r5
        L_0x0018:
            int r2 = nextKeyIndex(r2, r1)
            goto L_0x000b
        L_0x001d:
            int r3 = r5.size
            int r3 = r3 + 1
            int r4 = r3 << 1
            int r4 = r4 + r3
            if (r4 <= r1) goto L_0x002d
            boolean r1 = r5.resize(r1)
            if (r1 == 0) goto L_0x002d
            goto L_0x0004
        L_0x002d:
            int r1 = r5.modCount
            int r1 = r1 + 1
            r5.modCount = r1
            r0[r2] = r6
            int r2 = r2 + 1
            r0[r2] = r7
            r5.size = r3
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.IdentityHashMap.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    private boolean resize(int i) {
        int i2 = i * 2;
        Object[] objArr = this.table;
        int length = objArr.length;
        if (length == 1073741824) {
            if (this.size != 536870911) {
                return false;
            }
            throw new IllegalStateException("Capacity exhausted.");
        } else if (length >= i2) {
            return false;
        } else {
            Object[] objArr2 = new Object[i2];
            for (int i3 = 0; i3 < length; i3 += 2) {
                Object obj = objArr[i3];
                if (obj != null) {
                    int i4 = i3 + 1;
                    Object obj2 = objArr[i4];
                    objArr[i3] = null;
                    objArr[i4] = null;
                    int hash = hash(obj, i2);
                    while (objArr2[hash] != null) {
                        hash = nextKeyIndex(hash, i2);
                    }
                    objArr2[hash] = obj;
                    objArr2[hash + 1] = obj2;
                }
            }
            this.table = objArr2;
            return true;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        int size2 = map.size();
        if (size2 != 0) {
            if (size2 > this.size) {
                resize(capacity(size2));
            }
            for (Map.Entry entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Object maskNull = maskNull(obj);
        Object[] objArr = this.table;
        int length = objArr.length;
        int hash = hash(maskNull, length);
        while (true) {
            Object obj2 = objArr[hash];
            if (obj2 == maskNull) {
                this.modCount++;
                this.size--;
                int i = hash + 1;
                Object obj3 = objArr[i];
                objArr[i] = null;
                objArr[hash] = null;
                closeDeletion(hash);
                return obj3;
            } else if (obj2 == null) {
                return null;
            } else {
                hash = nextKeyIndex(hash, length);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeMapping(Object obj, Object obj2) {
        Object maskNull = maskNull(obj);
        Object[] objArr = this.table;
        int length = objArr.length;
        int hash = hash(maskNull, length);
        while (true) {
            Object obj3 = objArr[hash];
            if (obj3 == maskNull) {
                int i = hash + 1;
                if (objArr[i] != obj2) {
                    return false;
                }
                this.modCount++;
                this.size--;
                objArr[hash] = null;
                objArr[i] = null;
                closeDeletion(hash);
                return true;
            } else if (obj3 == null) {
                return false;
            } else {
                hash = nextKeyIndex(hash, length);
            }
        }
    }

    private void closeDeletion(int i) {
        Object[] objArr = this.table;
        int length = objArr.length;
        int nextKeyIndex = nextKeyIndex(i, length);
        while (true) {
            Object obj = objArr[nextKeyIndex];
            if (obj != null) {
                int hash = hash(obj, length);
                if ((nextKeyIndex < hash && (hash <= i || i <= nextKeyIndex)) || (hash <= i && i <= nextKeyIndex)) {
                    objArr[i] = obj;
                    int i2 = nextKeyIndex + 1;
                    objArr[i + 1] = objArr[i2];
                    objArr[nextKeyIndex] = null;
                    objArr[i2] = null;
                    i = nextKeyIndex;
                }
                nextKeyIndex = nextKeyIndex(nextKeyIndex, length);
            } else {
                return;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.modCount++;
        Object[] objArr = this.table;
        for (int i = 0; i < objArr.length; i++) {
            objArr[i] = null;
        }
        this.size = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof IdentityHashMap) {
            IdentityHashMap identityHashMap = (IdentityHashMap) obj;
            if (identityHashMap.size() != this.size) {
                return false;
            }
            Object[] objArr = identityHashMap.table;
            for (int i = 0; i < objArr.length; i += 2) {
                Object obj2 = objArr[i];
                if (!(obj2 == null || containsMapping(obj2, objArr[i + 1]))) {
                    return false;
                }
            }
            return true;
        } else if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        } else {
            return false;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        Object[] objArr = this.table;
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2 += 2) {
            Object obj = objArr[i2];
            if (obj != null) {
                i += System.identityHashCode(unmaskNull(obj)) ^ System.identityHashCode(objArr[i2 + 1]);
            }
        }
        return i;
    }

    @Override // java.util.AbstractMap
    public Object clone() {
        try {
            IdentityHashMap identityHashMap = (IdentityHashMap) super.clone();
            identityHashMap.entrySet = null;
            identityHashMap.table = (Object[]) this.table.clone();
            return identityHashMap;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /* access modifiers changed from: private */
    public abstract class IdentityHashMapIterator implements Iterator {
        int expectedModCount;
        int index;
        boolean indexValid;
        int lastReturnedIndex;
        Object[] traversalTable;

        private IdentityHashMapIterator() {
            IdentityHashMap identityHashMap = IdentityHashMap.this;
            this.index = identityHashMap.size != 0 ? 0 : identityHashMap.table.length;
            IdentityHashMap identityHashMap2 = IdentityHashMap.this;
            this.expectedModCount = identityHashMap2.modCount;
            this.lastReturnedIndex = -1;
            this.traversalTable = identityHashMap2.table;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Object[] objArr = this.traversalTable;
            for (int i = this.index; i < objArr.length; i += 2) {
                if (objArr[i] != null) {
                    this.index = i;
                    this.indexValid = true;
                    return true;
                }
            }
            this.index = objArr.length;
            return false;
        }

        /* access modifiers changed from: protected */
        public int nextIndex() {
            if (IdentityHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (this.indexValid || hasNext()) {
                this.indexValid = false;
                int i = this.index;
                this.lastReturnedIndex = i;
                this.index = i + 2;
                return this.lastReturnedIndex;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            int i = this.lastReturnedIndex;
            if (i != -1) {
                IdentityHashMap identityHashMap = IdentityHashMap.this;
                int i2 = identityHashMap.modCount;
                if (i2 == this.expectedModCount) {
                    int i3 = i2 + 1;
                    identityHashMap.modCount = i3;
                    this.expectedModCount = i3;
                    this.lastReturnedIndex = -1;
                    this.index = i;
                    this.indexValid = false;
                    Object[] objArr = this.traversalTable;
                    int length = objArr.length;
                    Object obj = objArr[i];
                    objArr[i] = null;
                    objArr[i + 1] = null;
                    if (objArr != identityHashMap.table) {
                        identityHashMap.remove(obj);
                        this.expectedModCount = IdentityHashMap.this.modCount;
                        return;
                    }
                    identityHashMap.size--;
                    int nextKeyIndex = IdentityHashMap.nextKeyIndex(i, length);
                    int i4 = i;
                    while (true) {
                        Object obj2 = objArr[nextKeyIndex];
                        if (obj2 != null) {
                            int hash = IdentityHashMap.hash(obj2, length);
                            if ((nextKeyIndex < hash && (hash <= i4 || i4 <= nextKeyIndex)) || (hash <= i4 && i4 <= nextKeyIndex)) {
                                if (nextKeyIndex < i && i4 >= i && this.traversalTable == IdentityHashMap.this.table) {
                                    int i5 = length - i;
                                    Object[] objArr2 = new Object[i5];
                                    System.arraycopy(objArr, i, objArr2, 0, i5);
                                    this.traversalTable = objArr2;
                                    this.index = 0;
                                }
                                objArr[i4] = obj2;
                                int i6 = nextKeyIndex + 1;
                                objArr[i4 + 1] = objArr[i6];
                                objArr[nextKeyIndex] = null;
                                objArr[i6] = null;
                                i4 = nextKeyIndex;
                            }
                            nextKeyIndex = IdentityHashMap.nextKeyIndex(nextKeyIndex, length);
                        } else {
                            return;
                        }
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /* access modifiers changed from: private */
    public class KeyIterator extends IdentityHashMapIterator {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Object next() {
            return IdentityHashMap.unmaskNull(this.traversalTable[nextIndex()]);
        }
    }

    /* access modifiers changed from: private */
    public class ValueIterator extends IdentityHashMapIterator {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Object next() {
            return this.traversalTable[nextIndex() + 1];
        }
    }

    /* access modifiers changed from: private */
    public class EntryIterator extends IdentityHashMapIterator {
        private Entry lastReturnedEntry;

        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry next() {
            this.lastReturnedEntry = new Entry(nextIndex());
            return this.lastReturnedEntry;
        }

        @Override // java.util.Iterator, java.util.IdentityHashMap.IdentityHashMapIterator
        public void remove() {
            Entry entry = this.lastReturnedEntry;
            this.lastReturnedIndex = entry == null ? -1 : entry.index;
            super.remove();
            this.lastReturnedEntry.index = this.lastReturnedIndex;
            this.lastReturnedEntry = null;
        }

        /* access modifiers changed from: private */
        public class Entry implements Map.Entry {
            private int index;

            private Entry(int i) {
                this.index = i;
            }

            @Override // java.util.Map.Entry
            public Object getKey() {
                checkIndexForEntryUse();
                return IdentityHashMap.unmaskNull(EntryIterator.this.traversalTable[this.index]);
            }

            @Override // java.util.Map.Entry
            public Object getValue() {
                checkIndexForEntryUse();
                return EntryIterator.this.traversalTable[this.index + 1];
            }

            public boolean equals(Object obj) {
                if (this.index < 0) {
                    return super.equals(obj);
                }
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getKey() == IdentityHashMap.unmaskNull(EntryIterator.this.traversalTable[this.index]) && entry.getValue() == EntryIterator.this.traversalTable[this.index + 1]) {
                    return true;
                }
                return false;
            }

            @Override // java.util.Map.Entry
            public int hashCode() {
                EntryIterator entryIterator = EntryIterator.this;
                if (entryIterator.lastReturnedIndex < 0) {
                    return super.hashCode();
                }
                return System.identityHashCode(EntryIterator.this.traversalTable[this.index + 1]) ^ System.identityHashCode(IdentityHashMap.unmaskNull(entryIterator.traversalTable[this.index]));
            }

            public String toString() {
                if (this.index >= 0) {
                    return IdentityHashMap.unmaskNull(EntryIterator.this.traversalTable[this.index]) + "=" + EntryIterator.this.traversalTable[this.index + 1];
                }
                super.toString();
                throw null;
            }

            private void checkIndexForEntryUse() {
                if (this.index < 0) {
                    throw new IllegalStateException("Entry was removed");
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    private class KeySet extends AbstractSet {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IdentityHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return IdentityHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            IdentityHashMap identityHashMap = IdentityHashMap.this;
            int i = identityHashMap.size;
            identityHashMap.remove(obj);
            return IdentityHashMap.this.size != i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean removeAll(Collection collection) {
            Objects.requireNonNull(collection);
            Iterator it = iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            IdentityHashMap.this.clear();
        }

        @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
        public int hashCode() {
            Iterator it = iterator();
            int i = 0;
            while (it.hasNext()) {
                i += System.identityHashCode(it.next());
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return toArray(new Object[0]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray(Object[] objArr) {
            int i = IdentityHashMap.this.modCount;
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            Object[] objArr2 = IdentityHashMap.this.table;
            int i2 = 0;
            for (int i3 = 0; i3 < objArr2.length; i3 += 2) {
                Object obj = objArr2[i3];
                if (obj != null) {
                    if (i2 < size) {
                        objArr[i2] = IdentityHashMap.unmaskNull(obj);
                        i2++;
                    } else {
                        throw new ConcurrentModificationException();
                    }
                }
            }
            if (i2 < size || i != IdentityHashMap.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (i2 < objArr.length) {
                objArr[i2] = null;
            }
            return objArr;
        }
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

    private class Values extends AbstractCollection {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return IdentityHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return IdentityHashMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            Iterator it = iterator();
            while (it.hasNext()) {
                if (it.next() == obj) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            IdentityHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return toArray(new Object[0]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            int i = IdentityHashMap.this.modCount;
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            Object[] objArr2 = IdentityHashMap.this.table;
            int i2 = 0;
            for (int i3 = 0; i3 < objArr2.length; i3 += 2) {
                if (objArr2[i3] != null) {
                    if (i2 < size) {
                        objArr[i2] = objArr2[i3 + 1];
                        i2++;
                    } else {
                        throw new ConcurrentModificationException();
                    }
                }
            }
            if (i2 < size || i != IdentityHashMap.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (i2 < objArr.length) {
                objArr[i2] = null;
            }
            return objArr;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        Set set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    /* access modifiers changed from: private */
    public class EntrySet extends AbstractSet {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return IdentityHashMap.this.containsMapping(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return IdentityHashMap.this.removeMapping(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IdentityHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            IdentityHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean removeAll(Collection collection) {
            Objects.requireNonNull(collection);
            Iterator it = iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return toArray(new Object[0]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray(Object[] objArr) {
            int i = IdentityHashMap.this.modCount;
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            Object[] objArr2 = IdentityHashMap.this.table;
            int i2 = 0;
            for (int i3 = 0; i3 < objArr2.length; i3 += 2) {
                Object obj = objArr2[i3];
                if (obj != null) {
                    if (i2 < size) {
                        objArr[i2] = new AbstractMap.SimpleEntry(IdentityHashMap.unmaskNull(obj), objArr2[i3 + 1]);
                        i2++;
                    } else {
                        throw new ConcurrentModificationException();
                    }
                }
            }
            if (i2 < size || i != IdentityHashMap.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (i2 < objArr.length) {
                objArr[i2] = null;
            }
            return objArr;
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
