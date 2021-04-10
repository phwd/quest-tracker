package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.Map;

public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V> implements Serializable, Cloneable {
    private static final Object NULL = new Object() {
        /* class java.util.EnumMap.AnonymousClass1 */

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "java.util.EnumMap.NULL";
        }
    };
    private static final Enum<?>[] ZERO_LENGTH_ENUM_ARRAY = new Enum[0];
    private static final long serialVersionUID = 458661240069192865L;
    private transient Set<Map.Entry<K, V>> entrySet;
    private final Class<K> keyType;
    private transient K[] keyUniverse;
    private transient int size = 0;
    private transient Object[] vals;

    static /* synthetic */ int access$210(EnumMap x0) {
        int i = x0.size;
        x0.size = i - 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object maskNull(Object value) {
        return value == null ? NULL : value;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private V unmaskNull(Object value) {
        if (value == NULL) {
            return null;
        }
        return value;
    }

    public EnumMap(Class<K> keyType2) {
        this.keyType = keyType2;
        this.keyUniverse = (K[]) getKeyUniverse(keyType2);
        this.vals = new Object[this.keyUniverse.length];
    }

    public EnumMap(EnumMap<K, ? extends V> m) {
        this.keyType = m.keyType;
        this.keyUniverse = m.keyUniverse;
        this.vals = (Object[]) m.vals.clone();
        this.size = m.size;
    }

    public EnumMap(Map<K, ? extends V> m) {
        if (m instanceof EnumMap) {
            EnumMap<K, ? extends V> em = (EnumMap) m;
            this.keyType = em.keyType;
            this.keyUniverse = em.keyUniverse;
            this.vals = (Object[]) em.vals.clone();
            this.size = em.size;
        } else if (!m.isEmpty()) {
            this.keyType = m.keySet().iterator().next().getDeclaringClass();
            this.keyUniverse = (K[]) getKeyUniverse(this.keyType);
            this.vals = new Object[this.keyUniverse.length];
            putAll(m);
        } else {
            throw new IllegalArgumentException("Specified map is empty");
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        Object value2 = maskNull(value);
        for (Object val : this.vals) {
            if (value2.equals(val)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return isValidKey(key) && this.vals[((Enum) key).ordinal()] != null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean containsMapping(Object key, Object value) {
        return isValidKey(key) && maskNull(value).equals(this.vals[((Enum) key).ordinal()]);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        if (isValidKey(key)) {
            return unmaskNull(this.vals[((Enum) key).ordinal()]);
        }
        return null;
    }

    public V put(K key, V value) {
        typeCheck(key);
        int index = key.ordinal();
        Object[] objArr = this.vals;
        Object oldValue = objArr[index];
        objArr[index] = maskNull(value);
        if (oldValue == null) {
            this.size++;
        }
        return unmaskNull(oldValue);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        if (!isValidKey(key)) {
            return null;
        }
        int index = ((Enum) key).ordinal();
        Object[] objArr = this.vals;
        Object oldValue = objArr[index];
        objArr[index] = null;
        if (oldValue != null) {
            this.size--;
        }
        return unmaskNull(oldValue);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeMapping(Object key, Object value) {
        if (!isValidKey(key)) {
            return false;
        }
        int index = ((Enum) key).ordinal();
        if (!maskNull(value).equals(this.vals[index])) {
            return false;
        }
        this.vals[index] = null;
        this.size--;
        return true;
    }

    private boolean isValidKey(Object key) {
        if (key == null) {
            return false;
        }
        Class<?> keyClass = key.getClass();
        if (keyClass == this.keyType || keyClass.getSuperclass() == this.keyType) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m instanceof EnumMap) {
            EnumMap<?, ?> em = (EnumMap) m;
            if (em.keyType == this.keyType) {
                for (int i = 0; i < this.keyUniverse.length; i++) {
                    Object emValue = em.vals[i];
                    if (emValue != null) {
                        if (this.vals[i] == null) {
                            this.size++;
                        }
                        this.vals[i] = emValue;
                    }
                }
            } else if (!em.isEmpty()) {
                throw new ClassCastException(((Object) em.keyType) + " != " + ((Object) this.keyType));
            }
        } else {
            super.putAll(m);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.vals, (Object) null);
        this.size = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        Set<K> ks2 = new KeySet();
        this.keySet = ks2;
        return ks2;
    }

    private class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return EnumMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return EnumMap.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            int oldSize = EnumMap.this.size;
            EnumMap.this.remove(o);
            return EnumMap.this.size != oldSize;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            EnumMap.this.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Collection<V> vs2 = new Values();
        this.values = vs2;
        return vs2;
    }

    private class Values extends AbstractCollection<V> {
        private Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return EnumMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return EnumMap.this.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object o) {
            Object o2 = EnumMap.this.maskNull(o);
            for (int i = 0; i < EnumMap.this.vals.length; i++) {
                if (o2.equals(EnumMap.this.vals[i])) {
                    EnumMap.this.vals[i] = null;
                    EnumMap.access$210(EnumMap.this);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            EnumMap.this.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            return EnumMap.this.containsMapping(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) o;
            return EnumMap.this.removeMapping(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return EnumMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            EnumMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return fillEntryArray(new Object[EnumMap.this.size]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] a) {
            int size = size();
            if (a.length < size) {
                a = (T[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), size));
            }
            if (a.length > size) {
                a[size] = null;
            }
            return (T[]) fillEntryArray(a);
        }

        private Object[] fillEntryArray(Object[] a) {
            int j = 0;
            for (int i = 0; i < EnumMap.this.vals.length; i++) {
                if (EnumMap.this.vals[i] != null) {
                    Enum r4 = EnumMap.this.keyUniverse[i];
                    EnumMap enumMap = EnumMap.this;
                    a[j] = new AbstractMap.SimpleEntry(r4, enumMap.unmaskNull(enumMap.vals[i]));
                    j++;
                }
            }
            return a;
        }
    }

    private abstract class EnumMapIterator<T> implements Iterator<T> {
        int index;
        int lastReturnedIndex;

        private EnumMapIterator() {
            this.index = 0;
            this.lastReturnedIndex = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.index < EnumMap.this.vals.length) {
                Object[] objArr = EnumMap.this.vals;
                int i = this.index;
                if (objArr[i] != null) {
                    break;
                }
                this.index = i + 1;
            }
            return this.index != EnumMap.this.vals.length;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkLastReturnedIndex();
            if (EnumMap.this.vals[this.lastReturnedIndex] != null) {
                EnumMap.this.vals[this.lastReturnedIndex] = null;
                EnumMap.access$210(EnumMap.this);
            }
            this.lastReturnedIndex = -1;
        }

        private void checkLastReturnedIndex() {
            if (this.lastReturnedIndex < 0) {
                throw new IllegalStateException();
            }
        }
    }

    private class KeyIterator extends EnumMap<K, V>.EnumMapIterator {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            if (hasNext()) {
                int i = this.index;
                this.index = i + 1;
                this.lastReturnedIndex = i;
                return (K) EnumMap.this.keyUniverse[this.lastReturnedIndex];
            }
            throw new NoSuchElementException();
        }
    }

    private class ValueIterator extends EnumMap<K, V>.EnumMapIterator {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            if (hasNext()) {
                int i = this.index;
                this.index = i + 1;
                this.lastReturnedIndex = i;
                EnumMap enumMap = EnumMap.this;
                return (V) enumMap.unmaskNull(enumMap.vals[this.lastReturnedIndex]);
            }
            throw new NoSuchElementException();
        }
    }

    /* access modifiers changed from: private */
    public class EntryIterator extends EnumMap<K, V>.EnumMapIterator {
        private EnumMap<K, V>.EntryIterator.Entry lastReturnedEntry;

        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            if (hasNext()) {
                int i = this.index;
                this.index = i + 1;
                this.lastReturnedEntry = new Entry(i);
                return this.lastReturnedEntry;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator, java.util.EnumMap.EnumMapIterator
        public void remove() {
            EnumMap<K, V>.EntryIterator.Entry entry = this.lastReturnedEntry;
            this.lastReturnedIndex = entry == null ? -1 : ((Entry) entry).index;
            super.remove();
            ((Entry) this.lastReturnedEntry).index = this.lastReturnedIndex;
            this.lastReturnedEntry = null;
        }

        /* access modifiers changed from: private */
        public class Entry implements Map.Entry<K, V> {
            private int index;

            private Entry(int index2) {
                this.index = index2;
            }

            @Override // java.util.Map.Entry
            public K getKey() {
                checkIndexForEntryUse();
                return (K) EnumMap.this.keyUniverse[this.index];
            }

            @Override // java.util.Map.Entry
            public V getValue() {
                checkIndexForEntryUse();
                return (V) EnumMap.this.unmaskNull(EnumMap.this.vals[this.index]);
            }

            @Override // java.util.Map.Entry
            public V setValue(V value) {
                checkIndexForEntryUse();
                V oldValue = (V) EnumMap.this.unmaskNull(EnumMap.this.vals[this.index]);
                EnumMap.this.vals[this.index] = EnumMap.this.maskNull(value);
                return oldValue;
            }

            @Override // java.util.Map.Entry
            public boolean equals(Object o) {
                if (this.index < 0) {
                    return o == this;
                }
                if (!(o instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry<?, ?> e = (Map.Entry) o;
                Object unmaskNull = EnumMap.this.unmaskNull(EnumMap.this.vals[this.index]);
                Object hisValue = e.getValue();
                if (e.getKey() == EnumMap.this.keyUniverse[this.index]) {
                    if (unmaskNull == hisValue) {
                        return true;
                    }
                    if (unmaskNull != null && unmaskNull.equals(hisValue)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // java.util.Map.Entry
            public int hashCode() {
                if (this.index < 0) {
                    return super.hashCode();
                }
                return EnumMap.this.entryHashCode(this.index);
            }

            public String toString() {
                if (this.index < 0) {
                    return super.toString();
                }
                return ((Object) EnumMap.this.keyUniverse[this.index]) + "=" + EnumMap.this.unmaskNull(EnumMap.this.vals[this.index]);
            }

            private void checkIndexForEntryUse() {
                if (this.index < 0) {
                    throw new IllegalStateException("Entry was removed");
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof EnumMap) {
            return equals((EnumMap) o);
        }
        if (!(o instanceof Map)) {
            return false;
        }
        Map<?, ?> m = (Map) o;
        if (this.size != m.size()) {
            return false;
        }
        int i = 0;
        while (true) {
            K[] kArr = this.keyUniverse;
            if (i >= kArr.length) {
                return true;
            }
            Object[] objArr = this.vals;
            if (objArr[i] != null) {
                K key = kArr[i];
                V value = unmaskNull(objArr[i]);
                if (value == null) {
                    if (m.get(key) != null || !m.containsKey(key)) {
                        return false;
                    }
                } else if (!value.equals(m.get(key))) {
                    return false;
                }
            }
            i++;
        }
        return false;
    }

    private boolean equals(EnumMap<?, ?> em) {
        if (em.keyType != this.keyType) {
            return this.size == 0 && em.size == 0;
        }
        for (int i = 0; i < this.keyUniverse.length; i++) {
            Object ourValue = this.vals[i];
            Object hisValue = em.vals[i];
            if (hisValue != ourValue && (hisValue == null || !hisValue.equals(ourValue))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int h = 0;
        for (int i = 0; i < this.keyUniverse.length; i++) {
            if (this.vals[i] != null) {
                h += entryHashCode(i);
            }
        }
        return h;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int entryHashCode(int index) {
        return this.keyUniverse[index].hashCode() ^ this.vals[index].hashCode();
    }

    @Override // java.util.AbstractMap
    public EnumMap<K, V> clone() {
        try {
            EnumMap<K, V> result = (EnumMap) super.clone();
            result.vals = (Object[]) result.vals.clone();
            result.entrySet = null;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private void typeCheck(K key) {
        Class<?> keyClass = key.getClass();
        if (keyClass != this.keyType && keyClass.getSuperclass() != this.keyType) {
            throw new ClassCastException(((Object) keyClass) + " != " + ((Object) this.keyType));
        }
    }

    private static <K extends Enum<K>> K[] getKeyUniverse(Class<K> keyType2) {
        return keyType2.getEnumConstantsShared();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(this.size);
        int entriesToBeWritten = this.size;
        int i = 0;
        while (entriesToBeWritten > 0) {
            if (this.vals[i] != null) {
                s.writeObject(this.keyUniverse[i]);
                s.writeObject(unmaskNull(this.vals[i]));
                entriesToBeWritten--;
            }
            i++;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.EnumMap<K extends java.lang.Enum<K>, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.keyUniverse = (K[]) getKeyUniverse(this.keyType);
        this.vals = new Object[this.keyUniverse.length];
        int size2 = s.readInt();
        for (int i = 0; i < size2; i++) {
            put((Enum) s.readObject(), s.readObject());
        }
    }
}
