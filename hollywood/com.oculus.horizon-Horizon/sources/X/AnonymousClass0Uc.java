package X;

import java.io.IOException;

/* renamed from: X.0Uc  reason: invalid class name */
public class AnonymousClass0Uc extends AnonymousClass0yl<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r1, Number number) throws IOException {
        r1.A0B(number);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Number A02(C09120zR r3) throws IOException {
        if (r3.A0G() == AnonymousClass007.A0I) {
            r3.A0P();
            return null;
        }
        try {
            return Short.valueOf((short) r3.A0D());
        } catch (NumberFormatException e) {
            throw new C03080c5(e);
        }
    }
}
