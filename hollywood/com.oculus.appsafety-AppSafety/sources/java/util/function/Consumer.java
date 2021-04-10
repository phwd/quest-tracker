package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return new Consumer(after) {
            /* class java.util.function.$$Lambda$Consumer$fZIgy_f2Fa5seBa8ztxXTExq2p4 */
            private final /* synthetic */ Consumer f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Consumer.lambda$andThen$0(Consumer.this, this.f$1, obj);
            }
        };
    }

    /* JADX INFO: Multiple debug info for r0v0 java.util.function.Consumer: [D('this' java.util.function.Consumer<T>), D('_this' java.util.function.Consumer)] */
    static /* synthetic */ default void lambda$andThen$0(Consumer _this, Consumer after, Object t) {
        _this.accept(t);
        after.accept(t);
    }
}
