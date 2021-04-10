package java.net;

import java.io.UnsupportedEncodingException;

public class URLDecoder {
    static String dfltEncName = URLEncoder.dfltEncName;

    @Deprecated
    public static String decode(String s) {
        try {
            return decode(s, dfltEncName);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decode(String s, String enc) throws UnsupportedEncodingException {
        boolean needToChange = false;
        int numChars = s.length();
        StringBuffer sb = new StringBuffer(numChars > 500 ? numChars / 2 : numChars);
        int i = 0;
        if (enc.length() != 0) {
            byte[] bytes = null;
            while (i < numChars) {
                char c = s.charAt(i);
                if (c == '%') {
                    if (bytes == null) {
                        try {
                            bytes = new byte[((numChars - i) / 3)];
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - " + e.getMessage());
                        }
                    }
                    int pos = 0;
                    while (i + 2 < numChars && c == '%') {
                        if (!isValidHexChar(s.charAt(i + 1)) || !isValidHexChar(s.charAt(i + 2))) {
                            throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern : " + s.substring(i, i + 3));
                        }
                        int v = Integer.parseInt(s.substring(i + 1, i + 3), 16);
                        if (v >= 0) {
                            int pos2 = pos + 1;
                            bytes[pos] = (byte) v;
                            i += 3;
                            if (i < numChars) {
                                c = s.charAt(i);
                            }
                            pos = pos2;
                        } else {
                            throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - negative value : " + s.substring(i, i + 3));
                        }
                    }
                    if (i < numChars) {
                        if (c == '%') {
                            throw new IllegalArgumentException("URLDecoder: Incomplete trailing escape (%) pattern");
                        }
                    }
                    sb.append(new String(bytes, 0, pos, enc));
                    needToChange = true;
                } else if (c != '+') {
                    sb.append(c);
                    i++;
                } else {
                    sb.append(' ');
                    i++;
                    needToChange = true;
                }
            }
            return needToChange ? sb.toString() : s;
        }
        throw new UnsupportedEncodingException("URLDecoder: empty string enc parameter");
    }

    private static boolean isValidHexChar(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }
}
