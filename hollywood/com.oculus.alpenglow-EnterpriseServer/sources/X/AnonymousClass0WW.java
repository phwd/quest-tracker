package X;

import java.io.IOException;
import java.math.BigInteger;

/* renamed from: X.0WW  reason: invalid class name */
public class AnonymousClass0WW extends AnonymousClass0Bd<BigInteger> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r1, BigInteger bigInteger) throws IOException {
        r1.A0C(bigInteger);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final BigInteger A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() == AnonymousClass007.A0I) {
            r3.A0L();
            return null;
        }
        try {
            return new BigInteger(r3.A0F());
        } catch (NumberFormatException e) {
            throw new AnonymousClass0XQ(e);
        }
    }
}
