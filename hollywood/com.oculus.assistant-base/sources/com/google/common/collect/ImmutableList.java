package com.google.common.collect;

import X.AbstractC0370Ug;
import X.AbstractC1160uA;
import X.AbstractC1186un;
import X.AnonymousClass08;
import X.W3;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {
    public static final AbstractC1186un A00 = new W3(RegularImmutableList.A02, 0);

    public class ReverseImmutableList<E> extends ImmutableList<E> {
        public final transient ImmutableList A00;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0B() {
            return this.A00.A0B();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            return this.A00.contains(obj);
        }

        @Override // com.google.common.collect.ImmutableList
        public final int indexOf(Object obj) {
            int lastIndexOf = this.A00.lastIndexOf(obj);
            if (lastIndexOf >= 0) {
                return A00(lastIndexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList
        public final int lastIndexOf(Object obj) {
            int indexOf = this.A00.indexOf(obj);
            if (indexOf >= 0) {
                return A00(indexOf);
            }
            return -1;
        }

        public final int size() {
            return this.A00.size();
        }

        public ReverseImmutableList(ImmutableList immutableList) {
            this.A00 = immutableList;
        }

        private int A00(int i) {
            return (size() - 1) - i;
        }

        private int A01(int i) {
            return size() - i;
        }

        @Override // com.google.common.collect.ImmutableList
        public final ImmutableList A0F() {
            return this.A00;
        }

        @Override // com.google.common.collect.ImmutableList
        /* renamed from: A0G */
        public final ImmutableList subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            return this.A00.subList(A01(i2), A01(i)).A0F();
        }

        @Override // java.util.List
        public final Object get(int i) {
            Preconditions.checkElementIndex(i, size());
            return this.A00.get(A00(i));
        }
    }

    public class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] elements;

        public Object readResolve() {
            return ImmutableList.A08(this.elements);
        }

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }
    }

    public class SubList extends ImmutableList<E> {
        public final transient int A00;
        public final transient int A01;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0B() {
            return true;
        }

        public SubList(int i, int i2) {
            this.A01 = i;
            this.A00 = i2;
        }

        @Override // com.google.common.collect.ImmutableList
        /* renamed from: A0G */
        public final ImmutableList subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, this.A00);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.A01;
            return immutableList.subList(i + i3, i2 + i3);
        }

        @Override // java.util.List
        public final Object get(int i) {
            Preconditions.checkElementIndex(i, this.A00);
            return ImmutableList.this.get(i + this.A01);
        }

        public final int size() {
            return this.A00;
        }
    }

    private final ImmutableList A04(int i, int i2) {
        return new SubList(i, i2 - i);
    }

    public static ImmutableList A05(Object obj) {
        return A09(obj);
    }

    public static ImmutableList A07(Object[] objArr) {
        return A0A(objArr, objArr.length);
    }

    public static ImmutableList A08(Object[] objArr) {
        if (objArr.length == 0) {
            return of();
        }
        return A09((Object[]) objArr.clone());
    }

    public static ImmutableList A09(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new NullPointerException(AnonymousClass08.A00("at index ", i));
            }
        }
        return A07(objArr);
    }

    /* access modifiers changed from: private */
    /* renamed from: A0H */
    public final AbstractC1186un listIterator() {
        return listIterator(0);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList A0D() {
        return this;
    }

    public static Builder A02() {
        return new Builder();
    }

    public static ImmutableList A03() {
        return A09("copyOf", "create");
    }

    public static ImmutableList A06(Collection collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return A09(collection.toArray());
        }
        ImmutableList A0D = ((ImmutableCollection) collection).A0D();
        if (A0D.A0B()) {
            return A07(A0D.toArray());
        }
        return A0D;
    }

    public static ImmutableList A0A(Object[] objArr, int i) {
        if (i == 0) {
            return of();
        }
        return new RegularImmutableList(objArr, i);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            if (r5 == r4) goto L_0x0059
            boolean r0 = r5 instanceof java.util.List
            if (r0 == 0) goto L_0x0050
            java.util.List r5 = (java.util.List) r5
            int r3 = r4.size()
            int r0 = r5.size()
            if (r3 != r0) goto L_0x0050
            boolean r0 = r4 instanceof java.util.RandomAccess
            if (r0 == 0) goto L_0x002e
            boolean r0 = r5 instanceof java.util.RandomAccess
            if (r0 == 0) goto L_0x002e
            r2 = 0
        L_0x001b:
            if (r2 >= r3) goto L_0x0059
            java.lang.Object r1 = r4.get(r2)
            java.lang.Object r0 = r5.get(r2)
            boolean r0 = com.google.common.base.Objects.equal(r1, r0)
            if (r0 == 0) goto L_0x0050
            int r2 = r2 + 1
            goto L_0x001b
        L_0x002e:
            java.util.Iterator r3 = r4.iterator()
            java.util.Iterator r2 = r5.iterator()
        L_0x0036:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0052
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0050
            java.lang.Object r1 = r3.next()
            java.lang.Object r0 = r2.next()
            boolean r0 = com.google.common.base.Objects.equal(r1, r0)
            if (r0 != 0) goto L_0x0036
        L_0x0050:
            r0 = 0
            return r0
        L_0x0052:
            boolean r0 = r2.hasNext()
            r0 = r0 ^ 1
            return r0
        L_0x0059:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableList.equals(java.lang.Object):boolean");
    }

    public int indexOf(Object obj) {
        if (obj != null) {
            if (this instanceof RandomAccess) {
                int size = size();
                for (int i = 0; i < size; i++) {
                    if (obj.equals(get(i))) {
                        return i;
                    }
                }
            } else {
                ListIterator<E> listIterator = listIterator();
                while (listIterator.hasNext()) {
                    if (Objects.equal(obj, listIterator.next())) {
                        return listIterator.previousIndex();
                    }
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (this instanceof RandomAccess) {
            int size = size();
            do {
                size--;
                if (size < 0) {
                    return -1;
                }
            } while (!obj.equals(get(size)));
            return size;
        }
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (Objects.equal(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: private */
    /* renamed from: A0I */
    public final AbstractC1186un listIterator(int i) {
        Preconditions.checkPositionIndex(i, size());
        if (isEmpty()) {
            return A00;
        }
        return new W3(this, i);
    }

    public static ImmutableList of() {
        return RegularImmutableList.A02;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int A0C(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final AbstractC0370Ug A0E() {
        return listIterator();
    }

    public ImmutableList A0F() {
        if (size() <= 1) {
            return this;
        }
        return new ReverseImmutableList(this);
    }

    /* renamed from: A0G */
    public ImmutableList subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return of();
        }
        return A04(i, i2);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (((i * 31) + get(i2).hashCode()) ^ -1) ^ -1;
        }
        return i;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public final class Builder extends AbstractC1160uA {
        public Builder() {
            this(4);
        }

        public Builder(int i) {
        }

        @Override // X.Tx, X.AbstractC1160uA, X.AbstractC1160uA
        public Builder add(Object obj) {
            super.add(obj);
            return this;
        }

        @Override // X.Tx, X.AbstractC1160uA
        public Builder add(Object... objArr) {
            super.add(objArr);
            return this;
        }

        @Override // X.Tx
        public ImmutableList build() {
            this.A01 = true;
            return ImmutableList.A0A(this.A02, this.A00);
        }
    }
}
