package X;

import java.io.IOException;

/* renamed from: X.0dW  reason: invalid class name */
public class AnonymousClass0dW extends AnonymousClass13Y<StringBuilder> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, StringBuilder sb) throws IOException {
        String obj;
        if (sb == null) {
            obj = null;
        } else {
            obj = sb.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final StringBuilder A02(AnonymousClass14I r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A09) {
            return new StringBuilder(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}
