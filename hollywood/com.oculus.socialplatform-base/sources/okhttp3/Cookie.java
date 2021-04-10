package okhttp3;

import X.AnonymousClass006;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.modules.PanelCookiesModuleImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;
import org.apache.commons.cli.HelpFormatter;

public final class Cookie {
    public static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    public static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    public static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    public static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    public final String domain;
    public final long expiresAt;
    public final boolean hostOnly;
    public final boolean httpOnly;
    public final String name;
    public final String path;
    public final boolean persistent;
    public final boolean secure;
    public final String value;

    public static final class Builder {
        public String domain;
        public long expiresAt = HttpDate.MAX_DATE;
        public boolean hostOnly;
        public boolean httpOnly;
        public String name;
        public String path = "/";
        public boolean persistent;
        public boolean secure;
        public String value;

        public Builder hostOnlyDomain(String str) {
            domain(str, true);
            return this;
        }

        public Builder httpOnly() {
            this.httpOnly = true;
            return this;
        }

        public Builder secure() {
            this.secure = true;
            return this;
        }

        public Cookie build() {
            return new Cookie(this);
        }

        public Builder expiresAt(long j) {
            if (j <= 0) {
                j = Long.MIN_VALUE;
            } else if (j > HttpDate.MAX_DATE) {
                j = HttpDate.MAX_DATE;
            }
            this.expiresAt = j;
            this.persistent = true;
            return this;
        }

        public Builder name(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str.trim().equals(str)) {
                this.name = str;
                return this;
            } else {
                throw new IllegalArgumentException("name is not trimmed");
            }
        }

        public Builder path(String str) {
            if (str.startsWith("/")) {
                this.path = str;
                return this;
            }
            throw new IllegalArgumentException("path must start with '/'");
        }

        public Builder value(String str) {
            if (str == null) {
                throw new NullPointerException("value == null");
            } else if (str.trim().equals(str)) {
                this.value = str;
                return this;
            } else {
                throw new IllegalArgumentException("value is not trimmed");
            }
        }

        private Builder domain(String str, boolean z) {
            if (str != null) {
                String domainToAscii = Util.domainToAscii(str);
                if (domainToAscii != null) {
                    this.domain = domainToAscii;
                    this.hostOnly = z;
                    return this;
                }
                throw new IllegalArgumentException(AnonymousClass006.A07("unexpected domain: ", str));
            }
            throw new NullPointerException("domain == null");
        }

        public Builder domain(String str) {
            domain(str, false);
            return this;
        }
    }

    public static long parseExpires(String str, int i, int i2) {
        int dateCharacterOffset = dateCharacterOffset(str, i, i2, false);
        Matcher matcher = TIME_PATTERN.matcher(str);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        while (dateCharacterOffset < i2) {
            int dateCharacterOffset2 = dateCharacterOffset(str, dateCharacterOffset + 1, i2, true);
            matcher.region(dateCharacterOffset, dateCharacterOffset2);
            if (i4 == -1 && matcher.usePattern(TIME_PATTERN).matches()) {
                i4 = Integer.parseInt(matcher.group(1));
                i7 = Integer.parseInt(matcher.group(2));
                i8 = Integer.parseInt(matcher.group(3));
            } else if (i5 == -1 && matcher.usePattern(DAY_OF_MONTH_PATTERN).matches()) {
                i5 = Integer.parseInt(matcher.group(1));
            } else if (i6 == -1 && matcher.usePattern(MONTH_PATTERN).matches()) {
                i6 = MONTH_PATTERN.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) >> 2;
            } else if (i3 == -1 && matcher.usePattern(YEAR_PATTERN).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
            }
            dateCharacterOffset = dateCharacterOffset(str, dateCharacterOffset2 + 1, i2, false);
        }
        if (i3 >= 70 && i3 <= 99) {
            i3 += 1900;
        }
        if (i3 >= 0) {
            if (i3 <= 69) {
                i3 += 2000;
            }
            if (i3 >= 1601) {
                if (i6 == -1) {
                    throw new IllegalArgumentException();
                } else if (i5 < 1 || i5 > 31) {
                    throw new IllegalArgumentException();
                } else if (i4 < 0 || i4 > 23) {
                    throw new IllegalArgumentException();
                } else if (i7 < 0 || i7 > 59) {
                    throw new IllegalArgumentException();
                } else if (i8 < 0 || i8 > 59) {
                    throw new IllegalArgumentException();
                } else {
                    GregorianCalendar gregorianCalendar = new GregorianCalendar(Util.UTC);
                    gregorianCalendar.setLenient(false);
                    gregorianCalendar.set(1, i3);
                    gregorianCalendar.set(2, i6 - 1);
                    gregorianCalendar.set(5, i5);
                    gregorianCalendar.set(11, i4);
                    gregorianCalendar.set(12, i7);
                    gregorianCalendar.set(13, i8);
                    gregorianCalendar.set(14, 0);
                    return gregorianCalendar.getTimeInMillis();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public static int dateCharacterOffset(String str, int i, int i2, boolean z) {
        boolean z2;
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt >= ' ' ? charAt >= 127 || ((charAt >= '0' && charAt <= '9') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= 'A' && charAt <= 'Z') || charAt == ':'))) : charAt != '\t') {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2 == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static boolean domainMatch(HttpUrl httpUrl, String str) {
        String str2 = httpUrl.host;
        if (str2.equals(str) || (str2.endsWith(str) && str2.charAt((str2.length() - str.length()) - 1) == '.' && !Util.verifyAsIpAddress(str2))) {
            return true;
        }
        return false;
    }

    public static List<Cookie> parseAll(HttpUrl httpUrl, Headers headers) {
        List<String> values = headers.values("Set-Cookie");
        int size = values.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            Cookie parse = parse(httpUrl, values.get(i));
            if (parse != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(parse);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public static String parseDomain(String str) {
        if (!str.endsWith(".")) {
            if (str.startsWith(".")) {
                str = str.substring(1);
            }
            String domainToAscii = Util.domainToAscii(str);
            if (domainToAscii != null) {
                return domainToAscii;
            }
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    public static long parseMaxAge(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 0) {
                return parseLong;
            }
            return Long.MIN_VALUE;
        } catch (NumberFormatException e) {
            if (!str.matches("-?\\d+")) {
                throw e;
            } else if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                return RecyclerView.FOREVER_NS;
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (cookie.name.equals(this.name) && cookie.value.equals(this.value) && cookie.domain.equals(this.domain) && cookie.path.equals(this.path) && cookie.expiresAt == this.expiresAt && cookie.secure == this.secure && cookie.httpOnly == this.httpOnly && cookie.persistent == this.persistent && cookie.hostOnly == this.hostOnly) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.expiresAt;
        return ((((((((((((((((527 + this.name.hashCode()) * 31) + this.value.hashCode()) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + (!this.secure ? 1 : 0)) * 31) + (!this.httpOnly ? 1 : 0)) * 31) + (!this.persistent ? 1 : 0)) * 31) + (!this.hostOnly ? 1 : 0);
    }

    public boolean matches(HttpUrl httpUrl) {
        boolean domainMatch;
        if (this.hostOnly) {
            domainMatch = httpUrl.host.equals(this.domain);
        } else {
            domainMatch = domainMatch(httpUrl, this.domain);
        }
        if (!domainMatch || !pathMatch(httpUrl, this.path) || (this.secure && !httpUrl.isHttps())) {
            return false;
        }
        return true;
    }

    public static boolean pathMatch(HttpUrl httpUrl, String str) {
        String encodedPath = httpUrl.encodedPath();
        if (!encodedPath.equals(str)) {
            if (!encodedPath.startsWith(str)) {
                return false;
            }
            if (str.endsWith("/") || encodedPath.charAt(str.length()) == '/') {
                return true;
            }
            return false;
        }
        return true;
    }

    public String domain() {
        return this.domain;
    }

    public long expiresAt() {
        return this.expiresAt;
    }

    public boolean hostOnly() {
        return this.hostOnly;
    }

    public boolean httpOnly() {
        return this.httpOnly;
    }

    public String name() {
        return this.name;
    }

    public String path() {
        return this.path;
    }

    public boolean persistent() {
        return this.persistent;
    }

    public boolean secure() {
        return this.secure;
    }

    public String value() {
        return this.value;
    }

    public Cookie(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.name = str;
        this.value = str2;
        this.expiresAt = j;
        this.domain = str3;
        this.path = str4;
        this.secure = z;
        this.httpOnly = z2;
        this.hostOnly = z3;
        this.persistent = z4;
    }

    public Cookie(Builder builder) {
        String str = builder.name;
        if (str != null) {
            String str2 = builder.value;
            if (str2 != null) {
                String str3 = builder.domain;
                if (str3 != null) {
                    this.name = str;
                    this.value = str2;
                    this.expiresAt = builder.expiresAt;
                    this.domain = str3;
                    this.path = builder.path;
                    this.secure = builder.secure;
                    this.httpOnly = builder.httpOnly;
                    this.persistent = builder.persistent;
                    this.hostOnly = builder.hostOnly;
                    return;
                }
                throw new NullPointerException("builder.domain == null");
            }
            throw new NullPointerException("builder.value == null");
        }
        throw new NullPointerException("builder.name == null");
    }

    public static Cookie parse(long j, HttpUrl httpUrl, String str) {
        long j2;
        String str2;
        int length = str.length();
        char c = ';';
        int delimiterOffset = Util.delimiterOffset(str, 0, length, ';');
        char c2 = '=';
        int delimiterOffset2 = Util.delimiterOffset(str, 0, delimiterOffset, '=');
        String str3 = null;
        if (delimiterOffset2 != delimiterOffset) {
            String trimSubstring = Util.trimSubstring(str, 0, delimiterOffset2);
            if (!trimSubstring.isEmpty() && Util.indexOfControlOrNonAscii(trimSubstring) == -1) {
                String trimSubstring2 = Util.trimSubstring(str, delimiterOffset2 + 1, delimiterOffset);
                if (Util.indexOfControlOrNonAscii(trimSubstring2) == -1) {
                    int i = delimiterOffset + 1;
                    String str4 = null;
                    long j3 = -1;
                    long j4 = HttpDate.MAX_DATE;
                    boolean z = false;
                    boolean z2 = false;
                    boolean z3 = true;
                    boolean z4 = false;
                    while (i < length) {
                        int delimiterOffset3 = Util.delimiterOffset(str, i, length, c);
                        int delimiterOffset4 = Util.delimiterOffset(str, i, delimiterOffset3, c2);
                        String trimSubstring3 = Util.trimSubstring(str, i, delimiterOffset4);
                        if (delimiterOffset4 < delimiterOffset3) {
                            str2 = Util.trimSubstring(str, delimiterOffset4 + 1, delimiterOffset3);
                        } else {
                            str2 = "";
                        }
                        if (trimSubstring3.equalsIgnoreCase(PanelCookiesModuleImpl.COOKIE_JSON_EXPIRES)) {
                            try {
                                j4 = parseExpires(str2, 0, str2.length());
                            } catch (IllegalArgumentException unused) {
                            }
                        } else if (trimSubstring3.equalsIgnoreCase("max-age")) {
                            j3 = parseMaxAge(str2);
                        } else {
                            if (trimSubstring3.equalsIgnoreCase("domain")) {
                                str3 = parseDomain(str2);
                                z3 = false;
                            } else if (trimSubstring3.equalsIgnoreCase("path")) {
                                str4 = str2;
                            } else if (trimSubstring3.equalsIgnoreCase("secure")) {
                                z = true;
                            } else if (trimSubstring3.equalsIgnoreCase("httponly")) {
                                z2 = true;
                            }
                            i = delimiterOffset3 + 1;
                            c = ';';
                            c2 = '=';
                        }
                        z4 = true;
                        i = delimiterOffset3 + 1;
                        c = ';';
                        c2 = '=';
                    }
                    long j5 = Long.MIN_VALUE;
                    if (j3 != Long.MIN_VALUE) {
                        if (j3 != -1) {
                            if (j3 <= 9223372036854775L) {
                                j2 = j3 * 1000;
                            } else {
                                j2 = RecyclerView.FOREVER_NS;
                            }
                            j5 = j + j2;
                            if (j5 < j || j5 > HttpDate.MAX_DATE) {
                                j5 = HttpDate.MAX_DATE;
                            }
                        } else {
                            j5 = j4;
                        }
                    }
                    if (str3 == null) {
                        str3 = httpUrl.host;
                    } else if (!domainMatch(httpUrl, str3)) {
                        return null;
                    }
                    String str5 = "/";
                    if (str4 == null || !str4.startsWith(str5)) {
                        String encodedPath = httpUrl.encodedPath();
                        int lastIndexOf = encodedPath.lastIndexOf(47);
                        if (lastIndexOf != 0) {
                            str5 = encodedPath.substring(0, lastIndexOf);
                        }
                    } else {
                        str5 = str4;
                    }
                    return new Cookie(trimSubstring, trimSubstring2, j5, str3, str5, z, z2, z3, z4);
                }
            }
        }
        return null;
    }

    public static Cookie parse(HttpUrl httpUrl, String str) {
        return parse(System.currentTimeMillis(), httpUrl, str);
    }

    public String toString() {
        return toString(false);
    }

    public String toString(boolean z) {
        String format;
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append('=');
        sb.append(this.value);
        if (this.persistent) {
            long j = this.expiresAt;
            if (j == Long.MIN_VALUE) {
                format = "; max-age=0";
            } else {
                sb.append("; expires=");
                format = HttpDate.format(new Date(j));
            }
            sb.append(format);
        }
        if (!this.hostOnly) {
            sb.append("; domain=");
            if (z) {
                sb.append(".");
            }
            sb.append(this.domain);
        }
        sb.append("; path=");
        sb.append(this.path);
        if (this.secure) {
            sb.append("; secure");
        }
        if (this.httpOnly) {
            sb.append("; httponly");
        }
        return sb.toString();
    }
}
