package com.google.common.collect;

import X.AnonymousClass0Bw;
import X.AnonymousClass0dN;
import X.AnonymousClass0dz;
import X.AnonymousClass0r9;
import X.AnonymousClass0rF;
import X.C07820vP;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    public static final RegularImmutableMultiset<Object> A03 = new RegularImmutableMultiset<>(new AnonymousClass0rF());
    @LazyInit
    public transient ImmutableSet<E> A00;
    public final transient AnonymousClass0rF<E> A01;
    public final transient int A02;

    public final class ElementSet extends IndexedImmutableSet<E> {
        @Override // com.google.common.collect.ImmutableCollection
        public final boolean A0H() {
            return true;
        }

        public ElementSet() {
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public final E A0N(int i) {
            AnonymousClass0rF<E> r1 = RegularImmutableMultiset.this.A01;
            Preconditions.checkElementIndex(i, r1.A01);
            return (E) r1.A06[i];
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(@NullableDecl Object obj) {
            return RegularImmutableMultiset.this.contains(obj);
        }

        public final int size() {
            return RegularImmutableMultiset.this.A01.A01;
        }
    }

    @GwtIncompatible
    public static class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final int[] counts;
        public final Object[] elements;

        public Object readResolve() {
            AnonymousClass0dz r3 = new AnonymousClass0dz(this.elements.length);
            int i = 0;
            while (true) {
                Object[] objArr = this.elements;
                if (i >= objArr.length) {
                    break;
                }
                r3.A03(objArr[i], this.counts[i]);
                i++;
            }
            AnonymousClass0rF<E> r1 = r3.A00;
            if (r1.A01 == 0) {
                return RegularImmutableMultiset.A03;
            }
            r3.A01 = true;
            return new RegularImmutableMultiset(r1);
        }

        public SerializedForm(AnonymousClass0r9<?> r6) {
            int size = r6.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (AnonymousClass0dN r2 : r6.entrySet()) {
                this.elements[i] = r2.A01();
                this.counts[i] = r2.A00();
                i++;
            }
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean A0H() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    public final ImmutableSet<E> A0M() {
        ImmutableSet<E> immutableSet = this.A00;
        if (immutableSet != null) {
            return immutableSet;
        }
        ElementSet elementSet = new ElementSet();
        this.A00 = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    public final Multiset.Entry<E> A0N(int i) {
        AnonymousClass0rF<E> r1 = this.A01;
        Preconditions.checkElementIndex(i, r1.A01);
        return new AnonymousClass0Bw(r1, i);
    }

    @Override // X.AnonymousClass0r9
    public final int A1v(@NullableDecl Object obj) {
        AnonymousClass0rF<E> r2 = this.A01;
        int A06 = r2.A06(obj);
        if (A06 == -1) {
            return 0;
        }
        return r2.A04[A06];
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public RegularImmutableMultiset(AnonymousClass0rF<E> r6) {
        this.A01 = r6;
        long j = 0;
        int i = 0;
        while (true) {
            int i2 = r6.A01;
            if (i < i2) {
                Preconditions.checkElementIndex(i, i2);
                j += (long) r6.A04[i];
                i++;
            } else {
                this.A02 = C07820vP.A00(j);
                return;
            }
        }
    }

    @Override // X.AnonymousClass0r9
    public final int size() {
        return this.A02;
    }
}
