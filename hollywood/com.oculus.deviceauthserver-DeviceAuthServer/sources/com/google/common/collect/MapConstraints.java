package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.Nullable;

@Beta
@GwtCompatible
public final class MapConstraints {
    private MapConstraints() {
    }

    public static MapConstraint<Object, Object> notNull() {
        return NotNullMapConstraint.INSTANCE;
    }

    private enum NotNullMapConstraint implements MapConstraint<Object, Object> {
        INSTANCE;

        @Override // com.google.common.collect.MapConstraint
        public void checkKeyValue(Object key, Object value) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(value);
        }

        @Override // com.google.common.collect.MapConstraint
        public String toString() {
            return "Not null";
        }
    }

    public static <K, V> Map<K, V> constrainedMap(Map<K, V> map, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedMap(map, constraint);
    }

    public static <K, V> Multimap<K, V> constrainedMultimap(Multimap<K, V> multimap, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedMultimap(multimap, constraint);
    }

    public static <K, V> ListMultimap<K, V> constrainedListMultimap(ListMultimap<K, V> multimap, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedListMultimap(multimap, constraint);
    }

    public static <K, V> SetMultimap<K, V> constrainedSetMultimap(SetMultimap<K, V> multimap, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedSetMultimap(multimap, constraint);
    }

    public static <K, V> SortedSetMultimap<K, V> constrainedSortedSetMultimap(SortedSetMultimap<K, V> multimap, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedSortedSetMultimap(multimap, constraint);
    }

    /* access modifiers changed from: private */
    public static <K, V> Map.Entry<K, V> constrainedEntry(final Map.Entry<K, V> entry, final MapConstraint<? super K, ? super V> constraint) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkNotNull(constraint);
        return new ForwardingMapEntry<K, V>() {
            /* class com.google.common.collect.MapConstraints.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingMapEntry
            public Map.Entry<K, V> delegate() {
                return entry;
            }

            @Override // java.util.Map.Entry, com.google.common.collect.ForwardingMapEntry
            public V setValue(V value) {
                constraint.checkKeyValue(getKey(), value);
                return (V) entry.setValue(value);
            }
        };
    }

    /* access modifiers changed from: private */
    public static <K, V> Map.Entry<K, Collection<V>> constrainedAsMapEntry(final Map.Entry<K, Collection<V>> entry, final MapConstraint<? super K, ? super V> constraint) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkNotNull(constraint);
        return new ForwardingMapEntry<K, Collection<V>>() {
            /* class com.google.common.collect.MapConstraints.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingMapEntry
            public Map.Entry<K, Collection<V>> delegate() {
                return entry;
            }

            @Override // java.util.Map.Entry, com.google.common.collect.ForwardingMapEntry
            public Collection<V> getValue() {
                return Constraints.constrainedTypePreservingCollection((Collection) entry.getValue(), new Constraint<V>() {
                    /* class com.google.common.collect.MapConstraints.AnonymousClass2.AnonymousClass1 */

                    @Override // com.google.common.collect.Constraint
                    public V checkElement(V value) {
                        constraint.checkKeyValue(AnonymousClass2.this.getKey(), value);
                        return value;
                    }
                });
            }
        };
    }

    /* access modifiers changed from: private */
    public static <K, V> Set<Map.Entry<K, Collection<V>>> constrainedAsMapEntries(Set<Map.Entry<K, Collection<V>>> entries, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedAsMapEntries(entries, constraint);
    }

    /* access modifiers changed from: private */
    public static <K, V> Collection<Map.Entry<K, V>> constrainedEntries(Collection<Map.Entry<K, V>> entries, MapConstraint<? super K, ? super V> constraint) {
        if (entries instanceof Set) {
            return constrainedEntrySet((Set) entries, constraint);
        }
        return new ConstrainedEntries(entries, constraint);
    }

    /* access modifiers changed from: private */
    public static <K, V> Set<Map.Entry<K, V>> constrainedEntrySet(Set<Map.Entry<K, V>> entries, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedEntrySet(entries, constraint);
    }

    /* access modifiers changed from: package-private */
    public static class ConstrainedMap<K, V> extends ForwardingMap<K, V> {
        final MapConstraint<? super K, ? super V> constraint;
        private final Map<K, V> delegate;
        private transient Set<Map.Entry<K, V>> entrySet;

        ConstrainedMap(Map<K, V> delegate2, MapConstraint<? super K, ? super V> constraint2) {
            this.delegate = (Map) Preconditions.checkNotNull(delegate2);
            this.constraint = (MapConstraint) Preconditions.checkNotNull(constraint2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public Map<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> result = this.entrySet;
            if (result != null) {
                return result;
            }
            Set<Map.Entry<K, V>> result2 = MapConstraints.constrainedEntrySet(this.delegate.entrySet(), this.constraint);
            this.entrySet = result2;
            return result2;
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public V put(K key, V value) {
            this.constraint.checkKeyValue(key, value);
            return this.delegate.put(key, value);
        }

        @Override // com.google.common.collect.ForwardingMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            this.delegate.putAll(MapConstraints.checkMap(map, this.constraint));
        }
    }

    public static <K, V> BiMap<K, V> constrainedBiMap(BiMap<K, V> map, MapConstraint<? super K, ? super V> constraint) {
        return new ConstrainedBiMap(map, null, constraint);
    }

    private static class ConstrainedBiMap<K, V> extends ConstrainedMap<K, V> implements BiMap<K, V> {
        volatile BiMap<V, K> inverse;

        ConstrainedBiMap(BiMap<K, V> delegate, @Nullable BiMap<V, K> inverse2, MapConstraint<? super K, ? super V> constraint) {
            super(delegate, constraint);
            this.inverse = inverse2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.MapConstraints.ConstrainedMap, com.google.common.collect.MapConstraints.ConstrainedMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
        public BiMap<K, V> delegate() {
            return (BiMap) super.delegate();
        }

        @Override // com.google.common.collect.BiMap
        public V forcePut(K key, V value) {
            this.constraint.checkKeyValue(key, value);
            return delegate().forcePut(key, value);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<V, K> inverse() {
            if (this.inverse == null) {
                this.inverse = new ConstrainedBiMap(delegate().inverse(), this, new InverseConstraint(this.constraint));
            }
            return this.inverse;
        }

        @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, java.util.Map
        public Set<V> values() {
            return delegate().values();
        }
    }

    private static class InverseConstraint<K, V> implements MapConstraint<K, V> {
        final MapConstraint<? super V, ? super K> constraint;

        public InverseConstraint(MapConstraint<? super V, ? super K> constraint2) {
            this.constraint = (MapConstraint) Preconditions.checkNotNull(constraint2);
        }

        @Override // com.google.common.collect.MapConstraint
        public void checkKeyValue(K key, V value) {
            this.constraint.checkKeyValue(value, key);
        }
    }

    /* access modifiers changed from: private */
    public static class ConstrainedMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
        transient Map<K, Collection<V>> asMap;
        final MapConstraint<? super K, ? super V> constraint;
        final Multimap<K, V> delegate;
        transient Collection<Map.Entry<K, V>> entries;

        public ConstrainedMultimap(Multimap<K, V> delegate2, MapConstraint<? super K, ? super V> constraint2) {
            this.delegate = (Multimap) Preconditions.checkNotNull(delegate2);
            this.constraint = (MapConstraint) Preconditions.checkNotNull(constraint2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public Multimap<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> result = this.asMap;
            if (result != null) {
                return result;
            }
            final Map<K, Collection<V>> asMapDelegate = this.delegate.asMap();
            Map<K, Collection<V>> result2 = new ForwardingMap<K, Collection<V>>() {
                /* class com.google.common.collect.MapConstraints.ConstrainedMultimap.AnonymousClass1 */
                Set<Map.Entry<K, Collection<V>>> entrySet;
                Collection<Collection<V>> values;

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
                public Map<K, Collection<V>> delegate() {
                    return asMapDelegate;
                }

                @Override // com.google.common.collect.ForwardingMap, java.util.Map
                public Set<Map.Entry<K, Collection<V>>> entrySet() {
                    Set<Map.Entry<K, Collection<V>>> result = this.entrySet;
                    if (result != null) {
                        return result;
                    }
                    Set<Map.Entry<K, Collection<V>>> result2 = MapConstraints.constrainedAsMapEntries(asMapDelegate.entrySet(), ConstrainedMultimap.this.constraint);
                    this.entrySet = result2;
                    return result2;
                }

                @Override // com.google.common.collect.ForwardingMap, java.util.Map
                public Collection<V> get(Object key) {
                    try {
                        Collection<V> collection = ConstrainedMultimap.this.get(key);
                        if (collection.isEmpty()) {
                            return null;
                        }
                        return collection;
                    } catch (ClassCastException e) {
                        return null;
                    }
                }

                @Override // com.google.common.collect.ForwardingMap, java.util.Map
                public Collection<Collection<V>> values() {
                    Collection<Collection<V>> result = this.values;
                    if (result != null) {
                        return result;
                    }
                    Collection<Collection<V>> result2 = new ConstrainedAsMapValues<>(delegate().values(), entrySet());
                    this.values = result2;
                    return result2;
                }

                @Override // com.google.common.collect.ForwardingMap
                public boolean containsValue(Object o) {
                    return values().contains(o);
                }
            };
            this.asMap = result2;
            return result2;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> result = this.entries;
            if (result != null) {
                return result;
            }
            Collection<Map.Entry<K, V>> result2 = MapConstraints.constrainedEntries(this.delegate.entries(), this.constraint);
            this.entries = result2;
            return result2;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<V> get(final K key) {
            return Constraints.constrainedTypePreservingCollection(this.delegate.get(key), new Constraint<V>() {
                /* class com.google.common.collect.MapConstraints.ConstrainedMultimap.AnonymousClass2 */

                @Override // com.google.common.collect.Constraint
                public V checkElement(V value) {
                    ConstrainedMultimap.this.constraint.checkKeyValue((Object) key, value);
                    return value;
                }
            });
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean put(K key, V value) {
            this.constraint.checkKeyValue(key, value);
            return this.delegate.put(key, value);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean putAll(K key, Iterable<? extends V> values) {
            return this.delegate.putAll(key, MapConstraints.checkValues(key, values, this.constraint));
        }

        /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.collect.MapConstraints$ConstrainedMultimap<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean changed = false;
            for (Map.Entry<? extends K, ? extends V> entry : multimap.entries()) {
                changed |= put(entry.getKey(), entry.getValue());
            }
            return changed;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<V> replaceValues(K key, Iterable<? extends V> values) {
            return this.delegate.replaceValues(key, MapConstraints.checkValues(key, values, this.constraint));
        }
    }

    /* access modifiers changed from: private */
    public static class ConstrainedAsMapValues<K, V> extends ForwardingCollection<Collection<V>> {
        final Collection<Collection<V>> delegate;
        final Set<Map.Entry<K, Collection<V>>> entrySet;

        ConstrainedAsMapValues(Collection<Collection<V>> delegate2, Set<Map.Entry<K, Collection<V>>> entrySet2) {
            this.delegate = delegate2;
            this.entrySet = entrySet2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Collection<Collection<V>> delegate() {
            return this.delegate;
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<Collection<V>> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> iterator = this.entrySet.iterator();
            return new Iterator<Collection<V>>() {
                /* class com.google.common.collect.MapConstraints.ConstrainedAsMapValues.AnonymousClass1 */

                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override // java.util.Iterator
                public Collection<V> next() {
                    return (Collection) ((Map.Entry) iterator.next()).getValue();
                }

                public void remove() {
                    iterator.remove();
                }
            };
        }

        @Override // com.google.common.collect.ForwardingCollection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public <T> T[] toArray(T[] array) {
            return (T[]) standardToArray(array);
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean contains(Object o) {
            return standardContains(o);
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean containsAll(Collection<?> c) {
            return standardContainsAll(c);
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean remove(Object o) {
            return standardRemove(o);
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean removeAll(Collection<?> c) {
            return standardRemoveAll(c);
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean retainAll(Collection<?> c) {
            return standardRetainAll(c);
        }
    }

    /* access modifiers changed from: private */
    public static class ConstrainedEntries<K, V> extends ForwardingCollection<Map.Entry<K, V>> {
        final MapConstraint<? super K, ? super V> constraint;
        final Collection<Map.Entry<K, V>> entries;

        ConstrainedEntries(Collection<Map.Entry<K, V>> entries2, MapConstraint<? super K, ? super V> constraint2) {
            this.entries = entries2;
            this.constraint = constraint2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Collection<Map.Entry<K, V>> delegate() {
            return this.entries;
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            final Iterator<Map.Entry<K, V>> iterator = this.entries.iterator();
            return new ForwardingIterator<Map.Entry<K, V>>() {
                /* class com.google.common.collect.MapConstraints.ConstrainedEntries.AnonymousClass1 */

                @Override // com.google.common.collect.ForwardingIterator, java.util.Iterator
                public Map.Entry<K, V> next() {
                    return MapConstraints.constrainedEntry((Map.Entry) iterator.next(), ConstrainedEntries.this.constraint);
                }

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
                public Iterator<Map.Entry<K, V>> delegate() {
                    return iterator;
                }
            };
        }

        @Override // com.google.common.collect.ForwardingCollection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public <T> T[] toArray(T[] array) {
            return (T[]) standardToArray(array);
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean contains(Object o) {
            return Maps.containsEntryImpl(delegate(), o);
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean containsAll(Collection<?> c) {
            return standardContainsAll(c);
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean remove(Object o) {
            return Maps.removeEntryImpl(delegate(), o);
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean removeAll(Collection<?> c) {
            return standardRemoveAll(c);
        }

        @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean retainAll(Collection<?> c) {
            return standardRetainAll(c);
        }
    }

    /* access modifiers changed from: package-private */
    public static class ConstrainedEntrySet<K, V> extends ConstrainedEntries<K, V> implements Set<Map.Entry<K, V>> {
        ConstrainedEntrySet(Set<Map.Entry<K, V>> entries, MapConstraint<? super K, ? super V> constraint) {
            super(entries, constraint);
        }

        public boolean equals(@Nullable Object object) {
            return Sets.equalsImpl(this, object);
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    /* access modifiers changed from: package-private */
    public static class ConstrainedAsMapEntries<K, V> extends ForwardingSet<Map.Entry<K, Collection<V>>> {
        private final MapConstraint<? super K, ? super V> constraint;
        private final Set<Map.Entry<K, Collection<V>>> entries;

        ConstrainedAsMapEntries(Set<Map.Entry<K, Collection<V>>> entries2, MapConstraint<? super K, ? super V> constraint2) {
            this.entries = entries2;
            this.constraint = constraint2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Set<Map.Entry<K, Collection<V>>> delegate() {
            return this.entries;
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> iterator = this.entries.iterator();
            return new ForwardingIterator<Map.Entry<K, Collection<V>>>() {
                /* class com.google.common.collect.MapConstraints.ConstrainedAsMapEntries.AnonymousClass1 */

                @Override // com.google.common.collect.ForwardingIterator, java.util.Iterator
                public Map.Entry<K, Collection<V>> next() {
                    return MapConstraints.constrainedAsMapEntry((Map.Entry) iterator.next(), ConstrainedAsMapEntries.this.constraint);
                }

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
                public Iterator<Map.Entry<K, Collection<V>>> delegate() {
                    return iterator;
                }
            };
        }

        @Override // com.google.common.collect.ForwardingCollection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public <T> T[] toArray(T[] array) {
            return (T[]) standardToArray(array);
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean contains(Object o) {
            return Maps.containsEntryImpl(delegate(), o);
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean containsAll(Collection<?> c) {
            return standardContainsAll(c);
        }

        @Override // com.google.common.collect.ForwardingSet
        public boolean equals(@Nullable Object object) {
            return standardEquals(object);
        }

        @Override // com.google.common.collect.ForwardingSet
        public int hashCode() {
            return standardHashCode();
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean remove(Object o) {
            return Maps.removeEntryImpl(delegate(), o);
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean removeAll(Collection<?> c) {
            return standardRemoveAll(c);
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean retainAll(Collection<?> c) {
            return standardRetainAll(c);
        }
    }

    private static class ConstrainedListMultimap<K, V> extends ConstrainedMultimap<K, V> implements ListMultimap<K, V> {
        ConstrainedListMultimap(ListMultimap<K, V> delegate, MapConstraint<? super K, ? super V> constraint) {
            super(delegate, constraint);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.MapConstraints.ConstrainedMultimap, com.google.common.collect.Multimap
        public List<V> get(K key) {
            return (List) super.get((Object) key);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
        public List<V> removeAll(Object key) {
            return (List) super.removeAll(key);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ListMultimap, com.google.common.collect.MapConstraints.ConstrainedMultimap, com.google.common.collect.Multimap
        public List<V> replaceValues(K key, Iterable<? extends V> values) {
            return (List) super.replaceValues((Object) key, (Iterable) values);
        }
    }

    /* access modifiers changed from: private */
    public static class ConstrainedSetMultimap<K, V> extends ConstrainedMultimap<K, V> implements SetMultimap<K, V> {
        ConstrainedSetMultimap(SetMultimap<K, V> delegate, MapConstraint<? super K, ? super V> constraint) {
            super(delegate, constraint);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.MapConstraints.ConstrainedMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<V> get(K key) {
            return (Set) super.get((Object) key);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.MapConstraints.ConstrainedMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<Map.Entry<K, V>> entries() {
            return (Set) super.entries();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<V> removeAll(Object key) {
            return (Set) super.removeAll(key);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.MapConstraints.ConstrainedMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public Set<V> replaceValues(K key, Iterable<? extends V> values) {
            return (Set) super.replaceValues((Object) key, (Iterable) values);
        }
    }

    private static class ConstrainedSortedSetMultimap<K, V> extends ConstrainedSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        ConstrainedSortedSetMultimap(SortedSetMultimap<K, V> delegate, MapConstraint<? super K, ? super V> constraint) {
            super(delegate, constraint);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.MapConstraints.ConstrainedMultimap, com.google.common.collect.MapConstraints.ConstrainedSetMultimap, com.google.common.collect.MapConstraints.ConstrainedSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public SortedSet<V> get(K key) {
            return (SortedSet) super.get((Object) key);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.MapConstraints.ConstrainedSetMultimap, com.google.common.collect.MapConstraints.ConstrainedSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public SortedSet<V> removeAll(Object key) {
            return (SortedSet) super.removeAll(key);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.SortedSetMultimap, com.google.common.collect.MapConstraints.ConstrainedMultimap, com.google.common.collect.MapConstraints.ConstrainedSetMultimap, com.google.common.collect.MapConstraints.ConstrainedSetMultimap, com.google.common.collect.Multimap, com.google.common.collect.SetMultimap
        public SortedSet<V> replaceValues(K key, Iterable<? extends V> values) {
            return (SortedSet) super.replaceValues((Object) key, (Iterable) values);
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            return ((SortedSetMultimap) delegate()).valueComparator();
        }
    }

    /* access modifiers changed from: private */
    public static <K, V> Collection<V> checkValues(K key, Iterable<? extends V> values, MapConstraint<? super K, ? super V> constraint) {
        Collection<V> copy = Lists.newArrayList(values);
        for (V value : copy) {
            constraint.checkKeyValue(key, value);
        }
        return copy;
    }

    /* access modifiers changed from: private */
    public static <K, V> Map<K, V> checkMap(Map<? extends K, ? extends V> map, MapConstraint<? super K, ? super V> constraint) {
        Map<K, V> copy = new LinkedHashMap<>(map);
        for (Map.Entry<K, V> entry : copy.entrySet()) {
            constraint.checkKeyValue(entry.getKey(), entry.getValue());
        }
        return copy;
    }
}
