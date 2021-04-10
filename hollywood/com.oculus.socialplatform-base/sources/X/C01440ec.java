package X;

import java.io.IOException;

/* renamed from: X.0ec  reason: invalid class name and case insensitive filesystem */
public class C01440ec extends AnonymousClass13Y<Number> {
    public final /* synthetic */ AnonymousClass13N A00;

    public C01440ec(AnonymousClass13N r1) {
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
        AnonymousClass13N.A01(number2.doubleValue());
        r3.A0B(number2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Number A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return Double.valueOf(r3.A0C());
        }
        r3.A0P();
        return null;
    }
}
