package X;

/* renamed from: X.0ok  reason: invalid class name and case insensitive filesystem */
public final class C03890ok {
    public static final C03890ok A0B = new C03890ok();
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public C03890ok A04;
    public boolean A05;
    public C03880oj[] A06;
    public String[] A07;
    public final int A08;
    public final boolean A09;
    public final boolean A0A;

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (r2 == r20) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String A01(char[] r18, int r19, int r20, int r21) {
        /*
        // Method dump skipped, instructions count: 491
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03890ok.A01(char[], int, int, int):java.lang.String");
    }

    public static C03890ok A00() {
        long currentTimeMillis = System.currentTimeMillis();
        C03890ok r0 = A0B;
        return new C03890ok(null, true, true, r0.A07, r0.A06, r0.A02, (((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1, r0.A01);
    }

    public C03890ok() {
        this.A09 = true;
        this.A0A = true;
        this.A05 = true;
        this.A08 = 0;
        this.A01 = 0;
        this.A07 = new String[64];
        this.A06 = new C03880oj[32];
        this.A00 = 63;
        this.A02 = 0;
        this.A01 = 0;
        this.A03 = 48;
    }

    public C03890ok(C03890ok r3, boolean z, boolean z2, String[] strArr, C03880oj[] r7, int i, int i2, int i3) {
        this.A04 = r3;
        this.A09 = z;
        this.A0A = z2;
        this.A07 = strArr;
        this.A06 = r7;
        this.A02 = i;
        this.A08 = i2;
        int length = strArr.length;
        this.A03 = length - (length >> 2);
        this.A00 = length - 1;
        this.A01 = i3;
        this.A05 = false;
    }
}
