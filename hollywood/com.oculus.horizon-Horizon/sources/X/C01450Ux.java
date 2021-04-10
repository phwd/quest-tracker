package X;

import java.io.IOException;
import java.net.URL;

/* renamed from: X.0Ux  reason: invalid class name and case insensitive filesystem */
public class C01450Ux extends AnonymousClass0yl<URL> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, URL url) throws IOException {
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
    @Override // X.AnonymousClass0yl
    public final URL A02(C09120zR r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A0I) {
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
