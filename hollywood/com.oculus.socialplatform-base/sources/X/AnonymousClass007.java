package X;

/* renamed from: X.007  reason: invalid class name */
public final class AnonymousClass007 {
    public static final Integer A00 = 0;
    public static final Integer A01 = 1;
    public static final Integer A02 = 16;
    public static final Integer A03 = 2;
    public static final Integer A04 = 3;
    public static final Integer A05 = 4;
    public static final Integer A06 = 5;
    public static final Integer A07 = 6;
    public static final Integer A08 = 7;
    public static final Integer A09 = 8;
    public static final Integer A0A = 9;
    public static final Integer[] A0B = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    public static Integer[] A00(int i) {
        Integer[] numArr = new Integer[i];
        int i2 = i;
        if (i > 17) {
            i2 = 17;
            for (int i3 = 17; i3 < i; i3++) {
                numArr[i3] = Integer.valueOf(i3);
            }
        }
        System.arraycopy(A0B, 0, numArr, 0, i2);
        return numArr;
    }
}
