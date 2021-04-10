package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;

public class Utils implements XMPConst {
    public static final int UUID_LENGTH = 36;
    public static final int UUID_SEGMENT_COUNT = 4;
    public static boolean[] xmlNameChars;
    public static boolean[] xmlNameStartChars;

    public static boolean checkUUIDFormat(String str) {
        if (str == null) {
            return false;
        }
        int i = 0;
        boolean z = true;
        int i2 = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '-') {
                i2++;
                if (!z || !(i == 8 || i == 13 || i == 18 || i == 23)) {
                    z = false;
                } else {
                    z = true;
                }
            }
            i++;
        }
        return z && 4 == i2 && 36 == i;
    }

    public static String escapeXML(String str, boolean z, boolean z2) {
        int length;
        String str2;
        int i = 0;
        while (true) {
            length = str.length();
            if (i >= length) {
                return str;
            }
            char charAt = str.charAt(i);
            if (charAt == '<' || charAt == '>' || charAt == '&' || ((z2 && (charAt == '\t' || charAt == '\n' || charAt == '\r')) || (z && charAt == '\"'))) {
                StringBuffer stringBuffer = new StringBuffer((length << 2) / 3);
            } else {
                i++;
            }
        }
        StringBuffer stringBuffer2 = new StringBuffer((length << 2) / 3);
        for (int i2 = 0; i2 < length; i2++) {
            char charAt2 = str.charAt(i2);
            if (!z2 || !(charAt2 == '\t' || charAt2 == '\n' || charAt2 == '\r')) {
                if (charAt2 != '\"') {
                    if (charAt2 == '&') {
                        str2 = "&amp;";
                    } else if (charAt2 != '<') {
                        if (charAt2 == '>') {
                            str2 = "&gt;";
                        }
                        stringBuffer2.append(charAt2);
                    } else {
                        str2 = "&lt;";
                    }
                } else if (z) {
                    str2 = "&quot;";
                } else {
                    str2 = "\"";
                }
                stringBuffer2.append(str2);
            } else {
                stringBuffer2.append("&#x");
                stringBuffer2.append(Integer.toHexString(charAt2).toUpperCase());
                charAt2 = ';';
                stringBuffer2.append(charAt2);
            }
        }
        return stringBuffer2.toString();
    }

    public static boolean isControlChar(char c) {
        return c > 31 ? c == 127 : (c == '\t' || c == '\n' || c == '\r') ? false : true;
    }

    public static void initCharTables() {
        boolean z;
        boolean[] zArr = new boolean[256];
        xmlNameChars = zArr;
        boolean[] zArr2 = new boolean[256];
        xmlNameStartChars = zArr2;
        char c = 0;
        do {
            boolean z2 = true;
            if (('a' > c || c > 'z') && (('A' > c || c > 'Z') && c != ':' && c != '_' && ((192 > c || c > 214) && (216 > c || c > 246)))) {
                z = false;
            } else {
                z = true;
            }
            zArr2[c] = z;
            if (('a' > c || c > 'z') && (('A' > c || c > 'Z') && !(('0' <= c && c <= '9') || c == ':' || c == '_' || c == '-' || c == '.' || c == 183 || ((192 <= c && c <= 214) || (216 <= c && c <= 246))))) {
                z2 = false;
            }
            zArr[c] = z2;
            c = (char) (c + 1);
        } while (c < 256);
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0019 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isInternalProperty(java.lang.String r2, java.lang.String r3) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.Utils.isInternalProperty(java.lang.String, java.lang.String):boolean");
    }

    public static boolean isNameChar(char c) {
        if (c > 255 || xmlNameChars[c]) {
            return true;
        }
        return false;
    }

    public static boolean isNameStartChar(char c) {
        if (c > 255 || xmlNameStartChars[c]) {
            return true;
        }
        return false;
    }

    public static String normalizeLangValue(String str) {
        char upperCase;
        if (XMPConst.X_DEFAULT.equals(str)) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 1;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != ' ') {
                if (charAt == '-' || charAt == '_') {
                    stringBuffer.append('-');
                    i++;
                } else {
                    if (i != 2) {
                        upperCase = Character.toLowerCase(str.charAt(i2));
                    } else {
                        upperCase = Character.toUpperCase(str.charAt(i2));
                    }
                    stringBuffer.append(upperCase);
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String removeControlChars(String str) {
        StringBuffer stringBuffer = new StringBuffer(str);
        for (int i = 0; i < stringBuffer.length(); i++) {
            if (isControlChar(stringBuffer.charAt(i))) {
                stringBuffer.setCharAt(i, ' ');
            }
        }
        return stringBuffer.toString();
    }

    public static String[] splitNameAndValue(String str) {
        int indexOf = str.indexOf(61);
        int i = 1;
        if (str.charAt(1) == '?') {
            i = 2;
        }
        String substring = str.substring(i, indexOf);
        int i2 = indexOf + 1;
        char charAt = str.charAt(i2);
        int i3 = i2 + 1;
        int length = str.length() - 2;
        StringBuffer stringBuffer = new StringBuffer(length - indexOf);
        while (i3 < length) {
            stringBuffer.append(str.charAt(i3));
            i3++;
            if (str.charAt(i3) == charAt) {
                i3++;
            }
        }
        return new String[]{substring, stringBuffer.toString()};
    }

    static {
        initCharTables();
    }

    public static boolean isXMLName(String str) {
        int length = str.length();
        if (length <= 0 || isNameStartChar(str.charAt(0))) {
            for (int i = 1; i < length; i++) {
                if (isNameChar(str.charAt(i))) {
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isXMLNameNS(String str) {
        int length = str.length();
        if (length <= 0 || (isNameStartChar(str.charAt(0)) && str.charAt(0) != ':')) {
            for (int i = 1; i < length; i++) {
                if (isNameChar(str.charAt(i)) && str.charAt(i) != ':') {
                }
            }
            return true;
        }
        return false;
    }
}
