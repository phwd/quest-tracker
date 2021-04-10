package X;

import java.io.IOException;

/* renamed from: X.0Xa  reason: invalid class name and case insensitive filesystem */
public class C02170Xa extends AnonymousClass0Bd<Number> {
    public final /* synthetic */ AnonymousClass08D A00;

    public C02170Xa(AnonymousClass08D r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r3, Number number) throws IOException {
        Number number2 = number;
        if (number2 == null) {
            r3.A0A();
            return;
        }
        AnonymousClass08D.A02((double) number2.floatValue());
        r3.A0C(number2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Number A02(AnonymousClass0Fo r4) throws IOException {
        if (r4.A0D() != AnonymousClass007.A0I) {
            return Float.valueOf((float) r4.A09());
        }
        r4.A0L();
        return null;
    }
}
