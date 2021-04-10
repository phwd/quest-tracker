package com.google.common.collect;

import X.AbstractC01540ep;
import X.AbstractC05160uM;
import X.AbstractC05710wh;
import X.AnonymousClass006;
import X.AnonymousClass0Bc;
import X.AnonymousClass0fR;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {
    public static final AbstractC01540ep<Object> A00 = new AnonymousClass0Bc(RegularImmutableList.A02, 0);

    public static class ReverseImmutableList<E> extends ImmutableList<E> {
        public final transient ImmutableList<E> A00;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0F() {
            return this.A00.A0F();
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

        @Override // com.google.common.collect.ImmutableList
        public final ImmutableList<E> A0J() {
            return this.A00;
        }

        @Override // com.google.common.collect.ImmutableList
        /* renamed from: A0K */
        public final ImmutableList<E> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            return this.A00.subList(A01(i2), A01(i)).A0J();
        }

        @Override // java.util.List
        public final E get(int i) {
            Preconditions.checkElementIndex(i, size());
            return this.A00.get(A00(i));
        }
    }

    public static class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] elements;

        public Object readResolve() {
            return ImmutableList.A0C(this.elements);
        }

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }
    }

    public class SubList extends ImmutableList<E> {
        public final transient int A00;
        public final transient int A01;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0F() {
            return true;
        }

        public SubList(int i, int i2) {
            this.A01 = i;
            this.A00 = i2;
        }

        @Override // com.google.common.collect.ImmutableList
        /* renamed from: A0K */
        public final ImmutableList<E> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, this.A00);
            ImmutableList immutableList = ImmutableList.this;
            int i3 = this.A01;
            return immutableList.subList(i + i3, i2 + i3);
        }

        @Override // java.util.List
        public final E get(int i) {
            Preconditions.checkElementIndex(i, this.A00);
            return (E) ImmutableList.this.get(i + this.A01);
        }

        public final int size() {
            return this.A00;
        }
    }

    private final ImmutableList<E> A05(int i, int i2) {
        return new SubList(i, i2 - i);
    }

    public static <E> ImmutableList<E> A0B(Object[] objArr) {
        return A0E(objArr, objArr.length);
    }

    public static <E> ImmutableList<E> A0C(E[] eArr) {
        if (eArr.length == 0) {
            return of();
        }
        return A0D((Object[]) eArr.clone());
    }

    public static <E> ImmutableList<E> A0D(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == null) {
                throw new NullPointerException(AnonymousClass006.A03("at index ", i));
            }
        }
        return A0B(objArr);
    }

    /* access modifiers changed from: private */
    /* renamed from: A0L */
    public final AbstractC01540ep<E> listIterator() {
        return listIterator(0);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> A0H() {
        return this;
    }

    public static <E> Builder<E> A02() {
        return new Builder<>();
    }

    /* JADX WARN: Incorrect args count in method signature: <E:Ljava/lang/Object;>(TE;TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableList<TE;>; */
    public static ImmutableList A03() {
        return A0D("FBBD", "FBBV", "FBCA", "FBMF", "FBPN");
    }

    /* JADX WARN: Incorrect args count in method signature: <E:Ljava/lang/Object;>(TE;TE;TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableList<TE;>; */
    public static ImmutableList A04() {
        return A0D("FBAN", "FBAV", "FBDV", "FBCR", "FBLC", "FBSV");
    }

    public static <E> ImmutableList<E> A0A(Collection<? extends E> collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return A0D(collection.toArray());
        }
        ImmutableList<E> A0H = ((ImmutableCollection) collection).A0H();
        if (A0H.A0F()) {
            return A0B(A0H.toArray());
        }
        return A0H;
    }

    public static <E> ImmutableList<E> A0E(Object[] objArr, int i) {
        if (i == 0) {
            return of();
        }
        return new RegularImmutableList(objArr, i);
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
    public final boolean equals(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r5) {
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

    public static final class Builder<E> extends AnonymousClass0fR<E> {
        @Override // X.AnonymousClass0fR, X.AbstractC05160uM
        @CanIgnoreReturnValue
        public final /* bridge */ /* synthetic */ AbstractC05160uM A02(Iterable iterable) {
            A03(iterable);
            return this;
        }

        /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Iterable<+TE;>;)Lcom/google/common/collect/ImmutableList$Builder<TE;>; */
        @CanIgnoreReturnValue
        public final void A03(Iterable iterable) {
            super.A02(iterable);
        }

        public Builder() {
            this(4);
        }

        public Builder(int i) {
        }

        @Override // X.AnonymousClass0fR, X.AnonymousClass0fR, X.AbstractC05160uM
        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            super.add((Object) e);
            return this;
        }

        @Override // X.AnonymousClass0fR, X.AbstractC05160uM
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }

        @Override // X.AbstractC05160uM
        public ImmutableList<E> build() {
            this.A01 = true;
            return ImmutableList.A0E(this.A02, this.A00);
        }
    }

    public static <E> ImmutableList<E> A06(E e) {
        return A0D(e);
    }

    public static <E> ImmutableList<E> A07(E e, E e2) {
        return A0D(e, e2);
    }

    public static <E> ImmutableList<E> A08(E e, E e2, E e3) {
        return A0D(e, e2, e3);
    }

    public static <E> ImmutableList<E> A09(E e, E e2, E e3, E e4) {
        return A0D(e, e2, e3, e4);
    }

    /* access modifiers changed from: private */
    /* renamed from: A0M */
    public final AbstractC01540ep<E> listIterator(int i) {
        Preconditions.checkPositionIndex(i, size());
        return isEmpty() ? (AbstractC01540ep<E>) A00 : new AnonymousClass0Bc(this, i);
    }

    public static <E> ImmutableList<E> of() {
        return (ImmutableList<E>) RegularImmutableList.A02;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int A0G(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final AbstractC05710wh<E> A0I() {
        return listIterator();
    }

    public ImmutableList<E> A0J() {
        if (size() <= 1) {
            return this;
        }
        return new ReverseImmutableList(this);
    }

    /* renamed from: A0K */
    public ImmutableList<E> subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return of();
        }
        return A05(i, i2);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@NullableDecl Object obj) {
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
}
