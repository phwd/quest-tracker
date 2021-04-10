package X;

import java.io.IOException;

/* renamed from: X.0cl  reason: invalid class name and case insensitive filesystem */
public class C03360cl extends AnonymousClass0yl<Number> {
    public final /* synthetic */ C08780ya A00;

    public C03360cl(C08780ya r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r3, Number number) throws IOException {
        Number number2 = number;
        if (number2 == null) {
            r3.A09();
            return;
        }
        C08780ya.A01(number2.doubleValue());
        r3.A0B(number2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Number A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return Double.valueOf(r3.A0C());
        }
        r3.A0P();
        return null;
    }
}
