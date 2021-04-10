package java.util.concurrent;

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
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import sun.misc.Unsafe;

public class ConcurrentHashMap extends AbstractMap implements ConcurrentMap, Serializable {
    private static final int ABASE;
    private static final int ASHIFT;
    private static final long BASECOUNT;
    private static final long CELLSBUSY;
    private static final long CELLVALUE;
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final long SIZECTL;
    private static final long TRANSFERINDEX;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("segments", Segment[].class), new ObjectStreamField("segmentMask", Integer.TYPE), new ObjectStreamField("segmentShift", Integer.TYPE)};
    private static final long serialVersionUID = 7249069246763182397L;
    private volatile transient long baseCount;
    private volatile transient int cellsBusy;
    private volatile transient CounterCell[] counterCells;
    private transient EntrySetView entrySet;
    private transient KeySetView keySet;
    private volatile transient Node[] nextTable;
    private volatile transient int sizeCtl;
    volatile transient Node[] table;
    private volatile transient int transferIndex;
    private transient ValuesView values;

    static final int spread(int i) {
        return (i ^ (i >>> 16)) & Integer.MAX_VALUE;
    }

    private static final int tableSizeFor(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = i6 | (i6 >>> 16);
        if (i7 < 0) {
            return 1;
        }
        if (i7 >= 1073741824) {
            return 1073741824;
        }
        return 1 + i7;
    }

    static {
        try {
            SIZECTL = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("sizeCtl"));
            TRANSFERINDEX = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("transferIndex"));
            BASECOUNT = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("baseCount"));
            CELLSBUSY = U.objectFieldOffset(ConcurrentHashMap.class.getDeclaredField("cellsBusy"));
            CELLVALUE = U.objectFieldOffset(CounterCell.class.getDeclaredField("value"));
            ABASE = U.arrayBaseOffset(Node[].class);
            int arrayIndexScale = U.arrayIndexScale(Node[].class);
            if (((arrayIndexScale - 1) & arrayIndexScale) == 0) {
                ASHIFT = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);
                return;
            }
            throw new Error("array index scale not a power of two");
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public static class Node implements Map.Entry {
        final int hash;
        final Object key;
        volatile Node next;
        volatile Object val;

        Node(int i, Object obj, Object obj2, Node node) {
            this.hash = i;
            this.key = obj;
            this.val = obj2;
            this.next = node;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            return this.val;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            return this.val.hashCode() ^ this.key.hashCode();
        }

        public final String toString() {
            Helpers.mapEntryToString(this.key, this.val);
            throw null;
        }

        public final boolean equals(Object obj) {
            Map.Entry entry;
            Object key2;
            Object value;
            Object obj2;
            Object obj3;
            return (obj instanceof Map.Entry) && (key2 = (entry = (Map.Entry) obj).getKey()) != null && (value = entry.getValue()) != null && (key2 == (obj2 = this.key) || key2.equals(obj2)) && (value == (obj3 = this.val) || value.equals(obj3));
        }

        /* access modifiers changed from: package-private */
        public Node find(int i, Object obj) {
            Object obj2;
            if (obj == null) {
                return null;
            }
            do {
                if (this.hash == i && ((obj2 = this.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                    return this;
                }
                this = this.next;
            } while (this != null);
            return null;
        }
    }

    static Class comparableClassFor(Object obj) {
        Type[] actualTypeArguments;
        if (!(obj instanceof Comparable)) {
            return null;
        }
        Class cls = obj.getClass();
        if (cls == String.class) {
            return cls;
        }
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces == null) {
            return null;
        }
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType() == Comparable.class && (actualTypeArguments = parameterizedType.getActualTypeArguments()) != null && actualTypeArguments.length == 1 && actualTypeArguments[0] == cls) {
                    return cls;
                }
            }
        }
        return null;
    }

    static int compareComparables(Class cls, Object obj, Object obj2) {
        if (obj2 == null || obj2.getClass() != cls) {
            return 0;
        }
        return ((Comparable) obj).compareTo(obj2);
    }

    static final Node tabAt(Node[] nodeArr, int i) {
        return (Node) U.getObjectVolatile(nodeArr, (((long) i) << ASHIFT) + ((long) ABASE));
    }

    static final boolean casTabAt(Node[] nodeArr, int i, Node node, Node node2) {
        return U.compareAndSwapObject(nodeArr, (((long) i) << ASHIFT) + ((long) ABASE), node, node2);
    }

    static final void setTabAt(Node[] nodeArr, int i, Node node) {
        U.putObjectVolatile(nodeArr, (((long) i) << ASHIFT) + ((long) ABASE), node);
    }

    public ConcurrentHashMap() {
    }

    public ConcurrentHashMap(int i) {
        int i2;
        if (i >= 0) {
            if (i >= 536870912) {
                i2 = 1073741824;
            } else {
                i2 = tableSizeFor(i + (i >>> 1) + 1);
            }
            this.sizeCtl = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public ConcurrentHashMap(int i, float f, int i2) {
        int i3;
        if (f <= 0.0f || i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        }
        long j = (long) (((double) (((float) ((long) (i < i2 ? i2 : i))) / f)) + 1.0d);
        if (j >= 1073741824) {
            i3 = 1073741824;
        } else {
            i3 = tableSizeFor((int) j);
        }
        this.sizeCtl = i3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        long sumCount = sumCount();
        if (sumCount < 0) {
            return 0;
        }
        if (sumCount > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) sumCount;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return sumCount() <= 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
        return r3.val;
     */
    @Override // java.util.AbstractMap, java.util.Map
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(java.lang.Object r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            int r0 = spread(r0)
            java.util.concurrent.ConcurrentHashMap$Node[] r3 = r3.table
            r1 = 0
            if (r3 == 0) goto L_0x004e
            int r2 = r3.length
            if (r2 <= 0) goto L_0x004e
            int r2 = r2 + -1
            r2 = r2 & r0
            java.util.concurrent.ConcurrentHashMap$Node r3 = tabAt(r3, r2)
            if (r3 == 0) goto L_0x004e
            int r2 = r3.hash
            if (r2 != r0) goto L_0x002c
            java.lang.Object r2 = r3.key
            if (r2 == r4) goto L_0x0029
            if (r2 == 0) goto L_0x0037
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0037
        L_0x0029:
            java.lang.Object r3 = r3.val
            return r3
        L_0x002c:
            if (r2 >= 0) goto L_0x0037
            java.util.concurrent.ConcurrentHashMap$Node r3 = r3.find(r0, r4)
            if (r3 == 0) goto L_0x0036
            java.lang.Object r1 = r3.val
        L_0x0036:
            return r1
        L_0x0037:
            java.util.concurrent.ConcurrentHashMap$Node r3 = r3.next
            if (r3 == 0) goto L_0x004e
            int r2 = r3.hash
            if (r2 != r0) goto L_0x0037
            java.lang.Object r2 = r3.key
            if (r2 == r4) goto L_0x004b
            if (r2 == 0) goto L_0x0037
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0037
        L_0x004b:
            java.lang.Object r3 = r3.val
            return r3
        L_0x004e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap
    public boolean containsValue(Object obj) {
        if (obj != null) {
            Node[] nodeArr = this.table;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    Object obj2 = advance.val;
                    if (obj2 == obj) {
                        return true;
                    }
                    if (obj2 != null && obj.equals(obj2)) {
                        return true;
                    }
                }
            }
            return false;
        }
        throw new NullPointerException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        return putVal(obj, obj2, false);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
        r7 = r1.val;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        if (r11 != false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
        r1.val = r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object putVal(java.lang.Object r9, java.lang.Object r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 164
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.putVal(java.lang.Object, java.lang.Object, boolean):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        tryPresize(map.size());
        for (Map.Entry entry : map.entrySet()) {
            putVal(entry.getKey(), entry.getValue(), false);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        return replaceNode(obj, null, null);
    }

    /* access modifiers changed from: package-private */
    public final Object replaceNode(Object obj, Object obj2, Object obj3) {
        int length;
        int i;
        Node tabAt;
        boolean z;
        Object obj4;
        TreeNode findTreeNode;
        Object obj5;
        int spread = spread(obj.hashCode());
        Node[] nodeArr = this.table;
        while (true) {
            if (nodeArr == null || (length = nodeArr.length) == 0 || (tabAt = tabAt(nodeArr, (i = (length - 1) & spread))) == null) {
                break;
            }
            int i2 = tabAt.hash;
            if (i2 == -1) {
                nodeArr = helpTransfer(nodeArr, tabAt);
            } else {
                synchronized (tabAt) {
                    z = true;
                    if (tabAt(nodeArr, i) == tabAt) {
                        if (i2 >= 0) {
                            Node node = null;
                            Node node2 = tabAt;
                            while (true) {
                                if (node2.hash != spread || ((obj5 = node2.key) != obj && (obj5 == null || !obj.equals(obj5)))) {
                                    Node node3 = node2.next;
                                    if (node3 == null) {
                                        break;
                                    }
                                    node = node2;
                                    node2 = node3;
                                }
                            }
                            obj4 = node2.val;
                            if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                if (obj2 != null) {
                                    node2.val = obj2;
                                } else if (node != null) {
                                    node.next = node2.next;
                                } else {
                                    setTabAt(nodeArr, i, node2.next);
                                }
                            }
                        } else if (tabAt instanceof TreeBin) {
                            TreeBin treeBin = (TreeBin) tabAt;
                            TreeNode treeNode = treeBin.root;
                            if (treeNode != null && (findTreeNode = treeNode.findTreeNode(spread, obj, null)) != null) {
                                obj4 = findTreeNode.val;
                                if (obj3 == null || obj3 == obj4 || (obj4 != null && obj3.equals(obj4))) {
                                    if (obj2 != null) {
                                        findTreeNode.val = obj2;
                                    } else if (treeBin.removeTreeNode(findTreeNode)) {
                                        setTabAt(nodeArr, i, untreeify(treeBin.first));
                                    }
                                }
                            }
                        }
                        obj4 = null;
                    }
                    obj4 = null;
                    z = false;
                }
                if (z) {
                    if (obj4 != null) {
                        if (obj2 == null) {
                            addCount(-1, -1);
                        }
                        return obj4;
                    }
                }
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Node node;
        Node[] nodeArr = this.table;
        int i = 0;
        long j = 0;
        while (nodeArr != null && i < nodeArr.length) {
            Node tabAt = tabAt(nodeArr, i);
            if (tabAt == null) {
                i++;
            } else {
                int i2 = tabAt.hash;
                if (i2 == -1) {
                    nodeArr = helpTransfer(nodeArr, tabAt);
                    i = 0;
                } else {
                    synchronized (tabAt) {
                        if (tabAt(nodeArr, i) == tabAt) {
                            if (i2 >= 0) {
                                node = tabAt;
                            } else {
                                node = tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null;
                            }
                            while (node != null) {
                                j--;
                                node = node.next;
                            }
                            setTabAt(nodeArr, i, null);
                            i++;
                        }
                    }
                }
            }
        }
        if (j != 0) {
            addCount(j, -1);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        KeySetView keySetView = this.keySet;
        if (keySetView != null) {
            return keySetView;
        }
        KeySetView keySetView2 = new KeySetView(this, null);
        this.keySet = keySetView2;
        return keySetView2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection values() {
        ValuesView valuesView = this.values;
        if (valuesView != null) {
            return valuesView;
        }
        ValuesView valuesView2 = new ValuesView(this);
        this.values = valuesView2;
        return valuesView2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        EntrySetView entrySetView = this.entrySet;
        if (entrySetView != null) {
            return entrySetView;
        }
        EntrySetView entrySetView2 = new EntrySetView(this);
        this.entrySet = entrySetView2;
        return entrySetView2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        Node[] nodeArr = this.table;
        int i = 0;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node advance = traverser.advance();
                if (advance == null) {
                    break;
                }
                i += advance.val.hashCode() ^ advance.key.hashCode();
            }
        }
        return i;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        Node advance = traverser.advance();
        if (advance != null) {
            while (true) {
                Object obj = advance.key;
                Object obj2 = advance.val;
                if (obj == this) {
                    obj = "(this Map)";
                }
                sb.append(obj);
                sb.append('=');
                if (obj2 == this) {
                    obj2 = "(this Map)";
                }
                sb.append(obj2);
                advance = traverser.advance();
                if (advance == null) {
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
    public boolean equals(Object obj) {
        Object value;
        Object obj2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        Node[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        while (true) {
            Node advance = traverser.advance();
            if (advance != null) {
                Object obj3 = advance.val;
                Object obj4 = map.get(advance.key);
                if (obj4 == null || (obj4 != obj3 && !obj4.equals(obj3))) {
                    return false;
                }
            } else {
                for (Map.Entry entry : map.entrySet()) {
                    Object key = entry.getKey();
                    if (key == null || (value = entry.getValue()) == null || (obj2 = get(key)) == null || (value != obj2 && !value.equals(obj2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static class Segment extends ReentrantLock implements Serializable {
        private static final long serialVersionUID = 2249069246763182397L;
        final float loadFactor;

        Segment(float f) {
            this.loadFactor = f;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        for (int i = 1; i < 16; i <<= 1) {
        }
        Segment[] segmentArr = new Segment[16];
        for (int i2 = 0; i2 < segmentArr.length; i2++) {
            segmentArr[i2] = new Segment(0.75f);
        }
        objectOutputStream.putFields();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.sizeCtl = -1;
        objectInputStream.defaultReadObject();
        throw null;
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public Object putIfAbsent(Object obj, Object obj2) {
        return putVal(obj, obj2, true);
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object obj, Object obj2) {
        if (obj != null) {
            return (obj2 == null || replaceNode(obj, null, obj2) == null) ? false : true;
        }
        throw new NullPointerException();
    }

    public long mappingCount() {
        long sumCount = sumCount();
        if (sumCount < 0) {
            return 0;
        }
        return sumCount;
    }

    /* access modifiers changed from: package-private */
    public static final class ForwardingNode extends Node {
        final Node[] nextTable;

        ForwardingNode(Node[] nodeArr) {
            super(-1, null, null, null);
            this.nextTable = nodeArr;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
            if ((r3 instanceof java.util.concurrent.ConcurrentHashMap.ForwardingNode) == false) goto L_0x0030;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
            r3 = ((java.util.concurrent.ConcurrentHashMap.ForwardingNode) r3).nextTable;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
            return r3.find(r4, r5);
         */
        @Override // java.util.concurrent.ConcurrentHashMap.Node
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.concurrent.ConcurrentHashMap.Node find(int r4, java.lang.Object r5) {
            /*
                r3 = this;
                java.util.concurrent.ConcurrentHashMap$Node[] r3 = r3.nextTable
            L_0x0002:
                r0 = 0
                if (r5 == 0) goto L_0x0039
                if (r3 == 0) goto L_0x0039
                int r1 = r3.length
                if (r1 == 0) goto L_0x0039
                int r1 = r1 + -1
                r1 = r1 & r4
                java.util.concurrent.ConcurrentHashMap$Node r3 = java.util.concurrent.ConcurrentHashMap.tabAt(r3, r1)
                if (r3 != 0) goto L_0x0014
                goto L_0x0039
            L_0x0014:
                int r1 = r3.hash
                if (r1 != r4) goto L_0x0025
                java.lang.Object r2 = r3.key
                if (r2 == r5) goto L_0x0024
                if (r2 == 0) goto L_0x0025
                boolean r2 = r5.equals(r2)
                if (r2 == 0) goto L_0x0025
            L_0x0024:
                return r3
            L_0x0025:
                if (r1 >= 0) goto L_0x0035
                boolean r0 = r3 instanceof java.util.concurrent.ConcurrentHashMap.ForwardingNode
                if (r0 == 0) goto L_0x0030
                java.util.concurrent.ConcurrentHashMap$ForwardingNode r3 = (java.util.concurrent.ConcurrentHashMap.ForwardingNode) r3
                java.util.concurrent.ConcurrentHashMap$Node[] r3 = r3.nextTable
                goto L_0x0002
            L_0x0030:
                java.util.concurrent.ConcurrentHashMap$Node r3 = r3.find(r4, r5)
                return r3
            L_0x0035:
                java.util.concurrent.ConcurrentHashMap$Node r3 = r3.next
                if (r3 != 0) goto L_0x0014
            L_0x0039:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.ForwardingNode.find(int, java.lang.Object):java.util.concurrent.ConcurrentHashMap$Node");
        }
    }

    static final int resizeStamp(int i) {
        return Integer.numberOfLeadingZeros(i) | 32768;
    }

    private final Node[] initTable() {
        while (true) {
            Node[] nodeArr = this.table;
            if (nodeArr != null && nodeArr.length != 0) {
                return nodeArr;
            }
            int i = this.sizeCtl;
            if (i < 0) {
                Thread.yield();
            } else if (U.compareAndSwapInt(this, SIZECTL, i, -1)) {
                try {
                    Node[] nodeArr2 = this.table;
                    if (nodeArr2 == null || nodeArr2.length == 0) {
                        int i2 = i > 0 ? i : 16;
                        Node[] nodeArr3 = new Node[i2];
                        this.table = nodeArr3;
                        i = i2 - (i2 >>> 2);
                        nodeArr2 = nodeArr3;
                    }
                    return nodeArr2;
                } finally {
                    this.sizeCtl = i;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
        if (r1.compareAndSwapLong(r11, r3, r5, r9) == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void addCount(long r12, int r14) {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.addCount(long, int):void");
    }

    /* access modifiers changed from: package-private */
    public final Node[] helpTransfer(Node[] nodeArr, Node node) {
        Node[] nodeArr2;
        int i;
        if (nodeArr == null || !(node instanceof ForwardingNode) || (nodeArr2 = ((ForwardingNode) node).nextTable) == null) {
            return this.table;
        }
        int resizeStamp = resizeStamp(nodeArr.length);
        while (true) {
            if (nodeArr2 == this.nextTable && this.table == nodeArr && (i = this.sizeCtl) < 0 && (i >>> 16) == resizeStamp && i != resizeStamp + 1 && i != 65535 + resizeStamp && this.transferIndex > 0) {
                if (U.compareAndSwapInt(this, SIZECTL, i, i + 1)) {
                    transfer(nodeArr, nodeArr2);
                    break;
                }
            } else {
                break;
            }
        }
        return nodeArr2;
    }

    private final void tryPresize(int i) {
        int length;
        int tableSizeFor = i >= 536870912 ? 1073741824 : tableSizeFor(i + (i >>> 1) + 1);
        while (true) {
            int i2 = this.sizeCtl;
            if (i2 >= 0) {
                Node[] nodeArr = this.table;
                if (nodeArr == null || (length = nodeArr.length) == 0) {
                    int i3 = i2 > tableSizeFor ? i2 : tableSizeFor;
                    if (U.compareAndSwapInt(this, SIZECTL, i2, -1)) {
                        try {
                            if (this.table == nodeArr) {
                                this.table = new Node[i3];
                                i2 = i3 - (i3 >>> 2);
                            }
                        } finally {
                            this.sizeCtl = i2;
                        }
                    }
                } else if (tableSizeFor > i2 && length < 1073741824) {
                    if (nodeArr == this.table) {
                        if (U.compareAndSwapInt(this, SIZECTL, i2, (resizeStamp(length) << 16) + 2)) {
                            transfer(nodeArr, null);
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

    private final void transfer(Node[] nodeArr, Node[] nodeArr2) {
        Node[] nodeArr3;
        int i;
        int i2;
        boolean z;
        ConcurrentHashMap concurrentHashMap;
        ForwardingNode forwardingNode;
        char c;
        Node[] nodeArr4;
        int i3;
        Node node;
        Node node2;
        Node node3;
        int i4;
        ConcurrentHashMap concurrentHashMap2 = this;
        Node[] nodeArr5 = nodeArr;
        int length = nodeArr5.length;
        int i5 = NCPU;
        boolean z2 = true;
        int i6 = i5 > 1 ? (length >>> 3) / i5 : length;
        char c2 = 16;
        int i7 = i6 < 16 ? 16 : i6;
        if (nodeArr2 == null) {
            try {
                Node[] nodeArr6 = new Node[(length << 1)];
                concurrentHashMap2.nextTable = nodeArr6;
                concurrentHashMap2.transferIndex = length;
                nodeArr3 = nodeArr6;
            } catch (Throwable unused) {
                concurrentHashMap2.sizeCtl = Integer.MAX_VALUE;
                return;
            }
        } else {
            nodeArr3 = nodeArr2;
        }
        int length2 = nodeArr3.length;
        ForwardingNode forwardingNode2 = new ForwardingNode(nodeArr3);
        boolean z3 = true;
        int i8 = 0;
        int i9 = 0;
        boolean z4 = false;
        while (true) {
            if (z3) {
                int i10 = i9 - 1;
                if (i10 >= i8 || z4) {
                    i8 = i8;
                    i9 = i10;
                } else {
                    int i11 = concurrentHashMap2.transferIndex;
                    if (i11 <= 0) {
                        i9 = -1;
                    } else {
                        Unsafe unsafe = U;
                        long j = TRANSFERINDEX;
                        int i12 = i11 > i7 ? i11 - i7 : 0;
                        if (unsafe.compareAndSwapInt(this, j, i11, i12)) {
                            i9 = i11 - 1;
                            i8 = i12;
                        } else {
                            i8 = i8;
                            i9 = i10;
                        }
                    }
                }
                z3 = false;
            } else {
                Node node4 = null;
                if (i9 < 0 || i9 >= length || (i3 = i9 + length) >= length2) {
                    nodeArr4 = nodeArr5;
                    i2 = i7;
                    i = length2;
                    forwardingNode = forwardingNode2;
                    if (z4) {
                        this.nextTable = null;
                        this.table = nodeArr3;
                        this.sizeCtl = (length << 1) - (length >>> 1);
                        return;
                    }
                    z = true;
                    concurrentHashMap = this;
                    Unsafe unsafe2 = U;
                    long j2 = SIZECTL;
                    int i13 = concurrentHashMap.sizeCtl;
                    if (unsafe2.compareAndSwapInt(this, j2, i13, i13 - 1)) {
                        c = 16;
                        if (i13 - 2 == (resizeStamp(length) << 16)) {
                            i9 = length;
                            z3 = true;
                            z4 = true;
                        } else {
                            return;
                        }
                    } else {
                        c = 16;
                        i9 = i9;
                    }
                } else {
                    Node tabAt = tabAt(nodeArr5, i9);
                    if (tabAt == null) {
                        z3 = casTabAt(nodeArr5, i9, null, forwardingNode2);
                        c = c2;
                        i2 = i7;
                        i = length2;
                        z = z2;
                        concurrentHashMap = concurrentHashMap2;
                        nodeArr4 = nodeArr5;
                    } else {
                        int i14 = tabAt.hash;
                        if (i14 == -1) {
                            z3 = z2;
                            c = c2;
                            i2 = i7;
                            i = length2;
                            concurrentHashMap = concurrentHashMap2;
                            nodeArr4 = nodeArr5;
                            z = z3;
                        } else {
                            synchronized (tabAt) {
                                if (tabAt(nodeArr5, i9) == tabAt) {
                                    if (i14 >= 0) {
                                        int i15 = i14 & length;
                                        Node node5 = tabAt;
                                        for (Node node6 = tabAt.next; node6 != null; node6 = node6.next) {
                                            int i16 = node6.hash & length;
                                            if (i16 != i15) {
                                                node5 = node6;
                                                i15 = i16;
                                            }
                                        }
                                        if (i15 == 0) {
                                            node3 = null;
                                            node4 = node5;
                                        } else {
                                            node3 = node5;
                                        }
                                        Node node7 = node3;
                                        Node node8 = tabAt;
                                        while (node8 != node5) {
                                            int i17 = node8.hash;
                                            Object obj = node8.key;
                                            Object obj2 = node8.val;
                                            if ((i17 & length) == 0) {
                                                i4 = length2;
                                                node4 = new Node(i17, obj, obj2, node4);
                                            } else {
                                                i4 = length2;
                                                node7 = new Node(i17, obj, obj2, node7);
                                            }
                                            node8 = node8.next;
                                            i7 = i7;
                                            length2 = i4;
                                        }
                                        i2 = i7;
                                        i = length2;
                                        setTabAt(nodeArr3, i9, node4);
                                        setTabAt(nodeArr3, i3, node7);
                                        setTabAt(nodeArr5, i9, forwardingNode2);
                                        nodeArr4 = nodeArr5;
                                        forwardingNode = forwardingNode2;
                                    } else {
                                        i2 = i7;
                                        i = length2;
                                        if (tabAt instanceof TreeBin) {
                                            TreeBin treeBin = (TreeBin) tabAt;
                                            Node node9 = treeBin.first;
                                            TreeNode treeNode = null;
                                            TreeNode treeNode2 = null;
                                            TreeNode treeNode3 = null;
                                            TreeNode treeNode4 = null;
                                            int i18 = 0;
                                            int i19 = 0;
                                            while (node9 != null) {
                                                int i20 = node9.hash;
                                                TreeNode treeNode5 = new TreeNode(i20, node9.key, node9.val, null, null);
                                                if ((i20 & length) == 0) {
                                                    treeNode5.prev = treeNode4;
                                                    if (treeNode4 == null) {
                                                        treeNode = treeNode5;
                                                    } else {
                                                        treeNode4.next = treeNode5;
                                                    }
                                                    i18++;
                                                    treeNode4 = treeNode5;
                                                } else {
                                                    treeNode5.prev = treeNode3;
                                                    if (treeNode3 == null) {
                                                        treeNode2 = treeNode5;
                                                    } else {
                                                        treeNode3.next = treeNode5;
                                                    }
                                                    i19++;
                                                    treeNode3 = treeNode5;
                                                }
                                                node9 = node9.next;
                                                treeBin = treeBin;
                                                forwardingNode2 = forwardingNode2;
                                            }
                                            if (i18 <= 6) {
                                                node = untreeify(treeNode);
                                            } else {
                                                node = i19 != 0 ? new TreeBin(treeNode) : treeBin;
                                            }
                                            if (i19 <= 6) {
                                                node2 = untreeify(treeNode2);
                                            } else {
                                                node2 = i18 != 0 ? new TreeBin(treeNode2) : treeBin;
                                            }
                                            setTabAt(nodeArr3, i9, node);
                                            setTabAt(nodeArr3, i3, node2);
                                            nodeArr4 = nodeArr;
                                            forwardingNode = forwardingNode2;
                                            setTabAt(nodeArr4, i9, forwardingNode);
                                        } else {
                                            nodeArr4 = nodeArr5;
                                        }
                                    }
                                    z3 = true;
                                } else {
                                    nodeArr4 = nodeArr5;
                                    i2 = i7;
                                    i = length2;
                                }
                                forwardingNode = forwardingNode2;
                            }
                            c = 16;
                            z = true;
                            concurrentHashMap = this;
                        }
                    }
                    forwardingNode = forwardingNode2;
                }
                forwardingNode2 = forwardingNode;
                i8 = i8;
                i7 = i2;
                length2 = i;
                nodeArr5 = nodeArr4;
                concurrentHashMap2 = concurrentHashMap;
                z2 = z;
                c2 = c;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CounterCell {
        volatile long value;

        CounterCell(long j) {
            this.value = j;
        }
    }

    /* access modifiers changed from: package-private */
    public final long sumCount() {
        CounterCell[] counterCellArr = this.counterCells;
        long j = this.baseCount;
        if (counterCellArr != null) {
            for (CounterCell counterCell : counterCellArr) {
                if (counterCell != null) {
                    j += counterCell.value;
                }
            }
        }
        return j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009d, code lost:
        if (r24.counterCells != r7) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009f, code lost:
        r1 = new java.util.concurrent.ConcurrentHashMap.CounterCell[(r8 << 1)];
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a4, code lost:
        if (r2 >= r8) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a6, code lost:
        r1[r2] = r7[r2];
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ad, code lost:
        r24.counterCells = r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0101 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x001c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void fullAddCount(long r25, boolean r27) {
        /*
        // Method dump skipped, instructions count: 258
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.fullAddCount(long, boolean):void");
    }

    private final void treeifyBin(Node[] nodeArr, int i) {
        if (nodeArr != null) {
            int length = nodeArr.length;
            if (length < 64) {
                tryPresize(length << 1);
                return;
            }
            Node tabAt = tabAt(nodeArr, i);
            if (tabAt != null && tabAt.hash >= 0) {
                synchronized (tabAt) {
                    if (tabAt(nodeArr, i) == tabAt) {
                        TreeNode treeNode = null;
                        TreeNode treeNode2 = null;
                        Node node = tabAt;
                        while (node != null) {
                            TreeNode treeNode3 = new TreeNode(node.hash, node.key, node.val, null, null);
                            treeNode3.prev = treeNode2;
                            if (treeNode2 == null) {
                                treeNode = treeNode3;
                            } else {
                                treeNode2.next = treeNode3;
                            }
                            node = node.next;
                            treeNode2 = treeNode3;
                        }
                        setTabAt(nodeArr, i, new TreeBin(treeNode));
                    }
                }
            }
        }
    }

    static Node untreeify(Node node) {
        Node node2 = null;
        Node node3 = null;
        while (node != null) {
            Node node4 = new Node(node.hash, node.key, node.val, null);
            if (node3 == null) {
                node2 = node4;
            } else {
                node3.next = node4;
            }
            node = node.next;
            node3 = node4;
        }
        return node2;
    }

    /* access modifiers changed from: package-private */
    public static final class TreeNode extends Node {
        TreeNode left;
        TreeNode parent;
        TreeNode prev;
        boolean red;
        TreeNode right;

        TreeNode(int i, Object obj, Object obj2, Node node, TreeNode treeNode) {
            super(i, obj, obj2, node);
            this.parent = treeNode;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentHashMap.Node
        public Node find(int i, Object obj) {
            return findTreeNode(i, obj, null);
        }

        /* access modifiers changed from: package-private */
        public final TreeNode findTreeNode(int i, Object obj, Class cls) {
            int compareComparables;
            if (obj == null) {
                return null;
            }
            do {
                TreeNode treeNode = this.left;
                TreeNode treeNode2 = this.right;
                int i2 = this.hash;
                if (i2 <= i) {
                    if (i2 >= i) {
                        Object obj2 = this.key;
                        if (obj2 == obj || (obj2 != null && obj.equals(obj2))) {
                            return this;
                        }
                        if (treeNode != null) {
                            if (treeNode2 != null) {
                                if ((cls == null && (cls = ConcurrentHashMap.comparableClassFor(obj)) == null) || (compareComparables = ConcurrentHashMap.compareComparables(cls, obj, obj2)) == 0) {
                                    TreeNode findTreeNode = treeNode2.findTreeNode(i, obj, cls);
                                    if (findTreeNode != null) {
                                        return findTreeNode;
                                    }
                                } else if (compareComparables >= 0) {
                                    treeNode = treeNode2;
                                }
                            }
                        }
                    }
                    this = treeNode2;
                    continue;
                }
                this = treeNode;
                continue;
            } while (this != null);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TreeBin extends Node {
        private static final long LOCKSTATE;
        private static final Unsafe U = Unsafe.getUnsafe();
        volatile TreeNode first;
        volatile int lockState;
        TreeNode root;
        volatile Thread waiter;

        static int tieBreakOrder(Object obj, Object obj2) {
            int compareTo;
            if (obj != null && obj2 != null && (compareTo = obj.getClass().getName().compareTo(obj2.getClass().getName())) != 0) {
                return compareTo;
            }
            return System.identityHashCode(obj) <= System.identityHashCode(obj2) ? -1 : 1;
        }

        TreeBin(TreeNode treeNode) {
            super(-2, null, null, null);
            int tieBreakOrder;
            int compareComparables;
            this.first = treeNode;
            TreeNode treeNode2 = null;
            while (treeNode != null) {
                TreeNode treeNode3 = (TreeNode) treeNode.next;
                treeNode.right = null;
                treeNode.left = null;
                if (treeNode2 == null) {
                    treeNode.parent = null;
                    treeNode.red = false;
                } else {
                    Object obj = treeNode.key;
                    int i = treeNode.hash;
                    Class cls = null;
                    TreeNode treeNode4 = treeNode2;
                    while (true) {
                        Object obj2 = treeNode4.key;
                        int i2 = treeNode4.hash;
                        tieBreakOrder = i2 > i ? -1 : i2 < i ? 1 : ((cls == null && (cls = ConcurrentHashMap.comparableClassFor(obj)) == null) || (compareComparables = ConcurrentHashMap.compareComparables(cls, obj, obj2)) == 0) ? tieBreakOrder(obj, obj2) : compareComparables;
                        TreeNode treeNode5 = tieBreakOrder <= 0 ? treeNode4.left : treeNode4.right;
                        if (treeNode5 == null) {
                            break;
                        }
                        treeNode4 = treeNode5;
                    }
                    treeNode.parent = treeNode4;
                    if (tieBreakOrder <= 0) {
                        treeNode4.left = treeNode;
                    } else {
                        treeNode4.right = treeNode;
                    }
                    treeNode = balanceInsertion(treeNode2, treeNode);
                }
                treeNode2 = treeNode;
                treeNode = treeNode3;
            }
            this.root = treeNode2;
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
            boolean z = false;
            while (true) {
                int i = this.lockState;
                if ((i & -3) == 0) {
                    if (U.compareAndSwapInt(this, LOCKSTATE, i, 1)) {
                        break;
                    }
                } else if ((i & 2) == 0) {
                    if (U.compareAndSwapInt(this, LOCKSTATE, i, i | 2)) {
                        z = true;
                        this.waiter = Thread.currentThread();
                    }
                } else if (z) {
                    LockSupport.park(this);
                }
            }
            if (z) {
                this.waiter = null;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.ConcurrentHashMap.Node
        public final Node find(int i, Object obj) {
            Thread thread;
            Object obj2;
            TreeNode treeNode = null;
            if (obj != null) {
                Node node = this.first;
                while (node != null) {
                    int i2 = this.lockState;
                    if ((i2 & 3) != 0) {
                        if (node.hash == i && ((obj2 = node.key) == obj || (obj2 != null && obj.equals(obj2)))) {
                            return node;
                        }
                        node = node.next;
                    } else if (U.compareAndSwapInt(this, LOCKSTATE, i2, i2 + 4)) {
                        try {
                            TreeNode treeNode2 = this.root;
                            if (treeNode2 != null) {
                                treeNode = treeNode2.findTreeNode(i, obj, null);
                            }
                            return treeNode;
                        } finally {
                            if (U.getAndAddInt(this, LOCKSTATE, -4) == 6 && (thread = this.waiter) != null) {
                                LockSupport.unpark(thread);
                            }
                        }
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x005d, code lost:
            return r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x009f, code lost:
            return null;
         */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x0067  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x006a  */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x00a5 A[LOOP:0: B:1:0x000a->B:53:0x00a5, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x006e A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.concurrent.ConcurrentHashMap.TreeNode putTreeVal(int r15, java.lang.Object r16, java.lang.Object r17) {
            /*
            // Method dump skipped, instructions count: 169
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.TreeBin.putTreeVal(int, java.lang.Object, java.lang.Object):java.util.concurrent.ConcurrentHashMap$TreeNode");
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public final boolean removeTreeNode(TreeNode treeNode) {
            TreeNode treeNode2;
            TreeNode treeNode3;
            TreeNode treeNode4 = (TreeNode) treeNode.next;
            TreeNode treeNode5 = treeNode.prev;
            if (treeNode5 == null) {
                this.first = treeNode4;
            } else {
                treeNode5.next = treeNode4;
            }
            if (treeNode4 != null) {
                treeNode4.prev = treeNode5;
            }
            if (this.first == null) {
                this.root = null;
                return true;
            }
            TreeNode treeNode6 = this.root;
            if (treeNode6 == null || treeNode6.right == null || (treeNode2 = treeNode6.left) == null || treeNode2.left == null) {
                return true;
            }
            lockRoot();
            try {
                TreeNode treeNode7 = treeNode.left;
                TreeNode treeNode8 = treeNode.right;
                if (treeNode7 != null && treeNode8 != null) {
                    TreeNode treeNode9 = treeNode8;
                    while (true) {
                        TreeNode treeNode10 = treeNode9.left;
                        if (treeNode10 == null) {
                            break;
                        }
                        treeNode9 = treeNode10;
                    }
                    boolean z = treeNode9.red;
                    treeNode9.red = treeNode.red;
                    treeNode.red = z;
                    TreeNode treeNode11 = treeNode9.right;
                    TreeNode treeNode12 = treeNode.parent;
                    if (treeNode9 == treeNode8) {
                        treeNode.parent = treeNode9;
                        treeNode9.right = treeNode;
                    } else {
                        TreeNode treeNode13 = treeNode9.parent;
                        treeNode.parent = treeNode13;
                        if (treeNode13 != null) {
                            if (treeNode9 == treeNode13.left) {
                                treeNode13.left = treeNode;
                            } else {
                                treeNode13.right = treeNode;
                            }
                        }
                        treeNode9.right = treeNode8;
                        if (treeNode8 != null) {
                            treeNode8.parent = treeNode9;
                        }
                    }
                    treeNode.left = null;
                    treeNode.right = treeNode11;
                    if (treeNode11 != null) {
                        treeNode11.parent = treeNode;
                    }
                    treeNode9.left = treeNode7;
                    if (treeNode7 != null) {
                        treeNode7.parent = treeNode9;
                    }
                    treeNode9.parent = treeNode12;
                    if (treeNode12 == null) {
                        treeNode6 = treeNode9;
                    } else if (treeNode == treeNode12.left) {
                        treeNode12.left = treeNode9;
                    } else {
                        treeNode12.right = treeNode9;
                    }
                    if (treeNode11 == null) {
                        treeNode11 = treeNode;
                    }
                    treeNode7 = treeNode11;
                } else if (treeNode7 == null) {
                    treeNode7 = treeNode8 != null ? treeNode8 : treeNode;
                }
                if (treeNode7 != treeNode) {
                    TreeNode treeNode14 = treeNode.parent;
                    treeNode7.parent = treeNode14;
                    if (treeNode14 == null) {
                        treeNode6 = treeNode7;
                    } else if (treeNode == treeNode14.left) {
                        treeNode14.left = treeNode7;
                    } else {
                        treeNode14.right = treeNode7;
                    }
                    treeNode.parent = null;
                    treeNode.right = null;
                    treeNode.left = null;
                }
                if (!treeNode.red) {
                    treeNode6 = balanceDeletion(treeNode6, treeNode7);
                }
                this.root = treeNode6;
                if (treeNode == treeNode7 && (treeNode3 = treeNode.parent) != null) {
                    if (treeNode == treeNode3.left) {
                        treeNode3.left = null;
                    } else if (treeNode == treeNode3.right) {
                        treeNode3.right = null;
                    }
                    treeNode.parent = null;
                }
                unlockRoot();
                return false;
            } catch (Throwable th) {
                unlockRoot();
                throw th;
            }
        }

        static TreeNode rotateLeft(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (!(treeNode2 == null || (treeNode3 = treeNode2.right) == null)) {
                TreeNode treeNode4 = treeNode3.left;
                treeNode2.right = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.left == treeNode2) {
                    treeNode5.left = treeNode3;
                } else {
                    treeNode5.right = treeNode3;
                }
                treeNode3.left = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static TreeNode rotateRight(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            if (!(treeNode2 == null || (treeNode3 = treeNode2.left) == null)) {
                TreeNode treeNode4 = treeNode3.right;
                treeNode2.left = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.right == treeNode2) {
                    treeNode5.right = treeNode3;
                } else {
                    treeNode5.left = treeNode3;
                }
                treeNode3.right = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        static TreeNode balanceInsertion(TreeNode treeNode, TreeNode treeNode2) {
            TreeNode treeNode3;
            TreeNode treeNode4;
            TreeNode treeNode5;
            treeNode2.red = true;
            while (true) {
                TreeNode treeNode6 = treeNode2.parent;
                if (treeNode6 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                } else if (!treeNode6.red || (treeNode3 = treeNode6.parent) == null) {
                    return treeNode;
                } else {
                    TreeNode treeNode7 = treeNode3.left;
                    if (treeNode6 == treeNode7) {
                        TreeNode treeNode8 = treeNode3.right;
                        if (treeNode8 == null || !treeNode8.red) {
                            if (treeNode2 == treeNode6.right) {
                                treeNode = rotateLeft(treeNode, treeNode6);
                                treeNode5 = treeNode6.parent;
                                treeNode3 = treeNode5 == null ? null : treeNode5.parent;
                            } else {
                                treeNode6 = treeNode2;
                                treeNode5 = treeNode6;
                            }
                            if (treeNode5 != null) {
                                treeNode5.red = false;
                                if (treeNode3 != null) {
                                    treeNode3.red = true;
                                    treeNode = rotateRight(treeNode, treeNode3);
                                }
                            }
                            treeNode2 = treeNode6;
                        } else {
                            treeNode8.red = false;
                            treeNode6.red = false;
                            treeNode3.red = true;
                        }
                    } else if (treeNode7 == null || !treeNode7.red) {
                        if (treeNode2 == treeNode6.left) {
                            treeNode = rotateRight(treeNode, treeNode6);
                            treeNode4 = treeNode6.parent;
                            treeNode3 = treeNode4 == null ? null : treeNode4.parent;
                        } else {
                            treeNode6 = treeNode2;
                            treeNode4 = treeNode6;
                        }
                        if (treeNode4 != null) {
                            treeNode4.red = false;
                            if (treeNode3 != null) {
                                treeNode3.red = true;
                                treeNode = rotateLeft(treeNode, treeNode3);
                            }
                        }
                        treeNode2 = treeNode6;
                    } else {
                        treeNode7.red = false;
                        treeNode6.red = false;
                        treeNode3.red = true;
                    }
                    treeNode2 = treeNode3;
                }
            }
            return treeNode;
        }

        static TreeNode balanceDeletion(TreeNode treeNode, TreeNode treeNode2) {
            boolean z;
            boolean z2;
            while (treeNode2 != null && treeNode2 != treeNode) {
                TreeNode treeNode3 = treeNode2.parent;
                if (treeNode3 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                } else if (treeNode2.red) {
                    treeNode2.red = false;
                    return treeNode;
                } else {
                    TreeNode treeNode4 = treeNode3.left;
                    TreeNode treeNode5 = null;
                    if (treeNode4 == treeNode2) {
                        TreeNode treeNode6 = treeNode3.right;
                        if (treeNode6 != null && treeNode6.red) {
                            treeNode6.red = false;
                            treeNode3.red = true;
                            treeNode = rotateLeft(treeNode, treeNode3);
                            treeNode3 = treeNode2.parent;
                            treeNode6 = treeNode3 == null ? null : treeNode3.right;
                        }
                        if (treeNode6 != null) {
                            TreeNode treeNode7 = treeNode6.left;
                            TreeNode treeNode8 = treeNode6.right;
                            if ((treeNode8 == null || !treeNode8.red) && (treeNode7 == null || !treeNode7.red)) {
                                treeNode6.red = true;
                            } else {
                                if (treeNode8 == null || !treeNode8.red) {
                                    if (treeNode7 != null) {
                                        treeNode7.red = false;
                                    }
                                    treeNode6.red = true;
                                    treeNode = rotateRight(treeNode, treeNode6);
                                    treeNode3 = treeNode2.parent;
                                    if (treeNode3 != null) {
                                        treeNode5 = treeNode3.right;
                                    }
                                    treeNode6 = treeNode5;
                                }
                                if (treeNode6 != null) {
                                    if (treeNode3 == null) {
                                        z2 = false;
                                    } else {
                                        z2 = treeNode3.red;
                                    }
                                    treeNode6.red = z2;
                                    TreeNode treeNode9 = treeNode6.right;
                                    if (treeNode9 != null) {
                                        treeNode9.red = false;
                                    }
                                }
                                if (treeNode3 != null) {
                                    treeNode3.red = false;
                                    treeNode = rotateLeft(treeNode, treeNode3);
                                }
                            }
                        }
                        treeNode2 = treeNode3;
                    } else {
                        if (treeNode4 != null && treeNode4.red) {
                            treeNode4.red = false;
                            treeNode3.red = true;
                            treeNode = rotateRight(treeNode, treeNode3);
                            treeNode3 = treeNode2.parent;
                            treeNode4 = treeNode3 == null ? null : treeNode3.left;
                        }
                        if (treeNode4 != null) {
                            TreeNode treeNode10 = treeNode4.left;
                            TreeNode treeNode11 = treeNode4.right;
                            if ((treeNode10 == null || !treeNode10.red) && (treeNode11 == null || !treeNode11.red)) {
                                treeNode4.red = true;
                            } else {
                                if (treeNode10 == null || !treeNode10.red) {
                                    if (treeNode11 != null) {
                                        treeNode11.red = false;
                                    }
                                    treeNode4.red = true;
                                    treeNode = rotateLeft(treeNode, treeNode4);
                                    treeNode3 = treeNode2.parent;
                                    if (treeNode3 != null) {
                                        treeNode5 = treeNode3.left;
                                    }
                                    treeNode4 = treeNode5;
                                }
                                if (treeNode4 != null) {
                                    if (treeNode3 == null) {
                                        z = false;
                                    } else {
                                        z = treeNode3.red;
                                    }
                                    treeNode4.red = z;
                                    TreeNode treeNode12 = treeNode4.left;
                                    if (treeNode12 != null) {
                                        treeNode12.red = false;
                                    }
                                }
                                if (treeNode3 != null) {
                                    treeNode3.red = false;
                                    treeNode = rotateRight(treeNode, treeNode3);
                                }
                            }
                        }
                        treeNode2 = treeNode3;
                    }
                    treeNode2 = treeNode;
                }
            }
            return treeNode;
        }

        static {
            try {
                LOCKSTATE = U.objectFieldOffset(TreeBin.class.getDeclaredField("lockState"));
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TableStack {
        int index;
        int length;
        TableStack next;
        Node[] tab;

        TableStack() {
        }
    }

    /* access modifiers changed from: package-private */
    public static class Traverser {
        int baseIndex;
        int baseLimit;
        final int baseSize;
        int index;
        Node next = null;
        TableStack spare;
        TableStack stack;
        Node[] tab;

        Traverser(Node[] nodeArr, int i, int i2, int i3) {
            this.tab = nodeArr;
            this.baseSize = i;
            this.index = i2;
            this.baseIndex = i2;
            this.baseLimit = i3;
        }

        /* access modifiers changed from: package-private */
        public final Node advance() {
            Node[] nodeArr;
            int length;
            int i;
            Node node = this.next;
            if (node != null) {
                node = node.next;
            }
            while (node == null) {
                if (this.baseIndex >= this.baseLimit || (nodeArr = this.tab) == null || (length = nodeArr.length) <= (i = this.index) || i < 0) {
                    this.next = null;
                    return null;
                }
                Node tabAt = ConcurrentHashMap.tabAt(nodeArr, i);
                if (tabAt == null || tabAt.hash >= 0) {
                    node = tabAt;
                } else if (tabAt instanceof ForwardingNode) {
                    this.tab = ((ForwardingNode) tabAt).nextTable;
                    pushState(nodeArr, i, length);
                    node = null;
                } else {
                    node = tabAt instanceof TreeBin ? ((TreeBin) tabAt).first : null;
                }
                if (this.stack != null) {
                    recoverState(length);
                } else {
                    int i2 = i + this.baseSize;
                    this.index = i2;
                    if (i2 >= length) {
                        int i3 = this.baseIndex + 1;
                        this.baseIndex = i3;
                        this.index = i3;
                    }
                }
            }
            this.next = node;
            return node;
        }

        private void pushState(Node[] nodeArr, int i, int i2) {
            TableStack tableStack = this.spare;
            if (tableStack != null) {
                this.spare = tableStack.next;
            } else {
                tableStack = new TableStack();
            }
            tableStack.tab = nodeArr;
            tableStack.length = i2;
            tableStack.index = i;
            tableStack.next = this.stack;
            this.stack = tableStack;
        }

        private void recoverState(int i) {
            TableStack tableStack;
            while (true) {
                tableStack = this.stack;
                if (tableStack == null) {
                    break;
                }
                int i2 = this.index;
                int i3 = tableStack.length;
                int i4 = i2 + i3;
                this.index = i4;
                if (i4 < i) {
                    break;
                }
                this.index = tableStack.index;
                this.tab = tableStack.tab;
                tableStack.tab = null;
                TableStack tableStack2 = tableStack.next;
                tableStack.next = this.spare;
                this.stack = tableStack2;
                this.spare = tableStack;
                i = i3;
            }
            if (tableStack == null) {
                int i5 = this.index + this.baseSize;
                this.index = i5;
                if (i5 >= i) {
                    int i6 = this.baseIndex + 1;
                    this.baseIndex = i6;
                    this.index = i6;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class BaseIterator extends Traverser {
        Node lastReturned;
        final ConcurrentHashMap map;

        BaseIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentHashMap;
            advance();
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        public final boolean hasMoreElements() {
            return this.next != null;
        }

        public final void remove() {
            Node node = this.lastReturned;
            if (node != null) {
                this.lastReturned = null;
                this.map.replaceNode(node.key, null, null);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class KeyIterator extends BaseIterator implements Iterator, Enumeration {
        KeyIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        @Override // java.util.Iterator
        public final Object next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.key;
                this.lastReturned = node;
                advance();
                return obj;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Enumeration
        public final Object nextElement() {
            return next();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ValueIterator extends BaseIterator implements Iterator, Enumeration {
        ValueIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        @Override // java.util.Iterator
        public final Object next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.val;
                this.lastReturned = node;
                advance();
                return obj;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Enumeration
        public final Object nextElement() {
            return next();
        }
    }

    static final class EntryIterator extends BaseIterator implements Iterator {
        EntryIterator(Node[] nodeArr, int i, int i2, int i3, ConcurrentHashMap concurrentHashMap) {
            super(nodeArr, i, i2, i3, concurrentHashMap);
        }

        @Override // java.util.Iterator
        public final Map.Entry next() {
            Node node = this.next;
            if (node != null) {
                Object obj = node.key;
                Object obj2 = node.val;
                this.lastReturned = node;
                advance();
                return new MapEntry(obj, obj2, this.map);
            }
            throw new NoSuchElementException();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class MapEntry implements Map.Entry {
        final Object key;
        final ConcurrentHashMap map;
        Object val;

        MapEntry(Object obj, Object obj2, ConcurrentHashMap concurrentHashMap) {
            this.key = obj;
            this.val = obj2;
            this.map = concurrentHashMap;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.val;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.val.hashCode() ^ this.key.hashCode();
        }

        public String toString() {
            Helpers.mapEntryToString(this.key, this.val);
            throw null;
        }

        public boolean equals(Object obj) {
            Map.Entry entry;
            Object key2;
            Object value;
            Object obj2;
            Object obj3;
            return (obj instanceof Map.Entry) && (key2 = (entry = (Map.Entry) obj).getKey()) != null && (value = entry.getValue()) != null && (key2 == (obj2 = this.key) || key2.equals(obj2)) && (value == (obj3 = this.val) || value.equals(obj3));
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class CollectionView implements Collection, Serializable {
        private static final long serialVersionUID = 7249069246763182397L;
        final ConcurrentHashMap map;

        @Override // java.util.Collection
        public abstract boolean contains(Object obj);

        @Override // java.util.Collection, java.lang.Iterable
        public abstract Iterator iterator();

        CollectionView(ConcurrentHashMap concurrentHashMap) {
            this.map = concurrentHashMap;
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
            long mappingCount = this.map.mappingCount();
            if (mappingCount <= 2147483639) {
                int i = (int) mappingCount;
                Object[] objArr = new Object[i];
                int i2 = 0;
                Iterator it = iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (i2 == i) {
                        int i3 = 2147483639;
                        if (i < 2147483639) {
                            if (i < 1073741819) {
                                i3 = (i >>> 1) + 1 + i;
                            }
                            objArr = Arrays.copyOf(objArr, i3);
                            i = i3;
                        } else {
                            throw new OutOfMemoryError("Required array size too large");
                        }
                    }
                    objArr[i2] = next;
                    i2++;
                }
                if (i2 == i) {
                    return objArr;
                }
                return Arrays.copyOf(objArr, i2);
            }
            throw new OutOfMemoryError("Required array size too large");
        }

        @Override // java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            Object[] objArr2;
            long mappingCount = this.map.mappingCount();
            if (mappingCount <= 2147483639) {
                int i = (int) mappingCount;
                if (objArr.length >= i) {
                    objArr2 = objArr;
                } else {
                    objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
                }
                int length = objArr2.length;
                int i2 = 0;
                Iterator it = iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    if (i2 == length) {
                        int i3 = 2147483639;
                        if (length < 2147483639) {
                            if (length < 1073741819) {
                                i3 = (length >>> 1) + 1 + length;
                            }
                            objArr2 = Arrays.copyOf(objArr2, i3);
                            length = i3;
                        } else {
                            throw new OutOfMemoryError("Required array size too large");
                        }
                    }
                    objArr2[i2] = next;
                    i2++;
                }
                if (objArr == objArr2 && i2 < length) {
                    objArr2[i2] = null;
                    return objArr2;
                } else if (i2 == length) {
                    return objArr2;
                } else {
                    return Arrays.copyOf(objArr2, i2);
                }
            } else {
                throw new OutOfMemoryError("Required array size too large");
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Iterator it = iterator();
            if (it.hasNext()) {
                while (true) {
                    Object next = it.next();
                    if (next == this) {
                        next = "(this Collection)";
                    }
                    sb.append(next);
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
        public final boolean containsAll(java.util.Collection r2) {
            /*
                r1 = this;
                if (r2 == r1) goto L_0x001a
                java.util.Iterator r2 = r2.iterator()
            L_0x0006:
                boolean r0 = r2.hasNext()
                if (r0 == 0) goto L_0x001a
                java.lang.Object r0 = r2.next()
                if (r0 == 0) goto L_0x0018
                boolean r0 = r1.contains(r0)
                if (r0 != 0) goto L_0x0006
            L_0x0018:
                r1 = 0
                return r1
            L_0x001a:
                r1 = 1
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.ConcurrentHashMap.CollectionView.containsAll(java.util.Collection):boolean");
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection collection) {
            if (collection != null) {
                boolean z = false;
                Iterator it = iterator();
                while (it.hasNext()) {
                    if (collection.contains(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
                return z;
            }
            throw new NullPointerException();
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection collection) {
            if (collection != null) {
                boolean z = false;
                Iterator it = iterator();
                while (it.hasNext()) {
                    if (!collection.contains(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
                return z;
            }
            throw new NullPointerException();
        }
    }

    public static class KeySetView extends CollectionView implements Set, Serializable {
        private static final long serialVersionUID = 7249069246763182397L;
        private final Object value;

        KeySetView(ConcurrentHashMap concurrentHashMap, Object obj) {
            super(concurrentHashMap);
            this.value = obj;
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return this.map.remove(obj) != null;
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new KeyIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean add(Object obj) {
            Object obj2 = this.value;
            if (obj2 != null) {
                return this.map.putVal(obj, obj2, true) == null;
            }
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public boolean addAll(Collection collection) {
            Object obj = this.value;
            if (obj != null) {
                boolean z = false;
                for (Object obj2 : collection) {
                    if (this.map.putVal(obj2, obj, true) == null) {
                        z = true;
                    }
                }
                return z;
            }
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            Iterator it = iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().hashCode();
            }
            return i;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            Set set;
            return (obj instanceof Set) && ((set = (Set) obj) == this || (containsAll(set) && set.containsAll(this)));
        }
    }

    static final class ValuesView extends CollectionView implements Collection, Serializable {
        private static final long serialVersionUID = 2249069246763182397L;

        ValuesView(ConcurrentHashMap concurrentHashMap) {
            super(concurrentHashMap);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection
        public final boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            if (obj == null) {
                return false;
            }
            Iterator it = iterator();
            while (it.hasNext()) {
                if (obj.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new ValueIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }
    }

    static final class EntrySetView extends CollectionView implements Set, Serializable {
        private static final long serialVersionUID = 2249069246763182397L;

        EntrySetView(ConcurrentHashMap concurrentHashMap) {
            super(concurrentHashMap);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Map.Entry entry;
            Object key;
            Object obj2;
            Object value;
            return (!(obj instanceof Map.Entry) || (key = (entry = (Map.Entry) obj).getKey()) == null || (obj2 = this.map.get(key)) == null || (value = entry.getValue()) == null || (value != obj2 && !value.equals(obj2))) ? false : true;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            Map.Entry entry;
            Object key;
            Object value;
            return (obj instanceof Map.Entry) && (key = (entry = (Map.Entry) obj).getKey()) != null && (value = entry.getValue()) != null && this.map.remove(key, value);
        }

        @Override // java.util.concurrent.ConcurrentHashMap.CollectionView, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator iterator() {
            ConcurrentHashMap concurrentHashMap = this.map;
            Node[] nodeArr = concurrentHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new EntryIterator(nodeArr, length, 0, length, concurrentHashMap);
        }

        public boolean add(Map.Entry entry) {
            return this.map.putVal(entry.getKey(), entry.getValue(), false) == null;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean addAll(Collection collection) {
            Iterator it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (add((Map.Entry) it.next())) {
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.Collection, java.util.Set
        public final int hashCode() {
            Node[] nodeArr = this.map.table;
            int i = 0;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node advance = traverser.advance();
                    if (advance == null) {
                        break;
                    }
                    i += advance.hashCode();
                }
            }
            return i;
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean equals(Object obj) {
            Set set;
            return (obj instanceof Set) && ((set = (Set) obj) == this || (containsAll(set) && set.containsAll(this)));
        }
    }
}
