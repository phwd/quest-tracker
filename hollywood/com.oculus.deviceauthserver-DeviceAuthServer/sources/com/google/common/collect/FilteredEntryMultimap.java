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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible
public class FilteredEntryMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Predicate<? super Map.Entry<K, V>> predicate;
    final Multimap<K, V> unfiltered;

    FilteredEntryMultimap(Multimap<K, V> unfiltered2, Predicate<? super Map.Entry<K, V>> predicate2) {
        this.unfiltered = (Multimap) Preconditions.checkNotNull(unfiltered2);
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
    private boolean satisfies(K key, V value) {
        return this.predicate.apply(Maps.immutableEntry(key, value));
    }

    /* access modifiers changed from: package-private */
    public final class ValuePredicate implements Predicate<V> {
        private final K key;

        ValuePredicate(K key2) {
            this.key = key2;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(@Nullable V value) {
            return FilteredEntryMultimap.this.satisfies(this.key, value);
        }
    }

    static <E> Collection<E> filterCollection(Collection<E> collection, Predicate<? super E> predicate2) {
        if (collection instanceof Set) {
            return Sets.filter((Set) collection, predicate2);
        }
        return Collections2.filter(collection, predicate2);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@Nullable Object key) {
        return asMap().get(key) != null;
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> removeAll(@Nullable Object key) {
        return (Collection) MoreObjects.firstNonNull(asMap().remove(key), unmodifiableEmptyCollection());
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
    public Collection<V> get(K key) {
        return filterCollection(this.unfiltered.get(key), new ValuePredicate(key));
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

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public Set<K> keySet() {
        return asMap().keySet();
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntriesIf(Predicate<? super Map.Entry<K, Collection<V>>> predicate2) {
        Iterator<Map.Entry<K, Collection<V>>> entryIterator = this.unfiltered.asMap().entrySet().iterator();
        boolean changed = false;
        while (entryIterator.hasNext()) {
            Map.Entry<K, Collection<V>> entry = entryIterator.next();
            K key = entry.getKey();
            Collection<V> collection = filterCollection(entry.getValue(), new ValuePredicate(key));
            if (!collection.isEmpty() && predicate2.apply(Maps.immutableEntry(key, collection))) {
                if (collection.size() == entry.getValue().size()) {
                    entryIterator.remove();
                } else {
                    collection.clear();
                }
                changed = true;
            }
        }
        return changed;
    }

    /* access modifiers changed from: package-private */
    public class AsMap extends Maps.ImprovedAbstractMap<K, Collection<V>> {
        AsMap() {
        }

        public boolean containsKey(@Nullable Object key) {
            return get(key) != null;
        }

        public void clear() {
            FilteredEntryMultimap.this.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(@Nullable Object key) {
            Collection<V> result = FilteredEntryMultimap.this.unfiltered.asMap().get(key);
            if (result == null) {
                return null;
            }
            Collection<V> result2 = FilteredEntryMultimap.filterCollection(result, new ValuePredicate(key));
            if (result2.isEmpty()) {
                return null;
            }
            return result2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(@Nullable Object key) {
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.asMap().get(key);
            if (collection == null) {
                return null;
            }
            List<V> result = Lists.newArrayList();
            Iterator<V> itr = collection.iterator();
            while (itr.hasNext()) {
                V v = itr.next();
                if (FilteredEntryMultimap.this.satisfies(key, v)) {
                    itr.remove();
                    result.add(v);
                }
            }
            if (result.isEmpty()) {
                return null;
            }
            if (FilteredEntryMultimap.this.unfiltered instanceof SetMultimap) {
                return Collections.unmodifiableSet(Sets.newLinkedHashSet(result));
            }
            return Collections.unmodifiableList(result);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<K> createKeySet() {
            return new Maps.KeySet<K, Collection<V>>(this) {
                /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass1 */

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set
                public boolean removeAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(c)));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
                }

                @Override // com.google.common.collect.Maps.KeySet
                public boolean remove(@Nullable Object o) {
                    return AsMap.this.remove(o) != null;
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new Maps.EntrySet<K, Collection<V>>() {
                /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass2 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.Maps.EntrySet
                public Map<K, Collection<V>> map() {
                    return AsMap.this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
                public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                    return new AbstractIterator<Map.Entry<K, Collection<V>>>() {
                        /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass2.AnonymousClass1 */
                        final Iterator<Map.Entry<K, Collection<V>>> backingIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();

                        /* access modifiers changed from: protected */
                        @Override // com.google.common.collect.AbstractIterator
                        public Map.Entry<K, Collection<V>> computeNext() {
                            while (this.backingIterator.hasNext()) {
                                Map.Entry<K, Collection<V>> entry = this.backingIterator.next();
                                K key = entry.getKey();
                                Collection<V> collection = FilteredEntryMultimap.filterCollection(entry.getValue(), new ValuePredicate(key));
                                if (!collection.isEmpty()) {
                                    return Maps.immutableEntry(key, collection);
                                }
                            }
                            return (Map.Entry) endOfData();
                        }
                    };
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.AbstractSet, java.util.Set, com.google.common.collect.Maps.EntrySet
                public boolean removeAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.in(c));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set, com.google.common.collect.Maps.EntrySet
                public boolean retainAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.not(Predicates.in(c)));
                }

                @Override // com.google.common.collect.Maps.EntrySet
                public int size() {
                    return Iterators.size(iterator());
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ImprovedAbstractMap
        public Collection<Collection<V>> createValues() {
            return new Maps.Values<K, Collection<V>>(this) {
                /* class com.google.common.collect.FilteredEntryMultimap.AsMap.AnonymousClass3 */

                @Override // com.google.common.collect.Maps.Values
                public boolean remove(@Nullable Object o) {
                    if (!(o instanceof Collection)) {
                        return false;
                    }
                    Collection<?> c = (Collection) o;
                    Iterator<Map.Entry<K, Collection<V>>> entryIterator = FilteredEntryMultimap.this.unfiltered.asMap().entrySet().iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry<K, Collection<V>> entry = entryIterator.next();
                        Collection<V> collection = FilteredEntryMultimap.filterCollection(entry.getValue(), new ValuePredicate(entry.getKey()));
                        if (!collection.isEmpty() && c.equals(collection)) {
                            if (collection.size() == entry.getValue().size()) {
                                entryIterator.remove();
                                return true;
                            }
                            collection.clear();
                            return true;
                        }
                    }
                    return false;
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                public boolean removeAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Maps.Values, java.util.Collection
                public boolean retainAll(Collection<?> c) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
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
        public int remove(@Nullable Object key, int occurrences) {
            CollectPreconditions.checkNonnegative(occurrences, "occurrences");
            if (occurrences == 0) {
                return count(key);
            }
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.asMap().get(key);
            if (collection == null) {
                return 0;
            }
            int oldCount = 0;
            Iterator<V> itr = collection.iterator();
            while (itr.hasNext()) {
                if (FilteredEntryMultimap.this.satisfies(key, itr.next()) && (oldCount = oldCount + 1) <= occurrences) {
                    itr.remove();
                }
            }
            return oldCount;
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
                public boolean removeAll(Collection<?> c) {
                    return removeEntriesIf(Predicates.in(c));
                }

                @Override // java.util.AbstractCollection, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> c) {
                    return removeEntriesIf(Predicates.not(Predicates.in(c)));
                }
            };
        }
    }
}
