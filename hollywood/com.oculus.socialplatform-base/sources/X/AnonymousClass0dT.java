package X;

import java.io.IOException;
import java.net.URL;

/* renamed from: X.0dT  reason: invalid class name */
public class AnonymousClass0dT extends AnonymousClass13Y<URL> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, URL url) throws IOException {
        String externalForm;
        URL url2 = url;
        if (url2 == null) {
            externalForm = null;
        } else {
            externalForm = url2.toExternalForm();
        }
        r2.A0D(externalForm);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final URL A02(AnonymousClass14I r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A09) {
            r4.A0P();
        } else {
            String A0J = r4.A0J();
            if (!"null".equals(A0J)) {
                return new URL(A0J);
            }
        }
        return null;
    }
}
