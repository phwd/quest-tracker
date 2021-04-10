package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04040gi;
import X.AbstractC04100gp;
import X.AnonymousClass006;
import X.AnonymousClass007;
import X.AnonymousClass0jg;
import X.AnonymousClass0k2;
import X.C03990gZ;
import X.EnumC04010gf;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    public static final long serialVersionUID = 1;
    public final Class<?> _valueClass;

    public static final JsonDeserializer<?> A05(AbstractC04020gg r2, AbstractC04030gh r3, JsonDeserializer<?> jsonDeserializer) throws C03990gZ {
        Object A0S;
        AbstractC04040gi A01 = r2._config.A01();
        if (A01 == null || r3 == null || (A0S = A01.A0S(r3.A3p())) == null) {
            return jsonDeserializer;
        }
        r2.A02(A0S);
        throw null;
    }

    public void A0N(AbstractC04100gp r2, AbstractC04020gg r3, Object obj, String str) throws IOException, AnonymousClass0jg {
        if (obj == null) {
            obj = this._valueClass;
        }
        if (!r3.A0I(EnumC04010gf.FAIL_ON_UNKNOWN_PROPERTIES)) {
            r2.A0Z();
            return;
        }
        A0D();
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        throw null;
    }

    public static final String A06(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        String A0J = r2.A0J();
        if (A0J != null) {
            return A0J;
        }
        throw r3.A07(String.class, r2.A0a());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if ("0".equals(r1) == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r0 = java.lang.Boolean.TRUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r4.A0N() == 0) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean A07(X.AbstractC04100gp r4) throws java.io.IOException, X.AnonymousClass0jg {
        /*
            java.lang.Integer r1 = r4.A0Q()
            java.lang.Integer r0 = X.AnonymousClass007.A01
            if (r1 != r0) goto L_0x0019
            long r3 = r4.A0N()
            r1 = 0
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x002e
        L_0x0012:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0014:
            boolean r0 = r0.booleanValue()
            return r0
        L_0x0019:
            java.lang.String r1 = r4.A0e()
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A07(X.0gp):boolean");
    }

    public final double A0F(AbstractC04100gp r5, AbstractC04020gg r6) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r5.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return r5.A0K();
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r5.A0e().trim();
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
                    throw null;
                } catch (Exception unused2) {
                    throw null;
                }
            }
        } else if (A0a != EnumC04820ji.VALUE_NULL) {
            throw r6.A07(this._valueClass, A0a);
        }
        return OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY;
    }

    public final float A0G(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return r4.A0L();
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r4.A0e().trim();
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
                    throw null;
                } catch (Exception unused2) {
                    throw null;
                }
            }
        } else if (A0a != EnumC04820ji.VALUE_NULL) {
            throw r5.A07(this._valueClass, A0a);
        }
        return 0.0f;
    }

    public final int A0H(AbstractC04100gp r7, AbstractC04020gg r8) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r7.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return r7.A0M();
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r7.A0e().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= LibraryDBContract.VERSION_NOT_INSTALLED && parseLong <= 2147483647L) {
                        return (int) parseLong;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of int (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    sb.toString();
                    try {
                        throw null;
                    } catch (Exception unused) {
                        throw null;
                    }
                } else if (length != 0) {
                    return AnonymousClass0k2.A01(trim);
                }
            } catch (IllegalArgumentException unused2) {
                throw null;
            } catch (Exception unused3) {
                throw null;
            }
        } else if (A0a != EnumC04820ji.VALUE_NULL) {
            throw r8.A07(this._valueClass, A0a);
        }
        return 0;
    }

    public final long A0I(AbstractC04100gp r6, AbstractC04020gg r7) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r6.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return r6.A0N();
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r6.A0e().trim();
            int length = trim.length();
            if (length != 0) {
                if (length > 9) {
                    return Long.parseLong(trim);
                }
                try {
                    return (long) AnonymousClass0k2.A01(trim);
                } catch (IllegalArgumentException unused) {
                    throw null;
                } catch (Exception unused2) {
                    throw null;
                }
            }
        } else if (A0a != EnumC04820ji.VALUE_NULL) {
            throw r7.A07(this._valueClass, A0a);
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        if (r3.A0M() == 0) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Boolean A0J(X.AbstractC04100gp r3, X.AbstractC04020gg r4) throws java.io.IOException, X.AnonymousClass0jg {
        /*
        // Method dump skipped, instructions count: 101
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdDeserializer.A0J(X.0gp, X.0gg):java.lang.Boolean");
    }

    public final Double A0K(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        double A0K;
        double d;
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            A0K = r4.A0K();
        } else {
            if (A0a == EnumC04820ji.VALUE_STRING) {
                String trim = r4.A0e().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0K = Double.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0K = Double.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0K = Double.NEGATIVE_INFINITY;
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
                    } catch (Exception unused2) {
                        throw null;
                    }
                }
            } else if (A0a != EnumC04820ji.VALUE_NULL) {
                throw r5.A07(this._valueClass, A0a);
            }
            return A08();
        }
        return Double.valueOf(A0K);
    }

    public final Integer A0L(AbstractC04100gp r7, AbstractC04020gg r8) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r7.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return Integer.valueOf(r7.A0M());
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r7.A0e().trim();
            try {
                int length = trim.length();
                if (length > 9) {
                    long parseLong = Long.parseLong(trim);
                    if (parseLong >= LibraryDBContract.VERSION_NOT_INSTALLED && parseLong <= 2147483647L) {
                        return Integer.valueOf((int) parseLong);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Overflow: numeric value (");
                    sb.append(trim);
                    sb.append(") out of range of Integer (");
                    sb.append(Integer.MIN_VALUE);
                    sb.append(" - ");
                    sb.append(Integer.MAX_VALUE);
                    sb.append(")");
                    sb.toString();
                    try {
                        throw null;
                    } catch (Exception unused) {
                        throw null;
                    }
                } else if (length == 0) {
                    return A08();
                } else {
                    return Integer.valueOf(AnonymousClass0k2.A01(trim));
                }
            } catch (IllegalArgumentException unused2) {
                throw null;
            } catch (Exception unused3) {
                throw null;
            }
        } else if (A0a == EnumC04820ji.VALUE_NULL) {
            return A08();
        } else {
            throw r8.A07(this._valueClass, A0a);
        }
    }

    public Date A0M(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT) {
            return new Date(r4.A0N());
        }
        if (A0a == EnumC04820ji.VALUE_NULL) {
            return A08();
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            try {
                String trim = r4.A0e().trim();
                if (trim.length() == 0) {
                    return A08();
                }
                return r5.A0E(trim);
            } catch (IllegalArgumentException e) {
                AnonymousClass006.A07("not a valid representation (error: ", e.getMessage(), ")");
                throw null;
            } catch (Exception unused) {
                throw null;
            }
        } else {
            throw r5.A07(this._valueClass, A0a);
        }
    }

    public final boolean A0O(AbstractC04100gp r5, AbstractC04020gg r6) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r5.A0a();
        if (A0a == EnumC04820ji.VALUE_TRUE) {
            return true;
        }
        if (A0a == EnumC04820ji.VALUE_FALSE || A0a == EnumC04820ji.VALUE_NULL) {
            return false;
        }
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT) {
            if (r5.A0Q() != AnonymousClass007.A00) {
                return A07(r5);
            }
            if (r5.A0M() == 0) {
                return false;
            }
            return true;
        } else if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r5.A0e().trim();
            if ("true".equals(trim)) {
                return true;
            }
            if ("false".equals(trim) || trim.length() == 0) {
                return false;
            }
            try {
                throw null;
            } catch (Exception unused) {
                throw null;
            }
        } else {
            throw r6.A07(this._valueClass, A0a);
        }
    }

    public StdDeserializer(AbstractC04000gb r2) {
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
