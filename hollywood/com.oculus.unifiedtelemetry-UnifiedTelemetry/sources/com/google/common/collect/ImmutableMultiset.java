package com.google.common.collect;

import X.AbstractC0181Ug;
import X.AnonymousClass34;
import X.AnonymousClass3g;
import X.AnonymousClass6y;
import X.AnonymousClass8f;
import X.C0187Uo;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements AnonymousClass34<E> {
    @LazyInit
    public transient ImmutableSet<Multiset.Entry<E>> A00;

    public final class EntrySet extends IndexedImmutableSet<Multiset.Entry<E>> {
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public final Object A0H(int i) {
            return ImmutableMultiset.this.A0G(i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof AbstractC0181Ug)) {
                return false;
            }
            AbstractC0181Ug ug = (AbstractC0181Ug) obj;
            if (ug.A00() <= 0 || ImmutableMultiset.this.A1c(ug.A01()) != ug.A00()) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet
        public final int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        public final int size() {
            return ImmutableMultiset.this.A1t().size();
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

    /* renamed from: A0F */
    public abstract ImmutableSet<E> A1t();

    public abstract Multiset.Entry<E> A0G(int i);

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public abstract Object writeReplace();

    /* renamed from: A0E */
    public final ImmutableSet<Multiset.Entry<E>> entrySet() {
        ImmutableSet<Multiset.Entry<E>> immutableSet = this.A00;
        if (immutableSet == null) {
            if (isEmpty()) {
                immutableSet = RegularImmutableSet.A05;
            } else {
                immutableSet = new EntrySet();
            }
            this.A00 = immutableSet;
        }
        return immutableSet;
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    @Deprecated
    public final int A11(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    @Deprecated
    public final int A4f(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    @Deprecated
    public final int A4x(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass34
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean A4y(E e, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int A0C(Object[] objArr, int i) {
        AnonymousClass8f<Multiset.Entry<E>> A0D = entrySet().iterator();
        while (A0D.hasNext()) {
            AbstractC0181Ug next = A0D.next();
            Arrays.fill(objArr, i, next.A00() + i, next.A01());
            i += next.A00();
        }
        return i;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final AnonymousClass8f<E> A0D() {
        return new C0187Uo(this, entrySet().iterator());
    }

    @Override // com.google.common.collect.ImmutableCollection, X.AnonymousClass34
    public final boolean contains(@NullableDecl Object obj) {
        if (A1c(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass34
    public final boolean equals(@NullableDecl Object obj) {
        return AnonymousClass3g.A00(this, obj);
    }

    @Override // X.AnonymousClass34
    public final int hashCode() {
        return AnonymousClass6y.A00(entrySet());
    }

    public final String toString() {
        return entrySet().toString();
    }
}
