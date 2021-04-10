package X;

import com.facebook.proxygen.HTTPRequestHandler;
import com.facebook.proxygen.HTTPTransportCallback;
import java.util.Arrays;

/* renamed from: X.cL  reason: case insensitive filesystem */
public final class C0580cL {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = HTTPRequestHandler.SMALL_REQUESTS_BODY_BUFFER_SIZE;
    public int A03 = 7;
    public int A04 = Integer.MAX_VALUE;
    public boolean A05;
    public C0578cJ[] A06 = new C0578cJ[8];
    public final AnonymousClass33 A07;
    public final boolean A08 = true;

    public static void A00(C0580cL cLVar, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C0578cJ[] cJVarArr = cLVar.A06;
            int length = cJVarArr.length;
            while (true) {
                length--;
                i2 = cLVar.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(cJVarArr, i4, cJVarArr, i4 + i3, cLVar.A01);
                    C0578cJ[] cJVarArr2 = cLVar.A06;
                    int i5 = cLVar.A03 + 1;
                    Arrays.fill(cJVarArr2, i5, i5 + i3, (Object) null);
                    cLVar.A03 += i3;
                } else {
                    int i6 = cJVarArr[length].A00;
                    i -= i6;
                    cLVar.A00 -= i6;
                    cLVar.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(cJVarArr, i42, cJVarArr, i42 + i3, cLVar.A01);
            C0578cJ[] cJVarArr22 = cLVar.A06;
            int i52 = cLVar.A03 + 1;
            Arrays.fill(cJVarArr22, i52, i52 + i3, (Object) null);
            cLVar.A03 += i3;
        }
    }

    public C0580cL(AnonymousClass33 r4) {
        this.A07 = r4;
    }

    public static final void A01(C0580cL cLVar, int i, int i2, int i3) {
        if (i < i2) {
            cLVar.A07.A08(i | i3);
            return;
        }
        AnonymousClass33 r2 = cLVar.A07;
        r2.A08(i3 | i2);
        int i4 = i - i2;
        while (i4 >= 128) {
            r2.A08(128 | (i4 & 127));
            i4 >>>= 7;
        }
        r2.A08(i4);
    }

    public static final void A02(C0580cL cLVar, C0603cm cmVar) {
        int A052;
        if (cLVar.A08) {
            long j = 0;
            int i = 0;
            while (true) {
                A052 = cmVar.A05();
                if (i >= A052) {
                    break;
                }
                j += (long) C0588cV.A02[cmVar.A04(i) & 255];
                i++;
            }
            if (((int) ((j + 7) >> 3)) < A052) {
                AnonymousClass33 r4 = new AnonymousClass33();
                int i2 = 0;
                long j2 = 0;
                byte b = 0;
                while (i2 < cmVar.A05()) {
                    int A042 = cmVar.A04(i2) & 255;
                    int i3 = C0588cV.A03[A042];
                    byte b2 = C0588cV.A02[A042];
                    j2 = (j2 << b2) | ((long) i3);
                    int i4 = b + b2;
                    while (i4 >= 8) {
                        i4 = (i4 == 1 ? 1 : 0) - 8;
                        r4.A5m((int) (j2 >> i4));
                    }
                    i2++;
                    b = i4;
                }
                if (b > 0) {
                    r4.A5m((int) (((long) (255 >>> b)) | (j2 << (8 - b))));
                }
                C0603cm cmVar2 = new C0603cm(r4.A4d());
                A01(cLVar, cmVar2.A05(), 127, HTTPTransportCallback.BODY_BYTES_RECEIVED);
                cmVar2.A0D(cLVar.A07);
                return;
            }
        }
        A01(cLVar, cmVar.A05(), 127, 0);
        cmVar.A0D(cLVar.A07);
    }
}
