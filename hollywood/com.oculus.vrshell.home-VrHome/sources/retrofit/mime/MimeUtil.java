package retrofit.mime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MimeUtil {
    private static final Pattern CHARSET = Pattern.compile("\\Wcharset=([^\\s;]+)", 2);

    @Deprecated
    public static String parseCharset(String mimeType) {
        return parseCharset(mimeType, "UTF-8");
    }

    public static String parseCharset(String mimeType, String defaultCharset) {
        Matcher match = CHARSET.matcher(mimeType);
        if (match.find()) {
            return match.group(1).replaceAll("[\"\\\\]", "");
        }
        return defaultCharset;
    }

    private MimeUtil() {
    }
}
