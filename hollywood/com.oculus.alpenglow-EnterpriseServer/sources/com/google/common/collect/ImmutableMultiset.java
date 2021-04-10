package com.google.common.collect;

import X.AnonymousClass0Y0;
import X.AnonymousClass0Y9;
import X.AnonymousClass0tC;
import X.AnonymousClass0tF;
import X.AnonymousClass0td;
import X.AnonymousClass0u6;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements AnonymousClass0tC<E> {
    @LazyInit
    public transient ImmutableList<E> A00;
    @LazyInit
    public transient ImmutableSet<Multiset.Entry<E>> A01;

    public final class EntrySet extends IndexedImmutableSet<Multiset.Entry<E>> {
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A08() {
            return ImmutableMultiset.this.A08();
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public final Object A0B(int i) {
            return ImmutableMultiset.this.A0B(i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof AnonymousClass0Y0)) {
                return false;
            }
            AnonymousClass0Y0 r4 = (AnonymousClass0Y0) obj;
            if (r4.A00() <= 0 || ImmutableMultiset.this.A1s(r4.A01()) != r4.A00()) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet
        public final int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        public final int size() {
            return ImmutableMultiset.this.A2G().size();
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

    /* renamed from: A0A */
    public abstract ImmutableSet<E> A2G();

    public abstract Multiset.Entry<E> A0B(int i);

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public abstract Object writeReplace();

    /* renamed from: A09 */
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

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    @Deprecated
    public final int A0x(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    @Deprecated
    public final int A7L(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    @Deprecated
    public final int A7q(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0tC
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean A7r(E e, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.A00;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> asList = super.asList();
        this.A00 = asList;
        return asList;
    }

    @Override // X.AnonymousClass0tC, com.google.common.collect.ImmutableCollection
    public final boolean contains(@NullableDecl Object obj) {
        if (A1s(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int copyIntoArray(Object[] objArr, int i) {
        AnonymousClass0u6<Multiset.Entry<E>> it = entrySet().iterator();
        while (it.hasNext()) {
            AnonymousClass0Y0 next = it.next();
            Arrays.fill(objArr, i, next.A00() + i, next.A01());
            i += next.A00();
        }
        return i;
    }

    @Override // X.AnonymousClass0tC
    public final boolean equals(@NullableDecl Object obj) {
        return AnonymousClass0tF.A00(this, obj);
    }

    @Override // X.AnonymousClass0tC
    public final int hashCode() {
        return AnonymousClass0td.A00(entrySet());
    }

    public final String toString() {
        return entrySet().toString();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
    public final AnonymousClass0u6<E> iterator() {
        return new AnonymousClass0Y9(this, entrySet().iterator());
    }
}
