package X;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: X.0wR  reason: invalid class name and case insensitive filesystem */
public final class C08370wR {
    public static final Pattern A02 = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    public static final Pattern A03 = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    public final String A00;
    public final String A01;

    public static C08370wR A00(String str) {
        int length;
        Matcher matcher = A03.matcher(str);
        if (matcher.lookingAt()) {
            matcher.group(1);
            matcher.group(2);
            Matcher matcher2 = A02.matcher(str);
            int end = matcher.end();
            String str2 = null;
            while (true) {
                int length2 = str.length();
                if (end < length2) {
                    matcher2.region(end, length2);
                    if (!matcher2.lookingAt()) {
                        break;
                    }
                    String group = matcher2.group(1);
                    if (group != null && group.equalsIgnoreCase("charset")) {
                        String group2 = matcher2.group(2);
                        if (group2 == null) {
                            group2 = matcher2.group(3);
                        } else if (group2.startsWith("'") && group2.endsWith("'") && (length = group2.length()) > 2) {
                            group2 = group2.substring(1, length - 1);
                        }
                        if (str2 == null || group2.equalsIgnoreCase(str2)) {
                            str2 = group2;
                        } else {
                            throw new IllegalArgumentException(AnonymousClass006.A05("Multiple different charsets: ", str));
                        }
                    }
                    end = matcher2.end();
                } else {
                    return new C08370wR(str, str2);
                }
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C08370wR) || !((C08370wR) obj).A01.equals(this.A01)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A01.hashCode();
    }

    public C08370wR(String str, String str2) {
        this.A01 = str;
        this.A00 = str2;
    }

    public final String toString() {
        return this.A01;
    }
}
