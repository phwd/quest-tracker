package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.Map;

public class EnumMap extends AbstractMap implements Serializable, Cloneable {
    private static final Object NULL = new Object() {
        /* class java.util.EnumMap.AnonymousClass1 */

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "java.util.EnumMap.NULL";
        }
    };
    private static final Enum[] ZERO_LENGTH_ENUM_ARRAY = new Enum[0];
    private static final long serialVersionUID = 458661240069192865L;
    private transient Set entrySet;
    private final Class keyType;
    private transient Enum[] keyUniverse;
    private transient int size = 0;
    private transient Object[] vals;

    static /* synthetic */ int access$210(EnumMap enumMap) {
        int i = enumMap.size;
        enumMap.size = i - 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object maskNull(Object obj) {
        return obj == null ? NULL : obj;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object unmaskNull(Object obj) {
        if (obj == NULL) {
            return null;
        }
        return obj;
    }

    public EnumMap(Class cls) {
        this.keyType = cls;
        this.keyUniverse = getKeyUniverse(cls);
        this.vals = new Object[this.keyUniverse.length];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap
    public boolean containsValue(Object obj) {
        Object maskNull = maskNull(obj);
        for (Object obj2 : this.vals) {
            if (maskNull.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return isValidKey(obj) && this.vals[((Enum) obj).ordinal()] != null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean containsMapping(Object obj, Object obj2) {
        return isValidKey(obj) && maskNull(obj2).equals(this.vals[((Enum) obj).ordinal()]);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        if (isValidKey(obj)) {
            return unmaskNull(this.vals[((Enum) obj).ordinal()]);
        }
        return null;
    }

    public Object put(Enum r3, Object obj) {
        typeCheck(r3);
        int ordinal = r3.ordinal();
        Object[] objArr = this.vals;
        Object obj2 = objArr[ordinal];
        objArr[ordinal] = maskNull(obj);
        if (obj2 == null) {
            this.size++;
        }
        return unmaskNull(obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        if (!isValidKey(obj)) {
            return null;
        }
        int ordinal = ((Enum) obj).ordinal();
        Object[] objArr = this.vals;
        Object obj2 = objArr[ordinal];
        objArr[ordinal] = null;
        if (obj2 != null) {
            this.size--;
        }
        return unmaskNull(obj2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean removeMapping(Object obj, Object obj2) {
        if (!isValidKey(obj)) {
            return false;
        }
        int ordinal = ((Enum) obj).ordinal();
        if (!maskNull(obj2).equals(this.vals[ordinal])) {
            return false;
        }
        this.vals[ordinal] = null;
        this.size--;
        return true;
    }

    private boolean isValidKey(Object obj) {
        if (obj == null) {
            return false;
        }
        Class cls = obj.getClass();
        if (cls == this.keyType || cls.getSuperclass() == this.keyType) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        if (map instanceof EnumMap) {
            EnumMap enumMap = (EnumMap) map;
            if (enumMap.keyType == this.keyType) {
                for (int i = 0; i < this.keyUniverse.length; i++) {
                    Object obj = enumMap.vals[i];
                    if (obj != null) {
                        if (this.vals[i] == null) {
                            this.size++;
                        }
                        this.vals[i] = obj;
                    }
                }
            } else if (!enumMap.isEmpty()) {
                throw new ClassCastException(enumMap.keyType + " != " + this.keyType);
            }
        } else {
            super.putAll(map);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.vals, (Object) null);
        this.size = 0;
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
            return EnumMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return EnumMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int i = EnumMap.this.size;
            EnumMap.this.remove(obj);
            return EnumMap.this.size != i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            EnumMap.this.clear();
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
            return EnumMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return EnumMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            Object maskNull = EnumMap.this.maskNull(obj);
            for (int i = 0; i < EnumMap.this.vals.length; i++) {
                if (maskNull.equals(EnumMap.this.vals[i])) {
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
            return EnumMap.this.containsMapping(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
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
            Object[] objArr = new Object[EnumMap.this.size];
            fillEntryArray(objArr);
            return objArr;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray(Object[] objArr) {
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            if (objArr.length > size) {
                objArr[size] = null;
            }
            fillEntryArray(objArr);
            return objArr;
        }

        private Object[] fillEntryArray(Object[] objArr) {
            int i = 0;
            for (int i2 = 0; i2 < EnumMap.this.vals.length; i2++) {
                if (EnumMap.this.vals[i2] != null) {
                    Enum r4 = EnumMap.this.keyUniverse[i2];
                    EnumMap enumMap = EnumMap.this;
                    objArr[i] = new AbstractMap.SimpleEntry(r4, enumMap.unmaskNull(enumMap.vals[i2]));
                    i++;
                }
            }
            return objArr;
        }
    }

    /* access modifiers changed from: private */
    public abstract class EnumMapIterator implements Iterator {
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

    private class KeyIterator extends EnumMapIterator {
        private KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Enum next() {
            if (hasNext()) {
                int i = this.index;
                this.index = i + 1;
                this.lastReturnedIndex = i;
                return EnumMap.this.keyUniverse[this.lastReturnedIndex];
            }
            throw new NoSuchElementException();
        }
    }

    private class ValueIterator extends EnumMapIterator {
        private ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Object next() {
            if (hasNext()) {
                int i = this.index;
                this.index = i + 1;
                this.lastReturnedIndex = i;
                EnumMap enumMap = EnumMap.this;
                return enumMap.unmaskNull(enumMap.vals[this.lastReturnedIndex]);
            }
            throw new NoSuchElementException();
        }
    }

    /* access modifiers changed from: private */
    public class EntryIterator extends EnumMapIterator {
        private Entry lastReturnedEntry;

        private EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry next() {
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
            public Enum getKey() {
                checkIndexForEntryUse();
                return EnumMap.this.keyUniverse[this.index];
            }

            @Override // java.util.Map.Entry
            public Object getValue() {
                checkIndexForEntryUse();
                EnumMap enumMap = EnumMap.this;
                return enumMap.unmaskNull(enumMap.vals[this.index]);
            }

            public boolean equals(Object obj) {
                if (this.index < 0) {
                    return obj == this;
                }
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                EnumMap enumMap = EnumMap.this;
                Object unmaskNull = enumMap.unmaskNull(enumMap.vals[this.index]);
                Object value = entry.getValue();
                if (entry.getKey() == EnumMap.this.keyUniverse[this.index]) {
                    if (unmaskNull == value) {
                        return true;
                    }
                    if (unmaskNull != null && unmaskNull.equals(value)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // java.util.Map.Entry
            public int hashCode() {
                int i = this.index;
                if (i < 0) {
                    return super.hashCode();
                }
                return EnumMap.this.entryHashCode(i);
            }

            public String toString() {
                if (this.index >= 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(EnumMap.this.keyUniverse[this.index]);
                    sb.append("=");
                    EnumMap enumMap = EnumMap.this;
                    sb.append(enumMap.unmaskNull(enumMap.vals[this.index]));
                    return sb.toString();
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnumMap) {
            return equals((EnumMap) obj);
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this.size != map.size()) {
            return false;
        }
        int i = 0;
        while (true) {
            Enum[] enumArr = this.keyUniverse;
            if (i >= enumArr.length) {
                return true;
            }
            Object[] objArr = this.vals;
            if (objArr[i] != null) {
                Enum r3 = enumArr[i];
                Object unmaskNull = unmaskNull(objArr[i]);
                if (unmaskNull == null) {
                    if (map.get(r3) != null || !map.containsKey(r3)) {
                        return false;
                    }
                } else if (!unmaskNull.equals(map.get(r3))) {
                    return false;
                }
            }
            i++;
        }
        return false;
    }

    private boolean equals(EnumMap enumMap) {
        if (enumMap.keyType != this.keyType) {
            return this.size == 0 && enumMap.size == 0;
        }
        for (int i = 0; i < this.keyUniverse.length; i++) {
            Object obj = this.vals[i];
            Object obj2 = enumMap.vals[i];
            if (obj2 != obj && (obj2 == null || !obj2.equals(obj))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.keyUniverse.length; i2++) {
            if (this.vals[i2] != null) {
                i += entryHashCode(i2);
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int entryHashCode(int i) {
        return this.vals[i].hashCode() ^ this.keyUniverse[i].hashCode();
    }

    @Override // java.util.AbstractMap
    public EnumMap clone() {
        try {
            EnumMap enumMap = (EnumMap) super.clone();
            enumMap.vals = (Object[]) enumMap.vals.clone();
            enumMap.entrySet = null;
            return enumMap;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }

    private void typeCheck(Enum r3) {
        Class cls = r3.getClass();
        if (cls != this.keyType && cls.getSuperclass() != this.keyType) {
            throw new ClassCastException(cls + " != " + this.keyType);
        }
    }

    private static Enum[] getKeyUniverse(Class cls) {
        return (Enum[]) cls.getEnumConstantsShared();
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
