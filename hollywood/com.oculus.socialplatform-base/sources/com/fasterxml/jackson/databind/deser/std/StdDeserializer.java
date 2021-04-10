package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02230iJ;
import X.AbstractC02280iQ;
import X.AnonymousClass006;
import X.AnonymousClass007;
import X.AnonymousClass0P1;
import X.C02180iD;
import X.C03620oC;
import X.C03780oX;
import X.C04030p3;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.oculus.library.database.contract.LibraryDBContract;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _valueClass;

    public static final JsonDeserializer<?> A05(AbstractC02210iH r2, AbstractC02220iI r3, JsonDeserializer<?> jsonDeserializer) throws C02180iD {
        Object A0e;
        AbstractC02230iJ A01 = r2._config.A01();
        if (A01 == null || r3 == null || (A0e = A01.A0e(r3.A4N())) == null) {
            return jsonDeserializer;
        }
        r2.A05(A0e);
        throw new NullPointerException("getInputType");
    }

    public void A0N(AbstractC02280iQ r9, AbstractC02210iH r10, Object obj, String str) throws IOException, C03620oC {
        Class<?> cls;
        if (obj == null) {
            obj = this._valueClass;
        }
        if (!r10.A0P(EnumC02200iG.FAIL_ON_UNKNOWN_PROPERTIES)) {
            r9.A0h();
            return;
        }
        Collection<Object> A0D = A0D();
        AbstractC02280iQ r4 = r10.A00;
        if (obj != null) {
            if (obj instanceof Class) {
                cls = obj;
            } else {
                cls = obj.getClass();
            }
            AnonymousClass0P1 r2 = new AnonymousClass0P1(AnonymousClass006.A0C("Unrecognized field \"", str, "\" (class ", cls.getName(), "), not marked as ignorable"), r4.A0V(), cls, str, A0D);
            r2.A04(new C04030p3(obj, str));
            throw r2;
        }
        throw new IllegalArgumentException();
    }

    public static final String A06(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
        String A0P = r2.A0P();
        if (A0P != null) {
            return A0P;
        }
        throw r3.A0C(String.class, r2.A0i());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if ("0".equals(r1) == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r4.A0U() == 0) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean A07(X.AbstractC02280iQ r4) throws java.io.IOException, X.C03620oC {
        /*
            java.lang.Integer r1 = r4.A0X()
            java.lang.Integer r0 = X.AnonymousClass007.A01
            if (r1 != r0) goto L_0x0019
            long r3 = r4.A0U()
            r1 = 0
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x002e
        L_0x0012:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0014:
            boolean r0 = r0.booleanValue()
            return r0
        L_0x0019:
            java.lang.String r1 = r4.A0m()
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A07(X.0iQ):boolean");
    }

    public final double A0F(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
        EnumC03640oE A0i = r6.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return r6.A0R();
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r6.A0m().trim();
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
                    throw r7.A0G(trim, this._valueClass, "not a valid double value");
                }
            }
        } else if (A0i != EnumC03640oE.VALUE_NULL) {
            throw r7.A0C(this._valueClass, A0i);
        }
        return 0.0d;
    }

    public final float A0G(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return r4.A0S();
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r4.A0m().trim();
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
                    throw r5.A0G(trim, this._valueClass, "not a valid float value");
                }
            }
        } else if (A0i != EnumC03640oE.VALUE_NULL) {
            throw r5.A0C(this._valueClass, A0i);
        }
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    public final int A0H(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
        EnumC03640oE A0i = r7.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return r7.A0T();
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r7.A0m().trim();
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
                    throw r8.A0G(trim, cls, sb.toString());
                } else if (length != 0) {
                    return C03780oX.A01(trim);
                }
            } catch (IllegalArgumentException unused) {
                throw r8.A0G(trim, this._valueClass, "not a valid int value");
            }
        } else if (A0i != EnumC03640oE.VALUE_NULL) {
            throw r8.A0C(this._valueClass, A0i);
        }
        return 0;
    }

    public final long A0I(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
        EnumC03640oE A0i = r6.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return r6.A0U();
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r6.A0m().trim();
            int length = trim.length();
            if (length != 0) {
                if (length > 9) {
                    return Long.parseLong(trim);
                }
                try {
                    return (long) C03780oX.A01(trim);
                } catch (IllegalArgumentException unused) {
                    throw r7.A0G(trim, this._valueClass, "not a valid long value");
                }
            }
        } else if (A0i != EnumC03640oE.VALUE_NULL) {
            throw r7.A0C(this._valueClass, A0i);
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A0T() == 0) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean A0J(X.AbstractC02280iQ r4, X.AbstractC02210iH r5) throws java.io.IOException, X.C03620oC {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A0J(X.0iQ, X.0iH):java.lang.Boolean");
    }

    public final Double A0K(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        double A0R;
        double d;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            A0R = r4.A0R();
        } else {
            if (A0i == EnumC03640oE.VALUE_STRING) {
                String trim = r4.A0m().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0R = Double.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0R = Double.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0R = Double.NEGATIVE_INFINITY;
                    }
                    try {
                        if ("2.2250738585072012e-308".equals(trim)) {
                            d = Double.MIN_VALUE;
                        } else {
                            d = Double.parseDouble(trim);
                        }
                        return Double.valueOf(d);
                    } catch (IllegalArgumentException unused) {
                        throw r5.A0G(trim, this._valueClass, "not a valid Double value");
                    }
                }
            } else if (A0i != EnumC03640oE.VALUE_NULL) {
                throw r5.A0C(this._valueClass, A0i);
            }
            return A08();
        }
        return Double.valueOf(A0R);
    }

    public final Integer A0L(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException, C03620oC {
        EnumC03640oE A0i = r8.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(r8.A0T());
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r8.A0m().trim();
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
                    throw r9.A0G(trim, cls, sb.toString());
                } else if (length == 0) {
                    return A08();
                } else {
                    return Integer.valueOf(C03780oX.A01(trim));
                }
            } catch (IllegalArgumentException unused) {
                throw r9.A0G(trim, this._valueClass, "not a valid Integer value");
            }
        } else if (A0i == EnumC03640oE.VALUE_NULL) {
            return A08();
        } else {
            throw r9.A0C(this._valueClass, A0i);
        }
    }

    public Date A0M(AbstractC02280iQ r6, AbstractC02210iH r7) throws IOException, C03620oC {
        EnumC03640oE A0i = r6.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT) {
            return new Date(r6.A0U());
        }
        if (A0i == EnumC03640oE.VALUE_NULL) {
            return A08();
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            try {
                String trim = r6.A0m().trim();
                if (trim.length() == 0) {
                    return A08();
                }
                return r7.A0M(trim);
            } catch (IllegalArgumentException e) {
                throw r7.A0G(null, this._valueClass, AnonymousClass006.A09("not a valid representation (error: ", e.getMessage(), ")"));
            }
        } else {
            throw r7.A0C(this._valueClass, A0i);
        }
    }

    public final boolean A0O(AbstractC02280iQ r5, AbstractC02210iH r6) throws IOException, C03620oC {
        EnumC03640oE A0i = r5.A0i();
        if (A0i == EnumC03640oE.VALUE_TRUE) {
            return true;
        }
        if (A0i == EnumC03640oE.VALUE_FALSE || A0i == EnumC03640oE.VALUE_NULL) {
            return false;
        }
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT) {
            if (r5.A0X() != AnonymousClass007.A00) {
                return A07(r5);
            }
            if (r5.A0T() == 0) {
                return false;
            }
            return true;
        } else if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r5.A0m().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || trim.length() == 0) {
                return false;
            }
            throw r6.A0G(trim, this._valueClass, "only \"true\" or \"false\" recognized");
        } else {
            throw r6.A0C(this._valueClass, A0i);
        }
    }

    public StdDeserializer(AbstractC02190iF r2) {
        Class<?> cls;
        if (r2 == null) {
            cls = null;
        } else {
            cls = r2._class;
        }
        this._valueClass = cls;
    }

    public StdDeserializer(Class<?> cls) {
        this._valueClass = cls;
    }
}
