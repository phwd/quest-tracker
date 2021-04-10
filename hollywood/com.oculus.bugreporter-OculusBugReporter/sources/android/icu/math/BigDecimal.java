package android.icu.math;

import android.icu.text.PluralRules;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.Year;

public class BigDecimal extends Number implements Serializable, Comparable<BigDecimal> {
    private static final int MaxArg = 999999999;
    private static final int MaxExp = 999999999;
    private static final int MinArg = -999999999;
    private static final int MinExp = -999999999;
    public static final BigDecimal ONE = new BigDecimal(1L);
    public static final int ROUND_CEILING = 2;
    public static final int ROUND_DOWN = 1;
    public static final int ROUND_FLOOR = 3;
    public static final int ROUND_HALF_DOWN = 5;
    public static final int ROUND_HALF_EVEN = 6;
    public static final int ROUND_HALF_UP = 4;
    public static final int ROUND_UNNECESSARY = 7;
    public static final int ROUND_UP = 0;
    public static final BigDecimal TEN = new BigDecimal(10);
    public static final BigDecimal ZERO = new BigDecimal(0L);
    private static byte[] bytecar = new byte[190];
    private static byte[] bytedig = diginit();
    private static final byte isneg = -1;
    private static final byte ispos = 1;
    private static final byte iszero = 0;
    private static final MathContext plainMC = new MathContext(0, 0);
    private static final long serialVersionUID = 8245355804974198832L;
    private int exp;
    private byte form;
    private byte ind;
    private byte[] mant;

    public BigDecimal(java.math.BigDecimal bd) {
        this(bd.toString());
    }

    public BigDecimal(BigInteger bi) {
        this(bi.toString(10));
    }

    public BigDecimal(BigInteger bi, int scale) {
        this(bi.toString(10));
        if (scale >= 0) {
            this.exp = -scale;
            return;
        }
        throw new NumberFormatException("Negative scale: " + scale);
    }

    public BigDecimal(char[] inchars) {
        this(inchars, 0, inchars.length);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        if ((r2 - r15) <= (r12 - 2)) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        bad(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009d, code lost:
        if (r23[r2 + 1] != '-') goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009f, code lost:
        r5 = r2 + 2;
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ab, code lost:
        if (r23[r2 + 1] != '+') goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ad, code lost:
        r5 = r2 + 2;
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b2, code lost:
        r5 = r2 + 1;
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b6, code lost:
        r6 = r12 - (r5 - r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ba, code lost:
        if (r6 != 0) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00bc, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00be, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c1, code lost:
        if (r6 <= 9) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c3, code lost:
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c5, code lost:
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c7, code lost:
        if ((r1 | r11) == false) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c9, code lost:
        bad(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00cc, code lost:
        r1 = r6;
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ce, code lost:
        if (r1 <= 0) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d0, code lost:
        r8 = r23[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d2, code lost:
        if (r8 >= '0') goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d4, code lost:
        bad(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d9, code lost:
        if (r8 <= '9') goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00df, code lost:
        if (android.icu.lang.UCharacter.isDigit(r8) != false) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e1, code lost:
        bad(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e4, code lost:
        r9 = android.icu.lang.UCharacter.digit(r8, 10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ea, code lost:
        if (r9 >= 0) goto L_0x00f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00ec, code lost:
        bad(r23);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00f0, code lost:
        r9 = r8 - '0';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f2, code lost:
        r22.exp = (r22.exp * 10) + r9;
        r1 = r1 - 1;
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00ff, code lost:
        if (r4 == false) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0101, code lost:
        r22.exp = -r22.exp;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0106, code lost:
        r17 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BigDecimal(char[] r23, int r24, int r25) {
        /*
        // Method dump skipped, instructions count: 481
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.<init>(char[], int, int):void");
    }

    public BigDecimal(double num) {
        this(new java.math.BigDecimal(num).toString());
    }

    public BigDecimal(int num) {
        this.form = 0;
        if (num > 9 || num < -9) {
            if (num > 0) {
                this.ind = 1;
                num = -num;
            } else {
                this.ind = -1;
            }
            int mun = num;
            int i = 9;
            while (true) {
                mun /= 10;
                if (mun == 0) {
                    break;
                }
                i--;
            }
            this.mant = new byte[(10 - i)];
            int i2 = (10 - i) - 1;
            while (true) {
                this.mant[i2] = (byte) (-((byte) (num % 10)));
                num /= 10;
                if (num != 0) {
                    i2--;
                } else {
                    return;
                }
            }
        } else if (num == 0) {
            this.mant = ZERO.mant;
            this.ind = 0;
        } else if (num == 1) {
            this.mant = ONE.mant;
            this.ind = 1;
        } else if (num == -1) {
            this.mant = ONE.mant;
            this.ind = -1;
        } else {
            this.mant = new byte[1];
            if (num > 0) {
                this.mant[0] = (byte) num;
                this.ind = 1;
                return;
            }
            this.mant[0] = (byte) (-num);
            this.ind = -1;
        }
    }

    public BigDecimal(long num) {
        this.form = 0;
        if (num > 0) {
            this.ind = 1;
            num = -num;
        } else if (num == 0) {
            this.ind = 0;
        } else {
            this.ind = -1;
        }
        long mun = num;
        int i = 18;
        while (true) {
            mun /= 10;
            if (mun == 0) {
                break;
            }
            i--;
        }
        this.mant = new byte[(19 - i)];
        int i2 = (19 - i) - 1;
        while (true) {
            this.mant[i2] = (byte) (-((byte) ((int) (num % 10))));
            num /= 10;
            if (num != 0) {
                i2--;
            } else {
                return;
            }
        }
    }

    public BigDecimal(String string) {
        this(string.toCharArray(), 0, string.length());
    }

    private BigDecimal() {
        this.form = 0;
    }

    public BigDecimal abs() {
        return abs(plainMC);
    }

    public BigDecimal abs(MathContext set) {
        if (this.ind == -1) {
            return negate(set);
        }
        return plus(set);
    }

    public BigDecimal add(BigDecimal rhs) {
        return add(rhs, plainMC);
    }

    /* JADX INFO: Multiple debug info for r2v1 byte[]: [D('newlen' int), D('usel' byte[])] */
    /* JADX INFO: Multiple debug info for r4v1 byte[]: [D('mult' int), D('user' byte[])] */
    public BigDecimal add(BigDecimal rhs, MathContext set) {
        boolean z;
        int ia;
        int ea;
        byte ca;
        byte cb;
        int newlen;
        BigDecimal rhs2 = rhs;
        if (set.lostDigits) {
            checkdigits(rhs2, set.digits);
        }
        BigDecimal lhs = this;
        if (lhs.ind == 0 && set.form != 0) {
            return rhs.plus(set);
        }
        if (rhs2.ind == 0 && set.form != 0) {
            return lhs.plus(set);
        }
        int reqdig = set.digits;
        if (reqdig > 0) {
            if (lhs.mant.length > reqdig) {
                lhs = clone(lhs).round(set);
            }
            if (rhs2.mant.length > reqdig) {
                rhs2 = clone(rhs).round(set);
            }
        }
        BigDecimal res = new BigDecimal();
        byte[] usel = lhs.mant;
        int usellen = lhs.mant.length;
        byte[] user = rhs2.mant;
        int userlen = rhs2.mant.length;
        int ia2 = lhs.exp;
        int ib = rhs2.exp;
        if (ia2 == ib) {
            res.exp = ia2;
            z = false;
        } else if (ia2 > ib) {
            int newlen2 = (usellen + ia2) - ib;
            z = false;
            if (newlen2 < userlen + reqdig + 1 || reqdig <= 0) {
                res.exp = rhs2.exp;
                if (newlen2 > reqdig + 1 && reqdig > 0) {
                    int tlen = (newlen2 - reqdig) - 1;
                    userlen -= tlen;
                    res.exp += tlen;
                    newlen2 = reqdig + 1;
                }
                if (newlen2 > usellen) {
                    usellen = newlen2;
                }
            } else {
                res.mant = usel;
                res.exp = ia2;
                res.ind = lhs.ind;
                if (usellen < reqdig) {
                    res.mant = extend(lhs.mant, reqdig);
                    res.exp -= reqdig - usellen;
                }
                return res.finish(set, false);
            }
        } else {
            z = false;
            int newlen3 = (userlen + ib) - ia2;
            if (newlen3 < usellen + reqdig + 1 || reqdig <= 0) {
                res.exp = lhs.exp;
                if (newlen3 <= reqdig + 1 || reqdig <= 0) {
                    newlen = newlen3;
                } else {
                    int tlen2 = (newlen3 - reqdig) - 1;
                    usellen -= tlen2;
                    res.exp += tlen2;
                    newlen = reqdig + 1;
                }
                if (newlen > userlen) {
                    userlen = newlen;
                }
            } else {
                res.mant = user;
                res.exp = ib;
                res.ind = rhs2.ind;
                if (userlen < reqdig) {
                    res.mant = extend(rhs2.mant, reqdig);
                    res.exp -= reqdig - userlen;
                }
                return res.finish(set, false);
            }
        }
        byte b = lhs.ind;
        if (b == 0) {
            res.ind = 1;
        } else {
            res.ind = b;
        }
        if ((lhs.ind == -1) == (rhs2.ind == -1)) {
            ia = 1;
        } else {
            if (rhs2.ind != 0) {
                if ((usellen < userlen) || (lhs.ind == 0)) {
                    usel = user;
                    user = usel;
                    usellen = userlen;
                    userlen = usellen;
                    res.ind = (byte) (-res.ind);
                    ia = -1;
                } else if (usellen <= userlen) {
                    int ia3 = 0;
                    int ib2 = 0;
                    int ea2 = usel.length - 1;
                    ia = -1;
                    int eb = user.length - 1;
                    while (true) {
                        if (ia3 <= ea2) {
                            ca = usel[ia3];
                            ea = ea2;
                        } else if (ib2 <= eb) {
                            ea = ea2;
                            ca = 0;
                        } else if (set.form != 0) {
                            return ZERO;
                        }
                        if (ib2 <= eb) {
                            cb = user[ib2];
                        } else {
                            cb = 0;
                        }
                        if (ca == cb) {
                            ia3++;
                            ib2++;
                            ea2 = ea;
                        } else if (ca < cb) {
                            usel = user;
                            user = usel;
                            usellen = userlen;
                            userlen = usellen;
                            res.ind = (byte) (-res.ind);
                        }
                    }
                }
            }
            ia = -1;
        }
        res.mant = byteaddsub(usel, usellen, user, userlen, ia, false);
        return res.finish(set, false);
    }

    public int compareTo(BigDecimal rhs) {
        return compareTo(rhs, plainMC);
    }

    public int compareTo(BigDecimal rhs, MathContext set) {
        if (set.lostDigits) {
            checkdigits(rhs, set.digits);
        }
        boolean z = true;
        if ((this.ind == rhs.ind) && (this.exp == rhs.exp)) {
            int thislength = this.mant.length;
            byte[] bArr = rhs.mant;
            if (thislength < bArr.length) {
                return (byte) (-this.ind);
            }
            if (thislength > bArr.length) {
                return this.ind;
            }
            boolean z2 = thislength <= set.digits;
            if (set.digits != 0) {
                z = false;
            }
            if (z2 || z) {
                int $6 = thislength;
                int i = 0;
                while ($6 > 0) {
                    byte[] bArr2 = this.mant;
                    byte b = bArr2[i];
                    byte[] bArr3 = rhs.mant;
                    if (b < bArr3[i]) {
                        return (byte) (-this.ind);
                    }
                    if (bArr2[i] > bArr3[i]) {
                        return this.ind;
                    }
                    $6--;
                    i++;
                }
                return 0;
            }
        } else {
            byte b2 = this.ind;
            byte b3 = rhs.ind;
            if (b2 < b3) {
                return -1;
            }
            if (b2 > b3) {
                return 1;
            }
        }
        BigDecimal newrhs = clone(rhs);
        newrhs.ind = (byte) (-newrhs.ind);
        return add(newrhs, set).ind;
    }

    public BigDecimal divide(BigDecimal rhs) {
        return dodivide('D', rhs, plainMC, -1);
    }

    public BigDecimal divide(BigDecimal rhs, int round) {
        return dodivide('D', rhs, new MathContext(0, 0, false, round), -1);
    }

    public BigDecimal divide(BigDecimal rhs, int scale, int round) {
        if (scale >= 0) {
            return dodivide('D', rhs, new MathContext(0, 0, false, round), scale);
        }
        throw new ArithmeticException("Negative scale: " + scale);
    }

    public BigDecimal divide(BigDecimal rhs, MathContext set) {
        return dodivide('D', rhs, set, -1);
    }

    public BigDecimal divideInteger(BigDecimal rhs) {
        return dodivide('I', rhs, plainMC, 0);
    }

    public BigDecimal divideInteger(BigDecimal rhs, MathContext set) {
        return dodivide('I', rhs, set, 0);
    }

    public BigDecimal max(BigDecimal rhs) {
        return max(rhs, plainMC);
    }

    public BigDecimal max(BigDecimal rhs, MathContext set) {
        if (compareTo(rhs, set) >= 0) {
            return plus(set);
        }
        return rhs.plus(set);
    }

    public BigDecimal min(BigDecimal rhs) {
        return min(rhs, plainMC);
    }

    public BigDecimal min(BigDecimal rhs, MathContext set) {
        if (compareTo(rhs, set) <= 0) {
            return plus(set);
        }
        return rhs.plus(set);
    }

    public BigDecimal multiply(BigDecimal rhs) {
        return multiply(rhs, plainMC);
    }

    public BigDecimal multiply(BigDecimal rhs, MathContext set) {
        byte[] multand;
        byte[] multer;
        int acclen;
        byte[] multer2;
        int acclen2;
        byte[] multand2;
        boolean z;
        BigDecimal res;
        BigDecimal rhs2 = rhs;
        if (set.lostDigits) {
            checkdigits(rhs2, set.digits);
        }
        BigDecimal lhs = this;
        int padding = 0;
        int reqdig = set.digits;
        if (reqdig > 0) {
            if (lhs.mant.length > reqdig) {
                lhs = clone(lhs).round(set);
            }
            if (rhs2.mant.length > reqdig) {
                rhs2 = clone(rhs).round(set);
            }
        } else {
            int i = lhs.exp;
            if (i > 0) {
                padding = 0 + i;
            }
            int i2 = rhs2.exp;
            if (i2 > 0) {
                padding += i2;
            }
        }
        if (lhs.mant.length < rhs2.mant.length) {
            multer = lhs.mant;
            multand = rhs2.mant;
        } else {
            multer = rhs2.mant;
            multand = lhs.mant;
        }
        int multandlen = (multer.length + multand.length) - 1;
        boolean z2 = false;
        if (multer[0] * multand[0] > 9) {
            acclen = multandlen + 1;
        } else {
            acclen = multandlen;
        }
        BigDecimal res2 = new BigDecimal();
        int multandlen2 = multandlen;
        int n = 0;
        int $7 = multer.length;
        byte[] acc = new byte[acclen];
        while ($7 > 0) {
            byte mult = multer[n];
            if (mult != 0) {
                multer2 = multer;
                multand2 = multand;
                res = res2;
                acclen2 = acclen;
                z = z2;
                acc = byteaddsub(acc, acc.length, multand, multandlen2, mult, true);
            } else {
                multer2 = multer;
                multand2 = multand;
                acclen2 = acclen;
                res = res2;
                z = z2;
            }
            multandlen2--;
            $7--;
            n++;
            multer = multer2;
            res2 = res;
            z2 = z;
            multand = multand2;
            acclen = acclen2;
        }
        res2.ind = (byte) (lhs.ind * rhs2.ind);
        res2.exp = (lhs.exp + rhs2.exp) - padding;
        if (padding == 0) {
            res2.mant = acc;
        } else {
            res2.mant = extend(acc, acc.length + padding);
        }
        return res2.finish(set, z2);
    }

    public BigDecimal negate() {
        return negate(plainMC);
    }

    public BigDecimal negate(MathContext set) {
        if (set.lostDigits) {
            checkdigits(null, set.digits);
        }
        BigDecimal res = clone(this);
        res.ind = (byte) (-res.ind);
        return res.finish(set, false);
    }

    public BigDecimal plus() {
        return plus(plainMC);
    }

    public BigDecimal plus(MathContext set) {
        if (set.lostDigits) {
            checkdigits(null, set.digits);
        }
        if (set.form == 0 && this.form == 0 && (this.mant.length <= set.digits || set.digits == 0)) {
            return this;
        }
        return clone(this).finish(set, false);
    }

    public BigDecimal pow(BigDecimal rhs) {
        return pow(rhs, plainMC);
    }

    public BigDecimal pow(BigDecimal rhs, MathContext set) {
        BigDecimal lhs;
        int workdigits;
        if (set.lostDigits) {
            checkdigits(rhs, set.digits);
        }
        int n = rhs.intcheck(Year.MIN_VALUE, Year.MAX_VALUE);
        BigDecimal lhs2 = this;
        int reqdig = set.digits;
        if (reqdig == 0) {
            if (rhs.ind != -1) {
                workdigits = 0;
                lhs = lhs2;
            } else {
                throw new ArithmeticException("Negative power: " + rhs.toString());
            }
        } else if (rhs.mant.length + rhs.exp <= reqdig) {
            if (lhs2.mant.length > reqdig) {
                lhs2 = clone(lhs2).round(set);
            }
            workdigits = reqdig + rhs.mant.length + rhs.exp + 1;
            lhs = lhs2;
        } else {
            throw new ArithmeticException("Too many digits: " + rhs.toString());
        }
        MathContext workset = new MathContext(workdigits, set.form, false, set.roundingMode);
        BigDecimal res = ONE;
        if (n == 0) {
            return res;
        }
        if (n < 0) {
            n = -n;
        }
        boolean seenbit = false;
        int i = 1;
        while (true) {
            n += n;
            if (n < 0) {
                seenbit = true;
                res = res.multiply(lhs, workset);
            }
            if (i == 31) {
                break;
            }
            if (seenbit) {
                res = res.multiply(res, workset);
            }
            i++;
        }
        if (rhs.ind < 0) {
            res = ONE.divide(res, workset);
        }
        return res.finish(set, true);
    }

    public BigDecimal remainder(BigDecimal rhs) {
        return dodivide('R', rhs, plainMC, -1);
    }

    public BigDecimal remainder(BigDecimal rhs, MathContext set) {
        return dodivide('R', rhs, set, -1);
    }

    public BigDecimal subtract(BigDecimal rhs) {
        return subtract(rhs, plainMC);
    }

    public BigDecimal subtract(BigDecimal rhs, MathContext set) {
        if (set.lostDigits) {
            checkdigits(rhs, set.digits);
        }
        BigDecimal newrhs = clone(rhs);
        newrhs.ind = (byte) (-newrhs.ind);
        return add(newrhs, set);
    }

    public byte byteValueExact() {
        int num = intValueExact();
        boolean z = true;
        boolean z2 = num > 127;
        if (num >= -128) {
            z = false;
        }
        if (!z && !z2) {
            return (byte) num;
        }
        throw new ArithmeticException("Conversion overflow: " + toString());
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.valueOf(toString()).doubleValue();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BigDecimal)) {
            return false;
        }
        BigDecimal rhs = (BigDecimal) obj;
        if (this.ind != rhs.ind) {
            return false;
        }
        if (((this.mant.length == rhs.mant.length) & (this.exp == rhs.exp)) && (this.form == rhs.form)) {
            int $8 = this.mant.length;
            int i = 0;
            while ($8 > 0) {
                if (this.mant[i] != rhs.mant[i]) {
                    return false;
                }
                $8--;
                i++;
            }
        } else {
            char[] lca = layout();
            char[] rca = rhs.layout();
            if (lca.length != rca.length) {
                return false;
            }
            int $9 = lca.length;
            int i2 = 0;
            while ($9 > 0) {
                if (lca[i2] != rca[i2]) {
                    return false;
                }
                $9--;
                i2++;
            }
        }
        return true;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.valueOf(toString()).floatValue();
    }

    public String format(int before, int after) {
        return format(before, after, -1, -1, 1, 4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0196  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String format(int r26, int r27, int r28, int r29, int r30, int r31) {
        /*
        // Method dump skipped, instructions count: 593
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.format(int, int, int, int, int, int):java.lang.String");
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return toBigInteger().intValue();
    }

    public int intValueExact() {
        int useexp;
        if (this.ind == 0) {
            return 0;
        }
        byte[] bArr = this.mant;
        int lodigit = bArr.length - 1;
        int i = this.exp;
        if (i < 0) {
            lodigit += i;
            if (!allzero(bArr, lodigit + 1)) {
                throw new ArithmeticException("Decimal part non-zero: " + toString());
            } else if (lodigit < 0) {
                return 0;
            } else {
                useexp = 0;
            }
        } else if (i + lodigit <= 9) {
            useexp = this.exp;
        } else {
            throw new ArithmeticException("Conversion overflow: " + toString());
        }
        int result = 0;
        int $16 = lodigit + useexp;
        for (int i2 = 0; i2 <= $16; i2++) {
            result *= 10;
            if (i2 <= lodigit) {
                result += this.mant[i2];
            }
        }
        if (lodigit + useexp == 9) {
            int topdig = result / 1000000000;
            byte[] bArr2 = this.mant;
            if (topdig != bArr2[0]) {
                if (result == Integer.MIN_VALUE && this.ind == -1 && bArr2[0] == 2) {
                    return result;
                }
                throw new ArithmeticException("Conversion overflow: " + toString());
            }
        }
        if (this.ind == 1) {
            return result;
        }
        return -result;
    }

    @Override // java.lang.Number
    public long longValue() {
        return toBigInteger().longValue();
    }

    public long longValueExact() {
        int useexp;
        int cstart;
        if (this.ind == 0) {
            return 0;
        }
        byte[] bArr = this.mant;
        int lodigit = bArr.length - 1;
        int i = this.exp;
        if (i < 0) {
            lodigit += i;
            if (lodigit < 0) {
                cstart = 0;
            } else {
                cstart = lodigit + 1;
            }
            if (!allzero(this.mant, cstart)) {
                throw new ArithmeticException("Decimal part non-zero: " + toString());
            } else if (lodigit < 0) {
                return 0;
            } else {
                useexp = 0;
            }
        } else if (i + bArr.length <= 18) {
            useexp = this.exp;
        } else {
            throw new ArithmeticException("Conversion overflow: " + toString());
        }
        long result = 0;
        int $17 = lodigit + useexp;
        for (int i2 = 0; i2 <= $17; i2++) {
            result *= 10;
            if (i2 <= lodigit) {
                result += (long) this.mant[i2];
            }
        }
        if (lodigit + useexp == 18) {
            byte[] bArr2 = this.mant;
            if (result / 1000000000000000000L != ((long) bArr2[0])) {
                if (result == Long.MIN_VALUE && this.ind == -1 && bArr2[0] == 9) {
                    return result;
                }
                throw new ArithmeticException("Conversion overflow: " + toString());
            }
        }
        if (this.ind == 1) {
            return result;
        }
        return -result;
    }

    public BigDecimal movePointLeft(int n) {
        BigDecimal res = clone(this);
        res.exp -= n;
        return res.finish(plainMC, false);
    }

    public BigDecimal movePointRight(int n) {
        BigDecimal res = clone(this);
        res.exp += n;
        return res.finish(plainMC, false);
    }

    public int scale() {
        int i = this.exp;
        if (i >= 0) {
            return 0;
        }
        return -i;
    }

    public BigDecimal setScale(int scale) {
        return setScale(scale, 7);
    }

    public BigDecimal setScale(int scale, int round) {
        int padding;
        int ourscale = scale();
        if (ourscale == scale && this.form == 0) {
            return this;
        }
        BigDecimal res = clone(this);
        if (ourscale <= scale) {
            if (ourscale == 0) {
                padding = res.exp + scale;
            } else {
                padding = scale - ourscale;
            }
            byte[] bArr = res.mant;
            res.mant = extend(bArr, bArr.length + padding);
            res.exp = -scale;
        } else if (scale >= 0) {
            res = res.round(res.mant.length - (ourscale - scale), round);
            if (res.exp != (-scale)) {
                byte[] bArr2 = res.mant;
                res.mant = extend(bArr2, bArr2.length + 1);
                res.exp--;
            }
        } else {
            throw new ArithmeticException("Negative scale: " + scale);
        }
        res.form = 0;
        return res;
    }

    public short shortValueExact() {
        int num = intValueExact();
        boolean z = true;
        boolean z2 = num > 32767;
        if (num >= -32768) {
            z = false;
        }
        if (!z && !z2) {
            return (short) num;
        }
        throw new ArithmeticException("Conversion overflow: " + toString());
    }

    public int signum() {
        return this.ind;
    }

    public java.math.BigDecimal toBigDecimal() {
        return new java.math.BigDecimal(unscaledValue(), scale());
    }

    public BigInteger toBigInteger() {
        BigDecimal res;
        boolean z = true;
        boolean z2 = this.exp >= 0;
        if (this.form != 0) {
            z = false;
        }
        if (z2 && z) {
            res = this;
        } else {
            int i = this.exp;
            if (i >= 0) {
                res = clone(this);
                res.form = 0;
            } else if ((-i) >= this.mant.length) {
                res = ZERO;
            } else {
                res = clone(this);
                byte[] bArr = res.mant;
                int newlen = bArr.length + res.exp;
                byte[] newmant = new byte[newlen];
                System.arraycopy(bArr, 0, newmant, 0, newlen);
                res.mant = newmant;
                res.form = 0;
                res.exp = 0;
            }
        }
        return new BigInteger(new String(res.layout()));
    }

    public BigInteger toBigIntegerExact() {
        int i = this.exp;
        if (i < 0) {
            byte[] bArr = this.mant;
            if (!allzero(bArr, bArr.length + i)) {
                throw new ArithmeticException("Decimal part non-zero: " + toString());
            }
        }
        return toBigInteger();
    }

    public char[] toCharArray() {
        return layout();
    }

    public String toString() {
        return new String(layout());
    }

    public BigInteger unscaledValue() {
        BigDecimal res;
        if (this.exp >= 0) {
            res = this;
        } else {
            res = clone(this);
            res.exp = 0;
        }
        return res.toBigInteger();
    }

    public static BigDecimal valueOf(double dub) {
        return new BigDecimal(new Double(dub).toString());
    }

    public static BigDecimal valueOf(long lint) {
        return valueOf(lint, 0);
    }

    public static BigDecimal valueOf(long lint, int scale) {
        BigDecimal res;
        if (lint == 0) {
            res = ZERO;
        } else if (lint == 1) {
            res = ONE;
        } else if (lint == 10) {
            res = TEN;
        } else {
            res = new BigDecimal(lint);
        }
        if (scale == 0) {
            return res;
        }
        if (scale >= 0) {
            BigDecimal res2 = clone(res);
            res2.exp = -scale;
            return res2;
        }
        throw new NumberFormatException("Negative scale: " + scale);
    }

    private char[] layout() {
        char csign;
        byte[] bArr = this.mant;
        char[] cmant = new char[bArr.length];
        int $18 = bArr.length;
        int i = 0;
        while ($18 > 0) {
            cmant[i] = (char) (this.mant[i] + 48);
            $18--;
            i++;
        }
        if (this.form != 0) {
            StringBuilder sb = new StringBuilder(cmant.length + 15);
            if (this.ind == -1) {
                sb.append('-');
            }
            int euse = (this.exp + cmant.length) - 1;
            if (this.form == 1) {
                sb.append(cmant[0]);
                if (cmant.length > 1) {
                    sb.append('.');
                    sb.append(cmant, 1, cmant.length - 1);
                }
            } else {
                int sig = euse % 3;
                if (sig < 0) {
                    sig += 3;
                }
                euse -= sig;
                int sig2 = sig + 1;
                if (sig2 >= cmant.length) {
                    sb.append(cmant, 0, cmant.length);
                    for (int $19 = sig2 - cmant.length; $19 > 0; $19--) {
                        sb.append('0');
                    }
                } else {
                    sb.append(cmant, 0, sig2);
                    sb.append('.');
                    sb.append(cmant, sig2, cmant.length - sig2);
                }
            }
            if (euse != 0) {
                if (euse < 0) {
                    euse = -euse;
                    csign = '-';
                } else {
                    csign = '+';
                }
                sb.append('E');
                sb.append(csign);
                sb.append(euse);
            }
            char[] rec = new char[sb.length()];
            int srcEnd = sb.length();
            if (srcEnd != 0) {
                sb.getChars(0, srcEnd, rec, 0);
            }
            return rec;
        } else if (this.exp != 0) {
            int needsign = this.ind == -1 ? 1 : 0;
            int i2 = this.exp;
            int mag = cmant.length + i2;
            if (mag < 1) {
                char[] rec2 = new char[((needsign + 2) - i2)];
                if (needsign != 0) {
                    rec2[0] = '-';
                }
                rec2[needsign] = '0';
                rec2[needsign + 1] = '.';
                int $20 = -mag;
                int i3 = needsign + 2;
                while ($20 > 0) {
                    rec2[i3] = '0';
                    $20--;
                    i3++;
                }
                System.arraycopy((Object) cmant, 0, (Object) rec2, (needsign + 2) - mag, cmant.length);
                return rec2;
            } else if (mag > cmant.length) {
                char[] rec3 = new char[(needsign + mag)];
                if (needsign != 0) {
                    rec3[0] = '-';
                }
                System.arraycopy((Object) cmant, 0, (Object) rec3, needsign, cmant.length);
                int $21 = mag - cmant.length;
                int i4 = cmant.length + needsign;
                while ($21 > 0) {
                    rec3[i4] = '0';
                    $21--;
                    i4++;
                }
                return rec3;
            } else {
                char[] rec4 = new char[(needsign + 1 + cmant.length)];
                if (needsign != 0) {
                    rec4[0] = '-';
                }
                System.arraycopy((Object) cmant, 0, (Object) rec4, needsign, mag);
                rec4[needsign + mag] = '.';
                System.arraycopy((Object) cmant, mag, (Object) rec4, needsign + mag + 1, cmant.length - mag);
                return rec4;
            }
        } else if (this.ind >= 0) {
            return cmant;
        } else {
            char[] rec5 = new char[(cmant.length + 1)];
            rec5[0] = '-';
            System.arraycopy((Object) cmant, 0, (Object) rec5, 1, cmant.length);
            return rec5;
        }
    }

    private int intcheck(int min, int max) {
        int i = intValueExact();
        boolean z = true;
        boolean z2 = i < min;
        if (i <= max) {
            z = false;
        }
        if (!z && !z2) {
            return i;
        }
        throw new ArithmeticException("Conversion overflow: " + i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r3v6 byte[]: [D('newexp' int), D('var2' byte[])] */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x014e, code lost:
        if (r31 == 0) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0150, code lost:
        r13 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0152, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0153, code lost:
        if (r12 == 0) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0155, code lost:
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0157, code lost:
        r14 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0159, code lost:
        if ((r13 | r14) == false) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x015b, code lost:
        r4.mant[r31] = (byte) r12;
        r13 = r31 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0164, code lost:
        if (r13 != (r7 + 1)) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x016b, code lost:
        if (r11[0] != 0) goto L_0x016f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x016f, code lost:
        r31 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0171, code lost:
        if (r1 < 0) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0176, code lost:
        if ((-r4.exp) <= r1) goto L_0x0179;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x017b, code lost:
        if (r0 == 'D') goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x017f, code lost:
        if (r4.exp > 0) goto L_0x0185;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.math.BigDecimal dodivide(char r39, android.icu.math.BigDecimal r40, android.icu.math.MathContext r41, int r42) {
        /*
        // Method dump skipped, instructions count: 875
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.dodivide(char, android.icu.math.BigDecimal, android.icu.math.MathContext, int):android.icu.math.BigDecimal");
    }

    private void bad(char[] s) {
        throw new NumberFormatException("Not a number: " + String.valueOf(s));
    }

    private void badarg(String name, int pos, String value) {
        throw new IllegalArgumentException("Bad argument " + pos + " to " + name + PluralRules.KEYWORD_RULE_SEPARATOR + value);
    }

    private static final byte[] extend(byte[] inarr, int newlen) {
        if (inarr.length == newlen) {
            return inarr;
        }
        byte[] newarr = new byte[newlen];
        System.arraycopy(inarr, 0, newarr, 0, inarr.length);
        return newarr;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:54:0x006b */
    private static final byte[] byteaddsub(byte[] a, int avlen, byte[] b, int bvlen, int m, boolean reuse) {
        byte[] newarr;
        byte b2;
        int alength = a.length;
        int blength = b.length;
        int ap = avlen - 1;
        int bp = bvlen - 1;
        int maxarr = bp;
        if (maxarr < ap) {
            maxarr = ap;
        }
        byte[] reb = null;
        if (reuse && maxarr + 1 == alength) {
            reb = a;
        }
        if (reb == null) {
            reb = new byte[(maxarr + 1)];
        }
        boolean quickm = false;
        if (m == 1) {
            quickm = true;
        } else if (m == -1) {
            quickm = true;
        }
        int digit = 0;
        int op = maxarr;
        while (op >= 0) {
            if (ap >= 0) {
                if (ap < alength) {
                    digit += a[ap];
                }
                ap--;
            }
            if (bp >= 0) {
                if (bp < blength) {
                    if (!quickm) {
                        digit += b[bp] * m;
                    } else if (m > 0) {
                        digit += b[bp];
                    } else {
                        digit -= b[bp];
                    }
                }
                bp--;
            }
            if (digit >= 10 || digit < 0) {
                int dp90 = digit + 90;
                reb[op] = bytedig[dp90];
                b2 = bytecar[dp90];
            } else {
                reb[op] = (byte) digit;
                b2 = 0;
            }
            op--;
            digit = b2;
        }
        if (digit == 0) {
            return reb;
        }
        byte[] newarr2 = null;
        if (reuse && maxarr + 2 == a.length) {
            newarr2 = a;
        }
        if (newarr2 == null) {
            newarr = new byte[(maxarr + 2)];
        } else {
            newarr = newarr2;
        }
        newarr[0] = (byte) digit;
        if (maxarr < 10) {
            int $24 = maxarr + 1;
            int i = 0;
            while ($24 > 0) {
                newarr[i + 1] = reb[i];
                $24--;
                i++;
            }
        } else {
            System.arraycopy(reb, 0, newarr, 1, maxarr + 1);
        }
        return newarr;
    }

    private static final byte[] diginit() {
        byte[] work = new byte[190];
        for (int op = 0; op <= 189; op++) {
            int digit = op - 90;
            if (digit >= 0) {
                work[op] = (byte) (digit % 10);
                bytecar[op] = (byte) (digit / 10);
            } else {
                int digit2 = digit + 100;
                work[op] = (byte) (digit2 % 10);
                bytecar[op] = (byte) ((digit2 / 10) - 10);
            }
        }
        return work;
    }

    private static final BigDecimal clone(BigDecimal dec) {
        BigDecimal copy = new BigDecimal();
        copy.ind = dec.ind;
        copy.exp = dec.exp;
        copy.form = dec.form;
        copy.mant = dec.mant;
        return copy;
    }

    private void checkdigits(BigDecimal rhs, int dig) {
        if (dig != 0) {
            byte[] bArr = this.mant;
            if (bArr.length > dig && !allzero(bArr, dig)) {
                throw new ArithmeticException("Too many digits: " + toString());
            } else if (rhs != null) {
                byte[] bArr2 = rhs.mant;
                if (bArr2.length > dig && !allzero(bArr2, dig)) {
                    throw new ArithmeticException("Too many digits: " + rhs.toString());
                }
            }
        }
    }

    private BigDecimal round(MathContext set) {
        return round(set.digits, set.roundingMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.math.BigDecimal round(int r19, int r20) {
        /*
        // Method dump skipped, instructions count: 292
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.round(int, int):android.icu.math.BigDecimal");
    }

    private static final boolean allzero(byte[] array, int start) {
        if (start < 0) {
            start = 0;
        }
        int $25 = array.length - 1;
        for (int i = start; i <= $25; i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a0, code lost:
        if (r10 <= 999999999) goto L_0x00b8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.math.BigDecimal finish(android.icu.math.MathContext r13, boolean r14) {
        /*
        // Method dump skipped, instructions count: 238
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.math.BigDecimal.finish(android.icu.math.MathContext, boolean):android.icu.math.BigDecimal");
    }
}
