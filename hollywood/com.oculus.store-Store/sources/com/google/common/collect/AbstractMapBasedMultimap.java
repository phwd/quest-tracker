package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    private transient Map<K, Collection<V>> map;
    private transient int totalSize;

    /* access modifiers changed from: package-private */
    public abstract Collection<V> createCollection();

    static /* synthetic */ int access$208(AbstractMapBasedMultimap x0) {
        int i = x0.totalSize;
        x0.totalSize = i + 1;
        return i;
    }

    static /* synthetic */ int access$210(AbstractMapBasedMultimap x0) {
        int i = x0.totalSize;
        x0.totalSize = i - 1;
        return i;
    }

    static /* synthetic */ int access$212(AbstractMapBasedMultimap x0, int x1) {
        int i = x0.totalSize + x1;
        x0.totalSize = i;
        return i;
    }

    static /* synthetic */ int access$220(AbstractMapBasedMultimap x0, int x1) {
        int i = x0.totalSize - x1;
        x0.totalSize = i;
        return i;
    }

    protected AbstractMapBasedMultimap(Map<K, Collection<V>> map2) {
        Preconditions.checkArgument(map2.isEmpty());
        this.map = map2;
    }

    /* access modifiers changed from: package-private */
    public final void setMap(Map<K, Collection<V>> map2) {
        this.map = map2;
        this.totalSize = 0;
        for (Collection<V> values : map2.values()) {
            Preconditions.checkArgument(!values.isEmpty());
            this.totalSize += values.size();
        }
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: java.util.Collection<V>, java.util.Collection<E> */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.Collection<E>, java.util.Collection<V> */
    /* access modifiers changed from: package-private */
    public Collection<V> createUnmodifiableEmptyCollection() {
        return (Collection<E>) unmodifiableCollectionSubclass(createCollection());
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createCollection(@NullableDecl K k) {
        return createCollection();
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> backingMap() {
        return this.map;
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.totalSize;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object key) {
        return this.map.containsKey(key);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean put(@NullableDecl K key, @NullableDecl V value) {
        Collection<V> collection = this.map.get(key);
        if (collection == null) {
            Collection<V> collection2 = createCollection(key);
            if (collection2.add(value)) {
                this.totalSize++;
                this.map.put(key, collection2);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(value)) {
            return false;
        } else {
            this.totalSize++;
            return true;
        }
    }

    private Collection<V> getOrCreateCollection(@NullableDecl K key) {
        Collection<V> collection = this.map.get(key);
        if (collection != null) {
            return collection;
        }
        Collection<V> collection2 = createCollection(key);
        this.map.put(key, collection2);
        return collection2;
    }

    /* JADX DEBUG: Type inference failed for r3v4. Raw type applied. Possible types: java.util.Collection<E>, java.util.Collection<V> */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<V> replaceValues(@org.checkerframework.checker.nullness.compatqual.NullableDecl K r6, java.lang.Iterable<? extends V> r7) {
        /*
            r5 = this;
            java.util.Iterator r1 = r7.iterator()
            boolean r3 = r1.hasNext()
            if (r3 != 0) goto L_0x000f
            java.util.Collection r3 = r5.removeAll(r6)
        L_0x000e:
            return r3
        L_0x000f:
            java.util.Collection r0 = r5.getOrCreateCollection(r6)
            java.util.Collection r2 = r5.createCollection()
            r2.addAll(r0)
            int r3 = r5.totalSize
            int r4 = r0.size()
            int r3 = r3 - r4
            r5.totalSize = r3
            r0.clear()
        L_0x0026:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x003d
            java.lang.Object r3 = r1.next()
            boolean r3 = r0.add(r3)
            if (r3 == 0) goto L_0x0026
            int r3 = r5.totalSize
            int r3 = r3 + 1
            r5.totalSize = r3
            goto L_0x0026
        L_0x003d:
            java.util.Collection r3 = r5.unmodifiableCollectionSubclass(r2)
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.replaceValues(java.lang.Object, java.lang.Iterable):java.util.Collection");
    }

    /* JADX DEBUG: Type inference failed for r2v3. Raw type applied. Possible types: java.util.Collection<E>, java.util.Collection<V> */
    @Override // com.google.common.collect.Multimap
    public Collection<V> removeAll(@NullableDecl Object key) {
        Collection<V> collection = this.map.remove(key);
        if (collection == null) {
            return createUnmodifiableEmptyCollection();
        }
        Collection createCollection = createCollection();
        createCollection.addAll(collection);
        this.totalSize -= collection.size();
        collection.clear();
        return (Collection<E>) unmodifiableCollectionSubclass(createCollection);
    }

    /* access modifiers changed from: package-private */
    public <E> Collection<E> unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableCollection(collection);
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        for (Collection<V> collection : this.map.values()) {
            collection.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> get(@NullableDecl K key) {
        Collection<V> collection = this.map.get(key);
        if (collection == null) {
            collection = createCollection(key);
        }
        return wrapCollection(key, collection);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> wrapCollection(@NullableDecl K key, Collection<V> collection) {
        return new WrappedCollection(key, collection, null);
    }

    /* access modifiers changed from: package-private */
    public final List<V> wrapList(@NullableDecl K key, List<V> list, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor) {
        if (list instanceof RandomAccess) {
            return new RandomAccessWrappedList(key, list, ancestor);
        }
        return new WrappedList(key, list, ancestor);
    }

    /* access modifiers changed from: package-private */
    public class WrappedCollection extends AbstractCollection<V> {
        @NullableDecl
        final AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor;
        @NullableDecl
        final Collection<V> ancestorDelegate;
        Collection<V> delegate;
        @NullableDecl
        final K key;

        WrappedCollection(@NullableDecl K key2, Collection<V> delegate2, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor2) {
            this.key = key2;
            this.delegate = delegate2;
            this.ancestor = ancestor2;
            this.ancestorDelegate = ancestor2 == null ? null : ancestor2.getDelegate();
        }

        /* access modifiers changed from: package-private */
        public void refreshIfEmpty() {
            Collection<V> newDelegate;
            if (this.ancestor != null) {
                this.ancestor.refreshIfEmpty();
                if (this.ancestor.getDelegate() != this.ancestorDelegate) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.delegate.isEmpty() && (newDelegate = (Collection) AbstractMapBasedMultimap.this.map.get(this.key)) != null) {
                this.delegate = newDelegate;
            }
        }

        /* access modifiers changed from: package-private */
        public void removeIfEmpty() {
            if (this.ancestor != null) {
                this.ancestor.removeIfEmpty();
            } else if (this.delegate.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.key);
            }
        }

        /* access modifiers changed from: package-private */
        public K getKey() {
            return this.key;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.Map */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void addToMap() {
            if (this.ancestor != null) {
                this.ancestor.addToMap();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
            }
        }

        public int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        public boolean equals(@NullableDecl Object object) {
            if (object == this) {
                return true;
            }
            refreshIfEmpty();
            return this.delegate.equals(object);
        }

        public int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        public String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }

        /* access modifiers changed from: package-private */
        public Collection<V> getDelegate() {
            return this.delegate;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            refreshIfEmpty();
            return new WrappedIterator();
        }

        /* access modifiers changed from: package-private */
        public class WrappedIterator implements Iterator<V> {
            final Iterator<V> delegateIterator;
            final Collection<V> originalDelegate = WrappedCollection.this.delegate;

            WrappedIterator() {
                this.delegateIterator = AbstractMapBasedMultimap.iteratorOrListIterator(WrappedCollection.this.delegate);
            }

            WrappedIterator(Iterator<V> delegateIterator2) {
                this.delegateIterator = delegateIterator2;
            }

            /* access modifiers changed from: package-private */
            public void validateIterator() {
                WrappedCollection.this.refreshIfEmpty();
                if (WrappedCollection.this.delegate != this.originalDelegate) {
                    throw new ConcurrentModificationException();
                }
            }

            public boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            public void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                WrappedCollection.this.removeIfEmpty();
            }

            /* access modifiers changed from: package-private */
            public Iterator<V> getDelegateIterator() {
                validateIterator();
                return this.delegateIterator;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(V value) {
            refreshIfEmpty();
            boolean wasEmpty = this.delegate.isEmpty();
            boolean changed = this.delegate.add(value);
            if (changed) {
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (wasEmpty) {
                    addToMap();
                }
            }
            return changed;
        }

        /* access modifiers changed from: package-private */
        public AbstractMapBasedMultimap<K, V>.WrappedCollection getAncestor() {
            return this.ancestor;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int oldSize = size();
            boolean changed = this.delegate.addAll(collection);
            if (!changed) {
                return changed;
            }
            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - oldSize);
            if (oldSize != 0) {
                return changed;
            }
            addToMap();
            return changed;
        }

        public boolean contains(Object o) {
            refreshIfEmpty();
            return this.delegate.contains(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> c) {
            refreshIfEmpty();
            return this.delegate.containsAll(c);
        }

        public void clear() {
            int oldSize = size();
            if (oldSize != 0) {
                this.delegate.clear();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, oldSize);
                removeIfEmpty();
            }
        }

        public boolean remove(Object o) {
            refreshIfEmpty();
            boolean changed = this.delegate.remove(o);
            if (changed) {
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                removeIfEmpty();
            }
            return changed;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> c) {
            if (c.isEmpty()) {
                return false;
            }
            int oldSize = size();
            boolean changed = this.delegate.removeAll(c);
            if (!changed) {
                return changed;
            }
            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - oldSize);
            removeIfEmpty();
            return changed;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> c) {
            Preconditions.checkNotNull(c);
            int oldSize = size();
            boolean changed = this.delegate.retainAll(c);
            if (changed) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - oldSize);
                removeIfEmpty();
            }
            return changed;
        }
    }

    /* access modifiers changed from: private */
    public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
        if (collection instanceof List) {
            return ((List) collection).listIterator();
        }
        return collection.iterator();
    }

    class WrappedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
        WrappedSet(@NullableDecl K key, Set<V> delegate) {
            super(key, delegate, null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection
        public boolean removeAll(Collection<?> c) {
            if (c.isEmpty()) {
                return false;
            }
            int oldSize = size();
            boolean changed = Sets.removeAllImpl((Set) this.delegate, c);
            if (!changed) {
                return changed;
            }
            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - oldSize);
            removeIfEmpty();
            return changed;
        }
    }

    /* access modifiers changed from: package-private */
    public class WrappedSortedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements SortedSet<V> {
        WrappedSortedSet(@NullableDecl K key, SortedSet<V> delegate, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor) {
            super(key, delegate, ancestor);
        }

        /* access modifiers changed from: package-private */
        public SortedSet<V> getSortedSetDelegate() {
            return (SortedSet) getDelegate();
        }

        @Override // java.util.SortedSet
        public Comparator<? super V> comparator() {
            return getSortedSetDelegate().comparator();
        }

        @Override // java.util.SortedSet
        public V first() {
            refreshIfEmpty();
            return getSortedSetDelegate().first();
        }

        @Override // java.util.SortedSet
        public V last() {
            refreshIfEmpty();
            return getSortedSetDelegate().last();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v2, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection] */
        /* JADX WARN: Type inference failed for: r5v3 */
        @Override // java.util.SortedSet
        public SortedSet<V> headSet(V toElement) {
            refreshIfEmpty();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object key = getKey();
            SortedSet<V> headSet = getSortedSetDelegate().headSet(toElement);
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            ?? r5 = this;
            if (ancestor != null) {
                r5 = getAncestor();
            }
            return new WrappedSortedSet(key, headSet, r5);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v2, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection] */
        /* JADX WARN: Type inference failed for: r5v3 */
        @Override // java.util.SortedSet
        public SortedSet<V> subSet(V fromElement, V toElement) {
            refreshIfEmpty();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object key = getKey();
            SortedSet<V> subSet = getSortedSetDelegate().subSet(fromElement, toElement);
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            ?? r5 = this;
            if (ancestor != null) {
                r5 = getAncestor();
            }
            return new WrappedSortedSet(key, subSet, r5);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v2, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection] */
        /* JADX WARN: Type inference failed for: r5v3 */
        @Override // java.util.SortedSet
        public SortedSet<V> tailSet(V fromElement) {
            refreshIfEmpty();
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object key = getKey();
            SortedSet<V> tailSet = getSortedSetDelegate().tailSet(fromElement);
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            ?? r5 = this;
            if (ancestor != null) {
                r5 = getAncestor();
            }
            return new WrappedSortedSet(key, tailSet, r5);
        }
    }

    class WrappedNavigableSet extends AbstractMapBasedMultimap<K, V>.WrappedSortedSet implements NavigableSet<V> {
        WrappedNavigableSet(@NullableDecl K key, NavigableSet<V> delegate, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor) {
            super(key, delegate, ancestor);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.WrappedSortedSet
        public NavigableSet<V> getSortedSetDelegate() {
            return (NavigableSet) super.getSortedSetDelegate();
        }

        @Override // java.util.NavigableSet
        public V lower(V v) {
            return getSortedSetDelegate().lower(v);
        }

        @Override // java.util.NavigableSet
        public V floor(V v) {
            return getSortedSetDelegate().floor(v);
        }

        @Override // java.util.NavigableSet
        public V ceiling(V v) {
            return getSortedSetDelegate().ceiling(v);
        }

        @Override // java.util.NavigableSet
        public V higher(V v) {
            return getSortedSetDelegate().higher(v);
        }

        @Override // java.util.NavigableSet
        public V pollFirst() {
            return (V) Iterators.pollNext(iterator());
        }

        @Override // java.util.NavigableSet
        public V pollLast() {
            return (V) Iterators.pollNext(descendingIterator());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v2, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection] */
        /* JADX WARN: Type inference failed for: r4v3 */
        private NavigableSet<V> wrap(NavigableSet<V> wrapped) {
            AbstractMapBasedMultimap abstractMapBasedMultimap = AbstractMapBasedMultimap.this;
            Object obj = this.key;
            AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor = getAncestor();
            ?? r4 = this;
            if (ancestor != null) {
                r4 = getAncestor();
            }
            return new WrappedNavigableSet(obj, wrapped, r4);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> descendingSet() {
            return wrap(getSortedSetDelegate().descendingSet());
        }

        @Override // java.util.NavigableSet
        public Iterator<V> descendingIterator() {
            return new WrappedCollection.WrappedIterator(getSortedSetDelegate().descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> subSet(V fromElement, boolean fromInclusive, V toElement, boolean toInclusive) {
            return wrap(getSortedSetDelegate().subSet(fromElement, fromInclusive, toElement, toInclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> headSet(V toElement, boolean inclusive) {
            return wrap(getSortedSetDelegate().headSet(toElement, inclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> tailSet(V fromElement, boolean inclusive) {
            return wrap(getSortedSetDelegate().tailSet(fromElement, inclusive));
        }
    }

    /* access modifiers changed from: package-private */
    public class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {
        WrappedList(@NullableDecl K key, List<V> delegate, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor) {
            super(key, delegate, ancestor);
        }

        /* access modifiers changed from: package-private */
        public List<V> getListDelegate() {
            return (List) getDelegate();
        }

        @Override // java.util.List
        public boolean addAll(int index, Collection<? extends V> c) {
            if (c.isEmpty()) {
                return false;
            }
            int oldSize = size();
            boolean changed = getListDelegate().addAll(index, c);
            if (!changed) {
                return changed;
            }
            AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, getDelegate().size() - oldSize);
            if (oldSize != 0) {
                return changed;
            }
            addToMap();
            return changed;
        }

        @Override // java.util.List
        public V get(int index) {
            refreshIfEmpty();
            return getListDelegate().get(index);
        }

        @Override // java.util.List
        public V set(int index, V element) {
            refreshIfEmpty();
            return getListDelegate().set(index, element);
        }

        @Override // java.util.List
        public void add(int index, V element) {
            refreshIfEmpty();
            boolean wasEmpty = getDelegate().isEmpty();
            getListDelegate().add(index, element);
            AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
            if (wasEmpty) {
                addToMap();
            }
        }

        @Override // java.util.List
        public V remove(int index) {
            refreshIfEmpty();
            V value = getListDelegate().remove(index);
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
            removeIfEmpty();
            return value;
        }

        public int indexOf(Object o) {
            refreshIfEmpty();
            return getListDelegate().indexOf(o);
        }

        public int lastIndexOf(Object o) {
            refreshIfEmpty();
            return getListDelegate().lastIndexOf(o);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            refreshIfEmpty();
            return new WrappedListIterator();
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int index) {
            refreshIfEmpty();
            return new WrappedListIterator(index);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.google.common.collect.AbstractMapBasedMultimap] */
        /* JADX WARN: Type inference failed for: r4v2, types: [com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection] */
        /* JADX WARN: Type inference failed for: r4v3 */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.List
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.List<V> subList(int r5, int r6) {
            /*
                r4 = this;
                r4.refreshIfEmpty()
                com.google.common.collect.AbstractMapBasedMultimap r0 = com.google.common.collect.AbstractMapBasedMultimap.this
                java.lang.Object r1 = r4.getKey()
                java.util.List r2 = r4.getListDelegate()
                java.util.List r2 = r2.subList(r5, r6)
                com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection r3 = r4.getAncestor()
                if (r3 != 0) goto L_0x001c
            L_0x0017:
                java.util.List r0 = r0.wrapList(r1, r2, r4)
                return r0
            L_0x001c:
                com.google.common.collect.AbstractMapBasedMultimap$WrappedCollection r4 = r4.getAncestor()
                goto L_0x0017
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultimap.WrappedList.subList(int, int):java.util.List");
        }

        private class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedCollection.WrappedIterator implements ListIterator<V> {
            WrappedListIterator() {
                super();
            }

            public WrappedListIterator(int index) {
                super(WrappedList.this.getListDelegate().listIterator(index));
            }

            private ListIterator<V> getDelegateListIterator() {
                return (ListIterator) getDelegateIterator();
            }

            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return getDelegateListIterator().previous();
            }

            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V value) {
                getDelegateListIterator().set(value);
            }

            @Override // java.util.ListIterator
            public void add(V value) {
                boolean wasEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(value);
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (wasEmpty) {
                    WrappedList.this.addToMap();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        RandomAccessWrappedList(@NullableDecl K key, List<V> delegate, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor) {
            super(key, delegate, ancestor);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Set<K> createKeySet() {
        return new KeySet(this.map);
    }

    /* access modifiers changed from: package-private */
    public final Set<K> createMaybeNavigableKeySet() {
        if (this.map instanceof NavigableMap) {
            return new NavigableKeySet((NavigableMap) this.map);
        }
        if (this.map instanceof SortedMap) {
            return new SortedKeySet((SortedMap) this.map);
        }
        return new KeySet(this.map);
    }

    private class KeySet extends Maps.KeySet<K, Collection<V>> {
        KeySet(Map<K, Collection<V>> subMap) {
            super(subMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, com.google.common.collect.Maps.KeySet, java.lang.Iterable
        public Iterator<K> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> entryIterator = map().entrySet().iterator();
            return new Iterator<K>() {
                /* class com.google.common.collect.AbstractMapBasedMultimap.KeySet.AnonymousClass1 */
                @NullableDecl
                Map.Entry<K, Collection<V>> entry;

                public boolean hasNext() {
                    return entryIterator.hasNext();
                }

                @Override // java.util.Iterator
                public K next() {
                    this.entry = (Map.Entry) entryIterator.next();
                    return this.entry.getKey();
                }

                public void remove() {
                    CollectPreconditions.checkRemove(this.entry != null);
                    Collection<V> collection = this.entry.getValue();
                    entryIterator.remove();
                    AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, collection.size());
                    collection.clear();
                    this.entry = null;
                }
            };
        }

        @Override // com.google.common.collect.Maps.KeySet
        public boolean remove(Object key) {
            int count = 0;
            Collection<V> collection = map().remove(key);
            if (collection != null) {
                count = collection.size();
                collection.clear();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, count);
            }
            return count > 0;
        }

        @Override // com.google.common.collect.Maps.KeySet
        public void clear() {
            Iterators.clear(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> c) {
            return map().keySet().containsAll(c);
        }

        public boolean equals(@NullableDecl Object object) {
            return this == object || map().keySet().equals(object);
        }

        public int hashCode() {
            return map().keySet().hashCode();
        }
    }

    /* access modifiers changed from: private */
    public class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        SortedKeySet(SortedMap<K, Collection<V>> subMap) {
            super(subMap);
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) super.map();
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return sortedMap().firstKey();
        }

        @Override // java.util.SortedSet
        public SortedSet<K> headSet(K toElement) {
            return new SortedKeySet(sortedMap().headMap(toElement));
        }

        @Override // java.util.SortedSet
        public K last() {
            return sortedMap().lastKey();
        }

        @Override // java.util.SortedSet
        public SortedSet<K> subSet(K fromElement, K toElement) {
            return new SortedKeySet(sortedMap().subMap(fromElement, toElement));
        }

        @Override // java.util.SortedSet
        public SortedSet<K> tailSet(K fromElement) {
            return new SortedKeySet(sortedMap().tailMap(fromElement));
        }
    }

    /* access modifiers changed from: package-private */
    public class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        NavigableKeySet(NavigableMap<K, Collection<V>> subMap) {
            super(subMap);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet
        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        @Override // java.util.NavigableSet
        public K lower(K k) {
            return sortedMap().lowerKey(k);
        }

        @Override // java.util.NavigableSet
        public K floor(K k) {
            return sortedMap().floorKey(k);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k) {
            return sortedMap().ceilingKey(k);
        }

        @Override // java.util.NavigableSet
        public K higher(K k) {
            return sortedMap().higherKey(k);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Iterators.pollNext(iterator());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Iterators.pollNext(descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(sortedMap().descendingMap());
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.SortedSet, com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.NavigableSet
        public NavigableSet<K> headSet(K toElement) {
            return headSet(toElement, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K toElement, boolean inclusive) {
            return new NavigableKeySet(sortedMap().headMap(toElement, inclusive));
        }

        @Override // java.util.SortedSet, com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.NavigableSet
        public NavigableSet<K> subSet(K fromElement, K toElement) {
            return subSet(fromElement, true, toElement, false);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K fromElement, boolean fromInclusive, K toElement, boolean toInclusive) {
            return new NavigableKeySet(sortedMap().subMap(fromElement, fromInclusive, toElement, toInclusive));
        }

        @Override // java.util.SortedSet, com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.NavigableSet
        public NavigableSet<K> tailSet(K fromElement) {
            return tailSet(fromElement, true);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K fromElement, boolean inclusive) {
            return new NavigableKeySet(sortedMap().tailMap(fromElement, inclusive));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeValuesForKey(Object key) {
        Collection<V> collection = (Collection) Maps.safeRemove(this.map, key);
        if (collection != null) {
            int count = collection.size();
            collection.clear();
            this.totalSize -= count;
        }
    }

    private abstract class Itr<T> implements Iterator<T> {
        @MonotonicNonNullDecl
        Collection<V> collection = null;
        @NullableDecl
        K key = null;
        final Iterator<Map.Entry<K, Collection<V>>> keyIterator;
        Iterator<V> valueIterator = Iterators.emptyModifiableIterator();

        /* access modifiers changed from: package-private */
        public abstract T output(K k, V v);

        Itr() {
            this.keyIterator = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.keyIterator.hasNext() || this.valueIterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.valueIterator.hasNext()) {
                Map.Entry<K, Collection<V>> mapEntry = this.keyIterator.next();
                this.key = mapEntry.getKey();
                this.collection = mapEntry.getValue();
                this.valueIterator = this.collection.iterator();
            }
            return output(this.key, this.valueIterator.next());
        }

        public void remove() {
            this.valueIterator.remove();
            if (this.collection.isEmpty()) {
                this.keyIterator.remove();
            }
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
        }
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<V> values() {
        return super.values();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Collection<V> createValues() {
        return new AbstractMultimap.Values();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<V> valueIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr() {
            /* class com.google.common.collect.AbstractMapBasedMultimap.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            public V output(K k, V value) {
                return value;
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Multiset<K> createKeys() {
        return new Multimaps.Keys(this);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Collection<Map.Entry<K, V>> createEntries() {
        if (this instanceof SetMultimap) {
            return new AbstractMultimap.EntrySet();
        }
        return new AbstractMultimap.Entries();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr() {
            /* class com.google.common.collect.AbstractMapBasedMultimap.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            public Map.Entry<K, V> output(K key, V value) {
                return Maps.immutableEntry(key, value);
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Map<K, Collection<V>> createAsMap() {
        return new AsMap(this.map);
    }

    /* access modifiers changed from: package-private */
    public final Map<K, Collection<V>> createMaybeNavigableAsMap() {
        if (this.map instanceof NavigableMap) {
            return new NavigableAsMap((NavigableMap) this.map);
        }
        if (this.map instanceof SortedMap) {
            return new SortedAsMap((SortedMap) this.map);
        }
        return new AsMap(this.map);
    }

    /* access modifiers changed from: private */
    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> submap;

        AsMap(Map<K, Collection<V>> submap2) {
            this.submap = submap2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries();
        }

        public boolean containsKey(Object key) {
            return Maps.safeContainsKey(this.submap, key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(Object key) {
            Collection<V> collection = (Collection) Maps.safeGet(this.submap, key);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(key, collection);
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        public int size() {
            return this.submap.size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(Object key) {
            Collection<V> collection = this.submap.remove(key);
            if (collection == null) {
                return null;
            }
            Collection<V> output = AbstractMapBasedMultimap.this.createCollection();
            output.addAll(collection);
            AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, collection.size());
            collection.clear();
            return output;
        }

        public boolean equals(@NullableDecl Object object) {
            return this == object || this.submap.equals(object);
        }

        public int hashCode() {
            return this.submap.hashCode();
        }

        public String toString() {
            return this.submap.toString();
        }

        public void clear() {
            if (this.submap == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.clear(new AsMapIterator());
            }
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> wrapEntry(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, entry.getValue()));
        }

        class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
            AsMapEntries() {
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.Maps.EntrySet
            public Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator();
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public boolean contains(Object o) {
                return Collections2.safeContains(AsMap.this.submap.entrySet(), o);
            }

            @Override // com.google.common.collect.Maps.EntrySet
            public boolean remove(Object o) {
                if (!contains(o)) {
                    return false;
                }
                AbstractMapBasedMultimap.this.removeValuesForKey(((Map.Entry) o).getKey());
                return true;
            }
        }

        class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {
            @NullableDecl
            Collection<V> collection;
            final Iterator<Map.Entry<K, Collection<V>>> delegateIterator = AsMap.this.submap.entrySet().iterator();

            AsMapIterator() {
            }

            public boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry<K, Collection<V>> entry = this.delegateIterator.next();
                this.collection = entry.getValue();
                return AsMap.this.wrapEntry(entry);
            }

            public void remove() {
                CollectPreconditions.checkRemove(this.collection != null);
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, this.collection.size());
                this.collection.clear();
                this.collection = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        @MonotonicNonNullDecl
        SortedSet<K> sortedKeySet;

        SortedAsMap(SortedMap<K, Collection<V>> submap) {
            super(submap);
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> sortedMap() {
            return (SortedMap) this.submap;
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return sortedMap().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return sortedMap().firstKey();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return sortedMap().lastKey();
        }

        @Override // java.util.SortedMap
        public SortedMap<K, Collection<V>> headMap(K toKey) {
            return new SortedAsMap(sortedMap().headMap(toKey));
        }

        @Override // java.util.SortedMap
        public SortedMap<K, Collection<V>> subMap(K fromKey, K toKey) {
            return new SortedAsMap(sortedMap().subMap(fromKey, toKey));
        }

        @Override // java.util.SortedMap
        public SortedMap<K, Collection<V>> tailMap(K fromKey) {
            return new SortedAsMap(sortedMap().tailMap(fromKey));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.AsMap, java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.SortedMap
        public SortedSet<K> keySet() {
            SortedSet<K> result = this.sortedKeySet;
            if (result != null) {
                return result;
            }
            SortedSet<K> result2 = createKeySet();
            this.sortedKeySet = result2;
            return result2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public SortedSet<K> createKeySet() {
            return new SortedKeySet(sortedMap());
        }
    }

    class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        NavigableAsMap(NavigableMap<K, Collection<V>> submap) {
            super(submap);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        public NavigableMap<K, Collection<V>> sortedMap() {
            return (NavigableMap) super.sortedMap();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lowerEntry(K key) {
            Map.Entry<K, Collection<V>> entry = sortedMap().lowerEntry(key);
            if (entry == null) {
                return null;
            }
            return wrapEntry(entry);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            return sortedMap().lowerKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> floorEntry(K key) {
            Map.Entry<K, Collection<V>> entry = sortedMap().floorEntry(key);
            if (entry == null) {
                return null;
            }
            return wrapEntry(entry);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            return sortedMap().floorKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> ceilingEntry(K key) {
            Map.Entry<K, Collection<V>> entry = sortedMap().ceilingEntry(key);
            if (entry == null) {
                return null;
            }
            return wrapEntry(entry);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            return sortedMap().ceilingKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> higherEntry(K key) {
            Map.Entry<K, Collection<V>> entry = sortedMap().higherEntry(key);
            if (entry == null) {
                return null;
            }
            return wrapEntry(entry);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            return sortedMap().higherKey(key);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry<K, Collection<V>> entry = sortedMap().firstEntry();
            if (entry == null) {
                return null;
            }
            return wrapEntry(entry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry<K, Collection<V>> entry = sortedMap().lastEntry();
            if (entry == null) {
                return null;
            }
            return wrapEntry(entry);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.AbstractMapBasedMultimap$NavigableAsMap */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return pollAsMapEntry(entrySet().iterator());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return pollAsMapEntry(descendingMap().entrySet().iterator());
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> pollAsMapEntry(Iterator<Map.Entry<K, Collection<V>>> entryIterator) {
            if (!entryIterator.hasNext()) {
                return null;
            }
            Map.Entry<K, Collection<V>> entry = entryIterator.next();
            Collection<V> output = AbstractMapBasedMultimap.this.createCollection();
            output.addAll(entry.getValue());
            entryIterator.remove();
            return Maps.immutableEntry(entry.getKey(), AbstractMapBasedMultimap.this.unmodifiableCollectionSubclass(output));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(sortedMap().descendingMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.AsMap, com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.AbstractMap, java.util.Map, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.SortedMap
        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.Maps.ViewCachingAbstractMap
        public NavigableSet<K> createKeySet() {
            return new NavigableKeySet(sortedMap());
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, Collection<V>> subMap(K fromKey, K toKey) {
            return subMap(fromKey, true, toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            return new NavigableAsMap(sortedMap().subMap(fromKey, fromInclusive, toKey, toInclusive));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, Collection<V>> headMap(K toKey) {
            return headMap(toKey, false);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K toKey, boolean inclusive) {
            return new NavigableAsMap(sortedMap().headMap(toKey, inclusive));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.NavigableMap, java.util.SortedMap
        public NavigableMap<K, Collection<V>> tailMap(K fromKey) {
            return tailMap(fromKey, true);
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K fromKey, boolean inclusive) {
            return new NavigableAsMap(sortedMap().tailMap(fromKey, inclusive));
        }
    }
}
