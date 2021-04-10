package com.google.common.collect;

import X.AnonymousClass06;
import X.AnonymousClass9V;
import X.N0;
import X.Qc;
import X.RB;
import X.Rt;
import X.TW;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
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
                int A002 = Qc.A00(hashCode);
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
                throw new NullPointerException(AnonymousClass06.A01("at index ", i5));
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

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> A0G() {
        ImmutableList<E> immutableList = this.A00;
        if (immutableList == null) {
            if (this instanceof SingletonImmutableSet) {
                immutableList = ImmutableList.A08(((SingletonImmutableSet) this).A01);
            } else if (this instanceof RegularImmutableSet) {
                RegularImmutableSet regularImmutableSet = (RegularImmutableSet) this;
                immutableList = ImmutableList.A0D(regularImmutableSet.A02, regularImmutableSet.A01);
            } else if (this instanceof RegularImmutableMap.EntrySet) {
                immutableList = new ImmutableList<Map.Entry<K, V>>() {
                    /* class com.google.common.collect.RegularImmutableMap.EntrySet.AnonymousClass1 */

                    @Override // com.google.common.collect.ImmutableCollection
                    public final boolean A0E() {
                        return true;
                    }

                    @Override // java.util.List
                    public final Object get(int i) {
                        Preconditions.checkElementIndex(i, EntrySet.this.A00);
                        Object[] objArr = EntrySet.this.A01;
                        int i2 = i << 1;
                        return new AbstractMap.SimpleImmutableEntry(objArr[0 + i2], objArr[i2 + 1]);
                    }

                    public final int size() {
                        return EntrySet.this.A00;
                    }
                };
            } else if (!(this instanceof IndexedImmutableSet)) {
                immutableList = ImmutableList.A0A(toArray());
            } else {
                immutableList = new ImmutableList<E>() {
                    /* class com.google.common.collect.IndexedImmutableSet.AnonymousClass1 */

                    @Override // com.google.common.collect.ImmutableCollection
                    public final boolean A0E() {
                        return IndexedImmutableSet.this.A0E();
                    }

                    @Override // java.util.List
                    public final E get(int i) {
                        IndexedImmutableSet indexedImmutableSet = IndexedImmutableSet.this;
                        if (!(indexedImmutableSet instanceof RegularImmutableMultiset.ElementSet)) {
                            RB<E> rb = ((RegularImmutableMultiset) ImmutableMultiset.this).A01;
                            Preconditions.checkElementIndex(i, rb.A02);
                            return (E) new AnonymousClass9V(rb, i);
                        }
                        RB<E> rb2 = RegularImmutableMultiset.this.A01;
                        Preconditions.checkElementIndex(i, rb2.A02);
                        return (E) rb2.A07[i];
                    }

                    public final int size() {
                        return IndexedImmutableSet.this.size();
                    }
                };
            }
            this.A00 = immutableList;
        }
        return immutableList;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public TW<E> A0H() {
        ImmutableList<E> A0G;
        if (this instanceof SingletonImmutableSet) {
            return new N0(((SingletonImmutableSet) this).A01);
        }
        if ((this instanceof RegularImmutableSet) || !(this instanceof RegularImmutableMap.KeySet)) {
            A0G = A0G();
        } else {
            A0G = ((RegularImmutableMap.KeySet) this).A0G();
        }
        return A0G.iterator();
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && ((this instanceof SingletonImmutableSet) ? ((SingletonImmutableSet) this).A00 != 0 : (this instanceof RegularImmutableSet))) {
            ImmutableSet immutableSet = (ImmutableSet) obj;
            if ((immutableSet instanceof SingletonImmutableSet) ? ((SingletonImmutableSet) immutableSet).A00 != 0 : (immutableSet instanceof RegularImmutableSet)) {
                if (hashCode() != obj.hashCode()) {
                    return false;
                }
            }
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

    public int hashCode() {
        return Rt.A00(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }
}
