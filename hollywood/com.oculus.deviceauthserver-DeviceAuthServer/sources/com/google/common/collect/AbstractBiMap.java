package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public abstract class AbstractBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
    @GwtIncompatible("Not needed in emulated source.")
    private static final long serialVersionUID = 0;
    private transient Map<K, V> delegate;
    private transient Set<Map.Entry<K, V>> entrySet;
    transient AbstractBiMap<V, K> inverse;
    private transient Set<K> keySet;
    private transient Set<V> valueSet;

    AbstractBiMap(Map<K, V> forward, Map<V, K> backward) {
        setDelegates(forward, backward);
    }

    private AbstractBiMap(Map<K, V> backward, AbstractBiMap<V, K> forward) {
        this.delegate = backward;
        this.inverse = forward;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<K, V> delegate() {
        return this.delegate;
    }

    /* access modifiers changed from: package-private */
    public K checkKey(@Nullable K key) {
        return key;
    }

    /* access modifiers changed from: package-private */
    public V checkValue(@Nullable V value) {
        return value;
    }

    /* access modifiers changed from: package-private */
    public void setDelegates(Map<K, V> forward, Map<V, K> backward) {
        boolean z = true;
        Preconditions.checkState(this.delegate == null);
        Preconditions.checkState(this.inverse == null);
        Preconditions.checkArgument(forward.isEmpty());
        Preconditions.checkArgument(backward.isEmpty());
        if (forward == backward) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.delegate = forward;
        this.inverse = new Inverse(backward, this);
    }

    /* access modifiers changed from: package-private */
    public void setInverse(AbstractBiMap<V, K> inverse2) {
        this.inverse = inverse2;
    }

    @Override // com.google.common.collect.ForwardingMap
    public boolean containsValue(@Nullable Object value) {
        return this.inverse.containsKey(value);
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, java.util.Map
    public V put(@Nullable K key, @Nullable V value) {
        return putInBothMaps(key, value, false);
    }

    @Override // com.google.common.collect.BiMap
    public V forcePut(@Nullable K key, @Nullable V value) {
        return putInBothMaps(key, value, true);
    }

    private V putInBothMaps(@Nullable K key, @Nullable V value, boolean force) {
        checkKey(key);
        checkValue(value);
        boolean containedKey = containsKey(key);
        if (containedKey && Objects.equal(value, get(key))) {
            return value;
        }
        if (force) {
            inverse().remove(value);
        } else {
            Preconditions.checkArgument(!containsValue(value), "value already present: %s", value);
        }
        V oldValue = this.delegate.put(key, value);
        updateInverseMap(key, containedKey, oldValue, value);
        return oldValue;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateInverseMap(K key, boolean containedKey, V oldValue, V newValue) {
        if (containedKey) {
            removeFromInverseMap(oldValue);
        }
        this.inverse.delegate.put(newValue, key);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public V remove(@Nullable Object key) {
        if (containsKey(key)) {
            return removeFromBothMaps(key);
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private V removeFromBothMaps(Object key) {
        V oldValue = this.delegate.remove(key);
        removeFromInverseMap(oldValue);
        return oldValue;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeFromInverseMap(V oldValue) {
        this.inverse.delegate.remove(oldValue);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.AbstractBiMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.common.collect.ForwardingMap
    public void clear() {
        this.delegate.clear();
        this.inverse.delegate.clear();
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        return this.inverse;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<K> keySet() {
        Set<K> result = this.keySet;
        if (result != null) {
            return result;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    /* access modifiers changed from: private */
    public class KeySet extends ForwardingSet<K> {
        private KeySet() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Set<K> delegate() {
            return AbstractBiMap.this.delegate.keySet();
        }

        @Override // com.google.common.collect.ForwardingCollection
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean remove(Object key) {
            if (!contains(key)) {
                return false;
            }
            AbstractBiMap.this.removeFromBothMaps(key);
            return true;
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean removeAll(Collection<?> keysToRemove) {
            return standardRemoveAll(keysToRemove);
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean retainAll(Collection<?> keysToRetain) {
            return standardRetainAll(keysToRetain);
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<K> iterator() {
            return Maps.keyIterator(AbstractBiMap.this.entrySet().iterator());
        }
    }

    @Override // com.google.common.collect.BiMap, com.google.common.collect.ForwardingMap, java.util.Map
    public Set<V> values() {
        Set<V> result = this.valueSet;
        if (result != null) {
            return result;
        }
        ValueSet valueSet2 = new ValueSet();
        this.valueSet = valueSet2;
        return valueSet2;
    }

    /* access modifiers changed from: private */
    public class ValueSet extends ForwardingSet<V> {
        final Set<V> valuesDelegate;

        private ValueSet() {
            this.valuesDelegate = AbstractBiMap.this.inverse.keySet();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Set<V> delegate() {
            return this.valuesDelegate;
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<V> iterator() {
            return Maps.valueIterator(AbstractBiMap.this.entrySet().iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public <T> T[] toArray(T[] array) {
            return (T[]) standardToArray(array);
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> result = this.entrySet;
        if (result != null) {
            return result;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    /* access modifiers changed from: private */
    public class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
        final Set<Map.Entry<K, V>> esDelegate;

        private EntrySet() {
            this.esDelegate = AbstractBiMap.this.delegate.entrySet();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
        public Set<Map.Entry<K, V>> delegate() {
            return this.esDelegate;
        }

        @Override // com.google.common.collect.ForwardingCollection
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.ForwardingCollection
        public boolean remove(Object object) {
            if (!this.esDelegate.contains(object)) {
                return false;
            }
            Map.Entry<?, ?> entry = (Map.Entry) object;
            ((AbstractBiMap) AbstractBiMap.this.inverse).delegate.remove(entry.getValue());
            this.esDelegate.remove(entry);
            return true;
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            final Iterator<Map.Entry<K, V>> iterator = this.esDelegate.iterator();
            return new Iterator<Map.Entry<K, V>>() {
                /* class com.google.common.collect.AbstractBiMap.EntrySet.AnonymousClass1 */
                Map.Entry<K, V> entry;

                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    this.entry = (Map.Entry) iterator.next();
                    final Map.Entry<K, V> finalEntry = this.entry;
                    return new ForwardingMapEntry<K, V>() {
                        /* class com.google.common.collect.AbstractBiMap.EntrySet.AnonymousClass1.AnonymousClass1 */

                        /* access modifiers changed from: protected */
                        @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingMapEntry
                        public Map.Entry<K, V> delegate() {
                            return finalEntry;
                        }

                        @Override // java.util.Map.Entry, com.google.common.collect.ForwardingMapEntry
                        public V setValue(V value) {
                            Preconditions.checkState(EntrySet.this.contains(this), "entry no longer in map");
                            if (Objects.equal(value, getValue())) {
                                return value;
                            }
                            Preconditions.checkArgument(!AbstractBiMap.this.containsValue(value), "value already present: %s", value);
                            V oldValue = (V) finalEntry.setValue(value);
                            Preconditions.checkState(Objects.equal(value, AbstractBiMap.this.get(getKey())), "entry no longer in map");
                            AbstractBiMap.this.updateInverseMap(getKey(), true, oldValue, value);
                            return oldValue;
                        }
                    };
                }

                public void remove() {
                    CollectPreconditions.checkRemove(this.entry != null);
                    V value = this.entry.getValue();
                    iterator.remove();
                    AbstractBiMap.this.removeFromInverseMap(value);
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

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean removeAll(Collection<?> c) {
            return standardRemoveAll(c);
        }

        @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
        public boolean retainAll(Collection<?> c) {
            return standardRetainAll(c);
        }
    }

    /* access modifiers changed from: private */
    public static class Inverse<K, V> extends AbstractBiMap<K, V> {
        @GwtIncompatible("Not needed in emulated source.")
        private static final long serialVersionUID = 0;

        private Inverse(Map<K, V> backward, AbstractBiMap<V, K> forward) {
            super(backward, forward);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractBiMap
        public K checkKey(K key) {
            return (K) this.inverse.checkValue(key);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractBiMap
        public V checkValue(V value) {
            return (V) this.inverse.checkKey(value);
        }

        @GwtIncompatible("java.io.ObjectOuputStream")
        private void writeObject(ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
            stream.writeObject(inverse());
        }

        @GwtIncompatible("java.io.ObjectInputStream")
        private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
            stream.defaultReadObject();
            setInverse((AbstractBiMap) stream.readObject());
        }

        /* access modifiers changed from: package-private */
        @GwtIncompatible("Not needed in the emulated source.")
        public Object readResolve() {
            return inverse().inverse();
        }
    }
}
