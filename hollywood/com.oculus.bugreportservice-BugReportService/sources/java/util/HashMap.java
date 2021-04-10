package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMap extends AbstractMap implements Map, Cloneable, Serializable {
    private static final long serialVersionUID = 362498820763181265L;
    transient Set entrySet;
    final float loadFactor;
    transient int modCount;
    transient int size;
    transient Node[] table;
    int threshold;

    static final int tableSizeFor(int i) {
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

    /* access modifiers changed from: package-private */
    public void afterNodeAccess(Node node) {
    }

    /* access modifiers changed from: package-private */
    public void afterNodeInsertion(boolean z) {
    }

    /* access modifiers changed from: package-private */
    public void afterNodeRemoval(Node node) {
    }

    /* access modifiers changed from: package-private */
    public static class Node implements Map.Entry {
        final int hash;
        final Object key;
        Node next;
        Object value;

        Node(int i, Object obj, Object obj2, Node node) {
            this.hash = i;
            this.key = obj;
            this.value = obj2;
            this.next = node;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            return this.value;
        }

        public final String toString() {
            return this.key + "=" + this.value;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            return Objects.hashCode(this.value) ^ Objects.hashCode(this.key);
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!Objects.equals(this.key, entry.getKey()) || !Objects.equals(this.value, entry.getValue())) {
                return false;
            }
            return true;
        }
    }

    static final int hash(Object obj) {
        if (obj == null) {
            return 0;
        }
        int hashCode = obj.hashCode();
        return hashCode ^ (hashCode >>> 16);
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

    public HashMap(int i, float f) {
        if (i >= 0) {
            i = i > 1073741824 ? 1073741824 : i;
            if (f <= 0.0f || Float.isNaN(f)) {
                throw new IllegalArgumentException("Illegal load factor: " + f);
            }
            this.loadFactor = f;
            this.threshold = tableSizeFor(i);
            return;
        }
        throw new IllegalArgumentException("Illegal initial capacity: " + i);
    }

    public HashMap(int i) {
        this(i, 0.75f);
    }

    public HashMap() {
        this.loadFactor = 0.75f;
    }

    public HashMap(Map map) {
        this.loadFactor = 0.75f;
        putMapEntries(map, false);
    }

    /* access modifiers changed from: package-private */
    public final void putMapEntries(Map map, boolean z) {
        int size2 = map.size();
        if (size2 > 0) {
            if (this.table == null) {
                float f = (((float) size2) / this.loadFactor) + 1.0f;
                int i = f < 1.07374182E9f ? (int) f : 1073741824;
                if (i > this.threshold) {
                    this.threshold = tableSizeFor(i);
                }
            } else if (size2 > this.threshold) {
                resize();
            }
            for (Map.Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                putVal(hash(key), key, entry.getValue(), false, z);
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
    public Object get(Object obj) {
        Node node = getNode(hash(obj), obj);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    /* access modifiers changed from: package-private */
    public final Node getNode(int i, Object obj) {
        int length;
        Node node;
        Object obj2;
        Object obj3;
        Node[] nodeArr = this.table;
        if (nodeArr == null || (length = nodeArr.length) <= 0 || (node = nodeArr[(length - 1) & i]) == null) {
            return null;
        }
        if (node.hash == i && ((obj3 = node.key) == obj || (obj != null && obj.equals(obj3)))) {
            return node;
        }
        Node node2 = node.next;
        if (node2 == null) {
            return null;
        }
        if (node instanceof TreeNode) {
            return ((TreeNode) node).getTreeNode(i, obj);
        }
        do {
            if (node2.hash == i && ((obj2 = node2.key) == obj || (obj != null && obj.equals(obj2)))) {
                return node2;
            }
            node2 = node2.next;
        } while (node2 != null);
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return getNode(hash(obj), obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object put(Object obj, Object obj2) {
        return putVal(hash(obj), obj, obj2, false, true);
    }

    /* access modifiers changed from: package-private */
    public final Object putVal(int i, Object obj, Object obj2, boolean z, boolean z2) {
        int i2;
        Node node;
        Object obj3;
        Object obj4;
        Node[] nodeArr = this.table;
        if (nodeArr == null || (i2 = nodeArr.length) == 0) {
            nodeArr = resize();
            i2 = nodeArr.length;
        }
        int i3 = (i2 - 1) & i;
        TreeNode treeNode = nodeArr[i3];
        if (treeNode == null) {
            nodeArr[i3] = newNode(i, obj, obj2, null);
        } else {
            if (treeNode.hash != i || ((obj4 = treeNode.key) != obj && (obj == null || !obj.equals(obj4)))) {
                if (treeNode instanceof TreeNode) {
                    treeNode = ((TreeNode) treeNode).putTreeVal(this, nodeArr, i, obj, obj2);
                } else {
                    int i4 = 0;
                    while (true) {
                        node = treeNode.next;
                        if (node != null) {
                            if (node.hash == i && ((obj3 = node.key) == obj || (obj != null && obj.equals(obj3)))) {
                                break;
                            }
                            i4++;
                            treeNode = node;
                        } else {
                            treeNode.next = newNode(i, obj, obj2, null);
                            if (i4 >= 7) {
                                treeifyBin(nodeArr, i);
                            }
                        }
                    }
                    treeNode = node;
                }
            }
            if (treeNode != null) {
                Object obj5 = treeNode.value;
                if (!z || obj5 == null) {
                    treeNode.value = obj2;
                }
                afterNodeAccess(treeNode);
                return obj5;
            }
        }
        this.modCount++;
        int i5 = this.size + 1;
        this.size = i5;
        if (i5 > this.threshold) {
            resize();
        }
        afterNodeInsertion(z2);
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0041 A[LOOP:0: B:26:0x0041->B:52:0x008d, LOOP_START, PHI: r1 
      PHI: (r1v1 int) = (r1v0 int), (r1v2 int) binds: [B:25:0x003f, B:52:0x008d] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.HashMap.Node[] resize() {
        /*
        // Method dump skipped, instructions count: 145
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.HashMap.resize():java.util.HashMap$Node[]");
    }

    /* access modifiers changed from: package-private */
    public final void treeifyBin(Node[] nodeArr, int i) {
        int length;
        if (nodeArr == null || (length = nodeArr.length) < 64) {
            resize();
            return;
        }
        int i2 = i & (length - 1);
        Node node = nodeArr[i2];
        if (node != null) {
            TreeNode treeNode = null;
            TreeNode treeNode2 = null;
            while (true) {
                TreeNode replacementTreeNode = replacementTreeNode(node, null);
                if (treeNode == null) {
                    treeNode2 = replacementTreeNode;
                } else {
                    replacementTreeNode.prev = treeNode;
                    treeNode.next = replacementTreeNode;
                }
                node = node.next;
                if (node == null) {
                    break;
                }
                treeNode = replacementTreeNode;
            }
            nodeArr[i2] = treeNode2;
            if (treeNode2 != null) {
                treeNode2.treeify(nodeArr);
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void putAll(Map map) {
        putMapEntries(map, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        Node removeNode = removeNode(hash(obj), obj, null, false, true);
        if (removeNode == null) {
            return null;
        }
        return removeNode.value;
    }

    /* access modifiers changed from: package-private */
    public final Node removeNode(int i, Object obj, Object obj2, boolean z, boolean z2) {
        int length;
        int i2;
        Node node;
        TreeNode treeNode;
        Object obj3;
        Object obj4;
        Object obj5;
        Node[] nodeArr = this.table;
        if (!(nodeArr == null || (length = nodeArr.length) <= 0 || (node = nodeArr[(i2 = (length - 1) & i)]) == null)) {
            if (node.hash != i || ((obj5 = node.key) != obj && (obj == null || !obj.equals(obj5)))) {
                Node node2 = node.next;
                if (node2 == null) {
                    treeNode = null;
                } else if (node instanceof TreeNode) {
                    treeNode = ((TreeNode) node).getTreeNode(i, obj);
                } else {
                    while (true) {
                        if (node2.hash != i || ((obj4 = node2.key) != obj && (obj == null || !obj.equals(obj4)))) {
                            Node node3 = node2.next;
                            if (node3 == null) {
                                treeNode = null;
                                node = node2;
                                break;
                            }
                            node2 = node3;
                            node = node2;
                        }
                    }
                    treeNode = node2;
                }
            } else {
                treeNode = node;
            }
            if (treeNode != null && (!z || (obj3 = treeNode.value) == obj2 || (obj2 != null && obj2.equals(obj3)))) {
                if (treeNode instanceof TreeNode) {
                    treeNode.removeTreeNode(this, nodeArr, z2);
                } else if (treeNode == node) {
                    nodeArr[i2] = treeNode.next;
                } else {
                    node.next = treeNode.next;
                }
                this.modCount++;
                this.size--;
                afterNodeRemoval(treeNode);
                return treeNode;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.modCount++;
        Node[] nodeArr = this.table;
        if (nodeArr != null && this.size > 0) {
            this.size = 0;
            for (int i = 0; i < nodeArr.length; i++) {
                nodeArr[i] = null;
            }
        }
    }

    @Override // java.util.AbstractMap
    public boolean containsValue(Object obj) {
        Node[] nodeArr = this.table;
        if (nodeArr != null && this.size > 0) {
            for (Node node : nodeArr) {
                for (; node != null; node = node.next) {
                    Object obj2 = node.value;
                    if (obj2 == obj) {
                        return true;
                    }
                    if (obj != null && obj.equals(obj2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    final class KeySet extends AbstractSet {
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
        public final Iterator iterator() {
            return new KeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            return HashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            return HashMap.this.removeNode(HashMap.hash(obj), obj, null, false, true) != null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.values = values;
        return values;
    }

    final class Values extends AbstractCollection {
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
        public final Iterator iterator() {
            return new ValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            return HashMap.this.containsValue(obj);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set entrySet() {
        Set set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    final class EntrySet extends AbstractSet {
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
        public final Iterator iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Node node = HashMap.this.getNode(HashMap.hash(key), key);
            if (node == null || !node.equals(entry)) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            if (HashMap.this.removeNode(HashMap.hash(key), key, entry.getValue(), true, true) != null) {
                return true;
            }
            return false;
        }
    }

    @Override // java.util.Map
    public Object putIfAbsent(Object obj, Object obj2) {
        return putVal(hash(obj), obj, obj2, true, true);
    }

    @Override // java.util.Map
    public boolean remove(Object obj, Object obj2) {
        return removeNode(hash(obj), obj, obj2, true, true) != null;
    }

    @Override // java.util.AbstractMap
    public Object clone() {
        try {
            HashMap hashMap = (HashMap) super.clone();
            hashMap.reinitialize();
            hashMap.putMapEntries(this, false);
            return hashMap;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final int capacity() {
        Node[] nodeArr = this.table;
        if (nodeArr != null) {
            return nodeArr.length;
        }
        int i = this.threshold;
        if (i > 0) {
            return i;
        }
        return 16;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        capacity();
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    /* access modifiers changed from: package-private */
    public abstract class HashIterator {
        Node current = null;
        int expectedModCount;
        int index = 0;
        Node next = null;

        HashIterator() {
            Node node;
            this.expectedModCount = HashMap.this.modCount;
            Node[] nodeArr = HashMap.this.table;
            if (nodeArr != null && HashMap.this.size > 0) {
                do {
                    int i = this.index;
                    if (i < nodeArr.length) {
                        this.index = i + 1;
                        node = nodeArr[i];
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
        public final Node nextNode() {
            Node[] nodeArr;
            Node node;
            Node node2 = this.next;
            HashMap hashMap = HashMap.this;
            if (hashMap.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (node2 != null) {
                this.current = node2;
                Node node3 = node2.next;
                this.next = node3;
                if (node3 == null && (nodeArr = hashMap.table) != null) {
                    do {
                        int i = this.index;
                        if (i >= nodeArr.length) {
                            break;
                        }
                        this.index = i + 1;
                        node = nodeArr[i];
                        this.next = node;
                    } while (node == null);
                }
                return node2;
            } else {
                throw new NoSuchElementException();
            }
        }

        public final void remove() {
            Node node = this.current;
            if (node != null) {
                HashMap hashMap = HashMap.this;
                if (hashMap.modCount == this.expectedModCount) {
                    this.current = null;
                    Object obj = node.key;
                    hashMap.removeNode(HashMap.hash(obj), obj, null, false, false);
                    this.expectedModCount = HashMap.this.modCount;
                    return;
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException();
        }
    }

    final class KeyIterator extends HashIterator implements Iterator {
        KeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Object next() {
            return nextNode().key;
        }
    }

    final class ValueIterator extends HashIterator implements Iterator {
        ValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Object next() {
            return nextNode().value;
        }
    }

    final class EntryIterator extends HashIterator implements Iterator {
        EntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Map.Entry next() {
            return nextNode();
        }
    }

    /* access modifiers changed from: package-private */
    public Node newNode(int i, Object obj, Object obj2, Node node) {
        return new Node(i, obj, obj2, node);
    }

    /* access modifiers changed from: package-private */
    public Node replacementNode(Node node, Node node2) {
        return new Node(node.hash, node.key, node.value, node2);
    }

    /* access modifiers changed from: package-private */
    public TreeNode newTreeNode(int i, Object obj, Object obj2, Node node) {
        return new TreeNode(i, obj, obj2, node);
    }

    /* access modifiers changed from: package-private */
    public TreeNode replacementTreeNode(Node node, Node node2) {
        return new TreeNode(node.hash, node.key, node.value, node2);
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
    public static final class TreeNode extends LinkedHashMap.LinkedHashMapEntry {
        TreeNode left;
        TreeNode parent;
        TreeNode prev;
        boolean red;
        TreeNode right;

        TreeNode(int i, Object obj, Object obj2, Node node) {
            super(i, obj, obj2, node);
        }

        /* access modifiers changed from: package-private */
        public final TreeNode root() {
            while (true) {
                TreeNode treeNode = this.parent;
                if (treeNode == null) {
                    return this;
                }
                this = treeNode;
            }
        }

        static void moveRootToFront(Node[] nodeArr, TreeNode treeNode) {
            int length;
            if (treeNode != null && nodeArr != null && (length = nodeArr.length) > 0) {
                int i = (length - 1) & treeNode.hash;
                TreeNode treeNode2 = (TreeNode) nodeArr[i];
                if (treeNode != treeNode2) {
                    nodeArr[i] = treeNode;
                    TreeNode treeNode3 = treeNode.prev;
                    Node node = treeNode.next;
                    if (node != null) {
                        ((TreeNode) node).prev = treeNode3;
                    }
                    if (treeNode3 != null) {
                        treeNode3.next = node;
                    }
                    if (treeNode2 != null) {
                        treeNode2.prev = treeNode;
                    }
                    treeNode.next = treeNode2;
                    treeNode.prev = null;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final TreeNode find(int i, Object obj, Class cls) {
            int compareComparables;
            do {
                TreeNode treeNode = this.left;
                TreeNode treeNode2 = this.right;
                int i2 = this.hash;
                if (i2 <= i) {
                    if (i2 >= i) {
                        Object obj2 = this.key;
                        if (obj2 == obj) {
                            return this;
                        }
                        if (obj != null && obj.equals(obj2)) {
                            return this;
                        }
                        if (treeNode != null) {
                            if (treeNode2 != null) {
                                if ((cls == null && (cls = HashMap.comparableClassFor(obj)) == null) || (compareComparables = HashMap.compareComparables(cls, obj, obj2)) == 0) {
                                    TreeNode find = treeNode2.find(i, obj, cls);
                                    if (find != null) {
                                        return find;
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

        /* access modifiers changed from: package-private */
        public final TreeNode getTreeNode(int i, Object obj) {
            if (this.parent != null) {
                this = root();
            }
            return this.find(i, obj, null);
        }

        static int tieBreakOrder(Object obj, Object obj2) {
            int compareTo;
            if (obj != null && obj2 != null && (compareTo = obj.getClass().getName().compareTo(obj2.getClass().getName())) != 0) {
                return compareTo;
            }
            return System.identityHashCode(obj) <= System.identityHashCode(obj2) ? -1 : 1;
        }

        /* access modifiers changed from: package-private */
        public final void treeify(Node[] nodeArr) {
            int tieBreakOrder;
            int compareComparables;
            TreeNode treeNode = null;
            while (this != null) {
                TreeNode treeNode2 = (TreeNode) this.next;
                this.right = null;
                this.left = null;
                if (treeNode == null) {
                    this.parent = null;
                    this.red = false;
                } else {
                    Object obj = this.key;
                    int i = this.hash;
                    Class cls = null;
                    TreeNode treeNode3 = treeNode;
                    while (true) {
                        Object obj2 = treeNode3.key;
                        int i2 = treeNode3.hash;
                        tieBreakOrder = i2 > i ? -1 : i2 < i ? 1 : ((cls == null && (cls = HashMap.comparableClassFor(obj)) == null) || (compareComparables = HashMap.compareComparables(cls, obj, obj2)) == 0) ? tieBreakOrder(obj, obj2) : compareComparables;
                        TreeNode treeNode4 = tieBreakOrder <= 0 ? treeNode3.left : treeNode3.right;
                        if (treeNode4 == null) {
                            break;
                        }
                        treeNode3 = treeNode4;
                    }
                    this.parent = treeNode3;
                    if (tieBreakOrder <= 0) {
                        treeNode3.left = this;
                    } else {
                        treeNode3.right = this;
                    }
                    this = balanceInsertion(treeNode, this);
                }
                treeNode = this;
                this = treeNode2;
            }
            moveRootToFront(nodeArr, treeNode);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v0, types: [java.util.HashMap] */
        /* JADX WARN: Type inference failed for: r4v1, types: [java.util.HashMap$Node] */
        /* JADX WARN: Type inference failed for: r4v3 */
        /* access modifiers changed from: package-private */
        public final Node untreeify(HashMap hashMap) {
            Node node = null;
            Node node2 = null;
            for (?? r4 = this; r4 != 0; r4 = r4.next) {
                Node replacementNode = hashMap.replacementNode(r4, null);
                if (node2 == null) {
                    node = replacementNode;
                } else {
                    node2.next = replacementNode;
                }
                node2 = replacementNode;
            }
            return node;
        }

        /* access modifiers changed from: package-private */
        public final TreeNode putTreeVal(HashMap hashMap, Node[] nodeArr, int i, Object obj, Object obj2) {
            TreeNode treeNode;
            TreeNode treeNode2;
            int compareComparables;
            if (this.parent != null) {
                this = root();
            }
            Class cls = null;
            boolean z = false;
            TreeNode treeNode3 = this;
            while (true) {
                int i2 = treeNode3.hash;
                int i3 = 1;
                if (i2 > i) {
                    i3 = -1;
                } else if (i2 >= i) {
                    Object obj3 = treeNode3.key;
                    if (obj3 == obj || (obj != null && obj.equals(obj3))) {
                        return treeNode3;
                    }
                    if ((cls == null && (cls = HashMap.comparableClassFor(obj)) == null) || (compareComparables = HashMap.compareComparables(cls, obj, obj3)) == 0) {
                        if (!z) {
                            TreeNode treeNode4 = treeNode3.left;
                            if ((treeNode4 == null || (treeNode = treeNode4.find(i, obj, cls)) == null) && ((treeNode2 = treeNode3.right) == null || (treeNode = treeNode2.find(i, obj, cls)) == null)) {
                                z = true;
                            }
                        }
                        i3 = tieBreakOrder(obj, obj3);
                    } else {
                        i3 = compareComparables;
                    }
                }
                TreeNode treeNode5 = i3 <= 0 ? treeNode3.left : treeNode3.right;
                if (treeNode5 == null) {
                    Node node = treeNode3.next;
                    TreeNode newTreeNode = hashMap.newTreeNode(i, obj, obj2, node);
                    if (i3 <= 0) {
                        treeNode3.left = newTreeNode;
                    } else {
                        treeNode3.right = newTreeNode;
                    }
                    treeNode3.next = newTreeNode;
                    newTreeNode.prev = treeNode3;
                    newTreeNode.parent = treeNode3;
                    if (node != null) {
                        ((TreeNode) node).prev = newTreeNode;
                    }
                    moveRootToFront(nodeArr, balanceInsertion(this, newTreeNode));
                    return null;
                }
                treeNode3 = treeNode5;
            }
            return treeNode;
        }

        /* access modifiers changed from: package-private */
        public final void removeTreeNode(HashMap hashMap, Node[] nodeArr, boolean z) {
            int length;
            TreeNode treeNode;
            TreeNode treeNode2;
            if (nodeArr != null && (length = nodeArr.length) != 0) {
                int i = (length - 1) & this.hash;
                TreeNode treeNode3 = (TreeNode) nodeArr[i];
                TreeNode treeNode4 = (TreeNode) this.next;
                TreeNode treeNode5 = this.prev;
                if (treeNode5 == null) {
                    nodeArr[i] = treeNode4;
                    treeNode = treeNode4;
                } else {
                    treeNode5.next = treeNode4;
                    treeNode = treeNode3;
                }
                if (treeNode4 != null) {
                    treeNode4.prev = treeNode5;
                }
                if (treeNode != null) {
                    if (treeNode3.parent != null) {
                        treeNode3 = treeNode3.root();
                    }
                    if (treeNode3 == null || treeNode3.right == null || (treeNode2 = treeNode3.left) == null || treeNode2.left == null) {
                        nodeArr[i] = treeNode.untreeify(hashMap);
                        return;
                    }
                    TreeNode treeNode6 = this.left;
                    TreeNode treeNode7 = this.right;
                    if (treeNode6 != null && treeNode7 != null) {
                        TreeNode treeNode8 = treeNode7;
                        while (true) {
                            TreeNode treeNode9 = treeNode8.left;
                            if (treeNode9 == null) {
                                break;
                            }
                            treeNode8 = treeNode9;
                        }
                        boolean z2 = treeNode8.red;
                        treeNode8.red = this.red;
                        this.red = z2;
                        TreeNode treeNode10 = treeNode8.right;
                        TreeNode treeNode11 = this.parent;
                        if (treeNode8 == treeNode7) {
                            this.parent = treeNode8;
                            treeNode8.right = this;
                        } else {
                            TreeNode treeNode12 = treeNode8.parent;
                            this.parent = treeNode12;
                            if (treeNode12 != null) {
                                if (treeNode8 == treeNode12.left) {
                                    treeNode12.left = this;
                                } else {
                                    treeNode12.right = this;
                                }
                            }
                            treeNode8.right = treeNode7;
                            if (treeNode7 != null) {
                                treeNode7.parent = treeNode8;
                            }
                        }
                        this.left = null;
                        this.right = treeNode10;
                        if (treeNode10 != null) {
                            treeNode10.parent = this;
                        }
                        treeNode8.left = treeNode6;
                        if (treeNode6 != null) {
                            treeNode6.parent = treeNode8;
                        }
                        treeNode8.parent = treeNode11;
                        if (treeNode11 == null) {
                            treeNode3 = treeNode8;
                        } else if (this == treeNode11.left) {
                            treeNode11.left = treeNode8;
                        } else {
                            treeNode11.right = treeNode8;
                        }
                        if (treeNode10 == null) {
                            treeNode10 = this;
                        }
                        treeNode6 = treeNode10;
                    } else if (treeNode6 == null) {
                        treeNode6 = treeNode7 != null ? treeNode7 : this;
                    }
                    if (treeNode6 != this) {
                        TreeNode treeNode13 = this.parent;
                        treeNode6.parent = treeNode13;
                        if (treeNode13 == null) {
                            treeNode3 = treeNode6;
                        } else if (this == treeNode13.left) {
                            treeNode13.left = treeNode6;
                        } else {
                            treeNode13.right = treeNode6;
                        }
                        this.parent = null;
                        this.right = null;
                        this.left = null;
                    }
                    if (!this.red) {
                        treeNode3 = balanceDeletion(treeNode3, treeNode6);
                    }
                    if (treeNode6 == this) {
                        TreeNode treeNode14 = this.parent;
                        this.parent = null;
                        if (treeNode14 != null) {
                            if (this == treeNode14.left) {
                                treeNode14.left = null;
                            } else if (this == treeNode14.right) {
                                treeNode14.right = null;
                            }
                        }
                    }
                    if (z) {
                        moveRootToFront(nodeArr, treeNode3);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void split(HashMap hashMap, Node[] nodeArr, int i, int i2) {
            int i3 = 0;
            int i4 = 0;
            TreeNode treeNode = null;
            TreeNode treeNode2 = null;
            TreeNode treeNode3 = null;
            TreeNode treeNode4 = null;
            while (this != null) {
                TreeNode treeNode5 = (TreeNode) this.next;
                this.next = null;
                if ((this.hash & i2) == 0) {
                    this.prev = treeNode3;
                    if (treeNode3 == null) {
                        treeNode = this;
                    } else {
                        treeNode3.next = this;
                    }
                    i3++;
                    treeNode3 = this;
                } else {
                    this.prev = treeNode2;
                    if (treeNode2 == null) {
                        treeNode4 = this;
                    } else {
                        treeNode2.next = this;
                    }
                    i4++;
                    treeNode2 = this;
                }
                this = treeNode5;
            }
            if (treeNode != null) {
                if (i3 <= 6) {
                    nodeArr[i] = treeNode.untreeify(hashMap);
                } else {
                    nodeArr[i] = treeNode;
                    if (treeNode4 != null) {
                        treeNode.treeify(nodeArr);
                    }
                }
            }
            if (treeNode4 == null) {
                return;
            }
            if (i4 <= 6) {
                nodeArr[i + i2] = treeNode4.untreeify(hashMap);
                return;
            }
            nodeArr[i + i2] = treeNode4;
            if (treeNode != null) {
                treeNode4.treeify(nodeArr);
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
    }
}
