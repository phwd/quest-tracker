package com.android.org.bouncycastle.util;

import com.android.org.bouncycastle.util.encoders.UTF8;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Vector;

public final class Strings {
    private static String LINE_SEPARATOR;

    static {
        try {
            LINE_SEPARATOR = (String) AccessController.doPrivileged(new PrivilegedAction<String>() {
                /* class com.android.org.bouncycastle.util.Strings.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public String run() {
                    return System.getProperty("line.separator");
                }
            });
        } catch (Exception e) {
            try {
                LINE_SEPARATOR = String.format("%n", new Object[0]);
            } catch (Exception e2) {
                LINE_SEPARATOR = "\n";
            }
        }
    }

    public static String fromUTF8ByteArray(byte[] bytes) {
        char[] chars = new char[bytes.length];
        int len = UTF8.transcodeToUTF16(bytes, chars);
        if (len >= 0) {
            return new String(chars, 0, len);
        }
        throw new IllegalArgumentException("Invalid UTF-8 input");
    }

    public static byte[] toUTF8ByteArray(String string) {
        return toUTF8ByteArray(string.toCharArray());
    }

    public static byte[] toUTF8ByteArray(char[] string) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try {
            toUTF8ByteArray(string, bOut);
            return bOut.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static void toUTF8ByteArray(char[] string, OutputStream sOut) throws IOException {
        int i = 0;
        while (i < string.length) {
            char ch = string[i];
            if (ch < 128) {
                sOut.write(ch);
            } else if (ch < 2048) {
                sOut.write((ch >> 6) | 192);
                sOut.write(128 | (ch & '?'));
            } else if (ch < 55296 || ch > 57343) {
                sOut.write((ch >> '\f') | 224);
                sOut.write(((ch >> 6) & 63) | 128);
                sOut.write(128 | (ch & '?'));
            } else if (i + 1 < string.length) {
                i++;
                char ch2 = string[i];
                if (ch <= 56319) {
                    int codePoint = (((ch & 1023) << 10) | (ch2 & 1023)) + 65536;
                    sOut.write((codePoint >> 18) | 240);
                    sOut.write(((codePoint >> 12) & 63) | 128);
                    sOut.write(((codePoint >> 6) & 63) | 128);
                    sOut.write(128 | (codePoint & 63));
                } else {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
            } else {
                throw new IllegalStateException("invalid UTF-16 codepoint");
            }
            i++;
        }
    }

    public static String toUpperCase(String string) {
        boolean changed = false;
        char[] chars = string.toCharArray();
        for (int i = 0; i != chars.length; i++) {
            char ch = chars[i];
            if ('a' <= ch && 'z' >= ch) {
                changed = true;
                chars[i] = (char) ((ch - 'a') + 65);
            }
        }
        if (changed) {
            return new String(chars);
        }
        return string;
    }

    public static String toLowerCase(String string) {
        boolean changed = false;
        char[] chars = string.toCharArray();
        for (int i = 0; i != chars.length; i++) {
            char ch = chars[i];
            if ('A' <= ch && 'Z' >= ch) {
                changed = true;
                chars[i] = (char) ((ch - 'A') + 97);
            }
        }
        if (changed) {
            return new String(chars);
        }
        return string;
    }

    public static byte[] toByteArray(char[] chars) {
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i != bytes.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        return bytes;
    }

    public static byte[] toByteArray(String string) {
        byte[] bytes = new byte[string.length()];
        for (int i = 0; i != bytes.length; i++) {
            bytes[i] = (byte) string.charAt(i);
        }
        return bytes;
    }

    public static int toByteArray(String s, byte[] buf, int off) {
        int count = s.length();
        for (int i = 0; i < count; i++) {
            buf[off + i] = (byte) s.charAt(i);
        }
        return count;
    }

    public static String fromByteArray(byte[] bytes) {
        return new String(asCharArray(bytes));
    }

    public static char[] asCharArray(byte[] bytes) {
        char[] chars = new char[bytes.length];
        for (int i = 0; i != chars.length; i++) {
            chars[i] = (char) (bytes[i] & UnsignedBytes.MAX_VALUE);
        }
        return chars;
    }

    public static String[] split(String input, char delimiter) {
        Vector v = new Vector();
        boolean moreTokens = true;
        while (moreTokens) {
            int tokenLocation = input.indexOf(delimiter);
            if (tokenLocation > 0) {
                v.addElement(input.substring(0, tokenLocation));
                input = input.substring(tokenLocation + 1);
            } else {
                moreTokens = false;
                v.addElement(input);
            }
        }
        String[] res = new String[v.size()];
        for (int i = 0; i != res.length; i++) {
            res[i] = (String) v.elementAt(i);
        }
        return res;
    }

    public static StringList newList() {
        return new StringListImpl();
    }

    public static String lineSeparator() {
        return LINE_SEPARATOR;
    }

    private static class StringListImpl extends ArrayList<String> implements StringList {
        private StringListImpl() {
        }

        @Override // java.util.List, com.android.org.bouncycastle.util.StringList, java.util.AbstractList, java.util.ArrayList
        public /* bridge */ /* synthetic */ String get(int x0) {
            return (String) super.get(x0);
        }

        @Override // com.android.org.bouncycastle.util.StringList
        public boolean add(String s) {
            return super.add((Object) s);
        }

        public String set(int index, String element) {
            return (String) super.set(index, (Object) element);
        }

        public void add(int index, String element) {
            super.add(index, (Object) element);
        }

        @Override // com.android.org.bouncycastle.util.StringList
        public String[] toStringArray() {
            String[] strs = new String[size()];
            for (int i = 0; i != strs.length; i++) {
                strs[i] = (String) get(i);
            }
            return strs;
        }

        @Override // com.android.org.bouncycastle.util.StringList
        public String[] toStringArray(int from, int to) {
            String[] strs = new String[(to - from)];
            int i = from;
            while (i != size() && i != to) {
                strs[i - from] = (String) get(i);
                i++;
            }
            return strs;
        }
    }
}
