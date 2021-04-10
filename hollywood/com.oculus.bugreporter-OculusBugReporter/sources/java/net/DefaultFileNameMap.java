package java.net;

import libcore.net.MimeUtils;

/* access modifiers changed from: package-private */
public class DefaultFileNameMap implements FileNameMap {
    DefaultFileNameMap() {
    }

    @Override // java.net.FileNameMap
    public String getContentTypeFor(String filename) {
        if (filename.endsWith("/")) {
            return MimeUtils.guessMimeTypeFromExtension("html");
        }
        int lastCharInExtension = filename.lastIndexOf(35);
        if (lastCharInExtension < 0) {
            lastCharInExtension = filename.length();
        }
        int firstCharInExtension = filename.lastIndexOf(46) + 1;
        String ext = "";
        if (firstCharInExtension > filename.lastIndexOf(47)) {
            ext = filename.substring(firstCharInExtension, lastCharInExtension);
        }
        return MimeUtils.guessMimeTypeFromExtension(ext);
    }
}
