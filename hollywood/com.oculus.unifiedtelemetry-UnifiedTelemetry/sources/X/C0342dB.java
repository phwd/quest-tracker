package X;

import com.squareup.okhttp.internal.framed.Hpack;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.dB  reason: case insensitive filesystem */
public final class C0342dB {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 4096;
    public int A03 = 7;
    public C0343dC[] A04 = new C0343dC[8];
    public final int A05 = 4096;
    public final List<C0343dC> A06 = new ArrayList();
    public final KC A07;

    public static final int A00(C0342dB dBVar, int i, int i2) throws IOException {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int readByte = dBVar.A07.readByte() & 255;
            if ((readByte & 128) == 0) {
                return i2 + (readByte << i4);
            }
            i2 += (readByte & Hpack.PREFIX_7_BITS) << i4;
            i4 += 7;
        }
    }

    public static void A02(C0342dB dBVar, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C0343dC[] dCVarArr = dBVar.A04;
            int length = dCVarArr.length;
            while (true) {
                length--;
                i2 = dBVar.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(dCVarArr, i4, dCVarArr, i4 + i3, dBVar.A01);
                    dBVar.A03 += i3;
                } else {
                    int i5 = dCVarArr[length].A00;
                    i -= i5;
                    dBVar.A00 -= i5;
                    dBVar.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(dCVarArr, i42, dCVarArr, i42 + i3, dBVar.A01);
            dBVar.A03 += i3;
        }
    }

    public C0342dB(AbstractC0312cb cbVar) {
        this.A07 = new AnonymousClass93(cbVar);
    }

    public static final ci A01(C0342dB dBVar) throws IOException {
        KC kc = dBVar.A07;
        int readByte = kc.readByte() & 255;
        boolean z = false;
        if ((readByte & 128) == 128) {
            z = true;
        }
        int A002 = A00(dBVar, readByte, Hpack.PREFIX_7_BITS);
        if (!z) {
            return kc.A4U((long) A002);
        }
        d0 d0Var = d0.A01;
        byte[] A4T = kc.A4T((long) A002);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C0333d1 d1Var = d0Var.A00;
        C0333d1 d1Var2 = d1Var;
        int i = 0;
        int i2 = 0;
        for (byte b : A4T) {
            i = (i << 8) | (b & 255);
            i2 += 8;
            while (i2 >= 8) {
                d1Var2 = d1Var2.A02[(i >>> (i2 - 8)) & 255];
                if (d1Var2.A02 == null) {
                    byteArrayOutputStream.write(d1Var2.A00);
                    i2 -= d1Var2.A01;
                    d1Var2 = d1Var;
                } else {
                    i2 -= 8;
                }
            }
        }
        while (i2 > 0) {
            C0333d1 d1Var3 = d1Var2.A02[(i << (8 - i2)) & 255];
            if (d1Var3.A02 != null || d1Var3.A01 > i2) {
                break;
            }
            byteArrayOutputStream.write(d1Var3.A00);
            i2 -= d1Var3.A01;
            d1Var2 = d1Var;
        }
        return ci.A05(byteArrayOutputStream.toByteArray());
    }

    public static void A03(C0342dB dBVar, C0343dC dCVar) {
        int i;
        dBVar.A06.add(dCVar);
        int i2 = dCVar.A00;
        int i3 = dBVar.A02;
        if (i2 > i3) {
            Arrays.fill(dBVar.A04, (Object) null);
            dBVar.A03 = dBVar.A04.length - 1;
            i = 0;
            dBVar.A01 = 0;
        } else {
            A02(dBVar, (dBVar.A00 + i2) - i3);
            int i4 = dBVar.A01 + 1;
            C0343dC[] dCVarArr = dBVar.A04;
            int length = dCVarArr.length;
            if (i4 > length) {
                C0343dC[] dCVarArr2 = new C0343dC[(length << 1)];
                System.arraycopy(dCVarArr, 0, dCVarArr2, length, length);
                dBVar.A03 = dBVar.A04.length - 1;
                dBVar.A04 = dCVarArr2;
                dCVarArr = dCVarArr2;
            }
            int i5 = dBVar.A03;
            dBVar.A03 = i5 - 1;
            dCVarArr[i5] = dCVar;
            dBVar.A01++;
            i = dBVar.A00 + i2;
        }
        dBVar.A00 = i;
    }
}
