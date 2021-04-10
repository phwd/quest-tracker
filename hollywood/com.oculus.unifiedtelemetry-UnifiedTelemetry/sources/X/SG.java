package X;

import java.io.IOException;

public class SG extends AbstractC0131Ob<Boolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Boolean bool) throws IOException {
        mmVar.A0D(bool);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Boolean A02(lk lkVar) throws IOException {
        boolean A0S;
        Integer A0G = lkVar.A0G();
        if (A0G == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        if (A0G == AnonymousClass07.A05) {
            A0S = Boolean.parseBoolean(lkVar.A0J());
        } else {
            A0S = lkVar.A0S();
        }
        return Boolean.valueOf(A0S);
    }
}
