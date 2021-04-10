package java.net;

import libcore.net.MimeUtils;

/* access modifiers changed from: package-private */
public class DefaultFileNameMap implements FileNameMap {
    DefaultFileNameMap() {
    }

    @Override // java.net.FileNameMap
    public String getContentTypeFor(String str) {
        if (str.endsWith("/")) {
            return MimeUtils.guessMimeTypeFromExtension("html");
        }
        int lastIndexOf = str.lastIndexOf(35);
        if (lastIndexOf < 0) {
            lastIndexOf = str.length();
        }
        int lastIndexOf2 = str.lastIndexOf(46) + 1;
        return MimeUtils.guessMimeTypeFromExtension(lastIndexOf2 > str.lastIndexOf(47) ? str.substring(lastIndexOf2, lastIndexOf) : "");
    }
}
