package org.apache.commons.cli;

public final class Util {
    public static String stripLeadingHyphens(String str) {
        int i;
        if (str == null) {
            return null;
        }
        if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            i = 2;
        } else if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            return str;
        } else {
            i = 1;
        }
        return str.substring(i, str.length());
    }

    public static String stripLeadingAndTrailingQuotes(String str) {
        int length = str.length();
        if (length <= 1 || !str.startsWith("\"") || !str.endsWith("\"")) {
            return str;
        }
        int i = length - 1;
        if (str.substring(1, i).indexOf(34) == -1) {
            return str.substring(1, i);
        }
        return str;
    }
}
