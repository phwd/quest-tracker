package X;

import java.io.IOException;

/* renamed from: X.0dA  reason: invalid class name */
public class AnonymousClass0dA extends AnonymousClass13Y<Boolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, Boolean bool) throws IOException {
        String obj;
        if (bool == null) {
            obj = "null";
        } else {
            obj = bool.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Boolean A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return Boolean.valueOf(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}
