package com.google.common.collect;

import X.AnonymousClass0Y4;
import X.AnonymousClass0u6;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.concurrent.LazyInit;

@GwtCompatible(emulated = true, serializable = true)
public final class SingletonImmutableSet<E> extends ImmutableSet<E> {
    @LazyInit
    public transient int A00;
    public final transient E A01;

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean A08() {
        return false;
    }

    public final int size() {
        return 1;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList<E> A09() {
        return ImmutableList.of(this.A01);
    }

    @Override // com.google.common.collect.ImmutableSet
    public final boolean A0A() {
        if (this.A00 != 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean contains(Object obj) {
        return this.A01.equals(obj);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int copyIntoArray(Object[] objArr, int i) {
        objArr[i] = this.A01;
        return i + 1;
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
        return "[" + this.A01.toString() + ']';
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

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public final AnonymousClass0u6<E> iterator() {
        return new AnonymousClass0Y4(this.A01);
    }
}
