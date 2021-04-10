package okhttp3;

import X.AnonymousClass006;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MediaType {
    public static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    public static final String QUOTED = "\"([^\"]*)\"";
    public static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    public static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    public final String charset;
    public final String mediaType;
    public final String subtype;
    public final String type;

    public static MediaType parse(String str) {
        int length;
        Matcher matcher = TYPE_SUBTYPE.matcher(str);
        if (matcher.lookingAt()) {
            String group = matcher.group(1);
            Locale locale = Locale.US;
            String lowerCase = group.toLowerCase(locale);
            String lowerCase2 = matcher.group(2).toLowerCase(locale);
            Matcher matcher2 = PARAMETER.matcher(str);
            int end = matcher.end();
            String str2 = null;
            while (true) {
                int length2 = str.length();
                if (end < length2) {
                    matcher2.region(end, length2);
                    if (!matcher2.lookingAt()) {
                        break;
                    }
                    String group2 = matcher2.group(1);
                    if (group2 != null && group2.equalsIgnoreCase("charset")) {
                        String group3 = matcher2.group(2);
                        if (group3 == null) {
                            group3 = matcher2.group(3);
                        } else if (group3.startsWith("'") && group3.endsWith("'") && (length = group3.length()) > 2) {
                            group3 = group3.substring(1, length - 1);
                        }
                        if (str2 == null || group3.equalsIgnoreCase(str2)) {
                            str2 = group3;
                        } else {
                            throw new IllegalArgumentException(AnonymousClass006.A07("Multiple different charsets: ", str));
                        }
                    }
                    end = matcher2.end();
                } else {
                    return new MediaType(str, lowerCase, lowerCase2, str2);
                }
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaType) || !((MediaType) obj).mediaType.equals(this.mediaType)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }

    public MediaType(String str, String str2, String str3, String str4) {
        this.mediaType = str;
        this.type = str2;
        this.subtype = str3;
        this.charset = str4;
    }

    public String subtype() {
        return this.subtype;
    }

    public String toString() {
        return this.mediaType;
    }

    public String type() {
        return this.type;
    }

    public Charset charset() {
        String str = this.charset;
        if (str != null) {
            return Charset.forName(str);
        }
        return null;
    }

    public Charset charset(Charset charset2) {
        String str = this.charset;
        return str != null ? Charset.forName(str) : charset2;
    }
}
