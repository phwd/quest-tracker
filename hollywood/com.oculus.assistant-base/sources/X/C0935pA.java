package X;

/* renamed from: X.pA  reason: case insensitive filesystem */
public final class C0935pA {
    public final AnonymousClass6J A00;

    public final void A00(Object obj, String str, int i) {
        C0847jr.A01((C0847jr) obj, str, Integer.valueOf(i));
    }

    public final void A01(Object obj, String str, long j) {
        C0847jr.A01((C0847jr) obj, str, Long.valueOf(j));
    }

    public final void A02(Object obj, String str, String[] strArr) {
        C0846jq A04 = ((C0847jr) obj).A04(str);
        int length = strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = Boolean.parseBoolean(strArr[i]);
        }
        for (int i2 = 0; i2 < length; i2++) {
            C0846jq.A01(A04, Boolean.valueOf(zArr[i2]));
        }
    }

    public final void A03(Object obj, String str, String[] strArr) {
        C0846jq A04 = ((C0847jr) obj).A04(str);
        int length = strArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = Double.parseDouble(strArr[i]);
        }
        for (int i2 = 0; i2 < length; i2++) {
            C0846jq.A01(A04, Double.valueOf(dArr[i2]));
        }
    }

    public final void A04(Object obj, String str, String[] strArr) {
        C0846jq A04 = ((C0847jr) obj).A04(str);
        int length = strArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = Long.parseLong(strArr[i]);
        }
        for (int i2 = 0; i2 < length; i2++) {
            C0846jq.A01(A04, Long.valueOf(jArr[i2]));
        }
    }

    public final void A05(Object obj, String str, String[] strArr) {
        C0846jq A04 = ((C0847jr) obj).A04(str);
        for (String str2 : strArr) {
            C0846jq.A01(A04, str2);
        }
    }

    public C0935pA(AnonymousClass6J r1) {
        this.A00 = r1;
    }
}
