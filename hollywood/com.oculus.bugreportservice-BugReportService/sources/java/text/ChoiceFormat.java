package java.text;

import java.io.ObjectInputStream;
import java.util.Arrays;

public class ChoiceFormat extends NumberFormat {
    private static final long serialVersionUID = 1795184449645032964L;
    private String[] choiceFormats;
    private double[] choiceLimits;

    public void applyPattern(String str) {
        int i;
        StringBuffer[] stringBufferArr = new StringBuffer[2];
        for (int i2 = 0; i2 < stringBufferArr.length; i2++) {
            stringBufferArr[i2] = new StringBuffer();
        }
        double d = 0.0d;
        double d2 = Double.NaN;
        int i3 = 0;
        boolean z = false;
        String[] strArr = new String[30];
        double[] dArr = new double[30];
        int i4 = 0;
        char c = 0;
        while (i4 < str.length()) {
            char charAt = str.charAt(i4);
            if (charAt == '\'') {
                int i5 = i4 + 1;
                if (i5 >= str.length() || str.charAt(i5) != charAt) {
                    z = !z;
                } else {
                    stringBufferArr[c].append(charAt);
                    i4 = i5;
                }
            } else if (z) {
                stringBufferArr[c].append(charAt);
            } else if (charAt == '<' || charAt == '#' || charAt == 8804) {
                if (stringBufferArr[0].length() != 0) {
                    try {
                        String stringBuffer = stringBufferArr[0].toString();
                        if (stringBuffer.equals("∞")) {
                            d = Double.POSITIVE_INFINITY;
                        } else if (stringBuffer.equals("-∞")) {
                            d = Double.NEGATIVE_INFINITY;
                        } else {
                            d = Double.parseDouble(stringBufferArr[0].toString());
                        }
                        if (!(charAt != '<' || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY)) {
                            d = nextDouble(d);
                        }
                        if (d > d2) {
                            stringBufferArr[0].setLength(0);
                            i = 1;
                            c = 1;
                            i4 += i;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } catch (Exception unused) {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } else if (charAt == '|') {
                if (i3 == dArr.length) {
                    dArr = doubleArraySize(dArr);
                    strArr = doubleArraySize(strArr);
                }
                dArr[i3] = d;
                strArr[i3] = stringBufferArr[1].toString();
                i3++;
                stringBufferArr[1].setLength(0);
                d2 = d;
                c = 0;
            } else {
                stringBufferArr[c].append(charAt);
            }
            i = 1;
            i4 += i;
        }
        if (c == 1) {
            if (i3 == dArr.length) {
                dArr = doubleArraySize(dArr);
                strArr = doubleArraySize(strArr);
            }
            dArr[i3] = d;
            strArr[i3] = stringBufferArr[1].toString();
            i3++;
        }
        this.choiceLimits = new double[i3];
        System.arraycopy(dArr, 0, this.choiceLimits, 0, i3);
        this.choiceFormats = new String[i3];
        System.arraycopy(strArr, 0, this.choiceFormats, 0, i3);
    }

    public ChoiceFormat(String str) {
        applyPattern(str);
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format((double) j, stringBuffer, fieldPosition);
    }

    @Override // java.text.NumberFormat
    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        int i = 0;
        while (true) {
            double[] dArr = this.choiceLimits;
            if (i >= dArr.length || d < dArr[i]) {
                int i2 = i - 1;
            } else {
                i++;
            }
        }
        int i22 = i - 1;
        if (i22 < 0) {
            i22 = 0;
        }
        stringBuffer.append(this.choiceFormats[i22]);
        return stringBuffer;
    }

    @Override // java.text.NumberFormat
    public Number parse(String str, ParsePosition parsePosition) {
        int i = parsePosition.index;
        double d = Double.NaN;
        int i2 = i;
        int i3 = 0;
        while (true) {
            String[] strArr = this.choiceFormats;
            if (i3 >= strArr.length) {
                break;
            }
            String str2 = strArr[i3];
            if (str.regionMatches(i, str2, 0, str2.length())) {
                parsePosition.index = str2.length() + i;
                double d2 = this.choiceLimits[i3];
                int i4 = parsePosition.index;
                if (i4 > i2) {
                    d = d2;
                    if (i4 == str.length()) {
                        i2 = i4;
                        break;
                    }
                    i2 = i4;
                } else {
                    continue;
                }
            }
            i3++;
        }
        parsePosition.index = i2;
        if (parsePosition.index == i) {
            parsePosition.errorIndex = i2;
        }
        return new Double(d);
    }

    public static final double nextDouble(double d) {
        return nextDouble(d, true);
    }

    @Override // java.text.Format, java.text.NumberFormat
    public Object clone() {
        ChoiceFormat choiceFormat = (ChoiceFormat) super.clone();
        choiceFormat.choiceLimits = (double[]) this.choiceLimits.clone();
        choiceFormat.choiceFormats = (String[]) this.choiceFormats.clone();
        return choiceFormat;
    }

    @Override // java.text.NumberFormat
    public int hashCode() {
        int length = this.choiceLimits.length;
        String[] strArr = this.choiceFormats;
        return strArr.length > 0 ? length ^ strArr[strArr.length - 1].hashCode() : length;
    }

    @Override // java.text.NumberFormat
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (ChoiceFormat.class != obj.getClass()) {
            return false;
        }
        ChoiceFormat choiceFormat = (ChoiceFormat) obj;
        return Arrays.equals(this.choiceLimits, choiceFormat.choiceLimits) && Arrays.equals(this.choiceFormats, choiceFormat.choiceFormats);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    public static double nextDouble(double d, boolean z) {
        if (Double.isNaN(d)) {
            return d;
        }
        if (d == 0.0d) {
            double longBitsToDouble = Double.longBitsToDouble(1);
            return z ? longBitsToDouble : -longBitsToDouble;
        }
        long doubleToLongBits = Double.doubleToLongBits(d);
        long j = Long.MAX_VALUE & doubleToLongBits;
        if ((doubleToLongBits > 0) != z) {
            j--;
        } else if (j != 9218868437227405312L) {
            j++;
        }
        return Double.longBitsToDouble((doubleToLongBits & Long.MIN_VALUE) | j);
    }

    private static double[] doubleArraySize(double[] dArr) {
        int length = dArr.length;
        double[] dArr2 = new double[(length * 2)];
        System.arraycopy(dArr, 0, dArr2, 0, length);
        return dArr2;
    }

    private String[] doubleArraySize(String[] strArr) {
        int length = strArr.length;
        String[] strArr2 = new String[(length * 2)];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        return strArr2;
    }
}
