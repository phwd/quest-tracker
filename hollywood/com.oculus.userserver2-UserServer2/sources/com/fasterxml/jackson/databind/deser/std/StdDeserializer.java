package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass7s;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _valueClass;

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if (r3.A00() == 0) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean A04(X.Rn r3) throws java.io.IOException, X.AnonymousClass9r {
        /*
            r2 = this;
            r0 = r3
            X.B3 r0 = (X.B3) r0
            X.9p r1 = r0.A00
            X.9p r0 = X.AnonymousClass9p.VALUE_TRUE
            if (r1 == r0) goto L_0x005a
            X.9p r0 = X.AnonymousClass9p.VALUE_FALSE
            if (r1 == r0) goto L_0x001f
            X.9p r0 = X.AnonymousClass9p.VALUE_NUMBER_INT
            if (r1 != r0) goto L_0x0022
            java.lang.Integer r1 = r3.A05()
            java.lang.Integer r0 = X.AnonymousClass07.A00
            if (r1 != r0) goto L_0x004f
            int r0 = r3.A00()
            if (r0 != 0) goto L_0x005a
        L_0x001f:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            return r0
        L_0x0022:
            X.9p r0 = X.AnonymousClass9p.VALUE_NULL
            if (r1 == r0) goto L_0x0048
            X.9p r0 = X.AnonymousClass9p.VALUE_STRING
            if (r1 != r0) goto L_0x0058
            java.lang.String r0 = r3.A09()
            java.lang.String r1 = r0.trim()
            java.lang.String r0 = "true"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x005a
            java.lang.String r0 = "false"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x001f
            int r0 = r1.length()
            if (r0 != 0) goto L_0x0058
        L_0x0048:
            java.lang.Object r0 = r2.A02()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            return r0
        L_0x004f:
            boolean r0 = A01(r3)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        L_0x0058:
            r0 = 0
            throw r0
        L_0x005a:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A04(X.Rn):java.lang.Boolean");
    }

    public final Double A05(Rn rn) throws IOException, AnonymousClass9r {
        double doubleValue;
        double d;
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT || r1 == AnonymousClass9p.VALUE_NUMBER_FLOAT) {
            doubleValue = rn.A06().doubleValue();
        } else {
            if (r1 == AnonymousClass9p.VALUE_STRING) {
                String trim = rn.A09().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                doubleValue = Double.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            doubleValue = Double.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        doubleValue = Double.NEGATIVE_INFINITY;
                    }
                    try {
                        if ("2.2250738585072012e-308".equals(trim)) {
                            d = Double.MIN_VALUE;
                        } else {
                            d = Double.parseDouble(trim);
                        }
                        return Double.valueOf(d);
                    } catch (IllegalArgumentException unused) {
                        throw null;
                    }
                }
            } else if (r1 != AnonymousClass9p.VALUE_NULL) {
                throw null;
            }
            return A02();
        }
        return Double.valueOf(doubleValue);
    }

    public final Integer A06(Rn rn) throws IOException, AnonymousClass9r {
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT || r1 == AnonymousClass9p.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(rn.A00());
        }
        if (r1 == AnonymousClass9p.VALUE_STRING) {
            String trim = rn.A09().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong < -2147483648L) {
                        throw null;
                    } else if (parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    } else {
                        throw null;
                    }
                } else if (length == 0) {
                    return A02();
                } else {
                    return Integer.valueOf(AnonymousClass7s.A00(trim));
                }
            } catch (IllegalArgumentException unused) {
                throw null;
            }
        } else if (r1 == AnonymousClass9p.VALUE_NULL) {
            return A02();
        } else {
            throw null;
        }
    }

    public Date A07(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT) {
            return new Date(rn.A06().longValue());
        }
        if (r1 == AnonymousClass9p.VALUE_NULL) {
            return A02();
        }
        if (r1 == AnonymousClass9p.VALUE_STRING) {
            try {
                if (rn.A09().trim().length() == 0) {
                    return A02();
                }
                throw null;
            } catch (IllegalArgumentException e) {
                e.getMessage();
                throw null;
            }
        } else {
            throw null;
        }
    }

    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
        if (com.oculus.util.constants.OculusConstants.DEFAULT_ENTERPRISE_USER_ID.equals(r1) == false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        if (r4.A06().longValue() == 0) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean A01(X.Rn r4) throws java.io.IOException, X.AnonymousClass9r {
        /*
            java.lang.Integer r1 = r4.A05()
            java.lang.Integer r0 = X.AnonymousClass07.A01
            if (r1 != r0) goto L_0x001d
            java.lang.Number r0 = r4.A06()
            long r3 = r0.longValue()
            r1 = 0
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x0032
        L_0x0016:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0018:
            boolean r0 = r0.booleanValue()
            return r0
        L_0x001d:
            java.lang.String r1 = r4.A09()
            java.lang.String r0 = "0.0"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0016
            java.lang.String r0 = "0"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0032
            goto L_0x0016
        L_0x0032:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A01(X.Rn):boolean");
    }

    public void A08(Rn rn, AbstractC0122Rd rd, Object obj, String str) throws IOException, AnonymousClass9r {
        throw null;
    }
}
