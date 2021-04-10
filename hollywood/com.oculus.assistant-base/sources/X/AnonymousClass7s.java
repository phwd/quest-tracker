package X;

import java.io.Writer;

/* renamed from: X.7s  reason: invalid class name */
public final class AnonymousClass7s extends AnonymousClass2K {
    public static final char[] A06 = C0245Ne.A02();
    public int A00;
    public int A01 = 0;
    public int A02 = 0;
    public char[] A03;
    public char[] A04;
    public final Writer A05;

    private void A01(char c, int i) {
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
                    int i8 = (c >> '\b') & 255;
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
                int i12 = (c >> '\b') & 255;
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

    private int A00(char[] cArr, int i, int i2, char c, int i3) {
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
                int i6 = (c >> '\b') & 255;
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
                int i11 = (c >> '\b') & 255;
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

    public static void A02(AnonymousClass7s r3) {
        if (r3.A02 + 4 >= r3.A00) {
            A03(r3);
        }
        int i = r3.A02;
        char[] cArr = r3.A03;
        cArr[i] = 'n';
        int i2 = i + 1;
        cArr[i2] = 'u';
        int i3 = i2 + 1;
        cArr[i3] = 'l';
        int i4 = i3 + 1;
        cArr[i4] = 'l';
        r3.A02 = i4 + 1;
    }

    public static final void A03(AnonymousClass7s r4) {
        int i = r4.A02;
        int i2 = r4.A01;
        int i3 = i - i2;
        if (i3 > 0) {
            r4.A01 = 0;
            r4.A02 = 0;
            r4.A05.write(r4.A03, i2, i3);
        }
    }

    public static void A04(AnonymousClass7s r6, char c, int i) {
        int i2;
        if (i >= 0) {
            if (r6.A02 + 2 > r6.A00) {
                A03(r6);
            }
            char[] cArr = r6.A03;
            int i3 = r6.A02;
            int i4 = i3 + 1;
            r6.A02 = i4;
            cArr[i3] = '\\';
            r6.A02 = i4 + 1;
            cArr[i4] = (char) i;
        } else if (i != -2) {
            if (r6.A02 + 2 > r6.A00) {
                A03(r6);
            }
            int i5 = r6.A02;
            char[] cArr2 = r6.A03;
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
            r6.A02 = i11;
        } else {
            throw new NullPointerException("getEscapeSequence");
        }
    }

    public static void A05(AnonymousClass7s r4, Object obj) {
        if (r4.A02 >= r4.A00) {
            A03(r4);
        }
        char[] cArr = r4.A03;
        int i = r4.A02;
        r4.A02 = i + 1;
        cArr[i] = '\"';
        r4.A0O(obj.toString());
        if (r4.A02 >= r4.A00) {
            A03(r4);
        }
        char[] cArr2 = r4.A03;
        int i2 = r4.A02;
        r4.A02 = i2 + 1;
        cArr2[i2] = '\"';
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00e6, code lost:
        r1 = r15.A01;
        r3 = r3 - r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00e9, code lost:
        if (r3 <= 0) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00eb, code lost:
        r15.A05.write(r2, r1, r3);
     */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x003c A[EDGE_INSN: B:79:0x003c->B:14:0x003c ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00d4 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(X.AnonymousClass7s r15, java.lang.String r16) {
        /*
        // Method dump skipped, instructions count: 263
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass7s.A06(X.7s, java.lang.String):void");
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

    public AnonymousClass7s(C0247Ng ng, int i, NY ny, Writer writer) {
        super(ng, i, ny);
        this.A05 = writer;
        if (ng.A00 == null) {
            char[] A002 = ng.A03.A00(AnonymousClass09.A01, 0);
            ng.A00 = A002;
            this.A03 = A002;
            this.A00 = A002.length;
            return;
        }
        throw new IllegalStateException("Trying to call same allocXxx() method second time");
    }

    @Override // X.VH, X.AbstractC1012qg, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        super.close();
        if (this.A03 != null && A0X(NS.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                int i = ((NW) ((VH) this).A01).A01;
                if (i != 1) {
                    if (i != 2) {
                        break;
                    }
                    A09();
                } else {
                    A08();
                }
            }
        }
        A03(this);
        Writer writer = this.A05;
        if (writer != null) {
            if (((AnonymousClass2K) this).A03.A05 || A0X(NS.AUTO_CLOSE_TARGET)) {
                writer.close();
            } else if (A0X(NS.FLUSH_PASSED_TO_STREAM)) {
                writer.flush();
            }
        }
        char[] cArr = this.A03;
        if (cArr != null) {
            this.A03 = null;
            C0247Ng ng = ((AnonymousClass2K) this).A03;
            if (cArr == ng.A00) {
                ng.A00 = null;
                ng.A03.A00[1] = cArr;
                return;
            }
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
    }
}
