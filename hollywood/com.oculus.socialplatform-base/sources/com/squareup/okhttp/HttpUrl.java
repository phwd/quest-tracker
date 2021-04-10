package com.squareup.okhttp;

import X.AnonymousClass006;
import com.adobe.xmp.impl.Base64;
import com.oculus.localmedia.LocalMediaManager;
import java.net.IDN;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import okio.Buffer;

public final class HttpUrl {
    public static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    public static final String FRAGMENT_ENCODE_SET = "";
    public static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    public static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    public static final String QUERY_COMPONENT_ENCODE_SET = " \"'<>#&=";
    public static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    public static final String QUERY_ENCODE_SET = " \"'<>#";
    public static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    public final String fragment;
    public final String host;
    public final String password;
    public final List<String> pathSegments;
    public final int port;
    public final List<String> queryNamesAndValues;
    public final String scheme;
    public final String url;
    public final String username;

    public static final class Builder {
        public String encodedFragment;
        public String encodedPassword = "";
        public final List<String> encodedPathSegments;
        public List<String> encodedQueryNamesAndValues;
        public String encodedUsername = "";
        public String host;
        public int port = -1;
        public String scheme;

        public enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public static String canonicalizeHost(String str, int i, int i2) {
            String percentDecode = HttpUrl.percentDecode(str, i, i2, false);
            if (!percentDecode.startsWith("[") || !percentDecode.endsWith("]")) {
                return domainToAscii(percentDecode);
            }
            InetAddress decodeIpv6 = decodeIpv6(percentDecode, 1, percentDecode.length() - 1);
            if (decodeIpv6 == null) {
                return null;
            }
            byte[] address = decodeIpv6.getAddress();
            if (address.length == 16) {
                return inet6AddressToAscii(address);
            }
            throw new AssertionError();
        }

        public static boolean containsInvalidHostnameAsciiCodes(String str) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt <= 31 || charAt >= 127 || " #%/:?@[\\]".indexOf(charAt) != -1) {
                    return true;
                }
            }
            return false;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
            if ((r4 - r8) == 0) goto L_0x0027;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static boolean decodeIpv4Suffix(java.lang.String r7, int r8, int r9, byte[] r10, int r11) {
            /*
                r5 = r11
            L_0x0001:
                r6 = 0
                if (r8 >= r9) goto L_0x003f
                int r0 = r10.length
                if (r5 == r0) goto L_0x0027
                if (r5 == r11) goto L_0x0013
                char r1 = r7.charAt(r8)
                r0 = 46
                if (r1 != r0) goto L_0x0027
                int r8 = r8 + 1
            L_0x0013:
                r4 = r8
                r2 = 0
            L_0x0015:
                if (r4 >= r9) goto L_0x0033
                char r3 = r7.charAt(r4)
                r1 = 48
                if (r3 < r1) goto L_0x0033
                r0 = 57
                if (r3 > r0) goto L_0x0033
                if (r2 != 0) goto L_0x0028
                if (r8 == r4) goto L_0x0028
            L_0x0027:
                return r6
            L_0x0028:
                int r2 = r2 * 10
                int r2 = r2 + r3
                int r2 = r2 - r1
                r0 = 255(0xff, float:3.57E-43)
                if (r2 > r0) goto L_0x0027
                int r4 = r4 + 1
                goto L_0x0015
            L_0x0033:
                int r0 = r4 - r8
                if (r0 == 0) goto L_0x0027
                int r1 = r5 + 1
                byte r0 = (byte) r2
                r10[r5] = r0
                r5 = r1
                r8 = r4
                goto L_0x0001
            L_0x003f:
                int r0 = r11 + 4
                if (r5 != r0) goto L_0x0027
                r0 = 1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.Builder.decodeIpv4Suffix(java.lang.String, int, int, byte[], int):boolean");
        }

        public static String domainToAscii(String str) {
            try {
                String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
                if (lowerCase.isEmpty() || containsInvalidHostnameAsciiCodes(lowerCase)) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        public static String inet6AddressToAscii(byte[] bArr) {
            int length;
            int i = 0;
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            while (true) {
                length = bArr.length;
                if (i2 >= length) {
                    break;
                }
                int i5 = i2;
                while (i5 < 16 && bArr[i5] == 0 && bArr[i5 + 1] == 0) {
                    i5 += 2;
                }
                int i6 = i5 - i2;
                if (i6 > i4) {
                    i3 = i2;
                    i4 = i6;
                }
                i2 = i5 + 2;
            }
            Buffer buffer = new Buffer();
            while (i < length) {
                if (i == i3) {
                    buffer.writeByte(58);
                    i += i4;
                    if (i == 16) {
                        buffer.writeByte(58);
                    }
                } else {
                    if (i > 0) {
                        buffer.writeByte(58);
                    }
                    buffer.writeHexadecimalUnsignedLong((long) (((bArr[i] & Base64.INVALID) << 8) | (bArr[i + 1] & Base64.INVALID)));
                    i += 2;
                }
            }
            return buffer.readUtf8();
        }

        public static int parsePort(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(HttpUrl.canonicalize(str, i, i2, "", false, false, true));
                if (parseInt <= 0 || parseInt > 65535) {
                    return -1;
                }
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
            */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x003e A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0021  */
        private void resolvePath(java.lang.String r10, int r11, int r12) {
            /*
                r9 = this;
                r6 = r11
                if (r11 == r12) goto L_0x003e
                r4 = r10
                char r1 = r10.charAt(r11)
                r0 = 47
                java.lang.String r2 = ""
                r8 = 1
                r3 = r9
                if (r1 == r0) goto L_0x0033
                r0 = 92
                if (r1 == r0) goto L_0x0033
                java.util.List<java.lang.String> r1 = r9.encodedPathSegments
                int r0 = r1.size()
                int r0 = r0 - r8
                r1.set(r0, r2)
            L_0x001e:
                r5 = r6
                if (r6 >= r12) goto L_0x003e
                java.lang.String r0 = "/\\"
                int r6 = com.squareup.okhttp.HttpUrl.delimiterOffset(r10, r6, r12, r0)
                r7 = 0
                if (r6 >= r12) goto L_0x002b
                r7 = 1
            L_0x002b:
                r3.push(r4, r5, r6, r7, r8)
                if (r7 == 0) goto L_0x001e
            L_0x0030:
                int r6 = r6 + 1
                goto L_0x001e
            L_0x0033:
                java.util.List<java.lang.String> r0 = r9.encodedPathSegments
                r0.clear()
                java.util.List<java.lang.String> r0 = r9.encodedPathSegments
                r0.add(r2)
                goto L_0x0030
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.Builder.resolvePath(java.lang.String, int, int):void");
        }

        public static int slashCount(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        public Builder addEncodedPathSegment(String str) {
            if (str != null) {
                push(str, 0, str.length(), false, true);
                return this;
            }
            throw new IllegalArgumentException("encodedPathSegment == null");
        }

        public Builder addPathSegment(String str) {
            if (str != null) {
                push(str, 0, str.length(), false, false);
                return this;
            }
            throw new IllegalArgumentException("pathSegment == null");
        }

        public Builder setEncodedPathSegment(int i, String str) {
            if (str != null) {
                String canonicalize = HttpUrl.canonicalize(str, 0, str.length(), " \"<>^`{}|/\\?#", true, false, true);
                this.encodedPathSegments.set(i, canonicalize);
                if (!isDot(canonicalize) && !isDotDot(canonicalize)) {
                    return this;
                }
                throw new IllegalArgumentException(AnonymousClass006.A07("unexpected path segment: ", str));
            }
            throw new IllegalArgumentException("encodedPathSegment == null");
        }

        public Builder setPathSegment(int i, String str) {
            if (str != null) {
                String canonicalize = HttpUrl.canonicalize(str, 0, str.length(), " \"<>^`{}|/\\?#", false, false, true);
                if (isDot(canonicalize) || isDotDot(canonicalize)) {
                    throw new IllegalArgumentException(AnonymousClass006.A07("unexpected path segment: ", str));
                }
                this.encodedPathSegments.set(i, canonicalize);
                return this;
            }
            throw new IllegalArgumentException("pathSegment == null");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0072, code lost:
            if (r3 == 16) goto L_0x0082;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0074, code lost:
            if (r2 == -1) goto L_0x008d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0076, code lost:
            r1 = r3 - r2;
            java.lang.System.arraycopy(r5, r2, r5, 16 - r1, r1);
            java.util.Arrays.fill(r5, r2, (16 - r3) + r2, (byte) 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0086, code lost:
            return java.net.InetAddress.getByAddress(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x008c, code lost:
            throw new java.lang.AssertionError();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.net.InetAddress decodeIpv6(java.lang.String r11, int r12, int r13) {
            /*
            // Method dump skipped, instructions count: 142
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.Builder.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
        }

        private boolean isDot(String str) {
            if (str.equals(".") || str.equalsIgnoreCase("%2e")) {
                return true;
            }
            return false;
        }

        private boolean isDotDot(String str) {
            if (str.equals(LocalMediaManager.PARENT_FOLDER_NAME) || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e")) {
                return true;
            }
            return false;
        }

        private void pop() {
            List<String> list = this.encodedPathSegments;
            if (!list.remove(list.size() - 1).isEmpty() || this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
                return;
            }
            List<String> list2 = this.encodedPathSegments;
            list2.set(list2.size() - 1, "");
        }

        public static int portColonOffset(String str, int i, int i2) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt == ':') {
                    return i;
                }
                if (charAt == '[') {
                    do {
                        i++;
                        if (i >= i2) {
                            break;
                        }
                    } while (str.charAt(i) != ']');
                }
                i++;
            }
            return i2;
        }

        private void push(String str, int i, int i2, boolean z, boolean z2) {
            String canonicalize = HttpUrl.canonicalize(str, i, i2, " \"<>^`{}|/\\?#", z2, false, true);
            if (isDot(canonicalize)) {
                return;
            }
            if (isDotDot(canonicalize)) {
                pop();
                return;
            }
            List<String> list = this.encodedPathSegments;
            if (list.get(list.size() - 1).isEmpty()) {
                List<String> list2 = this.encodedPathSegments;
                list2.set(list2.size() - 1, canonicalize);
            } else {
                this.encodedPathSegments.add(canonicalize);
            }
            if (z) {
                this.encodedPathSegments.add("");
            }
        }

        private void removeAllCanonicalQueryParameters(String str) {
            int size = this.encodedQueryNamesAndValues.size();
            while (true) {
                size -= 2;
                if (size < 0) {
                    return;
                }
                if (str.equals(this.encodedQueryNamesAndValues.get(size))) {
                    this.encodedQueryNamesAndValues.remove(size + 1);
                    this.encodedQueryNamesAndValues.remove(size);
                    if (this.encodedQueryNamesAndValues.isEmpty()) {
                        this.encodedQueryNamesAndValues = null;
                        return;
                    }
                }
            }
        }

        public static int schemeDelimiterOffset(String str, int i, int i2) {
            char charAt;
            if (i2 - i >= 2 && (((charAt = str.charAt(i)) >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                while (true) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                    char charAt2 = str.charAt(i);
                    if ((charAt2 < 'a' || charAt2 > 'z') && ((charAt2 < 'A' || charAt2 > 'Z') && !((charAt2 >= '0' && charAt2 <= '9') || charAt2 == '+' || charAt2 == '-' || charAt2 == '.'))) {
                        if (charAt2 == ':') {
                            return i;
                        }
                    }
                }
            }
            return -1;
        }

        private int skipLeadingAsciiWhitespace(String str, int i, int i2) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                    return i;
                }
                i++;
            }
            return i2;
        }

        private int skipTrailingAsciiWhitespace(String str, int i, int i2) {
            while (true) {
                i2--;
                if (i2 < i) {
                    return i;
                }
                char charAt = str.charAt(i2);
                if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                    return i2 + 1;
                }
            }
        }

        public Builder addEncodedQueryParameter(String str, String str2) {
            String str3;
            if (str != null) {
                List list = this.encodedQueryNamesAndValues;
                if (list == null) {
                    list = new ArrayList();
                    this.encodedQueryNamesAndValues = list;
                }
                list.add(HttpUrl.canonicalize(str, " \"'<>#&=", true, true, true));
                List<String> list2 = this.encodedQueryNamesAndValues;
                if (str2 != null) {
                    str3 = HttpUrl.canonicalize(str2, " \"'<>#&=", true, true, true);
                } else {
                    str3 = null;
                }
                list2.add(str3);
                return this;
            }
            throw new IllegalArgumentException("encodedName == null");
        }

        public Builder addQueryParameter(String str, String str2) {
            String str3;
            if (str != null) {
                List list = this.encodedQueryNamesAndValues;
                if (list == null) {
                    list = new ArrayList();
                    this.encodedQueryNamesAndValues = list;
                }
                list.add(HttpUrl.canonicalize(str, " \"'<>#&=", false, true, true));
                List<String> list2 = this.encodedQueryNamesAndValues;
                if (str2 != null) {
                    str3 = HttpUrl.canonicalize(str2, " \"'<>#&=", false, true, true);
                } else {
                    str3 = null;
                }
                list2.add(str3);
                return this;
            }
            throw new IllegalArgumentException("name == null");
        }

        public HttpUrl build() {
            if (this.scheme == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.host != null) {
                return new HttpUrl(this);
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public int effectivePort() {
            int i = this.port;
            if (i == -1) {
                return HttpUrl.defaultPort(this.scheme);
            }
            return i;
        }

        public Builder encodedFragment(String str) {
            String str2;
            if (str != null) {
                str2 = HttpUrl.canonicalize(str, "", true, false, false);
            } else {
                str2 = null;
            }
            this.encodedFragment = str2;
            return this;
        }

        public Builder encodedPassword(String str) {
            if (str != null) {
                this.encodedPassword = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", true, false, true);
                return this;
            }
            throw new IllegalArgumentException("encodedPassword == null");
        }

        public Builder encodedPath(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPath == null");
            } else if (str.startsWith("/")) {
                resolvePath(str, 0, str.length());
                return this;
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A07("unexpected encodedPath: ", str));
            }
        }

        public Builder encodedQuery(String str) {
            List<String> list;
            if (str != null) {
                list = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(str, " \"'<>#", true, true, true));
            } else {
                list = null;
            }
            this.encodedQueryNamesAndValues = list;
            return this;
        }

        public Builder encodedUsername(String str) {
            if (str != null) {
                this.encodedUsername = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", true, false, true);
                return this;
            }
            throw new IllegalArgumentException("encodedUsername == null");
        }

        public Builder fragment(String str) {
            String str2;
            if (str != null) {
                str2 = HttpUrl.canonicalize(str, "", false, false, false);
            } else {
                str2 = null;
            }
            this.encodedFragment = str2;
            return this;
        }

        public Builder host(String str) {
            if (str != null) {
                String canonicalizeHost = canonicalizeHost(str, 0, str.length());
                if (canonicalizeHost != null) {
                    this.host = canonicalizeHost;
                    return this;
                }
                throw new IllegalArgumentException(AnonymousClass006.A07("unexpected host: ", str));
            }
            throw new IllegalArgumentException("host == null");
        }

        public ParseResult parse(HttpUrl httpUrl, String str) {
            int delimiterOffset;
            char charAt;
            int length = str.length();
            int skipLeadingAsciiWhitespace = skipLeadingAsciiWhitespace(str, 0, length);
            int skipTrailingAsciiWhitespace = skipTrailingAsciiWhitespace(str, skipLeadingAsciiWhitespace, length);
            if (schemeDelimiterOffset(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace) != -1) {
                if (str.regionMatches(true, skipLeadingAsciiWhitespace, "https:", 0, 6)) {
                    this.scheme = "https";
                    skipLeadingAsciiWhitespace += 6;
                } else if (!str.regionMatches(true, skipLeadingAsciiWhitespace, "http:", 0, 5)) {
                    return ParseResult.UNSUPPORTED_SCHEME;
                } else {
                    this.scheme = "http";
                    skipLeadingAsciiWhitespace += 5;
                }
            } else if (httpUrl == null) {
                return ParseResult.MISSING_SCHEME;
            } else {
                this.scheme = httpUrl.scheme;
            }
            int slashCount = slashCount(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace);
            if (slashCount >= 2 || httpUrl == null || !httpUrl.scheme.equals(this.scheme)) {
                int i = skipLeadingAsciiWhitespace + slashCount;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    delimiterOffset = HttpUrl.delimiterOffset(str, i, skipTrailingAsciiWhitespace, "@/\\?#");
                    if (delimiterOffset == skipTrailingAsciiWhitespace || (charAt = str.charAt(delimiterOffset)) == 65535 || charAt == '#' || charAt == '/' || charAt == '\\' || charAt == '?') {
                        int portColonOffset = portColonOffset(str, i, delimiterOffset);
                        int i2 = portColonOffset + 1;
                        this.host = canonicalizeHost(str, i, portColonOffset);
                    } else if (charAt == '@') {
                        if (!z) {
                            int delimiterOffset2 = HttpUrl.delimiterOffset(str, i, delimiterOffset, ":");
                            String canonicalize = HttpUrl.canonicalize(str, i, delimiterOffset2, " \"':;<=>@[]^`{}|/\\?#", true, false, true);
                            if (z2) {
                                canonicalize = AnonymousClass006.A09(this.encodedUsername, "%40", canonicalize);
                            }
                            this.encodedUsername = canonicalize;
                            if (delimiterOffset2 != delimiterOffset) {
                                this.encodedPassword = HttpUrl.canonicalize(str, delimiterOffset2 + 1, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, true);
                                z = true;
                            }
                            z2 = true;
                        } else {
                            this.encodedPassword = AnonymousClass006.A09(this.encodedPassword, "%40", HttpUrl.canonicalize(str, i, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false, true));
                        }
                        i = delimiterOffset + 1;
                    }
                }
                int portColonOffset2 = portColonOffset(str, i, delimiterOffset);
                int i22 = portColonOffset2 + 1;
                this.host = canonicalizeHost(str, i, portColonOffset2);
                if (i22 < delimiterOffset) {
                    int parsePort = parsePort(str, i22, delimiterOffset);
                    this.port = parsePort;
                    if (parsePort == -1) {
                        return ParseResult.INVALID_PORT;
                    }
                } else {
                    this.port = HttpUrl.defaultPort(this.scheme);
                }
                if (this.host == null) {
                    return ParseResult.INVALID_HOST;
                }
                skipLeadingAsciiWhitespace = delimiterOffset;
            } else {
                this.encodedUsername = httpUrl.encodedUsername();
                this.encodedPassword = httpUrl.encodedPassword();
                this.host = httpUrl.host;
                this.port = httpUrl.port;
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(httpUrl.encodedPathSegments());
                if (skipLeadingAsciiWhitespace == skipTrailingAsciiWhitespace || str.charAt(skipLeadingAsciiWhitespace) == '#') {
                    encodedQuery(httpUrl.encodedQuery());
                }
            }
            int delimiterOffset3 = HttpUrl.delimiterOffset(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace, "?#");
            resolvePath(str, skipLeadingAsciiWhitespace, delimiterOffset3);
            if (delimiterOffset3 < skipTrailingAsciiWhitespace && str.charAt(delimiterOffset3) == '?') {
                delimiterOffset3 = HttpUrl.delimiterOffset(str, delimiterOffset3, skipTrailingAsciiWhitespace, "#");
                this.encodedQueryNamesAndValues = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(str, delimiterOffset3 + 1, delimiterOffset3, " \"'<>#", true, true, true));
            }
            if (delimiterOffset3 < skipTrailingAsciiWhitespace && str.charAt(delimiterOffset3) == '#') {
                this.encodedFragment = HttpUrl.canonicalize(str, 1 + delimiterOffset3, skipTrailingAsciiWhitespace, "", true, false, false);
            }
            return ParseResult.SUCCESS;
        }

        public Builder password(String str) {
            if (str != null) {
                this.encodedPassword = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", false, false, true);
                return this;
            }
            throw new IllegalArgumentException("password == null");
        }

        public Builder port(int i) {
            if (i <= 0 || i > 65535) {
                throw new IllegalArgumentException(AnonymousClass006.A03("unexpected port: ", i));
            }
            this.port = i;
            return this;
        }

        public Builder query(String str) {
            List<String> list;
            if (str != null) {
                list = HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(str, " \"'<>#", false, true, true));
            } else {
                list = null;
            }
            this.encodedQueryNamesAndValues = list;
            return this;
        }

        public Builder reencodeForUri() {
            int size = this.encodedPathSegments.size();
            for (int i = 0; i < size; i++) {
                this.encodedPathSegments.set(i, HttpUrl.canonicalize(this.encodedPathSegments.get(i), "[]", true, false, true));
            }
            List<String> list = this.encodedQueryNamesAndValues;
            if (list != null) {
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = this.encodedQueryNamesAndValues.get(i2);
                    if (str != null) {
                        this.encodedQueryNamesAndValues.set(i2, HttpUrl.canonicalize(str, "\\^`{|}", true, true, true));
                    }
                }
            }
            String str2 = this.encodedFragment;
            if (str2 != null) {
                this.encodedFragment = HttpUrl.canonicalize(str2, " \"#<>\\^`{|}", true, false, false);
            }
            return this;
        }

        public Builder removeAllEncodedQueryParameters(String str) {
            if (str != null) {
                if (this.encodedQueryNamesAndValues != null) {
                    removeAllCanonicalQueryParameters(HttpUrl.canonicalize(str, " \"'<>#&=", true, true, true));
                }
                return this;
            }
            throw new IllegalArgumentException("encodedName == null");
        }

        public Builder removeAllQueryParameters(String str) {
            if (str != null) {
                if (this.encodedQueryNamesAndValues != null) {
                    removeAllCanonicalQueryParameters(HttpUrl.canonicalize(str, " \"'<>#&=", false, true, true));
                }
                return this;
            }
            throw new IllegalArgumentException("name == null");
        }

        public Builder removePathSegment(int i) {
            this.encodedPathSegments.remove(i);
            if (this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            }
            return this;
        }

        public Builder scheme(String str) {
            if (str != null) {
                String str2 = "http";
                if (!str.equalsIgnoreCase(str2)) {
                    str2 = "https";
                    if (!str.equalsIgnoreCase(str2)) {
                        throw new IllegalArgumentException(AnonymousClass006.A07("unexpected scheme: ", str));
                    }
                }
                this.scheme = str2;
                return this;
            }
            throw new IllegalArgumentException("scheme == null");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            String str = this.scheme;
            sb.append(str);
            sb.append("://");
            String str2 = this.encodedUsername;
            if (!str2.isEmpty() || !this.encodedPassword.isEmpty()) {
                sb.append(str2);
                String str3 = this.encodedPassword;
                if (!str3.isEmpty()) {
                    sb.append(':');
                    sb.append(str3);
                }
                sb.append('@');
            }
            String str4 = this.host;
            if (str4.indexOf(58) != -1) {
                sb.append('[');
                sb.append(str4);
                sb.append(']');
            } else {
                sb.append(str4);
            }
            int effectivePort = effectivePort();
            if (effectivePort != HttpUrl.defaultPort(str)) {
                sb.append(':');
                sb.append(effectivePort);
            }
            HttpUrl.pathSegmentsToString(sb, this.encodedPathSegments);
            List<String> list = this.encodedQueryNamesAndValues;
            if (list != null) {
                sb.append('?');
                HttpUrl.namesAndValuesToQueryString(sb, list);
            }
            String str5 = this.encodedFragment;
            if (str5 != null) {
                sb.append('#');
                sb.append(str5);
            }
            return sb.toString();
        }

        public Builder username(String str) {
            if (str != null) {
                this.encodedUsername = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", false, false, true);
                return this;
            }
            throw new IllegalArgumentException("username == null");
        }

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this.encodedPathSegments = arrayList;
            arrayList.add("");
        }

        public Builder setEncodedQueryParameter(String str, String str2) {
            removeAllEncodedQueryParameters(str);
            addEncodedQueryParameter(str, str2);
            return this;
        }

        public Builder setQueryParameter(String str, String str2) {
            removeAllQueryParameters(str);
            addQueryParameter(str, str2);
            return this;
        }
    }

    public static int decodeHexDigit(char c) {
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

    /* renamed from: com.squareup.okhttp.HttpUrl$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.squareup.okhttp.HttpUrl$Builder$ParseResult[] r0 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.squareup.okhttp.HttpUrl.AnonymousClass1.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult = r2
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r0 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r0 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.INVALID_HOST     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r0 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.UNSUPPORTED_SCHEME     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r0 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.MISSING_SCHEME     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r0 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.INVALID_PORT     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.AnonymousClass1.<clinit>():void");
        }
    }

    public static int defaultPort(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return 443;
        }
        return -1;
    }

    public static int delimiterOffset(String str, int i, int i2, String str2) {
        while (i < i2) {
            if (str2.indexOf(str.charAt(i)) != -1) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static HttpUrl getChecked(String str) throws MalformedURLException, UnknownHostException {
        Builder builder = new Builder();
        Builder.ParseResult parse = builder.parse(null, str);
        switch (parse.ordinal()) {
            case 0:
                return builder.build();
            case 1:
            case 2:
            case 3:
            default:
                StringBuilder sb = new StringBuilder("Invalid URL: ");
                sb.append(parse);
                sb.append(" for ");
                sb.append(str);
                throw new MalformedURLException(sb.toString());
            case 4:
                throw new UnknownHostException(AnonymousClass006.A07("Invalid host: ", str));
        }
    }

    public static HttpUrl parse(String str) {
        Builder builder = new Builder();
        if (builder.parse(null, str) == Builder.ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    public static List<String> queryStringToNamesAndValues(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int length = str.length();
            if (i > length) {
                return arrayList;
            }
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = length;
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                str2 = null;
            } else {
                arrayList.add(str.substring(i, indexOf2));
                str2 = str.substring(indexOf2 + 1, indexOf);
            }
            arrayList.add(str2);
            i = indexOf + 1;
        }
    }

    public String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        String str = this.url;
        return str.substring(str.indexOf(35) + 1);
    }

    public String encodedPassword() {
        if (this.password.isEmpty()) {
            return "";
        }
        String str = this.url;
        return str.substring(str.indexOf(58, this.scheme.length() + 3) + 1, str.indexOf(64));
    }

    public String encodedPath() {
        String str = this.url;
        int indexOf = str.indexOf(47, this.scheme.length() + 3);
        return this.url.substring(indexOf, delimiterOffset(str, indexOf, str.length(), "?#"));
    }

    public List<String> encodedPathSegments() {
        String str = this.url;
        int indexOf = str.indexOf(47, this.scheme.length() + 3);
        int delimiterOffset = delimiterOffset(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < delimiterOffset) {
            int i = indexOf + 1;
            indexOf = delimiterOffset(this.url, i, delimiterOffset, "/");
            arrayList.add(this.url.substring(i, indexOf));
        }
        return arrayList;
    }

    public String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        String str = this.url;
        int indexOf = str.indexOf(63) + 1;
        return this.url.substring(indexOf, delimiterOffset(str, indexOf + 1, str.length(), "#"));
    }

    public String encodedUsername() {
        if (this.username.isEmpty()) {
            return "";
        }
        int length = this.scheme.length() + 3;
        String str = this.url;
        return this.url.substring(length, delimiterOffset(str, length, str.length(), ":@"));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpUrl) || !((HttpUrl) obj).url.equals(this.url)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public boolean isHttps() {
        return this.scheme.equals("https");
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.scheme = this.scheme;
        builder.encodedUsername = encodedUsername();
        builder.encodedPassword = encodedPassword();
        builder.host = this.host;
        int i = this.port;
        if (i == defaultPort(this.scheme)) {
            i = -1;
        }
        builder.port = i;
        builder.encodedPathSegments.clear();
        builder.encodedPathSegments.addAll(encodedPathSegments());
        builder.encodedQuery(encodedQuery());
        builder.encodedFragment = encodedFragment();
        return builder;
    }

    public int pathSize() {
        return this.pathSegments.size();
    }

    public String query() {
        List<String> list = this.queryNamesAndValues;
        if (list == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        namesAndValuesToQueryString(sb, list);
        return sb.toString();
    }

    public String queryParameter(String str) {
        List<String> list = this.queryNamesAndValues;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                if (str.equals(this.queryNamesAndValues.get(i))) {
                    return this.queryNamesAndValues.get(i + 1);
                }
            }
        }
        return null;
    }

    public String queryParameterName(int i) {
        return this.queryNamesAndValues.get(i << 1);
    }

    public Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            linkedHashSet.add(this.queryNamesAndValues.get(i));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public String queryParameterValue(int i) {
        return this.queryNamesAndValues.get((i << 1) + 1);
    }

    public List<String> queryParameterValues(String str) {
        List<String> list = this.queryNamesAndValues;
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.queryNamesAndValues.get(i))) {
                arrayList.add(this.queryNamesAndValues.get(i + 1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int querySize() {
        List<String> list = this.queryNamesAndValues;
        if (list != null) {
            return list.size() >> 1;
        }
        return 0;
    }

    public HttpUrl resolve(String str) {
        Builder builder = new Builder();
        if (builder.parse(this, str) == Builder.ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    public URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void namesAndValuesToQueryString(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = list.get(i);
            String str2 = list.get(i + 1);
            if (i > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static void pathSegmentsToString(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append(list.get(i));
        }
    }

    public String fragment() {
        return this.fragment;
    }

    public String host() {
        return this.host;
    }

    public String password() {
        return this.password;
    }

    public List<String> pathSegments() {
        return this.pathSegments;
    }

    public int port() {
        return this.port;
    }

    public String scheme() {
        return this.scheme;
    }

    public String toString() {
        return this.url;
    }

    public URI uri() {
        try {
            Builder newBuilder = newBuilder();
            newBuilder.reencodeForUri();
            return new URI(newBuilder.toString());
        } catch (URISyntaxException unused) {
            throw new IllegalStateException(AnonymousClass006.A07("not valid as a java.net.URI: ", this.url));
        }
    }

    public String username() {
        return this.username;
    }

    public HttpUrl(Builder builder) {
        List<String> list;
        this.scheme = builder.scheme;
        this.username = percentDecode(builder.encodedUsername, false);
        this.password = percentDecode(builder.encodedPassword, false);
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.pathSegments = percentDecode(builder.encodedPathSegments, false);
        List<String> list2 = builder.encodedQueryNamesAndValues;
        String str = null;
        if (list2 != null) {
            list = percentDecode(list2, true);
        } else {
            list = null;
        }
        this.queryNamesAndValues = list;
        String str2 = builder.encodedFragment;
        this.fragment = str2 != null ? percentDecode(str2, false) : str;
        this.url = builder.toString();
    }

    public /* synthetic */ HttpUrl(Builder builder, AnonymousClass1 r2) {
        this(builder);
    }

    public static String canonicalize(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt >= 32 && codePointAt != 127 && ((codePointAt < 128 || !z3) && str2.indexOf(codePointAt) == -1)) {
                if (codePointAt == 37) {
                    if (z) {
                    }
                } else if (codePointAt == 43 && z2) {
                }
                i3 += Character.charCount(codePointAt);
            }
            Buffer buffer = new Buffer();
            buffer.writeUtf8(str, i, i3);
            canonicalize(buffer, str, i3, i2, str2, z, z2, z3);
            return buffer.readUtf8();
        }
        return str.substring(i, i2);
    }

    public static String canonicalize(String str, String str2, boolean z, boolean z2, boolean z3) {
        return canonicalize(str, 0, str.length(), str2, z, z2, z3);
    }

    public static void canonicalize(Buffer buffer, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3) {
        String str3;
        Buffer buffer2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!z || !(codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13)) {
                if (codePointAt == 43 && z2) {
                    if (z) {
                        str3 = "+";
                    } else {
                        str3 = "%2B";
                    }
                    buffer.writeUtf8(str3);
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z3) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && !z))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(codePointAt);
                    while (!buffer2.exhausted()) {
                        int readByte = buffer2.readByte() & Base64.INVALID;
                        buffer.writeByte(37);
                        char[] cArr = HEX_DIGITS;
                        buffer.writeByte((int) cArr[(readByte >> 4) & 15]);
                        buffer.writeByte((int) cArr[readByte & 15]);
                    }
                } else {
                    buffer.writeUtf8CodePoint(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    public static HttpUrl get(URI uri) {
        return parse(uri.toString());
    }

    public static HttpUrl get(URL url2) {
        return parse(url2.toString());
    }

    public static String percentDecode(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (charAt == '+' && z)) {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, i, i3);
                percentDecode(buffer, str, i3, i2, z);
                return buffer.readUtf8();
            }
        }
        return str.substring(i, i2);
    }

    public static String percentDecode(String str, boolean z) {
        return percentDecode(str, 0, str.length(), z);
    }

    private List<String> percentDecode(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(next != null ? percentDecode(next, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static void percentDecode(Buffer buffer, String str, int i, int i2, boolean z) {
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt == 37) {
                int i3 = i + 2;
                if (i3 < i2) {
                    int decodeHexDigit = decodeHexDigit(str.charAt(i + 1));
                    int decodeHexDigit2 = decodeHexDigit(str.charAt(i3));
                    if (!(decodeHexDigit == -1 || decodeHexDigit2 == -1)) {
                        buffer.writeByte((decodeHexDigit << 4) + decodeHexDigit2);
                        i = i3;
                    }
                }
                buffer.writeUtf8CodePoint(codePointAt);
            } else {
                if (codePointAt == 43 && z) {
                    buffer.writeByte(32);
                }
                buffer.writeUtf8CodePoint(codePointAt);
            }
            i += Character.charCount(codePointAt);
        }
    }
}
