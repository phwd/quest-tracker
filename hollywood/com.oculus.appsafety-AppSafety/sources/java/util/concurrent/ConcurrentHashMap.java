package java.util.concurrent;

import android.icu.impl.Normalizer2Impl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import sun.misc.Unsafe;

public class ConcurrentHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    private static final int ABASE;
    private static final int ASHIFT;
    private static final long BASECOUNT;
    private static final long CELLSBUSY;
    private static final long CELLVALUE;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    static final int HASH_BITS = Integer.MAX_VALUE;
    private static final float LOAD_FACTOR = 0.75f;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_ARRAY_SIZE = 2147483639;
    private static final int MAX_RESIZERS = 65535;
    private static final int MIN_TRANSFER_STRIDE = 16;
    static final int MIN_TREEIFY_CAPACITY = 64;
    static final int MOVED = -1;
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    static final int RESERVED = -3;
    private static final int RESIZE_STAMP_BITS = 16;
    private static final int RESIZE_STAMP_SHIFT = 16;
    private static final long SIZECTL;
    private static final long TRANSFERINDEX;
    static final int TREEBIN = -2;
    static final int TREEIFY_THRESHOLD = 8;
    private static final Unsafe U = Unsafe.getUnsafe();
    static final int UNTREEIFY_THRESHOLD = 6;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("segments", Segment[].class), new ObjectStreamField("segmentMask", Integer.TYPE), new ObjectStreamField("segmentShift", Integer.TYPE)};
    private static final long serialVersionUID = 7249069246763182397L;
    private volatile transient long baseCount;
    private volatile transient int cellsBusy;
    private volatile transient CounterCell[] counterCells;
    private transient EntrySetView<K, V> entrySet;
    private transient KeySetView<K, V> keySet;
    private volatile transient Node<K, V>[] nextTable;
    private volatile transient int sizeCtl;
    volatile transient Node<K, V>[] table;
    private volatile transient int transferIndex;
    private transient ValuesView<K, V> values;

    static {
        try {
            SIZECTL = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("sizeCtl"));
            TRANSFERINDEX = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("transferIndex"));
            BASECOUNT = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("baseCount"));
            CELLSBUSY = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("cellsBusy"));
            CELLVALUE = U.objectFieldOffset(CounterCell.class.getDeclaredField("value"));
            ABASE = U.arrayBaseOffset(Node[].class);
            int scale = U.arrayIndexScale(Node[].class);
            if (((scale - 1) & scale) == 0) {
                ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
                return;
            }
            throw new Error("array index scale not a power of two");
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        volatile Node<K, V> next;
        volatile V val;

        Node(int hash2, K key2, V val2, Node<K, V> next2) {
            this.hash = hash2;
            this.key = key2;
            this.val = val2;
            this.next = next2;
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            return this.val;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public final String toString() {
            return Helpers.mapEntryToString(this.key, this.val);
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object o) {
            Map.Entry<?, ?> e;
            Object k;
            Object v;
            K k2;
            Object u;
            return (o instanceof Map.Entry) && (k = (e = (Map.Entry) o).getKey()) != null && (v = e.getValue()) != null && (k == (k2 = this.key) || k.equals(k2)) && (v == (u = this.val) || v.equals(u));
        }

        /* access modifiers changed from: package-private */
        public Node<K, V> find(int h, Object k) {
            Node<K, V> node;
            K ek;
            Node<K, V> e = this;
            if (k == null) {
                return null;
            }
            do {
                if (e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek)))) {
                    return e;
                }
                node = e.next;
                e = node;
            } while (node != null);
            return null;
        }
    }

    static final int spread(int h) {
        return ((h >>> 16) ^ h) & Integer.MAX_VALUE;
    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
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

    static final <K, V> Node<K, V> tabAt(Node<K, V>[] tab, int i) {
        return (Node) U.getObjectVolatile(tab, (((long) i) << ASHIFT) + ((long) ABASE));
    }

    static final <K, V> boolean casTabAt(Node<K, V>[] tab, int i, Node<K, V> c, Node<K, V> v) {
        return U.compareAndSwapObject(tab, (((long) i) << ASHIFT) + ((long) ABASE), c, v);
    }

    static final <K, V> void setTabAt(Node<K, V>[] tab, int i, Node<K, V> v) {
        U.putObjectVolatile(tab, (((long) i) << ASHIFT) + ((long) ABASE), v);
    }

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int initialCapacity) {
        int cap;
        if (initialCapacity >= 0) {
            if (initialCapacity >= 536870912) {
                cap = 1073741824;
            } else {
                cap = tableSizeFor((initialCapacity >>> 1) + initialCapacity + 1);
            }
            this.sizeCtl = cap;
            return;
        }
        throw new IllegalArgumentException();
    }

    public ConcurrentHashMap(Map<? extends K, ? extends V> m) {
        this.sizeCtl = 16;
        putAll(m);
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor) {
        this(initialCapacity, loadFactor, 1);
    }

    public ConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        if (loadFactor <= 0.0f || initialCapacity < 0 || concurrencyLevel <= 0) {
            throw new IllegalArgumentException();
        }
        long size = (long) (((double) (((float) ((long) (initialCapacity < concurrencyLevel ? concurrencyLevel : initialCapacity))) / loadFactor)) + 1.0d);
        this.sizeCtl = size >= 1073741824 ? 1073741824 : tableSizeFor((int) size);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long n = sumCount();
        if (n < 0) {
            return 0;
        }
        if (n > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) n;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return sumCount() <= 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        int n;
        K ek;
        int h = spread(key.hashCode());
        Node<K, V>[] tab = this.table;
        if (tab != null && (n = tab.length) > 0) {
            Node<K, V> tabAt = tabAt(tab, (n - 1) & h);
            Node<K, V> e = tabAt;
            if (tabAt != null) {
                int eh = e.hash;
                if (eh == h) {
                    K ek2 = e.key;
                    if (ek2 == key || (ek2 != null && key.equals(ek2))) {
                        return e.val;
                    }
                } else if (eh < 0) {
                    Node<K, V> p = e.find(h, key);
                    if (p != null) {
                        return p.val;
                    }
                    return null;
                }
                while (true) {
                    Node<K, V> node = e.next;
                    e = node;
                    if (node == null) {
                        break;
                    } else if (e.hash != h || ((ek = e.key) != key && (ek == null || !key.equals(ek)))) {
                    }
                }
                return e.val;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        if (value != null) {
            Node<K, V>[] t = this.table;
            if (t != null) {
                Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                while (true) {
                    Node<K, V> p = it.advance();
                    if (p == null) {
                        break;
                    }
                    V v = p.val;
                    if (v == value) {
                        return true;
                    }
                    if (v != null && value.equals(v)) {
                        return true;
                    }
                }
            }
            return false;
        }
        throw new NullPointerException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        return putVal(key, value, false);
    }

    /* access modifiers changed from: package-private */
    public final V putVal(K key, V value, boolean onlyIfAbsent) {
        K ek;
        if (key == null || value == null) {
            throw new NullPointerException();
        }
        int hash = spread(key.hashCode());
        int binCount = 0;
        Node<K, V>[] tab = this.table;
        while (true) {
            if (tab != null) {
                int n = tab.length;
                if (n != 0) {
                    int i = (n - 1) & hash;
                    Node<K, V> f = tabAt(tab, i);
                    if (f != null) {
                        int fh = f.hash;
                        if (fh == -1) {
                            tab = helpTransfer(tab, f);
                        } else {
                            V oldVal = null;
                            synchronized (f) {
                                if (tabAt(tab, i) == f) {
                                    if (fh >= 0) {
                                        binCount = 1;
                                        Node<K, V> e = f;
                                        while (true) {
                                            if (e.hash != hash || ((ek = e.key) != key && (ek == null || !key.equals(ek)))) {
                                                Node<K, V> node = e.next;
                                                e = node;
                                                if (node == null) {
                                                    e.next = new Node<>(hash, key, value, null);
                                                    break;
                                                }
                                                binCount++;
                                            }
                                        }
                                        oldVal = e.val;
                                        if (!onlyIfAbsent) {
                                            e.val = value;
                                        }
                                    } else if (f instanceof TreeBin) {
                                        binCount = 2;
                                        Node<K, V> p = ((TreeBin) f).putTreeVal(hash, key, value);
                                        if (p != null) {
                                            oldVal = p.val;
                                            if (!onlyIfAbsent) {
                                                p.val = value;
                                            }
                                        }
                                    } else if (f instanceof ReservationNode) {
                                        throw new IllegalStateException("Recursive update");
                                    }
                                }
                            }
                            if (binCount != 0) {
                                if (binCount >= 8) {
                                    treeifyBin(tab, i);
                                }
                                if (oldVal != null) {
                                    return oldVal;
                                }
                            }
                        }
                    } else if (casTabAt(tab, i, null, new Node(hash, key, value, null))) {
                        break;
                    }
                }
            }
            tab = initTable();
        }
        addCount(1, binCount);
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.concurrent.ConcurrentHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        tryPresize(m.size());
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            putVal(e.getKey(), e.getValue(), false);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        return replaceNode(key, null, null);
    }

    /* access modifiers changed from: package-private */
    public final V replaceNode(Object key, V value, Object cv) {
        int i;
        Node<K, V> f;
        TreeNode<K, V> p;
        Object obj = key;
        int hash = spread(key.hashCode());
        Node<K, V>[] tab = this.table;
        while (tab != null && (n = tab.length) != 0 && (f = tabAt(tab, (i = (n - 1) & hash))) != null) {
            int fh = f.hash;
            if (fh == -1) {
                tab = helpTransfer(tab, f);
            } else {
                V oldVal = null;
                boolean validated = false;
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            validated = true;
                            Node<K, V> e = f;
                            Node<K, V> pred = null;
                            while (true) {
                                if (e.hash == hash) {
                                    K ek = e.key;
                                    if (ek != obj) {
                                        if (ek != null && obj.equals(ek)) {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                pred = e;
                                Node<K, V> node = e.next;
                                e = node;
                                if (node == null) {
                                    break;
                                }
                            }
                            V ev = e.val;
                            if (cv == null || cv == ev || (ev != null && cv.equals(ev))) {
                                oldVal = ev;
                                if (value != null) {
                                    e.val = value;
                                } else if (pred != null) {
                                    pred.next = e.next;
                                } else {
                                    setTabAt(tab, i, e.next);
                                }
                            }
                        } else if (f instanceof TreeBin) {
                            validated = true;
                            TreeBin<K, V> t = (TreeBin) f;
                            TreeNode<K, V> r = t.root;
                            if (!(r == null || (p = r.findTreeNode(hash, obj, null)) == null)) {
                                Object obj2 = p.val;
                                if (cv == null || cv == obj2 || (obj2 != null && cv.equals(obj2))) {
                                    oldVal = (V) obj2;
                                    if (value != null) {
                                        p.val = value;
                                    } else if (t.removeTreeNode(p)) {
                                        setTabAt(tab, i, untreeify(t.first));
                                    }
                                }
                            }
                        } else if (f instanceof ReservationNode) {
                            throw new IllegalStateException("Recursive update");
                        }
                    }
                }
                if (validated) {
                    if (oldVal == null) {
                        return null;
                    }
                    if (value == null) {
                        addCount(-1, -1);
                    }
                    return oldVal;
                }
            }
            obj = key;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Throwable th;
        Node<K, V> p;
        long delta = 0;
        int i = 0;
        Node<K, V>[] tab = this.table;
        while (tab != null && i < tab.length) {
            Node<K, V> f = tabAt(tab, i);
            if (f == null) {
                i++;
            } else {
                int fh = f.hash;
                if (fh == -1) {
                    tab = helpTransfer(tab, f);
                    i = 0;
                } else {
                    synchronized (f) {
                        try {
                            if (tabAt(tab, i) == f) {
                                if (fh >= 0) {
                                    p = f;
                                } else {
                                    p = f instanceof TreeBin ? ((TreeBin) f).first : null;
                                }
                                while (p != null) {
                                    delta--;
                                    p = p.next;
                                }
                                int i2 = i + 1;
                                try {
                                    setTabAt(tab, i, null);
                                    i = i2;
                                } catch (Throwable th2) {
                                    th = th2;
                                    throw th;
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            throw th;
                        }
                    }
                }
            }
        }
        if (delta != 0) {
            addCount(delta, -1);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        KeySetView<K, V> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        KeySetView<K, V> keySetView = new KeySetView<>(this, null);
        this.keySet = keySetView;
        return keySetView;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        ValuesView<K, V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        ValuesView<K, V> valuesView = new ValuesView<>(this);
        this.values = valuesView;
        return valuesView;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        EntrySetView<K, V> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySetView<K, V> entrySetView = new EntrySetView<>(this);
        this.entrySet = entrySetView;
        return entrySetView;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int h = 0;
        Node<K, V>[] t = this.table;
        if (t != null) {
            Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
            while (true) {
                Node<K, V> p = it.advance();
                if (p == null) {
                    break;
                }
                h += p.key.hashCode() ^ p.val.hashCode();
            }
        }
        return h;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        Node<K, V>[] t = this.table;
        int f = t == null ? 0 : t.length;
        Traverser<K, V> it = new Traverser<>(t, f, 0, f);
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Node<K, V> advance = it.advance();
        Node<K, V> p = advance;
        if (advance != null) {
            while (true) {
                K k = p.key;
                V v = p.val;
                Object obj = "(this Map)";
                sb.append(k == this ? obj : k);
                sb.append('=');
                if (v != this) {
                    obj = v;
                }
                sb.append(obj);
                Node<K, V> advance2 = it.advance();
                p = advance2;
                if (advance2 == null) {
                    break;
                }
                sb.append(',');
                sb.append(' ');
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object o) {
        Object mv;
        Object v;
        if (o == this) {
            return true;
        }
        if (!(o instanceof Map)) {
            return false;
        }
        Map<?, ?> m = (Map) o;
        Node<K, V>[] t = this.table;
        int f = t == null ? 0 : t.length;
        Traverser<K, V> it = new Traverser<>(t, f, 0, f);
        while (true) {
            Node<K, V> p = it.advance();
            if (p != null) {
                V val = p.val;
                Object v2 = m.get(p.key);
                if (v2 == null || (v2 != val && !v2.equals(val))) {
                    return false;
                }
            } else {
                for (Map.Entry<?, ?> e : m.entrySet()) {
                    Object mk = e.getKey();
                    if (mk == null || (mv = e.getValue()) == null || (v = get(mk)) == null || (mv != v && !mv.equals(v))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static class Segment<K, V> extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;
        final float loadFactor;

        Segment(float lf) {
            this.loadFactor = lf;
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        int sshift = 0;
        int ssize = 1;
        while (ssize < 16) {
            sshift++;
            ssize <<= 1;
        }
        int segmentShift = 32 - sshift;
        int segmentMask = ssize - 1;
        Segment<K, V>[] segments = new Segment[16];
        for (int i = 0; i < segments.length; i++) {
            segments[i] = new Segment<>(LOAD_FACTOR);
        }
        ObjectOutputStream.PutField streamFields = s.putFields();
        streamFields.put("segments", segments);
        streamFields.put("segmentShift", segmentShift);
        streamFields.put("segmentMask", segmentMask);
        s.writeFields();
        Node<K, V>[] t = this.table;
        if (t != null) {
            Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
            while (true) {
                Node<K, V> p = it.advance();
                if (p == null) {
                    break;
                }
                s.writeObject(p.key);
                s.writeObject(p.val);
            }
        }
        s.writeObject(null);
        s.writeObject(null);
    }

    /* JADX INFO: Multiple debug info for r14v2 K: [D('k' K), D('insertAtFront' boolean)] */
    /* JADX INFO: Multiple debug info for r4v6 int: [D('sz' int), D('n' int)] */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        int sz;
        int mask;
        long size;
        boolean insertAtFront;
        long j;
        this.sizeCtl = -1;
        s.defaultReadObject();
        long size2 = 0;
        Node<K, V> p = null;
        while (true) {
            Object readObject = s.readObject();
            Object readObject2 = s.readObject();
            if (readObject != null && readObject2 != null) {
                p = new Node<>(spread(readObject.hashCode()), readObject, readObject2, p);
                size2++;
            }
        }
        if (size2 == 0) {
            this.sizeCtl = 0;
            return;
        }
        if (size2 >= 536870912) {
            sz = 1073741824;
        } else {
            int n = (int) size2;
            sz = tableSizeFor((n >>> 1) + n + 1);
        }
        Node<K, V>[] tab = new Node[sz];
        int mask2 = sz - 1;
        long added = 0;
        while (p != null) {
            Node<K, V> next = p.next;
            int h = p.hash;
            int j2 = h & mask2;
            Node<K, V> first = tabAt(tab, j2);
            if (first == null) {
                insertAtFront = true;
                size = size2;
                mask = mask2;
            } else {
                K k = p.key;
                if (first.hash < 0) {
                    if (((TreeBin) first).putTreeVal(h, k, p.val) == null) {
                        added++;
                    }
                    size = size2;
                    insertAtFront = false;
                    mask = mask2;
                } else {
                    boolean insertAtFront2 = true;
                    int binCount = 0;
                    Node<K, V> q = first;
                    size = size2;
                    while (true) {
                        if (q == null) {
                            break;
                        }
                        if (q.hash == h) {
                            K qk = q.key;
                            if (qk != k) {
                                if (qk != null && k.equals(qk)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        binCount++;
                        q = q.next;
                    }
                    insertAtFront2 = false;
                    if (!insertAtFront2 || binCount < 8) {
                        mask = mask2;
                        insertAtFront = insertAtFront2;
                    } else {
                        boolean insertAtFront3 = false;
                        added++;
                        p.next = first;
                        TreeNode<K, V> hd = null;
                        Node<K, V> q2 = p;
                        TreeNode<K, V> tl = null;
                        while (q2 != null) {
                            TreeNode<K, V> t = new TreeNode<>(q2.hash, q2.key, q2.val, null, null);
                            t.prev = tl;
                            if (tl == null) {
                                hd = t;
                            } else {
                                tl.next = t;
                            }
                            tl = t;
                            q2 = q2.next;
                            insertAtFront3 = insertAtFront3;
                            mask2 = mask2;
                            added = added;
                        }
                        mask = mask2;
                        setTabAt(tab, j2, new TreeBin(hd));
                        insertAtFront = insertAtFront3;
                    }
                }
            }
            if (insertAtFront) {
                j = 1;
                added++;
                p.next = first;
                setTabAt(tab, j2, p);
            } else {
                j = 1;
            }
            p = next;
            size2 = size;
            mask2 = mask;
        }
        this.table = tab;
        this.sizeCtl = sz - (sz >>> 2);
        this.baseCount = added;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K key, V value) {
        return putVal(key, value, true);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object key, Object value) {
        if (key != null) {
            return (value == null || replaceNode(key, null, value) == null) ? false : true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K key, V oldValue, V newValue) {
        if (key != null && oldValue != null && newValue != null) {
            return replaceNode(key, newValue, oldValue) != null;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K key, V value) {
        if (key != null && value != null) {
            return replaceNode(key, value, null);
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V getOrDefault(Object key, V defaultValue) {
        V v = get(key);
        return v == null ? defaultValue : v;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action != null) {
            Node<K, V>[] t = this.table;
            if (t != null) {
                Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                while (true) {
                    Node<K, V> p = it.advance();
                    if (p != null) {
                        action.accept(p.key, p.val);
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: java.util.concurrent.ConcurrentHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        V v;
        if (function != null) {
            Node<K, V>[] t = this.table;
            if (t != null) {
                Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                while (true) {
                    Node<K, V> p = it.advance();
                    if (p != null) {
                        V oldValue = p.val;
                        K key = p.key;
                        do {
                            Object apply = function.apply(key, oldValue);
                            if (apply != null) {
                                if (replaceNode(key, apply, oldValue) != null) {
                                    break;
                                }
                                v = get(key);
                                oldValue = v;
                            } else {
                                throw new NullPointerException();
                            }
                        } while (v != null);
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntryIf(Predicate<? super Map.Entry<K, V>> function) {
        if (function != null) {
            boolean removed = false;
            Node<K, V>[] t = this.table;
            if (t != null) {
                Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                while (true) {
                    Node<K, V> p = it.advance();
                    if (p == null) {
                        break;
                    }
                    K k = p.key;
                    V v = p.val;
                    if (function.test(new AbstractMap.SimpleImmutableEntry<>(k, v)) && replaceNode(k, null, v) != null) {
                        removed = true;
                    }
                }
            }
            return removed;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public boolean removeValueIf(Predicate<? super V> function) {
        if (function != null) {
            boolean removed = false;
            Node<K, V>[] t = this.table;
            if (t != null) {
                Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                while (true) {
                    Node<K, V> p = it.advance();
                    if (p == null) {
                        break;
                    }
                    K k = p.key;
                    V v = p.val;
                    if (function.test(v) && replaceNode(k, null, v) != null) {
                        removed = true;
                    }
                }
            }
            return removed;
        }
        throw new NullPointerException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v5, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r8v7, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r12v2, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r10v9, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r4v4 int: [D('r' java.util.concurrent.ConcurrentHashMap$Node<K, V>), D('fh' int)] */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        TreeNode<K, V> p;
        K ek;
        if (key == null || mappingFunction == null) {
            throw new NullPointerException();
        }
        int h = spread(key.hashCode());
        V val = null;
        int binCount = 0;
        Node<K, V>[] tab = this.table;
        while (true) {
            if (tab != null) {
                int n = tab.length;
                if (n != 0) {
                    int i = (n - 1) & h;
                    Node<K, V> f = tabAt(tab, i);
                    if (f == null) {
                        Node<K, V> r = new ReservationNode<>();
                        synchronized (r) {
                            if (casTabAt(tab, i, null, r)) {
                                binCount = 1;
                                Node<K, V> node = null;
                                try {
                                    Object apply = mappingFunction.apply(key);
                                    val = apply;
                                    if (apply != 0) {
                                        node = new Node<>(h, key, val, null);
                                    }
                                } finally {
                                    setTabAt(tab, i, node);
                                }
                            }
                        }
                        if (binCount != 0) {
                            break;
                        }
                    } else {
                        int fh = f.hash;
                        if (fh == -1) {
                            tab = helpTransfer(tab, f);
                        } else {
                            boolean added = false;
                            synchronized (f) {
                                if (tabAt(tab, i) == f) {
                                    if (fh >= 0) {
                                        binCount = 1;
                                        Node<K, V> e = f;
                                        while (true) {
                                            if (e.hash != h || ((ek = e.key) != key && (ek == null || !key.equals(ek)))) {
                                                Node<K, V> node2 = e.next;
                                                e = node2;
                                                if (node2 == null) {
                                                    Object apply2 = mappingFunction.apply(key);
                                                    val = apply2;
                                                    if (apply2 != 0) {
                                                        if (e.next == null) {
                                                            added = true;
                                                            e.next = new Node<>(h, key, val, null);
                                                        } else {
                                                            throw new IllegalStateException("Recursive update");
                                                        }
                                                    }
                                                } else {
                                                    binCount++;
                                                }
                                            }
                                        }
                                        val = e.val;
                                    } else if (f instanceof TreeBin) {
                                        binCount = 2;
                                        TreeBin<K, V> t = (TreeBin) f;
                                        TreeNode<K, V> r2 = t.root;
                                        if (r2 == null || (p = r2.findTreeNode(h, key, null)) == null) {
                                            Object apply3 = mappingFunction.apply(key);
                                            val = apply3;
                                            if (apply3 != 0) {
                                                added = true;
                                                t.putTreeVal(h, key, val);
                                            }
                                        } else {
                                            val = p.val;
                                        }
                                    } else if (f instanceof ReservationNode) {
                                        throw new IllegalStateException("Recursive update");
                                    }
                                }
                            }
                            if (binCount != 0) {
                                if (binCount >= 8) {
                                    treeifyBin(tab, i);
                                }
                                if (!added) {
                                    return val;
                                }
                            }
                        }
                    }
                }
            }
            tab = initTable();
        }
        if (val != null) {
            addCount(1, binCount);
        }
        return val;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v12, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r11v6, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        TreeNode<K, V> p;
        K ek;
        if (key == null || remappingFunction == null) {
            throw new NullPointerException();
        }
        int h = spread(key.hashCode());
        V val = null;
        int delta = 0;
        int binCount = 0;
        Node<K, V>[] tab = this.table;
        while (true) {
            if (tab != null) {
                int n = tab.length;
                if (n != 0) {
                    int i = (n - 1) & h;
                    Node<K, V> f = tabAt(tab, i);
                    if (f == null) {
                        break;
                    }
                    int fh = f.hash;
                    if (fh == -1) {
                        tab = helpTransfer(tab, f);
                    } else {
                        synchronized (f) {
                            if (tabAt(tab, i) == f) {
                                Node<K, V> pred = null;
                                if (fh >= 0) {
                                    binCount = 1;
                                    Node<K, V> e = f;
                                    while (true) {
                                        if (e.hash != h || ((ek = e.key) != key && (ek == null || !key.equals(ek)))) {
                                            pred = e;
                                            Node<K, V> node = e.next;
                                            e = node;
                                            if (node == null) {
                                                break;
                                            }
                                            binCount++;
                                        }
                                    }
                                    val = remappingFunction.apply(key, e.val);
                                    if (val != null) {
                                        e.val = val;
                                    } else {
                                        delta = -1;
                                        Node<K, V> en = e.next;
                                        if (pred != null) {
                                            pred.next = en;
                                        } else {
                                            setTabAt(tab, i, en);
                                        }
                                    }
                                } else if (f instanceof TreeBin) {
                                    binCount = 2;
                                    TreeBin<K, V> t = (TreeBin) f;
                                    TreeNode<K, V> r = t.root;
                                    if (!(r == null || (p = r.findTreeNode(h, key, null)) == null)) {
                                        val = remappingFunction.apply(key, (Object) p.val);
                                        if (val != null) {
                                            p.val = val;
                                        } else {
                                            delta = -1;
                                            if (t.removeTreeNode(p)) {
                                                setTabAt(tab, i, untreeify(t.first));
                                            }
                                        }
                                    }
                                } else if (f instanceof ReservationNode) {
                                    throw new IllegalStateException("Recursive update");
                                }
                            }
                        }
                        if (binCount != 0) {
                            break;
                        }
                    }
                }
            }
            tab = initTable();
        }
        if (delta != 0) {
            addCount((long) delta, binCount);
        }
        return val;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r16v0, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r15v3, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r12v5, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r0v28, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0092, code lost:
        r5 = r20.apply(r2, r6.val);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0099, code lost:
        if (r5 == null) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009b, code lost:
        r6.val = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x009e, code lost:
        r8 = -1;
        r12 = r6.next;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a1, code lost:
        if (r0 == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a3, code lost:
        r0.next = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a6, code lost:
        setTabAt(r7, r0, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x011f, code lost:
        if (r6 == 0) goto L_0x0137;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0123, code lost:
        if (r6 < 8) goto L_0x0128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0125, code lost:
        treeifyBin(r7, r0);
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V compute(K r19, java.util.function.BiFunction<? super K, ? super V, ? extends V> r20) {
        /*
        // Method dump skipped, instructions count: 321
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.compute(java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r15v5, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r6v23, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r0v9 int: [D('delta' int), D('fh' int)] */
    /* JADX INFO: Multiple debug info for r6v26 java.util.concurrent.ConcurrentHashMap$Node<K, V>: [D('delta' int), D('en' java.util.concurrent.ConcurrentHashMap$Node<K, V>)] */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0132, code lost:
        if (r9 == 0) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0136, code lost:
        if (r9 < 8) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0138, code lost:
        treeifyBin(r8, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x013b, code lost:
        r0 = r6;
        r6 = r7;
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V merge(K r20, V r21, java.util.function.BiFunction<? super V, ? super V, ? extends V> r22) {
        /*
        // Method dump skipped, instructions count: 350
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.merge(java.lang.Object, java.lang.Object, java.util.function.BiFunction):java.lang.Object");
    }

    public boolean contains(Object value) {
        return containsValue(value);
    }

    public Enumeration<K> keys() {
        Node<K, V>[] t = this.table;
        int f = t == null ? 0 : t.length;
        return new KeyIterator(t, f, 0, f, this);
    }

    public Enumeration<V> elements() {
        Node<K, V>[] t = this.table;
        int f = t == null ? 0 : t.length;
        return new ValueIterator(t, f, 0, f, this);
    }

    public long mappingCount() {
        long n = sumCount();
        if (n < 0) {
            return 0;
        }
        return n;
    }

    public static <K> KeySetView<K, Boolean> newKeySet() {
        return new KeySetView<>(new ConcurrentHashMap(), Boolean.TRUE);
    }

    public static <K> KeySetView<K, Boolean> newKeySet(int initialCapacity) {
        return new KeySetView<>(new ConcurrentHashMap(initialCapacity), Boolean.TRUE);
    }

    public KeySetView<K, V> keySet(V mappedValue) {
        if (mappedValue != null) {
            return new KeySetView<>(this, mappedValue);
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class ForwardingNode<K, V> extends Node<K, V> {
        final Node<K, V>[] nextTable;

        ForwardingNode(Node<K, V>[] tab) {
            super(-1, null, null, null);
            this.nextTable = tab;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
            if ((r4 instanceof java.util.concurrent.ConcurrentHashMap.ForwardingNode) == false) goto L_0x0035;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
            r0 = ((java.util.concurrent.ConcurrentHashMap.ForwardingNode) r4).nextTable;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
            return r4.find(r8, r9);
         */
        @Override // java.util.concurrent.ConcurrentHashMap.Node
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.concurrent.ConcurrentHashMap.Node<K, V> find(int r8, java.lang.Object r9) {
            /*
                r7 = this;
                java.util.concurrent.ConcurrentHashMap$Node<K, V>[] r0 = r7.nextTable
            L_0x0002:
                r1 = 0
                if (r9 == 0) goto L_0x0041
                if (r0 == 0) goto L_0x0041
                int r2 = r0.length
                r3 = r2
                if (r2 == 0) goto L_0x0041
                int r2 = r3 + -1
                r2 = r2 & r8
                java.util.concurrent.ConcurrentHashMap$Node r2 = java.util.concurrent.ConcurrentHashMap.tabAt(r0, r2)
                r4 = r2
                if (r2 != 0) goto L_0x0016
                goto L_0x0041
            L_0x0016:
                int r2 = r4.hash
                r5 = r2
                if (r2 != r8) goto L_0x0029
                K r2 = r4.key
                r6 = r2
                if (r2 == r9) goto L_0x0028
                if (r6 == 0) goto L_0x0029
                boolean r2 = r9.equals(r6)
                if (r2 == 0) goto L_0x0029
            L_0x0028:
                return r4
            L_0x0029:
                if (r5 >= 0) goto L_0x003a
                boolean r1 = r4 instanceof java.util.concurrent.ConcurrentHashMap.ForwardingNode
                if (r1 == 0) goto L_0x0035
                r1 = r4
                java.util.concurrent.ConcurrentHashMap$ForwardingNode r1 = (java.util.concurrent.ConcurrentHashMap.ForwardingNode) r1
                java.util.concurrent.ConcurrentHashMap$Node<K, V>[] r0 = r1.nextTable
                goto L_0x0002
            L_0x0035:
                java.util.concurrent.ConcurrentHashMap$Node r1 = r4.find(r8, r9)
                return r1
            L_0x003a:
                java.util.concurrent.ConcurrentHashMap$Node<K, V> r2 = r4.next
                r4 = r2
                if (r2 != 0) goto L_0x0040
                return r1
            L_0x0040:
                goto L_0x0016
            L_0x0041:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.ForwardingNode.find(int, java.lang.Object):java.util.concurrent.ConcurrentHashMap$Node");
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ReservationNode<K, V> extends Node<K, V> {
        ReservationNode() {
            super(-3, null, null, null);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentHashMap.Node
        public Node<K, V> find(int h, Object k) {
            return null;
        }
    }

    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | 32768;
    }

    private final Node<K, V>[] initTable() {
        Node<K, V>[] tab;
        while (true) {
            Node<K, V>[] nodeArr = this.table;
            tab = nodeArr;
            if (nodeArr != null && tab.length != 0) {
                break;
            }
            int i = this.sizeCtl;
            int sc = i;
            if (i < 0) {
                Thread.yield();
            } else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                try {
                    Node<K, V>[] nodeArr2 = this.table;
                    tab = nodeArr2;
                    if (nodeArr2 == null || tab.length == 0) {
                        int n = sc > 0 ? sc : 16;
                        Node<K, V>[] nt = new Node[n];
                        tab = nt;
                        this.table = nt;
                        sc = n - (n >>> 2);
                    }
                } finally {
                    this.sizeCtl = sc;
                }
            }
        }
        return tab;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001b, code lost:
        if (r0.compareAndSwapLong(r23, r2, r4, r6) == false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void addCount(long r24, int r26) {
        /*
        // Method dump skipped, instructions count: 185
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.addCount(long, int):void");
    }

    /* access modifiers changed from: package-private */
    public final Node<K, V>[] helpTransfer(Node<K, V>[] tab, Node<K, V> f) {
        Node<K, V>[] nextTab;
        int sc;
        if (tab == null || !(f instanceof ForwardingNode) || (nextTab = ((ForwardingNode) f).nextTable) == null) {
            return this.table;
        }
        int rs = resizeStamp(tab.length);
        while (true) {
            if (nextTab == this.nextTable && this.table == tab && (sc = this.sizeCtl) < 0 && (sc >>> 16) == rs && sc != rs + 1 && sc != 65535 + rs && this.transferIndex > 0) {
                if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1)) {
                    transfer(tab, nextTab);
                    break;
                }
            } else {
                break;
            }
        }
        return nextTab;
    }

    private final void tryPresize(int size) {
        int c;
        int n;
        if (size >= 536870912) {
            c = 1073741824;
        } else {
            c = tableSizeFor((size >>> 1) + size + 1);
        }
        while (true) {
            int i = this.sizeCtl;
            int sc = i;
            if (i >= 0) {
                Node<K, V>[] tab = this.table;
                if (tab == null || (n = tab.length) == 0) {
                    int n2 = sc > c ? sc : c;
                    if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                        try {
                            if (this.table == tab) {
                                this.table = new Node[n2];
                                sc = n2 - (n2 >>> 2);
                            }
                        } finally {
                            this.sizeCtl = sc;
                        }
                    }
                } else if (c > sc && n < 1073741824) {
                    if (tab == this.table) {
                        if (U.compareAndSwapInt(this, SIZECTL, sc, (resizeStamp(n) << 16) + 2)) {
                            transfer(tab, null);
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r7v13. Raw type applied. Possible types: java.util.concurrent.ConcurrentHashMap$Node<K, V> */
    /* JADX INFO: Multiple debug info for r12v6 int: [D('h' int), D('stride' int)] */
    /* JADX INFO: Multiple debug info for r0v27 K: [D('pk' K), D('runBit' int)] */
    /* JADX INFO: Multiple debug info for r1v23 V: [D('pv' V), D('fh' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01f6, code lost:
        r1 = 1;
        r3 = 16;
        r7 = r32;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void transfer(java.util.concurrent.ConcurrentHashMap.Node<K, V>[] r33, java.util.concurrent.ConcurrentHashMap.Node<K, V>[] r34) {
        /*
        // Method dump skipped, instructions count: 594
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.transfer(java.util.concurrent.ConcurrentHashMap$Node[], java.util.concurrent.ConcurrentHashMap$Node[]):void");
    }

    /* access modifiers changed from: package-private */
    public static final class CounterCell {
        volatile long value;

        CounterCell(long x) {
            this.value = x;
        }
    }

    /* access modifiers changed from: package-private */
    public final long sumCount() {
        CounterCell[] as = this.counterCells;
        long sum = this.baseCount;
        if (as != null) {
            for (CounterCell a : as) {
                if (a != null) {
                    sum += a.value;
                }
            }
        }
        return sum;
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: Multiple debug info for r0v2 java.util.concurrent.ConcurrentHashMap$CounterCell[]: [D('wasUncontended' boolean), D('as' java.util.concurrent.ConcurrentHashMap$CounterCell[])] */
    private final void fullAddCount(long x, boolean wasUncontended) {
        boolean wasUncontended2;
        int n;
        CounterCell a;
        int m;
        int probe = ThreadLocalRandom.getProbe();
        int h = probe;
        if (probe == 0) {
            ThreadLocalRandom.localInit();
            h = ThreadLocalRandom.getProbe();
            wasUncontended2 = true;
        } else {
            wasUncontended2 = wasUncontended;
        }
        boolean wasUncontended3 = wasUncontended2;
        int h2 = h;
        boolean collide = false;
        while (true) {
            CounterCell[] as = this.counterCells;
            if (as != null && (n = as.length) > 0) {
                CounterCell a2 = as[(n - 1) & h2];
                if (a2 == null) {
                    if (this.cellsBusy == 0) {
                        CounterCell r = new CounterCell(x);
                        if (this.cellsBusy == 0) {
                            a = a2;
                            if (U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                                boolean created = false;
                                try {
                                    CounterCell[] rs = this.counterCells;
                                    if (rs != null && (m = rs.length) > 0) {
                                        int j = (m - 1) & h2;
                                        if (rs[j] == null) {
                                            rs[j] = r;
                                            created = true;
                                        }
                                    }
                                    if (created) {
                                        return;
                                    }
                                } finally {
                                    this.cellsBusy = 0;
                                }
                            }
                        } else {
                            a = a2;
                        }
                    } else {
                        a = a2;
                    }
                    collide = false;
                } else if (!wasUncontended3) {
                    wasUncontended3 = true;
                } else {
                    Unsafe unsafe = U;
                    long j2 = CELLVALUE;
                    long v = a2.value;
                    if (!unsafe.compareAndSwapLong(a2, j2, v, v + x)) {
                        if (this.counterCells == as) {
                            if (n < NCPU) {
                                if (!collide) {
                                    collide = true;
                                } else if (this.cellsBusy == 0) {
                                    if (U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                                        try {
                                            if (this.counterCells == as) {
                                                CounterCell[] rs2 = new CounterCell[(n << 1)];
                                                for (int i = 0; i < n; i++) {
                                                    rs2[i] = as[i];
                                                }
                                                this.counterCells = rs2;
                                            }
                                            this.cellsBusy = 0;
                                            collide = false;
                                        } catch (Throwable th) {
                                            this.cellsBusy = 0;
                                            throw th;
                                        }
                                    }
                                }
                            }
                        }
                        collide = false;
                    } else {
                        return;
                    }
                }
                h2 = ThreadLocalRandom.advanceProbe(h2);
            } else if (this.cellsBusy == 0 && this.counterCells == as && U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
                boolean init = false;
                try {
                    if (this.counterCells == as) {
                        CounterCell[] rs3 = new CounterCell[2];
                        rs3[h2 & 1] = new CounterCell(x);
                        this.counterCells = rs3;
                        init = true;
                    }
                    if (init) {
                        return;
                    }
                } finally {
                    this.cellsBusy = 0;
                }
            } else {
                Unsafe unsafe2 = U;
                long j3 = BASECOUNT;
                long v2 = this.baseCount;
                if (unsafe2.compareAndSwapLong(this, j3, v2, v2 + x)) {
                    return;
                }
            }
        }
    }

    private final void treeifyBin(Node<K, V>[] tab, int index) {
        if (tab != null) {
            int n = tab.length;
            if (n < 64) {
                tryPresize(n << 1);
                return;
            }
            Node<K, V> b = tabAt(tab, index);
            if (b != null && b.hash >= 0) {
                synchronized (b) {
                    if (tabAt(tab, index) == b) {
                        TreeNode<K, V> hd = null;
                        TreeNode<K, V> tl = null;
                        for (Node<K, V> e = b; e != null; e = e.next) {
                            TreeNode<K, V> p = new TreeNode<>(e.hash, e.key, e.val, null, null);
                            p.prev = tl;
                            if (tl == null) {
                                hd = p;
                            } else {
                                tl.next = p;
                            }
                            tl = p;
                        }
                        setTabAt(tab, index, new TreeBin(hd));
                    }
                }
            }
        }
    }

    static <K, V> Node<K, V> untreeify(Node<K, V> b) {
        Node<K, V> hd = null;
        Node<K, V> tl = null;
        for (Node<K, V> q = b; q != null; q = q.next) {
            Node<K, V> p = new Node<>(q.hash, q.key, q.val, null);
            if (tl == null) {
                hd = p;
            } else {
                tl.next = p;
            }
            tl = p;
        }
        return hd;
    }

    /* access modifiers changed from: package-private */
    public static final class TreeNode<K, V> extends Node<K, V> {
        TreeNode<K, V> left;
        TreeNode<K, V> parent;
        TreeNode<K, V> prev;
        boolean red;
        TreeNode<K, V> right;

        TreeNode(int hash, K key, V val, Node<K, V> next, TreeNode<K, V> parent2) {
            super(hash, key, val, next);
            this.parent = parent2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentHashMap.Node
        public Node<K, V> find(int h, Object k) {
            return findTreeNode(h, k, null);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
            if (r3 != null) goto L_0x0031;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.concurrent.ConcurrentHashMap.TreeNode<K, V> findTreeNode(int r8, java.lang.Object r9, java.lang.Class<?> r10) {
            /*
                r7 = this;
                if (r9 == 0) goto L_0x004c
                r0 = r7
            L_0x0003:
                java.util.concurrent.ConcurrentHashMap$TreeNode<K, V> r1 = r0.left
                java.util.concurrent.ConcurrentHashMap$TreeNode<K, V> r2 = r0.right
                int r3 = r0.hash
                r4 = r3
                if (r3 <= r8) goto L_0x000e
                r0 = r1
                goto L_0x0048
            L_0x000e:
                if (r4 >= r8) goto L_0x0012
                r0 = r2
                goto L_0x0048
            L_0x0012:
                java.lang.Object r3 = r0.key
                r5 = r3
                if (r3 == r9) goto L_0x004b
                if (r5 == 0) goto L_0x0020
                boolean r3 = r9.equals(r5)
                if (r3 == 0) goto L_0x0020
                goto L_0x004b
            L_0x0020:
                if (r1 != 0) goto L_0x0024
                r0 = r2
                goto L_0x0048
            L_0x0024:
                if (r2 != 0) goto L_0x0028
                r0 = r1
                goto L_0x0048
            L_0x0028:
                if (r10 != 0) goto L_0x0031
                java.lang.Class r3 = java.util.concurrent.ConcurrentHashMap.comparableClassFor(r9)
                r10 = r3
                if (r3 == 0) goto L_0x003f
            L_0x0031:
                int r3 = java.util.concurrent.ConcurrentHashMap.compareComparables(r10, r9, r5)
                r6 = r3
                if (r3 == 0) goto L_0x003f
                if (r6 >= 0) goto L_0x003c
                r3 = r1
                goto L_0x003d
            L_0x003c:
                r3 = r2
            L_0x003d:
                r0 = r3
                goto L_0x0048
            L_0x003f:
                java.util.concurrent.ConcurrentHashMap$TreeNode r3 = r2.findTreeNode(r8, r9, r10)
                r6 = r3
                if (r3 == 0) goto L_0x0047
                return r6
            L_0x0047:
                r0 = r1
            L_0x0048:
                if (r0 != 0) goto L_0x0003
                goto L_0x004c
            L_0x004b:
                return r0
            L_0x004c:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.TreeNode.findTreeNode(int, java.lang.Object, java.lang.Class):java.util.concurrent.ConcurrentHashMap$TreeNode");
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TreeBin<K, V> extends Node<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long LOCKSTATE;
        static final int READER = 4;
        private static final Unsafe U = Unsafe.getUnsafe();
        static final int WAITER = 2;
        static final int WRITER = 1;
        volatile TreeNode<K, V> first;
        volatile int lockState;
        TreeNode<K, V> root;
        volatile Thread waiter;

        static {
            try {
                LOCKSTATE = U.objectFieldOffset(TreeBin.class.getDeclaredField("lockState"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }

        static int tieBreakOrder(Object a, Object b) {
            int d;
            if (a != null && b != null && (d = a.getClass().getName().compareTo(b.getClass().getName())) != 0) {
                return d;
            }
            return System.identityHashCode(a) <= System.identityHashCode(b) ? -1 : 1;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
            if (r9 != null) goto L_0x0038;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        TreeBin(java.util.concurrent.ConcurrentHashMap.TreeNode<K, V> r14) {
            /*
            // Method dump skipped, instructions count: 101
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.TreeBin.<init>(java.util.concurrent.ConcurrentHashMap$TreeNode):void");
        }

        private final void lockRoot() {
            if (!U.compareAndSwapInt(this, LOCKSTATE, 0, 1)) {
                contendedLock();
            }
        }

        private final void unlockRoot() {
            this.lockState = 0;
        }

        private final void contendedLock() {
            boolean waiting = false;
            while (true) {
                int s = this.lockState;
                if ((s & -3) == 0) {
                    if (U.compareAndSwapInt(this, LOCKSTATE, s, 1)) {
                        break;
                    }
                } else if ((s & 2) == 0) {
                    if (U.compareAndSwapInt(this, LOCKSTATE, s, s | 2)) {
                        waiting = true;
                        this.waiter = Thread.currentThread();
                    }
                } else if (waiting) {
                    LockSupport.park(this);
                }
            }
            if (waiting) {
                this.waiter = null;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentHashMap.Node
        public final Node<K, V> find(int h, Object k) {
            Thread w;
            K ek;
            TreeNode<K, V> p = null;
            if (k != null) {
                Node<K, V> e = this.first;
                while (e != null) {
                    int s = this.lockState;
                    if ((s & 3) != 0) {
                        if (e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek)))) {
                            return e;
                        }
                        e = e.next;
                    } else if (U.compareAndSwapInt(this, LOCKSTATE, s, s + 4)) {
                        try {
                            TreeNode<K, V> r = this.root;
                            if (r != null) {
                                p = r.findTreeNode(h, k, null);
                            }
                            return p;
                        } finally {
                            if (U.getAndAddInt(this, LOCKSTATE, -4) == 6 && (w = this.waiter) != null) {
                                LockSupport.unpark(w);
                            }
                        }
                    }
                }
            }
            return null;
        }

        /* JADX INFO: Multiple debug info for r2v7 java.lang.Object: [D('pk' K), D('dir' int)] */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
            if (r2 != null) goto L_0x004b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.concurrent.ConcurrentHashMap.TreeNode<K, V> putTreeVal(int r17, K r18, V r19) {
            /*
            // Method dump skipped, instructions count: 199
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.TreeBin.putTreeVal(int, java.lang.Object, java.lang.Object):java.util.concurrent.ConcurrentHashMap$TreeNode");
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public final boolean removeTreeNode(TreeNode<K, V> p) {
            TreeNode<K, V> rl;
            TreeNode<K, V> replacement;
            TreeNode<K, V> pp;
            TreeNode<K, V> next = (TreeNode) p.next;
            TreeNode<K, V> pred = p.prev;
            if (pred == null) {
                this.first = next;
            } else {
                pred.next = next;
            }
            if (next != null) {
                next.prev = pred;
            }
            if (this.first == null) {
                this.root = null;
                return true;
            }
            TreeNode<K, V> treeNode = this.root;
            TreeNode<K, V> r = treeNode;
            if (treeNode == null || r.right == null || (rl = r.left) == null || rl.left == null) {
                return true;
            }
            lockRoot();
            try {
                TreeNode<K, V> pl = p.left;
                TreeNode<K, V> pr = p.right;
                if (pl != null && pr != null) {
                    TreeNode<K, V> s = pr;
                    while (true) {
                        TreeNode<K, V> sl = s.left;
                        if (sl == null) {
                            break;
                        }
                        s = sl;
                    }
                    boolean c = s.red;
                    s.red = p.red;
                    p.red = c;
                    TreeNode<K, V> sr = s.right;
                    TreeNode<K, V> pp2 = p.parent;
                    if (s == pr) {
                        p.parent = s;
                        s.right = p;
                    } else {
                        TreeNode<K, V> sp = s.parent;
                        p.parent = sp;
                        if (sp != null) {
                            if (s == sp.left) {
                                sp.left = p;
                            } else {
                                sp.right = p;
                            }
                        }
                        s.right = pr;
                        pr.parent = s;
                    }
                    p.left = null;
                    p.right = sr;
                    if (sr != null) {
                        sr.parent = p;
                    }
                    s.left = pl;
                    pl.parent = s;
                    s.parent = pp2;
                    if (pp2 == null) {
                        r = s;
                    } else if (p == pp2.left) {
                        pp2.left = s;
                    } else {
                        pp2.right = s;
                    }
                    if (sr != null) {
                        replacement = sr;
                    } else {
                        replacement = p;
                    }
                } else if (pl != null) {
                    replacement = pl;
                } else if (pr != null) {
                    replacement = pr;
                } else {
                    replacement = p;
                }
                if (replacement != p) {
                    TreeNode<K, V> pp3 = p.parent;
                    replacement.parent = pp3;
                    if (pp3 == null) {
                        r = replacement;
                    } else if (p == pp3.left) {
                        pp3.left = replacement;
                    } else {
                        pp3.right = replacement;
                    }
                    p.parent = null;
                    p.right = null;
                    p.left = null;
                }
                this.root = p.red ? r : balanceDeletion(r, replacement);
                if (p == replacement && (pp = p.parent) != null) {
                    if (p == pp.left) {
                        pp.left = null;
                    } else if (p == pp.right) {
                        pp.right = null;
                    }
                    p.parent = null;
                }
                unlockRoot();
                return false;
            } catch (Throwable th) {
                unlockRoot();
                throw th;
            }
        }

        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root2, TreeNode<K, V> p) {
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
                    root2 = r;
                    r.red = false;
                } else if (pp.left == p) {
                    pp.left = r;
                } else {
                    pp.right = r;
                }
                r.left = p;
                p.parent = r;
            }
            return root2;
        }

        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root2, TreeNode<K, V> p) {
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
                    root2 = l;
                    l.red = false;
                } else if (pp.right == p) {
                    pp.right = l;
                } else {
                    pp.left = l;
                }
                l.right = p;
                p.parent = l;
            }
            return root2;
        }

        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root2, TreeNode<K, V> x) {
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
                                root2 = rotateLeft(root2, xp);
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
                                    root2 = rotateRight(root2, xpp);
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
                            root2 = rotateRight(root2, xp);
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
                                root2 = rotateLeft(root2, xpp);
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
            return root2;
        }

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root2, TreeNode<K, V> x) {
            while (x != null && x != root2) {
                TreeNode<K, V> treeNode = x.parent;
                TreeNode<K, V> xp = treeNode;
                if (treeNode == null) {
                    x.red = false;
                    return x;
                } else if (x.red) {
                    x.red = false;
                    return root2;
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
                            root2 = rotateLeft(root2, xp);
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
                                    root2 = rotateRight(root2, xpr);
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
                                    root2 = rotateLeft(root2, xp);
                                }
                                x = root2;
                            }
                        }
                    } else {
                        if (xpl != null && xpl.red) {
                            xpl.red = false;
                            xp.red = true;
                            root2 = rotateRight(root2, xp);
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
                                    root2 = rotateLeft(root2, xpl);
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
                                    root2 = rotateRight(root2, xp);
                                }
                                x = root2;
                            }
                        }
                    }
                }
            }
            return root2;
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

    /* access modifiers changed from: package-private */
    public static final class TableStack<K, V> {
        int index;
        int length;
        TableStack<K, V> next;
        Node<K, V>[] tab;

        TableStack() {
        }
    }

    /* access modifiers changed from: package-private */
    public static class Traverser<K, V> {
        int baseIndex;
        int baseLimit;
        final int baseSize;
        int index;
        Node<K, V> next = null;
        TableStack<K, V> spare;
        TableStack<K, V> stack;
        Node<K, V>[] tab;

        Traverser(Node<K, V>[] tab2, int size, int index2, int limit) {
            this.tab = tab2;
            this.baseSize = size;
            this.index = index2;
            this.baseIndex = index2;
            this.baseLimit = limit;
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> advance() {
            Node<K, V>[] t;
            int n;
            int i;
            Node<K, V> node = this.next;
            Node<K, V> e = node;
            if (node != null) {
                e = e.next;
            }
            while (e == null) {
                if (this.baseIndex >= this.baseLimit || (t = this.tab) == null || (n = t.length) <= (i = this.index) || i < 0) {
                    this.next = null;
                    return null;
                }
                Node<K, V> tabAt = ConcurrentHashMap.tabAt(t, i);
                e = tabAt;
                if (tabAt != null && e.hash < 0) {
                    if (e instanceof ForwardingNode) {
                        this.tab = ((ForwardingNode) e).nextTable;
                        e = null;
                        pushState(t, i, n);
                    } else {
                        e = e instanceof TreeBin ? ((TreeBin) e).first : null;
                    }
                }
                if (this.stack != null) {
                    recoverState(n);
                } else {
                    int i2 = this.baseSize + i;
                    this.index = i2;
                    if (i2 >= n) {
                        int i3 = this.baseIndex + 1;
                        this.baseIndex = i3;
                        this.index = i3;
                    }
                }
            }
            this.next = e;
            return e;
        }

        private void pushState(Node<K, V>[] t, int i, int n) {
            TableStack<K, V> s = this.spare;
            if (s != null) {
                this.spare = s.next;
            } else {
                s = new TableStack<>();
            }
            s.tab = t;
            s.length = n;
            s.index = i;
            s.next = this.stack;
            this.stack = s;
        }

        private void recoverState(int n) {
            TableStack<K, V> s;
            while (true) {
                s = this.stack;
                if (s == null) {
                    break;
                }
                int i = this.index;
                int len = s.length;
                int i2 = i + len;
                this.index = i2;
                if (i2 < n) {
                    break;
                }
                n = len;
                this.index = s.index;
                this.tab = s.tab;
                s.tab = null;
                TableStack<K, V> next2 = s.next;
                s.next = this.spare;
                this.stack = next2;
                this.spare = s;
            }
            if (s == null) {
                int i3 = this.index + this.baseSize;
                this.index = i3;
                if (i3 >= n) {
                    int i4 = this.baseIndex + 1;
                    this.baseIndex = i4;
                    this.index = i4;
                }
            }
        }
    }

    static class BaseIterator<K, V> extends Traverser<K, V> {
        Node<K, V> lastReturned;
        final ConcurrentHashMap<K, V> map;

        BaseIterator(Node<K, V>[] tab, int size, int index, int limit, ConcurrentHashMap<K, V> map2) {
            super(tab, size, index, limit);
            this.map = map2;
            advance();
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        public final boolean hasMoreElements() {
            return this.next != null;
        }

        public final void remove() {
            Node<K, V> p = this.lastReturned;
            if (p != null) {
                this.lastReturned = null;
                this.map.replaceNode(p.key, null, null);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class KeyIterator<K, V> extends BaseIterator<K, V> implements Iterator<K>, Enumeration<K> {
        KeyIterator(Node<K, V>[] tab, int index, int size, int limit, ConcurrentHashMap<K, V> map) {
            super(tab, index, size, limit, map);
        }

        @Override // java.util.Iterator
        public final K next() {
            Node<K, V> p = this.next;
            if (p != null) {
                K k = p.key;
                this.lastReturned = p;
                advance();
                return k;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Enumeration
        public final K nextElement() {
            return next();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ValueIterator<K, V> extends BaseIterator<K, V> implements Iterator<V>, Enumeration<V> {
        ValueIterator(Node<K, V>[] tab, int index, int size, int limit, ConcurrentHashMap<K, V> map) {
            super(tab, index, size, limit, map);
        }

        @Override // java.util.Iterator
        public final V next() {
            Node<K, V> p = this.next;
            if (p != null) {
                V v = p.val;
                this.lastReturned = p;
                advance();
                return v;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Enumeration
        public final V nextElement() {
            return next();
        }
    }

    static final class EntryIterator<K, V> extends BaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        EntryIterator(Node<K, V>[] tab, int index, int size, int limit, ConcurrentHashMap<K, V> map) {
            super(tab, index, size, limit, map);
        }

        @Override // java.util.Iterator
        public final Map.Entry<K, V> next() {
            Node<K, V> p = this.next;
            if (p != null) {
                K k = p.key;
                V v = p.val;
                this.lastReturned = p;
                advance();
                return new MapEntry(k, v, this.map);
            }
            throw new NoSuchElementException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class MapEntry<K, V> implements Map.Entry<K, V> {
        final K key;
        final ConcurrentHashMap<K, V> map;
        V val;

        MapEntry(K key2, V val2, ConcurrentHashMap<K, V> map2) {
            this.key = key2;
            this.val = val2;
            this.map = map2;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.val;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.key.hashCode() ^ this.val.hashCode();
        }

        public String toString() {
            return Helpers.mapEntryToString(this.key, this.val);
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object o) {
            Map.Entry<?, ?> e;
            Object k;
            Object v;
            K k2;
            V v2;
            return (o instanceof Map.Entry) && (k = (e = (Map.Entry) o).getKey()) != null && (v = e.getValue()) != null && (k == (k2 = this.key) || k.equals(k2)) && (v == (v2 = this.val) || v.equals(v2));
        }

        @Override // java.util.Map.Entry
        public V setValue(V value) {
            if (value != null) {
                V v = this.val;
                this.val = value;
                this.map.put(this.key, value);
                return v;
            }
            throw new NullPointerException();
        }
    }

    static final class KeySpliterator<K, V> extends Traverser<K, V> implements Spliterator<K> {
        long est;

        KeySpliterator(Node<K, V>[] tab, int size, int index, int limit, long est2) {
            super(tab, size, index, limit);
            this.est = est2;
        }

        @Override // java.util.Spliterator
        public KeySpliterator<K, V> trySplit() {
            int i = this.baseIndex;
            int f = this.baseLimit;
            int h = (i + f) >>> 1;
            if (h <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i2 = this.baseSize;
            this.baseLimit = h;
            long j = this.est >>> 1;
            this.est = j;
            return new KeySpliterator<>(nodeArr, i2, h, f, j);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> action) {
            if (action != null) {
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        action.accept(p.key);
                    } else {
                        return;
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> action) {
            if (action != null) {
                Node<K, V> p = advance();
                if (p == null) {
                    return false;
                }
                action.accept(p.key);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4353;
        }
    }

    static final class ValueSpliterator<K, V> extends Traverser<K, V> implements Spliterator<V> {
        long est;

        ValueSpliterator(Node<K, V>[] tab, int size, int index, int limit, long est2) {
            super(tab, size, index, limit);
            this.est = est2;
        }

        @Override // java.util.Spliterator
        public ValueSpliterator<K, V> trySplit() {
            int i = this.baseIndex;
            int f = this.baseLimit;
            int h = (i + f) >>> 1;
            if (h <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i2 = this.baseSize;
            this.baseLimit = h;
            long j = this.est >>> 1;
            this.est = j;
            return new ValueSpliterator<>(nodeArr, i2, h, f, j);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super V> action) {
            if (action != null) {
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        action.accept(p.val);
                    } else {
                        return;
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super V> action) {
            if (action != null) {
                Node<K, V> p = advance();
                if (p == null) {
                    return false;
                }
                action.accept(p.val);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return Normalizer2Impl.Hangul.JAMO_L_BASE;
        }
    }

    static final class EntrySpliterator<K, V> extends Traverser<K, V> implements Spliterator<Map.Entry<K, V>> {
        long est;
        final ConcurrentHashMap<K, V> map;

        EntrySpliterator(Node<K, V>[] tab, int size, int index, int limit, long est2, ConcurrentHashMap<K, V> map2) {
            super(tab, size, index, limit);
            this.map = map2;
            this.est = est2;
        }

        @Override // java.util.Spliterator
        public EntrySpliterator<K, V> trySplit() {
            int i = this.baseIndex;
            int f = this.baseLimit;
            int h = (i + f) >>> 1;
            if (h <= i) {
                return null;
            }
            Node[] nodeArr = this.tab;
            int i2 = this.baseSize;
            this.baseLimit = h;
            long j = this.est >>> 1;
            this.est = j;
            return new EntrySpliterator<>(nodeArr, i2, h, f, j, this.map);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        action.accept(new MapEntry(p.key, p.val, this.map));
                    } else {
                        return;
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                Node<K, V> p = advance();
                if (p == null) {
                    return false;
                }
                action.accept(new MapEntry(p.key, p.val, this.map));
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4353;
        }
    }

    /* access modifiers changed from: package-private */
    public final int batchFor(long b) {
        if (b == Long.MAX_VALUE) {
            return 0;
        }
        long n = sumCount();
        if (n <= 1 || n < b) {
            return 0;
        }
        int sp = ForkJoinPool.getCommonPoolParallelism() << 2;
        if (b > 0) {
            long n2 = n / b;
            if (n2 < ((long) sp)) {
                return (int) n2;
            }
        }
        return sp;
    }

    public void forEach(long parallelismThreshold, BiConsumer<? super K, ? super V> action) {
        if (action != null) {
            new ForEachMappingTask(null, batchFor(parallelismThreshold), 0, 0, this.table, action).invoke();
            return;
        }
        throw new NullPointerException();
    }

    public <U> void forEach(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> transformer, Consumer<? super U> action) {
        if (transformer == null || action == null) {
            throw new NullPointerException();
        }
        new ForEachTransformedMappingTask(null, batchFor(parallelismThreshold), 0, 0, this.table, transformer, action).invoke();
    }

    public <U> U search(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> searchFunction) {
        if (searchFunction != null) {
            return (U) new SearchMappingsTask(null, batchFor(parallelismThreshold), 0, 0, this.table, searchFunction, new AtomicReference()).invoke();
        }
        throw new NullPointerException();
    }

    public <U> U reduce(long parallelismThreshold, BiFunction<? super K, ? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
        if (transformer != null && reducer != null) {
            return (U) new MapReduceMappingsTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, reducer).invoke();
        }
        throw new NullPointerException();
    }

    public double reduceToDouble(long parallelismThreshold, ToDoubleBiFunction<? super K, ? super V> transformer, double basis, DoubleBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Double) new MapReduceMappingsToDoubleTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).doubleValue();
        }
        throw new NullPointerException();
    }

    public long reduceToLong(long parallelismThreshold, ToLongBiFunction<? super K, ? super V> transformer, long basis, LongBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Long) new MapReduceMappingsToLongTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).longValue();
        }
        throw new NullPointerException();
    }

    public int reduceToInt(long parallelismThreshold, ToIntBiFunction<? super K, ? super V> transformer, int basis, IntBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Integer) new MapReduceMappingsToIntTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).intValue();
        }
        throw new NullPointerException();
    }

    public void forEachKey(long parallelismThreshold, Consumer<? super K> action) {
        if (action != null) {
            new ForEachKeyTask(null, batchFor(parallelismThreshold), 0, 0, this.table, action).invoke();
            return;
        }
        throw new NullPointerException();
    }

    public <U> void forEachKey(long parallelismThreshold, Function<? super K, ? extends U> transformer, Consumer<? super U> action) {
        if (transformer == null || action == null) {
            throw new NullPointerException();
        }
        new ForEachTransformedKeyTask(null, batchFor(parallelismThreshold), 0, 0, this.table, transformer, action).invoke();
    }

    public <U> U searchKeys(long parallelismThreshold, Function<? super K, ? extends U> searchFunction) {
        if (searchFunction != null) {
            return (U) new SearchKeysTask(null, batchFor(parallelismThreshold), 0, 0, this.table, searchFunction, new AtomicReference()).invoke();
        }
        throw new NullPointerException();
    }

    public K reduceKeys(long parallelismThreshold, BiFunction<? super K, ? super K, ? extends K> reducer) {
        if (reducer != null) {
            return (K) new ReduceKeysTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, reducer).invoke();
        }
        throw new NullPointerException();
    }

    public <U> U reduceKeys(long parallelismThreshold, Function<? super K, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
        if (transformer != null && reducer != null) {
            return (U) new MapReduceKeysTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, reducer).invoke();
        }
        throw new NullPointerException();
    }

    public double reduceKeysToDouble(long parallelismThreshold, ToDoubleFunction<? super K> transformer, double basis, DoubleBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Double) new MapReduceKeysToDoubleTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).doubleValue();
        }
        throw new NullPointerException();
    }

    public long reduceKeysToLong(long parallelismThreshold, ToLongFunction<? super K> transformer, long basis, LongBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Long) new MapReduceKeysToLongTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).longValue();
        }
        throw new NullPointerException();
    }

    public int reduceKeysToInt(long parallelismThreshold, ToIntFunction<? super K> transformer, int basis, IntBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Integer) new MapReduceKeysToIntTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).intValue();
        }
        throw new NullPointerException();
    }

    public void forEachValue(long parallelismThreshold, Consumer<? super V> action) {
        if (action != null) {
            new ForEachValueTask(null, batchFor(parallelismThreshold), 0, 0, this.table, action).invoke();
            return;
        }
        throw new NullPointerException();
    }

    public <U> void forEachValue(long parallelismThreshold, Function<? super V, ? extends U> transformer, Consumer<? super U> action) {
        if (transformer == null || action == null) {
            throw new NullPointerException();
        }
        new ForEachTransformedValueTask(null, batchFor(parallelismThreshold), 0, 0, this.table, transformer, action).invoke();
    }

    public <U> U searchValues(long parallelismThreshold, Function<? super V, ? extends U> searchFunction) {
        if (searchFunction != null) {
            return (U) new SearchValuesTask(null, batchFor(parallelismThreshold), 0, 0, this.table, searchFunction, new AtomicReference()).invoke();
        }
        throw new NullPointerException();
    }

    public V reduceValues(long parallelismThreshold, BiFunction<? super V, ? super V, ? extends V> reducer) {
        if (reducer != null) {
            return (V) new ReduceValuesTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, reducer).invoke();
        }
        throw new NullPointerException();
    }

    public <U> U reduceValues(long parallelismThreshold, Function<? super V, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
        if (transformer != null && reducer != null) {
            return (U) new MapReduceValuesTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, reducer).invoke();
        }
        throw new NullPointerException();
    }

    public double reduceValuesToDouble(long parallelismThreshold, ToDoubleFunction<? super V> transformer, double basis, DoubleBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Double) new MapReduceValuesToDoubleTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).doubleValue();
        }
        throw new NullPointerException();
    }

    public long reduceValuesToLong(long parallelismThreshold, ToLongFunction<? super V> transformer, long basis, LongBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Long) new MapReduceValuesToLongTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).longValue();
        }
        throw new NullPointerException();
    }

    public int reduceValuesToInt(long parallelismThreshold, ToIntFunction<? super V> transformer, int basis, IntBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Integer) new MapReduceValuesToIntTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).intValue();
        }
        throw new NullPointerException();
    }

    public void forEachEntry(long parallelismThreshold, Consumer<? super Map.Entry<K, V>> action) {
        if (action != null) {
            new ForEachEntryTask(null, batchFor(parallelismThreshold), 0, 0, this.table, action).invoke();
            return;
        }
        throw new NullPointerException();
    }

    public <U> void forEachEntry(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> transformer, Consumer<? super U> action) {
        if (transformer == null || action == null) {
            throw new NullPointerException();
        }
        new ForEachTransformedEntryTask(null, batchFor(parallelismThreshold), 0, 0, this.table, transformer, action).invoke();
    }

    public <U> U searchEntries(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> searchFunction) {
        if (searchFunction != null) {
            return (U) new SearchEntriesTask(null, batchFor(parallelismThreshold), 0, 0, this.table, searchFunction, new AtomicReference()).invoke();
        }
        throw new NullPointerException();
    }

    public Map.Entry<K, V> reduceEntries(long parallelismThreshold, BiFunction<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer) {
        if (reducer != null) {
            return (Map.Entry) new ReduceEntriesTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, reducer).invoke();
        }
        throw new NullPointerException();
    }

    public <U> U reduceEntries(long parallelismThreshold, Function<Map.Entry<K, V>, ? extends U> transformer, BiFunction<? super U, ? super U, ? extends U> reducer) {
        if (transformer != null && reducer != null) {
            return (U) new MapReduceEntriesTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, reducer).invoke();
        }
        throw new NullPointerException();
    }

    public double reduceEntriesToDouble(long parallelismThreshold, ToDoubleFunction<Map.Entry<K, V>> transformer, double basis, DoubleBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Double) new MapReduceEntriesToDoubleTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).doubleValue();
        }
        throw new NullPointerException();
    }

    public long reduceEntriesToLong(long parallelismThreshold, ToLongFunction<Map.Entry<K, V>> transformer, long basis, LongBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Long) new MapReduceEntriesToLongTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).longValue();
        }
        throw new NullPointerException();
    }

    public int reduceEntriesToInt(long parallelismThreshold, ToIntFunction<Map.Entry<K, V>> transformer, int basis, IntBinaryOperator reducer) {
        if (transformer != null && reducer != null) {
            return ((Integer) new MapReduceEntriesToIntTask(null, batchFor(parallelismThreshold), 0, 0, this.table, null, transformer, basis, reducer).invoke()).intValue();
        }
        throw new NullPointerException();
    }

    static abstract class CollectionView<K, V, E> implements Collection<E>, Serializable {
        private static final String OOME_MSG = "Required array size too large";
        private static final long serialVersionUID = 7249069246763182397L;
        final ConcurrentHashMap<K, V> map;

        @Override // java.util.Collection
        public abstract boolean contains(Object obj);

        @Override // java.util.Collection, java.lang.Iterable
        public abstract Iterator<E> iterator();

        @Override // java.util.Collection
        public abstract boolean remove(Object obj);

        CollectionView(ConcurrentHashMap<K, V> map2) {
            this.map = map2;
        }

        public ConcurrentHashMap<K, V> getMap() {
            return this.map;
        }

        @Override // java.util.Collection
        public final void clear() {
            this.map.clear();
        }

        @Override // java.util.Collection
        public final int size() {
            return this.map.size();
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            long sz = this.map.mappingCount();
            if (sz <= 2147483639) {
                int n = (int) sz;
                Object[] r = new Object[n];
                int i = 0;
                Iterator<E> it = iterator();
                while (it.hasNext()) {
                    E e = it.next();
                    if (i == n) {
                        if (n < ConcurrentHashMap.MAX_ARRAY_SIZE) {
                            if (n >= 1073741819) {
                                n = ConcurrentHashMap.MAX_ARRAY_SIZE;
                            } else {
                                n += (n >>> 1) + 1;
                            }
                            r = Arrays.copyOf(r, n);
                        } else {
                            throw new OutOfMemoryError(OOME_MSG);
                        }
                    }
                    r[i] = e;
                    i++;
                }
                return i == n ? r : Arrays.copyOf(r, i);
            }
            throw new OutOfMemoryError(OOME_MSG);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v17, resolved type: T[] */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public final <T> T[] toArray(T[] a) {
            long sz = this.map.mappingCount();
            if (sz <= 2147483639) {
                int m = (int) sz;
                T[] r = a.length >= m ? a : (T[]) ((Object[]) Array.newInstance(a.getClass().getComponentType(), m));
                int n = r.length;
                int i = 0;
                Iterator<E> it = iterator();
                while (it.hasNext()) {
                    E e = it.next();
                    if (i == n) {
                        if (n < ConcurrentHashMap.MAX_ARRAY_SIZE) {
                            if (n >= 1073741819) {
                                n = ConcurrentHashMap.MAX_ARRAY_SIZE;
                            } else {
                                n += (n >>> 1) + 1;
                            }
                            r = (T[]) Arrays.copyOf(r, n);
                        } else {
                            throw new OutOfMemoryError(OOME_MSG);
                        }
                    }
                    r[i] = e;
                    i++;
                }
                if (a != r || i >= n) {
                    return i == n ? r : (T[]) Arrays.copyOf(r, i);
                }
                r[i] = null;
                return r;
            }
            throw new OutOfMemoryError(OOME_MSG);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Iterator<E> it = iterator();
            if (it.hasNext()) {
                while (true) {
                    Object e = it.next();
                    sb.append(e == this ? "(this Collection)" : e);
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(',');
                    sb.append(' ');
                }
            }
            sb.append(']');
            return sb.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:4:0x000c  */
        @Override // java.util.Collection
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean containsAll(java.util.Collection<?> r4) {
            /*
                r3 = this;
                if (r4 == r3) goto L_0x001c
                java.util.Iterator r0 = r4.iterator()
            L_0x0006:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x001c
                java.lang.Object r1 = r0.next()
                if (r1 == 0) goto L_0x001a
                boolean r2 = r3.contains(r1)
                if (r2 != 0) goto L_0x0019
                goto L_0x001a
            L_0x0019:
                goto L_0x0006
            L_0x001a:
                r0 = 0
                return r0
            L_0x001c:
                r0 = 1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.CollectionView.containsAll(java.util.Collection):boolean");
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection<?> c) {
            if (c != null) {
                boolean modified = false;
                Iterator<E> it = iterator();
                while (it.hasNext()) {
                    if (c.contains(it.next())) {
                        it.remove();
                        modified = true;
                    }
                }
                return modified;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection<?> c) {
            if (c != null) {
                boolean modified = false;
                Iterator<E> it = iterator();
                while (it.hasNext()) {
                    if (!c.contains(it.next())) {
                        it.remove();
                        modified = true;
                    }
                }
                return modified;
            }
            throw new NullPointerException();
        }
    }

    public static class KeySetView<K, V> extends CollectionView<K, V, K> implements Set<K>, Serializable {
        private static final long serialVersionUID = 7249069246763182397L;
        private final V value;

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView
        public /* bridge */ /* synthetic */ ConcurrentHashMap getMap() {
            return super.getMap();
        }

        KeySetView(ConcurrentHashMap<K, V> map, V value2) {
            super(map);
            this.value = value2;
        }

        public V getMappedValue() {
            return this.value;
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return this.map.containsKey(o);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return this.map.remove(o) != null;
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<K> iterator() {
            ConcurrentHashMap<K, V> m = this.map;
            Node<K, V>[] t = m.table;
            int f = t == null ? 0 : t.length;
            return new KeyIterator(t, f, 0, f, m);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean add(K e) {
            V v = this.value;
            if (v != null) {
                return this.map.putVal(e, v, true) == null;
            }
            throw new UnsupportedOperationException();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.ConcurrentHashMap */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends K> c) {
            boolean added = false;
            V v = this.value;
            if (v != null) {
                Iterator<? extends K> it = c.iterator();
                while (it.hasNext()) {
                    if (this.map.putVal(it.next(), v, true) == null) {
                        added = true;
                    }
                }
                return added;
            }
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int h = 0;
            Iterator<K> it = iterator();
            while (it.hasNext()) {
                h += it.next().hashCode();
            }
            return h;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object o) {
            Set<?> c;
            return (o instanceof Set) && ((c = (Set) o) == this || (containsAll(c) && c.containsAll(this)));
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<K> spliterator() {
            ConcurrentHashMap<K, V> m = this.map;
            long n = m.sumCount();
            Node<K, V>[] t = m.table;
            int f = t == null ? 0 : t.length;
            return new KeySpliterator(t, f, 0, f, n < 0 ? 0 : n);
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super K> action) {
            if (action != null) {
                Node<K, V>[] t = this.map.table;
                if (t != null) {
                    Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                    while (true) {
                        Node<K, V> p = it.advance();
                        if (p != null) {
                            action.accept(p.key);
                        } else {
                            return;
                        }
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }
    }

    static final class ValuesView<K, V> extends CollectionView<K, V, V> implements Collection<V>, Serializable {
        private static final long serialVersionUID = 2249069246763182397L;

        ValuesView(ConcurrentHashMap<K, V> map) {
            super(map);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean contains(Object o) {
            return this.map.containsValue(o);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean remove(Object o) {
            if (o == null) {
                return false;
            }
            Iterator<V> it = iterator();
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            ConcurrentHashMap<K, V> m = this.map;
            Node<K, V>[] t = m.table;
            int f = t == null ? 0 : t.length;
            return new ValueIterator(t, f, 0, f, m);
        }

        @Override // java.util.Collection
        public final boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super V> filter) {
            return this.map.removeValueIf(filter);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<V> spliterator() {
            ConcurrentHashMap<K, V> m = this.map;
            long n = m.sumCount();
            Node<K, V>[] t = m.table;
            int f = t == null ? 0 : t.length;
            return new ValueSpliterator(t, f, 0, f, n < 0 ? 0 : n);
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super V> action) {
            if (action != null) {
                Node<K, V>[] t = this.map.table;
                if (t != null) {
                    Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                    while (true) {
                        Node<K, V> p = it.advance();
                        if (p != null) {
                            action.accept(p.val);
                        } else {
                            return;
                        }
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }
    }

    static final class EntrySetView<K, V> extends CollectionView<K, V, Map.Entry<K, V>> implements Set<Map.Entry<K, V>>, Serializable {
        private static final long serialVersionUID = 2249069246763182397L;

        @Override // java.util.Collection, java.util.Set
        public /* bridge */ /* synthetic */ boolean add(Object obj) {
            return add((Map.Entry) ((Map.Entry) obj));
        }

        EntrySetView(ConcurrentHashMap<K, V> map) {
            super(map);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            Map.Entry<?, ?> e;
            Object k;
            Object r;
            Object v;
            return (!(o instanceof Map.Entry) || (k = (e = (Map.Entry) o).getKey()) == null || (r = this.map.get(k)) == null || (v = e.getValue()) == null || (v != r && !v.equals(r))) ? false : true;
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            Map.Entry<?, ?> e;
            Object k;
            Object v;
            return (o instanceof Map.Entry) && (k = (e = (Map.Entry) o).getKey()) != null && (v = e.getValue()) != null && this.map.remove(k, v);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            ConcurrentHashMap<K, V> m = this.map;
            Node<K, V>[] t = m.table;
            int f = t == null ? 0 : t.length;
            return new EntryIterator(t, f, 0, f, m);
        }

        public boolean add(Map.Entry<K, V> e) {
            return this.map.putVal(e.getKey(), e.getValue(), false) == null;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
            boolean added = false;
            for (Map.Entry<K, V> e : c) {
                if (add((Map.Entry) e)) {
                    added = true;
                }
            }
            return added;
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super Map.Entry<K, V>> filter) {
            return this.map.removeEntryIf(filter);
        }

        @Override // java.util.Collection, java.util.Set
        public final int hashCode() {
            int h = 0;
            Node<K, V>[] t = this.map.table;
            if (t != null) {
                Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                while (true) {
                    Node<K, V> p = it.advance();
                    if (p == null) {
                        break;
                    }
                    h += p.hashCode();
                }
            }
            return h;
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean equals(Object o) {
            Set<?> c;
            return (o instanceof Set) && ((c = (Set) o) == this || (containsAll(c) && c.containsAll(this)));
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<Map.Entry<K, V>> spliterator() {
            ConcurrentHashMap<K, V> m = this.map;
            long n = m.sumCount();
            Node<K, V>[] t = m.table;
            int f = t == null ? 0 : t.length;
            return new EntrySpliterator(t, f, 0, f, n < 0 ? 0 : n, m);
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                Node<K, V>[] t = this.map.table;
                if (t != null) {
                    Traverser<K, V> it = new Traverser<>(t, t.length, 0, t.length);
                    while (true) {
                        Node<K, V> p = it.advance();
                        if (p != null) {
                            action.accept(new MapEntry(p.key, p.val, this.map));
                        } else {
                            return;
                        }
                    }
                }
            } else {
                throw new NullPointerException();
            }
        }
    }

    static abstract class BulkTask<K, V, R> extends CountedCompleter<R> {
        int baseIndex;
        int baseLimit;
        final int baseSize;
        int batch;
        int index;
        Node<K, V> next;
        TableStack<K, V> spare;
        TableStack<K, V> stack;
        Node<K, V>[] tab;

        BulkTask(BulkTask<K, V, ?> par, int b, int i, int f, Node<K, V>[] t) {
            super(par);
            this.batch = b;
            this.baseIndex = i;
            this.index = i;
            this.tab = t;
            if (t == null) {
                this.baseLimit = 0;
                this.baseSize = 0;
            } else if (par == null) {
                int length = t.length;
                this.baseLimit = length;
                this.baseSize = length;
            } else {
                this.baseLimit = f;
                this.baseSize = par.baseSize;
            }
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> advance() {
            Node<K, V>[] t;
            int n;
            int i;
            Node<K, V> node = this.next;
            Node<K, V> e = node;
            if (node != null) {
                e = e.next;
            }
            while (e == null) {
                if (this.baseIndex >= this.baseLimit || (t = this.tab) == null || (n = t.length) <= (i = this.index) || i < 0) {
                    this.next = null;
                    return null;
                }
                Node<K, V> tabAt = ConcurrentHashMap.tabAt(t, i);
                e = tabAt;
                if (tabAt != null && e.hash < 0) {
                    if (e instanceof ForwardingNode) {
                        this.tab = ((ForwardingNode) e).nextTable;
                        e = null;
                        pushState(t, i, n);
                    } else {
                        e = e instanceof TreeBin ? ((TreeBin) e).first : null;
                    }
                }
                if (this.stack != null) {
                    recoverState(n);
                } else {
                    int i2 = this.baseSize + i;
                    this.index = i2;
                    if (i2 >= n) {
                        int i3 = this.baseIndex + 1;
                        this.baseIndex = i3;
                        this.index = i3;
                    }
                }
            }
            this.next = e;
            return e;
        }

        private void pushState(Node<K, V>[] t, int i, int n) {
            TableStack<K, V> s = this.spare;
            if (s != null) {
                this.spare = s.next;
            } else {
                s = new TableStack<>();
            }
            s.tab = t;
            s.length = n;
            s.index = i;
            s.next = this.stack;
            this.stack = s;
        }

        private void recoverState(int n) {
            TableStack<K, V> s;
            while (true) {
                s = this.stack;
                if (s == null) {
                    break;
                }
                int i = this.index;
                int len = s.length;
                int i2 = i + len;
                this.index = i2;
                if (i2 < n) {
                    break;
                }
                n = len;
                this.index = s.index;
                this.tab = s.tab;
                s.tab = null;
                TableStack<K, V> next2 = s.next;
                s.next = this.spare;
                this.stack = next2;
                this.spare = s;
            }
            if (s == null) {
                int i3 = this.index + this.baseSize;
                this.index = i3;
                if (i3 >= n) {
                    int i4 = this.baseIndex + 1;
                    this.baseIndex = i4;
                    this.index = i4;
                }
            }
        }
    }

    static final class ForEachKeyTask<K, V> extends BulkTask<K, V, Void> {
        final Consumer<? super K> action;

        ForEachKeyTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Consumer<? super K> action2) {
            super(p, b, i, f, t);
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Consumer<? super K> action2 = this.action;
            if (action2 != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachKeyTask(this, i2, h, f, this.tab, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        action2.accept(p.key);
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class ForEachValueTask<K, V> extends BulkTask<K, V, Void> {
        final Consumer<? super V> action;

        ForEachValueTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Consumer<? super V> action2) {
            super(p, b, i, f, t);
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Consumer<? super V> action2 = this.action;
            if (action2 != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachValueTask(this, i2, h, f, this.tab, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        action2.accept(p.val);
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class ForEachEntryTask<K, V> extends BulkTask<K, V, Void> {
        final Consumer<? super Map.Entry<K, V>> action;

        ForEachEntryTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Consumer<? super Map.Entry<K, V>> action2) {
            super(p, b, i, f, t);
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Consumer<? super Map.Entry<K, V>> action2 = this.action;
            if (action2 != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachEntryTask(this, i2, h, f, this.tab, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        action2.accept(p);
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class ForEachMappingTask<K, V> extends BulkTask<K, V, Void> {
        final BiConsumer<? super K, ? super V> action;

        ForEachMappingTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, BiConsumer<? super K, ? super V> action2) {
            super(p, b, i, f, t);
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            BiConsumer<? super K, ? super V> action2 = this.action;
            if (action2 != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachMappingTask(this, i2, h, f, this.tab, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        action2.accept(p.key, p.val);
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class ForEachTransformedKeyTask<K, V, U> extends BulkTask<K, V, Void> {
        final Consumer<? super U> action;
        final Function<? super K, ? extends U> transformer;

        ForEachTransformedKeyTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super K, ? extends U> transformer2, Consumer<? super U> action2) {
            super(p, b, i, f, t);
            this.transformer = transformer2;
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Consumer<? super U> action2;
            Function<? super K, ? extends U> transformer2 = this.transformer;
            if (transformer2 != null && (action2 = this.action) != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachTransformedKeyTask(this, i2, h, f, this.tab, transformer2, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        Object obj = (Object) transformer2.apply(p.key);
                        if (obj != 0) {
                            action2.accept(obj);
                        }
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class ForEachTransformedValueTask<K, V, U> extends BulkTask<K, V, Void> {
        final Consumer<? super U> action;
        final Function<? super V, ? extends U> transformer;

        ForEachTransformedValueTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super V, ? extends U> transformer2, Consumer<? super U> action2) {
            super(p, b, i, f, t);
            this.transformer = transformer2;
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Consumer<? super U> action2;
            Function<? super V, ? extends U> transformer2 = this.transformer;
            if (transformer2 != null && (action2 = this.action) != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachTransformedValueTask(this, i2, h, f, this.tab, transformer2, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        Object obj = (Object) transformer2.apply(p.val);
                        if (obj != 0) {
                            action2.accept(obj);
                        }
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class ForEachTransformedEntryTask<K, V, U> extends BulkTask<K, V, Void> {
        final Consumer<? super U> action;
        final Function<Map.Entry<K, V>, ? extends U> transformer;

        ForEachTransformedEntryTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<Map.Entry<K, V>, ? extends U> transformer2, Consumer<? super U> action2) {
            super(p, b, i, f, t);
            this.transformer = transformer2;
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Consumer<? super U> action2;
            Function<Map.Entry<K, V>, ? extends U> transformer2 = this.transformer;
            if (transformer2 != null && (action2 = this.action) != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachTransformedEntryTask(this, i2, h, f, this.tab, transformer2, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        Object obj = (Object) transformer2.apply(p);
                        if (obj != 0) {
                            action2.accept(obj);
                        }
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class ForEachTransformedMappingTask<K, V, U> extends BulkTask<K, V, Void> {
        final Consumer<? super U> action;
        final BiFunction<? super K, ? super V, ? extends U> transformer;

        ForEachTransformedMappingTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, BiFunction<? super K, ? super V, ? extends U> transformer2, Consumer<? super U> action2) {
            super(p, b, i, f, t);
            this.transformer = transformer2;
            this.action = action2;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Consumer<? super U> action2;
            BiFunction<? super K, ? super V, ? extends U> transformer2 = this.transformer;
            if (transformer2 != null && (action2 = this.action) != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    new ForEachTransformedMappingTask(this, i2, h, f, this.tab, transformer2, action2).fork();
                }
                while (true) {
                    Node<K, V> p = advance();
                    if (p != null) {
                        Object obj = (Object) transformer2.apply(p.key, p.val);
                        if (obj != 0) {
                            action2.accept(obj);
                        }
                    } else {
                        propagateCompletion();
                        return;
                    }
                }
            }
        }
    }

    static final class SearchKeysTask<K, V, U> extends BulkTask<K, V, U> {
        final AtomicReference<U> result;
        final Function<? super K, ? extends U> searchFunction;

        SearchKeysTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super K, ? extends U> searchFunction2, AtomicReference<U> result2) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction2;
            this.result = result2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result.get();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReference<U> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            AtomicReference<U> result2;
            Function<? super K, ? extends U> searchFunction2 = this.searchFunction;
            if (searchFunction2 != null && (result2 = this.result) != 0) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    } else if (result2.get() == null) {
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        new SearchKeysTask(this, i2, h, f, this.tab, searchFunction2, result2).fork();
                    } else {
                        return;
                    }
                }
                while (result2.get() == null) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        propagateCompletion();
                        return;
                    }
                    Object apply = searchFunction2.apply(p.key);
                    if (apply != null) {
                        if (result2.compareAndSet(null, apply)) {
                            quietlyCompleteRoot();
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    static final class SearchValuesTask<K, V, U> extends BulkTask<K, V, U> {
        final AtomicReference<U> result;
        final Function<? super V, ? extends U> searchFunction;

        SearchValuesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<? super V, ? extends U> searchFunction2, AtomicReference<U> result2) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction2;
            this.result = result2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result.get();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReference<U> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            AtomicReference<U> result2;
            Function<? super V, ? extends U> searchFunction2 = this.searchFunction;
            if (searchFunction2 != null && (result2 = this.result) != 0) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    } else if (result2.get() == null) {
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        new SearchValuesTask(this, i2, h, f, this.tab, searchFunction2, result2).fork();
                    } else {
                        return;
                    }
                }
                while (result2.get() == null) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        propagateCompletion();
                        return;
                    }
                    Object apply = searchFunction2.apply(p.val);
                    if (apply != null) {
                        if (result2.compareAndSet(null, apply)) {
                            quietlyCompleteRoot();
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    static final class SearchEntriesTask<K, V, U> extends BulkTask<K, V, U> {
        final AtomicReference<U> result;
        final Function<Map.Entry<K, V>, ? extends U> searchFunction;

        SearchEntriesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, Function<Map.Entry<K, V>, ? extends U> searchFunction2, AtomicReference<U> result2) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction2;
            this.result = result2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result.get();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReference<U> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            AtomicReference<U> result2;
            Function<Map.Entry<K, V>, ? extends U> searchFunction2 = this.searchFunction;
            if (searchFunction2 != null && (result2 = this.result) != 0) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    } else if (result2.get() == null) {
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        new SearchEntriesTask(this, i2, h, f, this.tab, searchFunction2, result2).fork();
                    } else {
                        return;
                    }
                }
                while (result2.get() == null) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        propagateCompletion();
                        return;
                    }
                    Object apply = searchFunction2.apply(p);
                    if (apply != null) {
                        if (result2.compareAndSet(null, apply)) {
                            quietlyCompleteRoot();
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    static final class SearchMappingsTask<K, V, U> extends BulkTask<K, V, U> {
        final AtomicReference<U> result;
        final BiFunction<? super K, ? super V, ? extends U> searchFunction;

        SearchMappingsTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, BiFunction<? super K, ? super V, ? extends U> searchFunction2, AtomicReference<U> result2) {
            super(p, b, i, f, t);
            this.searchFunction = searchFunction2;
            this.result = result2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result.get();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.concurrent.atomic.AtomicReference<U> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            AtomicReference<U> result2;
            BiFunction<? super K, ? super V, ? extends U> searchFunction2 = this.searchFunction;
            if (searchFunction2 != null && (result2 = this.result) != 0) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    } else if (result2.get() == null) {
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        new SearchMappingsTask(this, i2, h, f, this.tab, searchFunction2, result2).fork();
                    } else {
                        return;
                    }
                }
                while (result2.get() == null) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        propagateCompletion();
                        return;
                    }
                    Object apply = searchFunction2.apply(p.key, p.val);
                    if (apply != null) {
                        if (result2.compareAndSet(null, apply)) {
                            quietlyCompleteRoot();
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    static final class ReduceKeysTask<K, V> extends BulkTask<K, V, K> {
        ReduceKeysTask<K, V> nextRight;
        final BiFunction<? super K, ? super K, ? extends K> reducer;
        K result;
        ReduceKeysTask<K, V> rights;

        ReduceKeysTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, ReduceKeysTask<K, V> nextRight2, BiFunction<? super K, ? super K, ? extends K> reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final K getRawResult() {
            return this.result;
        }

        /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0048 */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v5 */
        /* JADX WARN: Type inference failed for: r3v6 */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.concurrent.CountedCompleter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void compute() {
            /*
            // Method dump skipped, instructions count: 119
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.ReduceKeysTask.compute():void");
        }
    }

    static final class ReduceValuesTask<K, V> extends BulkTask<K, V, V> {
        ReduceValuesTask<K, V> nextRight;
        final BiFunction<? super V, ? super V, ? extends V> reducer;
        V result;
        ReduceValuesTask<K, V> rights;

        ReduceValuesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, ReduceValuesTask<K, V> nextRight2, BiFunction<? super V, ? super V, ? extends V> reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final V getRawResult() {
            return this.result;
        }

        /* JADX DEBUG: Failed to insert an additional move for type inference into block B:32:0x0044 */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v5 */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // java.util.concurrent.CountedCompleter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void compute() {
            /*
            // Method dump skipped, instructions count: 115
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.ReduceValuesTask.compute():void");
        }
    }

    static final class ReduceEntriesTask<K, V> extends BulkTask<K, V, Map.Entry<K, V>> {
        ReduceEntriesTask<K, V> nextRight;
        final BiFunction<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer;
        Map.Entry<K, V> result;
        ReduceEntriesTask<K, V> rights;

        ReduceEntriesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, ReduceEntriesTask<K, V> nextRight2, BiFunction<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Map.Entry<K, V> getRawResult() {
            return this.result;
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            Map.Entry<K, V> entry;
            BiFunction<Map.Entry<K, V>, Map.Entry<K, V>, ? extends Map.Entry<K, V>> reducer2 = this.reducer;
            if (reducer2 != null) {
                int i = this.baseIndex;
                while (this.batch > 0) {
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    ReduceEntriesTask<K, V> reduceEntriesTask = new ReduceEntriesTask<>(this, i2, h, f, this.tab, this.rights, reducer2);
                    this.rights = reduceEntriesTask;
                    reduceEntriesTask.fork();
                }
                Map.Entry<K, V> r = null;
                while (true) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        break;
                    }
                    r = r == null ? p : (Map.Entry) reducer2.apply(r, p);
                }
                this.result = r;
                for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                    ReduceEntriesTask<K, V> t = (ReduceEntriesTask) c;
                    ReduceEntriesTask<K, V> s = t.rights;
                    while (s != null) {
                        Map.Entry<K, V> sr = s.result;
                        if (sr != null) {
                            Map.Entry<K, V> tr = t.result;
                            if (tr == null) {
                                entry = sr;
                            } else {
                                entry = (Map.Entry) reducer2.apply(tr, sr);
                            }
                            t.result = entry;
                        }
                        ReduceEntriesTask<K, V> reduceEntriesTask2 = s.nextRight;
                        t.rights = reduceEntriesTask2;
                        s = reduceEntriesTask2;
                    }
                }
            }
        }
    }

    static final class MapReduceKeysTask<K, V, U> extends BulkTask<K, V, U> {
        MapReduceKeysTask<K, V, U> nextRight;
        final BiFunction<? super U, ? super U, ? extends U> reducer;
        U result;
        MapReduceKeysTask<K, V, U> rights;
        final Function<? super K, ? extends U> transformer;

        MapReduceKeysTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysTask<K, V, U> nextRight2, Function<? super K, ? extends U> transformer2, BiFunction<? super U, ? super U, ? extends U> reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 2 */
        @Override // java.util.concurrent.CountedCompleter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void compute() {
            /*
            // Method dump skipped, instructions count: 128
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.MapReduceKeysTask.compute():void");
        }
    }

    static final class MapReduceValuesTask<K, V, U> extends BulkTask<K, V, U> {
        MapReduceValuesTask<K, V, U> nextRight;
        final BiFunction<? super U, ? super U, ? extends U> reducer;
        U result;
        MapReduceValuesTask<K, V, U> rights;
        final Function<? super V, ? extends U> transformer;

        MapReduceValuesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesTask<K, V, U> nextRight2, Function<? super V, ? extends U> transformer2, BiFunction<? super U, ? super U, ? extends U> reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 2 */
        @Override // java.util.concurrent.CountedCompleter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void compute() {
            /*
            // Method dump skipped, instructions count: 128
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.MapReduceValuesTask.compute():void");
        }
    }

    static final class MapReduceEntriesTask<K, V, U> extends BulkTask<K, V, U> {
        MapReduceEntriesTask<K, V, U> nextRight;
        final BiFunction<? super U, ? super U, ? extends U> reducer;
        U result;
        MapReduceEntriesTask<K, V, U> rights;
        final Function<Map.Entry<K, V>, ? extends U> transformer;

        MapReduceEntriesTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesTask<K, V, U> nextRight2, Function<Map.Entry<K, V>, ? extends U> transformer2, BiFunction<? super U, ? super U, ? extends U> reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 2 */
        @Override // java.util.concurrent.CountedCompleter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void compute() {
            /*
            // Method dump skipped, instructions count: 126
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.MapReduceEntriesTask.compute():void");
        }
    }

    static final class MapReduceMappingsTask<K, V, U> extends BulkTask<K, V, U> {
        MapReduceMappingsTask<K, V, U> nextRight;
        final BiFunction<? super U, ? super U, ? extends U> reducer;
        U result;
        MapReduceMappingsTask<K, V, U> rights;
        final BiFunction<? super K, ? super V, ? extends U> transformer;

        MapReduceMappingsTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsTask<K, V, U> nextRight2, BiFunction<? super K, ? super V, ? extends U> transformer2, BiFunction<? super U, ? super U, ? extends U> reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final U getRawResult() {
            return this.result;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 2 */
        @Override // java.util.concurrent.CountedCompleter
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void compute() {
            /*
            // Method dump skipped, instructions count: 130
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.MapReduceMappingsTask.compute():void");
        }
    }

    static final class MapReduceKeysToDoubleTask<K, V> extends BulkTask<K, V, Double> {
        final double basis;
        MapReduceKeysToDoubleTask<K, V> nextRight;
        final DoubleBinaryOperator reducer;
        double result;
        MapReduceKeysToDoubleTask<K, V> rights;
        final ToDoubleFunction<? super K> transformer;

        MapReduceKeysToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysToDoubleTask<K, V> nextRight2, ToDoubleFunction<? super K> transformer2, double basis2, DoubleBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Double getRawResult() {
            return Double.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToDoubleFunction<? super K> transformer2;
            ToDoubleFunction<? super K> toDoubleFunction = this.transformer;
            ToDoubleFunction<? super K> transformer3 = toDoubleFunction;
            if (toDoubleFunction != null) {
                DoubleBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    double r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceKeysToDoubleTask<K, V> mapReduceKeysToDoubleTask = new MapReduceKeysToDoubleTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceKeysToDoubleTask;
                        mapReduceKeysToDoubleTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsDouble(r, transformer2.applyAsDouble(p.key));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceKeysToDoubleTask<K, V> t = (MapReduceKeysToDoubleTask) c;
                        MapReduceKeysToDoubleTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsDouble(t.result, s.result);
                            MapReduceKeysToDoubleTask<K, V> mapReduceKeysToDoubleTask2 = s.nextRight;
                            t.rights = mapReduceKeysToDoubleTask2;
                            s = mapReduceKeysToDoubleTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceValuesToDoubleTask<K, V> extends BulkTask<K, V, Double> {
        final double basis;
        MapReduceValuesToDoubleTask<K, V> nextRight;
        final DoubleBinaryOperator reducer;
        double result;
        MapReduceValuesToDoubleTask<K, V> rights;
        final ToDoubleFunction<? super V> transformer;

        MapReduceValuesToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesToDoubleTask<K, V> nextRight2, ToDoubleFunction<? super V> transformer2, double basis2, DoubleBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Double getRawResult() {
            return Double.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToDoubleFunction<? super V> transformer2;
            ToDoubleFunction<? super V> toDoubleFunction = this.transformer;
            ToDoubleFunction<? super V> transformer3 = toDoubleFunction;
            if (toDoubleFunction != null) {
                DoubleBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    double r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceValuesToDoubleTask<K, V> mapReduceValuesToDoubleTask = new MapReduceValuesToDoubleTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceValuesToDoubleTask;
                        mapReduceValuesToDoubleTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsDouble(r, transformer2.applyAsDouble(p.val));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceValuesToDoubleTask<K, V> t = (MapReduceValuesToDoubleTask) c;
                        MapReduceValuesToDoubleTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsDouble(t.result, s.result);
                            MapReduceValuesToDoubleTask<K, V> mapReduceValuesToDoubleTask2 = s.nextRight;
                            t.rights = mapReduceValuesToDoubleTask2;
                            s = mapReduceValuesToDoubleTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceEntriesToDoubleTask<K, V> extends BulkTask<K, V, Double> {
        final double basis;
        MapReduceEntriesToDoubleTask<K, V> nextRight;
        final DoubleBinaryOperator reducer;
        double result;
        MapReduceEntriesToDoubleTask<K, V> rights;
        final ToDoubleFunction<Map.Entry<K, V>> transformer;

        MapReduceEntriesToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesToDoubleTask<K, V> nextRight2, ToDoubleFunction<Map.Entry<K, V>> transformer2, double basis2, DoubleBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Double getRawResult() {
            return Double.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToDoubleFunction<Map.Entry<K, V>> transformer2;
            ToDoubleFunction<Map.Entry<K, V>> toDoubleFunction = this.transformer;
            ToDoubleFunction<Map.Entry<K, V>> transformer3 = toDoubleFunction;
            if (toDoubleFunction != null) {
                DoubleBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    double r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceEntriesToDoubleTask<K, V> mapReduceEntriesToDoubleTask = new MapReduceEntriesToDoubleTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceEntriesToDoubleTask;
                        mapReduceEntriesToDoubleTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsDouble(r, transformer2.applyAsDouble(p));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceEntriesToDoubleTask<K, V> t = (MapReduceEntriesToDoubleTask) c;
                        MapReduceEntriesToDoubleTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsDouble(t.result, s.result);
                            MapReduceEntriesToDoubleTask<K, V> mapReduceEntriesToDoubleTask2 = s.nextRight;
                            t.rights = mapReduceEntriesToDoubleTask2;
                            s = mapReduceEntriesToDoubleTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceMappingsToDoubleTask<K, V> extends BulkTask<K, V, Double> {
        final double basis;
        MapReduceMappingsToDoubleTask<K, V> nextRight;
        final DoubleBinaryOperator reducer;
        double result;
        MapReduceMappingsToDoubleTask<K, V> rights;
        final ToDoubleBiFunction<? super K, ? super V> transformer;

        MapReduceMappingsToDoubleTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsToDoubleTask<K, V> nextRight2, ToDoubleBiFunction<? super K, ? super V> transformer2, double basis2, DoubleBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Double getRawResult() {
            return Double.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToDoubleBiFunction<? super K, ? super V> transformer2;
            ToDoubleBiFunction<? super K, ? super V> toDoubleBiFunction = this.transformer;
            ToDoubleBiFunction<? super K, ? super V> transformer3 = toDoubleBiFunction;
            if (toDoubleBiFunction != null) {
                DoubleBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    double r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceMappingsToDoubleTask<K, V> mapReduceMappingsToDoubleTask = new MapReduceMappingsToDoubleTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceMappingsToDoubleTask;
                        mapReduceMappingsToDoubleTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsDouble(r, transformer2.applyAsDouble(p.key, p.val));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceMappingsToDoubleTask<K, V> t = (MapReduceMappingsToDoubleTask) c;
                        MapReduceMappingsToDoubleTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsDouble(t.result, s.result);
                            MapReduceMappingsToDoubleTask<K, V> mapReduceMappingsToDoubleTask2 = s.nextRight;
                            t.rights = mapReduceMappingsToDoubleTask2;
                            s = mapReduceMappingsToDoubleTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceKeysToLongTask<K, V> extends BulkTask<K, V, Long> {
        final long basis;
        MapReduceKeysToLongTask<K, V> nextRight;
        final LongBinaryOperator reducer;
        long result;
        MapReduceKeysToLongTask<K, V> rights;
        final ToLongFunction<? super K> transformer;

        MapReduceKeysToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysToLongTask<K, V> nextRight2, ToLongFunction<? super K> transformer2, long basis2, LongBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Long getRawResult() {
            return Long.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToLongFunction<? super K> transformer2;
            ToLongFunction<? super K> toLongFunction = this.transformer;
            ToLongFunction<? super K> transformer3 = toLongFunction;
            if (toLongFunction != null) {
                LongBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    long r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceKeysToLongTask<K, V> mapReduceKeysToLongTask = new MapReduceKeysToLongTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceKeysToLongTask;
                        mapReduceKeysToLongTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsLong(r, transformer2.applyAsLong(p.key));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceKeysToLongTask<K, V> t = (MapReduceKeysToLongTask) c;
                        MapReduceKeysToLongTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsLong(t.result, s.result);
                            MapReduceKeysToLongTask<K, V> mapReduceKeysToLongTask2 = s.nextRight;
                            t.rights = mapReduceKeysToLongTask2;
                            s = mapReduceKeysToLongTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceValuesToLongTask<K, V> extends BulkTask<K, V, Long> {
        final long basis;
        MapReduceValuesToLongTask<K, V> nextRight;
        final LongBinaryOperator reducer;
        long result;
        MapReduceValuesToLongTask<K, V> rights;
        final ToLongFunction<? super V> transformer;

        MapReduceValuesToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesToLongTask<K, V> nextRight2, ToLongFunction<? super V> transformer2, long basis2, LongBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Long getRawResult() {
            return Long.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToLongFunction<? super V> transformer2;
            ToLongFunction<? super V> toLongFunction = this.transformer;
            ToLongFunction<? super V> transformer3 = toLongFunction;
            if (toLongFunction != null) {
                LongBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    long r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceValuesToLongTask<K, V> mapReduceValuesToLongTask = new MapReduceValuesToLongTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceValuesToLongTask;
                        mapReduceValuesToLongTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsLong(r, transformer2.applyAsLong(p.val));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceValuesToLongTask<K, V> t = (MapReduceValuesToLongTask) c;
                        MapReduceValuesToLongTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsLong(t.result, s.result);
                            MapReduceValuesToLongTask<K, V> mapReduceValuesToLongTask2 = s.nextRight;
                            t.rights = mapReduceValuesToLongTask2;
                            s = mapReduceValuesToLongTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceEntriesToLongTask<K, V> extends BulkTask<K, V, Long> {
        final long basis;
        MapReduceEntriesToLongTask<K, V> nextRight;
        final LongBinaryOperator reducer;
        long result;
        MapReduceEntriesToLongTask<K, V> rights;
        final ToLongFunction<Map.Entry<K, V>> transformer;

        MapReduceEntriesToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesToLongTask<K, V> nextRight2, ToLongFunction<Map.Entry<K, V>> transformer2, long basis2, LongBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Long getRawResult() {
            return Long.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToLongFunction<Map.Entry<K, V>> transformer2;
            ToLongFunction<Map.Entry<K, V>> toLongFunction = this.transformer;
            ToLongFunction<Map.Entry<K, V>> transformer3 = toLongFunction;
            if (toLongFunction != null) {
                LongBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    long r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceEntriesToLongTask<K, V> mapReduceEntriesToLongTask = new MapReduceEntriesToLongTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceEntriesToLongTask;
                        mapReduceEntriesToLongTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsLong(r, transformer2.applyAsLong(p));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceEntriesToLongTask<K, V> t = (MapReduceEntriesToLongTask) c;
                        MapReduceEntriesToLongTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsLong(t.result, s.result);
                            MapReduceEntriesToLongTask<K, V> mapReduceEntriesToLongTask2 = s.nextRight;
                            t.rights = mapReduceEntriesToLongTask2;
                            s = mapReduceEntriesToLongTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceMappingsToLongTask<K, V> extends BulkTask<K, V, Long> {
        final long basis;
        MapReduceMappingsToLongTask<K, V> nextRight;
        final LongBinaryOperator reducer;
        long result;
        MapReduceMappingsToLongTask<K, V> rights;
        final ToLongBiFunction<? super K, ? super V> transformer;

        MapReduceMappingsToLongTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsToLongTask<K, V> nextRight2, ToLongBiFunction<? super K, ? super V> transformer2, long basis2, LongBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Long getRawResult() {
            return Long.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            ToLongBiFunction<? super K, ? super V> transformer2;
            ToLongBiFunction<? super K, ? super V> toLongBiFunction = this.transformer;
            ToLongBiFunction<? super K, ? super V> transformer3 = toLongBiFunction;
            if (toLongBiFunction != null) {
                LongBinaryOperator reducer2 = this.reducer;
                if (reducer2 != null) {
                    long r = this.basis;
                    int i = this.baseIndex;
                    while (true) {
                        if (this.batch <= 0) {
                            transformer2 = transformer3;
                            break;
                        }
                        int f = this.baseLimit;
                        int h = (f + i) >>> 1;
                        if (h <= i) {
                            transformer2 = transformer3;
                            break;
                        }
                        addToPendingCount(1);
                        int i2 = this.batch >>> 1;
                        this.batch = i2;
                        this.baseLimit = h;
                        MapReduceMappingsToLongTask<K, V> mapReduceMappingsToLongTask = new MapReduceMappingsToLongTask<>(this, i2, h, f, this.tab, this.rights, transformer3, r, reducer2);
                        this.rights = mapReduceMappingsToLongTask;
                        mapReduceMappingsToLongTask.fork();
                        transformer3 = transformer3;
                        i = i;
                    }
                    while (true) {
                        Node<K, V> p = advance();
                        if (p == null) {
                            break;
                        }
                        r = reducer2.applyAsLong(r, transformer2.applyAsLong(p.key, p.val));
                    }
                    this.result = r;
                    for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                        MapReduceMappingsToLongTask<K, V> t = (MapReduceMappingsToLongTask) c;
                        MapReduceMappingsToLongTask<K, V> s = t.rights;
                        while (s != null) {
                            t.result = reducer2.applyAsLong(t.result, s.result);
                            MapReduceMappingsToLongTask<K, V> mapReduceMappingsToLongTask2 = s.nextRight;
                            t.rights = mapReduceMappingsToLongTask2;
                            s = mapReduceMappingsToLongTask2;
                        }
                    }
                }
            }
        }
    }

    static final class MapReduceKeysToIntTask<K, V> extends BulkTask<K, V, Integer> {
        final int basis;
        MapReduceKeysToIntTask<K, V> nextRight;
        final IntBinaryOperator reducer;
        int result;
        MapReduceKeysToIntTask<K, V> rights;
        final ToIntFunction<? super K> transformer;

        MapReduceKeysToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceKeysToIntTask<K, V> nextRight2, ToIntFunction<? super K> transformer2, int basis2, IntBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Integer getRawResult() {
            return Integer.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            IntBinaryOperator reducer2;
            int r;
            ToIntFunction<? super K> transformer2 = this.transformer;
            if (!(transformer2 == null || (reducer2 = this.reducer) == null)) {
                int r2 = this.basis;
                int i = this.baseIndex;
                while (true) {
                    if (this.batch <= 0) {
                        r = r2;
                        break;
                    }
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        r = r2;
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    MapReduceKeysToIntTask<K, V> mapReduceKeysToIntTask = new MapReduceKeysToIntTask<>(this, i2, h, f, this.tab, this.rights, transformer2, r2, reducer2);
                    this.rights = mapReduceKeysToIntTask;
                    mapReduceKeysToIntTask.fork();
                    r2 = r2;
                }
                int r3 = r;
                while (true) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        break;
                    }
                    r3 = reducer2.applyAsInt(r3, transformer2.applyAsInt(p.key));
                }
                this.result = r3;
                for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                    MapReduceKeysToIntTask<K, V> t = (MapReduceKeysToIntTask) c;
                    MapReduceKeysToIntTask<K, V> s = t.rights;
                    while (s != null) {
                        t.result = reducer2.applyAsInt(t.result, s.result);
                        MapReduceKeysToIntTask<K, V> mapReduceKeysToIntTask2 = s.nextRight;
                        t.rights = mapReduceKeysToIntTask2;
                        s = mapReduceKeysToIntTask2;
                    }
                }
            }
        }
    }

    static final class MapReduceValuesToIntTask<K, V> extends BulkTask<K, V, Integer> {
        final int basis;
        MapReduceValuesToIntTask<K, V> nextRight;
        final IntBinaryOperator reducer;
        int result;
        MapReduceValuesToIntTask<K, V> rights;
        final ToIntFunction<? super V> transformer;

        MapReduceValuesToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceValuesToIntTask<K, V> nextRight2, ToIntFunction<? super V> transformer2, int basis2, IntBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Integer getRawResult() {
            return Integer.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            IntBinaryOperator reducer2;
            int r;
            ToIntFunction<? super V> transformer2 = this.transformer;
            if (!(transformer2 == null || (reducer2 = this.reducer) == null)) {
                int r2 = this.basis;
                int i = this.baseIndex;
                while (true) {
                    if (this.batch <= 0) {
                        r = r2;
                        break;
                    }
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        r = r2;
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    MapReduceValuesToIntTask<K, V> mapReduceValuesToIntTask = new MapReduceValuesToIntTask<>(this, i2, h, f, this.tab, this.rights, transformer2, r2, reducer2);
                    this.rights = mapReduceValuesToIntTask;
                    mapReduceValuesToIntTask.fork();
                    r2 = r2;
                }
                int r3 = r;
                while (true) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        break;
                    }
                    r3 = reducer2.applyAsInt(r3, transformer2.applyAsInt(p.val));
                }
                this.result = r3;
                for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                    MapReduceValuesToIntTask<K, V> t = (MapReduceValuesToIntTask) c;
                    MapReduceValuesToIntTask<K, V> s = t.rights;
                    while (s != null) {
                        t.result = reducer2.applyAsInt(t.result, s.result);
                        MapReduceValuesToIntTask<K, V> mapReduceValuesToIntTask2 = s.nextRight;
                        t.rights = mapReduceValuesToIntTask2;
                        s = mapReduceValuesToIntTask2;
                    }
                }
            }
        }
    }

    static final class MapReduceEntriesToIntTask<K, V> extends BulkTask<K, V, Integer> {
        final int basis;
        MapReduceEntriesToIntTask<K, V> nextRight;
        final IntBinaryOperator reducer;
        int result;
        MapReduceEntriesToIntTask<K, V> rights;
        final ToIntFunction<Map.Entry<K, V>> transformer;

        MapReduceEntriesToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceEntriesToIntTask<K, V> nextRight2, ToIntFunction<Map.Entry<K, V>> transformer2, int basis2, IntBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Integer getRawResult() {
            return Integer.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            IntBinaryOperator reducer2;
            int r;
            ToIntFunction<Map.Entry<K, V>> transformer2 = this.transformer;
            if (!(transformer2 == null || (reducer2 = this.reducer) == null)) {
                int r2 = this.basis;
                int i = this.baseIndex;
                while (true) {
                    if (this.batch <= 0) {
                        r = r2;
                        break;
                    }
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        r = r2;
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    MapReduceEntriesToIntTask<K, V> mapReduceEntriesToIntTask = new MapReduceEntriesToIntTask<>(this, i2, h, f, this.tab, this.rights, transformer2, r2, reducer2);
                    this.rights = mapReduceEntriesToIntTask;
                    mapReduceEntriesToIntTask.fork();
                    r2 = r2;
                }
                int r3 = r;
                while (true) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        break;
                    }
                    r3 = reducer2.applyAsInt(r3, transformer2.applyAsInt(p));
                }
                this.result = r3;
                for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                    MapReduceEntriesToIntTask<K, V> t = (MapReduceEntriesToIntTask) c;
                    MapReduceEntriesToIntTask<K, V> s = t.rights;
                    while (s != null) {
                        t.result = reducer2.applyAsInt(t.result, s.result);
                        MapReduceEntriesToIntTask<K, V> mapReduceEntriesToIntTask2 = s.nextRight;
                        t.rights = mapReduceEntriesToIntTask2;
                        s = mapReduceEntriesToIntTask2;
                    }
                }
            }
        }
    }

    static final class MapReduceMappingsToIntTask<K, V> extends BulkTask<K, V, Integer> {
        final int basis;
        MapReduceMappingsToIntTask<K, V> nextRight;
        final IntBinaryOperator reducer;
        int result;
        MapReduceMappingsToIntTask<K, V> rights;
        final ToIntBiFunction<? super K, ? super V> transformer;

        MapReduceMappingsToIntTask(BulkTask<K, V, ?> p, int b, int i, int f, Node<K, V>[] t, MapReduceMappingsToIntTask<K, V> nextRight2, ToIntBiFunction<? super K, ? super V> transformer2, int basis2, IntBinaryOperator reducer2) {
            super(p, b, i, f, t);
            this.nextRight = nextRight2;
            this.transformer = transformer2;
            this.basis = basis2;
            this.reducer = reducer2;
        }

        @Override // java.util.concurrent.ForkJoinTask, java.util.concurrent.CountedCompleter
        public final Integer getRawResult() {
            return Integer.valueOf(this.result);
        }

        @Override // java.util.concurrent.CountedCompleter
        public final void compute() {
            IntBinaryOperator reducer2;
            int r;
            ToIntBiFunction<? super K, ? super V> transformer2 = this.transformer;
            if (!(transformer2 == null || (reducer2 = this.reducer) == null)) {
                int r2 = this.basis;
                int i = this.baseIndex;
                while (true) {
                    if (this.batch <= 0) {
                        r = r2;
                        break;
                    }
                    int f = this.baseLimit;
                    int h = (f + i) >>> 1;
                    if (h <= i) {
                        r = r2;
                        break;
                    }
                    addToPendingCount(1);
                    int i2 = this.batch >>> 1;
                    this.batch = i2;
                    this.baseLimit = h;
                    MapReduceMappingsToIntTask<K, V> mapReduceMappingsToIntTask = new MapReduceMappingsToIntTask<>(this, i2, h, f, this.tab, this.rights, transformer2, r2, reducer2);
                    this.rights = mapReduceMappingsToIntTask;
                    mapReduceMappingsToIntTask.fork();
                    r2 = r2;
                }
                int r3 = r;
                while (true) {
                    Node<K, V> p = advance();
                    if (p == null) {
                        break;
                    }
                    r3 = reducer2.applyAsInt(r3, transformer2.applyAsInt(p.key, p.val));
                }
                this.result = r3;
                for (CountedCompleter<?> c = firstComplete(); c != null; c = c.nextComplete()) {
                    MapReduceMappingsToIntTask<K, V> t = (MapReduceMappingsToIntTask) c;
                    MapReduceMappingsToIntTask<K, V> s = t.rights;
                    while (s != null) {
                        t.result = reducer2.applyAsInt(t.result, s.result);
                        MapReduceMappingsToIntTask<K, V> mapReduceMappingsToIntTask2 = s.nextRight;
                        t.rights = mapReduceMappingsToIntTask2;
                        s = mapReduceMappingsToIntTask2;
                    }
                }
            }
        }
    }
}
