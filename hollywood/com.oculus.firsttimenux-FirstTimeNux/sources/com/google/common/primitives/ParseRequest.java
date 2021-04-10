package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class ParseRequest {
    final int radix;
    final String rawValue;

    private ParseRequest(String rawValue2, int radix2) {
        this.rawValue = rawValue2;
        this.radix = radix2;
    }

    static ParseRequest fromString(String stringValue) {
        String rawValue2;
        int radix2;
        if (stringValue.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        char firstChar = stringValue.charAt(0);
        if (stringValue.startsWith("0x") || stringValue.startsWith("0X")) {
            rawValue2 = stringValue.substring(2);
            radix2 = 16;
        } else if (firstChar == '#') {
            rawValue2 = stringValue.substring(1);
            radix2 = 16;
        } else if (firstChar != '0' || stringValue.length() <= 1) {
            rawValue2 = stringValue;
            radix2 = 10;
        } else {
            rawValue2 = stringValue.substring(1);
            radix2 = 8;
        }
        return new ParseRequest(rawValue2, radix2);
    }
}
