package X;

import java.io.IOException;
import java.util.Currency;

/* renamed from: X.Sg  reason: case insensitive filesystem */
public class C0147Sg extends AbstractC0131Ob<Currency> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Currency currency) throws IOException {
        mmVar.A0G(currency.getCurrencyCode());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Currency A02(lk lkVar) throws IOException {
        return Currency.getInstance(lkVar.A0J());
    }
}
