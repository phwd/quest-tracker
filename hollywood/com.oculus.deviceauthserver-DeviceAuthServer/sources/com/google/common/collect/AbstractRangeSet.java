package com.google.common.collect;

import java.lang.Comparable;
import javax.annotation.Nullable;

abstract class AbstractRangeSet<C extends Comparable> implements RangeSet<C> {
    @Override // com.google.common.collect.RangeSet
    public abstract boolean encloses(Range<C> range);

    @Override // com.google.common.collect.RangeSet
    public abstract Range<C> rangeContaining(C c);

    AbstractRangeSet() {
    }

    @Override // com.google.common.collect.RangeSet
    public boolean contains(C value) {
        return rangeContaining(value) != null;
    }

    @Override // com.google.common.collect.RangeSet
    public boolean isEmpty() {
        return asRanges().isEmpty();
    }

    @Override // com.google.common.collect.RangeSet
    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet
    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeSet
    public void clear() {
        remove(Range.all());
    }

    @Override // com.google.common.collect.RangeSet
    public boolean enclosesAll(RangeSet<C> other) {
        for (Range<C> range : other.asRanges()) {
            if (!encloses(range)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.common.collect.RangeSet
    public void addAll(RangeSet<C> other) {
        for (Range<C> range : other.asRanges()) {
            add(range);
        }
    }

    @Override // com.google.common.collect.RangeSet
    public void removeAll(RangeSet<C> other) {
        for (Range<C> range : other.asRanges()) {
            remove(range);
        }
    }

    @Override // com.google.common.collect.RangeSet
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RangeSet) {
            return asRanges().equals(((RangeSet) obj).asRanges());
        }
        return false;
    }

    @Override // com.google.common.collect.RangeSet
    public final int hashCode() {
        return asRanges().hashCode();
    }

    @Override // com.google.common.collect.RangeSet
    public final String toString() {
        return asRanges().toString();
    }
}
