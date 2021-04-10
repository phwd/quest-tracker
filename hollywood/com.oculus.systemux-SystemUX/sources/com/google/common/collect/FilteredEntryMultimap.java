package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public class FilteredEntryMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Predicate<? super Map.Entry<K, V>> predicate;
    final Multimap<K, V> unfiltered;

    FilteredEntryMultimap(Multimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> predicate2) {
        this.unfiltered = (Multimap) Preconditions.checkNotNull(multimap);
        this.predicate = (Predicate) Preconditions.checkNotNull(predicate2);
    }

    @Override // com.google.common.collect.FilteredMultimap
    public Multimap<K, V> unfiltered() {
        return this.unfiltered;
    }

    @Override // com.google.common.collect.FilteredMultimap
    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return this.predicate;
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return entries().size();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean satisfies(K k, V v) {
        return this.predicate.apply(Maps.immutableEntry(k, v));
    }

    /* access modifiers changed from: package-private */
    public final class ValuePredicate implements Predicate<V> {
        private final K key;

        ValuePredicate(K k) {
            this.key = k;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(@NullableDecl V v) {
            return FilteredEntryMultimap.this.satisfies(this.key, v);
        }
    }

    static <E> Collection<E> filterCollection(Collection<E> collection, Predicate<? super E> predicate2) {
        if (collection instanceof Set) {
            return Sets.filter((Set) collection, predicate2);
        }
        return Collections2.filter(collection, predicate2);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return asMap().get(obj) != null;
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> removeAll(@NullableDecl Object obj) {
        return (Collection) MoreObjects.firstNonNull(asMap().remove(obj), unmodifiableEmptyCollection());
    }

    /* access modifiers changed from: package-private */
    public Collection<V> unmodifiableEmptyCollection() {
        if (this.unfiltered instanceof SetMultimap) {
            return Collections.emptySet();
        }
        return Collections.emptyList();
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        entries().clear();
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> get(K k) {
        return filterCollection(this.unfiltered.get(k), new ValuePredicate(k));
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Collection<Map.Entry<K, V>> createEntries() {
        return filterCollection(this.unfiltered.entries(), this.predicate);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Collection<V> createValues() {
        return new FilteredMultimapValues(this);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Map<K, Collection<V>> createAsMap() {
        return new AsMap();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Set<K> createKeySet() {
        return asMap().keySet();
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntriesIf(Predicate<? super Map.Entry<K, Collection<V>>> predicate2) {
        Iterator<Map.Entry<K, Collection<V>>> it = this.unfiltered.asMap().entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry<K, Collection<V>> next = it.next();
            K key = next.getKey();
            Collection filterCollection = filterCollection(next.getValue(), new ValuePredicate(key));
            if (!filterCollection.isEmpty() && predicate2.apply(Maps.immutableEntry(key, filterCollection))) {
                if (filterCollection.size() == next.getValue().size()) {
                    it.remove();
                } else {
                    filterCollection.clear();
                }
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        AsMap() {
        }

        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        public void clear() {
            FilteredEntryMultimap.this.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(@NullableDecl Object obj) {
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> filterCollection = FilteredEntryMultimap.filterCollection(collection, new ValuePredicate(obj));
            if (filterCollection.isEmpty()) {
                return null;
            }
            return filterCollection;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(@NullableDecl Object obj) {
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            ArrayList newArrayList = Lists.newArrayList();
            Iterator<V> it = collection.iterator();
            while (it.hasNext()) {
                V next = it.next();
                if (FilteredEntryMultimap.this.satisfies(obj, next)) {
                    it.remove();
                    newArrayList.add(next);
                }
            }
            if (newArrayList.isEmpty()) {
                return null;
            }
            if (FilteredEntryMultimap.this.unfiltered instanceof SetMultimap) {
                return Collections.unmodifiableSet(Sets.newLinkedHashSet(newArrayList));
            }
            return Collections.unmodifiableList(newArrayList);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<K> createKeySet() {
            return new Maps.KeySet<K, Collection<V>>() {
                /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass1KeySetImpl */

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(collection)));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }

                @Override // com.google.common.collect.Maps.KeySet
                public boolean remove(@NullableDecl Object obj) {
                    return AsMap.this.remove(obj) != null;
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new Maps.EntrySet<K, Collection<V>>() {
                /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass1EntrySetImpl */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, Collection<V>> map() {
                    return AsMap.this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                    return new AbstractIterator<Map.Entry<K, Collection<V>>>() {
                        /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass1EntrySetImpl.AnonymousClass1 */
                        final Iterator<Map.Entry<K, Collection<V>>> backingIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();

                        /* access modifiers changed from: protected */
                        @Override // com.google.common.collect.AbstractIterator
                        public Map.Entry<K, Collection<V>> computeNext() {
                            while (this.backingIterator.hasNext()) {
                                Map.Entry<K, Collection<V>> next = this.backingIterator.next();
                                K key = next.getKey();
                                Collection filterCollection = FilteredEntryMultimap.filterCollection(next.getValue(), new ValuePredicate(key));
                                if (!filterCollection.isEmpty()) {
                                    return Maps.immutableEntry(key, filterCollection);
                                }
                            }
                            return (Map.Entry) endOfData();
                        }
                    };
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set, com.google.common.collect.Maps.EntrySet
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.in(collection));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set, com.google.common.collect.Maps.EntrySet
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.not(Predicates.in(collection)));
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public int size() {
                    return Iterators.size(iterator());
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        public Collection<Collection<V>> createValues() {
            return new Maps.Values<K, Collection<V>>() {
                /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass1ValuesImpl */

                @Override // com.google.common.collect.Maps.Values
                public boolean remove(@NullableDecl Object obj) {
                    if (!(obj instanceof Collection)) {
                        return false;
                    }
                    Collection collection = (Collection) obj;
                    Iterator<Map.Entry<K, Collection<V>>> it = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<K, Collection<V>> next = it.next();
                        Collection filterCollection = FilteredEntryMultimap.filterCollection(next.getValue(), new ValuePredicate(next.getKey()));
                        if (!filterCollection.isEmpty() && collection.equals(filterCollection)) {
                            if (filterCollection.size() == next.getValue().size()) {
                                it.remove();
                                return true;
                            }
                            filterCollection.clear();
                            return true;
                        }
                    }
                    return false;
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }
            };
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Multiset<K> createKeys() {
        return new Keys();
    }

    /* access modifiers changed from: package-private */
    public class Keys extends Multimaps.Keys<K, V> {
        Keys() {
            super(FilteredEntryMultimap.this);
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.Multimaps.Keys, com.google.common.collect.AbstractMultiset
        public int remove(@NullableDecl Object obj, int i) {
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.asMap().get(obj);
            int i2 = 0;
            if (collection == null) {
                return 0;
            }
            Iterator<V> it = collection.iterator();
            while (it.hasNext()) {
                if (FilteredEntryMultimap.this.satisfies(obj, it.next()) && (i2 = i2 + 1) <= i) {
                    it.remove();
                }
            }
            return i2;
        }

        @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
        public Set<Multiset.Entry<K>> entrySet() {
            return new Multisets.EntrySet<K>() {
                /* class com.google.common.collect.FilteredEntryMultimap.Keys.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.Multisets.EntrySet
                public Multiset<K> multiset() {
                    return Keys.this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Multiset.Entry<K>> iterator() {
                    return Keys.this.entryIterator();
                }

                public int size() {
                    return FilteredEntryMultimap.this.keySet().size();
                }

                private boolean removeEntriesIf(final Predicate<? super Multiset.Entry<K>> predicate) {
                    return FilteredEntryMultimap.this.removeEntriesIf(new Predicate<Map.Entry<K, Collection<V>>>() {
                        /* class com.google.common.collect.FilteredEntryMultimap.Keys.AnonymousClass1.AnonymousClass1 */

                        @Override // com.google.common.base.Predicate
                        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                            return apply((Map.Entry) ((Map.Entry) obj));
                        }

                        public boolean apply(Map.Entry<K, Collection<V>> entry) {
                            return predicate.apply(Multisets.immutableEntry(entry.getKey(), entry.getValue().size()));
                        }
                    });
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return removeEntriesIf(Predicates.in(collection));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return removeEntriesIf(Predicates.not(Predicates.in(collection)));
                }
            };
        }
    }
}
