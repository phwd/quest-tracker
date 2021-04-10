package android.icu.impl.number;

import android.icu.text.DateFormat;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class DecimalQuantity_DualStorageBCD extends DecimalQuantity_AbstractBCD {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private byte[] bcdBytes;
    private long bcdLong = 0;
    private boolean usingBytes = false;

    @Override // android.icu.impl.number.DecimalQuantity
    public int maxRepresentableDigits() {
        return Integer.MAX_VALUE;
    }

    public DecimalQuantity_DualStorageBCD() {
        setBcdToZero();
        this.flags = 0;
    }

    public DecimalQuantity_DualStorageBCD(long input) {
        setToLong(input);
    }

    public DecimalQuantity_DualStorageBCD(int input) {
        setToInt(input);
    }

    public DecimalQuantity_DualStorageBCD(double input) {
        setToDouble(input);
    }

    public DecimalQuantity_DualStorageBCD(BigInteger input) {
        setToBigInteger(input);
    }

    public DecimalQuantity_DualStorageBCD(BigDecimal input) {
        setToBigDecimal(input);
    }

    public DecimalQuantity_DualStorageBCD(DecimalQuantity_DualStorageBCD other) {
        copyFrom(other);
    }

    public DecimalQuantity_DualStorageBCD(Number number) {
        if (number instanceof Long) {
            setToLong(number.longValue());
        } else if (number instanceof Integer) {
            setToInt(number.intValue());
        } else if (number instanceof Float) {
            setToDouble(number.doubleValue());
        } else if (number instanceof Double) {
            setToDouble(number.doubleValue());
        } else if (number instanceof BigInteger) {
            setToBigInteger((BigInteger) number);
        } else if (number instanceof BigDecimal) {
            setToBigDecimal((BigDecimal) number);
        } else if (number instanceof android.icu.math.BigDecimal) {
            setToBigDecimal(((android.icu.math.BigDecimal) number).toBigDecimal());
        } else {
            throw new IllegalArgumentException("Number is of an unsupported type: " + number.getClass().getName());
        }
    }

    @Override // android.icu.impl.number.DecimalQuantity
    public DecimalQuantity createCopy() {
        return new DecimalQuantity_DualStorageBCD(this);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public byte getDigitPos(int position) {
        if (this.usingBytes) {
            if (position < 0 || position >= this.precision) {
                return 0;
            }
            return this.bcdBytes[position];
        } else if (position < 0 || position >= 16) {
            return 0;
        } else {
            return (byte) ((int) ((this.bcdLong >>> (position * 4)) & 15));
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void setDigitPos(int position, byte value) {
        if (this.usingBytes) {
            ensureCapacity(position + 1);
            this.bcdBytes[position] = value;
        } else if (position >= 16) {
            switchStorage();
            ensureCapacity(position + 1);
            this.bcdBytes[position] = value;
        } else {
            int shift = position * 4;
            this.bcdLong = (this.bcdLong & (~(15 << shift))) | (((long) value) << shift);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void shiftLeft(int numDigits) {
        if (!this.usingBytes && this.precision + numDigits > 16) {
            switchStorage();
        }
        if (this.usingBytes) {
            ensureCapacity(this.precision + numDigits);
            int i = (this.precision + numDigits) - 1;
            while (i >= numDigits) {
                byte[] bArr = this.bcdBytes;
                bArr[i] = bArr[i - numDigits];
                i--;
            }
            while (i >= 0) {
                this.bcdBytes[i] = 0;
                i--;
            }
        } else {
            this.bcdLong <<= numDigits * 4;
        }
        this.scale -= numDigits;
        this.precision += numDigits;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void shiftRight(int numDigits) {
        if (this.usingBytes) {
            int i = 0;
            while (i < this.precision - numDigits) {
                byte[] bArr = this.bcdBytes;
                bArr[i] = bArr[i + numDigits];
                i++;
            }
            while (i < this.precision) {
                this.bcdBytes[i] = 0;
                i++;
            }
        } else {
            this.bcdLong >>>= numDigits * 4;
        }
        this.scale += numDigits;
        this.precision -= numDigits;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void setBcdToZero() {
        if (this.usingBytes) {
            this.bcdBytes = null;
            this.usingBytes = false;
        }
        this.bcdLong = 0;
        this.scale = 0;
        this.precision = 0;
        this.isApproximate = false;
        this.origDouble = 0.0d;
        this.origDelta = 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void readIntToBcd(int n) {
        long result = 0;
        int i = 16;
        while (n != 0) {
            result = (result >>> 4) + ((((long) n) % 10) << 60);
            n /= 10;
            i--;
        }
        this.bcdLong = result >>> (i * 4);
        this.scale = 0;
        this.precision = 16 - i;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void readLongToBcd(long n) {
        if (n >= 10000000000000000L) {
            ensureCapacity();
            int i = 0;
            while (n != 0) {
                this.bcdBytes[i] = (byte) ((int) (n % 10));
                n /= 10;
                i++;
            }
            this.scale = 0;
            this.precision = i;
            return;
        }
        long result = 0;
        int i2 = 16;
        while (n != 0) {
            result = (result >>> 4) + ((n % 10) << 60);
            n /= 10;
            i2--;
        }
        this.bcdLong = result >>> (i2 * 4);
        this.scale = 0;
        this.precision = 16 - i2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void readBigIntegerToBcd(BigInteger n) {
        ensureCapacity();
        int i = 0;
        while (n.signum() != 0) {
            BigInteger[] temp = n.divideAndRemainder(BigInteger.TEN);
            ensureCapacity(i + 1);
            this.bcdBytes[i] = temp[1].byteValue();
            n = temp[0];
            i++;
        }
        this.scale = 0;
        this.precision = i;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public BigDecimal bcdToBigDecimal() {
        BigDecimal result;
        if (this.usingBytes) {
            BigDecimal result2 = new BigDecimal(toNumberString());
            if (isNegative()) {
                return result2.negate();
            }
            return result2;
        }
        long tempLong = 0;
        for (int shift = this.precision - 1; shift >= 0; shift--) {
            tempLong = (10 * tempLong) + ((long) getDigitPos(shift));
        }
        BigDecimal result3 = BigDecimal.valueOf(tempLong);
        if (((long) (result3.scale() + this.scale)) <= -2147483648L) {
            result = BigDecimal.ZERO;
        } else {
            result = result3.scaleByPowerOfTen(this.scale);
        }
        if (isNegative()) {
            return result.negate();
        }
        return result;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void compact() {
        if (this.usingBytes) {
            int delta = 0;
            while (delta < this.precision && this.bcdBytes[delta] == 0) {
                delta++;
            }
            if (delta == this.precision) {
                setBcdToZero();
                return;
            }
            shiftRight(delta);
            int leading = this.precision - 1;
            while (leading >= 0 && this.bcdBytes[leading] == 0) {
                leading--;
            }
            this.precision = leading + 1;
            if (this.precision <= 16) {
                switchStorage();
                return;
            }
            return;
        }
        long j = this.bcdLong;
        if (j == 0) {
            setBcdToZero();
            return;
        }
        int delta2 = Long.numberOfTrailingZeros(j) / 4;
        this.bcdLong >>>= delta2 * 4;
        this.scale += delta2;
        this.precision = 16 - (Long.numberOfLeadingZeros(this.bcdLong) / 4);
    }

    private void ensureCapacity() {
        ensureCapacity(40);
    }

    private void ensureCapacity(int capacity) {
        if (capacity != 0) {
            int oldCapacity = this.usingBytes ? this.bcdBytes.length : 0;
            if (!this.usingBytes) {
                this.bcdBytes = new byte[capacity];
            } else if (oldCapacity < capacity) {
                byte[] bcd1 = new byte[(capacity * 2)];
                System.arraycopy(this.bcdBytes, 0, bcd1, 0, oldCapacity);
                this.bcdBytes = bcd1;
            }
            this.usingBytes = true;
        }
    }

    private void switchStorage() {
        if (this.usingBytes) {
            this.bcdLong = 0;
            for (int i = this.precision - 1; i >= 0; i--) {
                this.bcdLong <<= 4;
                this.bcdLong |= (long) this.bcdBytes[i];
            }
            this.bcdBytes = null;
            this.usingBytes = false;
            return;
        }
        ensureCapacity();
        for (int i2 = 0; i2 < this.precision; i2++) {
            byte[] bArr = this.bcdBytes;
            long j = this.bcdLong;
            bArr[i2] = (byte) ((int) (15 & j));
            this.bcdLong = j >>> 4;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void copyBcdFrom(DecimalQuantity _other) {
        DecimalQuantity_DualStorageBCD other = (DecimalQuantity_DualStorageBCD) _other;
        setBcdToZero();
        if (other.usingBytes) {
            ensureCapacity(other.precision);
            System.arraycopy(other.bcdBytes, 0, this.bcdBytes, 0, other.precision);
            return;
        }
        this.bcdLong = other.bcdLong;
    }

    @Deprecated
    public String checkHealth() {
        if (!this.usingBytes) {
            if (this.bcdBytes != null) {
                int i = 0;
                while (true) {
                    byte[] bArr = this.bcdBytes;
                    if (i >= bArr.length) {
                        break;
                    } else if (bArr[i] != 0) {
                        return "Nonzero digits in byte array but we are in long mode";
                    } else {
                        i++;
                    }
                }
            }
            if (this.precision == 0 && this.bcdLong != 0) {
                return "Value in bcdLong even though precision is zero";
            }
            if (this.precision > 16) {
                return "Precision exceeds length of long";
            }
            if (this.precision != 0 && getDigitPos(this.precision - 1) == 0) {
                return "Most significant digit is zero in long mode";
            }
            if (this.precision != 0 && getDigitPos(0) == 0) {
                return "Least significant digit is zero in long mode";
            }
            for (int i2 = 0; i2 < this.precision; i2++) {
                if (getDigitPos(i2) >= 10) {
                    return "Digit exceeding 10 in long";
                }
                if (getDigitPos(i2) < 0) {
                    return "Digit below 0 in long (?!)";
                }
            }
            for (int i3 = this.precision; i3 < 16; i3++) {
                if (getDigitPos(i3) != 0) {
                    return "Nonzero digits outside of range in long";
                }
            }
            return null;
        } else if (this.bcdLong != 0) {
            return "Value in bcdLong but we are in byte mode";
        } else {
            if (this.precision == 0) {
                return "Zero precision but we are in byte mode";
            }
            if (this.precision > this.bcdBytes.length) {
                return "Precision exceeds length of byte array";
            }
            if (getDigitPos(this.precision - 1) == 0) {
                return "Most significant digit is zero in byte mode";
            }
            if (getDigitPos(0) == 0) {
                return "Least significant digit is zero in long mode";
            }
            for (int i4 = 0; i4 < this.precision; i4++) {
                if (getDigitPos(i4) >= 10) {
                    return "Digit exceeding 10 in byte array";
                }
                if (getDigitPos(i4) < 0) {
                    return "Digit below 0 in byte array";
                }
            }
            for (int i5 = this.precision; i5 < this.bcdBytes.length; i5++) {
                if (getDigitPos(i5) != 0) {
                    return "Nonzero digits outside of range in byte array";
                }
            }
            return null;
        }
    }

    @Deprecated
    public boolean isUsingBytes() {
        return this.usingBytes;
    }

    public String toString() {
        Object[] objArr = new Object[7];
        objArr[0] = this.lOptPos > 1000 ? "999" : String.valueOf(this.lOptPos);
        objArr[1] = Integer.valueOf(this.lReqPos);
        objArr[2] = Integer.valueOf(this.rReqPos);
        objArr[3] = this.rOptPos < -1000 ? "-999" : String.valueOf(this.rOptPos);
        objArr[4] = this.usingBytes ? "bytes" : "long";
        objArr[5] = isNegative() ? "-" : "";
        objArr[6] = toNumberString();
        return String.format("<DecimalQuantity %s:%d:%d:%s %s %s%s>", objArr);
    }

    private String toNumberString() {
        StringBuilder sb = new StringBuilder();
        if (this.usingBytes) {
            if (this.precision == 0) {
                sb.append('0');
            }
            for (int i = this.precision - 1; i >= 0; i--) {
                sb.append((int) this.bcdBytes[i]);
            }
        } else {
            sb.append(Long.toHexString(this.bcdLong));
        }
        sb.append(DateFormat.ABBR_WEEKDAY);
        sb.append(this.scale);
        return sb.toString();
    }
}
