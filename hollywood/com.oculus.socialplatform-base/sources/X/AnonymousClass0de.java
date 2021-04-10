package X;

import java.io.IOException;

/* renamed from: X.0de  reason: invalid class name */
public class AnonymousClass0de extends AnonymousClass13Y<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r1, Number number) throws IOException {
        r1.A0B(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Number A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() == AnonymousClass007.A09) {
            r3.A0P();
            return null;
        }
        try {
            return Long.valueOf(r3.A0F());
        } catch (NumberFormatException e) {
            throw new AnonymousClass0eR(e);
        }
    }
}
