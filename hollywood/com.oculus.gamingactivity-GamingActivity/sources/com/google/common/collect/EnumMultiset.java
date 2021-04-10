package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class EnumMultiset<E extends Enum<E>> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    private transient int[] counts = new int[this.enumConstants.length];
    private transient int distinctElements;
    private transient E[] enumConstants;
    private transient long size;
    private transient Class<E> type;

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean setCount(@NullableDecl Object obj, int i, int i2) {
        return super.setCount(obj, i, i2);
    }

    static /* synthetic */ int access$210(EnumMultiset x0) {
        int i = x0.distinctElements;
        x0.distinctElements = i - 1;
        return i;
    }

    static /* synthetic */ long access$322(EnumMultiset x0, long x1) {
        long j = x0.size - x1;
        x0.size = j;
        return j;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Class<E> type2) {
        return new EnumMultiset<>(type2);
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements) {
        Iterator<E> iterator = elements.iterator();
        Preconditions.checkArgument(iterator.hasNext(), "EnumMultiset constructor passed empty Iterable");
        EnumMultiset<E> multiset = new EnumMultiset<>(iterator.next().getDeclaringClass());
        Iterables.addAll(multiset, elements);
        return multiset;
    }

    public static <E extends Enum<E>> EnumMultiset<E> create(Iterable<E> elements, Class<E> type2) {
        EnumMultiset<E> result = create(type2);
        Iterables.addAll(result, elements);
        return result;
    }

    private EnumMultiset(Class<E> type2) {
        this.type = type2;
        Preconditions.checkArgument(type2.isEnum());
        this.enumConstants = type2.getEnumConstants();
    }

    private boolean isActuallyE(@NullableDecl Object o) {
        Enum<?> e;
        int index;
        if (!(o instanceof Enum) || (index = (e = (Enum) o).ordinal()) >= this.enumConstants.length || this.enumConstants[index] != e) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void checkIsE(@NullableDecl Object element) {
        Preconditions.checkNotNull(element);
        if (!isActuallyE(element)) {
            throw new ClassCastException("Expected an " + this.type + " but got " + element);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return this.distinctElements;
    }

    @Override // com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(this.size);
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object element) {
        if (element == null || !isActuallyE(element)) {
            return 0;
        }
        return this.counts[((Enum) element).ordinal()];
    }

    @CanIgnoreReturnValue
    public int add(E element, int occurrences) {
        checkIsE(element);
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        int index = element.ordinal();
        int oldCount = this.counts[index];
        long newCount = ((long) oldCount) + ((long) occurrences);
        Preconditions.checkArgument(newCount <= 2147483647L, "too many occurrences: %s", newCount);
        this.counts[index] = (int) newCount;
        if (oldCount == 0) {
            this.distinctElements++;
        }
        this.size += (long) occurrences;
        return oldCount;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object element, int occurrences) {
        if (element == null || !isActuallyE(element)) {
            return 0;
        }
        Enum<?> e = (Enum) element;
        CollectPreconditions.checkNonnegative(occurrences, "occurrences");
        if (occurrences == 0) {
            return count(element);
        }
        int index = e.ordinal();
        int oldCount = this.counts[index];
        if (oldCount == 0) {
            return 0;
        }
        if (oldCount <= occurrences) {
            this.counts[index] = 0;
            this.distinctElements--;
            this.size -= (long) oldCount;
            return oldCount;
        }
        this.counts[index] = oldCount - occurrences;
        this.size -= (long) occurrences;
        return oldCount;
    }

    @CanIgnoreReturnValue
    public int setCount(E element, int count) {
        checkIsE(element);
        CollectPreconditions.checkNonnegative(count, "count");
        int index = element.ordinal();
        int oldCount = this.counts[index];
        this.counts[index] = count;
        this.size += (long) (count - oldCount);
        if (oldCount == 0 && count > 0) {
            this.distinctElements++;
        } else if (oldCount > 0 && count == 0) {
            this.distinctElements--;
        }
        return oldCount;
    }

    @Override // com.google.common.collect.AbstractMultiset
    public void clear() {
        Arrays.fill(this.counts, 0);
        this.size = 0;
        this.distinctElements = 0;
    }

    abstract class Itr<T> implements Iterator<T> {
        int index = 0;
        int toRemove = -1;

        /* access modifiers changed from: package-private */
        public abstract T output(int i);

        Itr() {
        }

        public boolean hasNext() {
            while (this.index < EnumMultiset.this.enumConstants.length) {
                if (EnumMultiset.this.counts[this.index] > 0) {
                    return true;
                }
                this.index++;
            }
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = output(this.index);
            this.toRemove = this.index;
            this.index++;
            return result;
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.toRemove >= 0);
            if (EnumMultiset.this.counts[this.toRemove] > 0) {
                EnumMultiset.access$210(EnumMultiset.this);
                EnumMultiset.access$322(EnumMultiset.this, (long) EnumMultiset.this.counts[this.toRemove]);
                EnumMultiset.this.counts[this.toRemove] = 0;
            }
            this.toRemove = -1;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<E> elementIterator() {
        return new EnumMultiset<E>.Itr() {
            /* class com.google.common.collect.EnumMultiset.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.EnumMultiset.Itr
            public E output(int index) {
                return (E) EnumMultiset.this.enumConstants[index];
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new EnumMultiset<E>.Itr() {
            /* class com.google.common.collect.EnumMultiset.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.EnumMultiset.Itr
            public Multiset.Entry<E> output(final int index) {
                return new Multisets.AbstractEntry<E>() {
                    /* class com.google.common.collect.EnumMultiset.AnonymousClass2.AnonymousClass1 */

                    @Override // com.google.common.collect.Multiset.Entry
                    public E getElement() {
                        return (E) EnumMultiset.this.enumConstants[index];
                    }

                    @Override // com.google.common.collect.Multiset.Entry
                    public int getCount() {
                        return EnumMultiset.this.counts[index];
                    }
                };
            }
        };
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.type);
        Serialization.writeMultiset(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.type = (Class) stream.readObject();
        this.enumConstants = this.type.getEnumConstants();
        this.counts = new int[this.enumConstants.length];
        Serialization.populateMultiset(this, stream);
    }
}
