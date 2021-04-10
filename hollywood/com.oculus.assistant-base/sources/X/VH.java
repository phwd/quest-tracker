package X;

public abstract class VH extends AbstractC1012qg {
    public NY A00;
    public C1017ql A01 = new C1017ql(0, null);
    public boolean A02;
    public int A03;

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005e, code lost:
        if (r5 == 5) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0W(java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 155
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VH.A0W(java.lang.String):void");
    }

    public final boolean A0X(NS ns) {
        if ((ns.getMask() & this.A03) != 0) {
            return true;
        }
        return false;
    }

    public VH(int i, NY ny) {
        this.A03 = i;
        this.A00 = ny;
        this.A02 = A0X(NS.WRITE_NUMBERS_AS_STRINGS);
    }

    @Override // X.AbstractC1012qg, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
