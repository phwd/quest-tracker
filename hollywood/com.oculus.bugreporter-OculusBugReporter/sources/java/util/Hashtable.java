package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Hashtable<K, V> extends Dictionary<K, V> implements Map<K, V>, Cloneable, Serializable {
    private static final int ENTRIES = 2;
    private static final int KEYS = 0;
    private static final int MAX_ARRAY_SIZE = 2147483639;
    private static final int VALUES = 1;
    private static final long serialVersionUID = 1421746759512286392L;
    private transient int count;
    private volatile transient Set<Map.Entry<K, V>> entrySet;
    private volatile transient Set<K> keySet;
    private float loadFactor;
    private transient int modCount;
    private transient HashtableEntry<?, ?>[] table;
    private int threshold;
    private volatile transient Collection<V> values;

    static /* synthetic */ int access$210(Hashtable x0) {
        int i = x0.count;
        x0.count = i - 1;
        return i;
    }

    static /* synthetic */ int access$508(Hashtable x0) {
        int i = x0.modCount;
        x0.modCount = i + 1;
        return i;
    }

    public Hashtable(int initialCapacity, float loadFactor2) {
        this.modCount = 0;
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        } else if (loadFactor2 <= 0.0f || Float.isNaN(loadFactor2)) {
            throw new IllegalArgumentException("Illegal Load: " + loadFactor2);
        } else {
            initialCapacity = initialCapacity == 0 ? 1 : initialCapacity;
            this.loadFactor = loadFactor2;
            this.table = new HashtableEntry[initialCapacity];
            this.threshold = Math.min(initialCapacity, 2147483640);
        }
    }

    public Hashtable(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public Hashtable() {
        this(11, 0.75f);
    }

    public Hashtable(Map<? extends K, ? extends V> t) {
        this(Math.max(t.size() * 2, 11), 0.75f);
        putAll(t);
    }

    @Override // java.util.Map, java.util.Dictionary
    public synchronized int size() {
        return this.count;
    }

    @Override // java.util.Map, java.util.Dictionary
    public synchronized boolean isEmpty() {
        return this.count == 0;
    }

    /* JADX DEBUG: Type inference failed for r0v2. Raw type applied. Possible types: java.util.Enumeration<T>, java.util.Enumeration<K> */
    @Override // java.util.Dictionary
    public synchronized Enumeration<K> keys() {
        return (Enumeration<T>) getEnumeration(0);
    }

    /* JADX DEBUG: Type inference failed for r0v2. Raw type applied. Possible types: java.util.Enumeration<T>, java.util.Enumeration<V> */
    @Override // java.util.Dictionary
    public synchronized Enumeration<V> elements() {
        return (Enumeration<T>) getEnumeration(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r3v3. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    public synchronized boolean contains(Object value) {
        if (value != null) {
            HashtableEntry[] hashtableEntryArr = this.table;
            int i = hashtableEntryArr.length;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    return false;
                }
                for (HashtableEntry hashtableEntry = hashtableEntryArr[i2]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
                    if (hashtableEntry.value.equals(value)) {
                        return true;
                    }
                }
                i = i2;
            }
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return contains(value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v1. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized boolean containsKey(Object key) {
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        for (HashtableEntry hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hash) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
            if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v1. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map, java.util.Dictionary
    public synchronized V get(Object key) {
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        for (HashtableEntry hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hash) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
            if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                return hashtableEntry.value;
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.util.Hashtable$HashtableEntry<?, ?>[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void rehash() {
        int oldCapacity = this.table.length;
        HashtableEntry[] hashtableEntryArr = this.table;
        int newCapacity = (oldCapacity << 1) + 1;
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            if (oldCapacity != MAX_ARRAY_SIZE) {
                newCapacity = MAX_ARRAY_SIZE;
            } else {
                return;
            }
        }
        HashtableEntry<?, ?>[] newMap = new HashtableEntry[newCapacity];
        this.modCount++;
        this.threshold = (int) Math.min(((float) newCapacity) * this.loadFactor, 2.14748365E9f);
        this.table = newMap;
        int i = oldCapacity;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                HashtableEntry hashtableEntry = hashtableEntryArr[i2];
                while (hashtableEntry != null) {
                    hashtableEntry = hashtableEntry.next;
                    int index = (hashtableEntry.hash & Integer.MAX_VALUE) % newCapacity;
                    hashtableEntry.next = (HashtableEntry<K, V>) newMap[index];
                    newMap[index] = hashtableEntry;
                }
                i = i2;
            } else {
                return;
            }
        }
    }

    private void addEntry(int hash, K key, V value, int index) {
        this.modCount++;
        HashtableEntry<?, ?>[] tab = this.table;
        if (this.count >= this.threshold) {
            rehash();
            tab = this.table;
            hash = key.hashCode();
            index = (Integer.MAX_VALUE & hash) % tab.length;
        }
        tab[index] = new HashtableEntry<>(hash, key, value, tab[index]);
        this.count++;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v2. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map, java.util.Dictionary
    public synchronized V put(K key, V value) {
        if (value != null) {
            HashtableEntry[] hashtableEntryArr = this.table;
            int hash = key.hashCode();
            int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
            for (HashtableEntry hashtableEntry = hashtableEntryArr[index]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
                if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                    V old = hashtableEntry.value;
                    hashtableEntry.value = value;
                    return old;
                }
            }
            addEntry(hash, key, value, index);
            return null;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.Dictionary
    public synchronized V remove(Object key) {
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
        HashtableEntry hashtableEntry = null;
        for (HashtableEntry<K, V> e = hashtableEntryArr[index]; e != null; e = e.next) {
            if (e.hash != hash || !e.key.equals(key)) {
                hashtableEntry = e;
            } else {
                this.modCount++;
                if (hashtableEntry != null) {
                    hashtableEntry.next = e.next;
                } else {
                    hashtableEntryArr[index] = e.next;
                }
                this.count--;
                V oldValue = e.value;
                e.value = null;
                return oldValue;
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.Hashtable<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public synchronized void putAll(Map<? extends K, ? extends V> t) {
        for (Map.Entry<? extends K, ? extends V> e : t.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override // java.util.Map
    public synchronized void clear() {
        HashtableEntry<?, ?>[] tab = this.table;
        this.modCount++;
        int index = tab.length;
        while (true) {
            index--;
            if (index >= 0) {
                tab[index] = null;
            } else {
                this.count = 0;
            }
        }
    }

    public synchronized Object clone() {
        Hashtable<?, ?> t;
        try {
            t = (Hashtable) super.clone();
            t.table = new HashtableEntry[this.table.length];
            int i = this.table.length;
            while (true) {
                int i2 = i - 1;
                HashtableEntry<?, ?> hashtableEntry = null;
                if (i > 0) {
                    HashtableEntry<?, ?>[] hashtableEntryArr = t.table;
                    if (this.table[i2] != null) {
                        hashtableEntry = (HashtableEntry) this.table[i2].clone();
                    }
                    hashtableEntryArr[i2] = hashtableEntry;
                    i = i2;
                } else {
                    t.keySet = null;
                    t.entrySet = null;
                    t.values = null;
                    t.modCount = 0;
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
        return t;
    }

    public synchronized String toString() {
        int max = size() - 1;
        if (max == -1) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<K, V>> it = entrySet().iterator();
        sb.append('{');
        int i = 0;
        while (true) {
            Map.Entry<K, V> e = it.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key == this ? "(this Map)" : key.toString());
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value.toString());
            if (i == max) {
                sb.append('}');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    private <T> Enumeration<T> getEnumeration(int type) {
        if (this.count == 0) {
            return Collections.emptyEnumeration();
        }
        return new Enumerator(type, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private <T> Iterator<T> getIterator(int type) {
        if (this.count == 0) {
            return Collections.emptyIterator();
        }
        return new Enumerator(type, true);
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = Collections.synchronizedSet(new KeySet(), this);
        }
        return this.keySet;
    }

    /* access modifiers changed from: private */
    public class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return Hashtable.this.getIterator(0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Hashtable.this.count;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return Hashtable.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return Hashtable.this.remove(o) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Hashtable.this.clear();
        }
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = Collections.synchronizedSet(new EntrySet(), this);
        }
        return this.entrySet;
    }

    /* access modifiers changed from: private */
    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            return add((Map.Entry) ((Map.Entry) obj));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return Hashtable.this.getIterator(2);
        }

        public boolean add(Map.Entry<K, V> o) {
            return super.add((Object) o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            Object key = entry.getKey();
            HashtableEntry<K, V>[] hashtableEntryArr = Hashtable.this.table;
            int hash = key.hashCode();
            for (HashtableEntry<K, V> hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hash) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
                if (hashtableEntry.hash == hash && hashtableEntry.equals(entry)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            Object key = entry.getKey();
            HashtableEntry<K, V>[] hashtableEntryArr = Hashtable.this.table;
            int hash = key.hashCode();
            int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
            HashtableEntry<K, V> prev = null;
            for (HashtableEntry<K, V> e = hashtableEntryArr[index]; e != null; e = e.next) {
                if (e.hash != hash || !e.equals(entry)) {
                    prev = e;
                } else {
                    Hashtable.access$508(Hashtable.this);
                    if (prev != null) {
                        prev.next = e.next;
                    } else {
                        hashtableEntryArr[index] = e.next;
                    }
                    Hashtable.access$210(Hashtable.this);
                    e.value = null;
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Hashtable.this.count;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Hashtable.this.clear();
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        if (this.values == null) {
            this.values = Collections.synchronizedCollection(new ValueCollection(), this);
        }
        return this.values;
    }

    private class ValueCollection extends AbstractCollection<V> {
        private ValueCollection() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return Hashtable.this.getIterator(1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return Hashtable.this.count;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return Hashtable.this.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Hashtable.this.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005c, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x005a A[ExcHandler: NullPointerException (e java.lang.NullPointerException), Splitter:B:15:0x001c] */
    @Override // java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = 1
            if (r9 != r8) goto L_0x0006
            monitor-exit(r8)
            return r0
        L_0x0006:
            boolean r1 = r9 instanceof java.util.Map     // Catch:{ all -> 0x0060 }
            r2 = 0
            if (r1 != 0) goto L_0x000d
            monitor-exit(r8)
            return r2
        L_0x000d:
            r1 = r9
            java.util.Map r1 = (java.util.Map) r1
            int r3 = r1.size()
            int r4 = r8.size()
            if (r3 == r4) goto L_0x001c
            monitor-exit(r8)
            return r2
        L_0x001c:
            java.util.Set r3 = r8.entrySet()     // Catch:{ ClassCastException -> 0x005d, NullPointerException -> 0x005a }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
        L_0x0024:
            boolean r4 = r3.hasNext()     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
            if (r4 == 0) goto L_0x0055
            java.lang.Object r4 = r3.next()     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
            java.lang.Object r5 = r4.getKey()     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
            java.lang.Object r6 = r4.getValue()     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
            if (r6 != 0) goto L_0x0048
            java.lang.Object r7 = r1.get(r5)     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
            if (r7 != 0) goto L_0x0046
            boolean r7 = r1.containsKey(r5)     // Catch:{ ClassCastException -> 0x0058, NullPointerException -> 0x005a }
            if (r7 != 0) goto L_0x0054
        L_0x0046:
            monitor-exit(r8)
            return r2
        L_0x0048:
            java.lang.Object r7 = r1.get(r5)
            boolean r7 = r6.equals(r7)
            if (r7 != 0) goto L_0x0054
            monitor-exit(r8)
            return r2
        L_0x0054:
            goto L_0x0024
        L_0x0055:
            monitor-exit(r8)
            return r0
        L_0x0058:
            r0 = move-exception
            goto L_0x005e
        L_0x005a:
            r0 = move-exception
            monitor-exit(r8)
            return r2
        L_0x005d:
            r0 = move-exception
        L_0x005e:
            monitor-exit(r8)
            return r2
        L_0x0060:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Hashtable.equals(java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r5v1. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized int hashCode() {
        int h = 0;
        if (this.count != 0) {
            if (this.loadFactor >= 0.0f) {
                this.loadFactor = -this.loadFactor;
                HashtableEntry[] hashtableEntryArr = this.table;
                for (HashtableEntry hashtableEntry : hashtableEntryArr) {
                    for (; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
                        h += hashtableEntry.hashCode();
                    }
                }
                this.loadFactor = -this.loadFactor;
                return h;
            }
        }
        return 0;
    }

    @Override // java.util.Map
    public synchronized V getOrDefault(Object key, V defaultValue) {
        V result;
        result = get(key);
        return result == null ? defaultValue : result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r5v1. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        int expectedModCount = this.modCount;
        HashtableEntry[] hashtableEntryArr = this.table;
        for (HashtableEntry hashtableEntry : hashtableEntryArr) {
            while (hashtableEntry != null) {
                action.accept(hashtableEntry.key, hashtableEntry.value);
                hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next;
                if (expectedModCount != this.modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r5v3. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        int expectedModCount = this.modCount;
        HashtableEntry[] hashtableEntryArr = this.table;
        for (HashtableEntry hashtableEntry : hashtableEntryArr) {
            while (hashtableEntry != null) {
                hashtableEntry.value = (V) Objects.requireNonNull(function.apply(hashtableEntry.key, hashtableEntry.value));
                hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next;
                if (expectedModCount != this.modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v2. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized V putIfAbsent(K key, V value) {
        Objects.requireNonNull(value);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
        for (HashtableEntry hashtableEntry = hashtableEntryArr[index]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
            if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                V old = hashtableEntry.value;
                if (old == null) {
                    hashtableEntry.value = value;
                }
                return old;
            }
        }
        addEntry(hash, key, value, index);
        return null;
    }

    @Override // java.util.Map
    public synchronized boolean remove(Object key, Object value) {
        Objects.requireNonNull(value);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
        HashtableEntry hashtableEntry = null;
        for (HashtableEntry<K, V> e = hashtableEntryArr[index]; e != null; e = e.next) {
            if (e.hash != hash || !e.key.equals(key) || !e.value.equals(value)) {
                hashtableEntry = e;
            } else {
                this.modCount++;
                if (hashtableEntry != null) {
                    hashtableEntry.next = e.next;
                } else {
                    hashtableEntryArr[index] = e.next;
                }
                this.count--;
                e.value = null;
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v1. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized boolean replace(K key, V oldValue, V newValue) {
        Objects.requireNonNull(oldValue);
        Objects.requireNonNull(newValue);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        for (HashtableEntry hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hash) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
            if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                if (!hashtableEntry.value.equals(oldValue)) {
                    return false;
                } else {
                    hashtableEntry.value = newValue;
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v2. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized V replace(K key, V value) {
        Objects.requireNonNull(value);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        for (HashtableEntry hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hash) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
            if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                V oldValue = hashtableEntry.value;
                hashtableEntry.value = value;
                return oldValue;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v2. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    @Override // java.util.Map
    public synchronized V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
        for (HashtableEntry hashtableEntry = hashtableEntryArr[index]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
            if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                return hashtableEntry.value;
            }
        }
        V newValue = (V) mappingFunction.apply(key);
        if (newValue != null) {
            addEntry(hash, key, newValue, index);
        }
        return newValue;
    }

    @Override // java.util.Map
    public synchronized V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
        HashtableEntry hashtableEntry = null;
        for (HashtableEntry<K, V> e = hashtableEntryArr[index]; e != null; e = e.next) {
            if (e.hash != hash || !e.key.equals(key)) {
                hashtableEntry = e;
            } else {
                V newValue = (V) remappingFunction.apply(key, e.value);
                if (newValue == null) {
                    this.modCount++;
                    if (hashtableEntry != null) {
                        hashtableEntry.next = e.next;
                    } else {
                        hashtableEntryArr[index] = e.next;
                    }
                    this.count--;
                } else {
                    e.value = newValue;
                }
                return newValue;
            }
        }
        return null;
    }

    @Override // java.util.Map
    public synchronized V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
        HashtableEntry hashtableEntry = null;
        for (HashtableEntry<K, V> e = hashtableEntryArr[index]; e != null; e = e.next) {
            if (e.hash != hash || !Objects.equals(e.key, key)) {
                hashtableEntry = e;
            } else {
                V newValue = (V) remappingFunction.apply(key, e.value);
                if (newValue == null) {
                    this.modCount++;
                    if (hashtableEntry != null) {
                        hashtableEntry.next = e.next;
                    } else {
                        hashtableEntryArr[index] = e.next;
                    }
                    this.count--;
                } else {
                    e.value = newValue;
                }
                return newValue;
            }
        }
        V newValue2 = (V) remappingFunction.apply(key, null);
        if (newValue2 != null) {
            addEntry(hash, key, newValue2, index);
        }
        return newValue2;
    }

    @Override // java.util.Map
    public synchronized V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hash = key.hashCode();
        int index = (Integer.MAX_VALUE & hash) % hashtableEntryArr.length;
        HashtableEntry hashtableEntry = null;
        for (HashtableEntry<K, V> e = hashtableEntryArr[index]; e != null; e = e.next) {
            if (e.hash != hash || !e.key.equals(key)) {
                hashtableEntry = e;
            } else {
                V newValue = (V) remappingFunction.apply(e.value, value);
                if (newValue == null) {
                    this.modCount++;
                    if (hashtableEntry != null) {
                        hashtableEntry.next = e.next;
                    } else {
                        hashtableEntryArr[index] = e.next;
                    }
                    this.count--;
                } else {
                    e.value = newValue;
                }
                return newValue;
            }
        }
        if (value != null) {
            addEntry(hash, key, value, index);
        }
        return value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r4v1. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V> */
    private void writeObject(ObjectOutputStream s) throws IOException {
        HashtableEntry<Object, Object> entryStack = null;
        synchronized (this) {
            s.defaultWriteObject();
            s.writeInt(this.table.length);
            s.writeInt(this.count);
            for (int index = 0; index < this.table.length; index++) {
                for (HashtableEntry hashtableEntry = this.table[index]; hashtableEntry != null; hashtableEntry = (HashtableEntry<K, V>) hashtableEntry.next) {
                    entryStack = new HashtableEntry<>(0, hashtableEntry.key, hashtableEntry.value, entryStack);
                }
            }
        }
        while (entryStack != null) {
            s.writeObject(entryStack.key);
            s.writeObject(entryStack.value);
            entryStack = entryStack.next;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.util.Hashtable<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        float f = this.loadFactor;
        if (f <= 0.0f || Float.isNaN(f)) {
            throw new StreamCorruptedException("Illegal Load: " + this.loadFactor);
        }
        int origlength = s.readInt();
        int elements = s.readInt();
        if (elements >= 0) {
            int origlength2 = Math.max(origlength, ((int) (((float) elements) / this.loadFactor)) + 1);
            int length = ((int) (((float) ((elements / 20) + elements)) / this.loadFactor)) + 3;
            if (length > elements && (length & 1) == 0) {
                length--;
            }
            int length2 = Math.min(length, origlength2);
            this.table = new HashtableEntry[length2];
            this.threshold = (int) Math.min(((float) length2) * this.loadFactor, 2.14748365E9f);
            this.count = 0;
            while (elements > 0) {
                reconstitutionPut(this.table, s.readObject(), s.readObject());
                elements--;
            }
            return;
        }
        throw new StreamCorruptedException("Illegal # of Elements: " + elements);
    }

    /* JADX INFO: Multiple debug info for r2v3 java.util.Hashtable$HashtableEntry<?, ?>: [D('e' java.util.Hashtable$HashtableEntry<?, ?>), D('e' java.util.Hashtable$HashtableEntry<K, V>)] */
    private void reconstitutionPut(HashtableEntry<?, ?>[] tab, K key, V value) throws StreamCorruptedException {
        if (value != null) {
            int hash = key.hashCode();
            int index = (Integer.MAX_VALUE & hash) % tab.length;
            for (HashtableEntry<K, V> hashtableEntry = (HashtableEntry<K, V>) tab[index]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
                if (hashtableEntry.hash == hash && hashtableEntry.key.equals(key)) {
                    throw new StreamCorruptedException();
                }
            }
            tab[index] = new HashtableEntry<>(hash, key, value, tab[index]);
            this.count++;
            return;
        }
        throw new StreamCorruptedException();
    }

    /* access modifiers changed from: private */
    public static class HashtableEntry<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        HashtableEntry<K, V> next;
        V value;

        protected HashtableEntry(int hash2, K key2, V value2, HashtableEntry<K, V> next2) {
            this.hash = hash2;
            this.key = key2;
            this.value = value2;
            this.next = next2;
        }

        /* access modifiers changed from: protected */
        public Object clone() {
            int i = this.hash;
            K k = this.key;
            V v = this.value;
            HashtableEntry<K, V> hashtableEntry = this.next;
            return new HashtableEntry(i, k, v, hashtableEntry == null ? null : (HashtableEntry) hashtableEntry.clone());
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V value2) {
            if (value2 != null) {
                V oldValue = this.value;
                this.value = value2;
                return oldValue;
            }
            throw new NullPointerException();
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0033 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        @Override // java.util.Map.Entry
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r5) {
            /*
                r4 = this;
                boolean r0 = r5 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                r0 = r5
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                K r2 = r4.key
                if (r2 != 0) goto L_0x0014
                java.lang.Object r2 = r0.getKey()
                if (r2 != 0) goto L_0x0035
                goto L_0x001e
            L_0x0014:
                java.lang.Object r3 = r0.getKey()
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0035
            L_0x001e:
                V r2 = r4.value
                if (r2 != 0) goto L_0x0029
                java.lang.Object r2 = r0.getValue()
                if (r2 != 0) goto L_0x0035
                goto L_0x0033
            L_0x0029:
                java.lang.Object r3 = r0.getValue()
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0035
            L_0x0033:
                r1 = 1
                goto L_0x0036
            L_0x0035:
            L_0x0036:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Hashtable.HashtableEntry.equals(java.lang.Object):boolean");
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.hash ^ Objects.hashCode(this.value);
        }

        public String toString() {
            return this.key.toString() + "=" + this.value.toString();
        }
    }

    /* access modifiers changed from: private */
    public class Enumerator<T> implements Enumeration<T>, Iterator<T> {
        HashtableEntry<?, ?> entry;
        protected int expectedModCount = Hashtable.this.modCount;
        int index = this.table.length;
        boolean iterator;
        HashtableEntry<?, ?> lastReturned;
        HashtableEntry<?, ?>[] table = Hashtable.this.table;
        int type;

        Enumerator(int type2, boolean iterator2) {
            this.type = type2;
            this.iterator = iterator2;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            HashtableEntry<?, ?> e = this.entry;
            int i = this.index;
            HashtableEntry<?, ?>[] t = this.table;
            while (e == null && i > 0) {
                i--;
                e = t[i];
            }
            this.entry = e;
            this.index = i;
            return e != null;
        }

        /* JADX DEBUG: Type inference failed for r4v1. Raw type applied. Possible types: java.util.Hashtable$HashtableEntry<K, V>, java.util.Hashtable$HashtableEntry<?, ?> */
        /* JADX WARN: Type inference failed for: r4v4, types: [T, V] */
        /* JADX WARN: Type inference failed for: r4v5, types: [T, K] */
        @Override // java.util.Enumeration
        public T nextElement() {
            HashtableEntry<?, ?> et = this.entry;
            int i = this.index;
            HashtableEntry<?, ?>[] t = this.table;
            while (et == null && i > 0) {
                i--;
                et = t[i];
            }
            this.entry = et;
            this.index = i;
            if (et != null) {
                T t2 = (T) this.entry;
                this.lastReturned = t2;
                this.entry = (HashtableEntry<K, V>) t2.next;
                int i2 = this.type;
                if (i2 == 0) {
                    return t2.key;
                }
                return i2 == 1 ? t2.value : t2;
            }
            throw new NoSuchElementException("Hashtable Enumerator");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return hasMoreElements();
        }

        @Override // java.util.Iterator
        public T next() {
            if (Hashtable.this.modCount == this.expectedModCount) {
                return nextElement();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (!this.iterator) {
                throw new UnsupportedOperationException();
            } else if (this.lastReturned == null) {
                throw new IllegalStateException("Hashtable Enumerator");
            } else if (Hashtable.this.modCount == this.expectedModCount) {
                synchronized (Hashtable.this) {
                    HashtableEntry<K, V>[] hashtableEntryArr = Hashtable.this.table;
                    int index2 = (this.lastReturned.hash & Integer.MAX_VALUE) % hashtableEntryArr.length;
                    HashtableEntry<K, V> prev = null;
                    for (HashtableEntry<K, V> e = hashtableEntryArr[index2]; e != null; e = e.next) {
                        if (e == this.lastReturned) {
                            Hashtable.access$508(Hashtable.this);
                            this.expectedModCount++;
                            if (prev == null) {
                                hashtableEntryArr[index2] = e.next;
                            } else {
                                prev.next = e.next;
                            }
                            Hashtable.access$210(Hashtable.this);
                            this.lastReturned = null;
                        } else {
                            prev = e;
                        }
                    }
                    throw new ConcurrentModificationException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }
}
