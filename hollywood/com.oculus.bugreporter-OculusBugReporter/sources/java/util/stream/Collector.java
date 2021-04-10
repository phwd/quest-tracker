package java.util.stream;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface Collector<T, A, R> {

    public enum Characteristics {
        CONCURRENT,
        UNORDERED,
        IDENTITY_FINISH
    }

    BiConsumer<A, T> accumulator();

    Set<Characteristics> characteristics();

    BinaryOperator<A> combiner();

    Function<A, R> finisher();

    Supplier<A> supplier();

    static default <T, R> Collector<T, R, R> of(Supplier<R> supplier, BiConsumer<R, T> accumulator, BinaryOperator<R> combiner, Characteristics... characteristics) {
        Set<Characteristics> cs;
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(characteristics);
        if (characteristics.length == 0) {
            cs = Collectors.CH_ID;
        } else {
            cs = Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, characteristics));
        }
        return new Collectors.CollectorImpl(supplier, accumulator, combiner, cs);
    }

    static default <T, A, R> Collector<T, A, R> of(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Function<A, R> finisher, Characteristics... characteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(accumulator);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(finisher);
        Objects.requireNonNull(characteristics);
        Set<Characteristics> cs = Collectors.CH_NOID;
        if (characteristics.length > 0) {
            Set<Characteristics> cs2 = EnumSet.noneOf(Characteristics.class);
            Collections.addAll(cs2, characteristics);
            cs = Collections.unmodifiableSet(cs2);
        }
        return new Collectors.CollectorImpl(supplier, accumulator, combiner, finisher, cs);
    }
}
