package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC02590aM;
import X.AnonymousClass006;
import X.AnonymousClass007;
import X.AnonymousClass0KF;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.C06120lz;
import X.C06290mV;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _valueClass;

    public static final JsonDeserializer<?> A05(AbstractC02570aK r2, AbstractC02580aL r3, JsonDeserializer<?> jsonDeserializer) throws AnonymousClass0aG {
        Object A0e;
        AbstractC02590aM A01 = r2._config.A01();
        if (A01 == null || r3 == null || (A0e = A01.A0e(r3.A41())) == null) {
            return jsonDeserializer;
        }
        r2.A05(A0e);
        throw null;
    }

    public void A0N(AnonymousClass0aT r9, AbstractC02570aK r10, Object obj, String str) throws IOException, C05910ld {
        Class<?> cls;
        if (obj == null) {
            obj = this._valueClass;
        }
        if (!r10.A0O(EnumC02560aJ.FAIL_ON_UNKNOWN_PROPERTIES)) {
            r9.A0F();
            return;
        }
        Collection<Object> A0D = A0D();
        AnonymousClass0aT r4 = r10.A00;
        if (obj != null) {
            if (obj instanceof Class) {
                cls = obj;
            } else {
                cls = obj.getClass();
            }
            AnonymousClass0KF r2 = new AnonymousClass0KF(AnonymousClass006.A09("Unrecognized field \"", str, "\" (class ", cls.getName(), "), not marked as ignorable"), r4.A0D(), cls, str, A0D);
            r2.A04(new C06290mV(obj, str));
            throw r2;
        }
        throw new IllegalArgumentException();
    }

    public static final String A06(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        String A0N = r2.A0N();
        if (A0N != null) {
            return A0N;
        }
        throw r3.A0C(String.class, r2.A0G());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (com.oculus.util.constants.OculusConstants.DEFAULT_ENTERPRISE_USER_ID.equals(r1) == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r4.A0B() == 0) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean A07(X.AnonymousClass0aT r4) throws java.io.IOException, X.C05910ld {
        /*
            java.lang.Integer r1 = r4.A0J()
            java.lang.Integer r0 = X.AnonymousClass007.A01
            if (r1 != r0) goto L_0x0019
            long r3 = r4.A0B()
            r1 = 0
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x002e
        L_0x0012:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0014:
            boolean r0 = r0.booleanValue()
            return r0
        L_0x0019:
            java.lang.String r1 = r4.A0P()
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A07(X.0aT):boolean");
    }

    public final double A0F(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
        EnumC05930lf A0G = r6.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            return r6.A03();
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r6.A0P().trim();
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
        } else if (A0G != EnumC05930lf.VALUE_NULL) {
            throw r7.A0C(this._valueClass, A0G);
        }
        return 0.0d;
    }

    public final float A0G(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            return r4.A04();
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r4.A0P().trim();
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
        } else if (A0G != EnumC05930lf.VALUE_NULL) {
            throw r5.A0C(this._valueClass, A0G);
        }
        return 0.0f;
    }

    public final int A0H(AnonymousClass0aT r7, AbstractC02570aK r8) throws IOException, C05910ld {
        EnumC05930lf A0G = r7.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            return r7.A06();
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r7.A0P().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= LibraryDBContract.VERSION_NOT_INSTALLED && parseLong <= 2147483647L) {
                        return (int) parseLong;
                    }
                    Class<?> cls = this._valueClass;
                    throw r8.A0G(trim, cls, "Overflow: numeric value (" + trim + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
                } else if (length != 0) {
                    return C06120lz.A00(trim);
                }
            } catch (IllegalArgumentException unused) {
                throw r8.A0G(trim, this._valueClass, "not a valid int value");
            }
        } else if (A0G != EnumC05930lf.VALUE_NULL) {
            throw r8.A0C(this._valueClass, A0G);
        }
        return 0;
    }

    public final long A0I(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
        EnumC05930lf A0G = r6.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            return r6.A0B();
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r6.A0P().trim();
            int length = trim.length();
            if (length != 0) {
                if (length > 9) {
                    return Long.parseLong(trim);
                }
                try {
                    return (long) C06120lz.A00(trim);
                } catch (IllegalArgumentException unused) {
                    throw r7.A0G(trim, this._valueClass, "not a valid long value");
                }
            }
        } else if (A0G != EnumC05930lf.VALUE_NULL) {
            throw r7.A0C(this._valueClass, A0G);
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r4.A06() == 0) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean A0J(X.AnonymousClass0aT r4, X.AbstractC02570aK r5) throws java.io.IOException, X.C05910ld {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A0J(X.0aT, X.0aK):java.lang.Boolean");
    }

    public final Double A0K(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        double A03;
        double d;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            A03 = r4.A03();
        } else {
            if (A0G == EnumC05930lf.VALUE_STRING) {
                String trim = r4.A0P().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A03 = Double.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A03 = Double.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A03 = Double.NEGATIVE_INFINITY;
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
            } else if (A0G != EnumC05930lf.VALUE_NULL) {
                throw r5.A0C(this._valueClass, A0G);
            }
            return A08();
        }
        return Double.valueOf(A03);
    }

    public final Integer A0L(AnonymousClass0aT r8, AbstractC02570aK r9) throws IOException, C05910ld {
        EnumC05930lf A0G = r8.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(r8.A06());
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r8.A0P().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= LibraryDBContract.VERSION_NOT_INSTALLED && parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    }
                    Class<?> cls = this._valueClass;
                    throw r9.A0G(trim, cls, "Overflow: numeric value (" + trim + ") out of range of Integer (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
                } else if (length == 0) {
                    return A08();
                } else {
                    return Integer.valueOf(C06120lz.A00(trim));
                }
            } catch (IllegalArgumentException unused) {
                throw r9.A0G(trim, this._valueClass, "not a valid Integer value");
            }
        } else if (A0G == EnumC05930lf.VALUE_NULL) {
            return A08();
        } else {
            throw r9.A0C(this._valueClass, A0G);
        }
    }

    public Date A0M(AnonymousClass0aT r6, AbstractC02570aK r7) throws IOException, C05910ld {
        EnumC05930lf A0G = r6.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT) {
            return new Date(r6.A0B());
        }
        if (A0G == EnumC05930lf.VALUE_NULL) {
            return A08();
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            try {
                String trim = r6.A0P().trim();
                if (trim.length() == 0) {
                    return A08();
                }
                return r7.A0L(trim);
            } catch (IllegalArgumentException e) {
                throw r7.A0G(null, this._valueClass, AnonymousClass006.A07("not a valid representation (error: ", e.getMessage(), ")"));
            }
        } else {
            throw r7.A0C(this._valueClass, A0G);
        }
    }

    public final boolean A0O(AnonymousClass0aT r5, AbstractC02570aK r6) throws IOException, C05910ld {
        EnumC05930lf A0G = r5.A0G();
        if (A0G == EnumC05930lf.VALUE_TRUE) {
            return true;
        }
        if (A0G == EnumC05930lf.VALUE_FALSE || A0G == EnumC05930lf.VALUE_NULL) {
            return false;
        }
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT) {
            if (r5.A0J() != AnonymousClass007.A00) {
                return A07(r5);
            }
            if (r5.A06() == 0) {
                return false;
            }
            return true;
        } else if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r5.A0P().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || trim.length() == 0) {
                return false;
            }
            throw r6.A0G(trim, this._valueClass, "only \"true\" or \"false\" recognized");
        } else {
            throw r6.A0C(this._valueClass, A0G);
        }
    }

    public StdDeserializer(AnonymousClass0aI r2) {
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
