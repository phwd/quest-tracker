package java.util;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MIN_TREEIFY_CAPACITY = 64;
    static final int TREEIFY_THRESHOLD = 8;
    static final int UNTREEIFY_THRESHOLD = 6;
    private static final long serialVersionUID = 362498820763181265L;
    transient Set<Map.Entry<K, V>> entrySet;
    final float loadFactor;
    transient int modCount;
    transient int size;
    transient Node<K, V>[] table;
    int threshold;

    /* access modifiers changed from: package-private */
    public static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        Node<K, V> next;
        V value;

        Node(int hash2, K key2, V value2, Node<K, V> next2) {
            this.hash = hash2;
            this.key = key2;
            this.value = value2;
            this.next = next2;
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            return this.value;
        }

        public final String toString() {
            return ((Object) this.key) + "=" + ((Object) this.value);
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);
        }

        @Override // java.util.Map.Entry
        public final V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            if (!Objects.equals(this.key, e.getKey()) || !Objects.equals(this.value, e.getValue())) {
                return false;
            }
            return true;
        }
    }

    static final int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    static Class<?> comparableClassFor(Object x) {
        Type[] as;
        if (!(x instanceof Comparable)) {
            return null;
        }
        Class<?> c = x.getClass();
        if (c == String.class) {
            return c;
        }
        Type[] ts = c.getGenericInterfaces();
        if (ts == null) {
            return null;
        }
        for (Type t : ts) {
            if (t instanceof ParameterizedType) {
                ParameterizedType p = (ParameterizedType) t;
                if (p.getRawType() == Comparable.class && (as = p.getActualTypeArguments()) != null && as.length == 1 && as[0] == c) {
                    return c;
                }
            }
        }
        return null;
    }

    static int compareComparables(Class<?> kc, Object k, Object x) {
        if (x == null || x.getClass() != kc) {
            return 0;
        }
        return ((Comparable) k).compareTo(x);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        int n2 = n | (n >>> 1);
        int n3 = n2 | (n2 >>> 2);
        int n4 = n3 | (n3 >>> 4);
        int n5 = n4 | (n4 >>> 8);
        int n6 = n5 | (n5 >>> 16);
        if (n6 < 0) {
            return 1;
        }
        if (n6 >= 1073741824) {
            return 1073741824;
        }
        return n6 + 1;
    }

    public HashMap(int initialCapacity, float loadFactor2) {
        if (initialCapacity >= 0) {
            initialCapacity = initialCapacity > 1073741824 ? 1073741824 : initialCapacity;
            if (loadFactor2 <= 0.0f || Float.isNaN(loadFactor2)) {
                throw new IllegalArgumentException("Illegal load factor: " + loadFactor2);
            }
            this.loadFactor = loadFactor2;
            this.threshold = tableSizeFor(initialCapacity);
            return;
        }
        throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: java.util.HashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
        int s = m.size();
        if (s > 0) {
            if (this.table == null) {
                float ft = (((float) s) / this.loadFactor) + 1.0f;
                int t = ft < 1.07374182E9f ? (int) ft : 1073741824;
                if (t > this.threshold) {
                    this.threshold = tableSizeFor(t);
                }
            } else if (s > this.threshold) {
                resize();
            }
            for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
                Object key = e.getKey();
                putVal(hash(key), key, e.getValue(), false, evict);
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        Node<K, V> e = getNode(hash(key), key);
        if (e == null) {
            return null;
        }
        return e.value;
    }

    /* access modifiers changed from: package-private */
    public final Node<K, V> getNode(int hash, Object key) {
        int n;
        Node<K, V> first;
        Node<K, V> node;
        K k;
        K k2;
        Node<K, V>[] tab = this.table;
        if (tab == null || (n = tab.length) <= 0 || (first = tab[(n - 1) & hash]) == null) {
            return null;
        }
        if (first.hash == hash && ((k2 = first.key) == key || (key != null && key.equals(k2)))) {
            return first;
        }
        Node<K, V> node2 = first.next;
        Node<K, V> e = node2;
        if (node2 == null) {
            return null;
        }
        if (first instanceof TreeNode) {
            return ((TreeNode) first).getTreeNode(hash, key);
        }
        do {
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                return e;
            }
            node = e.next;
            e = node;
        } while (node != null);
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /* access modifiers changed from: package-private */
    public final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        int n;
        Node<K, V>[] tab;
        Node<K, V> e;
        Node<K, V> e2;
        K k;
        K k2;
        int n2;
        Node<K, V>[] tab2 = this.table;
        if (tab2 == null || (n2 = tab2.length) == 0) {
            Node<K, V>[] tab3 = resize();
            tab = tab3;
            n = tab3.length;
        } else {
            tab = tab2;
            n = n2;
        }
        int i = (n - 1) & hash;
        Node<K, V> node = tab[i];
        Node<K, V> p = node;
        if (node == null) {
            tab[i] = newNode(hash, key, value, null);
        } else {
            if (p.hash == hash && ((k2 = p.key) == key || (key != null && key.equals(k2)))) {
                e = p;
            } else if (p instanceof TreeNode) {
                e = ((TreeNode) p).putTreeVal(this, tab, hash, key, value);
            } else {
                int binCount = 0;
                while (true) {
                    e2 = p.next;
                    if (e2 != null) {
                        if (e2.hash == hash && ((k = e2.key) == key || (key != null && key.equals(k)))) {
                            break;
                        }
                        p = e2;
                        binCount++;
                    } else {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= 7) {
                            treeifyBin(tab, hash);
                        }
                    }
                }
                e = e2;
            }
            if (e != null) {
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
                afterNodeAccess(e);
                return oldValue;
            }
        }
        this.modCount++;
        int i2 = this.size + 1;
        this.size = i2;
        if (i2 > this.threshold) {
            resize();
        }
        afterNodeInsertion(evict);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Node<K, V>[] resize() {
        int newCap;
        Node<K, V> next;
        Node<K, V>[] oldTab = this.table;
        int oldCap = oldTab == null ? 0 : oldTab.length;
        int oldThr = this.threshold;
        int newThr = 0;
        int i = Integer.MAX_VALUE;
        if (oldCap > 0) {
            if (oldCap >= 1073741824) {
                this.threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            int i2 = oldCap << 1;
            newCap = i2;
            if (i2 < 1073741824 && oldCap >= 16) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = 16;
            newThr = 12;
        }
        if (newThr == 0) {
            float ft = ((float) newCap) * this.loadFactor;
            if (newCap < 1073741824 && ft < 1.07374182E9f) {
                i = (int) ft;
            }
            newThr = i;
        }
        this.threshold = newThr;
        Node<K, V>[] newTab = new Node[newCap];
        this.table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; j++) {
                Node<K, V> node = oldTab[j];
                Node<K, V> e = node;
                if (node != null) {
                    oldTab[j] = null;
                    if (e.next == null) {
                        newTab[e.hash & (newCap - 1)] = e;
                    } else if (e instanceof TreeNode) {
                        ((TreeNode) e).split(this, newTab, j, oldCap);
                    } else {
                        Node<K, V> loHead = null;
                        Node<K, V> loTail = null;
                        Node<K, V> hiHead = null;
                        Node<K, V> hiTail = null;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null) {
                                    loHead = e;
                                } else {
                                    loTail.next = e;
                                }
                                loTail = e;
                            } else {
                                if (hiTail == null) {
                                    hiHead = e;
                                } else {
                                    hiTail.next = e;
                                }
                                hiTail = e;
                            }
                            e = next;
                        } while (next != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    /* access modifiers changed from: package-private */
    public final void treeifyBin(Node<K, V>[] tab, int hash) {
        int n;
        Node<K, V> node;
        if (tab == null || (n = tab.length) < 64) {
            resize();
            return;
        }
        int index = (n - 1) & hash;
        Node<K, V> node2 = tab[index];
        Node<K, V> e = node2;
        if (node2 != null) {
            TreeNode<K, V> hd = null;
            TreeNode<K, V> tl = null;
            do {
                TreeNode<K, V> p = replacementTreeNode(e, null);
                if (tl == null) {
                    hd = p;
                } else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
                node = e.next;
                e = node;
            } while (node != null);
            tab[index] = hd;
            if (hd != null) {
                hd.treeify(tab);
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        putMapEntries(m, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        Node<K, V> e = removeNode(hash(key), key, null, false, true);
        if (e == null) {
            return null;
        }
        return e.value;
    }

    /* access modifiers changed from: package-private */
    public final Node<K, V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
        int n;
        V v;
        K k;
        K k2;
        Node<K, V>[] tab = this.table;
        if (tab == null || (n = tab.length) <= 0) {
            return null;
        }
        int index = (n - 1) & hash;
        Node<K, V> node = tab[index];
        Node<K, V> p = node;
        if (node == null) {
            return null;
        }
        Node<K, V> node2 = null;
        if (p.hash != hash || ((k2 = p.key) != key && (key == null || !key.equals(k2)))) {
            Node<K, V> node3 = p.next;
            Node<K, V> e = node3;
            if (node3 != null) {
                if (p instanceof TreeNode) {
                    node2 = ((TreeNode) p).getTreeNode(hash, key);
                } else {
                    while (true) {
                        if (e.hash != hash || ((k = e.key) != key && (key == null || !key.equals(k)))) {
                            p = e;
                            Node<K, V> node4 = e.next;
                            e = node4;
                            if (node4 == null) {
                                break;
                            }
                        }
                    }
                    node2 = e;
                }
            }
        } else {
            node2 = p;
        }
        if (node2 == null) {
            return null;
        }
        if (matchValue && (v = node2.value) != value && (value == null || !value.equals(v))) {
            return null;
        }
        if (node2 instanceof TreeNode) {
            ((TreeNode) node2).removeTreeNode(this, tab, movable);
        } else if (node2 == p) {
            tab[index] = node2.next;
        } else {
            p.next = node2.next;
        }
        this.modCount++;
        this.size--;
        afterNodeRemoval(node2);
        return node2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.modCount++;
        Node<K, V>[] tab = this.table;
        if (tab != null && this.size > 0) {
            this.size = 0;
            for (int i = 0; i < tab.length; i++) {
                tab[i] = null;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        Node<K, V>[] tab = this.table;
        if (tab == null || this.size <= 0) {
            return false;
        }
        for (Node<K, V> e : tab) {
            for (; e != null; e = e.next) {
                V v = e.value;
                if (v == value) {
                    return true;
                }
                if (value != null && value.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        Set<K> ks2 = new KeySet();
        this.keySet = ks2;
        return ks2;
    }

    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return HashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            HashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public final Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object o) {
            return HashMap.this.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object key) {
            return HashMap.this.removeNode(HashMap.hash(key), key, null, false, true) != null;
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public final Spliterator<K> spliterator() {
            return new KeySpliterator(HashMap.this, 0, -1, 0, 0);
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer<? super K> action) {
            Node<K, V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            } else if (HashMap.this.size > 0 && (tab = HashMap.this.table) != null) {
                int mc = HashMap.this.modCount;
                for (int i = 0; i < tab.length && HashMap.this.modCount == mc; i++) {
                    for (Node<K, V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e.key);
                    }
                }
                if (HashMap.this.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Collection<V> vs2 = new Values();
        this.values = vs2;
        return vs2;
    }

    final class Values extends AbstractCollection<V> {
        Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final int size() {
            return HashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final void clear() {
            HashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object o) {
            return HashMap.this.containsValue(o);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Spliterator<V> spliterator() {
            return new ValueSpliterator(HashMap.this, 0, -1, 0, 0);
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer<? super V> action) {
            Node<K, V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            } else if (HashMap.this.size > 0 && (tab = HashMap.this.table) != null) {
                int mc = HashMap.this.modCount;
                for (int i = 0; i < tab.length && HashMap.this.modCount == mc; i++) {
                    for (Node<K, V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e.value);
                    }
                }
                if (HashMap.this.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return HashMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            HashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            Object key = e.getKey();
            Node<K, V> candidate = HashMap.this.getNode(HashMap.hash(key), key);
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
            if (HashMap.this.removeNode(HashMap.hash(key), key, e.getValue(), true, true) != null) {
                return true;
            }
            return false;
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public final Spliterator<Map.Entry<K, V>> spliterator() {
            return new EntrySpliterator(HashMap.this, 0, -1, 0, 0);
        }

        @Override // java.lang.Iterable
        public final void forEach(Consumer<? super Map.Entry<K, V>> action) {
            Node<K, V>[] tab;
            if (action == null) {
                throw new NullPointerException();
            } else if (HashMap.this.size > 0 && (tab = HashMap.this.table) != null) {
                int mc = HashMap.this.modCount;
                for (int i = 0; i < tab.length && HashMap.this.modCount == mc; i++) {
                    for (Node<K, V> e = tab[i]; e != null; e = e.next) {
                        action.accept(e);
                    }
                }
                if (HashMap.this.modCount != mc) {
                    throw new ConcurrentModificationException();
                }
            }
        }
    }

    @Override // java.util.Map
    public V getOrDefault(Object key, V defaultValue) {
        Node<K, V> e = getNode(hash(key), key);
        return e == null ? defaultValue : e.value;
    }

    @Override // java.util.Map
    public V putIfAbsent(K key, V value) {
        return putVal(hash(key), key, value, true, true);
    }

    @Override // java.util.Map
    public boolean remove(Object key, Object value) {
        return removeNode(hash(key), key, value, true, true) != null;
    }

    @Override // java.util.Map
    public boolean replace(K key, V oldValue, V newValue) {
        Node<K, V> e = getNode(hash(key), key);
        if (e == null) {
            return false;
        }
        V v = e.value;
        if (v != oldValue && (v == null || !v.equals(oldValue))) {
            return false;
        }
        e.value = newValue;
        afterNodeAccess(e);
        return true;
    }

    @Override // java.util.Map
    public V replace(K key, V value) {
        Node<K, V> e = getNode(hash(key), key);
        if (e == null) {
            return null;
        }
        V oldValue = e.value;
        e.value = value;
        afterNodeAccess(e);
        return oldValue;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        r2 = r3;
     */
    @Override // java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V computeIfAbsent(K r18, java.util.function.Function<? super K, ? extends V> r19) {
        /*
        // Method dump skipped, instructions count: 183
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.HashMap.computeIfAbsent(java.lang.Object, java.util.function.Function):java.lang.Object");
    }

    @Override // java.util.Map
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        V oldValue;
        if (remappingFunction != null) {
            int hash = hash(key);
            Node<K, V> e = getNode(hash, key);
            if (e == null || (oldValue = e.value) == null) {
                return null;
            }
            V v = (V) remappingFunction.apply(key, oldValue);
            if (v != null) {
                e.value = v;
                afterNodeAccess(e);
                return v;
            }
            removeNode(hash, key, null, false, true);
            return null;
        }
        throw new NullPointerException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        r5 = r0;
        r15 = null;
        r14 = r3;
     */
    @Override // java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V compute(K r21, java.util.function.BiFunction<? super K, ? super V, ? extends V> r22) {
        /*
        // Method dump skipped, instructions count: 223
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.HashMap.compute(java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v14, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        int n;
        Node<K, V>[] tab;
        TreeNode<K, V> t;
        Node<K, V> old;
        int binCount;
        V v;
        K k;
        Node<K, V>[] tab2;
        int n2;
        if (value == null) {
            throw new NullPointerException();
        } else if (remappingFunction != null) {
            int hash = hash(key);
            int binCount2 = 0;
            if (this.size > this.threshold || (tab2 = this.table) == null || (n2 = tab2.length) == 0) {
                Node<K, V>[] tab3 = resize();
                tab = tab3;
                n = tab3.length;
            } else {
                tab = tab2;
                n = n2;
            }
            int i = (n - 1) & hash;
            Node<K, V> first = tab[i];
            if (first == null) {
                binCount = 0;
                t = null;
                old = null;
            } else if (first instanceof TreeNode) {
                TreeNode<K, V> t2 = (TreeNode) first;
                binCount = 0;
                t = t2;
                old = t2.getTreeNode(hash, key);
            } else {
                Node<K, V> e = first;
                while (true) {
                    if (e.hash != hash || ((k = e.key) != key && (key == null || !key.equals(k)))) {
                        binCount2++;
                        Node<K, V> node = e.next;
                        e = node;
                        if (node == null) {
                            binCount = binCount2;
                            t = null;
                            old = null;
                            break;
                        }
                    }
                }
                binCount = binCount2;
                t = null;
                old = e;
            }
            if (old != null) {
                if (old.value != null) {
                    v = remappingFunction.apply(old.value, value);
                } else {
                    v = value;
                }
                if (v != null) {
                    old.value = v;
                    afterNodeAccess(old);
                    return v;
                }
                removeNode(hash, key, null, false, true);
                return v;
            }
            if (t != null) {
                t.putTreeVal(this, tab, hash, key, value);
            } else {
                tab[i] = newNode(hash, key, value, first);
                if (binCount >= 7) {
                    treeifyBin(tab, hash);
                }
            }
            this.modCount++;
            this.size++;
            afterNodeInsertion(true);
            return value;
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Node<K, V>[] tab;
        if (action == null) {
            throw new NullPointerException();
        } else if (this.size > 0 && (tab = this.table) != null) {
            int mc = this.modCount;
            for (int i = 0; i < tab.length && mc == this.modCount; i++) {
                for (Node<K, V> e = tab[i]; e != null; e = e.next) {
                    action.accept(e.key, e.value);
                }
            }
            if (this.modCount != mc) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Node<K, V>[] tab;
        if (function == null) {
            throw new NullPointerException();
        } else if (this.size > 0 && (tab = this.table) != null) {
            int mc = this.modCount;
            for (Node<K, V> e : tab) {
                for (; e != null; e = e.next) {
                    e.value = (V) function.apply(e.key, e.value);
                }
            }
            if (this.modCount != mc) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.AbstractMap
    public Object clone() {
        try {
            HashMap<K, V> result = (HashMap) super.clone();
            result.reinitialize();
            result.putMapEntries(this, false);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final float loadFactor() {
        return this.loadFactor;
    }

    /* access modifiers changed from: package-private */
    public final int capacity() {
        Node<K, V>[] nodeArr = this.table;
        if (nodeArr != null) {
            return nodeArr.length;
        }
        int i = this.threshold;
        if (i > 0) {
            return i;
        }
        return 16;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        int buckets = capacity();
        s.defaultWriteObject();
        s.writeInt(buckets);
        s.writeInt(this.size);
        internalWriteEntries(s);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r16v0, resolved type: java.util.HashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        int cap;
        s.defaultReadObject();
        reinitialize();
        float f = this.loadFactor;
        if (f <= 0.0f || Float.isNaN(f)) {
            throw new InvalidObjectException("Illegal load factor: " + this.loadFactor);
        }
        s.readInt();
        int mappings = s.readInt();
        if (mappings < 0) {
            throw new InvalidObjectException("Illegal mappings count: " + mappings);
        } else if (mappings > 0) {
            float lf = Math.min(Math.max(0.25f, this.loadFactor), 4.0f);
            float fc = (((float) mappings) / lf) + 1.0f;
            if (fc < 16.0f) {
                cap = 16;
            } else if (fc >= 1.07374182E9f) {
                cap = 1073741824;
            } else {
                cap = tableSizeFor((int) fc);
            }
            float ft = ((float) cap) * lf;
            this.threshold = (cap >= 1073741824 || ft >= 1.07374182E9f) ? Integer.MAX_VALUE : (int) ft;
            this.table = new Node[cap];
            for (int i = 0; i < mappings; i++) {
                Object readObject = s.readObject();
                putVal(hash(readObject), readObject, s.readObject(), false, false);
            }
        }
    }

    abstract class HashIterator {
        Node<K, V> current = null;
        int expectedModCount;
        int index = 0;
        Node<K, V> next = null;

        HashIterator() {
            Node<K, V> node;
            this.expectedModCount = HashMap.this.modCount;
            Node<K, V>[] t = HashMap.this.table;
            if (t != null && HashMap.this.size > 0) {
                do {
                    int i = this.index;
                    if (i < t.length) {
                        this.index = i + 1;
                        node = t[i];
                        this.next = node;
                    } else {
                        return;
                    }
                } while (node == null);
            }
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> nextNode() {
            Node<K, V>[] t;
            Node<K, V> node;
            Node<K, V> e = this.next;
            if (HashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (e != null) {
                this.current = e;
                Node<K, V> node2 = e.next;
                this.next = node2;
                if (node2 == null && (t = HashMap.this.table) != null) {
                    do {
                        int i = this.index;
                        if (i >= t.length) {
                            break;
                        }
                        this.index = i + 1;
                        node = t[i];
                        this.next = node;
                    } while (node == null);
                }
                return e;
            } else {
                throw new NoSuchElementException();
            }
        }

        public final void remove() {
            Node<K, V> p = this.current;
            if (p == null) {
                throw new IllegalStateException();
            } else if (HashMap.this.modCount == this.expectedModCount) {
                this.current = null;
                K key = p.key;
                HashMap.this.removeNode(HashMap.hash(key), key, null, false, false);
                this.expectedModCount = HashMap.this.modCount;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    final class KeyIterator extends HashMap<K, V>.HashIterator implements Iterator<K> {
        KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final K next() {
            return nextNode().key;
        }
    }

    final class ValueIterator extends HashMap<K, V>.HashIterator implements Iterator<V> {
        ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final V next() {
            return nextNode().value;
        }
    }

    final class EntryIterator extends HashMap<K, V>.HashIterator implements Iterator<Map.Entry<K, V>> {
        EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Map.Entry<K, V> next() {
            return nextNode();
        }
    }

    static class HashMapSpliterator<K, V> {
        Node<K, V> current;
        int est;
        int expectedModCount;
        int fence;
        int index;
        final HashMap<K, V> map;

        HashMapSpliterator(HashMap<K, V> m, int origin, int fence2, int est2, int expectedModCount2) {
            this.map = m;
            this.index = origin;
            this.fence = fence2;
            this.est = est2;
            this.expectedModCount = expectedModCount2;
        }

        /* access modifiers changed from: package-private */
        public final int getFence() {
            int hi = this.fence;
            if (hi >= 0) {
                return hi;
            }
            HashMap<K, V> m = this.map;
            this.est = m.size;
            this.expectedModCount = m.modCount;
            Node<K, V>[] tab = m.table;
            int hi2 = tab == null ? 0 : tab.length;
            this.fence = hi2;
            return hi2;
        }

        public final long estimateSize() {
            getFence();
            return (long) this.est;
        }
    }

    static final class KeySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<K> {
        KeySpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public KeySpliterator<K, V> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid || this.current != null) {
                return null;
            }
            HashMap hashMap = this.map;
            this.index = mid;
            int i = this.est >>> 1;
            this.est = i;
            return new KeySpliterator<>(hashMap, lo, mid, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> action) {
            int mc;
            if (action != null) {
                HashMap<K, V> m = this.map;
                Node<K, V>[] tab = m.table;
                int i = this.fence;
                int hi = i;
                if (i < 0) {
                    mc = m.modCount;
                    this.expectedModCount = mc;
                    int length = tab == null ? 0 : tab.length;
                    this.fence = length;
                    hi = length;
                } else {
                    mc = this.expectedModCount;
                }
                if (tab != null && tab.length >= hi) {
                    int i2 = this.index;
                    int i3 = i2;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (i3 < hi || this.current != null) {
                            Node<K, V> p = this.current;
                            this.current = null;
                            while (true) {
                                if (p == null) {
                                    p = tab[i3];
                                    i3++;
                                } else {
                                    action.accept(p.key);
                                    p = p.next;
                                }
                                if (p == null && i3 >= hi) {
                                    break;
                                }
                            }
                            if (m.modCount != mc) {
                                throw new ConcurrentModificationException();
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> action) {
            int hi;
            if (action != null) {
                Node<K, V>[] tab = this.map.table;
                if (tab == null || tab.length < (hi = getFence()) || this.index < 0) {
                    return false;
                }
                while (true) {
                    if (this.current == null && this.index >= hi) {
                        return false;
                    }
                    if (this.current == null) {
                        int i = this.index;
                        this.index = i + 1;
                        this.current = tab[i];
                    } else {
                        K k = this.current.key;
                        this.current = this.current.next;
                        action.accept(k);
                        if (this.map.modCount == this.expectedModCount) {
                            return true;
                        }
                        throw new ConcurrentModificationException();
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return ((this.fence < 0 || this.est == this.map.size) ? 64 : 0) | 1;
        }
    }

    static final class ValueSpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<V> {
        ValueSpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public ValueSpliterator<K, V> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid || this.current != null) {
                return null;
            }
            HashMap hashMap = this.map;
            this.index = mid;
            int i = this.est >>> 1;
            this.est = i;
            return new ValueSpliterator<>(hashMap, lo, mid, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super V> action) {
            int mc;
            if (action != null) {
                HashMap<K, V> m = this.map;
                Node<K, V>[] tab = m.table;
                int i = this.fence;
                int hi = i;
                if (i < 0) {
                    mc = m.modCount;
                    this.expectedModCount = mc;
                    int length = tab == null ? 0 : tab.length;
                    this.fence = length;
                    hi = length;
                } else {
                    mc = this.expectedModCount;
                }
                if (tab != null && tab.length >= hi) {
                    int i2 = this.index;
                    int i3 = i2;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (i3 < hi || this.current != null) {
                            Node<K, V> p = this.current;
                            this.current = null;
                            while (true) {
                                if (p == null) {
                                    p = tab[i3];
                                    i3++;
                                } else {
                                    action.accept(p.value);
                                    p = p.next;
                                }
                                if (p == null && i3 >= hi) {
                                    break;
                                }
                            }
                            if (m.modCount != mc) {
                                throw new ConcurrentModificationException();
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super V> action) {
            int hi;
            if (action != null) {
                Node<K, V>[] tab = this.map.table;
                if (tab == null || tab.length < (hi = getFence()) || this.index < 0) {
                    return false;
                }
                while (true) {
                    if (this.current == null && this.index >= hi) {
                        return false;
                    }
                    if (this.current == null) {
                        int i = this.index;
                        this.index = i + 1;
                        this.current = tab[i];
                    } else {
                        V v = this.current.value;
                        this.current = this.current.next;
                        action.accept(v);
                        if (this.map.modCount == this.expectedModCount) {
                            return true;
                        }
                        throw new ConcurrentModificationException();
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return (this.fence < 0 || this.est == this.map.size) ? 64 : 0;
        }
    }

    static final class EntrySpliterator<K, V> extends HashMapSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {
        EntrySpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
            super(m, origin, fence, est, expectedModCount);
        }

        @Override // java.util.Spliterator
        public EntrySpliterator<K, V> trySplit() {
            int hi = getFence();
            int lo = this.index;
            int mid = (lo + hi) >>> 1;
            if (lo >= mid || this.current != null) {
                return null;
            }
            HashMap hashMap = this.map;
            this.index = mid;
            int i = this.est >>> 1;
            this.est = i;
            return new EntrySpliterator<>(hashMap, lo, mid, i, this.expectedModCount);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            int mc;
            if (action != null) {
                HashMap<K, V> m = this.map;
                Node<K, V>[] tab = m.table;
                int i = this.fence;
                int hi = i;
                if (i < 0) {
                    mc = m.modCount;
                    this.expectedModCount = mc;
                    int length = tab == null ? 0 : tab.length;
                    this.fence = length;
                    hi = length;
                } else {
                    mc = this.expectedModCount;
                }
                if (tab != null && tab.length >= hi) {
                    int i2 = this.index;
                    int i3 = i2;
                    if (i2 >= 0) {
                        this.index = hi;
                        if (i3 < hi || this.current != null) {
                            Node<K, V> p = this.current;
                            this.current = null;
                            while (true) {
                                if (p == null) {
                                    p = tab[i3];
                                    i3++;
                                } else {
                                    action.accept(p);
                                    p = p.next;
                                }
                                if (p == null && i3 >= hi) {
                                    break;
                                }
                            }
                            if (m.modCount != mc) {
                                throw new ConcurrentModificationException();
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
            int hi;
            if (action != null) {
                Node<K, V>[] tab = this.map.table;
                if (tab == null || tab.length < (hi = getFence()) || this.index < 0) {
                    return false;
                }
                while (true) {
                    if (this.current == null && this.index >= hi) {
                        return false;
                    }
                    if (this.current == null) {
                        int i = this.index;
                        this.index = i + 1;
                        this.current = tab[i];
                    } else {
                        Node<K, V> e = this.current;
                        this.current = this.current.next;
                        action.accept(e);
                        if (this.map.modCount == this.expectedModCount) {
                            return true;
                        }
                        throw new ConcurrentModificationException();
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return ((this.fence < 0 || this.est == this.map.size) ? 64 : 0) | 1;
        }
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(hash, key, value, next);
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next) {
        return new Node<>(p.hash, p.key, p.value, next);
    }

    /* access modifiers changed from: package-private */
    public TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next) {
        return new TreeNode<>(hash, key, value, next);
    }

    /* access modifiers changed from: package-private */
    public TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next) {
        return new TreeNode<>(p.hash, p.key, p.value, next);
    }

    /* access modifiers changed from: package-private */
    public void reinitialize() {
        this.table = null;
        this.entrySet = null;
        this.keySet = null;
        this.values = null;
        this.modCount = 0;
        this.threshold = 0;
        this.size = 0;
    }

    /* access modifiers changed from: package-private */
    public void afterNodeAccess(Node<K, V> node) {
    }

    /* access modifiers changed from: package-private */
    public void afterNodeInsertion(boolean evict) {
    }

    /* access modifiers changed from: package-private */
    public void afterNodeRemoval(Node<K, V> node) {
    }

    /* access modifiers changed from: package-private */
    public void internalWriteEntries(ObjectOutputStream s) throws IOException {
        Node<K, V>[] tab;
        if (this.size > 0 && (tab = this.table) != null) {
            for (Node<K, V> e : tab) {
                for (; e != null; e = e.next) {
                    s.writeObject(e.key);
                    s.writeObject(e.value);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TreeNode<K, V> extends LinkedHashMap.LinkedHashMapEntry<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        TreeNode<K, V> left;
        TreeNode<K, V> parent;
        TreeNode<K, V> prev;
        boolean red;
        TreeNode<K, V> right;

        TreeNode(int hash, K key, V val, Node<K, V> next) {
            super(hash, key, val, next);
        }

        /* access modifiers changed from: package-private */
        public final TreeNode<K, V> root() {
            TreeNode<K, V> r = this;
            while (true) {
                TreeNode<K, V> p = r.parent;
                if (p == null) {
                    return r;
                }
                r = p;
            }
        }

        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
            int n;
            if (root != null && tab != null && (n = tab.length) > 0) {
                int index = (n - 1) & root.hash;
                TreeNode<K, V> first = (TreeNode) tab[index];
                if (root != first) {
                    tab[index] = root;
                    TreeNode<K, V> rp = root.prev;
                    Node<K, V> rn = root.next;
                    if (rn != null) {
                        ((TreeNode) rn).prev = rp;
                    }
                    if (rp != null) {
                        rp.next = rn;
                    }
                    if (first != null) {
                        first.prev = root;
                    }
                    root.next = first;
                    root.prev = null;
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
            if (r3 != null) goto L_0x002f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.HashMap.TreeNode<K, V> find(int r8, java.lang.Object r9, java.lang.Class<?> r10) {
            /*
                r7 = this;
                r0 = r7
            L_0x0001:
                java.util.HashMap$TreeNode<K, V> r1 = r0.left
                java.util.HashMap$TreeNode<K, V> r2 = r0.right
                int r3 = r0.hash
                r4 = r3
                if (r3 <= r8) goto L_0x000c
                r0 = r1
                goto L_0x0046
            L_0x000c:
                if (r4 >= r8) goto L_0x0010
                r0 = r2
                goto L_0x0046
            L_0x0010:
                java.lang.Object r3 = r0.key
                r5 = r3
                if (r3 == r9) goto L_0x004a
                if (r9 == 0) goto L_0x001e
                boolean r3 = r9.equals(r5)
                if (r3 == 0) goto L_0x001e
                goto L_0x004a
            L_0x001e:
                if (r1 != 0) goto L_0x0022
                r0 = r2
                goto L_0x0046
            L_0x0022:
                if (r2 != 0) goto L_0x0026
                r0 = r1
                goto L_0x0046
            L_0x0026:
                if (r10 != 0) goto L_0x002f
                java.lang.Class r3 = java.util.HashMap.comparableClassFor(r9)
                r10 = r3
                if (r3 == 0) goto L_0x003d
            L_0x002f:
                int r3 = java.util.HashMap.compareComparables(r10, r9, r5)
                r6 = r3
                if (r3 == 0) goto L_0x003d
                if (r6 >= 0) goto L_0x003a
                r3 = r1
                goto L_0x003b
            L_0x003a:
                r3 = r2
            L_0x003b:
                r0 = r3
                goto L_0x0046
            L_0x003d:
                java.util.HashMap$TreeNode r3 = r2.find(r8, r9, r10)
                r6 = r3
                if (r3 == 0) goto L_0x0045
                return r6
            L_0x0045:
                r0 = r1
            L_0x0046:
                if (r0 != 0) goto L_0x0001
                r1 = 0
                return r1
            L_0x004a:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.HashMap.TreeNode.find(int, java.lang.Object, java.lang.Class):java.util.HashMap$TreeNode");
        }

        /* access modifiers changed from: package-private */
        public final TreeNode<K, V> getTreeNode(int h, Object k) {
            return (this.parent != null ? root() : this).find(h, k, null);
        }

        static int tieBreakOrder(Object a, Object b) {
            int d;
            if (a != null && b != null && (d = a.getClass().getName().compareTo(b.getClass().getName())) != 0) {
                return d;
            }
            return System.identityHashCode(a) <= System.identityHashCode(b) ? -1 : 1;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
            if (r8 != null) goto L_0x0032;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void treeify(java.util.HashMap.Node<K, V>[] r13) {
            /*
                r12 = this;
                r0 = 0
                r1 = r12
            L_0x0002:
                if (r1 == 0) goto L_0x005b
                java.util.HashMap$Node r2 = r1.next
                java.util.HashMap$TreeNode r2 = (java.util.HashMap.TreeNode) r2
                r3 = 0
                r1.right = r3
                r1.left = r3
                if (r0 != 0) goto L_0x0016
                r1.parent = r3
                r3 = 0
                r1.red = r3
                r0 = r1
                goto L_0x0058
            L_0x0016:
                java.lang.Object r3 = r1.key
                int r4 = r1.hash
                r5 = 0
                r6 = r0
            L_0x001c:
                java.lang.Object r7 = r6.key
                int r8 = r6.hash
                r9 = r8
                if (r8 <= r4) goto L_0x0025
                r8 = -1
                goto L_0x003f
            L_0x0025:
                if (r9 >= r4) goto L_0x0029
                r8 = 1
                goto L_0x003f
            L_0x0029:
                if (r5 != 0) goto L_0x0032
                java.lang.Class r8 = java.util.HashMap.comparableClassFor(r3)
                r5 = r8
                if (r8 == 0) goto L_0x0039
            L_0x0032:
                int r8 = java.util.HashMap.compareComparables(r5, r3, r7)
                r10 = r8
                if (r8 != 0) goto L_0x003e
            L_0x0039:
                int r8 = tieBreakOrder(r3, r7)
                goto L_0x003f
            L_0x003e:
                r8 = r10
            L_0x003f:
                r10 = r6
                if (r8 > 0) goto L_0x0045
                java.util.HashMap$TreeNode<K, V> r11 = r6.left
                goto L_0x0047
            L_0x0045:
                java.util.HashMap$TreeNode<K, V> r11 = r6.right
            L_0x0047:
                r6 = r11
                if (r11 != 0) goto L_0x005a
                r1.parent = r10
                if (r8 > 0) goto L_0x0051
                r10.left = r1
                goto L_0x0053
            L_0x0051:
                r10.right = r1
            L_0x0053:
                java.util.HashMap$TreeNode r0 = balanceInsertion(r0, r1)
            L_0x0058:
                r1 = r2
                goto L_0x0002
            L_0x005a:
                goto L_0x001c
            L_0x005b:
                moveRootToFront(r13, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.HashMap.TreeNode.treeify(java.util.HashMap$Node[]):void");
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> untreeify(HashMap<K, V> map) {
            Node<K, V> hd = null;
            Node<K, V> tl = null;
            for (Node<K, V> q = this; q != null; q = q.next) {
                Node<K, V> p = map.replacementNode(q, null);
                if (tl == null) {
                    hd = p;
                } else {
                    tl.next = p;
                }
                tl = p;
            }
            return hd;
        }

        /* JADX INFO: Multiple debug info for r4v2 java.lang.Object: [D('pk' K), D('dir' int)] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
            if (r4 != null) goto L_0x0030;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.HashMap.TreeNode<K, V> putTreeVal(java.util.HashMap<K, V> r11, java.util.HashMap.Node<K, V>[] r12, int r13, K r14, V r15) {
            /*
            // Method dump skipped, instructions count: 137
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.HashMap.TreeNode.putTreeVal(java.util.HashMap, java.util.HashMap$Node[], int, java.lang.Object, java.lang.Object):java.util.HashMap$TreeNode");
        }

        /* JADX INFO: Multiple debug info for r3v11 java.util.HashMap$TreeNode<K, V>: [D('sp' java.util.HashMap$TreeNode<K, V>), D('n' int)] */
        /* access modifiers changed from: package-private */
        public final void removeTreeNode(HashMap<K, V> map, Node<K, V>[] tab, boolean movable) {
            TreeNode<K, V> root;
            TreeNode<K, V> replacement;
            TreeNode<K, V> pp;
            TreeNode<K, V> root2;
            TreeNode<K, V> root3;
            if (tab != null) {
                int n = tab.length;
                if (n != 0) {
                    int index = (n - 1) & this.hash;
                    TreeNode<K, V> first = (TreeNode) tab[index];
                    TreeNode<K, V> root4 = first;
                    TreeNode<K, V> succ = (TreeNode) this.next;
                    TreeNode<K, V> pred = this.prev;
                    if (pred == null) {
                        first = succ;
                        tab[index] = succ;
                    } else {
                        pred.next = succ;
                    }
                    if (succ != null) {
                        succ.prev = pred;
                    }
                    if (first != null) {
                        if (root4.parent != null) {
                            root4 = root4.root();
                        }
                        if (root4 != null && root4.right != null) {
                            TreeNode<K, V> rl = root4.left;
                            if (rl != null) {
                                if (rl.left != null) {
                                    TreeNode<K, V> pl = this.left;
                                    TreeNode<K, V> pr = this.right;
                                    if (pl == null || pr == null) {
                                        root = root4;
                                        if (pl != null) {
                                            replacement = pl;
                                        } else if (pr != null) {
                                            replacement = pr;
                                        } else {
                                            replacement = this;
                                        }
                                    } else {
                                        TreeNode<K, V> s = pr;
                                        while (true) {
                                            TreeNode<K, V> sl = s.left;
                                            if (sl == null) {
                                                break;
                                            }
                                            s = sl;
                                        }
                                        boolean c = s.red;
                                        s.red = this.red;
                                        this.red = c;
                                        TreeNode<K, V> sr = s.right;
                                        TreeNode<K, V> pp2 = this.parent;
                                        if (s == pr) {
                                            this.parent = s;
                                            s.right = this;
                                            root2 = root4;
                                        } else {
                                            TreeNode<K, V> sp = s.parent;
                                            this.parent = sp;
                                            if (sp != null) {
                                                root2 = root4;
                                                if (s == sp.left) {
                                                    sp.left = this;
                                                } else {
                                                    sp.right = this;
                                                }
                                            } else {
                                                root2 = root4;
                                            }
                                            s.right = pr;
                                            pr.parent = s;
                                        }
                                        this.left = null;
                                        this.right = sr;
                                        if (sr != null) {
                                            sr.parent = this;
                                        }
                                        s.left = pl;
                                        pl.parent = s;
                                        s.parent = pp2;
                                        if (pp2 == null) {
                                            root3 = s;
                                        } else {
                                            if (this == pp2.left) {
                                                pp2.left = s;
                                            } else {
                                                pp2.right = s;
                                            }
                                            root3 = root2;
                                        }
                                        if (sr != null) {
                                            replacement = sr;
                                        } else {
                                            replacement = this;
                                        }
                                        root = root3;
                                    }
                                    if (replacement != this) {
                                        TreeNode<K, V> pp3 = this.parent;
                                        replacement.parent = pp3;
                                        if (pp3 == null) {
                                            root = replacement;
                                        } else if (this == pp3.left) {
                                            pp3.left = replacement;
                                        } else {
                                            pp3.right = replacement;
                                        }
                                        this.parent = null;
                                        this.right = null;
                                        this.left = null;
                                        pp = root;
                                    } else {
                                        pp = root;
                                    }
                                    TreeNode<K, V> r = this.red ? pp : balanceDeletion(pp, replacement);
                                    if (replacement == this) {
                                        TreeNode<K, V> pp4 = this.parent;
                                        this.parent = null;
                                        if (pp4 != null) {
                                            if (this == pp4.left) {
                                                pp4.left = null;
                                            } else if (this == pp4.right) {
                                                pp4.right = null;
                                            }
                                        }
                                    }
                                    if (movable) {
                                        moveRootToFront(tab, r);
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                        tab[index] = first.untreeify(map);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void split(HashMap<K, V> map, Node<K, V>[] tab, int index, int bit) {
            TreeNode<K, V> loHead = null;
            TreeNode<K, V> loTail = null;
            TreeNode<K, V> hiHead = null;
            TreeNode<K, V> hiTail = null;
            int lc = 0;
            int hc = 0;
            TreeNode<K, V> e = this;
            while (e != null) {
                TreeNode<K, V> next = (TreeNode) e.next;
                e.next = null;
                if ((e.hash & bit) == 0) {
                    e.prev = loTail;
                    if (loTail == null) {
                        loHead = e;
                    } else {
                        loTail.next = e;
                    }
                    loTail = e;
                    lc++;
                } else {
                    e.prev = hiTail;
                    if (hiTail == null) {
                        hiHead = e;
                    } else {
                        hiTail.next = e;
                    }
                    hiTail = e;
                    hc++;
                }
                e = next;
            }
            if (loHead != null) {
                if (lc <= 6) {
                    tab[index] = loHead.untreeify(map);
                } else {
                    tab[index] = loHead;
                    if (hiHead != null) {
                        loHead.treeify(tab);
                    }
                }
            }
            if (hiHead == null) {
                return;
            }
            if (hc <= 6) {
                tab[index + bit] = hiHead.untreeify(map);
                return;
            }
            tab[index + bit] = hiHead;
            if (loHead != null) {
                hiHead.treeify(tab);
            }
        }

        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p) {
            TreeNode<K, V> r;
            if (!(p == null || (r = p.right) == null)) {
                TreeNode<K, V> rl = r.left;
                p.right = rl;
                if (rl != null) {
                    rl.parent = p;
                }
                TreeNode<K, V> pp = p.parent;
                r.parent = pp;
                if (pp == null) {
                    root = r;
                    r.red = false;
                } else if (pp.left == p) {
                    pp.left = r;
                } else {
                    pp.right = r;
                }
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p) {
            TreeNode<K, V> l;
            if (!(p == null || (l = p.left) == null)) {
                TreeNode<K, V> lr = l.right;
                p.left = lr;
                if (lr != null) {
                    lr.parent = p;
                }
                TreeNode<K, V> pp = p.parent;
                l.parent = pp;
                if (pp == null) {
                    root = l;
                    l.red = false;
                } else if (pp.right == p) {
                    pp.right = l;
                } else {
                    pp.left = l;
                }
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> x) {
            x.red = true;
            while (true) {
                TreeNode<K, V> treeNode = x.parent;
                TreeNode<K, V> xp = treeNode;
                if (treeNode != null) {
                    if (!xp.red) {
                        break;
                    }
                    TreeNode<K, V> treeNode2 = xp.parent;
                    TreeNode<K, V> xpp = treeNode2;
                    if (treeNode2 == null) {
                        break;
                    }
                    TreeNode<K, V> xppl = xpp.left;
                    TreeNode<K, V> treeNode3 = null;
                    if (xp == xppl) {
                        TreeNode<K, V> xppr = xpp.right;
                        if (xppr == null || !xppr.red) {
                            if (x == xp.right) {
                                x = xp;
                                root = rotateLeft(root, xp);
                                TreeNode<K, V> treeNode4 = x.parent;
                                xp = treeNode4;
                                if (treeNode4 != null) {
                                    treeNode3 = xp.parent;
                                }
                                xpp = treeNode3;
                            }
                            if (xp != null) {
                                xp.red = false;
                                if (xpp != null) {
                                    xpp.red = true;
                                    root = rotateRight(root, xpp);
                                }
                            }
                        } else {
                            xppr.red = false;
                            xp.red = false;
                            xpp.red = true;
                            x = xpp;
                        }
                    } else if (xppl == null || !xppl.red) {
                        if (x == xp.left) {
                            x = xp;
                            root = rotateRight(root, xp);
                            TreeNode<K, V> treeNode5 = x.parent;
                            xp = treeNode5;
                            if (treeNode5 != null) {
                                treeNode3 = xp.parent;
                            }
                            xpp = treeNode3;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    } else {
                        xppl.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    }
                } else {
                    x.red = false;
                    return x;
                }
            }
            return root;
        }

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> x) {
            while (x != null && x != root) {
                TreeNode<K, V> treeNode = x.parent;
                TreeNode<K, V> xp = treeNode;
                if (treeNode == null) {
                    x.red = false;
                    return x;
                } else if (x.red) {
                    x.red = false;
                    return root;
                } else {
                    TreeNode<K, V> treeNode2 = xp.left;
                    TreeNode<K, V> xpl = treeNode2;
                    TreeNode<K, V> treeNode3 = null;
                    if (treeNode2 == x) {
                        TreeNode<K, V> treeNode4 = xp.right;
                        TreeNode<K, V> xpr = treeNode4;
                        if (treeNode4 != null && xpr.red) {
                            xpr.red = false;
                            xp.red = true;
                            root = rotateLeft(root, xp);
                            TreeNode<K, V> treeNode5 = x.parent;
                            xp = treeNode5;
                            xpr = treeNode5 == null ? null : xp.right;
                        }
                        if (xpr == null) {
                            x = xp;
                        } else {
                            TreeNode<K, V> sl = xpr.left;
                            TreeNode<K, V> sr = xpr.right;
                            if ((sr == null || !sr.red) && (sl == null || !sl.red)) {
                                xpr.red = true;
                                x = xp;
                            } else {
                                if (sr == null || !sr.red) {
                                    if (sl != null) {
                                        sl.red = false;
                                    }
                                    xpr.red = true;
                                    root = rotateRight(root, xpr);
                                    TreeNode<K, V> treeNode6 = x.parent;
                                    xp = treeNode6;
                                    if (treeNode6 != null) {
                                        treeNode3 = xp.right;
                                    }
                                    xpr = treeNode3;
                                }
                                if (xpr != null) {
                                    xpr.red = xp == null ? false : xp.red;
                                    TreeNode<K, V> sr2 = xpr.right;
                                    if (sr2 != null) {
                                        sr2.red = false;
                                    }
                                }
                                if (xp != null) {
                                    xp.red = false;
                                    root = rotateLeft(root, xp);
                                }
                                x = root;
                            }
                        }
                    } else {
                        if (xpl != null && xpl.red) {
                            xpl.red = false;
                            xp.red = true;
                            root = rotateRight(root, xp);
                            TreeNode<K, V> treeNode7 = x.parent;
                            xp = treeNode7;
                            xpl = treeNode7 == null ? null : xp.left;
                        }
                        if (xpl == null) {
                            x = xp;
                        } else {
                            TreeNode<K, V> sl2 = xpl.left;
                            TreeNode<K, V> sr3 = xpl.right;
                            if ((sl2 == null || !sl2.red) && (sr3 == null || !sr3.red)) {
                                xpl.red = true;
                                x = xp;
                            } else {
                                if (sl2 == null || !sl2.red) {
                                    if (sr3 != null) {
                                        sr3.red = false;
                                    }
                                    xpl.red = true;
                                    root = rotateLeft(root, xpl);
                                    TreeNode<K, V> treeNode8 = x.parent;
                                    xp = treeNode8;
                                    if (treeNode8 != null) {
                                        treeNode3 = xp.left;
                                    }
                                    xpl = treeNode3;
                                }
                                if (xpl != null) {
                                    xpl.red = xp == null ? false : xp.red;
                                    TreeNode<K, V> sl3 = xpl.left;
                                    if (sl3 != null) {
                                        sl3.red = false;
                                    }
                                }
                                if (xp != null) {
                                    xp.red = false;
                                    root = rotateRight(root, xp);
                                }
                                x = root;
                            }
                        }
                    }
                }
            }
            return root;
        }

        static <K, V> boolean checkInvariants(TreeNode<K, V> t) {
            TreeNode<K, V> tp = t.parent;
            TreeNode<K, V> tl = t.left;
            TreeNode<K, V> tr = t.right;
            TreeNode<K, V> tb = t.prev;
            TreeNode<K, V> tn = (TreeNode) t.next;
            if (tb != null && tb.next != t) {
                return false;
            }
            if (tn != null && tn.prev != t) {
                return false;
            }
            if (tp != null && t != tp.left && t != tp.right) {
                return false;
            }
            if (tl != null && (tl.parent != t || tl.hash > t.hash)) {
                return false;
            }
            if (tr != null && (tr.parent != t || tr.hash < t.hash)) {
                return false;
            }
            if (t.red && tl != null && tl.red && tr != null && tr.red) {
                return false;
            }
            if (tl != null && !checkInvariants(tl)) {
                return false;
            }
            if (tr == null || checkInvariants(tr)) {
                return true;
            }
            return false;
        }
    }
}
