package X;

import java.io.IOException;

/* renamed from: X.Sn  reason: case insensitive filesystem */
public class C0152Sn extends AbstractC0131Ob<StringBuilder> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, StringBuilder sb) throws IOException {
        String obj;
        if (sb == null) {
            obj = null;
        } else {
            obj = sb.toString();
        }
        mmVar.A0G(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final StringBuilder A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return new StringBuilder(lkVar.A0J());
        }
        lkVar.A0P();
        return null;
    }
}
