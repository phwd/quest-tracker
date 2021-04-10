package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.hyperthrift.reflect.GeneratedHyperThriftClassLookup;
import com.oculus.aidl.OVRServiceInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* renamed from: X.Eh  reason: case insensitive filesystem */
public final class C0157Eh {
    public final C0158Ek A00;
    public final LH A01;
    public final GeneratedHyperThriftClassLookup A02;

    private Object A01(byte b, El el) {
        boolean z;
        byte b2 = el.A00;
        int i = 0;
        switch (b2) {
            case 1:
                return null;
            case 2:
                if (b == 2) {
                    C0985q3 q3Var = (C0985q3) this.A01;
                    Boolean bool = q3Var.A02;
                    if (bool != null) {
                        z = bool.booleanValue();
                        q3Var.A02 = null;
                    } else {
                        z = true;
                        if (q3Var.A08() != 1) {
                            z = false;
                        }
                    }
                    return Boolean.valueOf(z);
                }
                break;
            case 3:
                if (b == 3) {
                    return Byte.valueOf(this.A01.A08());
                }
                break;
            case 4:
                if (b == 4) {
                    return Double.valueOf(this.A01.A09());
                }
                break;
            case 6:
                if (b == 6) {
                    int A012 = C0985q3.A01((C0985q3) this.A01);
                    return Short.valueOf((short) ((-(A012 & 1)) ^ (A012 >>> 1)));
                }
                break;
            case 8:
                if (b == 8) {
                    int A013 = C0985q3.A01((C0985q3) this.A01);
                    return Integer.valueOf((-(A013 & 1)) ^ (A013 >>> 1));
                }
                break;
            case 10:
                if (b == 10) {
                    LH lh = this.A01;
                    int i2 = 0;
                    long j = 0;
                    while (true) {
                        byte A08 = lh.A08();
                        j |= ((long) (A08 & Byte.MAX_VALUE)) << i2;
                        if ((A08 & 128) != 128) {
                            return Long.valueOf((-(j & 1)) ^ (j >>> 1));
                        }
                        i2 += 7;
                    }
                }
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                if (b == 11) {
                    C0985q3 q3Var2 = (C0985q3) this.A01;
                    int A014 = C0985q3.A01(q3Var2);
                    C0985q3.A05(q3Var2, A014);
                    if (A014 == 0) {
                        return OacrConstants.AUTO_SPEECH_DOMAIN;
                    }
                    if (-1 >= A014) {
                        return new String((byte[]) null, 0, A014, LT.A00);
                    }
                    q3Var2.A0B((byte) 3);
                    byte[] bArr = new byte[A014];
                    ((LH) q3Var2).A00.A00(bArr, A014);
                    return new String(bArr, LT.A00);
                }
                break;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                if (b == 12) {
                    return A00(this, el.A03);
                }
                break;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                if (b == 13) {
                    LF A0E = this.A01.A0E();
                    byte b3 = A0E.A00;
                    if (b3 == 0 || A0E.A01 == 0) {
                        byte b4 = A0E.A01;
                        if (b3 == 0) {
                            b3 = Eo.A00(el.A01.A00);
                        }
                        if (b4 == 0) {
                            b4 = Eo.A00(el.A02.A00);
                        }
                        A0E = new LF(b3, b4, A0E.A02);
                    }
                    int i3 = A0E.A02;
                    HashMap hashMap = new HashMap(Math.max(0, i3));
                    while (i3 >= 0) {
                        if (i >= i3) {
                            return hashMap;
                        }
                        hashMap.put(A01(A0E.A00, el.A01), A01(A0E.A01, el.A02));
                        i++;
                    }
                    throw new L9("Peeking into a map not supported, likely because it's sized");
                }
                break;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                if (b == 14) {
                    LE A0D = this.A01.A0D();
                    LK lk = new LK(A0D.A00, A0D.A01);
                    int i4 = lk.A01;
                    HashSet hashSet = new HashSet(Math.max(0, i4));
                    while (i4 >= 0) {
                        if (i >= i4) {
                            return hashSet;
                        }
                        hashSet.add(A01(lk.A00, el.A01));
                        i++;
                    }
                    throw new L9("Peeking into a set not supported, likely because it's sized");
                }
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                if (b == 15) {
                    LE A0D2 = this.A01.A0D();
                    int i5 = A0D2.A01;
                    ArrayList arrayList = new ArrayList(Math.max(0, i5));
                    while (i5 >= 0) {
                        if (i >= i5) {
                            return arrayList;
                        }
                        arrayList.add(A01(A0D2.A00, el.A01));
                        i++;
                    }
                    throw new L9("Peeking into a list not supported, likely because it's sized");
                }
                break;
            case 16:
                if (b == 8 || b == 11) {
                    int A015 = C0985q3.A01((C0985q3) this.A01);
                    return Integer.valueOf((-(A015 & 1)) ^ (A015 >>> 1));
                }
            case OVRServiceInterface.Stub.TRANSACTION_startPartyChat /*{ENCODED_INT: 19}*/:
                if (b == 19) {
                    return Float.valueOf(this.A01.A0A());
                }
                break;
            case 20:
                if (b == 11) {
                    C0985q3 q3Var3 = (C0985q3) this.A01;
                    int A016 = C0985q3.A01(q3Var3);
                    C0985q3.A05(q3Var3, A016);
                    if (A016 == 0) {
                        return new byte[0];
                    }
                    q3Var3.A0B((byte) 3);
                    byte[] bArr2 = new byte[A016];
                    ((LH) q3Var3).A00.A00(bArr2, A016);
                    return bArr2;
                }
                break;
        }
        throw new C0156Eg(AnonymousClass08.A02("Expected ", b2, "; got ", b));
    }

    public C0157Eh(C0158Ek ek, LH lh, GeneratedHyperThriftClassLookup generatedHyperThriftClassLookup) {
        this.A02 = generatedHyperThriftClassLookup;
        this.A00 = ek;
        this.A01 = lh;
    }

    /* JADX WARNING: Removed duplicated region for block: B:751:0x0bf0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.hyperthrift.HyperThriftBase A00(X.C0157Eh r18, java.lang.String r19) {
        /*
        // Method dump skipped, instructions count: 4024
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0157Eh.A00(X.Eh, java.lang.String):com.facebook.hyperthrift.HyperThriftBase");
    }
}
