package com.google.common.collect;

import X.AbstractC0370Ug;
import X.AnonymousClass08;
import X.C0131Cj;
import X.C1161uD;
import X.C1166uN;
import X.Tw;
import X.UQ;
import X.UX;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.RegularImmutableMultiset;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    public transient ImmutableList A00;

    public class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] elements;

        public Object readResolve() {
            return ImmutableSet.A04(this.elements);
        }

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }
    }

    public static int A02(int i) {
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

    public static ImmutableSet A03(int i, Object... objArr) {
        Object[] objArr2 = objArr;
        if (i == 0) {
            return RegularImmutableSet.A05;
        }
        if (i == 1) {
            return new SingletonImmutableSet(objArr[0]);
        }
        int A02 = A02(i);
        Object[] objArr3 = new Object[A02];
        int i2 = A02 - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            if (obj != null) {
                int hashCode = obj.hashCode();
                int A002 = Tw.A00(hashCode);
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
                throw new NullPointerException(AnonymousClass08.A00("at index ", i5));
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new SingletonImmutableSet(objArr[0], i3);
        }
        if (A02(i4) < (A02 >> 1)) {
            return A03(i4, objArr);
        }
        int length = objArr.length;
        if (i4 < (length >> 1) + (length >> 2)) {
            objArr2 = Arrays.copyOf(objArr, i4);
        }
        return new RegularImmutableSet(objArr2, i3, objArr3, i2, i4);
    }

    public static ImmutableSet A04(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return RegularImmutableSet.A05;
        }
        if (length != 1) {
            return A03(length, (Object[]) objArr.clone());
        }
        return new SingletonImmutableSet(objArr[0]);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList A0D() {
        ImmutableList immutableList = this.A00;
        if (immutableList == null) {
            if (this instanceof SingletonImmutableSet) {
                immutableList = ImmutableList.A05(((SingletonImmutableSet) this).A01);
            } else if (this instanceof RegularImmutableSet) {
                RegularImmutableSet regularImmutableSet = (RegularImmutableSet) this;
                immutableList = ImmutableList.A0A(regularImmutableSet.A02, regularImmutableSet.A01);
            } else if (this instanceof RegularImmutableMap.EntrySet) {
                immutableList = new ImmutableList<Map.Entry<K, V>>() {
                    /* class com.google.common.collect.RegularImmutableMap.EntrySet.AnonymousClass1 */

                    @Override // com.google.common.collect.ImmutableCollection
                    public final boolean A0B() {
                        return true;
                    }

                    @Override // java.util.List
                    public final Object get(int i) {
                        Preconditions.checkElementIndex(i, EntrySet.this.A01);
                        EntrySet entrySet = EntrySet.this;
                        Object[] objArr = entrySet.A02;
                        int i2 = i << 1;
                        int i3 = entrySet.A00;
                        return new AbstractMap.SimpleImmutableEntry(objArr[i3 + i2], objArr[i2 + (i3 ^ 1)]);
                    }

                    public final int size() {
                        return EntrySet.this.A01;
                    }
                };
            } else if (this instanceof IndexedImmutableSet) {
                immutableList = new ImmutableList<E>() {
                    /* class com.google.common.collect.IndexedImmutableSet.AnonymousClass1 */

                    @Override // com.google.common.collect.ImmutableCollection
                    public final boolean A0B() {
                        return IndexedImmutableSet.this.A0B();
                    }

                    @Override // java.util.List
                    public final Object get(int i) {
                        IndexedImmutableSet indexedImmutableSet = IndexedImmutableSet.this;
                        if (!(indexedImmutableSet instanceof RegularImmutableMultiset.ElementSet)) {
                            UQ uq = ((RegularImmutableMultiset) ImmutableMultiset.this).A01;
                            Preconditions.checkElementIndex(i, uq.A02);
                            return new C0131Cj(uq, i);
                        }
                        UQ uq2 = RegularImmutableMultiset.this.A01;
                        Preconditions.checkElementIndex(i, uq2.A02);
                        return uq2.A07[i];
                    }

                    public final int size() {
                        return IndexedImmutableSet.this.size();
                    }
                };
            } else if (!(this instanceof ImmutableSortedMap.AnonymousClass1EntrySet)) {
                immutableList = ImmutableList.A07(toArray());
            } else {
                immutableList = new ImmutableList<Map.Entry<K, V>>() {
                    /* class com.google.common.collect.ImmutableSortedMap.AnonymousClass1EntrySet.AnonymousClass1 */

                    @Override // com.google.common.collect.ImmutableCollection
                    public final boolean A0B() {
                        return true;
                    }

                    @Override // java.util.List
                    public final Object get(int i) {
                        return new AbstractMap.SimpleImmutableEntry(ImmutableSortedMap.this.A02.A0D().get(i), ImmutableSortedMap.this.A01.get(i));
                    }

                    public final int size() {
                        return ImmutableSortedMap.this.size();
                    }
                };
            }
            this.A00 = immutableList;
        }
        return immutableList;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public AbstractC0370Ug A0E() {
        ImmutableList immutableList;
        if (this instanceof SingletonImmutableSet) {
            return new C1166uN(((SingletonImmutableSet) this).A01);
        }
        if (!(this instanceof RegularImmutableSet)) {
            if (this instanceof RegularImmutableMap.KeySet) {
                immutableList = ((RegularImmutableMap.KeySet) this).A0D();
                return immutableList.iterator();
            } else if (!(this instanceof RegularImmutableMap.EntrySet) && !(this instanceof IndexedImmutableSet) && !(this instanceof ImmutableSortedMap.AnonymousClass1EntrySet)) {
                return new C1161uD(((ImmutableSetMultimap.EntrySet) this).A00);
            }
        }
        immutableList = A0D();
        return immutableList.iterator();
    }

    public boolean equals(Object obj) {
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
        return UX.A00(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }
}
