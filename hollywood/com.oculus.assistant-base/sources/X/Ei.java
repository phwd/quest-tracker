package X;

import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.oculus.aidl.OVRServiceInterface;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

public final class Ei {
    public final C0158Ek A00;
    public final LH A01;

    public static void A00(Ei ei, String str, HyperThriftBase hyperThriftBase) {
        SystraceMessage.A00("HyperThriftWriter.write").A00("type", str);
        try {
            En A002 = ei.A00.A00(str);
            Object[] objArr = hyperThriftBase.A03;
            LH lh = ei.A01;
            new LP();
            C0985q3 q3Var = (C0985q3) lh;
            q3Var.A00.A00(q3Var.A03);
            q3Var.A03 = 0;
            Em[] emArr = A002.A02;
            int length = emArr.length;
            for (int i = 0; i < length; i++) {
                Em em = emArr[i];
                Object obj = objArr[i];
                if (!(obj == null || obj == HyperThriftBase.A04)) {
                    String str2 = em.A01;
                    if (str2 == null) {
                        str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
                    }
                    El el = em.A00;
                    LD ld = new LD(str2, Eo.A00(el.A00), em.A02);
                    if (ld.A00 == 2) {
                        q3Var.A01 = ld;
                    } else {
                        C0985q3.A07(q3Var, ld, (byte) -1);
                    }
                    ei.A01(el, obj);
                }
            }
            C0985q3.A02(q3Var, (byte) 0);
            L8 l8 = q3Var.A00;
            short[] sArr = l8.A01;
            int i2 = l8.A00;
            l8.A00 = i2 - 1;
            q3Var.A03 = sArr[i2];
        } finally {
            Systrace.A00(4);
        }
    }

    private void A01(El el, Object obj) {
        LH lh;
        int i;
        int i2;
        switch (el.A00) {
            case 2:
                LH lh2 = this.A01;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                C0985q3 q3Var = (C0985q3) lh2;
                LD ld = q3Var.A01;
                byte b = 1;
                if (ld != null) {
                    if (!booleanValue) {
                        b = 2;
                    }
                    C0985q3.A07(q3Var, ld, b);
                    q3Var.A01 = null;
                    return;
                }
                if (!booleanValue) {
                    b = 2;
                }
                C0985q3.A02(q3Var, b);
                return;
            case 3:
                C0985q3.A02((C0985q3) this.A01, ((Number) obj).byteValue());
                return;
            case 4:
                C0985q3 q3Var2 = (C0985q3) this.A01;
                long doubleToLongBits = Double.doubleToLongBits(((Number) obj).doubleValue());
                byte[] bArr = q3Var2.A04;
                bArr[0] = (byte) ((int) ((doubleToLongBits >> 56) & 255));
                bArr[1] = (byte) ((int) ((doubleToLongBits >> 48) & 255));
                bArr[2] = (byte) ((int) ((doubleToLongBits >> 40) & 255));
                bArr[3] = (byte) ((int) ((doubleToLongBits >> 32) & 255));
                bArr[4] = (byte) ((int) ((doubleToLongBits >> 24) & 255));
                bArr[5] = (byte) ((int) ((doubleToLongBits >> 16) & 255));
                bArr[6] = (byte) ((int) ((doubleToLongBits >> 8) & 255));
                bArr[7] = (byte) ((int) (doubleToLongBits & 255));
                ((LH) q3Var2).A00.A01(bArr, 0, 8);
                return;
            case 5:
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipStatus /*{ENCODED_INT: 17}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipMicrophoneMuted /*{ENCODED_INT: 18}*/:
            default:
                return;
            case 6:
                lh = this.A01;
                i = ((Number) obj).shortValue();
                break;
            case 8:
            case 16:
                lh = this.A01;
                i = ((Number) obj).intValue();
                break;
            case 10:
                LH lh3 = this.A01;
                long longValue = ((Number) obj).longValue();
                C0985q3 q3Var3 = (C0985q3) lh3;
                long j = (longValue >> 63) ^ (longValue << 1);
                int i3 = 0;
                while (true) {
                    int i4 = ((-128 & j) > 0 ? 1 : ((-128 & j) == 0 ? 0 : -1));
                    byte[] bArr2 = q3Var3.A04;
                    int i5 = i3 + 1;
                    if (i4 == 0) {
                        bArr2[i3] = (byte) ((int) j);
                        ((LH) q3Var3).A00.A01(bArr2, 0, i5);
                        return;
                    }
                    bArr2[i3] = (byte) ((int) ((127 & j) | 128));
                    j >>>= 7;
                    i3 = i5;
                }
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                C0985q3 q3Var4 = (C0985q3) this.A01;
                byte[] bytes = ((String) obj).getBytes(LT.A00);
                int length = bytes.length;
                C0985q3.A06(q3Var4, length);
                ((LH) q3Var4).A00.A01(bytes, 0, length);
                return;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                A00(this, el.A03, (HyperThriftBase) obj);
                return;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                AbstractMap abstractMap = (AbstractMap) obj;
                int size = abstractMap.size();
                LH lh4 = this.A01;
                El el2 = el.A01;
                byte A002 = Eo.A00(el2.A00);
                El el3 = el.A02;
                LF lf = new LF(A002, Eo.A00(el3.A00), size);
                C0985q3 q3Var5 = (C0985q3) lh4;
                int i6 = lf.A02;
                if (i6 == 0) {
                    i2 = 0;
                } else {
                    C0985q3.A06(q3Var5, i6);
                    byte b2 = lf.A00;
                    byte[] bArr3 = C0985q3.A09;
                    i2 = bArr3[lf.A01] | (bArr3[b2] << 4);
                }
                C0985q3.A02(q3Var5, (byte) i2);
                for (Map.Entry entry : abstractMap.entrySet()) {
                    A01(el2, entry.getKey());
                    A01(el3, entry.getValue());
                }
                return;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                AbstractCollection abstractCollection = (AbstractCollection) obj;
                int size2 = abstractCollection.size();
                LH lh5 = this.A01;
                El el4 = el.A01;
                LK lk = new LK(Eo.A00(el4.A00), size2);
                C0985q3.A03((C0985q3) lh5, lk.A00, lk.A01);
                Iterator it = abstractCollection.iterator();
                while (it.hasNext()) {
                    A01(el4, it.next());
                }
                return;
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                AbstractList abstractList = (AbstractList) obj;
                int size3 = abstractList.size();
                LH lh6 = this.A01;
                El el5 = el.A01;
                LE le = new LE(Eo.A00(el5.A00), size3);
                C0985q3.A03((C0985q3) lh6, le.A00, le.A01);
                for (int i7 = 0; i7 < size3; i7++) {
                    A01(el5, abstractList.get(i7));
                }
                return;
            case OVRServiceInterface.Stub.TRANSACTION_startPartyChat /*{ENCODED_INT: 19}*/:
                C0985q3 q3Var6 = (C0985q3) this.A01;
                int floatToIntBits = Float.floatToIntBits(((Number) obj).floatValue());
                byte[] bArr4 = q3Var6.A04;
                bArr4[0] = (byte) ((floatToIntBits >> 24) & 255);
                bArr4[1] = (byte) ((floatToIntBits >> 16) & 255);
                bArr4[2] = (byte) ((floatToIntBits >> 8) & 255);
                bArr4[3] = (byte) (floatToIntBits & 255);
                ((LH) q3Var6).A00.A01(bArr4, 0, 4);
                return;
            case 20:
                byte[] bArr5 = (byte[]) obj;
                C0985q3 q3Var7 = (C0985q3) this.A01;
                int length2 = bArr5.length;
                C0985q3.A06(q3Var7, length2);
                ((LH) q3Var7).A00.A01(bArr5, 0, length2);
                return;
        }
        C0985q3.A06((C0985q3) lh, (i >> 31) ^ (i << 1));
    }

    public Ei(C0158Ek ek, LH lh) {
        this.A00 = ek;
        this.A01 = lh;
    }
}
