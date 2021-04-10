package X;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* renamed from: X.0Uw  reason: invalid class name and case insensitive filesystem */
public class C01440Uw extends AnonymousClass0yl<URI> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, URI uri) throws IOException {
        String aSCIIString;
        URI uri2 = uri;
        if (uri2 == null) {
            aSCIIString = null;
        } else {
            aSCIIString = uri2.toASCIIString();
        }
        r2.A0D(aSCIIString);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final URI A02(C09120zR r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A0I) {
            r4.A0P();
        } else {
            try {
                String A0J = r4.A0J();
                if (!"null".equals(A0J)) {
                    return new URI(A0J);
                }
            } catch (URISyntaxException e) {
                throw new AnonymousClass0c9(e);
            }
        }
        return null;
    }
}
