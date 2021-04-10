package X;

import com.adobe.xmp.impl.Base64;
import com.oculus.localmedia.MediaProviderUtils;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.05Z  reason: invalid class name */
public final class AnonymousClass05Z extends AnonymousClass0HW {
    public static final char[] A06 = C03730oR.A02();
    public int A00;
    public int A01 = 0;
    public int A02 = 0;
    public char[] A03;
    public char[] A04;
    public final Writer A05;

    private void A04(char c, int i) throws IOException, C02310iT {
        int i2;
        if (i >= 0) {
            int i3 = this.A02;
            if (i3 >= 2) {
                int i4 = i3 - 2;
                this.A01 = i4;
                char[] cArr = this.A03;
                cArr[i4] = '\\';
                cArr[i4 + 1] = (char) i;
                return;
            }
            char[] cArr2 = this.A04;
            if (cArr2 == null) {
                cArr2 = A07();
            }
            this.A01 = i3;
            cArr2[1] = (char) i;
            this.A05.write(cArr2, 0, 2);
        } else if (i != -2) {
            int i5 = this.A02;
            if (i5 >= 6) {
                char[] cArr3 = this.A03;
                int i6 = i5 - 6;
                this.A01 = i6;
                cArr3[i6] = '\\';
                int i7 = i6 + 1;
                cArr3[i7] = 'u';
                if (c > 255) {
                    int i8 = (c >> '\b') & MediaProviderUtils.JPEG_HEADER;
                    int i9 = i7 + 1;
                    char[] cArr4 = A06;
                    cArr3[i9] = cArr4[i8 >> 4];
                    i2 = i9 + 1;
                    cArr3[i2] = cArr4[i8 & 15];
                    c = (char) (c & 255);
                } else {
                    int i10 = i7 + 1;
                    cArr3[i10] = '0';
                    i2 = i10 + 1;
                    cArr3[i2] = '0';
                }
                int i11 = i2 + 1;
                char[] cArr5 = A06;
                cArr3[i11] = cArr5[c >> 4];
                cArr3[i11 + 1] = cArr5[c & 15];
                return;
            }
            char[] cArr6 = this.A04;
            if (cArr6 == null) {
                cArr6 = A07();
            }
            this.A01 = i5;
            if (c > 255) {
                int i12 = (c >> '\b') & MediaProviderUtils.JPEG_HEADER;
                int i13 = c & 255;
                char[] cArr7 = A06;
                cArr6[10] = cArr7[i12 >> 4];
                cArr6[11] = cArr7[i12 & 15];
                cArr6[12] = cArr7[i13 >> 4];
                cArr6[13] = cArr7[i13 & 15];
                this.A05.write(cArr6, 8, 6);
                return;
            }
            char[] cArr8 = A06;
            cArr6[6] = cArr8[c >> 4];
            cArr6[7] = cArr8[c & 15];
            this.A05.write(cArr6, 2, 6);
        } else {
            throw new NullPointerException("getEscapeSequence");
        }
    }

    private int A00(char[] cArr, int i, int i2, char c, int i3) throws IOException, C02310iT {
        int i4;
        if (i3 >= 0) {
            if (i <= 1 || i >= i2) {
                char[] cArr2 = this.A04;
                if (cArr2 == null) {
                    cArr2 = A07();
                }
                cArr2[1] = (char) i3;
                this.A05.write(cArr2, 0, 2);
                return i;
            }
            int i5 = i - 2;
            cArr[i5] = '\\';
            cArr[i5 + 1] = (char) i3;
            return i5;
        } else if (i3 == -2) {
            throw new NullPointerException("getEscapeSequence");
        } else if (i <= 5 || i >= i2) {
            char[] cArr3 = this.A04;
            if (cArr3 == null) {
                cArr3 = A07();
            }
            this.A01 = this.A02;
            if (c > 255) {
                int i6 = (c >> '\b') & MediaProviderUtils.JPEG_HEADER;
                int i7 = c & 255;
                char[] cArr4 = A06;
                cArr3[10] = cArr4[i6 >> 4];
                cArr3[11] = cArr4[i6 & 15];
                cArr3[12] = cArr4[i7 >> 4];
                cArr3[13] = cArr4[i7 & 15];
                this.A05.write(cArr3, 8, 6);
                return i;
            }
            char[] cArr5 = A06;
            cArr3[6] = cArr5[c >> 4];
            cArr3[7] = cArr5[c & 15];
            this.A05.write(cArr3, 2, 6);
            return i;
        } else {
            int i8 = i - 6;
            int i9 = i8 + 1;
            cArr[i8] = '\\';
            int i10 = i9 + 1;
            cArr[i9] = 'u';
            if (c > 255) {
                int i11 = (c >> '\b') & MediaProviderUtils.JPEG_HEADER;
                int i12 = i10 + 1;
                char[] cArr6 = A06;
                cArr[i10] = cArr6[i11 >> 4];
                i4 = i12 + 1;
                cArr[i12] = cArr6[i11 & 15];
                c = (char) (c & 255);
            } else {
                int i13 = i10 + 1;
                cArr[i10] = '0';
                i4 = i13 + 1;
                cArr[i13] = '0';
            }
            int i14 = i4 + 1;
            char[] cArr7 = A06;
            cArr[i4] = cArr7[c >> 4];
            cArr[i14] = cArr7[c & 15];
            return i14 - 5;
        }
    }

    private void A01() throws IOException {
        if (this.A02 + 4 >= this.A00) {
            A02();
        }
        int i = this.A02;
        char[] cArr = this.A03;
        cArr[i] = 'n';
        int i2 = i + 1;
        cArr[i2] = 'u';
        int i3 = i2 + 1;
        cArr[i3] = 'l';
        int i4 = i3 + 1;
        cArr[i4] = 'l';
        this.A02 = i4 + 1;
    }

    private final void A02() throws IOException {
        int i = this.A02;
        int i2 = this.A01;
        int i3 = i - i2;
        if (i3 > 0) {
            this.A01 = 0;
            this.A02 = 0;
            this.A05.write(this.A03, i2, i3);
        }
    }

    private void A03(char c, int i) throws IOException, C02310iT {
        int i2;
        if (i >= 0) {
            if (this.A02 + 2 > this.A00) {
                A02();
            }
            char[] cArr = this.A03;
            int i3 = this.A02;
            int i4 = i3 + 1;
            this.A02 = i4;
            cArr[i3] = '\\';
            this.A02 = i4 + 1;
            cArr[i4] = (char) i;
        } else if (i != -2) {
            if (this.A02 + 2 > this.A00) {
                A02();
            }
            int i5 = this.A02;
            char[] cArr2 = this.A03;
            int i6 = i5 + 1;
            cArr2[i5] = '\\';
            int i7 = i6 + 1;
            cArr2[i6] = 'u';
            if (c > 255) {
                int i8 = 255 & (c >> '\b');
                int i9 = i7 + 1;
                char[] cArr3 = A06;
                cArr2[i7] = cArr3[i8 >> 4];
                i2 = i9 + 1;
                cArr2[i9] = cArr3[i8 & 15];
                c = (char) (c & 255);
            } else {
                int i10 = i7 + 1;
                cArr2[i7] = '0';
                i2 = i10 + 1;
                cArr2[i10] = '0';
            }
            int i11 = i2 + 1;
            char[] cArr4 = A06;
            cArr2[i2] = cArr4[c >> 4];
            cArr2[i11] = cArr4[c & 15];
            this.A02 = i11;
        } else {
            throw new NullPointerException("getEscapeSequence");
        }
    }

    private void A05(Object obj) throws IOException {
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr = this.A03;
        int i = this.A02;
        this.A02 = i + 1;
        cArr[i] = '\"';
        A0T(obj.toString());
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr2 = this.A03;
        int i2 = this.A02;
        this.A02 = i2 + 1;
        cArr2[i2] = '\"';
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e7, code lost:
        r1 = r17.A01;
        r3 = r3 - r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00ea, code lost:
        if (r3 <= 0) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ec, code lost:
        r17.A05.write(r2, r1, r3);
     */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x003d A[EDGE_INSN: B:79:0x003d->B:14:0x003d ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00d5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A06(java.lang.String r18) throws java.io.IOException, X.C02310iT {
        /*
        // Method dump skipped, instructions count: 264
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass05Z.A06(java.lang.String):void");
    }

    private char[] A07() {
        char[] cArr = new char[14];
        cArr[0] = '\\';
        cArr[2] = '\\';
        cArr[3] = 'u';
        cArr[4] = '0';
        cArr[5] = '0';
        cArr[8] = '\\';
        cArr[9] = 'u';
        this.A04 = cArr;
        return cArr;
    }

    @Override // X.AbstractC02300iS
    public final void A0E() throws IOException, C02310iT {
        C02260iN r2 = ((AnonymousClass0Sx) this).A00;
        if (((AbstractC03630oD) r2).A01 == 1) {
            AbstractC03660oG r1 = ((AbstractC02300iS) this).A00;
            if (r1 != null) {
                r1.ABE(this, ((AbstractC03630oD) r2).A00 + 1);
            } else {
                if (this.A02 >= this.A00) {
                    A02();
                }
                char[] cArr = this.A03;
                int i = this.A02;
                this.A02 = i + 1;
                cArr[i] = ']';
            }
            ((AnonymousClass0Sx) this).A00 = ((AnonymousClass0Sx) this).A00.A02;
            return;
        }
        throw new C02310iT(AnonymousClass006.A07("Current context not an ARRAY but ", r2.A00()));
    }

    @Override // X.AbstractC02300iS
    public final void A0F() throws IOException, C02310iT {
        C02260iN r2 = ((AnonymousClass0Sx) this).A00;
        if (((AbstractC03630oD) r2).A01 == 2) {
            AbstractC03660oG r1 = ((AbstractC02300iS) this).A00;
            if (r1 != null) {
                r1.ABF(this, ((AbstractC03630oD) r2).A00 + 1);
            } else {
                if (this.A02 >= this.A00) {
                    A02();
                }
                char[] cArr = this.A03;
                int i = this.A02;
                this.A02 = i + 1;
                cArr[i] = '}';
            }
            ((AnonymousClass0Sx) this).A00 = ((AnonymousClass0Sx) this).A00.A02;
            return;
        }
        throw new C02310iT(AnonymousClass006.A07("Current context not an object but ", r2.A00()));
    }

    @Override // X.AbstractC02300iS
    public final void A0G() throws IOException, C02310iT {
        A0b("write null value");
        A01();
    }

    @Override // X.AbstractC02300iS
    public final void A0H() throws IOException, C02310iT {
        A0b("start an array");
        C02260iN r2 = ((AnonymousClass0Sx) this).A00;
        C02260iN r1 = r2.A00;
        if (r1 == null) {
            r1 = new C02260iN(1, r2);
            r2.A00 = r1;
        } else {
            ((AbstractC03630oD) r1).A01 = 1;
            ((AbstractC03630oD) r1).A00 = -1;
            r1.A01 = null;
        }
        ((AnonymousClass0Sx) this).A00 = r1;
        AbstractC03660oG r0 = ((AbstractC02300iS) this).A00;
        if (r0 != null) {
            r0.ABK(this);
            return;
        }
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr = this.A03;
        int i = this.A02;
        this.A02 = i + 1;
        cArr[i] = '[';
    }

    @Override // X.AbstractC02300iS
    public final void A0I() throws IOException, C02310iT {
        A0b("start an object");
        C02260iN r2 = ((AnonymousClass0Sx) this).A00;
        C02260iN r1 = r2.A00;
        if (r1 == null) {
            r1 = new C02260iN(2, r2);
            r2.A00 = r1;
        } else {
            ((AbstractC03630oD) r1).A01 = 2;
            ((AbstractC03630oD) r1).A00 = -1;
            r1.A01 = null;
        }
        ((AnonymousClass0Sx) this).A00 = r1;
        AbstractC03660oG r0 = ((AbstractC02300iS) this).A00;
        if (r0 != null) {
            r0.ABL(this);
            return;
        }
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr = this.A03;
        int i = this.A02;
        this.A02 = i + 1;
        cArr[i] = '{';
    }

    @Override // X.AbstractC02300iS
    public final void A0J(char c) throws IOException, C02310iT {
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr = this.A03;
        int i = this.A02;
        this.A02 = i + 1;
        cArr[i] = c;
    }

    @Override // X.AbstractC02300iS
    public final void A0K(double d) throws IOException, C02310iT {
        if (((AnonymousClass0Sx) this).A03 || ((Double.isNaN(d) || Double.isInfinite(d)) && A0c(AnonymousClass0o7.QUOTE_NON_NUMERIC_NUMBERS))) {
            A0U(String.valueOf(d));
            return;
        }
        A0b("write number");
        A0T(String.valueOf(d));
    }

    @Override // X.AbstractC02300iS
    public final void A0L(float f) throws IOException, C02310iT {
        if (((AnonymousClass0Sx) this).A03 || ((Float.isNaN(f) || Float.isInfinite(f)) && A0c(AnonymousClass0o7.QUOTE_NON_NUMERIC_NUMBERS))) {
            A0U(String.valueOf(f));
            return;
        }
        A0b("write number");
        A0T(String.valueOf(f));
    }

    @Override // X.AbstractC02300iS
    public final void A0M(int i) throws IOException, C02310iT {
        A0b("write number");
        if (((AnonymousClass0Sx) this).A03) {
            if (this.A02 + 13 >= this.A00) {
                A02();
            }
            char[] cArr = this.A03;
            int i2 = this.A02;
            int i3 = i2 + 1;
            this.A02 = i3;
            cArr[i2] = '\"';
            int A002 = C03790oY.A00(i, cArr, i3);
            this.A02 = A002;
            char[] cArr2 = this.A03;
            this.A02 = A002 + 1;
            cArr2[A002] = '\"';
            return;
        }
        if (this.A02 + 11 >= this.A00) {
            A02();
        }
        this.A02 = C03790oY.A00(i, this.A03, this.A02);
    }

    @Override // X.AbstractC02300iS
    public final void A0N(long j) throws IOException, C02310iT {
        A0b("write number");
        if (((AnonymousClass0Sx) this).A03) {
            if (this.A02 + 23 >= this.A00) {
                A02();
            }
            char[] cArr = this.A03;
            int i = this.A02;
            int i2 = i + 1;
            this.A02 = i2;
            cArr[i] = '\"';
            int A032 = C03790oY.A03(j, cArr, i2);
            this.A02 = A032;
            char[] cArr2 = this.A03;
            this.A02 = A032 + 1;
            cArr2[A032] = '\"';
            return;
        }
        if (this.A02 + 21 >= this.A00) {
            A02();
        }
        this.A02 = C03790oY.A03(j, this.A03, this.A02);
    }

    @Override // X.AbstractC02300iS
    public final void A0O(AnonymousClass0o2 r15, byte[] bArr, int i, int i2) throws IOException, C02310iT {
        char c;
        int i3 = i;
        A0b("write binary value");
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr = this.A03;
        int i4 = this.A02;
        int i5 = i4 + 1;
        this.A02 = i5;
        cArr[i4] = '\"';
        int i6 = i2 + i;
        int i7 = i6 - 3;
        int i8 = this.A00 - 6;
        int i9 = r15.A01 >> 2;
        while (i3 <= i7) {
            if (i5 > i8) {
                A02();
            }
            int i10 = i3 + 1;
            int i11 = i10 + 1;
            i3 = i11 + 1;
            int i12 = (((bArr[i3] << 8) | (bArr[i10] & Base64.INVALID)) << 8) | (bArr[i11] & Base64.INVALID);
            char[] cArr2 = this.A03;
            int i13 = this.A02;
            int i14 = i13 + 1;
            char[] cArr3 = r15.A03;
            cArr2[i13] = cArr3[(i12 >> 18) & 63];
            int i15 = i14 + 1;
            cArr2[i14] = cArr3[(i12 >> 12) & 63];
            int i16 = i15 + 1;
            cArr2[i15] = cArr3[(i12 >> 6) & 63];
            i5 = i16 + 1;
            cArr2[i16] = cArr3[i12 & 63];
            this.A02 = i5;
            i9--;
            if (i9 <= 0) {
                int i17 = i5 + 1;
                this.A02 = i17;
                cArr2[i5] = '\\';
                i5 = i17 + 1;
                this.A02 = i5;
                cArr2[i17] = 'n';
                i9 = i9;
            }
        }
        int i18 = i6 - i3;
        if (i18 > 0) {
            if (i5 > i8) {
                A02();
            }
            int i19 = i3 + 1;
            int i20 = bArr[i3] << 16;
            if (i18 == 2) {
                i20 |= (bArr[i19] & Base64.INVALID) << 8;
            }
            char[] cArr4 = this.A03;
            int i21 = this.A02;
            int i22 = i21 + 1;
            char[] cArr5 = r15.A03;
            cArr4[i21] = cArr5[(i20 >> 18) & 63];
            i5 = i22 + 1;
            cArr4[i22] = cArr5[(i20 >> 12) & 63];
            if (r15.A02) {
                int i23 = i5 + 1;
                if (i18 == 2) {
                    c = cArr5[(i20 >> 6) & 63];
                } else {
                    c = r15.A00;
                }
                cArr4[i5] = c;
                i5 = i23 + 1;
                cArr4[i23] = r15.A00;
            } else if (i18 == 2) {
                cArr4[i5] = cArr5[(i20 >> 6) & 63];
                i5++;
            }
            this.A02 = i5;
        }
        if (i5 >= this.A00) {
            A02();
        }
        char[] cArr6 = this.A03;
        int i24 = this.A02;
        this.A02 = i24 + 1;
        cArr6[i24] = '\"';
    }

    @Override // X.AnonymousClass0Sx, X.AbstractC02300iS
    public final void A0P(AbstractC03670oH r9) throws IOException, C02310iT {
        int A012 = ((AnonymousClass0Sx) this).A00.A01(r9.getValue());
        if (A012 == 4) {
            throw new C02310iT("Can not write a field name, expecting a value");
        }
        boolean z = true;
        if (A012 != 1) {
            z = false;
        }
        AbstractC03660oG r0 = ((AbstractC02300iS) this).A00;
        if (r0 != null) {
            if (z) {
                r0.ABH(this);
            } else {
                r0.A1Z(this);
            }
            char[] A1U = r9.A1U();
            if (A0c(AnonymousClass0o7.QUOTE_FIELD_NAMES)) {
                if (this.A02 >= this.A00) {
                    A02();
                }
                char[] cArr = this.A03;
                int i = this.A02;
                this.A02 = i + 1;
                cArr[i] = '\"';
                A0Z(A1U, 0, A1U.length);
                if (this.A02 >= this.A00) {
                    A02();
                }
                char[] cArr2 = this.A03;
                int i2 = this.A02;
                this.A02 = i2 + 1;
                cArr2[i2] = '\"';
                return;
            }
            A0Z(A1U, 0, A1U.length);
            return;
        }
        if (this.A02 + 1 >= this.A00) {
            A02();
        }
        if (z) {
            char[] cArr3 = this.A03;
            int i3 = this.A02;
            this.A02 = i3 + 1;
            cArr3[i3] = ',';
        }
        char[] A1U2 = r9.A1U();
        if (!A0c(AnonymousClass0o7.QUOTE_FIELD_NAMES)) {
            A0Z(A1U2, 0, A1U2.length);
            return;
        }
        char[] cArr4 = this.A03;
        int i4 = this.A02;
        int i5 = i4 + 1;
        this.A02 = i5;
        cArr4[i4] = '\"';
        int length = A1U2.length;
        if (i5 + length + 1 >= this.A00) {
            A0Z(A1U2, 0, length);
            if (this.A02 >= this.A00) {
                A02();
            }
            char[] cArr5 = this.A03;
            int i6 = this.A02;
            this.A02 = i6 + 1;
            cArr5[i6] = '\"';
            return;
        }
        System.arraycopy(A1U2, 0, cArr4, i5, length);
        int i7 = this.A02 + length;
        this.A02 = i7;
        char[] cArr6 = this.A03;
        this.A02 = i7 + 1;
        cArr6[i7] = '\"';
    }

    @Override // X.AnonymousClass0Sx, X.AbstractC02300iS
    public final void A0Q(AbstractC03670oH r7) throws IOException, C02310iT {
        A0b("write text value");
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr = this.A03;
        int i = this.A02;
        this.A02 = i + 1;
        cArr[i] = '\"';
        char[] A1U = r7.A1U();
        int length = A1U.length;
        if (length < 32) {
            if (length > this.A00 - this.A02) {
                A02();
            }
            System.arraycopy(A1U, 0, this.A03, this.A02, length);
            this.A02 += length;
        } else {
            A02();
            this.A05.write(A1U, 0, length);
        }
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr2 = this.A03;
        int i2 = this.A02;
        this.A02 = i2 + 1;
        cArr2[i2] = '\"';
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0075, code lost:
        if (A0c(X.AnonymousClass0o7.QUOTE_FIELD_NAMES) == false) goto L_0x0077;
     */
    @Override // X.AbstractC02300iS
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0R(java.lang.String r5) throws java.io.IOException, X.C02310iT {
        /*
        // Method dump skipped, instructions count: 123
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass05Z.A0R(java.lang.String):void");
    }

    @Override // X.AbstractC02300iS
    public final void A0S(String str) throws IOException, C02310iT {
        A0b("write number");
        if (((AnonymousClass0Sx) this).A03) {
            A05(str);
        } else {
            A0T(str);
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0U(String str) throws IOException, C02310iT {
        A0b("write text value");
        if (str == null) {
            A01();
            return;
        }
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr = this.A03;
        int i = this.A02;
        this.A02 = i + 1;
        cArr[i] = '\"';
        A06(str);
        if (this.A02 >= this.A00) {
            A02();
        }
        char[] cArr2 = this.A03;
        int i2 = this.A02;
        this.A02 = i2 + 1;
        cArr2[i2] = '\"';
    }

    @Override // X.AbstractC02300iS
    public final void A0V(BigDecimal bigDecimal) throws IOException, C02310iT {
        A0b("write number");
        if (bigDecimal == null) {
            A01();
        } else if (((AnonymousClass0Sx) this).A03) {
            A05(bigDecimal);
        } else {
            A0T(bigDecimal.toString());
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0W(BigInteger bigInteger) throws IOException, C02310iT {
        A0b("write number");
        if (bigInteger == null) {
            A01();
        } else if (((AnonymousClass0Sx) this).A03) {
            A05(bigInteger);
        } else {
            A0T(bigInteger.toString());
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0X(short s) throws IOException, C02310iT {
        A0b("write number");
        if (((AnonymousClass0Sx) this).A03) {
            if (this.A02 + 8 >= this.A00) {
                A02();
            }
            char[] cArr = this.A03;
            int i = this.A02;
            int i2 = i + 1;
            this.A02 = i2;
            cArr[i] = '\"';
            int A002 = C03790oY.A00(s, cArr, i2);
            this.A02 = A002;
            char[] cArr2 = this.A03;
            this.A02 = A002 + 1;
            cArr2[A002] = '\"';
            return;
        }
        if (this.A02 + 6 >= this.A00) {
            A02();
        }
        this.A02 = C03790oY.A00(s, this.A03, this.A02);
    }

    @Override // X.AbstractC02300iS
    public final void A0Y(boolean z) throws IOException, C02310iT {
        int i;
        char c;
        A0b("write boolean value");
        if (this.A02 + 5 >= this.A00) {
            A02();
        }
        int i2 = this.A02;
        char[] cArr = this.A03;
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
        this.A02 = i6 + 1;
    }

    @Override // X.AbstractC02300iS
    public final void A0Z(char[] cArr, int i, int i2) throws IOException, C02310iT {
        if (i2 < 32) {
            if (i2 > this.A00 - this.A02) {
                A02();
            }
            System.arraycopy(cArr, i, this.A03, this.A02, i2);
            this.A02 += i2;
            return;
        }
        A02();
        this.A05.write(cArr, i, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0034 A[EDGE_INSN: B:54:0x0034->B:12:0x0034 ?: BREAK  , SYNTHETIC] */
    @Override // X.AbstractC02300iS
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0a(char[] r11, int r12, int r13) throws java.io.IOException, X.C02310iT {
        /*
        // Method dump skipped, instructions count: 199
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass05Z.A0a(char[], int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005b, code lost:
        if (r4 == 5) goto L_0x005d;
     */
    @Override // X.AnonymousClass0Sx
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0b(java.lang.String r7) throws java.io.IOException, X.C02310iT {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass05Z.A0b(java.lang.String):void");
    }

    public AnonymousClass05Z(C03750oT r4, int i, AbstractC03650oF r6, Writer writer) {
        super(r4, i, r6);
        this.A05 = writer;
        if (r4.A00 == null) {
            char[] A002 = r4.A03.A00(AnonymousClass007.A01, 0);
            r4.A00 = A002;
            this.A03 = A002;
            this.A00 = A002.length;
            return;
        }
        throw new IllegalStateException("Trying to call same allocXxx() method second time");
    }

    @Override // X.AbstractC02300iS
    public final void A0T(String str) throws IOException, C02310iT {
        int length = str.length();
        int i = this.A00;
        int i2 = this.A02;
        int i3 = i - i2;
        if (i3 == 0) {
            A02();
            int i4 = this.A00;
            i2 = this.A02;
            i3 = i4 - i2;
        }
        if (i3 >= length) {
            str.getChars(0, length, this.A03, i2);
            this.A02 += length;
            return;
        }
        str.getChars(0, i3, this.A03, i2);
        this.A02 += i3;
        A02();
        int i5 = length - i3;
        while (true) {
            int i6 = this.A00;
            if (i5 > i6) {
                int i7 = i3 + i6;
                str.getChars(i3, i7, this.A03, 0);
                this.A01 = 0;
                this.A02 = i6;
                A02();
                i5 -= i6;
                i3 = i7;
            } else {
                str.getChars(i3, i3 + i5, this.A03, 0);
                this.A01 = 0;
                this.A02 = i5;
                return;
            }
        }
    }

    @Override // X.AnonymousClass0Sx, java.io.Closeable, X.AbstractC02300iS, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        if (this.A03 != null && A0c(AnonymousClass0o7.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                int i = ((AbstractC03630oD) ((AnonymousClass0Sx) this).A00).A01;
                if (i != 1) {
                    if (i != 2) {
                        break;
                    }
                    A0F();
                } else {
                    A0E();
                }
            }
        }
        A02();
        Writer writer = this.A05;
        if (writer != null) {
            if (((AnonymousClass0HW) this).A03.A05 || A0c(AnonymousClass0o7.AUTO_CLOSE_TARGET)) {
                writer.close();
            } else if (A0c(AnonymousClass0o7.FLUSH_PASSED_TO_STREAM)) {
                writer.flush();
            }
        }
        char[] cArr = this.A03;
        if (cArr != null) {
            this.A03 = null;
            C03750oT r1 = ((AnonymousClass0HW) this).A03;
            if (cArr == r1.A00) {
                r1.A00 = null;
                r1.A03.A00[1] = cArr;
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }

    @Override // X.AnonymousClass0Sx, X.AbstractC02300iS, java.io.Flushable
    public final void flush() throws IOException {
        A02();
        Writer writer = this.A05;
        if (writer != null && A0c(AnonymousClass0o7.FLUSH_PASSED_TO_STREAM)) {
            writer.flush();
        }
    }
}
