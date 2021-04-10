package X;

import java.io.IOException;

/* renamed from: X.0cj  reason: invalid class name and case insensitive filesystem */
public class C03340cj extends AnonymousClass0yl<Number> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, Number number) throws IOException {
        if (number == null) {
            r2.A09();
        } else {
            r2.A0D(number.toString());
        }
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Number A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return Long.valueOf(r3.A0F());
        }
        r3.A0P();
        return null;
    }
}
