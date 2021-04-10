package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
public final class ExplicitOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final ImmutableMap<T, Integer> rankMap;

    ExplicitOrdering(List<T> valuesInOrder) {
        this(buildRankMap(valuesInOrder));
    }

    ExplicitOrdering(ImmutableMap<T, Integer> rankMap2) {
        this.rankMap = rankMap2;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T left, T right) {
        return rank(left) - rank(right);
    }

    private int rank(T value) {
        Integer rank = this.rankMap.get(value);
        if (rank != null) {
            return rank.intValue();
        }
        throw new Ordering.IncomparableValueException(value);
    }

    private static <T> ImmutableMap<T, Integer> buildRankMap(List<T> valuesInOrder) {
        ImmutableMap.Builder<T, Integer> builder = ImmutableMap.builder();
        int rank = 0;
        for (T value : valuesInOrder) {
            builder.put(value, Integer.valueOf(rank));
            rank++;
        }
        return builder.build();
    }

    public boolean equals(@Nullable Object object) {
        if (object instanceof ExplicitOrdering) {
            return this.rankMap.equals(((ExplicitOrdering) object).rankMap);
        }
        return false;
    }

    public int hashCode() {
        return this.rankMap.hashCode();
    }

    public String toString() {
        return "Ordering.explicit(" + this.rankMap.keySet() + ")";
    }
}
