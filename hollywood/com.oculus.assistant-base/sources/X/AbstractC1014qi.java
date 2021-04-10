package X;

import com.oculus.aidl.OVRServiceInterface;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.qi  reason: case insensitive filesystem */
public abstract class AbstractC1014qi implements Closeable, Nc {
    public int A00;

    public NX A0o() {
        VD vd = (VD) this;
        if (!(vd instanceof AnonymousClass2J)) {
            return vd.A00.A0o();
        }
        AnonymousClass2J r3 = (AnonymousClass2J) vd;
        AbstractC1014qi qiVar = ((VD) r3).A00;
        while (true) {
            NX A0o = qiVar.A0o();
            if (A0o != null) {
                return A0o;
            }
            int i = r3.A00;
            AbstractC1014qi[] qiVarArr = r3.A01;
            if (i >= qiVarArr.length) {
                return null;
            }
            r3.A00 = i + 1;
            qiVar = qiVarArr[i];
            ((VD) r3).A00 = qiVar;
        }
    }

    public String A0p() {
        return ((VD) this).A00.A0p();
    }

    public abstract String A0q(String str);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        if (r3 < r4) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int A0N(int r7) {
        /*
        // Method dump skipped, instructions count: 150
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1014qi.A0N(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
        if (r3 < r4) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long A0Q(long r7) {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1014qi.A0Q(long):long");
    }

    public final byte A0E() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0E();
        }
        int A0J = A0J();
        if (A0J >= -128 && A0J <= 255) {
            return (byte) A0J;
        }
        throw new C1013qh(AnonymousClass08.A05("Numeric value (", A0p(), ") out of range of Java byte"), A0R());
    }

    public final double A0F() {
        double d;
        if (this instanceof VD) {
            return ((VD) this).A00.A0F();
        }
        if (this instanceof C0680fB) {
            return A0Y().doubleValue();
        }
        AnonymousClass2L r4 = (AnonymousClass2L) this;
        int i = r4.A06;
        if ((i & 8) == 0) {
            if (i == 0) {
                AnonymousClass2L.A03(r4, 8);
            }
            int i2 = r4.A06;
            if ((8 & i2) == 0) {
                if ((i2 & 16) != 0) {
                    d = r4.A0G.doubleValue();
                } else if ((i2 & 4) != 0) {
                    d = r4.A0H.doubleValue();
                } else if ((i2 & 2) != 0) {
                    d = (double) r4.A0B;
                } else if ((i2 & 1) != 0) {
                    d = (double) r4.A07;
                } else {
                    O1.A02();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                r4.A00 = d;
                r4.A06 |= 8;
            }
        }
        return r4.A00;
    }

    public final double A0G() {
        if (!(this instanceof VD)) {
            return A0H(0.0d);
        }
        return ((VD) this).A00.A0G();
    }

    public final double A0H(double d) {
        VG vg;
        NX nx;
        if (this instanceof VD) {
            return ((VD) this).A00.A0H(d);
        }
        if ((this instanceof VG) && (nx = (vg = (VG) this).A00) != null) {
            switch (Nd.A00[nx.ordinal()]) {
                case 5:
                case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                    return vg.A0F();
                case 6:
                    return 1.0d;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                case 8:
                    return 0.0d;
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    Object A0Z = vg.A0Z();
                    if (A0Z instanceof Number) {
                        return ((Number) A0Z).doubleValue();
                    }
                    break;
                case 10:
                    String A0p = vg.A0p();
                    if (A0p != null) {
                        String trim = A0p.trim();
                        if (trim.length() != 0) {
                            try {
                                return Nj.A00(trim);
                            } catch (NumberFormatException unused) {
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        return d;
    }

    public final float A0I() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0I();
        }
        if (!(this instanceof C0680fB)) {
            return (float) A0F();
        }
        return A0Y().floatValue();
    }

    public final int A0J() {
        Number A0Y;
        int intValue;
        if (this instanceof VD) {
            return ((VD) this).A00.A0J();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass2L r3 = (AnonymousClass2L) this;
            int i = r3.A06;
            if ((i & 1) == 0) {
                if (i == 0) {
                    AnonymousClass2L.A03(r3, 1);
                }
                int i2 = r3.A06;
                if ((1 & i2) == 0) {
                    if ((i2 & 2) != 0) {
                        long j = r3.A0B;
                        int i3 = (int) j;
                        if (((long) i3) != j) {
                            r3.A0v(AnonymousClass08.A05("Numeric value (", r3.A0p(), ") out of range of int"));
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                        r3.A07 = i3;
                    } else {
                        if ((i2 & 4) != 0) {
                            if (AnonymousClass2L.A0V.compareTo(r3.A0H) > 0 || AnonymousClass2L.A0T.compareTo(r3.A0H) < 0) {
                                AnonymousClass2L.A01(r3);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                            intValue = r3.A0H.intValue();
                        } else if ((i2 & 8) != 0) {
                            double d = r3.A00;
                            if (d < -2.147483648E9d || d > 2.147483647E9d) {
                                AnonymousClass2L.A01(r3);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                            intValue = (int) d;
                        } else if ((i2 & 16) == 0) {
                            O1.A02();
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        } else if (AnonymousClass2L.A0R.compareTo(r3.A0G) > 0 || AnonymousClass2L.A0P.compareTo(r3.A0G) < 0) {
                            AnonymousClass2L.A01(r3);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        } else {
                            intValue = r3.A0G.intValue();
                        }
                        r3.A07 = intValue;
                    }
                    r3.A06 |= 1;
                }
            }
            return r3.A07;
        }
        C0680fB fBVar = (C0680fB) this;
        if (((VG) fBVar).A00 == NX.VALUE_NUMBER_INT) {
            A0Y = (Number) fBVar.A04.A02[fBVar.A00];
        } else {
            A0Y = fBVar.A0Y();
        }
        return A0Y.intValue();
    }

    public final int A0K() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0K();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass7v r4 = (AnonymousClass7v) this;
            NX nx = ((VG) r4).A00;
            if (nx == null) {
                return 0;
            }
            int i = C0249Nn.A00[nx.ordinal()];
            if (i == 1) {
                return r4.A0E.A02.length();
            }
            if (i != 2) {
                if (!(i == 3 || i == 4)) {
                    return nx.asCharArray().length;
                }
            } else if (r4.A02) {
                r4.A02 = false;
                AnonymousClass7v.A0A(r4);
            }
            return r4.A0O.A04();
        }
        String A0p = A0p();
        if (A0p != null) {
            return A0p.length();
        }
        return 0;
    }

    public final int A0L() {
        int i;
        if (this instanceof VD) {
            return ((VD) this).A00.A0L();
        }
        if (this instanceof C0680fB) {
            return 0;
        }
        AnonymousClass7v r3 = (AnonymousClass7v) this;
        NX nx = ((VG) r3).A00;
        if (nx == null || (i = C0249Nn.A00[nx.ordinal()]) == 1) {
            return 0;
        }
        if (i != 2) {
            if (!(i == 3 || i == 4)) {
                return 0;
            }
        } else if (r3.A02) {
            r3.A02 = false;
            AnonymousClass7v.A0A(r3);
        }
        int i2 = r3.A0O.A02;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    public final int A0M() {
        if (!(this instanceof VD)) {
            return A0N(0);
        }
        return ((VD) this).A00.A0M();
    }

    public final long A0O() {
        long longValue;
        if (this instanceof VD) {
            return ((VD) this).A00.A0O();
        }
        if (this instanceof C0680fB) {
            return A0Y().longValue();
        }
        AnonymousClass2L r5 = (AnonymousClass2L) this;
        int i = r5.A06;
        if ((i & 2) == 0) {
            if (i == 0) {
                AnonymousClass2L.A03(r5, 2);
            }
            int i2 = r5.A06;
            if ((2 & i2) == 0) {
                if ((i2 & 1) != 0) {
                    longValue = (long) r5.A07;
                } else if ((i2 & 4) != 0) {
                    if (AnonymousClass2L.A0W.compareTo(r5.A0H) > 0 || AnonymousClass2L.A0U.compareTo(r5.A0H) < 0) {
                        AnonymousClass2L.A02(r5);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    longValue = r5.A0H.longValue();
                } else if ((i2 & 8) != 0) {
                    double d = r5.A00;
                    if (d < -9.223372036854776E18d || d > 9.223372036854776E18d) {
                        AnonymousClass2L.A02(r5);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    longValue = (long) d;
                } else if ((i2 & 16) == 0) {
                    O1.A02();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else if (AnonymousClass2L.A0S.compareTo(r5.A0G) > 0 || AnonymousClass2L.A0Q.compareTo(r5.A0G) < 0) {
                    AnonymousClass2L.A02(r5);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else {
                    longValue = r5.A0G.longValue();
                }
                r5.A0B = longValue;
                r5.A06 |= 2;
            }
        }
        return r5.A0B;
    }

    public final long A0P() {
        if (!(this instanceof VD)) {
            return A0Q(0);
        }
        return ((VD) this).A00.A0P();
    }

    public final NT A0R() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0R();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass2L r2 = (AnonymousClass2L) this;
            int i = r2.A04;
            return new NT(r2.A0N.A04, -1, (r2.A0A + ((long) i)) - 1, r2.A01, (i - r2.A02) + 1);
        }
        NT nt = ((C0680fB) this).A01;
        if (nt == null) {
            return NT.A01;
        }
        return nt;
    }

    public final NT A0S() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0S();
        }
        if (this instanceof C0680fB) {
            return A0R();
        }
        AnonymousClass2L r1 = (AnonymousClass2L) this;
        Object obj = r1.A0N.A04;
        long j = r1.A0C;
        int i = r1.A09;
        int i2 = r1.A08;
        if (i2 >= 0) {
            i2++;
        }
        return new NT(obj, -1, j, i, i2);
    }

    public final AbstractC1014qi A0T() {
        if (!(this instanceof VD)) {
            VG vg = (VG) this;
            NX nx = vg.A00;
            if (nx != NX.START_OBJECT && nx != NX.START_ARRAY) {
                return vg;
            }
            int i = 1;
            while (true) {
                NX A0o = vg.A0o();
                if (A0o == null) {
                    vg.A0r();
                    return vg;
                }
                int i2 = Nd.A00[A0o.ordinal()];
                if (i2 == 1 || i2 == 2) {
                    i++;
                } else if ((i2 == 3 || i2 == 4) && i - 1 == 0) {
                    return vg;
                }
            }
        } else {
            VD vd = (VD) this;
            vd.A00.A0T();
            return vd;
        }
    }

    public final NX A0U() {
        if (!(this instanceof VD)) {
            return ((VG) this).A00;
        }
        return ((VD) this).A00.A0U();
    }

    public final NX A0V() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0V();
        }
        NX A0o = A0o();
        if (A0o == NX.FIELD_NAME) {
            return A0o();
        }
        return A0o;
    }

    public final NY A0W() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0W();
        }
        if (!(this instanceof C0680fB)) {
            return ((AnonymousClass7v) this).A00;
        }
        return ((C0680fB) this).A02;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        if ((r1 & 2) != 0) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0052, code lost:
        if ((r2.A06 & 16) != 0) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Integer A0X() {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1014qi.A0X():java.lang.Integer");
    }

    public final Number A0Y() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0Y();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass2L r2 = (AnonymousClass2L) this;
            if (r2.A06 == 0) {
                AnonymousClass2L.A03(r2, 0);
            }
            if (((VG) r2).A00 == NX.VALUE_NUMBER_INT) {
                int i = r2.A06;
                if ((i & 1) != 0) {
                    return Integer.valueOf(r2.A07);
                }
                if ((i & 2) != 0) {
                    return Long.valueOf(r2.A0B);
                }
                if ((i & 4) != 0) {
                    return r2.A0H;
                }
            } else {
                int i2 = r2.A06;
                if ((i2 & 16) == 0) {
                    if ((i2 & 8) != 0) {
                        return Double.valueOf(r2.A00);
                    }
                    O1.A02();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            return r2.A0G;
        }
        C0680fB fBVar = (C0680fB) this;
        NX nx = ((VG) fBVar).A00;
        if (nx == null || !nx.isNumeric()) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(nx);
            sb.append(") not numeric, can not use numeric value accessors");
            throw new C1013qh(sb.toString(), fBVar.A0R());
        }
        QK qk = fBVar.A04;
        Object obj = qk.A02[fBVar.A00];
        if (obj instanceof Number) {
            return (Number) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.indexOf(46) >= 0) {
                return Double.valueOf(Double.parseDouble(str));
            }
            return Long.valueOf(Long.parseLong(str));
        } else if (obj == null) {
            return null;
        } else {
            throw new IllegalStateException(AnonymousClass08.A04("Internal error: entry should be a Number, but is of type ", obj.getClass().getName()));
        }
    }

    public final Object A0Z() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0Z();
        }
        if (!(this instanceof C0680fB)) {
            return null;
        }
        C0680fB fBVar = (C0680fB) this;
        if (((VG) fBVar).A00 != NX.VALUE_EMBEDDED_OBJECT) {
            return null;
        }
        QK qk = fBVar.A04;
        return qk.A02[fBVar.A00];
    }

    public final Object A0a() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0a();
        }
        if (!(this instanceof AnonymousClass7v)) {
            return null;
        }
        return ((AnonymousClass7v) this).A01;
    }

    public final String A0b() {
        C1016qk qkVar;
        if (this instanceof VD) {
            return ((VD) this).A00.A0b();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass2L r2 = (AnonymousClass2L) this;
            NX nx = ((VG) r2).A00;
            if (nx == NX.START_OBJECT || nx == NX.START_ARRAY) {
                qkVar = r2.A0E.A04;
            } else {
                qkVar = r2.A0E;
            }
        } else {
            qkVar = ((C0680fB) this).A03;
        }
        return qkVar.A02;
    }

    public final BigDecimal A0c() {
        long j;
        BigDecimal valueOf;
        if (this instanceof VD) {
            return ((VD) this).A00.A0c();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass2L r2 = (AnonymousClass2L) this;
            int i = r2.A06;
            if ((i & 16) == 0) {
                if (i == 0) {
                    AnonymousClass2L.A03(r2, 16);
                }
                int i2 = r2.A06;
                if ((16 & i2) == 0) {
                    if ((i2 & 8) != 0) {
                        valueOf = new BigDecimal(r2.A0p());
                    } else if ((i2 & 4) != 0) {
                        valueOf = new BigDecimal(r2.A0H);
                    } else {
                        if ((i2 & 2) != 0) {
                            j = r2.A0B;
                        } else if ((i2 & 1) != 0) {
                            j = (long) r2.A07;
                        } else {
                            O1.A02();
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                        valueOf = BigDecimal.valueOf(j);
                    }
                    r2.A0G = valueOf;
                    r2.A06 |= 16;
                }
            }
            return r2.A0G;
        }
        Number A0Y = A0Y();
        if (A0Y instanceof BigDecimal) {
            return (BigDecimal) A0Y;
        }
        switch (A0X().intValue()) {
            case 0:
            case 1:
                return BigDecimal.valueOf(A0Y.longValue());
            case 2:
                return new BigDecimal((BigInteger) A0Y);
            default:
                return BigDecimal.valueOf(A0Y.doubleValue());
        }
    }

    public final BigInteger A0d() {
        BigInteger bigInteger;
        if (this instanceof VD) {
            return ((VD) this).A00.A0d();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass2L r2 = (AnonymousClass2L) this;
            int i = r2.A06;
            if ((i & 4) == 0) {
                if (i == 0) {
                    AnonymousClass2L.A03(r2, 4);
                }
                int i2 = r2.A06;
                if ((4 & i2) == 0) {
                    if ((i2 & 16) != 0) {
                        bigInteger = r2.A0G.toBigInteger();
                    } else if ((i2 & 2) != 0) {
                        bigInteger = BigInteger.valueOf(r2.A0B);
                    } else if ((i2 & 1) != 0) {
                        bigInteger = BigInteger.valueOf((long) r2.A07);
                    } else if ((i2 & 8) != 0) {
                        bigInteger = BigDecimal.valueOf(r2.A00).toBigInteger();
                    } else {
                        O1.A02();
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                    r2.A0H = bigInteger;
                    r2.A06 |= 4;
                }
            }
            return r2.A0H;
        }
        Number A0Y = A0Y();
        if (A0Y instanceof BigInteger) {
            return (BigInteger) A0Y;
        }
        if (A0X() == AnonymousClass09.A0L) {
            return ((BigDecimal) A0Y).toBigInteger();
        }
        return BigInteger.valueOf(A0Y.longValue());
    }

    public final short A0e() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0e();
        }
        int A0J = A0J();
        if (A0J >= -32768 && A0J <= 32767) {
            return (short) A0J;
        }
        throw new C1013qh(AnonymousClass08.A05("Numeric value (", A0p(), ") out of range of Java short"), A0R());
    }

    public final boolean A0f() {
        if (!(this instanceof VD)) {
            return A0k(false);
        }
        return ((VD) this).A00.A0f();
    }

    public final boolean A0g() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0g();
        }
        if (((VG) this).A00 != null) {
            return true;
        }
        return false;
    }

    public final boolean A0h() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0h();
        }
        if (this instanceof C0680fB) {
            return false;
        }
        AnonymousClass2L r2 = (AnonymousClass2L) this;
        NX nx = ((VG) r2).A00;
        if (nx == NX.VALUE_STRING) {
            return true;
        }
        if (nx == NX.FIELD_NAME) {
            return r2.A0I;
        }
        return false;
    }

    public final boolean A0j(NU nu) {
        if (this instanceof VD) {
            return ((VD) this).A00.A0j(nu);
        }
        if ((nu.getMask() & this.A00) != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0k(boolean r4) {
        /*
            r3 = this;
            boolean r0 = r3 instanceof X.VD
            if (r0 != 0) goto L_0x0045
            boolean r0 = r3 instanceof X.VG
            if (r0 == 0) goto L_0x001b
            r2 = r3
            X.VG r2 = (X.VG) r2
            X.NX r0 = r2.A00
            if (r0 == 0) goto L_0x001b
            int[] r1 = X.Nd.A00
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            switch(r0) {
                case 5: goto L_0x003d;
                case 6: goto L_0x003b;
                case 7: goto L_0x0044;
                case 8: goto L_0x0044;
                case 9: goto L_0x001c;
                case 10: goto L_0x002b;
                default: goto L_0x001b;
            }
        L_0x001b:
            return r4
        L_0x001c:
            java.lang.Object r1 = r2.A0Z()
            boolean r0 = r1 instanceof java.lang.Boolean
            if (r0 == 0) goto L_0x002b
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r4 = r1.booleanValue()
            return r4
        L_0x002b:
            java.lang.String r0 = r2.A0p()
            java.lang.String r1 = r0.trim()
            java.lang.String r0 = "true"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x001b
        L_0x003b:
            r4 = 1
            return r4
        L_0x003d:
            int r0 = r2.A0J()
            if (r0 == 0) goto L_0x0044
            r1 = 1
        L_0x0044:
            return r1
        L_0x0045:
            r0 = r3
            X.VD r0 = (X.VD) r0
            X.qi r0 = r0.A00
            boolean r0 = r0.A0k(r4)
            return r0
            switch-data {5->0x003d, 6->0x003b, 7->0x0044, 8->0x0044, 9->0x001c, 10->0x002b, }
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1014qi.A0k(boolean):boolean");
    }

    public final byte[] A0l(NP np) {
        byte[] bArr;
        if (this instanceof VD) {
            return ((VD) this).A00.A0l(np);
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass7v r3 = (AnonymousClass7v) this;
            NX nx = ((VG) r3).A00;
            if (nx == NX.VALUE_STRING || (nx == NX.VALUE_EMBEDDED_OBJECT && r3.A0K != null)) {
                if (r3.A02) {
                    try {
                        C0259Nx nx2 = r3.A0F;
                        if (nx2 == null) {
                            r3.A0F = new C0259Nx();
                        } else {
                            nx2.A01();
                        }
                        C0259Nx nx3 = r3.A0F;
                        while (true) {
                            if (((AnonymousClass2L) r3).A04 >= ((AnonymousClass2L) r3).A03) {
                                r3.A13();
                            }
                            char[] cArr = r3.A03;
                            int i = ((AnonymousClass2L) r3).A04;
                            ((AnonymousClass2L) r3).A04 = i + 1;
                            char c = cArr[i];
                            if (c > ' ') {
                                int A01 = np.A01(c);
                                if (A01 < 0) {
                                    if (c == '\"') {
                                        bArr = nx3.A05();
                                        break;
                                    }
                                    A01 = r3.A0z(np, c, 0);
                                    if (A01 < 0) {
                                    }
                                }
                                if (((AnonymousClass2L) r3).A04 >= ((AnonymousClass2L) r3).A03) {
                                    r3.A13();
                                }
                                char[] cArr2 = r3.A03;
                                int i2 = ((AnonymousClass2L) r3).A04;
                                ((AnonymousClass2L) r3).A04 = i2 + 1;
                                char c2 = cArr2[i2];
                                int A012 = np.A01(c2);
                                if (A012 < 0) {
                                    A012 = r3.A0z(np, c2, 1);
                                }
                                int i3 = (A01 << 6) | A012;
                                if (((AnonymousClass2L) r3).A04 >= ((AnonymousClass2L) r3).A03) {
                                    r3.A13();
                                }
                                char[] cArr3 = r3.A03;
                                int i4 = ((AnonymousClass2L) r3).A04;
                                ((AnonymousClass2L) r3).A04 = i4 + 1;
                                char c3 = cArr3[i4];
                                int A013 = np.A01(c3);
                                if (A013 < 0) {
                                    if (A013 != -2) {
                                        if (c3 == '\"' && !np.A02) {
                                            nx3.A02(i3 >> 4);
                                            bArr = nx3.A05();
                                            break;
                                        }
                                        A013 = r3.A0z(np, c3, 2);
                                    }
                                    if (A013 == -2) {
                                        if (((AnonymousClass2L) r3).A04 >= ((AnonymousClass2L) r3).A03) {
                                            r3.A13();
                                        }
                                        char[] cArr4 = r3.A03;
                                        int i5 = ((AnonymousClass2L) r3).A04;
                                        ((AnonymousClass2L) r3).A04 = i5 + 1;
                                        char c4 = cArr4[i5];
                                        char c5 = np.A00;
                                        boolean z = false;
                                        if (c4 == c5) {
                                            z = true;
                                        }
                                        if (z) {
                                            nx3.A02(i3 >> 4);
                                        } else {
                                            StringBuilder sb = new StringBuilder("expected padding character '");
                                            sb.append(c5);
                                            sb.append("'");
                                            throw AnonymousClass2L.A00(np, c4, 3, sb.toString());
                                        }
                                    }
                                }
                                int i6 = (i3 << 6) | A013;
                                if (((AnonymousClass2L) r3).A04 >= ((AnonymousClass2L) r3).A03) {
                                    r3.A13();
                                }
                                char[] cArr5 = r3.A03;
                                int i7 = ((AnonymousClass2L) r3).A04;
                                ((AnonymousClass2L) r3).A04 = i7 + 1;
                                char c6 = cArr5[i7];
                                int A014 = np.A01(c6);
                                if (A014 < 0) {
                                    if (A014 != -2) {
                                        if (c6 == '\"' && !np.A02) {
                                            nx3.A04(i6 >> 2);
                                            bArr = nx3.A05();
                                            break;
                                        }
                                        A014 = r3.A0z(np, c6, 3);
                                    }
                                    if (A014 == -2) {
                                        nx3.A04(i6 >> 2);
                                    }
                                }
                                nx3.A03((i6 << 6) | A014);
                            }
                        }
                        r3.A0K = bArr;
                        r3.A02 = false;
                    } catch (IllegalArgumentException e) {
                        StringBuilder sb2 = new StringBuilder("Failed to decode VALUE_STRING as base64 (");
                        sb2.append(np);
                        sb2.append("): ");
                        sb2.append(e.getMessage());
                        throw new C1013qh(sb2.toString(), r3.A0R());
                    }
                } else {
                    bArr = r3.A0K;
                    if (bArr == null) {
                        C0259Nx nx4 = r3.A0F;
                        if (nx4 == null) {
                            r3.A0F = new C0259Nx();
                        } else {
                            nx4.A01();
                        }
                        C0259Nx nx5 = r3.A0F;
                        r3.A0x(r3.A0p(), nx5, np);
                        byte[] A05 = nx5.A05();
                        r3.A0K = A05;
                        return A05;
                    }
                }
                return bArr;
            }
            StringBuilder sb3 = new StringBuilder("Current token (");
            sb3.append(nx);
            sb3.append(") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
            r3.A0v(sb3.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        C0680fB fBVar = (C0680fB) this;
        NX nx6 = ((VG) fBVar).A00;
        if (nx6 == NX.VALUE_EMBEDDED_OBJECT) {
            QK qk = fBVar.A04;
            Object obj = qk.A02[fBVar.A00];
            if (obj instanceof byte[]) {
                return (byte[]) obj;
            }
        }
        if (nx6 == NX.VALUE_STRING) {
            String A0p = fBVar.A0p();
            if (A0p == null) {
                return null;
            }
            C0259Nx nx7 = fBVar.A06;
            if (nx7 == null) {
                nx7 = new C0259Nx(100);
                fBVar.A06 = nx7;
            } else {
                nx7.A01();
            }
            fBVar.A0x(A0p, nx7, np);
            return nx7.A05();
        }
        StringBuilder sb4 = new StringBuilder("Current token (");
        sb4.append(nx6);
        sb4.append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
        throw new C1013qh(sb4.toString(), fBVar.A0R());
    }

    public final char[] A0m() {
        if (this instanceof VD) {
            return ((VD) this).A00.A0m();
        }
        if (!(this instanceof C0680fB)) {
            AnonymousClass7v r5 = (AnonymousClass7v) this;
            NX nx = ((VG) r5).A00;
            if (nx == null) {
                return null;
            }
            int i = C0249Nn.A00[nx.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (!(i == 3 || i == 4)) {
                        return nx.asCharArray();
                    }
                } else if (r5.A02) {
                    r5.A02 = false;
                    AnonymousClass7v.A0A(r5);
                }
                return r5.A0O.A0E();
            }
            if (!r5.A0I) {
                String str = r5.A0E.A02;
                int length = str.length();
                char[] cArr = r5.A0L;
                if (cArr == null) {
                    C0247Ng ng = r5.A0N;
                    if (ng.A01 == null) {
                        cArr = ng.A03.A00(AnonymousClass09.A0J, length);
                        ng.A01 = cArr;
                    } else {
                        throw new IllegalStateException("Trying to call same allocXxx() method second time");
                    }
                } else {
                    if (cArr.length < length) {
                        cArr = new char[length];
                    }
                    str.getChars(0, length, cArr, 0);
                    r5.A0I = true;
                }
                r5.A0L = cArr;
                str.getChars(0, length, cArr, 0);
                r5.A0I = true;
            }
            return r5.A0L;
        }
        String A0p = A0p();
        if (A0p != null) {
            return A0p.toCharArray();
        }
        return null;
    }

    public String A0n() {
        if (!(this instanceof VD)) {
            return A0q(null);
        }
        return ((VD) this).A00.A0n();
    }

    public final boolean A0i() {
        if (A0U() == NX.START_ARRAY) {
            return true;
        }
        return false;
    }

    public AbstractC1014qi() {
    }

    public AbstractC1014qi(int i) {
        this.A00 = 0;
    }
}
