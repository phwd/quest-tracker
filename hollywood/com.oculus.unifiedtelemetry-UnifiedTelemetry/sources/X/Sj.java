package X;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Sj extends AbstractC0131Ob<URI> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, URI uri) throws IOException {
        String aSCIIString;
        URI uri2 = uri;
        if (uri2 == null) {
            aSCIIString = null;
        } else {
            aSCIIString = uri2.toASCIIString();
        }
        mmVar.A0G(aSCIIString);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final URI A02(lk lkVar) throws IOException {
        if (lkVar.A0G() == AnonymousClass07.A08) {
            lkVar.A0P();
        } else {
            try {
                String A0J = lkVar.A0J();
                if (!"null".equals(A0J)) {
                    return new URI(A0J);
                }
            } catch (URISyntaxException e) {
                throw new U4(e);
            }
        }
        return null;
    }
}
