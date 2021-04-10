package com.facebook.common.util;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Comparator;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ComparableUtil {
    public static <T extends Comparable<T>> boolean lessThan(T t, T t2) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(t2);
        return t.compareTo(t2) < 0;
    }

    public static <T extends Comparable<T>> boolean lessThanOrEqual(T t, T t2) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(t2);
        return t.compareTo(t2) <= 0;
    }

    public static <T extends Comparable<T>> boolean greaterThan(T t, T t2) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(t2);
        return t.compareTo(t2) > 0;
    }

    public static <T extends Comparable<T>> boolean greaterThanOrEqual(T t, T t2) {
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(t2);
        return t.compareTo(t2) >= 0;
    }

    public static <T> Comparator<T> compareBy(Function<T, Integer> function) {
        return new TransformingComparator(function);
    }

    private static class TransformingComparator<V> implements Comparator<V> {
        private final Function<V, Integer> mFunction;

        TransformingComparator(Function<V, Integer> function) {
            this.mFunction = function;
        }

        @Override // java.util.Comparator
        public int compare(@Nullable V v, @Nullable V v2) {
            int intValue = this.mFunction.apply(v).intValue();
            int intValue2 = this.mFunction.apply(v2).intValue();
            if (intValue < intValue2) {
                return -1;
            }
            return intValue > intValue2 ? 1 : 0;
        }
    }
}
