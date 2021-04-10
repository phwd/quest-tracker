package java.util.concurrent;

import sun.misc.Unsafe;

public class Exchanger<V> {
    private static final int ABASE;
    private static final int ASHIFT = 7;
    private static final long BLOCKER;
    private static final long BOUND;
    static final int FULL;
    private static final long MATCH;
    private static final int MMASK = 255;
    private static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final Object NULL_ITEM = new Object();
    private static final int SEQ = 256;
    private static final long SLOT;
    private static final int SPINS = 1024;
    private static final Object TIMED_OUT = new Object();
    private static final Unsafe U = Unsafe.getUnsafe();
    private volatile Node[] arena;
    private volatile int bound;
    private final Participant participant = new Participant();
    private volatile Node slot;

    static {
        int i = NCPU;
        FULL = i >= 510 ? 255 : i >>> 1;
        try {
            BOUND = U.objectFieldOffset(Exchanger.class.getDeclaredField("bound"));
            SLOT = U.objectFieldOffset(Exchanger.class.getDeclaredField("slot"));
            MATCH = U.objectFieldOffset(Node.class.getDeclaredField("match"));
            BLOCKER = U.objectFieldOffset(Thread.class.getDeclaredField("parkBlocker"));
            int scale = U.arrayIndexScale(Node[].class);
            if (((scale - 1) & scale) != 0 || scale > 128) {
                throw new Error("Unsupported array scale");
            }
            ABASE = U.arrayBaseOffset(Node[].class) + 128;
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Node {
        int bound;
        int collides;
        int hash;
        int index;
        Object item;
        volatile Object match;
        volatile Thread parked;

        Node() {
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Participant extends ThreadLocal<Node> {
        Participant() {
        }

        @Override // java.lang.ThreadLocal
        public Node initialValue() {
            return new Node();
        }
    }

    /* JADX INFO: Multiple debug info for r0v5 int: [D('v' java.lang.Object), D('b' int)] */
    /* JADX INFO: Multiple debug info for r0v9 int: [D('c' int), D('i' int)] */
    private final Object arenaExchange(Object item, boolean timed, long ns) {
        long ns2;
        Exchanger<V> exchanger;
        int i;
        Thread t;
        int spins;
        Object v;
        Object v2;
        Object v3;
        long ns3;
        Exchanger<V> exchanger2 = this;
        Object obj = item;
        Node[] a = exchanger2.arena;
        Node p = (Node) exchanger2.participant.get();
        long ns4 = ns;
        int i2 = p.index;
        while (true) {
            Unsafe unsafe = U;
            long j = (long) ((i2 << 7) + ABASE);
            Node q = (Node) unsafe.getObjectVolatile(a, j);
            if (q == null || !U.compareAndSwapObject(a, j, q, null)) {
                int b = exchanger2.bound;
                int m = b & 255;
                if (i2 > m || q != null) {
                    ns2 = ns4;
                    exchanger = exchanger2;
                    if (p.bound != b) {
                        p.bound = b;
                        p.collides = 0;
                        i = (i2 != m || m == 0) ? m : m - 1;
                    } else {
                        int i3 = p.collides;
                        if (i3 < m || m == FULL || !U.compareAndSwapInt(this, BOUND, b, b + 256 + 1)) {
                            p.collides = i3 + 1;
                            i = i2 == 0 ? m : i2 - 1;
                        } else {
                            i = m + 1;
                        }
                    }
                    p.index = i;
                    i2 = i;
                } else {
                    p.item = obj;
                    Object obj2 = null;
                    if (U.compareAndSwapObject(a, j, null, p)) {
                        long end = (!timed || m != 0) ? 0 : System.nanoTime() + ns4;
                        Thread t2 = Thread.currentThread();
                        int h = p.hash;
                        int spins2 = 1024;
                        long ns5 = ns4;
                        while (true) {
                            Object v4 = p.match;
                            if (v4 != null) {
                                U.putOrderedObject(p, MATCH, obj2);
                                p.item = obj2;
                                p.hash = h;
                                return v4;
                            }
                            ns2 = ns5;
                            if (spins2 > 0) {
                                int h2 = (h << 1) ^ h;
                                int h3 = h2 ^ (h2 >>> 3);
                                int h4 = h3 ^ (h3 << 10);
                                if (h4 == 0) {
                                    v = null;
                                    h = ((int) t2.getId()) | 1024;
                                    t = t2;
                                    spins = spins2;
                                    ns5 = ns2;
                                    exchanger = this;
                                } else if (h4 < 0) {
                                    int spins3 = spins2 - 1;
                                    if ((spins3 & 511) == 0) {
                                        Thread.yield();
                                    }
                                    v = null;
                                    h = h4;
                                    t = t2;
                                    spins = spins3;
                                    ns5 = ns2;
                                    exchanger = this;
                                } else {
                                    v = null;
                                    h = h4;
                                    t = t2;
                                    spins = spins2;
                                    ns5 = ns2;
                                    exchanger = this;
                                }
                            } else if (U.getObjectVolatile(a, j) != p) {
                                v = null;
                                exchanger = this;
                                spins = 1024;
                                t = t2;
                                ns5 = ns2;
                            } else {
                                if (t2.isInterrupted() || m != 0) {
                                    v2 = v4;
                                    spins = spins2;
                                    v3 = null;
                                    exchanger = this;
                                } else {
                                    if (timed) {
                                        long nanoTime = end - System.nanoTime();
                                        ns3 = nanoTime;
                                        if (nanoTime <= 0) {
                                            v2 = v4;
                                            ns2 = ns3;
                                            spins = spins2;
                                            v3 = null;
                                            exchanger = this;
                                        }
                                    } else {
                                        ns3 = ns2;
                                    }
                                    spins = spins2;
                                    exchanger = this;
                                    U.putObject(t2, BLOCKER, exchanger);
                                    p.parked = t2;
                                    if (U.getObjectVolatile(a, j) == p) {
                                        U.park(false, ns3);
                                    }
                                    p.parked = null;
                                    U.putObject(t2, BLOCKER, null);
                                    v = null;
                                    t = t2;
                                    ns5 = ns3;
                                }
                                if (U.getObjectVolatile(a, j) == p) {
                                    t = t2;
                                    v = v3;
                                    if (U.compareAndSwapObject(a, j, p, null)) {
                                        if (m != 0) {
                                            U.compareAndSwapInt(this, BOUND, b, (b + 256) - 1);
                                        }
                                        p.item = v;
                                        p.hash = h;
                                        int i4 = p.index >>> 1;
                                        p.index = i4;
                                        if (Thread.interrupted()) {
                                            return v;
                                        }
                                        if (timed && m == 0 && ns2 <= 0) {
                                            return TIMED_OUT;
                                        }
                                        i2 = i4;
                                    }
                                } else {
                                    v = v3;
                                    t = t2;
                                }
                                ns5 = ns2;
                            }
                            obj2 = v;
                            spins2 = spins;
                            t2 = t;
                        }
                    } else {
                        ns2 = ns4;
                        exchanger = exchanger2;
                        p.item = null;
                    }
                }
                obj = item;
                exchanger2 = exchanger;
                ns4 = ns2;
            } else {
                Object v5 = q.item;
                q.match = obj;
                Thread w = q.parked;
                if (w != null) {
                    U.unpark(w);
                }
                return v5;
            }
        }
    }

    private final Object slotExchange(Object item, boolean timed, long ns) {
        Object v;
        int h;
        long ns2;
        Node p = (Node) this.participant.get();
        Thread t = Thread.currentThread();
        if (t.isInterrupted()) {
            return null;
        }
        while (true) {
            Node q = this.slot;
            int spins = 1;
            if (q != null) {
                if (U.compareAndSwapObject(this, SLOT, q, null)) {
                    Object v2 = q.item;
                    q.match = item;
                    Thread w = q.parked;
                    if (w != null) {
                        U.unpark(w);
                    }
                    return v2;
                } else if (NCPU > 1 && this.bound == 0 && U.compareAndSwapInt(this, BOUND, 0, 256)) {
                    this.arena = new Node[((FULL + 2) << 7)];
                }
            } else if (this.arena != null) {
                return null;
            } else {
                p.item = item;
                if (U.compareAndSwapObject(this, SLOT, null, p)) {
                    int h2 = p.hash;
                    long end = timed ? System.nanoTime() + ns : 0;
                    if (NCPU > 1) {
                        spins = 1024;
                    }
                    int h3 = h2;
                    long ns3 = ns;
                    while (true) {
                        Object obj = p.match;
                        v = obj;
                        if (obj != null) {
                            h = h3;
                            break;
                        } else if (spins > 0) {
                            int h4 = (h3 << 1) ^ h3;
                            int h5 = h4 ^ (h4 >>> 3);
                            h3 = h5 ^ (h5 << 10);
                            if (h3 == 0) {
                                h3 = ((int) t.getId()) | 1024;
                            } else if (h3 < 0) {
                                spins--;
                                if ((spins & 511) == 0) {
                                    Thread.yield();
                                }
                            }
                        } else if (this.slot != p) {
                            spins = 1024;
                        } else {
                            if (t.isInterrupted() || this.arena != null) {
                                ns2 = ns3;
                            } else {
                                if (timed) {
                                    long nanoTime = end - System.nanoTime();
                                    ns3 = nanoTime;
                                    if (nanoTime <= 0) {
                                        ns2 = ns3;
                                    }
                                }
                                U.putObject(t, BLOCKER, this);
                                p.parked = t;
                                if (this.slot == p) {
                                    U.park(false, ns3);
                                }
                                p.parked = null;
                                U.putObject(t, BLOCKER, null);
                            }
                            h = h3;
                            if (U.compareAndSwapObject(this, SLOT, p, null)) {
                                v = (!timed || ns2 > 0 || t.isInterrupted()) ? null : TIMED_OUT;
                            } else {
                                h3 = h;
                                ns3 = ns2;
                            }
                        }
                    }
                    U.putOrderedObject(p, MATCH, null);
                    p.item = null;
                    p.hash = h;
                    return v;
                }
                p.item = null;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r1 != 0) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        if (r5 != java.util.concurrent.Exchanger.NULL_ITEM) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if (r1 == 0) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V exchange(V r7) throws java.lang.InterruptedException {
        /*
            r6 = this;
            if (r7 != 0) goto L_0x0005
            java.lang.Object r0 = java.util.concurrent.Exchanger.NULL_ITEM
            goto L_0x0006
        L_0x0005:
            r0 = r7
        L_0x0006:
            java.util.concurrent.Exchanger$Node[] r1 = r6.arena
            r2 = 0
            r4 = 0
            if (r1 != 0) goto L_0x0014
            java.lang.Object r1 = r6.slotExchange(r0, r4, r2)
            r5 = r1
            if (r1 != 0) goto L_0x0021
        L_0x0014:
            boolean r1 = java.lang.Thread.interrupted()
            if (r1 != 0) goto L_0x0029
            java.lang.Object r1 = r6.arenaExchange(r0, r4, r2)
            r5 = r1
            if (r1 == 0) goto L_0x0029
        L_0x0021:
            java.lang.Object r1 = java.util.concurrent.Exchanger.NULL_ITEM
            if (r5 != r1) goto L_0x0027
            r1 = 0
            goto L_0x0028
        L_0x0027:
            r1 = r5
        L_0x0028:
            return r1
        L_0x0029:
            java.lang.InterruptedException r1 = new java.lang.InterruptedException
            r1.<init>()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.Exchanger.exchange(java.lang.Object):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v8, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r3v9, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        if (r3 != 0) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        if (r5 == java.util.concurrent.Exchanger.TIMED_OUT) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r5 != java.util.concurrent.Exchanger.NULL_ITEM) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0034, code lost:
        throw new java.util.concurrent.TimeoutException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        if (r3 == 0) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V exchange(V r7, long r8, java.util.concurrent.TimeUnit r10) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            r6 = this;
            if (r7 != 0) goto L_0x0005
            java.lang.Object r0 = java.util.concurrent.Exchanger.NULL_ITEM
            goto L_0x0006
        L_0x0005:
            r0 = r7
        L_0x0006:
            long r1 = r10.toNanos(r8)
            java.util.concurrent.Exchanger$Node[] r3 = r6.arena
            r4 = 1
            if (r3 != 0) goto L_0x0016
            java.lang.Object r3 = r6.slotExchange(r0, r4, r1)
            r5 = r3
            if (r3 != 0) goto L_0x0023
        L_0x0016:
            boolean r3 = java.lang.Thread.interrupted()
            if (r3 != 0) goto L_0x0035
            java.lang.Object r3 = r6.arenaExchange(r0, r4, r1)
            r5 = r3
            if (r3 == 0) goto L_0x0035
        L_0x0023:
            java.lang.Object r3 = java.util.concurrent.Exchanger.TIMED_OUT
            if (r5 == r3) goto L_0x002f
            java.lang.Object r3 = java.util.concurrent.Exchanger.NULL_ITEM
            if (r5 != r3) goto L_0x002d
            r3 = 0
            goto L_0x002e
        L_0x002d:
            r3 = r5
        L_0x002e:
            return r3
        L_0x002f:
            java.util.concurrent.TimeoutException r3 = new java.util.concurrent.TimeoutException
            r3.<init>()
            throw r3
        L_0x0035:
            java.lang.InterruptedException r3 = new java.lang.InterruptedException
            r3.<init>()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.Exchanger.exchange(java.lang.Object, long, java.util.concurrent.TimeUnit):java.lang.Object");
    }
}
