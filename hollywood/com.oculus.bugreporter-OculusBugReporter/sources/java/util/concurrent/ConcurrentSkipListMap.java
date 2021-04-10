package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import sun.misc.Unsafe;

public class ConcurrentSkipListMap<K, V> extends AbstractMap<K, V> implements ConcurrentNavigableMap<K, V>, Cloneable, Serializable {
    static final Object BASE_HEADER = new Object();
    private static final int EQ = 1;
    private static final int GT = 0;
    private static final long HEAD;
    private static final int LT = 2;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = -8627078645895051609L;
    final Comparator<? super K> comparator;
    private transient ConcurrentNavigableMap<K, V> descendingMap;
    private transient EntrySet<K, V> entrySet;
    private volatile transient HeadIndex<K, V> head;
    private transient KeySet<K, V> keySet;
    private transient Values<K, V> values;

    static {
        try {
            HEAD = U.objectFieldOffset(ConcurrentSkipListMap.class.getDeclaredField("head"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    private void initialize() {
        this.keySet = null;
        this.entrySet = null;
        this.values = null;
        this.descendingMap = null;
        this.head = new HeadIndex<>(new Node(null, BASE_HEADER, null), null, null, 1);
    }

    private boolean casHead(HeadIndex<K, V> cmp, HeadIndex<K, V> val) {
        return U.compareAndSwapObject(this, HEAD, cmp, val);
    }

    /* access modifiers changed from: package-private */
    public static final class Node<K, V> {
        private static final long NEXT;
        private static final Unsafe U = Unsafe.getUnsafe();
        private static final long VALUE;
        final K key;
        volatile Node<K, V> next;
        volatile Object value;

        Node(K key2, Object value2, Node<K, V> next2) {
            this.key = key2;
            this.value = value2;
            this.next = next2;
        }

        Node(Node<K, V> next2) {
            this.key = null;
            this.value = this;
            this.next = next2;
        }

        /* access modifiers changed from: package-private */
        public boolean casValue(Object cmp, Object val) {
            return U.compareAndSwapObject(this, VALUE, cmp, val);
        }

        /* access modifiers changed from: package-private */
        public boolean casNext(Node<K, V> cmp, Node<K, V> val) {
            return U.compareAndSwapObject(this, NEXT, cmp, val);
        }

        /* access modifiers changed from: package-private */
        public boolean isMarker() {
            return this.value == this;
        }

        /* access modifiers changed from: package-private */
        public boolean isBaseHeader() {
            return this.value == ConcurrentSkipListMap.BASE_HEADER;
        }

        /* access modifiers changed from: package-private */
        public boolean appendMarker(Node<K, V> f) {
            return casNext(f, new Node<>(f));
        }

        /* access modifiers changed from: package-private */
        public void helpDelete(Node<K, V> b, Node<K, V> f) {
            if (f != this.next || this != b.next) {
                return;
            }
            if (f == null || f.value != f) {
                casNext(f, new Node<>(f));
            } else {
                b.casNext(this, f.next);
            }
        }

        /* access modifiers changed from: package-private */
        public V getValidValue() {
            V v = (V) this.value;
            if (v == this || v == ConcurrentSkipListMap.BASE_HEADER) {
                return null;
            }
            return v;
        }

        /* access modifiers changed from: package-private */
        public AbstractMap.SimpleImmutableEntry<K, V> createSnapshot() {
            Object v = this.value;
            if (v == null || v == this || v == ConcurrentSkipListMap.BASE_HEADER) {
                return null;
            }
            return new AbstractMap.SimpleImmutableEntry<>(this.key, v);
        }

        static {
            try {
                VALUE = U.objectFieldOffset(Node.class.getDeclaredField("value"));
                NEXT = U.objectFieldOffset(Node.class.getDeclaredField("next"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class Index<K, V> {
        private static final long RIGHT;
        private static final Unsafe U = Unsafe.getUnsafe();
        final Index<K, V> down;
        final Node<K, V> node;
        volatile Index<K, V> right;

        Index(Node<K, V> node2, Index<K, V> down2, Index<K, V> right2) {
            this.node = node2;
            this.down = down2;
            this.right = right2;
        }

        /* access modifiers changed from: package-private */
        public final boolean casRight(Index<K, V> cmp, Index<K, V> val) {
            return U.compareAndSwapObject(this, RIGHT, cmp, val);
        }

        /* access modifiers changed from: package-private */
        public final boolean indexesDeletedNode() {
            return this.node.value == null;
        }

        /* access modifiers changed from: package-private */
        public final boolean link(Index<K, V> succ, Index<K, V> newSucc) {
            Node<K, V> n = this.node;
            newSucc.right = succ;
            return n.value != null && casRight(succ, newSucc);
        }

        /* access modifiers changed from: package-private */
        public final boolean unlink(Index<K, V> succ) {
            return this.node.value != null && casRight(succ, succ.right);
        }

        static {
            try {
                RIGHT = U.objectFieldOffset(Index.class.getDeclaredField("right"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class HeadIndex<K, V> extends Index<K, V> {
        final int level;

        HeadIndex(Node<K, V> node, Index<K, V> down, Index<K, V> right, int level2) {
            super(node, down, right);
            this.level = level2;
        }
    }

    static final int cpr(Comparator c, Object x, Object y) {
        return c != null ? c.compare(x, y) : ((Comparable) x).compareTo(y);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:23:0x0006 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r2v0. Raw type applied. Possible types: java.util.concurrent.ConcurrentSkipListMap$Index<K, V>, java.util.concurrent.ConcurrentSkipListMap$Index */
    /* JADX INFO: Multiple debug info for r2v0 java.util.concurrent.ConcurrentSkipListMap$Index<K, V>: [D('n' java.util.concurrent.ConcurrentSkipListMap$Node<K, V>), D('d' java.util.concurrent.ConcurrentSkipListMap$Index<K, V>)] */
    private Node<K, V> findPredecessor(Object key, Comparator<? super K> cmp) {
        if (key == null) {
            throw new NullPointerException();
        }
        while (true) {
            Index<K, V> q = this.head;
            Index<K, V> r = q.right;
            while (true) {
                if (r != null) {
                    Node<K, V> n = r.node;
                    K k = n.key;
                    if (n.value == null) {
                        if (q.unlink(r)) {
                            r = q.right;
                        }
                    } else if (cpr(cmp, key, k) > 0) {
                        q = r;
                        r = r.right;
                    }
                }
                Index<K, V> d = q.down;
                if (d == null) {
                    return q.node;
                }
                q = d;
                r = d.right;
            }
        }
    }

    private Node<K, V> findNode(Object key) {
        if (key != null) {
            Comparator<? super K> cmp = this.comparator;
            while (true) {
                Node<K, V> b = findPredecessor(key, cmp);
                Node<K, V> n = b.next;
                while (n != null) {
                    Node<K, V> f = n.next;
                    if (n == b.next) {
                        Object v = n.value;
                        if (v == null) {
                            n.helpDelete(b, f);
                        } else if (!(b.value == null || v == n)) {
                            int c = cpr(cmp, key, n.key);
                            if (c == 0) {
                                return n;
                            }
                            if (c < 0) {
                                return null;
                            }
                            b = n;
                            n = f;
                        }
                    }
                }
                return null;
            }
        }
        throw new NullPointerException();
    }

    private V doGet(Object key) {
        if (key != null) {
            Comparator<? super K> cmp = this.comparator;
            while (true) {
                Node<K, V> b = findPredecessor(key, cmp);
                Node<K, V> n = b.next;
                while (n != null) {
                    Node<K, V> f = n.next;
                    if (n == b.next) {
                        V v = (V) n.value;
                        if (v == null) {
                            n.helpDelete(b, f);
                        } else if (!(b.value == null || v == n)) {
                            int c = cpr(cmp, key, n.key);
                            if (c == 0) {
                                return v;
                            }
                            if (c < 0) {
                                return null;
                            }
                            b = n;
                            n = f;
                        }
                    }
                }
                return null;
            }
        }
        throw new NullPointerException();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:101:0x00c5 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:104:0x00c5 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r8v3. Raw type applied. Possible types: java.util.concurrent.ConcurrentSkipListMap$Index<K, V>, java.util.concurrent.ConcurrentSkipListMap$Index */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
        r6 = new java.util.concurrent.ConcurrentSkipListMap.Node<>(r17, r18, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004a, code lost:
        if (r4.casNext(r5, r6) != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        r4 = java.util.concurrent.ThreadLocalRandom.nextSecondarySeed();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0057, code lost:
        if ((-2147483647 & r4) != 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0059, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005a, code lost:
        r8 = r4 >>> 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        if ((r8 & 1) == 0) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        r5 = r5 + 1;
        r4 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0065, code lost:
        r4 = null;
        r8 = r0.head;
        r10 = r8.level;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        if (r5 > r10) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006d, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006e, code lost:
        if (r10 > r5) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0070, code lost:
        r4 = new java.util.concurrent.ConcurrentSkipListMap.Index<>(r6, r4, null);
        r10 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0079, code lost:
        r7 = r4;
        r2 = r5;
        r15 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x007d, code lost:
        r5 = r10 + 1;
        r10 = new java.util.concurrent.ConcurrentSkipListMap.Index[(r5 + 1)];
        r12 = 1;
        r13 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0085, code lost:
        if (r12 > r5) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0087, code lost:
        r4 = new java.util.concurrent.ConcurrentSkipListMap.Index<>(r6, r13, null);
        r13 = r4;
        r10[r12] = r4;
        r12 = r12 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0092, code lost:
        r8 = r0.head;
        r4 = r8.level;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0096, code lost:
        if (r5 > r4) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0098, code lost:
        r2 = r5;
        r15 = r8;
        r7 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009c, code lost:
        r12 = r8;
        r14 = r8.node;
        r15 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a1, code lost:
        if (r15 > r5) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a3, code lost:
        r12 = new java.util.concurrent.ConcurrentSkipListMap.HeadIndex<>(r14, r12, r10[r15], r15);
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b5, code lost:
        if (r0.casHead(r8, r12) == false) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b7, code lost:
        r7 = r10[r4];
        r2 = r4;
        r15 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00be, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00bf, code lost:
        r5 = r15.level;
        r8 = r15;
        r10 = r8.right;
        r12 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c5, code lost:
        if (r12 != null) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c8, code lost:
        if (r10 == null) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ca, code lost:
        r13 = r10.node;
        r14 = cpr(r3, r17, r13.key);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d4, code lost:
        if (r13.value != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00da, code lost:
        if (r8.unlink(r10) != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00dd, code lost:
        r10 = r8.right;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e2, code lost:
        if (r14 <= 0) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e4, code lost:
        r8 = r10;
        r10 = r10.right;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ea, code lost:
        if (r5 != r4) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f0, code lost:
        if (r8.link(r10, r12) != false) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00fa, code lost:
        if (r12.node.value != null) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fc, code lost:
        findNode(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0100, code lost:
        r4 = r4 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0102, code lost:
        if (r4 != 0) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0107, code lost:
        r5 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0109, code lost:
        if (r5 < r4) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x010b, code lost:
        if (r5 >= r2) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x010d, code lost:
        r12 = r12.down;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0110, code lost:
        r8 = r8.down;
        r10 = r8.right;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0117, code lost:
        r0 = r16;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private V doPut(K r17, V r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 294
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentSkipListMap.doPut(java.lang.Object, java.lang.Object, boolean):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public final V doRemove(Object key, Object value) {
        if (key != null) {
            Comparator<? super K> cmp = this.comparator;
            loop0:
            while (true) {
                Node<K, V> b = findPredecessor(key, cmp);
                Node<K, V> n = b.next;
                while (true) {
                    if (n != null) {
                        Node<K, V> f = n.next;
                        if (n != b.next) {
                            break;
                        }
                        V v = (V) n.value;
                        if (v != null) {
                            if (b.value != null && v != n) {
                                int c = cpr(cmp, key, n.key);
                                if (c >= 0) {
                                    if (c <= 0) {
                                        if (value != null && !value.equals(v)) {
                                            break;
                                        } else if (n.casValue(v, null)) {
                                            if (!n.appendMarker(f) || !b.casNext(n, f)) {
                                                findNode(key);
                                            } else {
                                                findPredecessor(key, cmp);
                                                if (this.head.right == null) {
                                                    tryReduceLevel();
                                                }
                                            }
                                            return v;
                                        }
                                    } else {
                                        b = n;
                                        n = f;
                                    }
                                } else {
                                    break loop0;
                                }
                            } else {
                                break;
                            }
                        } else {
                            n.helpDelete(b, f);
                            break;
                        }
                    } else {
                        break loop0;
                    }
                }
            }
            return null;
        }
        throw new NullPointerException();
    }

    private void tryReduceLevel() {
        HeadIndex<K, V> d;
        HeadIndex<K, V> e;
        HeadIndex<K, V> h = this.head;
        if (h.level > 3 && (d = (HeadIndex) h.down) != null && (e = (HeadIndex) d.down) != null && e.right == null && d.right == null && h.right == null && casHead(h, d) && h.right != null) {
            casHead(d, h);
        }
    }

    /* access modifiers changed from: package-private */
    public final Node<K, V> findFirst() {
        while (true) {
            Node<K, V> b = this.head.node;
            Node<K, V> n = b.next;
            if (n == null) {
                return null;
            }
            if (n.value != null) {
                return n;
            }
            n.helpDelete(b, n.next);
        }
    }

    private Map.Entry<K, V> doRemoveFirstEntry() {
        while (true) {
            Node<K, V> b = this.head.node;
            Node<K, V> n = b.next;
            if (n == null) {
                return null;
            }
            Node<K, V> f = n.next;
            if (n == b.next) {
                Object v = n.value;
                if (v == null) {
                    n.helpDelete(b, f);
                } else if (n.casValue(v, null)) {
                    if (!n.appendMarker(f) || !b.casNext(n, f)) {
                        findFirst();
                    }
                    clearIndexToFirst();
                    return new AbstractMap.SimpleImmutableEntry(n.key, v);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r2v0. Raw type applied. Possible types: java.util.concurrent.ConcurrentSkipListMap$Index<K, V> */
    private void clearIndexToFirst() {
        loop0:
        while (true) {
            Index<K, V> q = this.head;
            while (true) {
                Index<K, V> r = q.right;
                if (r == null || !r.indexesDeletedNode() || q.unlink(r)) {
                    Index<K, V> index = q.down;
                    q = index;
                    if (index == null) {
                        break loop0;
                    }
                }
            }
        }
        if (this.head.right == null) {
            tryReduceLevel();
        }
    }

    private Map.Entry<K, V> doRemoveLastEntry() {
        while (true) {
            Node<K, V> b = findPredecessorOfLast();
            Node<K, V> n = b.next;
            if (n != null) {
                while (true) {
                    Node<K, V> f = n.next;
                    if (n != b.next) {
                        continue;
                        break;
                    }
                    Object v = n.value;
                    if (v != null) {
                        if (b.value == null || v == n) {
                            break;
                        } else if (f != null) {
                            b = n;
                            n = f;
                        } else if (n.casValue(v, null)) {
                            K key = n.key;
                            if (!n.appendMarker(f) || !b.casNext(n, f)) {
                                findNode(key);
                            } else {
                                findPredecessor(key, this.comparator);
                                if (this.head.right == null) {
                                    tryReduceLevel();
                                }
                            }
                            return new AbstractMap.SimpleImmutableEntry(key, v);
                        }
                    } else {
                        n.helpDelete(b, f);
                        break;
                    }
                }
            } else if (b.isBaseHeader()) {
                return null;
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0002 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r1v1. Raw type applied. Possible types: java.util.concurrent.ConcurrentSkipListMap$Index<K, V> */
    /* access modifiers changed from: package-private */
    public final Node<K, V> findLast() {
        Node<K, V> b;
        Index<K, V> q = this.head;
        loop0:
        while (true) {
            Index<K, V> r = q.right;
            if (r == null) {
                Index<K, V> d = q.down;
                if (d == null) {
                    b = q.node;
                    Node<K, V> n = b.next;
                    while (n != null) {
                        Node<K, V> f = n.next;
                        if (n == b.next) {
                            Object v = n.value;
                            if (v == null) {
                                n.helpDelete(b, f);
                            } else if (!(b.value == null || v == n)) {
                                b = n;
                                n = f;
                            }
                        }
                        q = this.head;
                    }
                    break loop0;
                }
                q = d;
            } else if (r.indexesDeletedNode()) {
                q.unlink(r);
                q = this.head;
            } else {
                q = r;
            }
        }
        if (b.isBaseHeader()) {
            return null;
        }
        return b;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:19:0x0002 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r1v1. Raw type applied. Possible types: java.util.concurrent.ConcurrentSkipListMap$Index<K, V> */
    private Node<K, V> findPredecessorOfLast() {
        Index<K, V> r;
        while (true) {
            Index<K, V> q = this.head;
            while (true) {
                r = q.right;
                if (r != null) {
                    if (r.indexesDeletedNode()) {
                        break;
                    } else if (r.node.next != null) {
                        q = r;
                    }
                }
                Index<K, V> d = q.down;
                if (d == null) {
                    return q.node;
                }
                q = d;
            }
            q.unlink(r);
        }
    }

    /* access modifiers changed from: package-private */
    public final Node<K, V> findNear(K key, int rel, Comparator<? super K> cmp) {
        Node<K, V> n;
        if (key != null) {
            loop0:
            while (true) {
                Node<K, V> b = findPredecessor(key, cmp);
                n = b.next;
                while (n != null) {
                    Node<K, V> f = n.next;
                    if (n == b.next) {
                        Object v = n.value;
                        if (v == null) {
                            n.helpDelete(b, f);
                        } else if (!(b.value == null || v == n)) {
                            int c = cpr(cmp, key, n.key);
                            if ((c != 0 || (rel & 1) == 0) && (c >= 0 || (rel & 2) != 0)) {
                                if (c > 0 || (rel & 2) == 0) {
                                    b = n;
                                    n = f;
                                } else if (b.isBaseHeader()) {
                                    return null;
                                } else {
                                    return b;
                                }
                            }
                        }
                    }
                }
                if ((rel & 2) == 0 || b.isBaseHeader()) {
                    return null;
                }
                return b;
            }
            return n;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public final AbstractMap.SimpleImmutableEntry<K, V> getNear(K key, int rel) {
        AbstractMap.SimpleImmutableEntry<K, V> e;
        Comparator<? super K> cmp = this.comparator;
        do {
            Node<K, V> n = findNear(key, rel, cmp);
            if (n == null) {
                return null;
            }
            e = n.createSnapshot();
        } while (e == null);
        return e;
    }

    public ConcurrentSkipListMap() {
        this.comparator = null;
        initialize();
    }

    public ConcurrentSkipListMap(Comparator<? super K> comparator2) {
        this.comparator = comparator2;
        initialize();
    }

    public ConcurrentSkipListMap(Map<? extends K, ? extends V> m) {
        this.comparator = null;
        initialize();
        putAll(m);
    }

    public ConcurrentSkipListMap(SortedMap<K, ? extends V> m) {
        this.comparator = m.comparator();
        initialize();
        buildFromSorted(m);
    }

    @Override // java.util.AbstractMap
    public ConcurrentSkipListMap<K, V> clone() {
        try {
            ConcurrentSkipListMap<K, V> clone = (ConcurrentSkipListMap) super.clone();
            clone.initialize();
            clone.buildFromSorted(this);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.concurrent.ConcurrentSkipListMap$Index] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void buildFromSorted(java.util.SortedMap<K, ? extends V> r17) {
        /*
        // Method dump skipped, instructions count: 177
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentSkipListMap.buildFromSorted(java.util.SortedMap):void");
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        for (Node<K, V> n = findFirst(); n != null; n = n.next) {
            V v = n.getValidValue();
            if (v != null) {
                s.writeObject(n.key);
                s.writeObject(v);
            }
        }
        s.writeObject(null);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x002a */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.concurrent.ConcurrentSkipListMap$Index] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readObject(java.io.ObjectInputStream r17) throws java.io.IOException, java.lang.ClassNotFoundException {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentSkipListMap.readObject(java.io.ObjectInputStream):void");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return doGet(key) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object key) {
        return doGet(key);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V getOrDefault(Object key, V defaultValue) {
        V v = doGet(key);
        return v == null ? defaultValue : v;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K key, V value) {
        if (value != null) {
            return doPut(key, value, false);
        }
        throw new NullPointerException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object key) {
        return doRemove(key, null);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        if (value != null) {
            for (Node<K, V> n = findFirst(); n != null; n = n.next) {
                V v = n.getValidValue();
                if (v != null && value.equals(v)) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long count = 0;
        for (Node<K, V> n = findFirst(); n != null; n = n.next) {
            if (n.getValidValue() != null) {
                count++;
            }
        }
        if (count >= 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) count;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return findFirst() == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        initialize();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.ConcurrentSkipListMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Object apply;
        if (key == null || mappingFunction == null) {
            throw new NullPointerException();
        }
        V v = (V) doGet(key);
        if (v != null || (apply = mappingFunction.apply(key)) == null) {
            return v;
        }
        V p = doPut(key, apply, true);
        return p == null ? (V) apply : p;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (key == null || remappingFunction == null) {
            throw new NullPointerException();
        }
        while (true) {
            Node<K, V> n = findNode(key);
            if (n == null) {
                return null;
            }
            Object v = (Object) n.value;
            if (v != null) {
                V r = (V) remappingFunction.apply(key, v);
                if (r != null) {
                    if (n.casValue(v, r)) {
                        return r;
                    }
                } else if (doRemove(key, v) != null) {
                    return null;
                }
            }
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (key == null || remappingFunction == null) {
            throw new NullPointerException();
        }
        while (true) {
            Node<K, V> n = findNode(key);
            if (n == null) {
                V r = (V) remappingFunction.apply(key, null);
                if (r == null) {
                    break;
                } else if (doPut(key, r, true) == null) {
                    return r;
                }
            } else {
                Object v = (Object) n.value;
                if (v == null) {
                    continue;
                } else {
                    V r2 = (V) remappingFunction.apply(key, v);
                    if (r2 != null) {
                        if (n.casValue(v, r2)) {
                            return r2;
                        }
                    } else if (doRemove(key, v) != null) {
                        break;
                    }
                }
            }
        }
        return null;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        if (key == null || value == null || remappingFunction == null) {
            throw new NullPointerException();
        }
        while (true) {
            Node<K, V> n = findNode(key);
            if (n != null) {
                Object v = (Object) n.value;
                if (v == null) {
                    continue;
                } else {
                    V r = (V) remappingFunction.apply(v, value);
                    if (r != null) {
                        if (n.casValue(v, r)) {
                            return r;
                        }
                    } else if (doRemove(key, v) != null) {
                        return null;
                    }
                }
            } else if (doPut(key, value, true) == null) {
                return value;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.SortedMap
    public NavigableSet<K> keySet() {
        KeySet<K, V> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        KeySet<K, V> keySet2 = new KeySet<>(this);
        this.keySet = keySet2;
        return keySet2;
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
    public NavigableSet<K> navigableKeySet() {
        KeySet<K, V> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        KeySet<K, V> keySet2 = new KeySet<>(this);
        this.keySet = keySet2;
        return keySet2;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Collection<V> values() {
        Values<K, V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        Values<K, V> values2 = new Values<>(this);
        this.values = values2;
        return values2;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
    public Set<Map.Entry<K, V>> entrySet() {
        EntrySet<K, V> es = this.entrySet;
        if (es != null) {
            return es;
        }
        EntrySet<K, V> entrySet2 = new EntrySet<>(this);
        this.entrySet = entrySet2;
        return entrySet2;
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
    public ConcurrentNavigableMap<K, V> descendingMap() {
        ConcurrentNavigableMap<K, V> dm = this.descendingMap;
        if (dm != null) {
            return dm;
        }
        SubMap subMap = new SubMap(this, null, false, null, false, true);
        this.descendingMap = subMap;
        return subMap;
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
    public NavigableSet<K> descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0043 A[Catch:{ ClassCastException -> 0x0065, NullPointerException -> 0x0063 }] */
    @Override // java.util.AbstractMap, java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r9) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentSkipListMap.equals(java.lang.Object):boolean");
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K key, V value) {
        if (value != null) {
            return doPut(key, value, true);
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object key, Object value) {
        if (key != null) {
            return (value == null || doRemove(key, value) == null) ? false : true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean replace(K key, V oldValue, V newValue) {
        if (key == null || oldValue == null || newValue == null) {
            throw new NullPointerException();
        }
        while (true) {
            Node<K, V> n = findNode(key);
            if (n == null) {
                return false;
            }
            Object v = n.value;
            if (v != null) {
                if (!oldValue.equals(v)) {
                    return false;
                }
                if (n.casValue(v, newValue)) {
                    return true;
                }
            }
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public V replace(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        }
        while (true) {
            Node<K, V> n = findNode(key);
            if (n == null) {
                return null;
            }
            V v = (V) n.value;
            if (v != null && n.casValue(v, value)) {
                return v;
            }
        }
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return this.comparator;
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        Node<K, V> n = findFirst();
        if (n != null) {
            return n.key;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        Node<K, V> n = findLast();
        if (n != null) {
            return n.key;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
    public ConcurrentNavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        if (fromKey != null && toKey != null) {
            return new SubMap(this, fromKey, fromInclusive, toKey, toInclusive, false);
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
    public ConcurrentNavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        if (toKey != null) {
            return new SubMap(this, null, false, toKey, inclusive, false);
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
    public ConcurrentNavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        if (fromKey != null) {
            return new SubMap(this, fromKey, inclusive, null, false, false);
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap, java.util.SortedMap
    public ConcurrentNavigableMap<K, V> subMap(K fromKey, K toKey) {
        return subMap((Object) fromKey, true, (Object) toKey, false);
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap, java.util.SortedMap
    public ConcurrentNavigableMap<K, V> headMap(K toKey) {
        return headMap((Object) toKey, false);
    }

    @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap, java.util.SortedMap
    public ConcurrentNavigableMap<K, V> tailMap(K fromKey) {
        return tailMap((Object) fromKey, true);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K key) {
        return getNear(key, 2);
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K key) {
        Node<K, V> n = findNear(key, 2, this.comparator);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K key) {
        return getNear(key, 3);
    }

    @Override // java.util.NavigableMap
    public K floorKey(K key) {
        Node<K, V> n = findNear(key, 3, this.comparator);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K key) {
        return getNear(key, 1);
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K key) {
        Node<K, V> n = findNear(key, 1, this.comparator);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K key) {
        return getNear(key, 0);
    }

    @Override // java.util.NavigableMap
    public K higherKey(K key) {
        Node<K, V> n = findNear(key, 0, this.comparator);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        AbstractMap.SimpleImmutableEntry<K, V> e;
        do {
            Node<K, V> n = findFirst();
            if (n == null) {
                return null;
            }
            e = n.createSnapshot();
        } while (e == null);
        return e;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        AbstractMap.SimpleImmutableEntry<K, V> e;
        do {
            Node<K, V> n = findLast();
            if (n == null) {
                return null;
            }
            e = n.createSnapshot();
        } while (e == null);
        return e;
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollFirstEntry() {
        return doRemoveFirstEntry();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollLastEntry() {
        return doRemoveLastEntry();
    }

    abstract class Iter<T> implements Iterator<T> {
        Node<K, V> lastReturned;
        Node<K, V> next;
        V nextValue;

        Iter() {
            while (true) {
                Node<K, V> findFirst = ConcurrentSkipListMap.this.findFirst();
                this.next = findFirst;
                if (findFirst != null) {
                    V v = (V) this.next.value;
                    if (v != null && v != this.next) {
                        this.nextValue = v;
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: package-private */
        public final void advance() {
            Node<K, V> node = this.next;
            if (node != null) {
                this.lastReturned = node;
                while (true) {
                    Node<K, V> node2 = this.next.next;
                    this.next = node2;
                    if (node2 != null) {
                        V v = (V) this.next.value;
                        if (v != null && v != this.next) {
                            this.nextValue = v;
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            Node<K, V> l = this.lastReturned;
            if (l != null) {
                ConcurrentSkipListMap.this.remove(l.key);
                this.lastReturned = null;
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class ValueIterator extends ConcurrentSkipListMap<K, V>.Iter {
        ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public V next() {
            V v = (V) this.nextValue;
            advance();
            return v;
        }
    }

    final class KeyIterator extends ConcurrentSkipListMap<K, V>.Iter {
        KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public K next() {
            Node<K, V> n = this.next;
            advance();
            return n.key;
        }
    }

    final class EntryIterator extends ConcurrentSkipListMap<K, V>.Iter {
        EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            Node<K, V> n = this.next;
            Object obj = this.nextValue;
            advance();
            return new AbstractMap.SimpleImmutableEntry(n.key, obj);
        }
    }

    static final <E> List<E> toList(Collection<E> c) {
        ArrayList<E> list = new ArrayList<>();
        for (E e : c) {
            list.add(e);
        }
        return list;
    }

    /* access modifiers changed from: package-private */
    public static final class KeySet<K, V> extends AbstractSet<K> implements NavigableSet<K> {
        final ConcurrentNavigableMap<K, V> m;

        KeySet(ConcurrentNavigableMap<K, V> map) {
            this.m = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.m.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            return this.m.containsKey(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return this.m.remove(o) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.m.clear();
        }

        @Override // java.util.NavigableSet
        public K lower(K e) {
            return this.m.lowerKey(e);
        }

        @Override // java.util.NavigableSet
        public K floor(K e) {
            return this.m.floorKey(e);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K e) {
            return this.m.ceilingKey(e);
        }

        @Override // java.util.NavigableSet
        public K higher(K e) {
            return this.m.higherKey(e);
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return this.m.comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return this.m.firstKey();
        }

        @Override // java.util.SortedSet
        public K last() {
            return this.m.lastKey();
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            Map.Entry<K, V> e = this.m.pollFirstEntry();
            if (e == null) {
                return null;
            }
            return e.getKey();
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            Map.Entry<K, V> e = this.m.pollLastEntry();
            if (e == null) {
                return null;
            }
            return e.getKey();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.util.NavigableSet, java.lang.Iterable
        public Iterator<K> iterator() {
            ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
            if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                ConcurrentSkipListMap concurrentSkipListMap = (ConcurrentSkipListMap) concurrentNavigableMap;
                Objects.requireNonNull(concurrentSkipListMap);
                return new KeyIterator();
            }
            SubMap subMap = (SubMap) concurrentNavigableMap;
            Objects.requireNonNull(subMap);
            return new SubMap.SubMapKeyIterator();
        }

        @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Set)) {
                return false;
            }
            Collection<?> c = (Collection) o;
            try {
                if (!containsAll(c) || !c.containsAll(this)) {
                    return false;
                }
                return true;
            } catch (ClassCastException e) {
                return false;
            } catch (NullPointerException e2) {
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return ConcurrentSkipListMap.toList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] a) {
            return (T[]) ConcurrentSkipListMap.toList(this).toArray(a);
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K fromElement, boolean fromInclusive, K toElement, boolean toInclusive) {
            return new KeySet(this.m.subMap((Object) fromElement, fromInclusive, (Object) toElement, toInclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K toElement, boolean inclusive) {
            return new KeySet(this.m.headMap((Object) toElement, inclusive));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K fromElement, boolean inclusive) {
            return new KeySet(this.m.tailMap((Object) fromElement, inclusive));
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> subSet(K fromElement, K toElement) {
            return subSet(fromElement, true, toElement, false);
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> headSet(K toElement) {
            return headSet(toElement, false);
        }

        @Override // java.util.SortedSet, java.util.NavigableSet
        public NavigableSet<K> tailSet(K fromElement) {
            return tailSet(fromElement, true);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return new KeySet(this.m.descendingMap());
        }

        @Override // java.util.SortedSet, java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<K> spliterator() {
            ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
            if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                return ((ConcurrentSkipListMap) concurrentNavigableMap).keySpliterator();
            }
            SubMap subMap = (SubMap) concurrentNavigableMap;
            Objects.requireNonNull(subMap);
            return new SubMap.SubMapKeyIterator();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Values<K, V> extends AbstractCollection<V> {
        final ConcurrentNavigableMap<K, V> m;

        Values(ConcurrentNavigableMap<K, V> map) {
            this.m = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
            if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                ConcurrentSkipListMap concurrentSkipListMap = (ConcurrentSkipListMap) concurrentNavigableMap;
                Objects.requireNonNull(concurrentSkipListMap);
                return new ValueIterator();
            }
            SubMap subMap = (SubMap) concurrentNavigableMap;
            Objects.requireNonNull(subMap);
            return new SubMap.SubMapValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.m.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object o) {
            return this.m.containsValue(o);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.m.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return ConcurrentSkipListMap.toList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] a) {
            return (T[]) ConcurrentSkipListMap.toList(this).toArray(a);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<V> spliterator() {
            ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
            if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                return ((ConcurrentSkipListMap) concurrentNavigableMap).valueSpliterator();
            }
            SubMap subMap = (SubMap) concurrentNavigableMap;
            Objects.requireNonNull(subMap);
            return new SubMap.SubMapValueIterator();
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super V> filter) {
            if (filter != null) {
                ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
                if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                    return ((ConcurrentSkipListMap) concurrentNavigableMap).removeValueIf(filter);
                }
                SubMap subMap = (SubMap) concurrentNavigableMap;
                Objects.requireNonNull(subMap);
                Iterator<Map.Entry<K, V>> it = new SubMap.SubMapEntryIterator();
                boolean removed = false;
                while (it.hasNext()) {
                    Map.Entry<K, V> e = it.next();
                    V v = e.getValue();
                    if (filter.test(v) && this.m.remove(e.getKey(), v)) {
                        removed = true;
                    }
                }
                return removed;
            }
            throw new NullPointerException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {
        final ConcurrentNavigableMap<K, V> m;

        EntrySet(ConcurrentNavigableMap<K, V> map) {
            this.m = map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
            if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                ConcurrentSkipListMap concurrentSkipListMap = (ConcurrentSkipListMap) concurrentNavigableMap;
                Objects.requireNonNull(concurrentSkipListMap);
                return new EntryIterator();
            }
            SubMap subMap = (SubMap) concurrentNavigableMap;
            Objects.requireNonNull(subMap);
            return new SubMap.SubMapEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            V v = this.m.get(e.getKey());
            if (v == null || !v.equals(e.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            return this.m.remove(e.getKey(), e.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.m.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.m.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.m.clear();
        }

        @Override // java.util.Collection, java.util.AbstractSet, java.util.Set
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Set)) {
                return false;
            }
            Collection<?> c = (Collection) o;
            try {
                if (!containsAll(c) || !c.containsAll(this)) {
                    return false;
                }
                return true;
            } catch (ClassCastException e) {
                return false;
            } catch (NullPointerException e2) {
                return false;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return ConcurrentSkipListMap.toList(this).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] a) {
            return (T[]) ConcurrentSkipListMap.toList(this).toArray(a);
        }

        @Override // java.util.Collection, java.util.Set, java.lang.Iterable
        public Spliterator<Map.Entry<K, V>> spliterator() {
            ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
            if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                return ((ConcurrentSkipListMap) concurrentNavigableMap).entrySpliterator();
            }
            SubMap subMap = (SubMap) concurrentNavigableMap;
            Objects.requireNonNull(subMap);
            return new SubMap.SubMapEntryIterator();
        }

        @Override // java.util.Collection
        public boolean removeIf(Predicate<? super Map.Entry<K, V>> filter) {
            if (filter != null) {
                ConcurrentNavigableMap<K, V> concurrentNavigableMap = this.m;
                if (concurrentNavigableMap instanceof ConcurrentSkipListMap) {
                    return ((ConcurrentSkipListMap) concurrentNavigableMap).removeEntryIf(filter);
                }
                SubMap subMap = (SubMap) concurrentNavigableMap;
                Objects.requireNonNull(subMap);
                Iterator<Map.Entry<K, V>> it = new SubMap.SubMapEntryIterator();
                boolean removed = false;
                while (it.hasNext()) {
                    Map.Entry<K, V> e = it.next();
                    if (filter.test(e) && this.m.remove(e.getKey(), e.getValue())) {
                        removed = true;
                    }
                }
                return removed;
            }
            throw new NullPointerException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class SubMap<K, V> extends AbstractMap<K, V> implements ConcurrentNavigableMap<K, V>, Cloneable, Serializable {
        private static final long serialVersionUID = -7647078645895051609L;
        private transient Set<Map.Entry<K, V>> entrySetView;
        private final K hi;
        private final boolean hiInclusive;
        final boolean isDescending;
        private transient KeySet<K, V> keySetView;
        private final K lo;
        private final boolean loInclusive;
        final ConcurrentSkipListMap<K, V> m;
        private transient Collection<V> valuesView;

        SubMap(ConcurrentSkipListMap<K, V> map, K fromKey, boolean fromInclusive, K toKey, boolean toInclusive, boolean isDescending2) {
            Comparator<? super K> cmp = map.comparator;
            if (fromKey == null || toKey == null || ConcurrentSkipListMap.cpr(cmp, fromKey, toKey) <= 0) {
                this.m = map;
                this.lo = fromKey;
                this.hi = toKey;
                this.loInclusive = fromInclusive;
                this.hiInclusive = toInclusive;
                this.isDescending = isDescending2;
                return;
            }
            throw new IllegalArgumentException("inconsistent range");
        }

        /* access modifiers changed from: package-private */
        public boolean tooLow(Object key, Comparator<? super K> cmp) {
            int c;
            K k = this.lo;
            return k != null && ((c = ConcurrentSkipListMap.cpr(cmp, key, k)) < 0 || (c == 0 && !this.loInclusive));
        }

        /* access modifiers changed from: package-private */
        public boolean tooHigh(Object key, Comparator<? super K> cmp) {
            int c;
            K k = this.hi;
            return k != null && ((c = ConcurrentSkipListMap.cpr(cmp, key, k)) > 0 || (c == 0 && !this.hiInclusive));
        }

        /* access modifiers changed from: package-private */
        public boolean inBounds(Object key, Comparator<? super K> cmp) {
            return !tooLow(key, cmp) && !tooHigh(key, cmp);
        }

        /* access modifiers changed from: package-private */
        public void checkKeyBounds(K key, Comparator<? super K> cmp) {
            if (key == null) {
                throw new NullPointerException();
            } else if (!inBounds(key, cmp)) {
                throw new IllegalArgumentException("key out of range");
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isBeforeEnd(Node<K, V> n, Comparator<? super K> cmp) {
            K k;
            if (n == null) {
                return false;
            }
            if (this.hi == null || (k = n.key) == null) {
                return true;
            }
            int c = ConcurrentSkipListMap.cpr(cmp, k, this.hi);
            if (c > 0 || (c == 0 && !this.hiInclusive)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public Node<K, V> loNode(Comparator<? super K> cmp) {
            K k = this.lo;
            if (k == null) {
                return this.m.findFirst();
            }
            if (this.loInclusive) {
                return this.m.findNear(k, 1, cmp);
            }
            return this.m.findNear(k, 0, cmp);
        }

        /* access modifiers changed from: package-private */
        public Node<K, V> hiNode(Comparator<? super K> cmp) {
            K k = this.hi;
            if (k == null) {
                return this.m.findLast();
            }
            if (this.hiInclusive) {
                return this.m.findNear(k, 3, cmp);
            }
            return this.m.findNear(k, 2, cmp);
        }

        /* access modifiers changed from: package-private */
        public K lowestKey() {
            Comparator<? super K> cmp = this.m.comparator;
            Node<K, V> n = loNode(cmp);
            if (isBeforeEnd(n, cmp)) {
                return n.key;
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: package-private */
        public K highestKey() {
            Comparator<? super K> cmp = this.m.comparator;
            Node<K, V> n = hiNode(cmp);
            if (n != null) {
                K last = n.key;
                if (inBounds(last, cmp)) {
                    return last;
                }
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, V> lowestEntry() {
            Map.Entry<K, V> e;
            Comparator<? super K> cmp = this.m.comparator;
            do {
                Node<K, V> n = loNode(cmp);
                if (!isBeforeEnd(n, cmp)) {
                    return null;
                }
                e = n.createSnapshot();
            } while (e == null);
            return e;
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, V> highestEntry() {
            Map.Entry<K, V> e;
            Comparator<? super K> cmp = this.m.comparator;
            do {
                Node<K, V> n = hiNode(cmp);
                if (n == null || !inBounds(n.key, cmp)) {
                    return null;
                }
                e = n.createSnapshot();
            } while (e == null);
            return e;
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, V> removeLowest() {
            K k;
            V v;
            Comparator<? super K> cmp = this.m.comparator;
            do {
                Node<K, V> n = loNode(cmp);
                if (n == null) {
                    return null;
                }
                k = n.key;
                if (!inBounds(k, cmp)) {
                    return null;
                }
                v = this.m.doRemove(k, null);
            } while (v == null);
            return new AbstractMap.SimpleImmutableEntry(k, v);
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, V> removeHighest() {
            K k;
            V v;
            Comparator<? super K> cmp = this.m.comparator;
            do {
                Node<K, V> n = hiNode(cmp);
                if (n == null) {
                    return null;
                }
                k = n.key;
                if (!inBounds(k, cmp)) {
                    return null;
                }
                v = this.m.doRemove(k, null);
            } while (v == null);
            return new AbstractMap.SimpleImmutableEntry(k, v);
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, V> getNearEntry(K key, int rel) {
            K k;
            V v;
            Comparator<? super K> cmp = this.m.comparator;
            if (this.isDescending) {
                if ((rel & 2) == 0) {
                    rel |= 2;
                } else {
                    rel &= -3;
                }
            }
            if (tooLow(key, cmp)) {
                if ((rel & 2) != 0) {
                    return null;
                }
                return lowestEntry();
            } else if (!tooHigh(key, cmp)) {
                do {
                    Node<K, V> n = this.m.findNear(key, rel, cmp);
                    if (n == null || !inBounds(n.key, cmp)) {
                        return null;
                    }
                    k = n.key;
                    v = n.getValidValue();
                } while (v == null);
                return new AbstractMap.SimpleImmutableEntry(k, v);
            } else if ((rel & 2) != 0) {
                return highestEntry();
            } else {
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public K getNearKey(K key, int rel) {
            Node<K, V> n;
            K k;
            Node<K, V> n2;
            Comparator<? super K> cmp = this.m.comparator;
            if (this.isDescending) {
                if ((rel & 2) == 0) {
                    rel |= 2;
                } else {
                    rel &= -3;
                }
            }
            if (tooLow(key, cmp)) {
                if ((rel & 2) == 0) {
                    Node<K, V> n3 = loNode(cmp);
                    if (isBeforeEnd(n3, cmp)) {
                        return n3.key;
                    }
                }
                return null;
            } else if (tooHigh(key, cmp)) {
                if (!((rel & 2) == 0 || (n2 = hiNode(cmp)) == null)) {
                    K last = n2.key;
                    if (inBounds(last, cmp)) {
                        return last;
                    }
                }
                return null;
            } else {
                do {
                    n = this.m.findNear(key, rel, cmp);
                    if (n == null || !inBounds(n.key, cmp)) {
                        return null;
                    }
                    k = n.key;
                } while (n.getValidValue() == null);
                return k;
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object key) {
            if (key != null) {
                return inBounds(key, this.m.comparator) && this.m.containsKey(key);
            }
            throw new NullPointerException();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object key) {
            if (key == null) {
                throw new NullPointerException();
            } else if (!inBounds(key, this.m.comparator)) {
                return null;
            } else {
                return this.m.get(key);
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K key, V value) {
            checkKeyBounds(key, this.m.comparator);
            return this.m.put(key, value);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object key) {
            if (!inBounds(key, this.m.comparator)) {
                return null;
            }
            return this.m.remove(key);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            Comparator<? super K> cmp = this.m.comparator;
            long count = 0;
            for (Node<K, V> n = loNode(cmp); isBeforeEnd(n, cmp); n = n.next) {
                if (n.getValidValue() != null) {
                    count++;
                }
            }
            if (count >= 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) count;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            Comparator<? super K> cmp = this.m.comparator;
            return !isBeforeEnd(loNode(cmp), cmp);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(Object value) {
            if (value != null) {
                Comparator<? super K> cmp = this.m.comparator;
                for (Node<K, V> n = loNode(cmp); isBeforeEnd(n, cmp); n = n.next) {
                    V v = n.getValidValue();
                    if (v != null && value.equals(v)) {
                        return true;
                    }
                }
                return false;
            }
            throw new NullPointerException();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            Comparator<? super K> cmp = this.m.comparator;
            for (Node<K, V> n = loNode(cmp); isBeforeEnd(n, cmp); n = n.next) {
                if (n.getValidValue() != null) {
                    this.m.remove(n.key);
                }
            }
        }

        @Override // java.util.Map, java.util.concurrent.ConcurrentMap
        public V putIfAbsent(K key, V value) {
            checkKeyBounds(key, this.m.comparator);
            return this.m.putIfAbsent(key, value);
        }

        @Override // java.util.Map, java.util.concurrent.ConcurrentMap
        public boolean remove(Object key, Object value) {
            return inBounds(key, this.m.comparator) && this.m.remove(key, value);
        }

        @Override // java.util.Map, java.util.concurrent.ConcurrentMap
        public boolean replace(K key, V oldValue, V newValue) {
            checkKeyBounds(key, this.m.comparator);
            return this.m.replace(key, oldValue, newValue);
        }

        @Override // java.util.Map, java.util.concurrent.ConcurrentMap
        public V replace(K key, V value) {
            checkKeyBounds(key, this.m.comparator);
            return this.m.replace(key, value);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            Comparator<? super K> cmp = this.m.comparator();
            if (this.isDescending) {
                return Collections.reverseOrder(cmp);
            }
            return cmp;
        }

        /* access modifiers changed from: package-private */
        public SubMap<K, V> newSubMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            Comparator<? super K> cmp = this.m.comparator;
            if (this.isDescending) {
                fromKey = toKey;
                toKey = fromKey;
                fromInclusive = toInclusive;
                toInclusive = fromInclusive;
            }
            K tk = this.lo;
            if (tk != null) {
                if (fromKey == null) {
                    fromKey = this.lo;
                    fromInclusive = this.loInclusive;
                } else {
                    int c = ConcurrentSkipListMap.cpr(cmp, fromKey, tk);
                    if (c < 0 || (c == 0 && !this.loInclusive && fromInclusive)) {
                        throw new IllegalArgumentException("key out of range");
                    }
                }
            }
            K k = this.hi;
            if (k != null) {
                if (toKey == null) {
                    toKey = this.hi;
                    toInclusive = this.hiInclusive;
                } else {
                    int c2 = ConcurrentSkipListMap.cpr(cmp, toKey, k);
                    if (c2 > 0 || (c2 == 0 && !this.hiInclusive && toInclusive)) {
                        throw new IllegalArgumentException("key out of range");
                    }
                }
            }
            return new SubMap<>(this.m, fromKey, fromInclusive, toKey, toInclusive, this.isDescending);
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
        public SubMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
            if (fromKey != null && toKey != null) {
                return newSubMap(fromKey, fromInclusive, toKey, toInclusive);
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
        public SubMap<K, V> headMap(K toKey, boolean inclusive) {
            if (toKey != null) {
                return newSubMap(null, false, toKey, inclusive);
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
        public SubMap<K, V> tailMap(K fromKey, boolean inclusive) {
            if (fromKey != null) {
                return newSubMap(fromKey, inclusive, null, false);
            }
            throw new NullPointerException();
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap, java.util.SortedMap
        public SubMap<K, V> subMap(K fromKey, K toKey) {
            return subMap((Object) fromKey, true, (Object) toKey, false);
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap, java.util.SortedMap
        public SubMap<K, V> headMap(K toKey) {
            return headMap((Object) toKey, false);
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap, java.util.SortedMap
        public SubMap<K, V> tailMap(K fromKey) {
            return tailMap((Object) fromKey, true);
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
        public SubMap<K, V> descendingMap() {
            return new SubMap<>(this.m, this.lo, this.loInclusive, this.hi, this.hiInclusive, !this.isDescending);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> ceilingEntry(K key) {
            return getNearEntry(key, 1);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K key) {
            return getNearKey(key, 1);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lowerEntry(K key) {
            return getNearEntry(key, 2);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K key) {
            return getNearKey(key, 2);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> floorEntry(K key) {
            return getNearEntry(key, 3);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K key) {
            return getNearKey(key, 3);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> higherEntry(K key) {
            return getNearEntry(key, 0);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K key) {
            return getNearKey(key, 0);
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return this.isDescending ? highestKey() : lowestKey();
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return this.isDescending ? lowestKey() : highestKey();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> firstEntry() {
            return this.isDescending ? highestEntry() : lowestEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> lastEntry() {
            return this.isDescending ? lowestEntry() : highestEntry();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollFirstEntry() {
            return this.isDescending ? removeHighest() : removeLowest();
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, V> pollLastEntry() {
            return this.isDescending ? removeLowest() : removeHighest();
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.concurrent.ConcurrentNavigableMap, java.util.concurrent.ConcurrentNavigableMap, java.util.SortedMap
        public NavigableSet<K> keySet() {
            KeySet<K, V> ks = this.keySetView;
            if (ks != null) {
                return ks;
            }
            KeySet<K, V> keySet = new KeySet<>(this);
            this.keySetView = keySet;
            return keySet;
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            KeySet<K, V> ks = this.keySetView;
            if (ks != null) {
                return ks;
            }
            KeySet<K, V> keySet = new KeySet<>(this);
            this.keySetView = keySet;
            return keySet;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Collection<V> values() {
            Collection<V> vs = this.valuesView;
            if (vs != null) {
                return vs;
            }
            Values values = new Values(this);
            this.valuesView = values;
            return values;
        }

        @Override // java.util.AbstractMap, java.util.Map, java.util.SortedMap
        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> es = this.entrySetView;
            if (es != null) {
                return es;
            }
            EntrySet entrySet = new EntrySet(this);
            this.entrySetView = entrySet;
            return entrySet;
        }

        @Override // java.util.concurrent.ConcurrentNavigableMap, java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        abstract class SubMapIter<T> implements Iterator<T>, Spliterator<T> {
            Node<K, V> lastReturned;
            Node<K, V> next;
            V nextValue;

            SubMapIter() {
                Node<K, V> node;
                Comparator<? super K> cmp = SubMap.this.m.comparator;
                while (true) {
                    this.next = SubMap.this.isDescending ? SubMap.this.hiNode(cmp) : SubMap.this.loNode(cmp);
                    Node<K, V> node2 = this.next;
                    if (node2 != null) {
                        V v = (V) node2.value;
                        if (v != null && v != (node = this.next)) {
                            if (!SubMap.this.inBounds(node.key, cmp)) {
                                this.next = null;
                                return;
                            } else {
                                this.nextValue = v;
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                return this.next != null;
            }

            /* access modifiers changed from: package-private */
            public final void advance() {
                Node<K, V> node = this.next;
                if (node != null) {
                    this.lastReturned = node;
                    if (SubMap.this.isDescending) {
                        descend();
                    } else {
                        ascend();
                    }
                } else {
                    throw new NoSuchElementException();
                }
            }

            private void ascend() {
                Node<K, V> node;
                Comparator<? super K> cmp = SubMap.this.m.comparator;
                while (true) {
                    this.next = this.next.next;
                    Node<K, V> node2 = this.next;
                    if (node2 != null) {
                        V v = (V) node2.value;
                        if (v != null && v != (node = this.next)) {
                            if (SubMap.this.tooHigh(node.key, cmp)) {
                                this.next = null;
                                return;
                            } else {
                                this.nextValue = v;
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }

            private void descend() {
                Node<K, V> node;
                Comparator<? super K> cmp = SubMap.this.m.comparator;
                while (true) {
                    this.next = SubMap.this.m.findNear(this.lastReturned.key, 2, cmp);
                    Node<K, V> node2 = this.next;
                    if (node2 != null) {
                        V v = (V) node2.value;
                        if (v != null && v != (node = this.next)) {
                            if (SubMap.this.tooLow(node.key, cmp)) {
                                this.next = null;
                                return;
                            } else {
                                this.nextValue = v;
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                Node<K, V> l = this.lastReturned;
                if (l != null) {
                    SubMap.this.m.remove(l.key);
                    this.lastReturned = null;
                    return;
                }
                throw new IllegalStateException();
            }

            @Override // java.util.Spliterator
            public Spliterator<T> trySplit() {
                return null;
            }

            @Override // java.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> action) {
                if (!hasNext()) {
                    return false;
                }
                action.accept((Object) next());
                return true;
            }

            @Override // java.util.Iterator, java.util.Spliterator
            public void forEachRemaining(Consumer<? super T> action) {
                while (hasNext()) {
                    action.accept((Object) next());
                }
            }

            @Override // java.util.Spliterator
            public long estimateSize() {
                return Long.MAX_VALUE;
            }
        }

        final class SubMapValueIterator extends SubMap<K, V>.SubMapIter {
            SubMapValueIterator() {
                super();
            }

            @Override // java.util.Iterator
            public V next() {
                V v = (V) this.nextValue;
                advance();
                return v;
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return 0;
            }
        }

        final class SubMapKeyIterator extends SubMap<K, V>.SubMapIter {
            SubMapKeyIterator() {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                Node<K, V> n = this.next;
                advance();
                return n.key;
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return 21;
            }

            @Override // java.util.Spliterator
            public final Comparator<? super K> getComparator() {
                return SubMap.this.comparator();
            }
        }

        final class SubMapEntryIterator extends SubMap<K, V>.SubMapIter {
            SubMapEntryIterator() {
                super();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                Node<K, V> n = this.next;
                Object obj = this.nextValue;
                advance();
                return new AbstractMap.SimpleImmutableEntry(n.key, obj);
            }

            @Override // java.util.Spliterator
            public int characteristics() {
                return 1;
            }
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action != null) {
            for (Node<K, V> n = findFirst(); n != null; n = n.next) {
                V v = n.getValidValue();
                if (v != null) {
                    action.accept(n.key, v);
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        V v;
        Object apply;
        if (function != null) {
            for (Node<K, V> n = findFirst(); n != null; n = n.next) {
                do {
                    v = n.getValidValue();
                    if (v == null) {
                        break;
                    }
                    apply = function.apply(n.key, v);
                    if (apply == null) {
                        throw new NullPointerException();
                    }
                } while (!n.casValue(v, apply));
            }
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public boolean removeEntryIf(Predicate<? super Map.Entry<K, V>> function) {
        if (function != null) {
            boolean removed = false;
            for (Node<K, V> n = findFirst(); n != null; n = n.next) {
                V v = n.getValidValue();
                if (v != null) {
                    K k = n.key;
                    if (function.test(new AbstractMap.SimpleImmutableEntry<>(k, v)) && remove(k, v)) {
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
            for (Node<K, V> n = findFirst(); n != null; n = n.next) {
                V v = n.getValidValue();
                if (v != null) {
                    K k = n.key;
                    if (function.test(v) && remove(k, v)) {
                        removed = true;
                    }
                }
            }
            return removed;
        }
        throw new NullPointerException();
    }

    static abstract class CSLMSpliterator<K, V> {
        final Comparator<? super K> comparator;
        Node<K, V> current;
        int est;
        final K fence;
        Index<K, V> row;

        CSLMSpliterator(Comparator<? super K> comparator2, Index<K, V> row2, Node<K, V> origin, K fence2, int est2) {
            this.comparator = comparator2;
            this.row = row2;
            this.current = origin;
            this.fence = fence2;
            this.est = est2;
        }

        public final long estimateSize() {
            return (long) this.est;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class KeySpliterator<K, V> extends CSLMSpliterator<K, V> implements Spliterator<K> {
        KeySpliterator(Comparator<? super K> comparator, Index<K, V> row, Node<K, V> origin, K fence, int est) {
            super(comparator, row, origin, fence, est);
        }

        @Override // java.util.Spliterator
        public KeySpliterator<K, V> trySplit() {
            K ek;
            Node<K, V> b;
            Node<K, V> n;
            K sk;
            Comparator<? super K> cmp = this.comparator;
            Object obj = this.fence;
            Node<K, V> e = this.current;
            if (e == null || (ek = e.key) == null) {
                return null;
            }
            Index<K, V> q = this.row;
            while (q != null) {
                Index<K, V> s = q.right;
                if (s == null || (b = s.node) == null || (n = b.next) == null || n.value == null || (sk = n.key) == null || ConcurrentSkipListMap.cpr(cmp, sk, ek) <= 0 || (obj != null && ConcurrentSkipListMap.cpr(cmp, sk, obj) >= 0)) {
                    Index<K, V> index = q.down;
                    this.row = index;
                    q = index;
                } else {
                    this.current = n;
                    Index<K, V> r = q.down;
                    this.row = s.right != null ? s : s.down;
                    this.est -= this.est >>> 2;
                    return new KeySpliterator<>(cmp, r, e, sk, this.est);
                }
            }
            return null;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> action) {
            if (action != null) {
                Comparator<? super K> cmp = this.comparator;
                Object obj = this.fence;
                this.current = null;
                for (Node<K, V> e = this.current; e != null; e = e.next) {
                    K k = e.key;
                    if (k == null || obj == null || ConcurrentSkipListMap.cpr(cmp, obj, k) > 0) {
                        Object v = e.value;
                        if (!(v == null || v == e)) {
                            action.accept(k);
                        }
                    } else {
                        return;
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> action) {
            if (action != null) {
                Comparator<? super K> cmp = this.comparator;
                Object obj = this.fence;
                Node<K, V> e = this.current;
                while (true) {
                    if (e != null) {
                        K k = e.key;
                        if (k != null && obj != null && ConcurrentSkipListMap.cpr(cmp, obj, k) <= 0) {
                            e = null;
                            break;
                        }
                        Object v = e.value;
                        if (v == null || v == e) {
                            e = e.next;
                        } else {
                            this.current = e.next;
                            action.accept(k);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                this.current = e;
                return false;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4373;
        }

        @Override // java.util.Spliterator
        public final Comparator<? super K> getComparator() {
            return this.comparator;
        }
    }

    /* access modifiers changed from: package-private */
    public final KeySpliterator<K, V> keySpliterator() {
        HeadIndex<K, V> h;
        Node<K, V> p;
        Comparator<? super K> cmp = this.comparator;
        while (true) {
            h = this.head;
            Node<K, V> b = h.node;
            p = b.next;
            if (p != null && p.value == null) {
                p.helpDelete(b, p.next);
            }
        }
        return new KeySpliterator<>(cmp, h, p, null, p == null ? 0 : Integer.MAX_VALUE);
    }

    /* access modifiers changed from: package-private */
    public static final class ValueSpliterator<K, V> extends CSLMSpliterator<K, V> implements Spliterator<V> {
        ValueSpliterator(Comparator<? super K> comparator, Index<K, V> row, Node<K, V> origin, K fence, int est) {
            super(comparator, row, origin, fence, est);
        }

        @Override // java.util.Spliterator
        public ValueSpliterator<K, V> trySplit() {
            K ek;
            Node<K, V> b;
            Node<K, V> n;
            K sk;
            Comparator<? super K> cmp = this.comparator;
            Object obj = this.fence;
            Node<K, V> e = this.current;
            if (e == null || (ek = e.key) == null) {
                return null;
            }
            Index<K, V> q = this.row;
            while (q != null) {
                Index<K, V> s = q.right;
                if (s == null || (b = s.node) == null || (n = b.next) == null || n.value == null || (sk = n.key) == null || ConcurrentSkipListMap.cpr(cmp, sk, ek) <= 0 || (obj != null && ConcurrentSkipListMap.cpr(cmp, sk, obj) >= 0)) {
                    Index<K, V> index = q.down;
                    this.row = index;
                    q = index;
                } else {
                    this.current = n;
                    Index<K, V> r = q.down;
                    this.row = s.right != null ? s : s.down;
                    this.est -= this.est >>> 2;
                    return new ValueSpliterator<>(cmp, r, e, sk, this.est);
                }
            }
            return null;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super V> action) {
            if (action != null) {
                Comparator<? super K> cmp = this.comparator;
                Object obj = this.fence;
                this.current = null;
                for (Node<K, V> e = this.current; e != null; e = e.next) {
                    K k = e.key;
                    if (k == null || obj == null || ConcurrentSkipListMap.cpr(cmp, obj, k) > 0) {
                        Object v = (Object) e.value;
                        if (!(v == null || v == e)) {
                            action.accept(v);
                        }
                    } else {
                        return;
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super V> action) {
            if (action != null) {
                Comparator<? super K> cmp = this.comparator;
                Object obj = this.fence;
                Node<K, V> e = this.current;
                while (true) {
                    if (e != null) {
                        K k = e.key;
                        if (k != null && obj != null && ConcurrentSkipListMap.cpr(cmp, obj, k) <= 0) {
                            e = null;
                            break;
                        }
                        Object v = (Object) e.value;
                        if (v == null || v == e) {
                            e = e.next;
                        } else {
                            this.current = e.next;
                            action.accept(v);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                this.current = e;
                return false;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4368;
        }
    }

    /* access modifiers changed from: package-private */
    public final ValueSpliterator<K, V> valueSpliterator() {
        HeadIndex<K, V> h;
        Node<K, V> p;
        Comparator<? super K> cmp = this.comparator;
        while (true) {
            h = this.head;
            Node<K, V> b = h.node;
            p = b.next;
            if (p != null && p.value == null) {
                p.helpDelete(b, p.next);
            }
        }
        return new ValueSpliterator<>(cmp, h, p, null, p == null ? 0 : Integer.MAX_VALUE);
    }

    /* access modifiers changed from: package-private */
    public static final class EntrySpliterator<K, V> extends CSLMSpliterator<K, V> implements Spliterator<Map.Entry<K, V>> {
        EntrySpliterator(Comparator<? super K> comparator, Index<K, V> row, Node<K, V> origin, K fence, int est) {
            super(comparator, row, origin, fence, est);
        }

        @Override // java.util.Spliterator
        public EntrySpliterator<K, V> trySplit() {
            K ek;
            Node<K, V> b;
            Node<K, V> n;
            K sk;
            Comparator<? super K> cmp = this.comparator;
            Object obj = this.fence;
            Node<K, V> e = this.current;
            if (e == null || (ek = e.key) == null) {
                return null;
            }
            Index<K, V> q = this.row;
            while (q != null) {
                Index<K, V> s = q.right;
                if (s == null || (b = s.node) == null || (n = b.next) == null || n.value == null || (sk = n.key) == null || ConcurrentSkipListMap.cpr(cmp, sk, ek) <= 0 || (obj != null && ConcurrentSkipListMap.cpr(cmp, sk, obj) >= 0)) {
                    Index<K, V> index = q.down;
                    this.row = index;
                    q = index;
                } else {
                    this.current = n;
                    Index<K, V> r = q.down;
                    this.row = s.right != null ? s : s.down;
                    this.est -= this.est >>> 2;
                    return new EntrySpliterator<>(cmp, r, e, sk, this.est);
                }
            }
            return null;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                Comparator<? super K> cmp = this.comparator;
                Object obj = this.fence;
                this.current = null;
                for (Node<K, V> e = this.current; e != null; e = e.next) {
                    K k = e.key;
                    if (k == null || obj == null || ConcurrentSkipListMap.cpr(cmp, obj, k) > 0) {
                        Object v = e.value;
                        if (!(v == null || v == e)) {
                            action.accept(new AbstractMap.SimpleImmutableEntry(k, v));
                        }
                    } else {
                        return;
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
            if (action != null) {
                Comparator<? super K> cmp = this.comparator;
                Object obj = this.fence;
                Node<K, V> e = this.current;
                while (true) {
                    if (e != null) {
                        K k = e.key;
                        if (k != null && obj != null && ConcurrentSkipListMap.cpr(cmp, obj, k) <= 0) {
                            e = null;
                            break;
                        }
                        Object v = e.value;
                        if (v == null || v == e) {
                            e = e.next;
                        } else {
                            this.current = e.next;
                            action.accept(new AbstractMap.SimpleImmutableEntry(k, v));
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                this.current = e;
                return false;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4373;
        }

        @Override // java.util.Spliterator
        public final Comparator<Map.Entry<K, V>> getComparator() {
            if (this.comparator != null) {
                return Map.Entry.comparingByKey(this.comparator);
            }
            return $$Lambda$ConcurrentSkipListMap$EntrySpliterator$y0KdhWWpZC4eKUM6bCtPBgl2u2o.INSTANCE;
        }
    }

    /* access modifiers changed from: package-private */
    public final EntrySpliterator<K, V> entrySpliterator() {
        HeadIndex<K, V> h;
        Node<K, V> p;
        Comparator<? super K> cmp = this.comparator;
        while (true) {
            h = this.head;
            Node<K, V> b = h.node;
            p = b.next;
            if (p != null && p.value == null) {
                p.helpDelete(b, p.next);
            }
        }
        return new EntrySpliterator<>(cmp, h, p, null, p == null ? 0 : Integer.MAX_VALUE);
    }
}
