package X;

import java.io.IOException;
import java.util.BitSet;

/* renamed from: X.0dJ  reason: invalid class name */
public class AnonymousClass0dJ extends AnonymousClass13Y<BitSet> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        if (r7.A0D() != 0) goto L_0x0041;
     */
    @Override // X.AnonymousClass13Y
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.BitSet A02(X.AnonymousClass14I r7) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0dJ.A02(X.14I):java.lang.Object");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r5, BitSet bitSet) throws IOException {
        BitSet bitSet2 = bitSet;
        r5.A05();
        int length = bitSet2.length();
        for (int i = 0; i < length; i++) {
            r5.A0A(bitSet2.get(i) ? 1 : 0);
        }
        r5.A07();
    }
}
