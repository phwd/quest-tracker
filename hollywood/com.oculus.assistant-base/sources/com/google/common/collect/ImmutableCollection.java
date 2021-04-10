package com.google.common.collect;

import X.AbstractC0370Ug;
import X.AbstractC1179ua;
import X.C1161uD;
import X.C1162uF;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.RegularImmutableMap;
import com.google.common.collect.RegularImmutableMultiset;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;

public abstract class ImmutableCollection<E> extends AbstractCollection<E> implements Serializable {
    public static final Object[] A00 = new Object[0];

    public abstract boolean contains(Object obj);

    public boolean A0B() {
        if (this instanceof RegularImmutableMultiset) {
            return false;
        }
        if (this instanceof ImmutableMultimap.EntryCollection) {
            return ((ImmutableMultimap.EntryCollection) this).multimap.A01.A09();
        }
        if (this instanceof SingletonImmutableSet) {
            return false;
        }
        if (this instanceof RegularImmutableSortedSet) {
            return ((RegularImmutableSortedSet) this).A00.A0B();
        }
        if (this instanceof RegularImmutableSet) {
            return false;
        }
        if ((this instanceof RegularImmutableMap.KeySet) || (this instanceof RegularImmutableMap.EntrySet)) {
            return true;
        }
        if (this instanceof ImmutableSetMultimap.EntrySet) {
            return false;
        }
        if (this instanceof RegularImmutableMultiset.ElementSet) {
            return true;
        }
        if (!(this instanceof ImmutableMultiset.EntrySet)) {
            return ImmutableSortedMap.this.A09();
        }
        return ImmutableMultiset.this.A0B();
    }

    public int A0C(Object[] objArr, int i) {
        ImmutableList immutableList;
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
                immutableList = ((RegularImmutableMap.KeySet) this).A0D();
            } else if ((this instanceof RegularImmutableMap.EntrySet) || (this instanceof IndexedImmutableSet)) {
                immutableList = A0D();
            } else if (!(this instanceof ImmutableMultiset)) {
                AbstractC0370Ug A0E = iterator();
                while (A0E.hasNext()) {
                    objArr[i] = A0E.next();
                    i++;
                }
                return i;
            } else {
                AbstractC0370Ug A0E2 = ((ImmutableMultiset) this).entrySet().iterator();
                while (A0E2.hasNext()) {
                    AbstractC1179ua uaVar = (AbstractC1179ua) A0E2.next();
                    Arrays.fill(objArr, i, uaVar.A00() + i, uaVar.A01());
                    i += uaVar.A00();
                }
                return i;
            }
            return immutableList.A0C(objArr, i);
        }
        objArr[i] = ((SingletonImmutableSet) this).A01;
        return i + 1;
    }

    /* renamed from: A0E */
    public AbstractC0370Ug iterator() {
        if (!(this instanceof ImmutableMultiset)) {
            return new C1161uD(((ImmutableMultimap.EntryCollection) this).multimap);
        }
        ImmutableMultiset immutableMultiset = (ImmutableMultiset) this;
        return new C1162uF(immutableMultiset, immutableMultiset.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public ImmutableList A0D() {
        if (isEmpty()) {
            return ImmutableList.of();
        }
        return ImmutableList.A07(toArray());
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
        A0C(objArr, 0);
        return objArr;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        if (objArr != null) {
            int size = size();
            int length = objArr.length;
            if (length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            } else if (length > size) {
                objArr[size] = null;
            }
            A0C(objArr, 0);
            return objArr;
        }
        throw null;
    }
}
