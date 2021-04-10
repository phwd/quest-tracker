package org.apache.commons.cli;

import X.AnonymousClass006;

public final class OptionValidator {
    public static void validateOption(String str) throws IllegalArgumentException {
        if (str != null) {
            if (str.length() == 1) {
                char charAt = str.charAt(0);
                if (!isValidOpt(charAt)) {
                    throw new IllegalArgumentException(AnonymousClass006.A02("Illegal option name '", charAt, "'"));
                }
                return;
            }
            char[] charArray = str.toCharArray();
            for (char c : charArray) {
                if (!Character.isJavaIdentifierPart(c)) {
                    StringBuilder sb = new StringBuilder("The option '");
                    sb.append(str);
                    sb.append("' contains an illegal ");
                    sb.append("character : '");
                    sb.append(c);
                    sb.append("'");
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
    }

    public static boolean isValidChar(char c) {
        return Character.isJavaIdentifierPart(c);
    }

    public static boolean isValidOpt(char c) {
        if (Character.isJavaIdentifierPart(c) || c == '?' || c == '@') {
            return true;
        }
        return false;
    }
}
