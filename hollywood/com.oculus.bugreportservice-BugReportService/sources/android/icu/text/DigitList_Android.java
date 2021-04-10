package android.icu.text;

import android.icu.math.BigDecimal;
import java.math.BigInteger;

public final class DigitList_Android {
    private static byte[] LONG_MIN_REP = new byte[19];
    public int count = 0;
    public int decimalAt = 0;
    private boolean didRound = false;
    public byte[] digits = new byte[19];

    private final void ensureCapacity(int i, int i2) {
        byte[] bArr = this.digits;
        if (i > bArr.length) {
            byte[] bArr2 = new byte[(i * 2)];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.digits = bArr2;
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

    public void append(int i) {
        int i2 = this.count;
        ensureCapacity(i2 + 1, i2);
        byte[] bArr = this.digits;
        int i3 = this.count;
        this.count = i3 + 1;
        bArr[i3] = (byte) i;
    }

    public byte getDigitValue(int i) {
        return (byte) (this.digits[i] - 48);
    }

    public final long getLong() {
        if (this.count == 0) {
            return 0;
        }
        if (isLongMIN_VALUE()) {
            return Long.MIN_VALUE;
        }
        StringBuilder sb = new StringBuilder(this.count);
        int i = 0;
        while (i < this.decimalAt) {
            sb.append(i < this.count ? (char) this.digits[i] : '0');
            i++;
        }
        return Long.parseLong(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.math.BigInteger getBigInteger(boolean r4) {
        /*
            r3 = this;
            boolean r0 = r3.isZero()
            if (r0 == 0) goto L_0x000d
            r3 = 0
            java.math.BigInteger r3 = java.math.BigInteger.valueOf(r3)
            return r3
        L_0x000d:
            int r0 = r3.decimalAt
            int r1 = r3.count
            if (r0 <= r1) goto L_0x0014
            goto L_0x0015
        L_0x0014:
            r0 = r1
        L_0x0015:
            if (r4 != 0) goto L_0x0019
            int r0 = r0 + 1
        L_0x0019:
            char[] r0 = new char[r0]
            r1 = 0
            if (r4 != 0) goto L_0x0031
            r4 = 45
            r0[r1] = r4
        L_0x0022:
            int r4 = r3.count
            if (r1 >= r4) goto L_0x0046
            int r4 = r1 + 1
            byte[] r2 = r3.digits
            byte r1 = r2[r1]
            char r1 = (char) r1
            r0[r4] = r1
            r1 = r4
            goto L_0x0022
        L_0x0031:
            int r4 = r3.count
            if (r1 >= r4) goto L_0x003f
            byte[] r4 = r3.digits
            byte r4 = r4[r1]
            char r4 = (char) r4
            r0[r1] = r4
            int r1 = r1 + 1
            goto L_0x0031
        L_0x003f:
            int r3 = r0.length
            if (r4 >= r3) goto L_0x0049
            r3 = 48
            r0[r4] = r3
        L_0x0046:
            int r4 = r4 + 1
            goto L_0x003f
        L_0x0049:
            java.math.BigInteger r3 = new java.math.BigInteger
            java.lang.String r3 = new java.lang.String
            r3.<init>(r0)
            r3 = 0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DigitList_Android.getBigInteger(boolean):java.math.BigInteger");
    }

    private String getStringRep(boolean z) {
        if (isZero()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder(this.count + 1);
        if (!z) {
            sb.append('-');
        }
        int i = this.decimalAt;
        if (i < 0) {
            sb.append('.');
            while (i < 0) {
                sb.append('0');
                i++;
            }
            i = -1;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            if (i == i2) {
                sb.append('.');
            }
            sb.append((char) this.digits[i2]);
        }
        while (true) {
            int i3 = i - 1;
            if (i <= this.count) {
                return sb.toString();
            }
            sb.append('0');
            i = i3;
        }
    }

    public BigDecimal getBigDecimalICU(boolean z) {
        if (isZero()) {
            return BigDecimal.valueOf(0L);
        }
        int i = this.count;
        long j = ((long) i) - ((long) this.decimalAt);
        if (j <= 0) {
            return new BigDecimal(getStringRep(z));
        }
        if (j > 2147483647L) {
            long j2 = j - 2147483647L;
            if (j2 >= ((long) i)) {
                return new BigDecimal(0);
            }
            i = (int) (((long) i) - j2);
        }
        StringBuilder sb = new StringBuilder(i + 1);
        if (!z) {
            sb.append('-');
        }
        for (int i2 = 0; i2 < i; i2++) {
            sb.append((char) this.digits[i2]);
        }
        return new BigDecimal(new BigInteger(sb.toString()), (int) j);
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
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void set(double r4, int r6, boolean r7) {
        /*
            r3 = this;
            r0 = 0
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x0007
            r4 = r0
        L_0x0007:
            java.lang.String r4 = java.lang.Double.toString(r4)
            r5 = 0
            r3.didRound = r5
            r0 = 19
            r3.set(r4, r0)
            r4 = 1
            if (r7 == 0) goto L_0x0038
            int r0 = r3.decimalAt
            int r1 = -r0
            if (r1 <= r6) goto L_0x001e
            r3.count = r5
            return
        L_0x001e:
            int r0 = -r0
            if (r0 != r6) goto L_0x0038
            boolean r6 = r3.shouldRoundUp(r5)
            if (r6 == 0) goto L_0x0035
            r3.count = r4
            int r6 = r3.decimalAt
            int r6 = r6 + r4
            r3.decimalAt = r6
            byte[] r3 = r3.digits
            r4 = 49
            r3[r5] = r4
            goto L_0x0037
        L_0x0035:
            r3.count = r5
        L_0x0037:
            return
        L_0x0038:
            int r5 = r3.count
            if (r5 <= r4) goto L_0x004b
            byte[] r0 = r3.digits
            int r1 = r5 + -1
            byte r0 = r0[r1]
            r1 = 48
            if (r0 != r1) goto L_0x004b
            int r5 = r5 + -1
            r3.count = r5
            goto L_0x0038
        L_0x004b:
            if (r7 == 0) goto L_0x0051
            int r4 = r3.decimalAt
            int r6 = r6 + r4
            goto L_0x0054
        L_0x0051:
            if (r6 != 0) goto L_0x0054
            r6 = -1
        L_0x0054:
            r3.round(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.DigitList_Android.set(double, int, boolean):void");
    }

    private void set(String str, int i) {
        boolean z;
        int i2;
        int i3;
        this.decimalAt = -1;
        int i4 = 0;
        this.count = 0;
        if (str.charAt(0) == '-') {
            i2 = 0;
            z = false;
            i3 = 1;
        } else {
            i3 = 0;
            i2 = 0;
            z = false;
        }
        while (true) {
            if (i3 >= str.length()) {
                break;
            }
            char charAt = str.charAt(i3);
            if (charAt == '.') {
                this.decimalAt = this.count;
            } else if (charAt == 'e' || charAt == 'E') {
                int i5 = i3 + 1;
            } else if (this.count < i) {
                if (!z) {
                    z = charAt != '0';
                    if (!z && this.decimalAt != -1) {
                        i2++;
                    }
                }
                if (z) {
                    int i6 = this.count;
                    ensureCapacity(i6 + 1, i6);
                    byte[] bArr = this.digits;
                    int i7 = this.count;
                    this.count = i7 + 1;
                    bArr[i7] = (byte) charAt;
                }
            }
            i3++;
        }
        int i52 = i3 + 1;
        if (str.charAt(i52) == '+') {
            i52++;
        }
        i4 = Integer.valueOf(str.substring(i52)).intValue();
        if (this.decimalAt == -1) {
            this.decimalAt = this.count;
        }
        this.decimalAt += i4 - i2;
    }

    private boolean shouldRoundUp(int i) {
        if (i >= this.count) {
            return false;
        }
        byte[] bArr = this.digits;
        if (bArr[i] > 53) {
            return true;
        }
        if (bArr[i] != 53) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.count; i2++) {
            if (this.digits[i2] != 48) {
                return true;
            }
        }
        if (i <= 0 || this.digits[i - 1] % 2 == 0) {
            return false;
        }
        return true;
    }

    public final void round(int i) {
        if (i >= 0 && i < this.count) {
            if (shouldRoundUp(i)) {
                while (true) {
                    i--;
                    if (i >= 0) {
                        byte[] bArr = this.digits;
                        bArr[i] = (byte) (bArr[i] + 1);
                        this.didRound = true;
                        if (bArr[i] <= 57) {
                            break;
                        }
                    } else {
                        this.digits[0] = 49;
                        this.decimalAt++;
                        this.didRound = true;
                        i = 0;
                        break;
                    }
                }
                i++;
            }
            this.count = i;
        }
        while (true) {
            int i2 = this.count;
            if (i2 > 1 && this.digits[i2 - 1] == 48) {
                this.count = i2 - 1;
            } else {
                return;
            }
        }
    }

    public boolean wasRounded() {
        return this.didRound;
    }

    public final void set(long j) {
        set(j, 0);
    }

    public final void set(long j, int i) {
        byte[] bArr;
        this.didRound = false;
        if (j > 0) {
            int i2 = 19;
            while (j > 0) {
                i2--;
                this.digits[i2] = (byte) ((int) ((j % 10) + 48));
                j /= 10;
            }
            this.decimalAt = 19 - i2;
            int i3 = 18;
            while (true) {
                bArr = this.digits;
                if (bArr[i3] != 48) {
                    break;
                }
                i3--;
            }
            this.count = (i3 - i2) + 1;
            System.arraycopy(bArr, i2, bArr, 0, this.count);
        } else if (j == Long.MIN_VALUE) {
            this.count = 19;
            this.decimalAt = 19;
            System.arraycopy(LONG_MIN_REP, 0, this.digits, 0, this.count);
        } else {
            this.count = 0;
            this.decimalAt = 0;
        }
        if (i > 0) {
            round(i);
        }
    }

    public final void set(BigInteger bigInteger, int i) {
        int i2;
        String bigInteger2 = bigInteger.toString();
        int length = bigInteger2.length();
        this.decimalAt = length;
        this.count = length;
        this.didRound = false;
        while (true) {
            int i3 = this.count;
            i2 = 1;
            if (i3 > 1 && bigInteger2.charAt(i3 - 1) == '0') {
                this.count--;
            }
        }
        if (bigInteger2.charAt(0) == '-') {
            this.count--;
            this.decimalAt--;
        } else {
            i2 = 0;
        }
        ensureCapacity(this.count, 0);
        for (int i4 = 0; i4 < this.count; i4++) {
            this.digits[i4] = (byte) bigInteger2.charAt(i4 + i2);
        }
        if (i > 0) {
            round(i);
        }
    }

    private void setBigDecimalDigits(String str, int i, boolean z) {
        this.didRound = false;
        set(str, str.length());
        if (z) {
            i += this.decimalAt;
        } else if (i == 0) {
            i = -1;
        }
        round(i);
    }

    public final void set(java.math.BigDecimal bigDecimal, int i, boolean z) {
        setBigDecimalDigits(bigDecimal.toString(), i, z);
    }

    public final void set(BigDecimal bigDecimal, int i, boolean z) {
        setBigDecimalDigits(bigDecimal.toString(), i, z);
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
        String l = Long.toString(Long.MIN_VALUE);
        int i = 0;
        while (i < 19) {
            int i2 = i + 1;
            LONG_MIN_REP[i] = (byte) l.charAt(i2);
            i = i2;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DigitList_Android)) {
            return false;
        }
        DigitList_Android digitList_Android = (DigitList_Android) obj;
        if (!(this.count == digitList_Android.count && this.decimalAt == digitList_Android.decimalAt)) {
            return false;
        }
        for (int i = 0; i < this.count; i++) {
            if (this.digits[i] != digitList_Android.digits[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.decimalAt;
        for (int i2 = 0; i2 < this.count; i2++) {
            i = (i * 37) + this.digits[i2];
        }
        return i;
    }

    public String toString() {
        if (isZero()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < this.count; i++) {
            sb.append((char) this.digits[i]);
        }
        sb.append("x10^");
        sb.append(this.decimalAt);
        return sb.toString();
    }
}
