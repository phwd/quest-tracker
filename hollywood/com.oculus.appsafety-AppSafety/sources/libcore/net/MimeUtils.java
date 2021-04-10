package libcore.net;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public final class MimeUtils {
    private static final Map<String, String> extensionToMimeTypeMap = new HashMap();
    private static final Map<String, String> mimeTypeToExtensionMap = new HashMap();
    private static final Pattern splitPattern = Pattern.compile("\\s+");

    static {
        parseTypes("mime.types");
        parseTypes("android.mime.types");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ca, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00cf, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d0, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d3, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseTypes(java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 236
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.net.MimeUtils.parseTypes(java.lang.String):void");
    }

    private MimeUtils() {
    }

    private static String canonicalize(String s) {
        return s.toLowerCase(Locale.ROOT);
    }

    private static boolean allowedInMap(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean hasMimeType(String mimeType) {
        return guessExtensionFromMimeType(mimeType) != null;
    }

    public static String guessMimeTypeFromExtension(String extension) {
        if (!allowedInMap(extension)) {
            return null;
        }
        return extensionToMimeTypeMap.get(canonicalize(extension));
    }

    public static boolean hasExtension(String extension) {
        return guessMimeTypeFromExtension(extension) != null;
    }

    public static String guessExtensionFromMimeType(String mimeType) {
        if (!allowedInMap(mimeType)) {
            return null;
        }
        return mimeTypeToExtensionMap.get(canonicalize(mimeType));
    }
}
