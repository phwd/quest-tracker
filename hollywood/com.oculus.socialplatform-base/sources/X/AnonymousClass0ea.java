package X;

import java.io.IOException;

/* renamed from: X.0ea  reason: invalid class name */
public class AnonymousClass0ea extends AnonymousClass13Y<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, Number number) throws IOException {
        if (number == null) {
            r2.A09();
        } else {
            r2.A0D(number.toString());
        }
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Number A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return Long.valueOf(r3.A0F());
        }
        r3.A0P();
        return null;
    }
}
