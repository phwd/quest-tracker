package com.fasterxml.jackson.core;

public final class Base64Variants {
    public static final Base64Variant MIME = new Base64Variant("MIME", STD_BASE64_ALPHABET, true, '=', 76);
    public static final Base64Variant MIME_NO_LINEFEEDS = new Base64Variant(MIME, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
    public static final Base64Variant MODIFIED_FOR_URL;
    public static final Base64Variant PEM = new Base64Variant(MIME, "PEM", true, '=', 64);
    static final String STD_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    static {
        StringBuffer stringBuffer = new StringBuffer(STD_BASE64_ALPHABET);
        stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
        stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
        MODIFIED_FOR_URL = new Base64Variant("MODIFIED-FOR-URL", stringBuffer.toString(), false, (char) 0, Integer.MAX_VALUE);
    }

    public static Base64Variant getDefaultVariant() {
        return MIME_NO_LINEFEEDS;
    }

    public static Base64Variant valueOf(String str) throws IllegalArgumentException {
        String str2;
        if (MIME._name.equals(str)) {
            return MIME;
        }
        if (MIME_NO_LINEFEEDS._name.equals(str)) {
            return MIME_NO_LINEFEEDS;
        }
        if (PEM._name.equals(str)) {
            return PEM;
        }
        if (MODIFIED_FOR_URL._name.equals(str)) {
            return MODIFIED_FOR_URL;
        }
        if (str == null) {
            str2 = "<null>";
        } else {
            str2 = "'" + str + "'";
        }
        throw new IllegalArgumentException("No Base64Variant with name " + str2);
    }
}
