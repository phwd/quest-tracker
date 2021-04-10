package X;

import java.io.IOException;
import java.util.Currency;

/* renamed from: X.0WO  reason: invalid class name */
public class AnonymousClass0WO extends AnonymousClass0Bd<Currency> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Currency currency) throws IOException {
        r2.A0E(currency.getCurrencyCode());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Currency A02(AnonymousClass0Fo r2) throws IOException {
        return Currency.getInstance(r2.A0F());
    }
}
