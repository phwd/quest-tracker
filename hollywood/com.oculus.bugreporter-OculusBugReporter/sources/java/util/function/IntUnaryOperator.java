package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface IntUnaryOperator {
    int applyAsInt(int i);

    default IntUnaryOperator compose(IntUnaryOperator before) {
        Objects.requireNonNull(before);
        return new IntUnaryOperator(before) {
            /* class java.util.function.$$Lambda$IntUnaryOperator$HBwqtJWwVkNFC818pCzqJ5KBLm0 */
            private final /* synthetic */ IntUnaryOperator f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return IntUnaryOperator.this.applyAsInt(this.f$1.applyAsInt(i));
            }
        };
    }

    default IntUnaryOperator andThen(IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return new IntUnaryOperator(after) {
            /* class java.util.function.$$Lambda$IntUnaryOperator$F5l8VUrgGacAzg6VKzynJCfDBx4 */
            private final /* synthetic */ IntUnaryOperator f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return this.f$1.applyAsInt(IntUnaryOperator.this.applyAsInt(i));
            }
        };
    }

    static default IntUnaryOperator identity() {
        return $$Lambda$IntUnaryOperator$DKxG0oyUAYjk17nXTQ5xEXFgU.INSTANCE;
    }

    static /* synthetic */ default int lambda$identity$2(int t) {
        return t;
    }
}
