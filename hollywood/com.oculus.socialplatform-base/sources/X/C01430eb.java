package X;

import java.io.IOException;

/* renamed from: X.0eb  reason: invalid class name and case insensitive filesystem */
public class C01430eb extends AnonymousClass13Y<Number> {
    public final /* synthetic */ AnonymousClass13N A00;

    public C01430eb(AnonymousClass13N r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r3, Number number) throws IOException {
        Number number2 = number;
        if (number2 == null) {
            r3.A09();
            return;
        }
        AnonymousClass13N.A01((double) number2.floatValue());
        r3.A0B(number2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Number A02(AnonymousClass14I r4) throws IOException {
        if (r4.A0G() != AnonymousClass007.A09) {
            return Float.valueOf((float) r4.A0C());
        }
        r4.A0P();
        return null;
    }
}
