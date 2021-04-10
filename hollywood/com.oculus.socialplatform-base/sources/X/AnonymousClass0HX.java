package X;

import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0HX  reason: invalid class name */
public abstract class AnonymousClass0HX extends AbstractC01190Sv {
    public static final BigDecimal A0P = new BigDecimal(A0T);
    public static final BigDecimal A0Q = new BigDecimal(A0U);
    public static final BigDecimal A0R = new BigDecimal(A0V);
    public static final BigDecimal A0S = new BigDecimal(A0W);
    public static final BigInteger A0T = BigInteger.valueOf(2147483647L);
    public static final BigInteger A0U = BigInteger.valueOf(RecyclerView.FOREVER_NS);
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
    public EnumC03640oE A0C;
    public AnonymousClass0iO A0D;
    public C03940or A0E = null;
    public BigDecimal A0F;
    public BigInteger A0G;
    public boolean A0H = false;
    public boolean A0I;
    public long A0J = 0;
    public boolean A0K;
    public byte[] A0L;
    public char[] A0M = null;
    public final C03970ou A0N;
    public final C03750oT A0O;

    @Override // X.AbstractC02280iQ
    public final Object A0Z() throws IOException, C02290iR {
        return null;
    }

    public abstract void A15() throws IOException;

    public abstract boolean A18() throws IOException;

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.IllegalArgumentException A09(X.AnonymousClass0o2 r2, int r3, int r4, java.lang.String r5) throws java.lang.IllegalArgumentException {
        /*
        // Method dump skipped, instructions count: 134
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HX.A09(X.0o2, int, int, java.lang.String):java.lang.IllegalArgumentException");
    }

    private final void A0A() throws IOException, C02290iR {
        StringBuilder sb = new StringBuilder("Numeric value (");
        sb.append(A0m());
        sb.append(") out of range of int (");
        sb.append(Integer.MIN_VALUE);
        sb.append(" - ");
        sb.append(Integer.MAX_VALUE);
        sb.append(")");
        A0x(sb.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    private final void A0B() throws IOException, C02290iR {
        StringBuilder sb = new StringBuilder("Numeric value (");
        sb.append(A0m());
        sb.append(") out of range of long (");
        sb.append(Long.MIN_VALUE);
        sb.append(" - ");
        sb.append(RecyclerView.FOREVER_NS);
        sb.append(")");
        A0x(sb.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    private final void A0C(int i) throws IOException, C02290iR {
        BigDecimal bigDecimal;
        String str;
        int i2;
        int i3;
        EnumC03640oE r2 = ((AbstractC01190Sv) this).A00;
        if (r2 == EnumC03640oE.VALUE_NUMBER_INT) {
            C03970ou r1 = this.A0N;
            char[] A0E2 = r1.A0E();
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
                i3 = C03780oX.A02(A0E2, i4, i5);
                if (z) {
                    i3 = -i3;
                }
            } else if (i5 <= 18) {
                int i6 = i5 - 9;
                long A022 = (((long) C03780oX.A02(A0E2, i4, i6)) * 1000000000) + ((long) C03780oX.A02(A0E2, i4 + i6, 9));
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
                String A052 = r1.A05();
                try {
                    if (this.A0I) {
                        str = C03780oX.A00;
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
                        this.A0G = new BigInteger(A052);
                        i2 = 4;
                        this.A06 = i2;
                        return;
                    }
                    this.A0A = Long.parseLong(A052);
                    i2 = 2;
                    this.A06 = i2;
                    return;
                } catch (NumberFormatException e) {
                    throw new C02290iR(AnonymousClass006.A09("Malformed numeric value '", A052, "'"), A0V(), e);
                }
            }
            this.A07 = i3;
            this.A06 = 1;
        } else if (r2 != EnumC03640oE.VALUE_NUMBER_FLOAT) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(r2);
            sb.append(") not numeric, can not use numeric value accessors");
            A0x(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (i == 16) {
            try {
                C03970ou r3 = this.A0N;
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
                        bigDecimal = new BigDecimal(r3.A0A());
                    }
                }
                this.A0F = bigDecimal;
                this.A06 = 16;
            } catch (NumberFormatException e2) {
                throw new C02290iR(AnonymousClass006.A09("Malformed numeric value '", this.A0N.A05(), "'"), A0V(), e2);
            }
        } else {
            this.A00 = C03780oX.A00(this.A0N.A05());
            this.A06 = 8;
        }
    }

    @Override // X.AbstractC02280iQ
    public final double A0R() throws IOException, C02290iR {
        double d;
        int i = this.A06;
        if ((i & 8) == 0) {
            if (i == 0) {
                A0C(8);
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
                    C03980ov.A03();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                this.A00 = d;
                this.A06 |= 8;
            }
        }
        return this.A00;
    }

    @Override // X.AbstractC02280iQ
    public final int A0T() throws IOException, C02290iR {
        int intValue;
        int i = this.A06;
        if ((i & 1) == 0) {
            if (i == 0) {
                A0C(1);
            }
            int i2 = this.A06;
            if ((1 & i2) == 0) {
                if ((i2 & 2) != 0) {
                    long j = this.A0A;
                    int i3 = (int) j;
                    if (((long) i3) != j) {
                        A0x(AnonymousClass006.A09("Numeric value (", A0m(), ") out of range of int"));
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    this.A07 = i3;
                } else {
                    if ((i2 & 4) != 0) {
                        if (A0V.compareTo(this.A0G) > 0 || A0T.compareTo(this.A0G) < 0) {
                            A0A();
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                        intValue = this.A0G.intValue();
                    } else if ((i2 & 8) != 0) {
                        double d = this.A00;
                        if (d < -2.147483648E9d || d > 2.147483647E9d) {
                            A0A();
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                        intValue = (int) d;
                    } else if ((i2 & 16) == 0) {
                        C03980ov.A03();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    } else if (A0R.compareTo(this.A0F) > 0 || A0P.compareTo(this.A0F) < 0) {
                        A0A();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    } else {
                        intValue = this.A0F.intValue();
                    }
                    this.A07 = intValue;
                }
                this.A06 |= 1;
            }
        }
        return this.A07;
    }

    @Override // X.AbstractC02280iQ
    public final long A0U() throws IOException, C02290iR {
        long longValue;
        int i = this.A06;
        if ((i & 2) == 0) {
            if (i == 0) {
                A0C(2);
            }
            int i2 = this.A06;
            if ((2 & i2) == 0) {
                if ((i2 & 1) != 0) {
                    longValue = (long) this.A07;
                } else if ((i2 & 4) != 0) {
                    if (A0W.compareTo(this.A0G) > 0 || A0U.compareTo(this.A0G) < 0) {
                        A0B();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    longValue = this.A0G.longValue();
                } else if ((i2 & 8) != 0) {
                    double d = this.A00;
                    if (d < -9.223372036854776E18d || d > 9.223372036854776E18d) {
                        A0B();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    longValue = (long) d;
                } else if ((i2 & 16) == 0) {
                    C03980ov.A03();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else if (A0S.compareTo(this.A0F) > 0 || A0Q.compareTo(this.A0F) < 0) {
                    A0B();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else {
                    longValue = this.A0F.longValue();
                }
                this.A0A = longValue;
                this.A06 |= 2;
            }
        }
        return this.A0A;
    }

    @Override // X.AbstractC02280iQ
    public final AnonymousClass0o8 A0V() {
        int i = this.A04;
        return new AnonymousClass0o8(this.A0O.A04, -1, (this.A0J + ((long) i)) - 1, this.A01, (i - this.A02) + 1);
    }

    @Override // X.AbstractC02280iQ
    public final AnonymousClass0o8 A0W() {
        Object obj = this.A0O.A04;
        long j = this.A0B;
        int i = this.A09;
        int i2 = this.A08;
        if (i2 >= 0) {
            i2++;
        }
        return new AnonymousClass0o8(obj, -1, j, i, i2);
    }

    @Override // X.AbstractC02280iQ
    public final Integer A0X() throws IOException, C02290iR {
        if (this.A06 == 0) {
            A0C(0);
        }
        if (((AbstractC01190Sv) this).A00 == EnumC03640oE.VALUE_NUMBER_INT) {
            int i = this.A06;
            if ((i & 1) != 0) {
                return AnonymousClass007.A00;
            }
            if ((i & 2) != 0) {
                return AnonymousClass007.A01;
            }
            return AnonymousClass007.A03;
        } else if ((this.A06 & 16) != 0) {
            return AnonymousClass007.A06;
        } else {
            return AnonymousClass007.A05;
        }
    }

    @Override // X.AbstractC02280iQ
    public final Number A0Y() throws IOException, C02290iR {
        if (this.A06 == 0) {
            A0C(0);
        }
        if (((AbstractC01190Sv) this).A00 == EnumC03640oE.VALUE_NUMBER_INT) {
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
        C03980ov.A03();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC02280iQ
    public final BigDecimal A0a() throws IOException, C02290iR {
        long j;
        BigDecimal valueOf;
        int i = this.A06;
        if ((i & 16) == 0) {
            if (i == 0) {
                A0C(16);
            }
            int i2 = this.A06;
            if ((16 & i2) == 0) {
                if ((i2 & 8) != 0) {
                    valueOf = new BigDecimal(A0m());
                } else if ((i2 & 4) != 0) {
                    valueOf = new BigDecimal(this.A0G);
                } else {
                    if ((i2 & 2) != 0) {
                        j = this.A0A;
                    } else if ((i2 & 1) != 0) {
                        j = (long) this.A07;
                    } else {
                        C03980ov.A03();
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

    @Override // X.AbstractC02280iQ
    public final BigInteger A0b() throws IOException, C02290iR {
        BigInteger bigInteger;
        int i = this.A06;
        if ((i & 4) == 0) {
            if (i == 0) {
                A0C(4);
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
                    C03980ov.A03();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                this.A0G = bigInteger;
                this.A06 |= 4;
            }
        }
        return this.A0G;
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final String A0l() throws IOException, C02290iR {
        AnonymousClass0iO r0;
        EnumC03640oE r1 = ((AbstractC01190Sv) this).A00;
        if (r1 == EnumC03640oE.START_OBJECT || r1 == EnumC03640oE.START_ARRAY) {
            r0 = this.A0D.A04;
        } else {
            r0 = this.A0D;
        }
        return r0.A02;
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final boolean A0p() {
        EnumC03640oE r1 = ((AbstractC01190Sv) this).A00;
        if (r1 == EnumC03640oE.VALUE_STRING) {
            return true;
        }
        if (r1 == EnumC03640oE.FIELD_NAME) {
            return this.A0H;
        }
        return false;
    }

    @Override // X.AbstractC01190Sv
    public final void A0t() throws C02290iR {
        AnonymousClass0iO r2 = this.A0D;
        if (((AbstractC03630oD) r2).A01 != 0) {
            StringBuilder sb = new StringBuilder(": expected close marker for ");
            sb.append(r2.A00());
            sb.append(" (from ");
            sb.append(new AnonymousClass0o8(this.A0O.A04, -1, -1, r2.A01, r2.A00));
            sb.append(")");
            A0y(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public char A10() throws IOException, C02290iR {
        throw new UnsupportedOperationException();
    }

    public final int A11(AnonymousClass0o2 r3, char c, int i) throws IOException, C02290iR {
        if (c == '\\') {
            char A10 = A10();
            if (A10 <= ' ' && i == 0) {
                return -1;
            }
            int A012 = r3.A01(A10);
            if (A012 >= 0) {
                return A012;
            }
            throw A09(r3, A10, i, null);
        }
        throw A09(r3, c, i, null);
    }

    public final EnumC03640oE A12(String str, double d) {
        C03970ou r3 = this.A0N;
        r3.A08 = null;
        r3.A02 = -1;
        r3.A01 = 0;
        r3.A04 = str;
        r3.A09 = null;
        if (r3.A06) {
            C03970ou.A00(r3);
        }
        r3.A00 = 0;
        this.A00 = d;
        this.A06 = 8;
        return EnumC03640oE.VALUE_NUMBER_FLOAT;
    }

    public void A13() throws IOException {
        this.A0N.A06();
        char[] cArr = this.A0M;
        if (cArr != null) {
            this.A0M = null;
            C03750oT r1 = this.A0O;
            if (cArr == r1.A01) {
                r1.A01 = null;
                r1.A03.A00[3] = cArr;
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    public final void A16(int i, char c) throws C02290iR {
        StringBuilder sb = new StringBuilder("");
        AnonymousClass0iO r1 = this.A0D;
        sb.append(new AnonymousClass0o8(this.A0O.A04, -1, -1, r1.A01, r1.A00));
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
        A0x(sb2.toString());
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A17(int i, String str) throws C02290iR {
        String A092 = AnonymousClass006.A09("Unexpected character (", AbstractC01190Sv.A0D(i), ") in numeric value");
        if (str != null) {
            A092 = AnonymousClass006.A09(A092, ": ", str);
        }
        A0x(A092);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC01190Sv, java.io.Closeable, java.lang.AutoCloseable, X.AbstractC02280iQ
    public void close() throws IOException {
        if (!this.A0K) {
            this.A0K = true;
            try {
                A15();
            } finally {
                A13();
            }
        }
    }

    public AnonymousClass0HX(C03750oT r6, int i) {
        ((AbstractC02280iQ) this).A00 = i;
        this.A0O = r6;
        this.A0N = new C03970ou(r6.A03);
        this.A0D = new AnonymousClass0iO(null, 0, 1, 0);
    }

    @Override // X.AbstractC02280iQ
    public final float A0S() throws IOException, C02290iR {
        return (float) A0R();
    }

    public final void A14() throws IOException {
        if (!A18()) {
            StringBuilder sb = new StringBuilder(" in ");
            sb.append(((AbstractC01190Sv) this).A00);
            A0y(sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    @Override // X.AbstractC01190Sv, X.AbstractC03700oK, X.AbstractC02280iQ
    public final C03690oJ version() {
        return PackageVersion.VERSION;
    }
}
