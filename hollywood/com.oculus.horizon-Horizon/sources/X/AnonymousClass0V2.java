package X;

import java.io.IOException;
import java.math.BigDecimal;

/* renamed from: X.0V2  reason: invalid class name */
public class AnonymousClass0V2 extends AnonymousClass0yl<BigDecimal> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r1, BigDecimal bigDecimal) throws IOException {
        r1.A0B(bigDecimal);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final BigDecimal A02(C09120zR r3) throws IOException {
        if (r3.A0G() == AnonymousClass007.A0I) {
            r3.A0P();
            return null;
        }
        try {
            return new BigDecimal(r3.A0J());
        } catch (NumberFormatException e) {
            throw new C03080c5(e);
        }
    }
}
