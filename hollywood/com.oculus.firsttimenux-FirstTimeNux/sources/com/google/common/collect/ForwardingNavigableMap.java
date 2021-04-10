package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;

@GwtIncompatible
public abstract class ForwardingNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingSortedMap
    public abstract NavigableMap<K, V> delegate();

    protected ForwardingNavigableMap() {
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K key) {
        return delegate().lowerEntry(key);
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardLowerEntry(K key) {
        return headMap(key, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K key) {
        return delegate().lowerKey(key);
    }

    /* access modifiers changed from: protected */
    public K standardLowerKey(K key) {
        return (K) Maps.keyOrNull(lowerEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K key) {
        return delegate().floorEntry(key);
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardFloorEntry(K key) {
        return headMap(key, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K floorKey(K key) {
        return delegate().floorKey(key);
    }

    /* access modifiers changed from: protected */
    public K standardFloorKey(K key) {
        return (K) Maps.keyOrNull(floorEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K key) {
        return delegate().ceilingEntry(key);
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardCeilingEntry(K key) {
        return tailMap(key, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K key) {
        return delegate().ceilingKey(key);
    }

    /* access modifiers changed from: protected */
    public K standardCeilingKey(K key) {
        return (K) Maps.keyOrNull(ceilingEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K key) {
        return delegate().higherEntry(key);
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardHigherEntry(K key) {
        return tailMap(key, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K higherKey(K key) {
        return delegate().higherKey(key);
    }

    /* access modifiers changed from: protected */
    public K standardHigherKey(K key) {
        return (K) Maps.keyOrNull(higherEntry(key));
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        return delegate().firstEntry();
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardFirstEntry() {
        return (Map.Entry) Iterables.getFirst(entrySet(), null);
    }

    /* access modifiers changed from: protected */
    public K standardFirstKey() {
        Map.Entry<K, V> entry = firstEntry();
        if (entry != null) {
            return entry.getKey();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        return delegate().lastEntry();
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardLastEntry() {
        return (Map.Entry) Iterables.getFirst(descendingMap().entrySet(), null);
    }

    /* access modifiers changed from: protected */
    public K standardLastKey() {
        Map.Entry<K, V> entry = lastEntry();
        if (entry != null) {
            return entry.getKey();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollFirstEntry() {
        return delegate().pollFirstEntry();
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardPollFirstEntry() {
        return (Map.Entry) Iterators.pollNext(entrySet().iterator());
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollLastEntry() {
        return delegate().pollLastEntry();
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> standardPollLastEntry() {
        return (Map.Entry) Iterators.pollNext(descendingMap().entrySet().iterator());
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> descendingMap() {
        return delegate().descendingMap();
    }

    /* access modifiers changed from: protected */
    @Beta
    public class StandardDescendingMap extends Maps.DescendingMap<K, V> {
        public StandardDescendingMap() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.DescendingMap
        public NavigableMap<K, V> forward() {
            return ForwardingNavigableMap.this;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.Maps.DescendingMap
        public Iterator<Map.Entry<K, V>> entryIterator() {
            return new Iterator<Map.Entry<K, V>>() {
                /* class com.google.common.collect.ForwardingNavigableMap.StandardDescendingMap.AnonymousClass1 */
                private Map.Entry<K, V> nextOrNull = StandardDescendingMap.this.forward().lastEntry();
                private Map.Entry<K, V> toRemove = null;

                public boolean hasNext() {
                    return this.nextOrNull != null;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    try {
                        return this.nextOrNull;
                    } finally {
                        this.toRemove = this.nextOrNull;
                        this.nextOrNull = StandardDescendingMap.this.forward().lowerEntry(this.nextOrNull.getKey());
                    }
                }

                public void remove() {
                    CollectPreconditions.checkRemove(this.toRemove != null);
                    StandardDescendingMap.this.forward().remove(this.toRemove.getKey());
                    this.toRemove = null;
                }
            };
        }
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> navigableKeySet() {
        return delegate().navigableKeySet();
    }

    @Beta
    protected class StandardNavigableKeySet extends Maps.NavigableKeySet<K, V> {
        public StandardNavigableKeySet() {
            super(ForwardingNavigableMap.this);
        }
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> descendingKeySet() {
        return delegate().descendingKeySet();
    }

    /* access modifiers changed from: protected */
    @Beta
    public NavigableSet<K> standardDescendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedMap
    public SortedMap<K, V> standardSubMap(K fromKey, K toKey) {
        return subMap(fromKey, true, toKey, false);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return delegate().subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return delegate().headMap(toKey, inclusive);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return delegate().tailMap(fromKey, inclusive);
    }

    /* access modifiers changed from: protected */
    public SortedMap<K, V> standardHeadMap(K toKey) {
        return headMap(toKey, false);
    }

    /* access modifiers changed from: protected */
    public SortedMap<K, V> standardTailMap(K fromKey) {
        return tailMap(fromKey, true);
    }
}
