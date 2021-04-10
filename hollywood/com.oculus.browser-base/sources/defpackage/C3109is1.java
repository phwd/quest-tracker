package defpackage;

/* renamed from: is1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3109is1 extends AbstractC2597fs1 {
    public static int d(byte[] bArr, int i, long j, int i2) {
        if (i2 == 0) {
            AbstractC2597fs1 fs1 = AbstractC3280js1.f10244a;
            if (i > -12) {
                return -1;
            }
            return i;
        } else if (i2 == 1) {
            return AbstractC3280js1.a(i, Op1.g(bArr, j));
        } else {
            if (i2 == 2) {
                return AbstractC3280js1.d(i, Op1.g(bArr, j), Op1.g(bArr, j + 1));
            }
            throw new AssertionError();
        }
    }

    @Override // defpackage.AbstractC2597fs1
    public String a(byte[] bArr, int i, int i2) {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte g = Op1.g(bArr, (long) i);
                if (!AbstractC2426es1.b(g)) {
                    break;
                }
                i++;
                cArr[i4] = (char) g;
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte g2 = Op1.g(bArr, (long) i);
                if (AbstractC2426es1.b(g2)) {
                    int i7 = i5 + 1;
                    cArr[i5] = (char) g2;
                    i = i6;
                    while (true) {
                        i5 = i7;
                        if (i >= i3) {
                            break;
                        }
                        byte g3 = Op1.g(bArr, (long) i);
                        if (!AbstractC2426es1.b(g3)) {
                            break;
                        }
                        i++;
                        i7 = i5 + 1;
                        cArr[i5] = (char) g3;
                    }
                } else {
                    if (!(g2 < -32)) {
                        if (g2 < -16) {
                            if (i6 < i3 - 1) {
                                int i8 = i6 + 1;
                                AbstractC2426es1.d(g2, Op1.g(bArr, (long) i6), Op1.g(bArr, (long) i8), cArr, i5);
                                i = i8 + 1;
                                i5++;
                            } else {
                                throw L30.b();
                            }
                        } else if (i6 < i3 - 2) {
                            int i9 = i6 + 1;
                            int i10 = i9 + 1;
                            AbstractC2426es1.a(g2, Op1.g(bArr, (long) i6), Op1.g(bArr, (long) i9), Op1.g(bArr, (long) i10), cArr, i5);
                            i5 = i5 + 1 + 1;
                            i = i10 + 1;
                        } else {
                            throw L30.b();
                        }
                    } else if (i6 < i3) {
                        AbstractC2426es1.c(g2, Op1.g(bArr, (long) i6), cArr, i5);
                        i = i6 + 1;
                        i5++;
                    } else {
                        throw L30.b();
                    }
                }
            }
            return new String(cArr, 0, i5);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033 A[LOOP:1: B:13:0x0033->B:37:0x00fc, LOOP_START, PHI: r2 r3 r4 r11 
      PHI: (r2v4 int) = (r2v3 int), (r2v6 int) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v3 char) = (r3v2 char), (r3v4 char) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v3 long) = (r4v2 long), (r4v5 long) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v3 long) = (r11v2 long), (r11v5 long) binds: [B:10:0x002f, B:37:0x00fc] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.AbstractC2597fs1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b(java.lang.CharSequence r22, byte[] r23, int r24, int r25) {
        /*
        // Method dump skipped, instructions count: 362
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3109is1.b(java.lang.CharSequence, byte[], int, int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        if (defpackage.Op1.g(r25, r8) > -65) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0067, code lost:
        if (defpackage.Op1.g(r25, r8) > -65) goto L_0x0069;
     */
    @Override // defpackage.AbstractC2597fs1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c(int r24, byte[] r25, int r26, int r27) {
        /*
        // Method dump skipped, instructions count: 375
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3109is1.c(int, byte[], int, int):int");
    }
}
