package X;

import java.io.IOException;

/* renamed from: X.0V4  reason: invalid class name */
public class AnonymousClass0V4 extends AnonymousClass0yl<Character> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, Character ch) throws IOException {
        String valueOf;
        if (ch == null) {
            valueOf = null;
        } else {
            valueOf = String.valueOf(ch);
        }
        r2.A0D(valueOf);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Character A02(C09120zR r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A0I) {
            r4.A0P();
            return null;
        }
        String A0J = r4.A0J();
        if (A0J.length() == 1) {
            return Character.valueOf(A0J.charAt(0));
        }
        throw new C03080c5(AnonymousClass006.A05("Expecting character, got: ", A0J));
    }
}
