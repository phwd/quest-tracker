package com.facebook.common.collectlite;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.debug.log.LoggingUtil;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

public class DirectHashSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {
    private static final int DEFAULT_CAPACITY = 0;
    private static final Object[] EMPTY_BACKING_ARRAY = new Object[0];
    private static final float LOAD_FACTOR = 0.75f;
    private static final Object NULL_OBJECT = new Object() {
        /* class com.facebook.common.collectlite.DirectHashSet.AnonymousClass1 */

        public boolean equals(Object obj) {
            return obj == this;
        }

        public int hashCode() {
            return 0;
        }
    };
    private static final long serialVersionUID = 0;
    private transient Object[] mBackingArray;
    private transient int mModCount;
    private int mSize;
    private transient int mThreshold;

    public DirectHashSet() {
        this(0);
    }

    public DirectHashSet(int i) {
        Object[] objArr;
        if (i >= 0) {
            this.mSize = 0;
            this.mModCount = 0;
            this.mThreshold = i;
            float f = ((float) i) / LOAD_FACTOR;
            int i2 = (int) f;
            if (i2 >= 0) {
                if (i2 == 0) {
                    objArr = EMPTY_BACKING_ARRAY;
                } else {
                    objArr = new Object[i2];
                }
                this.mBackingArray = objArr;
                return;
            }
            throw new RuntimeException(StringFormatUtil.formatStrLocaleSafe("adjustedCapacity = %d, capacity = %d, LOAD_FACTOR = %s, (capacity / LOAD_FACTOR) = %s", Integer.valueOf(i2), Integer.valueOf(i), Float.toString(LOAD_FACTOR), Float.toString(f)));
        }
        throw new IllegalArgumentException("capacity < 0: " + i);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.facebook.common.collectlite.DirectHashSet<E> */
    /* JADX WARN: Multi-variable type inference failed */
    public DirectHashSet(Collection<? extends E> collection) {
        this(collection.size());
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    private void rehash() {
        int length = this.mBackingArray.length * 2;
        if (length == 0) {
            length = 2;
        }
        Object[] objArr = new Object[length];
        Object[] objArr2 = this.mBackingArray;
        for (Object obj : objArr2) {
            if (obj != null) {
                objArr[findIndex(obj, objArr)] = obj;
            }
        }
        this.mBackingArray = objArr;
        this.mThreshold = (int) (((float) length) * LOAD_FACTOR);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(@Nullable E e) {
        if (this.mSize >= this.mThreshold) {
            this.mModCount++;
            rehash();
        }
        if (e == null) {
            e = (E) NULL_OBJECT;
        }
        int length = this.mBackingArray.length;
        int moduloHash = getModuloHash(e, length);
        while (true) {
            Object[] objArr = this.mBackingArray;
            Object obj = objArr[moduloHash];
            if (obj == null) {
                this.mSize++;
                this.mModCount++;
                objArr[moduloHash] = e;
                return true;
            } else if (obj == e || obj.equals(e)) {
                return false;
            } else {
                moduloHash++;
                if (moduloHash == length) {
                    moduloHash = 0;
                }
            }
        }
        return false;
    }

    public void clear() {
        this.mSize = 0;
        Arrays.fill(this.mBackingArray, (Object) null);
        this.mModCount++;
    }

    @Override // java.lang.Object
    public Object clone() {
        try {
            DirectHashSet directHashSet = (DirectHashSet) super.clone();
            directHashSet.mBackingArray = new Object[this.mBackingArray.length];
            System.arraycopy(this.mBackingArray, 0, directHashSet.mBackingArray, 0, this.mBackingArray.length);
            return directHashSet;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public boolean contains(@Nullable Object obj) {
        if (this.mBackingArray.length == 0) {
            return false;
        }
        if (obj == null) {
            obj = NULL_OBJECT;
        }
        Object[] objArr = this.mBackingArray;
        if (objArr[findIndex(obj, objArr)] != null) {
            return true;
        }
        return false;
    }

    public void copyTo(Collection<? super E> collection) {
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            collection.add(valueAt(firstIndex));
            firstIndex = nextIndex(firstIndex);
        }
    }

    public boolean isEmpty() {
        return this.mSize == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<E> iterator() {
        return new DirectHashSetIterator(this);
    }

    public boolean remove(@Nullable Object obj) {
        boolean z;
        Object obj2;
        if (this.mBackingArray.length == 0) {
            return false;
        }
        if (obj == null) {
            obj = NULL_OBJECT;
        }
        int findIndex = findIndex(obj, this.mBackingArray);
        Object[] objArr = this.mBackingArray;
        if (objArr[findIndex] == null) {
            return false;
        }
        int length = objArr.length;
        while (true) {
            do {
                z = true;
                findIndex++;
                if (findIndex >= length) {
                    findIndex = 0;
                }
                Object[] objArr2 = this.mBackingArray;
                obj2 = objArr2[findIndex];
                if (obj2 == null) {
                    this.mModCount++;
                    this.mSize--;
                    objArr2[findIndex] = null;
                    return true;
                }
                int moduloHash = getModuloHash(obj2, length);
                boolean z2 = moduloHash > findIndex;
                if (findIndex < findIndex) {
                    if (z2) {
                        continue;
                    } else if (moduloHash <= findIndex) {
                        continue;
                    }
                } else if (z2 && moduloHash <= findIndex) {
                }
                z = false;
                continue;
            } while (z);
            this.mBackingArray[findIndex] = obj2;
        }
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        boolean z = true;
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            Object obj = this.mBackingArray[firstIndex];
            if (!z) {
                sb.append(", ");
            }
            if (obj == NULL_OBJECT) {
                obj = LoggingUtil.NO_HASHCODE;
            }
            sb.append(obj);
            z = false;
            firstIndex = nextIndex(firstIndex);
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        if (size() != set.size()) {
            return false;
        }
        Object[] objArr = this.mBackingArray;
        for (Object obj2 : objArr) {
            if (!(obj2 == null || set.contains(obj2))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int firstIndex = firstIndex();
        int i = 0;
        while (firstIndex >= 0) {
            i += this.mBackingArray[firstIndex].hashCode();
            firstIndex = nextIndex(firstIndex);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public E getFromBackingArray(int i) {
        return (E) convertElement(this.mBackingArray[i]);
    }

    @Nullable
    private static Object convertElement(Object obj) {
        if (obj == NULL_OBJECT) {
            return null;
        }
        return obj;
    }

    public Object[] toArray() {
        int size = size();
        if (size == 0) {
            return EMPTY_BACKING_ARRAY;
        }
        Object[] objArr = new Object[size];
        int i = 0;
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            objArr[i] = getFromBackingArray(firstIndex);
            firstIndex = nextIndex(firstIndex);
            i++;
        }
        return objArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v9, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        int i = 0;
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            tArr[i] = getFromBackingArray(firstIndex);
            firstIndex = nextIndex(firstIndex);
            i++;
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    public int firstIndex() {
        return nextIndex(-1);
    }

    public int nextIndex(int i) {
        Object[] objArr;
        do {
            i++;
            objArr = this.mBackingArray;
            if (i >= objArr.length) {
                return Integer.MIN_VALUE;
            }
        } while (objArr[i] == null);
        return i;
    }

    @Nullable
    public E valueAt(int i) {
        Object obj = this.mBackingArray[i];
        if (obj != null) {
            return (E) convertElement(obj);
        }
        throw new NoSuchElementException();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.mBackingArray.length);
        objectOutputStream.writeFloat(LOAD_FACTOR);
        objectOutputStream.writeInt(size());
        int firstIndex = firstIndex();
        while (firstIndex >= 0) {
            objectOutputStream.writeObject(getFromBackingArray(firstIndex));
            firstIndex = nextIndex(firstIndex);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.facebook.common.collectlite.DirectHashSet<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        objectInputStream.readFloat();
        this.mBackingArray = new Object[readInt];
        this.mSize = 0;
        int readInt2 = objectInputStream.readInt();
        for (int i = 0; i < readInt2; i++) {
            add(objectInputStream.readObject());
        }
    }

    private static int findIndex(Object obj, Object[] objArr) {
        int moduloHash = getModuloHash(obj, objArr.length);
        int i = moduloHash;
        do {
            Object obj2 = objArr[i];
            if (obj2 == null || obj2 == obj || obj2.equals(obj)) {
                return i;
            }
            i++;
            if (i == objArr.length) {
                i = 0;
                continue;
            }
        } while (i != moduloHash);
        return i;
    }

    private static int getModuloHash(Object obj, int i) {
        int hashCode = obj.hashCode();
        int i2 = hashCode + ((hashCode << 15) ^ -12931);
        int i3 = i2 ^ (i2 >>> 10);
        int i4 = i3 + (i3 << 3);
        int i5 = i4 ^ (i4 >>> 6);
        int i6 = i5 + (i5 << 2) + (i5 << 14);
        return ((i6 ^ (i6 >>> 16)) & Integer.MAX_VALUE) % i;
    }

    private final class DirectHashSetIterator implements Iterator<E> {
        private final E[] mBackingArray;
        private boolean mCanRemove = false;
        private int mCurrentPosition;
        private int mExpectedModCount;
        private int mLastPosition = this.mCurrentPosition;
        private final DirectHashSet<E> mSet;

        DirectHashSetIterator(DirectHashSet<E> directHashSet) {
            this.mSet = directHashSet;
            this.mBackingArray = (E[]) ((DirectHashSet) directHashSet).mBackingArray;
            this.mExpectedModCount = ((DirectHashSet) directHashSet).mModCount;
            this.mCurrentPosition = directHashSet.firstIndex();
        }

        public final boolean hasNext() {
            return this.mCurrentPosition >= 0;
        }

        @Override // java.util.Iterator
        @Nullable
        public final E next() {
            if (this.mExpectedModCount == ((DirectHashSet) this.mSet).mModCount) {
                int i = this.mCurrentPosition;
                if (i >= 0) {
                    E e = this.mBackingArray[i];
                    if (e == DirectHashSet.NULL_OBJECT) {
                        e = null;
                    }
                    int i2 = this.mCurrentPosition;
                    this.mLastPosition = i2;
                    this.mCanRemove = true;
                    int i3 = i2 + 1;
                    while (true) {
                        E[] eArr = this.mBackingArray;
                        if (i3 >= eArr.length) {
                            this.mCurrentPosition = -1;
                            return e;
                        } else if (eArr[i3] != null) {
                            this.mCurrentPosition = i3;
                            return e;
                        } else {
                            i3++;
                        }
                    }
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final void remove() {
            if (this.mExpectedModCount != ((DirectHashSet) this.mSet).mModCount) {
                throw new ConcurrentModificationException();
            } else if (this.mCanRemove) {
                this.mCanRemove = false;
                this.mSet.remove(this.mBackingArray[this.mLastPosition]);
                this.mExpectedModCount++;
                if (!BuildConstants.isInternalBuild() || ((DirectHashSet) this.mSet).mModCount == this.mExpectedModCount) {
                    int i = this.mLastPosition;
                    while (true) {
                        E[] eArr = this.mBackingArray;
                        if (i >= eArr.length) {
                            this.mCurrentPosition = -1;
                            return;
                        } else if (eArr[i] != null) {
                            this.mCurrentPosition = i;
                            return;
                        } else {
                            i++;
                        }
                    }
                } else {
                    throw new AssertionError();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
