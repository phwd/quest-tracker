package com.google.common.collect;

import com.facebook.common.time.Clock;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.Comparable;
import java.math.BigInteger;
import java.util.NoSuchElementException;

@GwtCompatible
public abstract class DiscreteDomain<C extends Comparable> {
    final boolean supportsFastOffset;

    public abstract long distance(C c, C c2);

    public abstract C next(C c);

    public abstract C previous(C c);

    public static DiscreteDomain<Integer> integers() {
        return IntegerDomain.INSTANCE;
    }

    private static final class IntegerDomain extends DiscreteDomain<Integer> implements Serializable {
        private static final IntegerDomain INSTANCE = new IntegerDomain();
        private static final long serialVersionUID = 0;

        IntegerDomain() {
            super(true);
        }

        public Integer next(Integer value) {
            int i = value.intValue();
            if (i == Integer.MAX_VALUE) {
                return null;
            }
            return Integer.valueOf(i + 1);
        }

        public Integer previous(Integer value) {
            int i = value.intValue();
            if (i == Integer.MIN_VALUE) {
                return null;
            }
            return Integer.valueOf(i - 1);
        }

        /* access modifiers changed from: package-private */
        public Integer offset(Integer origin, long distance) {
            CollectPreconditions.checkNonnegative(distance, "distance");
            return Integer.valueOf(Ints.checkedCast(origin.longValue() + distance));
        }

        public long distance(Integer start, Integer end) {
            return ((long) end.intValue()) - ((long) start.intValue());
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Integer minValue() {
            return Integer.MIN_VALUE;
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Integer maxValue() {
            return Integer.MAX_VALUE;
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.integers()";
        }
    }

    public static DiscreteDomain<Long> longs() {
        return LongDomain.INSTANCE;
    }

    private static final class LongDomain extends DiscreteDomain<Long> implements Serializable {
        private static final LongDomain INSTANCE = new LongDomain();
        private static final long serialVersionUID = 0;

        LongDomain() {
            super(true);
        }

        public Long next(Long value) {
            long l = value.longValue();
            if (l == Clock.MAX_TIME) {
                return null;
            }
            return Long.valueOf(1 + l);
        }

        public Long previous(Long value) {
            long l = value.longValue();
            if (l == Long.MIN_VALUE) {
                return null;
            }
            return Long.valueOf(l - 1);
        }

        /* access modifiers changed from: package-private */
        public Long offset(Long origin, long distance) {
            CollectPreconditions.checkNonnegative(distance, "distance");
            long result = origin.longValue() + distance;
            if (result < 0) {
                Preconditions.checkArgument(origin.longValue() < 0, "overflow");
            }
            return Long.valueOf(result);
        }

        public long distance(Long start, Long end) {
            long result = end.longValue() - start.longValue();
            if (end.longValue() > start.longValue() && result < 0) {
                return Clock.MAX_TIME;
            }
            if (end.longValue() >= start.longValue() || result <= 0) {
                return result;
            }
            return Long.MIN_VALUE;
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Long minValue() {
            return Long.MIN_VALUE;
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Long maxValue() {
            return Long.valueOf((long) Clock.MAX_TIME);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.longs()";
        }
    }

    public static DiscreteDomain<BigInteger> bigIntegers() {
        return BigIntegerDomain.INSTANCE;
    }

    private static final class BigIntegerDomain extends DiscreteDomain<BigInteger> implements Serializable {
        private static final BigIntegerDomain INSTANCE = new BigIntegerDomain();
        private static final BigInteger MAX_LONG = BigInteger.valueOf(Clock.MAX_TIME);
        private static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        private static final long serialVersionUID = 0;

        BigIntegerDomain() {
            super(true);
        }

        public BigInteger next(BigInteger value) {
            return value.add(BigInteger.ONE);
        }

        public BigInteger previous(BigInteger value) {
            return value.subtract(BigInteger.ONE);
        }

        /* access modifiers changed from: package-private */
        public BigInteger offset(BigInteger origin, long distance) {
            CollectPreconditions.checkNonnegative(distance, "distance");
            return origin.add(BigInteger.valueOf(distance));
        }

        public long distance(BigInteger start, BigInteger end) {
            return end.subtract(start).max(MIN_LONG).min(MAX_LONG).longValue();
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.bigIntegers()";
        }
    }

    protected DiscreteDomain() {
        this(false);
    }

    private DiscreteDomain(boolean supportsFastOffset2) {
        this.supportsFastOffset = supportsFastOffset2;
    }

    /* access modifiers changed from: package-private */
    public C offset(C origin, long distance) {
        CollectPreconditions.checkNonnegative(distance, "distance");
        for (long i = 0; i < distance; i++) {
            origin = next(origin);
        }
        return origin;
    }

    @CanIgnoreReturnValue
    public C minValue() {
        throw new NoSuchElementException();
    }

    @CanIgnoreReturnValue
    public C maxValue() {
        throw new NoSuchElementException();
    }
}
