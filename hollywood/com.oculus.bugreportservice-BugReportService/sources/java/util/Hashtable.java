package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class Hashtable extends Dictionary implements Map, Cloneable, Serializable {
    private static final long serialVersionUID = 1421746759512286392L;
    private transient int count;
    private volatile transient Set entrySet;
    private volatile transient Set keySet;
    private float loadFactor;
    private transient int modCount;
    private transient HashtableEntry[] table;
    private int threshold;
    private volatile transient Collection values;

    static /* synthetic */ int access$210(Hashtable hashtable) {
        int i = hashtable.count;
        hashtable.count = i - 1;
        return i;
    }

    static /* synthetic */ int access$508(Hashtable hashtable) {
        int i = hashtable.modCount;
        hashtable.modCount = i + 1;
        return i;
    }

    public Hashtable(int i, float f) {
        this.modCount = 0;
        if (i < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + i);
        } else if (f <= 0.0f || Float.isNaN(f)) {
            throw new IllegalArgumentException("Illegal Load: " + f);
        } else {
            i = i == 0 ? 1 : i;
            this.loadFactor = f;
            this.table = new HashtableEntry[i];
            this.threshold = Math.min(i, 2147483640);
        }
    }

    public Hashtable(int i) {
        this(i, 0.75f);
    }

    public Hashtable() {
        this(11, 0.75f);
    }

    @Override // java.util.Map
    public synchronized int size() {
        return this.count;
    }

    @Override // java.util.Map
    public synchronized boolean isEmpty() {
        return this.count == 0;
    }

    public synchronized Enumeration keys() {
        return getEnumeration(0);
    }

    public synchronized boolean contains(Object obj) {
        if (obj != null) {
            HashtableEntry[] hashtableEntryArr = this.table;
            int length = hashtableEntryArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    return false;
                }
                for (HashtableEntry hashtableEntry = hashtableEntryArr[i]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
                    if (hashtableEntry.value.equals(obj)) {
                        return true;
                    }
                }
                length = i;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public boolean containsValue(Object obj) {
        return contains(obj);
    }

    @Override // java.util.Map
    public synchronized boolean containsKey(Object obj) {
        HashtableEntry[] hashtableEntryArr = this.table;
        int hashCode = obj.hashCode();
        for (HashtableEntry hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
            if (hashtableEntry.hash == hashCode && hashtableEntry.key.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public synchronized Object get(Object obj) {
        HashtableEntry[] hashtableEntryArr = this.table;
        int hashCode = obj.hashCode();
        for (HashtableEntry hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
            if (hashtableEntry.hash == hashCode && hashtableEntry.key.equals(obj)) {
                return hashtableEntry.value;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        HashtableEntry[] hashtableEntryArr = this.table;
        int length = hashtableEntryArr.length;
        int i = (length << 1) + 1;
        if (i - 2147483639 > 0) {
            if (length != 2147483639) {
                i = 2147483639;
            } else {
                return;
            }
        }
        HashtableEntry[] hashtableEntryArr2 = new HashtableEntry[i];
        this.modCount++;
        this.threshold = (int) Math.min(((float) i) * this.loadFactor, 2.14748365E9f);
        this.table = hashtableEntryArr2;
        while (true) {
            int i2 = length - 1;
            if (length > 0) {
                HashtableEntry hashtableEntry = hashtableEntryArr[i2];
                while (hashtableEntry != null) {
                    HashtableEntry hashtableEntry2 = hashtableEntry.next;
                    int i3 = (hashtableEntry.hash & Integer.MAX_VALUE) % i;
                    hashtableEntry.next = hashtableEntryArr2[i3];
                    hashtableEntryArr2[i3] = hashtableEntry;
                    hashtableEntry = hashtableEntry2;
                }
                length = i2;
            } else {
                return;
            }
        }
    }

    private void addEntry(int i, Object obj, Object obj2, int i2) {
        this.modCount++;
        HashtableEntry[] hashtableEntryArr = this.table;
        if (this.count >= this.threshold) {
            rehash();
            hashtableEntryArr = this.table;
            i = obj.hashCode();
            i2 = (Integer.MAX_VALUE & i) % hashtableEntryArr.length;
        }
        hashtableEntryArr[i2] = new HashtableEntry(i, obj, obj2, hashtableEntryArr[i2]);
        this.count++;
    }

    @Override // java.util.Map
    public synchronized Object put(Object obj, Object obj2) {
        if (obj2 != null) {
            HashtableEntry[] hashtableEntryArr = this.table;
            int hashCode = obj.hashCode();
            int length = (Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length;
            for (HashtableEntry hashtableEntry = hashtableEntryArr[length]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
                if (hashtableEntry.hash == hashCode && hashtableEntry.key.equals(obj)) {
                    Object obj3 = hashtableEntry.value;
                    hashtableEntry.value = obj2;
                    return obj3;
                }
            }
            addEntry(hashCode, obj, obj2, length);
            return null;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map
    public synchronized Object remove(Object obj) {
        HashtableEntry[] hashtableEntryArr = this.table;
        int hashCode = obj.hashCode();
        int length = (Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length;
        HashtableEntry hashtableEntry = null;
        for (HashtableEntry hashtableEntry2 = hashtableEntryArr[length]; hashtableEntry2 != null; hashtableEntry2 = hashtableEntry2.next) {
            if (hashtableEntry2.hash != hashCode || !hashtableEntry2.key.equals(obj)) {
                hashtableEntry = hashtableEntry2;
            } else {
                this.modCount++;
                if (hashtableEntry != null) {
                    hashtableEntry.next = hashtableEntry2.next;
                } else {
                    hashtableEntryArr[length] = hashtableEntry2.next;
                }
                this.count--;
                Object obj2 = hashtableEntry2.value;
                hashtableEntry2.value = null;
                return obj2;
            }
        }
        return null;
    }

    @Override // java.util.Map
    public synchronized void putAll(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public synchronized void clear() {
        HashtableEntry[] hashtableEntryArr = this.table;
        this.modCount++;
        int length = hashtableEntryArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                hashtableEntryArr[length] = null;
            } else {
                this.count = 0;
            }
        }
    }

    public synchronized Object clone() {
        Hashtable hashtable;
        try {
            hashtable = (Hashtable) super.clone();
            hashtable.table = new HashtableEntry[this.table.length];
            int length = this.table.length;
            while (true) {
                int i = length - 1;
                HashtableEntry hashtableEntry = null;
                if (length > 0) {
                    HashtableEntry[] hashtableEntryArr = hashtable.table;
                    if (this.table[i] != null) {
                        hashtableEntry = (HashtableEntry) this.table[i].clone();
                    }
                    hashtableEntryArr[i] = hashtableEntry;
                    length = i;
                } else {
                    hashtable.keySet = null;
                    hashtable.entrySet = null;
                    hashtable.values = null;
                    hashtable.modCount = 0;
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
        return hashtable;
    }

    public synchronized String toString() {
        String str;
        String str2;
        int size = size() - 1;
        if (size == -1) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = entrySet().iterator();
        sb.append('{');
        int i = 0;
        while (true) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == this) {
                str = "(this Map)";
            } else {
                str = key.toString();
            }
            sb.append(str);
            sb.append('=');
            if (value == this) {
                str2 = "(this Map)";
            } else {
                str2 = value.toString();
            }
            sb.append(str2);
            if (i == size) {
                sb.append('}');
                return sb.toString();
            }
            sb.append(", ");
            i++;
        }
    }

    private Enumeration getEnumeration(int i) {
        if (this.count == 0) {
            return Collections.emptyEnumeration();
        }
        return new Enumerator(i, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Iterator getIterator(int i) {
        if (this.count == 0) {
            return Collections.emptyIterator();
        }
        return new Enumerator(i, true);
    }

    @Override // java.util.Map
    public Set keySet() {
        if (this.keySet == null) {
            this.keySet = Collections.synchronizedSet(new KeySet(), this);
        }
        return this.keySet;
    }

    private class KeySet extends AbstractSet {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            return Hashtable.this.getIterator(0);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Hashtable.this.count;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return Hashtable.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return Hashtable.this.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Hashtable.this.clear();
        }
    }

    @Override // java.util.Map
    public Set entrySet() {
        if (this.entrySet == null) {
            this.entrySet = Collections.synchronizedSet(new EntrySet(), this);
        }
        return this.entrySet;
    }

    /* access modifiers changed from: private */
    public class EntrySet extends AbstractSet {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            add((Map.Entry) obj);
            throw null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            return Hashtable.this.getIterator(2);
        }

        public boolean add(Map.Entry entry) {
            super.add((Object) entry);
            throw null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            HashtableEntry[] hashtableEntryArr = Hashtable.this.table;
            int hashCode = key.hashCode();
            for (HashtableEntry hashtableEntry = hashtableEntryArr[(Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
                if (hashtableEntry.hash == hashCode && hashtableEntry.equals(entry)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            HashtableEntry[] hashtableEntryArr = Hashtable.this.table;
            int hashCode = key.hashCode();
            int length = (Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length;
            HashtableEntry hashtableEntry = null;
            for (HashtableEntry hashtableEntry2 = hashtableEntryArr[length]; hashtableEntry2 != null; hashtableEntry2 = hashtableEntry2.next) {
                if (hashtableEntry2.hash != hashCode || !hashtableEntry2.equals(entry)) {
                    hashtableEntry = hashtableEntry2;
                } else {
                    Hashtable.access$508(Hashtable.this);
                    if (hashtableEntry != null) {
                        hashtableEntry.next = hashtableEntry2.next;
                    } else {
                        hashtableEntryArr[length] = hashtableEntry2.next;
                    }
                    Hashtable.access$210(Hashtable.this);
                    hashtableEntry2.value = null;
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
    public Collection values() {
        if (this.values == null) {
            this.values = Collections.synchronizedCollection(new ValueCollection(), this);
        }
        return this.values;
    }

    private class ValueCollection extends AbstractCollection {
        private ValueCollection() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return Hashtable.this.getIterator(1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return Hashtable.this.count;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return Hashtable.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Hashtable.this.clear();
        }
    }

    @Override // java.util.Map
    public synchronized boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != size()) {
            return false;
        }
        try {
            for (Map.Entry entry : entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    if (map.get(key) != null || !map.containsKey(key)) {
                        return false;
                    }
                } else if (!value.equals(map.get(key))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused2) {
            return false;
        }
    }

    @Override // java.util.Map
    public synchronized int hashCode() {
        if (this.count != 0) {
            if (this.loadFactor >= 0.0f) {
                this.loadFactor = -this.loadFactor;
                HashtableEntry[] hashtableEntryArr = this.table;
                int i = 0;
                for (HashtableEntry hashtableEntry : hashtableEntryArr) {
                    for (; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
                        i += hashtableEntry.hashCode();
                    }
                }
                this.loadFactor = -this.loadFactor;
                return i;
            }
        }
        return 0;
    }

    @Override // java.util.Map
    public synchronized Object putIfAbsent(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hashCode = obj.hashCode();
        int length = (Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length;
        for (HashtableEntry hashtableEntry = hashtableEntryArr[length]; hashtableEntry != null; hashtableEntry = hashtableEntry.next) {
            if (hashtableEntry.hash == hashCode && hashtableEntry.key.equals(obj)) {
                Object obj3 = hashtableEntry.value;
                if (obj3 == null) {
                    hashtableEntry.value = obj2;
                }
                return obj3;
            }
        }
        addEntry(hashCode, obj, obj2, length);
        return null;
    }

    @Override // java.util.Map
    public synchronized boolean remove(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        HashtableEntry[] hashtableEntryArr = this.table;
        int hashCode = obj.hashCode();
        int length = (Integer.MAX_VALUE & hashCode) % hashtableEntryArr.length;
        HashtableEntry hashtableEntry = null;
        for (HashtableEntry hashtableEntry2 = hashtableEntryArr[length]; hashtableEntry2 != null; hashtableEntry2 = hashtableEntry2.next) {
            if (hashtableEntry2.hash != hashCode || !hashtableEntry2.key.equals(obj) || !hashtableEntry2.value.equals(obj2)) {
                hashtableEntry = hashtableEntry2;
            } else {
                this.modCount++;
                if (hashtableEntry != null) {
                    hashtableEntry.next = hashtableEntry2.next;
                } else {
                    hashtableEntryArr[length] = hashtableEntry2.next;
                }
                this.count--;
                hashtableEntry2.value = null;
                return true;
            }
        }
        return false;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        synchronized (this) {
            objectOutputStream.defaultWriteObject();
            throw null;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    /* access modifiers changed from: private */
    public static class HashtableEntry implements Map.Entry {
        final int hash;
        final Object key;
        HashtableEntry next;
        Object value;

        protected HashtableEntry(int i, Object obj, Object obj2, HashtableEntry hashtableEntry) {
            this.hash = i;
            this.key = obj;
            this.value = obj2;
            this.next = hashtableEntry;
        }

        /* access modifiers changed from: protected */
        public Object clone() {
            int i = this.hash;
            Object obj = this.key;
            Object obj2 = this.value;
            HashtableEntry hashtableEntry = this.next;
            return new HashtableEntry(i, obj, obj2, hashtableEntry == null ? null : (HashtableEntry) hashtableEntry.clone());
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.value;
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0032 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r3.key
                if (r0 != 0) goto L_0x0013
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x0033
                goto L_0x001d
            L_0x0013:
                java.lang.Object r2 = r4.getKey()
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0033
            L_0x001d:
                java.lang.Object r3 = r3.value
                if (r3 != 0) goto L_0x0028
                java.lang.Object r3 = r4.getValue()
                if (r3 != 0) goto L_0x0033
                goto L_0x0032
            L_0x0028:
                java.lang.Object r4 = r4.getValue()
                boolean r3 = r3.equals(r4)
                if (r3 == 0) goto L_0x0033
            L_0x0032:
                r1 = 1
            L_0x0033:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Hashtable.HashtableEntry.equals(java.lang.Object):boolean");
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return Objects.hashCode(this.value) ^ this.hash;
        }

        public String toString() {
            return this.key.toString() + "=" + this.value.toString();
        }
    }

    /* access modifiers changed from: private */
    public class Enumerator implements Enumeration, Iterator {
        HashtableEntry entry;
        protected int expectedModCount = Hashtable.this.modCount;
        int index = this.table.length;
        boolean iterator;
        HashtableEntry lastReturned;
        HashtableEntry[] table = Hashtable.this.table;
        int type;

        Enumerator(int i, boolean z) {
            this.type = i;
            this.iterator = z;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            HashtableEntry hashtableEntry = this.entry;
            int i = this.index;
            HashtableEntry[] hashtableEntryArr = this.table;
            while (hashtableEntry == null && i > 0) {
                i--;
                hashtableEntry = hashtableEntryArr[i];
            }
            this.entry = hashtableEntry;
            this.index = i;
            return hashtableEntry != null;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            HashtableEntry hashtableEntry = this.entry;
            int i = this.index;
            HashtableEntry[] hashtableEntryArr = this.table;
            while (hashtableEntry == null && i > 0) {
                i--;
                hashtableEntry = hashtableEntryArr[i];
            }
            this.entry = hashtableEntry;
            this.index = i;
            if (hashtableEntry != null) {
                HashtableEntry hashtableEntry2 = this.entry;
                this.lastReturned = hashtableEntry2;
                this.entry = hashtableEntry2.next;
                int i2 = this.type;
                if (i2 == 0) {
                    return hashtableEntry2.key;
                }
                return i2 == 1 ? hashtableEntry2.value : hashtableEntry2;
            }
            throw new NoSuchElementException("Hashtable Enumerator");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return hasMoreElements();
        }

        @Override // java.util.Iterator
        public Object next() {
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
                    HashtableEntry[] hashtableEntryArr = Hashtable.this.table;
                    int length = (this.lastReturned.hash & Integer.MAX_VALUE) % hashtableEntryArr.length;
                    HashtableEntry hashtableEntry = null;
                    for (HashtableEntry hashtableEntry2 = hashtableEntryArr[length]; hashtableEntry2 != null; hashtableEntry2 = hashtableEntry2.next) {
                        if (hashtableEntry2 == this.lastReturned) {
                            Hashtable.access$508(Hashtable.this);
                            this.expectedModCount++;
                            if (hashtableEntry == null) {
                                hashtableEntryArr[length] = hashtableEntry2.next;
                            } else {
                                hashtableEntry.next = hashtableEntry2.next;
                            }
                            Hashtable.access$210(Hashtable.this);
                            this.lastReturned = null;
                        } else {
                            hashtableEntry = hashtableEntry2;
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
