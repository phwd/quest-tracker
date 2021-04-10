package java.net;

import java.io.IOException;

/* access modifiers changed from: package-private */
/* compiled from: URLConnection */
public class UnknownContentHandler extends ContentHandler {
    static final ContentHandler INSTANCE = new UnknownContentHandler();

    UnknownContentHandler() {
    }

    @Override // java.net.ContentHandler
    public Object getContent(URLConnection uc) throws IOException {
        return uc.getInputStream();
    }
}
