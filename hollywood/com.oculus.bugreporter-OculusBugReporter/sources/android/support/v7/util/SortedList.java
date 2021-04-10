package android.support.v7.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList<T> {
    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    T[] mData;
    private int mNewDataStart;
    private T[] mOldData;
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class<T> mTClass;

    public SortedList(@NonNull Class<T> klass, @NonNull Callback<T> callback) {
        this(klass, callback, 10);
    }

    public SortedList(@NonNull Class<T> klass, @NonNull Callback<T> callback, int initialCapacity) {
        this.mTClass = klass;
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) klass, initialCapacity));
        this.mCallback = callback;
        this.mSize = 0;
    }

    public int size() {
        return this.mSize;
    }

    public int add(T item) {
        throwIfInMutationOperation();
        return add(item, true);
    }

    public void addAll(@NonNull T[] items, boolean mayModifyInput) {
        throwIfInMutationOperation();
        if (items.length != 0) {
            if (mayModifyInput) {
                addAllInternal(items);
            } else {
                addAllInternal(copyArray(items));
            }
        }
    }

    public void addAll(@NonNull T... items) {
        addAll(items, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: android.support.v7.util.SortedList<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public void addAll(@NonNull Collection<T> items) {
        addAll(items.toArray((Object[]) Array.newInstance((Class<?>) this.mTClass, items.size())), true);
    }

    public void replaceAll(@NonNull T[] items, boolean mayModifyInput) {
        throwIfInMutationOperation();
        if (mayModifyInput) {
            replaceAllInternal(items);
        } else {
            replaceAllInternal(copyArray(items));
        }
    }

    public void replaceAll(@NonNull T... items) {
        replaceAll(items, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: android.support.v7.util.SortedList<T> */
    /* JADX WARN: Multi-variable type inference failed */
    public void replaceAll(@NonNull Collection<T> items) {
        replaceAll(items.toArray((Object[]) Array.newInstance((Class<?>) this.mTClass, items.size())), true);
    }

    private void addAllInternal(T[] newItems) {
        if (newItems.length >= 1) {
            int newSize = sortAndDedup(newItems);
            if (this.mSize == 0) {
                this.mData = newItems;
                this.mSize = newSize;
                this.mCallback.onInserted(0, newSize);
                return;
            }
            merge(newItems, newSize);
        }
    }

    private void replaceAllInternal(@NonNull T[] newData) {
        boolean forceBatchedUpdates = !(this.mCallback instanceof BatchedCallback);
        if (forceBatchedUpdates) {
            beginBatchedUpdates();
        }
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mOldData = this.mData;
        this.mNewDataStart = 0;
        int newSize = sortAndDedup(newData);
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, newSize));
        while (true) {
            if (this.mNewDataStart >= newSize && this.mOldDataStart >= this.mOldDataSize) {
                break;
            }
            int i = this.mOldDataStart;
            int i2 = this.mOldDataSize;
            if (i >= i2) {
                int insertIndex = this.mNewDataStart;
                int itemCount = newSize - this.mNewDataStart;
                System.arraycopy(newData, insertIndex, this.mData, insertIndex, itemCount);
                this.mNewDataStart += itemCount;
                this.mSize += itemCount;
                this.mCallback.onInserted(insertIndex, itemCount);
                break;
            }
            int i3 = this.mNewDataStart;
            if (i3 >= newSize) {
                int itemCount2 = i2 - i;
                this.mSize -= itemCount2;
                this.mCallback.onRemoved(i3, itemCount2);
                break;
            }
            T oldItem = this.mOldData[i];
            T newItem = newData[i3];
            int result = this.mCallback.compare(oldItem, newItem);
            if (result < 0) {
                replaceAllRemove();
            } else if (result > 0) {
                replaceAllInsert(newItem);
            } else if (!this.mCallback.areItemsTheSame(oldItem, newItem)) {
                replaceAllRemove();
                replaceAllInsert(newItem);
            } else {
                T[] tArr = this.mData;
                int i4 = this.mNewDataStart;
                tArr[i4] = newItem;
                this.mOldDataStart++;
                this.mNewDataStart = i4 + 1;
                if (!this.mCallback.areContentsTheSame(oldItem, newItem)) {
                    Callback callback = this.mCallback;
                    callback.onChanged(this.mNewDataStart - 1, 1, callback.getChangePayload(oldItem, newItem));
                }
            }
        }
        this.mOldData = null;
        if (forceBatchedUpdates) {
            endBatchedUpdates();
        }
    }

    private void replaceAllInsert(T newItem) {
        T[] tArr = this.mData;
        int i = this.mNewDataStart;
        tArr[i] = newItem;
        this.mNewDataStart = i + 1;
        this.mSize++;
        this.mCallback.onInserted(this.mNewDataStart - 1, 1);
    }

    private void replaceAllRemove() {
        this.mSize--;
        this.mOldDataStart++;
        this.mCallback.onRemoved(this.mNewDataStart, 1);
    }

    private int sortAndDedup(@NonNull T[] items) {
        if (items.length == 0) {
            return 0;
        }
        Arrays.sort(items, this.mCallback);
        int rangeStart = 0;
        int rangeEnd = 1;
        for (int i = 1; i < items.length; i++) {
            T currentItem = items[i];
            if (this.mCallback.compare(items[rangeStart], currentItem) == 0) {
                int sameItemPos = findSameItem(currentItem, items, rangeStart, rangeEnd);
                if (sameItemPos != -1) {
                    items[sameItemPos] = currentItem;
                } else {
                    if (rangeEnd != i) {
                        items[rangeEnd] = currentItem;
                    }
                    rangeEnd++;
                }
            } else {
                if (rangeEnd != i) {
                    items[rangeEnd] = currentItem;
                }
                rangeStart = rangeEnd;
                rangeEnd++;
            }
        }
        return rangeEnd;
    }

    private int findSameItem(T item, T[] items, int from, int to) {
        for (int pos = from; pos < to; pos++) {
            if (this.mCallback.areItemsTheSame(items[pos], item)) {
                return pos;
            }
        }
        return -1;
    }

    private void merge(T[] newData, int newDataSize) {
        boolean forceBatchedUpdates = !(this.mCallback instanceof BatchedCallback);
        if (forceBatchedUpdates) {
            beginBatchedUpdates();
        }
        this.mOldData = this.mData;
        this.mOldDataStart = 0;
        int i = this.mSize;
        this.mOldDataSize = i;
        this.mData = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, i + newDataSize + 10));
        this.mNewDataStart = 0;
        int newDataStart = 0;
        while (true) {
            if (this.mOldDataStart >= this.mOldDataSize && newDataStart >= newDataSize) {
                break;
            }
            int i2 = this.mOldDataStart;
            int i3 = this.mOldDataSize;
            if (i2 == i3) {
                int itemCount = newDataSize - newDataStart;
                System.arraycopy(newData, newDataStart, this.mData, this.mNewDataStart, itemCount);
                this.mNewDataStart += itemCount;
                this.mSize += itemCount;
                this.mCallback.onInserted(this.mNewDataStart - itemCount, itemCount);
                break;
            } else if (newDataStart == newDataSize) {
                int itemCount2 = i3 - i2;
                System.arraycopy(this.mOldData, i2, this.mData, this.mNewDataStart, itemCount2);
                this.mNewDataStart += itemCount2;
                break;
            } else {
                T oldItem = this.mOldData[i2];
                T newItem = newData[newDataStart];
                int compare = this.mCallback.compare(oldItem, newItem);
                if (compare > 0) {
                    T[] tArr = this.mData;
                    int i4 = this.mNewDataStart;
                    this.mNewDataStart = i4 + 1;
                    tArr[i4] = newItem;
                    this.mSize++;
                    newDataStart++;
                    this.mCallback.onInserted(this.mNewDataStart - 1, 1);
                } else if (compare != 0 || !this.mCallback.areItemsTheSame(oldItem, newItem)) {
                    T[] tArr2 = this.mData;
                    int i5 = this.mNewDataStart;
                    this.mNewDataStart = i5 + 1;
                    tArr2[i5] = oldItem;
                    this.mOldDataStart++;
                } else {
                    T[] tArr3 = this.mData;
                    int i6 = this.mNewDataStart;
                    this.mNewDataStart = i6 + 1;
                    tArr3[i6] = newItem;
                    newDataStart++;
                    this.mOldDataStart++;
                    if (!this.mCallback.areContentsTheSame(oldItem, newItem)) {
                        Callback callback = this.mCallback;
                        callback.onChanged(this.mNewDataStart - 1, 1, callback.getChangePayload(oldItem, newItem));
                    }
                }
            }
        }
        this.mOldData = null;
        if (forceBatchedUpdates) {
            endBatchedUpdates();
        }
    }

    private void throwIfInMutationOperation() {
        if (this.mOldData != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    public void beginBatchedUpdates() {
        throwIfInMutationOperation();
        Callback callback = this.mCallback;
        if (!(callback instanceof BatchedCallback)) {
            if (this.mBatchedCallback == null) {
                this.mBatchedCallback = new BatchedCallback(callback);
            }
            this.mCallback = this.mBatchedCallback;
        }
    }

    public void endBatchedUpdates() {
        throwIfInMutationOperation();
        Callback callback = this.mCallback;
        if (callback instanceof BatchedCallback) {
            ((BatchedCallback) callback).dispatchLastEvent();
        }
        Callback callback2 = this.mCallback;
        BatchedCallback batchedCallback = this.mBatchedCallback;
        if (callback2 == batchedCallback) {
            this.mCallback = batchedCallback.mWrappedCallback;
        }
    }

    private int add(T item, boolean notify) {
        int index = findIndexOf(item, this.mData, 0, this.mSize, 1);
        if (index == -1) {
            index = 0;
        } else if (index < this.mSize) {
            T existing = this.mData[index];
            if (this.mCallback.areItemsTheSame(existing, item)) {
                if (this.mCallback.areContentsTheSame(existing, item)) {
                    this.mData[index] = item;
                    return index;
                }
                this.mData[index] = item;
                Callback callback = this.mCallback;
                callback.onChanged(index, 1, callback.getChangePayload(existing, item));
                return index;
            }
        }
        addToData(index, item);
        if (notify) {
            this.mCallback.onInserted(index, 1);
        }
        return index;
    }

    public boolean remove(T item) {
        throwIfInMutationOperation();
        return remove(item, true);
    }

    public T removeItemAt(int index) {
        throwIfInMutationOperation();
        T item = get(index);
        removeItemAtIndex(index, true);
        return item;
    }

    private boolean remove(T item, boolean notify) {
        int index = findIndexOf(item, this.mData, 0, this.mSize, 2);
        if (index == -1) {
            return false;
        }
        removeItemAtIndex(index, notify);
        return true;
    }

    private void removeItemAtIndex(int index, boolean notify) {
        T[] tArr = this.mData;
        System.arraycopy(tArr, index + 1, tArr, index, (this.mSize - index) - 1);
        this.mSize--;
        this.mData[this.mSize] = null;
        if (notify) {
            this.mCallback.onRemoved(index, 1);
        }
    }

    public void updateItemAt(int index, T item) {
        throwIfInMutationOperation();
        T existing = get(index);
        boolean contentsChanged = existing == item || !this.mCallback.areContentsTheSame(existing, item);
        if (existing == item || this.mCallback.compare(existing, item) != 0) {
            if (contentsChanged) {
                Callback callback = this.mCallback;
                callback.onChanged(index, 1, callback.getChangePayload(existing, item));
            }
            removeItemAtIndex(index, false);
            int newIndex = add(item, false);
            if (index != newIndex) {
                this.mCallback.onMoved(index, newIndex);
                return;
            }
            return;
        }
        this.mData[index] = item;
        if (contentsChanged) {
            Callback callback2 = this.mCallback;
            callback2.onChanged(index, 1, callback2.getChangePayload(existing, item));
        }
    }

    public void recalculatePositionOfItemAt(int index) {
        throwIfInMutationOperation();
        T item = get(index);
        removeItemAtIndex(index, false);
        int newIndex = add(item, false);
        if (index != newIndex) {
            this.mCallback.onMoved(index, newIndex);
        }
    }

    public T get(int index) throws IndexOutOfBoundsException {
        int i;
        if (index >= this.mSize || index < 0) {
            throw new IndexOutOfBoundsException("Asked to get item at " + index + " but size is " + this.mSize);
        }
        T[] tArr = this.mOldData;
        if (tArr == null || index < (i = this.mNewDataStart)) {
            return this.mData[index];
        }
        return tArr[(index - i) + this.mOldDataStart];
    }

    public int indexOf(T item) {
        if (this.mOldData == null) {
            return findIndexOf(item, this.mData, 0, this.mSize, 4);
        }
        int index = findIndexOf(item, this.mData, 0, this.mNewDataStart, 4);
        if (index != -1) {
            return index;
        }
        int index2 = findIndexOf(item, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
        if (index2 != -1) {
            return (index2 - this.mOldDataStart) + this.mNewDataStart;
        }
        return -1;
    }

    private int findIndexOf(T item, T[] mData2, int left, int right, int reason) {
        while (left < right) {
            int middle = (left + right) / 2;
            T myItem = mData2[middle];
            int cmp = this.mCallback.compare(myItem, item);
            if (cmp < 0) {
                left = middle + 1;
            } else if (cmp != 0) {
                right = middle;
            } else if (this.mCallback.areItemsTheSame(myItem, item)) {
                return middle;
            } else {
                int exact = linearEqualitySearch(item, middle, left, right);
                if (reason == 1) {
                    return exact == -1 ? middle : exact;
                }
                return exact;
            }
        }
        if (reason == 1) {
            return left;
        }
        return -1;
    }

    private int linearEqualitySearch(T item, int middle, int left, int right) {
        for (int next = middle - 1; next >= left; next--) {
            T nextItem = this.mData[next];
            if (this.mCallback.compare(nextItem, item) != 0) {
                break;
            } else if (this.mCallback.areItemsTheSame(nextItem, item)) {
                return next;
            }
        }
        for (int next2 = middle + 1; next2 < right; next2++) {
            T nextItem2 = this.mData[next2];
            if (this.mCallback.compare(nextItem2, item) != 0) {
                return -1;
            }
            if (this.mCallback.areItemsTheSame(nextItem2, item)) {
                return next2;
            }
        }
        return -1;
    }

    private void addToData(int index, T item) {
        int i = this.mSize;
        if (index <= i) {
            T[] tArr = this.mData;
            if (i == tArr.length) {
                T[] newData = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, tArr.length + 10));
                System.arraycopy(this.mData, 0, newData, 0, index);
                newData[index] = item;
                System.arraycopy(this.mData, index, newData, index + 1, this.mSize - index);
                this.mData = newData;
            } else {
                System.arraycopy(tArr, index, tArr, index + 1, i - index);
                this.mData[index] = item;
            }
            this.mSize++;
            return;
        }
        throw new IndexOutOfBoundsException("cannot add item to " + index + " because size is " + this.mSize);
    }

    private T[] copyArray(T[] items) {
        T[] copy = (T[]) ((Object[]) Array.newInstance((Class<?>) this.mTClass, items.length));
        System.arraycopy(items, 0, copy, 0, items.length);
        return copy;
    }

    public void clear() {
        throwIfInMutationOperation();
        if (this.mSize != 0) {
            int prevSize = this.mSize;
            Arrays.fill(this.mData, 0, prevSize, (Object) null);
            this.mSize = 0;
            this.mCallback.onRemoved(0, prevSize);
        }
    }

    public static abstract class Callback<T2> implements Comparator<T2>, ListUpdateCallback {
        public abstract boolean areContentsTheSame(T2 t2, T2 t22);

        public abstract boolean areItemsTheSame(T2 t2, T2 t22);

        @Override // java.util.Comparator
        public abstract int compare(T2 t2, T2 t22);

        public abstract void onChanged(int i, int i2);

        @Override // android.support.v7.util.ListUpdateCallback
        public void onChanged(int position, int count, Object payload) {
            onChanged(position, count);
        }

        @Nullable
        public Object getChangePayload(T2 t2, T2 t22) {
            return null;
        }
    }

    public static class BatchedCallback<T2> extends Callback<T2> {
        private final BatchingListUpdateCallback mBatchingListUpdateCallback = new BatchingListUpdateCallback(this.mWrappedCallback);
        final Callback<T2> mWrappedCallback;

        public BatchedCallback(Callback<T2> wrappedCallback) {
            this.mWrappedCallback = wrappedCallback;
        }

        @Override // android.support.v7.util.SortedList.Callback, java.util.Comparator
        public int compare(T2 o1, T2 o2) {
            return this.mWrappedCallback.compare(o1, o2);
        }

        @Override // android.support.v7.util.ListUpdateCallback
        public void onInserted(int position, int count) {
            this.mBatchingListUpdateCallback.onInserted(position, count);
        }

        @Override // android.support.v7.util.ListUpdateCallback
        public void onRemoved(int position, int count) {
            this.mBatchingListUpdateCallback.onRemoved(position, count);
        }

        @Override // android.support.v7.util.ListUpdateCallback
        public void onMoved(int fromPosition, int toPosition) {
            this.mBatchingListUpdateCallback.onMoved(fromPosition, toPosition);
        }

        @Override // android.support.v7.util.SortedList.Callback
        public void onChanged(int position, int count) {
            this.mBatchingListUpdateCallback.onChanged(position, count, null);
        }

        @Override // android.support.v7.util.ListUpdateCallback, android.support.v7.util.SortedList.Callback
        public void onChanged(int position, int count, Object payload) {
            this.mBatchingListUpdateCallback.onChanged(position, count, payload);
        }

        @Override // android.support.v7.util.SortedList.Callback
        public boolean areContentsTheSame(T2 oldItem, T2 newItem) {
            return this.mWrappedCallback.areContentsTheSame(oldItem, newItem);
        }

        @Override // android.support.v7.util.SortedList.Callback
        public boolean areItemsTheSame(T2 item1, T2 item2) {
            return this.mWrappedCallback.areItemsTheSame(item1, item2);
        }

        @Override // android.support.v7.util.SortedList.Callback
        @Nullable
        public Object getChangePayload(T2 item1, T2 item2) {
            return this.mWrappedCallback.getChangePayload(item1, item2);
        }

        public void dispatchLastEvent() {
            this.mBatchingListUpdateCallback.dispatchLastEvent();
        }
    }
}
