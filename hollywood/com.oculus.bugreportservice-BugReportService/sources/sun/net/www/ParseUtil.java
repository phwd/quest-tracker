package sun.net.www;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.BitSet;
import sun.nio.cs.ThreadLocalCoders;

public class ParseUtil {
    private static final long H_ALPHA = (H_LOWALPHA | H_UPALPHA);
    private static final long H_ALPHANUM = (H_ALPHA | 0);
    private static final long H_DASH = highMask("-");
    private static final long H_HEX = (highMask('A', 'F') | highMask('a', 'f'));
    private static final long H_LOWALPHA = highMask('a', 'z');
    private static final long H_MARK = highMask("-_.!~*'()");
    private static final long H_PATH = (H_PCHAR | highMask(";/"));
    private static final long H_PCHAR = ((H_UNRESERVED | 0) | highMask(":@&=+$,"));
    private static final long H_REG_NAME = ((H_UNRESERVED | 0) | highMask("$,;:@&=+"));
    private static final long H_RESERVED = highMask(";/?:@&=+$,[]");
    private static final long H_SERVER = (((H_USERINFO | H_ALPHANUM) | H_DASH) | highMask(".:@[]"));
    private static final long H_UNRESERVED = (H_ALPHANUM | H_MARK);
    private static final long H_UPALPHA = highMask('A', 'Z');
    private static final long H_URIC = ((H_RESERVED | H_UNRESERVED) | 0);
    private static final long H_USERINFO = ((H_UNRESERVED | 0) | highMask(";:&=+$,"));
    private static final long L_ALPHANUM = (L_DIGIT | 0);
    private static final long L_DASH = lowMask("-");
    private static final long L_DIGIT = lowMask('0', '9');
    private static final long L_HEX = L_DIGIT;
    private static final long L_MARK = lowMask("-_.!~*'()");
    private static final long L_PATH = (L_PCHAR | lowMask(";/"));
    private static final long L_PCHAR;
    private static final long L_REG_NAME = ((L_UNRESERVED | 1) | lowMask("$,;:@&=+"));
    private static final long L_RESERVED = lowMask(";/?:@&=+$,[]");
    private static final long L_SERVER = (((L_USERINFO | L_ALPHANUM) | L_DASH) | lowMask(".:@[]"));
    private static final long L_UNRESERVED = (L_ALPHANUM | L_MARK);
    private static final long L_URIC;
    private static final long L_USERINFO = ((L_UNRESERVED | 1) | lowMask(";:&=+$,"));
    static BitSet encodedInPath = new BitSet(256);
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static boolean match(char c, long j, long j2) {
        if (c < '@') {
            return ((1 << c) & j) != 0;
        }
        if (c < 128) {
            return ((1 << (c - 64)) & j2) != 0;
        }
        return false;
    }

    static {
        encodedInPath.set(61);
        encodedInPath.set(59);
        encodedInPath.set(63);
        encodedInPath.set(47);
        encodedInPath.set(35);
        encodedInPath.set(32);
        encodedInPath.set(60);
        encodedInPath.set(62);
        encodedInPath.set(37);
        encodedInPath.set(34);
        encodedInPath.set(123);
        encodedInPath.set(125);
        encodedInPath.set(124);
        encodedInPath.set(92);
        encodedInPath.set(94);
        encodedInPath.set(91);
        encodedInPath.set(93);
        encodedInPath.set(96);
        for (int i = 0; i < 32; i++) {
            encodedInPath.set(i);
        }
        encodedInPath.set(127);
        long j = L_RESERVED;
        long j2 = L_UNRESERVED;
        L_URIC = j | j2 | 1;
        L_PCHAR = j2 | 1 | lowMask(":@&=+$,");
    }

    public static String encodePath(String str) {
        encodePath(str, true);
        throw null;
    }

    public static String encodePath(String str, boolean z) {
        int i;
        char[] charArray = str.toCharArray();
        int length = str.length();
        char[] cArr = new char[((str.length() * 2) + 16)];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char c = charArray[i3];
            if ((!z && c == '/') || (z && c == File.separatorChar)) {
                cArr[i2] = '/';
                i2++;
            } else if (c <= 127) {
                if ((c >= 'a' && c <= 'z') || ((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                    i = i2 + 1;
                    cArr[i2] = c;
                } else if (encodedInPath.get(c)) {
                    i2 = escape(cArr, c, i2);
                } else {
                    i = i2 + 1;
                    cArr[i2] = c;
                }
                i2 = i;
            } else if (c > 2047) {
                i2 = escape(cArr, (char) (((c >> 0) & 63) | 128), escape(cArr, (char) (((c >> 6) & 63) | 128), escape(cArr, (char) (((c >> '\f') & 15) | 224), i2)));
            } else {
                i2 = escape(cArr, (char) (((c >> 0) & 63) | 128), escape(cArr, (char) (((c >> 6) & 31) | 192), i2));
            }
            if (i2 + 9 > cArr.length) {
                int length2 = (cArr.length * 2) + 16;
                if (length2 < 0) {
                    length2 = Integer.MAX_VALUE;
                }
                char[] cArr2 = new char[length2];
                System.arraycopy((Object) cArr, 0, (Object) cArr2, 0, i2);
                cArr = cArr2;
            }
        }
        new String(cArr, 0, i2);
        throw null;
    }

    private static int escape(char[] cArr, char c, int i) {
        int i2 = i + 1;
        cArr[i] = '%';
        int i3 = i2 + 1;
        cArr[i2] = Character.forDigit((c >> 4) & 15, 16);
        int i4 = i3 + 1;
        cArr[i3] = Character.forDigit(c & 15, 16);
        return i4;
    }

    private static byte unescape(String str, int i) {
        return (byte) Integer.parseInt(str.substring(i + 1, i + 3), 16);
    }

    public static String decode(String str) {
        int length = str.length();
        if (length == 0 || str.indexOf(37) < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        ByteBuffer allocate = ByteBuffer.allocate(length);
        CharBuffer allocate2 = CharBuffer.allocate(length);
        CharsetDecoder onUnmappableCharacter = ThreadLocalCoders.decoderFor("UTF-8").onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        int i = 0;
        char charAt = str.charAt(0);
        while (i < length) {
            if (charAt != '%') {
                sb.append(charAt);
                i++;
                if (i >= length) {
                    break;
                }
                charAt = str.charAt(i);
            } else {
                allocate.clear();
                do {
                    try {
                        allocate.put(unescape(str, i));
                        i += 3;
                        if (i >= length) {
                            break;
                        }
                        charAt = str.charAt(i);
                    } catch (NumberFormatException unused) {
                        throw new IllegalArgumentException();
                    }
                } while (charAt == '%');
                allocate.flip();
                allocate2.clear();
                onUnmappableCharacter.reset();
                if (onUnmappableCharacter.decode(allocate, allocate2, true).isError()) {
                    throw new IllegalArgumentException("Error decoding percent encoded characters");
                } else if (!onUnmappableCharacter.flush(allocate2).isError()) {
                    allocate2.flip();
                    sb.append(allocate2.toString());
                } else {
                    throw new IllegalArgumentException("Error decoding percent encoded characters");
                }
            }
        }
        return sb.toString();
    }

    public String canonizeString(String str) {
        str.length();
        while (true) {
            int indexOf = str.indexOf("/../");
            if (indexOf < 0) {
                break;
            }
            int lastIndexOf = str.lastIndexOf(47, indexOf - 1);
            if (lastIndexOf >= 0) {
                str = str.substring(0, lastIndexOf) + str.substring(indexOf + 3);
            } else {
                str = str.substring(indexOf + 3);
            }
        }
        while (true) {
            int indexOf2 = str.indexOf("/./");
            if (indexOf2 < 0) {
                break;
            }
            str = str.substring(0, indexOf2) + str.substring(indexOf2 + 2);
        }
        while (str.endsWith("/..")) {
            int indexOf3 = str.indexOf("/..");
            int lastIndexOf2 = str.lastIndexOf(47, indexOf3 - 1);
            if (lastIndexOf2 >= 0) {
                str = str.substring(0, lastIndexOf2 + 1);
            } else {
                str = str.substring(0, indexOf3);
            }
        }
        return str.endsWith("/.") ? str.substring(0, str.length() - 1) : str;
    }

    public static URI toURI(URL url) {
        String protocol = url.getProtocol();
        String authority = url.getAuthority();
        String path = url.getPath();
        String query = url.getQuery();
        String ref = url.getRef();
        if (path != null && !path.startsWith("/")) {
            path = "/" + path;
        }
        if (authority != null && authority.endsWith(":-1")) {
            authority = authority.substring(0, authority.length() - 3);
        }
        try {
            return createURI(protocol, authority, path, query, ref);
        } catch (URISyntaxException unused) {
            return null;
        }
    }

    private static URI createURI(String str, String str2, String str3, String str4, String str5) {
        String parseUtil = toString(str, null, str2, null, null, -1, str3, str4, str5);
        checkPath(parseUtil, str, str3);
        return new URI(parseUtil);
    }

    private static String toString(String str, String str2, String str3, String str4, String str5, int i, String str6, String str7, String str8) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
            stringBuffer.append(':');
        }
        appendSchemeSpecificPart(stringBuffer, str2, str3, str4, str5, i, str6, str7);
        appendFragment(stringBuffer, str8);
        return stringBuffer.toString();
    }

    private static void appendSchemeSpecificPart(StringBuffer stringBuffer, String str, String str2, String str3, String str4, int i, String str5, String str6) {
        String str7;
        if (str == null) {
            appendAuthority(stringBuffer, str2, str3, str4, i);
            if (str5 != null) {
                stringBuffer.append(quote(str5, L_PATH, H_PATH));
            }
            if (str6 != null) {
                stringBuffer.append('?');
                stringBuffer.append(quote(str6, L_URIC, H_URIC));
            }
        } else if (str.startsWith("//[")) {
            int indexOf = str.indexOf("]");
            if (indexOf != -1 && str.indexOf(":") != -1) {
                if (indexOf == str.length()) {
                    str7 = "";
                } else {
                    int i2 = indexOf + 1;
                    String substring = str.substring(0, i2);
                    str7 = str.substring(i2);
                    str = substring;
                }
                stringBuffer.append(str);
                stringBuffer.append(quote(str7, L_URIC, H_URIC));
            }
        } else {
            stringBuffer.append(quote(str, L_URIC, H_URIC));
        }
    }

    private static void appendAuthority(StringBuffer stringBuffer, String str, String str2, String str3, int i) {
        String str4;
        boolean z = true;
        if (str3 != null) {
            stringBuffer.append("//");
            if (str2 != null) {
                stringBuffer.append(quote(str2, L_USERINFO, H_USERINFO));
                stringBuffer.append('@');
            }
            if (str3.indexOf(58) < 0 || str3.startsWith("[") || str3.endsWith("]")) {
                z = false;
            }
            if (z) {
                stringBuffer.append('[');
            }
            stringBuffer.append(str3);
            if (z) {
                stringBuffer.append(']');
            }
            if (i != -1) {
                stringBuffer.append(':');
                stringBuffer.append(i);
            }
        } else if (str != null) {
            stringBuffer.append("//");
            if (str.startsWith("[")) {
                int indexOf = str.indexOf("]");
                if (indexOf != -1 && str.indexOf(":") != -1) {
                    if (indexOf == str.length()) {
                        str4 = "";
                    } else {
                        int i2 = indexOf + 1;
                        String substring = str.substring(0, i2);
                        str4 = str.substring(i2);
                        str = substring;
                    }
                    stringBuffer.append(str);
                    stringBuffer.append(quote(str4, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
                    return;
                }
                return;
            }
            stringBuffer.append(quote(str, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
        }
    }

    private static void appendFragment(StringBuffer stringBuffer, String str) {
        if (str != null) {
            stringBuffer.append('#');
            stringBuffer.append(quote(str, L_URIC, H_URIC));
        }
    }

    private static String quote(String str, long j, long j2) {
        str.length();
        boolean z = (1 & j) != 0;
        StringBuffer stringBuffer = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < 128) {
                if (!match(charAt, j, j2) && !isEscaped(str, i)) {
                    if (stringBuffer == null) {
                        stringBuffer = new StringBuffer();
                        stringBuffer.append(str.substring(0, i));
                    }
                    appendEscape(stringBuffer, (byte) charAt);
                } else if (stringBuffer != null) {
                    stringBuffer.append(charAt);
                }
            } else if (z && (Character.isSpaceChar(charAt) || Character.isISOControl(charAt))) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer();
                    stringBuffer.append(str.substring(0, i));
                }
                appendEncoded(stringBuffer, charAt);
            } else if (stringBuffer != null) {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer == null ? str : stringBuffer.toString();
    }

    private static boolean isEscaped(String str, int i) {
        int i2;
        if (str == null || str.length() <= (i2 = i + 2) || str.charAt(i) != '%' || !match(str.charAt(i + 1), L_HEX, H_HEX) || !match(str.charAt(i2), L_HEX, H_HEX)) {
            return false;
        }
        return true;
    }

    private static void appendEncoded(StringBuffer stringBuffer, char c) {
        ByteBuffer byteBuffer;
        try {
            CharsetEncoder encoderFor = ThreadLocalCoders.encoderFor("UTF-8");
            byteBuffer = encoderFor.encode(CharBuffer.wrap("" + c));
        } catch (CharacterCodingException unused) {
            byteBuffer = null;
        }
        while (byteBuffer.hasRemaining()) {
            int i = byteBuffer.get() & 255;
            if (i >= 128) {
                appendEscape(stringBuffer, (byte) i);
            } else {
                stringBuffer.append((char) i);
            }
        }
    }

    private static void appendEscape(StringBuffer stringBuffer, byte b) {
        stringBuffer.append('%');
        stringBuffer.append(hexDigits[(b >> 4) & 15]);
        stringBuffer.append(hexDigits[(b >> 0) & 15]);
    }

    private static void checkPath(String str, String str2, String str3) {
        if (str2 != null && str3 != null && str3.length() > 0 && str3.charAt(0) != '/') {
            throw new URISyntaxException(str, "Relative path in absolute URI");
        }
    }

    private static long lowMask(char c, char c2) {
        long j = 0;
        for (int max = Math.max(Math.min((int) c, 63), 0); max <= Math.max(Math.min((int) c2, 63), 0); max++) {
            j |= 1 << max;
        }
        return j;
    }

    private static long lowMask(String str) {
        int length = str.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt < '@') {
                j |= 1 << charAt;
            }
        }
        return j;
    }

    private static long highMask(char c, char c2) {
        long j = 0;
        for (int max = Math.max(Math.min((int) c, 127), 64) - 64; max <= Math.max(Math.min((int) c2, 127), 64) - 64; max++) {
            j |= 1 << max;
        }
        return j;
    }

    private static long highMask(String str) {
        int length = str.length();
        long j = 0;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= '@' && charAt < 128) {
                j |= 1 << (charAt - '@');
            }
        }
        return j;
    }
}
