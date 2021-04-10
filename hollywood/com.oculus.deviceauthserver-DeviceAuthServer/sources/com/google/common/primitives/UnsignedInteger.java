package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class UnsignedInteger extends Number implements Comparable<UnsignedInteger> {
    public static final UnsignedInteger MAX_VALUE = fromIntBits(-1);
    public static final UnsignedInteger ONE = fromIntBits(1);
    public static final UnsignedInteger ZERO = fromIntBits(0);
    private final int value;

    private UnsignedInteger(int value2) {
        this.value = value2 & -1;
    }

    public static UnsignedInteger fromIntBits(int bits) {
        return new UnsignedInteger(bits);
    }

    public static UnsignedInteger valueOf(long value2) {
        Preconditions.checkArgument((4294967295L & value2) == value2, "value (%s) is outside the range for an unsigned integer value", Long.valueOf(value2));
        return fromIntBits((int) value2);
    }

    public static UnsignedInteger valueOf(BigInteger value2) {
        Preconditions.checkNotNull(value2);
        Preconditions.checkArgument(value2.signum() >= 0 && value2.bitLength() <= 32, "value (%s) is outside the range for an unsigned integer value", value2);
        return fromIntBits(value2.intValue());
    }

    public static UnsignedInteger valueOf(String string) {
        return valueOf(string, 10);
    }

    public static UnsignedInteger valueOf(String string, int radix) {
        return fromIntBits(UnsignedInts.parseUnsignedInt(string, radix));
    }

    @CheckReturnValue
    public UnsignedInteger plus(UnsignedInteger val) {
        return fromIntBits(this.value + ((UnsignedInteger) Preconditions.checkNotNull(val)).value);
    }

    @CheckReturnValue
    public UnsignedInteger minus(UnsignedInteger val) {
        return fromIntBits(this.value - ((UnsignedInteger) Preconditions.checkNotNull(val)).value);
    }

    @GwtIncompatible("Does not truncate correctly")
    @CheckReturnValue
    public UnsignedInteger times(UnsignedInteger val) {
        return fromIntBits(this.value * ((UnsignedInteger) Preconditions.checkNotNull(val)).value);
    }

    @CheckReturnValue
    public UnsignedInteger dividedBy(UnsignedInteger val) {
        return fromIntBits(UnsignedInts.divide(this.value, ((UnsignedInteger) Preconditions.checkNotNull(val)).value));
    }

    @CheckReturnValue
    public UnsignedInteger mod(UnsignedInteger val) {
        return fromIntBits(UnsignedInts.remainder(this.value, ((UnsignedInteger) Preconditions.checkNotNull(val)).value));
    }

    public int intValue() {
        return this.value;
    }

    public long longValue() {
        return UnsignedInts.toLong(this.value);
    }

    public float floatValue() {
        return (float) longValue();
    }

    public double doubleValue() {
        return (double) longValue();
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(longValue());
    }

    public int compareTo(UnsignedInteger other) {
        Preconditions.checkNotNull(other);
        return UnsignedInts.compare(this.value, other.value);
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof UnsignedInteger) || this.value != ((UnsignedInteger) obj).value) {
            return false;
        }
        return true;
    }

    public String toString() {
        return toString(10);
    }

    public String toString(int radix) {
        return UnsignedInts.toString(this.value, radix);
    }
}
