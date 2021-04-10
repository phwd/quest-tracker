package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.oculus.common.build.BuildConfig;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class SingletonImmutableSet<E> extends ImmutableSet<E> {
    @LazyInit
    public transient int A00;
    public final transient E A01;

    public final int size() {
        return 1;
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
