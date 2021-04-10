package com.google.common.collect;

import X.AbstractC02280Xm;
import X.AnonymousClass006;
import X.AnonymousClass0YB;
import X.AnonymousClass0u6;
import X.C00920Bw;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.http.useragent.UserAgentBuilder;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {
    public static final AbstractC02280Xm<Object> EMPTY_ITR = new C00920Bw(RegularImmutableList.A02, 0);

    public static class ReverseImmutableList<E> extends ImmutableList<E> {
        public final transient ImmutableList<E> A00;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A08() {
            return this.A00.A08();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public final boolean contains(@NullableDecl Object obj) {
            return this.A00.contains(obj);
        }

        @Override // com.google.common.collect.ImmutableList
        public final int indexOf(@NullableDecl Object obj) {
            int lastIndexOf = this.A00.lastIndexOf(obj);
            if (lastIndexOf >= 0) {
                return A00(lastIndexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList
        public final int lastIndexOf(@NullableDecl Object obj) {
            int indexOf = this.A00.indexOf(obj);
            if (indexOf >= 0) {
                return A00(indexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList
        public final ImmutableList<E> reverse() {
            return this.A00;
        }

        public final int size() {
            return this.A00.size();
        }

        public ReverseImmutableList(ImmutableList<E> immutableList) {
            this.A00 = immutableList;
        }

        private int A00(int i) {
            return (size() - 1) - i;
        }

        private int A01(int i) {
            return size() - i;
        }

        @Override // java.util.List
        public final E get(int i) {
            Preconditions.checkElementIndex(i, size());
            return this.A00.get(A00(i));
        }

        @Override // java.util.AbstractCollection, java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
        public final /* bridge */ /* synthetic */ Iterator iterator() {
            return ImmutableList.super.iterator();
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public final /* bridge */ /* synthetic */ ListIterator listIterator() {
            return ImmutableList.super.listIterator();
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public final /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
            return ImmutableList.super.listIterator(i);
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public final ImmutableList<E> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            return this.A00.subList(A01(i2), A01(i)).reverse();
        }
    }

    public static class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] elements;

        public Object readResolve() {
            return ImmutableList.copyOf(this.elements);
        }

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }
    }

    public class SubList extends ImmutableList<E> {
        public final transient int A00;
        public final transient int A01;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A08() {
            return true;
        }

        public SubList(int i, int i2) {
            this.A01 = i;
            this.A00 = i2;
        }

        @Override // java.util.List
        public final E get(int i) {
            Preconditions.checkElementIndex(i, this.A00);
            return (E) ImmutableList.this.get(i + this.A01);
        }

        public final int size() {
            return this.A00;
        }

        @Override // java.util.AbstractCollection, java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
        public final /* bridge */ /* synthetic */ Iterator iterator() {
            return ImmutableList.super.iterator();
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public final /* bridge */ /* synthetic */ ListIterator listIterator() {
            return ImmutableList.super.listIterator();
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public final /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
            return ImmutableList.super.listIterator(i);
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public final ImmutableList<E> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, this.A00);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.A01;
            return immutableList.subList(i + i3, i2 + i3);
        }
    }

    public static <E> ImmutableList<E> construct(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new NullPointerException(AnonymousClass006.A01("at index ", i));
            }
        }
        return asImmutableList(objArr);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> asList() {
        return this;
    }

    public ImmutableList<E> subListUnchecked(int i, int i2) {
        return new SubList(i, i2 - i);
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r5) {
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

    public int indexOf(@NullableDecl Object obj) {
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

    public int lastIndexOf(@NullableDecl Object obj) {
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
    @CanIgnoreReturnValue
    @Deprecated
    public final E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    @Deprecated
    public final E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@NullableDecl Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (((i * 31) + get(i2).hashCode()) ^ -1) ^ -1;
        }
        return i;
    }

    public ImmutableList<E> reverse() {
        if (size() <= 1) {
            return this;
        }
        return new ReverseImmutableList(this);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static final class Builder<E> extends AnonymousClass0YB<E> {
        public Builder() {
            this(4);
        }

        public Builder(int i) {
        }

        @Override // X.AnonymousClass0YB, X.AnonymousClass0YB, X.AbstractC07410rk
        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            super.add((Object) e);
            return this;
        }

        @Override // X.AnonymousClass0YB, X.AbstractC07410rk
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }

        @Override // X.AbstractC07410rk
        public ImmutableList<E> build() {
            this.A01 = true;
            return ImmutableList.asImmutableList(this.A02, this.A00);
        }
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] objArr) {
        return asImmutableList(objArr, objArr.length);
    }

    public static <E> ImmutableList<E> asImmutableList(Object[] objArr, int i) {
        if (i == 0) {
            return of();
        }
        return new RegularImmutableList(objArr, i);
    }

    public static <E> ImmutableList<E> of(E e, E e2, E e3, E e4, E e5) {
        return construct(UserAgentBuilder.FB_BRAND, UserAgentBuilder.FB_BUILD_VERSION, UserAgentBuilder.FB_CPU_ABI, UserAgentBuilder.FB_MANUFACTURER, UserAgentBuilder.FB_PACKAGE_NAME);
    }

    public static <E> ImmutableList<E> copyOf(Collection<? extends E> collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return construct(collection.toArray());
        }
        ImmutableList<E> asList = ((ImmutableCollection) collection).asList();
        return asList.A08() ? asImmutableList(asList.toArray()) : asList;
    }

    public static <E> ImmutableList<E> of(E e, E e2, E e3, E e4, E e5, E e6) {
        return construct(UserAgentBuilder.FB_APP_NAME, UserAgentBuilder.FB_APP_VERSION, UserAgentBuilder.FB_DEVICE, UserAgentBuilder.FB_CARRIER, UserAgentBuilder.FB_LOCALE, UserAgentBuilder.FB_SYSTEM_VERSION);
    }

    public static <E> ImmutableList<E> copyOf(E[] eArr) {
        if (eArr.length == 0) {
            return of();
        }
        return construct((Object[]) eArr.clone());
    }

    public static <E> ImmutableList<E> of() {
        return (ImmutableList<E>) RegularImmutableList.A02;
    }

    public static <E> ImmutableList<E> of(E e) {
        return construct(e);
    }

    public static <E> ImmutableList<E> of(E e, E e2) {
        return construct(UserAgentBuilder.FB_DEVICE_WIDE_STATE, UserAgentBuilder.FB_APP_VERSION_MAP);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
    public AnonymousClass0u6<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public AbstractC02280Xm<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public AbstractC02280Xm<E> listIterator(int i) {
        int size = size();
        if (i >= 0 && i <= size) {
            return isEmpty() ? (AbstractC02280Xm<E>) EMPTY_ITR : new C00920Bw(this, i);
        }
        throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(i, size, "index"));
    }

    @Override // java.util.List
    public ImmutableList<E> subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return of();
        }
        return subListUnchecked(i, i2);
    }
}
