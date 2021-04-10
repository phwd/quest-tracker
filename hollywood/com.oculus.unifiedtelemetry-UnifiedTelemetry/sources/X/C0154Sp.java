package X;

import java.io.IOException;
import java.math.BigDecimal;

/* renamed from: X.Sp  reason: case insensitive filesystem */
public class C0154Sp extends AbstractC0131Ob<BigDecimal> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, BigDecimal bigDecimal) throws IOException {
        mmVar.A0E(bigDecimal);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final BigDecimal A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        try {
            return new BigDecimal(lkVar.A0J());
        } catch (NumberFormatException e) {
            throw new U0(e);
        }
    }
}
