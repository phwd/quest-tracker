package X;

public class AT {
    public Zx A00;
    public AO A01;

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0068 A[ExcHandler: IllegalAccessException | InstantiationException | InvocationTargetException (r1v4 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:14:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AT(X.AQ r6, X.AO r7) {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AT.<init>(X.AQ, X.AO):void");
    }

    public final void A00(AR ar, AN an) {
        AO A012 = Zw.A01(an);
        AO ao = this.A01;
        if (A012 != null && A012.compareTo((Enum) ao) < 0) {
            ao = A012;
        }
        this.A01 = ao;
        this.A00.A42(ar, an);
        this.A01 = A012;
    }
}
