package X;

/* renamed from: X.gr  reason: case insensitive filesystem */
public final class C0765gr implements AbstractC0105Aj {
    public final /* synthetic */ C00658s A00;

    public C0765gr(C00658s r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C0776h5 h5Var = (C0776h5) ak.A2L();
        String[] strArr = h5Var.A01;
        C00658s r4 = this.A00;
        boolean[] zArr = h5Var.A02;
        r4.A0E = zArr;
        String str = h5Var.A00;
        if (!str.isEmpty()) {
            int i = r4.A04;
            int length = strArr.length;
            r4.A04 = i + length + 1;
            r4.A0C.add(str.toLowerCase());
            for (String str2 : strArr) {
                r4.A0C.add(str2.toLowerCase());
            }
            if (length < 2) {
                r4.A00 += 2 - length;
            }
            int i2 = 0;
            for (boolean z : zArr) {
                if (z) {
                    i2++;
                }
            }
            r4.A07 += i2;
            return;
        }
        r4.A00 += 3;
    }
}
