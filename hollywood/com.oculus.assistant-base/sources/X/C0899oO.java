package X;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: X.oO  reason: case insensitive filesystem */
public final class C0899oO implements AbstractC0545bi {
    public final Map A00 = new HashMap();

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        if (r1.A01 == false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r2 = r6.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r2 != 80) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r2 = 443;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        r1 = r6.A0C();
        r1.A05("https");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (r2 <= 0) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        if (r2 > 65535) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
        r1.A00 = r2;
        r1 = r1.A03();
        r0 = new X.C0550bn(r4);
        r0.A04 = r1;
        r4 = r0.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        throw new java.lang.IllegalArgumentException(X.AnonymousClass08.A00("unexpected port: ", r2));
     */
    @Override // X.AbstractC0545bi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0554br A3L(X.C1138te r9) {
        /*
        // Method dump skipped, instructions count: 125
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0899oO.A3L(X.te):X.br");
    }

    public C0899oO(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0178Gj gj = (C0178Gj) it.next();
            this.A00.put(gj.A00, gj);
        }
    }
}
