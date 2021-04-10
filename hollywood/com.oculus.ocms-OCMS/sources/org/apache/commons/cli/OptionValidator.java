package org.apache.commons.cli;

/* access modifiers changed from: package-private */
public final class OptionValidator {
    OptionValidator() {
    }

    static void validateOption(String str) throws IllegalArgumentException {
        if (str != null) {
            if (str.length() == 1) {
                char charAt = str.charAt(0);
                if (!isValidOpt(charAt)) {
                    throw new IllegalArgumentException("Illegal option name '" + charAt + "'");
                }
                return;
            }
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                if (!isValidChar(c)) {
                    throw new IllegalArgumentException("The option '" + str + "' contains an illegal " + "character : '" + c + "'");
                }
            }
        }
    }

    private static boolean isValidOpt(char c) {
        return isValidChar(c) || c == '?' || c == '@';
    }

    private static boolean isValidChar(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}
