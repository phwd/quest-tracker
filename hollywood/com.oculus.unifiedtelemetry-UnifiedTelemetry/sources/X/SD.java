package X;

import java.io.IOException;

public class SD extends AbstractC0131Ob<Boolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Boolean bool) throws IOException {
        String obj;
        if (bool == null) {
            obj = "null";
        } else {
            obj = bool.toString();
        }
        mmVar.A0G(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Boolean A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return Boolean.valueOf(lkVar.A0J());
        }
        lkVar.A0P();
        return null;
    }
}
