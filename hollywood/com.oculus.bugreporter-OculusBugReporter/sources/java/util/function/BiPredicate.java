package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);

    default BiPredicate<T, U> and(BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return new BiPredicate(other) {
            /* class java.util.function.$$Lambda$BiPredicate$uIXzGqdBtyDdYjd7p0mbJFqyjRE */
            private final /* synthetic */ BiPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return BiPredicate.lambda$and$0(BiPredicate.this, this.f$1, obj, obj2);
            }
        };
    }

    /* JADX INFO: Multiple debug info for r1v0 java.util.function.BiPredicate: [D('_this' java.util.function.BiPredicate), D('this' java.util.function.BiPredicate<T, U>)] */
    static /* synthetic */ default boolean lambda$and$0(BiPredicate _this, BiPredicate other, Object t, Object u) {
        return _this.test(t, u) && other.test(t, u);
    }

    /* JADX INFO: Multiple debug info for r1v0 java.util.function.BiPredicate: [D('_this' java.util.function.BiPredicate), D('this' java.util.function.BiPredicate<T, U>)] */
    static /* synthetic */ default boolean lambda$negate$1(BiPredicate _this, Object t, Object u) {
        return !_this.test(t, u);
    }

    default BiPredicate<T, U> negate() {
        return new BiPredicate() {
            /* class java.util.function.$$Lambda$BiPredicate$ZiDuSsQaw4dQsCoX8HU1cLSeS8 */

            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return BiPredicate.lambda$negate$1(BiPredicate.this, obj, obj2);
            }
        };
    }

    default BiPredicate<T, U> or(BiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return new BiPredicate(other) {
            /* class java.util.function.$$Lambda$BiPredicate$OpXxJPTnCjvZwHcfaL3bBDqxCyQ */
            private final /* synthetic */ BiPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return BiPredicate.lambda$or$2(BiPredicate.this, this.f$1, obj, obj2);
            }
        };
    }

    /* JADX INFO: Multiple debug info for r1v0 java.util.function.BiPredicate: [D('_this' java.util.function.BiPredicate), D('this' java.util.function.BiPredicate<T, U>)] */
    static /* synthetic */ default boolean lambda$or$2(BiPredicate _this, BiPredicate other, Object t, Object u) {
        return _this.test(t, u) || other.test(t, u);
    }
}
