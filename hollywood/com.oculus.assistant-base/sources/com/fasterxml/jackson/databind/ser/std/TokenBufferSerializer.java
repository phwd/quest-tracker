package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1012qg;
import X.AnonymousClass08;
import X.C1011qf;
import X.JD;
import X.NX;
import X.Na;
import X.QJ;
import X.QK;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.oculus.aidl.OVRServiceInterface;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class TokenBufferSerializer extends StdSerializer {
    public TokenBufferSerializer() {
        super(JD.class);
    }

    public static final void A00(JD jd, AbstractC1012qg qgVar) {
        QK qk = jd.A02;
        int i = -1;
        while (true) {
            i++;
            if (i >= 16) {
                qk = qk.A01;
                if (qk != null) {
                    i = 0;
                } else {
                    return;
                }
            }
            long j = qk.A00;
            if (i > 0) {
                j >>= i << 2;
            }
            NX nx = QK.A03[((int) j) & 15];
            if (nx != null) {
                switch (QJ.A00[nx.ordinal()]) {
                    case 1:
                        qgVar.A0C();
                        continue;
                    case 2:
                        qgVar.A09();
                        continue;
                    case 3:
                        qgVar.A0B();
                        continue;
                    case 4:
                        qgVar.A08();
                        continue;
                    case 5:
                        Object obj = qk.A02[i];
                        if (obj instanceof Na) {
                            qgVar.A0J((Na) obj);
                            continue;
                        } else {
                            qgVar.A0M((String) obj);
                        }
                    case 6:
                        Object obj2 = qk.A02[i];
                        if (obj2 instanceof Na) {
                            qgVar.A0K((Na) obj2);
                            continue;
                        } else {
                            qgVar.A0P((String) obj2);
                        }
                    case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                        Object obj3 = qk.A02[i];
                        if (!(obj3 instanceof Integer)) {
                            if (obj3 instanceof BigInteger) {
                                qgVar.A0R((BigInteger) obj3);
                                continue;
                            } else if (obj3 instanceof Long) {
                                qgVar.A0H(((Number) obj3).longValue());
                            } else if (obj3 instanceof Short) {
                                qgVar.A0S(((Number) obj3).shortValue());
                            }
                        }
                        qgVar.A0G(((Number) obj3).intValue());
                    case 8:
                        Object obj4 = qk.A02[i];
                        if (obj4 instanceof Double) {
                            qgVar.A0E(((Number) obj4).doubleValue());
                            continue;
                        } else if (obj4 instanceof BigDecimal) {
                            qgVar.A0Q((BigDecimal) obj4);
                        } else if (obj4 instanceof Float) {
                            qgVar.A0F(((Number) obj4).floatValue());
                        } else if (obj4 != null) {
                            if (obj4 instanceof String) {
                                qgVar.A0N((String) obj4);
                            } else {
                                throw new C1011qf(AnonymousClass08.A05("Unrecognized value type for VALUE_NUMBER_FLOAT: ", obj4.getClass().getName(), ", can not serialize"));
                            }
                        }
                        break;
                    case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                        qgVar.A0T(true);
                        continue;
                    case 10:
                        qgVar.A0T(false);
                        continue;
                    case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                        break;
                    case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                        qgVar.A0L(qk.A02[i]);
                        continue;
                    default:
                        throw new RuntimeException("Internal error: should never end up through this code path");
                }
                qgVar.A0A();
            } else {
                return;
            }
        }
    }
}
