package com.google.common.primitives;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.math.BigInteger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = BuildConfig.IS_INTERNAL_BUILD)
public final class UnsignedLong extends Number implements Comparable<UnsignedLong>, Serializable {
    public static final UnsignedLong MAX_VALUE = new UnsignedLong(-1);
    public static final UnsignedLong ONE = new UnsignedLong(1);
    private static final long UNSIGNED_MASK = Long.MAX_VALUE;
    public static final UnsignedLong ZERO = new UnsignedLong(0);
    private final long value;

    private UnsignedLong(long value2) {
        this.value = value2;
    }

    public static UnsignedLong fromLongBits(long bits) {
        return new UnsignedLong(bits);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(long value2) {
        Preconditions.checkArgument(value2 >= 0, "value (%s) is outside the range for an unsigned long value", value2);
        return fromLongBits(value2);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(BigInteger value2) {
        Preconditions.checkNotNull(value2);
        Preconditions.checkArgument(value2.signum() >= 0 && value2.bitLength() <= 64, "value (%s) is outside the range for an unsigned long value", value2);
        return fromLongBits(value2.longValue());
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(String string) {
        return valueOf(string, 10);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(String string, int radix) {
        return fromLongBits(UnsignedLongs.parseUnsignedLong(string, radix));
    }

    public UnsignedLong plus(UnsignedLong val) {
        return fromLongBits(((UnsignedLong) Preconditions.checkNotNull(val)).value + this.value);
    }

    public UnsignedLong minus(UnsignedLong val) {
        return fromLongBits(this.value - ((UnsignedLong) Preconditions.checkNotNull(val)).value);
    }

    public UnsignedLong times(UnsignedLong val) {
        return fromLongBits(((UnsignedLong) Preconditions.checkNotNull(val)).value * this.value);
    }

    public UnsignedLong dividedBy(UnsignedLong val) {
        return fromLongBits(UnsignedLongs.divide(this.value, ((UnsignedLong) Preconditions.checkNotNull(val)).value));
    }

    public UnsignedLong mod(UnsignedLong val) {
        return fromLongBits(UnsignedLongs.remainder(this.value, ((UnsignedLong) Preconditions.checkNotNull(val)).value));
    }

    public int intValue() {
        return (int) this.value;
    }

    public long longValue() {
        return this.value;
    }

    public float floatValue() {
        float fValue = (float) (this.value & Long.MAX_VALUE);
        if (this.value < 0) {
            return fValue + 9.223372E18f;
        }
        return fValue;
    }

    public double doubleValue() {
        double dValue = (double) (this.value & Long.MAX_VALUE);
        if (this.value < 0) {
            return dValue + 9.223372036854776E18d;
        }
        return dValue;
    }

    public BigInteger bigIntegerValue() {
        BigInteger bigInt = BigInteger.valueOf(this.value & Long.MAX_VALUE);
        if (this.value < 0) {
            return bigInt.setBit(63);
        }
        return bigInt;
    }

    public int compareTo(UnsignedLong o) {
        Preconditions.checkNotNull(o);
        return UnsignedLongs.compare(this.value, o.value);
    }

    public int hashCode() {
        return Longs.hashCode(this.value);
    }

    public boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof UnsignedLong) || this.value != ((UnsignedLong) obj).value) {
            return false;
        }
        return true;
    }

    public String toString() {
        return UnsignedLongs.toString(this.value);
    }

    public String toString(int radix) {
        return UnsignedLongs.toString(this.value, radix);
    }
}
