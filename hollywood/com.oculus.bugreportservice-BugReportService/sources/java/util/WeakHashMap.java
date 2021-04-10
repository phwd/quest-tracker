package java.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.Map;

public class WeakHashMap extends AbstractMap implements Map {
    private static final Object NULL_KEY = new Object();
    private transient Set entrySet;
    private final float loadFactor;
    int modCount;
    private final ReferenceQueue queue;
    private int size;
    Entry[] table;
    private int threshold;

    private static int indexFor(int i, int i2) {
        return i & (i2 - 1);
    }

    private Entry[] newTable(int i) {
        return new Entry[i];
    }

    public WeakHashMap(int i, float f) {
        this.queue = new ReferenceQueue();
        if (i >= 0) {
            i = i > 1073741824 ? 1073741824 : i;
            if (f <= 0.0f || Float.isNaN(f)) {
                throw new IllegalArgumentException("Illegal Load factor: " + f);
            }
            int i2 = 1;
            while (i2 < i) {
                i2 <<= 1;
            }
            this.table = newTable(i2);
            this.loadFactor = f;
            this.threshold = (int) (((float) i2) * f);
            return;
        }
        throw new IllegalArgumentException("Illegal Initial Capacity: " + i);
    }

    public WeakHashMap() {
        this(16, 0.75f);
    }

    private static Object maskNull(Object obj) {
        return obj == null ? NULL_KEY : obj;
    }

    static Object unmaskNull(Object obj) {
        if (obj == NULL_KEY) {
            return null;
        }
        return obj;
    }

    private static boolean eq(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    /* access modifiers changed from: package-private */
    public final int hash(Object obj) {
        int hashCode = obj.hashCode();
        int i = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return (i >>> 4) ^ ((i >>> 7) ^ i);
    }

    private void expungeStaleEntries() {
        while (true) {
            Reference poll = this.queue.poll();
            if (poll != null) {
                synchronized (this.queue) {
                    Entry entry = (Entry) poll;
                    int indexFor = indexFor(entry.hash, this.table.length);
                    Entry entry2 = this.table[indexFor];
                    Entry entry3 = entry2;
                    while (true) {
                        if (entry2 == null) {
                            break;
                        }
                        Entry entry4 = entry2.next;
                        if (entry2 == entry) {
                            if (entry3 == entry) {
                                this.table[indexFor] = entry4;
                            } else {
                                entry3.next = entry4;
                            }
                            entry.value = null;
                            this.size--;
                        } else {
                            entry3 = entry2;
                            entry2 = entry4;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    private Entry[] getTable() {
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
    public Object get(Object obj) {
        Object maskNull = maskNull(obj);
        int hash = hash(maskNull);
        Entry[] table2 = getTable();
        for (Entry entry = table2[indexFor(hash, table2.length)]; entry != null; entry = entry.next) {
            if (entry.hash == hash && eq(maskNull, entry.get())) {
                return entry.value;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return getEntry(obj) != null;
    }

    /* access modifiers changed from: package-private */
    public Entry getEntry(Object obj) {
        Object maskNull = maskNull(obj);
        int hash = hash(maskNull);
        Entry[] table2 = getTable();
        Entry entry = table2[indexFor(hash, table2.length)];
        while (entry != null && (entry.hash != hash || !eq(maskNull, entry.get()))) {
            entry = entry.next;
        }
        return entry;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        Object maskNull = maskNull(obj);
        int hash = hash(maskNull);
        Entry[] table2 = getTable();
        int indexFor = indexFor(hash, table2.length);
        for (Entry entry = table2[indexFor]; entry != null; entry = entry.next) {
            if (hash == entry.hash && eq(maskNull, entry.get())) {
                Object obj3 = entry.value;
                if (obj2 != obj3) {
                    entry.value = obj2;
                }
                return obj3;
            }
        }
        this.modCount++;
        table2[indexFor] = new Entry(maskNull, obj2, this.queue, hash, table2[indexFor]);
        int i = this.size + 1;
        this.size = i;
        if (i < this.threshold) {
            return null;
        }
        resize(table2.length * 2);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void resize(int i) {
        Entry[] table2 = getTable();
        if (table2.length == 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = newTable(i);
        transfer(table2, newTable);
        this.table = newTable;
        if (this.size >= this.threshold / 2) {
            this.threshold = (int) (((float) i) * this.loadFactor);
            return;
        }
        expungeStaleEntries();
        transfer(newTable, table2);
        this.table = table2;
    }

    private void transfer(Entry[] entryArr, Entry[] entryArr2) {
        for (int i = 0; i < entryArr.length; i++) {
            Entry entry = entryArr[i];
            entryArr[i] = null;
            while (entry != null) {
                Entry entry2 = entry.next;
                if (entry.get() == null) {
                    entry.next = null;
                    entry.value = null;
                    this.size--;
                } else {
                    int indexFor = indexFor(entry.hash, entryArr2.length);
                    entry.next = entryArr2[indexFor];
                    entryArr2[indexFor] = entry;
                }
                entry = entry2;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        int size2 = map.size();
        if (size2 != 0) {
            if (size2 > this.threshold) {
                int i = (int) ((((float) size2) / this.loadFactor) + 1.0f);
                if (i > 1073741824) {
                    i = 1073741824;
                }
                int length = this.table.length;
                while (length < i) {
                    length <<= 1;
                }
                if (length > this.table.length) {
                    resize(length);
                }
            }
            for (Map.Entry entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Object maskNull = maskNull(obj);
        int hash = hash(maskNull);
        Entry[] table2 = getTable();
        int indexFor = indexFor(hash, table2.length);
        Entry entry = table2[indexFor];
        Entry entry2 = entry;
        while (entry != null) {
            Entry entry3 = entry.next;
            if (hash != entry.hash || !eq(maskNull, entry.get())) {
                entry2 = entry;
                entry = entry3;
            } else {
                this.modCount++;
                this.size--;
                if (entry2 == entry) {
                    table2[indexFor] = entry3;
                } else {
                    entry2.next = entry3;
                }
                return entry.value;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean removeMapping(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Entry[] table2 = getTable();
        Map.Entry entry = (Map.Entry) obj;
        int hash = hash(maskNull(entry.getKey()));
        int indexFor = indexFor(hash, table2.length);
        Entry entry2 = table2[indexFor];
        Entry entry3 = entry2;
        while (entry2 != null) {
            Entry entry4 = entry2.next;
            if (hash != entry2.hash || !entry2.equals(entry)) {
                entry3 = entry2;
                entry2 = entry4;
            } else {
                this.modCount++;
                this.size--;
                if (entry3 == entry2) {
                    table2[indexFor] = entry4;
                } else {
                    entry3.next = entry4;
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

    @Override // java.util.AbstractMap
    public boolean containsValue(Object obj) {
        if (obj == null) {
            return containsNullValue();
        }
        Entry[] table2 = getTable();
        int length = table2.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            for (Entry entry = table2[i]; entry != null; entry = entry.next) {
                if (obj.equals(entry.value)) {
                    return true;
                }
            }
            length = i;
        }
    }

    private boolean containsNullValue() {
        Entry[] table2 = getTable();
        int length = table2.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            for (Entry entry = table2[i]; entry != null; entry = entry.next) {
                if (entry.value == null) {
                    return true;
                }
            }
            length = i;
        }
    }

    /* access modifiers changed from: private */
    public static class Entry extends WeakReference implements Map.Entry {
        final int hash;
        Entry next;
        Object value;

        Entry(Object obj, Object obj2, ReferenceQueue referenceQueue, int i, Entry entry) {
            super(obj, referenceQueue);
            this.value = obj2;
            this.hash = i;
            this.next = entry;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return WeakHashMap.unmaskNull(get());
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.value;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = getKey();
            Object key2 = entry.getKey();
            if (key == key2 || (key != null && key.equals(key2))) {
                Object value2 = getValue();
                Object value3 = entry.getValue();
                if (value2 == value3) {
                    return true;
                }
                if (value2 == null || !value2.equals(value3)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object key = getKey();
            Object value2 = getValue();
            return Objects.hashCode(value2) ^ Objects.hashCode(key);
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* access modifiers changed from: private */
    public abstract class HashIterator implements Iterator {
        private Object currentKey;
        private Entry entry;
        private int expectedModCount = WeakHashMap.this.modCount;
        private int index;
        private Entry lastReturned;
        private Object nextKey;

        HashIterator() {
            this.index = WeakHashMap.this.isEmpty() ? 0 : WeakHashMap.this.table.length;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Entry[] entryArr = WeakHashMap.this.table;
            while (this.nextKey == null) {
                Entry entry2 = this.entry;
                int i = this.index;
                while (entry2 == null && i > 0) {
                    i--;
                    entry2 = entryArr[i];
                }
                this.entry = entry2;
                this.index = i;
                if (entry2 == null) {
                    this.currentKey = null;
                    return false;
                }
                this.nextKey = entry2.get();
                if (this.nextKey == null) {
                    this.entry = this.entry.next;
                }
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public Entry nextEntry() {
            if (WeakHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (this.nextKey != null || hasNext()) {
                Entry entry2 = this.entry;
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
            if (this.lastReturned != null) {
                WeakHashMap weakHashMap = WeakHashMap.this;
                if (weakHashMap.modCount == this.expectedModCount) {
                    weakHashMap.remove(this.currentKey);
                    this.expectedModCount = WeakHashMap.this.modCount;
                    this.lastReturned = null;
                    this.currentKey = null;
                    return;
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException();
        }
    }

    private class ValueIterator extends HashIterator {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Object next() {
            return nextEntry().value;
        }
    }

    private class KeyIterator extends HashIterator {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Object next() {
            return nextEntry().getKey();
        }
    }

    /* access modifiers changed from: private */
    public class EntryIterator extends HashIterator {
        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry next() {
            return nextEntry();
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
            return WeakHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return WeakHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!WeakHashMap.this.containsKey(obj)) {
                return false;
            }
            WeakHashMap.this.remove(obj);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            WeakHashMap.this.clear();
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
            return WeakHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return WeakHashMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            WeakHashMap.this.clear();
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

    private class EntrySet extends AbstractSet {
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
            Entry entry2 = WeakHashMap.this.getEntry(entry.getKey());
            if (entry2 == null || !entry2.equals(entry)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return WeakHashMap.this.removeMapping(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return WeakHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            WeakHashMap.this.clear();
        }

        private List deepCopy() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(new AbstractMap.SimpleEntry((Map.Entry) it.next()));
            }
            return arrayList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return deepCopy().toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray(Object[] objArr) {
            return deepCopy().toArray(objArr);
        }
    }
}
