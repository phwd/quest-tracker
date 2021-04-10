package X;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: X.0lT  reason: invalid class name and case insensitive filesystem */
public final class C05820lT {
    public static final Pattern A01 = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
    public static final Pattern A02 = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    public final String A00;

    public static C05820lT A00(String str) {
        int length;
        Matcher matcher = A02.matcher(str);
        if (matcher.lookingAt()) {
            matcher.group(1);
            matcher.group(2);
            Matcher matcher2 = A01.matcher(str);
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
                    return new C05820lT(str);
                }
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C05820lT) || !((C05820lT) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        return this.A00;
    }

    public C05820lT(String str) {
        this.A00 = str;
    }
}
