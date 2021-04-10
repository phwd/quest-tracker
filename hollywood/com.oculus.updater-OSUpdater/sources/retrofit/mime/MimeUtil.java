package retrofit.mime;

import com.oculus.common.build.BuildConfig;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MimeUtil {
    private static final Pattern CHARSET = Pattern.compile("\\Wcharset=([^\\s;]+)", 2);

    public static String parseCharset(String str, String str2) {
        Matcher matcher = CHARSET.matcher(str);
        return matcher.find() ? matcher.group(1).replaceAll("[\"\\\\]", BuildConfig.PROVIDER_SUFFIX) : str2;
    }

    private MimeUtil() {
    }
}
