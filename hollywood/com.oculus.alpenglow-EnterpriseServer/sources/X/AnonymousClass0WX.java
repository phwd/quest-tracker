package X;

import java.io.IOException;
import java.math.BigDecimal;

/* renamed from: X.0WX  reason: invalid class name */
public class AnonymousClass0WX extends AnonymousClass0Bd<BigDecimal> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r1, BigDecimal bigDecimal) throws IOException {
        r1.A0C(bigDecimal);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final BigDecimal A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() == AnonymousClass007.A0I) {
            r3.A0L();
            return null;
        }
        try {
            return new BigDecimal(r3.A0F());
        } catch (NumberFormatException e) {
            throw new AnonymousClass0XQ(e);
        }
    }
}
