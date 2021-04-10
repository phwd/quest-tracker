package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>(null);
    @Nullable
    private final T ref;

    public static <T> Optional<T> empty() {
        return (Optional<T>) EMPTY;
    }

    public static <T> Optional<T> of(T t) {
        Preconditions.checkNotNull(t);
        return new Optional<>(t);
    }

    public static <T> Optional<T> ofNullable(@Nullable T t) {
        return t == null ? empty() : of(t);
    }

    private Optional(@Nullable T t) {
        this.ref = t;
    }

    public boolean isPresent() {
        return this.ref != null;
    }

    public T get() {
        T t = this.ref;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException();
    }

    public <U> Optional<U> map(Function<? super T, ? extends U> function) {
        Preconditions.checkNotNull(function);
        return isPresent() ? ofNullable(function.apply(this.ref)) : empty();
    }

    public T orElse(T t) {
        T t2 = this.ref;
        return t2 != null ? t2 : t;
    }

    @Nullable
    public T orNull() {
        return this.ref;
    }

    public String toString() {
        if (!isPresent()) {
            return "Optional.empty()";
        }
        return "Optional.of(" + String.valueOf(this.ref) + ")";
    }
}
