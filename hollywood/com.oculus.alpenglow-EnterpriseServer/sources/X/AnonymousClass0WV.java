package X;

import java.io.IOException;

/* renamed from: X.0WV  reason: invalid class name */
public class AnonymousClass0WV extends AnonymousClass0Bd<StringBuilder> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, StringBuilder sb) throws IOException {
        String sb2;
        StringBuilder sb3 = sb;
        if (sb3 == null) {
            sb2 = null;
        } else {
            sb2 = sb3.toString();
        }
        r2.A0E(sb2);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final StringBuilder A02(AnonymousClass0Fo r3) throws IOException {
        if (r3.A0D() != AnonymousClass007.A0I) {
            return new StringBuilder(r3.A0F());
        }
        r3.A0L();
        return null;
    }
}
