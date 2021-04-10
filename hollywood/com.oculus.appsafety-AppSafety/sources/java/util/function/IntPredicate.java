package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface IntPredicate {
    boolean test(int i);

    default IntPredicate and(IntPredicate other) {
        Objects.requireNonNull(other);
        return new IntPredicate(other) {
            /* class java.util.function.$$Lambda$IntPredicate$Gjqjw1UkLLbkSrWX6rKKkHJDvzI */
            private final /* synthetic */ IntPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IntPredicate.lambda$and$0(IntPredicate.this, this.f$1, i);
            }
        };
    }

    static /* synthetic */ default boolean lambda$and$0(IntPredicate _this, IntPredicate other, int value) {
        return _this.test(value) && other.test(value);
    }

    static /* synthetic */ default boolean lambda$negate$1(IntPredicate _this, int value) {
        return !_this.test(value);
    }

    default IntPredicate negate() {
        return new IntPredicate() {
            /* class java.util.function.$$Lambda$IntPredicate$6LuiLiTSEVs3MpquRl2gnnnEIxg */

            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IntPredicate.lambda$negate$1(IntPredicate.this, i);
            }
        };
    }

    default IntPredicate or(IntPredicate other) {
        Objects.requireNonNull(other);
        return new IntPredicate(other) {
            /* class java.util.function.$$Lambda$IntPredicate$K711jAs3MudbXoV61T3AbYlIaU */
            private final /* synthetic */ IntPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IntPredicate.lambda$or$2(IntPredicate.this, this.f$1, i);
            }
        };
    }

    static /* synthetic */ default boolean lambda$or$2(IntPredicate _this, IntPredicate other, int value) {
        return _this.test(value) || other.test(value);
    }
}
