package X;

import java.io.IOException;
import java.math.BigInteger;

/* renamed from: X.So  reason: case insensitive filesystem */
public class C0153So extends AbstractC0131Ob<BigInteger> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, BigInteger bigInteger) throws IOException {
        mmVar.A0E(bigInteger);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final BigInteger A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        try {
            return new BigInteger(lkVar.A0J());
        } catch (NumberFormatException e) {
            throw new U0(e);
        }
    }
}
