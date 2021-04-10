package X;

public final class JP {
    public static JT A00;
    public static final JU A01;
    public static final JT A02;
    public static final JT A03 = new C0948pQ();

    static {
        C0949pR pRVar = new C0949pR();
        A02 = pRVar;
        A01 = new C0951pT(pRVar);
    }

    public static synchronized JT A00() {
        JT jt;
        synchronized (JP.class) {
            jt = A00;
            if (jt == null) {
                throw new IllegalStateException();
            }
        }
        return jt;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void A01(android.content.Context r16) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: X.JP.A01(android.content.Context):void");
    }
}
