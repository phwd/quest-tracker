package com.squareup.okhttp.internal.tls;

import X.AnonymousClass006;
import javax.security.auth.x500.X500Principal;

public final class DistinguishedNameParser {
    public int beg;
    public char[] chars;
    public int cur;
    public final String dn;
    public int end;
    public final int length;
    public int pos;

    public String findMostSpecific(String str) {
        String quotedAV;
        this.pos = 0;
        this.beg = 0;
        this.end = 0;
        this.cur = 0;
        this.chars = this.dn.toCharArray();
        String nextAT = nextAT();
        if (nextAT != null) {
            do {
                int i = this.pos;
                if (i != this.length) {
                    char c = this.chars[i];
                    if (c == '\"') {
                        quotedAV = quotedAV();
                    } else if (c == '#') {
                        quotedAV = hexAV();
                    } else if (c == '+' || c == ',' || c == ';') {
                        quotedAV = "";
                    } else {
                        quotedAV = escapedAV();
                    }
                    if (str.equalsIgnoreCase(nextAT)) {
                        return quotedAV;
                    }
                    int i2 = this.pos;
                    if (i2 < this.length) {
                        char c2 = this.chars[i2];
                        if (c2 == ',' || c2 == ';' || c2 == '+') {
                            this.pos = i2 + 1;
                            nextAT = nextAT();
                        } else {
                            throw new IllegalStateException(AnonymousClass006.A07("Malformed DN: ", this.dn));
                        }
                    }
                }
            } while (nextAT != null);
            throw new IllegalStateException(AnonymousClass006.A07("Malformed DN: ", this.dn));
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r5 = r5 - r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String escapedAV() {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.tls.DistinguishedNameParser.escapedAV():java.lang.String");
    }

    private int getByte(int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < this.length) {
            char[] cArr = this.chars;
            char c = cArr[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else if (c < 'A' || c > 'F') {
                throw new IllegalStateException(AnonymousClass006.A07("Malformed DN: ", this.dn));
            } else {
                i2 = c - '7';
            }
            char c2 = cArr[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException(AnonymousClass006.A07("Malformed DN: ", this.dn));
            } else {
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Malformed DN: ", this.dn));
    }

    private char getEscaped() {
        int i = this.pos + 1;
        this.pos = i;
        if (i != this.length) {
            char c = this.chars[i];
            if (c == ' ' || c == '%' || c == '\\' || c == '_' || c == '\"' || c == '#') {
                return c;
            }
            switch (c) {
                case '*':
                case '+':
                case ',':
                    return c;
                default:
                    switch (c) {
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                            return c;
                        default:
                            return getUTF8();
                    }
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("Unexpected end of DN: ", this.dn));
        }
    }

    private char getUTF8() {
        int i;
        int i2 = this.pos;
        int i3 = getByte(i2);
        int i4 = i2 + 1;
        this.pos = i4;
        if (i3 >= 128) {
            if (i3 >= 192 && i3 <= 247) {
                if (i3 <= 223) {
                    i3 &= 31;
                    i = 1;
                } else if (i3 <= 239) {
                    i = 2;
                    i3 &= 15;
                } else {
                    i = 3;
                    i3 &= 7;
                }
                int i5 = 0;
                do {
                    int i6 = i4 + 1;
                    this.pos = i6;
                    if (i6 != this.length && this.chars[i6] == '\\') {
                        int i7 = i6 + 1;
                        this.pos = i7;
                        int i8 = getByte(i7);
                        i4 = i7 + 1;
                        this.pos = i4;
                        if ((i8 & 192) == 128) {
                            i3 = (i3 << 6) + (i8 & 63);
                            i5++;
                        }
                    }
                } while (i5 < i);
            }
            return '?';
        }
        return (char) i3;
    }

    private String hexAV() {
        char[] cArr;
        char c;
        int i = this.pos;
        int i2 = i + 4;
        int i3 = this.length;
        if (i2 < i3) {
            this.beg = i;
            while (true) {
                i++;
                this.pos = i;
                if (i == i3 || (c = (cArr = this.chars)[i]) == '+' || c == ',' || c == ';') {
                    this.end = i;
                } else if (c == ' ') {
                    this.end = i;
                    do {
                        i++;
                        this.pos = i;
                        if (i >= i3) {
                            break;
                        }
                    } while (cArr[i] == ' ');
                } else if (c >= 'A' && c <= 'F') {
                    cArr[i] = (char) (c + ' ');
                }
            }
            this.end = i;
            int i4 = i - i;
            if (i4 < 5 || (i4 & 1) == 0) {
                throw new IllegalStateException(AnonymousClass006.A07("Unexpected end of DN: ", this.dn));
            }
            int i5 = i4 >> 1;
            int i6 = i + 1;
            for (int i7 = 0; i7 < i5; i7++) {
                getByte(i6);
                i6 += 2;
            }
            return new String(this.chars, i, i4);
        }
        throw new IllegalStateException(AnonymousClass006.A07("Unexpected end of DN: ", this.dn));
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0016 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String nextAT() {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.tls.DistinguishedNameParser.nextAT():java.lang.String");
    }

    private String quotedAV() {
        int i = this.pos + 1;
        this.pos = i;
        int i2 = i;
        this.beg = i;
        while (true) {
            this.end = i;
            int i3 = this.length;
            if (i2 != i3) {
                char[] cArr = this.chars;
                char c = cArr[i2];
                if (c == '\"') {
                    int i4 = i2 + 1;
                    while (true) {
                        this.pos = i4;
                        if (i4 < i3 && cArr[i4] == ' ') {
                            i4++;
                        }
                    }
                    return new String(cArr, i, i - i);
                }
                if (c == '\\') {
                    c = getEscaped();
                }
                cArr[i] = c;
                i2 = this.pos + 1;
                this.pos = i2;
                i++;
            } else {
                throw new IllegalStateException(AnonymousClass006.A07("Unexpected end of DN: ", this.dn));
            }
        }
    }

    public DistinguishedNameParser(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.dn = name;
        this.length = name.length();
    }
}
