package com.google.common.collect;

public final class SingletonImmutableSet<E> extends ImmutableSet<E> {
    public transient int A00;
    public final transient Object A01;

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

    public SingletonImmutableSet(Object obj) {
        if (obj != null) {
            this.A01 = obj;
            return;
        }
        throw null;
    }

    public SingletonImmutableSet(Object obj, int i) {
        this.A01 = obj;
        this.A00 = i;
    }
}
