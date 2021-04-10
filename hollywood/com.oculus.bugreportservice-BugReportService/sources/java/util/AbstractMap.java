package java.util;

import java.io.Serializable;
import java.util.Map;

public abstract class AbstractMap implements Map {
    transient Set keySet;
    transient Collection values;

    @Override // java.util.Map
    public abstract Set entrySet();

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

    public boolean containsValue(Object obj) {
        Iterator it = entrySet().iterator();
        if (obj == null) {
            while (it.hasNext()) {
                if (((Map.Entry) it.next()).getValue() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(((Map.Entry) it.next()).getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        Iterator it = entrySet().iterator();
        if (obj == null) {
            while (it.hasNext()) {
                if (((Map.Entry) it.next()).getKey() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(((Map.Entry) it.next()).getKey())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Iterator it = entrySet().iterator();
        if (obj == null) {
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey() == null) {
                    return entry.getValue();
                }
            }
            return null;
        }
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            if (obj.equals(entry2.getKey())) {
                return entry2.getValue();
            }
        }
        return null;
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        Map.Entry entry;
        Iterator it = entrySet().iterator();
        if (obj == null) {
            entry = null;
            while (entry == null && it.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it.next();
                if (entry2.getKey() == null) {
                    entry = entry2;
                }
            }
        } else {
            Map.Entry entry3 = null;
            while (entry3 == null && it.hasNext()) {
                Map.Entry entry4 = (Map.Entry) it.next();
                if (obj.equals(entry4.getKey())) {
                    entry3 = entry4;
                }
            }
            entry = entry3;
        }
        if (entry == null) {
            return null;
        }
        Object value = entry.getValue();
        it.remove();
        return value;
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public void clear() {
        entrySet().clear();
    }

    @Override // java.util.Map
    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        AnonymousClass1 r0 = new AbstractSet() {
            /* class java.util.AbstractMap.AnonymousClass1 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator iterator() {
                return new Iterator() {
                    /* class java.util.AbstractMap.AnonymousClass1.AnonymousClass1 */
                    private Iterator i = AbstractMap.this.entrySet().iterator();

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Object next() {
                        return ((Map.Entry) this.i.next()).getKey();
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
            public boolean contains(Object obj) {
                return AbstractMap.this.containsKey(obj);
            }
        };
        this.keySet = r0;
        return r0;
    }

    @Override // java.util.Map
    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        AnonymousClass2 r0 = new AbstractCollection() {
            /* class java.util.AbstractMap.AnonymousClass2 */

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
            public Iterator iterator() {
                return new Iterator() {
                    /* class java.util.AbstractMap.AnonymousClass2.AnonymousClass1 */
                    private Iterator i = AbstractMap.this.entrySet().iterator();

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Object next() {
                        return ((Map.Entry) this.i.next()).getValue();
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
            public boolean contains(Object obj) {
                return AbstractMap.this.containsValue(obj);
            }
        };
        this.values = r0;
        return r0;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
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
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.Map
    public int hashCode() {
        int i = 0;
        for (Map.Entry entry : entrySet()) {
            i += entry.hashCode();
        }
        return i;
    }

    public String toString() {
        Iterator it = entrySet().iterator();
        if (!it.hasNext()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        while (true) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == this) {
                key = "(this Map)";
            }
            sb.append(key);
            sb.append('=');
            if (value == this) {
                value = "(this Map)";
            }
            sb.append(value);
            if (!it.hasNext()) {
                sb.append('}');
                return sb.toString();
            }
            sb.append(',');
            sb.append(' ');
        }
    }

    /* access modifiers changed from: protected */
    public Object clone() {
        AbstractMap abstractMap = (AbstractMap) super.clone();
        abstractMap.keySet = null;
        abstractMap.values = null;
        return abstractMap;
    }

    /* access modifiers changed from: private */
    public static boolean eq(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static class SimpleEntry implements Map.Entry, Serializable {
        private static final long serialVersionUID = -8499721149061103585L;
        private final Object key;
        private Object value;

        public SimpleEntry(Object obj, Object obj2) {
            this.key = obj;
            this.value = obj2;
        }

        public SimpleEntry(Map.Entry entry) {
            this.key = entry.getKey();
            this.value = entry.getValue();
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.key;
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
            if (!AbstractMap.eq(this.key, entry.getKey()) || !AbstractMap.eq(this.value, entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.key;
            int i = 0;
            int hashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.value;
            if (obj2 != null) {
                i = obj2.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public static class SimpleImmutableEntry implements Map.Entry, Serializable {
        private static final long serialVersionUID = 7138329143949025153L;
        private final Object key;
        private final Object value;

        public SimpleImmutableEntry(Object obj, Object obj2) {
            this.key = obj;
            this.value = obj2;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.key;
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
            if (!AbstractMap.eq(this.key, entry.getKey()) || !AbstractMap.eq(this.value, entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            Object obj = this.key;
            int i = 0;
            int hashCode = obj == null ? 0 : obj.hashCode();
            Object obj2 = this.value;
            if (obj2 != null) {
                i = obj2.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
