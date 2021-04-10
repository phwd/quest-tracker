package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class Equivalence<T> {
    /* access modifiers changed from: protected */
    public abstract boolean doEquivalent(T t, T t2);

    /* access modifiers changed from: protected */
    public abstract int doHash(T t);

    protected Equivalence() {
    }

    public final boolean equivalent(@Nullable T a, @Nullable T b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return doEquivalent(a, b);
    }

    public final int hash(@Nullable T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    public final <F> Equivalence<F> onResultOf(Function<F, ? extends T> function) {
        return new FunctionalEquivalence(function, this);
    }

    public final <S extends T> Wrapper<S> wrap(@Nullable S reference) {
        return new Wrapper<>(reference);
    }

    public static final class Wrapper<T> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Equivalence<? super T> equivalence;
        @Nullable
        private final T reference;

        private Wrapper(Equivalence<? super T> equivalence2, @Nullable T reference2) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence2);
            this.reference = reference2;
        }

        @Nullable
        public T get() {
            return this.reference;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Wrapper)) {
                return false;
            }
            Wrapper<?> that = (Wrapper) obj;
            if (this.equivalence.equals(that.equivalence)) {
                return this.equivalence.equivalent(this.reference, that.reference);
            }
            return false;
        }

        public int hashCode() {
            return this.equivalence.hash(this.reference);
        }

        public String toString() {
            return this.equivalence + ".wrap(" + ((Object) this.reference) + ")";
        }
    }

    @GwtCompatible(serializable = true)
    public final <S extends T> Equivalence<Iterable<S>> pairwise() {
        return new PairwiseEquivalence(this);
    }

    @Beta
    public final Predicate<T> equivalentTo(@Nullable T target) {
        return new EquivalentToPredicate(this, target);
    }

    private static final class EquivalentToPredicate<T> implements Predicate<T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Equivalence<T> equivalence;
        @Nullable
        private final T target;

        EquivalentToPredicate(Equivalence<T> equivalence2, @Nullable T target2) {
            this.equivalence = (Equivalence) Preconditions.checkNotNull(equivalence2);
            this.target = target2;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(@Nullable T input) {
            return this.equivalence.equivalent(input, this.target);
        }

        @Override // com.google.common.base.Predicate
        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EquivalentToPredicate)) {
                return false;
            }
            EquivalentToPredicate<?> that = (EquivalentToPredicate) obj;
            if (!this.equivalence.equals(that.equivalence) || !Objects.equal(this.target, that.target)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.equivalence, this.target);
        }

        public String toString() {
            return this.equivalence + ".equivalentTo(" + ((Object) this.target) + ")";
        }
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return Identity.INSTANCE;
    }

    static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals INSTANCE = new Equals();
        private static final long serialVersionUID = 1;

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

        private Object readResolve() {
            return INSTANCE;
        }
    }

    static final class Identity extends Equivalence<Object> implements Serializable {
        static final Identity INSTANCE = new Identity();
        private static final long serialVersionUID = 1;

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

        private Object readResolve() {
            return INSTANCE;
        }
    }
}
