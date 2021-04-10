package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.oculus.aidl.OVRServiceInterface;

public abstract class LH {
    public LQ A00;

    public final byte A08() {
        C0985q3 q3Var = (C0985q3) this;
        LQ lq = ((LH) q3Var).A00;
        byte[] bArr = q3Var.A04;
        lq.A00(bArr, 1);
        return bArr[0];
    }

    public final double A09() {
        C0985q3 q3Var = (C0985q3) this;
        LQ lq = ((LH) q3Var).A00;
        byte[] bArr = q3Var.A04;
        lq.A00(bArr, 8);
        return Double.longBitsToDouble(((((long) bArr[0]) & 255) << 56) | ((((long) bArr[1]) & 255) << 48) | ((((long) bArr[2]) & 255) << 40) | ((((long) bArr[3]) & 255) << 32) | ((((long) bArr[4]) & 255) << 24) | ((((long) bArr[5]) & 255) << 16) | ((((long) bArr[6]) & 255) << 8) | (255 & ((long) bArr[7])));
    }

    public final float A0A() {
        C0985q3 q3Var = (C0985q3) this;
        LQ lq = ((LH) q3Var).A00;
        byte[] bArr = q3Var.A04;
        lq.A00(bArr, 4);
        return Float.intBitsToFloat((bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8));
    }

    public final LD A0C() {
        int i;
        Boolean bool;
        C0985q3 q3Var = (C0985q3) this;
        byte A08 = q3Var.A08();
        if (A08 == 0) {
            return C0985q3.A07;
        }
        short s = (short) ((A08 & 240) >> 4);
        if (s == 0) {
            int A01 = C0985q3.A01(q3Var);
            i = (-(A01 & 1)) ^ (A01 >>> 1);
        } else {
            i = q3Var.A03 + s;
        }
        int i2 = A08 & 15;
        byte b = (byte) i2;
        LD ld = new LD(OacrConstants.AUTO_SPEECH_DOMAIN, C0985q3.A00(b), (short) i);
        if (i2 == 1 || i2 == 2) {
            if (b == 1) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            q3Var.A02 = bool;
        }
        q3Var.A03 = ld.A03;
        return ld;
    }

    public final LE A0D() {
        C0985q3 q3Var = (C0985q3) this;
        byte A08 = q3Var.A08();
        int i = (A08 >> 4) & 15;
        if (i == 15) {
            i = C0985q3.A01(q3Var);
        }
        C0985q3.A04(q3Var, i);
        byte A002 = C0985q3.A00(A08);
        q3Var.A0B(A002);
        return new LE(A002, i);
    }

    public final LF A0E() {
        byte A08;
        C0985q3 q3Var = (C0985q3) this;
        int A01 = C0985q3.A01(q3Var);
        C0985q3.A04(q3Var, A01);
        if (A01 == 0) {
            A08 = 0;
        } else {
            A08 = q3Var.A08();
        }
        byte A002 = C0985q3.A00((byte) (A08 >> 4));
        byte A003 = C0985q3.A00((byte) (A08 & 15));
        if (A01 > 0) {
            q3Var.A0B(A002);
            q3Var.A0B(A003);
        }
        return new LF(A002, A003, A01);
    }

    public final int A0B(byte b) {
        int i;
        if (!(!(this instanceof C0985q3) || (i = b & 15) == 2 || i == 3 || i == 4 || i == 6 || i == 8 || i == 19)) {
            switch (i) {
                case 10:
                case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                case 16:
                    break;
                default:
                    throw new C0986q4(1, AnonymousClass08.A00("Unexpected data type ", (byte) i));
            }
        }
        return 1;
    }

    public LH(LQ lq) {
        this.A00 = lq;
    }
}
