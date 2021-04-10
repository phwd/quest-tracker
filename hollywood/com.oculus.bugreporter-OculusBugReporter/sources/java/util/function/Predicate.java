package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return new Predicate(other) {
            /* class java.util.function.$$Lambda$Predicate$GyIVQ08CWbeMZxHDkkrN5apRkc */
            private final /* synthetic */ Predicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$and$0(Predicate.this, this.f$1, obj);
            }
        };
    }

    /* JADX INFO: Multiple debug info for r1v0 java.util.function.Predicate: [D('_this' java.util.function.Predicate), D('this' java.util.function.Predicate<T>)] */
    static /* synthetic */ default boolean lambda$and$0(Predicate _this, Predicate other, Object t) {
        return _this.test(t) && other.test(t);
    }

    /* JADX INFO: Multiple debug info for r1v0 java.util.function.Predicate: [D('_this' java.util.function.Predicate), D('this' java.util.function.Predicate<T>)] */
    static /* synthetic */ default boolean lambda$negate$1(Predicate _this, Object t) {
        return !_this.test(t);
    }

    default Predicate<T> negate() {
        return new Predicate() {
            /* class java.util.function.$$Lambda$Predicate$L51YwfosqnYQ8QKStSMYaDgSslA */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$negate$1(Predicate.this, obj);
            }
        };
    }

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return new Predicate(other) {
            /* class java.util.function.$$Lambda$Predicate$17UUIF1CH_K9duk0ChtjSwOycuM */
            private final /* synthetic */ Predicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Predicate.lambda$or$2(Predicate.this, this.f$1, obj);
            }
        };
    }

    /* JADX INFO: Multiple debug info for r1v0 java.util.function.Predicate: [D('_this' java.util.function.Predicate), D('this' java.util.function.Predicate<T>)] */
    static /* synthetic */ default boolean lambda$or$2(Predicate _this, Predicate other, Object t) {
        return _this.test(t) || other.test(t);
    }

    static default <T> Predicate<T> isEqual(Object targetRef) {
        if (targetRef == null) {
            return $$Lambda$wLIh0GiBW9398cTP8uaTH8KoGwo.INSTANCE;
        }
        return new Predicate() {
            /* class java.util.function.$$Lambda$Predicate$SDsDck317M7uJ9htNLy7zOBr1L8 */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Object.this.equals(obj);
            }
        };
    }
}
