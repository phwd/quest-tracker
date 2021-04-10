package sun.nio.fs;

public class Globs {
    private static char EOL = 0;
    private static final String globMetaChars = "\\*?[{";
    private static final String regexMetaChars = ".^$+{[]|()";

    private Globs() {
    }

    private static boolean isRegexMeta(char c) {
        return regexMetaChars.indexOf(c) != -1;
    }

    private static boolean isGlobMeta(char c) {
        return globMetaChars.indexOf(c) != -1;
    }

    private static char next(String glob, int i) {
        if (i < glob.length()) {
            return glob.charAt(i);
        }
        return EOL;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:77:0x010f, code lost:
        if (r2 != ']') goto L_0x0119;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0111, code lost:
        r1.append("]]");
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0122, code lost:
        throw new java.util.regex.PatternSyntaxException("Missing ']", r14, r3 - 1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String toRegexPattern(java.lang.String r14, boolean r15) {
        /*
        // Method dump skipped, instructions count: 417
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.fs.Globs.toRegexPattern(java.lang.String, boolean):java.lang.String");
    }

    static String toUnixRegexPattern(String globPattern) {
        return toRegexPattern(globPattern, false);
    }

    static String toWindowsRegexPattern(String globPattern) {
        return toRegexPattern(globPattern, true);
    }
}
