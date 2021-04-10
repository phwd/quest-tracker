package X;

import java.io.IOException;

public class SA extends AbstractC0131Ob<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Number number) throws IOException {
        mmVar.A0E(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Number A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        try {
            return Short.valueOf((short) lkVar.A0D());
        } catch (NumberFormatException e) {
            throw new U0(e);
        }
    }
}
