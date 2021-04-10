package java.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LinkedHashMap<K, V> extends HashMap<K, V> implements Map<K, V> {
    private static final long serialVersionUID = 3801124242820219131L;
    final boolean accessOrder;
    transient LinkedHashMapEntry<K, V> head;
    transient LinkedHashMapEntry<K, V> tail;

    /* access modifiers changed from: package-private */
    public static class LinkedHashMapEntry<K, V> extends HashMap.Node<K, V> {
        LinkedHashMapEntry<K, V> after;
        LinkedHashMapEntry<K, V> before;

        LinkedHashMapEntry(int hash, K key, V value, HashMap.Node<K, V> next) {
            super(hash, key, value, next);
        }
    }

    private void linkNodeLast(LinkedHashMapEntry<K, V> p) {
        LinkedHashMapEntry<K, V> last = this.tail;
        this.tail = p;
        if (last == null) {
            this.head = p;
            return;
        }
        p.before = last;
        last.after = p;
    }

    private void transferLinks(LinkedHashMapEntry<K, V> src, LinkedHashMapEntry<K, V> dst) {
        LinkedHashMapEntry<K, V> b = src.before;
        dst.before = b;
        LinkedHashMapEntry<K, V> a = src.after;
        dst.after = a;
        if (b == null) {
            this.head = dst;
        } else {
            b.after = dst;
        }
        if (a == null) {
            this.tail = dst;
        } else {
            a.before = dst;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void reinitialize() {
        super.reinitialize();
        this.tail = null;
        this.head = null;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public HashMap.Node<K, V> newNode(int hash, K key, V value, HashMap.Node<K, V> e) {
        LinkedHashMapEntry<K, V> p = new LinkedHashMapEntry<>(hash, key, value, e);
        linkNodeLast(p);
        return p;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public HashMap.Node<K, V> replacementNode(HashMap.Node<K, V> p, HashMap.Node<K, V> next) {
        LinkedHashMapEntry<K, V> q = (LinkedHashMapEntry) p;
        LinkedHashMapEntry<K, V> t = new LinkedHashMapEntry<>(q.hash, q.key, q.value, next);
        transferLinks(q, t);
        return t;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public HashMap.TreeNode<K, V> newTreeNode(int hash, K key, V value, HashMap.Node<K, V> next) {
        HashMap.TreeNode<K, V> p = new HashMap.TreeNode<>(hash, key, value, next);
        linkNodeLast(p);
        return p;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public HashMap.TreeNode<K, V> replacementTreeNode(HashMap.Node<K, V> p, HashMap.Node<K, V> next) {
        LinkedHashMapEntry<K, V> q = (LinkedHashMapEntry) p;
        HashMap.TreeNode<K, V> t = new HashMap.TreeNode<>(q.hash, q.key, q.value, next);
        transferLinks(q, t);
        return t;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void afterNodeRemoval(HashMap.Node<K, V> e) {
        LinkedHashMapEntry<K, V> p = (LinkedHashMapEntry) e;
        LinkedHashMapEntry<K, V> b = p.before;
        LinkedHashMapEntry<K, V> a = p.after;
        p.after = null;
        p.before = null;
        if (b == null) {
            this.head = a;
        } else {
            b.after = a;
        }
        if (a == null) {
            this.tail = b;
        } else {
            a.before = b;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void afterNodeInsertion(boolean evict) {
        LinkedHashMapEntry<K, V> first;
        if (evict && (first = this.head) != null && removeEldestEntry(first)) {
            Object obj = first.key;
            removeNode(hash(obj), obj, null, false, true);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void afterNodeAccess(HashMap.Node<K, V> e) {
        if (this.accessOrder) {
            LinkedHashMapEntry<K, V> linkedHashMapEntry = this.tail;
            LinkedHashMapEntry<K, V> last = linkedHashMapEntry;
            if (linkedHashMapEntry != e) {
                LinkedHashMapEntry<K, V> p = (LinkedHashMapEntry) e;
                LinkedHashMapEntry<K, V> b = p.before;
                LinkedHashMapEntry<K, V> a = p.after;
                p.after = null;
                if (b == null) {
                    this.head = a;
                } else {
                    b.after = a;
                }
                if (a != null) {
                    a.before = b;
                } else {
                    last = b;
                }
                if (last == null) {
                    this.head = p;
                } else {
                    p.before = last;
                    last.after = p;
                }
                this.tail = p;
                this.modCount++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void internalWriteEntries(ObjectOutputStream s) throws IOException {
        for (LinkedHashMapEntry<K, V> e = this.head; e != null; e = e.after) {
            s.writeObject(e.key);
            s.writeObject(e.value);
        }
    }

    public LinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.accessOrder = false;
    }

    public LinkedHashMap(int initialCapacity) {
        super(initialCapacity);
        this.accessOrder = false;
    }

    public LinkedHashMap() {
        this.accessOrder = false;
    }

    public LinkedHashMap(Map<? extends K, ? extends V> m) {
        this.accessOrder = false;
        putMapEntries(m, false);
    }

    public LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder2) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder2;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public boolean containsValue(Object value) {
        for (LinkedHashMapEntry<K, V> e = this.head; e != null; e = e.after) {
            Object obj = e.value;
            if (obj == value) {
                return true;
            }
            if (value != null && value.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public V get(Object key) {
        HashMap.Node<K, V> e = getNode(hash(key), key);
        if (e == null) {
            return null;
        }
        if (this.accessOrder) {
            afterNodeAccess(e);
        }
        return e.value;
    }

    @Override // java.util.Map, java.util.HashMap
    public V getOrDefault(Object key, V defaultValue) {
        HashMap.Node<K, V> e = getNode(hash(key), key);
        if (e == null) {
            return defaultValue;
        }
        if (this.accessOrder) {
            afterNodeAccess(e);
        }
        return e.value;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public void clear() {
        super.clear();
        this.tail = null;
        this.head = null;
    }

    public Map.Entry<K, V> eldest() {
        return this.head;
    }

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        Set<K> ks2 = new LinkedKeySet();
        this.keySet = ks2;
        return ks2;
    }

    final class LinkedKeySet extends AbstractSet<K> {
        LinkedKeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return LinkedHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            LinkedHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public final Iterator<K> iterator() {
            return new LinkedKeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object o) {
            return LinkedHashMap.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object key) {
            return LinkedHashMap.this.removeNode(HashMap.hash(key), key, null, false, true) != null;
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public final Spliterator<K> spliterator() {
            return Spliterators.spliterator(this, 81);
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer<? super K> action) {
            if (action != null) {
                int mc = LinkedHashMap.this.modCount;
                for (LinkedHashMapEntry<K, V> e = LinkedHashMap.this.head; e != null && LinkedHashMap.this.modCount == mc; e = e.after) {
                    action.accept((Object) e.key);
                }
                if (LinkedHashMap.this.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Collection<V> vs2 = new LinkedValues();
        this.values = vs2;
        return vs2;
    }

    final class LinkedValues extends AbstractCollection<V> {
        LinkedValues() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final int size() {
            return LinkedHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final void clear() {
            LinkedHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            return new LinkedValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object o) {
            return LinkedHashMap.this.containsValue(o);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Spliterator<V> spliterator() {
            return Spliterators.spliterator(this, 80);
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer<? super V> action) {
            if (action != null) {
                int mc = LinkedHashMap.this.modCount;
                for (LinkedHashMapEntry<K, V> e = LinkedHashMap.this.head; e != null && LinkedHashMap.this.modCount == mc; e = e.after) {
                    action.accept((Object) e.value);
                }
                if (LinkedHashMap.this.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        LinkedEntrySet linkedEntrySet = new LinkedEntrySet();
        this.entrySet = linkedEntrySet;
        return linkedEntrySet;
    }

    final class LinkedEntrySet extends AbstractSet<Map.Entry<K, V>> {
        LinkedEntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return LinkedHashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            LinkedHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            Object key = e.getKey();
            HashMap.Node<K, V> candidate = LinkedHashMap.this.getNode(HashMap.hash(key), key);
            if (candidate == null || !candidate.equals(e)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            Object key = e.getKey();
            if (LinkedHashMap.this.removeNode(HashMap.hash(key), key, e.getValue(), true, true) != null) {
                return true;
            }
            return false;
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public final Spliterator<Map.Entry<K, V>> spliterator() {
            return Spliterators.spliterator(this, 81);
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                int mc = LinkedHashMap.this.modCount;
                for (LinkedHashMapEntry<K, V> e = LinkedHashMap.this.head; e != null && mc == LinkedHashMap.this.modCount; e = e.after) {
                    action.accept(e);
                }
                if (LinkedHashMap.this.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    @Override // java.util.Map, java.util.HashMap
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action != null) {
            int mc = this.modCount;
            LinkedHashMapEntry<K, V> e = this.head;
            while (this.modCount == mc && e != null) {
                action.accept((Object) e.key, (Object) e.value);
                e = e.after;
            }
            if (this.modCount != mc) {
                throw new ConcurrentModificationException();
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.HashMap
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        if (function != null) {
            int mc = this.modCount;
            LinkedHashMapEntry<K, V> e = this.head;
            while (this.modCount == mc && e != null) {
                e.value = function.apply((Object) e.key, (Object) e.value);
                e = e.after;
            }
            if (this.modCount != mc) {
                throw new ConcurrentModificationException();
            }
            return;
        }
        throw new NullPointerException();
    }

    abstract class LinkedHashIterator {
        LinkedHashMapEntry<K, V> current = null;
        int expectedModCount;
        LinkedHashMapEntry<K, V> next;

        LinkedHashIterator() {
            this.next = LinkedHashMap.this.head;
            this.expectedModCount = LinkedHashMap.this.modCount;
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: package-private */
        public final LinkedHashMapEntry<K, V> nextNode() {
            LinkedHashMapEntry<K, V> e = this.next;
            if (LinkedHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (e != null) {
                this.current = e;
                this.next = e.after;
                return e;
            } else {
                throw new NoSuchElementException();
            }
        }

        public final void remove() {
            HashMap.Node<K, V> p = this.current;
            if (p == null) {
                throw new IllegalStateException();
            } else if (LinkedHashMap.this.modCount == this.expectedModCount) {
                this.current = null;
                K key = p.key;
                LinkedHashMap.this.removeNode(HashMap.hash(key), key, null, false, false);
                this.expectedModCount = LinkedHashMap.this.modCount;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    final class LinkedKeyIterator extends LinkedHashMap<K, V>.LinkedHashIterator implements Iterator<K> {
        LinkedKeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final K next() {
            return nextNode().getKey();
        }
    }

    final class LinkedValueIterator extends LinkedHashMap<K, V>.LinkedHashIterator implements Iterator<V> {
        LinkedValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final V next() {
            return (V) nextNode().value;
        }
    }

    final class LinkedEntryIterator extends LinkedHashMap<K, V>.LinkedHashIterator implements Iterator<Map.Entry<K, V>> {
        LinkedEntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Map.Entry<K, V> next() {
            return nextNode();
        }
    }
}
