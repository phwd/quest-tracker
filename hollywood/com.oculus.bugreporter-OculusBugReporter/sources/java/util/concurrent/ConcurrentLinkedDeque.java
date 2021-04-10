package java.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import sun.misc.Unsafe;

public class ConcurrentLinkedDeque<E> extends AbstractCollection<E> implements Deque<E>, Serializable {
    private static final long HEAD;
    private static final int HOPS = 2;
    private static final Node<Object> NEXT_TERMINATOR = new Node<>();
    private static final Node<Object> PREV_TERMINATOR = new Node<>();
    private static final long TAIL;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long serialVersionUID = 876323262645176354L;
    private volatile transient Node<E> head;
    private volatile transient Node<E> tail;

    /* access modifiers changed from: package-private */
    public Node<E> prevTerminator() {
        return (Node<E>) PREV_TERMINATOR;
    }

    /* access modifiers changed from: package-private */
    public Node<E> nextTerminator() {
        return (Node<E>) NEXT_TERMINATOR;
    }

    /* access modifiers changed from: package-private */
    public static final class Node<E> {
        private static final long ITEM;
        private static final long NEXT;
        private static final long PREV;
        private static final Unsafe U = Unsafe.getUnsafe();
        volatile E item;
        volatile Node<E> next;
        volatile Node<E> prev;

        Node() {
        }

        Node(E item2) {
            U.putObject(this, ITEM, item2);
        }

        /* access modifiers changed from: package-private */
        public boolean casItem(E cmp, E val) {
            return U.compareAndSwapObject(this, ITEM, cmp, val);
        }

        /* access modifiers changed from: package-private */
        public void lazySetNext(Node<E> val) {
            U.putOrderedObject(this, NEXT, val);
        }

        /* access modifiers changed from: package-private */
        public boolean casNext(Node<E> cmp, Node<E> val) {
            return U.compareAndSwapObject(this, NEXT, cmp, val);
        }

        /* access modifiers changed from: package-private */
        public void lazySetPrev(Node<E> val) {
            U.putOrderedObject(this, PREV, val);
        }

        /* access modifiers changed from: package-private */
        public boolean casPrev(Node<E> cmp, Node<E> val) {
            return U.compareAndSwapObject(this, PREV, cmp, val);
        }

        static {
            try {
                PREV = U.objectFieldOffset(Node.class.getDeclaredField("prev"));
                ITEM = U.objectFieldOffset(Node.class.getDeclaredField("item"));
                NEXT = U.objectFieldOffset(Node.class.getDeclaredField("next"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    private void linkFirst(E e) {
        Node<E> h;
        Node<E> p;
        Node<E> newNode = new Node<>(Objects.requireNonNull(e));
        loop0:
        while (true) {
            h = this.head;
            p = h;
            while (true) {
                Node<E> q = p.prev;
                if (q != null) {
                    p = q;
                    Node<E> q2 = q.prev;
                    if (q2 != null) {
                        Node<E> h2 = this.head;
                        p = h != h2 ? h2 : q2;
                        h = h2;
                    }
                }
                if (p.next != p) {
                    newNode.lazySetNext(p);
                    if (p.casPrev(null, newNode)) {
                        break loop0;
                    }
                }
            }
        }
        if (p != h) {
            casHead(h, newNode);
        }
    }

    private void linkLast(E e) {
        Node<E> t;
        Node<E> p;
        Node<E> newNode = new Node<>(Objects.requireNonNull(e));
        loop0:
        while (true) {
            t = this.tail;
            p = t;
            while (true) {
                Node<E> q = p.next;
                if (q != null) {
                    p = q;
                    Node<E> q2 = q.next;
                    if (q2 != null) {
                        Node<E> t2 = this.tail;
                        p = t != t2 ? t2 : q2;
                        t = t2;
                    }
                }
                if (p.prev != p) {
                    newNode.lazySetPrev(p);
                    if (p.casNext(null, newNode)) {
                        break loop0;
                    }
                }
            }
        }
        if (p != t) {
            casTail(t, newNode);
        }
    }

    /* JADX INFO: Multiple debug info for r4v5 java.util.concurrent.ConcurrentLinkedDeque$Node<E>: [D('q' java.util.concurrent.ConcurrentLinkedDeque$Node<E>), D('activeSucc' java.util.concurrent.ConcurrentLinkedDeque$Node<E>)] */
    /* JADX INFO: Multiple debug info for r4v7 java.util.concurrent.ConcurrentLinkedDeque$Node<E>: [D('activePred' java.util.concurrent.ConcurrentLinkedDeque$Node<E>), D('q' java.util.concurrent.ConcurrentLinkedDeque$Node<E>)] */
    /* access modifiers changed from: package-private */
    public void unlink(Node<E> x) {
        boolean isFirst;
        Node<E> activePred;
        Node<E> q;
        boolean isLast;
        Node<E> prev = x.prev;
        Node<E> next = x.next;
        if (prev == null) {
            unlinkFirst(x, next);
        } else if (next == null) {
            unlinkLast(x, prev);
        } else {
            int hops = 1;
            Node<E> p = prev;
            while (true) {
                if (p.item != null) {
                    isFirst = false;
                    activePred = p;
                    break;
                }
                Node<E> q2 = p.prev;
                if (q2 == null) {
                    if (p.next != p) {
                        activePred = p;
                        isFirst = true;
                    } else {
                        return;
                    }
                } else if (p != q2) {
                    p = q2;
                    hops++;
                } else {
                    return;
                }
            }
            Node<E> p2 = next;
            while (true) {
                if (p2.item != null) {
                    q = p2;
                    isLast = false;
                    break;
                }
                Node<E> activeSucc = p2.next;
                if (activeSucc == null) {
                    if (p2.prev != p2) {
                        q = p2;
                        isLast = true;
                    } else {
                        return;
                    }
                } else if (p2 != activeSucc) {
                    p2 = activeSucc;
                    hops++;
                } else {
                    return;
                }
            }
            if (hops >= 2 || (!isFirst && !isLast)) {
                skipDeletedSuccessors(activePred);
                skipDeletedPredecessors(q);
                if ((isFirst || isLast) && activePred.next == q && q.prev == activePred) {
                    if (isFirst) {
                        if (activePred.prev != null) {
                            return;
                        }
                    } else if (activePred.item == null) {
                        return;
                    }
                    if (isLast) {
                        if (q.next != null) {
                            return;
                        }
                    } else if (q.item == null) {
                        return;
                    }
                    updateHead();
                    updateTail();
                    x.lazySetPrev(isFirst ? prevTerminator() : x);
                    x.lazySetNext(isLast ? nextTerminator() : x);
                }
            }
        }
    }

    private void unlinkFirst(Node<E> first, Node<E> next) {
        Node<E> q;
        Node<E> o = null;
        Node<E> p = next;
        while (p.item == null && (q = p.next) != null) {
            if (p != q) {
                o = p;
                p = q;
            } else {
                return;
            }
        }
        if (o != null && p.prev != p && first.casNext(next, p)) {
            skipDeletedPredecessors(p);
            if (first.prev != null) {
                return;
            }
            if ((p.next == null || p.item != null) && p.prev == first) {
                updateHead();
                updateTail();
                o.lazySetNext(o);
                o.lazySetPrev(prevTerminator());
            }
        }
    }

    private void unlinkLast(Node<E> last, Node<E> prev) {
        Node<E> q;
        Node<E> o = null;
        Node<E> p = prev;
        while (p.item == null && (q = p.prev) != null) {
            if (p != q) {
                o = p;
                p = q;
            } else {
                return;
            }
        }
        if (o != null && p.next != p && last.casPrev(prev, p)) {
            skipDeletedSuccessors(p);
            if (last.next != null) {
                return;
            }
            if ((p.prev == null || p.item != null) && p.next == last) {
                updateHead();
                updateTail();
                o.lazySetPrev(o);
                o.lazySetNext(nextTerminator());
            }
        }
    }

    private final void updateHead() {
        while (true) {
            Node<E> h = this.head;
            if (h.item == null) {
                Node<E> node = h.prev;
                Node<E> p = node;
                if (node != null) {
                    while (true) {
                        Node<E> q = p.prev;
                        if (q == null) {
                            break;
                        }
                        p = q;
                        Node<E> q2 = q.prev;
                        if (q2 == null) {
                            break;
                        } else if (h != this.head) {
                            break;
                        } else {
                            p = q2;
                        }
                    }
                    if (casHead(h, p)) {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private final void updateTail() {
        while (true) {
            Node<E> t = this.tail;
            if (t.item == null) {
                Node<E> node = t.next;
                Node<E> p = node;
                if (node != null) {
                    while (true) {
                        Node<E> q = p.next;
                        if (q == null) {
                            break;
                        }
                        p = q;
                        Node<E> q2 = q.next;
                        if (q2 == null) {
                            break;
                        } else if (t != this.tail) {
                            break;
                        } else {
                            p = q2;
                        }
                    }
                    if (casTail(t, p)) {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r1.next == r1) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        if (r0 == r1) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        if (r5.casPrev(r0, r1) == false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void skipDeletedPredecessors(java.util.concurrent.ConcurrentLinkedDeque.Node<E> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r0 = r5.prev
            r1 = r0
        L_0x0003:
            E r2 = r1.item
            if (r2 == 0) goto L_0x0008
            goto L_0x0011
        L_0x0008:
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r2 = r1.prev
            if (r2 != 0) goto L_0x001a
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r3 = r1.next
            if (r3 != r1) goto L_0x0011
            goto L_0x001d
        L_0x0011:
            if (r0 == r1) goto L_0x0019
            boolean r2 = r5.casPrev(r0, r1)
            if (r2 == 0) goto L_0x001d
        L_0x0019:
            return
        L_0x001a:
            if (r1 != r2) goto L_0x0026
        L_0x001d:
            E r0 = r5.item
            if (r0 != 0) goto L_0x0000
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r0 = r5.next
            if (r0 == 0) goto L_0x0000
            return
        L_0x0026:
            r1 = r2
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentLinkedDeque.skipDeletedPredecessors(java.util.concurrent.ConcurrentLinkedDeque$Node):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        if (r1.prev == r1) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        if (r0 == r1) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        if (r5.casNext(r0, r1) == false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void skipDeletedSuccessors(java.util.concurrent.ConcurrentLinkedDeque.Node<E> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r0 = r5.next
            r1 = r0
        L_0x0003:
            E r2 = r1.item
            if (r2 == 0) goto L_0x0008
            goto L_0x0011
        L_0x0008:
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r2 = r1.next
            if (r2 != 0) goto L_0x001a
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r3 = r1.prev
            if (r3 != r1) goto L_0x0011
            goto L_0x001d
        L_0x0011:
            if (r0 == r1) goto L_0x0019
            boolean r2 = r5.casNext(r0, r1)
            if (r2 == 0) goto L_0x001d
        L_0x0019:
            return
        L_0x001a:
            if (r1 != r2) goto L_0x0026
        L_0x001d:
            E r0 = r5.item
            if (r0 != 0) goto L_0x0000
            java.util.concurrent.ConcurrentLinkedDeque$Node<E> r0 = r5.prev
            if (r0 == 0) goto L_0x0000
            return
        L_0x0026:
            r1 = r2
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentLinkedDeque.skipDeletedSuccessors(java.util.concurrent.ConcurrentLinkedDeque$Node):void");
    }

    /* access modifiers changed from: package-private */
    public final Node<E> succ(Node<E> p) {
        Node<E> q = p.next;
        return p == q ? first() : q;
    }

    /* access modifiers changed from: package-private */
    public final Node<E> pred(Node<E> p) {
        Node<E> q = p.prev;
        return p == q ? last() : q;
    }

    /* access modifiers changed from: package-private */
    public Node<E> first() {
        Node<E> h;
        Node<E> p;
        do {
            h = this.head;
            p = h;
            while (true) {
                Node<E> q = p.prev;
                if (q == null) {
                    break;
                }
                p = q;
                Node<E> q2 = q.prev;
                if (q2 == null) {
                    break;
                }
                Node<E> h2 = this.head;
                p = h != h2 ? h2 : q2;
                h = h2;
            }
            if (p == h) {
                break;
            }
        } while (!casHead(h, p));
        return p;
    }

    /* access modifiers changed from: package-private */
    public Node<E> last() {
        Node<E> t;
        Node<E> p;
        do {
            t = this.tail;
            p = t;
            while (true) {
                Node<E> q = p.next;
                if (q == null) {
                    break;
                }
                p = q;
                Node<E> q2 = q.next;
                if (q2 == null) {
                    break;
                }
                Node<E> t2 = this.tail;
                p = t != t2 ? t2 : q2;
                t = t2;
            }
            if (p == t) {
                break;
            }
        } while (!casTail(t, p));
        return p;
    }

    private E screenNullResult(E v) {
        if (v != null) {
            return v;
        }
        throw new NoSuchElementException();
    }

    public ConcurrentLinkedDeque() {
        Node<E> node = new Node<>(null);
        this.tail = node;
        this.head = node;
    }

    public ConcurrentLinkedDeque(Collection<? extends E> c) {
        Node<E> h = null;
        Node<E> t = null;
        Iterator<? extends E> it = c.iterator();
        while (it.hasNext()) {
            Node<E> newNode = new Node<>(Objects.requireNonNull(it.next()));
            if (h == null) {
                t = newNode;
                h = newNode;
            } else {
                t.lazySetNext(newNode);
                newNode.lazySetPrev(t);
                t = newNode;
            }
        }
        initHeadTail(h, t);
    }

    private void initHeadTail(Node<E> h, Node<E> t) {
        if (h == t) {
            if (h == null) {
                Node<E> node = new Node<>(null);
                t = node;
                h = node;
            } else {
                Node<E> newNode = new Node<>(null);
                t.lazySetNext(newNode);
                newNode.lazySetPrev(t);
                t = newNode;
            }
        }
        this.head = h;
        this.tail = t;
    }

    @Override // java.util.Deque
    public void addFirst(E e) {
        linkFirst(e);
    }

    @Override // java.util.Deque
    public void addLast(E e) {
        linkLast(e);
    }

    @Override // java.util.Deque
    public boolean offerFirst(E e) {
        linkFirst(e);
        return true;
    }

    @Override // java.util.Deque
    public boolean offerLast(E e) {
        linkLast(e);
        return true;
    }

    @Override // java.util.Deque
    public E peekFirst() {
        Node<E> p = first();
        while (p != null) {
            E item = p.item;
            if (item != null) {
                return item;
            }
            p = succ(p);
        }
        return null;
    }

    @Override // java.util.Deque
    public E peekLast() {
        Node<E> p = last();
        while (p != null) {
            E item = p.item;
            if (item != null) {
                return item;
            }
            p = pred(p);
        }
        return null;
    }

    @Override // java.util.Deque
    public E getFirst() {
        return screenNullResult(peekFirst());
    }

    @Override // java.util.Deque
    public E getLast() {
        return screenNullResult(peekLast());
    }

    @Override // java.util.Deque
    public E pollFirst() {
        Node<E> p = first();
        while (p != null) {
            E item = p.item;
            if (item == null || !p.casItem(item, null)) {
                p = succ(p);
            } else {
                unlink(p);
                return item;
            }
        }
        return null;
    }

    @Override // java.util.Deque
    public E pollLast() {
        Node<E> p = last();
        while (p != null) {
            E item = p.item;
            if (item == null || !p.casItem(item, null)) {
                p = pred(p);
            } else {
                unlink(p);
                return item;
            }
        }
        return null;
    }

    @Override // java.util.Deque
    public E removeFirst() {
        return screenNullResult(pollFirst());
    }

    @Override // java.util.Deque
    public E removeLast() {
        return screenNullResult(pollLast());
    }

    @Override // java.util.Queue, java.util.Deque
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue, java.util.Deque
    public boolean add(E e) {
        return offerLast(e);
    }

    @Override // java.util.Queue, java.util.Deque
    public E poll() {
        return pollFirst();
    }

    @Override // java.util.Queue, java.util.Deque
    public E peek() {
        return peekFirst();
    }

    @Override // java.util.Queue, java.util.Deque
    public E remove() {
        return removeFirst();
    }

    @Override // java.util.Deque
    public E pop() {
        return removeFirst();
    }

    @Override // java.util.Queue, java.util.Deque
    public E element() {
        return getFirst();
    }

    @Override // java.util.Deque
    public void push(E e) {
        addFirst(e);
    }

    @Override // java.util.Deque
    public boolean removeFirstOccurrence(Object o) {
        Objects.requireNonNull(o);
        Node<E> p = first();
        while (p != null) {
            E item = p.item;
            if (item == null || !o.equals(item) || !p.casItem(item, null)) {
                p = succ(p);
            } else {
                unlink(p);
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Deque
    public boolean removeLastOccurrence(Object o) {
        Objects.requireNonNull(o);
        Node<E> p = last();
        while (p != null) {
            E item = p.item;
            if (item == null || !o.equals(item) || !p.casItem(item, null)) {
                p = pred(p);
            } else {
                unlink(p);
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        Node<E> p = first();
        while (p != null) {
            E item = p.item;
            if (item != null && o.equals(item)) {
                return true;
            }
            p = succ(p);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return peekFirst() == null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public int size() {
        int count;
        loop0:
        while (true) {
            count = 0;
            Node<E> p = first();
            while (true) {
                if (p == null || (p.item != null && (count = count + 1) == Integer.MAX_VALUE)) {
                    return count;
                }
                Node<E> p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
        return count;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends E> c) {
        Node<E> t;
        if (c != this) {
            Node<E> beginningOfTheEnd = null;
            Node<E> last = null;
            Iterator<? extends E> it = c.iterator();
            while (it.hasNext()) {
                Node<E> newNode = new Node<>(Objects.requireNonNull(it.next()));
                if (beginningOfTheEnd == null) {
                    last = newNode;
                    beginningOfTheEnd = newNode;
                } else {
                    last.lazySetNext(newNode);
                    newNode.lazySetPrev(last);
                    last = newNode;
                }
            }
            if (beginningOfTheEnd == null) {
                return false;
            }
            loop1:
            while (true) {
                t = this.tail;
                Node<E> p = t;
                while (true) {
                    Node<E> q = p.next;
                    if (q != null) {
                        p = q;
                        Node<E> q2 = q.next;
                        if (q2 != null) {
                            Node<E> t2 = this.tail;
                            p = t != t2 ? t2 : q2;
                            t = t2;
                        }
                    }
                    if (p.prev != p) {
                        beginningOfTheEnd.lazySetPrev(p);
                        if (p.casNext(null, beginningOfTheEnd)) {
                            break loop1;
                        }
                    }
                }
            }
            if (casTail(t, last)) {
                return true;
            }
            Node<E> t3 = this.tail;
            if (last.next != null) {
                return true;
            }
            casTail(t3, last);
            return true;
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        do {
        } while (pollFirst() != null);
    }

    /* JADX INFO: Multiple debug info for r5v0 java.util.concurrent.ConcurrentLinkedDeque$Node<E>: [D('p' java.util.concurrent.ConcurrentLinkedDeque$Node<E>), D('s' java.lang.String)] */
    @Override // java.util.AbstractCollection
    public String toString() {
        int charLength;
        int size;
        String[] a = null;
        loop0:
        while (true) {
            charLength = 0;
            size = 0;
            Node<E> p = first();
            while (true) {
                if (p == null) {
                    break loop0;
                }
                E item = p.item;
                if (item != null) {
                    if (a == null) {
                        a = new String[4];
                    } else if (size == a.length) {
                        a = (String[]) Arrays.copyOf(a, size * 2);
                    }
                    String s = item.toString();
                    a[size] = s;
                    charLength += s.length();
                    size++;
                }
                Node<E> p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
        if (size == 0) {
            return "[]";
        }
        return Helpers.toString(a, size, charLength);
    }

    /* JADX INFO: Multiple debug info for r4v0 java.util.concurrent.ConcurrentLinkedDeque$Node<E>: [D('p' java.util.concurrent.ConcurrentLinkedDeque$Node<E>), D('size' int)] */
    private Object[] toArrayInternal(Object[] a) {
        int size;
        Object[] x = a;
        loop0:
        while (true) {
            size = 0;
            Node<E> p = first();
            while (true) {
                if (p == null) {
                    break loop0;
                }
                E item = p.item;
                if (item != null) {
                    if (x == null) {
                        x = new Object[4];
                    } else if (size == x.length) {
                        x = Arrays.copyOf(x, (size + 4) * 2);
                    }
                    x[size] = item;
                    size++;
                }
                Node<E> p2 = p.next;
                if (p != p2) {
                    p = p2;
                }
            }
        }
        if (x == null) {
            return new Object[0];
        }
        if (a == null || size > a.length) {
            return size == x.length ? x : Arrays.copyOf(x, size);
        }
        if (a != x) {
            System.arraycopy(x, 0, a, 0, size);
        }
        if (size < a.length) {
            a[size] = null;
        }
        return a;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArrayInternal(null);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] a) {
        if (a != null) {
            return (T[]) toArrayInternal(a);
        }
        throw new NullPointerException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        return new DescendingItr();
    }

    private abstract class AbstractItr implements Iterator<E> {
        private Node<E> lastRet;
        private E nextItem;
        private Node<E> nextNode;

        /* access modifiers changed from: package-private */
        public abstract Node<E> nextNode(Node<E> node);

        /* access modifiers changed from: package-private */
        public abstract Node<E> startNode();

        AbstractItr() {
            advance();
        }

        private void advance() {
            Node<E> node = this.nextNode;
            this.lastRet = node;
            Node<E> p = node == null ? startNode() : nextNode(node);
            while (p != null) {
                E item = p.item;
                if (item != null) {
                    this.nextNode = p;
                    this.nextItem = item;
                    return;
                }
                p = nextNode(p);
            }
            this.nextNode = null;
            this.nextItem = null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextItem != null;
        }

        @Override // java.util.Iterator
        public E next() {
            E item = this.nextItem;
            if (item != null) {
                advance();
                return item;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            Node<E> l = this.lastRet;
            if (l != null) {
                l.item = null;
                ConcurrentLinkedDeque.this.unlink(l);
                this.lastRet = null;
                return;
            }
            throw new IllegalStateException();
        }
    }

    private class Itr extends ConcurrentLinkedDeque<E>.AbstractItr {
        private Itr() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentLinkedDeque.AbstractItr
        public Node<E> startNode() {
            return ConcurrentLinkedDeque.this.first();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentLinkedDeque.AbstractItr
        public Node<E> nextNode(Node<E> p) {
            return ConcurrentLinkedDeque.this.succ(p);
        }
    }

    private class DescendingItr extends ConcurrentLinkedDeque<E>.AbstractItr {
        private DescendingItr() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentLinkedDeque.AbstractItr
        public Node<E> startNode() {
            return ConcurrentLinkedDeque.this.last();
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentLinkedDeque.AbstractItr
        public Node<E> nextNode(Node<E> p) {
            return ConcurrentLinkedDeque.this.pred(p);
        }
    }

    static final class CLDSpliterator<E> implements Spliterator<E> {
        static final int MAX_BATCH = 33554432;
        int batch;
        Node<E> current;
        boolean exhausted;
        final ConcurrentLinkedDeque<E> queue;

        CLDSpliterator(ConcurrentLinkedDeque<E> queue2) {
            this.queue = queue2;
        }

        @Override // java.util.Spliterator
        public Spliterator<E> trySplit() {
            ConcurrentLinkedDeque<E> q = this.queue;
            int b = this.batch;
            int n = MAX_BATCH;
            if (b <= 0) {
                n = 1;
            } else if (b < MAX_BATCH) {
                n = b + 1;
            }
            if (this.exhausted) {
                return null;
            }
            Node<E> node = this.current;
            Node<E> p = node;
            if (node == null) {
                Node<E> first = q.first();
                p = first;
                if (first == null) {
                    return null;
                }
            }
            if (p.item == null) {
                Node<E> p2 = p.next;
                if (p == p2) {
                    Node<E> first2 = q.first();
                    p = first2;
                    this.current = first2;
                } else {
                    p = p2;
                }
            }
            if (p == null || p.next == null) {
                return null;
            }
            Object[] a = new Object[n];
            int i = 0;
            do {
                E e = p.item;
                a[i] = e;
                if (e != null) {
                    i++;
                }
                Node<E> p3 = p.next;
                if (p == p3) {
                    p = q.first();
                } else {
                    p = p3;
                }
                if (p == null) {
                    break;
                }
            } while (i < n);
            this.current = p;
            if (p == null) {
                this.exhausted = true;
            }
            if (i <= 0) {
                return null;
            }
            this.batch = i;
            return Spliterators.spliterator(a, 0, i, 4368);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super E> action) {
            if (action != null) {
                ConcurrentLinkedDeque<E> q = this.queue;
                if (!this.exhausted) {
                    Node<E> node = this.current;
                    Node<E> p = node;
                    if (node == null) {
                        Node<E> first = q.first();
                        p = first;
                        if (first == null) {
                            return;
                        }
                    }
                    this.exhausted = true;
                    do {
                        E e = p.item;
                        Node<E> p2 = p.next;
                        if (p == p2) {
                            p = q.first();
                        } else {
                            p = p2;
                        }
                        if (e != null) {
                            action.accept(e);
                            continue;
                        }
                    } while (p != null);
                    return;
                }
                return;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super E> action) {
            E e;
            if (action != null) {
                ConcurrentLinkedDeque<E> q = this.queue;
                if (this.exhausted) {
                    return false;
                }
                Node<E> node = this.current;
                Node<E> p = node;
                if (node == null) {
                    Node<E> first = q.first();
                    p = first;
                    if (first == null) {
                        return false;
                    }
                }
                do {
                    e = p.item;
                    Node<E> p2 = p.next;
                    if (p == p2) {
                        p = q.first();
                    } else {
                        p = p2;
                    }
                    if (e != null) {
                        break;
                    }
                } while (p != null);
                this.current = p;
                if (p == null) {
                    this.exhausted = true;
                }
                if (e == null) {
                    return false;
                }
                action.accept(e);
                return true;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return Long.MAX_VALUE;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4368;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return new CLDSpliterator(this);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        Node<E> p = first();
        while (p != null) {
            E item = p.item;
            if (item != null) {
                s.writeObject(item);
            }
            p = succ(p);
        }
        s.writeObject(null);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        Node<E> h = null;
        Node<E> t = null;
        while (true) {
            Object item = s.readObject();
            if (item != null) {
                Node<E> newNode = new Node<>(item);
                if (h == null) {
                    t = newNode;
                    h = newNode;
                } else {
                    t.lazySetNext(newNode);
                    newNode.lazySetPrev(t);
                    t = newNode;
                }
            } else {
                initHeadTail(h, t);
                return;
            }
        }
    }

    private boolean casHead(Node<E> cmp, Node<E> val) {
        return U.compareAndSwapObject(this, HEAD, cmp, val);
    }

    private boolean casTail(Node<E> cmp, Node<E> val) {
        return U.compareAndSwapObject(this, TAIL, cmp, val);
    }

    static {
        Node<E> node = (Node<E>) PREV_TERMINATOR;
        node.next = node;
        Node<E> node2 = (Node<E>) NEXT_TERMINATOR;
        node2.prev = node2;
        try {
            HEAD = U.objectFieldOffset(ConcurrentLinkedDeque.class.getDeclaredField("head"));
            TAIL = U.objectFieldOffset(ConcurrentLinkedDeque.class.getDeclaredField("tail"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}
