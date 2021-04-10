package sun.misc;

import java.util.Arrays;
import sun.misc.FloatingDecimal;

public class FormattedFloatingDecimal {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ThreadLocal<Object> threadLocalCharBuffer = new ThreadLocal<Object>() {
        /* class sun.misc.FormattedFloatingDecimal.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Object initialValue() {
            return new char[20];
        }
    };
    private int decExponentRounded;
    private char[] exponent;
    private char[] mantissa;

    public enum Form {
        SCIENTIFIC,
        COMPATIBLE,
        DECIMAL_FLOAT,
        GENERAL
    }

    public static FormattedFloatingDecimal valueOf(double d, int precision, Form form) {
        return new FormattedFloatingDecimal(precision, form, FloatingDecimal.getBinaryToASCIIConverter(d, form == Form.COMPATIBLE));
    }

    private static char[] getBuffer() {
        return (char[]) threadLocalCharBuffer.get();
    }

    private FormattedFloatingDecimal(int precision, Form form, FloatingDecimal.BinaryToASCIIConverter fdConverter) {
        if (fdConverter.isExceptional()) {
            this.mantissa = fdConverter.toJavaFormatString().toCharArray();
            this.exponent = null;
            return;
        }
        char[] digits = getBuffer();
        int nDigits = fdConverter.getDigits(digits);
        int decExp = fdConverter.getDecimalExponent();
        boolean isNegative = fdConverter.isNegative();
        int i = AnonymousClass2.$SwitchMap$sun$misc$FormattedFloatingDecimal$Form[form.ordinal()];
        if (i == 1) {
            this.decExponentRounded = decExp;
            fillCompatible(precision, digits, nDigits, decExp, isNegative);
        } else if (i == 2) {
            int exp = applyPrecision(decExp, digits, nDigits, decExp + precision);
            fillDecimal(precision, digits, nDigits, exp, isNegative);
            this.decExponentRounded = exp;
        } else if (i == 3) {
            int exp2 = applyPrecision(decExp, digits, nDigits, precision + 1);
            fillScientific(precision, digits, nDigits, exp2, isNegative);
            this.decExponentRounded = exp2;
        } else if (i == 4) {
            int exp3 = applyPrecision(decExp, digits, nDigits, precision);
            if (exp3 - 1 < -4 || exp3 - 1 >= precision) {
                fillScientific(precision - 1, digits, nDigits, exp3, isNegative);
            } else {
                fillDecimal(precision - exp3, digits, nDigits, exp3, isNegative);
            }
            this.decExponentRounded = exp3;
        }
    }

    /* renamed from: sun.misc.FormattedFloatingDecimal$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$sun$misc$FormattedFloatingDecimal$Form = new int[Form.values().length];

        static {
            try {
                $SwitchMap$sun$misc$FormattedFloatingDecimal$Form[Form.COMPATIBLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$sun$misc$FormattedFloatingDecimal$Form[Form.DECIMAL_FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$sun$misc$FormattedFloatingDecimal$Form[Form.SCIENTIFIC.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$sun$misc$FormattedFloatingDecimal$Form[Form.GENERAL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public int getExponentRounded() {
        return this.decExponentRounded - 1;
    }

    public char[] getMantissa() {
        return this.mantissa;
    }

    public char[] getExponent() {
        return this.exponent;
    }

    private static int applyPrecision(int decExp, char[] digits, int nDigits, int prec) {
        if (prec >= nDigits || prec < 0) {
            return decExp;
        }
        if (prec != 0) {
            if (digits[prec] >= '5') {
                int i = prec - 1;
                char c = digits[i];
                if (c == '9') {
                    while (c == '9' && i > 0) {
                        i--;
                        c = digits[i];
                    }
                    if (c == '9') {
                        digits[0] = '1';
                        Arrays.fill(digits, 1, nDigits, '0');
                        return decExp + 1;
                    }
                }
                digits[i] = (char) (c + 1);
                Arrays.fill(digits, i + 1, nDigits, '0');
            } else {
                Arrays.fill(digits, prec, nDigits, '0');
            }
            return decExp;
        } else if (digits[0] >= '5') {
            digits[0] = '1';
            Arrays.fill(digits, 1, nDigits, '0');
            return decExp + 1;
        } else {
            Arrays.fill(digits, 0, nDigits, '0');
            return decExp;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r14v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private void fillCompatible(int precision, char[] digits, int nDigits, int exp, boolean isNegative) {
        int expStartIntex;
        int e;
        boolean isNegExp = false;
        if (exp <= 0 || exp >= 8) {
            if (exp > 0 || exp <= -3) {
                if (nDigits > 1) {
                    this.mantissa = create(isNegative, nDigits + 1);
                    char[] cArr = this.mantissa;
                    cArr[isNegative] = digits[0];
                    cArr[isNegative + 1] = '.';
                    System.arraycopy((Object) digits, 1, (Object) cArr, isNegative + 2, nDigits - 1);
                } else {
                    this.mantissa = create(isNegative, 3);
                    char[] cArr2 = this.mantissa;
                    cArr2[isNegative ? 1 : 0] = digits[0];
                    cArr2[(isNegative ? 1 : 0) + 1] = '.';
                    cArr2[isNegative + 2] = '0';
                }
                if (exp <= 0) {
                    isNegExp = true;
                }
                if (isNegExp) {
                    e = (-exp) + 1;
                    expStartIntex = 1;
                } else {
                    e = exp - 1;
                    expStartIntex = 0;
                }
                if (e <= 9) {
                    this.exponent = create(isNegExp, 1);
                    this.exponent[expStartIntex] = (char) (e + 48);
                } else if (e <= 99) {
                    this.exponent = create(isNegExp, 2);
                    char[] cArr3 = this.exponent;
                    cArr3[expStartIntex] = (char) ((e / 10) + 48);
                    cArr3[expStartIntex + 1] = (char) ((e % 10) + 48);
                } else {
                    this.exponent = create(isNegExp, 3);
                    char[] cArr4 = this.exponent;
                    cArr4[expStartIntex] = (char) ((e / 100) + 48);
                    int e2 = e % 100;
                    cArr4[expStartIntex + 1] = (char) ((e2 / 10) + 48);
                    cArr4[expStartIntex + 2] = (char) ((e2 % 10) + 48);
                }
            } else {
                int zeros = Math.max(0, Math.min(-exp, precision));
                int t = Math.max(0, Math.min(nDigits, precision + exp));
                if (zeros > 0) {
                    this.mantissa = create(isNegative, zeros + 2 + t);
                    char[] cArr5 = this.mantissa;
                    cArr5[isNegative] = '0';
                    cArr5[isNegative + 1] = '.';
                    Arrays.fill(cArr5, isNegative + 2, isNegative + 2 + zeros, '0');
                    if (t > 0) {
                        System.arraycopy((Object) digits, 0, (Object) this.mantissa, isNegative + 2 + zeros, t);
                    }
                } else if (t > 0) {
                    this.mantissa = create(isNegative, zeros + 2 + t);
                    char[] cArr6 = this.mantissa;
                    cArr6[isNegative] = '0';
                    cArr6[isNegative + 1] = '.';
                    System.arraycopy((Object) digits, 0, (Object) cArr6, isNegative + 2, t);
                } else {
                    this.mantissa = create(isNegative, 1);
                    this.mantissa[isNegative] = '0';
                }
            }
        } else if (nDigits < exp) {
            int extraZeros = exp - nDigits;
            this.mantissa = create(isNegative, nDigits + extraZeros + 2);
            System.arraycopy((Object) digits, 0, (Object) this.mantissa, (int) isNegative, nDigits);
            Arrays.fill(this.mantissa, isNegative + nDigits, isNegative + nDigits + extraZeros, '0');
            char[] cArr7 = this.mantissa;
            cArr7[isNegative + nDigits + extraZeros] = '.';
            cArr7[isNegative + nDigits + extraZeros + 1] = '0';
        } else if (exp < nDigits) {
            int t2 = Math.min(nDigits - exp, precision);
            this.mantissa = create(isNegative, exp + 1 + t2);
            System.arraycopy((Object) digits, 0, (Object) this.mantissa, (int) isNegative, exp);
            char[] cArr8 = this.mantissa;
            cArr8[isNegative + exp] = '.';
            System.arraycopy((Object) digits, exp, (Object) cArr8, isNegative + exp + 1, t2);
        } else {
            this.mantissa = create(isNegative, nDigits + 2);
            System.arraycopy((Object) digits, 0, (Object) this.mantissa, (int) isNegative, nDigits);
            char[] cArr9 = this.mantissa;
            cArr9[isNegative + nDigits] = '.';
            cArr9[isNegative + nDigits + 1] = '0';
        }
    }

    private static char[] create(boolean isNegative, int size) {
        if (!isNegative) {
            return new char[size];
        }
        char[] r = new char[(size + 1)];
        r[0] = '-';
        return r;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r13v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private void fillDecimal(int precision, char[] digits, int nDigits, int exp, boolean isNegative) {
        if (exp > 0) {
            if (nDigits < exp) {
                this.mantissa = create(isNegative, exp);
                System.arraycopy((Object) digits, 0, (Object) this.mantissa, (int) isNegative, nDigits);
                Arrays.fill(this.mantissa, isNegative + nDigits, isNegative + exp, '0');
                return;
            }
            int t = Math.min(nDigits - exp, precision);
            this.mantissa = create(isNegative, (t > 0 ? t + 1 : 0) + exp);
            System.arraycopy((Object) digits, 0, (Object) this.mantissa, (int) isNegative, exp);
            if (t > 0) {
                char[] cArr = this.mantissa;
                cArr[isNegative + exp] = '.';
                System.arraycopy((Object) digits, exp, (Object) cArr, isNegative + exp + 1, t);
            }
        } else if (exp <= 0) {
            int zeros = Math.max(0, Math.min(-exp, precision));
            int t2 = Math.max(0, Math.min(nDigits, precision + exp));
            if (zeros > 0) {
                this.mantissa = create(isNegative, zeros + 2 + t2);
                char[] cArr2 = this.mantissa;
                cArr2[isNegative] = '0';
                cArr2[isNegative + 1] = '.';
                Arrays.fill(cArr2, isNegative + 2, isNegative + 2 + zeros, '0');
                if (t2 > 0) {
                    System.arraycopy((Object) digits, 0, (Object) this.mantissa, isNegative + 2 + zeros, t2);
                }
            } else if (t2 > 0) {
                this.mantissa = create(isNegative, zeros + 2 + t2);
                char[] cArr3 = this.mantissa;
                cArr3[isNegative] = '0';
                cArr3[(isNegative ? 1 : 0) + 1] = '.';
                System.arraycopy((Object) digits, 0, (Object) cArr3, isNegative + 2, t2);
            } else {
                this.mantissa = create(isNegative, 1);
                this.mantissa[isNegative ? 1 : 0] = '0';
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r21v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private void fillScientific(int precision, char[] digits, int nDigits, int exp, boolean isNegative) {
        int e;
        char expSign;
        int t = Math.max(0, Math.min(nDigits - 1, precision));
        if (t > 0) {
            this.mantissa = create(isNegative, t + 2);
            char[] cArr = this.mantissa;
            cArr[isNegative] = digits[0];
            cArr[(isNegative ? 1 : 0) + 1] = '.';
            System.arraycopy((Object) digits, 1, (Object) cArr, isNegative + 2, t);
        } else {
            this.mantissa = create(isNegative, 1);
            this.mantissa[isNegative ? 1 : 0] = digits[0];
        }
        if (exp <= 0) {
            expSign = '-';
            e = (-exp) + 1;
        } else {
            expSign = '+';
            e = exp - 1;
        }
        if (e <= 9) {
            this.exponent = new char[]{expSign, '0', (char) (e + 48)};
        } else if (e <= 99) {
            this.exponent = new char[]{expSign, (char) ((e / 10) + 48), (char) ((e % 10) + 48)};
        } else {
            int e2 = e % 100;
            this.exponent = new char[]{expSign, (char) ((e / 100) + 48), (char) ((e2 / 10) + 48), (char) ((e2 % 10) + 48)};
        }
    }
}
