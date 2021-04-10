package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1020qp;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.AbstractC1034r7;
import X.AbstractC1044rJ;
import X.AbstractC1072ro;
import X.AnonymousClass08;
import X.AnonymousClass09;
import X.EnumC1023qs;
import X.NX;
import X.Nj;
import X.O5;
import X.Rw;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public abstract class StdDeserializer extends JsonDeserializer implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class _valueClass;

    public static final JsonDeserializer A05(AbstractC1022qr qrVar, O5 o5, JsonDeserializer jsonDeserializer) {
        JsonDeserialize jsonDeserialize;
        Class contentConverter;
        AbstractC1020qp A01 = qrVar._config.A01();
        if (!(A01 == null || o5 == null)) {
            AbstractC1044rJ A2e = o5.A2e();
            if (!(!(A01 instanceof Rw) || (jsonDeserialize = (JsonDeserialize) A2e.A0L(JsonDeserialize.class)) == null || (contentConverter = jsonDeserialize.contentConverter()) == AbstractC1072ro.class || contentConverter == null)) {
                qrVar.A06(contentConverter);
                throw new NullPointerException("getInputType");
            }
        }
        return jsonDeserializer;
    }

    public void A0L(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, String str) {
        if (obj == null) {
            obj = this._valueClass;
        }
        if (!qrVar.A0O(EnumC1023qs.FAIL_ON_UNKNOWN_PROPERTIES)) {
            qiVar.A0T();
            return;
        }
        if (this instanceof BeanDeserializerBase) {
            ArrayList arrayList = new ArrayList();
            Iterator it = ((BeanDeserializerBase) this)._beanProperties.iterator();
            while (it.hasNext()) {
                arrayList.add(((AbstractC1034r7) it.next())._propName);
            }
        }
        if (obj != null) {
            throw new NullPointerException("getCurrentLocation");
        }
        throw new IllegalArgumentException();
    }

    public static final String A06(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        String A0n = qiVar.A0n();
        if (A0n != null) {
            return A0n;
        }
        throw qrVar.A0A(String.class, qiVar.A0U());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if ("0".equals(r1) == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r4.A0O() == 0) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean A07(X.AbstractC1014qi r4) {
        /*
            java.lang.Integer r1 = r4.A0X()
            java.lang.Integer r0 = X.AnonymousClass09.A01
            if (r1 != r0) goto L_0x0019
            long r3 = r4.A0O()
            r1 = 0
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x002e
        L_0x0012:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0014:
            boolean r0 = r0.booleanValue()
            return r0
        L_0x0019:
            java.lang.String r1 = r4.A0p()
            java.lang.String r0 = "0.0"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0012
            java.lang.String r0 = "0"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002e
            goto L_0x0012
        L_0x002e:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A07(X.qi):boolean");
    }

    public final double A0D(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            return qiVar.A0F();
        }
        if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            if (trim.length() != 0) {
                char charAt = trim.charAt(0);
                if (charAt != '-') {
                    if (charAt != 'I') {
                        if (charAt == 'N' && "NaN".equals(trim)) {
                            return Double.NaN;
                        }
                    } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Double.POSITIVE_INFINITY;
                    }
                } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Double.NEGATIVE_INFINITY;
                }
                try {
                    if ("2.2250738585072012e-308".equals(trim)) {
                        return Double.MIN_VALUE;
                    }
                    return Double.parseDouble(trim);
                } catch (IllegalArgumentException unused) {
                    qrVar.A0L(this._valueClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else if (A0U != NX.VALUE_NULL) {
            throw qrVar.A0A(this._valueClass, A0U);
        }
        return 0.0d;
    }

    public final float A0E(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            return qiVar.A0I();
        }
        if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            if (trim.length() != 0) {
                char charAt = trim.charAt(0);
                if (charAt != '-') {
                    if (charAt != 'I') {
                        if (charAt == 'N' && "NaN".equals(trim)) {
                            return Float.NaN;
                        }
                    } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                        return Float.POSITIVE_INFINITY;
                    }
                } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                    return Float.NEGATIVE_INFINITY;
                }
                try {
                    return Float.parseFloat(trim);
                } catch (IllegalArgumentException unused) {
                    qrVar.A0L(this._valueClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else if (A0U != NX.VALUE_NULL) {
            throw qrVar.A0A(this._valueClass, A0U);
        }
        return 0.0f;
    }

    public final int A0F(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            return qiVar.A0J();
        }
        if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return (int) parseLong;
                    }
                    Class cls = this._valueClass;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of int (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    sb.toString();
                    qrVar.A0L(cls);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else if (length != 0) {
                    return Nj.A01(trim);
                }
            } catch (IllegalArgumentException unused) {
                qrVar.A0L(this._valueClass);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else if (A0U != NX.VALUE_NULL) {
            throw qrVar.A0A(this._valueClass, A0U);
        }
        return 0;
    }

    public final long A0G(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            return qiVar.A0O();
        }
        if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            int length = trim.length();
            if (length != 0) {
                if (length > 9) {
                    return Long.parseLong(trim);
                }
                try {
                    return (long) Nj.A01(trim);
                } catch (IllegalArgumentException unused) {
                    qrVar.A0L(this._valueClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        } else if (A0U != NX.VALUE_NULL) {
            throw qrVar.A0A(this._valueClass, A0U);
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r3.A0J() == 0) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean A0H(X.AbstractC1014qi r3, X.AbstractC1022qr r4) {
        /*
        // Method dump skipped, instructions count: 110
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A0H(X.qi, X.qr):java.lang.Boolean");
    }

    public final Double A0I(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        double A0F;
        double d;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            A0F = qiVar.A0F();
        } else {
            if (A0U == NX.VALUE_STRING) {
                String trim = qiVar.A0p().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0F = Double.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0F = Double.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0F = Double.NEGATIVE_INFINITY;
                    }
                    try {
                        if ("2.2250738585072012e-308".equals(trim)) {
                            d = Double.MIN_VALUE;
                        } else {
                            d = Double.parseDouble(trim);
                        }
                        return Double.valueOf(d);
                    } catch (IllegalArgumentException unused) {
                        qrVar.A0L(this._valueClass);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else if (A0U != NX.VALUE_NULL) {
                throw qrVar.A0A(this._valueClass, A0U);
            }
            return (Double) A08();
        }
        return Double.valueOf(A0F);
    }

    public final Integer A0J(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(qiVar.A0J());
        }
        if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= -2147483648L && parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    }
                    Class cls = this._valueClass;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of Integer (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    sb.toString();
                    qrVar.A0L(cls);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                } else if (length == 0) {
                    return (Integer) A08();
                } else {
                    return Integer.valueOf(Nj.A01(trim));
                }
            } catch (IllegalArgumentException unused) {
                qrVar.A0L(this._valueClass);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else if (A0U == NX.VALUE_NULL) {
            return (Integer) A08();
        } else {
            throw qrVar.A0A(this._valueClass, A0U);
        }
    }

    public Date A0K(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT) {
            return new Date(qiVar.A0O());
        }
        if (A0U == NX.VALUE_NULL) {
            return (Date) A08();
        }
        if (A0U == NX.VALUE_STRING) {
            try {
                String trim = qiVar.A0p().trim();
                if (trim.length() == 0) {
                    return (Date) A08();
                }
                return qrVar.A0I(trim);
            } catch (IllegalArgumentException e) {
                Class cls = this._valueClass;
                AnonymousClass08.A05("not a valid representation (error: ", e.getMessage(), ")");
                qrVar.A0L(cls);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            throw qrVar.A0A(this._valueClass, A0U);
        }
    }

    public final boolean A0M(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_TRUE) {
            return true;
        }
        if (A0U == NX.VALUE_FALSE || A0U == NX.VALUE_NULL) {
            return false;
        }
        if (A0U == NX.VALUE_NUMBER_INT) {
            if (qiVar.A0X() != AnonymousClass09.A00) {
                return A07(qiVar);
            }
            if (qiVar.A0J() == 0) {
                return false;
            }
            return true;
        } else if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || trim.length() == 0) {
                return false;
            }
            qrVar.A0L(this._valueClass);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            throw qrVar.A0A(this._valueClass, A0U);
        }
    }

    public StdDeserializer(AbstractC1024qt qtVar) {
        Class cls;
        if (qtVar == null) {
            cls = null;
        } else {
            cls = qtVar._class;
        }
        this._valueClass = cls;
    }

    public StdDeserializer(Class cls) {
        this._valueClass = cls;
    }
}
