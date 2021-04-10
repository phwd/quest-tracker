package X;

import com.oculus.aidl.OVRServiceInterface;
import java.util.Collections;

public final class LJ {
    public static void A00(LH lh, byte b, int i) {
        C0985q3 q3Var;
        if (i > 0) {
            if (b != 2) {
                q3Var = lh;
                if (b != 3) {
                    if (b == 4) {
                        lh.A09();
                        return;
                    } else if (b == 6 || b == 8) {
                        C0985q3.A01((C0985q3) lh);
                        return;
                    } else if (b != 19) {
                        int i2 = 0;
                        switch (b) {
                            case 10:
                                do {
                                } while ((lh.A08() & 128) == 128);
                                return;
                            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                                C0985q3 q3Var2 = (C0985q3) lh;
                                int A01 = C0985q3.A01(q3Var2);
                                C0985q3.A05(q3Var2, A01);
                                if (A01 != 0) {
                                    q3Var2.A0B((byte) 3);
                                    ((LH) q3Var2).A00.A00(new byte[A01], A01);
                                    return;
                                }
                                return;
                            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                                Collections.emptyMap();
                                C0985q3 q3Var3 = (C0985q3) lh;
                                q3Var3.A00.A00(q3Var3.A03);
                                q3Var3.A03 = 0;
                                while (true) {
                                    byte b2 = lh.A0C().A00;
                                    if (b2 == 0) {
                                        L8 l8 = q3Var3.A00;
                                        short[] sArr = l8.A01;
                                        int i3 = l8.A00;
                                        l8.A00 = i3 - 1;
                                        q3Var3.A03 = sArr[i3];
                                        return;
                                    }
                                    A00(lh, b2, i - 1);
                                }
                            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                                LF A0E = lh.A0E();
                                while (true) {
                                    int i4 = A0E.A02;
                                    if (i4 < 0) {
                                        throw new L9("Peeking into a map not supported, likely because it's sized");
                                    } else if (i2 < i4) {
                                        int i5 = i - 1;
                                        A00(lh, A0E.A00, i5);
                                        A00(lh, A0E.A01, i5);
                                        i2++;
                                    } else {
                                        return;
                                    }
                                }
                            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                                LE A0D = lh.A0D();
                                LK lk = new LK(A0D.A00, A0D.A01);
                                while (true) {
                                    int i6 = lk.A01;
                                    if (i6 < 0) {
                                        throw new L9("Peeking into a set not supported, likely because it's sized");
                                    } else if (i2 < i6) {
                                        A00(lh, lk.A00, i - 1);
                                        i2++;
                                    } else {
                                        return;
                                    }
                                }
                            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                                LE A0D2 = lh.A0D();
                                while (true) {
                                    int i7 = A0D2.A01;
                                    if (i7 < 0) {
                                        throw new L9("Peeking into a list not supported, likely because it's sized");
                                    } else if (i2 < i7) {
                                        A00(lh, A0D2.A00, i - 1);
                                        i2++;
                                    } else {
                                        return;
                                    }
                                }
                            default:
                                throw new C0986q4(1, AnonymousClass08.A00("Invalid type encountered during skipping: ", b));
                        }
                    } else {
                        lh.A0A();
                        return;
                    }
                }
            } else {
                C0985q3 q3Var4 = (C0985q3) lh;
                Boolean bool = q3Var4.A02;
                q3Var = q3Var4;
                if (bool != null) {
                    q3Var4.A02 = null;
                    return;
                }
            }
            q3Var.A08();
            return;
        }
        throw new L9("Maximum skip depth exceeded");
    }
}
