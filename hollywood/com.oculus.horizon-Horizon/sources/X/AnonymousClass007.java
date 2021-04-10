package X;

/* renamed from: X.007  reason: invalid class name */
public final class AnonymousClass007 {
    public static final Integer A00 = 0;
    public static final Integer A01 = 1;
    public static final Integer A02 = 10;
    public static final Integer A03 = 11;
    public static final Integer A04 = 12;
    public static final Integer A05 = 13;
    public static final Integer A06 = 14;
    public static final Integer A07 = 15;
    public static final Integer A08 = 16;
    public static final Integer A09 = 17;
    public static final Integer A0A = 18;
    public static final Integer A0B = 19;
    public static final Integer A0C = 2;
    public static final Integer A0D = 3;
    public static final Integer A0E = 4;
    public static final Integer A0F = 5;
    public static final Integer A0G = 6;
    public static final Integer A0H = 7;
    public static final Integer A0I = 8;
    public static final Integer A0J = 9;
    public static final Integer[] A0K = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

    public static Integer[] A00(int i) {
        Integer[] numArr = new Integer[i];
        int i2 = i;
        if (i > 20) {
            i2 = 20;
            for (int i3 = 20; i3 < i; i3++) {
                numArr[i3] = Integer.valueOf(i3);
            }
        }
        System.arraycopy(A0K, 0, numArr, 0, i2);
        return numArr;
    }
}
