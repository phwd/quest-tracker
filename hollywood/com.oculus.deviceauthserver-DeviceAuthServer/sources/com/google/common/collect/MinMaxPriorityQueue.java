package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

@Beta
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    private final MinMaxPriorityQueue<E>.Heap maxHeap;
    @VisibleForTesting
    final int maximumSize;
    private final MinMaxPriorityQueue<E>.Heap minHeap;
    private int modCount;
    private Object[] queue;
    private int size;

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> initialContents) {
        return new Builder(Ordering.natural()).create(initialContents);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    public static Builder<Comparable> expectedSize(int expectedSize) {
        return new Builder(Ordering.natural()).expectedSize(expectedSize);
    }

    public static Builder<Comparable> maximumSize(int maximumSize2) {
        return new Builder(Ordering.natural()).maximumSize(maximumSize2);
    }

    @Beta
    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        private int maximumSize;

        private Builder(Comparator<B> comparator2) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        public Builder<B> expectedSize(int expectedSize2) {
            Preconditions.checkArgument(expectedSize2 >= 0);
            this.expectedSize = expectedSize2;
            return this;
        }

        public Builder<B> maximumSize(int maximumSize2) {
            Preconditions.checkArgument(maximumSize2 > 0);
            this.maximumSize = maximumSize2;
            return this;
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.MinMaxPriorityQueue<T extends B> */
        /* JADX WARN: Multi-variable type inference failed */
        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> initialContents) {
            MinMaxPriorityQueue<T> queue = (MinMaxPriorityQueue<T>) new MinMaxPriorityQueue(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, initialContents));
            Iterator<? extends T> it = initialContents.iterator();
            while (it.hasNext()) {
                queue.offer(it.next());
            }
            return queue;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int queueSize) {
        Ordering<E> ordering = builder.ordering();
        this.minHeap = new Heap(ordering);
        this.maxHeap = new Heap(ordering.reverse());
        MinMaxPriorityQueue<E>.Heap heap = this.minHeap;
        MinMaxPriorityQueue<E>.Heap heap2 = this.maxHeap;
        heap.otherHeap = heap2;
        heap2.otherHeap = heap;
        this.maximumSize = ((Builder) builder).maximumSize;
        this.queue = new Object[queueSize];
    }

    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue, java.util.Queue
    public boolean add(E element) {
        offer(element);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.MinMaxPriorityQueue<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.AbstractQueue
    public boolean addAll(Collection<? extends E> newElements) {
        boolean modified = false;
        Iterator<? extends E> it = newElements.iterator();
        while (it.hasNext()) {
            offer(it.next());
            modified = true;
        }
        return modified;
    }

    @Override // java.util.Queue
    public boolean offer(E element) {
        Preconditions.checkNotNull(element);
        this.modCount++;
        int insertIndex = this.size;
        this.size = insertIndex + 1;
        growIfNeeded();
        heapForIndex(insertIndex).bubbleUp(insertIndex, element);
        if (this.size <= this.maximumSize || pollLast() != element) {
            return true;
        }
        return false;
    }

    @Override // java.util.Queue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    /* access modifiers changed from: package-private */
    public E elementData(int index) {
        return (E) this.queue[index];
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData(0);
    }

    private int getMaxElementIndex() {
        int i = this.size;
        if (i != 1) {
            return (i == 2 || this.maxHeap.compareElements(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    public E pollFirst() {
        return poll();
    }

    public E removeFirst() {
        return remove();
    }

    public E peekFirst() {
        return peek();
    }

    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    public E removeLast() {
        if (!isEmpty()) {
            return removeAndGet(getMaxElementIndex());
        }
        throw new NoSuchElementException();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return elementData(getMaxElementIndex());
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public MoveDesc<E> removeAt(int index) {
        Preconditions.checkPositionIndex(index, this.size);
        this.modCount++;
        this.size--;
        int i = this.size;
        if (i == index) {
            this.queue[i] = null;
            return null;
        }
        E actualLastElement = elementData(i);
        int lastElementAt = heapForIndex(this.size).getCorrectLastElement(actualLastElement);
        E toTrickle = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> changes = fillHole(index, toTrickle);
        if (lastElementAt >= index) {
            return changes;
        }
        if (changes == null) {
            return new MoveDesc<>(actualLastElement, toTrickle);
        }
        return new MoveDesc<>(actualLastElement, changes.replaced);
    }

    private MoveDesc<E> fillHole(int index, E toTrickle) {
        MinMaxPriorityQueue<E>.Heap heap = heapForIndex(index);
        int vacated = heap.fillHoleAt(index);
        int bubbledTo = heap.bubbleUpAlternatingLevels(vacated, toTrickle);
        if (bubbledTo == vacated) {
            return heap.tryCrossOverAndBubbleUp(index, vacated, toTrickle);
        }
        if (bubbledTo < index) {
            return new MoveDesc<>(toTrickle, elementData(index));
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public static class MoveDesc<E> {
        final E replaced;
        final E toTrickle;

        MoveDesc(E toTrickle2, E replaced2) {
            this.toTrickle = toTrickle2;
            this.replaced = replaced2;
        }
    }

    private E removeAndGet(int index) {
        E value = elementData(index);
        removeAt(index);
        return value;
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int i) {
        return isEvenLevel(i) ? this.minHeap : this.maxHeap;
    }

    @VisibleForTesting
    static boolean isEvenLevel(int index) {
        int oneBased = index + 1;
        Preconditions.checkState(oneBased > 0, "negative index");
        return (EVEN_POWERS_OF_TWO & oneBased) > (ODD_POWERS_OF_TWO & oneBased);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isIntact() {
        for (int i = 1; i < this.size; i++) {
            if (!heapForIndex(i).verifyIndex(i)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public class Heap {
        final Ordering<E> ordering;
        MinMaxPriorityQueue<E>.Heap otherHeap;

        Heap(Ordering<E> ordering2) {
            this.ordering = ordering2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.Ordering<E> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public int compareElements(int a, int b) {
            return this.ordering.compare(MinMaxPriorityQueue.this.elementData(a), MinMaxPriorityQueue.this.elementData(b));
        }

        /* access modifiers changed from: package-private */
        public MoveDesc<E> tryCrossOverAndBubbleUp(int removeIndex, int vacated, E toTrickle) {
            Object obj;
            int crossOver = crossOver(vacated, toTrickle);
            if (crossOver == vacated) {
                return null;
            }
            if (crossOver < removeIndex) {
                obj = MinMaxPriorityQueue.this.elementData(removeIndex);
            } else {
                obj = MinMaxPriorityQueue.this.elementData(getParentIndex(removeIndex));
            }
            if (this.otherHeap.bubbleUpAlternatingLevels(crossOver, toTrickle) < removeIndex) {
                return new MoveDesc<>(toTrickle, obj);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public void bubbleUp(int index, E x) {
            Heap heap;
            int crossOver = crossOverUp(index, x);
            if (crossOver == index) {
                heap = this;
            } else {
                index = crossOver;
                heap = this.otherHeap;
            }
            heap.bubbleUpAlternatingLevels(index, x);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.Ordering<E> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public int bubbleUpAlternatingLevels(int index, E x) {
            while (index > 2) {
                int grandParentIndex = getGrandparentIndex(index);
                Object elementData = MinMaxPriorityQueue.this.elementData(grandParentIndex);
                if (this.ordering.compare(elementData, x) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[index] = elementData;
                index = grandParentIndex;
            }
            MinMaxPriorityQueue.this.queue[index] = x;
            return index;
        }

        /* access modifiers changed from: package-private */
        public int findMin(int index, int len) {
            if (index >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(index > 0);
            int limit = Math.min(index, MinMaxPriorityQueue.this.size - len) + len;
            int minIndex = index;
            for (int i = index + 1; i < limit; i++) {
                if (compareElements(i, minIndex) < 0) {
                    minIndex = i;
                }
            }
            return minIndex;
        }

        /* access modifiers changed from: package-private */
        public int findMinChild(int index) {
            return findMin(getLeftChildIndex(index), 2);
        }

        /* access modifiers changed from: package-private */
        public int findMinGrandChild(int index) {
            int leftChildIndex = getLeftChildIndex(index);
            if (leftChildIndex < 0) {
                return -1;
            }
            return findMin(getLeftChildIndex(leftChildIndex), 4);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.Ordering<E> */
        /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: com.google.common.collect.Ordering<E> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public int crossOverUp(int index, E x) {
            int uncleIndex;
            if (index == 0) {
                MinMaxPriorityQueue.this.queue[0] = x;
                return 0;
            }
            int parentIndex = getParentIndex(index);
            Object elementData = MinMaxPriorityQueue.this.elementData(parentIndex);
            if (!(parentIndex == 0 || (uncleIndex = getRightChildIndex(getParentIndex(parentIndex))) == parentIndex || getLeftChildIndex(uncleIndex) < MinMaxPriorityQueue.this.size)) {
                Object elementData2 = MinMaxPriorityQueue.this.elementData(uncleIndex);
                if (this.ordering.compare(elementData2, elementData) < 0) {
                    parentIndex = uncleIndex;
                    elementData = elementData2;
                }
            }
            if (this.ordering.compare(elementData, x) < 0) {
                MinMaxPriorityQueue.this.queue[index] = elementData;
                MinMaxPriorityQueue.this.queue[parentIndex] = x;
                return parentIndex;
            }
            MinMaxPriorityQueue.this.queue[index] = x;
            return index;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: com.google.common.collect.Ordering<E> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public int getCorrectLastElement(E actualLastElement) {
            int uncleIndex;
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (!(parentIndex == 0 || (uncleIndex = getRightChildIndex(getParentIndex(parentIndex))) == parentIndex || getLeftChildIndex(uncleIndex) < MinMaxPriorityQueue.this.size)) {
                Object elementData = MinMaxPriorityQueue.this.elementData(uncleIndex);
                if (this.ordering.compare(elementData, actualLastElement) < 0) {
                    MinMaxPriorityQueue.this.queue[uncleIndex] = actualLastElement;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = elementData;
                    return uncleIndex;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.google.common.collect.Ordering<E> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public int crossOver(int index, E x) {
            int minChildIndex = findMinChild(index);
            if (minChildIndex <= 0 || this.ordering.compare(MinMaxPriorityQueue.this.elementData(minChildIndex), x) >= 0) {
                return crossOverUp(index, x);
            }
            MinMaxPriorityQueue.this.queue[index] = MinMaxPriorityQueue.this.elementData(minChildIndex);
            MinMaxPriorityQueue.this.queue[minChildIndex] = x;
            return minChildIndex;
        }

        /* access modifiers changed from: package-private */
        public int fillHoleAt(int index) {
            while (true) {
                int minGrandchildIndex = findMinGrandChild(index);
                if (minGrandchildIndex <= 0) {
                    return index;
                }
                MinMaxPriorityQueue.this.queue[index] = MinMaxPriorityQueue.this.elementData(minGrandchildIndex);
                index = minGrandchildIndex;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean verifyIndex(int i) {
            if (getLeftChildIndex(i) < MinMaxPriorityQueue.this.size && compareElements(i, getLeftChildIndex(i)) > 0) {
                return false;
            }
            if (getRightChildIndex(i) < MinMaxPriorityQueue.this.size && compareElements(i, getRightChildIndex(i)) > 0) {
                return false;
            }
            if (i > 0 && compareElements(i, getParentIndex(i)) > 0) {
                return false;
            }
            if (i <= 2 || compareElements(getGrandparentIndex(i), i) <= 0) {
                return true;
            }
            return false;
        }

        private int getLeftChildIndex(int i) {
            return (i * 2) + 1;
        }

        private int getRightChildIndex(int i) {
            return (i * 2) + 2;
        }

        private int getParentIndex(int i) {
            return (i - 1) / 2;
        }

        private int getGrandparentIndex(int i) {
            return getParentIndex(getParentIndex(i));
        }
    }

    private class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        private Queue<E> forgetMeNot;
        private E lastFromForgetMeNot;
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        public boolean hasNext() {
            checkModCount();
            if (nextNotInSkipMe(this.cursor + 1) < MinMaxPriorityQueue.this.size()) {
                return true;
            }
            Queue<E> queue = this.forgetMeNot;
            if (queue == null || queue.isEmpty()) {
                return false;
            }
            return true;
        }

        @Override // java.util.Iterator
        public E next() {
            checkModCount();
            int tempCursor = nextNotInSkipMe(this.cursor + 1);
            if (tempCursor < MinMaxPriorityQueue.this.size()) {
                this.cursor = tempCursor;
                this.canRemove = true;
                return (E) MinMaxPriorityQueue.this.elementData(this.cursor);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                this.lastFromForgetMeNot = this.forgetMeNot.poll();
                E e = this.lastFromForgetMeNot;
                if (e != null) {
                    this.canRemove = true;
                    return e;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor < MinMaxPriorityQueue.this.size()) {
                MoveDesc<E> moved = MinMaxPriorityQueue.this.removeAt(this.cursor);
                if (moved != null) {
                    if (this.forgetMeNot == null) {
                        this.forgetMeNot = new ArrayDeque();
                        this.skipMe = new ArrayList(3);
                    }
                    this.forgetMeNot.add(moved.toTrickle);
                    this.skipMe.add(moved.replaced);
                }
                this.cursor--;
                return;
            }
            Preconditions.checkState(removeExact(this.lastFromForgetMeNot));
            this.lastFromForgetMeNot = null;
        }

        private boolean containsExact(Iterable<E> elements, E target) {
            for (E element : elements) {
                if (element == target) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean removeExact(Object target) {
            for (int i = 0; i < MinMaxPriorityQueue.this.size; i++) {
                if (MinMaxPriorityQueue.this.queue[i] == target) {
                    MinMaxPriorityQueue.this.removeAt(i);
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.MinMaxPriorityQueue$QueueIterator */
        /* JADX WARN: Multi-variable type inference failed */
        private int nextNotInSkipMe(int c) {
            if (this.skipMe != null) {
                while (c < MinMaxPriorityQueue.this.size() && containsExact(this.skipMe, MinMaxPriorityQueue.this.elementData(c))) {
                    c++;
                }
            }
            return c;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = null;
        }
        this.size = 0;
    }

    public Object[] toArray() {
        int i = this.size;
        Object[] copyTo = new Object[i];
        System.arraycopy(this.queue, 0, copyTo, 0, i);
        return copyTo;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public int capacity() {
        return this.queue.length;
    }

    @VisibleForTesting
    static int initialQueueSize(int configuredExpectedSize, int maximumSize2, Iterable<?> initialContents) {
        int result;
        if (configuredExpectedSize == -1) {
            result = 11;
        } else {
            result = configuredExpectedSize;
        }
        if (initialContents instanceof Collection) {
            result = Math.max(result, ((Collection) initialContents).size());
        }
        return capAtMaximumSize(result, maximumSize2);
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] newQueue = new Object[calculateNewCapacity()];
            Object[] objArr = this.queue;
            System.arraycopy(objArr, 0, newQueue, 0, objArr.length);
            this.queue = newQueue;
        }
    }

    private int calculateNewCapacity() {
        int newCapacity;
        int oldCapacity = this.queue.length;
        if (oldCapacity < 64) {
            newCapacity = (oldCapacity + 1) * 2;
        } else {
            newCapacity = IntMath.checkedMultiply(oldCapacity / 2, 3);
        }
        return capAtMaximumSize(newCapacity, this.maximumSize);
    }

    private static int capAtMaximumSize(int queueSize, int maximumSize2) {
        return Math.min(queueSize - 1, maximumSize2) + 1;
    }
}
