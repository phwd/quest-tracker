package X;

import java.io.IOException;

/* renamed from: X.0V0  reason: invalid class name */
public class AnonymousClass0V0 extends AnonymousClass0yl<StringBuilder> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, StringBuilder sb) throws IOException {
        String obj;
        if (sb == null) {
            obj = null;
        } else {
            obj = sb.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final StringBuilder A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return new StringBuilder(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}
