package android.icu.impl.duration;

public final class Period {
    final int[] counts;
    final boolean inFuture;
    final byte timeLimit;

    public static Period at(float count, TimeUnit unit) {
        checkCount(count);
        return new Period(0, false, count, unit);
    }

    public static Period moreThan(float count, TimeUnit unit) {
        checkCount(count);
        return new Period(2, false, count, unit);
    }

    public static Period lessThan(float count, TimeUnit unit) {
        checkCount(count);
        return new Period(1, false, count, unit);
    }

    public Period and(float count, TimeUnit unit) {
        checkCount(count);
        return setTimeUnitValue(unit, count);
    }

    public Period omit(TimeUnit unit) {
        return setTimeUnitInternalValue(unit, 0);
    }

    public Period at() {
        return setTimeLimit((byte) 0);
    }

    public Period moreThan() {
        return setTimeLimit((byte) 2);
    }

    public Period lessThan() {
        return setTimeLimit((byte) 1);
    }

    public Period inFuture() {
        return setFuture(true);
    }

    public Period inPast() {
        return setFuture(false);
    }

    public Period inFuture(boolean future) {
        return setFuture(future);
    }

    public Period inPast(boolean past) {
        return setFuture(!past);
    }

    public boolean isSet() {
        int i = 0;
        while (true) {
            int[] iArr = this.counts;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i] != 0) {
                return true;
            }
            i++;
        }
    }

    public boolean isSet(TimeUnit unit) {
        return this.counts[unit.ordinal] > 0;
    }

    public float getCount(TimeUnit unit) {
        int ord = unit.ordinal;
        int[] iArr = this.counts;
        if (iArr[ord] == 0) {
            return 0.0f;
        }
        return ((float) (iArr[ord] - 1)) / 1000.0f;
    }

    public boolean isInFuture() {
        return this.inFuture;
    }

    public boolean isInPast() {
        return !this.inFuture;
    }

    public boolean isMoreThan() {
        return this.timeLimit == 2;
    }

    public boolean isLessThan() {
        return this.timeLimit == 1;
    }

    public boolean equals(Object rhs) {
        try {
            return equals((Period) rhs);
        } catch (ClassCastException e) {
            return false;
        }
    }

    public boolean equals(Period rhs) {
        if (rhs == null || this.timeLimit != rhs.timeLimit || this.inFuture != rhs.inFuture) {
            return false;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.counts;
            if (i >= iArr.length) {
                return true;
            }
            if (iArr[i] != rhs.counts[i]) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int hc = (this.timeLimit << 1) | (this.inFuture ? 1 : 0);
        int i = 0;
        while (true) {
            int[] iArr = this.counts;
            if (i >= iArr.length) {
                return hc;
            }
            hc = (hc << 2) ^ iArr[i];
            i++;
        }
    }

    private Period(int limit, boolean future, float count, TimeUnit unit) {
        this.timeLimit = (byte) limit;
        this.inFuture = future;
        this.counts = new int[TimeUnit.units.length];
        this.counts[unit.ordinal] = ((int) (1000.0f * count)) + 1;
    }

    Period(int timeLimit2, boolean inFuture2, int[] counts2) {
        this.timeLimit = (byte) timeLimit2;
        this.inFuture = inFuture2;
        this.counts = counts2;
    }

    private Period setTimeUnitValue(TimeUnit unit, float value) {
        if (value >= 0.0f) {
            return setTimeUnitInternalValue(unit, ((int) (1000.0f * value)) + 1);
        }
        throw new IllegalArgumentException("value: " + value);
    }

    private Period setTimeUnitInternalValue(TimeUnit unit, int value) {
        int ord = unit.ordinal;
        int[] iArr = this.counts;
        if (iArr[ord] == value) {
            return this;
        }
        int[] newCounts = new int[iArr.length];
        int i = 0;
        while (true) {
            int[] iArr2 = this.counts;
            if (i < iArr2.length) {
                newCounts[i] = iArr2[i];
                i++;
            } else {
                newCounts[ord] = value;
                return new Period(this.timeLimit, this.inFuture, newCounts);
            }
        }
    }

    private Period setFuture(boolean future) {
        if (this.inFuture != future) {
            return new Period(this.timeLimit, future, this.counts);
        }
        return this;
    }

    private Period setTimeLimit(byte limit) {
        if (this.timeLimit != limit) {
            return new Period(limit, this.inFuture, this.counts);
        }
        return this;
    }

    private static void checkCount(float count) {
        if (count < 0.0f) {
            throw new IllegalArgumentException("count (" + count + ") cannot be negative");
        }
    }
}
