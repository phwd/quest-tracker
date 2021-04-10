package X;

import com.facebook.acra.ErrorReporter;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

public class h5 implements Closeable {
    public static final char[] A0F = ")]}'\n".toCharArray();
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 0;
    public int A03 = 0;
    public int A04;
    public int A05 = 0;
    public long A06;
    public boolean A07 = false;
    public int A08;
    public String A09;
    public int[] A0A;
    public int[] A0B;
    public String[] A0C;
    public final char[] A0D = new char[ErrorReporter.SIGQUIT_PROCESS_NAME_BUFFER_SIZE];
    public final Reader A0E;

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007f, code lost:
        A07();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String A05() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 156
        */
        throw new UnsupportedOperationException("Method not decompiled: X.h5.A05():java.lang.String");
    }

    static {
        hT.A00 = new KD();
    }

    private char A03() throws IOException {
        int i;
        int i2;
        if (this.A05 != this.A00 || A0B(1)) {
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
                        A09("Invalid escape sequence");
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    } else if (i4 + 4 <= this.A00 || A0B(4)) {
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
                                    throw new NumberFormatException(AnonymousClass06.A03("\\u", new String(cArr, i5, 4)));
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
        A09("Unterminated escape sequence");
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
        r0 = A0B(2);
        r3 = r9.A05 + 1;
        r9.A05 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        if (r0 != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        A07();
        r1 = r6[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0055, code lost:
        if (r1 == '*') goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0057, code lost:
        if (r1 != '/') goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0059, code lost:
        r9.A05 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005e, code lost:
        r9.A05 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0062, code lost:
        if (r4 != '#') goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0064, code lost:
        A07();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008f, code lost:
        r0 = r3 + 1;
        r9.A05 = r0;
        r4 = "*\/".length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0099, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009d, code lost:
        if ((r0 + r4) <= r9.A00) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a3, code lost:
        if (A0B(r4) != false) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a5, code lost:
        A09("Unterminated comment");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b1, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b2, code lost:
        r3 = r9.A05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b9, code lost:
        if (r6[r3] != '\n') goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00bb, code lost:
        r9.A01++;
        r9.A02 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c4, code lost:
        r0 = r9.A05 + 1;
        r9.A05 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ca, code lost:
        if (r8 >= r4) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d5, code lost:
        if (r6[r9.A05 + r8] != "*\/".charAt(r8)) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d7, code lost:
        r8 = r8 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int A04(boolean r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 239
        */
        throw new UnsupportedOperationException("Method not decompiled: X.h5.A04(boolean):int");
    }

    private String A06(char c) throws IOException {
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
                        sb.append(A03());
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
                    if (!A0B(1)) {
                        A09("Unterminated string");
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            }
        }
    }

    private void A07() throws IOException {
        if (!this.A07) {
            A09("Use JsonReader.setLenient(true) to accept malformed JSON");
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private void A08(int i) {
        int i2 = this.A08;
        int[] iArr = this.A0B;
        if (i2 == iArr.length) {
            int i3 = i2 << 1;
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            String[] strArr = new String[i3];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.A0A, 0, iArr3, 0, this.A08);
            System.arraycopy(this.A0C, 0, strArr, 0, this.A08);
            this.A0B = iArr2;
            iArr = iArr2;
            this.A0A = iArr3;
            this.A0C = strArr;
        }
        int i4 = this.A08;
        this.A08 = i4 + 1;
        iArr[i4] = i;
    }

    private boolean A0A(char c) throws IOException {
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
        A07();
        return false;
    }

    private boolean A0B(int i) throws IOException {
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

    public final double A0C() throws IOException {
        String str;
        int[] iArr;
        double d;
        int i;
        char c;
        String str2;
        if (!(this instanceof LT)) {
            int i2 = this.A03;
            if (i2 == 0) {
                i2 = A0E();
            }
            if (i2 == 15) {
                this.A03 = 0;
                int[] iArr2 = this.A0A;
                int i3 = this.A08 - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return (double) this.A06;
            }
            if (i2 == 16) {
                this.A09 = new String(this.A0D, this.A05, this.A04);
                this.A05 += this.A04;
            } else {
                if (i2 == 8) {
                    c = '\'';
                } else if (i2 == 9) {
                    c = '\"';
                } else if (i2 == 10) {
                    str2 = A05();
                    this.A09 = str2;
                } else if (i2 != 11) {
                    str = AnonymousClass06.A04("Expected a double but was ", h4.A00(A0G()), A0K());
                    throw new IllegalStateException(str);
                }
                str2 = A06(c);
                this.A09 = str2;
            }
            this.A03 = 11;
            d = Double.parseDouble(this.A09);
            if (this.A07 || (!Double.isNaN(d) && !Double.isInfinite(d))) {
                this.A09 = null;
                this.A03 = 0;
                iArr = this.A0A;
                i = this.A08;
            } else {
                StringBuilder sb = new StringBuilder("JSON forbids NaN and infinities: ");
                sb.append(d);
                sb.append(A0K());
                throw new h2(sb.toString());
            }
        } else {
            LT lt = (LT) this;
            Integer A0G = lt.A0G();
            Integer num = AnonymousClass07.A06;
            if (A0G == num || A0G == AnonymousClass07.A05) {
                M1 m1 = (M1) lt.A02[lt.A00 - 1];
                if (m1.A00 instanceof Number) {
                    d = m1.A02().doubleValue();
                } else {
                    d = Double.parseDouble(m1.A03());
                }
                if (lt.A07 || (!Double.isNaN(d) && !Double.isInfinite(d))) {
                    LT.A00(lt);
                    i = lt.A00;
                    if (i <= 0) {
                        return d;
                    }
                    iArr = lt.A01;
                } else {
                    StringBuilder sb2 = new StringBuilder("JSON forbids NaN and infinities: ");
                    sb2.append(d);
                    throw new NumberFormatException(sb2.toString());
                }
            } else {
                str = AnonymousClass06.A06("Expected ", h4.A00(num), " but was ", h4.A00(A0G), AnonymousClass06.A03(" at path ", lt.A0H()));
                throw new IllegalStateException(str);
            }
        }
        int i4 = i - 1;
        iArr[i4] = iArr[i4] + 1;
        return d;
    }

    public final int A0D() throws IOException {
        String str;
        int parseInt;
        int i;
        char c;
        String A062;
        StringBuilder sb;
        if (!(this instanceof LT)) {
            int i2 = this.A03;
            if (i2 == 0) {
                i2 = A0E();
            }
            if (i2 == 15) {
                long j = this.A06;
                i = (int) j;
                if (j != ((long) i)) {
                    sb = new StringBuilder();
                    sb.append("Expected an int but was ");
                    sb.append(j);
                }
                this.A03 = 0;
                int[] iArr = this.A0A;
                int i3 = this.A08 - 1;
                iArr[i3] = iArr[i3] + 1;
                return i;
            }
            if (i2 == 16) {
                A062 = new String(this.A0D, this.A05, this.A04);
                this.A09 = A062;
                this.A05 += this.A04;
            } else {
                if (i2 == 8) {
                    c = '\'';
                    A062 = A06(c);
                } else if (i2 == 9) {
                    c = '\"';
                    A062 = A06(c);
                } else if (i2 == 10) {
                    A062 = A05();
                } else {
                    str = AnonymousClass06.A04("Expected an int but was ", h4.A00(A0G()), A0K());
                }
                this.A09 = A062;
                try {
                    int parseInt2 = Integer.parseInt(A062);
                    this.A03 = 0;
                    int[] iArr2 = this.A0A;
                    int i4 = this.A08 - 1;
                    iArr2[i4] = iArr2[i4] + 1;
                    return parseInt2;
                } catch (NumberFormatException unused) {
                }
            }
            this.A03 = 11;
            double parseDouble = Double.parseDouble(A062);
            i = (int) parseDouble;
            if (((double) i) == parseDouble) {
                this.A09 = null;
                this.A03 = 0;
                int[] iArr3 = this.A0A;
                int i32 = this.A08 - 1;
                iArr3[i32] = iArr3[i32] + 1;
                return i;
            }
            sb = new StringBuilder();
            sb.append("Expected an int but was ");
            sb.append(A062);
            sb.append(A0K());
            throw new NumberFormatException(sb.toString());
        }
        LT lt = (LT) this;
        Integer A0G = lt.A0G();
        Integer num = AnonymousClass07.A06;
        if (A0G == num || A0G == AnonymousClass07.A05) {
            M1 m1 = (M1) lt.A02[lt.A00 - 1];
            if (m1.A00 instanceof Number) {
                parseInt = m1.A02().intValue();
            } else {
                parseInt = Integer.parseInt(m1.A03());
            }
            LT.A00(lt);
            int i5 = lt.A00;
            if (i5 > 0) {
                int[] iArr4 = lt.A01;
                int i6 = i5 - 1;
                iArr4[i6] = iArr4[i6] + 1;
            }
            return parseInt;
        }
        str = AnonymousClass06.A06("Expected ", h4.A00(num), " but was ", h4.A00(A0G), AnonymousClass06.A03(" at path ", lt.A0H()));
        throw new IllegalStateException(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0169, code lost:
        if (r13 == 1) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0231, code lost:
        if (r13 != 5) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0248, code lost:
        if (A0A(r0) != false) goto L_0x0279;
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
        r21.A06 = r2;
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
    public final int A0E() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 709
        */
        throw new UnsupportedOperationException("Method not decompiled: X.h5.A0E():int");
    }

    public final long A0F() throws IOException {
        String str;
        long parseLong;
        char c;
        String A062;
        if (!(this instanceof LT)) {
            int i = this.A03;
            if (i == 0) {
                i = A0E();
            }
            if (i == 15) {
                this.A03 = 0;
                int[] iArr = this.A0A;
                int i2 = this.A08 - 1;
                iArr[i2] = iArr[i2] + 1;
                return this.A06;
            }
            if (i == 16) {
                A062 = new String(this.A0D, this.A05, this.A04);
                this.A09 = A062;
                this.A05 += this.A04;
            } else {
                if (i == 8) {
                    c = '\'';
                    A062 = A06(c);
                } else if (i == 9) {
                    c = '\"';
                    A062 = A06(c);
                } else if (i == 10) {
                    A062 = A05();
                } else {
                    str = AnonymousClass06.A04("Expected a long but was ", h4.A00(A0G()), A0K());
                }
                this.A09 = A062;
                try {
                    long parseLong2 = Long.parseLong(A062);
                    this.A03 = 0;
                    int[] iArr2 = this.A0A;
                    int i3 = this.A08 - 1;
                    iArr2[i3] = iArr2[i3] + 1;
                    return parseLong2;
                } catch (NumberFormatException unused) {
                }
            }
            this.A03 = 11;
            double parseDouble = Double.parseDouble(A062);
            long j = (long) parseDouble;
            if (((double) j) == parseDouble) {
                this.A09 = null;
                this.A03 = 0;
                int[] iArr3 = this.A0A;
                int i4 = this.A08 - 1;
                iArr3[i4] = iArr3[i4] + 1;
                return j;
            }
            throw new NumberFormatException(AnonymousClass06.A04("Expected a long but was ", A062, A0K()));
        }
        LT lt = (LT) this;
        Integer A0G = lt.A0G();
        Integer num = AnonymousClass07.A06;
        if (A0G == num || A0G == AnonymousClass07.A05) {
            M1 m1 = (M1) lt.A02[lt.A00 - 1];
            if (m1.A00 instanceof Number) {
                parseLong = m1.A02().longValue();
            } else {
                parseLong = Long.parseLong(m1.A03());
            }
            LT.A00(lt);
            int i5 = lt.A00;
            if (i5 > 0) {
                int[] iArr4 = lt.A01;
                int i6 = i5 - 1;
                iArr4[i6] = iArr4[i6] + 1;
            }
            return parseLong;
        }
        str = AnonymousClass06.A06("Expected ", h4.A00(num), " but was ", h4.A00(A0G), AnonymousClass06.A03(" at path ", lt.A0H()));
        throw new IllegalStateException(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005c, code lost:
        if ((r1 instanceof java.lang.Number) != false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0074, code lost:
        if (r1 != false) goto L_0x0076;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Integer A0G() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 180
        */
        throw new UnsupportedOperationException("Method not decompiled: X.h5.A0G():java.lang.Integer");
    }

    public final String A0H() {
        if (!(this instanceof LT)) {
            StringBuilder sb = new StringBuilder("$");
            int i = this.A08;
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = this.A0B[i2];
                if (i3 == 1 || i3 == 2) {
                    sb.append('[');
                    sb.append(this.A0A[i2]);
                    sb.append(']');
                } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                    sb.append('.');
                    String[] strArr = this.A0C;
                    if (strArr[i2] != null) {
                        sb.append(strArr[i2]);
                    }
                }
            }
            return sb.toString();
        }
        LT lt = (LT) this;
        StringBuilder sb2 = new StringBuilder("$");
        int i4 = 0;
        while (i4 < lt.A00) {
            Object[] objArr = lt.A02;
            if (objArr[i4] instanceof M5) {
                i4++;
                if (objArr[i4] instanceof Iterator) {
                    sb2.append('[');
                    sb2.append(lt.A01[i4]);
                    sb2.append(']');
                }
            } else if (objArr[i4] instanceof M2) {
                i4++;
                if (objArr[i4] instanceof Iterator) {
                    sb2.append('.');
                    String[] strArr2 = lt.A03;
                    if (strArr2[i4] != null) {
                        sb2.append(strArr2[i4]);
                    }
                }
            }
            i4++;
        }
        return sb2.toString();
    }

    public final String A0I() throws IOException {
        char c;
        String A062;
        if (!(this instanceof LT)) {
            int i = this.A03;
            if (i == 0) {
                i = A0E();
            }
            if (i == 14) {
                A062 = A05();
            } else {
                if (i == 12) {
                    c = '\'';
                } else if (i == 13) {
                    c = '\"';
                } else {
                    throw new IllegalStateException(AnonymousClass06.A04("Expected a name but was ", h4.A00(A0G()), A0K()));
                }
                A062 = A06(c);
            }
            this.A03 = 0;
            this.A0C[this.A08 - 1] = A062;
            return A062;
        }
        LT lt = (LT) this;
        LT.A01(lt, AnonymousClass07.A04);
        Map.Entry entry = (Map.Entry) ((Iterator) lt.A02[lt.A00 - 1]).next();
        String str = (String) entry.getKey();
        lt.A03[lt.A00 - 1] = str;
        LT.A02(lt, entry.getValue());
        return str;
    }

    public final String A0J() throws IOException {
        String str;
        String A032;
        int i;
        int[] iArr;
        if (!(this instanceof LT)) {
            int i2 = this.A03;
            if (i2 == 0) {
                i2 = A0E();
            }
            if (i2 == 10) {
                A032 = A05();
            } else if (i2 == 8) {
                A032 = A06('\'');
            } else if (i2 == 9) {
                A032 = A06('\"');
            } else if (i2 == 11) {
                A032 = this.A09;
                this.A09 = null;
            } else if (i2 == 15) {
                A032 = Long.toString(this.A06);
            } else if (i2 == 16) {
                A032 = new String(this.A0D, this.A05, this.A04);
                this.A05 += this.A04;
            } else {
                str = AnonymousClass06.A04("Expected a string but was ", h4.A00(A0G()), A0K());
                throw new IllegalStateException(str);
            }
            this.A03 = 0;
            iArr = this.A0A;
            i = this.A08;
        } else {
            LT lt = (LT) this;
            Integer A0G = lt.A0G();
            Integer num = AnonymousClass07.A05;
            if (A0G == num || A0G == AnonymousClass07.A06) {
                A032 = ((M1) LT.A00(lt)).A03();
                i = lt.A00;
                if (i > 0) {
                    iArr = lt.A01;
                }
                return A032;
            }
            str = AnonymousClass06.A06("Expected ", h4.A00(num), " but was ", h4.A00(A0G), AnonymousClass06.A03(" at path ", lt.A0H()));
            throw new IllegalStateException(str);
        }
        int i3 = i - 1;
        iArr[i3] = iArr[i3] + 1;
        return A032;
    }

    public final String A0K() {
        int i = this.A01 + 1;
        int i2 = (this.A05 - this.A02) + 1;
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(i);
        sb.append(" column ");
        sb.append(i2);
        sb.append(" path ");
        sb.append(A0H());
        return sb.toString();
    }

    public final void A0L() throws IOException {
        if (!(this instanceof LT)) {
            int i = this.A03;
            if (i == 0) {
                i = A0E();
            }
            if (i == 3) {
                A08(1);
                this.A0A[this.A08 - 1] = 0;
                this.A03 = 0;
                return;
            }
            throw new IllegalStateException(AnonymousClass06.A04("Expected BEGIN_ARRAY but was ", h4.A00(A0G()), A0K()));
        }
        LT lt = (LT) this;
        LT.A01(lt, AnonymousClass07.A00);
        LT.A02(lt, ((M5) lt.A02[lt.A00 - 1]).iterator());
        lt.A01[lt.A00 - 1] = 0;
    }

    public final void A0M() throws IOException {
        if (!(this instanceof LT)) {
            int i = this.A03;
            if (i == 0) {
                i = A0E();
            }
            if (i == 1) {
                A08(3);
                this.A03 = 0;
                return;
            }
            throw new IllegalStateException(AnonymousClass06.A04("Expected BEGIN_OBJECT but was ", h4.A00(A0G()), A0K()));
        }
        LT lt = (LT) this;
        LT.A01(lt, AnonymousClass07.A02);
        LT.A02(lt, ((M2) lt.A02[lt.A00 - 1]).A00.entrySet().iterator());
    }

    public final void A0N() throws IOException {
        if (!(this instanceof LT)) {
            int i = this.A03;
            if (i == 0) {
                i = A0E();
            }
            if (i == 4) {
                int i2 = this.A08 - 1;
                this.A08 = i2;
                int[] iArr = this.A0A;
                int i3 = i2 - 1;
                iArr[i3] = iArr[i3] + 1;
                this.A03 = 0;
                return;
            }
            throw new IllegalStateException(AnonymousClass06.A04("Expected END_ARRAY but was ", h4.A00(A0G()), A0K()));
        }
        LT lt = (LT) this;
        LT.A01(lt, AnonymousClass07.A01);
        LT.A00(lt);
        LT.A00(lt);
        int i4 = lt.A00;
        if (i4 > 0) {
            int[] iArr2 = lt.A01;
            int i5 = i4 - 1;
            iArr2[i5] = iArr2[i5] + 1;
        }
    }

    public final void A0O() throws IOException {
        if (!(this instanceof LT)) {
            int i = this.A03;
            if (i == 0) {
                i = A0E();
            }
            if (i == 2) {
                int i2 = this.A08 - 1;
                this.A08 = i2;
                this.A0C[i2] = null;
                int[] iArr = this.A0A;
                int i3 = i2 - 1;
                iArr[i3] = iArr[i3] + 1;
                this.A03 = 0;
                return;
            }
            throw new IllegalStateException(AnonymousClass06.A04("Expected END_OBJECT but was ", h4.A00(A0G()), A0K()));
        }
        LT lt = (LT) this;
        LT.A01(lt, AnonymousClass07.A03);
        LT.A00(lt);
        LT.A00(lt);
        int i4 = lt.A00;
        if (i4 > 0) {
            int[] iArr2 = lt.A01;
            int i5 = i4 - 1;
            iArr2[i5] = iArr2[i5] + 1;
        }
    }

    public final void A0P() throws IOException {
        int i;
        int[] iArr;
        if (!(this instanceof LT)) {
            int i2 = this.A03;
            if (i2 == 0) {
                i2 = A0E();
            }
            if (i2 == 7) {
                this.A03 = 0;
                iArr = this.A0A;
                i = this.A08;
            } else {
                throw new IllegalStateException(AnonymousClass06.A04("Expected null but was ", h4.A00(A0G()), A0K()));
            }
        } else {
            LT lt = (LT) this;
            LT.A01(lt, AnonymousClass07.A08);
            LT.A00(lt);
            i = lt.A00;
            if (i > 0) {
                iArr = lt.A01;
            } else {
                return;
            }
        }
        int i3 = i - 1;
        iArr[i3] = iArr[i3] + 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:85:0x00f0, code lost:
        A07();
     */
    /* JADX WARNING: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x011b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0Q() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 316
        */
        throw new UnsupportedOperationException("Method not decompiled: X.h5.A0Q():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0013 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0R() throws java.io.IOException {
        /*
            r3 = this;
            boolean r0 = r3 instanceof X.LT
            if (r0 != 0) goto L_0x0015
            int r2 = r3.A03
            if (r2 != 0) goto L_0x000c
            int r2 = r3.A0E()
        L_0x000c:
            r0 = 2
            if (r2 == r0) goto L_0x0013
            r1 = 4
            r0 = 1
            if (r2 != r1) goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            return r0
        L_0x0015:
            java.lang.Integer r2 = r3.A0G()
            java.lang.Integer r0 = X.AnonymousClass07.A03
            if (r2 == r0) goto L_0x0013
            java.lang.Integer r1 = X.AnonymousClass07.A01
            r0 = 1
            if (r2 != r1) goto L_0x0014
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: X.h5.A0R():boolean");
    }

    public final boolean A0S() throws IOException {
        boolean parseBoolean;
        int[] iArr;
        int i;
        int i2;
        if (!(this instanceof LT)) {
            int i3 = this.A03;
            if (i3 == 0) {
                i3 = A0E();
            }
            parseBoolean = true;
            if (i3 == 5) {
                this.A03 = 0;
                iArr = this.A0A;
                i = this.A08 - 1;
                i2 = iArr[i] + 1;
            } else if (i3 == 6) {
                this.A03 = 0;
                int[] iArr2 = this.A0A;
                int i4 = this.A08 - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return false;
            } else {
                throw new IllegalStateException(AnonymousClass06.A04("Expected a boolean but was ", h4.A00(A0G()), A0K()));
            }
        } else {
            LT lt = (LT) this;
            LT.A01(lt, AnonymousClass07.A07);
            M1 m1 = (M1) LT.A00(lt);
            Object obj = m1.A00;
            if (obj instanceof Boolean) {
                parseBoolean = ((Boolean) obj).booleanValue();
            } else {
                parseBoolean = Boolean.parseBoolean(m1.A03());
            }
            int i5 = lt.A00;
            if (i5 > 0) {
                iArr = lt.A01;
                i = i5 - 1;
                i2 = iArr[i] + 1;
            }
            return parseBoolean;
        }
        iArr[i] = i2;
        return parseBoolean;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!(this instanceof LT)) {
            this.A03 = 0;
            this.A0B[0] = 8;
            this.A08 = 1;
            this.A0E.close();
            return;
        }
        LT lt = (LT) this;
        lt.A02 = new Object[]{LT.A04};
        lt.A00 = 1;
    }

    public h5(Reader reader) {
        int[] iArr = new int[32];
        this.A0B = iArr;
        this.A08 = 0;
        this.A08 = 1;
        iArr[0] = 6;
        this.A0C = new String[32];
        this.A0A = new int[32];
        this.A0E = reader;
    }

    private void A09(String str) throws IOException {
        throw new h2(AnonymousClass06.A03(str, A0K()));
    }

    public String toString() {
        return AnonymousClass06.A03(getClass().getSimpleName(), A0K());
    }
}
