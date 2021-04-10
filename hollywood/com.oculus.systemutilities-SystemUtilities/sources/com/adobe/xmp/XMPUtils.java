package com.adobe.xmp;

import com.adobe.xmp.impl.Base64;
import com.adobe.xmp.impl.ISO8601Converter;

public class XMPUtils {
    public static boolean convertToBoolean(String value) throws XMPException {
        boolean z = false;
        if (value == null || value.length() == 0) {
            throw new XMPException("Empty convert-string", 5);
        }
        String value2 = value.toLowerCase();
        try {
            return Integer.parseInt(value2) != 0;
        } catch (NumberFormatException e) {
            if ("true".equals(value2) || "t".equals(value2) || "on".equals(value2) || "yes".equals(value2)) {
                z = true;
            }
            return z;
        }
    }

    public static int convertToInteger(String rawValue) throws XMPException {
        if (rawValue != null) {
            try {
                if (rawValue.length() != 0) {
                    if (rawValue.startsWith("0x")) {
                        return Integer.parseInt(rawValue.substring(2), 16);
                    }
                    return Integer.parseInt(rawValue);
                }
            } catch (NumberFormatException e) {
                throw new XMPException("Invalid integer string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static long convertToLong(String rawValue) throws XMPException {
        if (rawValue != null) {
            try {
                if (rawValue.length() != 0) {
                    if (rawValue.startsWith("0x")) {
                        return Long.parseLong(rawValue.substring(2), 16);
                    }
                    return Long.parseLong(rawValue);
                }
            } catch (NumberFormatException e) {
                throw new XMPException("Invalid long string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static double convertToDouble(String rawValue) throws XMPException {
        if (rawValue != null) {
            try {
                if (rawValue.length() != 0) {
                    return Double.parseDouble(rawValue);
                }
            } catch (NumberFormatException e) {
                throw new XMPException("Invalid double string", 5);
            }
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static XMPDateTime convertToDate(String rawValue) throws XMPException {
        if (rawValue != null && rawValue.length() != 0) {
            return ISO8601Converter.parse(rawValue);
        }
        throw new XMPException("Empty convert-string", 5);
    }

    public static String convertFromDate(XMPDateTime value) {
        return ISO8601Converter.render(value);
    }

    public static byte[] decodeBase64(String base64String) throws XMPException {
        try {
            return Base64.decode(base64String.getBytes());
        } catch (Throwable e) {
            throw new XMPException("Invalid base64 string", 5, e);
        }
    }
}
