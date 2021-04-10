package X;

import java.io.IOException;

/* renamed from: X.Sq  reason: case insensitive filesystem */
public class C0155Sq extends AbstractC0131Ob<String> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, String str) throws IOException {
        mmVar.A0G(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final String A02(lk lkVar) throws IOException {
        Integer A0G = lkVar.A0G();
        if (A0G == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        } else if (A0G == AnonymousClass07.A07) {
            return Boolean.toString(lkVar.A0S());
        } else {
            return lkVar.A0J();
        }
    }
}
