package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qq  reason: invalid class name and case insensitive filesystem */
public final class C10031qq {
    public static C10031qq A02;
    public int A00;
    public final AbstractC10041qr A01;

    public static AnonymousClass1tL A00(InputStream inputStream) {
        C10031qq r0;
        int i;
        int i2;
        AnonymousClass1tL r1;
        boolean A002;
        boolean z;
        try {
            synchronized (C10031qq.class) {
                r0 = A02;
                if (r0 == null) {
                    r0 = new C10031qq();
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
                AnonymousClass0KU.A01(Boolean.valueOf(z2));
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
                if (i < 20 || !(A002 = C01180Ma.A00(bArr, i2, C01180Ma.A03)) || !C01180Ma.A00(bArr, 8, C01180Ma.A02)) {
                    byte[] bArr2 = AbstractC10041qr.A09;
                    if (i < bArr2.length || !AbstractC10041qr.A00(bArr, bArr2, i2)) {
                        byte[] bArr3 = AbstractC10041qr.A0A;
                        if (i >= bArr3.length && AbstractC10041qr.A00(bArr, bArr3, i2)) {
                            r1 = C10191ri.A06;
                        } else if (i < 6 || (!AbstractC10041qr.A00(bArr, AbstractC10041qr.A05, i2) && !AbstractC10041qr.A00(bArr, AbstractC10041qr.A06, i2))) {
                            byte[] bArr4 = AbstractC10041qr.A02;
                            if (i < bArr4.length || !AbstractC10041qr.A00(bArr, bArr4, i2)) {
                                byte[] bArr5 = AbstractC10041qr.A08;
                                if (i < bArr5.length || !AbstractC10041qr.A00(bArr, bArr5, i2)) {
                                    if (i >= 12 && bArr[3] >= 8 && AbstractC10041qr.A00(bArr, AbstractC10041qr.A07, 4)) {
                                        byte[][] bArr6 = AbstractC10041qr.A0B;
                                        int length = bArr6.length;
                                        int i4 = 0;
                                        while (true) {
                                            if (i4 >= length) {
                                                break;
                                            } else if (AbstractC10041qr.A00(bArr, bArr6[i4], 8)) {
                                                r1 = C10191ri.A03;
                                                break;
                                            } else {
                                                i4++;
                                            }
                                        }
                                    }
                                    if (i < AbstractC10041qr.A01 || (!AbstractC10041qr.A00(bArr, AbstractC10041qr.A03, i2) && !AbstractC10041qr.A00(bArr, AbstractC10041qr.A04, i2))) {
                                        r1 = AnonymousClass1tL.A01;
                                    } else {
                                        r1 = C10191ri.A01;
                                    }
                                } else {
                                    r1 = C10191ri.A04;
                                }
                            } else {
                                r1 = C10191ri.A00;
                            }
                        } else {
                            r1 = C10191ri.A02;
                        }
                    } else {
                        r1 = C10191ri.A05;
                    }
                } else {
                    if (i < 20 || !A002) {
                        z = false;
                    } else {
                        z = true;
                    }
                    AnonymousClass0KU.A01(Boolean.valueOf(z));
                    if (C01180Ma.A00(bArr, 12, C01180Ma.A06)) {
                        r1 = C10191ri.A0B;
                    } else if (C01180Ma.A00(bArr, 12, C01180Ma.A04)) {
                        r1 = C10191ri.A0A;
                    } else if (i < 21 || !C01180Ma.A00(bArr, 12, C01180Ma.A05)) {
                        r1 = AnonymousClass1tL.A01;
                    } else {
                        byte b = bArr[20];
                        if ((b & 2) == 2) {
                            r1 = C10191ri.A07;
                        } else if ((b & 16) == 16) {
                            r1 = C10191ri.A09;
                        } else {
                            r1 = C10191ri.A08;
                        }
                    }
                }
                if (r1 == null || r1 == AnonymousClass1tL.A01) {
                    return AnonymousClass1tL.A01;
                }
                return r1;
            }
            throw null;
        } catch (IOException e) {
            AnonymousClass0KY.A00(e);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public C10031qq() {
        AbstractC10041qr r0 = new AbstractC10041qr();
        this.A01 = r0;
        this.A00 = r0.A00;
    }
}
