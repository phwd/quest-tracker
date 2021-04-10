package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public final class Synchronized {
    private Synchronized() {
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedObject implements Serializable {
        @GwtIncompatible("not needed in emulated source")
        private static final long serialVersionUID = 0;
        final Object delegate;
        final Object mutex;

        SynchronizedObject(Object delegate2, @Nullable Object mutex2) {
            this.delegate = Preconditions.checkNotNull(delegate2);
            this.mutex = mutex2 == null ? this : mutex2;
        }

        /* access modifiers changed from: package-private */
        public Object delegate() {
            return this.delegate;
        }

        public String toString() {
            String obj;
            synchronized (this.mutex) {
                obj = this.delegate.toString();
            }
            return obj;
        }

        @GwtIncompatible("java.io.ObjectOutputStream")
        private void writeObject(ObjectOutputStream stream) throws IOException {
            synchronized (this.mutex) {
                stream.defaultWriteObject();
            }
        }
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> collection(Collection<E> collection, @Nullable Object mutex) {
        return new SynchronizedCollection(collection, mutex);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class SynchronizedCollection<E> extends SynchronizedObject implements Collection<E> {
        private static final long serialVersionUID = 0;

        private SynchronizedCollection(Collection<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Collection<E> delegate() {
            return (Collection) super.delegate();
        }

        @Override // java.util.Collection
        public boolean add(E e) {
            boolean add;
            synchronized (this.mutex) {
                add = delegate().add(e);
            }
            return add;
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends E> c) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(c);
            }
            return addAll;
        }

        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public boolean contains(Object o) {
            boolean contains;
            synchronized (this.mutex) {
                contains = delegate().contains(o);
            }
            return contains;
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<?> c) {
            boolean containsAll;
            synchronized (this.mutex) {
                containsAll = delegate().containsAll(c);
            }
            return containsAll;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return delegate().iterator();
        }

        public boolean remove(Object o) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(o);
            }
            return remove;
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> c) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(c);
            }
            return removeAll;
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection<?> c) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = delegate().retainAll(c);
            }
            return retainAll;
        }

        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        public Object[] toArray() {
            Object[] array;
            synchronized (this.mutex) {
                array = delegate().toArray();
            }
            return array;
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] a) {
            T[] tArr;
            synchronized (this.mutex) {
                tArr = (T[]) delegate().toArray(a);
            }
            return tArr;
        }
    }

    @VisibleForTesting
    static <E> Set<E> set(Set<E> set, @Nullable Object mutex) {
        return new SynchronizedSet(set, mutex);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSet(Set<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedCollection
        public Set<E> delegate() {
            return (Set) super.delegate();
        }

        public boolean equals(Object o) {
            boolean equals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(o);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    /* access modifiers changed from: private */
    public static <E> SortedSet<E> sortedSet(SortedSet<E> set, @Nullable Object mutex) {
        return new SynchronizedSortedSet(set, mutex);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSet(SortedSet<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedSet
        public SortedSet<E> delegate() {
            return (SortedSet) super.delegate();
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            SortedSet<E> sortedSet;
            synchronized (this.mutex) {
                sortedSet = Synchronized.sortedSet(delegate().subSet(fromElement, toElement), this.mutex);
            }
            return sortedSet;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E toElement) {
            SortedSet<E> sortedSet;
            synchronized (this.mutex) {
                sortedSet = Synchronized.sortedSet(delegate().headSet(toElement), this.mutex);
            }
            return sortedSet;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E fromElement) {
            SortedSet<E> sortedSet;
            synchronized (this.mutex) {
                sortedSet = Synchronized.sortedSet(delegate().tailSet(fromElement), this.mutex);
            }
            return sortedSet;
        }

        @Override // java.util.SortedSet
        public E first() {
            E first;
            synchronized (this.mutex) {
                first = delegate().first();
            }
            return first;
        }

        @Override // java.util.SortedSet
        public E last() {
            E last;
            synchronized (this.mutex) {
                last = delegate().last();
            }
            return last;
        }
    }

    /* access modifiers changed from: private */
    public static <E> List<E> list(List<E> list, @Nullable Object mutex) {
        if (list instanceof RandomAccess) {
            return new SynchronizedRandomAccessList(list, mutex);
        }
        return new SynchronizedList(list, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {
        private static final long serialVersionUID = 0;

        SynchronizedList(List<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedCollection
        public List<E> delegate() {
            return (List) super.delegate();
        }

        @Override // java.util.List
        public void add(int index, E element) {
            synchronized (this.mutex) {
                delegate().add(index, element);
            }
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends E> c) {
            boolean addAll;
            synchronized (this.mutex) {
                addAll = delegate().addAll(index, c);
            }
            return addAll;
        }

        @Override // java.util.List
        public E get(int index) {
            E e;
            synchronized (this.mutex) {
                e = delegate().get(index);
            }
            return e;
        }

        public int indexOf(Object o) {
            int indexOf;
            synchronized (this.mutex) {
                indexOf = delegate().indexOf(o);
            }
            return indexOf;
        }

        public int lastIndexOf(Object o) {
            int lastIndexOf;
            synchronized (this.mutex) {
                lastIndexOf = delegate().lastIndexOf(o);
            }
            return lastIndexOf;
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return delegate().listIterator();
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int index) {
            return delegate().listIterator(index);
        }

        @Override // java.util.List
        public E remove(int index) {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove(index);
            }
            return remove;
        }

        @Override // java.util.List
        public E set(int index, E element) {
            E e;
            synchronized (this.mutex) {
                e = delegate().set(index, element);
            }
            return e;
        }

        @Override // java.util.List
        public List<E> subList(int fromIndex, int toIndex) {
            List<E> list;
            synchronized (this.mutex) {
                list = Synchronized.list(delegate().subList(fromIndex, toIndex), this.mutex);
            }
            return list;
        }

        public boolean equals(Object o) {
            boolean equals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(o);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    /* access modifiers changed from: private */
    public static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {
        private static final long serialVersionUID = 0;

        SynchronizedRandomAccessList(List<E> list, @Nullable Object mutex) {
            super(list, mutex);
        }
    }

    static <E> Multiset<E> multiset(Multiset<E> multiset, @Nullable Object mutex) {
        if ((multiset instanceof SynchronizedMultiset) || (multiset instanceof ImmutableMultiset)) {
            return multiset;
        }
        return new SynchronizedMultiset(multiset, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedMultiset<E> extends SynchronizedCollection<E> implements Multiset<E> {
        private static final long serialVersionUID = 0;
        transient Set<E> elementSet;
        transient Set<Multiset.Entry<E>> entrySet;

        SynchronizedMultiset(Multiset<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedCollection
        public Multiset<E> delegate() {
            return (Multiset) super.delegate();
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object o) {
            int count;
            synchronized (this.mutex) {
                count = delegate().count(o);
            }
            return count;
        }

        @Override // com.google.common.collect.Multiset
        public int add(E e, int n) {
            int add;
            synchronized (this.mutex) {
                add = delegate().add(e, n);
            }
            return add;
        }

        @Override // com.google.common.collect.Multiset
        public int remove(Object o, int n) {
            int remove;
            synchronized (this.mutex) {
                remove = delegate().remove(o, n);
            }
            return remove;
        }

        @Override // com.google.common.collect.Multiset
        public int setCount(E element, int count) {
            int count2;
            synchronized (this.mutex) {
                count2 = delegate().setCount(element, count);
            }
            return count2;
        }

        @Override // com.google.common.collect.Multiset
        public boolean setCount(E element, int oldCount, int newCount) {
            boolean count;
            synchronized (this.mutex) {
                count = delegate().setCount(element, oldCount, newCount);
            }
            return count;
        }

        @Override // com.google.common.collect.Multiset
        public Set<E> elementSet() {
            Set<E> set;
            synchronized (this.mutex) {
                if (this.elementSet == null) {
                    this.elementSet = Synchronized.typePreservingSet(delegate().elementSet(), this.mutex);
                }
                set = this.elementSet;
            }
            return set;
        }

        @Override // com.google.common.collect.Multiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = Synchronized.typePreservingSet(delegate().entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // com.google.common.collect.Multiset
        public boolean equals(Object o) {
            boolean equals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(o);
            }
            return equals;
        }

        @Override // com.google.common.collect.Multiset
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    static <K, V> Multimap<K, V> multimap(Multimap<K, V> multimap, @Nullable Object mutex) {
        if ((multimap instanceof SynchronizedMultimap) || (multimap instanceof ImmutableMultimap)) {
            return multimap;
        }
        return new SynchronizedMultimap(multimap, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedMultimap<K, V> extends SynchronizedObject implements Multimap<K, V> {
        private static final long serialVersionUID = 0;
        transient Map<K, Collection<V>> asMap;
        transient Collection<Map.Entry<K, V>> entries;
        transient Set<K> keySet;
        transient Multiset<K> keys;
        transient Collection<V> valuesCollection;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Multimap<K, V> delegate() {
            return (Multimap) super.delegate();
        }

        SynchronizedMultimap(Multimap<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // com.google.common.collect.Multimap
        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object key) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(key);
            }
            return containsKey;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsValue(Object value) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(value);
            }
            return containsValue;
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsEntry(Object key, Object value) {
            boolean containsEntry;
            synchronized (this.mutex) {
                containsEntry = delegate().containsEntry(key, value);
            }
            return containsEntry;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V> get(K key) {
            Collection<V> typePreservingCollection;
            synchronized (this.mutex) {
                typePreservingCollection = Synchronized.typePreservingCollection(delegate().get(key), this.mutex);
            }
            return typePreservingCollection;
        }

        @Override // com.google.common.collect.Multimap
        public boolean put(K key, V value) {
            boolean put;
            synchronized (this.mutex) {
                put = delegate().put(key, value);
            }
            return put;
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(K key, Iterable<? extends V> values) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(key, values);
            }
            return putAll;
        }

        @Override // com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean putAll;
            synchronized (this.mutex) {
                putAll = delegate().putAll(multimap);
            }
            return putAll;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V> replaceValues(K key, Iterable<? extends V> values) {
            Collection<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues(key, values);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.Multimap
        public boolean remove(Object key, Object value) {
            boolean remove;
            synchronized (this.mutex) {
                remove = delegate().remove(key, value);
            }
            return remove;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V> removeAll(Object key) {
            Collection<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(key);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        @Override // com.google.common.collect.Multimap
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = Synchronized.typePreservingSet(delegate().keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                if (this.valuesCollection == null) {
                    this.valuesCollection = Synchronized.collection(delegate().values(), this.mutex);
                }
                collection = this.valuesCollection;
            }
            return collection;
        }

        @Override // com.google.common.collect.Multimap
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection;
            synchronized (this.mutex) {
                if (this.entries == null) {
                    this.entries = Synchronized.typePreservingCollection(delegate().entries(), this.mutex);
                }
                collection = this.entries;
            }
            return collection;
        }

        @Override // com.google.common.collect.Multimap
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map;
            synchronized (this.mutex) {
                if (this.asMap == null) {
                    this.asMap = new SynchronizedAsMap(delegate().asMap(), this.mutex);
                }
                map = this.asMap;
            }
            return map;
        }

        @Override // com.google.common.collect.Multimap
        public Multiset<K> keys() {
            Multiset<K> multiset;
            synchronized (this.mutex) {
                if (this.keys == null) {
                    this.keys = Synchronized.multiset(delegate().keys(), this.mutex);
                }
                multiset = this.keys;
            }
            return multiset;
        }

        @Override // com.google.common.collect.Multimap
        public boolean equals(Object o) {
            boolean equals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(o);
            }
            return equals;
        }

        @Override // com.google.common.collect.Multimap
        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    static <K, V> ListMultimap<K, V> listMultimap(ListMultimap<K, V> multimap, @Nullable Object mutex) {
        if ((multimap instanceof SynchronizedListMultimap) || (multimap instanceof ImmutableListMultimap)) {
            return multimap;
        }
        return new SynchronizedListMultimap(multimap, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedListMultimap<K, V> extends SynchronizedMultimap<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedListMultimap(ListMultimap<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap
        public ListMultimap<K, V> delegate() {
            return (ListMultimap) super.delegate();
        }

        @Override // com.google.common.collect.ListMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap
        public List<V> get(K key) {
            List<V> list;
            synchronized (this.mutex) {
                list = Synchronized.list(delegate().get((Object) key), this.mutex);
            }
            return list;
        }

        @Override // com.google.common.collect.ListMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap
        public List<V> removeAll(Object key) {
            List<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(key);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.ListMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap
        public List<V> replaceValues(K key, Iterable<? extends V> values) {
            List<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((Object) key, (Iterable) values);
            }
            return replaceValues;
        }
    }

    static <K, V> SetMultimap<K, V> setMultimap(SetMultimap<K, V> multimap, @Nullable Object mutex) {
        if ((multimap instanceof SynchronizedSetMultimap) || (multimap instanceof ImmutableSetMultimap)) {
            return multimap;
        }
        return new SynchronizedSetMultimap(multimap, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedSetMultimap<K, V> extends SynchronizedMultimap<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, V>> entrySet;

        SynchronizedSetMultimap(SetMultimap<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap
        public SetMultimap<K, V> delegate() {
            return (SetMultimap) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<V> get(K key) {
            Set<V> set;
            synchronized (this.mutex) {
                set = Synchronized.set(delegate().get((Object) key), this.mutex);
            }
            return set;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<V> removeAll(Object key) {
            Set<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(key);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<V> replaceValues(K key, Iterable<? extends V> values) {
            Set<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((Object) key, (Iterable) values);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<Map.Entry<K, V>> entries() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = Synchronized.set(delegate().entries(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }
    }

    static <K, V> SortedSetMultimap<K, V> sortedSetMultimap(SortedSetMultimap<K, V> multimap, @Nullable Object mutex) {
        if (multimap instanceof SynchronizedSortedSetMultimap) {
            return multimap;
        }
        return new SynchronizedSortedSetMultimap(multimap, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedSortedSetMultimap<K, V> extends SynchronizedSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSetMultimap(SortedSetMultimap<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap
        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) super.delegate();
        }

        @Override // com.google.common.collect.SortedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public SortedSet<V> get(K key) {
            SortedSet<V> sortedSet;
            synchronized (this.mutex) {
                sortedSet = Synchronized.sortedSet(delegate().get((Object) key), this.mutex);
            }
            return sortedSet;
        }

        @Override // com.google.common.collect.SortedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public SortedSet<V> removeAll(Object key) {
            SortedSet<V> removeAll;
            synchronized (this.mutex) {
                removeAll = delegate().removeAll(key);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.SortedSetMultimap, com.google.common.collect.Synchronized.SynchronizedMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Synchronized.SynchronizedSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public SortedSet<V> replaceValues(K key, Iterable<? extends V> values) {
            SortedSet<V> replaceValues;
            synchronized (this.mutex) {
                replaceValues = delegate().replaceValues((Object) key, (Iterable) values);
            }
            return replaceValues;
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            Comparator<? super V> valueComparator;
            synchronized (this.mutex) {
                valueComparator = delegate().valueComparator();
            }
            return valueComparator;
        }
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> typePreservingCollection(Collection<E> collection, @Nullable Object mutex) {
        if (collection instanceof SortedSet) {
            return sortedSet((SortedSet) collection, mutex);
        }
        if (collection instanceof Set) {
            return set((Set) collection, mutex);
        }
        if (collection instanceof List) {
            return list((List) collection, mutex);
        }
        return collection(collection, mutex);
    }

    /* access modifiers changed from: private */
    public static <E> Set<E> typePreservingSet(Set<E> set, @Nullable Object mutex) {
        if (set instanceof SortedSet) {
            return sortedSet((SortedSet) set, mutex);
        }
        return set(set, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedAsMapEntries<K, V> extends SynchronizedSet<Map.Entry<K, Collection<V>>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapEntries(Set<Map.Entry<K, Collection<V>>> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> iterator = super.iterator();
            return new ForwardingIterator<Map.Entry<K, Collection<V>>>() {
                /* class com.google.common.collect.Synchronized.SynchronizedAsMapEntries.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
                public Iterator<Map.Entry<K, Collection<V>>> delegate() {
                    return iterator;
                }

                @Override // com.google.common.collect.ForwardingIterator, java.util.Iterator
                public Map.Entry<K, Collection<V>> next() {
                    final Map.Entry<K, Collection<V>> entry = (Map.Entry) super.next();
                    return new ForwardingMapEntry<K, Collection<V>>() {
                        /* class com.google.common.collect.Synchronized.SynchronizedAsMapEntries.AnonymousClass1.AnonymousClass1 */

                        /* access modifiers changed from: protected */
                        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingMapEntry
                        public Map.Entry<K, Collection<V>> delegate() {
                            return entry;
                        }

                        @Override // java.util.Map.Entry, com.google.common.collect.ForwardingMapEntry
                        public Collection<V> getValue() {
                            return Synchronized.typePreservingCollection((Collection) entry.getValue(), SynchronizedAsMapEntries.this.mutex);
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection
        public Object[] toArray() {
            Object[] arrayImpl;
            synchronized (this.mutex) {
                arrayImpl = ObjectArrays.toArrayImpl(delegate());
            }
            return arrayImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] array) {
            T[] tArr;
            synchronized (this.mutex) {
                tArr = (T[]) ObjectArrays.toArrayImpl(delegate(), array);
            }
            return tArr;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection
        public boolean contains(Object o) {
            boolean containsEntryImpl;
            synchronized (this.mutex) {
                containsEntryImpl = Maps.containsEntryImpl(delegate(), o);
            }
            return containsEntryImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            boolean containsAllImpl;
            synchronized (this.mutex) {
                containsAllImpl = Collections2.containsAllImpl(delegate(), c);
            }
            return containsAllImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSet
        public boolean equals(Object o) {
            boolean equalsImpl;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                equalsImpl = Sets.equalsImpl(delegate(), o);
            }
            return equalsImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection
        public boolean remove(Object o) {
            boolean removeEntryImpl;
            synchronized (this.mutex) {
                removeEntryImpl = Maps.removeEntryImpl(delegate(), o);
            }
            return removeEntryImpl;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> c) {
            boolean removeAll;
            synchronized (this.mutex) {
                removeAll = Iterators.removeAll(delegate().iterator(), c);
            }
            return removeAll;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            boolean retainAll;
            synchronized (this.mutex) {
                retainAll = Iterators.retainAll(delegate().iterator(), c);
            }
            return retainAll;
        }
    }

    @VisibleForTesting
    static <K, V> Map<K, V> map(Map<K, V> map, @Nullable Object mutex) {
        return new SynchronizedMap(map, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedMap<K, V> extends SynchronizedObject implements Map<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, V>> entrySet;
        transient Set<K> keySet;
        transient Collection<V> values;

        SynchronizedMap(Map<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Map<K, V> delegate() {
            return (Map) super.delegate();
        }

        public void clear() {
            synchronized (this.mutex) {
                delegate().clear();
            }
        }

        public boolean containsKey(Object key) {
            boolean containsKey;
            synchronized (this.mutex) {
                containsKey = delegate().containsKey(key);
            }
            return containsKey;
        }

        public boolean containsValue(Object value) {
            boolean containsValue;
            synchronized (this.mutex) {
                containsValue = delegate().containsValue(value);
            }
            return containsValue;
        }

        @Override // java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.mutex) {
                if (this.entrySet == null) {
                    this.entrySet = Synchronized.set(delegate().entrySet(), this.mutex);
                }
                set = this.entrySet;
            }
            return set;
        }

        @Override // java.util.Map
        public V get(Object key) {
            V v;
            synchronized (this.mutex) {
                v = delegate().get(key);
            }
            return v;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.mutex) {
                isEmpty = delegate().isEmpty();
            }
            return isEmpty;
        }

        @Override // java.util.Map
        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.mutex) {
                if (this.keySet == null) {
                    this.keySet = Synchronized.set(delegate().keySet(), this.mutex);
                }
                set = this.keySet;
            }
            return set;
        }

        @Override // java.util.Map
        public V put(K key, V value) {
            V put;
            synchronized (this.mutex) {
                put = delegate().put(key, value);
            }
            return put;
        }

        @Override // java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.mutex) {
                delegate().putAll(map);
            }
        }

        @Override // java.util.Map
        public V remove(Object key) {
            V remove;
            synchronized (this.mutex) {
                remove = delegate().remove(key);
            }
            return remove;
        }

        public int size() {
            int size;
            synchronized (this.mutex) {
                size = delegate().size();
            }
            return size;
        }

        @Override // java.util.Map
        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.mutex) {
                if (this.values == null) {
                    this.values = Synchronized.collection(delegate().values(), this.mutex);
                }
                collection = this.values;
            }
            return collection;
        }

        public boolean equals(Object o) {
            boolean equals;
            if (o == this) {
                return true;
            }
            synchronized (this.mutex) {
                equals = delegate().equals(o);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }
    }

    static <K, V> SortedMap<K, V> sortedMap(SortedMap<K, V> sortedMap, @Nullable Object mutex) {
        return new SynchronizedSortedMap(sortedMap, mutex);
    }

    /* access modifiers changed from: package-private */
    public static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedMap(SortedMap<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public SortedMap<K, V> delegate() {
            return (SortedMap) super.delegate();
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.mutex) {
                comparator = delegate().comparator();
            }
            return comparator;
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            K firstKey;
            synchronized (this.mutex) {
                firstKey = delegate().firstKey();
            }
            return firstKey;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            SortedMap<K, V> sortedMap;
            synchronized (this.mutex) {
                sortedMap = Synchronized.sortedMap(delegate().headMap(toKey), this.mutex);
            }
            return sortedMap;
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            K lastKey;
            synchronized (this.mutex) {
                lastKey = delegate().lastKey();
            }
            return lastKey;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            SortedMap<K, V> sortedMap;
            synchronized (this.mutex) {
                sortedMap = Synchronized.sortedMap(delegate().subMap(fromKey, toKey), this.mutex);
            }
            return sortedMap;
        }

        @Override // java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            SortedMap<K, V> sortedMap;
            synchronized (this.mutex) {
                sortedMap = Synchronized.sortedMap(delegate().tailMap(fromKey), this.mutex);
            }
            return sortedMap;
        }
    }

    static <K, V> BiMap<K, V> biMap(BiMap<K, V> bimap, @Nullable Object mutex) {
        if ((bimap instanceof SynchronizedBiMap) || (bimap instanceof ImmutableBiMap)) {
            return bimap;
        }
        return new SynchronizedBiMap(bimap, mutex, null);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class SynchronizedBiMap<K, V> extends SynchronizedMap<K, V> implements BiMap<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        private transient BiMap<V, K> inverse;
        private transient Set<V> valueSet;

        private SynchronizedBiMap(BiMap<K, V> delegate, @Nullable Object mutex, @Nullable BiMap<V, K> inverse2) {
            super(delegate, mutex);
            this.inverse = inverse2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public BiMap<K, V> delegate() {
            return (BiMap) super.delegate();
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.BiMap, java.util.Map
        public Set<V> values() {
            Set<V> set;
            synchronized (this.mutex) {
                if (this.valueSet == null) {
                    this.valueSet = Synchronized.set(delegate().values(), this.mutex);
                }
                set = this.valueSet;
            }
            return set;
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(K key, V value) {
            V forcePut;
            synchronized (this.mutex) {
                forcePut = delegate().forcePut(key, value);
            }
            return forcePut;
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap;
            synchronized (this.mutex) {
                if (this.inverse == null) {
                    this.inverse = new SynchronizedBiMap(delegate().inverse(), this.mutex, this);
                }
                biMap = this.inverse;
            }
            return biMap;
        }
    }

    private static class SynchronizedAsMap<K, V> extends SynchronizedMap<K, Collection<V>> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, Collection<V>>> asMapEntrySet;
        transient Collection<Collection<V>> asMapValues;

        SynchronizedAsMap(Map<K, Collection<V>> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<V> get(Object key) {
            Collection<V> collection;
            synchronized (this.mutex) {
                Collection<V> collection2 = (Collection) super.get(key);
                if (collection2 == null) {
                    collection = null;
                } else {
                    collection = Synchronized.typePreservingCollection(collection2, this.mutex);
                }
            }
            return collection;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            Set<Map.Entry<K, Collection<V>>> set;
            synchronized (this.mutex) {
                if (this.asMapEntrySet == null) {
                    this.asMapEntrySet = new SynchronizedAsMapEntries(delegate().entrySet(), this.mutex);
                }
                set = this.asMapEntrySet;
            }
            return set;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map
        public Collection<Collection<V>> values() {
            Collection<Collection<V>> collection;
            synchronized (this.mutex) {
                if (this.asMapValues == null) {
                    this.asMapValues = new SynchronizedAsMapValues(delegate().values(), this.mutex);
                }
                collection = this.asMapValues;
            }
            return collection;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap
        public boolean containsValue(Object o) {
            return values().contains(o);
        }
    }

    /* access modifiers changed from: private */
    public static class SynchronizedAsMapValues<V> extends SynchronizedCollection<Collection<V>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapValues(Collection<Collection<V>> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Collection<V>> iterator() {
            final Iterator<Collection<V>> iterator = super.iterator();
            return new ForwardingIterator<Collection<V>>() {
                /* class com.google.common.collect.Synchronized.SynchronizedAsMapValues.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
                public Iterator<Collection<V>> delegate() {
                    return iterator;
                }

                @Override // com.google.common.collect.ForwardingIterator, java.util.Iterator
                public Collection<V> next() {
                    return Synchronized.typePreservingCollection((Collection) super.next(), SynchronizedAsMapValues.this.mutex);
                }
            };
        }
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible("NavigableSet")
    @VisibleForTesting
    public static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {
        private static final long serialVersionUID = 0;
        transient NavigableSet<E> descendingSet;

        SynchronizedNavigableSet(NavigableSet<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedSet, com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSortedSet, com.google.common.collect.Synchronized.SynchronizedSortedSet
        public NavigableSet<E> delegate() {
            return (NavigableSet) super.delegate();
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            E ceiling;
            synchronized (this.mutex) {
                ceiling = delegate().ceiling(e);
            }
            return ceiling;
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return delegate().descendingIterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            synchronized (this.mutex) {
                if (this.descendingSet == null) {
                    NavigableSet<E> dS = Synchronized.navigableSet(delegate().descendingSet(), this.mutex);
                    this.descendingSet = dS;
                    return dS;
                }
                return this.descendingSet;
            }
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            E floor;
            synchronized (this.mutex) {
                floor = delegate().floor(e);
            }
            return floor;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E toElement, boolean inclusive) {
            NavigableSet<E> navigableSet;
            synchronized (this.mutex) {
                navigableSet = Synchronized.navigableSet(delegate().headSet(toElement, inclusive), this.mutex);
            }
            return navigableSet;
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            E higher;
            synchronized (this.mutex) {
                higher = delegate().higher(e);
            }
            return higher;
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            E lower;
            synchronized (this.mutex) {
                lower = delegate().lower(e);
            }
            return lower;
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
            NavigableSet<E> navigableSet;
            synchronized (this.mutex) {
                navigableSet = Synchronized.navigableSet(delegate().subSet(fromElement, fromInclusive, toElement, toInclusive), this.mutex);
            }
            return navigableSet;
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
            NavigableSet<E> navigableSet;
            synchronized (this.mutex) {
                navigableSet = Synchronized.navigableSet(delegate().tailSet(fromElement, inclusive), this.mutex);
            }
            return navigableSet;
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, com.google.common.collect.Synchronized.SynchronizedSortedSet
        public SortedSet<E> headSet(E toElement) {
            return headSet(toElement, false);
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, com.google.common.collect.Synchronized.SynchronizedSortedSet
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return subSet(fromElement, true, toElement, false);
        }

        @Override // java.util.SortedSet, java.util.NavigableSet, com.google.common.collect.Synchronized.SynchronizedSortedSet
        public SortedSet<E> tailSet(E fromElement) {
            return tailSet(fromElement, true);
        }
    }

    @GwtIncompatible("NavigableSet")
    static <E> NavigableSet<E> navigableSet(NavigableSet<E> navigableSet, @Nullable Object mutex) {
        return new SynchronizedNavigableSet(navigableSet, mutex);
    }

    @GwtIncompatible("NavigableSet")
    static <E> NavigableSet<E> navigableSet(NavigableSet<E> navigableSet) {
        return navigableSet(navigableSet, null);
    }

    @GwtIncompatible("NavigableMap")
    static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> navigableMap) {
        return navigableMap(navigableMap, null);
    }

    @GwtIncompatible("NavigableMap")
    static <K, V> NavigableMap<K, V> navigableMap(NavigableMap<K, V> navigableMap, @Nullable Object mutex) {
        return new SynchronizedNavigableMap(navigableMap, mutex);
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible("NavigableMap")
    @VisibleForTesting
    public static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {
        private static final long serialVersionUID = 0;
        transient NavigableSet<K> descendingKeySet;
        transient NavigableMap<K, V> descendingMap;
        transient NavigableSet<K> navigableKeySet;

        SynchronizedNavigableMap(NavigableMap<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedMap, com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedSortedMap, com.google.common.collect.Synchronized.SynchronizedObject
        public NavigableMap<K, V> delegate() {
            return (NavigableMap) super.delegate();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().ceilingEntry(key), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            K ceilingKey;
            synchronized (this.mutex) {
                ceilingKey = delegate().ceilingKey(key);
            }
            return ceilingKey;
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            synchronized (this.mutex) {
                if (this.descendingKeySet == null) {
                    NavigableSet<K> navigableSet = Synchronized.navigableSet(delegate().descendingKeySet(), this.mutex);
                    this.descendingKeySet = navigableSet;
                    return navigableSet;
                }
                return this.descendingKeySet;
            }
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> descendingMap() {
            synchronized (this.mutex) {
                if (this.descendingMap == null) {
                    NavigableMap<K, V> navigableMap = Synchronized.navigableMap(delegate().descendingMap(), this.mutex);
                    this.descendingMap = navigableMap;
                    return navigableMap;
                }
                return this.descendingMap;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().firstEntry(), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().floorEntry(key), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            K floorKey;
            synchronized (this.mutex) {
                floorKey = delegate().floorKey(key);
            }
            return floorKey;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
            NavigableMap<K, V> navigableMap;
            synchronized (this.mutex) {
                navigableMap = Synchronized.navigableMap(delegate().headMap(toKey, inclusive), this.mutex);
            }
            return navigableMap;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().higherEntry(key), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            K higherKey;
            synchronized (this.mutex) {
                higherKey = delegate().higherKey(key);
            }
            return higherKey;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().lastEntry(), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().lowerEntry(key), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            K lowerKey;
            synchronized (this.mutex) {
                lowerKey = delegate().lowerKey(key);
            }
            return lowerKey;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedMap, java.util.Map, java.util.SortedMap
        public Set<K> keySet() {
            return navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            synchronized (this.mutex) {
                if (this.navigableKeySet == null) {
                    NavigableSet<K> navigableSet = Synchronized.navigableSet(delegate().navigableKeySet(), this.mutex);
                    this.navigableKeySet = navigableSet;
                    return navigableSet;
                }
                return this.navigableKeySet;
            }
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().pollFirstEntry(), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> nullableSynchronizedEntry;
            synchronized (this.mutex) {
                nullableSynchronizedEntry = Synchronized.nullableSynchronizedEntry(delegate().pollLastEntry(), this.mutex);
            }
            return nullableSynchronizedEntry;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            NavigableMap<K, V> navigableMap;
            synchronized (this.mutex) {
                navigableMap = Synchronized.navigableMap(delegate().subMap(fromKey, fromInclusive, toKey, toInclusive), this.mutex);
            }
            return navigableMap;
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
            NavigableMap<K, V> navigableMap;
            synchronized (this.mutex) {
                navigableMap = Synchronized.navigableMap(delegate().tailMap(fromKey, inclusive), this.mutex);
            }
            return navigableMap;
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> headMap(K toKey) {
            return headMap(toKey, false);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> subMap(K fromKey, K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // com.google.common.collect.Synchronized.SynchronizedSortedMap, java.util.NavigableMap, java.util.SortedMap
        public SortedMap<K, V> tailMap(K fromKey) {
            return tailMap(fromKey, true);
        }
    }

    /* access modifiers changed from: private */
    @GwtIncompatible("works but is needed only for NavigableMap")
    public static <K, V> Map.Entry<K, V> nullableSynchronizedEntry(@Nullable Map.Entry<K, V> entry, @Nullable Object mutex) {
        if (entry == null) {
            return null;
        }
        return new SynchronizedEntry(entry, mutex);
    }

    /* access modifiers changed from: private */
    @GwtIncompatible("works but is needed only for NavigableMap")
    public static class SynchronizedEntry<K, V> extends SynchronizedObject implements Map.Entry<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedEntry(Map.Entry<K, V> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject
        public Map.Entry<K, V> delegate() {
            return (Map.Entry) super.delegate();
        }

        public boolean equals(Object obj) {
            boolean equals;
            synchronized (this.mutex) {
                equals = delegate().equals(obj);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.mutex) {
                hashCode = delegate().hashCode();
            }
            return hashCode;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            K key;
            synchronized (this.mutex) {
                key = delegate().getKey();
            }
            return key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            V value;
            synchronized (this.mutex) {
                value = delegate().getValue();
            }
            return value;
        }

        @Override // java.util.Map.Entry
        public V setValue(V value) {
            V value2;
            synchronized (this.mutex) {
                value2 = delegate().setValue(value);
            }
            return value2;
        }
    }

    static <E> Queue<E> queue(Queue<E> queue, @Nullable Object mutex) {
        if (queue instanceof SynchronizedQueue) {
            return queue;
        }
        return new SynchronizedQueue(queue, mutex);
    }

    /* access modifiers changed from: private */
    public static class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
        private static final long serialVersionUID = 0;

        SynchronizedQueue(Queue<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedCollection
        public Queue<E> delegate() {
            return (Queue) super.delegate();
        }

        @Override // java.util.Queue
        public E element() {
            E element;
            synchronized (this.mutex) {
                element = delegate().element();
            }
            return element;
        }

        @Override // java.util.Queue
        public boolean offer(E e) {
            boolean offer;
            synchronized (this.mutex) {
                offer = delegate().offer(e);
            }
            return offer;
        }

        @Override // java.util.Queue
        public E peek() {
            E peek;
            synchronized (this.mutex) {
                peek = delegate().peek();
            }
            return peek;
        }

        @Override // java.util.Queue
        public E poll() {
            E poll;
            synchronized (this.mutex) {
                poll = delegate().poll();
            }
            return poll;
        }

        @Override // java.util.Queue
        public E remove() {
            E remove;
            synchronized (this.mutex) {
                remove = delegate().remove();
            }
            return remove;
        }
    }

    @GwtIncompatible("Deque")
    static <E> Deque<E> deque(Deque<E> deque, @Nullable Object mutex) {
        return new SynchronizedDeque(deque, mutex);
    }

    @GwtIncompatible("Deque")
    private static final class SynchronizedDeque<E> extends SynchronizedQueue<E> implements Deque<E> {
        private static final long serialVersionUID = 0;

        SynchronizedDeque(Deque<E> delegate, @Nullable Object mutex) {
            super(delegate, mutex);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedQueue, com.google.common.collect.Synchronized.SynchronizedObject, com.google.common.collect.Synchronized.SynchronizedCollection, com.google.common.collect.Synchronized.SynchronizedCollection
        public Deque<E> delegate() {
            return (Deque) super.delegate();
        }

        @Override // java.util.Deque
        public void addFirst(E e) {
            synchronized (this.mutex) {
                delegate().addFirst(e);
            }
        }

        @Override // java.util.Deque
        public void addLast(E e) {
            synchronized (this.mutex) {
                delegate().addLast(e);
            }
        }

        @Override // java.util.Deque
        public boolean offerFirst(E e) {
            boolean offerFirst;
            synchronized (this.mutex) {
                offerFirst = delegate().offerFirst(e);
            }
            return offerFirst;
        }

        @Override // java.util.Deque
        public boolean offerLast(E e) {
            boolean offerLast;
            synchronized (this.mutex) {
                offerLast = delegate().offerLast(e);
            }
            return offerLast;
        }

        @Override // java.util.Deque
        public E removeFirst() {
            E removeFirst;
            synchronized (this.mutex) {
                removeFirst = delegate().removeFirst();
            }
            return removeFirst;
        }

        @Override // java.util.Deque
        public E removeLast() {
            E removeLast;
            synchronized (this.mutex) {
                removeLast = delegate().removeLast();
            }
            return removeLast;
        }

        @Override // java.util.Deque
        public E pollFirst() {
            E pollFirst;
            synchronized (this.mutex) {
                pollFirst = delegate().pollFirst();
            }
            return pollFirst;
        }

        @Override // java.util.Deque
        public E pollLast() {
            E pollLast;
            synchronized (this.mutex) {
                pollLast = delegate().pollLast();
            }
            return pollLast;
        }

        @Override // java.util.Deque
        public E getFirst() {
            E first;
            synchronized (this.mutex) {
                first = delegate().getFirst();
            }
            return first;
        }

        @Override // java.util.Deque
        public E getLast() {
            E last;
            synchronized (this.mutex) {
                last = delegate().getLast();
            }
            return last;
        }

        @Override // java.util.Deque
        public E peekFirst() {
            E peekFirst;
            synchronized (this.mutex) {
                peekFirst = delegate().peekFirst();
            }
            return peekFirst;
        }

        @Override // java.util.Deque
        public E peekLast() {
            E peekLast;
            synchronized (this.mutex) {
                peekLast = delegate().peekLast();
            }
            return peekLast;
        }

        public boolean removeFirstOccurrence(Object o) {
            boolean removeFirstOccurrence;
            synchronized (this.mutex) {
                removeFirstOccurrence = delegate().removeFirstOccurrence(o);
            }
            return removeFirstOccurrence;
        }

        public boolean removeLastOccurrence(Object o) {
            boolean removeLastOccurrence;
            synchronized (this.mutex) {
                removeLastOccurrence = delegate().removeLastOccurrence(o);
            }
            return removeLastOccurrence;
        }

        @Override // java.util.Deque
        public void push(E e) {
            synchronized (this.mutex) {
                delegate().push(e);
            }
        }

        @Override // java.util.Deque
        public E pop() {
            E pop;
            synchronized (this.mutex) {
                pop = delegate().pop();
            }
            return pop;
        }

        @Override // java.util.Deque
        public Iterator<E> descendingIterator() {
            Iterator<E> descendingIterator;
            synchronized (this.mutex) {
                descendingIterator = delegate().descendingIterator();
            }
            return descendingIterator;
        }
    }
}
