package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Ok  reason: invalid class name and case insensitive filesystem */
public final class C00990Ok {
    public static C00990Ok A02;
    public int A00;
    public final AbstractC03520nC A01;

    public static AnonymousClass0Oj A00(InputStream inputStream) {
        C00990Ok r0;
        int i;
        int i2;
        AnonymousClass0Oj r1;
        boolean A002;
        boolean z;
        try {
            synchronized (C00990Ok.class) {
                r0 = A02;
                if (r0 == null) {
                    r0 = new C00990Ok();
                    A02 = r0;
                }
            }
            if (inputStream != null) {
                int i3 = r0.A00;
                byte[] bArr = new byte[i3];
                boolean z2 = false;
                if (i3 >= i3) {
                    z2 = true;
                }
                C00740Ii.A01(Boolean.valueOf(z2));
                if (inputStream.markSupported()) {
                    try {
                        inputStream.mark(i3);
                        i2 = 0;
                        if (i3 >= 0) {
                            i = 0;
                            while (i < i3) {
                                int read = inputStream.read(bArr, 0 + i, i3 - i);
                                if (read == -1) {
                                    break;
                                }
                                i += read;
                            }
                        } else {
                            throw new IndexOutOfBoundsException("len is negative");
                        }
                    } finally {
                        inputStream.reset();
                    }
                } else {
                    i2 = 0;
                    if (i3 >= 0) {
                        i = 0;
                        while (i < i3) {
                            int read2 = inputStream.read(bArr, 0 + i, i3 - i);
                            if (read2 == -1) {
                                break;
                            }
                            i += read2;
                        }
                    } else {
                        throw new IndexOutOfBoundsException("len is negative");
                    }
                }
                if (i < 20 || !(A002 = AnonymousClass0LU.A00(bArr, i2, AnonymousClass0LU.A03)) || !AnonymousClass0LU.A00(bArr, 8, AnonymousClass0LU.A02)) {
                    byte[] bArr2 = AbstractC03520nC.A09;
                    if (i < bArr2.length || !AbstractC03520nC.A00(bArr, bArr2, i2)) {
                        byte[] bArr3 = AbstractC03520nC.A0A;
                        if (i >= bArr3.length && AbstractC03520nC.A00(bArr, bArr3, i2)) {
                            r1 = AnonymousClass0Oi.A06;
                        } else if (i < 6 || (!AbstractC03520nC.A00(bArr, AbstractC03520nC.A05, i2) && !AbstractC03520nC.A00(bArr, AbstractC03520nC.A06, i2))) {
                            byte[] bArr4 = AbstractC03520nC.A02;
                            if (i < bArr4.length || !AbstractC03520nC.A00(bArr, bArr4, i2)) {
                                byte[] bArr5 = AbstractC03520nC.A08;
                                if (i < bArr5.length || !AbstractC03520nC.A00(bArr, bArr5, i2)) {
                                    if (i >= 12 && bArr[3] >= 8 && AbstractC03520nC.A00(bArr, AbstractC03520nC.A07, 4)) {
                                        byte[][] bArr6 = AbstractC03520nC.A0B;
                                        int length = bArr6.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length) {
                                                break;
                                            } else if (AbstractC03520nC.A00(bArr, bArr6[i4], 8)) {
                                                r1 = AnonymousClass0Oi.A03;
                                                break;
                                            } else {
                                                i4++;
                                            }
                                        }
                                    }
                                    if (i < AbstractC03520nC.A01 || (!AbstractC03520nC.A00(bArr, AbstractC03520nC.A03, i2) && !AbstractC03520nC.A00(bArr, AbstractC03520nC.A04, i2))) {
                                        r1 = AnonymousClass0Oj.A01;
                                    } else {
                                        r1 = AnonymousClass0Oi.A01;
                                    }
                                } else {
                                    r1 = AnonymousClass0Oi.A04;
                                }
                            } else {
                                r1 = AnonymousClass0Oi.A00;
                            }
                        } else {
                            r1 = AnonymousClass0Oi.A02;
                        }
                    } else {
                        r1 = AnonymousClass0Oi.A05;
                    }
                } else {
                    if (i < 20 || !A002) {
                        z = false;
                    } else {
                        z = true;
                    }
                    C00740Ii.A01(Boolean.valueOf(z));
                    if (AnonymousClass0LU.A00(bArr, 12, AnonymousClass0LU.A06)) {
                        r1 = AnonymousClass0Oi.A0B;
                    } else if (AnonymousClass0LU.A00(bArr, 12, AnonymousClass0LU.A04)) {
                        r1 = AnonymousClass0Oi.A0A;
                    } else if (i < 21 || !AnonymousClass0LU.A00(bArr, 12, AnonymousClass0LU.A05)) {
                        r1 = AnonymousClass0Oj.A01;
                    } else {
                        byte b = bArr[20];
                        if ((b & 2) == 2) {
                            r1 = AnonymousClass0Oi.A07;
                        } else if ((b & 16) == 16) {
                            r1 = AnonymousClass0Oi.A09;
                        } else {
                            r1 = AnonymousClass0Oi.A08;
                        }
                    }
                }
                if (r1 == null || r1 == AnonymousClass0Oj.A01) {
                    return AnonymousClass0Oj.A01;
                }
                return r1;
            }
            throw null;
        } catch (IOException e) {
            C00770Im.A00(e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public C00990Ok() {
        AbstractC03520nC r0 = new AbstractC03520nC();
        this.A01 = r0;
        this.A00 = r0.A00;
    }
}
