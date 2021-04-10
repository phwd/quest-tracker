package X;

import java.io.IOException;

/* renamed from: X.0da  reason: invalid class name and case insensitive filesystem */
public class C01310da extends AnonymousClass13Y<Character> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, Character ch) throws IOException {
        String valueOf;
        if (ch == null) {
            valueOf = null;
        } else {
            valueOf = String.valueOf(ch);
        }
        r2.A0D(valueOf);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Character A02(AnonymousClass14I r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A09) {
            r4.A0P();
            return null;
        }
        String A0J = r4.A0J();
        if (A0J.length() == 1) {
            return Character.valueOf(A0J.charAt(0));
        }
        throw new AnonymousClass0eR(AnonymousClass006.A07("Expecting character, got: ", A0J));
    }
}
