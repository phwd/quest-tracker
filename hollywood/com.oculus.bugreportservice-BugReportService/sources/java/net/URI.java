package java.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.text.Normalizer;
import sun.nio.cs.ThreadLocalCoders;

public final class URI implements Comparable, Serializable {
    private static final long H_ALPHA = (H_LOWALPHA | H_UPALPHA);
    private static final long H_ALPHANUM = (H_ALPHA | 0);
    private static final long H_DASH = highMask("-");
    private static final long H_DOT = highMask(".");
    private static final long H_HEX = (highMask('A', 'F') | highMask('a', 'f'));
    private static final long H_LEFT_BRACKET = highMask("[");
    private static final long H_LOWALPHA = highMask('a', 'z');
    private static final long H_MARK = highMask("-_.!~*'()");
    private static final long H_PATH = (H_PCHAR | highMask(";/"));
    private static final long H_PCHAR = ((H_UNRESERVED | 0) | highMask(":@&=+$,"));
    private static final long H_REG_NAME = ((H_UNRESERVED | 0) | highMask("$,;:@&=+"));
    private static final long H_RESERVED = highMask(";/?:@&=+$,[]");
    private static final long H_SCHEME = ((H_ALPHA | 0) | highMask("+-."));
    private static final long H_SERVER = (((H_USERINFO | H_ALPHANUM) | H_DASH) | highMask(".:@[]"));
    private static final long H_SERVER_PERCENT = (H_SERVER | highMask("%"));
    private static final long H_UNDERSCORE = highMask("_");
    private static final long H_UNRESERVED = (H_ALPHANUM | H_MARK);
    private static final long H_UPALPHA = highMask('A', 'Z');
    private static final long H_URIC = ((H_RESERVED | H_UNRESERVED) | 0);
    private static final long H_URIC_NO_SLASH = ((H_UNRESERVED | 0) | highMask(";?:@&=+$,"));
    private static final long H_USERINFO = ((H_UNRESERVED | 0) | highMask(";:&=+$,"));
    private static final long L_ALPHANUM;
    private static final long L_DASH = lowMask("-");
    private static final long L_DIGIT = lowMask('0', '9');
    private static final long L_DOT = lowMask(".");
    private static final long L_HEX;
    private static final long L_LEFT_BRACKET = lowMask("[");
    private static final long L_MARK = lowMask("-_.!~*'()");
    private static final long L_PATH = (L_PCHAR | lowMask(";/"));
    private static final long L_PCHAR;
    private static final long L_REG_NAME = ((L_UNRESERVED | 1) | lowMask("$,;:@&=+"));
    private static final long L_RESERVED = lowMask(";/?:@&=+$,[]");
    private static final long L_SCHEME = ((L_DIGIT | 0) | lowMask("+-."));
    private static final long L_SERVER = (((L_USERINFO | L_ALPHANUM) | L_DASH) | lowMask(".:@[]"));
    private static final long L_SERVER_PERCENT = (L_SERVER | lowMask("%"));
    private static final long L_UNDERSCORE = lowMask("_");
    private static final long L_UNRESERVED = (L_ALPHANUM | L_MARK);
    private static final long L_URIC;
    private static final long L_URIC_NO_SLASH = ((L_UNRESERVED | 1) | lowMask(";?:@&=+$,"));
    private static final long L_USERINFO = ((L_UNRESERVED | 1) | lowMask(";:&=+$,"));
    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final long serialVersionUID = -6052424284110960213L;
    private transient String authority;
    private volatile transient String decodedAuthority;
    private volatile transient String decodedFragment;
    private volatile transient String decodedPath;
    private volatile transient String decodedQuery;
    private volatile transient String decodedSchemeSpecificPart;
    private volatile transient String decodedUserInfo;
    private transient String fragment;
    private volatile transient int hash;
    private transient String host;
    private transient String path;
    private transient int port;
    private transient String query;
    private transient String scheme;
    private volatile transient String schemeSpecificPart;
    private volatile String string;
    private transient String userInfo;

    private static int decode(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    /* access modifiers changed from: private */
    public static boolean match(char c, long j, long j2) {
        if (c == 0) {
            return false;
        }
        return c < '@' ? ((1 << c) & j) != 0 : c < 128 && ((1 << (c - 64)) & j2) != 0;
    }

    private static int toLower(char c) {
        return (c < 'A' || c > 'Z') ? c : c + ' ';
    }

    private static int toUpper(char c) {
        return (c < 'a' || c > 'z') ? c : c - ' ';
    }

    private URI() {
        this.port = -1;
        this.decodedUserInfo = null;
        this.decodedAuthority = null;
        this.decodedPath = null;
        this.decodedQuery = null;
        this.decodedFragment = null;
        this.decodedSchemeSpecificPart = null;
    }

    public URI(String str) {
        this.port = -1;
        this.decodedUserInfo = null;
        this.decodedAuthority = null;
        this.decodedPath = null;
        this.decodedQuery = null;
        this.decodedFragment = null;
        this.decodedSchemeSpecificPart = null;
        new Parser(str).parse(false);
    }

    public URI(String str, String str2, String str3, int i, String str4, String str5, String str6) {
        this.port = -1;
        this.decodedUserInfo = null;
        this.decodedAuthority = null;
        this.decodedPath = null;
        this.decodedQuery = null;
        this.decodedFragment = null;
        this.decodedSchemeSpecificPart = null;
        String uri = toString(str, null, null, str2, str3, i, str4, str5, str6);
        checkPath(uri, str, str4);
        new Parser(uri).parse(true);
    }

    public URI(String str, String str2, String str3, String str4) {
        this(str, null, str2, -1, str3, null, str4);
    }

    public static URI create(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public URL toURL() {
        if (isAbsolute()) {
            return new URL(toString());
        }
        throw new IllegalArgumentException("URI is not absolute");
    }

    public String getScheme() {
        return this.scheme;
    }

    public boolean isAbsolute() {
        return this.scheme != null;
    }

    public boolean isOpaque() {
        return this.path == null;
    }

    public String getAuthority() {
        if (this.decodedAuthority == null) {
            this.decodedAuthority = decode(this.authority);
        }
        return this.decodedAuthority;
    }

    public String getHost() {
        return this.host;
    }

    public String getPath() {
        String str;
        if (this.decodedPath == null && (str = this.path) != null) {
            this.decodedPath = decode(str);
        }
        return this.decodedPath;
    }

    public String getQuery() {
        String str;
        if (this.decodedQuery == null && (str = this.query) != null) {
            this.decodedQuery = decode(str);
        }
        return this.decodedQuery;
    }

    public String getFragment() {
        String str;
        if (this.decodedFragment == null && (str = this.fragment) != null) {
            this.decodedFragment = decode(str);
        }
        return this.decodedFragment;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof URI)) {
            return false;
        }
        URI uri = (URI) obj;
        if (isOpaque() != uri.isOpaque() || !equalIgnoringCase(this.scheme, uri.scheme) || !equal(this.fragment, uri.fragment)) {
            return false;
        }
        if (isOpaque()) {
            return equal(this.schemeSpecificPart, uri.schemeSpecificPart);
        }
        if (!equal(this.path, uri.path) || !equal(this.query, uri.query)) {
            return false;
        }
        String str = this.authority;
        String str2 = uri.authority;
        if (str == str2) {
            return true;
        }
        if (this.host != null) {
            return equal(this.userInfo, uri.userInfo) && equalIgnoringCase(this.host, uri.host) && this.port == uri.port;
        }
        if (str != null) {
            if (!equal(str, str2)) {
                return false;
            }
        } else if (str != str2) {
            return false;
        }
    }

    public int hashCode() {
        int i;
        if (this.hash != 0) {
            return this.hash;
        }
        int hash2 = hash(hashIgnoringCase(0, this.scheme), this.fragment);
        if (isOpaque()) {
            i = hash(hash2, this.schemeSpecificPart);
        } else {
            int hash3 = hash(hash(hash2, this.path), this.query);
            if (this.host != null) {
                i = hashIgnoringCase(hash(hash3, this.userInfo), this.host) + (this.port * 1949);
            } else {
                i = hash(hash3, this.authority);
            }
        }
        this.hash = i;
        return i;
    }

    public int compareTo(URI uri) {
        int compareIgnoringCase = compareIgnoringCase(this.scheme, uri.scheme);
        if (compareIgnoringCase != 0) {
            return compareIgnoringCase;
        }
        if (isOpaque()) {
            if (!uri.isOpaque()) {
                return 1;
            }
            int compare = compare(this.schemeSpecificPart, uri.schemeSpecificPart);
            if (compare != 0) {
                return compare;
            }
            return compare(this.fragment, uri.fragment);
        } else if (uri.isOpaque()) {
            return -1;
        } else {
            if (this.host == null || uri.host == null) {
                int compare2 = compare(this.authority, uri.authority);
                if (compare2 != 0) {
                    return compare2;
                }
            } else {
                int compare3 = compare(this.userInfo, uri.userInfo);
                if (compare3 != 0) {
                    return compare3;
                }
                int compareIgnoringCase2 = compareIgnoringCase(this.host, uri.host);
                if (compareIgnoringCase2 != 0) {
                    return compareIgnoringCase2;
                }
                int i = this.port - uri.port;
                if (i != 0) {
                    return i;
                }
            }
            int compare4 = compare(this.path, uri.path);
            if (compare4 != 0) {
                return compare4;
            }
            int compare5 = compare(this.query, uri.query);
            if (compare5 != 0) {
                return compare5;
            }
            return compare(this.fragment, uri.fragment);
        }
    }

    public String toString() {
        defineString();
        return this.string;
    }

    public String toASCIIString() {
        defineString();
        return encode(this.string);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        defineString();
        objectOutputStream.defaultWriteObject();
        throw null;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.port = -1;
        objectInputStream.defaultReadObject();
        throw null;
    }

    private static boolean equal(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null || str.length() != str2.length()) {
            return false;
        }
        if (str.indexOf(37) < 0) {
            return str.equals(str2);
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            char charAt2 = str2.charAt(i);
            if (charAt != '%') {
                if (charAt != charAt2) {
                    return false;
                }
            } else if (charAt2 != '%') {
                return false;
            } else {
                int i2 = i + 1;
                if (toLower(str.charAt(i2)) != toLower(str2.charAt(i2))) {
                    return false;
                }
                i = i2 + 1;
                if (toLower(str.charAt(i)) != toLower(str2.charAt(i))) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    private static boolean equalIgnoringCase(String str, String str2) {
        int length;
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null || str2.length() != (length = str.length())) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (toLower(str.charAt(i)) != toLower(str2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int hash(int i, String str) {
        if (str == null) {
            return i;
        }
        if (str.indexOf(37) < 0) {
            return (i * 127) + str.hashCode();
        }
        return normalizedHash(i, str);
    }

    private static int normalizedHash(int i, String str) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            i3 = (i3 * 31) + charAt;
            if (charAt == '%') {
                for (int i4 = i2 + 1; i4 < i2 + 3; i4++) {
                    i3 = (i3 * 31) + toUpper(str.charAt(i4));
                }
                i2 += 2;
            }
            i2++;
        }
        return (i * 127) + i3;
    }

    private static int hashIgnoringCase(int i, String str) {
        if (str == null) {
            return i;
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 31) + toLower(str.charAt(i2));
        }
        return i;
    }

    private static int compare(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 != null) {
            return str.compareTo(str2);
        }
        return 1;
    }

    private static int compareIgnoringCase(String str, String str2) {
        if (str == str2) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        int length = str.length();
        int length2 = str2.length();
        int i = length < length2 ? length : length2;
        for (int i2 = 0; i2 < i; i2++) {
            int lower = toLower(str.charAt(i2)) - toLower(str2.charAt(i2));
            if (lower != 0) {
                return lower;
            }
        }
        return length - length2;
    }

    private static void checkPath(String str, String str2, String str3) {
        if (str2 != null && str3 != null && str3.length() > 0 && str3.charAt(0) != '/') {
            throw new URISyntaxException(str, "Relative path in absolute URI");
        }
    }

    private void appendAuthority(StringBuffer stringBuffer, String str, String str2, String str3, int i) {
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
                if (indexOf == -1 || str.indexOf(":") == -1) {
                    str4 = "";
                } else if (indexOf == str.length()) {
                    str4 = str;
                    str = "";
                } else {
                    int i2 = indexOf + 1;
                    str4 = str.substring(0, i2);
                    str = str.substring(i2);
                }
                stringBuffer.append(str4);
                stringBuffer.append(quote(str, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
                return;
            }
            stringBuffer.append(quote(str, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
        }
    }

    private void appendSchemeSpecificPart(StringBuffer stringBuffer, String str, String str2, String str3, String str4, int i, String str5, String str6) {
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

    private void appendFragment(StringBuffer stringBuffer, String str) {
        if (str != null) {
            stringBuffer.append('#');
            stringBuffer.append(quote(str, L_URIC, H_URIC));
        }
    }

    private String toString(String str, String str2, String str3, String str4, String str5, int i, String str6, String str7, String str8) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
            stringBuffer.append(':');
        }
        appendSchemeSpecificPart(stringBuffer, str2, str3, str4, str5, i, str6, str7);
        appendFragment(stringBuffer, str8);
        return stringBuffer.toString();
    }

    private void defineString() {
        if (this.string == null) {
            StringBuffer stringBuffer = new StringBuffer();
            String str = this.scheme;
            if (str != null) {
                stringBuffer.append(str);
                stringBuffer.append(':');
            }
            if (isOpaque()) {
                stringBuffer.append(this.schemeSpecificPart);
            } else {
                if (this.host != null) {
                    stringBuffer.append("//");
                    String str2 = this.userInfo;
                    if (str2 != null) {
                        stringBuffer.append(str2);
                        stringBuffer.append('@');
                    }
                    boolean z = this.host.indexOf(58) >= 0 && !this.host.startsWith("[") && !this.host.endsWith("]");
                    if (z) {
                        stringBuffer.append('[');
                    }
                    stringBuffer.append(this.host);
                    if (z) {
                        stringBuffer.append(']');
                    }
                    if (this.port != -1) {
                        stringBuffer.append(':');
                        stringBuffer.append(this.port);
                    }
                } else if (this.authority != null) {
                    stringBuffer.append("//");
                    stringBuffer.append(this.authority);
                }
                String str3 = this.path;
                if (str3 != null) {
                    stringBuffer.append(str3);
                }
                if (this.query != null) {
                    stringBuffer.append('?');
                    stringBuffer.append(this.query);
                }
            }
            if (this.fragment != null) {
                stringBuffer.append('#');
                stringBuffer.append(this.fragment);
            }
            this.string = stringBuffer.toString();
        }
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

    private static long lowMask(char c, char c2) {
        long j = 0;
        for (int max = Math.max(Math.min((int) c, 63), 0); max <= Math.max(Math.min((int) c2, 63), 0); max++) {
            j |= 1 << max;
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

    static {
        long j = L_DIGIT;
        L_ALPHANUM = j | 0;
        L_HEX = j;
        long j2 = L_RESERVED;
        long j3 = L_UNRESERVED;
        L_URIC = j2 | j3 | 1;
        L_PCHAR = j3 | 1 | lowMask(":@&=+$,");
    }

    private static void appendEscape(StringBuffer stringBuffer, byte b) {
        stringBuffer.append('%');
        stringBuffer.append(hexDigits[(b >> 4) & 15]);
        stringBuffer.append(hexDigits[(b >> 0) & 15]);
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

    private static String quote(String str, long j, long j2) {
        str.length();
        boolean z = (1 & j) != 0;
        StringBuffer stringBuffer = null;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < 128) {
                if (!match(charAt, j, j2)) {
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

    private static String encode(String str) {
        int length = str.length();
        if (length == 0) {
            return str;
        }
        int i = 0;
        while (str.charAt(i) < 128) {
            i++;
            if (i >= length) {
                return str;
            }
        }
        ByteBuffer byteBuffer = null;
        try {
            byteBuffer = ThreadLocalCoders.encoderFor("UTF-8").encode(CharBuffer.wrap(Normalizer.normalize(str, Normalizer.Form.NFC)));
        } catch (CharacterCodingException unused) {
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (byteBuffer.hasRemaining()) {
            int i2 = byteBuffer.get() & 255;
            if (i2 >= 128) {
                appendEscape(stringBuffer, (byte) i2);
            } else {
                stringBuffer.append((char) i2);
            }
        }
        return stringBuffer.toString();
    }

    private static byte decode(char c, char c2) {
        return (byte) (((decode(c) & 15) << 4) | ((decode(c2) & 15) << 0));
    }

    private static String decode(String str) {
        int length;
        if (str == null || (length = str.length()) == 0 || str.indexOf(37) < 0) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(length);
        ByteBuffer allocate = ByteBuffer.allocate(length);
        CharBuffer allocate2 = CharBuffer.allocate(length);
        CharsetDecoder onUnmappableCharacter = ThreadLocalCoders.decoderFor("UTF-8").onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        boolean z = false;
        char charAt = str.charAt(0);
        int i = 0;
        while (i < length) {
            if (charAt == '[') {
                z = true;
            } else if (z && charAt == ']') {
                z = false;
            }
            if (charAt != '%' || z) {
                stringBuffer.append(charAt);
                i++;
                if (i >= length) {
                    break;
                }
                charAt = str.charAt(i);
            } else {
                allocate.clear();
                do {
                    int i2 = i + 1;
                    char charAt2 = str.charAt(i2);
                    int i3 = i2 + 1;
                    allocate.put(decode(charAt2, str.charAt(i3)));
                    i = i3 + 1;
                    if (i >= length) {
                        break;
                    }
                    charAt = str.charAt(i);
                } while (charAt == '%');
                allocate.flip();
                allocate2.clear();
                onUnmappableCharacter.reset();
                onUnmappableCharacter.decode(allocate, allocate2, true);
                onUnmappableCharacter.flush(allocate2);
                allocate2.flip();
                stringBuffer.append(allocate2.toString());
            }
        }
        return stringBuffer.toString();
    }

    private class Parser {
        private String input;
        private int ipv6byteCount = 0;
        private boolean requireServerAuthority = false;

        Parser(String str) {
            this.input = str;
            URI.this.string = str;
        }

        private void fail(String str) {
            throw new URISyntaxException(this.input, str);
        }

        private void fail(String str, int i) {
            throw new URISyntaxException(this.input, str, i);
        }

        private void failExpecting(String str, int i) {
            fail("Expected " + str, i);
            throw null;
        }

        private String substring(int i, int i2) {
            return this.input.substring(i, i2);
        }

        private char charAt(int i) {
            return this.input.charAt(i);
        }

        private boolean at(int i, int i2, char c) {
            return i < i2 && charAt(i) == c;
        }

        private boolean at(int i, int i2, String str) {
            int length = str.length();
            if (length > i2 - i) {
                return false;
            }
            int i3 = i;
            int i4 = 0;
            while (i4 < length) {
                int i5 = i3 + 1;
                if (charAt(i3) != str.charAt(i4)) {
                    break;
                }
                i4++;
                i3 = i5;
            }
            if (i4 == length) {
                return true;
            }
            return false;
        }

        private int scan(int i, int i2, char c) {
            return (i >= i2 || charAt(i) != c) ? i : i + 1;
        }

        private int scan(int i, int i2, String str, String str2) {
            while (i < i2) {
                char charAt = charAt(i);
                if (str.indexOf(charAt) >= 0) {
                    return -1;
                }
                if (str2.indexOf(charAt) >= 0) {
                    break;
                }
                i++;
            }
            return i;
        }

        private int scanEscape(int i, int i2, char c) {
            if (c != '%') {
                return (c <= 128 || Character.isSpaceChar(c) || Character.isISOControl(c)) ? i : i + 1;
            }
            int i3 = i + 3;
            if (i3 <= i2 && URI.match(charAt(i + 1), URI.L_HEX, URI.H_HEX) && URI.match(charAt(i + 2), URI.L_HEX, URI.H_HEX)) {
                return i3;
            }
            fail("Malformed escape pair", i);
            throw null;
        }

        private int scan(int i, int i2, long j, long j2) {
            int scanEscape;
            while (i < i2) {
                char charAt = charAt(i);
                if (!URI.match(charAt, j, j2)) {
                    if ((1 & j) == 0 || (scanEscape = scanEscape(i, i2, charAt)) <= i) {
                        break;
                    }
                    i = scanEscape;
                } else {
                    i++;
                }
            }
            return i;
        }

        private void checkChars(int i, int i2, long j, long j2, String str) {
            int scan = scan(i, i2, j, j2);
            if (scan < i2) {
                fail("Illegal character in " + str, scan);
                throw null;
            }
        }

        private void checkChar(int i, long j, long j2, String str) {
            checkChars(i, i + 1, j, j2, str);
        }

        /* access modifiers changed from: package-private */
        public void parse(boolean z) {
            int i;
            this.requireServerAuthority = z;
            int length = this.input.length();
            int i2 = 0;
            int scan = scan(0, length, "/?#", ":");
            if (scan < 0 || !at(scan, length, ':')) {
                i = parseHierarchical(0, length);
            } else if (scan != 0) {
                checkChar(0, 0, URI.H_ALPHA, "scheme name");
                checkChars(1, scan, URI.L_SCHEME, URI.H_SCHEME, "scheme name");
                URI.this.scheme = substring(0, scan);
                i2 = scan + 1;
                if (at(i2, length, '/')) {
                    i = parseHierarchical(i2, length);
                } else {
                    i = scan(i2, length, "", "#");
                    if (i > i2) {
                        checkChars(i2, i, URI.L_URIC, URI.H_URIC, "opaque part");
                    } else {
                        failExpecting("scheme-specific part", i2);
                        throw null;
                    }
                }
            } else {
                failExpecting("scheme name", 0);
                throw null;
            }
            URI.this.schemeSpecificPart = substring(i2, i);
            if (at(i, length, '#')) {
                int i3 = i + 1;
                checkChars(i3, length, URI.L_URIC, URI.H_URIC, "fragment");
                URI.this.fragment = substring(i3, length);
                i = length;
            }
            if (i < length) {
                fail("end of URI", i);
                throw null;
            }
        }

        private int parseHierarchical(int i, int i2) {
            if (at(i, i2, '/') && at(i + 1, i2, '/')) {
                i += 2;
                int scan = scan(i, i2, "", "/?#");
                if (scan > i) {
                    parseAuthority(i, scan);
                    i = scan;
                } else if (scan >= i2) {
                    failExpecting("authority", i);
                    throw null;
                }
            }
            int scan2 = scan(i, i2, "", "?#");
            checkChars(i, scan2, URI.L_PATH, URI.H_PATH, "path");
            URI.this.path = substring(i, scan2);
            if (!at(scan2, i2, '?')) {
                return scan2;
            }
            int i3 = scan2 + 1;
            int scan3 = scan(i3, i2, "", "#");
            checkChars(i3, scan3, URI.L_URIC, URI.H_URIC, "query");
            URI.this.query = substring(i3, scan3);
            return scan3;
        }

        private int parseAuthority(int i, int i2) {
            URISyntaxException uRISyntaxException;
            int i3;
            boolean z = true;
            boolean z2 = scan(i, i2, "", "]") <= i ? scan(i, i2, URI.L_SERVER, URI.H_SERVER) == i2 : scan(i, i2, URI.L_SERVER_PERCENT, URI.H_SERVER_PERCENT) == i2;
            if (scan(i, i2, URI.L_REG_NAME, URI.H_REG_NAME) != i2) {
                z = false;
            }
            if (!z || z2) {
                if (z2) {
                    try {
                        i3 = parseServer(i, i2);
                        if (i3 >= i2) {
                            URI.this.authority = substring(i, i2);
                        } else {
                            failExpecting("end of authority", i3);
                            throw null;
                        }
                    } catch (URISyntaxException e) {
                        URI.this.userInfo = null;
                        URI.this.host = null;
                        URI.this.port = -1;
                        if (!this.requireServerAuthority) {
                            uRISyntaxException = e;
                            i3 = i;
                        } else {
                            throw e;
                        }
                    }
                } else {
                    i3 = i;
                }
                uRISyntaxException = null;
                if (i3 < i2) {
                    if (z) {
                        URI.this.authority = substring(i, i2);
                    } else if (uRISyntaxException != null) {
                        throw uRISyntaxException;
                    } else {
                        fail("Illegal character in authority", i3);
                        throw null;
                    }
                }
                return i2;
            }
            URI.this.authority = substring(i, i2);
            return i2;
        }

        private int parseServer(int i, int i2) {
            int i3;
            int scan = scan(i, i2, "/?#", "@");
            if (scan >= i && at(scan, i2, '@')) {
                checkChars(i, scan, URI.L_USERINFO, URI.H_USERINFO, "user info");
                URI.this.userInfo = substring(i, scan);
                i = scan + 1;
            }
            if (at(i, i2, '[')) {
                int i4 = i + 1;
                int scan2 = scan(i4, i2, "/?#", "]");
                if (scan2 <= i4 || !at(scan2, i2, ']')) {
                    failExpecting("closing bracket for IPv6 address", scan2);
                    throw null;
                }
                int scan3 = scan(i4, scan2, "", "%");
                if (scan3 > i4) {
                    parseIPv6Reference(i4, scan3);
                    int i5 = scan3 + 1;
                    if (i5 != scan2) {
                        checkChars(i5, scan2, URI.L_ALPHANUM, URI.H_ALPHANUM, "scope id");
                    } else {
                        fail("scope id expected");
                        throw null;
                    }
                } else {
                    parseIPv6Reference(i4, scan2);
                }
                i3 = scan2 + 1;
                URI.this.host = substring(i4 - 1, i3);
            } else {
                i3 = parseIPv4Address(i, i2);
                if (i3 <= i) {
                    i3 = parseHostname(i, i2);
                }
            }
            if (at(i3, i2, ':')) {
                int i6 = i3 + 1;
                i3 = scan(i6, i2, "", "/");
                if (i3 > i6) {
                    checkChars(i6, i3, URI.L_DIGIT, 0, "port number");
                    try {
                        URI.this.port = Integer.parseInt(substring(i6, i3));
                    } catch (NumberFormatException unused) {
                        fail("Malformed port number", i6);
                        throw null;
                    }
                } else {
                    i3 = i6;
                }
            }
            if (i3 >= i2) {
                return i3;
            }
            failExpecting("port number", i3);
            throw null;
        }

        private int scanByte(int i, int i2) {
            int scan = scan(i, i2, URI.L_DIGIT, 0);
            return (scan > i && Integer.parseInt(substring(i, scan)) > 255) ? i : scan;
        }

        private int scanIPv4Address(int i, int i2, boolean z) {
            int scan = scan(i, i2, URI.L_DIGIT | URI.L_DOT, URI.H_DOT | 0);
            if (scan <= i) {
                return -1;
            }
            if (z && scan != i2) {
                return -1;
            }
            int scanByte = scanByte(i, scan);
            if (scanByte > i) {
                int scan2 = scan(scanByte, scan, '.');
                if (scan2 > scanByte) {
                    scanByte = scanByte(scan2, scan);
                    if (scanByte > scan2) {
                        scan2 = scan(scanByte, scan, '.');
                        if (scan2 > scanByte) {
                            scanByte = scanByte(scan2, scan);
                            if (scanByte > scan2) {
                                int scan3 = scan(scanByte, scan, '.');
                                if (scan3 > scanByte) {
                                    scanByte = scanByte(scan3, scan);
                                    if (scanByte > scan3 && scanByte >= scan) {
                                        return scanByte;
                                    }
                                } else {
                                    scanByte = scan3;
                                }
                            }
                        }
                    }
                }
                scanByte = scan2;
            }
            fail("Malformed IPv4 address", scanByte);
            throw null;
        }

        private int takeIPv4Address(int i, int i2, String str) {
            int scanIPv4Address = scanIPv4Address(i, i2, true);
            if (scanIPv4Address > i) {
                return scanIPv4Address;
            }
            failExpecting(str, i);
            throw null;
        }

        private int parseIPv4Address(int i, int i2) {
            try {
                int scanIPv4Address = scanIPv4Address(i, i2, false);
                if (scanIPv4Address > i && scanIPv4Address < i2 && charAt(scanIPv4Address) != ':') {
                    scanIPv4Address = -1;
                }
                if (scanIPv4Address > i) {
                    URI.this.host = substring(i, scanIPv4Address);
                }
                return scanIPv4Address;
            } catch (NumberFormatException | URISyntaxException unused) {
                return -1;
            }
        }

        private int parseHostname(int i, int i2) {
            int i3;
            int i4 = -1;
            int i5 = i;
            while (true) {
                int scan = scan(i5, i2, URI.L_ALPHANUM, URI.H_ALPHANUM);
                if (scan > i5) {
                    if (scan > i5) {
                        i3 = scan(scan, i2, URI.L_ALPHANUM | URI.L_DASH | URI.L_UNDERSCORE, URI.H_UNDERSCORE | URI.H_ALPHANUM | URI.H_DASH);
                        if (i3 > scan) {
                            int i6 = i3 - 1;
                            if (charAt(i6) == '-') {
                                fail("Illegal character in hostname", i6);
                                throw null;
                            }
                        } else {
                            i3 = scan;
                        }
                    } else {
                        i3 = i5;
                    }
                    int scan2 = scan(i3, i2, '.');
                    if (scan2 <= i3) {
                        break;
                    } else if (scan2 >= i2) {
                        i3 = scan2;
                        break;
                    } else {
                        i4 = i5;
                        i5 = scan2;
                    }
                } else {
                    i3 = i5;
                    i5 = i4;
                    break;
                }
            }
            if (i3 < i2 && !at(i3, i2, ':')) {
                fail("Illegal character in hostname", i3);
                throw null;
            } else if (i5 < 0) {
                failExpecting("hostname", i);
                throw null;
            } else if (i5 <= i || URI.match(charAt(i5), 0, URI.H_ALPHA)) {
                URI.this.host = substring(i, i3);
                return i3;
            } else {
                fail("Illegal character in hostname", i5);
                throw null;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0065  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int parseIPv6Reference(int r6, int r7) {
            /*
            // Method dump skipped, instructions count: 105
            */
            throw new UnsupportedOperationException("Method not decompiled: java.net.URI.Parser.parseIPv6Reference(int, int):int");
        }

        private int scanHexPost(int i, int i2) {
            if (i == i2) {
                return i;
            }
            int scanHexSeq = scanHexSeq(i, i2);
            if (scanHexSeq <= i) {
                int takeIPv4Address = takeIPv4Address(i, i2, "hex digits or IPv4 address");
                this.ipv6byteCount += 4;
                return takeIPv4Address;
            } else if (!at(scanHexSeq, i2, ':')) {
                return scanHexSeq;
            } else {
                int takeIPv4Address2 = takeIPv4Address(scanHexSeq + 1, i2, "hex digits or IPv4 address");
                this.ipv6byteCount += 4;
                return takeIPv4Address2;
            }
        }

        private int scanHexSeq(int i, int i2) {
            int scan = scan(i, i2, URI.L_HEX, URI.H_HEX);
            if (scan <= i || at(scan, i2, '.')) {
                return -1;
            }
            if (scan <= i + 4) {
                this.ipv6byteCount += 2;
                while (scan < i2 && at(scan, i2, ':')) {
                    int i3 = scan + 1;
                    if (at(i3, i2, ':')) {
                        return scan;
                    }
                    scan = scan(i3, i2, URI.L_HEX, URI.H_HEX);
                    if (scan <= i3) {
                        failExpecting("digits for an IPv6 address", i3);
                        throw null;
                    } else if (at(scan, i2, '.')) {
                        return i3 - 1;
                    } else {
                        if (scan <= i3 + 4) {
                            this.ipv6byteCount += 2;
                        } else {
                            fail("IPv6 hexadecimal digit sequence too long", i3);
                            throw null;
                        }
                    }
                }
                return scan;
            }
            fail("IPv6 hexadecimal digit sequence too long", i);
            throw null;
        }
    }
}
