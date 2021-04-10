package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface DoubleUnaryOperator {
    double applyAsDouble(double d);

    default DoubleUnaryOperator compose(DoubleUnaryOperator before) {
        Objects.requireNonNull(before);
        return new DoubleUnaryOperator(before) {
            /* class java.util.function.$$Lambda$DoubleUnaryOperator$TV17Df571GWp0dWUym3y8OK6ZbM */
            private final /* synthetic */ DoubleUnaryOperator f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d) {
                return DoubleUnaryOperator.this.applyAsDouble(this.f$1.applyAsDouble(d));
            }
        };
    }

    default DoubleUnaryOperator andThen(DoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return new DoubleUnaryOperator(after) {
            /* class java.util.function.$$Lambda$DoubleUnaryOperator$EzzlhUGRoL66wVBCG_euZgCCA */
            private final /* synthetic */ DoubleUnaryOperator f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d) {
                return this.f$1.applyAsDouble(DoubleUnaryOperator.this.applyAsDouble(d));
            }
        };
    }

    static default DoubleUnaryOperator identity() {
        return $$Lambda$DoubleUnaryOperator$i7wtM_8OusCB32HCfZ4usZ4zaQ.INSTANCE;
    }

    static /* synthetic */ default double lambda$identity$2(double t) {
        return t;
    }
}
