package X;

import java.io.IOException;
import java.util.BitSet;

/* renamed from: X.Sa  reason: case insensitive filesystem */
public class C0143Sa extends AbstractC0131Ob<BitSet> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        if (r7.A0D() != 0) goto L_0x0041;
     */
    @Override // X.AbstractC0131Ob
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.BitSet A02(X.lk r7) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0143Sa.A02(X.lk):java.lang.Object");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, BitSet bitSet) throws IOException {
        BitSet bitSet2 = bitSet;
        mmVar.A07();
        int length = bitSet2.length();
        for (int i = 0; i < length; i++) {
            mmVar.A0C(bitSet2.get(i) ? 1 : 0);
        }
        mmVar.A09();
    }
}
