package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class ForwardingMap<K, V> extends ForwardingObject implements Map<K, V> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public abstract Map<K, V> delegate();

    protected ForwardingMap() {
    }

    public int size() {
        return delegate().size();
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    public V remove(Object object) {
        return delegate().remove(object);
    }

    public void clear() {
        delegate().clear();
    }

    public boolean containsKey(@NullableDecl Object key) {
        return delegate().containsKey(key);
    }

    public boolean containsValue(@NullableDecl Object value) {
        return delegate().containsValue(value);
    }

    @Override // java.util.Map
    public V get(@NullableDecl Object key) {
        return delegate().get(key);
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    public V put(K key, V value) {
        return delegate().put(key, value);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return delegate().keySet();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return delegate().values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return delegate().entrySet();
    }

    public boolean equals(@NullableDecl Object object) {
        return object == this || delegate().equals(object);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    /* access modifiers changed from: protected */
    public void standardPutAll(Map<? extends K, ? extends V> map) {
        Maps.putAllImpl(this, map);
    }

    /* access modifiers changed from: protected */
    @Beta
    public V standardRemove(@NullableDecl Object key) {
        Iterator<Map.Entry<K, V>> entryIterator = entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<K, V> entry = entryIterator.next();
            if (Objects.equal(entry.getKey(), key)) {
                V value = entry.getValue();
                entryIterator.remove();
                return value;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void standardClear() {
        Iterators.clear(entrySet().iterator());
    }

    @Beta
    protected class StandardKeySet extends Maps.KeySet<K, V> {
        public StandardKeySet() {
            super(ForwardingMap.this);
        }
    }

    /* access modifiers changed from: protected */
    @Beta
    public boolean standardContainsKey(@NullableDecl Object key) {
        return Maps.containsKeyImpl(this, key);
    }

    @Beta
    protected class StandardValues extends Maps.Values<K, V> {
        public StandardValues() {
            super(ForwardingMap.this);
        }
    }

    /* access modifiers changed from: protected */
    public boolean standardContainsValue(@NullableDecl Object value) {
        return Maps.containsValueImpl(this, value);
    }

    @Beta
    protected abstract class StandardEntrySet extends Maps.EntrySet<K, V> {
        public StandardEntrySet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.EntrySet
        public Map<K, V> map() {
            return ForwardingMap.this;
        }
    }

    /* access modifiers changed from: protected */
    public boolean standardIsEmpty() {
        return !entrySet().iterator().hasNext();
    }

    /* access modifiers changed from: protected */
    public boolean standardEquals(@NullableDecl Object object) {
        return Maps.equalsImpl(this, object);
    }

    /* access modifiers changed from: protected */
    public int standardHashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    /* access modifiers changed from: protected */
    public String standardToString() {
        return Maps.toStringImpl(this);
    }
}
