package X;

import java.io.IOException;

/* renamed from: X.Sr  reason: case insensitive filesystem */
public class C0156Sr extends AbstractC0131Ob<Character> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Character ch) throws IOException {
        String valueOf;
        if (ch == null) {
            valueOf = null;
        } else {
            valueOf = String.valueOf(ch);
        }
        mmVar.A0G(valueOf);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Character A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        String A0J = lkVar.A0J();
        if (A0J.length() == 1) {
            return Character.valueOf(A0J.charAt(0));
        }
        throw new U0(AnonymousClass06.A04("Expecting character, got: ", A0J));
    }
}
