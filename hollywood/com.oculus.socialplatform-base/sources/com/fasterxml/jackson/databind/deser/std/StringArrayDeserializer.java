package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04520qa;
import X.AnonymousClass0rU;
import X.C03620oC;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements AbstractC04230pb {
    public static final StringArrayDeserializer A00 = new StringArrayDeserializer();
    public static final long serialVersionUID = -7589512013334920693L;
    public JsonDeserializer<String> _elementDeserializer;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        if (r2 != null) goto L_0x0016;
     */
    @Override // X.AbstractC04230pb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A2O(X.AbstractC02210iH r4, X.AbstractC02220iI r5) throws X.C02180iD {
        /*
            r3 = this;
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r0 = r3._elementDeserializer
            com.fasterxml.jackson.databind.JsonDeserializer r2 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r4, r5, r0)
            if (r2 != 0) goto L_0x002d
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            X.0HU r0 = r4._config
            X.0iF r0 = r0.A03(r1)
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
            boolean r0 = r2 instanceof X.AbstractC04230pb
            if (r0 == 0) goto L_0x0016
            X.0pb r2 = (X.AbstractC04230pb) r2
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r2.A2O(r4, r5)
            goto L_0x0014
        L_0x0038:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer.A2O(X.0iH, X.0iI):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final String[] A0A(AbstractC02280iQ r7, AbstractC02210iH r8) throws IOException, C03620oC {
        String A06;
        String A0A;
        if (!r7.A0K()) {
            String str = null;
            if (r8.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                if (r7.A0i() != EnumC03640oE.VALUE_NULL) {
                    str = StdDeserializer.A06(r7, r8);
                }
                return new String[]{str};
            } else if (r7.A0i() == EnumC03640oE.VALUE_STRING && r8.A0P(EnumC02200iG.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0m().length() == 0) {
                return null;
            } else {
                throw r8.A0B(this._valueClass);
            }
        } else {
            JsonDeserializer<String> jsonDeserializer = this._elementDeserializer;
            if (jsonDeserializer != null) {
                AnonymousClass0rU A0L = r8.A0L();
                Object[] A01 = A0L.A01();
                int i = 0;
                while (true) {
                    EnumC03640oE A0j = r7.A0j();
                    if (A0j != EnumC03640oE.END_ARRAY) {
                        if (A0j == EnumC03640oE.VALUE_NULL) {
                            A0A = null;
                        } else {
                            A0A = jsonDeserializer.A0A(r7, r8);
                        }
                        if (i >= A01.length) {
                            A01 = A0L.A02(A01);
                            i = 0;
                        }
                        A01[i] = A0A;
                        i++;
                    } else {
                        String[] strArr = (String[]) A0L.A03(A01, i, String.class);
                        r8.A0N(A0L);
                        return strArr;
                    }
                }
            } else {
                AnonymousClass0rU A0L2 = r8.A0L();
                Object[] A012 = A0L2.A01();
                int i2 = 0;
                while (true) {
                    EnumC03640oE A0j2 = r7.A0j();
                    if (A0j2 != EnumC03640oE.END_ARRAY) {
                        if (A0j2 == EnumC03640oE.VALUE_STRING) {
                            A06 = r7.A0m();
                        } else if (A0j2 == EnumC03640oE.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(r7, r8);
                        }
                        if (i2 >= A012.length) {
                            A012 = A0L2.A02(A012);
                            i2 = 0;
                        }
                        A012[i2] = A06;
                        i2++;
                    } else {
                        String[] strArr2 = (String[]) A0L2.A03(A012, i2, String.class);
                        r8.A0N(A0L2);
                        return strArr2;
                    }
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
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
