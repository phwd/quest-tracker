package com.oculus.localmedia.filters;

public enum MediaItemFilterOperand {
    UNKNOWN,
    EQUALS,
    DIFFERENT,
    GREATER,
    GREATER_OR_EQUALS,
    LESS,
    LESS_OR_EQUALS;

    public static MediaItemFilterOperand fromString(String str) {
        if ("=".equalsIgnoreCase(str)) {
            return EQUALS;
        }
        if ("!=".equalsIgnoreCase(str)) {
            return DIFFERENT;
        }
        if (">".equalsIgnoreCase(str)) {
            return GREATER;
        }
        if (">=".equalsIgnoreCase(str)) {
            return GREATER_OR_EQUALS;
        }
        if ("<".equalsIgnoreCase(str)) {
            return LESS;
        }
        if ("<=".equalsIgnoreCase(str)) {
            return LESS_OR_EQUALS;
        }
        return UNKNOWN;
    }

    public static boolean evaluate(MediaItemFilterOperand mediaItemFilterOperand, Comparable comparable, Comparable comparable2) {
        int compareTo = comparable.compareTo(comparable2);
        if (mediaItemFilterOperand == EQUALS && compareTo == 0) {
            return true;
        }
        if (mediaItemFilterOperand == GREATER && compareTo > 0) {
            return true;
        }
        if (mediaItemFilterOperand == GREATER_OR_EQUALS && compareTo >= 0) {
            return true;
        }
        if (mediaItemFilterOperand == LESS && compareTo < 0) {
            return true;
        }
        if (mediaItemFilterOperand == LESS_OR_EQUALS && compareTo <= 0) {
            return true;
        }
        if (mediaItemFilterOperand != DIFFERENT || compareTo == 0) {
            return false;
        }
        return true;
    }
}
