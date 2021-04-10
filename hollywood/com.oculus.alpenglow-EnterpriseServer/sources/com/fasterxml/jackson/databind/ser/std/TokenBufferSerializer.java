package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AbstractC05960li;
import X.AnonymousClass006;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C01570Jk;
import X.C02650aW;
import X.C05910ld;
import X.C07290p5;
import X.C07300p6;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public final class TokenBufferSerializer extends StdSerializer<C01570Jk> {
    public TokenBufferSerializer() {
        super(C01570Jk.class);
    }

    public static final void A00(C01570Jk r7, AbstractC02640aV r8) throws IOException, C02650aW {
        int intValue;
        C07300p6 r2 = r7.A01;
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
            EnumC05930lf r0 = C07300p6.A03[((int) j) & 15];
            if (r0 != null) {
                switch (C07290p5.A00[r0.ordinal()]) {
                    case 1:
                        r8.A0F();
                        continue;
                    case 2:
                        r8.A0C();
                        continue;
                    case 3:
                        r8.A0E();
                        continue;
                    case 4:
                        r8.A0B();
                        continue;
                    case 5:
                        Object obj = r2.A02[i];
                        if (obj instanceof AbstractC05960li) {
                            r8.A0N((AbstractC05960li) obj);
                            continue;
                        } else {
                            r8.A0P((String) obj);
                        }
                    case 6:
                        Object obj2 = r2.A02[i];
                        if (obj2 instanceof AbstractC05960li) {
                            r8.A0O((AbstractC05960li) obj2);
                            continue;
                        } else {
                            r8.A0S((String) obj2);
                        }
                    case 7:
                        Object obj3 = r2.A02[i];
                        if (obj3 instanceof Integer) {
                            intValue = ((Integer) obj3).intValue();
                        } else if (obj3 instanceof BigInteger) {
                            r8.A0U((BigInteger) obj3);
                        } else if (obj3 instanceof Long) {
                            r8.A0K(((Long) obj3).longValue());
                        } else if (obj3 instanceof Short) {
                            r8.A0V(((Short) obj3).shortValue());
                        } else {
                            intValue = ((Number) obj3).intValue();
                        }
                        r8.A0J(intValue);
                        continue;
                    case 8:
                        Object obj4 = r2.A02[i];
                        if (obj4 instanceof Double) {
                            r8.A0H(((Double) obj4).doubleValue());
                            continue;
                        } else if (obj4 instanceof BigDecimal) {
                            r8.A0T((BigDecimal) obj4);
                        } else if (obj4 instanceof Float) {
                            r8.A0I(((Float) obj4).floatValue());
                        } else if (obj4 != null) {
                            if (obj4 instanceof String) {
                                r8.A0Q((String) obj4);
                            } else {
                                throw new C02650aW(AnonymousClass006.A07("Unrecognized value type for VALUE_NUMBER_FLOAT: ", obj4.getClass().getName(), ", can not serialize"));
                            }
                        }
                        break;
                    case 9:
                        r8.A0W(true);
                        continue;
                    case 10:
                        r8.A0W(false);
                        continue;
                    case 11:
                        break;
                    case 12:
                        r8.A09(r2.A02[i]);
                        continue;
                    default:
                        throw new RuntimeException("Internal error: should never end up through this code path");
                }
                r8.A0D();
            } else {
                return;
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r2, AnonymousClass0a8 r3, AnonymousClass0o6 r4) throws IOException, C05910ld {
        C01570Jk r1 = (C01570Jk) obj;
        r4.A03(r1, r2);
        A00(r1, r2);
        r4.A06(r1, r2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final /* bridge */ /* synthetic */ void A0D(C01570Jk r1, AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C02650aW {
        A00(r1, r2);
    }
}
