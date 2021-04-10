package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.C03990gZ;
import X.EnumC04820ji;
import java.io.IOException;

public class JdkDeserializers$StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    public static final JdkDeserializers$StackTraceElementDeserializer A00 = new JdkDeserializers$StackTraceElementDeserializer();

    public JdkDeserializers$StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final StackTraceElement A09(AbstractC04100gp r8, AbstractC04020gg r9) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r8.A0a();
        if (A0a == EnumC04820ji.START_OBJECT) {
            String str = "";
            String str2 = str;
            String str3 = str;
            int i = -1;
            while (true) {
                EnumC04820ji A0c = r8.A0c();
                if (A0c == EnumC04820ji.END_OBJECT) {
                    return new StackTraceElement(str, str2, str3, i);
                }
                String A0d = r8.A0d();
                if ("className".equals(A0d)) {
                    str = r8.A0e();
                } else if ("fileName".equals(A0d)) {
                    str3 = r8.A0e();
                } else if ("lineNumber".equals(A0d)) {
                    if (A0c.isNumeric()) {
                        i = r8.A0M();
                    } else {
                        StringBuilder sb = new StringBuilder("Non-numeric token (");
                        sb.append(A0c);
                        sb.append(") for property 'lineNumber'");
                        throw C03990gZ.A00(r8, sb.toString());
                    }
                } else if ("methodName".equals(A0d)) {
                    str2 = r8.A0e();
                } else if (!"nativeMethod".equals(A0d)) {
                    A0N(r8, r9, this._valueClass, A0d);
                }
            }
        } else {
            throw r9.A07(this._valueClass, A0a);
        }
    }
}
