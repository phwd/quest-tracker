package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.C0223Wj;
import X.EnumC0470q2;
import X.q0;
import java.io.IOException;

public class JdkDeserializers$StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    public static final JdkDeserializers$StackTraceElementDeserializer A00 = new JdkDeserializers$StackTraceElementDeserializer();

    public JdkDeserializers$StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final StackTraceElement A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.START_OBJECT) {
            String str = "";
            String str2 = str;
            String str3 = str;
            int i = -1;
            while (true) {
                EnumC0470q2 A0b = ww.A0b();
                if (A0b == EnumC0470q2.END_OBJECT) {
                    return new StackTraceElement(str, str2, str3, i);
                }
                String A0c = ww.A0c();
                if ("className".equals(A0c)) {
                    str = ww.A0d();
                } else if ("fileName".equals(A0c)) {
                    str3 = ww.A0d();
                } else if ("lineNumber".equals(A0c)) {
                    if (A0b.isNumeric()) {
                        i = ww.A0L();
                    } else {
                        StringBuilder sb = new StringBuilder("Non-numeric token (");
                        sb.append(A0b);
                        sb.append(") for property 'lineNumber'");
                        throw C0223Wj.A00(ww, sb.toString());
                    }
                } else if ("methodName".equals(A0c)) {
                    str2 = ww.A0d();
                } else if (!"nativeMethod".equals(A0c)) {
                    A0N(ww, wn, this._valueClass, A0c);
                }
            }
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
    }
}
