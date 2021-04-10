package retrofit.mime;

import com.facebook.acra.LogCatCollector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MimeUtil {
    public static final Pattern CHARSET = Pattern.compile("\\Wcharset=([^\\s;]+)", 2);

    @Deprecated
    public static String parseCharset(String str) {
        return parseCharset(str, LogCatCollector.UTF_8_ENCODING);
    }

    public static String parseCharset(String str, String str2) {
        Matcher matcher = CHARSET.matcher(str);
        return matcher.find() ? matcher.group(1).replaceAll("[\"\\\\]", "") : str2;
    }
}
