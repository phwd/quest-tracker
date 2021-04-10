package org.apache.commons.cli;

/* access modifiers changed from: package-private */
public final class Util {
    Util() {
    }

    static String stripLeadingHyphens(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
            return str.substring(2, str.length());
        }
        return str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX) ? str.substring(1, str.length()) : str;
    }

    static String stripLeadingAndTrailingQuotes(String str) {
        int length = str.length();
        if (length <= 1 || !str.startsWith("\"") || !str.endsWith("\"")) {
            return str;
        }
        int i = length - 1;
        return str.substring(1, i).indexOf(34) == -1 ? str.substring(1, i) : str;
    }
}
