package X;

import java.io.IOException;
import java.math.BigInteger;

/* renamed from: X.0dX  reason: invalid class name and case insensitive filesystem */
public class C01290dX extends AnonymousClass13Y<BigInteger> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r1, BigInteger bigInteger) throws IOException {
        r1.A0B(bigInteger);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final BigInteger A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() == AnonymousClass007.A09) {
            r3.A0P();
            return null;
        }
        try {
            return new BigInteger(r3.A0J());
        } catch (NumberFormatException e) {
            throw new AnonymousClass0eR(e);
        }
    }
}
