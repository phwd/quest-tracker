package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.J9;
import X.V4;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements Zy {
    public static final StringArrayDeserializer A00 = new StringArrayDeserializer();
    public static final long serialVersionUID = -7589512013334920693L;
    public JsonDeserializer<String> _elementDeserializer;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        if (r2 != null) goto L_0x0016;
     */
    @Override // X.Zy
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A1g(X.AbstractC0226Wn r4, X.AbstractC0227Wo r5) throws X.C0223Wj {
        /*
            r3 = this;
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r0 = r3._elementDeserializer
            com.fasterxml.jackson.databind.JsonDeserializer r2 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r4, r5, r0)
            if (r2 != 0) goto L_0x002d
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            X.8M r0 = r4._config
            X.Wl r0 = r0.A03(r1)
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r4.A06(r0, r5)
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
            boolean r0 = r2 instanceof X.Zy
            if (r0 == 0) goto L_0x0016
            X.Zy r2 = (X.Zy) r2
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r2.A1g(r4, r5)
            goto L_0x0014
        L_0x0038:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer.A1g(X.Wn, X.Wo):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final String[] A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        String A06;
        String A09;
        if (!ww.A0F()) {
            String str = null;
            if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                if (ww.A0Z() != EnumC0470q2.VALUE_NULL) {
                    str = StdDeserializer.A06(ww, wn);
                }
                return new String[]{str};
            } else if (ww.A0Z() == EnumC0470q2.VALUE_STRING && wn.A0L(EnumC0225Wm.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && ww.A0d().length() == 0) {
                return null;
            } else {
                throw wn.A08(this._valueClass);
            }
        } else {
            JsonDeserializer<String> jsonDeserializer = this._elementDeserializer;
            if (jsonDeserializer != null) {
                J9 A0H = wn.A0H();
                Object[] A01 = A0H.A01();
                int i = 0;
                while (true) {
                    EnumC0470q2 A0a = ww.A0a();
                    if (A0a != EnumC0470q2.END_ARRAY) {
                        if (A0a == EnumC0470q2.VALUE_NULL) {
                            A09 = null;
                        } else {
                            A09 = jsonDeserializer.A09(ww, wn);
                        }
                        if (i >= A01.length) {
                            A01 = A0H.A02(A01);
                            i = 0;
                        }
                        A01[i] = A09;
                        i++;
                    } else {
                        String[] strArr = (String[]) A0H.A03(A01, i, String.class);
                        wn.A0J(A0H);
                        return strArr;
                    }
                }
            } else {
                J9 A0H2 = wn.A0H();
                Object[] A012 = A0H2.A01();
                int i2 = 0;
                while (true) {
                    EnumC0470q2 A0a2 = ww.A0a();
                    if (A0a2 != EnumC0470q2.END_ARRAY) {
                        if (A0a2 == EnumC0470q2.VALUE_STRING) {
                            A06 = ww.A0d();
                        } else if (A0a2 == EnumC0470q2.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(ww, wn);
                        }
                        if (i2 >= A012.length) {
                            A012 = A0H2.A02(A012);
                            i2 = 0;
                        }
                        A012[i2] = A06;
                        i2++;
                    } else {
                        String[] strArr2 = (String[]) A0H2.A03(A012, i2, String.class);
                        wn.A0J(A0H2);
                        return strArr2;
                    }
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A08(ww, wn);
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
