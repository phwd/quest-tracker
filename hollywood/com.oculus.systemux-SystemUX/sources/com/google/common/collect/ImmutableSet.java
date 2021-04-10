package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    private static final int CUTOFF = 751619276;
    private static final double DESIRED_LOAD_FACTOR = 0.7d;
    static final double HASH_FLOODING_FPP = 0.001d;
    static final int MAX_RUN_MULTIPLIER = 12;
    static final int MAX_TABLE_SIZE = 1073741824;
    static final int SPLITERATOR_CHARACTERISTICS = 1297;
    @NullableDecl
    @RetainedWith
    @LazyInit
    private transient ImmutableList<E> asList;

    /* access modifiers changed from: package-private */
    public boolean isHashCodeFast() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();

    @Beta
    public static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return CollectCollectors.toImmutableSet();
    }

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    public static <E> ImmutableSet<E> of(E e) {
        return new SingletonImmutableSet(e);
    }

    public static <E> ImmutableSet<E> of(E e, E e2) {
        return construct(2, e, e2);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3) {
        return construct(3, e, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4) {
        return construct(4, e, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5) {
        return construct(5, e, e2, e3, e4, e5);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        Preconditions.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        Object[] objArr = new Object[(eArr.length + 6)];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(objArr.length, objArr);
    }

    private static <E> ImmutableSet<E> construct(int i, Object... objArr) {
        if (i == 0) {
            return of();
        }
        int i2 = 0;
        if (i == 1) {
            return of(objArr[0]);
        }
        RegularSetBuilderImpl regularSetBuilderImpl = new RegularSetBuilderImpl(4);
        while (i2 < i) {
            i2++;
            regularSetBuilderImpl = regularSetBuilderImpl.add(Preconditions.checkNotNull(objArr[i2]));
        }
        return regularSetBuilderImpl.review().build();
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        } else if (collection instanceof EnumSet) {
            return copyOfEnumSet((EnumSet) collection);
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.google.common.collect.ImmutableSet$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return of();
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return of(next);
        }
        return new Builder().add(next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return of();
        }
        if (length != 1) {
            return construct(eArr.length, (Object[]) eArr.clone());
        }
        return of(eArr[0]);
    }

    private static ImmutableSet copyOfEnumSet(EnumSet enumSet) {
        return ImmutableEnumSet.asImmutable(EnumSet.copyOf(enumSet));
    }

    ImmutableSet() {
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableSet) || !isHashCodeFast() || !((ImmutableSet) obj).isHashCodeFast() || hashCode() == obj.hashCode()) {
            return Sets.equalsImpl(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> createAsList = createAsList();
        this.asList = createAsList;
        return createAsList;
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> createAsList() {
        return new RegularImmutableAsList(this, toArray());
    }

    /* access modifiers changed from: package-private */
    public static abstract class Indexed<E> extends ImmutableSet<E> {
        /* access modifiers changed from: package-private */
        public abstract E get(int i);

        Indexed() {
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public UnmodifiableIterator<E> iterator() {
            return asList().iterator();
        }

        @Override // java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
        public Spliterator<E> spliterator() {
            return CollectSpliterators.indexed(size(), ImmutableSet.SPLITERATOR_CHARACTERISTICS, new IntFunction() {
                /* class com.google.common.collect.$$Lambda$3BLy52QWdLAhlRrhtUvnf2tWQ0U */

                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return ImmutableSet.Indexed.this.get(i);
                }
            });
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super E> consumer) {
            Preconditions.checkNotNull(consumer);
            int size = size();
            for (int i = 0; i < size; i++) {
                consumer.accept(get(i));
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<E> createAsList() {
            return new ImmutableAsList<E>() {
                /* class com.google.common.collect.ImmutableSet.Indexed.AnonymousClass1 */

                @Override // java.util.List
                public E get(int i) {
                    return (E) Indexed.this.get(i);
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.collect.ImmutableAsList
                public Indexed<E> delegateCollection() {
                    return Indexed.this;
                }
            };
        }
    }

    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    @Beta
    public static <E> Builder<E> builderWithExpectedSize(int i) {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        return new Builder<>(i);
    }

    static Object[] rebuildHashTable(int i, Object[] objArr, int i2) {
        int i3;
        Object[] objArr2 = new Object[i];
        int length = objArr2.length - 1;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            int smear = Hashing.smear(obj.hashCode());
            while (true) {
                i3 = smear & length;
                if (objArr2[i3] == null) {
                    break;
                }
                smear++;
            }
            objArr2[i3] = obj;
        }
        return objArr2;
    }

    public static class Builder<E> extends ImmutableCollection.Builder<E> {
        boolean forceCopy;
        private SetBuilderImpl<E> impl;

        public Builder() {
            this(4);
        }

        Builder(int i) {
            this.impl = new RegularSetBuilderImpl(i);
        }

        Builder(boolean z) {
            this.impl = null;
        }

        /* access modifiers changed from: package-private */
        @VisibleForTesting
        public void forceJdk() {
            this.impl = new JdkBackedSetBuilderImpl(this.impl);
        }

        /* access modifiers changed from: package-private */
        public final void copyIfNecessary() {
            if (this.forceCopy) {
                copy();
                this.forceCopy = false;
            }
        }

        /* access modifiers changed from: package-private */
        public void copy() {
            this.impl = this.impl.copy();
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            Preconditions.checkNotNull(e);
            copyIfNecessary();
            this.impl = this.impl.add(e);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder<E> combine(Builder<E> builder) {
            copyIfNecessary();
            this.impl = this.impl.combine(builder.impl);
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSet<E> build() {
            this.forceCopy = true;
            this.impl = this.impl.review();
            return this.impl.build();
        }
    }

    /* access modifiers changed from: private */
    public static abstract class SetBuilderImpl<E> {
        E[] dedupedElements;
        int distinct;

        /* access modifiers changed from: package-private */
        public abstract SetBuilderImpl<E> add(E e);

        /* access modifiers changed from: package-private */
        public abstract ImmutableSet<E> build();

        /* access modifiers changed from: package-private */
        public abstract SetBuilderImpl<E> copy();

        /* access modifiers changed from: package-private */
        public SetBuilderImpl<E> review() {
            return this;
        }

        SetBuilderImpl(int i) {
            this.dedupedElements = (E[]) new Object[i];
            this.distinct = 0;
        }

        SetBuilderImpl(SetBuilderImpl<E> setBuilderImpl) {
            E[] eArr = setBuilderImpl.dedupedElements;
            this.dedupedElements = (E[]) Arrays.copyOf(eArr, eArr.length);
            this.distinct = setBuilderImpl.distinct;
        }

        private void ensureCapacity(int i) {
            E[] eArr = this.dedupedElements;
            if (i > eArr.length) {
                this.dedupedElements = (E[]) Arrays.copyOf(this.dedupedElements, ImmutableCollection.Builder.expandedCapacity(eArr.length, i));
            }
        }

        /* access modifiers changed from: package-private */
        public final void addDedupedElement(E e) {
            ensureCapacity(this.distinct + 1);
            E[] eArr = this.dedupedElements;
            int i = this.distinct;
            this.distinct = i + 1;
            eArr[i] = e;
        }

        /* access modifiers changed from: package-private */
        public final SetBuilderImpl<E> combine(SetBuilderImpl<E> setBuilderImpl) {
            SetBuilderImpl<E> setBuilderImpl2 = this;
            for (int i = 0; i < setBuilderImpl.distinct; i++) {
                setBuilderImpl2 = setBuilderImpl2.add(setBuilderImpl.dedupedElements[i]);
            }
            return setBuilderImpl2;
        }
    }

    @VisibleForTesting
    static int chooseTableSize(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < CUTOFF) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * DESIRED_LOAD_FACTOR < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z = false;
        }
        Preconditions.checkArgument(z, "collection too large");
        return 1073741824;
    }

    static boolean hashFloodingDetected(Object[] objArr) {
        int maxRunBeforeFallback = maxRunBeforeFallback(objArr.length);
        int i = 0;
        while (i < objArr.length && objArr[i] != null) {
            i++;
            if (i > maxRunBeforeFallback) {
                return true;
            }
        }
        int length = objArr.length - 1;
        while (length > i && objArr[length] != null) {
            if (((objArr.length - 1) - length) + i > maxRunBeforeFallback) {
                return true;
            }
            length--;
        }
        int i2 = i + 1;
        while (i2 < length) {
            int i3 = 0;
            while (i2 < length && objArr[i2] != null) {
                i3++;
                if (i3 > maxRunBeforeFallback) {
                    return true;
                }
                i2++;
            }
            i2++;
        }
        return false;
    }

    static int maxRunBeforeFallback(int i) {
        return IntMath.log2(i, RoundingMode.UNNECESSARY) * 12;
    }

    /* access modifiers changed from: private */
    public static final class RegularSetBuilderImpl<E> extends SetBuilderImpl<E> {
        private int expandTableThreshold;
        private int hashCode;
        private Object[] hashTable;
        private int maxRunBeforeFallback;

        RegularSetBuilderImpl(int i) {
            super(i);
            int chooseTableSize = ImmutableSet.chooseTableSize(i);
            this.hashTable = new Object[chooseTableSize];
            this.maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(chooseTableSize);
            this.expandTableThreshold = (int) (((double) chooseTableSize) * ImmutableSet.DESIRED_LOAD_FACTOR);
        }

        RegularSetBuilderImpl(RegularSetBuilderImpl<E> regularSetBuilderImpl) {
            super(regularSetBuilderImpl);
            Object[] objArr = regularSetBuilderImpl.hashTable;
            this.hashTable = Arrays.copyOf(objArr, objArr.length);
            this.maxRunBeforeFallback = regularSetBuilderImpl.maxRunBeforeFallback;
            this.expandTableThreshold = regularSetBuilderImpl.expandTableThreshold;
            this.hashCode = regularSetBuilderImpl.hashCode;
        }

        /* access modifiers changed from: package-private */
        public void ensureTableCapacity(int i) {
            if (i > this.expandTableThreshold) {
                Object[] objArr = this.hashTable;
                if (objArr.length < 1073741824) {
                    int length = objArr.length * 2;
                    this.hashTable = ImmutableSet.rebuildHashTable(length, this.dedupedElements, this.distinct);
                    this.maxRunBeforeFallback = ImmutableSet.maxRunBeforeFallback(length);
                    this.expandTableThreshold = (int) (((double) length) * ImmutableSet.DESIRED_LOAD_FACTOR);
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        public SetBuilderImpl<E> add(E e) {
            Preconditions.checkNotNull(e);
            int hashCode2 = e.hashCode();
            int smear = Hashing.smear(hashCode2);
            int length = this.hashTable.length - 1;
            for (int i = smear; i - smear < this.maxRunBeforeFallback; i++) {
                int i2 = i & length;
                Object obj = this.hashTable[i2];
                if (obj == null) {
                    addDedupedElement(e);
                    this.hashTable[i2] = e;
                    this.hashCode += hashCode2;
                    ensureTableCapacity(this.distinct);
                    return this;
                } else if (obj.equals(e)) {
                    return this;
                }
            }
            return new JdkBackedSetBuilderImpl(this).add(e);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        public SetBuilderImpl<E> copy() {
            return new RegularSetBuilderImpl(this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        public SetBuilderImpl<E> review() {
            int chooseTableSize = ImmutableSet.chooseTableSize(this.distinct);
            if (chooseTableSize * 2 < this.hashTable.length) {
                this.hashTable = ImmutableSet.rebuildHashTable(chooseTableSize, this.dedupedElements, this.distinct);
            }
            return ImmutableSet.hashFloodingDetected(this.hashTable) ? new JdkBackedSetBuilderImpl(this) : this;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        public ImmutableSet<E> build() {
            Object[] objArr;
            int i = this.distinct;
            if (i == 0) {
                return ImmutableSet.of();
            }
            if (i == 1) {
                return ImmutableSet.of(this.dedupedElements[0]);
            }
            if (this.distinct == this.dedupedElements.length) {
                objArr = this.dedupedElements;
            } else {
                objArr = Arrays.copyOf(this.dedupedElements, this.distinct);
            }
            int i2 = this.hashCode;
            Object[] objArr2 = this.hashTable;
            return new RegularImmutableSet(objArr, i2, objArr2, objArr2.length - 1);
        }
    }

    private static final class JdkBackedSetBuilderImpl<E> extends SetBuilderImpl<E> {
        private final Set<Object> delegate = Sets.newHashSetWithExpectedSize(this.distinct);

        JdkBackedSetBuilderImpl(SetBuilderImpl<E> setBuilderImpl) {
            super(setBuilderImpl);
            for (int i = 0; i < this.distinct; i++) {
                this.delegate.add(this.dedupedElements[i]);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        public SetBuilderImpl<E> add(E e) {
            Preconditions.checkNotNull(e);
            if (this.delegate.add(e)) {
                addDedupedElement(e);
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        public SetBuilderImpl<E> copy() {
            return new JdkBackedSetBuilderImpl(this);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.SetBuilderImpl
        public ImmutableSet<E> build() {
            int i = this.distinct;
            if (i == 0) {
                return ImmutableSet.of();
            }
            if (i != 1) {
                return new JdkBackedImmutableSet(this.delegate, ImmutableList.asImmutableList(this.dedupedElements, this.distinct));
            }
            return ImmutableSet.of(this.dedupedElements[0]);
        }
    }
}
