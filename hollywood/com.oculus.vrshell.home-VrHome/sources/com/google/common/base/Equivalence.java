package com.google.common.base;

import java.io.Serializable;

public abstract class Equivalence<T> {
    /* access modifiers changed from: protected */
    public abstract boolean doEquivalent(T t, T t2);

    /* access modifiers changed from: protected */
    public abstract int doHash(T t);

    protected Equivalence() {
    }

    public final boolean equivalent(T a, T b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return doEquivalent(a, b);
    }

    public final int hash(T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals INSTANCE = new Equals();

        Equals() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object a, Object b) {
            return a.equals(b);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(Object o) {
            return o.hashCode();
        }
    }

    static final class Identity extends Equivalence<Object> implements Serializable {
        static final Identity INSTANCE = new Identity();

        Identity() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public boolean doEquivalent(Object a, Object b) {
            return false;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Equivalence
        public int doHash(Object o) {
            return System.identityHashCode(o);
        }
    }
}
