package com.google.common.collect;

import X.AL;
import X.AbstractC0181Ug;
import X.AnonymousClass34;
import X.AnonymousClass3s;
import X.BJ;
import X.C0186Un;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    public static final RegularImmutableMultiset<Object> A03 = new RegularImmutableMultiset<>(new AnonymousClass3s());
    @LazyInit
    public transient ImmutableSet<E> A00;
    public final transient AnonymousClass3s<E> A01;
    public final transient int A02;

    public final class ElementSet extends IndexedImmutableSet<E> {
        public ElementSet() {
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public final E A0H(int i) {
            AnonymousClass3s<E> r1 = RegularImmutableMultiset.this.A01;
            Preconditions.checkElementIndex(i, r1.A02);
            return (E) r1.A07[i];
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(@NullableDecl Object obj) {
            return RegularImmutableMultiset.this.contains(obj);
        }

        public final int size() {
            return RegularImmutableMultiset.this.A01.A02;
        }
    }

    @GwtIncompatible
    public static class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final int[] counts;
        public final Object[] elements;

        public Object readResolve() {
            C0186Un un = new C0186Un(this.elements.length);
            int i = 0;
            while (true) {
                Object[] objArr = this.elements;
                if (i >= objArr.length) {
                    break;
                }
                un.A00(objArr[i], this.counts[i]);
                i++;
            }
            AnonymousClass3s<E> r1 = un.A00;
            if (r1.A02 == 0) {
                return RegularImmutableMultiset.A03;
            }
            un.A01 = true;
            return new RegularImmutableMultiset(r1);
        }

        public SerializedForm(AnonymousClass34<?> r6) {
            int size = r6.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (AbstractC0181Ug ug : r6.entrySet()) {
                this.elements[i] = ug.A01();
                this.counts[i] = ug.A00();
                i++;
            }
        }
    }

    @Override // com.google.common.collect.ImmutableMultiset
    public final ImmutableSet<E> A0F() {
        ImmutableSet<E> immutableSet = this.A00;
        if (immutableSet != null) {
            return immutableSet;
        }
        ElementSet elementSet = new ElementSet();
        this.A00 = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    public final Multiset.Entry<E> A0G(int i) {
        AnonymousClass3s<E> r1 = this.A01;
        Preconditions.checkElementIndex(i, r1.A02);
        return new BJ(r1, i);
    }

    @Override // X.AnonymousClass34
    public final int A1c(@NullableDecl Object obj) {
        AnonymousClass3s<E> r2 = this.A01;
        int A022 = r2.A02(obj);
        if (A022 == -1) {
            return 0;
        }
        return r2.A05[A022];
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public RegularImmutableMultiset(AnonymousClass3s<E> r6) {
        this.A01 = r6;
        long j = 0;
        int i = 0;
        while (true) {
            int i2 = r6.A02;
            if (i < i2) {
                Preconditions.checkElementIndex(i, i2);
                j += (long) r6.A05[i];
                i++;
            } else {
                this.A02 = AL.A00(j);
                return;
            }
        }
    }

    @Override // X.AnonymousClass34
    public final int size() {
        return this.A02;
    }
}
