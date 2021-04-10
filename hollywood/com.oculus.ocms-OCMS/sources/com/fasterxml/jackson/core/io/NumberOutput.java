package com.fasterxml.jackson.core.io;

import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.ipc.activity.ActivityConstants;
import com.facebook.stetho.dumpapp.Framer;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.util.constants.OculusConstants;

public final class NumberOutput {
    private static int BILLION = 1000000000;
    static final char[] FULL_TRIPLETS = new char[4000];
    static final byte[] FULL_TRIPLETS_B = new byte[4000];
    static final char[] LEADING_TRIPLETS = new char[4000];
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    private static final char NULL_CHAR = 0;
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static long TEN_BILLION_L = 10000000000L;
    private static long THOUSAND_L = 1000;
    static final String[] sSmallIntStrs = {OculusConstants.DEFAULT_ENTERPRISE_USER_ID, ActivityConstants.Extras.WATCH_FEED_INJECTION, "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final String[] sSmallIntStrs2 = {ErrorReportingConstants.ANR_DEFAULT_RECOVERY_DELAY_VAL, "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    static {
        int i = 0;
        int i2 = 0;
        while (i < 10) {
            char c = (char) (i + 48);
            char c2 = i == 0 ? 0 : c;
            int i3 = i2;
            int i4 = 0;
            while (i4 < 10) {
                char c3 = (char) (i4 + 48);
                char c4 = (i == 0 && i4 == 0) ? 0 : c3;
                int i5 = i3;
                for (int i6 = 0; i6 < 10; i6++) {
                    char c5 = (char) (i6 + 48);
                    char[] cArr = LEADING_TRIPLETS;
                    cArr[i5] = c2;
                    int i7 = i5 + 1;
                    cArr[i7] = c4;
                    int i8 = i5 + 2;
                    cArr[i8] = c5;
                    char[] cArr2 = FULL_TRIPLETS;
                    cArr2[i5] = c;
                    cArr2[i7] = c3;
                    cArr2[i8] = c5;
                    i5 += 4;
                }
                i4++;
                i3 = i5;
            }
            i++;
            i2 = i3;
        }
        for (int i9 = 0; i9 < 4000; i9++) {
            FULL_TRIPLETS_B[i9] = (byte) FULL_TRIPLETS[i9];
        }
    }

    public static int outputInt(int i, char[] cArr, int i2) {
        int i3;
        int i4;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong((long) i, cArr, i2);
            }
            cArr[i2] = '-';
            i = -i;
            i2++;
        }
        if (i >= MILLION) {
            boolean z = i >= BILLION;
            if (z) {
                int i5 = BILLION;
                i -= i5;
                if (i >= i5) {
                    i -= i5;
                    i3 = i2 + 1;
                    cArr[i2] = '2';
                } else {
                    i3 = i2 + 1;
                    cArr[i2] = '1';
                }
            } else {
                i3 = i2;
            }
            int i6 = i / 1000;
            int i7 = i - (i6 * 1000);
            int i8 = i6 / 1000;
            int i9 = i6 - (i8 * 1000);
            if (z) {
                i4 = outputFullTriplet(i8, cArr, i3);
            } else {
                i4 = outputLeadingTriplet(i8, cArr, i3);
            }
            return outputFullTriplet(i7, cArr, outputFullTriplet(i9, cArr, i4));
        } else if (i >= 1000) {
            int i10 = i / 1000;
            return outputFullTriplet(i - (i10 * 1000), cArr, outputLeadingTriplet(i10, cArr, i2));
        } else if (i >= 10) {
            return outputLeadingTriplet(i, cArr, i2);
        } else {
            int i11 = i2 + 1;
            cArr[i2] = (char) (i + 48);
            return i11;
        }
    }

    public static int outputInt(int i, byte[] bArr, int i2) {
        int i3;
        int i4;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong((long) i, bArr, i2);
            }
            bArr[i2] = Framer.STDIN_FRAME_PREFIX;
            i = -i;
            i2++;
        }
        if (i >= MILLION) {
            boolean z = i >= BILLION;
            if (z) {
                int i5 = BILLION;
                i -= i5;
                if (i >= i5) {
                    i -= i5;
                    i3 = i2 + 1;
                    bArr[i2] = Framer.STDERR_FRAME_PREFIX;
                } else {
                    i3 = i2 + 1;
                    bArr[i2] = Framer.STDOUT_FRAME_PREFIX;
                }
            } else {
                i3 = i2;
            }
            int i6 = i / 1000;
            int i7 = i - (i6 * 1000);
            int i8 = i6 / 1000;
            int i9 = i6 - (i8 * 1000);
            if (z) {
                i4 = outputFullTriplet(i8, bArr, i3);
            } else {
                i4 = outputLeadingTriplet(i8, bArr, i3);
            }
            return outputFullTriplet(i7, bArr, outputFullTriplet(i9, bArr, i4));
        } else if (i >= 1000) {
            int i10 = i / 1000;
            return outputFullTriplet(i - (i10 * 1000), bArr, outputLeadingTriplet(i10, bArr, i2));
        } else if (i >= 10) {
            return outputLeadingTriplet(i, bArr, i2);
        } else {
            int i11 = i2 + 1;
            bArr[i2] = (byte) (i + 48);
            return i11;
        }
    }

    public static int outputLong(long j, char[] cArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, cArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, length, cArr, i);
                return i + length;
            }
            cArr[i] = '-';
            j = -j;
            i++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, cArr, i);
        }
        int calcLongStrLength = calcLongStrLength(j) + i;
        int i2 = calcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i2 -= 3;
            long j2 = THOUSAND_L;
            long j3 = j / j2;
            outputFullTriplet((int) (j - (j2 * j3)), cArr, i2);
            j = j3;
        }
        int i3 = (int) j;
        while (i3 >= 1000) {
            i2 -= 3;
            int i4 = i3 / 1000;
            outputFullTriplet(i3 - (i4 * 1000), cArr, i2);
            i3 = i4;
        }
        outputLeadingTriplet(i3, cArr, i);
        return calcLongStrLength;
    }

    public static int outputLong(long j, byte[] bArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, bArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                int i2 = 0;
                while (i2 < length) {
                    bArr[i] = (byte) SMALLEST_LONG.charAt(i2);
                    i2++;
                    i++;
                }
                return i;
            }
            bArr[i] = Framer.STDIN_FRAME_PREFIX;
            j = -j;
            i++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, bArr, i);
        }
        int calcLongStrLength = calcLongStrLength(j) + i;
        int i3 = calcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i3 -= 3;
            long j2 = THOUSAND_L;
            long j3 = j / j2;
            outputFullTriplet((int) (j - (j2 * j3)), bArr, i3);
            j = j3;
        }
        int i4 = (int) j;
        while (i4 >= 1000) {
            i3 -= 3;
            int i5 = i4 / 1000;
            outputFullTriplet(i4 - (i5 * 1000), bArr, i3);
            i4 = i5;
        }
        outputLeadingTriplet(i4, bArr, i);
        return calcLongStrLength;
    }

    public static String toString(int i) {
        String[] strArr = sSmallIntStrs;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = sSmallIntStrs2;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    public static String toString(long j) {
        if (j > 2147483647L || j < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Long.toString(j);
        }
        return toString((int) j);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    private static int outputLeadingTriplet(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEADING_TRIPLETS[i3];
        if (c != 0) {
            cArr[i2] = c;
            i2++;
        }
        int i5 = i4 + 1;
        char c2 = LEADING_TRIPLETS[i4];
        if (c2 != 0) {
            cArr[i2] = c2;
            i2++;
        }
        int i6 = i2 + 1;
        cArr[i2] = LEADING_TRIPLETS[i5];
        return i6;
    }

    private static int outputLeadingTriplet(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEADING_TRIPLETS[i3];
        if (c != 0) {
            bArr[i2] = (byte) c;
            i2++;
        }
        int i5 = i4 + 1;
        char c2 = LEADING_TRIPLETS[i4];
        if (c2 != 0) {
            bArr[i2] = (byte) c2;
            i2++;
        }
        int i6 = i2 + 1;
        bArr[i2] = (byte) LEADING_TRIPLETS[i5];
        return i6;
    }

    private static int outputFullTriplet(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        char[] cArr2 = FULL_TRIPLETS;
        int i5 = i3 + 1;
        cArr[i2] = cArr2[i3];
        int i6 = i4 + 1;
        cArr[i4] = cArr2[i5];
        int i7 = i6 + 1;
        cArr[i6] = cArr2[i5 + 1];
        return i7;
    }

    private static int outputFullTriplet(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        byte[] bArr2 = FULL_TRIPLETS_B;
        int i5 = i3 + 1;
        bArr[i2] = bArr2[i3];
        int i6 = i4 + 1;
        bArr[i4] = bArr2[i5];
        int i7 = i6 + 1;
        bArr[i6] = bArr2[i5 + 1];
        return i7;
    }

    private static int calcLongStrLength(long j) {
        int i = 10;
        for (long j2 = TEN_BILLION_L; j >= j2 && i != 19; j2 = (j2 << 1) + (j2 << 3)) {
            i++;
        }
        return i;
    }
}
