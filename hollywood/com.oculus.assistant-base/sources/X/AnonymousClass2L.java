package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.2L  reason: invalid class name */
public abstract class AnonymousClass2L extends VG {
    public static final BigDecimal A0P = new BigDecimal(A0T);
    public static final BigDecimal A0Q = new BigDecimal(A0U);
    public static final BigDecimal A0R = new BigDecimal(A0V);
    public static final BigDecimal A0S = new BigDecimal(A0W);
    public static final BigInteger A0T = BigInteger.valueOf(2147483647L);
    public static final BigInteger A0U = BigInteger.valueOf(Long.MAX_VALUE);
    public static final BigInteger A0V = BigInteger.valueOf(-2147483648L);
    public static final BigInteger A0W = BigInteger.valueOf(Long.MIN_VALUE);
    public double A00;
    public int A01 = 1;
    public int A02 = 0;
    public int A03 = 0;
    public int A04 = 0;
    public int A05;
    public int A06 = 0;
    public int A07;
    public int A08 = 0;
    public int A09 = 1;
    public long A0A = 0;
    public long A0B;
    public long A0C = 0;
    public NX A0D;
    public C1016qk A0E;
    public C0259Nx A0F = null;
    public BigDecimal A0G;
    public BigInteger A0H;
    public boolean A0I = false;
    public boolean A0J;
    public byte[] A0K;
    public char[] A0L = null;
    public boolean A0M;
    public final C0247Ng A0N;
    public final O0 A0O;

    private final void A11() {
        AnonymousClass7v r2 = (AnonymousClass7v) this;
        Reader reader = r2.A01;
        if (reader != null) {
            if (r2.A0N.A05 || r2.A0j(NU.AUTO_CLOSE_SOURCE)) {
                reader.close();
            }
            r2.A01 = null;
        }
    }

    public final boolean A16() {
        AnonymousClass7v r5 = (AnonymousClass7v) this;
        long j = r5.A0A;
        int i = ((AnonymousClass2L) r5).A03;
        r5.A0A = j + ((long) i);
        ((AnonymousClass2L) r5).A02 -= i;
        Reader reader = r5.A01;
        if (reader == null) {
            return false;
        }
        char[] cArr = r5.A03;
        int read = reader.read(cArr, 0, cArr.length);
        if (read > 0) {
            ((AnonymousClass2L) r5).A04 = 0;
            ((AnonymousClass2L) r5).A03 = read;
            return true;
        }
        r5.A11();
        if (read != 0) {
            return false;
        }
        throw new IOException(AnonymousClass08.A00("Reader returned 0 characters when trying to read ", ((AnonymousClass2L) r5).A03));
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.IllegalArgumentException A00(X.NP r2, int r3, int r4, java.lang.String r5) {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2L.A00(X.NP, int, int, java.lang.String):java.lang.IllegalArgumentException");
    }

    public static final void A01(AnonymousClass2L r2) {
        StringBuilder sb = new StringBuilder("Numeric value (");
        sb.append(r2.A0p());
        sb.append(") out of range of int (");
        sb.append(Integer.MIN_VALUE);
        sb.append(" - ");
        sb.append(Integer.MAX_VALUE);
        sb.append(")");
        r2.A0v(sb.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static final void A02(AnonymousClass2L r3) {
        StringBuilder sb = new StringBuilder("Numeric value (");
        sb.append(r3.A0p());
        sb.append(") out of range of long (");
        sb.append(Long.MIN_VALUE);
        sb.append(" - ");
        sb.append(Long.MAX_VALUE);
        sb.append(")");
        r3.A0v(sb.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public static final void A03(AnonymousClass2L r11, int i) {
        BigDecimal bigDecimal;
        String str;
        int i2;
        int i3;
        NX nx = ((VG) r11).A00;
        if (nx == NX.VALUE_NUMBER_INT) {
            O0 o0 = r11.A0O;
            char[] A0E2 = o0.A0E();
            int i4 = o0.A02;
            if (i4 < 0) {
                i4 = 0;
            }
            int i5 = r11.A05;
            boolean z = r11.A0J;
            if (z) {
                i4++;
            }
            if (i5 <= 9) {
                i3 = Nj.A02(A0E2, i4, i5);
                if (z) {
                    i3 = -i3;
                }
            } else if (i5 <= 18) {
                int i6 = i5 - 9;
                long A022 = (((long) Nj.A02(A0E2, i4, i6)) * 1000000000) + ((long) Nj.A02(A0E2, i4 + i6, 9));
                if (z) {
                    A022 = -A022;
                }
                if (i5 != 10 || (!z ? A022 > 2147483647L : A022 < -2147483648L)) {
                    r11.A0B = A022;
                    r11.A06 = 2;
                    return;
                }
                i3 = (int) A022;
            } else {
                String A052 = o0.A05();
                try {
                    if (r11.A0J) {
                        str = Nj.A00;
                    } else {
                        str = "9223372036854775807";
                    }
                    int length = str.length();
                    if (i5 >= length) {
                        if (i5 <= length) {
                            int i7 = 0;
                            while (true) {
                                if (i7 >= length) {
                                    break;
                                }
                                int charAt = A0E2[i4 + i7] - str.charAt(i7);
                                if (charAt == 0) {
                                    i7++;
                                } else if (charAt < 0) {
                                }
                            }
                        }
                        r11.A0H = new BigInteger(A052);
                        i2 = 4;
                        r11.A06 = i2;
                        return;
                    }
                    r11.A0B = Long.parseLong(A052);
                    i2 = 2;
                    r11.A06 = i2;
                    return;
                } catch (NumberFormatException e) {
                    throw new C1013qh(AnonymousClass08.A05("Malformed numeric value '", A052, "'"), r11.A0R(), e);
                }
            }
            r11.A07 = i3;
            r11.A06 = 1;
        } else if (nx != NX.VALUE_NUMBER_FLOAT) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(nx);
            sb.append(") not numeric, can not use numeric value accessors");
            r11.A0v(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (i == 16) {
            try {
                O0 o02 = r11.A0O;
                char[] cArr = o02.A09;
                if (cArr != null) {
                    bigDecimal = new BigDecimal(cArr);
                } else {
                    int i8 = o02.A02;
                    if (i8 >= 0) {
                        bigDecimal = new BigDecimal(o02.A08, i8, o02.A01);
                    } else if (o02.A03 == 0) {
                        bigDecimal = new BigDecimal(o02.A07, 0, o02.A00);
                    } else {
                        bigDecimal = new BigDecimal(o02.A0A());
                    }
                }
                r11.A0G = bigDecimal;
                r11.A06 = 16;
            } catch (NumberFormatException e2) {
                throw new C1013qh(AnonymousClass08.A05("Malformed numeric value '", r11.A0O.A05(), "'"), r11.A0R(), e2);
            }
        } else {
            r11.A00 = Nj.A00(r11.A0O.A05());
            r11.A06 = 8;
        }
    }

    public final char A0y() {
        int i;
        if (!(this instanceof AnonymousClass7v)) {
            throw new UnsupportedOperationException();
        }
        AnonymousClass7v r3 = (AnonymousClass7v) this;
        if (((AnonymousClass2L) r3).A04 < ((AnonymousClass2L) r3).A03 || r3.A16()) {
            char[] cArr = r3.A03;
            int i2 = ((AnonymousClass2L) r3).A04;
            int i3 = i2 + 1;
            ((AnonymousClass2L) r3).A04 = i3;
            char c = cArr[i2];
            if (!(c == '\"' || c == '/' || c == '\\')) {
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
                if (c == 'u') {
                    int i4 = 0;
                    int i5 = 0;
                    do {
                        if (i3 < ((AnonymousClass2L) r3).A03 || r3.A16()) {
                            char[] cArr2 = r3.A03;
                            int i6 = ((AnonymousClass2L) r3).A04;
                            i3 = i6 + 1;
                            ((AnonymousClass2L) r3).A04 = i3;
                            char c2 = cArr2[i6];
                            if (c2 > 127 || (i = C0245Ne.A03[c2]) < 0) {
                                r3.A0t(c2, "expected a hex-digit for character escape sequence");
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                            i5 = (i5 << 4) | i;
                            i4++;
                        } else {
                            r3.A0w(" in character escape sequence");
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } while (i4 < 4);
                    return (char) i5;
                } else if (!r3.A0j(NU.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) && (c != '\'' || !r3.A0j(NU.ALLOW_SINGLE_QUOTES))) {
                    r3.A0v(AnonymousClass08.A04("Unrecognized character escape ", VG.A0D(c)));
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            return c;
        }
        r3.A0w(" in character escape sequence");
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final int A0z(NP np, char c, int i) {
        if (c == '\\') {
            char A0y = A0y();
            if (A0y <= ' ' && i == 0) {
                return -1;
            }
            int A012 = np.A01(A0y);
            if (A012 >= 0) {
                return A012;
            }
            throw A00(np, A0y, i, null);
        }
        throw A00(np, c, i, null);
    }

    public final NX A10(String str, double d) {
        O0 o0 = this.A0O;
        o0.A08 = null;
        o0.A02 = -1;
        o0.A01 = 0;
        o0.A04 = str;
        o0.A09 = null;
        if (o0.A06) {
            O0.A00(o0);
        }
        o0.A00 = 0;
        this.A00 = d;
        this.A06 = 8;
        return NX.VALUE_NUMBER_FLOAT;
    }

    public void A12() {
        this.A0O.A06();
        char[] cArr = this.A0L;
        if (cArr != null) {
            this.A0L = null;
            C0247Ng ng = this.A0N;
            if (cArr == ng.A01) {
                ng.A01 = null;
                ng.A03.A00[3] = cArr;
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void A14(int i, char c) {
        StringBuilder sb = new StringBuilder(OacrConstants.AUTO_SPEECH_DOMAIN);
        C1016qk qkVar = this.A0E;
        sb.append(new NT(this.A0N.A04, -1, -1, qkVar.A01, qkVar.A00));
        String obj = sb.toString();
        StringBuilder sb2 = new StringBuilder("Unexpected close marker '");
        sb2.append((char) i);
        sb2.append("': expected '");
        sb2.append(c);
        sb2.append("' (for ");
        sb2.append(this.A0E.A00());
        sb2.append(" starting at ");
        sb2.append(obj);
        sb2.append(")");
        A0v(sb2.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A15(int i, String str) {
        String A052 = AnonymousClass08.A05("Unexpected character (", VG.A0D(i), ") in numeric value");
        if (str != null) {
            A052 = AnonymousClass08.A05(A052, ": ", str);
        }
        A0v(A052);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC1014qi, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.A0M) {
            this.A0M = true;
            try {
                A11();
            } finally {
                A12();
            }
        }
    }

    public AnonymousClass2L(C0247Ng ng, int i) {
        ((AbstractC1014qi) this).A00 = i;
        this.A0N = ng;
        this.A0O = new O0(ng.A03);
        this.A0E = new C1016qk(null, 0, 1, 0);
    }

    public final void A13() {
        if (!A16()) {
            StringBuilder sb = new StringBuilder(" in ");
            sb.append(((VG) this).A00);
            A0w(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
