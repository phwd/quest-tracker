package sun.util.calendar;

public class CalendarUtils {
    public static final boolean isGregorianLeapYear(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    public static final boolean isJulianLeapYear(int i) {
        return i % 4 == 0;
    }

    public static final long floorDivide(long j, long j2) {
        return j >= 0 ? j / j2 : ((j + 1) / j2) - 1;
    }

    public static final int floorDivide(int i, int i2) {
        return i >= 0 ? i / i2 : ((i + 1) / i2) - 1;
    }

    public static final int floorDivide(int i, int i2, int[] iArr) {
        if (i >= 0) {
            iArr[0] = i % i2;
            return i / i2;
        }
        int i3 = ((i + 1) / i2) - 1;
        iArr[0] = i - (i2 * i3);
        return i3;
    }

    public static final long mod(long j, long j2) {
        return j - (j2 * floorDivide(j, j2));
    }

    public static final int mod(int i, int i2) {
        return i - (i2 * floorDivide(i, i2));
    }

    public static final StringBuilder sprintf0d(StringBuilder sb, int i, int i2) {
        long j = (long) i;
        if (j < 0) {
            sb.append('-');
            j = -j;
            i2--;
        }
        int i3 = 10;
        for (int i4 = 2; i4 < i2; i4++) {
            i3 *= 10;
        }
        for (int i5 = 1; i5 < i2 && j < ((long) i3); i5++) {
            sb.append('0');
            i3 /= 10;
        }
        sb.append(j);
        return sb;
    }

    public static final StringBuffer sprintf0d(StringBuffer stringBuffer, int i, int i2) {
        long j = (long) i;
        if (j < 0) {
            stringBuffer.append('-');
            j = -j;
            i2--;
        }
        int i3 = 10;
        for (int i4 = 2; i4 < i2; i4++) {
            i3 *= 10;
        }
        for (int i5 = 1; i5 < i2 && j < ((long) i3); i5++) {
            stringBuffer.append('0');
            i3 /= 10;
        }
        stringBuffer.append(j);
        return stringBuffer;
    }
}
