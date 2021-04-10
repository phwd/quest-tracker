package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface LongConsumer {
    void accept(long j);

    default LongConsumer andThen(LongConsumer after) {
        Objects.requireNonNull(after);
        return new LongConsumer(after) {
            /* class java.util.function.$$Lambda$LongConsumer$2wx0fq0YJI8kSCwhsFrV0qxRiZ4 */
            private final /* synthetic */ LongConsumer f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.LongConsumer
            public final void accept(long j) {
                LongConsumer.lambda$andThen$0(LongConsumer.this, this.f$1, j);
            }
        };
    }

    static /* synthetic */ default void lambda$andThen$0(LongConsumer _this, LongConsumer after, long t) {
        _this.accept(t);
        after.accept(t);
    }
}
