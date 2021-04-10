package X;

import java.io.IOException;

/* renamed from: X.0dZ  reason: invalid class name */
public class AnonymousClass0dZ extends AnonymousClass13Y<String> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r1, String str) throws IOException {
        r1.A0D(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final String A02(AnonymousClass14I r3) throws IOException {
        Integer A0G = r3.A0G();
        if (A0G == AnonymousClass007.A09) {
            r3.A0P();
            return null;
        } else if (A0G == AnonymousClass007.A08) {
            return Boolean.toString(r3.A0S());
        } else {
            return r3.A0J();
        }
    }
}
