package android.icu.impl.number;

import java.math.BigDecimal;
import java.math.BigInteger;

public final class DecimalQuantity_DualStorageBCD extends DecimalQuantity_AbstractBCD {
    private byte[] bcdBytes;
    private long bcdLong = 0;
    private boolean usingBytes = false;

    public DecimalQuantity_DualStorageBCD() {
        setBcdToZero();
        this.flags = 0;
    }

    public DecimalQuantity_DualStorageBCD(long j) {
        setToLong(j);
    }

    public DecimalQuantity_DualStorageBCD(double d) {
        setToDouble(d);
    }

    public DecimalQuantity_DualStorageBCD(DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD) {
        copyFrom(decimalQuantity_DualStorageBCD);
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
    public byte getDigitPos(int i) {
        if (this.usingBytes) {
            if (i < 0 || i >= this.precision) {
                return 0;
            }
            return this.bcdBytes[i];
        } else if (i < 0 || i >= 16) {
            return 0;
        } else {
            return (byte) ((int) ((this.bcdLong >>> (i * 4)) & 15));
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void setDigitPos(int i, byte b) {
        if (this.usingBytes) {
            ensureCapacity(i + 1);
            this.bcdBytes[i] = b;
        } else if (i >= 16) {
            switchStorage();
            ensureCapacity(i + 1);
            this.bcdBytes[i] = b;
        } else {
            int i2 = i * 4;
            this.bcdLong = (((long) b) << i2) | (this.bcdLong & (~(15 << i2)));
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void shiftLeft(int i) {
        if (!this.usingBytes && this.precision + i > 16) {
            switchStorage();
        }
        if (this.usingBytes) {
            ensureCapacity(this.precision + i);
            int i2 = (this.precision + i) - 1;
            while (i2 >= i) {
                byte[] bArr = this.bcdBytes;
                bArr[i2] = bArr[i2 - i];
                i2--;
            }
            while (i2 >= 0) {
                this.bcdBytes[i2] = 0;
                i2--;
            }
        } else {
            this.bcdLong <<= i * 4;
        }
        this.scale -= i;
        this.precision += i;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void shiftRight(int i) {
        if (this.usingBytes) {
            int i2 = 0;
            while (i2 < this.precision - i) {
                byte[] bArr = this.bcdBytes;
                bArr[i2] = bArr[i2 + i];
                i2++;
            }
            while (i2 < this.precision) {
                this.bcdBytes[i2] = 0;
                i2++;
            }
        } else {
            this.bcdLong >>>= i * 4;
        }
        this.scale += i;
        this.precision -= i;
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
    public void readIntToBcd(int i) {
        long j = 0;
        int i2 = 16;
        while (i != 0) {
            j = (j >>> 4) + ((((long) i) % 10) << 60);
            i /= 10;
            i2--;
        }
        this.bcdLong = j >>> (i2 * 4);
        this.scale = 0;
        this.precision = 16 - i2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void readLongToBcd(long j) {
        if (j >= 10000000000000000L) {
            ensureCapacity();
            int i = 0;
            while (j != 0) {
                this.bcdBytes[i] = (byte) ((int) (j % 10));
                j /= 10;
                i++;
            }
            this.scale = 0;
            this.precision = i;
            return;
        }
        int i2 = 16;
        long j2 = 0;
        while (j != 0) {
            j2 = (j2 >>> 4) + ((j % 10) << 60);
            j /= 10;
            i2--;
        }
        this.bcdLong = j2 >>> (i2 * 4);
        this.scale = 0;
        this.precision = 16 - i2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void readBigIntegerToBcd(BigInteger bigInteger) {
        ensureCapacity();
        int i = 0;
        while (bigInteger.signum() != 0) {
            BigInteger[] divideAndRemainder = bigInteger.divideAndRemainder(BigInteger.TEN);
            int i2 = i + 1;
            ensureCapacity(i2);
            this.bcdBytes[i] = divideAndRemainder[1].byteValue();
            bigInteger = divideAndRemainder[0];
            i = i2;
        }
        this.scale = 0;
        this.precision = i;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public BigDecimal bcdToBigDecimal() {
        BigDecimal bigDecimal;
        if (this.usingBytes) {
            BigDecimal bigDecimal2 = new BigDecimal(toNumberString());
            return isNegative() ? bigDecimal2.negate() : bigDecimal2;
        }
        long j = 0;
        for (int i = this.precision - 1; i >= 0; i--) {
            j = (j * 10) + ((long) getDigitPos(i));
        }
        BigDecimal valueOf = BigDecimal.valueOf(j);
        int scale = valueOf.scale();
        int i2 = this.scale;
        if (((long) (scale + i2)) <= -2147483648L) {
            bigDecimal = BigDecimal.ZERO;
        } else {
            bigDecimal = valueOf.scaleByPowerOfTen(i2);
        }
        return isNegative() ? bigDecimal.negate() : bigDecimal;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.number.DecimalQuantity_AbstractBCD
    public void compact() {
        if (this.usingBytes) {
            int i = 0;
            while (i < this.precision && this.bcdBytes[i] == 0) {
                i++;
            }
            if (i == this.precision) {
                setBcdToZero();
                return;
            }
            shiftRight(i);
            int i2 = this.precision - 1;
            while (i2 >= 0 && this.bcdBytes[i2] == 0) {
                i2--;
            }
            this.precision = i2 + 1;
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
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j) / 4;
        this.bcdLong >>>= numberOfTrailingZeros * 4;
        this.scale += numberOfTrailingZeros;
        this.precision = 16 - (Long.numberOfLeadingZeros(this.bcdLong) / 4);
    }

    private void ensureCapacity() {
        ensureCapacity(40);
    }

    private void ensureCapacity(int i) {
        if (i != 0) {
            int length = this.usingBytes ? this.bcdBytes.length : 0;
            if (!this.usingBytes) {
                this.bcdBytes = new byte[i];
            } else if (length < i) {
                byte[] bArr = new byte[(i * 2)];
                System.arraycopy(this.bcdBytes, 0, bArr, 0, length);
                this.bcdBytes = bArr;
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
    public void copyBcdFrom(DecimalQuantity decimalQuantity) {
        DecimalQuantity_DualStorageBCD decimalQuantity_DualStorageBCD = (DecimalQuantity_DualStorageBCD) decimalQuantity;
        setBcdToZero();
        if (decimalQuantity_DualStorageBCD.usingBytes) {
            ensureCapacity(decimalQuantity_DualStorageBCD.precision);
            System.arraycopy(decimalQuantity_DualStorageBCD.bcdBytes, 0, this.bcdBytes, 0, decimalQuantity_DualStorageBCD.precision);
            return;
        }
        this.bcdLong = decimalQuantity_DualStorageBCD.bcdLong;
    }

    public String toString() {
        Object[] objArr = new Object[7];
        int i = this.lOptPos;
        objArr[0] = i > 1000 ? "999" : String.valueOf(i);
        objArr[1] = Integer.valueOf(this.lReqPos);
        objArr[2] = Integer.valueOf(this.rReqPos);
        int i2 = this.rOptPos;
        objArr[3] = i2 < -1000 ? "-999" : String.valueOf(i2);
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
            sb.append("E");
            sb.append(this.scale);
            return sb.toString();
        }
        Long.toHexString(this.bcdLong);
        throw null;
    }
}
