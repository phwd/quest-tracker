package X;

import java.io.IOException;
import java.util.Currency;

/* renamed from: X.0Ut  reason: invalid class name and case insensitive filesystem */
public class C01410Ut extends AnonymousClass0yl<Currency> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, Currency currency) throws IOException {
        r2.A0D(currency.getCurrencyCode());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Currency A02(C09120zR r2) throws IOException {
        return Currency.getInstance(r2.A0J());
    }
}
