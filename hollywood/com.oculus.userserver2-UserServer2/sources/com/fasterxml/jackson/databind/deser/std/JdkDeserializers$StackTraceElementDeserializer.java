package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.RW;
import X.Rn;
import java.io.IOException;

public class JdkDeserializers$StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    public static final JdkDeserializers$StackTraceElementDeserializer A00 = new JdkDeserializers$StackTraceElementDeserializer();

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final StackTraceElement A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (((B3) rn).A00 == AnonymousClass9p.START_OBJECT) {
            String str = "";
            String str2 = str;
            String str3 = str;
            int i = -1;
            while (true) {
                AnonymousClass9p A04 = rn.A04();
                if (A04 == AnonymousClass9p.FIELD_NAME) {
                    A04 = rn.A04();
                }
                if (A04 == AnonymousClass9p.END_OBJECT) {
                    return new StackTraceElement(str, str2, str3, i);
                }
                String A08 = rn.A08();
                if ("className".equals(A08)) {
                    str = rn.A09();
                } else if ("fileName".equals(A08)) {
                    str3 = rn.A09();
                } else if ("lineNumber".equals(A08)) {
                    if (A04.isNumeric()) {
                        i = rn.A00();
                    } else {
                        StringBuilder sb = new StringBuilder("Non-numeric token (");
                        sb.append(A04);
                        sb.append(") for property 'lineNumber'");
                        throw RW.A00(rn, sb.toString());
                    }
                } else if ("methodName".equals(A08)) {
                    str2 = rn.A09();
                } else if (!"nativeMethod".equals(A08)) {
                    A08(rn, rd, this._valueClass, A08);
                }
            }
        } else {
            throw null;
        }
    }

    public JdkDeserializers$StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }
}
