package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C06440n5;
import X.EnumC04010gf;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements AbstractC05430l6 {
    public static final StringArrayDeserializer A00 = new StringArrayDeserializer();
    public static final long serialVersionUID = -7589512013334920693L;
    public JsonDeserializer<String> _elementDeserializer;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        if (r2 != null) goto L_0x0016;
     */
    @Override // X.AbstractC05430l6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A21(X.AbstractC04020gg r4, X.AbstractC04030gh r5) throws X.C03990gZ {
        /*
            r3 = this;
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r0 = r3._elementDeserializer
            com.fasterxml.jackson.databind.JsonDeserializer r2 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r4, r5, r0)
            if (r2 != 0) goto L_0x002d
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            X.08X r0 = r4._config
            X.0gb r0 = r0.A03(r1)
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r4.A05(r0, r5)
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
            boolean r0 = r2 instanceof X.AbstractC05430l6
            if (r0 == 0) goto L_0x0016
            X.0l6 r2 = (X.AbstractC05430l6) r2
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r2.A21(r4, r5)
            goto L_0x0014
        L_0x0038:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer.A21(X.0gg, X.0gh):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final String[] A09(AbstractC04100gp r7, AbstractC04020gg r8) throws IOException, AnonymousClass0jg {
        String A06;
        String A09;
        if (!r7.A0G()) {
            String str = null;
            if (r8.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                String[] strArr = new String[1];
                if (r7.A0a() != EnumC04820ji.VALUE_NULL) {
                    str = StdDeserializer.A06(r7, r8);
                }
                strArr[0] = str;
                return strArr;
            } else if (r7.A0a() == EnumC04820ji.VALUE_STRING && r8.A0I(EnumC04010gf.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && r7.A0e().length() == 0) {
                return null;
            } else {
                throw null;
            }
        } else {
            JsonDeserializer<String> jsonDeserializer = this._elementDeserializer;
            if (jsonDeserializer != null) {
                C06440n5 A0D = r8.A0D();
                Object[] A01 = A0D.A01();
                int i = 0;
                while (true) {
                    EnumC04820ji A0b = r7.A0b();
                    if (A0b != EnumC04820ji.END_ARRAY) {
                        if (A0b == EnumC04820ji.VALUE_NULL) {
                            A09 = null;
                        } else {
                            A09 = jsonDeserializer.A09(r7, r8);
                        }
                        if (i >= A01.length) {
                            A01 = A0D.A02(A01);
                            i = 0;
                        }
                        A01[i] = A09;
                        i++;
                    } else {
                        String[] strArr2 = (String[]) A0D.A03(A01, i, String.class);
                        r8.A0F(A0D);
                        return strArr2;
                    }
                }
            } else {
                C06440n5 A0D2 = r8.A0D();
                Object[] A012 = A0D2.A01();
                int i2 = 0;
                while (true) {
                    EnumC04820ji A0b2 = r7.A0b();
                    if (A0b2 != EnumC04820ji.END_ARRAY) {
                        if (A0b2 == EnumC04820ji.VALUE_STRING) {
                            A06 = r7.A0e();
                        } else if (A0b2 == EnumC04820ji.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(r7, r8);
                        }
                        if (i2 >= A012.length) {
                            A012 = A0D2.A02(A012);
                            i2 = 0;
                        }
                        A012[i2] = A06;
                        i2++;
                    } else {
                        String[] strArr3 = (String[]) A0D2.A03(A012, i2, String.class);
                        r8.A0F(A0D2);
                        return strArr3;
                    }
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
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
