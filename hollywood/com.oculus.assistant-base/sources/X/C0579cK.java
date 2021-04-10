package X;

import com.facebook.proxygen.HTTPRequestHandler;
import com.facebook.proxygen.HTTPTransportCallback;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.cK  reason: case insensitive filesystem */
public final class C0579cK {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = HTTPRequestHandler.SMALL_REQUESTS_BODY_BUFFER_SIZE;
    public int A03 = 7;
    public C0578cJ[] A04 = new C0578cJ[8];
    public final int A05 = HTTPRequestHandler.SMALL_REQUESTS_BODY_BUFFER_SIZE;
    public final List A06 = new ArrayList();
    public final t4 A07;

    public static final int A00(C0579cK cKVar, int i, int i2) {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int readByte = cKVar.A07.readByte() & 255;
            if ((readByte & HTTPTransportCallback.BODY_BYTES_RECEIVED) == 0) {
                return i2 + (readByte << i4);
            }
            i2 += (readByte & 127) << i4;
            i4 += 7;
        }
    }

    public static void A02(C0579cK cKVar, int i) {
        int i2;
        int i3 = 0;
        if (i > 0) {
            C0578cJ[] cJVarArr = cKVar.A04;
            int length = cJVarArr.length;
            while (true) {
                length--;
                i2 = cKVar.A03;
                if (length < i2 || i <= 0) {
                    int i4 = i2 + 1;
                    System.arraycopy(cJVarArr, i4, cJVarArr, i4 + i3, cKVar.A01);
                    cKVar.A03 += i3;
                } else {
                    int i5 = cJVarArr[length].A00;
                    i -= i5;
                    cKVar.A00 -= i5;
                    cKVar.A01--;
                    i3++;
                }
            }
            int i42 = i2 + 1;
            System.arraycopy(cJVarArr, i42, cJVarArr, i42 + i3, cKVar.A01);
            cKVar.A03 += i3;
        }
    }

    public C0579cK(AbstractC0609cs csVar) {
        this.A07 = new C00222y(csVar);
    }

    public static final C0603cm A01(C0579cK cKVar) {
        t4 t4Var = cKVar.A07;
        int readByte = t4Var.readByte() & 255;
        boolean z = false;
        if ((readByte & HTTPTransportCallback.BODY_BYTES_RECEIVED) == 128) {
            z = true;
        }
        int A002 = A00(cKVar, readByte, 127);
        if (!z) {
            return t4Var.A4f((long) A002);
        }
        C0588cV cVVar = C0588cV.A01;
        byte[] A4e = t4Var.A4e((long) A002);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C0587cU cUVar = cVVar.A00;
        C0587cU cUVar2 = cUVar;
        int i = 0;
        int i2 = 0;
        for (byte b : A4e) {
            i = (i << 8) | (b & 255);
            i2 += 8;
            while (i2 >= 8) {
                cUVar2 = cUVar2.A02[(i >>> (i2 - 8)) & 255];
                if (cUVar2.A02 == null) {
                    byteArrayOutputStream.write(cUVar2.A00);
                    i2 -= cUVar2.A01;
                    cUVar2 = cUVar;
                } else {
                    i2 -= 8;
                }
            }
        }
        while (i2 > 0) {
            C0587cU cUVar3 = cUVar2.A02[(i << (8 - i2)) & 255];
            if (cUVar3.A02 != null || cUVar3.A01 > i2) {
                break;
            }
            byteArrayOutputStream.write(cUVar3.A00);
            i2 -= cUVar3.A01;
            cUVar2 = cUVar;
        }
        return C0603cm.A03(byteArrayOutputStream.toByteArray());
    }

    public static void A03(C0579cK cKVar, C0578cJ cJVar) {
        int i;
        cKVar.A06.add(cJVar);
        int i2 = cJVar.A00;
        int i3 = cKVar.A02;
        if (i2 > i3) {
            Arrays.fill(cKVar.A04, (Object) null);
            cKVar.A03 = cKVar.A04.length - 1;
            i = 0;
            cKVar.A01 = 0;
        } else {
            A02(cKVar, (cKVar.A00 + i2) - i3);
            int i4 = cKVar.A01 + 1;
            C0578cJ[] cJVarArr = cKVar.A04;
            int length = cJVarArr.length;
            if (i4 > length) {
                C0578cJ[] cJVarArr2 = new C0578cJ[(length << 1)];
                System.arraycopy(cJVarArr, 0, cJVarArr2, length, length);
                cKVar.A03 = cKVar.A04.length - 1;
                cKVar.A04 = cJVarArr2;
                cJVarArr = cJVarArr2;
            }
            int i5 = cKVar.A03;
            cKVar.A03 = i5 - 1;
            cJVarArr[i5] = cJVar;
            cKVar.A01++;
            i = cKVar.A00 + i2;
        }
        cKVar.A00 = i;
    }
}
