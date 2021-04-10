package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);

    default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return new BiConsumer(after) {
            /* class java.util.function.$$Lambda$BiConsumer$V89VXFfSN6jmLaAoQrZCMiBju4 */
            private final /* synthetic */ BiConsumer f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                BiConsumer.lambda$andThen$0(BiConsumer.this, this.f$1, obj, obj2);
            }
        };
    }

    /* JADX INFO: Multiple debug info for r0v0 java.util.function.BiConsumer: [D('_this' java.util.function.BiConsumer), D('this' java.util.function.BiConsumer<T, U>)] */
    static /* synthetic */ default void lambda$andThen$0(BiConsumer _this, BiConsumer after, Object l, Object r) {
        _this.accept(l, r);
        after.accept(l, r);
    }
}
