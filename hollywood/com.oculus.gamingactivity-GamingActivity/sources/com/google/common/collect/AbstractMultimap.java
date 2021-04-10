package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimaps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
    @MonotonicNonNullDecl
    private transient Map<K, Collection<V>> asMap;
    @MonotonicNonNullDecl
    private transient Collection<Map.Entry<K, V>> entries;
    @MonotonicNonNullDecl
    private transient Set<K> keySet;
    @MonotonicNonNullDecl
    private transient Multiset<K> keys;
    @MonotonicNonNullDecl
    private transient Collection<V> values;

    /* access modifiers changed from: package-private */
    public abstract Map<K, Collection<V>> createAsMap();

    /* access modifiers changed from: package-private */
    public abstract Collection<Map.Entry<K, V>> createEntries();

    /* access modifiers changed from: package-private */
    public abstract Set<K> createKeySet();

    /* access modifiers changed from: package-private */
    public abstract Multiset<K> createKeys();

    /* access modifiers changed from: package-private */
    public abstract Collection<V> createValues();

    /* access modifiers changed from: package-private */
    public abstract Iterator<Map.Entry<K, V>> entryIterator();

    AbstractMultimap() {
    }

    @Override // com.google.common.collect.Multimap
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsValue(@NullableDecl Object value) {
        for (Collection<V> collection : asMap().values()) {
            if (collection.contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsEntry(@NullableDecl Object key, @NullableDecl Object value) {
        Collection<V> collection = asMap().get(key);
        return collection != null && collection.contains(value);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object key, @NullableDecl Object value) {
        Collection<V> collection = asMap().get(key);
        return collection != null && collection.remove(value);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean put(@NullableDecl K key, @NullableDecl V value) {
        return get(key).add(value);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(@NullableDecl K key, Iterable<? extends V> values2) {
        Preconditions.checkNotNull(values2);
        if (values2 instanceof Collection) {
            Collection<? extends V> valueCollection = (Collection) values2;
            return !valueCollection.isEmpty() && get(key).addAll(valueCollection);
        }
        Iterator<? extends V> valueItr = values2.iterator();
        return valueItr.hasNext() && Iterators.addAll(get(key), valueItr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.collect.AbstractMultimap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        boolean changed = false;
        for (Map.Entry<? extends K, ? extends V> entry : multimap.entries()) {
            changed |= put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public Collection<V> replaceValues(@NullableDecl K key, Iterable<? extends V> values2) {
        Preconditions.checkNotNull(values2);
        Collection<V> result = removeAll(key);
        putAll(key, values2);
        return result;
    }

    @Override // com.google.common.collect.Multimap
    public Collection<Map.Entry<K, V>> entries() {
        Collection<Map.Entry<K, V>> result = this.entries;
        if (result != null) {
            return result;
        }
        Collection<Map.Entry<K, V>> result2 = createEntries();
        this.entries = result2;
        return result2;
    }

    class Entries extends Multimaps.Entries<K, V> {
        Entries() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multimaps.Entries
        public Multimap<K, V> multimap() {
            return AbstractMultimap.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractMultimap.this.entryIterator();
        }
    }

    class EntrySet extends AbstractMultimap<K, V>.Entries implements Set<Map.Entry<K, V>> {
        EntrySet() {
            super();
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }

        public boolean equals(@NullableDecl Object obj) {
            return Sets.equalsImpl(this, obj);
        }
    }

    @Override // com.google.common.collect.Multimap
    public Set<K> keySet() {
        Set<K> result = this.keySet;
        if (result != null) {
            return result;
        }
        Set<K> result2 = createKeySet();
        this.keySet = result2;
        return result2;
    }

    @Override // com.google.common.collect.Multimap
    public Multiset<K> keys() {
        Multiset<K> result = this.keys;
        if (result != null) {
            return result;
        }
        Multiset<K> result2 = createKeys();
        this.keys = result2;
        return result2;
    }

    @Override // com.google.common.collect.Multimap
    public Collection<V> values() {
        Collection<V> result = this.values;
        if (result != null) {
            return result;
        }
        Collection<V> result2 = createValues();
        this.values = result2;
        return result2;
    }

    class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return AbstractMultimap.this.valueIterator();
        }

        public int size() {
            return AbstractMultimap.this.size();
        }

        public boolean contains(@NullableDecl Object o) {
            return AbstractMultimap.this.containsValue(o);
        }

        public void clear() {
            AbstractMultimap.this.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> valueIterator() {
        return Maps.valueIterator(entries().iterator());
    }

    @Override // com.google.common.collect.Multimap
    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> result = this.asMap;
        if (result != null) {
            return result;
        }
        Map<K, Collection<V>> result2 = createAsMap();
        this.asMap = result2;
        return result2;
    }

    @Override // com.google.common.collect.Multimap
    public boolean equals(@NullableDecl Object object) {
        return Multimaps.equalsImpl(this, object);
    }

    @Override // com.google.common.collect.Multimap
    public int hashCode() {
        return asMap().hashCode();
    }

    public String toString() {
        return asMap().toString();
    }
}
