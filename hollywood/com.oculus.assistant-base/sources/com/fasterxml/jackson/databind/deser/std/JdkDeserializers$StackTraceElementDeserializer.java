package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C1025qv;
import X.NX;
import X.VD;
import com.facebook.assistant.oacr.OacrConstants;

public class JdkDeserializers$StackTraceElementDeserializer extends StdScalarDeserializer {
    public static final JdkDeserializers$StackTraceElementDeserializer A00 = new JdkDeserializers$StackTraceElementDeserializer();

    public JdkDeserializers$StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final StackTraceElement A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0V;
        NX A0U = qiVar.A0U();
        if (A0U == NX.START_OBJECT) {
            String str = OacrConstants.AUTO_SPEECH_DOMAIN;
            String str2 = str;
            String str3 = str;
            int i = -1;
            while (true) {
                if (!(qiVar instanceof VD)) {
                    A0V = qiVar.A0o();
                    if (A0V == NX.FIELD_NAME) {
                        A0V = qiVar.A0o();
                    }
                } else {
                    A0V = ((VD) qiVar).A00.A0V();
                }
                if (A0V == NX.END_OBJECT) {
                    return new StackTraceElement(str, str2, str3, i);
                }
                String A0b = qiVar.A0b();
                if ("className".equals(A0b)) {
                    str = qiVar.A0p();
                } else if ("fileName".equals(A0b)) {
                    str3 = qiVar.A0p();
                } else if ("lineNumber".equals(A0b)) {
                    if (A0V.isNumeric()) {
                        i = qiVar.A0J();
                    } else {
                        StringBuilder sb = new StringBuilder("Non-numeric token (");
                        sb.append(A0V);
                        sb.append(") for property 'lineNumber'");
                        throw C1025qv.A00(qiVar, sb.toString());
                    }
                } else if ("methodName".equals(A0b)) {
                    str2 = qiVar.A0p();
                } else if (!"nativeMethod".equals(A0b)) {
                    A0L(qiVar, qrVar, this._valueClass, A0b);
                }
            }
        } else {
            throw qrVar.A0A(this._valueClass, A0U);
        }
    }
}
