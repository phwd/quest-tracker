package X;

import java.io.IOException;

/* renamed from: X.0WY  reason: invalid class name */
public class AnonymousClass0WY extends AnonymousClass0Bd<String> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r1, String str) throws IOException {
        r1.A0E(str);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final String A02(AnonymousClass0Fo r3) throws IOException {
        Integer A0D = r3.A0D();
        if (A0D == AnonymousClass007.A0I) {
            r3.A0L();
            return null;
        } else if (A0D == AnonymousClass007.A0H) {
            return Boolean.toString(r3.A0O());
        } else {
            return r3.A0F();
        }
    }
}
