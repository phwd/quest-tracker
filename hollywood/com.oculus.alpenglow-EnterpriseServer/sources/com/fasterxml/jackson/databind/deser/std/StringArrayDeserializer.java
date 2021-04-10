package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC06520n2;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.C07230oy;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements AbstractC06520n2 {
    public static final StringArrayDeserializer A00 = new StringArrayDeserializer();
    public static final long serialVersionUID = -7589512013334920693L;
    public JsonDeserializer<String> _elementDeserializer;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        if (r2 != null) goto L_0x0016;
     */
    @Override // X.AbstractC06520n2
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A1w(X.AbstractC02570aK r4, X.AbstractC02580aL r5) throws X.AnonymousClass0aG {
        /*
            r3 = this;
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r0 = r3._elementDeserializer
            com.fasterxml.jackson.databind.JsonDeserializer r2 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r4, r5, r0)
            if (r2 != 0) goto L_0x002d
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            X.0Fu r0 = r4._config
            X.0aI r0 = r0.A03(r1)
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r4.A09(r0, r5)
        L_0x0014:
            if (r2 == 0) goto L_0x0023
        L_0x0016:
            java.lang.Class r1 = r2.getClass()
            java.lang.Class<com.fasterxml.jackson.databind.annotation.JacksonStdImpl> r0 = com.fasterxml.jackson.databind.annotation.JacksonStdImpl.class
            java.lang.annotation.Annotation r0 = r1.getAnnotation(r0)
            if (r0 == 0) goto L_0x0023
            r2 = 0
        L_0x0023:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r0 = r3._elementDeserializer
            if (r0 == r2) goto L_0x0038
            com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer r0 = new com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer
            r0.<init>(r2)
            return r0
        L_0x002d:
            boolean r0 = r2 instanceof X.AbstractC06520n2
            if (r0 == 0) goto L_0x0016
            X.0n2 r2 = (X.AbstractC06520n2) r2
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r2.A1w(r4, r5)
            goto L_0x0014
        L_0x0038:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer.A1w(X.0aK, X.0aL):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final String[] A09(AnonymousClass0aT r7, AbstractC02570aK r8) throws IOException, C05910ld {
        String A06;
        String A09;
        if (!r7.A0V()) {
            String str = null;
            if (r8.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                if (r7.A0G() != EnumC05930lf.VALUE_NULL) {
                    str = StdDeserializer.A06(r7, r8);
                }
                return new String[]{str};
            } else if (r7.A0G() == EnumC05930lf.VALUE_STRING && r8.A0O(EnumC02560aJ.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0P().length() == 0) {
                return null;
            } else {
                throw r8.A0B(this._valueClass);
            }
        } else {
            JsonDeserializer<String> jsonDeserializer = this._elementDeserializer;
            if (jsonDeserializer != null) {
                C07230oy A0K = r8.A0K();
                Object[] A01 = A0K.A01();
                int i = 0;
                while (true) {
                    EnumC05930lf A0a = r7.A0a();
                    if (A0a != EnumC05930lf.END_ARRAY) {
                        if (A0a == EnumC05930lf.VALUE_NULL) {
                            A09 = null;
                        } else {
                            A09 = jsonDeserializer.A09(r7, r8);
                        }
                        if (i >= A01.length) {
                            A01 = A0K.A02(A01);
                            i = 0;
                        }
                        A01[i] = A09;
                        i++;
                    } else {
                        String[] strArr = (String[]) A0K.A03(A01, i, String.class);
                        r8.A0M(A0K);
                        return strArr;
                    }
                }
            } else {
                C07230oy A0K2 = r8.A0K();
                Object[] A012 = A0K2.A01();
                int i2 = 0;
                while (true) {
                    EnumC05930lf A0a2 = r7.A0a();
                    if (A0a2 != EnumC05930lf.END_ARRAY) {
                        if (A0a2 == EnumC05930lf.VALUE_STRING) {
                            A06 = r7.A0P();
                        } else if (A0a2 == EnumC05930lf.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(r7, r8);
                        }
                        if (i2 >= A012.length) {
                            A012 = A0K2.A02(A012);
                            i2 = 0;
                        }
                        A012[i2] = A06;
                        i2++;
                    } else {
                        String[] strArr2 = (String[]) A0K2.A03(A012, i2, String.class);
                        r8.A0M(A0K2);
                        return strArr2;
                    }
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A08(r2, r3);
    }

    public StringArrayDeserializer() {
        super(String[].class);
        this._elementDeserializer = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public StringArrayDeserializer(JsonDeserializer<?> jsonDeserializer) {
        super(String[].class);
        this._elementDeserializer = jsonDeserializer;
    }
}
