package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public class RegularImmutableList<E> extends ImmutableList<E> {
    private final transient Object[] array;
    private final transient int offset;
    private final transient int size;

    RegularImmutableList(Object[] array2, int offset2, int size2) {
        this.offset = offset2;
        this.size = size2;
        this.array = array2;
    }

    RegularImmutableList(Object[] array2) {
        this(array2, 0, array2.length);
    }

    public int size() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.size != this.array.length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int dstOff) {
        System.arraycopy(this.array, this.offset, dst, dstOff, this.size);
        return this.size + dstOff;
    }

    @Override // java.util.List
    public E get(int index) {
        Preconditions.checkElementIndex(index, this.size);
        return (E) this.array[this.offset + index];
    }

    @Override // com.google.common.collect.ImmutableList
    public int indexOf(@Nullable Object object) {
        if (object == null) {
            return -1;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.array[this.offset + i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.common.collect.ImmutableList
    public int lastIndexOf(@Nullable Object object) {
        if (object == null) {
            return -1;
        }
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.array[this.offset + i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList
    public ImmutableList<E> subListUnchecked(int fromIndex, int toIndex) {
        return new RegularImmutableList(this.array, this.offset + fromIndex, toIndex - fromIndex);
    }

    @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
    public UnmodifiableListIterator<E> listIterator(int index) {
        return Iterators.forArray(this.array, this.offset, this.size, index);
    }
}
