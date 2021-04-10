package com.google.common.collect;

import X.AbstractC1179ua;
import X.UM;
import X.UO;
import X.UX;
import com.google.common.collect.RegularImmutableMultiset;
import java.io.Serializable;
import java.util.Set;

public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements UM<E> {
    public transient ImmutableList A00;
    public transient ImmutableSet A01;

    public final class EntrySet extends IndexedImmutableSet {
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof AbstractC1179ua)) {
                return false;
            }
            AbstractC1179ua uaVar = (AbstractC1179ua) obj;
            if (uaVar.A00() <= 0 || ImmutableMultiset.this.A1V(uaVar.A01()) != uaVar.A00()) {
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
            ImmutableSet immutableSet = regularImmutableMultiset.A00;
            if (immutableSet == null) {
                immutableSet = new RegularImmutableMultiset.ElementSet();
                regularImmutableMultiset.A00 = immutableSet;
            }
            return immutableSet.size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return new EntrySetSerializedForm(ImmutableMultiset.this);
        }
    }

    public class EntrySetSerializedForm implements Serializable {
        public final ImmutableMultiset multiset;

        public Object readResolve() {
            return this.multiset.entrySet();
        }

        public EntrySetSerializedForm(ImmutableMultiset immutableMultiset) {
            this.multiset = immutableMultiset;
        }
    }

    @Override // X.UM
    public final /* bridge */ /* synthetic */ Set A1m() {
        RegularImmutableMultiset regularImmutableMultiset = (RegularImmutableMultiset) this;
        ImmutableSet immutableSet = regularImmutableMultiset.A00;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMultiset.ElementSet elementSet = new RegularImmutableMultiset.ElementSet();
        regularImmutableMultiset.A00 = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public abstract Object writeReplace();

    @Override // com.google.common.collect.ImmutableCollection
    public final ImmutableList A0D() {
        ImmutableList immutableList = this.A00;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList A0D = super.A0D();
        this.A00 = A0D;
        return A0D;
    }

    /* renamed from: A0F */
    public final ImmutableSet entrySet() {
        ImmutableSet immutableSet = this.A01;
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

    @Override // X.UM
    public final int A19(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.UM
    public final int A4m(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.UM
    public final int A4z(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.UM
    public final boolean A50(Object obj, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // X.UM, com.google.common.collect.ImmutableCollection
    public final boolean contains(Object obj) {
        if (A1V(obj) > 0) {
            return true;
        }
        return false;
    }

    @Override // X.UM
    public final boolean equals(Object obj) {
        return UO.A00(this, obj);
    }

    @Override // X.UM
    public final int hashCode() {
        return UX.A00(entrySet());
    }

    public final String toString() {
        return entrySet().toString();
    }
}
