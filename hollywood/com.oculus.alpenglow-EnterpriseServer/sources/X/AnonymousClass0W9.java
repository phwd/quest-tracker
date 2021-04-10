package X;

import java.io.IOException;

/* renamed from: X.0W9  reason: invalid class name */
public class AnonymousClass0W9 extends AnonymousClass0Bd<Boolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Boolean bool) throws IOException {
        String bool2;
        Boolean bool3 = bool;
        if (bool3 == null) {
            bool2 = "null";
        } else {
            bool2 = bool3.toString();
        }
        r2.A0E(bool2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Boolean A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return Boolean.valueOf(r3.A0F());
        }
        r3.A0L();
        return null;
    }
}
