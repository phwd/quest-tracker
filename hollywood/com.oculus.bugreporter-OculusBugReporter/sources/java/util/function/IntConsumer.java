package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface IntConsumer {
    void accept(int i);

    default IntConsumer andThen(IntConsumer after) {
        Objects.requireNonNull(after);
        return new IntConsumer(after) {
            /* class java.util.function.$$Lambda$IntConsumer$Zkqv8_f6uSuSHCYm5dVGj2OCUKA */
            private final /* synthetic */ IntConsumer f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                IntConsumer.lambda$andThen$0(IntConsumer.this, this.f$1, i);
            }
        };
    }

    static /* synthetic */ default void lambda$andThen$0(IntConsumer _this, IntConsumer after, int t) {
        _this.accept(t);
        after.accept(t);
    }
}
