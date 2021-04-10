package X;

import java.io.IOException;
import java.net.URL;

/* renamed from: X.Sk  reason: case insensitive filesystem */
public class C0150Sk extends AbstractC0131Ob<URL> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, URL url) throws IOException {
        String externalForm;
        URL url2 = url;
        if (url2 == null) {
            externalForm = null;
        } else {
            externalForm = url2.toExternalForm();
        }
        mmVar.A0G(externalForm);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final URL A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
        } else {
            String A0J = lkVar.A0J();
            if (!"null".equals(A0J)) {
                return new URL(A0J);
            }
        }
        return null;
    }
}
