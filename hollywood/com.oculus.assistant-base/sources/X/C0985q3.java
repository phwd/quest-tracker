package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.proxygen.HTTPTransportCallback;
import com.oculus.aidl.OVRServiceInterface;

/* renamed from: X.q3  reason: case insensitive filesystem */
public final class C0985q3 extends LH {
    public static final LD A07 = new LD(OacrConstants.AUTO_SPEECH_DOMAIN, (byte) 0, 0);
    public static final LP A08 = new LP(OacrConstants.AUTO_SPEECH_DOMAIN);
    public static final byte[] A09;
    public L8 A00 = new L8();
    public LD A01 = null;
    public Boolean A02 = null;
    public short A03 = 0;
    public final byte[] A04 = new byte[10];
    public final long A05;
    public final long A06;

    public static int A01(C0985q3 q3Var) {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte A082 = q3Var.A08();
            i |= (A082 & Byte.MAX_VALUE) << i2;
            if ((A082 & 128) != 128) {
                return i;
            }
            i2 += 7;
        }
    }

    public static void A04(C0985q3 q3Var, int i) {
        if (i >= 0) {
            long j = q3Var.A05;
            if (j > 0 && ((long) i) > j) {
                throw new C0986q4(3, String.format("Container length %s exceeded max allowed %s", Integer.valueOf(i), Long.valueOf(j)));
            }
            return;
        }
        throw new C0986q4(2, AnonymousClass08.A00("Negative length: ", i));
    }

    public static void A05(C0985q3 q3Var, int i) {
        if (i >= 0) {
            long j = q3Var.A06;
            if (j > 0 && ((long) i) > j) {
                throw new C0986q4(3, String.format("String/binary length %s exceeded max allowed %s", Integer.valueOf(i), Long.valueOf(j)));
            }
            return;
        }
        throw new C0986q4(2, AnonymousClass08.A00("Negative length: ", i));
    }

    public static void A06(C0985q3 q3Var, int i) {
        int i2 = 0;
        while ((i & -128) != 0) {
            q3Var.A04[i2] = (byte) ((i & 127) | HTTPTransportCallback.BODY_BYTES_RECEIVED);
            i >>>= 7;
            i2++;
        }
        byte[] bArr = q3Var.A04;
        bArr[i2] = (byte) i;
        ((LH) q3Var).A00.A01(bArr, 0, i2 + 1);
    }

    public static void A07(C0985q3 q3Var, LD ld, byte b) {
        int i;
        if (b == -1) {
            b = A09[ld.A00];
        }
        short s = ld.A03;
        short s2 = q3Var.A03;
        if (s <= s2 || (i = s - s2) > 15) {
            A02(q3Var, b);
            A06(q3Var, (s >> 31) ^ (s << 1));
        } else {
            A02(q3Var, (byte) (b | (i << 4)));
        }
        q3Var.A03 = s;
    }

    static {
        byte[] bArr = new byte[20];
        A09 = bArr;
        bArr[0] = 0;
        bArr[2] = 1;
        bArr[3] = 3;
        bArr[6] = 4;
        bArr[8] = 5;
        bArr[10] = 6;
        bArr[4] = 7;
        bArr[11] = 8;
        bArr[15] = 9;
        bArr[14] = 10;
        bArr[13] = 11;
        bArr[12] = 12;
        bArr[19] = 13;
    }

    public static byte A00(byte b) {
        byte b2 = (byte) (b & 15);
        switch (b2) {
            case 0:
                return 0;
            case 1:
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 8;
            case 6:
                return 10;
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                return 4;
            case 8:
                return 11;
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                return 15;
            case 10:
                return 14;
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                return 13;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                return 12;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                return 19;
            default:
                throw new C0986q4(AnonymousClass08.A00("don't know what type: ", b2));
        }
    }

    public static void A02(C0985q3 q3Var, byte b) {
        byte[] bArr = q3Var.A04;
        bArr[0] = b;
        ((LH) q3Var).A00.A01(bArr, 0, 1);
    }

    public static final void A03(C0985q3 q3Var, byte b, int i) {
        if (i <= 14) {
            A02(q3Var, (byte) (A09[b] | (i << 4)));
            return;
        }
        A02(q3Var, (byte) (A09[b] | 240));
        A06(q3Var, i);
    }

    public C0985q3(LQ lq, long j, long j2) {
        super(lq);
        this.A06 = j;
        this.A05 = j2;
    }
}
