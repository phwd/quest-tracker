package X;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* renamed from: X.0WR  reason: invalid class name */
public class AnonymousClass0WR extends AnonymousClass0Bd<URI> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, URI uri) throws IOException {
        String aSCIIString;
        URI uri2 = uri;
        if (uri2 == null) {
            aSCIIString = null;
        } else {
            aSCIIString = uri2.toASCIIString();
        }
        r2.A0E(aSCIIString);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final URI A02(AnonymousClass0Fo r4) throws IOException {
        if (r4.A0D() == AnonymousClass007.A0I) {
            r4.A0L();
        } else {
            try {
                String A0F = r4.A0F();
                if (!"null".equals(A0F)) {
                    return new URI(A0F);
                }
            } catch (URISyntaxException e) {
                throw new AnonymousClass0XU(e);
            }
        }
        return null;
    }
}
