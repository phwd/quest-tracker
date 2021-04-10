package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.lang.Comparable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
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
        return enclosesAll(other.asRanges());
    }

    @Override // com.google.common.collect.RangeSet
    public boolean enclosesAll(Iterable<Range<C>> ranges) {
        for (Range<C> range : ranges) {
            if (!encloses(range)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.common.collect.RangeSet
    public void addAll(RangeSet<C> other) {
        addAll(other.asRanges());
    }

    @Override // com.google.common.collect.RangeSet
    public void addAll(Iterable<Range<C>> ranges) {
        for (Range<C> range : ranges) {
            add(range);
        }
    }

    @Override // com.google.common.collect.RangeSet
    public void removeAll(RangeSet<C> other) {
        removeAll(other.asRanges());
    }

    @Override // com.google.common.collect.RangeSet
    public void removeAll(Iterable<Range<C>> ranges) {
        for (Range<C> range : ranges) {
            remove(range);
        }
    }

    @Override // com.google.common.collect.RangeSet
    public boolean intersects(Range<C> otherRange) {
        return !subRangeSet(otherRange).isEmpty();
    }

    @Override // com.google.common.collect.RangeSet
    public boolean equals(@NullableDecl Object obj) {
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
