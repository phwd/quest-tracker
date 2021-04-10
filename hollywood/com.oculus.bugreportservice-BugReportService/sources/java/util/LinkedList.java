package java.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;

public class LinkedList extends AbstractSequentialList implements List, Deque, Cloneable, Serializable {
    private static final long serialVersionUID = 876323262645176354L;
    transient Node first;
    transient Node last;
    transient int size = 0;

    /* access modifiers changed from: package-private */
    public void linkLast(Object obj) {
        Node node = this.last;
        Node node2 = new Node(node, obj, null);
        this.last = node2;
        if (node == null) {
            this.first = node2;
        } else {
            node.next = node2;
        }
        this.size++;
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    public void linkBefore(Object obj, Node node) {
        Node node2 = node.prev;
        Node node3 = new Node(node2, obj, node);
        node.prev = node3;
        if (node2 == null) {
            this.first = node3;
        } else {
            node2.next = node3;
        }
        this.size++;
        this.modCount++;
    }

    private Object unlinkFirst(Node node) {
        Object obj = node.item;
        Node node2 = node.next;
        node.item = null;
        node.next = null;
        this.first = node2;
        if (node2 == null) {
            this.last = null;
        } else {
            node2.prev = null;
        }
        this.size--;
        this.modCount++;
        return obj;
    }

    /* access modifiers changed from: package-private */
    public Object unlink(Node node) {
        Object obj = node.item;
        Node node2 = node.next;
        Node node3 = node.prev;
        if (node3 == null) {
            this.first = node2;
        } else {
            node3.next = node2;
            node.prev = null;
        }
        if (node2 == null) {
            this.last = node3;
        } else {
            node2.prev = node3;
            node.next = null;
        }
        node.item = null;
        this.size--;
        this.modCount++;
        return obj;
    }

    @Override // java.util.Deque
    public Object removeFirst() {
        Node node = this.first;
        if (node != null) {
            return unlinkFirst(node);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.util.Deque
    public boolean add(Object obj) {
        linkLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.Deque
    public boolean remove(Object obj) {
        if (obj == null) {
            for (Node node = this.first; node != null; node = node.next) {
                if (node.item == null) {
                    unlink(node);
                    return true;
                }
            }
            return false;
        }
        for (Node node2 = this.first; node2 != null; node2 = node2.next) {
            if (obj.equals(node2.item)) {
                unlink(node2);
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public boolean addAll(Collection collection) {
        return addAll(this.size, collection);
    }

    @Override // java.util.AbstractList
    public boolean addAll(int i, Collection collection) {
        Node node;
        Node node2;
        checkPositionIndex(i);
        Object[] array = collection.toArray();
        int length = array.length;
        int i2 = 0;
        if (length == 0) {
            return false;
        }
        if (i == this.size) {
            node = this.last;
            node2 = null;
        } else {
            Node node3 = node(i);
            node2 = node3;
            node = node3.prev;
        }
        int length2 = array.length;
        while (i2 < length2) {
            Node node4 = new Node(node, array[i2], null);
            if (node == null) {
                this.first = node4;
            } else {
                node.next = node4;
            }
            i2++;
            node = node4;
        }
        if (node2 == null) {
            this.last = node;
        } else {
            node.next = node2;
            node2.prev = node;
        }
        this.size += length;
        this.modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public void clear() {
        Node node = this.first;
        while (node != null) {
            Node node2 = node.next;
            node.item = null;
            node.next = null;
            node.prev = null;
            node = node2;
        }
        this.last = null;
        this.first = null;
        this.size = 0;
        this.modCount++;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object get(int i) {
        checkElementIndex(i);
        return node(i).item;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object set(int i, Object obj) {
        checkElementIndex(i);
        Node node = node(i);
        Object obj2 = node.item;
        node.item = obj;
        return obj2;
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int i, Object obj) {
        checkPositionIndex(i);
        if (i == this.size) {
            linkLast(obj);
        } else {
            linkBefore(obj, node(i));
        }
    }

    @Override // java.util.List, java.util.AbstractList
    public Object remove(int i) {
        checkElementIndex(i);
        return unlink(node(i));
    }

    private boolean isElementIndex(int i) {
        return i >= 0 && i < this.size;
    }

    private boolean isPositionIndex(int i) {
        return i >= 0 && i <= this.size;
    }

    private String outOfBoundsMsg(int i) {
        return "Index: " + i + ", Size: " + this.size;
    }

    private void checkElementIndex(int i) {
        if (!isElementIndex(i)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        }
    }

    private void checkPositionIndex(int i) {
        if (!isPositionIndex(i)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(i));
        }
    }

    /* access modifiers changed from: package-private */
    public Node node(int i) {
        int i2 = this.size;
        if (i < (i2 >> 1)) {
            Node node = this.first;
            for (int i3 = 0; i3 < i; i3++) {
                node = node.next;
            }
            return node;
        }
        Node node2 = this.last;
        for (int i4 = i2 - 1; i4 > i; i4--) {
            node2 = node2.prev;
        }
        return node2;
    }

    @Override // java.util.List, java.util.AbstractList
    public int indexOf(Object obj) {
        int i = 0;
        if (obj == null) {
            for (Node node = this.first; node != null; node = node.next) {
                if (node.item == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        for (Node node2 = this.first; node2 != null; node2 = node2.next) {
            if (obj.equals(node2.item)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // java.util.Deque
    public Object poll() {
        Node node = this.first;
        if (node == null) {
            return null;
        }
        return unlinkFirst(node);
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator listIterator(int i) {
        checkPositionIndex(i);
        return new ListItr(i);
    }

    private class ListItr implements ListIterator {
        private int expectedModCount = LinkedList.this.modCount;
        private Node lastReturned;
        private Node next;
        private int nextIndex;

        ListItr(int i) {
            this.next = i == LinkedList.this.size ? null : LinkedList.this.node(i);
            this.nextIndex = i;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return this.nextIndex < LinkedList.this.size;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public Object next() {
            checkForComodification();
            if (hasNext()) {
                Node node = this.next;
                this.lastReturned = node;
                this.next = node.next;
                this.nextIndex++;
                return this.lastReturned.item;
            }
            throw new NoSuchElementException();
        }

        public boolean hasPrevious() {
            return this.nextIndex > 0;
        }

        @Override // java.util.ListIterator
        public Object previous() {
            checkForComodification();
            if (hasPrevious()) {
                Node node = this.next;
                Node node2 = node == null ? LinkedList.this.last : node.prev;
                this.next = node2;
                this.lastReturned = node2;
                this.nextIndex--;
                return this.lastReturned.item;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.nextIndex - 1;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public void remove() {
            checkForComodification();
            Node node = this.lastReturned;
            if (node != null) {
                Node node2 = node.next;
                LinkedList.this.unlink(node);
                if (this.next == this.lastReturned) {
                    this.next = node2;
                } else {
                    this.nextIndex--;
                }
                this.lastReturned = null;
                this.expectedModCount++;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            if (this.lastReturned != null) {
                checkForComodification();
                this.lastReturned.item = obj;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            checkForComodification();
            this.lastReturned = null;
            Node node = this.next;
            if (node == null) {
                LinkedList.this.linkLast(obj);
            } else {
                LinkedList.this.linkBefore(obj, node);
            }
            this.nextIndex++;
            this.expectedModCount++;
        }

        /* access modifiers changed from: package-private */
        public final void checkForComodification() {
            if (LinkedList.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* access modifiers changed from: private */
    public static class Node {
        Object item;
        Node next;
        Node prev;

        Node(Node node, Object obj, Node node2) {
            this.item = obj;
            this.next = node2;
            this.prev = node;
        }
    }

    private LinkedList superClone() {
        try {
            return (LinkedList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    public Object clone() {
        LinkedList superClone = superClone();
        superClone.last = null;
        superClone.first = null;
        superClone.size = 0;
        superClone.modCount = 0;
        for (Node node = this.first; node != null; node = node.next) {
            superClone.add(node.item);
        }
        return superClone;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public Object[] toArray() {
        Object[] objArr = new Object[this.size];
        Node node = this.first;
        int i = 0;
        while (node != null) {
            objArr[i] = node.item;
            node = node.next;
            i++;
        }
        return objArr;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
    public Object[] toArray(Object[] objArr) {
        if (objArr.length < this.size) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.size);
        }
        int i = 0;
        Node node = this.first;
        while (node != null) {
            objArr[i] = node.item;
            node = node.next;
            i++;
        }
        int length = objArr.length;
        int i2 = this.size;
        if (length > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
