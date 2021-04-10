package com.google.common.collect;

import X.AbstractC0120Qz;
import X.C0145Tq;
import X.Mh;
import X.N4;
import X.RB;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    public static final RegularImmutableMultiset<Object> A03 = new RegularImmutableMultiset<>(new RB());
    @LazyInit
    public transient ImmutableSet<E> A00;
    public final transient RB<E> A01;
    public final transient int A02;

    public final class ElementSet extends IndexedImmutableSet<E> {
        public ElementSet() {
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
            N4 n4 = new N4(this.elements.length);
            int i = 0;
            while (true) {
                Object[] objArr = this.elements;
                if (i >= objArr.length) {
                    break;
                }
                n4.A00(objArr[i], this.counts[i]);
                i++;
            }
            RB<E> rb = n4.A00;
            if (rb.A02 == 0) {
                return RegularImmutableMultiset.A03;
            }
            n4.A01 = true;
            return new RegularImmutableMultiset(rb);
        }

        public SerializedForm(AbstractC0120Qz<?> qz) {
            int size = qz.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (Mh mh : qz.entrySet()) {
                this.elements[i] = mh.A01();
                this.counts[i] = mh.A00();
                i++;
            }
        }
    }

    @Override // X.AbstractC0120Qz
    public final int A1D(@NullableDecl Object obj) {
        RB<E> rb = this.A01;
        int A04 = rb.A04(obj);
        if (A04 == -1) {
            return 0;
        }
        return rb.A05[A04];
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public RegularImmutableMultiset(RB<E> rb) {
        this.A01 = rb;
        long j = 0;
        int i = 0;
        while (true) {
            int i2 = rb.A02;
            if (i < i2) {
                Preconditions.checkElementIndex(i, i2);
                j += (long) rb.A05[i];
                i++;
            } else {
                this.A02 = C0145Tq.A00(j);
                return;
            }
        }
    }

    @Override // X.AbstractC0120Qz
    public final int size() {
        return this.A02;
    }
}
