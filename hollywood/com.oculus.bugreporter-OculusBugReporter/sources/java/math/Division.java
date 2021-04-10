package java.math;

/* access modifiers changed from: package-private */
public class Division {
    Division() {
    }

    static int divideArrayByInt(int[] quotient, int[] dividend, int dividendLength, int divisor) {
        long quot;
        long rem = 0;
        long bLong = ((long) divisor) & 4294967295L;
        for (int i = dividendLength - 1; i >= 0; i--) {
            long temp = (rem << 32) | (((long) dividend[i]) & 4294967295L);
            if (temp >= 0) {
                rem = temp % bLong;
                quot = temp / bLong;
            } else {
                long aPos = temp >>> 1;
                long bPos = (long) (divisor >>> 1);
                quot = aPos / bPos;
                long rem2 = ((aPos % bPos) << 1) + (temp & 1);
                if ((divisor & 1) == 0) {
                    rem = rem2;
                } else if (quot <= rem2) {
                    rem = rem2 - quot;
                } else if (quot - rem2 <= bLong) {
                    long rem3 = rem2 + (bLong - quot);
                    quot--;
                    rem = rem3;
                } else {
                    long rem4 = rem2 + ((bLong << 1) - quot);
                    quot -= 2;
                    rem = rem4;
                }
            }
            quotient[i] = (int) (quot & 4294967295L);
        }
        return (int) rem;
    }
}
