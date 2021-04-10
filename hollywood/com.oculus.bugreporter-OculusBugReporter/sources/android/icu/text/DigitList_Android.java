package android.icu.text;

import android.icu.math.BigDecimal;
import java.math.BigInteger;

public final class DigitList_Android {
    public static final int DBL_DIG = 17;
    private static byte[] LONG_MIN_REP = new byte[19];
    public static final int MAX_LONG_DIGITS = 19;
    public int count = 0;
    public int decimalAt = 0;
    private boolean didRound = false;
    public byte[] digits = new byte[19];

    private final void ensureCapacity(int digitCapacity, int digitsToCopy) {
        byte[] bArr = this.digits;
        if (digitCapacity > bArr.length) {
            byte[] newDigits = new byte[(digitCapacity * 2)];
            System.arraycopy(bArr, 0, newDigits, 0, digitsToCopy);
            this.digits = newDigits;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isZero() {
        for (int i = 0; i < this.count; i++) {
            if (this.digits[i] != 48) {
                return false;
            }
        }
        return true;
    }

    public void append(int digit) {
        int i = this.count;
        ensureCapacity(i + 1, i);
        byte[] bArr = this.digits;
        int i2 = this.count;
        this.count = i2 + 1;
        bArr[i2] = (byte) digit;
    }

    public byte getDigitValue(int i) {
        return (byte) (this.digits[i] - 48);
    }

    public final double getDouble() {
        int i = this.count;
        if (i == 0) {
            return 0.0d;
        }
        StringBuilder temp = new StringBuilder(i);
        temp.append('.');
        for (int i2 = 0; i2 < this.count; i2++) {
            temp.append((char) this.digits[i2]);
        }
        temp.append('E');
        temp.append(Integer.toString(this.decimalAt));
        return Double.valueOf(temp.toString()).doubleValue();
    }

    public final long getLong() {
        if (this.count == 0) {
            return 0;
        }
        if (isLongMIN_VALUE()) {
            return Long.MIN_VALUE;
        }
        StringBuilder temp = new StringBuilder(this.count);
        int i = 0;
        while (i < this.decimalAt) {
            temp.append(i < this.count ? (char) this.digits[i] : '0');
            i++;
        }
        return Long.parseLong(temp.toString());
    }

    public BigInteger getBigInteger(boolean isPositive) {
        int n;
        int i;
        if (isZero()) {
            return BigInteger.valueOf(0);
        }
        int len = this.decimalAt;
        int i2 = this.count;
        if (len <= i2) {
            len = i2;
        }
        if (!isPositive) {
            len++;
        }
        char[] text = new char[len];
        if (!isPositive) {
            text[0] = '-';
            int i3 = 0;
            while (true) {
                i = this.count;
                if (i3 >= i) {
                    break;
                }
                text[i3 + 1] = (char) this.digits[i3];
                i3++;
            }
            n = i + 1;
        } else {
            for (int i4 = 0; i4 < this.count; i4++) {
                text[i4] = (char) this.digits[i4];
            }
            n = this.count;
        }
        for (int i5 = n; i5 < text.length; i5++) {
            text[i5] = '0';
        }
        return new BigInteger(new String(text));
    }

    private String getStringRep(boolean isPositive) {
        if (isZero()) {
            return AndroidHardcodedSystemProperties.JAVA_VERSION;
        }
        StringBuilder stringRep = new StringBuilder(this.count + 1);
        if (!isPositive) {
            stringRep.append('-');
        }
        int d = this.decimalAt;
        if (d < 0) {
            stringRep.append('.');
            while (d < 0) {
                stringRep.append('0');
                d++;
            }
            d = -1;
        }
        for (int i = 0; i < this.count; i++) {
            if (d == i) {
                stringRep.append('.');
            }
            stringRep.append((char) this.digits[i]);
        }
        while (true) {
            int d2 = d - 1;
            if (d <= this.count) {
                return stringRep.toString();
            }
            stringRep.append('0');
            d = d2;
        }
    }

    public BigDecimal getBigDecimalICU(boolean isPositive) {
        if (isZero()) {
            return BigDecimal.valueOf(0L);
        }
        int i = this.count;
        long scale = ((long) i) - ((long) this.decimalAt);
        if (scale <= 0) {
            return new BigDecimal(getStringRep(isPositive));
        }
        int numDigits = this.count;
        if (scale > 2147483647L) {
            long numShift = scale - 2147483647L;
            if (numShift >= ((long) i)) {
                return new BigDecimal(0);
            }
            numDigits = (int) (((long) numDigits) - numShift);
        }
        StringBuilder significantDigits = new StringBuilder(numDigits + 1);
        if (!isPositive) {
            significantDigits.append('-');
        }
        for (int i2 = 0; i2 < numDigits; i2++) {
            significantDigits.append((char) this.digits[i2]);
        }
        return new BigDecimal(new BigInteger(significantDigits.toString()), (int) scale);
    }

    /* access modifiers changed from: package-private */
    public boolean isIntegral() {
        while (true) {
            int i = this.count;
            if (i <= 0 || this.digits[i - 1] != 48) {
                int i2 = this.count;
            } else {
                this.count = i - 1;
            }
        }
        int i22 = this.count;
        return i22 == 0 || this.decimalAt >= i22;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void set(double r6, int r8, boolean r9) {
        /*
            r5 = this;
            r0 = 0
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0008
            r6 = 0
        L_0x0008:
            java.lang.String r0 = java.lang.Double.toString(r6)
            r1 = 0
            r5.didRound = r1
            r2 = 19
            r5.set(r0, r2)
            r2 = 1
            if (r9 == 0) goto L_0x0039
            int r3 = r5.decimalAt
            int r4 = -r3
            if (r4 <= r8) goto L_0x001f
            r5.count = r1
            return
        L_0x001f:
            int r3 = -r3
            if (r3 != r8) goto L_0x0039
            boolean r3 = r5.shouldRoundUp(r1)
            if (r3 == 0) goto L_0x0036
            r5.count = r2
            int r3 = r5.decimalAt
            int r3 = r3 + r2
            r5.decimalAt = r3
            byte[] r2 = r5.digits
            r3 = 49
            r2[r1] = r3
            goto L_0x0038
        L_0x0036:
            r5.count = r1
        L_0x0038:
            return
        L_0x0039:
            int r1 = r5.count
            if (r1 <= r2) goto L_0x004c
            byte[] r3 = r5.digits
            int r4 = r1 + -1
            byte r3 = r3[r4]
            r4 = 48
            if (r3 != r4) goto L_0x004c
            int r1 = r1 + -1
            r5.count = r1
            goto L_0x0039
        L_0x004c:
            if (r9 == 0) goto L_0x0052
            int r1 = r5.decimalAt
            int r1 = r1 + r8
            goto L_0x0057
        L_0x0052:
            if (r8 != 0) goto L_0x0056
            r1 = -1
            goto L_0x0057
        L_0x0056:
            r1 = r8
        L_0x0057:
            r5.round(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DigitList_Android.set(double, int, boolean):void");
    }

    private void set(String rep, int maxCount) {
        this.decimalAt = -1;
        this.count = 0;
        int exponent = 0;
        int leadingZerosAfterDecimal = 0;
        boolean nonZeroDigitSeen = false;
        int i = 0;
        if (rep.charAt(0) == '-') {
            i = 0 + 1;
        }
        while (true) {
            if (i >= rep.length()) {
                break;
            }
            char c = rep.charAt(i);
            if (c == '.') {
                this.decimalAt = this.count;
            } else if (c == 'e' || c == 'E') {
                int i2 = i + 1;
            } else if (this.count < maxCount) {
                if (!nonZeroDigitSeen) {
                    nonZeroDigitSeen = c != '0';
                    if (!nonZeroDigitSeen && this.decimalAt != -1) {
                        leadingZerosAfterDecimal++;
                    }
                }
                if (nonZeroDigitSeen) {
                    int i3 = this.count;
                    ensureCapacity(i3 + 1, i3);
                    byte[] bArr = this.digits;
                    int i4 = this.count;
                    this.count = i4 + 1;
                    bArr[i4] = (byte) c;
                }
            }
            i++;
        }
        int i22 = i + 1;
        if (rep.charAt(i22) == '+') {
            i22++;
        }
        exponent = Integer.valueOf(rep.substring(i22)).intValue();
        if (this.decimalAt == -1) {
            this.decimalAt = this.count;
        }
        this.decimalAt += exponent - leadingZerosAfterDecimal;
    }

    private boolean shouldRoundUp(int maximumDigits) {
        if (maximumDigits < this.count) {
            byte[] bArr = this.digits;
            if (bArr[maximumDigits] > 53) {
                return true;
            }
            if (bArr[maximumDigits] == 53) {
                for (int i = maximumDigits + 1; i < this.count; i++) {
                    if (this.digits[i] != 48) {
                        return true;
                    }
                }
                if (maximumDigits <= 0 || this.digits[maximumDigits - 1] % 2 == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final void round(int maximumDigits) {
        if (maximumDigits >= 0 && maximumDigits < this.count) {
            if (shouldRoundUp(maximumDigits)) {
                while (true) {
                    maximumDigits--;
                    if (maximumDigits >= 0) {
                        byte[] bArr = this.digits;
                        bArr[maximumDigits] = (byte) (bArr[maximumDigits] + 1);
                        this.didRound = true;
                        if (bArr[maximumDigits] <= 57) {
                            break;
                        }
                    } else {
                        this.digits[0] = 49;
                        this.decimalAt++;
                        maximumDigits = 0;
                        this.didRound = true;
                        break;
                    }
                }
                maximumDigits++;
            }
            this.count = maximumDigits;
        }
        while (true) {
            int i = this.count;
            if (i > 1 && this.digits[i - 1] == 48) {
                this.count = i - 1;
            } else {
                return;
            }
        }
    }

    public boolean wasRounded() {
        return this.didRound;
    }

    public final void set(long source) {
        set(source, 0);
    }

    public final void set(long source, int maximumDigits) {
        byte[] bArr;
        this.didRound = false;
        if (source > 0) {
            int left = 19;
            while (source > 0) {
                left--;
                this.digits[left] = (byte) ((int) ((source % 10) + 48));
                source /= 10;
            }
            this.decimalAt = 19 - left;
            int right = 18;
            while (true) {
                bArr = this.digits;
                if (bArr[right] != 48) {
                    break;
                }
                right--;
            }
            this.count = (right - left) + 1;
            System.arraycopy(bArr, left, bArr, 0, this.count);
        } else if (source == Long.MIN_VALUE) {
            this.count = 19;
            this.decimalAt = 19;
            System.arraycopy(LONG_MIN_REP, 0, this.digits, 0, this.count);
        } else {
            this.count = 0;
            this.decimalAt = 0;
        }
        if (maximumDigits > 0) {
            round(maximumDigits);
        }
    }

    public final void set(BigInteger source, int maximumDigits) {
        String stringDigits = source.toString();
        int length = stringDigits.length();
        this.decimalAt = length;
        this.count = length;
        this.didRound = false;
        while (true) {
            int i = this.count;
            if (i <= 1 || stringDigits.charAt(i - 1) != '0') {
                int offset = 0;
            } else {
                this.count--;
            }
        }
        int offset2 = 0;
        if (stringDigits.charAt(0) == '-') {
            offset2 = 0 + 1;
            this.count--;
            this.decimalAt--;
        }
        ensureCapacity(this.count, 0);
        for (int i2 = 0; i2 < this.count; i2++) {
            this.digits[i2] = (byte) stringDigits.charAt(i2 + offset2);
        }
        if (maximumDigits > 0) {
            round(maximumDigits);
        }
    }

    private void setBigDecimalDigits(String stringDigits, int maximumDigits, boolean fixedPoint) {
        this.didRound = false;
        set(stringDigits, stringDigits.length());
        round(fixedPoint ? this.decimalAt + maximumDigits : maximumDigits == 0 ? -1 : maximumDigits);
    }

    public final void set(java.math.BigDecimal source, int maximumDigits, boolean fixedPoint) {
        setBigDecimalDigits(source.toString(), maximumDigits, fixedPoint);
    }

    public final void set(BigDecimal source, int maximumDigits, boolean fixedPoint) {
        setBigDecimalDigits(source.toString(), maximumDigits, fixedPoint);
    }

    private boolean isLongMIN_VALUE() {
        int i = this.decimalAt;
        int i2 = this.count;
        if (!(i == i2 && i2 == 19)) {
            return false;
        }
        for (int i3 = 0; i3 < this.count; i3++) {
            if (this.digits[i3] != LONG_MIN_REP[i3]) {
                return false;
            }
        }
        return true;
    }

    static {
        String s = Long.toString(Long.MIN_VALUE);
        for (int i = 0; i < 19; i++) {
            LONG_MIN_REP[i] = (byte) s.charAt(i + 1);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DigitList_Android)) {
            return false;
        }
        DigitList_Android other = (DigitList_Android) obj;
        if (!(this.count == other.count && this.decimalAt == other.decimalAt)) {
            return false;
        }
        for (int i = 0; i < this.count; i++) {
            if (this.digits[i] != other.digits[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int hashcode = this.decimalAt;
        for (int i = 0; i < this.count; i++) {
            hashcode = (hashcode * 37) + this.digits[i];
        }
        return hashcode;
    }

    public String toString() {
        if (isZero()) {
            return AndroidHardcodedSystemProperties.JAVA_VERSION;
        }
        StringBuilder buf = new StringBuilder("0.");
        for (int i = 0; i < this.count; i++) {
            buf.append((char) this.digits[i]);
        }
        buf.append("x10^");
        buf.append(this.decimalAt);
        return buf.toString();
    }
}
