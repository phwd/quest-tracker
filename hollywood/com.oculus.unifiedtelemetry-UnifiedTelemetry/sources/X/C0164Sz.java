package X;

/* renamed from: X.Sz  reason: case insensitive filesystem */
public final class C0164Sz implements AbstractC0132Os {
    public final Lj<?> A00;
    public final OO<?> A01;
    public final lQ<?> A02;
    public final Class<?> A03;
    public final boolean A04;

    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        lQ<?> lQVar2 = this.A02;
        if (lQVar2 != null) {
            if (!lQVar2.equals(lQVar) && (!this.A04 || lQVar2.type != lQVar.rawType)) {
                return null;
            }
        } else if (!this.A03.isAssignableFrom(lQVar.rawType)) {
            return null;
        }
        return new C0163Sy(this.A01, this.A00, hy, lQVar, this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r2 != null) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0164Sz(java.lang.Object r4, X.lQ<?> r5, boolean r6, java.lang.Class<?> r7) {
        /*
            r3 = this;
            r3.<init>()
            boolean r0 = r4 instanceof X.OO
            r2 = 0
            r1 = r2
            if (r0 == 0) goto L_0x000c
            r1 = r4
            X.OO r1 = (X.OO) r1
        L_0x000c:
            r3.A01 = r1
            boolean r0 = r4 instanceof X.Lj
            if (r0 == 0) goto L_0x0015
            r2 = r4
            X.Lj r2 = (X.Lj) r2
        L_0x0015:
            r3.A00 = r2
            if (r1 != 0) goto L_0x001c
            r0 = 0
            if (r2 == 0) goto L_0x001d
        L_0x001c:
            r0 = 1
        L_0x001d:
            X.Rn.A00(r0)
            r3.A02 = r5
            r3.A04 = r6
            r3.A03 = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0164Sz.<init>(java.lang.Object, X.lQ, boolean, java.lang.Class):void");
    }
}
