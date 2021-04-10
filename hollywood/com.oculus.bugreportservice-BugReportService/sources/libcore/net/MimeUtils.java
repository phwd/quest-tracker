package libcore.net;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public final class MimeUtils {
    private static final Map extensionToMimeTypeMap = new HashMap();
    private static final Map mimeTypeToExtensionMap = new HashMap();
    private static final Pattern splitPattern = Pattern.compile("\\s+");

    static {
        parseTypes("mime.types");
        parseTypes("android.mime.types");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c1, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c7, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ca, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseTypes(java.lang.String r10) {
        /*
        // Method dump skipped, instructions count: 227
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.net.MimeUtils.parseTypes(java.lang.String):void");
    }

    private MimeUtils() {
    }

    private static String canonicalize(String str) {
        return str.toLowerCase(Locale.ROOT);
    }

    private static boolean allowedInMap(String str) {
        return str != null && !str.isEmpty();
    }

    public static String guessMimeTypeFromExtension(String str) {
        if (!allowedInMap(str)) {
            return null;
        }
        return (String) extensionToMimeTypeMap.get(canonicalize(str));
    }
}
