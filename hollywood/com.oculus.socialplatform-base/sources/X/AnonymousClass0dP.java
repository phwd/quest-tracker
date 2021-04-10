package X;

import java.io.IOException;
import java.util.Currency;

/* renamed from: X.0dP  reason: invalid class name */
public class AnonymousClass0dP extends AnonymousClass13Y<Currency> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, Currency currency) throws IOException {
        r2.A0D(currency.getCurrencyCode());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Currency A02(AnonymousClass14I r2) throws IOException {
        return Currency.getInstance(r2.A0J());
    }
}
