package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.AnonymousClass06;
import X.AnonymousClass07;
import X.C0223Wj;
import X.C0434i2;
import X.CF;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.Wp;
import X.p2;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _valueClass;

    public static final JsonDeserializer<?> A05(AbstractC0226Wn wn, AbstractC0227Wo wo, JsonDeserializer<?> jsonDeserializer) throws C0223Wj {
        Object A0T;
        Wp A01 = wn._config.A01();
        if (A01 == null || wo == null || (A0T = A01.A0T(wo.A2d())) == null) {
            return jsonDeserializer;
        }
        wn.A03(A0T);
        throw null;
    }

    public void A0N(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, String str) throws IOException, q0 {
        Class<?> cls;
        if (obj == null) {
            obj = this._valueClass;
        }
        if (!wn.A0L(EnumC0225Wm.FAIL_ON_UNKNOWN_PROPERTIES)) {
            ww.A0Y();
            return;
        }
        Collection<Object> A0D = A0D();
        AbstractC0232Ww ww2 = wn.A00;
        if (obj != null) {
            if (obj instanceof Class) {
                cls = obj;
            } else {
                cls = obj.getClass();
            }
            CF cf = new CF(AnonymousClass06.A07("Unrecognized field \"", str, "\" (class ", cls.getName(), "), not marked as ignorable"), ww2.A0N(), cls, str, A0D);
            C0434i2 i2Var = new C0434i2(obj, str);
            LinkedList<C0434i2> linkedList = cf._path;
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                cf._path = linkedList;
            }
            if (linkedList.size() < 1000) {
                linkedList.addFirst(i2Var);
            }
            throw cf;
        }
        throw new IllegalArgumentException();
    }

    public static final String A06(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        String A0I = ww.A0I();
        if (A0I != null) {
            return A0I;
        }
        throw wn.A09(String.class, ww.A0Z());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if ("0".equals(r1) == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r4.A0M() == 0) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean A07(X.AbstractC0232Ww r4) throws java.io.IOException, X.q0 {
        /*
            java.lang.Integer r1 = r4.A0P()
            java.lang.Integer r0 = X.AnonymousClass07.A01
            if (r1 != r0) goto L_0x0019
            long r3 = r4.A0M()
            r1 = 0
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x002e
        L_0x0012:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0014:
            boolean r0 = r0.booleanValue()
            return r0
        L_0x0019:
            java.lang.String r1 = r4.A0d()
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A07(X.Ww):boolean");
    }

    public final double A0F(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            return ww.A0J();
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
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
                    throw wn.A0D(trim, this._valueClass, "not a valid double value");
                }
            }
        } else if (A0Z != EnumC0470q2.VALUE_NULL) {
            throw wn.A09(this._valueClass, A0Z);
        }
        return 0.0d;
    }

    public final float A0G(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            return ww.A0K();
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
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
                    throw wn.A0D(trim, this._valueClass, "not a valid float value");
                }
            }
        } else if (A0Z != EnumC0470q2.VALUE_NULL) {
            throw wn.A09(this._valueClass, A0Z);
        }
        return 0.0f;
    }

    public final int A0H(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            return ww.A0L();
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= LibraryDBContract.VERSION_NOT_INSTALLED && parseLong <= 2147483647L) {
                        return (int) parseLong;
                    }
                    Class<?> cls = this._valueClass;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of int (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    throw wn.A0D(trim, cls, sb.toString());
                } else if (length != 0) {
                    return p2.A01(trim);
                }
            } catch (IllegalArgumentException unused) {
                throw wn.A0D(trim, this._valueClass, "not a valid int value");
            }
        } else if (A0Z != EnumC0470q2.VALUE_NULL) {
            throw wn.A09(this._valueClass, A0Z);
        }
        return 0;
    }

    public final long A0I(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            return ww.A0M();
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            int length = trim.length();
            if (length != 0) {
                if (length > 9) {
                    return Long.parseLong(trim);
                }
                try {
                    return (long) p2.A01(trim);
                } catch (IllegalArgumentException unused) {
                    throw wn.A0D(trim, this._valueClass, "not a valid long value");
                }
            }
        } else if (A0Z != EnumC0470q2.VALUE_NULL) {
            throw wn.A09(this._valueClass, A0Z);
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A0L() == 0) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean A0J(X.AbstractC0232Ww r4, X.AbstractC0226Wn r5) throws java.io.IOException, X.q0 {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A0J(X.Ww, X.Wn):java.lang.Boolean");
    }

    public final Double A0K(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        double A0J;
        double d;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            A0J = ww.A0J();
        } else {
            if (A0Z == EnumC0470q2.VALUE_STRING) {
                String trim = ww.A0d().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0J = Double.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0J = Double.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0J = Double.NEGATIVE_INFINITY;
                    }
                    try {
                        if ("2.2250738585072012e-308".equals(trim)) {
                            d = Double.MIN_VALUE;
                        } else {
                            d = Double.parseDouble(trim);
                        }
                        return Double.valueOf(d);
                    } catch (IllegalArgumentException unused) {
                        throw wn.A0D(trim, this._valueClass, "not a valid Double value");
                    }
                }
            } else if (A0Z != EnumC0470q2.VALUE_NULL) {
                throw wn.A09(this._valueClass, A0Z);
            }
            return A08();
        }
        return Double.valueOf(A0J);
    }

    public final Integer A0L(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(ww.A0L());
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= LibraryDBContract.VERSION_NOT_INSTALLED && parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    }
                    Class<?> cls = this._valueClass;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of Integer (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    throw wn.A0D(trim, cls, sb.toString());
                } else if (length == 0) {
                    return A08();
                } else {
                    return Integer.valueOf(p2.A01(trim));
                }
            } catch (IllegalArgumentException unused) {
                throw wn.A0D(trim, this._valueClass, "not a valid Integer value");
            }
        } else if (A0Z == EnumC0470q2.VALUE_NULL) {
            return A08();
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
    }

    public Date A0M(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT) {
            return new Date(ww.A0M());
        }
        if (A0Z == EnumC0470q2.VALUE_NULL) {
            return A08();
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            try {
                String trim = ww.A0d().trim();
                if (trim.length() == 0) {
                    return A08();
                }
                return wn.A0I(trim);
            } catch (IllegalArgumentException e) {
                throw wn.A0D(null, this._valueClass, AnonymousClass06.A05("not a valid representation (error: ", e.getMessage(), ")"));
            }
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
    }

    public final boolean A0O(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_TRUE) {
            return true;
        }
        if (A0Z == EnumC0470q2.VALUE_FALSE || A0Z == EnumC0470q2.VALUE_NULL) {
            return false;
        }
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT) {
            if (ww.A0P() != AnonymousClass07.A00) {
                return A07(ww);
            }
            if (ww.A0L() == 0) {
                return false;
            }
            return true;
        } else if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || trim.length() == 0) {
                return false;
            }
            throw wn.A0D(trim, this._valueClass, "only \"true\" or \"false\" recognized");
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
    }

    public StdDeserializer(AbstractC0224Wl wl) {
        Class<?> cls;
        if (wl == null) {
            cls = null;
        } else {
            cls = wl._class;
        }
        this._valueClass = cls;
    }

    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }
}
