package afu.plume;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class RegexUtil {
    private RegexUtil() {
        throw new Error("do not instantiate");
    }

    public static class CheckedPatternSyntaxException extends Exception {
        private static final long serialVersionUID = 6266881831979001480L;
        private final PatternSyntaxException pse;

        public CheckedPatternSyntaxException(PatternSyntaxException patternSyntaxException) {
            this.pse = patternSyntaxException;
        }

        public CheckedPatternSyntaxException(String str, String str2, int i) {
            this(new PatternSyntaxException(str, str2, i));
        }

        public String getDescription() {
            return this.pse.getDescription();
        }

        public int getIndex() {
            return this.pse.getIndex();
        }

        public String getMessage() {
            return this.pse.getMessage();
        }

        public String getPattern() {
            return this.pse.getPattern();
        }
    }

    public static boolean isRegex(String str) {
        return isRegex(str, 0);
    }

    public static boolean isRegex(String str, int i) {
        try {
            if (getGroupCount(Pattern.compile(str)) >= i) {
                return true;
            }
            return false;
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    public static boolean isRegex(char c) {
        return isRegex(Character.toString(c));
    }

    public static String regexError(String str) {
        return regexError(str, 0);
    }

    public static String regexError(String str, int i) {
        try {
            int groupCount = getGroupCount(Pattern.compile(str));
            if (groupCount < i) {
                return regexErrorMessage(str, i, groupCount);
            }
            return null;
        } catch (PatternSyntaxException e) {
            return e.getMessage();
        }
    }

    public static PatternSyntaxException regexException(String str) {
        return regexException(str, 0);
    }

    public static PatternSyntaxException regexException(String str, int i) {
        try {
            int groupCount = getGroupCount(Pattern.compile(str));
            if (groupCount < i) {
                return new PatternSyntaxException(regexErrorMessage(str, i, groupCount), str, -1);
            }
            return null;
        } catch (PatternSyntaxException e) {
            return e;
        }
    }

    public static String asRegex(String str) {
        return asRegex(str, 0);
    }

    public static String asRegex(String str, int i) {
        try {
            int groupCount = getGroupCount(Pattern.compile(str));
            if (groupCount >= i) {
                return str;
            }
            throw new Error(regexErrorMessage(str, i, groupCount));
        } catch (PatternSyntaxException e) {
            throw new Error(e);
        }
    }

    private static String regexErrorMessage(String str, int i, int i2) {
        return "regex \"" + str + "\" has " + i2 + " groups, but " + i + " groups are needed.";
    }

    private static int getGroupCount(Pattern pattern) {
        return pattern.matcher("").groupCount();
    }
}
