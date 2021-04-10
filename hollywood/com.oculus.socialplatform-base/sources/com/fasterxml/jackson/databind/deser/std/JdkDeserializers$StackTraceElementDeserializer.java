package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C02180iD;
import X.C03620oC;
import X.EnumC03640oE;
import java.io.IOException;

public class JdkDeserializers$StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    public static final JdkDeserializers$StackTraceElementDeserializer A00 = new JdkDeserializers$StackTraceElementDeserializer();

    public JdkDeserializers$StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final StackTraceElement A0A(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException, C03620oC {
        EnumC03640oE A0i = r8.A0i();
        if (A0i == EnumC03640oE.START_OBJECT) {
            String str = "";
            String str2 = str;
            String str3 = str;
            int i = -1;
            while (true) {
                EnumC03640oE A0k = r8.A0k();
                if (A0k == EnumC03640oE.END_OBJECT) {
                    return new StackTraceElement(str, str2, str3, i);
                }
                String A0l = r8.A0l();
                if ("className".equals(A0l)) {
                    str = r8.A0m();
                } else if ("fileName".equals(A0l)) {
                    str3 = r8.A0m();
                } else if ("lineNumber".equals(A0l)) {
                    if (A0k.isNumeric()) {
                        i = r8.A0T();
                    } else {
                        StringBuilder sb = new StringBuilder("Non-numeric token (");
                        sb.append(A0k);
                        sb.append(") for property 'lineNumber'");
                        throw C02180iD.A00(r8, sb.toString());
                    }
                } else if ("methodName".equals(A0l)) {
                    str2 = r8.A0m();
                } else if (!"nativeMethod".equals(A0l)) {
                    A0N(r8, r9, this._valueClass, A0l);
                }
            }
        } else {
            throw r9.A0C(this._valueClass, A0i);
        }
    }
}
