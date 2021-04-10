package java.util.function;

import java.util.Comparator;
import java.util.Objects;

@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
    static default <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return new BinaryOperator() {
            /* class java.util.function.$$Lambda$BinaryOperator$WKN0kahVeFfmJEk_tKszY8tRayo */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return BinaryOperator.lambda$minBy$0(Comparator.this, obj, obj2);
            }
        };
    }

    static /* synthetic */ default Object lambda$minBy$0(Comparator comparator, Object a, Object b) {
        return comparator.compare(a, b) <= 0 ? a : b;
    }

    static default <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return new BinaryOperator() {
            /* class java.util.function.$$Lambda$BinaryOperator$V_WUclL0kAOZvMw9EtWtwAvmNJc */

            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return BinaryOperator.lambda$maxBy$1(Comparator.this, obj, obj2);
            }
        };
    }

    static /* synthetic */ default Object lambda$maxBy$1(Comparator comparator, Object a, Object b) {
        return comparator.compare(a, b) >= 0 ? a : b;
    }
}
