package X;

import java.math.BigInteger;

/* renamed from: X.1Vv  reason: invalid class name */
public final class AnonymousClass1Vv {
    public static final BigInteger A00;
    public static final BigInteger A01;
    public static final BigInteger A02;
    public static final BigInteger A03;
    public static final BigInteger A04;
    public static final BigInteger A05;
    public static final BigInteger A06;
    public static final BigInteger A07;

    static {
        BigInteger valueOf = BigInteger.valueOf(1024);
        A02 = valueOf;
        BigInteger multiply = valueOf.multiply(valueOf);
        A03 = multiply;
        BigInteger bigInteger = A02;
        BigInteger multiply2 = bigInteger.multiply(multiply);
        A01 = multiply2;
        BigInteger multiply3 = bigInteger.multiply(multiply2);
        A05 = multiply3;
        BigInteger multiply4 = bigInteger.multiply(multiply3);
        A04 = multiply4;
        A00 = bigInteger.multiply(multiply4);
        BigInteger multiply5 = BigInteger.valueOf(1024).multiply(BigInteger.valueOf(1152921504606846976L));
        A07 = multiply5;
        A06 = A02.multiply(multiply5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a8, code lost:
        if (r0.length <= 0) goto L_0x0063;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00b0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ff A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(java.io.File r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 317
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Vv.A00(java.io.File):void");
    }
}
