package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.08Z  reason: invalid class name */
public abstract class AnonymousClass08Z extends AnonymousClass0HQ {
    public static final BigDecimal A0P = new BigDecimal(A0T);
    public static final BigDecimal A0Q = new BigDecimal(A0U);
    public static final BigDecimal A0R = new BigDecimal(A0V);
    public static final BigDecimal A0S = new BigDecimal(A0W);
    public static final BigInteger A0T = BigInteger.valueOf(2147483647L);
    public static final BigInteger A0U = BigInteger.valueOf(Long.MAX_VALUE);
    public static final BigInteger A0V = BigInteger.valueOf(LibraryDBContract.VERSION_NOT_INSTALLED);
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
    public long A0A;
    public long A0B = 0;
    public EnumC04820ji A0C;
    public C04080gn A0D;
    public C05110kM A0E = null;
    public BigDecimal A0F;
    public BigInteger A0G;
    public boolean A0H = false;
    public boolean A0I;
    public long A0J = 0;
    public boolean A0K;
    public byte[] A0L;
    public char[] A0M = null;
    public final C05140kP A0N;
    public final C04970jy A0O;

    @Override // X.AbstractC04100gp
    public final Object A0S() throws IOException, C04110gq {
        return null;
    }

    public abstract void A0x() throws IOException;

    public abstract boolean A10() throws IOException;

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.IllegalArgumentException A08(X.C04780jW r2, int r3, int r4, java.lang.String r5) throws java.lang.IllegalArgumentException {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass08Z.A08(X.0jW, int, int, java.lang.String):java.lang.IllegalArgumentException");
    }

    private final void A09(int i) throws IOException, C04110gq {
        NumberFormatException e;
        String str;
        BigDecimal bigDecimal;
        String str2;
        int i2;
        int i3;
        EnumC04820ji r2 = ((AnonymousClass0HQ) this).A00;
        if (r2 == EnumC04820ji.VALUE_NUMBER_INT) {
            C05140kP r1 = this.A0N;
            char[] A0B2 = r1.A0B();
            int i4 = r1.A02;
            if (i4 < 0) {
                i4 = 0;
            }
            int i5 = this.A05;
            boolean z = this.A0I;
            if (z) {
                i4++;
            }
            if (i5 <= 9) {
                i3 = AnonymousClass0k2.A02(A0B2, i4, i5);
                if (z) {
                    i3 = -i3;
                }
            } else if (i5 <= 18) {
                int i6 = i5 - 9;
                long A022 = (((long) AnonymousClass0k2.A02(A0B2, i4, i6)) * 1000000000) + ((long) AnonymousClass0k2.A02(A0B2, i4 + i6, 9));
                if (z) {
                    A022 = -A022;
                }
                if (i5 != 10 || (!z ? A022 > 2147483647L : A022 < LibraryDBContract.VERSION_NOT_INSTALLED)) {
                    this.A0A = A022;
                    this.A06 = 2;
                    return;
                }
                i3 = (int) A022;
            } else {
                String A062 = r1.A06();
                try {
                    if (this.A0I) {
                        str2 = AnonymousClass0k2.A00;
                    } else {
                        str2 = "9223372036854775807";
                    }
                    int length = str2.length();
                    if (i5 >= length) {
                        if (i5 <= length) {
                            int i7 = 0;
                            while (true) {
                                if (i7 >= length) {
                                    break;
                                }
                                int charAt = A0B2[i4 + i7] - str2.charAt(i7);
                                if (charAt == 0) {
                                    i7++;
                                } else if (charAt < 0) {
                                }
                            }
                        }
                        this.A0G = new BigInteger(A062);
                        i2 = 4;
                        this.A06 = i2;
                        return;
                    }
                    this.A0A = Long.parseLong(A062);
                    i2 = 2;
                    this.A06 = i2;
                    return;
                } catch (NumberFormatException e2) {
                    e = e2;
                    str = AnonymousClass006.A07("Malformed numeric value '", A062, "'");
                    throw new C04110gq(str, A0O(), e);
                }
            }
            this.A07 = i3;
            this.A06 = 1;
        } else if (r2 != EnumC04820ji.VALUE_NUMBER_FLOAT) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(r2);
            sb.append(") not numeric, can not use numeric value accessors");
            A0p(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (i == 16) {
            try {
                C05140kP r3 = this.A0N;
                char[] cArr = r3.A09;
                if (cArr != null) {
                    bigDecimal = new BigDecimal(cArr);
                } else {
                    int i8 = r3.A02;
                    if (i8 >= 0) {
                        bigDecimal = new BigDecimal(r3.A08, i8, r3.A01);
                    } else if (r3.A03 == 0) {
                        bigDecimal = new BigDecimal(r3.A07, 0, r3.A00);
                    } else {
                        bigDecimal = new BigDecimal(C05140kP.A03(r3));
                    }
                }
                this.A0F = bigDecimal;
                this.A06 = 16;
            } catch (NumberFormatException e3) {
                e = e3;
                str = AnonymousClass006.A07("Malformed numeric value '", this.A0N.A06(), "'");
                throw new C04110gq(str, A0O(), e);
            }
        } else {
            this.A00 = AnonymousClass0k2.A00(this.A0N.A06());
            this.A06 = 8;
        }
    }

    @Override // X.AbstractC04100gp
    public final double A0K() throws IOException, C04110gq {
        double d;
        int i = this.A06;
        if ((i & 8) == 0) {
            if (i == 0) {
                A09(8);
            }
            int i2 = this.A06;
            if ((8 & i2) == 0) {
                if ((i2 & 16) != 0) {
                    d = this.A0F.doubleValue();
                } else if ((i2 & 4) != 0) {
                    d = this.A0G.doubleValue();
                } else if ((i2 & 2) != 0) {
                    d = (double) this.A0A;
                } else if ((i2 & 1) != 0) {
                    d = (double) this.A07;
                } else {
                    C05150kQ.A02();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                this.A00 = d;
                this.A06 |= 8;
            }
        }
        return this.A00;
    }

    @Override // X.AbstractC04100gp
    public final int A0M() throws IOException, C04110gq {
        String obj;
        int intValue;
        int i = this.A06;
        if ((i & 1) == 0) {
            if (i == 0) {
                A09(1);
            }
            int i2 = this.A06;
            if ((1 & i2) == 0) {
                if ((i2 & 2) != 0) {
                    long j = this.A0A;
                    int i3 = (int) j;
                    if (((long) i3) != j) {
                        obj = AnonymousClass006.A07("Numeric value (", A0e(), ") out of range of int");
                    } else {
                        this.A07 = i3;
                        this.A06 |= 1;
                    }
                } else {
                    if ((i2 & 4) != 0) {
                        if (A0V.compareTo(this.A0G) <= 0 && A0T.compareTo(this.A0G) >= 0) {
                            intValue = this.A0G.intValue();
                        }
                        StringBuilder sb = new StringBuilder("Numeric value (");
                        sb.append(A0e());
                        sb.append(") out of range of int (");
                        sb.append(Integer.MIN_VALUE);
                        sb.append(" - ");
                        sb.append(Integer.MAX_VALUE);
                        sb.append(")");
                        obj = sb.toString();
                    } else if ((i2 & 8) != 0) {
                        double d = this.A00;
                        if (d >= -2.147483648E9d && d <= 2.147483647E9d) {
                            intValue = (int) d;
                        }
                        StringBuilder sb2 = new StringBuilder("Numeric value (");
                        sb2.append(A0e());
                        sb2.append(") out of range of int (");
                        sb2.append(Integer.MIN_VALUE);
                        sb2.append(" - ");
                        sb2.append(Integer.MAX_VALUE);
                        sb2.append(")");
                        obj = sb2.toString();
                    } else if ((i2 & 16) != 0) {
                        if (A0R.compareTo(this.A0F) <= 0 && A0P.compareTo(this.A0F) >= 0) {
                            intValue = this.A0F.intValue();
                        }
                        StringBuilder sb22 = new StringBuilder("Numeric value (");
                        sb22.append(A0e());
                        sb22.append(") out of range of int (");
                        sb22.append(Integer.MIN_VALUE);
                        sb22.append(" - ");
                        sb22.append(Integer.MAX_VALUE);
                        sb22.append(")");
                        obj = sb22.toString();
                    } else {
                        C05150kQ.A02();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    this.A07 = intValue;
                    this.A06 |= 1;
                }
                A0p(obj);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
        return this.A07;
    }

    @Override // X.AbstractC04100gp
    public final long A0N() throws IOException, C04110gq {
        long longValue;
        int i = this.A06;
        if ((i & 2) == 0) {
            if (i == 0) {
                A09(2);
            }
            int i2 = this.A06;
            if ((2 & i2) == 0) {
                if ((i2 & 1) != 0) {
                    longValue = (long) this.A07;
                } else {
                    if ((i2 & 4) != 0) {
                        if (A0W.compareTo(this.A0G) <= 0 && A0U.compareTo(this.A0G) >= 0) {
                            longValue = this.A0G.longValue();
                        }
                    } else if ((i2 & 8) != 0) {
                        double d = this.A00;
                        if (d >= -9.223372036854776E18d && d <= 9.223372036854776E18d) {
                            longValue = (long) d;
                        }
                    } else if ((i2 & 16) == 0) {
                        C05150kQ.A02();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    } else if (A0S.compareTo(this.A0F) <= 0 && A0Q.compareTo(this.A0F) >= 0) {
                        longValue = this.A0F.longValue();
                    }
                    StringBuilder sb = new StringBuilder("Numeric value (");
                    sb.append(A0e());
                    sb.append(") out of range of long (");
                    sb.append(Long.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Long.MAX_VALUE);
                    sb.append(")");
                    A0p(sb.toString());
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                this.A0A = longValue;
                this.A06 |= 2;
            }
        }
        return this.A0A;
    }

    @Override // X.AbstractC04100gp
    public final AnonymousClass0jc A0O() {
        int i = this.A04;
        return new AnonymousClass0jc(this.A0O.A03, -1, (this.A0J + ((long) i)) - 1, this.A01, (i - this.A02) + 1);
    }

    @Override // X.AbstractC04100gp
    public final AnonymousClass0jc A0P() {
        Object obj = this.A0O.A03;
        long j = this.A0B;
        int i = this.A09;
        int i2 = this.A08;
        if (i2 >= 0) {
            i2++;
        }
        return new AnonymousClass0jc(obj, -1, j, i, i2);
    }

    @Override // X.AbstractC04100gp
    public final Integer A0Q() throws IOException, C04110gq {
        if (this.A06 == 0) {
            A09(0);
        }
        if (((AnonymousClass0HQ) this).A00 == EnumC04820ji.VALUE_NUMBER_INT) {
            int i = this.A06;
            if ((i & 1) != 0) {
                return AnonymousClass007.A00;
            }
            if ((i & 2) != 0) {
                return AnonymousClass007.A01;
            }
            return AnonymousClass007.A0C;
        } else if ((this.A06 & 16) != 0) {
            return AnonymousClass007.A0F;
        } else {
            return AnonymousClass007.A0E;
        }
    }

    @Override // X.AbstractC04100gp
    public final Number A0R() throws IOException, C04110gq {
        if (this.A06 == 0) {
            A09(0);
        }
        if (((AnonymousClass0HQ) this).A00 == EnumC04820ji.VALUE_NUMBER_INT) {
            int i = this.A06;
            if ((i & 1) != 0) {
                return Integer.valueOf(this.A07);
            }
            if ((i & 2) != 0) {
                return Long.valueOf(this.A0A);
            }
            if ((i & 4) != 0) {
                return this.A0G;
            }
            return this.A0F;
        }
        int i2 = this.A06;
        if ((i2 & 16) != 0) {
            return this.A0F;
        }
        if ((i2 & 8) != 0) {
            return Double.valueOf(this.A00);
        }
        C05150kQ.A02();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC04100gp
    public final BigDecimal A0T() throws IOException, C04110gq {
        long j;
        BigDecimal valueOf;
        int i = this.A06;
        if ((i & 16) == 0) {
            if (i == 0) {
                A09(16);
            }
            int i2 = this.A06;
            if ((16 & i2) == 0) {
                if ((i2 & 8) != 0) {
                    valueOf = new BigDecimal(A0e());
                } else if ((i2 & 4) != 0) {
                    valueOf = new BigDecimal(this.A0G);
                } else {
                    if ((i2 & 2) != 0) {
                        j = this.A0A;
                    } else if ((i2 & 1) != 0) {
                        j = (long) this.A07;
                    } else {
                        C05150kQ.A02();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    valueOf = BigDecimal.valueOf(j);
                }
                this.A0F = valueOf;
                this.A06 |= 16;
            }
        }
        return this.A0F;
    }

    @Override // X.AbstractC04100gp
    public final BigInteger A0U() throws IOException, C04110gq {
        BigInteger bigInteger;
        int i = this.A06;
        if ((i & 4) == 0) {
            if (i == 0) {
                A09(4);
            }
            int i2 = this.A06;
            if ((4 & i2) == 0) {
                if ((i2 & 16) != 0) {
                    bigInteger = this.A0F.toBigInteger();
                } else if ((i2 & 2) != 0) {
                    bigInteger = BigInteger.valueOf(this.A0A);
                } else if ((i2 & 1) != 0) {
                    bigInteger = BigInteger.valueOf((long) this.A07);
                } else if ((i2 & 8) != 0) {
                    bigInteger = BigDecimal.valueOf(this.A00).toBigInteger();
                } else {
                    C05150kQ.A02();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                this.A0G = bigInteger;
                this.A06 |= 4;
            }
        }
        return this.A0G;
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final String A0d() throws IOException, C04110gq {
        C04080gn r0;
        EnumC04820ji r1 = ((AnonymousClass0HQ) this).A00;
        if (r1 == EnumC04820ji.START_OBJECT || r1 == EnumC04820ji.START_ARRAY) {
            r0 = this.A0D.A04;
        } else {
            r0 = this.A0D;
        }
        return r0.A02;
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final boolean A0h() {
        EnumC04820ji r1 = ((AnonymousClass0HQ) this).A00;
        if (r1 == EnumC04820ji.VALUE_STRING) {
            return true;
        }
        if (r1 == EnumC04820ji.FIELD_NAME) {
            return this.A0H;
        }
        return false;
    }

    @Override // X.AnonymousClass0HQ
    public final void A0l() throws C04110gq {
        C04080gn r2 = this.A0D;
        if (((AnonymousClass0jh) r2).A01 != 0) {
            StringBuilder sb = new StringBuilder(": expected close marker for ");
            sb.append(r2.A00());
            sb.append(" (from ");
            sb.append(new AnonymousClass0jc(this.A0O.A03, -1, -1, r2.A01, r2.A00));
            sb.append(")");
            A0q(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public char A0s() throws IOException, C04110gq {
        throw new UnsupportedOperationException();
    }

    public final int A0t(C04780jW r2, char c, int i) throws IOException, C04110gq {
        if (c == '\\') {
            c = A0s();
            if (c <= ' ' && i == 0) {
                return -1;
            }
            int A012 = r2.A01(c);
            if (A012 >= 0) {
                return A012;
            }
        }
        throw A08(r2, c, i, null);
    }

    public final EnumC04820ji A0u(String str, double d) {
        C05140kP r3 = this.A0N;
        r3.A08 = null;
        r3.A02 = -1;
        r3.A01 = 0;
        r3.A04 = str;
        r3.A09 = null;
        if (r3.A06) {
            C05140kP.A00(r3);
        }
        r3.A00 = 0;
        this.A00 = d;
        this.A06 = 8;
        return EnumC04820ji.VALUE_NUMBER_FLOAT;
    }

    public void A0v() throws IOException {
        C05140kP r3 = this.A0N;
        AnonymousClass0kL r1 = r3.A0A;
        if (r1 == null) {
            r3.A02 = -1;
            r3.A00 = 0;
            r3.A01 = 0;
            r3.A08 = null;
            r3.A04 = null;
            r3.A09 = null;
            if (r3.A06) {
                C05140kP.A00(r3);
            }
        } else {
            char[] cArr = r3.A07;
            if (cArr != null) {
                r3.A02 = -1;
                r3.A00 = 0;
                r3.A01 = 0;
                r3.A08 = null;
                r3.A04 = null;
                r3.A09 = null;
                if (r3.A06) {
                    C05140kP.A00(r3);
                }
                r3.A07 = null;
                r1.A00[2] = cArr;
            }
        }
        char[] cArr2 = this.A0M;
        if (cArr2 != null) {
            this.A0M = null;
            C04970jy r12 = this.A0O;
            if (cArr2 == r12.A00) {
                r12.A00 = null;
                r12.A02.A00[3] = cArr2;
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void A0y(int i, char c) throws C04110gq {
        StringBuilder sb = new StringBuilder("");
        C04080gn r1 = this.A0D;
        sb.append(new AnonymousClass0jc(this.A0O.A03, -1, -1, r1.A01, r1.A00));
        String obj = sb.toString();
        StringBuilder sb2 = new StringBuilder("Unexpected close marker '");
        sb2.append((char) i);
        sb2.append("': expected '");
        sb2.append(c);
        sb2.append("' (for ");
        sb2.append(this.A0D.A00());
        sb2.append(" starting at ");
        sb2.append(obj);
        sb2.append(")");
        A0p(sb2.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A0z(int i, String str) throws C04110gq {
        String A072 = AnonymousClass006.A07("Unexpected character (", AnonymousClass0HQ.A0A(i), ") in numeric value");
        if (str != null) {
            A072 = AnonymousClass006.A07(A072, ": ", str);
        }
        A0p(A072);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0HQ, X.AbstractC04100gp
    public void close() throws IOException {
        if (!this.A0K) {
            this.A0K = true;
            try {
                A0x();
            } finally {
                A0v();
            }
        }
    }

    public AnonymousClass08Z(C04970jy r6, int i) {
        ((AbstractC04100gp) this).A00 = i;
        this.A0O = r6;
        this.A0N = new C05140kP(r6.A02);
        this.A0D = new C04080gn(null, 0, 1, 0);
    }

    @Override // X.AbstractC04100gp
    public final float A0L() throws IOException, C04110gq {
        return (float) A0K();
    }

    public final void A0w() throws IOException {
        if (!A10()) {
            StringBuilder sb = new StringBuilder(" in ");
            sb.append(((AnonymousClass0HQ) this).A00);
            A0q(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
