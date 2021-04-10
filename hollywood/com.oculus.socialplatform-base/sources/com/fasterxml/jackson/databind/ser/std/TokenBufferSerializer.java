package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC03670oH;
import X.AbstractC04550qd;
import X.AnonymousClass006;
import X.AnonymousClass0OD;
import X.C02310iT;
import X.C03620oC;
import X.C04950rb;
import X.C04960rc;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class TokenBufferSerializer extends StdSerializer<AnonymousClass0OD> {
    public TokenBufferSerializer() {
        super(AnonymousClass0OD.class);
    }

    public static final void A00(AnonymousClass0OD r7, AbstractC02300iS r8) throws IOException, C02310iT {
        C04960rc r2 = r7.A01;
        int i = -1;
        while (true) {
            i++;
            if (i >= 16) {
                r2 = r2.A01;
                if (r2 != null) {
                    i = 0;
                } else {
                    return;
                }
            }
            long j = r2.A00;
            if (i > 0) {
                j >>= i << 2;
            }
            EnumC03640oE r0 = C04960rc.A03[((int) j) & 15];
            if (r0 != null) {
                switch (C04950rb.A00[r0.ordinal()]) {
                    case 1:
                        r8.A0I();
                        continue;
                    case 2:
                        r8.A0F();
                        continue;
                    case 3:
                        r8.A0H();
                        continue;
                    case 4:
                        r8.A0E();
                        continue;
                    case 5:
                        Object obj = r2.A02[i];
                        if (obj instanceof AbstractC03670oH) {
                            r8.A0P((AbstractC03670oH) obj);
                            continue;
                        } else {
                            r8.A0R((String) obj);
                        }
                    case 6:
                        Object obj2 = r2.A02[i];
                        if (obj2 instanceof AbstractC03670oH) {
                            r8.A0Q((AbstractC03670oH) obj2);
                            continue;
                        } else {
                            r8.A0U((String) obj2);
                        }
                    case 7:
                        Object obj3 = r2.A02[i];
                        if (!(obj3 instanceof Integer)) {
                            if (obj3 instanceof BigInteger) {
                                r8.A0W((BigInteger) obj3);
                                continue;
                            } else if (obj3 instanceof Long) {
                                r8.A0N(((Number) obj3).longValue());
                            } else if (obj3 instanceof Short) {
                                r8.A0X(((Number) obj3).shortValue());
                            }
                        }
                        r8.A0M(((Number) obj3).intValue());
                    case 8:
                        Object obj4 = r2.A02[i];
                        if (obj4 instanceof Double) {
                            r8.A0K(((Number) obj4).doubleValue());
                            continue;
                        } else if (obj4 instanceof BigDecimal) {
                            r8.A0V((BigDecimal) obj4);
                        } else if (obj4 instanceof Float) {
                            r8.A0L(((Number) obj4).floatValue());
                        } else if (obj4 != null) {
                            if (obj4 instanceof String) {
                                r8.A0S((String) obj4);
                            } else {
                                throw new C02310iT(AnonymousClass006.A09("Unrecognized value type for VALUE_NUMBER_FLOAT: ", obj4.getClass().getName(), ", can not serialize"));
                            }
                        }
                        break;
                    case 9:
                        r8.A0Y(true);
                        continue;
                    case 10:
                        r8.A0Y(false);
                        continue;
                    case 11:
                        break;
                    case 12:
                        r8.A0C(r2.A02[i]);
                        continue;
                    default:
                        throw new RuntimeException("Internal error: should never end up through this code path");
                }
                r8.A0G();
            } else {
                return;
            }
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final /* bridge */ /* synthetic */ void serialize(AnonymousClass0OD r1, AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C02310iT {
        A00(r1, r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C03620oC {
        AnonymousClass0OD r1 = (AnonymousClass0OD) obj;
        r4.A03(r1, r2);
        A00(r1, r2);
        r4.A06(r1, r2);
    }
}
