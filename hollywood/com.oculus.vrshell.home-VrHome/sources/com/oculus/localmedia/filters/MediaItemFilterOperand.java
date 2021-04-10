package com.oculus.localmedia.filters;

public enum MediaItemFilterOperand {
    UNKNOWN,
    EQUALS,
    DIFFERENT,
    GREATER,
    GREATER_OR_EQUALS,
    LESS,
    LESS_OR_EQUALS;

    public static boolean evaluate(MediaItemFilterOperand operand, Comparable value, Comparable compareTo) {
        int compare = value.compareTo(compareTo);
        if (operand == EQUALS && compare == 0) {
            return true;
        }
        if (operand == GREATER && compare > 0) {
            return true;
        }
        if (operand == GREATER_OR_EQUALS && compare >= 0) {
            return true;
        }
        if (operand == LESS && compare < 0) {
            return true;
        }
        if (operand == LESS_OR_EQUALS && compare <= 0) {
            return true;
        }
        if (operand != DIFFERENT || compare == 0) {
            return false;
        }
        return true;
    }

    public static MediaItemFilterOperand fromString(String operand) {
        if ("=".equalsIgnoreCase(operand)) {
            return EQUALS;
        }
        if ("!=".equalsIgnoreCase(operand)) {
            return DIFFERENT;
        }
        if (">".equalsIgnoreCase(operand)) {
            return GREATER;
        }
        if (">=".equalsIgnoreCase(operand)) {
            return GREATER_OR_EQUALS;
        }
        if ("<".equalsIgnoreCase(operand)) {
            return LESS;
        }
        if ("<=".equalsIgnoreCase(operand)) {
            return LESS_OR_EQUALS;
        }
        return UNKNOWN;
    }
}
