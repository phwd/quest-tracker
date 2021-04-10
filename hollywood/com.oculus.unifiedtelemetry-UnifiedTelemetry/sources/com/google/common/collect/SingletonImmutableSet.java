package com.google.common.collect;

import X.AnonymousClass8f;
import X.C0185Uk;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.concurrent.LazyInit;

@GwtCompatible(emulated = true, serializable = true)
public final class SingletonImmutableSet<E> extends ImmutableSet<E> {
    @LazyInit
    public transient int A00;
    public final transient E A01;

    public final int size() {
        return 1;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int A0C(Object[] objArr, int i) {
        objArr[i] = this.A01;
        return i + 1;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final AnonymousClass8f<E> A0D() {
        return new C0185Uk(this.A01);
    }

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList<E> A0E() {
        return ImmutableList.A07(this.A01);
    }

    @Override // com.google.common.collect.ImmutableSet
    public final boolean A0G() {
        if (this.A00 != 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(Object obj) {
        return this.A01.equals(obj);
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        int i = this.A00;
        if (i != 0) {
            return i;
        }
        int hashCode = this.A01.hashCode();
        this.A00 = hashCode;
        return hashCode;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.A01.toString());
        sb.append(']');
        return sb.toString();
    }

    public SingletonImmutableSet(E e) {
        if (e != null) {
            this.A01 = e;
            return;
        }
        throw null;
    }

    public SingletonImmutableSet(E e, int i) {
        this.A01 = e;
        this.A00 = i;
    }
}
