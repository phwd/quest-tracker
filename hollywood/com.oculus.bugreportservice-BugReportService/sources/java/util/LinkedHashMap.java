package java.util;

import java.util.HashMap;
import java.util.Map;

public class LinkedHashMap extends HashMap implements Map {
    private static final long serialVersionUID = 3801124242820219131L;
    final boolean accessOrder;
    transient LinkedHashMapEntry head;
    transient LinkedHashMapEntry tail;

    /* access modifiers changed from: protected */
    public boolean removeEldestEntry(Map.Entry entry) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public static class LinkedHashMapEntry extends HashMap.Node {
        LinkedHashMapEntry after;
        LinkedHashMapEntry before;

        LinkedHashMapEntry(int i, Object obj, Object obj2, HashMap.Node node) {
            super(i, obj, obj2, node);
        }
    }

    private void linkNodeLast(LinkedHashMapEntry linkedHashMapEntry) {
        LinkedHashMapEntry linkedHashMapEntry2 = this.tail;
        this.tail = linkedHashMapEntry;
        if (linkedHashMapEntry2 == null) {
            this.head = linkedHashMapEntry;
            return;
        }
        linkedHashMapEntry.before = linkedHashMapEntry2;
        linkedHashMapEntry2.after = linkedHashMapEntry;
    }

    private void transferLinks(LinkedHashMapEntry linkedHashMapEntry, LinkedHashMapEntry linkedHashMapEntry2) {
        LinkedHashMapEntry linkedHashMapEntry3 = linkedHashMapEntry.before;
        linkedHashMapEntry2.before = linkedHashMapEntry3;
        LinkedHashMapEntry linkedHashMapEntry4 = linkedHashMapEntry.after;
        linkedHashMapEntry2.after = linkedHashMapEntry4;
        if (linkedHashMapEntry3 == null) {
            this.head = linkedHashMapEntry2;
        } else {
            linkedHashMapEntry3.after = linkedHashMapEntry2;
        }
        if (linkedHashMapEntry4 == null) {
            this.tail = linkedHashMapEntry2;
        } else {
            linkedHashMapEntry4.before = linkedHashMapEntry2;
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
    public HashMap.Node newNode(int i, Object obj, Object obj2, HashMap.Node node) {
        LinkedHashMapEntry linkedHashMapEntry = new LinkedHashMapEntry(i, obj, obj2, node);
        linkNodeLast(linkedHashMapEntry);
        return linkedHashMapEntry;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public HashMap.Node replacementNode(HashMap.Node node, HashMap.Node node2) {
        LinkedHashMapEntry linkedHashMapEntry = (LinkedHashMapEntry) node;
        LinkedHashMapEntry linkedHashMapEntry2 = new LinkedHashMapEntry(linkedHashMapEntry.hash, linkedHashMapEntry.key, linkedHashMapEntry.value, node2);
        transferLinks(linkedHashMapEntry, linkedHashMapEntry2);
        return linkedHashMapEntry2;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public HashMap.TreeNode newTreeNode(int i, Object obj, Object obj2, HashMap.Node node) {
        HashMap.TreeNode treeNode = new HashMap.TreeNode(i, obj, obj2, node);
        linkNodeLast(treeNode);
        return treeNode;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public HashMap.TreeNode replacementTreeNode(HashMap.Node node, HashMap.Node node2) {
        LinkedHashMapEntry linkedHashMapEntry = (LinkedHashMapEntry) node;
        HashMap.TreeNode treeNode = new HashMap.TreeNode(linkedHashMapEntry.hash, linkedHashMapEntry.key, linkedHashMapEntry.value, node2);
        transferLinks(linkedHashMapEntry, treeNode);
        return treeNode;
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void afterNodeRemoval(HashMap.Node node) {
        LinkedHashMapEntry linkedHashMapEntry = (LinkedHashMapEntry) node;
        LinkedHashMapEntry linkedHashMapEntry2 = linkedHashMapEntry.before;
        LinkedHashMapEntry linkedHashMapEntry3 = linkedHashMapEntry.after;
        linkedHashMapEntry.after = null;
        linkedHashMapEntry.before = null;
        if (linkedHashMapEntry2 == null) {
            this.head = linkedHashMapEntry3;
        } else {
            linkedHashMapEntry2.after = linkedHashMapEntry3;
        }
        if (linkedHashMapEntry3 == null) {
            this.tail = linkedHashMapEntry2;
        } else {
            linkedHashMapEntry3.before = linkedHashMapEntry2;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void afterNodeInsertion(boolean z) {
        LinkedHashMapEntry linkedHashMapEntry;
        if (z && (linkedHashMapEntry = this.head) != null && removeEldestEntry(linkedHashMapEntry)) {
            Object obj = linkedHashMapEntry.key;
            removeNode(HashMap.hash(obj), obj, null, false, true);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // java.util.HashMap
    public void afterNodeAccess(HashMap.Node node) {
        LinkedHashMapEntry linkedHashMapEntry;
        if (this.accessOrder && (linkedHashMapEntry = this.tail) != node) {
            LinkedHashMapEntry linkedHashMapEntry2 = (LinkedHashMapEntry) node;
            LinkedHashMapEntry linkedHashMapEntry3 = linkedHashMapEntry2.before;
            LinkedHashMapEntry linkedHashMapEntry4 = linkedHashMapEntry2.after;
            linkedHashMapEntry2.after = null;
            if (linkedHashMapEntry3 == null) {
                this.head = linkedHashMapEntry4;
            } else {
                linkedHashMapEntry3.after = linkedHashMapEntry4;
            }
            if (linkedHashMapEntry4 != null) {
                linkedHashMapEntry4.before = linkedHashMapEntry3;
            } else {
                linkedHashMapEntry = linkedHashMapEntry3;
            }
            if (linkedHashMapEntry == null) {
                this.head = linkedHashMapEntry2;
            } else {
                linkedHashMapEntry2.before = linkedHashMapEntry;
                linkedHashMapEntry.after = linkedHashMapEntry2;
            }
            this.tail = linkedHashMapEntry2;
            this.modCount++;
        }
    }

    public LinkedHashMap(int i, float f) {
        super(i, f);
        this.accessOrder = false;
    }

    public LinkedHashMap(int i) {
        super(i);
        this.accessOrder = false;
    }

    public LinkedHashMap() {
        this.accessOrder = false;
    }

    public LinkedHashMap(Map map) {
        this.accessOrder = false;
        putMapEntries(map, false);
    }

    public LinkedHashMap(int i, float f, boolean z) {
        super(i, f);
        this.accessOrder = z;
    }

    @Override // java.util.AbstractMap, java.util.HashMap
    public boolean containsValue(Object obj) {
        for (LinkedHashMapEntry linkedHashMapEntry = this.head; linkedHashMapEntry != null; linkedHashMapEntry = linkedHashMapEntry.after) {
            Object obj2 = linkedHashMapEntry.value;
            if (obj2 == obj) {
                return true;
            }
            if (obj != null && obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Object get(Object obj) {
        HashMap.Node node = getNode(HashMap.hash(obj), obj);
        if (node == null) {
            return null;
        }
        if (this.accessOrder) {
            afterNodeAccess(node);
        }
        return node.value;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public void clear() {
        super.clear();
        this.tail = null;
        this.head = null;
    }

    public Map.Entry eldest() {
        return this.head;
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        LinkedKeySet linkedKeySet = new LinkedKeySet();
        this.keySet = linkedKeySet;
        return linkedKeySet;
    }

    final class LinkedKeySet extends AbstractSet {
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
        public final Iterator iterator() {
            return new LinkedKeyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            return LinkedHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            return LinkedHashMap.this.removeNode(HashMap.hash(obj), obj, null, false, true) != null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Collection values() {
        Collection collection = this.values;
        if (collection != null) {
            return collection;
        }
        LinkedValues linkedValues = new LinkedValues();
        this.values = linkedValues;
        return linkedValues;
    }

    final class LinkedValues extends AbstractCollection {
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
        public final Iterator iterator() {
            return new LinkedValueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            return LinkedHashMap.this.containsValue(obj);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, java.util.HashMap
    public Set entrySet() {
        Set set = this.entrySet;
        if (set != null) {
            return set;
        }
        LinkedEntrySet linkedEntrySet = new LinkedEntrySet();
        this.entrySet = linkedEntrySet;
        return linkedEntrySet;
    }

    final class LinkedEntrySet extends AbstractSet {
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
        public final Iterator iterator() {
            return new LinkedEntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            HashMap.Node node = LinkedHashMap.this.getNode(HashMap.hash(key), key);
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
            if (LinkedHashMap.this.removeNode(HashMap.hash(key), key, entry.getValue(), true, true) != null) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract class LinkedHashIterator {
        LinkedHashMapEntry current = null;
        int expectedModCount;
        LinkedHashMapEntry next;

        LinkedHashIterator() {
            this.next = LinkedHashMap.this.head;
            this.expectedModCount = LinkedHashMap.this.modCount;
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        /* access modifiers changed from: package-private */
        public final LinkedHashMapEntry nextNode() {
            LinkedHashMapEntry linkedHashMapEntry = this.next;
            if (LinkedHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (linkedHashMapEntry != null) {
                this.current = linkedHashMapEntry;
                this.next = linkedHashMapEntry.after;
                return linkedHashMapEntry;
            } else {
                throw new NoSuchElementException();
            }
        }

        public final void remove() {
            LinkedHashMapEntry linkedHashMapEntry = this.current;
            if (linkedHashMapEntry != null) {
                LinkedHashMap linkedHashMap = LinkedHashMap.this;
                if (linkedHashMap.modCount == this.expectedModCount) {
                    this.current = null;
                    Object obj = linkedHashMapEntry.key;
                    linkedHashMap.removeNode(HashMap.hash(obj), obj, null, false, false);
                    this.expectedModCount = LinkedHashMap.this.modCount;
                    return;
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException();
        }
    }

    final class LinkedKeyIterator extends LinkedHashIterator implements Iterator {
        LinkedKeyIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Object next() {
            return nextNode().getKey();
        }
    }

    final class LinkedValueIterator extends LinkedHashIterator implements Iterator {
        LinkedValueIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Object next() {
            return nextNode().value;
        }
    }

    final class LinkedEntryIterator extends LinkedHashIterator implements Iterator {
        LinkedEntryIterator() {
            super();
        }

        @Override // java.util.Iterator
        public final Map.Entry next() {
            return nextNode();
        }
    }
}
