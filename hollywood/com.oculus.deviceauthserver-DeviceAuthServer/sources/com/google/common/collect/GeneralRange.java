package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
public final class GeneralRange<T> implements Serializable {
    private final Comparator<? super T> comparator;
    private final boolean hasLowerBound;
    private final boolean hasUpperBound;
    private final BoundType lowerBoundType;
    @Nullable
    private final T lowerEndpoint;
    private transient GeneralRange<T> reverse;
    private final BoundType upperBoundType;
    @Nullable
    private final T upperEndpoint;

    static <T extends Comparable> GeneralRange<T> from(Range<T> range) {
        T upperEndpoint2 = null;
        T lowerEndpoint2 = range.hasLowerBound() ? range.lowerEndpoint() : null;
        BoundType lowerBoundType2 = range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN;
        if (range.hasUpperBound()) {
            upperEndpoint2 = range.upperEndpoint();
        }
        return new GeneralRange<>(Ordering.natural(), range.hasLowerBound(), lowerEndpoint2, lowerBoundType2, range.hasUpperBound(), upperEndpoint2, range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN);
    }

    static <T> GeneralRange<T> all(Comparator<? super T> comparator2) {
        return new GeneralRange<>(comparator2, false, null, BoundType.OPEN, false, null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> downTo(Comparator<? super T> comparator2, @Nullable T endpoint, BoundType boundType) {
        return new GeneralRange<>(comparator2, true, endpoint, boundType, false, null, BoundType.OPEN);
    }

    static <T> GeneralRange<T> upTo(Comparator<? super T> comparator2, @Nullable T endpoint, BoundType boundType) {
        return new GeneralRange<>(comparator2, false, null, BoundType.OPEN, true, endpoint, boundType);
    }

    static <T> GeneralRange<T> range(Comparator<? super T> comparator2, @Nullable T lower, BoundType lowerType, @Nullable T upper, BoundType upperType) {
        return new GeneralRange<>(comparator2, true, lower, lowerType, true, upper, upperType);
    }

    private GeneralRange(Comparator<? super T> comparator2, boolean hasLowerBound2, @Nullable T lowerEndpoint2, BoundType lowerBoundType2, boolean hasUpperBound2, @Nullable T upperEndpoint2, BoundType upperBoundType2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        this.hasLowerBound = hasLowerBound2;
        this.hasUpperBound = hasUpperBound2;
        this.lowerEndpoint = lowerEndpoint2;
        this.lowerBoundType = (BoundType) Preconditions.checkNotNull(lowerBoundType2);
        this.upperEndpoint = upperEndpoint2;
        this.upperBoundType = (BoundType) Preconditions.checkNotNull(upperBoundType2);
        if (hasLowerBound2) {
            comparator2.compare(lowerEndpoint2, lowerEndpoint2);
        }
        if (hasUpperBound2) {
            comparator2.compare(upperEndpoint2, upperEndpoint2);
        }
        if (hasLowerBound2 && hasUpperBound2) {
            int cmp = comparator2.compare(lowerEndpoint2, upperEndpoint2);
            boolean z = true;
            Preconditions.checkArgument(cmp <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", lowerEndpoint2, upperEndpoint2);
            if (cmp == 0) {
                Preconditions.checkArgument((upperBoundType2 == BoundType.OPEN ? false : z) | (lowerBoundType2 != BoundType.OPEN));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Comparator<? super T> comparator() {
        return this.comparator;
    }

    /* access modifiers changed from: package-private */
    public boolean hasLowerBound() {
        return this.hasLowerBound;
    }

    /* access modifiers changed from: package-private */
    public boolean hasUpperBound() {
        return this.hasUpperBound;
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return (hasUpperBound() && tooLow(getUpperEndpoint())) || (hasLowerBound() && tooHigh(getLowerEndpoint()));
    }

    /* access modifiers changed from: package-private */
    public boolean tooLow(@Nullable T t) {
        boolean z = false;
        if (!hasLowerBound()) {
            return false;
        }
        int cmp = this.comparator.compare(t, getLowerEndpoint());
        boolean z2 = cmp < 0;
        boolean z3 = cmp == 0;
        if (getLowerBoundType() == BoundType.OPEN) {
            z = true;
        }
        return (z & z3) | z2;
    }

    /* access modifiers changed from: package-private */
    public boolean tooHigh(@Nullable T t) {
        boolean z = false;
        if (!hasUpperBound()) {
            return false;
        }
        int cmp = this.comparator.compare(t, getUpperEndpoint());
        boolean z2 = cmp > 0;
        boolean z3 = cmp == 0;
        if (getUpperBoundType() == BoundType.OPEN) {
            z = true;
        }
        return (z & z3) | z2;
    }

    /* access modifiers changed from: package-private */
    public boolean contains(@Nullable T t) {
        return !tooLow(t) && !tooHigh(t);
    }

    /* JADX INFO: Multiple debug info for r5v1 boolean: [D('hasUpBound' boolean), D('cmp' int)] */
    /* access modifiers changed from: package-private */
    public GeneralRange<T> intersect(GeneralRange<T> other) {
        T upEnd;
        boolean hasUpBound;
        BoundType upType;
        BoundType lowType;
        int cmp;
        int cmp2;
        int cmp3;
        Preconditions.checkNotNull(other);
        Preconditions.checkArgument(this.comparator.equals(other.comparator));
        boolean hasLowBound = this.hasLowerBound;
        T lowEnd = getLowerEndpoint();
        BoundType lowType2 = getLowerBoundType();
        if (!hasLowerBound()) {
            hasLowBound = other.hasLowerBound;
            lowEnd = other.getLowerEndpoint();
            lowType2 = other.getLowerBoundType();
        } else if (other.hasLowerBound() && ((cmp3 = this.comparator.compare(getLowerEndpoint(), other.getLowerEndpoint())) < 0 || (cmp3 == 0 && other.getLowerBoundType() == BoundType.OPEN))) {
            lowEnd = other.getLowerEndpoint();
            lowType2 = other.getLowerBoundType();
        }
        boolean hasUpBound2 = this.hasUpperBound;
        T upEnd2 = getUpperEndpoint();
        BoundType upType2 = getUpperBoundType();
        if (!hasUpperBound()) {
            boolean hasUpBound3 = other.hasUpperBound;
            T upEnd3 = other.getUpperEndpoint();
            upType2 = other.getUpperBoundType();
            hasUpBound = hasUpBound3;
            upEnd = upEnd3;
        } else if (!other.hasUpperBound() || ((cmp2 = this.comparator.compare(getUpperEndpoint(), other.getUpperEndpoint())) <= 0 && !(cmp2 == 0 && other.getUpperBoundType() == BoundType.OPEN))) {
            hasUpBound = hasUpBound2;
            upEnd = upEnd2;
        } else {
            T upEnd4 = other.getUpperEndpoint();
            upType2 = other.getUpperBoundType();
            hasUpBound = hasUpBound2;
            upEnd = upEnd4;
        }
        if (!hasLowBound || !hasUpBound || ((cmp = this.comparator.compare(lowEnd, upEnd)) <= 0 && !(cmp == 0 && lowType2 == BoundType.OPEN && upType2 == BoundType.OPEN))) {
            lowType = lowType2;
            upType = upType2;
        } else {
            lowEnd = upEnd;
            lowType = BoundType.OPEN;
            upType = BoundType.CLOSED;
        }
        return new GeneralRange<>(this.comparator, hasLowBound, lowEnd, lowType, hasUpBound, upEnd, upType);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof GeneralRange)) {
            return false;
        }
        GeneralRange<?> r = (GeneralRange) obj;
        if (!this.comparator.equals(r.comparator) || this.hasLowerBound != r.hasLowerBound || this.hasUpperBound != r.hasUpperBound || !getLowerBoundType().equals(r.getLowerBoundType()) || !getUpperBoundType().equals(r.getUpperBoundType()) || !Objects.equal(getLowerEndpoint(), r.getLowerEndpoint()) || !Objects.equal(getUpperEndpoint(), r.getUpperEndpoint())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.comparator, getLowerEndpoint(), getLowerBoundType(), getUpperEndpoint(), getUpperBoundType());
    }

    /* access modifiers changed from: package-private */
    public GeneralRange<T> reverse() {
        GeneralRange<T> result = this.reverse;
        if (result != null) {
            return result;
        }
        GeneralRange<T> result2 = new GeneralRange<>(Ordering.from(this.comparator).reverse(), this.hasUpperBound, getUpperEndpoint(), getUpperBoundType(), this.hasLowerBound, getLowerEndpoint(), getLowerBoundType());
        result2.reverse = this;
        this.reverse = result2;
        return result2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.comparator);
        sb.append(":");
        sb.append(this.lowerBoundType == BoundType.CLOSED ? '[' : '(');
        sb.append(this.hasLowerBound ? this.lowerEndpoint : "-∞");
        sb.append(',');
        sb.append(this.hasUpperBound ? this.upperEndpoint : "∞");
        sb.append(this.upperBoundType == BoundType.CLOSED ? ']' : ')');
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public T getLowerEndpoint() {
        return this.lowerEndpoint;
    }

    /* access modifiers changed from: package-private */
    public BoundType getLowerBoundType() {
        return this.lowerBoundType;
    }

    /* access modifiers changed from: package-private */
    public T getUpperEndpoint() {
        return this.upperEndpoint;
    }

    /* access modifiers changed from: package-private */
    public BoundType getUpperBoundType() {
        return this.upperBoundType;
    }
}
