package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.EnumC1023qs;
import X.NX;
import X.QE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer implements AbstractC0264Od {
    public static final StringArrayDeserializer A00 = new StringArrayDeserializer();
    public static final long serialVersionUID = -7589512013334920693L;
    public JsonDeserializer _elementDeserializer;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0014, code lost:
        if (r2 != null) goto L_0x0016;
     */
    @Override // X.AbstractC0264Od
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer A1X(X.AbstractC1022qr r4, X.O5 r5) {
        /*
            r3 = this;
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3._elementDeserializer
            com.fasterxml.jackson.databind.JsonDeserializer r2 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r4, r5, r0)
            if (r2 != 0) goto L_0x002d
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            X.2I r0 = r4._config
            X.qt r0 = r0.A03(r1)
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r4.A08(r0, r5)
        L_0x0014:
            if (r2 == 0) goto L_0x0023
        L_0x0016:
            java.lang.Class r1 = r2.getClass()
            java.lang.Class<com.fasterxml.jackson.databind.annotation.JacksonStdImpl> r0 = com.fasterxml.jackson.databind.annotation.JacksonStdImpl.class
            java.lang.annotation.Annotation r0 = r1.getAnnotation(r0)
            if (r0 == 0) goto L_0x0023
            r2 = 0
        L_0x0023:
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3._elementDeserializer
            if (r0 == r2) goto L_0x0038
            com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer r0 = new com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer
            r0.<init>(r2)
            return r0
        L_0x002d:
            boolean r0 = r2 instanceof X.AbstractC0264Od
            if (r0 == 0) goto L_0x0016
            X.Od r2 = (X.AbstractC0264Od) r2
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r2.A1X(r4, r5)
            goto L_0x0014
        L_0x0038:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer.A1X(X.qr, X.O5):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final String[] A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        String A06;
        Object A0C;
        if (!qiVar.A0i()) {
            String str = null;
            if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                String[] strArr = new String[1];
                if (qiVar.A0U() != NX.VALUE_NULL) {
                    str = StdDeserializer.A06(qiVar, qrVar);
                }
                strArr[0] = str;
                return strArr;
            } else if (qiVar.A0U() == NX.VALUE_STRING && qrVar.A0O(EnumC1023qs.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && qiVar.A0p().length() == 0) {
                return null;
            } else {
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            JsonDeserializer jsonDeserializer = this._elementDeserializer;
            if (jsonDeserializer != null) {
                QE A0H = qrVar.A0H();
                Object[] A01 = A0H.A01();
                int i = 0;
                while (true) {
                    NX A0o = qiVar.A0o();
                    if (A0o != NX.END_ARRAY) {
                        if (A0o == NX.VALUE_NULL) {
                            A0C = null;
                        } else {
                            A0C = jsonDeserializer.A0C(qiVar, qrVar);
                        }
                        if (i >= A01.length) {
                            A01 = A0H.A02(A01);
                            i = 0;
                        }
                        A01[i] = A0C;
                        i++;
                    } else {
                        String[] strArr2 = (String[]) A0H.A03(A01, i, String.class);
                        qrVar.A0K(A0H);
                        return strArr2;
                    }
                }
            } else {
                QE A0H2 = qrVar.A0H();
                Object[] A012 = A0H2.A01();
                int i2 = 0;
                while (true) {
                    NX A0o2 = qiVar.A0o();
                    if (A0o2 != NX.END_ARRAY) {
                        if (A0o2 == NX.VALUE_STRING) {
                            A06 = qiVar.A0p();
                        } else if (A0o2 == NX.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(qiVar, qrVar);
                        }
                        if (i2 >= A012.length) {
                            A012 = A0H2.A02(A012);
                            i2 = 0;
                        }
                        A012[i2] = A06;
                        i2++;
                    } else {
                        String[] strArr3 = (String[]) A0H2.A03(A012, i2, String.class);
                        qrVar.A0K(A0H2);
                        return strArr3;
                    }
                }
            }
        }
    }

    public StringArrayDeserializer() {
        super(String[].class);
        this._elementDeserializer = null;
    }

    public StringArrayDeserializer(JsonDeserializer jsonDeserializer) {
        super(String[].class);
        this._elementDeserializer = jsonDeserializer;
    }
}
