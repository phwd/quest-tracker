package java.net;

import java.io.IOException;
import java.io.InvalidObjectException;
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

public final class URI implements Comparable<URI>, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long H_ALPHA = (H_LOWALPHA | H_UPALPHA);
    private static final long H_ALPHANUM = (H_ALPHA | 0);
    private static final long H_DASH = highMask("-");
    private static final long H_DIGIT = 0;
    private static final long H_DOT = highMask(".");
    private static final long H_ESCAPED = 0;
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
    private static final long L_ALPHA = 0;
    private static final long L_ALPHANUM;
    private static final long L_DASH = lowMask("-");
    private static final long L_DIGIT = lowMask('0', '9');
    private static final long L_DOT = lowMask(".");
    private static final long L_ESCAPED = 1;
    private static final long L_HEX;
    private static final long L_LEFT_BRACKET = lowMask("[");
    private static final long L_LOWALPHA = 0;
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
    private static final long L_UPALPHA = 0;
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

    static {
        long j = L_DIGIT;
        L_ALPHANUM = j | 0;
        L_HEX = j;
        long j2 = L_RESERVED;
        long j3 = L_UNRESERVED;
        L_URIC = j2 | j3 | 1;
        L_PCHAR = j3 | 1 | lowMask(":@&=+$,");
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

    public URI(String str) throws URISyntaxException {
        this.port = -1;
        this.decodedUserInfo = null;
        this.decodedAuthority = null;
        this.decodedPath = null;
        this.decodedQuery = null;
        this.decodedFragment = null;
        this.decodedSchemeSpecificPart = null;
        new Parser(str).parse(false);
    }

    public URI(String scheme2, String userInfo2, String host2, int port2, String path2, String query2, String fragment2) throws URISyntaxException {
        this.port = -1;
        this.decodedUserInfo = null;
        this.decodedAuthority = null;
        this.decodedPath = null;
        this.decodedQuery = null;
        this.decodedFragment = null;
        this.decodedSchemeSpecificPart = null;
        String s = toString(scheme2, null, null, userInfo2, host2, port2, path2, query2, fragment2);
        checkPath(s, scheme2, path2);
        new Parser(s).parse(true);
    }

    public URI(String scheme2, String authority2, String path2, String query2, String fragment2) throws URISyntaxException {
        this.port = -1;
        this.decodedUserInfo = null;
        this.decodedAuthority = null;
        this.decodedPath = null;
        this.decodedQuery = null;
        this.decodedFragment = null;
        this.decodedSchemeSpecificPart = null;
        String s = toString(scheme2, null, authority2, null, null, -1, path2, query2, fragment2);
        checkPath(s, scheme2, path2);
        new Parser(s).parse(false);
    }

    public URI(String scheme2, String host2, String path2, String fragment2) throws URISyntaxException {
        this(scheme2, null, host2, -1, path2, null, fragment2);
    }

    public URI(String scheme2, String ssp, String fragment2) throws URISyntaxException {
        this.port = -1;
        this.decodedUserInfo = null;
        this.decodedAuthority = null;
        this.decodedPath = null;
        this.decodedQuery = null;
        this.decodedFragment = null;
        this.decodedSchemeSpecificPart = null;
        new Parser(toString(scheme2, ssp, null, null, null, -1, null, null, fragment2)).parse(false);
    }

    public static URI create(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException x) {
            throw new IllegalArgumentException(x.getMessage(), x);
        }
    }

    public URI parseServerAuthority() throws URISyntaxException {
        if (this.host != null || this.authority == null) {
            return this;
        }
        defineString();
        new Parser(this.string).parse(true);
        return this;
    }

    public URI normalize() {
        return normalize(this);
    }

    public URI resolve(URI uri) {
        return resolve(this, uri);
    }

    public URI resolve(String str) {
        return resolve(create(str));
    }

    public URI relativize(URI uri) {
        return relativize(this, uri);
    }

    public URL toURL() throws MalformedURLException {
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

    public String getRawSchemeSpecificPart() {
        defineSchemeSpecificPart();
        return this.schemeSpecificPart;
    }

    public String getSchemeSpecificPart() {
        if (this.decodedSchemeSpecificPart == null) {
            this.decodedSchemeSpecificPart = decode(getRawSchemeSpecificPart());
        }
        return this.decodedSchemeSpecificPart;
    }

    public String getRawAuthority() {
        return this.authority;
    }

    public String getAuthority() {
        if (this.decodedAuthority == null) {
            this.decodedAuthority = decode(this.authority);
        }
        return this.decodedAuthority;
    }

    public String getRawUserInfo() {
        return this.userInfo;
    }

    public String getUserInfo() {
        String str;
        if (this.decodedUserInfo == null && (str = this.userInfo) != null) {
            this.decodedUserInfo = decode(str);
        }
        return this.decodedUserInfo;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getRawPath() {
        return this.path;
    }

    public String getPath() {
        String str;
        if (this.decodedPath == null && (str = this.path) != null) {
            this.decodedPath = decode(str);
        }
        return this.decodedPath;
    }

    public String getRawQuery() {
        return this.query;
    }

    public String getQuery() {
        String str;
        if (this.decodedQuery == null && (str = this.query) != null) {
            this.decodedQuery = decode(str);
        }
        return this.decodedQuery;
    }

    public String getRawFragment() {
        return this.fragment;
    }

    public String getFragment() {
        String str;
        if (this.decodedFragment == null && (str = this.fragment) != null) {
            this.decodedFragment = decode(str);
        }
        return this.decodedFragment;
    }

    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }
        if (!(ob instanceof URI)) {
            return false;
        }
        URI that = (URI) ob;
        if (isOpaque() != that.isOpaque() || !equalIgnoringCase(this.scheme, that.scheme) || !equal(this.fragment, that.fragment)) {
            return false;
        }
        if (isOpaque()) {
            return equal(this.schemeSpecificPart, that.schemeSpecificPart);
        }
        if (!equal(this.path, that.path) || !equal(this.query, that.query)) {
            return false;
        }
        String str = this.authority;
        String str2 = that.authority;
        if (str == str2) {
            return true;
        }
        if (this.host != null) {
            if (equal(this.userInfo, that.userInfo) && equalIgnoringCase(this.host, that.host) && this.port == that.port) {
                return true;
            }
            return false;
        } else if (str != null) {
            if (!equal(str, str2)) {
                return false;
            }
        } else if (str != str2) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int h;
        if (this.hash != 0) {
            return this.hash;
        }
        int h2 = hash(hashIgnoringCase(0, this.scheme), this.fragment);
        if (isOpaque()) {
            h = hash(h2, this.schemeSpecificPart);
        } else {
            int h3 = hash(hash(h2, this.path), this.query);
            if (this.host != null) {
                h = hashIgnoringCase(hash(h3, this.userInfo), this.host) + (this.port * 1949);
            } else {
                h = hash(h3, this.authority);
            }
        }
        this.hash = h;
        return h;
    }

    public int compareTo(URI that) {
        int c = compareIgnoringCase(this.scheme, that.scheme);
        if (c != 0) {
            return c;
        }
        if (isOpaque()) {
            if (!that.isOpaque()) {
                return 1;
            }
            int c2 = compare(this.schemeSpecificPart, that.schemeSpecificPart);
            if (c2 != 0) {
                return c2;
            }
            return compare(this.fragment, that.fragment);
        } else if (that.isOpaque()) {
            return -1;
        } else {
            if (this.host == null || that.host == null) {
                int c3 = compare(this.authority, that.authority);
                if (c3 != 0) {
                    return c3;
                }
            } else {
                int c4 = compare(this.userInfo, that.userInfo);
                if (c4 != 0) {
                    return c4;
                }
                int c5 = compareIgnoringCase(this.host, that.host);
                if (c5 != 0) {
                    return c5;
                }
                int c6 = this.port - that.port;
                if (c6 != 0) {
                    return c6;
                }
            }
            int c7 = compare(this.path, that.path);
            if (c7 != 0) {
                return c7;
            }
            int c8 = compare(this.query, that.query);
            if (c8 != 0) {
                return c8;
            }
            return compare(this.fragment, that.fragment);
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

    private void writeObject(ObjectOutputStream os) throws IOException {
        defineString();
        os.defaultWriteObject();
    }

    private void readObject(ObjectInputStream is) throws ClassNotFoundException, IOException {
        this.port = -1;
        is.defaultReadObject();
        try {
            new Parser(this.string).parse(false);
        } catch (URISyntaxException x) {
            IOException y = new InvalidObjectException("Invalid URI");
            y.initCause(x);
            throw y;
        }
    }

    private static int toLower(char c) {
        if (c < 'A' || c > 'Z') {
            return c;
        }
        return c + ' ';
    }

    private static int toUpper(char c) {
        if (c < 'a' || c > 'z') {
            return c;
        }
        return c - ' ';
    }

    private static boolean equal(String s, String t) {
        if (s == t) {
            return true;
        }
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        if (s.indexOf(37) < 0) {
            return s.equals(t);
        }
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            if (c != '%') {
                if (c != d) {
                    return false;
                }
                i++;
            } else if (d != '%') {
                return false;
            } else {
                int i2 = i + 1;
                if (toLower(s.charAt(i2)) != toLower(t.charAt(i2))) {
                    return false;
                }
                int i3 = i2 + 1;
                if (toLower(s.charAt(i3)) != toLower(t.charAt(i3))) {
                    return false;
                }
                i = i3 + 1;
            }
        }
        return true;
    }

    private static boolean equalIgnoringCase(String s, String t) {
        int n;
        if (s == t) {
            return true;
        }
        if (s == null || t == null || t.length() != (n = s.length())) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (toLower(s.charAt(i)) != toLower(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int hash(int hash2, String s) {
        if (s == null) {
            return hash2;
        }
        if (s.indexOf(37) < 0) {
            return (hash2 * 127) + s.hashCode();
        }
        return normalizedHash(hash2, s);
    }

    private static int normalizedHash(int hash2, String s) {
        int h = 0;
        int index = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            int h2 = (h * 31) + ch;
            if (ch == '%') {
                for (int i = index + 1; i < index + 3; i++) {
                    h2 = (h2 * 31) + toUpper(s.charAt(i));
                }
                index += 2;
            }
            h = h2;
            index++;
        }
        return (hash2 * 127) + h;
    }

    private static int hashIgnoringCase(int hash2, String s) {
        if (s == null) {
            return hash2;
        }
        int h = hash2;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            h = (h * 31) + toLower(s.charAt(i));
        }
        return h;
    }

    private static int compare(String s, String t) {
        if (s == t) {
            return 0;
        }
        if (s == null) {
            return -1;
        }
        if (t != null) {
            return s.compareTo(t);
        }
        return 1;
    }

    private static int compareIgnoringCase(String s, String t) {
        if (s == t) {
            return 0;
        }
        if (s == null) {
            return -1;
        }
        if (t == null) {
            return 1;
        }
        int sn = s.length();
        int tn = t.length();
        int n = sn < tn ? sn : tn;
        for (int i = 0; i < n; i++) {
            int c = toLower(s.charAt(i)) - toLower(t.charAt(i));
            if (c != 0) {
                return c;
            }
        }
        return sn - tn;
    }

    private static void checkPath(String s, String scheme2, String path2) throws URISyntaxException {
        if (scheme2 != null && path2 != null && path2.length() > 0 && path2.charAt(0) != '/') {
            throw new URISyntaxException(s, "Relative path in absolute URI");
        }
    }

    private void appendAuthority(StringBuffer sb, String authority2, String userInfo2, String host2, int port2) {
        boolean needBrackets = false;
        if (host2 != null) {
            sb.append("//");
            if (userInfo2 != null) {
                sb.append(quote(userInfo2, L_USERINFO, H_USERINFO));
                sb.append('@');
            }
            if (host2.indexOf(58) >= 0 && !host2.startsWith("[") && !host2.endsWith("]")) {
                needBrackets = true;
            }
            if (needBrackets) {
                sb.append('[');
            }
            sb.append(host2);
            if (needBrackets) {
                sb.append(']');
            }
            if (port2 != -1) {
                sb.append(':');
                sb.append(port2);
            }
        } else if (authority2 != null) {
            sb.append("//");
            if (authority2.startsWith("[")) {
                int end = authority2.indexOf("]");
                String doquote = authority2;
                String dontquote = "";
                if (!(end == -1 || authority2.indexOf(":") == -1)) {
                    if (end == authority2.length()) {
                        dontquote = authority2;
                        doquote = "";
                    } else {
                        dontquote = authority2.substring(0, end + 1);
                        doquote = authority2.substring(end + 1);
                    }
                }
                sb.append(dontquote);
                sb.append(quote(doquote, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
                return;
            }
            sb.append(quote(authority2, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
        }
    }

    private void appendSchemeSpecificPart(StringBuffer sb, String opaquePart, String authority2, String userInfo2, String host2, int port2, String path2, String query2) {
        String doquote;
        String dontquote;
        if (opaquePart == null) {
            appendAuthority(sb, authority2, userInfo2, host2, port2);
            if (path2 != null) {
                sb.append(quote(path2, L_PATH, H_PATH));
            }
            if (query2 != null) {
                sb.append('?');
                sb.append(quote(query2, L_URIC, H_URIC));
            }
        } else if (opaquePart.startsWith("//[")) {
            int end = opaquePart.indexOf("]");
            if (end != -1 && opaquePart.indexOf(":") != -1) {
                if (end == opaquePart.length()) {
                    dontquote = opaquePart;
                    doquote = "";
                } else {
                    dontquote = opaquePart.substring(0, end + 1);
                    doquote = opaquePart.substring(end + 1);
                }
                sb.append(dontquote);
                sb.append(quote(doquote, L_URIC, H_URIC));
            }
        } else {
            sb.append(quote(opaquePart, L_URIC, H_URIC));
        }
    }

    private void appendFragment(StringBuffer sb, String fragment2) {
        if (fragment2 != null) {
            sb.append('#');
            sb.append(quote(fragment2, L_URIC, H_URIC));
        }
    }

    private String toString(String scheme2, String opaquePart, String authority2, String userInfo2, String host2, int port2, String path2, String query2, String fragment2) {
        StringBuffer sb = new StringBuffer();
        if (scheme2 != null) {
            sb.append(scheme2);
            sb.append(':');
        }
        appendSchemeSpecificPart(sb, opaquePart, authority2, userInfo2, host2, port2, path2, query2);
        appendFragment(sb, fragment2);
        return sb.toString();
    }

    private void defineSchemeSpecificPart() {
        if (this.schemeSpecificPart == null) {
            StringBuffer sb = new StringBuffer();
            appendSchemeSpecificPart(sb, null, getAuthority(), getUserInfo(), this.host, this.port, getPath(), getQuery());
            if (sb.length() != 0) {
                this.schemeSpecificPart = sb.toString();
            }
        }
    }

    private void defineString() {
        if (this.string == null) {
            StringBuffer sb = new StringBuffer();
            String str = this.scheme;
            if (str != null) {
                sb.append(str);
                sb.append(':');
            }
            if (isOpaque()) {
                sb.append(this.schemeSpecificPart);
            } else {
                if (this.host != null) {
                    sb.append("//");
                    String str2 = this.userInfo;
                    if (str2 != null) {
                        sb.append(str2);
                        sb.append('@');
                    }
                    boolean needBrackets = this.host.indexOf(58) >= 0 && !this.host.startsWith("[") && !this.host.endsWith("]");
                    if (needBrackets) {
                        sb.append('[');
                    }
                    sb.append(this.host);
                    if (needBrackets) {
                        sb.append(']');
                    }
                    if (this.port != -1) {
                        sb.append(':');
                        sb.append(this.port);
                    }
                } else if (this.authority != null) {
                    sb.append("//");
                    sb.append(this.authority);
                }
                String str3 = this.path;
                if (str3 != null) {
                    sb.append(str3);
                }
                if (this.query != null) {
                    sb.append('?');
                    sb.append(this.query);
                }
            }
            if (this.fragment != null) {
                sb.append('#');
                sb.append(this.fragment);
            }
            this.string = sb.toString();
        }
    }

    private static String resolvePath(String base, String child, boolean absolute) {
        int i = base.lastIndexOf(47);
        int cn = child.length();
        String path2 = "";
        if (cn != 0) {
            StringBuffer sb = new StringBuffer(base.length() + cn);
            if (i >= 0) {
                sb.append(base.substring(0, i + 1));
            }
            sb.append(child);
            path2 = sb.toString();
        } else if (i >= 0) {
            path2 = base.substring(0, i + 1);
        }
        return normalize(path2, true);
    }

    private static URI resolve(URI base, URI child) {
        String str;
        if (child.isOpaque() || base.isOpaque()) {
            return child;
        }
        if (child.scheme == null && child.authority == null && child.path.equals("") && (str = child.fragment) != null && child.query == null) {
            String str2 = base.fragment;
            if (str2 != null && str.equals(str2)) {
                return base;
            }
            URI ru = new URI();
            ru.scheme = base.scheme;
            ru.authority = base.authority;
            ru.userInfo = base.userInfo;
            ru.host = base.host;
            ru.port = base.port;
            ru.path = base.path;
            ru.fragment = child.fragment;
            ru.query = base.query;
            return ru;
        } else if (child.scheme != null) {
            return child;
        } else {
            URI ru2 = new URI();
            ru2.scheme = base.scheme;
            ru2.query = child.query;
            ru2.fragment = child.fragment;
            String str3 = child.authority;
            if (str3 == null) {
                ru2.authority = base.authority;
                ru2.host = base.host;
                ru2.userInfo = base.userInfo;
                ru2.port = base.port;
                String str4 = child.path;
                if (str4 == null || str4.isEmpty()) {
                    ru2.path = base.path;
                    String str5 = child.query;
                    if (str5 == null) {
                        str5 = base.query;
                    }
                    ru2.query = str5;
                } else if (child.path.length() <= 0 || child.path.charAt(0) != '/') {
                    ru2.path = resolvePath(base.path, child.path, base.isAbsolute());
                } else {
                    ru2.path = normalize(child.path, true);
                }
            } else {
                ru2.authority = str3;
                ru2.host = child.host;
                ru2.userInfo = child.userInfo;
                ru2.host = child.host;
                ru2.port = child.port;
                ru2.path = child.path;
            }
            return ru2;
        }
    }

    private static URI normalize(URI u) {
        String str;
        String np;
        if (u.isOpaque() || (str = u.path) == null || str.length() == 0 || (np = normalize(u.path)) == u.path) {
            return u;
        }
        URI v = new URI();
        v.scheme = u.scheme;
        v.fragment = u.fragment;
        v.authority = u.authority;
        v.userInfo = u.userInfo;
        v.host = u.host;
        v.port = u.port;
        v.path = np;
        v.query = u.query;
        return v;
    }

    private static URI relativize(URI base, URI child) {
        if (child.isOpaque() || base.isOpaque() || !equalIgnoringCase(base.scheme, child.scheme) || !equal(base.authority, child.authority)) {
            return child;
        }
        String bp = normalize(base.path);
        String cp = normalize(child.path);
        boolean equals = bp.equals(cp);
        String bp2 = bp;
        if (!equals) {
            int indexOf = bp.indexOf(47);
            String bp3 = bp;
            if (indexOf != -1) {
                bp3 = bp.substring(0, bp.lastIndexOf(47) + 1);
            }
            boolean startsWith = cp.startsWith(bp3);
            bp2 = bp3;
            if (!startsWith) {
                return child;
            }
        }
        URI v = new URI();
        v.path = cp.substring(bp2.length());
        v.query = child.query;
        v.fragment = child.fragment;
        return v;
    }

    private static int needsNormalization(String path2) {
        boolean normal = true;
        int ns = 0;
        int end = path2.length() - 1;
        int p = 0;
        while (p <= end && path2.charAt(p) == '/') {
            p++;
        }
        if (p > 1) {
            normal = false;
        }
        while (p <= end) {
            if (path2.charAt(p) == '.' && (p == end || path2.charAt(p + 1) == '/' || (path2.charAt(p + 1) == '.' && (p + 1 == end || path2.charAt(p + 2) == '/')))) {
                normal = false;
            }
            ns++;
            while (true) {
                if (p > end) {
                    break;
                }
                int p2 = p + 1;
                if (path2.charAt(p) != 47) {
                    p = p2;
                } else {
                    p = p2;
                    while (p <= end && path2.charAt(p) == '/') {
                        normal = false;
                        p++;
                    }
                }
            }
        }
        if (normal) {
            return -1;
        }
        return ns;
    }

    private static void split(char[] path2, int[] segs) {
        int end = path2.length - 1;
        int p = 0;
        int p2 = 0;
        while (p <= end && path2[p] == '/') {
            path2[p] = 0;
            p++;
        }
        while (p <= end) {
            int i = p2 + 1;
            segs[p2] = p;
            p++;
            while (true) {
                if (p > end) {
                    p2 = i;
                    break;
                }
                int p3 = p + 1;
                if (path2[p] != '/') {
                    p = p3;
                } else {
                    path2[p3 - 1] = 0;
                    p = p3;
                    while (p <= end && path2[p] == '/') {
                        path2[p] = 0;
                        p++;
                    }
                    p2 = i;
                }
            }
        }
        if (p2 != segs.length) {
            throw new InternalError();
        }
    }

    private static int join(char[] path2, int[] segs) {
        int end = path2.length - 1;
        int p = 0;
        if (path2[0] == 0) {
            path2[0] = '/';
            p = 0 + 1;
        }
        for (int q : segs) {
            if (q != -1) {
                if (p == q) {
                    while (p <= end && path2[p] != 0) {
                        p++;
                    }
                    if (p <= end) {
                        path2[p] = '/';
                        p++;
                    }
                } else if (p < q) {
                    while (q <= end && path2[q] != 0) {
                        path2[p] = path2[q];
                        p++;
                        q++;
                    }
                    if (q <= end) {
                        path2[p] = '/';
                        p++;
                    }
                } else {
                    throw new InternalError();
                }
            }
        }
        return p;
    }

    private static void removeDots(char[] path2, int[] segs, boolean removeLeading) {
        int ns = segs.length;
        int end = path2.length - 1;
        int i = 0;
        while (i < ns) {
            int dots = 0;
            while (true) {
                int p = segs[i];
                if (path2[p] == '.') {
                    if (p == end) {
                        dots = 1;
                        break;
                    } else if (path2[p + 1] == 0) {
                        dots = 1;
                        break;
                    } else if (path2[p + 1] == '.' && (p + 1 == end || path2[p + 2] == 0)) {
                        dots = 2;
                    }
                }
                i++;
                if (i >= ns) {
                    break;
                }
            }
            if (i <= ns && dots != 0) {
                if (dots == 1) {
                    segs[i] = -1;
                } else {
                    int j = i - 1;
                    while (j >= 0 && segs[j] == -1) {
                        j--;
                    }
                    if (j >= 0) {
                        int q = segs[j];
                        if (path2[q] != '.' || path2[q + 1] != '.' || path2[q + 2] != 0) {
                            segs[i] = -1;
                            segs[j] = -1;
                        }
                    } else if (removeLeading) {
                        segs[i] = -1;
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    private static void maybeAddLeadingDot(char[] path2, int[] segs) {
        if (path2[0] != 0) {
            int ns = segs.length;
            int f = 0;
            while (f < ns && segs[f] < 0) {
                f++;
            }
            if (f < ns && f != 0) {
                int p = segs[f];
                while (p < path2.length && path2[p] != ':' && path2[p] != 0) {
                    p++;
                }
                if (p < path2.length && path2[p] != 0) {
                    path2[0] = '.';
                    path2[1] = 0;
                    segs[0] = 0;
                }
            }
        }
    }

    private static String normalize(String ps) {
        return normalize(ps, false);
    }

    private static String normalize(String ps, boolean removeLeading) {
        int ns = needsNormalization(ps);
        if (ns < 0) {
            return ps;
        }
        char[] path2 = ps.toCharArray();
        int[] segs = new int[ns];
        split(path2, segs);
        removeDots(path2, segs, removeLeading);
        maybeAddLeadingDot(path2, segs);
        String s = new String(path2, 0, join(path2, segs));
        if (s.equals(ps)) {
            return ps;
        }
        return s;
    }

    private static long lowMask(String chars) {
        int n = chars.length();
        long m = 0;
        for (int i = 0; i < n; i++) {
            char c = chars.charAt(i);
            if (c < '@') {
                m |= 1 << c;
            }
        }
        return m;
    }

    private static long highMask(String chars) {
        int n = chars.length();
        long m = 0;
        for (int i = 0; i < n; i++) {
            char c = chars.charAt(i);
            if (c >= '@' && c < 128) {
                m |= 1 << (c - '@');
            }
        }
        return m;
    }

    private static long lowMask(char first, char last) {
        long m = 0;
        for (int i = Math.max(Math.min((int) first, 63), 0); i <= Math.max(Math.min((int) last, 63), 0); i++) {
            m |= 1 << i;
        }
        return m;
    }

    private static long highMask(char first, char last) {
        long m = 0;
        int l = Math.max(Math.min((int) last, 127), 64) - 64;
        for (int i = Math.max(Math.min((int) first, 127), 64) - 64; i <= l; i++) {
            m |= 1 << i;
        }
        return m;
    }

    /* access modifiers changed from: private */
    public static boolean match(char c, long lowMask, long highMask) {
        if (c == 0) {
            return false;
        }
        if (c < '@') {
            if (((1 << c) & lowMask) != 0) {
                return true;
            }
            return false;
        } else if (c >= 128 || ((1 << (c - 64)) & highMask) == 0) {
            return false;
        } else {
            return true;
        }
    }

    private static void appendEscape(StringBuffer sb, byte b) {
        sb.append('%');
        sb.append(hexDigits[(b >> 4) & 15]);
        sb.append(hexDigits[(b >> 0) & 15]);
    }

    private static void appendEncoded(StringBuffer sb, char c) {
        ByteBuffer bb = null;
        try {
            CharsetEncoder encoderFor = ThreadLocalCoders.encoderFor("UTF-8");
            bb = encoderFor.encode(CharBuffer.wrap("" + c));
        } catch (CharacterCodingException e) {
        }
        while (bb.hasRemaining()) {
            int b = bb.get() & 255;
            if (b >= 128) {
                appendEscape(sb, (byte) b);
            } else {
                sb.append((char) b);
            }
        }
    }

    private static String quote(String s, long lowMask, long highMask) {
        s.length();
        StringBuffer sb = null;
        boolean allowNonASCII = (1 & lowMask) != 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < 128) {
                if (!match(c, lowMask, highMask)) {
                    if (sb == null) {
                        sb = new StringBuffer();
                        sb.append(s.substring(0, i));
                    }
                    appendEscape(sb, (byte) c);
                } else if (sb != null) {
                    sb.append(c);
                }
            } else if (allowNonASCII && (Character.isSpaceChar(c) || Character.isISOControl(c))) {
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append(s.substring(0, i));
                }
                appendEncoded(sb, c);
            } else if (sb != null) {
                sb.append(c);
            }
        }
        return sb == null ? s : sb.toString();
    }

    private static String encode(String s) {
        int n = s.length();
        if (n == 0) {
            return s;
        }
        int i = 0;
        while (s.charAt(i) < 128) {
            i++;
            if (i >= n) {
                return s;
            }
        }
        ByteBuffer bb = null;
        try {
            bb = ThreadLocalCoders.encoderFor("UTF-8").encode(CharBuffer.wrap(Normalizer.normalize(s, Normalizer.Form.NFC)));
        } catch (CharacterCodingException e) {
        }
        StringBuffer sb = new StringBuffer();
        while (bb.hasRemaining()) {
            int b = bb.get() & 255;
            if (b >= 128) {
                appendEscape(sb, (byte) b);
            } else {
                sb.append((char) b);
            }
        }
        return sb.toString();
    }

    private static int decode(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    private static byte decode(char c1, char c2) {
        return (byte) (((decode(c1) & 15) << 4) | ((decode(c2) & 15) << 0));
    }

    private static String decode(String s) {
        int n;
        if (s == null || (n = s.length()) == 0 || s.indexOf(37) < 0) {
            return s;
        }
        StringBuffer sb = new StringBuffer(n);
        ByteBuffer bb = ByteBuffer.allocate(n);
        CharBuffer cb = CharBuffer.allocate(n);
        CharsetDecoder dec = ThreadLocalCoders.decoderFor("UTF-8").onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        char c = s.charAt(0);
        boolean betweenBrackets = false;
        int i = 0;
        while (i < n) {
            if (c == '[') {
                betweenBrackets = true;
            } else if (betweenBrackets && c == ']') {
                betweenBrackets = false;
            }
            if (c != '%' || betweenBrackets) {
                sb.append(c);
                i++;
                if (i >= n) {
                    break;
                }
                c = s.charAt(i);
            } else {
                bb.clear();
                do {
                    int i2 = i + 1;
                    char charAt = s.charAt(i2);
                    int i3 = i2 + 1;
                    bb.put(decode(charAt, s.charAt(i3)));
                    i = i3 + 1;
                    if (i >= n) {
                        break;
                    }
                    c = s.charAt(i);
                } while (c == '%');
                bb.flip();
                cb.clear();
                dec.reset();
                dec.decode(bb, cb, true);
                dec.flush(cb);
                sb.append(cb.flip().toString());
            }
        }
        return sb.toString();
    }

    private class Parser {
        private String input;
        private int ipv6byteCount = 0;
        private boolean requireServerAuthority = false;

        Parser(String s) {
            this.input = s;
            URI.this.string = s;
        }

        private void fail(String reason) throws URISyntaxException {
            throw new URISyntaxException(this.input, reason);
        }

        private void fail(String reason, int p) throws URISyntaxException {
            throw new URISyntaxException(this.input, reason, p);
        }

        private void failExpecting(String expected, int p) throws URISyntaxException {
            fail("Expected " + expected, p);
        }

        private void failExpecting(String expected, String prior, int p) throws URISyntaxException {
            fail("Expected " + expected + " following " + prior, p);
        }

        private String substring(int start, int end) {
            return this.input.substring(start, end);
        }

        private char charAt(int p) {
            return this.input.charAt(p);
        }

        private boolean at(int start, int end, char c) {
            return start < end && charAt(start) == c;
        }

        private boolean at(int start, int end, String s) {
            int p = start;
            int sn = s.length();
            if (sn > end - p) {
                return false;
            }
            int i = 0;
            while (true) {
                if (i >= sn) {
                    break;
                }
                int p2 = p + 1;
                if (charAt(p) != s.charAt(i)) {
                    break;
                }
                i++;
                p = p2;
            }
            if (i == sn) {
                return true;
            }
            return false;
        }

        private int scan(int start, int end, char c) {
            if (start >= end || charAt(start) != c) {
                return start;
            }
            return start + 1;
        }

        private int scan(int start, int end, String err, String stop) {
            int p = start;
            while (p < end) {
                char c = charAt(p);
                if (err.indexOf(c) >= 0) {
                    return -1;
                }
                if (stop.indexOf(c) >= 0) {
                    break;
                }
                p++;
            }
            return p;
        }

        private int scanEscape(int start, int n, char first) throws URISyntaxException {
            if (first == '%') {
                if (start + 3 <= n && URI.match(charAt(start + 1), URI.L_HEX, URI.H_HEX) && URI.match(charAt(start + 2), URI.L_HEX, URI.H_HEX)) {
                    return start + 3;
                }
                fail("Malformed escape pair", start);
            } else if (first > 128 && !Character.isSpaceChar(first) && !Character.isISOControl(first)) {
                return start + 1;
            }
            return start;
        }

        private int scan(int start, int n, long lowMask, long highMask) throws URISyntaxException {
            int q;
            int p = start;
            while (p < n) {
                char c = charAt(p);
                if (!URI.match(c, lowMask, highMask)) {
                    if ((1 & lowMask) == 0 || (q = scanEscape(p, n, c)) <= p) {
                        break;
                    }
                    p = q;
                } else {
                    p++;
                }
            }
            return p;
        }

        private void checkChars(int start, int end, long lowMask, long highMask, String what) throws URISyntaxException {
            int p = scan(start, end, lowMask, highMask);
            if (p < end) {
                fail("Illegal character in " + what, p);
            }
        }

        private void checkChar(int p, long lowMask, long highMask, String what) throws URISyntaxException {
            checkChars(p, p + 1, lowMask, highMask, what);
        }

        /* access modifiers changed from: package-private */
        public void parse(boolean rsa) throws URISyntaxException {
            int p;
            int ssp;
            this.requireServerAuthority = rsa;
            int n = this.input.length();
            int p2 = scan(0, n, "/?#", ":");
            if (p2 < 0 || !at(p2, n, ':')) {
                p = parseHierarchical(0, n);
                ssp = 0;
            } else {
                if (p2 == 0) {
                    failExpecting("scheme name", 0);
                }
                checkChar(0, 0, URI.H_ALPHA, "scheme name");
                checkChars(1, p2, URI.L_SCHEME, URI.H_SCHEME, "scheme name");
                URI.this.scheme = substring(0, p2);
                int p3 = p2 + 1;
                if (at(p3, n, '/')) {
                    p = parseHierarchical(p3, n);
                    ssp = p3;
                } else {
                    int q = scan(p3, n, "", "#");
                    if (q <= p3) {
                        failExpecting("scheme-specific part", p3);
                    }
                    checkChars(p3, q, URI.L_URIC, URI.H_URIC, "opaque part");
                    p = q;
                    ssp = p3;
                }
            }
            URI.this.schemeSpecificPart = substring(ssp, p);
            if (at(p, n, '#')) {
                checkChars(p + 1, n, URI.L_URIC, URI.H_URIC, "fragment");
                URI.this.fragment = substring(p + 1, n);
                p = n;
            }
            if (p < n) {
                fail("end of URI", p);
            }
        }

        private int parseHierarchical(int start, int n) throws URISyntaxException {
            int p = start;
            if (at(p, n, '/') && at(p + 1, n, '/')) {
                p += 2;
                int q = scan(p, n, "", "/?#");
                if (q > p) {
                    p = parseAuthority(p, q);
                } else if (q >= n) {
                    failExpecting("authority", p);
                }
            }
            int q2 = scan(p, n, "", "?#");
            checkChars(p, q2, URI.L_PATH, URI.H_PATH, "path");
            URI.this.path = substring(p, q2);
            if (!at(q2, n, '?')) {
                return q2;
            }
            int p2 = q2 + 1;
            int q3 = scan(p2, n, "", "#");
            checkChars(p2, q3, URI.L_URIC, URI.H_URIC, "query");
            URI.this.query = substring(p2, q3);
            return q3;
        }

        private int parseAuthority(int start, int n) throws URISyntaxException {
            boolean serverChars;
            int q = start;
            URISyntaxException ex = null;
            boolean regChars = true;
            if (scan(start, n, "", "]") > start) {
                serverChars = scan(start, n, URI.L_SERVER_PERCENT, URI.H_SERVER_PERCENT) == n;
            } else {
                serverChars = scan(start, n, URI.L_SERVER, URI.H_SERVER) == n;
            }
            if (scan(start, n, URI.L_REG_NAME, URI.H_REG_NAME) != n) {
                regChars = false;
            }
            if (!regChars || serverChars) {
                if (serverChars) {
                    try {
                        q = parseServer(start, n);
                        if (q < n) {
                            failExpecting("end of authority", q);
                        }
                        URI.this.authority = substring(start, n);
                    } catch (URISyntaxException x) {
                        URI.this.userInfo = null;
                        URI.this.host = null;
                        URI.this.port = -1;
                        if (!this.requireServerAuthority) {
                            ex = x;
                            q = start;
                        } else {
                            throw x;
                        }
                    }
                }
                if (q < n) {
                    if (regChars) {
                        URI.this.authority = substring(start, n);
                    } else if (ex == null) {
                        fail("Illegal character in authority", q);
                    } else {
                        throw ex;
                    }
                }
                return n;
            }
            URI.this.authority = substring(start, n);
            return n;
        }

        private int parseServer(int start, int n) throws URISyntaxException {
            int p;
            int q;
            int p2 = start;
            int q2 = scan(p2, n, "/?#", "@");
            if (q2 >= p2 && at(q2, n, '@')) {
                checkChars(p2, q2, URI.L_USERINFO, URI.H_USERINFO, "user info");
                URI.this.userInfo = substring(p2, q2);
                p2 = q2 + 1;
            }
            if (at(p2, n, '[')) {
                p = p2 + 1;
                int q3 = scan(p, n, "/?#", "]");
                if (q3 <= p || !at(q3, n, ']')) {
                    failExpecting("closing bracket for IPv6 address", q3);
                } else {
                    int r = scan(p, q3, "", "%");
                    if (r > p) {
                        parseIPv6Reference(p, r);
                        if (r + 1 == q3) {
                            fail("scope id expected");
                        }
                        checkChars(r + 1, q3, URI.L_ALPHANUM, URI.H_ALPHANUM, "scope id");
                    } else {
                        parseIPv6Reference(p, q3);
                    }
                    URI.this.host = substring(p - 1, q3 + 1);
                    p = q3 + 1;
                }
            } else {
                int q4 = parseIPv4Address(p2, n);
                if (q4 <= p2) {
                    q = parseHostname(p2, n);
                } else {
                    q = q4;
                }
                p = q;
            }
            if (at(p, n, ':')) {
                int p3 = p + 1;
                int q5 = scan(p3, n, "", "/");
                if (q5 > p3) {
                    checkChars(p3, q5, URI.L_DIGIT, 0, "port number");
                    try {
                        URI.this.port = Integer.parseInt(substring(p3, q5));
                    } catch (NumberFormatException e) {
                        fail("Malformed port number", p3);
                    }
                    p = q5;
                } else {
                    p = p3;
                }
            }
            if (p < n) {
                failExpecting("port number", p);
            }
            return p;
        }

        private int scanByte(int start, int n) throws URISyntaxException {
            int q = scan(start, n, URI.L_DIGIT, 0);
            if (q > start && Integer.parseInt(substring(start, q)) > 255) {
                return start;
            }
            return q;
        }

        private int scanIPv4Address(int start, int n, boolean strict) throws URISyntaxException {
            int m = scan(start, n, URI.L_DIGIT | URI.L_DOT, 0 | URI.H_DOT);
            if (m <= start || (strict && m != n)) {
                return -1;
            }
            int scanByte = scanByte(start, m);
            int q = scanByte;
            if (scanByte > start) {
                int scan = scan(q, m, '.');
                q = scan;
                if (scan > q) {
                    int scanByte2 = scanByte(q, m);
                    q = scanByte2;
                    if (scanByte2 > q) {
                        int scan2 = scan(q, m, '.');
                        q = scan2;
                        if (scan2 > q) {
                            int scanByte3 = scanByte(q, m);
                            q = scanByte3;
                            if (scanByte3 > q) {
                                int scan3 = scan(q, m, '.');
                                q = scan3;
                                if (scan3 > q) {
                                    int scanByte4 = scanByte(q, m);
                                    q = scanByte4;
                                    if (scanByte4 > q && q >= m) {
                                        return q;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            fail("Malformed IPv4 address", q);
            return -1;
        }

        private int takeIPv4Address(int start, int n, String expected) throws URISyntaxException {
            int p = scanIPv4Address(start, n, true);
            if (p <= start) {
                failExpecting(expected, start);
            }
            return p;
        }

        private int parseIPv4Address(int start, int n) {
            try {
                int p = scanIPv4Address(start, n, false);
                if (p > start && p < n && charAt(p) != ':') {
                    p = -1;
                }
                if (p > start) {
                    URI.this.host = substring(start, p);
                }
                return p;
            } catch (URISyntaxException e) {
                return -1;
            } catch (NumberFormatException e2) {
                return -1;
            }
        }

        private int parseHostname(int start, int n) throws URISyntaxException {
            int p = start;
            int l = -1;
            do {
                int q = scan(p, n, URI.L_ALPHANUM, URI.H_ALPHANUM);
                if (q <= p) {
                    break;
                }
                l = p;
                if (q > p) {
                    p = q;
                    int q2 = scan(p, n, URI.L_ALPHANUM | URI.L_DASH | URI.L_UNDERSCORE, URI.H_UNDERSCORE | URI.H_ALPHANUM | URI.H_DASH);
                    if (q2 > p) {
                        if (charAt(q2 - 1) == '-') {
                            fail("Illegal character in hostname", q2 - 1);
                        }
                        p = q2;
                    }
                }
                int q3 = scan(p, n, '.');
                if (q3 <= p) {
                    break;
                }
                p = q3;
            } while (p < n);
            if (p < n && !at(p, n, ':')) {
                fail("Illegal character in hostname", p);
            }
            if (l < 0) {
                failExpecting("hostname", start);
            }
            if (l > start && !URI.match(charAt(l), 0, URI.H_ALPHA)) {
                fail("Illegal character in hostname", l);
            }
            URI.this.host = substring(start, p);
            return p;
        }

        private int parseIPv6Reference(int start, int n) throws URISyntaxException {
            int p = start;
            boolean compressedZeros = false;
            int q = scanHexSeq(p, n);
            if (q > p) {
                p = q;
                if (at(p, n, "::")) {
                    compressedZeros = true;
                    p = scanHexPost(p + 2, n);
                } else if (at(p, n, ':')) {
                    p = takeIPv4Address(p + 1, n, "IPv4 address");
                    this.ipv6byteCount += 4;
                }
            } else if (at(p, n, "::")) {
                compressedZeros = true;
                p = scanHexPost(p + 2, n);
            }
            if (p < n) {
                fail("Malformed IPv6 address", start);
            }
            if (this.ipv6byteCount > 16) {
                fail("IPv6 address too long", start);
            }
            if (!compressedZeros && this.ipv6byteCount < 16) {
                fail("IPv6 address too short", start);
            }
            if (compressedZeros && this.ipv6byteCount == 16) {
                fail("Malformed IPv6 address", start);
            }
            return p;
        }

        private int scanHexPost(int start, int n) throws URISyntaxException {
            if (start == n) {
                return start;
            }
            int q = scanHexSeq(start, n);
            if (q <= start) {
                int p = takeIPv4Address(start, n, "hex digits or IPv4 address");
                this.ipv6byteCount += 4;
                return p;
            } else if (!at(q, n, ':')) {
                return q;
            } else {
                int p2 = takeIPv4Address(q + 1, n, "hex digits or IPv4 address");
                this.ipv6byteCount += 4;
                return p2;
            }
        }

        private int scanHexSeq(int start, int n) throws URISyntaxException {
            int q = scan(start, n, URI.L_HEX, URI.H_HEX);
            if (q <= start || at(q, n, '.')) {
                return -1;
            }
            if (q > start + 4) {
                fail("IPv6 hexadecimal digit sequence too long", start);
            }
            this.ipv6byteCount += 2;
            int p = q;
            while (p < n && at(p, n, ':') && !at(p + 1, n, ':')) {
                int p2 = p + 1;
                int q2 = scan(p2, n, URI.L_HEX, URI.H_HEX);
                if (q2 <= p2) {
                    failExpecting("digits for an IPv6 address", p2);
                }
                if (at(q2, n, '.')) {
                    return p2 - 1;
                }
                if (q2 > p2 + 4) {
                    fail("IPv6 hexadecimal digit sequence too long", p2);
                }
                this.ipv6byteCount += 2;
                p = q2;
            }
            return p;
        }
    }
}
