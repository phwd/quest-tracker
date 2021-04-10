package sun.misc;

import java.util.Arrays;
import sun.misc.FloatingDecimal;

public class FormattedFloatingDecimal {
    private static final ThreadLocal threadLocalCharBuffer = new ThreadLocal() {
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

    public static FormattedFloatingDecimal valueOf(double d, int i, Form form) {
        return new FormattedFloatingDecimal(i, form, FloatingDecimal.getBinaryToASCIIConverter(d, form == Form.COMPATIBLE));
    }

    private static char[] getBuffer() {
        return (char[]) threadLocalCharBuffer.get();
    }

    private FormattedFloatingDecimal(int i, Form form, FloatingDecimal.BinaryToASCIIConverter binaryToASCIIConverter) {
        if (binaryToASCIIConverter.isExceptional()) {
            this.mantissa = binaryToASCIIConverter.toJavaFormatString().toCharArray();
            this.exponent = null;
            return;
        }
        char[] buffer = getBuffer();
        int digits = binaryToASCIIConverter.getDigits(buffer);
        int decimalExponent = binaryToASCIIConverter.getDecimalExponent();
        boolean isNegative = binaryToASCIIConverter.isNegative();
        int i2 = AnonymousClass2.$SwitchMap$sun$misc$FormattedFloatingDecimal$Form[form.ordinal()];
        if (i2 == 1) {
            this.decExponentRounded = decimalExponent;
            fillCompatible(i, buffer, digits, decimalExponent, isNegative);
        } else if (i2 == 2) {
            int applyPrecision = applyPrecision(decimalExponent, buffer, digits, decimalExponent + i);
            fillDecimal(i, buffer, digits, applyPrecision, isNegative);
            this.decExponentRounded = applyPrecision;
        } else if (i2 == 3) {
            int applyPrecision2 = applyPrecision(decimalExponent, buffer, digits, i + 1);
            fillScientific(i, buffer, digits, applyPrecision2, isNegative);
            this.decExponentRounded = applyPrecision2;
        } else if (i2 == 4) {
            int applyPrecision3 = applyPrecision(decimalExponent, buffer, digits, i);
            int i3 = applyPrecision3 - 1;
            if (i3 < -4 || i3 >= i) {
                fillScientific(i - 1, buffer, digits, applyPrecision3, isNegative);
            } else {
                fillDecimal(i - applyPrecision3, buffer, digits, applyPrecision3, isNegative);
            }
            this.decExponentRounded = applyPrecision3;
        }
    }

    /* renamed from: sun.misc.FormattedFloatingDecimal$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$sun$misc$FormattedFloatingDecimal$Form = new int[Form.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                sun.misc.FormattedFloatingDecimal$Form[] r0 = sun.misc.FormattedFloatingDecimal.Form.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                sun.misc.FormattedFloatingDecimal.AnonymousClass2.$SwitchMap$sun$misc$FormattedFloatingDecimal$Form = r0
                int[] r0 = sun.misc.FormattedFloatingDecimal.AnonymousClass2.$SwitchMap$sun$misc$FormattedFloatingDecimal$Form     // Catch:{ NoSuchFieldError -> 0x0014 }
                sun.misc.FormattedFloatingDecimal$Form r1 = sun.misc.FormattedFloatingDecimal.Form.COMPATIBLE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = sun.misc.FormattedFloatingDecimal.AnonymousClass2.$SwitchMap$sun$misc$FormattedFloatingDecimal$Form     // Catch:{ NoSuchFieldError -> 0x001f }
                sun.misc.FormattedFloatingDecimal$Form r1 = sun.misc.FormattedFloatingDecimal.Form.DECIMAL_FLOAT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = sun.misc.FormattedFloatingDecimal.AnonymousClass2.$SwitchMap$sun$misc$FormattedFloatingDecimal$Form     // Catch:{ NoSuchFieldError -> 0x002a }
                sun.misc.FormattedFloatingDecimal$Form r1 = sun.misc.FormattedFloatingDecimal.Form.SCIENTIFIC     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = sun.misc.FormattedFloatingDecimal.AnonymousClass2.$SwitchMap$sun$misc$FormattedFloatingDecimal$Form     // Catch:{ NoSuchFieldError -> 0x0035 }
                sun.misc.FormattedFloatingDecimal$Form r1 = sun.misc.FormattedFloatingDecimal.Form.GENERAL     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.misc.FormattedFloatingDecimal.AnonymousClass2.<clinit>():void");
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

    private static int applyPrecision(int i, char[] cArr, int i2, int i3) {
        if (i3 < i2 && i3 >= 0) {
            if (i3 == 0) {
                if (cArr[0] >= '5') {
                    cArr[0] = '1';
                    Arrays.fill(cArr, 1, i2, '0');
                } else {
                    Arrays.fill(cArr, 0, i2, '0');
                    return i;
                }
            } else if (cArr[i3] >= '5') {
                int i4 = i3 - 1;
                char c = cArr[i4];
                if (c == '9') {
                    while (c == '9' && i4 > 0) {
                        i4--;
                        c = cArr[i4];
                    }
                    if (c == '9') {
                        cArr[0] = '1';
                        Arrays.fill(cArr, 1, i2, '0');
                    }
                }
                cArr[i4] = (char) (c + 1);
                Arrays.fill(cArr, i4 + 1, i2, '0');
            } else {
                Arrays.fill(cArr, i3, i2, '0');
            }
            return i + 1;
        }
        return i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r12v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private void fillCompatible(int i, char[] cArr, int i2, int i3, boolean z) {
        int i4;
        int i5 = 0;
        if (i3 <= 0 || i3 >= 8) {
            if (i3 > 0 || i3 <= -3) {
                if (i2 > 1) {
                    this.mantissa = create(z, i2 + 1);
                    char[] cArr2 = this.mantissa;
                    cArr2[z] = cArr[0];
                    cArr2[z + 1] = '.';
                    System.arraycopy((Object) cArr, 1, (Object) cArr2, z + 2, i2 - 1);
                } else {
                    this.mantissa = create(z, 3);
                    char[] cArr3 = this.mantissa;
                    cArr3[z ? 1 : 0] = cArr[0];
                    cArr3[(z ? 1 : 0) + 1] = '.';
                    cArr3[z + 2] = '0';
                }
                boolean z2 = i3 <= 0;
                if (z2) {
                    i4 = (-i3) + 1;
                    i5 = 1;
                } else {
                    i4 = i3 - 1;
                }
                if (i4 <= 9) {
                    this.exponent = create(z2, 1);
                    this.exponent[i5] = (char) (i4 + 48);
                } else if (i4 <= 99) {
                    this.exponent = create(z2, 2);
                    char[] cArr4 = this.exponent;
                    cArr4[i5] = (char) ((i4 / 10) + 48);
                    cArr4[i5 + 1] = (char) ((i4 % 10) + 48);
                } else {
                    this.exponent = create(z2, 3);
                    char[] cArr5 = this.exponent;
                    cArr5[i5] = (char) ((i4 / 100) + 48);
                    int i6 = i4 % 100;
                    cArr5[i5 + 1] = (char) ((i6 / 10) + 48);
                    cArr5[i5 + 2] = (char) ((i6 % 10) + 48);
                }
            } else {
                int max = Math.max(0, Math.min(-i3, i));
                int max2 = Math.max(0, Math.min(i2, i + i3));
                if (max > 0) {
                    this.mantissa = create(z, max + 2 + max2);
                    char[] cArr6 = this.mantissa;
                    cArr6[z] = '0';
                    cArr6[z + 1] = '.';
                    int i7 = z + 2;
                    int i8 = max + i7;
                    Arrays.fill(cArr6, i7, i8, '0');
                    if (max2 > 0) {
                        System.arraycopy((Object) cArr, 0, (Object) this.mantissa, i8, max2);
                    }
                } else if (max2 > 0) {
                    this.mantissa = create(z, max + 2 + max2);
                    char[] cArr7 = this.mantissa;
                    cArr7[z] = '0';
                    cArr7[z + 1] = '.';
                    System.arraycopy((Object) cArr, 0, (Object) cArr7, z + 2, max2);
                } else {
                    this.mantissa = create(z, 1);
                    this.mantissa[z] = '0';
                }
            }
        } else if (i2 < i3) {
            int i9 = i3 - i2;
            this.mantissa = create(z, i2 + i9 + 2);
            System.arraycopy((Object) cArr, 0, (Object) this.mantissa, (int) z, i2);
            int i10 = z + i2;
            int i11 = i9 + i10;
            Arrays.fill(this.mantissa, i10, i11, '0');
            char[] cArr8 = this.mantissa;
            cArr8[i11] = '.';
            cArr8[i11 + 1] = '0';
        } else if (i3 < i2) {
            int min = Math.min(i2 - i3, i);
            this.mantissa = create(z, i3 + 1 + min);
            System.arraycopy((Object) cArr, 0, (Object) this.mantissa, (int) z, i3);
            char[] cArr9 = this.mantissa;
            int i12 = z + i3;
            cArr9[i12] = '.';
            System.arraycopy((Object) cArr, i3, (Object) cArr9, i12 + 1, min);
        } else {
            this.mantissa = create(z, i2 + 2);
            System.arraycopy((Object) cArr, 0, (Object) this.mantissa, (int) z, i2);
            char[] cArr10 = this.mantissa;
            int i13 = z + i2;
            cArr10[i13] = '.';
            cArr10[i13 + 1] = '0';
        }
    }

    private static char[] create(boolean z, int i) {
        if (!z) {
            return new char[i];
        }
        char[] cArr = new char[(i + 1)];
        cArr[0] = '-';
        return cArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private void fillDecimal(int i, char[] cArr, int i2, int i3, boolean z) {
        if (i3 > 0) {
            if (i2 < i3) {
                this.mantissa = create(z, i3);
                System.arraycopy((Object) cArr, 0, (Object) this.mantissa, (int) z, i2);
                Arrays.fill(this.mantissa, i2 + z, z + i3, '0');
                return;
            }
            int min = Math.min(i2 - i3, i);
            this.mantissa = create(z, (min > 0 ? min + 1 : 0) + i3);
            System.arraycopy((Object) cArr, 0, (Object) this.mantissa, (int) z, i3);
            if (min > 0) {
                char[] cArr2 = this.mantissa;
                int i4 = z + i3;
                cArr2[i4] = '.';
                System.arraycopy((Object) cArr, i3, (Object) cArr2, i4 + 1, min);
            }
        } else if (i3 <= 0) {
            int max = Math.max(0, Math.min(-i3, i));
            int max2 = Math.max(0, Math.min(i2, i + i3));
            if (max > 0) {
                this.mantissa = create(z, max + 2 + max2);
                char[] cArr3 = this.mantissa;
                cArr3[z] = '0';
                cArr3[z + 1] = '.';
                int i5 = z + 2;
                int i6 = max + i5;
                Arrays.fill(cArr3, i5, i6, '0');
                if (max2 > 0) {
                    System.arraycopy((Object) cArr, 0, (Object) this.mantissa, i6, max2);
                }
            } else if (max2 > 0) {
                this.mantissa = create(z, max + 2 + max2);
                char[] cArr4 = this.mantissa;
                cArr4[z] = '0';
                cArr4[(z ? 1 : 0) + 1] = '.';
                System.arraycopy((Object) cArr, 0, (Object) cArr4, z + 2, max2);
            } else {
                this.mantissa = create(z, 1);
                this.mantissa[z ? 1 : 0] = '0';
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    private void fillScientific(int i, char[] cArr, int i2, int i3, boolean z) {
        int i4;
        char c;
        int max = Math.max(0, Math.min(i2 - 1, i));
        if (max > 0) {
            this.mantissa = create(z, max + 2);
            char[] cArr2 = this.mantissa;
            cArr2[z] = cArr[0];
            cArr2[(z ? 1 : 0) + 1] = '.';
            System.arraycopy((Object) cArr, 1, (Object) cArr2, z + 2, max);
        } else {
            this.mantissa = create(z, 1);
            this.mantissa[z ? 1 : 0] = cArr[0];
        }
        if (i3 <= 0) {
            c = '-';
            i4 = (-i3) + 1;
        } else {
            c = '+';
            i4 = i3 - 1;
        }
        if (i4 <= 9) {
            this.exponent = new char[]{c, '0', (char) (i4 + 48)};
        } else if (i4 <= 99) {
            this.exponent = new char[]{c, (char) ((i4 / 10) + 48), (char) ((i4 % 10) + 48)};
        } else {
            int i5 = i4 % 100;
            this.exponent = new char[]{c, (char) ((i4 / 100) + 48), (char) ((i5 / 10) + 48), (char) ((i5 % 10) + 48)};
        }
    }
}
