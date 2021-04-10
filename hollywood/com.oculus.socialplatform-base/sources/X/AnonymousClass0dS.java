package X;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* renamed from: X.0dS  reason: invalid class name */
public class AnonymousClass0dS extends AnonymousClass13Y<URI> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, URI uri) throws IOException {
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
    @Override // X.AnonymousClass13Y
    public final URI A02(AnonymousClass14I r4) throws IOException {
        if (r4.A0G() == AnonymousClass007.A09) {
            r4.A0P();
        } else {
            try {
                String A0J = r4.A0J();
                if (!"null".equals(A0J)) {
                    return new URI(A0J);
                }
            } catch (URISyntaxException e) {
                throw new AnonymousClass0eV(e);
            }
        }
        return null;
    }
}
