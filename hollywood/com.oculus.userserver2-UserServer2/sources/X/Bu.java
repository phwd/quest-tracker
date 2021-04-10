package X;

public class Bu {
    public Td A00;
    public EnumC0040Bp A01;

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0068, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0068 A[ExcHandler: IllegalAccessException | InstantiationException | InvocationTargetException (r1v4 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:14:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Bu(X.AbstractC0042Br r6, X.EnumC0040Bp r7) {
        /*
        // Method dump skipped, instructions count: 111
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Bu.<init>(X.Br, X.Bp):void");
    }

    public final void A00(Bs bs, EnumC0039Bo bo) {
        EnumC0040Bp A012 = Tc.A01(bo);
        EnumC0040Bp bp = this.A01;
        if (A012 != null && A012.compareTo((Enum) bp) < 0) {
            bp = A012;
        }
        this.A01 = bp;
        this.A00.A2i(bs, bo);
        this.A01 = A012;
    }
}
