package java.util;

import dalvik.system.VMRuntime;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;

public class Collections {
    public static final List EMPTY_LIST = new EmptyList();
    public static final Map EMPTY_MAP = new EmptyMap();
    public static final Set EMPTY_SET = new EmptySet();

    public static void sort(List list, Comparator comparator) {
        if (VMRuntime.getRuntime().getTargetSdkVersion() > 25) {
            list.sort(comparator);
            return;
        }
        if (list.getClass() == ArrayList.class) {
            Arrays.sort(((ArrayList) list).elementData, 0, list.size(), comparator);
            return;
        }
        Object[] array = list.toArray();
        Arrays.sort(array, comparator);
        ListIterator listIterator = list.listIterator();
        for (Object obj : array) {
            listIterator.next();
            listIterator.set(obj);
        }
    }

    public static void reverse(List list) {
        int size = list.size();
        int i = 0;
        if (size < 18 || (list instanceof RandomAccess)) {
            int i2 = size >> 1;
            int i3 = size - 1;
            while (i < i2) {
                swap(list, i, i3);
                i++;
                i3--;
            }
            return;
        }
        ListIterator listIterator = list.listIterator();
        ListIterator listIterator2 = list.listIterator(size);
        int size2 = list.size() >> 1;
        while (i < size2) {
            Object next = listIterator.next();
            listIterator.set(listIterator2.previous());
            listIterator2.set(next);
            i++;
        }
    }

    public static void swap(List list, int i, int i2) {
        list.set(i, list.set(i2, list.get(i)));
    }

    public static Collection unmodifiableCollection(Collection collection) {
        return new UnmodifiableCollection(collection);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableCollection implements Collection, Serializable {
        private static final long serialVersionUID = 1820017752578914078L;
        final Collection c;

        UnmodifiableCollection(Collection collection) {
            if (collection != null) {
                this.c = collection;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Collection
        public int size() {
            return this.c.size();
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.c.isEmpty();
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            return this.c.contains(obj);
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return this.c.toArray();
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            return this.c.toArray(objArr);
        }

        public String toString() {
            return this.c.toString();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return new Iterator() {
                /* class java.util.Collections.UnmodifiableCollection.AnonymousClass1 */
                private final Iterator i = UnmodifiableCollection.this.c.iterator();

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.i.hasNext();
                }

                @Override // java.util.Iterator
                public Object next() {
                    return this.i.next();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            return this.c.containsAll(collection);
        }

        @Override // java.util.Collection
        public boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    public static Set unmodifiableSet(Set set) {
        return new UnmodifiableSet(set);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableSet extends UnmodifiableCollection implements Set, Serializable {
        private static final long serialVersionUID = -9215047833775013803L;

        UnmodifiableSet(Set set) {
            super(set);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return obj == this || this.c.equals(obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return this.c.hashCode();
        }
    }

    public static SortedSet unmodifiableSortedSet(SortedSet sortedSet) {
        return new UnmodifiableSortedSet(sortedSet);
    }

    /* access modifiers changed from: package-private */
    public static class UnmodifiableSortedSet extends UnmodifiableSet implements SortedSet, Serializable {
        private static final long serialVersionUID = -4929149591599911165L;
        private final SortedSet ss;

        UnmodifiableSortedSet(SortedSet sortedSet) {
            super(sortedSet);
            this.ss = sortedSet;
        }

        @Override // java.util.SortedSet
        public Comparator comparator() {
            return this.ss.comparator();
        }

        @Override // java.util.SortedSet
        public Object first() {
            return this.ss.first();
        }
    }

    public static List unmodifiableList(List list) {
        if (list instanceof RandomAccess) {
            return new UnmodifiableRandomAccessList(list);
        }
        return new UnmodifiableList(list);
    }

    static class UnmodifiableList extends UnmodifiableCollection implements List {
        private static final long serialVersionUID = -283967356065247728L;
        final List list;

        UnmodifiableList(List list2) {
            super(list2);
            this.list = list2;
        }

        @Override // java.util.List, java.util.Collection
        public boolean equals(Object obj) {
            return obj == this || this.list.equals(obj);
        }

        @Override // java.util.List, java.util.Collection
        public int hashCode() {
            return this.list.hashCode();
        }

        @Override // java.util.List
        public Object get(int i) {
            return this.list.get(i);
        }

        @Override // java.util.List
        public Object set(int i, Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public void add(int i, Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public Object remove(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            return this.list.indexOf(obj);
        }

        @Override // java.util.List
        public void sort(Comparator comparator) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.List
        public ListIterator listIterator() {
            return listIterator(0);
        }

        @Override // java.util.List
        public ListIterator listIterator(final int i) {
            return new ListIterator() {
                /* class java.util.Collections.UnmodifiableList.AnonymousClass1 */
                private final ListIterator i = UnmodifiableList.this.list.listIterator(i);

                @Override // java.util.Iterator, java.util.ListIterator
                public boolean hasNext() {
                    return this.i.hasNext();
                }

                @Override // java.util.Iterator, java.util.ListIterator
                public Object next() {
                    return this.i.next();
                }

                @Override // java.util.ListIterator
                public Object previous() {
                    return this.i.previous();
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return this.i.previousIndex();
                }

                @Override // java.util.Iterator, java.util.ListIterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.ListIterator
                public void set(Object obj) {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.ListIterator
                public void add(Object obj) {
                    throw new UnsupportedOperationException();
                }
            };
        }

        private Object readResolve() {
            List list2 = this.list;
            return list2 instanceof RandomAccess ? new UnmodifiableRandomAccessList(list2) : this;
        }
    }

    static class UnmodifiableRandomAccessList extends UnmodifiableList implements RandomAccess {
        private static final long serialVersionUID = -2542308836966382001L;

        UnmodifiableRandomAccessList(List list) {
            super(list);
        }

        private Object writeReplace() {
            return new UnmodifiableList(this.list);
        }
    }

    public static Map unmodifiableMap(Map map) {
        return new UnmodifiableMap(map);
    }

    private static class UnmodifiableMap implements Map, Serializable {
        private static final long serialVersionUID = -1034234728574286014L;
        private transient Set entrySet;
        private transient Set keySet;
        private final Map m;
        private transient Collection values;

        UnmodifiableMap(Map map) {
            if (map != null) {
                this.m = map;
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Map
        public int size() {
            return this.m.size();
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            return this.m.containsKey(obj);
        }

        @Override // java.util.Map
        public Object get(Object obj) {
            return this.m.get(obj);
        }

        @Override // java.util.Map
        public Object put(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public Object remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public void putAll(Map map) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public Set keySet() {
            if (this.keySet == null) {
                this.keySet = Collections.unmodifiableSet(this.m.keySet());
            }
            return this.keySet;
        }

        @Override // java.util.Map
        public Set entrySet() {
            if (this.entrySet == null) {
                this.entrySet = new UnmodifiableEntrySet(this.m.entrySet());
            }
            return this.entrySet;
        }

        @Override // java.util.Map
        public Collection values() {
            if (this.values == null) {
                this.values = Collections.unmodifiableCollection(this.m.values());
            }
            return this.values;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            return obj == this || this.m.equals(obj);
        }

        @Override // java.util.Map
        public int hashCode() {
            return this.m.hashCode();
        }

        public String toString() {
            return this.m.toString();
        }

        @Override // java.util.Map
        public Object putIfAbsent(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        static class UnmodifiableEntrySet extends UnmodifiableSet {
            private static final long serialVersionUID = 7854390611657943733L;

            UnmodifiableEntrySet(Set set) {
                super(set);
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator iterator() {
                return new Iterator() {
                    /* class java.util.Collections.UnmodifiableMap.UnmodifiableEntrySet.AnonymousClass1 */
                    private final Iterator i = UnmodifiableEntrySet.this.c.iterator();

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.i.hasNext();
                    }

                    @Override // java.util.Iterator
                    public Map.Entry next() {
                        return new UnmodifiableEntry((Map.Entry) this.i.next());
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                Object[] array = this.c.toArray();
                for (int i = 0; i < array.length; i++) {
                    array[i] = new UnmodifiableEntry((Map.Entry) array[i]);
                }
                return array;
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public Object[] toArray(Object[] objArr) {
                Object[] array = this.c.toArray(objArr.length == 0 ? objArr : Arrays.copyOf(objArr, 0));
                for (int i = 0; i < array.length; i++) {
                    array[i] = new UnmodifiableEntry((Map.Entry) array[i]);
                }
                if (array.length > objArr.length) {
                    return array;
                }
                System.arraycopy(array, 0, objArr, 0, array.length);
                if (objArr.length > array.length) {
                    objArr[array.length] = null;
                }
                return objArr;
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                return this.c.contains(new UnmodifiableEntry((Map.Entry) obj));
            }

            @Override // java.util.Collections.UnmodifiableCollection, java.util.Collection, java.util.Set
            public boolean containsAll(Collection collection) {
                for (Object obj : collection) {
                    if (!contains(obj)) {
                        return false;
                    }
                }
                return true;
            }

            @Override // java.util.Collection, java.util.Collections.UnmodifiableSet, java.util.Set
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Set)) {
                    return false;
                }
                Set set = (Set) obj;
                if (set.size() != this.c.size()) {
                    return false;
                }
                return containsAll(set);
            }

            /* access modifiers changed from: private */
            public static class UnmodifiableEntry implements Map.Entry {
                private Map.Entry e;

                UnmodifiableEntry(Map.Entry entry) {
                    this.e = (Map.Entry) Objects.requireNonNull(entry);
                }

                @Override // java.util.Map.Entry
                public Object getKey() {
                    return this.e.getKey();
                }

                @Override // java.util.Map.Entry
                public Object getValue() {
                    return this.e.getValue();
                }

                @Override // java.util.Map.Entry
                public int hashCode() {
                    return this.e.hashCode();
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Map.Entry)) {
                        return false;
                    }
                    Map.Entry entry = (Map.Entry) obj;
                    return Collections.eq(this.e.getKey(), entry.getKey()) && Collections.eq(this.e.getValue(), entry.getValue());
                }

                public String toString() {
                    return this.e.toString();
                }
            }
        }
    }

    public static SortedMap unmodifiableSortedMap(SortedMap sortedMap) {
        return new UnmodifiableSortedMap(sortedMap);
    }

    static class UnmodifiableSortedMap extends UnmodifiableMap implements SortedMap, Serializable {
        private static final long serialVersionUID = -8806743815996713206L;
        private final SortedMap sm;

        UnmodifiableSortedMap(SortedMap sortedMap) {
            super(sortedMap);
            this.sm = sortedMap;
        }

        @Override // java.util.SortedMap
        public Comparator comparator() {
            return this.sm.comparator();
        }

        @Override // java.util.SortedMap
        public Object firstKey() {
            return this.sm.firstKey();
        }
    }

    static Collection synchronizedCollection(Collection collection, Object obj) {
        return new SynchronizedCollection(collection, obj);
    }

    static class SynchronizedCollection implements Collection, Serializable {
        private static final long serialVersionUID = 3053995032091335093L;
        final Collection c;
        final Object mutex;

        SynchronizedCollection(Collection collection, Object obj) {
            this.c = (Collection) Objects.requireNonNull(collection);
            this.mutex = Objects.requireNonNull(obj);
        }

        @Override // java.util.Collection
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = this.c.size();
            }
            return size;
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = this.c.isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Collection
        public boolean contains(Object obj) {
            boolean contains;
            synchronized (this.mutex) {
                contains = this.c.contains(obj);
            }
            return contains;
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = this.c.toArray();
            }
            return array;
        }

        @Override // java.util.Collection
        public Object[] toArray(Object[] objArr) {
            Object[] array;
            synchronized (this.mutex) {
                array = this.c.toArray(objArr);
            }
            return array;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator iterator() {
            return this.c.iterator();
        }

        @Override // java.util.Collection
        public boolean add(Object obj) {
            boolean add;
            synchronized (this.mutex) {
                add = this.c.add(obj);
            }
            return add;
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            boolean remove;
            synchronized (this.mutex) {
                remove = this.c.remove(obj);
            }
            return remove;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection collection) {
            boolean containsAll;
            synchronized (this.mutex) {
                containsAll = this.c.containsAll(collection);
            }
            return containsAll;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection collection) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = this.c.addAll(collection);
            }
            return addAll;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = this.c.removeAll(collection);
            }
            return removeAll;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = this.c.retainAll(collection);
            }
            return retainAll;
        }

        @Override // java.util.Collection
        public void clear() {
            synchronized (this.mutex) {
                this.c.clear();
            }
        }

        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.c.toString();
            }
            return obj;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
                throw null;
            }
        }
    }

    static Set synchronizedSet(Set set, Object obj) {
        return new SynchronizedSet(set, obj);
    }

    static class SynchronizedSet extends SynchronizedCollection implements Set {
        private static final long serialVersionUID = 487447009682186044L;

        SynchronizedSet(Set set, Object obj) {
            super(set, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.c.equals(obj);
            }
            return equals;
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.c.hashCode();
            }
            return hashCode;
        }
    }

    public static Map synchronizedMap(Map map) {
        return new SynchronizedMap(map);
    }

    private static class SynchronizedMap implements Map, Serializable {
        private static final long serialVersionUID = 1978198479659022715L;
        private transient Set entrySet;
        private transient Set keySet;
        private final Map m;
        final Object mutex = this;
        private transient Collection values;

        SynchronizedMap(Map map) {
            this.m = (Map) Objects.requireNonNull(map);
        }

        @Override // java.util.Map
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = this.m.size();
            }
            return size;
        }

        @Override // java.util.Map
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = this.m.isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Map
        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = this.m.containsKey(obj);
            }
            return containsKey;
        }

        @Override // java.util.Map
        public Object get(Object obj) {
            Object obj2;
            synchronized (this.mutex) {
                obj2 = this.m.get(obj);
            }
            return obj2;
        }

        @Override // java.util.Map
        public Object put(Object obj, Object obj2) {
            Object put;
            synchronized (this.mutex) {
                put = this.m.put(obj, obj2);
            }
            return put;
        }

        @Override // java.util.Map
        public Object remove(Object obj) {
            Object remove;
            synchronized (this.mutex) {
                remove = this.m.remove(obj);
            }
            return remove;
        }

        @Override // java.util.Map
        public void putAll(Map map) {
            synchronized (this.mutex) {
                this.m.putAll(map);
            }
        }

        @Override // java.util.Map
        public void clear() {
            synchronized (this.mutex) {
                this.m.clear();
            }
        }

        @Override // java.util.Map
        public Set keySet() {
            Set set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = new SynchronizedSet(this.m.keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // java.util.Map
        public Set entrySet() {
            Set set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = new SynchronizedSet(this.m.entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // java.util.Map
        public Collection values() {
            Collection collection;
            synchronized (this.mutex) {
                if (this.values == null) {
                    this.values = new SynchronizedCollection(this.m.values(), this.mutex);
                }
                collection = this.values;
            }
            return collection;
        }

        @Override // java.util.Map
        public boolean equals(Object obj) {
            boolean equals;
            if (this == obj) {
                return true;
            }
            synchronized (this.mutex) {
                equals = this.m.equals(obj);
            }
            return equals;
        }

        @Override // java.util.Map
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = this.m.hashCode();
            }
            return hashCode;
        }

        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.m.toString();
            }
            return obj;
        }

        @Override // java.util.Map
        public Object putIfAbsent(Object obj, Object obj2) {
            Object putIfAbsent;
            synchronized (this.mutex) {
                putIfAbsent = this.m.putIfAbsent(obj, obj2);
            }
            return putIfAbsent;
        }

        @Override // java.util.Map
        public boolean remove(Object obj, Object obj2) {
            boolean remove;
            synchronized (this.mutex) {
                remove = this.m.remove(obj, obj2);
            }
            return remove;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.mutex) {
                objectOutputStream.defaultWriteObject();
                throw null;
            }
        }
    }

    public static Iterator emptyIterator() {
        return EmptyIterator.EMPTY_ITERATOR;
    }

    /* access modifiers changed from: private */
    public static class EmptyIterator implements Iterator {
        static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        private EmptyIterator() {
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    public static ListIterator emptyListIterator() {
        return EmptyListIterator.EMPTY_ITERATOR;
    }

    /* access modifiers changed from: private */
    public static class EmptyListIterator extends EmptyIterator implements ListIterator {
        static final EmptyListIterator EMPTY_ITERATOR = new EmptyListIterator();

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        private EmptyListIterator() {
            super();
        }

        @Override // java.util.ListIterator
        public Object previous() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException();
        }
    }

    public static Enumeration emptyEnumeration() {
        return EmptyEnumeration.EMPTY_ENUMERATION;
    }

    /* access modifiers changed from: private */
    public static class EmptyEnumeration implements Enumeration {
        static final EmptyEnumeration EMPTY_ENUMERATION = new EmptyEnumeration();

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        private EmptyEnumeration() {
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            throw new NoSuchElementException();
        }
    }

    public static final Set emptySet() {
        return EMPTY_SET;
    }

    private static class EmptySet extends AbstractSet implements Serializable {
        private static final long serialVersionUID = 1582296315990362920L;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return new Object[0];
        }

        private EmptySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            return Collections.emptyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection collection) {
            return collection.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray(Object[] objArr) {
            if (objArr.length > 0) {
                objArr[0] = null;
            }
            return objArr;
        }

        private Object readResolve() {
            return Collections.EMPTY_SET;
        }
    }

    public static final List emptyList() {
        return EMPTY_LIST;
    }

    private static class EmptyList extends AbstractList implements RandomAccess, Serializable {
        private static final long serialVersionUID = 8842843931221139166L;

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return false;
        }

        @Override // java.util.List, java.util.Collection, java.util.AbstractList
        public int hashCode() {
            return 1;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean isEmpty() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return 0;
        }

        @Override // java.util.List
        public void sort(Comparator comparator) {
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public Object[] toArray() {
            return new Object[0];
        }

        private EmptyList() {
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator iterator() {
            return Collections.emptyIterator();
        }

        @Override // java.util.List, java.util.AbstractList
        public ListIterator listIterator() {
            return Collections.emptyListIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection collection) {
            return collection.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public Object[] toArray(Object[] objArr) {
            if (objArr.length > 0) {
                objArr[0] = null;
            }
            return objArr;
        }

        @Override // java.util.List, java.util.AbstractList
        public Object get(int i) {
            throw new IndexOutOfBoundsException("Index: " + i);
        }

        @Override // java.util.List, java.util.Collection, java.util.AbstractList
        public boolean equals(Object obj) {
            return (obj instanceof List) && ((List) obj).isEmpty();
        }

        private Object readResolve() {
            return Collections.EMPTY_LIST;
        }
    }

    public static final Map emptyMap() {
        return EMPTY_MAP;
    }

    private static class EmptyMap extends AbstractMap implements Serializable {
        private static final long serialVersionUID = 6428348081105594320L;

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return false;
        }

        @Override // java.util.AbstractMap
        public boolean containsValue(Object obj) {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Object get(Object obj) {
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return 0;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return true;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return 0;
        }

        private EmptyMap() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set keySet() {
            return Collections.emptySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection values() {
            return Collections.emptySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set entrySet() {
            return Collections.emptySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean equals(Object obj) {
            return (obj instanceof Map) && ((Map) obj).isEmpty();
        }

        @Override // java.util.Map
        public Object putIfAbsent(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        private Object readResolve() {
            return Collections.EMPTY_MAP;
        }
    }

    public static Set singleton(Object obj) {
        return new SingletonSet(obj);
    }

    static Iterator singletonIterator(final Object obj) {
        return new Iterator() {
            /* class java.util.Collections.AnonymousClass1 */
            private boolean hasNext = true;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.hasNext;
            }

            @Override // java.util.Iterator
            public Object next() {
                if (this.hasNext) {
                    this.hasNext = false;
                    return Object.this;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* access modifiers changed from: private */
    public static class SingletonSet extends AbstractSet implements Serializable {
        private static final long serialVersionUID = 3193687207550431679L;
        private final Object element;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 1;
        }

        SingletonSet(Object obj) {
            this.element = obj;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            return Collections.singletonIterator(this.element);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return Collections.eq(obj, this.element);
        }
    }

    public static List singletonList(Object obj) {
        return new SingletonList(obj);
    }

    private static class SingletonList extends AbstractList implements RandomAccess, Serializable {
        private static final long serialVersionUID = 3093736618740652951L;
        private final Object element;

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            return 1;
        }

        @Override // java.util.List
        public void sort(Comparator comparator) {
        }

        SingletonList(Object obj) {
            this.element = obj;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator iterator() {
            return Collections.singletonIterator(this.element);
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean contains(Object obj) {
            return Collections.eq(obj, this.element);
        }

        @Override // java.util.List, java.util.AbstractList
        public Object get(int i) {
            if (i == 0) {
                return this.element;
            }
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: 1");
        }
    }

    public static Map singletonMap(Object obj, Object obj2) {
        return new SingletonMap(obj, obj2);
    }

    private static class SingletonMap extends AbstractMap implements Serializable {
        private static final long serialVersionUID = -6979724477215052911L;
        private transient Set entrySet;
        private final Object k;
        private transient Set keySet;
        private final Object v;
        private transient Collection values;

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return 1;
        }

        SingletonMap(Object obj, Object obj2) {
            this.k = obj;
            this.v = obj2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return Collections.eq(obj, this.k);
        }

        @Override // java.util.AbstractMap
        public boolean containsValue(Object obj) {
            return Collections.eq(obj, this.v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Object get(Object obj) {
            if (Collections.eq(obj, this.k)) {
                return this.v;
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set keySet() {
            if (this.keySet == null) {
                this.keySet = Collections.singleton(this.k);
            }
            return this.keySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set entrySet() {
            if (this.entrySet == null) {
                this.entrySet = Collections.singleton(new AbstractMap.SimpleImmutableEntry(this.k, this.v));
            }
            return this.entrySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection values() {
            if (this.values == null) {
                this.values = Collections.singleton(this.v);
            }
            return this.values;
        }

        @Override // java.util.Map
        public Object putIfAbsent(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }
    }

    public static Enumeration enumeration(final Collection collection) {
        return new Enumeration() {
            /* class java.util.Collections.AnonymousClass3 */
            private final Iterator i = Collection.this.iterator();

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.i.hasNext();
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                return this.i.next();
            }
        };
    }

    public static ArrayList list(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }

    static boolean eq(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static boolean addAll(Collection collection, Object... objArr) {
        boolean z = false;
        for (Object obj : objArr) {
            z |= collection.add(obj);
        }
        return z;
    }

    public static Set newSetFromMap(Map map) {
        return new SetFromMap(map);
    }

    /* access modifiers changed from: private */
    public static class SetFromMap extends AbstractSet implements Set, Serializable {
        private static final long serialVersionUID = 2454657854757543876L;
        private final Map m;
        private transient Set s;

        SetFromMap(Map map) {
            if (map.isEmpty()) {
                this.m = map;
                this.s = map.keySet();
                return;
            }
            throw new IllegalArgumentException("Map is non-empty");
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.m.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.m.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.m.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return this.m.remove(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Object obj) {
            return this.m.put(obj, Boolean.TRUE) == null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            return this.s.iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return this.s.toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray(Object[] objArr) {
            return this.s.toArray(objArr);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.s.toString();
        }

        @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
        public int hashCode() {
            return this.s.hashCode();
        }

        @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean equals(Object obj) {
            return obj == this || this.s.equals(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection collection) {
            return this.s.containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean removeAll(Collection collection) {
            return this.s.removeAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection collection) {
            return this.s.retainAll(collection);
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            throw null;
        }
    }
}
