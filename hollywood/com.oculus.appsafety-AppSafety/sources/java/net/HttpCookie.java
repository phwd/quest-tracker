package java.net;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import libcore.net.http.HttpDate;
import oculus.internal.license.store.LicenseStoreSchema;

public final class HttpCookie implements Cloneable {
    static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final long MAX_AGE_UNSPECIFIED = -1;
    private static final Set<String> RESERVED_NAMES = new HashSet();
    private static final String SET_COOKIE = "set-cookie:";
    private static final String SET_COOKIE2 = "set-cookie2:";
    static final Map<String, CookieAttributeAssignor> assignors = new HashMap();
    private static final String tspecials = ",;= \t";
    private String comment;
    private String commentURL;
    private String domain;
    private final String header;
    private boolean httpOnly;
    private long maxAge;
    private final String name;
    private String path;
    private String portlist;
    private boolean secure;
    private boolean toDiscard;
    private String value;
    private int version;
    private final long whenCreated;

    /* access modifiers changed from: package-private */
    public interface CookieAttributeAssignor {
        void assign(HttpCookie httpCookie, String str, String str2);
    }

    static {
        RESERVED_NAMES.add(LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_COMMENT);
        RESERVED_NAMES.add("commenturl");
        RESERVED_NAMES.add("discard");
        RESERVED_NAMES.add("domain");
        RESERVED_NAMES.add(LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_EXPIRES);
        RESERVED_NAMES.add("httponly");
        RESERVED_NAMES.add("max-age");
        RESERVED_NAMES.add("path");
        RESERVED_NAMES.add("port");
        RESERVED_NAMES.add("secure");
        RESERVED_NAMES.add("version");
        assignors.put(LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_COMMENT, new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass1 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                if (cookie.getComment() == null) {
                    cookie.setComment(attrValue);
                }
            }
        });
        assignors.put("commenturl", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass2 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                if (cookie.getCommentURL() == null) {
                    cookie.setCommentURL(attrValue);
                }
            }
        });
        assignors.put("discard", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass3 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                cookie.setDiscard(true);
            }
        });
        assignors.put("domain", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass4 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                if (cookie.getDomain() == null) {
                    cookie.setDomain(attrValue);
                }
            }
        });
        assignors.put("max-age", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass5 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                try {
                    long maxage = Long.parseLong(attrValue);
                    if (cookie.getMaxAge() == -1) {
                        cookie.setMaxAge(maxage);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Illegal cookie max-age attribute");
                }
            }
        });
        assignors.put("path", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass6 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                if (cookie.getPath() == null) {
                    cookie.setPath(attrValue);
                }
            }
        });
        assignors.put("port", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass7 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                if (cookie.getPortlist() == null) {
                    cookie.setPortlist(attrValue == null ? "" : attrValue);
                }
            }
        });
        assignors.put("secure", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass8 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                cookie.setSecure(true);
            }
        });
        assignors.put("httponly", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass9 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                cookie.setHttpOnly(true);
            }
        });
        assignors.put("version", new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass10 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                try {
                    cookie.setVersion(Integer.parseInt(attrValue));
                } catch (NumberFormatException e) {
                }
            }
        });
        assignors.put(LicenseStoreSchema.LicenseTableSchema.COLUMN_NAME_EXPIRES, new CookieAttributeAssignor() {
            /* class java.net.HttpCookie.AnonymousClass11 */

            @Override // java.net.HttpCookie.CookieAttributeAssignor
            public void assign(HttpCookie cookie, String attrName, String attrValue) {
                if (cookie.getMaxAge() == -1) {
                    Date date = HttpDate.parse(attrValue);
                    long maxAgeInSeconds = 0;
                    if (date != null) {
                        maxAgeInSeconds = (date.getTime() - cookie.whenCreated) / 1000;
                        if (maxAgeInSeconds == -1) {
                            maxAgeInSeconds = 0;
                        }
                    }
                    cookie.setMaxAge(maxAgeInSeconds);
                }
            }
        });
    }

    public HttpCookie(String name2, String value2) {
        this(name2, value2, null);
    }

    private HttpCookie(String name2, String value2, String header2) {
        this.maxAge = -1;
        this.version = 1;
        String name3 = name2.trim();
        if (name3.length() == 0 || !isToken(name3) || name3.charAt(0) == '$') {
            throw new IllegalArgumentException("Illegal cookie name");
        }
        this.name = name3;
        this.value = value2;
        this.toDiscard = false;
        this.secure = false;
        this.whenCreated = System.currentTimeMillis();
        this.portlist = null;
        this.header = header2;
    }

    public static List<HttpCookie> parse(String header2) {
        return parse(header2, false);
    }

    private static List<HttpCookie> parse(String header2, boolean retainHeader) {
        int version2 = guessCookieVersion(header2);
        if (startsWithIgnoreCase(header2, SET_COOKIE2)) {
            header2 = header2.substring(SET_COOKIE2.length());
        } else if (startsWithIgnoreCase(header2, SET_COOKIE)) {
            header2 = header2.substring(SET_COOKIE.length());
        }
        List<HttpCookie> cookies = new ArrayList<>();
        if (version2 == 0) {
            HttpCookie cookie = parseInternal(header2, retainHeader);
            cookie.setVersion(0);
            cookies.add(cookie);
        } else {
            for (String cookieStr : splitMultiCookies(header2)) {
                HttpCookie cookie2 = parseInternal(cookieStr, retainHeader);
                cookie2.setVersion(1);
                cookies.add(cookie2);
            }
        }
        return cookies;
    }

    public boolean hasExpired() {
        long j = this.maxAge;
        if (j == 0) {
            return true;
        }
        if (j != -1 && (System.currentTimeMillis() - this.whenCreated) / 1000 > this.maxAge) {
            return true;
        }
        return false;
    }

    public void setComment(String purpose) {
        this.comment = purpose;
    }

    public String getComment() {
        return this.comment;
    }

    public void setCommentURL(String purpose) {
        this.commentURL = purpose;
    }

    public String getCommentURL() {
        return this.commentURL;
    }

    public void setDiscard(boolean discard) {
        this.toDiscard = discard;
    }

    public boolean getDiscard() {
        return this.toDiscard;
    }

    public void setPortlist(String ports) {
        this.portlist = ports;
    }

    public String getPortlist() {
        return this.portlist;
    }

    public void setDomain(String pattern) {
        if (pattern != null) {
            this.domain = pattern.toLowerCase();
        } else {
            this.domain = pattern;
        }
    }

    public String getDomain() {
        return this.domain;
    }

    public void setMaxAge(long expiry) {
        this.maxAge = expiry;
    }

    public long getMaxAge() {
        return this.maxAge;
    }

    public void setPath(String uri) {
        this.path = uri;
    }

    public String getPath() {
        return this.path;
    }

    public void setSecure(boolean flag) {
        this.secure = flag;
    }

    public boolean getSecure() {
        return this.secure;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

    public String getValue() {
        return this.value;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int v) {
        if (v == 0 || v == 1) {
            this.version = v;
            return;
        }
        throw new IllegalArgumentException("cookie version should be 0 or 1");
    }

    public boolean isHttpOnly() {
        return this.httpOnly;
    }

    public void setHttpOnly(boolean httpOnly2) {
        this.httpOnly = httpOnly2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        if (r10.equalsIgnoreCase(r11 + ".local") != false) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean domainMatches(java.lang.String r10, java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 143
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.HttpCookie.domainMatches(java.lang.String, java.lang.String):boolean");
    }

    private static boolean isFullyQualifiedDomainName(String s, int firstCharacter) {
        int dotPosition = s.indexOf(46, firstCharacter + 1);
        return dotPosition != -1 && dotPosition < s.length() - 1;
    }

    public String toString() {
        if (getVersion() > 0) {
            return toRFC2965HeaderString();
        }
        return toNetscapeHeaderString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HttpCookie)) {
            return false;
        }
        HttpCookie other = (HttpCookie) obj;
        if (!equalsIgnoreCase(getName(), other.getName()) || !equalsIgnoreCase(getDomain(), other.getDomain()) || !Objects.equals(getPath(), other.getPath())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int h1 = this.name.toLowerCase().hashCode();
        String str = this.domain;
        int h3 = 0;
        int h2 = str != null ? str.toLowerCase().hashCode() : 0;
        String str2 = this.path;
        if (str2 != null) {
            h3 = str2.hashCode();
        }
        return h1 + h2 + h3;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static boolean isToken(String value2) {
        if (RESERVED_NAMES.contains(value2.toLowerCase(Locale.US))) {
            return false;
        }
        int len = value2.length();
        for (int i = 0; i < len; i++) {
            char c = value2.charAt(i);
            if (c < ' ' || c >= 127 || tspecials.indexOf(c) != -1) {
                return false;
            }
        }
        return true;
    }

    private static HttpCookie parseInternal(String header2, boolean retainHeader) {
        HttpCookie cookie;
        String value2;
        String name2;
        StringTokenizer tokenizer = new StringTokenizer(header2, ";");
        try {
            String namevaluePair = tokenizer.nextToken();
            int index = namevaluePair.indexOf(61);
            if (index != -1) {
                String name3 = namevaluePair.substring(0, index).trim();
                String value3 = namevaluePair.substring(index + 1).trim();
                if (retainHeader) {
                    cookie = new HttpCookie(name3, stripOffSurroundingQuote(value3), header2);
                } else {
                    cookie = new HttpCookie(name3, stripOffSurroundingQuote(value3));
                }
                while (tokenizer.hasMoreTokens()) {
                    String namevaluePair2 = tokenizer.nextToken();
                    int index2 = namevaluePair2.indexOf(61);
                    if (index2 != -1) {
                        name2 = namevaluePair2.substring(0, index2).trim();
                        value2 = namevaluePair2.substring(index2 + 1).trim();
                    } else {
                        name2 = namevaluePair2.trim();
                        value2 = null;
                    }
                    assignAttribute(cookie, name2, value2);
                }
                return cookie;
            }
            throw new IllegalArgumentException("Invalid cookie name-value pair");
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Empty cookie header string");
        }
    }

    private static void assignAttribute(HttpCookie cookie, String attrName, String attrValue) {
        String attrValue2 = stripOffSurroundingQuote(attrValue);
        CookieAttributeAssignor assignor = assignors.get(attrName.toLowerCase());
        if (assignor != null) {
            assignor.assign(cookie, attrName, attrValue2);
        }
    }

    private String header() {
        return this.header;
    }

    private String toNetscapeHeaderString() {
        return getName() + "=" + getValue();
    }

    private String toRFC2965HeaderString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append("=\"");
        sb.append(getValue());
        sb.append('\"');
        if (getPath() != null) {
            sb.append(";$Path=\"");
            sb.append(getPath());
            sb.append('\"');
        }
        if (getDomain() != null) {
            sb.append(";$Domain=\"");
            sb.append(getDomain());
            sb.append('\"');
        }
        if (getPortlist() != null) {
            sb.append(";$Port=\"");
            sb.append(getPortlist());
            sb.append('\"');
        }
        return sb.toString();
    }

    private static int guessCookieVersion(String header2) {
        String header3 = header2.toLowerCase();
        if (header3.indexOf("expires=") != -1) {
            return 0;
        }
        if (header3.indexOf("version=") == -1 && header3.indexOf("max-age") == -1 && !startsWithIgnoreCase(header3, SET_COOKIE2)) {
            return 0;
        }
        return 1;
    }

    private static String stripOffSurroundingQuote(String str) {
        if (str != null && str.length() > 2 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
            return str.substring(1, str.length() - 1);
        }
        if (str == null || str.length() <= 2 || str.charAt(0) != '\'' || str.charAt(str.length() - 1) != '\'') {
            return str;
        }
        return str.substring(1, str.length() - 1);
    }

    private static boolean equalsIgnoreCase(String s, String t) {
        if (s == t) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.equalsIgnoreCase(t);
    }

    private static boolean startsWithIgnoreCase(String s, String start) {
        if (s == null || start == null || s.length() < start.length() || !start.equalsIgnoreCase(s.substring(0, start.length()))) {
            return false;
        }
        return true;
    }

    private static List<String> splitMultiCookies(String header2) {
        List<String> cookies = new ArrayList<>();
        int quoteCount = 0;
        int q = 0;
        for (int p = 0; p < header2.length(); p++) {
            char c = header2.charAt(p);
            if (c == '\"') {
                quoteCount++;
            }
            if (c == ',' && quoteCount % 2 == 0) {
                cookies.add(header2.substring(q, p));
                q = p + 1;
            }
        }
        cookies.add(header2.substring(q));
        return cookies;
    }
}
