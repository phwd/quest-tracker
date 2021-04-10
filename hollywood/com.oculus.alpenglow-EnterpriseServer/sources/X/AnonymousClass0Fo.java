package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

/* renamed from: X.0Fo  reason: invalid class name */
public final class AnonymousClass0Fo implements Closeable {
    public static final char[] A0F = ")]}'\n".toCharArray();
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 0;
    public int A03 = 0;
    public int A04;
    public int A05 = 0;
    public int A06;
    public long A07;
    public boolean A08 = false;
    public int[] A09;
    public int[] A0A;
    public String[] A0B;
    public String A0C;
    public final char[] A0D = new char[1024];
    public final Reader A0E;

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007f, code lost:
        A04();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String A02() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 156
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Fo.A02():java.lang.String");
    }

    public final void A0M() throws IOException {
        int i;
        int i2;
        int i3;
        char c;
        int i4 = 0;
        do {
            int i5 = this.A03;
            if (i5 == 0) {
                i5 = A0B();
            }
            if (i5 == 3) {
                A05(1);
            } else if (i5 == 1) {
                A05(3);
            } else if (i5 == 4 || i5 == 2) {
                this.A06--;
                i4--;
                this.A03 = 0;
            } else {
                if (i5 == 14 || i5 == 10) {
                    while (true) {
                        i = 0;
                        while (true) {
                            i2 = this.A05;
                            int i6 = i2 + i;
                            if (i6 < this.A00) {
                                char c2 = this.A0D[i2 + i];
                                if (!(c2 == '\t' || c2 == '\n' || c2 == '\f' || c2 == '\r' || c2 == ' ')) {
                                    if (c2 != '#') {
                                        if (c2 != ',') {
                                            if (!(c2 == '/' || c2 == '=')) {
                                                if (!(c2 == '{' || c2 == '}' || c2 == ':')) {
                                                    if (c2 != ';') {
                                                        switch (c2) {
                                                            case '[':
                                                            case ']':
                                                                break;
                                                            case '\\':
                                                                break;
                                                            default:
                                                                i++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                this.A05 = i6;
                                if (!A08(1)) {
                                }
                            }
                        }
                    }
                    A04();
                    i3 = i2 + i;
                } else {
                    if (i5 == 8 || i5 == 12) {
                        c = '\'';
                    } else if (i5 == 9 || i5 == 13) {
                        c = '\"';
                    } else {
                        if (i5 == 16) {
                            this.A05 += this.A04;
                        }
                        this.A03 = 0;
                    }
                    char[] cArr = this.A0D;
                    while (true) {
                        int i7 = this.A05;
                        int i8 = this.A00;
                        while (true) {
                            if (i7 < i8) {
                                i3 = i7 + 1;
                                char c3 = cArr[i7];
                                if (c3 != c) {
                                    if (c3 == '\\') {
                                        this.A05 = i3;
                                        A00();
                                    } else {
                                        if (c3 == '\n') {
                                            this.A01++;
                                            this.A02 = i3;
                                        }
                                        i7 = i3;
                                    }
                                }
                            } else {
                                this.A05 = i7;
                                if (!A08(1)) {
                                    A06("Unterminated string");
                                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                                }
                            }
                        }
                    }
                }
                this.A05 = i3;
                this.A03 = 0;
            }
            i4++;
            this.A03 = 0;
        } while (i4 != 0);
        int[] iArr = this.A09;
        int i9 = this.A06;
        int i10 = i9 - 1;
        iArr[i10] = iArr[i10] + 1;
        this.A0B[i9 - 1] = "null";
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A03 = 0;
        this.A0A[0] = 8;
        this.A06 = 1;
        this.A0E.close();
    }

    static {
        AnonymousClass0D7.A00 = new AnonymousClass0W0();
    }

    private char A00() throws IOException {
        int i;
        int i2;
        if (this.A05 != this.A00 || A08(1)) {
            char[] cArr = this.A0D;
            int i3 = this.A05;
            int i4 = i3 + 1;
            this.A05 = i4;
            char c = cArr[i3];
            if (c != '\n') {
                if (!(c == '\"' || c == '\'' || c == '/' || c == '\\')) {
                    if (c == 'b') {
                        return '\b';
                    }
                    if (c == 'f') {
                        return '\f';
                    }
                    if (c == 'n') {
                        return '\n';
                    }
                    if (c == 'r') {
                        return '\r';
                    }
                    if (c == 't') {
                        return '\t';
                    }
                    if (c != 'u') {
                        A06("Invalid escape sequence");
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    } else if (i4 + 4 <= this.A00 || A08(4)) {
                        c = 0;
                        int i5 = this.A05;
                        int i6 = i5 + 4;
                        while (i5 < i6) {
                            char c2 = cArr[i5];
                            char c3 = (char) (c << 4);
                            if (c2 < '0' || c2 > '9') {
                                if (c2 >= 'a' && c2 <= 'f') {
                                    i = c2 - 'a';
                                } else if (c2 < 'A' || c2 > 'F') {
                                    throw new NumberFormatException(AnonymousClass006.A05("\\u", new String(cArr, i5, 4)));
                                } else {
                                    i = c2 - 'A';
                                }
                                i2 = i + 10;
                            } else {
                                i2 = c2 - '0';
                            }
                            c = (char) (c3 + i2);
                            i5++;
                        }
                        this.A05 = i5 + 4;
                    }
                }
                return c;
            }
            this.A01++;
            this.A02 = i4;
            return c;
        }
        A06("Unterminated escape sequence");
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        if (r4 != '/') goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r9.A05 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003d, code lost:
        if (r3 != r7) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003f, code lost:
        r9.A05 = r3 - 1;
        r0 = A08(2);
        r3 = r9.A05 + 1;
        r9.A05 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        if (r0 != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        A04();
        r1 = r6[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0055, code lost:
        if (r1 == '*') goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0057, code lost:
        if (r1 != '/') goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0059, code lost:
        r3 = r3 + 1;
        r9.A05 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005e, code lost:
        r9.A05 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0062, code lost:
        if (r4 != '#') goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0064, code lost:
        A04();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008d, code lost:
        r0 = r3 + 1;
        r9.A05 = r0;
        r4 = "*\/".length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0097, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009b, code lost:
        if ((r0 + r4) <= r9.A00) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a1, code lost:
        if (A08(r4) != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a3, code lost:
        A06("Unterminated comment");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00af, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b0, code lost:
        r3 = r9.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b7, code lost:
        if (r6[r3] != '\n') goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b9, code lost:
        r9.A01++;
        r9.A02 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c2, code lost:
        r0 = r9.A05 + 1;
        r9.A05 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c8, code lost:
        if (r8 >= r4) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d3, code lost:
        if (r6[r9.A05 + r8] != "*\/".charAt(r8)) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d5, code lost:
        r8 = r8 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int A01(boolean r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 237
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Fo.A01(boolean):int");
    }

    private String A03(char c) throws IOException {
        char[] cArr = this.A0D;
        StringBuilder sb = null;
        while (true) {
            int i = this.A05;
            int i2 = this.A00;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.A05 = i3;
                        int i4 = (i3 - i) - 1;
                        if (sb == null) {
                            return new String(cArr, i, i4);
                        }
                        sb.append(cArr, i, i4);
                        return sb.toString();
                    } else if (c2 == '\\') {
                        this.A05 = i3;
                        int i5 = (i3 - i) - 1;
                        if (sb == null) {
                            sb = new StringBuilder(Math.max((i5 + 1) << 1, 16));
                        }
                        sb.append(cArr, i, i5);
                        sb.append(A00());
                    } else {
                        if (c2 == '\n') {
                            this.A01++;
                            this.A02 = i3;
                        }
                        i = i3;
                    }
                } else {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i - i) << 1, 16));
                    }
                    sb.append(cArr, i, i - i);
                    this.A05 = i;
                    if (!A08(1)) {
                        A06("Unterminated string");
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            }
        }
    }

    private void A04() throws IOException {
        if (!this.A08) {
            A06("Use JsonReader.setLenient(true) to accept malformed JSON");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private void A05(int i) {
        int i2 = this.A06;
        int[] iArr = this.A0A;
        if (i2 == iArr.length) {
            int i3 = i2 << 1;
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            String[] strArr = new String[i3];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.A09, 0, iArr3, 0, this.A06);
            System.arraycopy(this.A0B, 0, strArr, 0, this.A06);
            this.A0A = iArr2;
            iArr = iArr2;
            this.A09 = iArr3;
            this.A0B = strArr;
        }
        int i4 = this.A06;
        this.A06 = i4 + 1;
        iArr[i4] = i;
    }

    private boolean A07(char c) throws IOException {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (!(c == '/' || c == '=')) {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        A04();
        return false;
    }

    private boolean A08(int i) throws IOException {
        int i2;
        char[] cArr = this.A0D;
        int i3 = this.A02;
        int i4 = this.A05;
        this.A02 = i3 - i4;
        int i5 = this.A00;
        if (i5 != i4) {
            int i6 = i5 - i4;
            this.A00 = i6;
            System.arraycopy(cArr, i4, cArr, 0, i6);
        } else {
            this.A00 = 0;
        }
        this.A05 = 0;
        do {
            Reader reader = this.A0E;
            int i7 = this.A00;
            int read = reader.read(cArr, i7, cArr.length - i7);
            if (read == -1) {
                return false;
            }
            i2 = this.A00 + read;
            this.A00 = i2;
            if (this.A01 == 0 && this.A02 == 0 && i2 > 0 && cArr[0] == 65279) {
                this.A05++;
                this.A02 = 1;
                i++;
                continue;
            }
        } while (i2 < i);
        return true;
    }

    public final double A09() throws IOException {
        char c;
        String str;
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 15) {
            this.A03 = 0;
            int[] iArr = this.A09;
            int i2 = this.A06 - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.A07;
        }
        if (i == 16) {
            this.A0C = new String(this.A0D, this.A05, this.A04);
            this.A05 += this.A04;
        } else {
            if (i == 8) {
                c = '\'';
            } else if (i == 9) {
                c = '\"';
            } else if (i == 10) {
                str = A02();
                this.A0C = str;
            } else if (i != 11) {
                throw new IllegalStateException(AnonymousClass006.A07("Expected a double but was ", AnonymousClass0GA.A00(A0D()), A0G()));
            }
            str = A03(c);
            this.A0C = str;
        }
        this.A03 = 11;
        double parseDouble = Double.parseDouble(this.A0C);
        if (this.A08 || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.A0C = null;
            this.A03 = 0;
            int[] iArr2 = this.A09;
            int i3 = this.A06 - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new AnonymousClass0GS("JSON forbids NaN and infinities: " + parseDouble + A0G());
    }

    public final int A0A() throws IOException {
        int i;
        char c;
        String A032;
        StringBuilder sb;
        int i2 = this.A03;
        if (i2 == 0) {
            i2 = A0B();
        }
        if (i2 == 15) {
            long j = this.A07;
            i = (int) j;
            if (j != ((long) i)) {
                sb = new StringBuilder();
                sb.append("Expected an int but was ");
                sb.append(j);
            }
            this.A03 = 0;
            int[] iArr = this.A09;
            int i3 = this.A06 - 1;
            iArr[i3] = iArr[i3] + 1;
            return i;
        }
        if (i2 == 16) {
            A032 = new String(this.A0D, this.A05, this.A04);
            this.A0C = A032;
            this.A05 += this.A04;
        } else {
            if (i2 == 8) {
                c = '\'';
                A032 = A03(c);
            } else if (i2 == 9) {
                c = '\"';
                A032 = A03(c);
            } else if (i2 == 10) {
                A032 = A02();
            } else {
                throw new IllegalStateException(AnonymousClass006.A07("Expected an int but was ", AnonymousClass0GA.A00(A0D()), A0G()));
            }
            this.A0C = A032;
            try {
                int parseInt = Integer.parseInt(A032);
                this.A03 = 0;
                int[] iArr2 = this.A09;
                int i4 = this.A06 - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }
        this.A03 = 11;
        double parseDouble = Double.parseDouble(A032);
        i = (int) parseDouble;
        if (((double) i) == parseDouble) {
            this.A0C = null;
            this.A03 = 0;
            int[] iArr3 = this.A09;
            int i32 = this.A06 - 1;
            iArr3[i32] = iArr3[i32] + 1;
            return i;
        }
        sb = new StringBuilder();
        sb.append("Expected an int but was ");
        sb.append(A032);
        sb.append(A0G());
        throw new NumberFormatException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0169, code lost:
        if (r13 == 1) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0231, code lost:
        if (r13 != 5) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0248, code lost:
        if (A07(r0) != false) goto L_0x0279;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x024a, code lost:
        if (r11 != 2) goto L_0x026e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x024c, code lost:
        if (r20 == false) goto L_0x0274;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0252, code lost:
        if (r2 != Long.MIN_VALUE) goto L_0x0256;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0254, code lost:
        if (r19 == false) goto L_0x0274;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x025a, code lost:
        if (r2 != 0) goto L_0x026b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x025c, code lost:
        if (r19 != false) goto L_0x0274;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x025e, code lost:
        r2 = -r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x025f, code lost:
        r21.A07 = r2;
        r21.A05 += r12;
        r0 = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x026b, code lost:
        if (r19 == false) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x026e, code lost:
        if (r11 == 2) goto L_0x0274;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0270, code lost:
        if (r11 == 4) goto L_0x0274;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0272, code lost:
        if (r11 != 7) goto L_0x0279;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0274, code lost:
        r21.A04 = r12;
        r0 = 16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0283  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0279 A[EDGE_INSN: B:218:0x0279->B:192:0x0279 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0109  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A0B() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 709
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Fo.A0B():int");
    }

    public final long A0C() throws IOException {
        char c;
        String A032;
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 15) {
            this.A03 = 0;
            int[] iArr = this.A09;
            int i2 = this.A06 - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.A07;
        }
        if (i == 16) {
            A032 = new String(this.A0D, this.A05, this.A04);
            this.A0C = A032;
            this.A05 += this.A04;
        } else {
            if (i == 8) {
                c = '\'';
                A032 = A03(c);
            } else if (i == 9) {
                c = '\"';
                A032 = A03(c);
            } else if (i == 10) {
                A032 = A02();
            } else {
                throw new IllegalStateException(AnonymousClass006.A07("Expected a long but was ", AnonymousClass0GA.A00(A0D()), A0G()));
            }
            this.A0C = A032;
            try {
                long parseLong = Long.parseLong(A032);
                this.A03 = 0;
                int[] iArr2 = this.A09;
                int i3 = this.A06 - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        this.A03 = 11;
        double parseDouble = Double.parseDouble(A032);
        long j = (long) parseDouble;
        if (((double) j) == parseDouble) {
            this.A0C = null;
            this.A03 = 0;
            int[] iArr3 = this.A09;
            int i4 = this.A06 - 1;
            iArr3[i4] = iArr3[i4] + 1;
            return j;
        }
        throw new NumberFormatException(AnonymousClass006.A07("Expected a long but was ", A032, A0G()));
    }

    public final Integer A0D() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        switch (i) {
            case 1:
                return AnonymousClass007.A0C;
            case 2:
                return AnonymousClass007.A0D;
            case 3:
                return AnonymousClass007.A00;
            case 4:
                return AnonymousClass007.A01;
            case 5:
            case 6:
                return AnonymousClass007.A0H;
            case 7:
                return AnonymousClass007.A0I;
            case 8:
            case 9:
            case 10:
            case 11:
                return AnonymousClass007.A0F;
            case 12:
            case 13:
            case 14:
                return AnonymousClass007.A0E;
            case Hpack.PREFIX_4_BITS /*{ENCODED_INT: 15}*/:
            case 16:
                return AnonymousClass007.A0G;
            case 17:
                return AnonymousClass007.A0J;
            default:
                throw new AssertionError();
        }
    }

    public final String A0E() throws IOException {
        char c;
        String A032;
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 14) {
            A032 = A02();
        } else {
            if (i == 12) {
                c = '\'';
            } else if (i == 13) {
                c = '\"';
            } else {
                throw new IllegalStateException(AnonymousClass006.A07("Expected a name but was ", AnonymousClass0GA.A00(A0D()), A0G()));
            }
            A032 = A03(c);
        }
        this.A03 = 0;
        this.A0B[this.A06 - 1] = A032;
        return A032;
    }

    public final String A0F() throws IOException {
        String str;
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 10) {
            str = A02();
        } else if (i == 8) {
            str = A03('\'');
        } else if (i == 9) {
            str = A03('\"');
        } else if (i == 11) {
            str = this.A0C;
            this.A0C = null;
        } else if (i == 15) {
            str = Long.toString(this.A07);
        } else if (i == 16) {
            str = new String(this.A0D, this.A05, this.A04);
            this.A05 += this.A04;
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("Expected a string but was ", AnonymousClass0GA.A00(A0D()), A0G()));
        }
        this.A03 = 0;
        int[] iArr = this.A09;
        int i2 = this.A06 - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final String A0G() {
        int i = this.A01 + 1;
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(i);
        sb.append(" column ");
        sb.append((this.A05 - this.A02) + 1);
        sb.append(" path ");
        StringBuilder sb2 = new StringBuilder("$");
        int i2 = this.A06;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.A0A[i3];
            if (i4 == 1 || i4 == 2) {
                sb2.append('[');
                sb2.append(this.A09[i3]);
                sb2.append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                sb2.append('.');
                String[] strArr = this.A0B;
                if (strArr[i3] != null) {
                    sb2.append(strArr[i3]);
                }
            }
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    public final void A0H() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 3) {
            A05(1);
            this.A09[this.A06 - 1] = 0;
            this.A03 = 0;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Expected BEGIN_ARRAY but was ", AnonymousClass0GA.A00(A0D()), A0G()));
    }

    public final void A0I() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 1) {
            A05(3);
            this.A03 = 0;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Expected BEGIN_OBJECT but was ", AnonymousClass0GA.A00(A0D()), A0G()));
    }

    public final void A0J() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 4) {
            int i2 = this.A06 - 1;
            this.A06 = i2;
            int[] iArr = this.A09;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.A03 = 0;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Expected END_ARRAY but was ", AnonymousClass0GA.A00(A0D()), A0G()));
    }

    public final void A0K() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 2) {
            int i2 = this.A06 - 1;
            this.A06 = i2;
            this.A0B[i2] = null;
            int[] iArr = this.A09;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.A03 = 0;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Expected END_OBJECT but was ", AnonymousClass0GA.A00(A0D()), A0G()));
    }

    public final void A0L() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 7) {
            this.A03 = 0;
            int[] iArr = this.A09;
            int i2 = this.A06 - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass006.A07("Expected null but was ", AnonymousClass0GA.A00(A0D()), A0G()));
    }

    public final boolean A0N() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 2 || i == 4) {
            return false;
        }
        return true;
    }

    public final boolean A0O() throws IOException {
        int i = this.A03;
        if (i == 0) {
            i = A0B();
        }
        if (i == 5) {
            this.A03 = 0;
            int[] iArr = this.A09;
            int i2 = this.A06 - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.A03 = 0;
            int[] iArr2 = this.A09;
            int i3 = this.A06 - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new IllegalStateException(AnonymousClass006.A07("Expected a boolean but was ", AnonymousClass0GA.A00(A0D()), A0G()));
        }
    }

    public AnonymousClass0Fo(Reader reader) {
        int[] iArr = new int[32];
        this.A0A = iArr;
        this.A06 = 0;
        this.A06 = 1;
        iArr[0] = 6;
        this.A0B = new String[32];
        this.A09 = new int[32];
        this.A0E = reader;
    }

    private void A06(String str) throws IOException {
        throw new AnonymousClass0GS(AnonymousClass006.A05(str, A0G()));
    }

    public final String toString() {
        return AnonymousClass006.A05(getClass().getSimpleName(), A0G());
    }
}
