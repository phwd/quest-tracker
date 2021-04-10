package com.google.common.collect;

import X.C0374Uk;
import X.C1163uG;
import X.UM;
import X.UQ;
import com.google.common.base.Preconditions;
import java.io.Serializable;

public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    public static final RegularImmutableMultiset A03 = new RegularImmutableMultiset(new UQ());
    public transient ImmutableSet A00;
    public final transient UQ A01;
    public final transient int A02;

    public final class ElementSet extends IndexedImmutableSet<E> {
        public ElementSet() {
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            return RegularImmutableMultiset.this.contains(obj);
        }

        public final int size() {
            return RegularImmutableMultiset.this.A01.A02;
        }
    }

    public class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final int[] counts;
        public final Object[] elements;

        public Object readResolve() {
            C1163uG uGVar = new C1163uG(this.elements.length);
            int i = 0;
            while (true) {
                Object[] objArr = this.elements;
                if (i >= objArr.length) {
                    break;
                }
                uGVar.A00(objArr[i], this.counts[i]);
                i++;
            }
            UQ uq = uGVar.A00;
            if (uq.A02 == 0) {
                return RegularImmutableMultiset.A03;
            }
            uGVar.A01 = true;
            return new RegularImmutableMultiset(uq);
        }

        public SerializedForm(UM um) {
            int size = um.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i = 0;
            for (E e : um.entrySet()) {
                this.elements[i] = e.A01();
                this.counts[i] = e.A00();
                i++;
            }
        }
    }

    @Override // X.UM
    public final int A1V(Object obj) {
        UQ uq = this.A01;
        int A04 = uq.A04(obj);
        if (A04 == -1) {
            return 0;
        }
        return uq.A05[A04];
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public RegularImmutableMultiset(UQ uq) {
        this.A01 = uq;
        long j = 0;
        int i = 0;
        while (true) {
            int i2 = uq.A02;
            if (i < i2) {
                Preconditions.checkElementIndex(i, i2);
                j += (long) uq.A05[i];
                i++;
            } else {
                this.A02 = C0374Uk.A00(j);
                return;
            }
        }
    }

    @Override // X.UM
    public final int size() {
        return this.A02;
    }
}
