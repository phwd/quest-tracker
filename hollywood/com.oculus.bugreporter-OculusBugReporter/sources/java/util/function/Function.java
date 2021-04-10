package java.util.function;

import java.util.Objects;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);

    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return new Function(before) {
            /* class java.util.function.$$Lambda$Function$kjgb589uNKoZ3YFTNw1KwlDNBo */
            private final /* synthetic */ Function f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Function.this.apply(this.f$1.apply(obj));
            }
        };
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return new Function(after) {
            /* class java.util.function.$$Lambda$Function$T8wYIfMRq5hbW0Q4qNkHIIrIBA */
            private final /* synthetic */ Function f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$1.apply(Function.this.apply(obj));
            }
        };
    }

    static default <T> Function<T, T> identity() {
        return $$Lambda$Function$1mm3dZ9IMG2T6zAULCCEh3eoHSY.INSTANCE;
    }

    static /* synthetic */ default Object lambda$identity$2(Object t) {
        return t;
    }
}
