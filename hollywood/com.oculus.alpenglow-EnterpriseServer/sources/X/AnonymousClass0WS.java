package X;

import java.io.IOException;
import java.net.URL;

/* renamed from: X.0WS  reason: invalid class name */
public class AnonymousClass0WS extends AnonymousClass0Bd<URL> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, URL url) throws IOException {
        String externalForm;
        URL url2 = url;
        if (url2 == null) {
            externalForm = null;
        } else {
            externalForm = url2.toExternalForm();
        }
        r2.A0E(externalForm);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final URL A02(AnonymousClass0Fo r4) throws IOException {
        if (r4.A0D() == AnonymousClass007.A0I) {
            r4.A0L();
        } else {
            String A0F = r4.A0F();
            if (!"null".equals(A0F)) {
                return new URL(A0F);
            }
        }
        return null;
    }
}
