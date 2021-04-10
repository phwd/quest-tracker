package com.google.common.collect;

import X.AbstractC07380s1;
import X.AnonymousClass0dN;
import X.AnonymousClass0e0;
import X.AnonymousClass0r9;
import X.AnonymousClass0rC;
import X.C07190ra;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements AnonymousClass0r9<E> {
    @LazyInit
    public transient ImmutableList<E> A00;
    @LazyInit
    public transient ImmutableSet<Multiset.Entry<E>> A01;

    public final class EntrySet extends IndexedImmutableSet<Multiset.Entry<E>> {
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0H() {
            return ImmutableMultiset.this.A0H();
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public final Object A0N(int i) {
            return ImmutableMultiset.this.A0N(i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof AnonymousClass0dN)) {
                return false;
            }
            AnonymousClass0dN r4 = (AnonymousClass0dN) obj;
            if (r4.A00() <= 0 || ImmutableMultiset.this.A1v(r4.A01()) != r4.A00()) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet
        public final int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        public final int size() {
            return ImmutableMultiset.this.A2N().size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @GwtIncompatible
        public Object writeReplace() {
            return new EntrySetSerializedForm(ImmutableMultiset.this);
        }
    }

    @GwtIncompatible
    public static class EntrySetSerializedForm<E> implements Serializable {
        public final ImmutableMultiset<E> multiset;

        public Object readResolve() {
            return this.multiset.entrySet();
        }

        public EntrySetSerializedForm(ImmutableMultiset<E> immutableMultiset) {
            this.multiset = immutableMultiset;
        }
    }

    /* renamed from: A0M */
    public abstract ImmutableSet<E> A2N();

    public abstract Multiset.Entry<E> A0N(int i);

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public abstract Object writeReplace();

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> A0J() {
        ImmutableList<E> immutableList = this.A00;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> A0J = super.A0J();
        this.A00 = A0J;
        return A0J;
    }

    /* renamed from: A0L */
    public final ImmutableSet<Multiset.Entry<E>> entrySet() {
        ImmutableSet<Multiset.Entry<E>> immutableSet = this.A01;
        if (immutableSet == null) {
            if (isEmpty()) {
                immutableSet = RegularImmutableSet.A05;
            } else {
                immutableSet = new EntrySet();
            }
            this.A01 = immutableSet;
        }
        return immutableSet;
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    @Deprecated
    public final int A11(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    @Deprecated
    public final int A87(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    @Deprecated
    public final int A8d(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0r9
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean A8e(E e, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int A0I(Object[] objArr, int i) {
        AbstractC07380s1<Multiset.Entry<E>> A0K = entrySet().iterator();
        while (A0K.hasNext()) {
            AnonymousClass0dN next = A0K.next();
            Arrays.fill(objArr, i, next.A00() + i, next.A01());
            i += next.A00();
        }
        return i;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final AbstractC07380s1<E> A0K() {
        return new AnonymousClass0e0(this, entrySet().iterator());
    }

    @Override // com.google.common.collect.ImmutableCollection, X.AnonymousClass0r9
    public final boolean contains(@NullableDecl Object obj) {
        if (A1v(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0r9
    public final boolean equals(@NullableDecl Object obj) {
        return AnonymousClass0rC.A00(this, obj);
    }

    @Override // X.AnonymousClass0r9
    public final int hashCode() {
        return C07190ra.A00(entrySet());
    }

    public final String toString() {
        return entrySet().toString();
    }
}
