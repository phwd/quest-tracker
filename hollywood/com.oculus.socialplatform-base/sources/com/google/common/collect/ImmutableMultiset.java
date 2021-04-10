package com.google.common.collect;

import X.AbstractC05490vp;
import X.AbstractC05710wh;
import X.AnonymousClass0f2;
import X.AnonymousClass0fN;
import X.AnonymousClass0wE;
import X.C05510vr;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements AbstractC05490vp<E> {
    @LazyInit
    public transient ImmutableList<E> A00;
    @LazyInit
    public transient ImmutableSet<Multiset.Entry<E>> A01;

    public final class EntrySet extends IndexedImmutableSet<Multiset.Entry<E>> {
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0F() {
            return ImmutableMultiset.this.A0F();
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public final Object A0L(int i) {
            return ImmutableMultiset.this.A0L(i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof AnonymousClass0f2)) {
                return false;
            }
            AnonymousClass0f2 r4 = (AnonymousClass0f2) obj;
            if (r4.A00() <= 0 || ImmutableMultiset.this.A2J(r4.A01()) != r4.A00()) {
                return false;
            }
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet
        public final int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        public final int size() {
            return ImmutableMultiset.this.A2k().size();
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

    /* renamed from: A0K */
    public abstract ImmutableSet<E> A2k();

    public abstract Multiset.Entry<E> A0L(int i);

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public abstract Object writeReplace();

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList<E> A0H() {
        ImmutableList<E> immutableList = this.A00;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> A0H = super.A0H();
        this.A00 = A0H;
        return A0H;
    }

    /* renamed from: A0J */
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

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    @Deprecated
    public final int A1A(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    @Deprecated
    public final int A92(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    @Deprecated
    public final int A9n(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC05490vp
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean A9o(E e, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int A0G(Object[] objArr, int i) {
        AbstractC05710wh<Multiset.Entry<E>> A0I = entrySet().iterator();
        while (A0I.hasNext()) {
            AnonymousClass0f2 next = A0I.next();
            Arrays.fill(objArr, i, next.A00() + i, next.A01());
            i += next.A00();
        }
        return i;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final AbstractC05710wh<E> A0I() {
        return new AnonymousClass0fN(this, entrySet().iterator());
    }

    @Override // com.google.common.collect.ImmutableCollection, X.AbstractC05490vp
    public final boolean contains(@NullableDecl Object obj) {
        if (A2J(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC05490vp
    public final boolean equals(@NullableDecl Object obj) {
        return C05510vr.A00(this, obj);
    }

    @Override // X.AbstractC05490vp
    public final int hashCode() {
        return AnonymousClass0wE.A00(entrySet());
    }

    public final String toString() {
        return entrySet().toString();
    }
}
