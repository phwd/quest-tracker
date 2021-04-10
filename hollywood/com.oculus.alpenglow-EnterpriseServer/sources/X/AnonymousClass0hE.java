package X;

import javax.security.auth.x500.X500Principal;

/* renamed from: X.0hE  reason: invalid class name */
public final class AnonymousClass0hE {
    public int A00;
    public char[] A01;
    public final int A02;
    public final String A03;

    public static char A00(AnonymousClass0hE r10) {
        int i;
        int i2 = r10.A00 + 1;
        r10.A00 = i2;
        int i3 = r10.A02;
        if (i2 != i3) {
            char[] cArr = r10.A01;
            char c = cArr[i2];
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
                            int A012 = A01(r10, i2);
                            int i4 = i2 + 1;
                            r10.A00 = i4;
                            if (A012 >= 128) {
                                if (A012 < 192 || A012 > 247) {
                                    return '?';
                                }
                                if (A012 <= 223) {
                                    A012 &= 31;
                                    i = 1;
                                } else if (A012 <= 239) {
                                    i = 2;
                                    A012 &= 15;
                                } else {
                                    i = 3;
                                    A012 &= 7;
                                }
                                int i5 = 0;
                                do {
                                    int i6 = i4 + 1;
                                    r10.A00 = i6;
                                    if (i6 == i3 || cArr[i6] != '\\') {
                                        return '?';
                                    }
                                    int i7 = i6 + 1;
                                    r10.A00 = i7;
                                    int A013 = A01(r10, i7);
                                    i4 = i7 + 1;
                                    r10.A00 = i4;
                                    if ((A013 & 192) != 128) {
                                        return '?';
                                    }
                                    A012 = (A012 << 6) + (A013 & 63);
                                    i5++;
                                } while (i5 < i);
                            }
                            return (char) A012;
                    }
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("Unexpected end of DN: ", r10.A03));
        }
    }

    public static int A01(AnonymousClass0hE r10, int i) {
        int i2;
        int i3;
        int i4 = i + 1;
        if (i4 < r10.A02) {
            char[] cArr = r10.A01;
            char c = cArr[i];
            if (c >= '0' && c <= '9') {
                i2 = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                i2 = c - 'W';
            } else if (c >= 'A' && c <= 'F') {
                i2 = c - '7';
            }
            char c2 = cArr[i4];
            if (c2 >= '0' && c2 <= '9') {
                i3 = c2 - '0';
            } else if (c2 >= 'a' && c2 <= 'f') {
                i3 = c2 - 'W';
            } else if (c2 >= 'A' && c2 <= 'F') {
                i3 = c2 - '7';
            }
            return (i2 << 4) + i3;
        }
        throw new IllegalStateException(AnonymousClass006.A05("Malformed DN: ", r10.A03));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        if (r8 != r7) goto L_0x0047;
     */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0016 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0018 A[LOOP:1: B:7:0x0018->B:11:0x0027, LOOP_START, PHI: r8 
      PHI: (r8v1 int) = (r8v0 int), (r8v2 int) binds: [B:5:0x0014, B:11:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String A02(X.AnonymousClass0hE r10) {
        /*
        // Method dump skipped, instructions count: 150
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0hE.A02(X.0hE):java.lang.String");
    }

    public AnonymousClass0hE(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.A03 = name;
        this.A02 = name.length();
    }
}
