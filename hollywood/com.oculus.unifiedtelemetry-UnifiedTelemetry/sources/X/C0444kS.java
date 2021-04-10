package X;

/* renamed from: X.kS  reason: case insensitive filesystem */
public final class C0444kS {
    public static final C0444kS A0B = new C0444kS();
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public C0444kS A04;
    public boolean A05;
    public C0445kT[] A06;
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
        throw new UnsupportedOperationException("Method not decompiled: X.C0444kS.A01(char[], int, int, int):java.lang.String");
    }

    public static C0444kS A00() {
        long currentTimeMillis = System.currentTimeMillis();
        C0444kS kSVar = A0B;
        return new C0444kS(null, true, true, kSVar.A07, kSVar.A06, kSVar.A02, (((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1, kSVar.A01);
    }

    public C0444kS() {
        this.A09 = true;
        this.A0A = true;
        this.A05 = true;
        this.A08 = 0;
        this.A01 = 0;
        this.A07 = new String[64];
        this.A06 = new C0445kT[32];
        this.A00 = 63;
        this.A02 = 0;
        this.A01 = 0;
        this.A03 = 48;
    }

    public C0444kS(C0444kS kSVar, boolean z, boolean z2, String[] strArr, C0445kT[] kTVarArr, int i, int i2, int i3) {
        this.A04 = kSVar;
        this.A09 = z;
        this.A0A = z2;
        this.A07 = strArr;
        this.A06 = kTVarArr;
        this.A02 = i;
        this.A08 = i2;
        int length = strArr.length;
        this.A03 = length - (length >> 2);
        this.A00 = length - 1;
        this.A01 = i3;
        this.A05 = false;
    }
}
