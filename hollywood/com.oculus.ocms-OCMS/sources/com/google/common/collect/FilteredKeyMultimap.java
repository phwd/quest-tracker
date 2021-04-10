package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public class FilteredKeyMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Predicate<? super K> keyPredicate;
    final Multimap<K, V> unfiltered;

    FilteredKeyMultimap(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        this.unfiltered = (Multimap) Preconditions.checkNotNull(multimap);
        this.keyPredicate = (Predicate) Preconditions.checkNotNull(predicate);
    }

    @Override // com.google.common.collect.FilteredMultimap
    public Multimap<K, V> unfiltered() {
        return this.unfiltered;
    }

    @Override // com.google.common.collect.FilteredMultimap
    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return Maps.keyPredicateOnEntries(this.keyPredicate);
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        int i = 0;
        for (Collection<V> collection : asMap().values()) {
            i += collection.size();
        }
        return i;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        if (this.unfiltered.containsKey(obj)) {
            return this.keyPredicate.apply(obj);
        }
        return false;
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> removeAll(Object obj) {
        return containsKey(obj) ? this.unfiltered.removeAll(obj) : unmodifiableEmptyCollection();
    }

    /* access modifiers changed from: package-private */
    public Collection<V> unmodifiableEmptyCollection() {
        if (this.unfiltered instanceof SetMultimap) {
            return ImmutableSet.of();
        }
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        keySet().clear();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Set<K> createKeySet() {
        return Sets.filter(this.unfiltered.keySet(), this.keyPredicate);
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> get(K k) {
        if (this.keyPredicate.apply(k)) {
            return this.unfiltered.get(k);
        }
        if (this.unfiltered instanceof SetMultimap) {
            return new AddRejectingSet(k);
        }
        return new AddRejectingList(k);
    }

    static class AddRejectingSet<K, V> extends ForwardingSet<V> {
        final K key;

        AddRejectingSet(K k) {
            this.key = k;
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean add(V v) {
            throw new IllegalArgumentException("Key does not satisfy predicate: " + ((Object) this.key));
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean addAll(Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + ((Object) this.key));
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Set<V> delegate() {
            return Collections.emptySet();
        }
    }

    static class AddRejectingList<K, V> extends ForwardingList<V> {
        final K key;

        AddRejectingList(K k) {
            this.key = k;
        }

        @Override // java.util.List, java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean add(V v) {
            add(0, v);
            return true;
        }

        @Override // java.util.List, com.google.common.collect.ForwardingList
        public void add(int i, V v) {
            Preconditions.checkPositionIndex(i, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + ((Object) this.key));
        }

        @Override // java.util.List, java.util.Collection, com.google.common.collect.ForwardingCollection
        public boolean addAll(Collection<? extends V> collection) {
            addAll(0, collection);
            return true;
        }

        @Override // java.util.List, com.google.common.collect.ForwardingList
        @CanIgnoreReturnValue
        public boolean addAll(int i, Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            Preconditions.checkPositionIndex(i, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + ((Object) this.key));
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingList, com.google.common.collect.ForwardingList, com.google.common.collect.ForwardingList, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public List<V> delegate() {
            return Collections.emptyList();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Collection<Map.Entry<K, V>> createEntries() {
        return new Entries();
    }

    class Entries extends ForwardingCollection<Map.Entry<K, V>> {
        Entries() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Collection<Map.Entry<K, V>> delegate() {
            return Collections2.filter(FilteredKeyMultimap.this.unfiltered.entries(), FilteredKeyMultimap.this.entryPredicate());
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean remove(@NullableDecl Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!FilteredKeyMultimap.this.unfiltered.containsKey(entry.getKey()) || !FilteredKeyMultimap.this.keyPredicate.apply((Object) entry.getKey())) {
                return false;
            }
            return FilteredKeyMultimap.this.unfiltered.remove(entry.getKey(), entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Collection<V> createValues() {
        return new FilteredMultimapValues(this);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Map<K, Collection<V>> createAsMap() {
        return Maps.filterKeys(this.unfiltered.asMap(), this.keyPredicate);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Multiset<K> createKeys() {
        return Multisets.filter(this.unfiltered.keys(), this.keyPredicate);
    }
}
