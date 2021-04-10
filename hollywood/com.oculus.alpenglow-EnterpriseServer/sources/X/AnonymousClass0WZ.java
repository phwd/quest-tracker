package X;

import java.io.IOException;

/* renamed from: X.0WZ  reason: invalid class name */
public class AnonymousClass0WZ extends AnonymousClass0Bd<Character> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Character ch) throws IOException {
        String valueOf;
        if (ch == null) {
            valueOf = null;
        } else {
            valueOf = String.valueOf(ch);
        }
        r2.A0E(valueOf);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Character A02(AnonymousClass0Fo r4) throws IOException {
        if (r4.A0D() == AnonymousClass007.A0I) {
            r4.A0L();
            return null;
        }
        String A0F = r4.A0F();
        if (A0F.length() == 1) {
            return Character.valueOf(A0F.charAt(0));
        }
        throw new AnonymousClass0XQ(AnonymousClass006.A05("Expecting character, got: ", A0F));
    }
}
