package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public class LinkedListMultimap<K, V> extends AbstractMultimap<K, V> implements ListMultimap<K, V>, Serializable {
    @GwtIncompatible("java serialization not supported")
    private static final long serialVersionUID = 0;
    private transient Node<K, V> head;
    private transient Map<K, KeyList<K, V>> keyToKeyList;
    private transient int modCount;
    private transient int size;
    private transient Node<K, V> tail;

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean containsEntry(@Nullable Object obj, @Nullable Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean putAll(Multimap multimap) {
        return super.putAll(multimap);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean putAll(@Nullable Object obj, Iterable iterable) {
        return super.putAll(obj, iterable);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public /* bridge */ /* synthetic */ boolean remove(@Nullable Object obj, @Nullable Object obj2) {
        return super.remove(obj, obj2);
    }

    @Override // com.google.common.collect.AbstractMultimap
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* access modifiers changed from: private */
    public static final class Node<K, V> extends AbstractMapEntry<K, V> {
        final K key;
        Node<K, V> next;
        Node<K, V> nextSibling;
        Node<K, V> previous;
        Node<K, V> previousSibling;
        V value;

        Node(@Nullable K key2, @Nullable V value2) {
            this.key = key2;
            this.value = value2;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(@Nullable V newValue) {
            V result = this.value;
            this.value = newValue;
            return result;
        }
    }

    /* access modifiers changed from: private */
    public static class KeyList<K, V> {
        int count = 1;
        Node<K, V> head;
        Node<K, V> tail;

        KeyList(Node<K, V> firstNode) {
            this.head = firstNode;
            this.tail = firstNode;
            firstNode.previousSibling = null;
            firstNode.nextSibling = null;
        }
    }

    public static <K, V> LinkedListMultimap<K, V> create() {
        return new LinkedListMultimap<>();
    }

    public static <K, V> LinkedListMultimap<K, V> create(int expectedKeys) {
        return new LinkedListMultimap<>(expectedKeys);
    }

    public static <K, V> LinkedListMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new LinkedListMultimap<>(multimap);
    }

    LinkedListMultimap() {
        this.keyToKeyList = Maps.newHashMap();
    }

    private LinkedListMultimap(int expectedKeys) {
        this.keyToKeyList = new HashMap(expectedKeys);
    }

    private LinkedListMultimap(Multimap<? extends K, ? extends V> multimap) {
        this(multimap.keySet().size());
        putAll(multimap);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Node<K, V> addNode(@Nullable K key, @Nullable V value, @Nullable Node<K, V> nextSibling) {
        Node<K, V> node = new Node<>(key, value);
        if (this.head == null) {
            this.tail = node;
            this.head = node;
            this.keyToKeyList.put(key, new KeyList<>(node));
            this.modCount++;
        } else if (nextSibling == null) {
            Node<K, V> node2 = this.tail;
            node2.next = node;
            node.previous = node2;
            this.tail = node;
            KeyList<K, V> keyList = this.keyToKeyList.get(key);
            if (keyList == null) {
                this.keyToKeyList.put(key, new KeyList<>(node));
                this.modCount++;
            } else {
                keyList.count++;
                Node<K, V> keyTail = keyList.tail;
                keyTail.nextSibling = node;
                node.previousSibling = keyTail;
                keyList.tail = node;
            }
        } else {
            this.keyToKeyList.get(key).count++;
            node.previous = nextSibling.previous;
            node.previousSibling = nextSibling.previousSibling;
            node.next = nextSibling;
            node.nextSibling = nextSibling;
            if (nextSibling.previousSibling == null) {
                this.keyToKeyList.get(key).head = node;
            } else {
                nextSibling.previousSibling.nextSibling = node;
            }
            if (nextSibling.previous == null) {
                this.head = node;
            } else {
                nextSibling.previous.next = node;
            }
            nextSibling.previous = node;
            nextSibling.previousSibling = node;
        }
        this.size++;
        return node;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeNode(Node<K, V> node) {
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            this.head = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            this.tail = node.previous;
        }
        if (node.previousSibling == null && node.nextSibling == null) {
            this.keyToKeyList.remove(node.key).count = 0;
            this.modCount++;
        } else {
            KeyList<K, V> keyList = this.keyToKeyList.get(node.key);
            keyList.count--;
            if (node.previousSibling == null) {
                keyList.head = node.nextSibling;
            } else {
                node.previousSibling.nextSibling = node.nextSibling;
            }
            if (node.nextSibling == null) {
                keyList.tail = node.previousSibling;
            } else {
                node.nextSibling.previousSibling = node.previousSibling;
            }
        }
        this.size--;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeAllNodes(@Nullable Object key) {
        Iterators.clear(new ValueForKeyIterator(key));
    }

    /* access modifiers changed from: private */
    public static void checkElement(@Nullable Object node) {
        if (node == null) {
            throw new NoSuchElementException();
        }
    }

    private class NodeIterator implements ListIterator<Map.Entry<K, V>> {
        Node<K, V> current;
        int expectedModCount = LinkedListMultimap.this.modCount;
        Node<K, V> next;
        int nextIndex;
        Node<K, V> previous;

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void add(Object obj) {
            add((Map.Entry) ((Map.Entry) obj));
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(Object obj) {
            set((Map.Entry) ((Map.Entry) obj));
        }

        NodeIterator(int index) {
            int size = LinkedListMultimap.this.size();
            Preconditions.checkPositionIndex(index, size);
            if (index < size / 2) {
                this.next = LinkedListMultimap.this.head;
                while (true) {
                    int index2 = index - 1;
                    if (index <= 0) {
                        break;
                    }
                    next();
                    index = index2;
                }
            } else {
                this.previous = LinkedListMultimap.this.tail;
                this.nextIndex = size;
                while (true) {
                    int index3 = index + 1;
                    if (index >= size) {
                        break;
                    }
                    previous();
                    index = index3;
                }
            }
            this.current = null;
        }

        private void checkForConcurrentModification() {
            if (LinkedListMultimap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasNext() {
            checkForConcurrentModification();
            return this.next != null;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public Node<K, V> next() {
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.next);
            Node<K, V> node = this.next;
            this.current = node;
            this.previous = node;
            this.next = node.next;
            this.nextIndex++;
            return this.current;
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.current != null);
            Node<K, V> node = this.current;
            if (node != this.next) {
                this.previous = node.previous;
                this.nextIndex--;
            } else {
                this.next = node.next;
            }
            LinkedListMultimap.this.removeNode(this.current);
            this.current = null;
            this.expectedModCount = LinkedListMultimap.this.modCount;
        }

        public boolean hasPrevious() {
            checkForConcurrentModification();
            return this.previous != null;
        }

        @Override // java.util.ListIterator
        public Node<K, V> previous() {
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.previous);
            Node<K, V> node = this.previous;
            this.current = node;
            this.next = node;
            this.previous = node.previous;
            this.nextIndex--;
            return this.current;
        }

        public int nextIndex() {
            return this.nextIndex;
        }

        public int previousIndex() {
            return this.nextIndex - 1;
        }

        public void set(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public void add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        public void setValue(V value) {
            Preconditions.checkState(this.current != null);
            this.current.value = value;
        }
    }

    private class DistinctKeyIterator implements Iterator<K> {
        Node<K, V> current;
        int expectedModCount;
        Node<K, V> next;
        final Set<K> seenKeys;

        private DistinctKeyIterator() {
            this.seenKeys = Sets.newHashSetWithExpectedSize(LinkedListMultimap.this.keySet().size());
            this.next = LinkedListMultimap.this.head;
            this.expectedModCount = LinkedListMultimap.this.modCount;
        }

        private void checkForConcurrentModification() {
            if (LinkedListMultimap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasNext() {
            checkForConcurrentModification();
            return this.next != null;
        }

        @Override // java.util.Iterator
        public K next() {
            Node<K, V> node;
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.next);
            this.current = this.next;
            this.seenKeys.add(this.current.key);
            do {
                this.next = this.next.next;
                node = this.next;
                if (node == null) {
                    break;
                }
            } while (!this.seenKeys.add(node.key));
            return this.current.key;
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.current != null);
            LinkedListMultimap.this.removeAllNodes(this.current.key);
            this.current = null;
            this.expectedModCount = LinkedListMultimap.this.modCount;
        }
    }

    /* access modifiers changed from: private */
    public class ValueForKeyIterator implements ListIterator<V> {
        Node<K, V> current;
        final Object key;
        Node<K, V> next;
        int nextIndex;
        Node<K, V> previous;

        ValueForKeyIterator(@Nullable Object key2) {
            this.key = key2;
            KeyList<K, V> keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(key2);
            this.next = keyList == null ? null : keyList.head;
        }

        public ValueForKeyIterator(@Nullable Object key2, int index) {
            KeyList<K, V> keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(key2);
            int size = keyList == null ? 0 : keyList.count;
            Preconditions.checkPositionIndex(index, size);
            if (index < size / 2) {
                this.next = keyList == null ? null : keyList.head;
                while (true) {
                    int index2 = index - 1;
                    if (index <= 0) {
                        break;
                    }
                    next();
                    index = index2;
                }
            } else {
                this.previous = keyList == null ? null : keyList.tail;
                this.nextIndex = size;
                while (true) {
                    int index3 = index + 1;
                    if (index >= size) {
                        break;
                    }
                    previous();
                    index = index3;
                }
            }
            this.key = key2;
            this.current = null;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public V next() {
            LinkedListMultimap.checkElement(this.next);
            Node<K, V> node = this.next;
            this.current = node;
            this.previous = node;
            this.next = node.nextSibling;
            this.nextIndex++;
            return this.current.value;
        }

        public boolean hasPrevious() {
            return this.previous != null;
        }

        @Override // java.util.ListIterator
        public V previous() {
            LinkedListMultimap.checkElement(this.previous);
            Node<K, V> node = this.previous;
            this.current = node;
            this.next = node;
            this.previous = node.previousSibling;
            this.nextIndex--;
            return this.current.value;
        }

        public int nextIndex() {
            return this.nextIndex;
        }

        public int previousIndex() {
            return this.nextIndex - 1;
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.current != null);
            Node<K, V> node = this.current;
            if (node != this.next) {
                this.previous = node.previousSibling;
                this.nextIndex--;
            } else {
                this.next = node.nextSibling;
            }
            LinkedListMultimap.this.removeNode(this.current);
            this.current = null;
        }

        @Override // java.util.ListIterator
        public void set(V value) {
            Preconditions.checkState(this.current != null);
            this.current.value = value;
        }

        @Override // java.util.ListIterator
        public void add(V value) {
            this.previous = LinkedListMultimap.this.addNode(this.key, value, this.next);
            this.nextIndex++;
            this.current = null;
        }
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@Nullable Object key) {
        return this.keyToKeyList.containsKey(key);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean containsValue(@Nullable Object value) {
        return values().contains(value);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean put(@Nullable K key, @Nullable V value) {
        addNode(key, value, null);
        return true;
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public List<V> replaceValues(@Nullable K key, Iterable<? extends V> values) {
        List<V> oldValues = getCopy(key);
        ValueForKeyIterator valueForKeyIterator = new ValueForKeyIterator(key);
        Iterator<? extends V> newValues = values.iterator();
        while (valueForKeyIterator.hasNext() && newValues.hasNext()) {
            valueForKeyIterator.next();
            valueForKeyIterator.set(newValues.next());
        }
        while (valueForKeyIterator.hasNext()) {
            valueForKeyIterator.next();
            valueForKeyIterator.remove();
        }
        while (newValues.hasNext()) {
            valueForKeyIterator.add(newValues.next());
        }
        return oldValues;
    }

    private List<V> getCopy(@Nullable Object key) {
        return Collections.unmodifiableList(Lists.newArrayList(new ValueForKeyIterator(key)));
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
    public List<V> removeAll(@Nullable Object key) {
        List<V> oldValues = getCopy(key);
        removeAllNodes(key);
        return oldValues;
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        this.head = null;
        this.tail = null;
        this.keyToKeyList.clear();
        this.size = 0;
        this.modCount++;
    }

    @Override // com.google.common.collect.ListMultimap, com.google.common.collect.Multimap
    public List<V> get(@Nullable final K key) {
        return new AbstractSequentialList<V>() {
            /* class com.google.common.collect.LinkedListMultimap.AnonymousClass1 */

            public int size() {
                KeyList<K, V> keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(key);
                if (keyList == null) {
                    return 0;
                }
                return keyList.count;
            }

            @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
            public ListIterator<V> listIterator(int index) {
                return new ValueForKeyIterator(key, index);
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Set<K> createKeySet() {
        return new Sets.ImprovedAbstractSet<K>() {
            /* class com.google.common.collect.LinkedListMultimap.AnonymousClass2 */

            public int size() {
                return LinkedListMultimap.this.keyToKeyList.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
            public Iterator<K> iterator() {
                return new DistinctKeyIterator();
            }

            public boolean contains(Object key) {
                return LinkedListMultimap.this.containsKey(key);
            }

            public boolean remove(Object o) {
                return !LinkedListMultimap.this.removeAll(o).isEmpty();
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public List<V> values() {
        return (List) super.values();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public List<V> createValues() {
        return new AbstractSequentialList<V>() {
            /* class com.google.common.collect.LinkedListMultimap.AnonymousClass3 */

            public int size() {
                return LinkedListMultimap.this.size;
            }

            @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
            public ListIterator<V> listIterator(int index) {
                final LinkedListMultimap<K, V>.NodeIterator nodeItr = new NodeIterator(index);
                return new TransformedListIterator<Map.Entry<K, V>, V>(nodeItr) {
                    /* class com.google.common.collect.LinkedListMultimap.AnonymousClass3.AnonymousClass1 */

                    /* access modifiers changed from: package-private */
                    @Override // com.google.common.collect.TransformedIterator
                    public /* bridge */ /* synthetic */ Object transform(Object obj) {
                        return transform((Map.Entry) ((Map.Entry) obj));
                    }

                    /* access modifiers changed from: package-private */
                    public V transform(Map.Entry<K, V> entry) {
                        return entry.getValue();
                    }

                    @Override // java.util.ListIterator, com.google.common.collect.TransformedListIterator
                    public void set(V value) {
                        nodeItr.setValue(value);
                    }
                };
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public List<Map.Entry<K, V>> entries() {
        return (List) super.entries();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public List<Map.Entry<K, V>> createEntries() {
        return new AbstractSequentialList<Map.Entry<K, V>>() {
            /* class com.google.common.collect.LinkedListMultimap.AnonymousClass4 */

            public int size() {
                return LinkedListMultimap.this.size;
            }

            @Override // java.util.List, java.util.AbstractList, java.util.AbstractSequentialList
            public ListIterator<Map.Entry<K, V>> listIterator(int index) {
                return new NodeIterator(index);
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Iterator<Map.Entry<K, V>> entryIterator() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultimap
    public Map<K, Collection<V>> createAsMap() {
        return new Multimaps.AsMap(this);
    }

    @GwtIncompatible("java.io.ObjectOutputStream")
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(size());
        for (Map.Entry<K, V> entry : entries()) {
            stream.writeObject(entry.getKey());
            stream.writeObject(entry.getValue());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.LinkedListMultimap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible("java.io.ObjectInputStream")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.keyToKeyList = Maps.newLinkedHashMap();
        int size2 = stream.readInt();
        for (int i = 0; i < size2; i++) {
            put(stream.readObject(), stream.readObject());
        }
    }
}
