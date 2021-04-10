package com.google.common.collect;

import X.AbstractC0120Qz;
import X.Mh;
import X.R7;
import X.Rt;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements AbstractC0120Qz<E> {
    @LazyInit
    public transient ImmutableList<E> A00;
    @LazyInit
    public transient ImmutableSet<Multiset.Entry<E>> A01;

    public final class EntrySet extends IndexedImmutableSet<Multiset.Entry<E>> {
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Mh)) {
                return false;
            }
            Mh mh = (Mh) obj;
            if (mh.A00() <= 0 || ImmutableMultiset.this.A1D(mh.A01()) != mh.A00()) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet
        public final int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        public final int size() {
            RegularImmutableMultiset regularImmutableMultiset = (RegularImmutableMultiset) ImmutableMultiset.this;
            RegularImmutableMultiset.ElementSet elementSet = regularImmutableMultiset.A00;
            if (elementSet == null) {
                elementSet = new RegularImmutableMultiset.ElementSet();
                regularImmutableMultiset.A00 = elementSet;
            }
            return elementSet.size();
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

    @Override // X.AbstractC0120Qz
    public final /* bridge */ /* synthetic */ Set A1R() {
        RegularImmutableMultiset regularImmutableMultiset = (RegularImmutableMultiset) this;
        ImmutableSet<E> immutableSet = regularImmutableMultiset.A00;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMultiset.ElementSet elementSet = new RegularImmutableMultiset.ElementSet();
        regularImmutableMultiset.A00 = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public abstract Object writeReplace();

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> A0G() {
        ImmutableList<E> immutableList = this.A00;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> A0G = super.A0G();
        this.A00 = A0G;
        return A0G;
    }

    /* renamed from: A0I */
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

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    @Deprecated
    public final int A0k(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    @Deprecated
    public final int A3F(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    @Deprecated
    public final int A3X(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC0120Qz
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean A3Y(E e, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC0120Qz, com.google.common.collect.ImmutableCollection
    public final boolean contains(@NullableDecl Object obj) {
        if (A1D(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0120Qz
    public final boolean equals(@NullableDecl Object obj) {
        return R7.A00(this, obj);
    }

    @Override // X.AbstractC0120Qz
    public final int hashCode() {
        return Rt.A00(entrySet());
    }

    public final String toString() {
        return entrySet().toString();
    }
}
