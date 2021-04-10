package java.net;

import java.io.IOException;

public abstract class ContentHandler {
    public abstract Object getContent(URLConnection uRLConnection) throws IOException;

    public Object getContent(URLConnection urlc, Class[] classes) throws IOException {
        Object obj = getContent(urlc);
        for (Class cls : classes) {
            if (cls.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }
}
