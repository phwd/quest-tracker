package X;

/* renamed from: X.07  reason: invalid class name */
public final class AnonymousClass07 {
    public static final Integer A00 = 0;
    public static final Integer A01 = 1;
    public static final Integer A02 = 2;
    public static final Integer A03 = 3;
    public static final Integer A04 = 4;
    public static final Integer A05 = 5;
    public static final Integer A06 = 6;
    public static final Integer A07 = 7;
    public static final Integer A08 = 8;
    public static final Integer A09 = 9;
    public static final Integer[] A0A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static Integer[] A00(int i) {
        Integer[] numArr = new Integer[i];
        int i2 = i;
        if (i > 10) {
            i2 = 10;
            for (int i3 = 10; i3 < i; i3++) {
                numArr[i3] = Integer.valueOf(i3);
            }
        }
        System.arraycopy(A0A, 0, numArr, 0, i2);
        return numArr;
    }
}
