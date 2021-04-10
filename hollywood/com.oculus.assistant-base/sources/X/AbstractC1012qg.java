package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.qg  reason: case insensitive filesystem */
public abstract class AbstractC1012qg implements Closeable, Nc, Flushable {
    public NZ A00;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public final void A08() {
        if (!(this instanceof JD)) {
            AnonymousClass7s r3 = (AnonymousClass7s) this;
            C1017ql qlVar = ((VH) r3).A01;
            if (((NW) qlVar).A01 == 1) {
                NZ nz = ((AbstractC1012qg) r3).A00;
                if (nz != null) {
                    nz.A5n(r3, ((NW) qlVar).A00 + 1);
                } else {
                    if (r3.A02 >= r3.A00) {
                        AnonymousClass7s.A03(r3);
                    }
                    char[] cArr = r3.A03;
                    int i = r3.A02;
                    r3.A02 = i + 1;
                    cArr[i] = ']';
                }
                ((VH) r3).A01 = ((VH) r3).A01.A02;
                return;
            }
            throw new C1011qf(AnonymousClass08.A04("Current context not an ARRAY but ", qlVar.A00()));
        }
        JD jd = (JD) this;
        JD.A01(jd, NX.END_ARRAY);
        C1017ql qlVar2 = jd.A01.A02;
        if (qlVar2 != null) {
            jd.A01 = qlVar2;
        }
    }

    public final void A09() {
        if (!(this instanceof JD)) {
            AnonymousClass7s r3 = (AnonymousClass7s) this;
            C1017ql qlVar = ((VH) r3).A01;
            if (((NW) qlVar).A01 == 2) {
                NZ nz = ((AbstractC1012qg) r3).A00;
                if (nz != null) {
                    nz.A5o(r3, ((NW) qlVar).A00 + 1);
                } else {
                    if (r3.A02 >= r3.A00) {
                        AnonymousClass7s.A03(r3);
                    }
                    char[] cArr = r3.A03;
                    int i = r3.A02;
                    r3.A02 = i + 1;
                    cArr[i] = '}';
                }
                ((VH) r3).A01 = ((VH) r3).A01.A02;
                return;
            }
            throw new C1011qf(AnonymousClass08.A04("Current context not an object but ", qlVar.A00()));
        }
        JD jd = (JD) this;
        JD.A01(jd, NX.END_OBJECT);
        C1017ql qlVar2 = jd.A01.A02;
        if (qlVar2 != null) {
            jd.A01 = qlVar2;
        }
    }

    public final void A0A() {
        if (!(this instanceof JD)) {
            AnonymousClass7s r1 = (AnonymousClass7s) this;
            r1.A0W("write null value");
            AnonymousClass7s.A02(r1);
            return;
        }
        JD.A01((JD) this, NX.VALUE_NULL);
    }

    public final void A0B() {
        if (!(this instanceof JD)) {
            AnonymousClass7s r3 = (AnonymousClass7s) this;
            r3.A0W("start an array");
            C1017ql qlVar = ((VH) r3).A01;
            C1017ql qlVar2 = qlVar.A00;
            if (qlVar2 == null) {
                qlVar2 = new C1017ql(1, qlVar);
                qlVar.A00 = qlVar2;
            } else {
                ((NW) qlVar2).A01 = 1;
                ((NW) qlVar2).A00 = -1;
                qlVar2.A01 = null;
            }
            ((VH) r3).A01 = qlVar2;
            NZ nz = ((AbstractC1012qg) r3).A00;
            if (nz != null) {
                nz.A5x(r3);
                return;
            }
            if (r3.A02 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            char[] cArr = r3.A03;
            int i = r3.A02;
            r3.A02 = i + 1;
            cArr[i] = '[';
            return;
        }
        JD jd = (JD) this;
        JD.A01(jd, NX.START_ARRAY);
        C1017ql qlVar3 = jd.A01;
        C1017ql qlVar4 = qlVar3.A00;
        if (qlVar4 == null) {
            qlVar4 = new C1017ql(1, qlVar3);
            qlVar3.A00 = qlVar4;
        } else {
            ((NW) qlVar4).A01 = 1;
            ((NW) qlVar4).A00 = -1;
            qlVar4.A01 = null;
        }
        jd.A01 = qlVar4;
    }

    public final void A0C() {
        if (!(this instanceof JD)) {
            AnonymousClass7s r3 = (AnonymousClass7s) this;
            r3.A0W("start an object");
            C1017ql qlVar = ((VH) r3).A01;
            C1017ql qlVar2 = qlVar.A00;
            if (qlVar2 == null) {
                qlVar2 = new C1017ql(2, qlVar);
                qlVar.A00 = qlVar2;
            } else {
                ((NW) qlVar2).A01 = 2;
                ((NW) qlVar2).A00 = -1;
                qlVar2.A01 = null;
            }
            ((VH) r3).A01 = qlVar2;
            NZ nz = ((AbstractC1012qg) r3).A00;
            if (nz != null) {
                nz.A5y(r3);
                return;
            }
            if (r3.A02 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            char[] cArr = r3.A03;
            int i = r3.A02;
            r3.A02 = i + 1;
            cArr[i] = '{';
            return;
        }
        JD jd = (JD) this;
        JD.A01(jd, NX.START_OBJECT);
        C1017ql qlVar3 = jd.A01;
        C1017ql qlVar4 = qlVar3.A00;
        if (qlVar4 == null) {
            qlVar4 = new C1017ql(2, qlVar3);
            qlVar3.A00 = qlVar4;
        } else {
            ((NW) qlVar4).A01 = 2;
            ((NW) qlVar4).A00 = -1;
            qlVar4.A01 = null;
        }
        jd.A01 = qlVar4;
    }

    public final void A0D(char c) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r3 = (AnonymousClass7s) this;
            if (r3.A02 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            char[] cArr = r3.A03;
            int i = r3.A02;
            r3.A02 = i + 1;
            cArr[i] = c;
            return;
        }
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    public final void A0E(double d) {
        if (!(this instanceof JD)) {
            VH vh = (VH) this;
            if (vh.A02 || ((Double.isNaN(d) || Double.isInfinite(d)) && vh.A0X(NS.QUOTE_NON_NUMERIC_NUMBERS))) {
                vh.A0P(String.valueOf(d));
                return;
            }
            vh.A0W("write number");
            vh.A0O(String.valueOf(d));
            return;
        }
        JD.A02((JD) this, NX.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    public final void A0F(float f) {
        if (!(this instanceof JD)) {
            VH vh = (VH) this;
            if (vh.A02 || ((Float.isNaN(f) || Float.isInfinite(f)) && vh.A0X(NS.QUOTE_NON_NUMERIC_NUMBERS))) {
                vh.A0P(String.valueOf(f));
                return;
            }
            vh.A0W("write number");
            vh.A0O(String.valueOf(f));
            return;
        }
        JD.A02((JD) this, NX.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    public final void A0G(int i) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r4 = (AnonymousClass7s) this;
            r4.A0W("write number");
            if (((VH) r4).A02) {
                if (r4.A02 + 13 >= r4.A00) {
                    AnonymousClass7s.A03(r4);
                }
                char[] cArr = r4.A03;
                int i2 = r4.A02;
                int i3 = i2 + 1;
                r4.A02 = i3;
                cArr[i2] = '\"';
                int A002 = Nk.A00(i, cArr, i3);
                r4.A02 = A002;
                char[] cArr2 = r4.A03;
                r4.A02 = A002 + 1;
                cArr2[A002] = '\"';
                return;
            }
            if (r4.A02 + 11 >= r4.A00) {
                AnonymousClass7s.A03(r4);
            }
            r4.A02 = Nk.A00(i, r4.A03, r4.A02);
            return;
        }
        JD.A02((JD) this, NX.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    public final void A0H(long j) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r4 = (AnonymousClass7s) this;
            r4.A0W("write number");
            if (((VH) r4).A02) {
                if (r4.A02 + 23 >= r4.A00) {
                    AnonymousClass7s.A03(r4);
                }
                char[] cArr = r4.A03;
                int i = r4.A02;
                int i2 = i + 1;
                r4.A02 = i2;
                cArr[i] = '\"';
                int A03 = Nk.A03(j, cArr, i2);
                r4.A02 = A03;
                char[] cArr2 = r4.A03;
                r4.A02 = A03 + 1;
                cArr2[A03] = '\"';
                return;
            }
            if (r4.A02 + 21 >= r4.A00) {
                AnonymousClass7s.A03(r4);
            }
            r4.A02 = Nk.A03(j, r4.A03, r4.A02);
            return;
        }
        JD.A02((JD) this, NX.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    public final void A0I(NP np, byte[] bArr, int i, int i2) {
        char c;
        int i3 = i;
        if (!(this instanceof JD)) {
            AnonymousClass7s r3 = (AnonymousClass7s) this;
            r3.A0W("write binary value");
            if (r3.A02 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            char[] cArr = r3.A03;
            int i4 = r3.A02;
            int i5 = i4 + 1;
            r3.A02 = i5;
            cArr[i4] = '\"';
            int i6 = i2 + i;
            int i7 = i6 - 3;
            int i8 = r3.A00 - 6;
            int i9 = np.A01 >> 2;
            while (i3 <= i7) {
                if (i5 > i8) {
                    AnonymousClass7s.A03(r3);
                }
                int i10 = i3 + 1;
                int i11 = i10 + 1;
                int i12 = ((bArr[i3] << 8) | (bArr[i10] & 255)) << 8;
                i3 = i11 + 1;
                int i13 = i12 | (bArr[i11] & 255);
                char[] cArr2 = r3.A03;
                int i14 = r3.A02;
                int i15 = i14 + 1;
                char[] cArr3 = np.A03;
                cArr2[i14] = cArr3[(i13 >> 18) & 63];
                int i16 = i15 + 1;
                cArr2[i15] = cArr3[(i13 >> 12) & 63];
                int i17 = i16 + 1;
                cArr2[i16] = cArr3[(i13 >> 6) & 63];
                i5 = i17 + 1;
                cArr2[i17] = cArr3[i13 & 63];
                r3.A02 = i5;
                i9--;
                if (i9 <= 0) {
                    int i18 = i5 + 1;
                    r3.A02 = i18;
                    cArr2[i5] = '\\';
                    i5 = i18 + 1;
                    r3.A02 = i5;
                    cArr2[i18] = 'n';
                    i9 = i9;
                }
            }
            int i19 = i6 - i3;
            if (i19 > 0) {
                if (i5 > i8) {
                    AnonymousClass7s.A03(r3);
                }
                int i20 = i3 + 1;
                int i21 = bArr[i3] << 16;
                if (i19 == 2) {
                    i21 |= (bArr[i20] & 255) << 8;
                }
                char[] cArr4 = r3.A03;
                int i22 = r3.A02;
                int i23 = i22 + 1;
                char[] cArr5 = np.A03;
                cArr4[i22] = cArr5[(i21 >> 18) & 63];
                i5 = i23 + 1;
                cArr4[i23] = cArr5[(i21 >> 12) & 63];
                if (np.A02) {
                    int i24 = i5 + 1;
                    if (i19 == 2) {
                        c = cArr5[(i21 >> 6) & 63];
                    } else {
                        c = np.A00;
                    }
                    cArr4[i5] = c;
                    i5 = i24 + 1;
                    cArr4[i24] = np.A00;
                } else if (i19 == 2) {
                    cArr4[i5] = cArr5[(i21 >> 6) & 63];
                    i5++;
                }
                r3.A02 = i5;
            }
            if (i5 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            char[] cArr6 = r3.A03;
            int i25 = r3.A02;
            r3.A02 = i25 + 1;
            cArr6[i25] = '\"';
            return;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i3, bArr2, 0, i2);
        A0L(bArr2);
    }

    public final void A0J(Na na) {
        if (!(this instanceof JD)) {
            VH vh = (VH) this;
            if (!(vh instanceof AnonymousClass7s)) {
                vh.A0M(na.getValue());
                return;
            }
            AnonymousClass7s r5 = (AnonymousClass7s) vh;
            int A01 = ((VH) r5).A01.A01(na.getValue());
            if (A01 == 4) {
                throw new C1011qf("Can not write a field name, expecting a value");
            }
            boolean z = true;
            if (A01 != 1) {
                z = false;
            }
            NZ nz = ((AbstractC1012qg) r5).A00;
            if (nz != null) {
                if (z) {
                    nz.A5s(r5);
                } else {
                    nz.A1L(r5);
                }
                char[] A1H = na.A1H();
                if (r5.A0X(NS.QUOTE_FIELD_NAMES)) {
                    if (r5.A02 >= r5.A00) {
                        AnonymousClass7s.A03(r5);
                    }
                    char[] cArr = r5.A03;
                    int i = r5.A02;
                    r5.A02 = i + 1;
                    cArr[i] = '\"';
                    r5.A0U(A1H, 0, A1H.length);
                    if (r5.A02 >= r5.A00) {
                        AnonymousClass7s.A03(r5);
                    }
                    char[] cArr2 = r5.A03;
                    int i2 = r5.A02;
                    r5.A02 = i2 + 1;
                    cArr2[i2] = '\"';
                    return;
                }
                r5.A0U(A1H, 0, A1H.length);
                return;
            }
            if (r5.A02 + 1 >= r5.A00) {
                AnonymousClass7s.A03(r5);
            }
            if (z) {
                char[] cArr3 = r5.A03;
                int i3 = r5.A02;
                r5.A02 = i3 + 1;
                cArr3[i3] = ',';
            }
            char[] A1H2 = na.A1H();
            if (!r5.A0X(NS.QUOTE_FIELD_NAMES)) {
                r5.A0U(A1H2, 0, A1H2.length);
                return;
            }
            char[] cArr4 = r5.A03;
            int i4 = r5.A02;
            int i5 = i4 + 1;
            r5.A02 = i5;
            cArr4[i4] = '\"';
            int length = A1H2.length;
            if (i5 + length + 1 >= r5.A00) {
                r5.A0U(A1H2, 0, length);
                if (r5.A02 >= r5.A00) {
                    AnonymousClass7s.A03(r5);
                }
                char[] cArr5 = r5.A03;
                int i6 = r5.A02;
                r5.A02 = i6 + 1;
                cArr5[i6] = '\"';
                return;
            }
            System.arraycopy(A1H2, 0, cArr4, i5, length);
            int i7 = r5.A02 + length;
            r5.A02 = i7;
            char[] cArr6 = r5.A03;
            r5.A02 = i7 + 1;
            cArr6[i7] = '\"';
            return;
        }
        JD jd = (JD) this;
        JD.A02(jd, NX.FIELD_NAME, na);
        jd.A01.A01(na.getValue());
    }

    public final void A0K(Na na) {
        if (!(this instanceof JD)) {
            VH vh = (VH) this;
            if (!(vh instanceof AnonymousClass7s)) {
                vh.A0P(na.getValue());
                return;
            }
            AnonymousClass7s r3 = (AnonymousClass7s) vh;
            r3.A0W("write text value");
            if (r3.A02 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            char[] cArr = r3.A03;
            int i = r3.A02;
            r3.A02 = i + 1;
            cArr[i] = '\"';
            char[] A1H = na.A1H();
            int length = A1H.length;
            if (length < 32) {
                if (length > r3.A00 - r3.A02) {
                    AnonymousClass7s.A03(r3);
                }
                System.arraycopy(A1H, 0, r3.A03, r3.A02, length);
                r3.A02 += length;
            } else {
                AnonymousClass7s.A03(r3);
                r3.A05.write(A1H, 0, length);
            }
            if (r3.A02 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            char[] cArr2 = r3.A03;
            int i2 = r3.A02;
            r3.A02 = i2 + 1;
            cArr2[i2] = '\"';
            return;
        }
        JD jd = (JD) this;
        if (na == null) {
            jd.A0A();
        } else {
            JD.A02(jd, NX.VALUE_STRING, na);
        }
    }

    public final void A0L(Object obj) {
        boolean z;
        long j;
        int i;
        short byteValue;
        if (!(this instanceof JD)) {
            VH vh = (VH) this;
            if (obj == null) {
                vh.A0A();
                return;
            }
            NY ny = vh.A00;
            if (ny != null) {
                C1028qz qzVar = (C1028qz) ny;
                AnonymousClass2H r4 = qzVar._serializationConfig;
                if (r4.A06(EnumC1030r1.INDENT_OUTPUT) && ((AbstractC1012qg) vh).A00 == null) {
                    ((AbstractC1012qg) vh).A00 = new C1019qo();
                }
                if (!r4.A06(EnumC1030r1.CLOSE_CLOSEABLE) || !(obj instanceof Closeable)) {
                    qzVar._serializerProvider.A0G(r4, qzVar._serializerFactory).A0H(vh, obj);
                    if (r4.A06(EnumC1030r1.FLUSH_AFTER_WRITE_VALUE)) {
                        vh.flush();
                        return;
                    }
                    return;
                }
                Closeable closeable = (Closeable) obj;
                try {
                    qzVar._serializerProvider.A0G(r4, qzVar._serializerFactory).A0H(vh, obj);
                    if (r4.A06(EnumC1030r1.FLUSH_AFTER_WRITE_VALUE)) {
                        vh.flush();
                    }
                    closeable.close();
                } catch (Throwable th) {
                    if (closeable != null) {
                        try {
                            closeable.close();
                            throw th;
                        } catch (IOException unused) {
                            throw th;
                        }
                    } else {
                        throw th;
                    }
                }
            } else if (obj instanceof String) {
                vh.A0P((String) obj);
            } else {
                if (obj instanceof Number) {
                    Number number = (Number) obj;
                    if (number instanceof Integer) {
                        i = number.intValue();
                    } else {
                        if (number instanceof Long) {
                            j = number.longValue();
                        } else if (number instanceof Double) {
                            vh.A0E(number.doubleValue());
                            return;
                        } else if (number instanceof Float) {
                            vh.A0F(number.floatValue());
                            return;
                        } else {
                            if (number instanceof Short) {
                                byteValue = number.shortValue();
                            } else if (number instanceof Byte) {
                                byteValue = (short) number.byteValue();
                            } else if (number instanceof BigInteger) {
                                vh.A0R((BigInteger) number);
                                return;
                            } else if (number instanceof BigDecimal) {
                                vh.A0Q((BigDecimal) number);
                                return;
                            } else if (number instanceof AtomicInteger) {
                                i = ((AtomicInteger) number).get();
                            } else if (number instanceof AtomicLong) {
                                j = ((AtomicLong) number).get();
                            }
                            vh.A0S(byteValue);
                            return;
                        }
                        vh.A0H(j);
                        return;
                    }
                    vh.A0G(i);
                    return;
                } else if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    vh.A0I(NQ.A01, bArr, 0, bArr.length);
                    return;
                } else {
                    if (obj instanceof Boolean) {
                        z = ((Boolean) obj).booleanValue();
                    } else if (obj instanceof AtomicBoolean) {
                        z = ((AtomicBoolean) obj).get();
                    }
                    vh.A0T(z);
                    return;
                }
                throw new IllegalStateException(AnonymousClass08.A05("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed ", obj.getClass().getName(), ")"));
            }
        } else {
            JD.A02((JD) this, NX.VALUE_EMBEDDED_OBJECT, obj);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007c, code lost:
        if (r4.A0X(X.NS.QUOTE_FIELD_NAMES) == false) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0M(java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1012qg.A0M(java.lang.String):void");
    }

    public final void A0N(String str) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r1 = (AnonymousClass7s) this;
            r1.A0W("write number");
            if (((VH) r1).A02) {
                AnonymousClass7s.A05(r1, str);
            } else {
                r1.A0O(str);
            }
        } else {
            JD.A02((JD) this, NX.VALUE_NUMBER_FLOAT, str);
        }
    }

    public final void A0O(String str) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r5 = (AnonymousClass7s) this;
            int length = str.length();
            int i = r5.A00;
            int i2 = r5.A02;
            int i3 = i - i2;
            if (i3 == 0) {
                AnonymousClass7s.A03(r5);
                int i4 = r5.A00;
                i2 = r5.A02;
                i3 = i4 - i2;
            }
            if (i3 >= length) {
                str.getChars(0, length, r5.A03, i2);
                r5.A02 += length;
                return;
            }
            str.getChars(0, i3, r5.A03, i2);
            r5.A02 += i3;
            AnonymousClass7s.A03(r5);
            int i5 = length - i3;
            while (true) {
                int i6 = r5.A00;
                if (i5 > i6) {
                    int i7 = i3 + i6;
                    str.getChars(i3, i7, r5.A03, 0);
                    r5.A01 = 0;
                    r5.A02 = i6;
                    AnonymousClass7s.A03(r5);
                    i5 -= i6;
                    i3 = i7;
                } else {
                    str.getChars(i3, i3 + i5, r5.A03, 0);
                    r5.A01 = 0;
                    r5.A02 = i5;
                    return;
                }
            }
        } else {
            throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
        }
    }

    public final void A0P(String str) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r4 = (AnonymousClass7s) this;
            r4.A0W("write text value");
            if (str == null) {
                AnonymousClass7s.A02(r4);
                return;
            }
            if (r4.A02 >= r4.A00) {
                AnonymousClass7s.A03(r4);
            }
            char[] cArr = r4.A03;
            int i = r4.A02;
            r4.A02 = i + 1;
            cArr[i] = '\"';
            AnonymousClass7s.A06(r4, str);
            if (r4.A02 >= r4.A00) {
                AnonymousClass7s.A03(r4);
            }
            char[] cArr2 = r4.A03;
            int i2 = r4.A02;
            r4.A02 = i2 + 1;
            cArr2[i2] = '\"';
            return;
        }
        JD jd = (JD) this;
        if (str == null) {
            jd.A0A();
        } else {
            JD.A02(jd, NX.VALUE_STRING, str);
        }
    }

    public final void A0Q(BigDecimal bigDecimal) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r1 = (AnonymousClass7s) this;
            r1.A0W("write number");
            if (bigDecimal == null) {
                AnonymousClass7s.A02(r1);
            } else if (((VH) r1).A02) {
                AnonymousClass7s.A05(r1, bigDecimal);
            } else {
                r1.A0O(bigDecimal.toString());
            }
        } else {
            JD jd = (JD) this;
            if (bigDecimal == null) {
                jd.A0A();
            } else {
                JD.A02(jd, NX.VALUE_NUMBER_FLOAT, bigDecimal);
            }
        }
    }

    public final void A0R(BigInteger bigInteger) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r1 = (AnonymousClass7s) this;
            r1.A0W("write number");
            if (bigInteger == null) {
                AnonymousClass7s.A02(r1);
            } else if (((VH) r1).A02) {
                AnonymousClass7s.A05(r1, bigInteger);
            } else {
                r1.A0O(bigInteger.toString());
            }
        } else {
            JD jd = (JD) this;
            if (bigInteger == null) {
                jd.A0A();
            } else {
                JD.A02(jd, NX.VALUE_NUMBER_INT, bigInteger);
            }
        }
    }

    public final void A0S(short s) {
        if (this instanceof JD) {
            JD.A02((JD) this, NX.VALUE_NUMBER_INT, Short.valueOf(s));
        } else if (!(this instanceof AnonymousClass7s)) {
            A0G(s);
        } else {
            AnonymousClass7s r4 = (AnonymousClass7s) this;
            r4.A0W("write number");
            if (((VH) r4).A02) {
                if (r4.A02 + 8 >= r4.A00) {
                    AnonymousClass7s.A03(r4);
                }
                char[] cArr = r4.A03;
                int i = r4.A02;
                int i2 = i + 1;
                r4.A02 = i2;
                cArr[i] = '\"';
                int A002 = Nk.A00(s, cArr, i2);
                r4.A02 = A002;
                char[] cArr2 = r4.A03;
                r4.A02 = A002 + 1;
                cArr2[A002] = '\"';
                return;
            }
            if (r4.A02 + 6 >= r4.A00) {
                AnonymousClass7s.A03(r4);
            }
            r4.A02 = Nk.A00(s, r4.A03, r4.A02);
        }
    }

    public final void A0T(boolean z) {
        NX nx;
        int i;
        char c;
        if (!(this instanceof JD)) {
            AnonymousClass7s r3 = (AnonymousClass7s) this;
            r3.A0W("write boolean value");
            if (r3.A02 + 5 >= r3.A00) {
                AnonymousClass7s.A03(r3);
            }
            int i2 = r3.A02;
            char[] cArr = r3.A03;
            if (z) {
                cArr[i2] = 't';
                int i3 = i2 + 1;
                cArr[i3] = 'r';
                i = i3 + 1;
                c = 'u';
            } else {
                cArr[i2] = 'f';
                int i4 = i2 + 1;
                cArr[i4] = 'a';
                int i5 = i4 + 1;
                cArr[i5] = 'l';
                i = i5 + 1;
                c = 's';
            }
            cArr[i] = c;
            int i6 = i + 1;
            cArr[i6] = 'e';
            r3.A02 = i6 + 1;
            return;
        }
        JD jd = (JD) this;
        if (z) {
            nx = NX.VALUE_TRUE;
        } else {
            nx = NX.VALUE_FALSE;
        }
        JD.A01(jd, nx);
    }

    public final void A0U(char[] cArr, int i, int i2) {
        if (!(this instanceof JD)) {
            AnonymousClass7s r2 = (AnonymousClass7s) this;
            if (i2 < 32) {
                if (i2 > r2.A00 - r2.A02) {
                    AnonymousClass7s.A03(r2);
                }
                System.arraycopy(cArr, i, r2.A03, r2.A02, i2);
                r2.A02 += i2;
                return;
            }
            AnonymousClass7s.A03(r2);
            r2.A05.write(cArr, i, i2);
            return;
        }
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x003b A[EDGE_INSN: B:56:0x003b->B:14:0x003b ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0V(char[] r12, int r13, int r14) {
        /*
        // Method dump skipped, instructions count: 215
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC1012qg.A0V(char[], int, int):void");
    }

    @Override // java.io.Flushable
    public final void flush() {
        if (!(this instanceof JD)) {
            AnonymousClass7s r2 = (AnonymousClass7s) this;
            AnonymousClass7s.A03(r2);
            Writer writer = r2.A05;
            if (writer != null && r2.A0X(NS.FLUSH_PASSED_TO_STREAM)) {
                writer.flush();
            }
        }
    }
}
