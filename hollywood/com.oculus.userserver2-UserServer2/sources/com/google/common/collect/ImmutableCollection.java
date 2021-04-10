package com.google.common.collect;

import X.Mh;
import X.N5;
import X.TW;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public abstract class ImmutableCollection<E> extends AbstractCollection<E> implements Serializable {
    public static final Object[] A00 = new Object[0];

    /* renamed from: A0H */
    public TW<E> iterator() {
        ImmutableMultiset immutableMultiset = (ImmutableMultiset) this;
        return new N5(immutableMultiset, immutableMultiset.entrySet().iterator());
    }

    public abstract boolean contains(@NullableDecl Object obj);

    public boolean A0E() {
        if ((this instanceof RegularImmutableMultiset) || (this instanceof SingletonImmutableSet)) {
            return false;
        }
        if (this instanceof RegularImmutableSortedSet) {
            return ((RegularImmutableSortedSet) this).A00.A0E();
        }
        if (this instanceof RegularImmutableSet) {
            return false;
        }
        if ((this instanceof RegularImmutableMap.KeySet) || (this instanceof RegularImmutableMap.EntrySet) || (this instanceof RegularImmutableMultiset.ElementSet)) {
            return true;
        }
        return ImmutableMultiset.this.A0E();
    }

    @CanIgnoreReturnValue
    public int A0F(Object[] objArr, int i) {
        ImmutableList<E> immutableList;
        if (!(this instanceof SingletonImmutableSet)) {
            if (this instanceof RegularImmutableSortedSet) {
                immutableList = ((RegularImmutableSortedSet) this).A00;
            } else if (this instanceof RegularImmutableSet) {
                RegularImmutableSet regularImmutableSet = (RegularImmutableSet) this;
                Object[] objArr2 = regularImmutableSet.A02;
                int i2 = regularImmutableSet.A01;
                System.arraycopy(objArr2, 0, objArr, i, i2);
                return i + i2;
            } else if (this instanceof RegularImmutableMap.KeySet) {
                immutableList = ((RegularImmutableMap.KeySet) this).A0G();
            } else if ((this instanceof RegularImmutableMap.EntrySet) || (this instanceof IndexedImmutableSet)) {
                immutableList = A0G();
            } else if (!(this instanceof ImmutableMultiset)) {
                TW<E> A0H = iterator();
                while (A0H.hasNext()) {
                    objArr[i] = A0H.next();
                    i++;
                }
                return i;
            } else {
                TW<Multiset.Entry<E>> A0H2 = ((ImmutableMultiset) this).entrySet().iterator();
                while (A0H2.hasNext()) {
                    Mh next = A0H2.next();
                    Arrays.fill(objArr, i, next.A00() + i, next.A01());
                    i += next.A00();
                }
                return i;
            }
            return immutableList.A0F(objArr, i);
        }
        objArr[i] = ((SingletonImmutableSet) this).A01;
        return i + 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList<E> A0G() {
        if (isEmpty()) {
            return ImmutableList.of();
        }
        return ImmutableList.A0A(toArray());
    }

    public Object writeReplace() {
        return new ImmutableList.SerializedForm(toArray());
    }

    public final Object[] toArray() {
        int size = size();
        if (size == 0) {
            return A00;
        }
        Object[] objArr = new Object[size];
        A0F(objArr, 0);
        return objArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final <T> T[] toArray(T[] tArr) {
        if (tArr != null) {
            int size = size();
            int length = tArr.length;
            if (length < size) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
            } else if (length > size) {
                tArr[size] = null;
            }
            A0F(tArr, 0);
            return tArr;
        }
        throw null;
    }
}
