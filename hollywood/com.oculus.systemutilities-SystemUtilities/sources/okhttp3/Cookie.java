package okhttp3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;

public final class Cookie {
    private static final Pattern DAY_OF_MONTH_PATTERN = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern MONTH_PATTERN = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern TIME_PATTERN = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private static final Pattern YEAR_PATTERN = Pattern.compile("(\\d{2,4})[^\\d]*");
    private final String domain;
    private final long expiresAt;
    private final boolean hostOnly;
    private final boolean httpOnly;
    private final String name;
    private final String path;
    private final boolean persistent;
    private final boolean secure;
    private final String value;

    private Cookie(String name2, String value2, long expiresAt2, String domain2, String path2, boolean secure2, boolean httpOnly2, boolean hostOnly2, boolean persistent2) {
        this.name = name2;
        this.value = value2;
        this.expiresAt = expiresAt2;
        this.domain = domain2;
        this.path = path2;
        this.secure = secure2;
        this.httpOnly = httpOnly2;
        this.hostOnly = hostOnly2;
        this.persistent = persistent2;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    private static boolean domainMatch(HttpUrl url, String domain2) {
        String urlHost = url.host();
        if (urlHost.equals(domain2)) {
            return true;
        }
        if (!urlHost.endsWith(domain2) || urlHost.charAt((urlHost.length() - domain2.length()) - 1) != '.' || Util.verifyAsIpAddress(urlHost)) {
            return false;
        }
        return true;
    }

    public static Cookie parse(HttpUrl url, String setCookie) {
        return parse(System.currentTimeMillis(), url, setCookie);
    }

    static Cookie parse(long currentTimeMillis, HttpUrl url, String setCookie) {
        long deltaMilliseconds;
        String attributeValue;
        int limit = setCookie.length();
        int cookiePairEnd = Util.delimiterOffset(setCookie, 0, limit, ';');
        int pairEqualsSign = Util.delimiterOffset(setCookie, 0, cookiePairEnd, '=');
        if (pairEqualsSign == cookiePairEnd) {
            return null;
        }
        String cookieName = Util.trimSubstring(setCookie, 0, pairEqualsSign);
        if (cookieName.isEmpty() || Util.indexOfControlOrNonAscii(cookieName) != -1) {
            return null;
        }
        String cookieValue = Util.trimSubstring(setCookie, pairEqualsSign + 1, cookiePairEnd);
        if (Util.indexOfControlOrNonAscii(cookieValue) != -1) {
            return null;
        }
        long expiresAt2 = 253402300799999L;
        long deltaSeconds = -1;
        String domain2 = null;
        String path2 = null;
        boolean secureOnly = false;
        boolean httpOnly2 = false;
        boolean hostOnly2 = true;
        boolean persistent2 = false;
        int pos = cookiePairEnd + 1;
        while (pos < limit) {
            int attributePairEnd = Util.delimiterOffset(setCookie, pos, limit, ';');
            int attributeEqualsSign = Util.delimiterOffset(setCookie, pos, attributePairEnd, '=');
            String attributeName = Util.trimSubstring(setCookie, pos, attributeEqualsSign);
            if (attributeEqualsSign < attributePairEnd) {
                attributeValue = Util.trimSubstring(setCookie, attributeEqualsSign + 1, attributePairEnd);
            } else {
                attributeValue = "";
            }
            if (attributeName.equalsIgnoreCase("expires")) {
                try {
                    expiresAt2 = parseExpires(attributeValue, 0, attributeValue.length());
                    persistent2 = true;
                } catch (IllegalArgumentException e) {
                }
            } else if (attributeName.equalsIgnoreCase("max-age")) {
                try {
                    deltaSeconds = parseMaxAge(attributeValue);
                    persistent2 = true;
                } catch (NumberFormatException e2) {
                }
            } else if (attributeName.equalsIgnoreCase("domain")) {
                try {
                    domain2 = parseDomain(attributeValue);
                    hostOnly2 = false;
                } catch (IllegalArgumentException e3) {
                }
            } else if (attributeName.equalsIgnoreCase("path")) {
                path2 = attributeValue;
            } else if (attributeName.equalsIgnoreCase("secure")) {
                secureOnly = true;
            } else if (attributeName.equalsIgnoreCase("httponly")) {
                httpOnly2 = true;
            }
            pos = attributePairEnd + 1;
        }
        if (deltaSeconds == Long.MIN_VALUE) {
            expiresAt2 = Long.MIN_VALUE;
        } else if (deltaSeconds != -1) {
            if (deltaSeconds <= 9223372036854775L) {
                deltaMilliseconds = deltaSeconds * 1000;
            } else {
                deltaMilliseconds = Long.MAX_VALUE;
            }
            expiresAt2 = currentTimeMillis + deltaMilliseconds;
            if (expiresAt2 < currentTimeMillis || expiresAt2 > 253402300799999L) {
                expiresAt2 = 253402300799999L;
            }
        }
        if (domain2 == null) {
            domain2 = url.host();
        } else if (!domainMatch(url, domain2)) {
            return null;
        }
        if (path2 == null || !path2.startsWith("/")) {
            String encodedPath = url.encodedPath();
            int lastSlash = encodedPath.lastIndexOf(47);
            path2 = lastSlash != 0 ? encodedPath.substring(0, lastSlash) : "/";
        }
        return new Cookie(cookieName, cookieValue, expiresAt2, domain2, path2, secureOnly, httpOnly2, hostOnly2, persistent2);
    }

    private static long parseExpires(String s, int pos, int limit) {
        int pos2 = dateCharacterOffset(s, pos, limit, false);
        int hour = -1;
        int minute = -1;
        int second = -1;
        int dayOfMonth = -1;
        int month = -1;
        int year = -1;
        Matcher matcher = TIME_PATTERN.matcher(s);
        while (pos2 < limit) {
            int end = dateCharacterOffset(s, pos2 + 1, limit, true);
            matcher.region(pos2, end);
            if (hour == -1 && matcher.usePattern(TIME_PATTERN).matches()) {
                hour = Integer.parseInt(matcher.group(1));
                minute = Integer.parseInt(matcher.group(2));
                second = Integer.parseInt(matcher.group(3));
            } else if (dayOfMonth == -1 && matcher.usePattern(DAY_OF_MONTH_PATTERN).matches()) {
                dayOfMonth = Integer.parseInt(matcher.group(1));
            } else if (month == -1 && matcher.usePattern(MONTH_PATTERN).matches()) {
                month = MONTH_PATTERN.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (year == -1 && matcher.usePattern(YEAR_PATTERN).matches()) {
                year = Integer.parseInt(matcher.group(1));
            }
            pos2 = dateCharacterOffset(s, end + 1, limit, false);
        }
        if (year >= 70 && year <= 99) {
            year += 1900;
        }
        if (year >= 0 && year <= 69) {
            year += 2000;
        }
        if (year < 1601) {
            throw new IllegalArgumentException();
        } else if (month == -1) {
            throw new IllegalArgumentException();
        } else if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException();
        } else if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException();
        } else if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException();
        } else if (second < 0 || second > 59) {
            throw new IllegalArgumentException();
        } else {
            Calendar calendar = new GregorianCalendar(Util.UTC);
            calendar.setLenient(false);
            calendar.set(1, year);
            calendar.set(2, month - 1);
            calendar.set(5, dayOfMonth);
            calendar.set(11, hour);
            calendar.set(12, minute);
            calendar.set(13, second);
            calendar.set(14, 0);
            return calendar.getTimeInMillis();
        }
    }

    private static int dateCharacterOffset(String input, int pos, int limit, boolean invert) {
        for (int i = pos; i < limit; i++) {
            int c = input.charAt(i);
            if (((c < 32 && c != 9) || c >= 127 || (c >= 48 && c <= 57) || ((c >= 97 && c <= 122) || ((c >= 65 && c <= 90) || c == 58))) == (!invert)) {
                return i;
            }
        }
        return limit;
    }

    private static long parseMaxAge(String s) {
        long parsed = Long.MIN_VALUE;
        try {
            long parsed2 = Long.parseLong(s);
            if (parsed2 <= 0) {
                return Long.MIN_VALUE;
            }
            return parsed2;
        } catch (NumberFormatException e) {
            if (s.matches("-?\\d+")) {
                if (!s.startsWith("-")) {
                    parsed = Long.MAX_VALUE;
                }
                return parsed;
            }
            throw e;
        }
    }

    private static String parseDomain(String s) {
        if (s.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (s.startsWith(".")) {
            s = s.substring(1);
        }
        String canonicalDomain = Util.domainToAscii(s);
        if (canonicalDomain != null) {
            return canonicalDomain;
        }
        throw new IllegalArgumentException();
    }

    public static List<Cookie> parseAll(HttpUrl url, Headers headers) {
        List<String> cookieStrings = headers.values("Set-Cookie");
        List<Cookie> cookies = null;
        int size = cookieStrings.size();
        for (int i = 0; i < size; i++) {
            Cookie cookie = parse(url, cookieStrings.get(i));
            if (cookie != null) {
                if (cookies == null) {
                    cookies = new ArrayList<>();
                }
                cookies.add(cookie);
            }
        }
        if (cookies != null) {
            return Collections.unmodifiableList(cookies);
        }
        return Collections.emptyList();
    }

    public String toString() {
        return toString(false);
    }

    /* access modifiers changed from: package-private */
    public String toString(boolean forObsoleteRfc2965) {
        StringBuilder result = new StringBuilder();
        result.append(this.name);
        result.append('=');
        result.append(this.value);
        if (this.persistent) {
            if (this.expiresAt == Long.MIN_VALUE) {
                result.append("; max-age=0");
            } else {
                result.append("; expires=").append(HttpDate.format(new Date(this.expiresAt)));
            }
        }
        if (!this.hostOnly) {
            result.append("; domain=");
            if (forObsoleteRfc2965) {
                result.append(".");
            }
            result.append(this.domain);
        }
        result.append("; path=").append(this.path);
        if (this.secure) {
            result.append("; secure");
        }
        if (this.httpOnly) {
            result.append("; httponly");
        }
        return result.toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Cookie)) {
            return false;
        }
        Cookie that = (Cookie) other;
        if (that.name.equals(this.name) && that.value.equals(this.value) && that.domain.equals(this.domain) && that.path.equals(this.path) && that.expiresAt == this.expiresAt && that.secure == this.secure && that.httpOnly == this.httpOnly && that.persistent == this.persistent && that.hostOnly == this.hostOnly) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int hashCode = (((((((((this.name.hashCode() + 527) * 31) + this.value.hashCode()) * 31) + this.domain.hashCode()) * 31) + this.path.hashCode()) * 31) + ((int) (this.expiresAt ^ (this.expiresAt >>> 32)))) * 31;
        if (this.secure) {
            i = 0;
        } else {
            i = 1;
        }
        int i5 = (hashCode + i) * 31;
        if (this.httpOnly) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        int i6 = (i5 + i2) * 31;
        if (this.persistent) {
            i3 = 0;
        } else {
            i3 = 1;
        }
        int i7 = (i6 + i3) * 31;
        if (!this.hostOnly) {
            i4 = 1;
        }
        return i7 + i4;
    }
}
