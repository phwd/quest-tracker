package X;

import java.io.IOException;

/* renamed from: X.0Ue  reason: invalid class name */
public class AnonymousClass0Ue extends AnonymousClass0yl<Boolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, Boolean bool) throws IOException {
        String obj;
        if (bool == null) {
            obj = "null";
        } else {
            obj = bool.toString();
        }
        r2.A0D(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Boolean A02(C09120zR r3) throws IOException {
        if (r3.A0G() != AnonymousClass007.A0I) {
            return Boolean.valueOf(r3.A0J());
        }
        r3.A0P();
        return null;
    }
}
