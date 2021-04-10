package X;

import java.io.IOException;
import java.util.BitSet;

/* renamed from: X.0WI  reason: invalid class name */
public class AnonymousClass0WI extends AnonymousClass0Bd<BitSet> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        if (r7.A0A() != 0) goto L_0x0041;
     */
    @Override // X.AnonymousClass0Bd
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.BitSet A02(X.AnonymousClass0Fo r7) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0WI.A02(X.0Fo):java.lang.Object");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r5, BitSet bitSet) throws IOException {
        BitSet bitSet2 = bitSet;
        r5.A06();
        int length = bitSet2.length();
        for (int i = 0; i < length; i++) {
            r5.A0B(bitSet2.get(i) ? 1 : 0);
        }
        r5.A08();
    }
}
