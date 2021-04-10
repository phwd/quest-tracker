package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface DoublePredicate {
    boolean test(double d);

    default DoublePredicate and(DoublePredicate other) {
        Objects.requireNonNull(other);
        return new DoublePredicate(other) {
            /* class java.util.function.$$Lambda$DoublePredicate$M8n9M3rXNLuHyZ2e0F7hUxAtVx0 */
            private final /* synthetic */ DoublePredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.DoublePredicate
            public final boolean test(double d) {
                return DoublePredicate.lambda$and$0(DoublePredicate.this, this.f$1, d);
            }
        };
    }

    static /* synthetic */ default boolean lambda$and$0(DoublePredicate _this, DoublePredicate other, double value) {
        return _this.test(value) && other.test(value);
    }

    static /* synthetic */ default boolean lambda$negate$1(DoublePredicate _this, double value) {
        return !_this.test(value);
    }

    default DoublePredicate negate() {
        return new DoublePredicate() {
            /* class java.util.function.$$Lambda$DoublePredicate$01E7YsTWsjaQSI72YV852C1Uqco */

            @Override // java.util.function.DoublePredicate
            public final boolean test(double d) {
                return DoublePredicate.lambda$negate$1(DoublePredicate.this, d);
            }
        };
    }

    default DoublePredicate or(DoublePredicate other) {
        Objects.requireNonNull(other);
        return new DoublePredicate(other) {
            /* class java.util.function.$$Lambda$DoublePredicate$9YmJG7lSNUbb1veFxbs9aIWObk */
            private final /* synthetic */ DoublePredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.DoublePredicate
            public final boolean test(double d) {
                return DoublePredicate.lambda$or$2(DoublePredicate.this, this.f$1, d);
            }
        };
    }

    static /* synthetic */ default boolean lambda$or$2(DoublePredicate _this, DoublePredicate other, double value) {
        return _this.test(value) || other.test(value);
    }
}
