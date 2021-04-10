package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);

    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return new BiFunction(after) {
            /* class java.util.function.$$Lambda$BiFunction$q2HhQ1fzCu6oYNirKhp1W_vpSM */
            private final /* synthetic */ Function f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return this.f$1.apply(BiFunction.this.apply(obj, obj2));
            }
        };
    }
}
