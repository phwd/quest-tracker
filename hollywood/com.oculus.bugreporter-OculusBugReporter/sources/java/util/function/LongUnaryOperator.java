package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface LongUnaryOperator {
    long applyAsLong(long j);

    default LongUnaryOperator compose(LongUnaryOperator before) {
        Objects.requireNonNull(before);
        return new LongUnaryOperator(before) {
            /* class java.util.function.$$Lambda$LongUnaryOperator$e52YMvir03pwSw7KvpRuqEbSDRg */
            private final /* synthetic */ LongUnaryOperator f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.LongUnaryOperator
            public final long applyAsLong(long j) {
                return LongUnaryOperator.this.applyAsLong(this.f$1.applyAsLong(j));
            }
        };
    }

    default LongUnaryOperator andThen(LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return new LongUnaryOperator(after) {
            /* class java.util.function.$$Lambda$LongUnaryOperator$MxQtG8PPTeFzxiwgpkR3tXCHLU */
            private final /* synthetic */ LongUnaryOperator f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.LongUnaryOperator
            public final long applyAsLong(long j) {
                return this.f$1.applyAsLong(LongUnaryOperator.this.applyAsLong(j));
            }
        };
    }

    static default LongUnaryOperator identity() {
        return $$Lambda$LongUnaryOperator$kI3lBaNH3h6ldTmGeiEUd61CYJI.INSTANCE;
    }

    static /* synthetic */ default long lambda$identity$2(long t) {
        return t;
    }
}
