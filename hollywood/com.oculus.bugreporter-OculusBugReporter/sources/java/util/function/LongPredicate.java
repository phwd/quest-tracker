package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface LongPredicate {
    boolean test(long j);

    default LongPredicate and(LongPredicate other) {
        Objects.requireNonNull(other);
        return new LongPredicate(other) {
            /* class java.util.function.$$Lambda$LongPredicate$DaAkBBvcfHLiOGrLHthRI1hFBUQ */
            private final /* synthetic */ LongPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return LongPredicate.lambda$and$0(LongPredicate.this, this.f$1, j);
            }
        };
    }

    static /* synthetic */ default boolean lambda$and$0(LongPredicate _this, LongPredicate other, long value) {
        return _this.test(value) && other.test(value);
    }

    static /* synthetic */ default boolean lambda$negate$1(LongPredicate _this, long value) {
        return !_this.test(value);
    }

    default LongPredicate negate() {
        return new LongPredicate() {
            /* class java.util.function.$$Lambda$LongPredicate$Qy6TdFCh7weCJdGMkUTh3wmWoA */

            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return LongPredicate.lambda$negate$1(LongPredicate.this, j);
            }
        };
    }

    default LongPredicate or(LongPredicate other) {
        Objects.requireNonNull(other);
        return new LongPredicate(other) {
            /* class java.util.function.$$Lambda$LongPredicate$csV2YowuwbIk10M6jhbu2oq5to */
            private final /* synthetic */ LongPredicate f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return LongPredicate.lambda$or$2(LongPredicate.this, this.f$1, j);
            }
        };
    }

    static /* synthetic */ default boolean lambda$or$2(LongPredicate _this, LongPredicate other, long value) {
        return _this.test(value) || other.test(value);
    }
}
