package com.google.common.collect;

import X.AnonymousClass006;
import X.AnonymousClass0rg;
import X.AnonymousClass0td;
import X.AnonymousClass0u6;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    @NullableDecl
    @RetainedWith
    @LazyInit
    public transient ImmutableList<E> A00;

    public static class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] elements;

        public Object readResolve() {
            return ImmutableSet.A07(this.elements);
        }

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }
    }

    @VisibleForTesting
    public static int A05(int i) {
        int max = Math.max(i, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) max)) {
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

    public static <E> ImmutableSet<E> A06(int i, Object... objArr) {
        Object[] objArr2 = objArr;
        if (i == 0) {
            return RegularImmutableSet.A05;
        }
        if (i == 1) {
            return new SingletonImmutableSet(objArr[0]);
        }
        int A05 = A05(i);
        Object[] objArr3 = new Object[A05];
        int i2 = A05 - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            if (obj != null) {
                int hashCode = obj.hashCode();
                int A002 = AnonymousClass0rg.A00(hashCode);
                while (true) {
                    int i6 = A002 & i2;
                    Object obj2 = objArr3[i6];
                    if (obj2 == null) {
                        objArr[i4] = obj;
                        objArr3[i6] = obj;
                        i3 += hashCode;
                        i4++;
                        break;
                    }
                    if (obj2.equals(obj)) {
                        break;
                    }
                    A002++;
                }
            } else {
                throw new NullPointerException(AnonymousClass006.A01("at index ", i5));
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new SingletonImmutableSet(objArr[0], i3);
        }
        if (A05(i4) < (A05 >> 1)) {
            return A06(i4, objArr);
        }
        int length = objArr.length;
        if (i4 < (length >> 1) + (length >> 2)) {
            objArr2 = Arrays.copyOf(objArr, i4);
        }
        return new RegularImmutableSet(objArr2, i3, objArr3, i2, i4);
    }

    public static <E> ImmutableSet<E> A07(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return RegularImmutableSet.A05;
        }
        if (length != 1) {
            return A06(length, (Object[]) eArr.clone());
        }
        return new SingletonImmutableSet(eArr[0]);
    }

    public boolean A0A() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public abstract AnonymousClass0u6<E> iterator();

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.A00;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> A09 = A09();
        this.A00 = A09;
        return A09;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && A0A() && ((ImmutableSet) obj).A0A() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set = (Set) obj;
        try {
            if (size() != set.size() || !containsAll(set)) {
                return false;
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public ImmutableList<E> A09() {
        return ImmutableList.asImmutableList(toArray());
    }

    public int hashCode() {
        return AnonymousClass0td.A00(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }
}
