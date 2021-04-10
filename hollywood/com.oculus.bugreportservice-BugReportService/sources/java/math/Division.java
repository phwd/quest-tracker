package java.math;

/* access modifiers changed from: package-private */
public class Division {
    static int divideArrayByInt(int[] iArr, int[] iArr2, int i, int i2) {
        long j;
        long j2;
        long j3 = ((long) i2) & 4294967295L;
        long j4 = 0;
        for (int i3 = i - 1; i3 >= 0; i3--) {
            long j5 = (j4 << 32) | (((long) iArr2[i3]) & 4294967295L);
            if (j5 >= 0) {
                long j6 = j5 / j3;
                j4 = j5 % j3;
                j = j6;
            } else {
                long j7 = j5 >>> 1;
                long j8 = (long) (i2 >>> 1);
                j = j7 / j8;
                long j9 = ((j7 % j8) << 1) + (j5 & 1);
                if ((i2 & 1) != 0) {
                    if (j <= j9) {
                        j9 -= j;
                    } else {
                        if (j - j9 <= j3) {
                            j9 += j3 - j;
                            j2 = j - 1;
                        } else {
                            j9 += (j3 << 1) - j;
                            j2 = j - 2;
                        }
                        j = j2;
                    }
                }
                j4 = j9;
            }
            iArr[i3] = (int) (j & 4294967295L);
        }
        return (int) j4;
    }
}
