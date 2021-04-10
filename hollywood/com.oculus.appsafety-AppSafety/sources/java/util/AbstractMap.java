package java.util;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    transient Set<K> keySet;
    transient Collection<V> values;

    @Override // java.util.Map
    public abstract Set<Map.Entry<K, V>> entrySet();

    protected AbstractMap() {
    }

    @Override // java.util.Map
    public int size() {
        return entrySet().size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        Iterator<Map.Entry<K, V>> i = entrySet().iterator();
        if (value == null) {
            while (i.hasNext()) {
                if (i.next().getValue() == null) {
                    return true;
                }
            }
            return false;
        }
        while (i.hasNext()) {
            if (value.equals(i.next().getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        Iterator<Map.Entry<K, V>> i = entrySet().iterator();
        if (key == null) {
            while (i.hasNext()) {
                if (i.next().getKey() == null) {
                    return true;
                }
            }
            return false;
        }
        while (i.hasNext()) {
            if (key.equals(i.next().getKey())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public V get(Object key) {
        Iterator<Map.Entry<K, V>> i = entrySet().iterator();
        if (key == null) {
            while (i.hasNext()) {
                Map.Entry<K, V> e = i.next();
                if (e.getKey() == null) {
                    return e.getValue();
                }
            }
            return null;
        }
        while (i.hasNext()) {
            Map.Entry<K, V> e2 = i.next();
            if (key.equals(e2.getKey())) {
                return e2.getValue();
            }
        }
        return null;
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public V remove(Object key) {
        Iterator<Map.Entry<K, V>> i = entrySet().iterator();
        Map.Entry<K, V> correctEntry = null;
        if (key == null) {
            while (correctEntry == null && i.hasNext()) {
                Map.Entry<K, V> e = i.next();
                if (e.getKey() == null) {
                    correctEntry = e;
                }
            }
        } else {
            while (correctEntry == null && i.hasNext()) {
                Map.Entry<K, V> e2 = i.next();
                if (key.equals(e2.getKey())) {
                    correctEntry = e2;
                }
            }
        }
        if (correctEntry == null) {
            return null;
        }
        V oldValue = correctEntry.getValue();
        i.remove();
        return oldValue;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.AbstractMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override // java.util.Map
    public void clear() {
        entrySet().clear();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        Set<K> ks2 = new AbstractSet<K>() {
            /* class java.util.AbstractMap.AnonymousClass1 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    /* class java.util.AbstractMap.AnonymousClass1.AnonymousClass1 */
                    private Iterator<Map.Entry<K, V>> i = AbstractMap.this.entrySet().iterator();

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public K next() {
                        return this.i.next().getKey();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        this.i.remove();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return AbstractMap.this.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return AbstractMap.this.isEmpty();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public void clear() {
                AbstractMap.this.clear();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object k) {
                return AbstractMap.this.containsKey(k);
            }
        };
        this.keySet = ks2;
        return ks2;
    }

    @Override // java.util.Map
    public Collection<V> values() {
        Collection<V> vals = this.values;
        if (vals != null) {
            return vals;
        }
        Collection<V> vals2 = new AbstractCollection<V>() {
            /* class java.util.AbstractMap.AnonymousClass2 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    /* class java.util.AbstractMap.AnonymousClass2.AnonymousClass1 */
                    private Iterator<Map.Entry<K, V>> i = AbstractMap.this.entrySet().iterator();

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public V next() {
                        return this.i.next().getValue();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        this.i.remove();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public int size() {
                return AbstractMap.this.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public boolean isEmpty() {
                return AbstractMap.this.isEmpty();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public void clear() {
                AbstractMap.this.clear();
            }

            @Override // java.util.AbstractCollection, java.util.Collection
            public boolean contains(Object v) {
                return AbstractMap.this.containsValue(v);
            }
        };
        this.values = vals2;
        return vals2;
    }

    @Override // java.util.Map
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Map)) {
            return false;
        }
        Map<?, ?> m = (Map) o;
        if (m.size() != size()) {
            return false;
        }
        try {
            for (Map.Entry<K, V> e : entrySet()) {
                K key = e.getKey();
                V value = e.getValue();
                if (value == null) {
                    if (m.get(key) != null || !m.containsKey(key)) {
                        return false;
                    }
                } else if (!value.equals(m.get(key))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException e2) {
            return false;
        } catch (NullPointerException e3) {
            return false;
        }
    }

    @Override // java.util.Map
    public int hashCode() {
        int h = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            h += entry.hashCode();
        }
        return h;
    }

    public String toString() {
        Iterator<Map.Entry<K, V>> i = entrySet().iterator();
        if (!i.hasNext()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        while (true) {
            Map.Entry<K, V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            Object obj = "(this Map)";
            sb.append(key == this ? obj : key);
            sb.append('=');
            if (value != this) {
                obj = value;
            }
            sb.append(obj);
            if (!i.hasNext()) {
                sb.append('}');
                return sb.toString();
            }
            sb.append(',');
            sb.append(' ');
        }
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        AbstractMap<?, ?> result = (AbstractMap) super.clone();
        result.keySet = null;
        result.values = null;
        return result;
    }

    /* access modifiers changed from: private */
    public static boolean eq(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }

    public static class SimpleEntry<K, V> implements Map.Entry<K, V>, Serializable {
        private static final long serialVersionUID = -8499721149061103585L;
        private final K key;
        private V value;

        public SimpleEntry(K key2, V value2) {
            this.key = key2;
            this.value = value2;
        }

        public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
            this.key = (K) entry.getKey();
            this.value = (V) entry.getValue();
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
            V oldValue = this.value;
            this.value = value2;
            return oldValue;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            if (!AbstractMap.eq(this.key, e.getKey()) || !AbstractMap.eq(this.value, e.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.key;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.value;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return ((Object) this.key) + "=" + ((Object) this.value);
        }
    }

    public static class SimpleImmutableEntry<K, V> implements Map.Entry<K, V>, Serializable {
        private static final long serialVersionUID = 7138329143949025153L;
        private final K key;
        private final V value;

        public SimpleImmutableEntry(K key2, V value2) {
            this.key = key2;
            this.value = value2;
        }

        public SimpleImmutableEntry(Map.Entry<? extends K, ? extends V> entry) {
            this.key = (K) entry.getKey();
            this.value = (V) entry.getValue();
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
        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            if (!AbstractMap.eq(this.key, e.getKey()) || !AbstractMap.eq(this.value, e.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.key;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.value;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return ((Object) this.key) + "=" + ((Object) this.value);
        }
    }
}
