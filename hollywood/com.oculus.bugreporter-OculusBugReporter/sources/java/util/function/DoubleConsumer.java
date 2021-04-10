package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface DoubleConsumer {
    void accept(double d);

    default DoubleConsumer andThen(DoubleConsumer after) {
        Objects.requireNonNull(after);
        return new DoubleConsumer(after) {
            /* class java.util.function.$$Lambda$DoubleConsumer$HNSB3MjwBDXE7Kpt1CBT9h3T8 */
            private final /* synthetic */ DoubleConsumer f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.DoubleConsumer
            public final void accept(double d) {
                DoubleConsumer.lambda$andThen$0(DoubleConsumer.this, this.f$1, d);
            }
        };
    }

    static /* synthetic */ default void lambda$andThen$0(DoubleConsumer _this, DoubleConsumer after, double t) {
        _this.accept(t);
        after.accept(t);
    }
}
