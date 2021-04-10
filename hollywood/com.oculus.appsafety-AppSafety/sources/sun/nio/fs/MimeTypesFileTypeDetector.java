package sun.nio.fs;

import java.nio.file.Path;
import libcore.net.MimeUtils;

class MimeTypesFileTypeDetector extends AbstractFileTypeDetector {
    MimeTypesFileTypeDetector() {
    }

    /* access modifiers changed from: protected */
    @Override // sun.nio.fs.AbstractFileTypeDetector
    public String implProbeContentType(Path path) {
        String mimeType;
        Path fn = path.getFileName();
        if (fn == null) {
            return null;
        }
        String ext = getExtension(fn.toString());
        if (ext.isEmpty()) {
            return null;
        }
        do {
            mimeType = MimeUtils.guessMimeTypeFromExtension(ext);
            if (mimeType == null) {
                ext = getExtension(ext);
            }
            if (mimeType != null) {
                break;
            }
        } while (!ext.isEmpty());
        return mimeType;
    }

    private static String getExtension(String name) {
        int dot;
        if (name == null || name.isEmpty() || (dot = name.indexOf(46)) < 0 || dot >= name.length() - 1) {
            return "";
        }
        return name.substring(dot + 1);
    }
}
