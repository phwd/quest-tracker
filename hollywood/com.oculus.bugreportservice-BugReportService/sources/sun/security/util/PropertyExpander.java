package sun.security.util;

import java.security.GeneralSecurityException;

public class PropertyExpander {

    public static class ExpandException extends GeneralSecurityException {
        private static final long serialVersionUID = -7941948581406161702L;

        public ExpandException(String str) {
            super(str);
        }
    }

    public static String expand(String str) {
        return expand(str, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009c, code lost:
        throw null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String expand(java.lang.String r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 211
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.util.PropertyExpander.expand(java.lang.String, boolean):java.lang.String");
    }
}
